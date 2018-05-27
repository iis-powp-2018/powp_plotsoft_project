package edu.iis.powp.command.complex;

import edu.iis.powp.command.ICompoundCommand;

public class CommandFileIOStorage {

    private final String commandPath;

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
//TO_DO
    }

    /**
     * Read complex command from disk
     *
     * @return
     */
    public ICompoundCommand read() {
        return null;
    }
}
