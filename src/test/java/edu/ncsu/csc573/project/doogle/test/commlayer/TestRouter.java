package edu.ncsu.csc573.project.doogle.test.commlayer;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import edu.ncsu.csc573.project.commlayer.IPoint;
import edu.ncsu.csc573.project.commlayer.Point;
import edu.ncsu.csc573.project.commlayer.Router;
import edu.ncsu.csc573.project.common.ConfigurationManager;

public class TestRouter {
	
	@Test
	public void testRoute() {
		//IPoint p = new Point(TestDigest.testCoordinates());
		Router rout = Router.getInstance();
		
		for(int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			rout.update(i, generateRandomPoint(), "192.168.2."+i+1);
		}
		System.out.println("no " + rout.getNextHop(generateRandomPoint()));
	}
	
	public IPoint generateRandomPoint() {
		byte[] p = new byte[ConfigurationManager.getInstance().getDimensions()];
		Random randInt = new Random();
		int limit = (int)1024/ConfigurationManager.getInstance().getDimensions();
		Assert.assertTrue(limit < 255);
		System.out.println("limit is " + limit);
		for(int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			int val = randInt.nextInt(limit);
			Assert.assertTrue(val >= 0);
			Assert.assertTrue(val < 127);
			p[i] = (byte)val;
			Assert.assertTrue(p[i] >= 0);
		}
		
		return new Point(p);
	}
	
}
