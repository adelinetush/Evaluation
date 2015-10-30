/**
 * 
 */
package com.planetsystems.monitoring.client.widgets.esignatures;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author Personal
 * 
 */
public class SigNetRegistrationWindow extends Window {

	Canvas canvas;
	Context2d context;
	static final int canvasHeight = 150;
	static final int canvasWidth = 300;
	boolean started = false;
	boolean signatureCountFull = false;
	int signatureCount = 1;
	List<String> imageList = new ArrayList<String>();

	final Button nextButton = new Button("Next");
	final Button clearButton = new Button("Clear");

	final Label instruction = new Label(
			"Please Sign 10 times so that the system can know your signature. Thank you");
	final Label countLabel = new Label(signatureCount + " of 10");

	public SigNetRegistrationWindow() {

		super();
		setWidth(410);
		setHeight(350);
		setTitle("SigNet Registration");

		HLayout hpanel = new HLayout();
		hpanel.setAlign(Alignment.CENTER);
		hpanel.addMember(clearButton);
		hpanel.addMember(nextButton);

		VerticalPanel panel = new VerticalPanel();
		// panel.setSpacing(5);
		canvas = Canvas.createIfSupported();

		if (canvas == null) {
			RootPanel
					.get()
					.add(new Label(
							"Sorry, your browser doesn't support the HTML5 Canvas element"));
			return;
		}

		canvas.setStyleName("mainCanvas");
		canvas.setWidth(canvasWidth + "px");
		canvas.setCoordinateSpaceWidth(canvasWidth);

		canvas.setHeight(canvasHeight + "px");
		canvas.setCoordinateSpaceHeight(canvasHeight);
		canvas.addMouseMoveHandler(new MouseMoveHandler() {
			public void onMouseMove(MouseMoveEvent event) {
				if (started) {
					context.lineTo(event.getX(), event.getY());
					context.stroke();
				}
			}
		});
		canvas.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				if (started) {
					context.beginPath();
					context.moveTo(event.getX(), event.getY());
					started = true;
				} else {
					context.beginPath();
					context.moveTo(event.getX(), event.getY());
					started = true;
					// context.lineTo(x, y);
					// context.stroke();
				}

			}
		});
		canvas.addMouseUpHandler(new MouseUpHandler() {

			public void onMouseUp(MouseUpEvent event) {
				if (started) {
					started = false;
				}
			}
		});

		clearButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				context.clearRect(0, 0, canvas.getOffsetWidth(),
						canvas.getOffsetHeight());
			}

		});

		nextButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
		/*		if (signatureCountFull) {

				} else {
					String imageurl = canvas.toDataUrl("image/png");

					signatureCount++;

					imageList.add(imageurl);
					if (signatureCount > 10) {
						// do sthg with image list

						instruction.setVisible(false);
						countLabel
								.setText("All your signatures have been captured");
						countLabel
								.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
						nextButton.setVisible(false);
						clearButton.setVisible(false);
						canvas.setVisible(false);
						signatureCountFull = true;
						setWidth(400);
						setHeight(100);

					} else {
						countLabel.setText(signatureCount + " of 10");
					}
					context.clearRect(0, 0, canvas.getOffsetWidth(),
							canvas.getOffsetHeight());
				}*/

			}
		});
		VerticalPanel parentPanel = new VerticalPanel();
		parentPanel.setHeight("230px");
	/*	panel.add(instruction);
		panel.add(countLabel);
		panel.add(canvas);
		parentPanel.add(panel);
		parentPanel.add(hpanel);
		addItem(parentPanel);*/
		addItem(instruction);
		addItem(countLabel);
		addItem(canvas);
		addItem(hpanel);
		context = canvas.getContext2d();

	}

	/**
	 * @return the imageList
	 */
	public List<String> getImageList() {
		return imageList;
	}

	/**
	 * @param imageList
	 *            the imageList to set
	 */
	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

	/**
	 * @return the started
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * @param started
	 *            the started to set
	 */
	public void setStarted(boolean started) {
		this.started = started;
	}

	/**
	 * @return the signatureCountFull
	 */
	public boolean isSignatureCountFull() {
		return signatureCountFull;
	}

	/**
	 * @param signatureCountFull
	 *            the signatureCountFull to set
	 */
	public void setSignatureCountFull(boolean signatureCountFull) {
		this.signatureCountFull = signatureCountFull;
	}

	/**
	 * @return the signatureCount
	 */
	public int getSignatureCount() {
		return signatureCount;
	}

	/**
	 * @param signatureCount
	 *            the signatureCount to set
	 */
	public void setSignatureCount(int signatureCount) {
		this.signatureCount = signatureCount;
	}

	/**
	 * @return the canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * @return the context
	 */
	public Context2d getContext() {
		return context;
	}

	/**
	 * @return the nextButton
	 */
	public Button getNextButton() {
		return nextButton;
	}

	/**
	 * @return the clearButton
	 */
	public Button getClearButton() {
		return clearButton;
	}

	/**
	 * @return the instruction
	 */
	public Label getInstruction() {
		return instruction;
	}

	/**
	 * @return the countLabel
	 */
	public Label getCountLabel() {
		return countLabel;
	}
}
