package edu.iis.powp.command.factory;

import java.util.HashSet;
import java.util.Iterator;

import edu.iis.powp.command.Coordinates;
import edu.iis.powp.command.IPlotterCommand;

public abstract class SimpleCommandFactory {
	private static HashSet<Coordinates> coordinatesPool = new HashSet<>();
	
	protected Coordinates checkPool(Coordinates coordinates) {
		Coordinates buff = null;
		
		if(coordinatesPool.contains(coordinates)) {
			Iterator<Coordinates> i = coordinatesPool.iterator();
			while(i.hasNext()) {
				if(coordinates.equals(buff = i.next())) {
					break;
				}
			}
		} else {
			buff = coordinates;
			coordinatesPool.add(coordinates);
		}
		
		return buff;
			
	}
	
	public abstract IPlotterCommand makeSimpleCommand(Coordinates coordinates);
	
}
