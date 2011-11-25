package edu.ncsu.csc573.project.doogle.test.hashmanagement;


import junit.framework.Assert;

import org.junit.Test;

import edu.ncsu.csc573.project.common.ByteOperationUtil;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.DigestAdaptor;
import edu.ncsu.csc573.project.controllayer.hashspacemanagement.IDigest;

public class TestDigest {
	
	
	@Test 
	public void testDigestComputation() {
		try {
			IDigest digestUtil = DigestAdaptor.getInstance();
			String query = "Today is sunday";
			byte[] queryDigest = digestUtil.getDigest(query);
			Assert.assertEquals(1024, queryDigest.length);
			int[] expectedSetBits = {465, 1084, 2938, 4189, 4582, 6045, 6267, 6646};
			for(int bit: expectedSetBits) {
				Assert.assertEquals(true, ByteOperationUtil.isBitSet(ByteOperationUtil.convertBytesToString(queryDigest), bit));
			}
		} catch (Exception e) {
			Assert.assertFalse(false);
			e.printStackTrace();
		}
	}
	/*@Test
	public void testquery(){
		Digest dig = new Digest();
		
		if(!dig.init_digest_table()) {
			Assert.assertFalse(true);
			System.out.println("Fail to initialize digest utility");
			return;
		}
		
		String query = "Today is sunday";
		int i;
		/* 
		 * Make sure none of the bits are set in digest_bits before calling 
		 

		//memset(digest_bits,0,sizeof(digest_bits));
		//System.out.println(("TESTING QUERY: " + query));
		dig.get_digest_for_query(query.getBytes(), digest_bits);

		/* To find which bits are set 
		//System.out.print("Bits are set in:");
		int[] expected = {465, 1084, 2938, 4189, 4582, 6045, 6267, 6646};
		int j = 0;
		for(i = 0;i < Digest.MAX_BITS_DIGEST; i++) {
			if((digest_bits[i/NUM_BITS_CHAR] & (byte)((1<<(NUM_BITS_CHAR - 1 - i%NUM_BITS_CHAR)))) > 0) {
				Assert.assertEquals(expected[j], i);
				j++;
			}
		}
		return;
	}*/
}
