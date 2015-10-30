package com.planetsystems.monitoring.client.windows;

import com.planetsystems.monitoring.client.listgrids.MemberTaskListGrid;
import com.planetsystems.monitoring.client.widgets.MemberDetailViewer;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class MemberProfile extends Window {

	private Img profilePic;

	private MemberTaskListGrid taskList;

	private MemberDetailViewer detailViewer;

	private IButton closeTaskButton, suspendTaskButton, delegateTaskButton;

	public MemberProfile() {
		super();
		VLayout mainLayout = new VLayout();
		mainLayout.setWidth100();
		mainLayout.setHeight100();

		HLayout headerLayout = new HLayout();
		headerLayout.setWidth100();
		headerLayout.setHeight("10%");

		HLayout taskButtonsLayout = new HLayout();
		taskButtonsLayout.setWidth("50%");
		taskButtonsLayout.setHeight("1%");
		closeTaskButton = new IButton("Close Task");
		suspendTaskButton = new IButton("Suspend Task");
		delegateTaskButton = new IButton("Delegate Task");

		taskButtonsLayout.setMembers(closeTaskButton, suspendTaskButton,
				delegateTaskButton);

		VLayout detailsLayout = new VLayout();
		detailsLayout.setWidth("90%");
		detailsLayout.setHeight100();

		detailViewer = new MemberDetailViewer();

		detailsLayout.addMember(detailViewer);

		VLayout imageLayout = new VLayout();
		imageLayout.setHeight("5%");
		imageLayout.setWidth100();

		profilePic = new Img();
		profilePic.setWidth("50%");
		profilePic.setHeight("70%");
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
		setHeight(com.google.gwt.user.client.Window.getClientHeight() - 10);
		addItem(mainLayout);
		setAlign(Alignment.CENTER);

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
