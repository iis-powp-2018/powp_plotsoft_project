package edu.iis.powp.command;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class TestCooridnate {

	@Test
	public void coordinateEqualsTest() {
		Coordinates coordinatesOne = new Coordinates(7, 2);
		Assert.assertEquals(coordinatesOne, coordinatesOne);
		
		Coordinates coordinatesTwo = new Coordinates(7, 2);
		Assert.assertEquals(coordinatesOne, coordinatesTwo);
		
		coordinatesTwo = new Coordinates(7, 3);
		Assert.assertNotSame(coordinatesOne, coordinatesTwo);
	}

}
