package edu.iis.powp.command;

import java.util.HashSet;
import java.util.Iterator;

public class SimpleCommandFactory {
	private static HashSet<Coordinates> coordinatesPool = new HashSet<>();
	
	public static Coordinates checkPool(Coordinates coordinates) {
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
	
	public static IPlotterCommand makeSetPositionCommand(Coordinates coordinates) {
		return new SetPositionCommand(checkPool(coordinates));
	}
	
	public static IPlotterCommand makeDrawToCommand(Coordinates coordinates) {
		return new DrawToCommand(checkPool(coordinates));
	}
	
}
