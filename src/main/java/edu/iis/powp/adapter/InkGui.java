package edu.iis.powp.adapter;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.app.Application;
import edu.iis.powp.app.gui.WindowComponent;

public class InkGui extends JFrame implements WindowComponent{

    private static InkGui ourInstance = new InkGui();

    private InkGuiUpdater inkGuiUpdater;
    private Application application;
    private JTextArea inkAmount;
    private IPlotter currentPlotter;

    private InkGui() {
        this.setTitle("Ink controller");
        this.setSize(400, 200);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        inkAmount = new JTextArea("");
        inkAmount.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(inkAmount, c);

        JButton btnClearCommand = new JButton("Fill up");
        btnClearCommand.addActionListener((ActionEvent e) -> inkGuiUpdater.updateValue(1000));
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnClearCommand, c);
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }

    public void updateValue(float dane){
        inkAmount.setText("Ink value:" + String.valueOf(dane));
    }

    public void changePlotter(){
        currentPlotter = application.getDriverManager().getCurrentPlotter();
        inkGuiUpdater = (InkGuiUpdater) currentPlotter;
    }

    public void setApplication(Application application){
        this.application = application;
        currentPlotter = application.getDriverManager().getCurrentPlotter();
        inkGuiUpdater = (InkGuiUpdater) currentPlotter;
    }

    public static InkGui getInstance() {
        return ourInstance;
    }

}
