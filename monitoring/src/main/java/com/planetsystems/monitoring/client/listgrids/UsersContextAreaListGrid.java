/**
 * 
 */
package com.planetsystems.monitoring.client.listgrids;

//import com.planetsystems.procnet.core.ApprovalConstants;
import com.planetsystems.monitoring.client.widgets.buttons.DeleteButton;
import com.planetsystems.monitoring.client.widgets.buttons.SaveButton;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Planet Developer 001
 * 
 */
public class UsersContextAreaListGrid extends ListGrid {
	private static final String ICON = "icon";
	private static final String ICON_DISPLAY_NAME = "#";
	private static final String USER_NAME = "userName";
	private static final String USER_NAME_DISPLAY = "User Name";
	public static final String USER_ID = "userID";
	private static final String USER_ID_DISPLAY = "User ID";
	private static final String USER_FIRST_NAME = "userFirstName";
	private static final String USER_FIRST_NAME_DISPLAY = "User FirstName";
	private static final String USER_SECOND_NAME = "userLastName";
	private static final String USER_SECOND_NAME_DISPLAY = "User Last Name";
	private static final String USER_EMAIL = "userEmail";
	private static final String USER_EMAIL_DISPLAY = "User Email";
	private static final String USER_DEPARTMENT = "departmentName";
	private static final String USER_DEPARTMENT_DISPLAY = "User Department";
	private static final String USER_DEPARTMENT_ID = "departmentID";
	private static final String USER_DEPARTMENT_ID_DISPLAY = "Departemnt ID";
	private static final String USER_ROLE_ID = "roleID";
	private static final String USER_ROLE_ID_DISPLAY = "Role ID";
	private static final String USER_ROLE_NAME = "roleName";
	private static final String USER_ROLE_NAME_DISPLAY = "Role";
	private static final String USER_STATUS = "userStatus";
	private static final String USER_STATUS_DISPLAY = "Status";
	private static final String USER_PHONE_NUMBER = "userPhoneNumber";
	private static final String USER_PHONE_NUMBER_DISPLAY = "Phone Number";
	private static final String EMPTY_FIELD = "emptyField";
	private static final String EMPTY_FIELD_DISPLAY_NAME = " ";
	private static final String URL_PREFIX = "icons/16/";
	private static final String URL_SUFFIX = ".png";
	private static final String CREATED_BY = "createdBy";
	private static final String CREATED_BY_DISPLAY = "Created By";
	private static final String CHANGED_BY = "changedBy";
	private static final String CHANGED_BY_DISPLAY = "ChangedBy";

	private static final int ICON_COLUMN_LENGTH = 27;

	private HLayout rollOverCanvas;
	private ListGridRecord rollOverRecord;

	private ImgButton editImg = new ImgButton();

	private UserRoleListGrid roleListGrid = new UserRoleListGrid();
	
	IButton closeButton = new IButton("Close"); 
	SaveButton addRoleButton = new SaveButton("Add Role"); 
	DeleteButton removeButton = new DeleteButton("Remove Role");  
	
	public UsersContextAreaListGrid() {
		super();

		// initialize the List Grid fields
		ListGridField iconField = new ListGridField(ICON, ICON_DISPLAY_NAME,
				ICON_COLUMN_LENGTH);
		iconField.setImageSize(16);
		iconField.setAlign(Alignment.CENTER);
		iconField.setType(ListGridFieldType.IMAGE);
		iconField.setImageURLPrefix(URL_PREFIX);
		iconField.setImageURLSuffix(URL_SUFFIX);

		ListGridField userIDField = new ListGridField(USER_ID, USER_ID_DISPLAY);
		userIDField.setHidden(true);

		ListGridField roleIDField = new ListGridField(USER_ROLE_ID,
				USER_ROLE_ID_DISPLAY);
		roleIDField.setHidden(true);

		ListGridField departmentIDField = new ListGridField(USER_DEPARTMENT_ID,
				USER_DEPARTMENT_ID_DISPLAY);
		departmentIDField.setHidden(true);

		ListGridField userNameField = new ListGridField(USER_NAME,
				USER_NAME_DISPLAY);
		userNameField.setWidth("10%");

		ListGridField userStatusField = new ListGridField(USER_STATUS,
				USER_STATUS_DISPLAY);
		userStatusField.setWidth("5%");

		ListGridField userFirstNameField = new ListGridField(USER_FIRST_NAME,
				USER_FIRST_NAME_DISPLAY);
		userFirstNameField.setWidth("10%");

		ListGridField userSecondNameField = new ListGridField(USER_SECOND_NAME,
				USER_SECOND_NAME_DISPLAY);
		userSecondNameField.setWidth("10%");

		ListGridField userEmailField = new ListGridField(USER_EMAIL,
				USER_EMAIL_DISPLAY);
		userEmailField.setWidth("10%");

		ListGridField userPhoneNumberField = new ListGridField(
				USER_PHONE_NUMBER, USER_PHONE_NUMBER_DISPLAY);
		userPhoneNumberField.setHidden(true);

		ListGridField userDepartmentField = new ListGridField(USER_DEPARTMENT,
				USER_DEPARTMENT_DISPLAY);
		userDepartmentField.setWidth("10l%");
		ListGridField userRoleField = new ListGridField(USER_ROLE_NAME,
				USER_ROLE_NAME_DISPLAY);
		userRoleField.setWidth("15%");
		ListGridField emptyField = new ListGridField(EMPTY_FIELD,
				EMPTY_FIELD_DISPLAY_NAME);
		emptyField.setWidth("*");

		ListGridField createdByField = new ListGridField(CREATED_BY,
				CREATED_BY_DISPLAY);
		createdByField.setWidth("15%");
		ListGridField changedByField = new ListGridField(CHANGED_BY,
				CHANGED_BY_DISPLAY);
		changedByField.setWidth("15%");

		this.setShowRollOverCanvas(true);
		this.setShowRollOver(true);
		this.setShowRowNumbers(true);
		this.setCanPickFields(false);
		this.setDrawAheadRatio(4);  
	    this.setCanExpandRecords(true); 

		this.setGroupByField("departmentName");
		this.setGroupStartOpen(GroupStartOpen.ALL);
		this.setFields(new ListGridField[] { iconField, userIDField,
				roleIDField, departmentIDField, userNameField,
				userFirstNameField, userSecondNameField, userEmailField,
				userDepartmentField, userStatusField,
				userPhoneNumberField, createdByField, changedByField });
		this.setLoadingDataMessage("Loading Users...");
		this.setLoadingMessage("Loading Users...");
		this.markForRedraw("Loading Data");
	
		// populate the List Grid
		// this.setData(UsersData.getRecords());

		// getRollOverCanvas(Integer rowNum, Integer colNum);

	}

	@Override
	public Canvas getRollOverCanvas(Integer rowNum, Integer colNum) {
		rollOverRecord = this.getRecord(rowNum);

		if (rollOverCanvas == null) {
			rollOverCanvas = new HLayout(3);
			rollOverCanvas.setSnapTo("TR");
			rollOverCanvas.setWidth(50);
			rollOverCanvas.setHeight(22);

			editImg.setShowDown(false);
			editImg.setShowRollOver(false);
			editImg.setLayoutAlign(Alignment.CENTER);
			editImg.setSrc("silk/edit.png");
			editImg.setPrompt("Edit User");
			editImg.setHeight(16);
			editImg.setWidth(16);

			ImgButton chartImg = new ImgButton();
			chartImg.setShowDown(false);
			chartImg.setShowRollOver(false);
			chartImg.setLayoutAlign(Alignment.CENTER);
			chartImg.setSrc("silk/chart_bar.png");
			chartImg.setPrompt("View Chart");
			chartImg.setHeight(16);
			chartImg.setWidth(16);
			chartImg.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					SC.say("Chart Icon Clicked for country : "
							+ rollOverRecord.getAttribute("countryName"));
				}
			});

			rollOverCanvas.addMember(editImg);
			// rollOverCanvas.addMember(chartImg);
		}
		return rollOverCanvas;

	}

	protected String getCellCSSText(ListGridRecord record, int rowNum,
			int colNum) {
		/*
		 * if (getFieldName(colNum).equals("estimatedCost")) { double amount =
		 * 1000000000; if
		 * (Double.parseDouble(record.getAttribute("estimatedCost").replace(",",
		 * "") )> amount) { return "font-weight:bold; color:#d64949;"; } else {
		 * return super.getCellCSSText(record, rowNum, colNum); } } else
		 */if (getFieldName(colNum).equals("userStatus")) {

			if (record.getAttributeAsString("userStatus").contentEquals(
					"Active")) {

				return "font-weight:bold; color:#0817F6;"; // 008000
			} else if (record.getAttributeAsString("userStatus").contentEquals(
					"Inactive")) {

				return "font-weight:bold; color:#d64949;";
			}
			/*
			 * else
			 * if(record.getAttributeAsString("planStatusName").contentEquals
			 * (ApprovalConstants.REJECTED_HEADOFDEPT)){
			 * 
			 * return "font-weight:bold; color:#d64949;"; //d64949 }
			 */
			else {
				return super.getCellCSSText(record, rowNum, colNum);
			}
		} else {
			return super.getCellCSSText(record, rowNum, colNum);
		}
		// }
	}

	/**
	 * @return the editImg
	 */
	public ImgButton getEditImg() {
		return editImg;
	}

	/**
	 * @return the rollOverRecord
	 */
	public ListGridRecord getRollOverRecord() {
		return rollOverRecord;
	}
	
	// Expansion Component
	protected Canvas getExpansionComponent(final ListGridRecord record){
		
		final ListGrid grid = this;
		
		for(ListGridRecord gr :  grid.getRecords())
		{
			if(gr!=record)
			{
				grid.collapseRecord(gr);
			}
		}
		grid.selectRecord(record);
		
		VLayout layout = new VLayout(5);
		layout.setPadding(5);
		
	
		roleListGrid.setWidth(800);
		roleListGrid.setHeight(224);
		roleListGrid.setCellHeight(22);
		//permissionsGrid.setDataSource(record);
		roleListGrid.setCanExpandRecords(false);
		roleListGrid.createdByField.setHidden(true);
		roleListGrid.changedByField.setHidden(true);
		roleListGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		
		layout.addMember(roleListGrid);
		
		 HLayout hLayout = new HLayout(10);  
         hLayout.setAlign(Alignment.CENTER);  
 
         addRoleButton.setTop(250);  
         addRoleButton.addClickHandler(new ClickHandler() {  
             public void onClick(ClickEvent event) {  
            	// permissionsGrid.saveAllEdits();  
             }  
         });  
        // hLayout.addMember(saveButton);  

         
         removeButton.addClickHandler(new ClickHandler() {  
             public void onClick(ClickEvent event) {  
            	 //permissionsGrid.discardAllEdits();  
             }  
         });  
       //  hLayout.addMember(discardButton);  

          
         closeButton.addClickHandler(new ClickHandler() {  
             public void onClick(ClickEvent event) {  
                 grid.collapseRecord(record);  
             }  
         });  
        // hLayout.addMember(closeButton);  
                                     
         hLayout.setMembers(addRoleButton,removeButton,closeButton);
         layout.addMember(hLayout);  

         return layout;  
	
       
		
	}

	/**
	 * @return the closeButton
	 */
	public IButton getCloseButton() {
		return closeButton;
	}

	/**
	 * @return the addRoleButton
	 */
	public SaveButton getAddRoleButton() {
		return addRoleButton;
	}

	/**
	 * @return the removeButton
	 */
	public DeleteButton getRemoveButton() {
		return removeButton;
	}

	/**
	 * @return the roleListGrid
	 */
	public UserRoleListGrid getRoleListGrid() {
		return roleListGrid;
	}
	
}
