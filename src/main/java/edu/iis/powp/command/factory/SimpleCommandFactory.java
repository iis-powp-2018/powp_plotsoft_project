package edu.iis.powp.command.factory;

import java.util.HashSet;
import java.util.Iterator;

import edu.iis.powp.command.Coordinates;
import edu.iis.powp.command.IPlotterCommand;

/**
 * Abstract factory for commands containing coordinates 
 */
public abstract class SimpleCommandFactory {
	private static HashSet<Coordinates> coordinatesPool = new HashSet<>();
	
	/**
	 *  Checking if passed coordinates are in coordinates pool, if not they are add to pool
	 * @param coordinates Coordinates to check
	 * @return pointer to coordinates from pool
	 */
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
	
	/**
	 * Create IPlotterCommand using coordinates from pool
	 * @param coordinates which we want to have in returning object
	 * @return IPlotterCommand using coordinates form pool
	 */
	public abstract IPlotterCommand makeSimpleCommand(Coordinates coordinates);
	
}
