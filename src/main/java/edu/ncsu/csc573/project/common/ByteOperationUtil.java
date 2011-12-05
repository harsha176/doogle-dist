package edu.ncsu.csc573.project.common;

import java.io.IOException;
import java.util.Arrays;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class ByteOperationUtil {
	private static final int BYTE_SIZE_IN_BITS = 8;
	private static final int DIGEST_SIZE = 1024;
	private static int grid_dimensions = ConfigurationManager.getInstance().getDimensions();
	
	public static String convertBytesToString(byte[] digest) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(digest);
	}

	public static byte[] convertStringToBytes(String digest) {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			return decoder.decodeBuffer(digest);
		} catch (IOException e) {
			Logger.getLogger(ByteOperationUtil.class).error(
					"Unable to decode byte array " + digest, e);
		}
		return null;
	}

	public static boolean isBitSet(byte[] digest, int pos) {
		if ((digest[pos / 8] & (byte) (1 << (BYTE_SIZE_IN_BITS - 1 - pos
				% BYTE_SIZE_IN_BITS))) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isBitSet(String digest, int pos) {
		return isBitSet(convertStringToBytes(digest), pos);
	}

	public static byte[] and(byte[] d1, byte[] d2) {
		byte[] d1Andd2 = new byte[d1.length];
		for (int i = 0; i < d1.length; i++) {
			d1Andd2[i] = (byte) (d1[i] & d2[i]);
		}
		return d1Andd2;
	}

	public static int[] and(int[] d1, int[] d2) {
		int[] d1Andd2 = new int[d1.length];
		for (int i = 0; i < d1.length; i++) {
			d1Andd2[i] = (byte) (d1[i] & d2[i]);
		}
		return d1Andd2;
	}
	
	/*public static getMatchFactor(byte[] query, byte[] other) {
		
	}*/

	public static int countSetBits(byte[] other) {
		int count = 0;
		for (byte b : other) {
			for (; b != 0; count++) {
				b &= b - 1;
			}
		}
		return count;
	}
	
	public static int countSetBits(int[] other) {
		int count = 0;
		for (int b : other) {
			for (; b != 0; count++) {
				b &= b - 1;
			}
		}
		return count;
	}
	
	public static int[] getCordinates(byte[] digest) {
		int totalLength = digest.length;
		int[] coordinates = new int[grid_dimensions];
		int step = totalLength/grid_dimensions;
		
		for(int i = step, j = 0; i <= totalLength && j < grid_dimensions; i = i + step, j++) {
			//System.out.println((i-step)+","+(i-1));
			coordinates[j] =  countSetBits(Arrays.copyOfRange(digest, i-step, i));
		}
		
		return coordinates;
	}
	
	public static int[] getCordinates(String digest) {
		return getCordinates(convertStringToBytes(digest));
	}
	
	public static String printCoordinates(byte[] coordinate) {
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for(byte b: coordinate) {
			sb.append(b + ",");
			count += b;
		}
		//System.out.println(count);
		return sb.toString();
	}
}

