package com.planetsystems.monitoring.model.security;

public final class PermissionConstants {

	/**
	 * default constructor (Note: it is private because this class can't be
	 * instantiated)
	 */
	private PermissionConstants() {

	}
	

	@PermissionAnnotation(id = "A0A08826-96C0-438b-876E-55534ABE0461", description = "Ability to view user")
	public static final String PERM_VIEW_USER = "perm_view_user";

	@PermissionAnnotation(id = "CEC2B41D-841C-4FED-9A63-04E5649F8D22", description = "Ability to add or update users")
	public static final String PERM_SAVE_USER = "perm_save_user";

	@PermissionAnnotation(id = "8233207F-921A-413c-9492-81651B0C024D", description = "Ability to delete users")
	public static final String PERM_DELETE_USER = "perm_delete_user";

	@PermissionAnnotation(id = "4E77BDD1-2EAC-40BE-9A91-5CAF9CF6CFEC", description = "Ability to add users")
	public static final String PERM_ADD_USER = "perm_add_user";

	@PermissionAnnotation(id = "A3DBF242-F761-42E9-AC7C-9D2EFC716B44", description = "Ability to add or update a role")
	public static final String PERM_SAVE_ROLE = "perm_save_role";

	@PermissionAnnotation(id = "0C93831C-1667-49C0-8909-233424CA73B8", description = "Ability to delete a role")
	public static final String PERM_DELETE_ROLE = "perm_delete_role";

	@PermissionAnnotation(id = "FE446BB1-E8F8-40F4-9FAB-65FDD8C6183D", description = "Ability to add roles")
	public static final String PERM_ADD_ROLE = "perm_add_role";

	@PermissionAnnotation(id = "9FD9EAD9-C9E0-4885-B357-51504FD10D16", description = "Ability to view roles")
	public static final String PERM_VIEW_ROLE = "perm_view_role";

	@PermissionAnnotation(id = "BE77AA9B-60CC-47F7-B163-12B0064BE0FA", description = "Ability to access web resources if api is used in a web application")
	public static final String PERM_WEB_ACCESS = "perm_web_access";

	@PermissionAnnotation(id = "12078A16-C74D-4f83-BF2E-A4B6A50824FB", description = "Ability to view the administration control panel ")
	public static final String PERM_VIEW_ADMINISTRATION = "perm_view_administration";

	@PermissionAnnotation(id = "4ED96336-2928-4b51-9E57-5CD797F2C9DB", description = "Ability to edit/save/delete users")
	public static final String PERM_EDIT_USERS = "perm_edit_users";

	@PermissionAnnotation(id = "1043B4BB-2BC5-46e2-A8B9-A978B6B1AA98", description = "Ability to edit/save/delete roles")
	public static final String PERM_EDIT_ROLES = "perm_edit_roles";

	@PermissionAnnotation(id = "6829D3EF-5562-44AF-BB1D-606090220B77", description = "Ability to add budgets")
	public static final String PERM_ADD_BUDGET = "perm_add_budget";

	@PermissionAnnotation(id = "ABCC901E-DFFF-44BE-A332-79BDDF660EA0", description = "Ability to edit budgets.")
	public static final String PERM_EDIT_BUDGET = "perm_edit_budget";

	@PermissionAnnotation(id = "2205D5DF-5481-4D9A-820A-931C47E07BFE", description = "Ability to delete budgets.")
	public static final String PERM_DELETE_BUDGET = "perm_delete_budget";

	@PermissionAnnotation(id = "A055C29A-DB53-4FD6-AB11-DC6F37FC4B14", description = "Ability to view budgets.")
	public static final String PERM_VIEW_BUDGET = "perm_view_budget";

	@PermissionAnnotation(id = "A055C29A-DB53-4FD6-AB11-DC6F38VC4B14", description = "Ability to display budgets.")
	public static final String PERM_DISPLAY_BUDGET = "perm_display_budget";

	@PermissionAnnotation(id = "345GD29A-DB53-4FD6-AB11-DC6F37FC4B14", description = "Ability to display planning.")
	public static final String PERM_DISPLAY_PLANNING = "perm_display_planning";

	@PermissionAnnotation(id = "A055C29A-DB53-DDG6-AB11-DC6F37FC4B14", description = "Ability to display requistion.")
	public static final String PERM_DISPLAY_REQUISTION = "perm_display_requistion";

	@PermissionAnnotation(id = "A055C29A-DB53-4FD6-AB11-8GNE37FC4B14", description = "Ability to display tender.")
	public static final String PERM_DISPLAY_TENDER = "perm_display_tender";

	@PermissionAnnotation(id = "A055C29A-DB53-4D9A-AB11-DC6F37FC4B14", description = "Ability to display committe.")
	public static final String PERM_DISPLAY_COMMITEE = "perm_display_commitee";

	@PermissionAnnotation(id = "3b57f2be-ae4b-4228-b133-4ad1edb5ffae", description = "Ability to save plan")
	public static final String PERM_SAVE_PLAN = "perm_save_plan";

	@PermissionAnnotation(id = "e848598f-0528-40d1-a774-dc7d3ca93ea8", description = "Ability to edit plan")
	public static final String PERM_EDIT_PLAN = "perm_edit_plan";

	@PermissionAnnotation(id = "0c17f3a8-6431-49e7-b5d2-b657e064c9e2", description = "Ability to view plan")
	public static final String PERM_VIEW_PLAN = "perm_view_plan";

	@PermissionAnnotation(id = "118dedd2-fdac-44a0-a6a0-de388d885b0c", description = "Ability to delete plan")
	public static final String PERM_DELETE_PLAN = "perm_delete_plan";

	@PermissionAnnotation(id = "6dd1c7e7-4c92-4301-89a5-22dd34d28655", description = "Ability to save planningPeriod")
	public static final String PERM_SAVE_PLANNINGPERIOD = "perm_save_planningPeriod";

	@PermissionAnnotation(id = "12632a6c-2ee2-4da6-91ee-05a3ca8e44ef", description = "Ability to save planningPeriod")
	public static final String PERM_EDIT_PLANNINGPERIOD = "perm_edit_planningPeriod";

	@PermissionAnnotation(id = "99dd49c9-1708-4d44-99ac-908e0ee593ee", description = "Ability to view planningPeriod")
	public static final String PERM_VIEW_PLANNINGPERIOD = "perm_view_planningPeriod";

	@PermissionAnnotation(id = "4d90f5e9-9873-439d-87c6-d031af036f0b", description = "Ability to delete planningPeriod")
	public static final String PERM_DELETE_PLANNINGPERIOD = "perm_delete_planningPeriod";

	@PermissionAnnotation(id = "951ee8e0-6a06-4738-9cf2-23ed1ce2d8f8", description = "Ability to approve departmenatal plans")
	public static final String PERM_APPROVE_DEPARTMENTALPLANS = "perm_approve_departmentalPlans";

	@PermissionAnnotation(id = "14863ea2-226d-417b-903f-ac17e7b75dab", description = "Ability to approve directorate plans")
	public static final String PERM_APPROVE_DIRECTORATEPLANS = "perm_approve_directoratePlans";

	@PermissionAnnotation(id = "26e1d4a6-1784-4b0f-9291-d1c8441d9c19", description = "Ability to approve departmenatal plans")
	public static final String PERM_START_PLANNINGPROCESS = "perm_start_planningProcess";

	@PermissionAnnotation(id = "a194585d-af0b-4ec8-8de0-2268f9abfa01", description = "Ability to view directorate plan")
	public static final String PERM_VIEW_DIRECTORATEPLAN = "perm_view_directoratePlan";

	@PermissionAnnotation(id = "13f5ec0c-c8bb-4458-b8a9-450202c584d9", description = "Ability to view department plan")
	public static final String PERM_VIEW_DEPARTMENTALPLAN = "perm_view_departmentalPlan";

	@PermissionAnnotation(id = "ec72743b-087f-420e-88a6-43d2ba7bed5c", description = "Ability to view consolidated plan")
	public static final String PERM_VIEW_CONSOLIDATEDPLAN = "perm_view_consolidatedPlan";

	@PermissionAnnotation(id = "bb0b5dab-6f68-4007-8ee8-ea40724e86ce", description = "Ability to view budget allocations")
	public static final String PERM_VIEW_BUDGETALLOCATIONS = "perm_view_budgetAllocations";

	@PermissionAnnotation(id = "ee1dbe36-a0db-4e38-b712-4ec42d056fc2", description = "Ability to consolidate plans")
	public static final String PERM_CONSOLIDATE_PLANS = "perm_consolidate_plans";

	@PermissionAnnotation(id = "60E4BDA4-CEC5-4DA0-B663-6174538D827B", description = "Ability to consolidate plans")
	public static final String PERM_VIEW_REQUISTIONS = "perm_view_requistions";

	@PermissionAnnotation(id = "9B02181F-1D7D-4968-851F-4FF50F98B43C", description = "Ability to consolidate plans")
	public static final String PERM_SAVE_REQUISTIONS = "perm_save_requistions";

	@PermissionAnnotation(id = "04C164C2-237A-40D7-B347-E887797D454D", description = "Ability to consolidate plans")
	public static final String PERM_DELETE_REQUISTIONS = "perm_delete_requistions";

	@PermissionAnnotation(id = "A9CE3C3A-C251-4D06-BF3D-4B48CC711D8E", description = "Ability to consolidate plans")
	public static final String PERM_EDIT_REQUISTIONS = "perm_edit_requistions";

	@PermissionAnnotation(id = "1e1f4a6f-17e2-45cc-b9cd-e11cb7b307b5", description = "Ability to edit commitees")
	public static final String PERM_EDIT_COMMITEE = "perm_edit_commitee";

	@PermissionAnnotation(id = "41b487c3-bc13-46f4-b553-2a449bfba448", description = "Ability to save commitees")
	public static final String PERM_SAVE_COMMITEE = "perm_save_commitee";

	@PermissionAnnotation(id = "2fae7004-5144-4805-861a-8afe1919dc47", description = "Ability to view commitee")
	public static final String PERM_VIEW_COMMITEE = "perm_view_commitee";

	@PermissionAnnotation(id = "5d7809dc-8cdd-4094-a48f-bd65c8f0ab60", description = "Ability to delete commitee")
	public static final String PERM_DELETE_COMMITEE = "perm_delete_commitee";

	@PermissionAnnotation(id = "aecbcbdd-7cec-433a-8852-4064ba7ebb75", description = "Ability to save agenda")
	public static final String PERM_SAVE_AGENDA = "perm_save_agenda";

	@PermissionAnnotation(id = "ec7cc839-3c17-45eb-85b2-e1ad07ca73ee", description = "Ability to edit agenda")
	public static final String PERM_EDIT_AGENDA = "perm_edit_agenda";

	@PermissionAnnotation(id = "6986b3b7-fe31-4d1f-bd48-2a406bc31762", description = "Ability to delete agenda")
	public static final String PERM_DELETE_AGENDA = "perm_delete_agenda";

	@PermissionAnnotation(id = "a459ecff-a0fc-4d67-91e2-28ab28558596", description = "Ability to view agenda")
	public static final String PERM_VIEW_AGENDA = "perm_view_agenda";

	@PermissionAnnotation(id = "b9a37795-66e5-476c-a323-b2ec6f0a00fd", description = "Ability to save meetings")
	public static final String PERM_SAVE_MEETINGS = "perm_save_meetings";

	@PermissionAnnotation(id = "8d2f8418-d2b7-4033-b214-7d05d0af6ef9", description = "Ability to edit meetings")
	public static final String PERM_EDIT_MEETINGS = "perm_edit_meetings";

	@PermissionAnnotation(id = "62ccf0d2-202c-477e-adfa-4fdb6af7803d", description = "Ability to delete meetings")
	public static final String PERM_DELETE_MEETINGS = "perm_delete_meetings";

	@PermissionAnnotation(id = "b4fede8c-f0e0-4676-ad00-46bb482b7f6c", description = "Ability to view meetings")
	public static final String PERM_VIEW_MEETINGS = "perm_view_meetings";

	/*
	 * 6cc04d34-46af-4a61-8d1f-da1d5313ab45
	 */

	@PermissionAnnotation(id = "d588c2d2-2145-45ca-84fc-8f993d313a31", description = "Ability to display purchase orders")
	public static final String PERM_DISPLAY_PURCHASE_ORDERS = "perm_display_purchase_orders";
	
	@PermissionAnnotation(id = "1427f3b5-28f0-49c2-b6b8-fe4148bfba78", description = "Ability to display bidding/solicitation")
	public static final String PERM_DISPLAY_BIDDING = "perm_display_bidding";
	
	@PermissionAnnotation(id = "2d032678-a2ef-4f13-9967-9a7fc3d8ed15", description = "Ability to display supplier & catalogues")
	public static final String PERM_DISPLAY_SUPPLIER_AND_CATALOGUES = "perm_display_suppliers_and_catalogues";
	
	@PermissionAnnotation(id = "0bdc8937-f0ec-4400-88a1-0298c060d9d4", description = "Ability to display evaluation")
	public static final String PERM_DISPLAY_EVALUATION = "perm_display_evaluation";
	
	@PermissionAnnotation(id = "3d1b55f6-5b35-4b06-aeac-23ddf3709fc6", description = "Ability to display contracts management")
	public static final String PERM_DISPLAY_CONTRACTS_MANAGEMENT = "perm_display_contracts_management";
	
	@PermissionAnnotation(id = "2a0ec466-514e-4c9f-8b31-c5edfa6fe7ee", description = "Ability to display inventory and stores")
	public static final String PERM_DISPLAY_INVENTORY_AND_STORES = "perm_display_inventory_and_stores";
	
	@PermissionAnnotation(id = "6cc04d34-46af-4a61-8d1f-da1d5313ab45", description = "Ability to display procurement audit")
	public static final String PERM_DISPLAY_PROCUREMENT_AUDIT = "perm_display_procurement_audit";
	
	
}