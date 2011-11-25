package edu.ncsu.csc573.project.controllayer.hashspacemanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class Digest {
	private Logger logger;
	private static int[] map = new int[128];
	public static final String MASTER_FILE = "MASTER.txt";
	public static final int NUM_BITS_BYTE = 8;

	public static final int MAX_CLASS_CUBED = (37 * 37 * 37 + 1);
	int digest[] = new int[MAX_CLASS_CUBED];

	public Digest() {
		logger = Logger.getLogger(Digest.class);
	}

	/*
	 * 37 classes. A-Z form 1 to 26 0-9 form 27 to 36 all other chars form class
	 * 0 The max number of useful trigrams is reduced from 37^3 to 74XX after
	 * parsing ASPELL dictionary dump. Refer the file "MASTER" for the subset of
	 * strings used MASTER is a text file. We have chosen 8192 as upper bound
	 * since its a multiple of 32 and 1024
	 */
	public static final int MAX_BITS_DIGEST = 8192;

	/*
	 * Func: get_digest_for_file Returns: OK or ERROR based on the successful
	 * Parameters: 1) Filename - Expected to be the current directory 2) any
	 * array of MAX_BITS_DIGEST bits EX: if an integer is 32 bits array should
	 * be for (MAX_BITS_DIGEST/32) integers if it is a byte(char) array. it
	 * shoule be for (MAX_BITS_DIGEST/8) characters Refer test.c
	 * function:testfile() for sample usage
	 */

	public boolean get_digest_for_file(String filename, byte[] arr) {
		// logger.debug("Inside get_digest_for_file " +filename + " " + new
		// String(arr));
		// File fd; /* fd of the master file obtained from the Aspell Dictionary
		// */
		byte buffer;
		int numRead = 0;
		byte first, second, third;
		boolean master = false;
		int tmp_digest = -1;
		InputStream is = null;
		// int *a = (int *)arr;

		try {
			if (null == arr) {
				master = true;
				is = ClassLoader.getSystemResourceAsStream(filename);
			} else {

				is = new FileInputStream(new File(filename));
			}
		} catch (FileNotFoundException e) {
			logger.error(filename + " is not found ", e);
			return false;
		}

		// fd = new File(filename);
     	if (is == null) {
				logger.error("Failed to open File " + filename
						+ " for creating digest\n");
				return false;
		}

		first = '\0';
		second = '\0';
		third = '\0';
		try {
			while ((buffer = (byte) is.read()) != -1) {
				if (!can_include_char_in_digest(buffer)) {
					continue;
				}
				/* New line or a space */
				if (stop_digest_this_word(buffer)) {
					/* Handle for last trigram of the line */
					if (numRead <= 1) {
						second = third = '\0';
					} else if (numRead == 2) {
						/*
						 * We have 2 characters. Make the third NULL.Do Digest
						 * for it
						 */
						third = '\0';
					} else {
						/*
						 * We have 2 characters followed by space. Make the
						 * third NULL.Do Digest for it
						 */
						first = second;
						second = third;
						third = '\0';
					}
					tmp_digest = get_hash(first, second, third);
					if (master == true) {
						digest[tmp_digest] = 0;

					} else {
						tmp_digest = digest[tmp_digest];
						arr[tmp_digest / NUM_BITS_BYTE] = (byte) (arr[tmp_digest
								/ NUM_BITS_BYTE] | (1 << (NUM_BITS_BYTE - 1 - tmp_digest
								% NUM_BITS_BYTE)));
					}
					numRead = 0;
					first = second = third = '\0';
				} else {
					numRead++;
					if (numRead == 1) {
						first = (byte) buffer;
					} else if (numRead == 2) {
						second = (byte) buffer;
					} else if (numRead == 3) {
						third = (byte) buffer;
						tmp_digest = get_hash(first, second, third);
						if (master == true) {
							digest[tmp_digest] = 0;
						} else {
							tmp_digest = digest[tmp_digest];
							arr[tmp_digest / NUM_BITS_BYTE] = (byte) ((arr[tmp_digest
									/ NUM_BITS_BYTE]) | (1 << (NUM_BITS_BYTE - 1 - tmp_digest
									% NUM_BITS_BYTE)));
						}
					} else {
						/*
						 * dont increment numRead. Keep at 3 for processing.
						 * Useful when seeing a space or a newline
						 */
						numRead = 3;
						first = second;
						second = third;
						third = (byte) buffer;
						tmp_digest = get_hash(first, second, third);
						if (master == true) {
							digest[tmp_digest] = 0;
						} else {
							tmp_digest = digest[tmp_digest];
							arr[tmp_digest / NUM_BITS_BYTE] = (byte) (arr[tmp_digest
									/ NUM_BITS_BYTE] | (1 << (NUM_BITS_BYTE - 1 - tmp_digest
									% NUM_BITS_BYTE)));
						}
					}
				}
			}
		} catch (IOException e1) {
			logger.error("Failed to read file" + filename, e1);
			e1.printStackTrace();
		}
		try {
			is.close();
		} catch (Exception e) {
			logger.error("Unable to close input stream ", e);
		}
		return true;
	}

	/*
	 * Func: get_digest_for_query Returns: OK or ERROR based on the successful
	 * Parameters: 1) query - string to be queried. 2) query_len - strlen+1 of
	 * the query 2) any array of MAX_BITS_DIGEST bits EX: if an integer is 32
	 * bits. array should be for (MAX_BITS_DIGEST/32) integers if it is a
	 * byte(char) array. it shoule be for (MAX_BITS_DIGEST/8) characters Refer
	 * test.c function:testquery() for sample usage
	 */
	public boolean get_digest_for_query(byte[] query, byte[] arr) {

		int query_len = query.length;
		byte buffer;
		int numRead = 0;
		byte first, second, third;
		int tmp_digest = -1;
		int i;

		// int *a = (int *)arr;

		first = second = third = '\0';
		for (i = 0; i < query_len; i++) {

			buffer = query[i];
			if (!can_include_char_in_digest(buffer)) {
				continue;
			}
			/* New line or a space */
			if (stop_digest_this_word(buffer)) {
				/* Handle for last trigram of the line */
				if (numRead <= 1) {
					second = third = '\0';
				} else if (numRead == 2) {
					/*
					 * We have 2 characters. Make the third NULL.Do Digest for
					 * it
					 */
					third = '\0';
				} else {
					/*
					 * We have 2 characters followed by space. Make the third
					 * NULL.Do Digest for it
					 */
					first = second;
					second = third;
					third = '\0';
				}
				tmp_digest = get_hash(first, second, third);

				tmp_digest = digest[tmp_digest];
				arr[tmp_digest / NUM_BITS_BYTE] = (byte) (arr[tmp_digest
						/ NUM_BITS_BYTE] | (1 << (NUM_BITS_BYTE - 1 - tmp_digest
						% NUM_BITS_BYTE)));

				numRead = 0;
				first = second = third = '\0';
			} else {
				numRead++;
				if (numRead == 1) {
					first = (byte) buffer;
				} else if (numRead == 2) {
					second = (byte) buffer;
				} else if (numRead == 3) {
					third = (byte) buffer;
					tmp_digest = get_hash(first, second, third);

					tmp_digest = digest[tmp_digest];
					arr[tmp_digest / NUM_BITS_BYTE] = (byte) (arr[tmp_digest
							/ NUM_BITS_BYTE] | (1 << (NUM_BITS_BYTE - 1 - tmp_digest
							% NUM_BITS_BYTE)));
				} else {
					/*
					 * dont increment numRead. Keep at 3 for processing. Useful
					 * when seeing a space or a newline
					 */
					numRead = 3;
					first = second;
					second = third;
					third = (byte) buffer;

					tmp_digest = get_hash(first, second, third);
					tmp_digest = digest[tmp_digest];
					arr[tmp_digest / NUM_BITS_BYTE] = (byte) (arr[tmp_digest
							/ NUM_BITS_BYTE] | (1 << (NUM_BITS_BYTE - 1 - tmp_digest
							% NUM_BITS_BYTE)));

				}
			}
		}
		/* digest the last trigram of the query */
		if (numRead <= 2) {
			third = '\0';
			if (numRead <= 1) {
				second = '\0';
			}
			// printf("LastChar:%c%c%c Numread:%d\n",first,second,third,numRead);
			tmp_digest = get_hash(first, second, third);
			tmp_digest = digest[tmp_digest];
			arr[tmp_digest / NUM_BITS_BYTE] = (byte) (arr[tmp_digest
					/ NUM_BITS_BYTE] | (1 << (NUM_BITS_BYTE - 1 - tmp_digest
					% NUM_BITS_BYTE)));
		}
		return true;
	}

	/*
	 * Func:init_digest_table Returns: OK or ERROR based on the status Entry
	 * Point to the digest library. To be called from the main function of the
	 * program Refer test.c for sample implemetation
	 */
	public boolean init_digest_table() {
		construct_ascii_mapping();
		fill_digest_map();
		if (!get_digest_for_file(MASTER_FILE, null)) {
			return false;
		}
		remap_digest();
		return true;
	}

	public boolean remap_digest() {
		int count = 0;
		int i;
		int numElements = MAX_CLASS_CUBED;
		for (i = 0; i < numElements; i++) {
			if (0 == digest[i]) {
				digest[i] = count;
				count++;
			}
		}
		for (i = 0; i < numElements; i++) {
			if (-1 == digest[i]) {
				digest[i] = count;
			}
		}
		// LOG("Max Pos where Bit is Set:%d\n",count);

		return true;
	}

	/*
	 * function: construct_ascii_mapping called as a part of init_digest_table
	 * classifies the characters into classes. a-z form class 1 to 26, 0-9 form
	 * class 27 to 36 all the other characters form class 0
	 */

	public int construct_ascii_mapping() {
		int i;
		for (i = 0; i < 128; i++) {
			if (i >= 'a' && i <= 'z') {
				map[i] = (1 + i - 'a');
			} else if (i >= 'A' && i <= 'Z') {
				map[i] = (1 + i - 'A');
			} else if (i >= '0' && i <= '9') {
				map[i] = (1 + 26 + i - '0');
			} else {
				map[i] = 0;
			}
		}
		return 0;
	}

	public boolean fill_digest_map() {
		int i;
		for (i = 0; i < MAX_CLASS_CUBED; i++) {
			digest[i] = -1;
		}
		return true;
	}

	/*
	 * The following function checks if the character is alpha numeric, or new
	 * line or a space. Returns 1 if the character is one of the above. Else
	 * Returns 0
	 */
	boolean can_include_char_in_digest(byte ch) {

		if (!(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z')
				&& !(ch >= '0' && ch <= '9') && ch != '\n' && ch != ' ')
			return false;
		return true;
	}

	/*
	 * When you encounter a new line or a space character stop digesting this
	 * word and continue to the next word
	 */

	boolean stop_digest_this_word(byte ch) {
		if (ch == ' ' || ch == '\n') {
			return true;
		}
		return false;
	}

	int get_hash(byte a, byte b, byte c) {
		/*
		 * Mult factor for .... First Character 37^0 = 0 Second character 37^1 =
		 * 37 Third Character 37^2 = 1369
		 */
		// LOG("%c%c%c\n",a,b,c);
		return 1369 * map[(int) a] + 37 * map[(int) b] + map[(int) c];
	}
}
