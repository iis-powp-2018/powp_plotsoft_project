package edu.iis.powp.command;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.iis.powp.command.complex.ComplexCommand;
import edu.iis.powp.command.factory.DrawToCommandFactory;
import edu.iis.powp.command.factory.SetPositionCommandFactory;
import junit.framework.Assert;

public class TestICompoundCommandTest {
	ComplexCommand command;
	List<IPlotterCommand> commands;
	
	@Before
	public void setUp() throws Exception {
		commands = new ArrayList<>();
		commands.add(SetPositionCommandFactory.getInstance().makeSimpleCommand(new Coordinates(2, 2)));
		commands.add(DrawToCommandFactory.getInstance().makeSimpleCommand(new Coordinates(2, 4)));
		commands.add(SetPositionCommandFactory.getInstance().makeSimpleCommand(new Coordinates(2, 4)));
		commands.add(DrawToCommandFactory.getInstance().makeSimpleCommand(new Coordinates(4, 4)));
		commands.add(SetPositionCommandFactory.getInstance().makeSimpleCommand(new Coordinates(4, 4)));
		commands.add(DrawToCommandFactory.getInstance().makeSimpleCommand(new Coordinates(4, 2)));
		commands.add(SetPositionCommandFactory.getInstance().makeSimpleCommand(new Coordinates(4, 2)));
		commands.add(DrawToCommandFactory.getInstance().makeSimpleCommand(new Coordinates(2, 2)));
		
		command = new ComplexCommand(commands, "Test Complex Command");
		}

	@Test
	public void changeSequenceTest() {
		IPlotterCommand commandOneToSwap = commands.get(2);
		IPlotterCommand commandTwoToSwap = commands.get(4);
		command.changeSequence(2, 4);
		int i=0;
		Iterator<IPlotterCommand> iterator = command.iterator();
		while(iterator.hasNext()) {
			if(i==2) {
				Assert.assertEquals(iterator.next(), commandTwoToSwap);
			} else {
				if(i==4) {
					Assert.assertEquals(iterator.next(), commandOneToSwap);
					break;
				} else {
					iterator.next();
				}
			}
			i++;
		}
	}
	
	@Test
	public void removeCommandTest() {
		IPlotterCommand commandToRamove = commands.get(3);
		command.removeCommand(3);
		int i=0;
		Iterator<IPlotterCommand> iterator = command.iterator();
		while(iterator.hasNext()) {
			if(i==3) {
				Assert.assertNotSame(iterator.next(), commandToRamove);
				break;
			}
			iterator.next();
			i++;
		}
	}
	
	@Test
	public void changeCommandTest() {
		IPlotterCommand commandToChange = DrawToCommandFactory.getInstance().makeSimpleCommand(new Coordinates(5, 15));
		command.changeCommand(3, commandToChange);
		int i=0;
		Iterator<IPlotterCommand> iterator = command.iterator();
		while(iterator.hasNext()) {
			if(i==3) {
				Assert.assertEquals(iterator.next(), commandToChange);
				break;
			}
			iterator.next();
			i++;
		}
	}
	

}
