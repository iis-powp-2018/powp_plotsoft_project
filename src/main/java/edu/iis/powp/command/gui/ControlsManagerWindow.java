package edu.iis.powp.command.gui;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.client.plottermagic.preset.FiguresJoe;
import edu.iis.powp.app.Application;
import edu.iis.powp.app.DriverManager;
import edu.iis.powp.app.gui.WindowComponent;

import edu.iis.powp.features.CommandsFeature;
import edu.iis.powp.features.DrawerFeature;
import edu.iis.powp.decorator.*;


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

        JButton btnMoveRight = new JButton("Move Right");
        btnMoveRight.addActionListener((ActionEvent e) -> this.moveRightWindow());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnMoveRight, c);

        JButton btnMoveLeft = new JButton("Move Left");
        btnMoveLeft.addActionListener((ActionEvent e) -> this.moveLeftWindow());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnMoveLeft, c);

        JButton btnMoveUp = new JButton("Move Up");
        btnMoveUp.addActionListener((ActionEvent e) -> this.moveUpWindow());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnMoveUp, c);

        JButton btnMoveDown = new JButton("Move Down");
        btnMoveDown.addActionListener((ActionEvent e) -> this.moveDownWindow());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(btnMoveDown, c);
	}

    private void moveDownWindow() {
        newPlotter = new MoveDownPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
    }

    private void moveUpWindow() {
        newPlotter = new MoveUpPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
    }

    private void moveLeftWindow() {
        newPlotter = new MoveLeftPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
    }

    private void moveRightWindow() {
        newPlotter = new MoveRightPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
    }

    private void flipHorizontalWindow() {
        newPlotter = new FlipHorizontalPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
		DrawerFeature.getDrawerController().clearPanel();
		commandhistory();
	}

	private void flipVerticalWindow() {
		newPlotter = new FlipVerticalPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
		DrawerFeature.getDrawerController().clearPanel();
		commandhistory();
    }

	private void rotateRightWindow() {
        newPlotter = new RotateRightPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
		DrawerFeature.getDrawerController().clearPanel();
		commandhistory();
    }

	private void rotateLeftWindow() {
        newPlotter = new RotateLeftPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
		DrawerFeature.getDrawerController().clearPanel();
		commandhistory();
	}

	private void zoomOutWindow() {
		newPlotter = new ZoomOutPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
		DrawerFeature.getDrawerController().clearPanel();
		commandhistory();
    }

	private void zoomInWindow() {
		newPlotter = new ZoomInPlotterDecorator(application.getDriverManager().getCurrentPlotter());
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
