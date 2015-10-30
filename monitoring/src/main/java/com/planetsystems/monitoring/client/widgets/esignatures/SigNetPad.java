/**
 * 
 */
package com.planetsystems.monitoring.client.widgets.esignatures;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
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
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.HLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;


/**
 * @author Personal
 *
 */
public class SigNetPad extends Window{

	Canvas canvas;
	Context2d context;
	static final int canvasHeight = 150;
	static final int canvasWidth = 300;
	boolean started = false;
	
	final Label countLabel = new Label("Sign Below");

	final Button nextButton = new Button("Capture");
	final Button clearButton = new Button("Clear");
	
	public SigNetPad() {

		super();
		setWidth(400);
		setHeight(300);
		setTitle("SigNet");

		HLayout hpanel = new HLayout();
		hpanel.setAlign(Alignment.CENTER);
		hpanel.addMember(clearButton);
		hpanel.addMember(nextButton);

		VerticalPanel panel = new VerticalPanel();
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
	/*	nextButton.addClickHandler(new ClickHandler(){

			public void onClick(
					com.smartgwt.client.widgets.events.ClickEvent event) {
				
				String imageurl = canvas.toDataUrl();
				if (imageurl != null) {
					countLabel.setText("Signature Captured Successfully!");
					countLabel
							.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
					nextButton.setVisible(false);
					clearButton.setVisible(false);
					canvas.setVisible(false);
					setWidth(400);
					setHeight(100);

				} else {
					SC.say("No Signature!");
				}
				context.clearRect(0, 0, canvas.getOffsetWidth(),
						canvas.getOffsetHeight());
						
							context.clearRect(0, 0, canvas.getOffsetWidth(),
						canvas.getOffsetHeight());
			}
			
		});*/
		VerticalPanel parentPanel = new VerticalPanel();

		panel.add(countLabel);
		panel.add(canvas);
		parentPanel.add(panel);
		parentPanel.add(hpanel);
		addItem(parentPanel);
		context = canvas.getContext2d();

	}

	/**
	 * @return the started
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * @param started the started to set
	 */
	public void setStarted(boolean started) {
		this.started = started;
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
	 * @return the countLabel
	 */
	public Label getCountLabel() {
		return countLabel;
	}
}
