package edu.iis.powp.command.complex;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import edu.iis.powp.command.Coordinates;
import edu.iis.powp.command.ICompoundCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SimpleCommand;
import edu.iis.powp.command.factory.DrawToCommandFactory;
import edu.iis.powp.command.factory.SetPositionCommandFactory;

public class CommandFileIOStorage {

    private final String commandPath;
    private JSONArray arrayOfCommands;
    /**
     * Constructor with command name
     *
     * @param path
     */

    public CommandFileIOStorage(String path) {
        commandPath = path;
    }

    /**
     * Save command to file
     *
     * @param command
     */
    public void save(ICompoundCommand command) {
    	arrayOfCommands = new JSONArray();
    	
    	createJSONOutput(command);
    	
    	try(FileWriter file  = new FileWriter(commandPath)){
    		file.write(arrayOfCommands.toJSONString());
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private void createJSONOutput(ICompoundCommand command) {
    	Iterator<IPlotterCommand> i = command.iterator();
    	while(i.hasNext()) {
    		IPlotterCommand buff = i.next();
    		
    		if(buff instanceof ICompoundCommand) {
    			createJSONOutput((ICompoundCommand)buff);
    		}
    		
    		if(buff instanceof SimpleCommand) {
    	    	JSONObject obj = new JSONObject();
    			
    			obj.put("Class", buff.getClass().getSimpleName());
    			
        		JSONArray coordinates = new JSONArray();
        		coordinates.add(((SimpleCommand) buff).getCoordinates().getPosX());
        		coordinates.add(((SimpleCommand) buff).getCoordinates().getPosY());
        		obj.put("Coordinates", coordinates);
        		
        		arrayOfCommands.add(obj);
    		}
    	} 	
    }

    /**
     * Read complex command from disk
     *
     * @return
     */
    public ICompoundCommand read() {
    	ICompoundCommand result = null;
    	List<IPlotterCommand> commands = new ArrayList<>();
        JSONParser jParser = new JSONParser();
        
        try(FileReader reader = new FileReader(commandPath)){
        	JSONArray arr = (JSONArray) jParser.parse(reader);
        	
        	for(int i=0; i<arr.size(); i++) {
        		JSONObject obj = (JSONObject) arr.get(i);
        		
        		String className = (String) obj.get("Class");
        		
        		JSONArray coordinatesArray = (JSONArray) obj.get("Coordinates");
        		Coordinates coordinates = new Coordinates((int)((long) coordinatesArray.get(0)),(int)((long) coordinatesArray.get(1)));
        		
        		if(className.equals("SetPositionCommand")) {
        			commands.add(SetPositionCommandFactory.getInstance().makeSimpleCommand(coordinates));
        		} else if(className.equals("DrawToCommand")) {
        			commands.add(DrawToCommandFactory.getInstance().makeSimpleCommand(coordinates));
        		}
        		
        		result = new ComplexCommand(commands);
        	}     	
        } catch(IOException|ParseException e) {
        	e.printStackTrace();
        }
        return result;
    }
}
