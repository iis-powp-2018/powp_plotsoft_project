package edu.iis.powp.command;

import edu.iis.client.plottermagic.IPlotter;

public interface InkControllerCommand {

    void execute(IPlotter driver);
}
