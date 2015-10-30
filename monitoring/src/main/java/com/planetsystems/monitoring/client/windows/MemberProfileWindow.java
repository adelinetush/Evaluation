package com.planetsystems.monitoring.client.windows;

import com.planetsystems.monitoring.client.listgrids.MemberTaskListGrid;
import com.planetsystems.monitoring.client.widgets.MemberDetailViewer;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class MemberProfileWindow extends Window {

	private Img profilePic;

	private MemberTaskListGrid taskList;

	private MemberDetailViewer detailViewer;

	private IButton closeTaskButton, suspendTaskButton, delegateTaskButton;

	public MemberProfileWindow() {
		super();
		VLayout mainLayout = new VLayout();
		mainLayout.setWidth100();
		mainLayout.setHeight100();

		HLayout headerLayout = new HLayout();
		headerLayout.setWidth100();
		headerLayout.setHeight("20%");

		HLayout taskButtonsLayout = new HLayout();
		taskButtonsLayout.setWidth("50%");
		taskButtonsLayout.setHeight("1%");
		closeTaskButton = new IButton("Close Task");
		suspendTaskButton = new IButton("Suspend Task");
		delegateTaskButton = new IButton("Delegate Task");

		taskButtonsLayout.setMembers(closeTaskButton, suspendTaskButton,
				delegateTaskButton);

		VLayout detailsLayout = new VLayout();
		detailsLayout.setWidth("80%");
		detailsLayout.setHeight100();

		detailViewer = new MemberDetailViewer();

		detailsLayout.addMember(detailViewer);

		VLayout imageLayout = new VLayout();
		imageLayout.setHeight("10%");
		imageLayout.setWidth("5%");

		profilePic = new Img();
		profilePic.setWidth100();
		profilePic.setHeight100();
		profilePic.setBackgroundColor("gray");

		imageLayout.addMember(profilePic);

		taskList = new MemberTaskListGrid();

		headerLayout.addMember(imageLayout);

		headerLayout.addMember(detailsLayout);

		mainLayout.addMember(headerLayout);

		mainLayout.addMember(taskButtonsLayout);

		mainLayout.addMember(taskList);

		setWidth("60%");
		setHeight("40%");
		addItem(mainLayout);
		setAutoCenter(true);

	}

	public Img getProfilePic() {
		return profilePic;
	}

	public MemberTaskListGrid getTaskList() {
		return taskList;
	}

	public MemberDetailViewer getDetailViewer() {
		return detailViewer;
	}

	public IButton getCloseTaskButton() {
		return closeTaskButton;
	}

	public IButton getSuspendTaskButton() {
		return suspendTaskButton;
	}

	public IButton getDelegateTaskButton() {
		return delegateTaskButton;
	}

}
