package edu.iis.powp.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.iis.powp.command.factory.IPlotterCommandFactory;
import junit.framework.Assert;

public class TestFactoryPool {

	@Test
	public void factoryPoolTest() {
		IPlotterCommandFactory factory = new IPlotterCommandFactory();
		SetPositionCommand commandOne = factory.makeSetPositionCommand(2, 7);
		SetPositionCommand commandTwo = factory.makeSetPositionCommand(2, 7);
		
		//checking reference
		Assert.assertTrue(commandOne==commandTwo);
		
		Assert.assertFalse(commandOne==new SetPositionCommand(2, 7));
	}

}
