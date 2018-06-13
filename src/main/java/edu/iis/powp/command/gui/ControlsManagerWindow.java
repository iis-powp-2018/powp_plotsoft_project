package edu.iis.powp.command.gui;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.app.Application;
import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.decorator.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlsManagerWindow extends JFrame implements WindowComponent {

	private static final long serialVersionUID = 9204679248304669948L;
	private Application application;
	private IPlotter newPlotter;

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
        newPlotter = new FlipHorizontalPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
	}

	private void flipVerticalWindow() {
		newPlotter = new FlipVerticalPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
    }

	private void rotateRightWindow() {
        newPlotter = new RotateRightPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
    }

	private void rotateLeftWindow() {
        newPlotter = new RotateLeftPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
	}

	private void zoomOutWindow() {
		newPlotter = new ZoomOutPlotterDecorator(application.getDriverManager().getCurrentPlotter());
        application.getDriverManager().setCurrentPlotter(newPlotter);
    }

	private void zoomInWindow() {
		newPlotter = new ZoomInPlotterDecorator(application.getDriverManager().getCurrentPlotter());
		application.getDriverManager().setCurrentPlotter(newPlotter);
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
