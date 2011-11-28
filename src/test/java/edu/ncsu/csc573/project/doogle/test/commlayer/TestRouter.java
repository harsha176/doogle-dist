package edu.ncsu.csc573.project.doogle.test.commlayer;

import java.util.Random;

import org.junit.Test;

import edu.ncsu.csc573.project.commlayer.IPoint;
import edu.ncsu.csc573.project.commlayer.Point;
import edu.ncsu.csc573.project.commlayer.Router;
import edu.ncsu.csc573.project.common.ConfigurationManager;

public class TestRouter {
	
	@Test
	public void testRoute() {
		//IPoint p = new Point(TestDigest.testCoordinates());
		Router rout = new Router();
		
		for(int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++) {
			rout.update(i, generateRandomPoint(), "192.168.2."+i+1);
		}
		System.out.println("no " + rout.getNextHop(generateRandomPoint()));
	}
	
	public IPoint generateRandomPoint() {
		byte[] p = new byte[ConfigurationManager.getInstance().getDimensions()];
		Random randInt = new Random();
		int limit = (int)1024/ConfigurationManager.getInstance().getDimensions();
		
		for(int i = 0; i < ConfigurationManager.getInstance().getDimensions(); i++)
			p[i] = (byte)randInt.nextInt(limit);
		
		return new Point(p);
	}
	
}
