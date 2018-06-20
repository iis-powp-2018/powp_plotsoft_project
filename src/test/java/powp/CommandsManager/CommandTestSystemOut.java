package powp.CommandsManager;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.command.IPlotterCommand;

public class CommandTestSystemOut implements IPlotterCommand {

    @Override
    public void execute(IPlotter plotter) {
        System.out.print("It works.");
    }
}
