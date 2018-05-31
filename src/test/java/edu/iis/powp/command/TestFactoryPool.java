package edu.iis.powp.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.iis.powp.command.factory.DrawToCommandFactory;
import edu.iis.powp.command.factory.SetPositionCommandFactory;
import junit.framework.Assert;

public class TestFactoryPool {

	@Test
	public void factoryPoolTest() {
		SimpleCommand commandOne = (SimpleCommand) SetPositionCommandFactory.getInstance().makeSimpleCommand(new Coordinates(2, 7));
		SimpleCommand commandTwo = (SimpleCommand) DrawToCommandFactory.getInstance().makeSimpleCommand(new Coordinates(2, 7));
		
		//checking reference
		Assert.assertTrue(commandOne.getCoordinates()==commandTwo.getCoordinates());
		
		Assert.assertFalse(commandOne.getCoordinates()==new Coordinates(2, 7));
	}

}
