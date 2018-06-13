package edu.iis.powp.command.gui;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.client.plottermagic.preset.FiguresJoe;
import edu.iis.powp.app.Application;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.factory.*;
import edu.iis.powp.features.CommandsFeature;
import edu.iis.powp.features.DrawerFeature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlsManagerWindow extends JFrame implements WindowComponent {

	private static final long serialVersionUID = 9204679248304669948L;
	private Application application;
	private IPlotter newPlotter;
	private DriverManager driverManager;

	public ControlsManagerWindow(Application application) {

		this.application = application;

		this.setTitle("Controls Manager");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());


		GridBagConstraints c = new GridBagConstraints();

		JButton btnZoomIn = new JButton("Zoom In");
		btnZoomIn.addActionListener((ActionEvent e) -> this.zoomInWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnZoomIn, c);

		JButton btnZoomOut = new JButton("Zoom Out");
		btnZoomOut.addActionListener((ActionEvent e) -> this.zoomOutWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnZoomOut, c);

		JButton btnRotateLeft = new JButton("Rotate Left");
		btnRotateLeft.addActionListener((ActionEvent e) -> this.rotateLeftWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnRotateLeft, c);

		JButton btnRotateRight = new JButton("Rotate Right");
		btnRotateRight.addActionListener((ActionEvent e) -> this.rotateRightWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnRotateRight, c);

		JButton btnFlipVertical = new JButton("Flip Vertical");
		btnFlipVertical.addActionListener((ActionEvent e) -> this.flipVerticalWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnFlipVertical, c);

		JButton btnFlipHorizontal = new JButton("Flip Horizontal");
		btnFlipHorizontal.addActionListener((ActionEvent e) -> this.flipHorizontalWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnFlipHorizontal, c);
	}

	private void flipHorizontalWindow() {
        newPlotter = new FlipHorizontalPlotterFactory(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
		DrawerFeature.getDrawerController().clearPanel();
		commandhistory();
	}

	private void flipVerticalWindow() {
		newPlotter = new FlipVerticalPlotterFactory(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
		DrawerFeature.getDrawerController().clearPanel();
		commandhistory();
    }

	private void rotateRightWindow() {
        newPlotter = new RotateRightPlotterFactory(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
		DrawerFeature.getDrawerController().clearPanel();
		commandhistory();
    }

	private void rotateLeftWindow() {
        newPlotter = new RotateLeftPlotterFactory(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
		DrawerFeature.getDrawerController().clearPanel();
		commandhistory();
	}

	private void zoomOutWindow() {
		newPlotter = new ZoomOutPlotterFactory(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
		DrawerFeature.getDrawerController().clearPanel();
		commandhistory();
    }

	private void zoomInWindow() {
		newPlotter = new ZoomInPlotterFactory(application.getDriverManager().getCurrentPlotter());
		application.getDriverManager().setCurrentPlotter(newPlotter);
		DrawerFeature.getDrawerController().clearPanel();
		commandhistory();
	}

	private void commandhistory() {

		for (String command: CommandsFeature.commandList) {
			if (command.equals("figureScript1")) {
				FiguresJoe.figureScript1(application.getDriverManager().getCurrentPlotter());
			}
			if (command.equals("figureScript2")) {
				FiguresJoe.figureScript2(application.getDriverManager().getCurrentPlotter());
			}
		}
	}


	@Override
	public void HideIfVisibleAndShowIfHidden() {
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}

}
