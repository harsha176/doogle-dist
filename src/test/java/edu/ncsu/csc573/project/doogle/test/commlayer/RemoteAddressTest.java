package edu.ncsu.csc573.project.doogle.test.commlayer;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class RemoteAddressTest {

	@Test
	public void test() {
		try {
			Socket soc = new Socket("www.google.com", 80);
			if(soc.getRemoteSocketAddress() instanceof InetSocketAddress) {
				System.out.println(((InetSocketAddress)soc.getRemoteSocketAddress()).getAddress().getHostAddress());
		}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
