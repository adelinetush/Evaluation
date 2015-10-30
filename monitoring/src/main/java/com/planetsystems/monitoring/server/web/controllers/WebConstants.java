package com.planetsystems.monitoring.server.web.controllers;

public final class WebConstants {

	/**
	 * parameter name for the error message in the model
	 */
	public static final String ERROR_MESSAGE_MODEL_PARAM = "errorMessage";

	/**
	 * parameter name for the system message in the model
	 */
	public static final String SYSTEM_MESSAGE_MODEL_PARAM = "systemMessage";

	/**
	 * the default name of the country supported by this application.
	 */
	public static final String DEFAULT_COUNTRY_NAME = "Uganda";

	/**
	 * defines the default date format.
	 */
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";

	/**
	 * the session key used to reference the identifier of a user
	 */
	public static final String USER_SESSION_KEY = "4DC45EDE-215B-4CA7-BBE6-7170BAD20282";

	/**
	 * the session key used to reference the identifier of a role.
	 */
	public static final String ROLE_SESSION_KEY = "F38916B5-1C99-4679-9FD0-183959638A3F";

	/**
	 * the session key used to reference the identifier of a concept.
	 */
	public static final String CONCEPT_SESSION_KEY = "89590AEF-E919-483F-BA55-224A725FDDE9";

	/**
	 * the number of items to fetch per query.
	 */
	public static final int ITEMS_PER_PAGE = 25;

	/**
	 * defines the task parameter name for the form id.
	 */
	public static final String TASK_PARAMETER_NAME_FORM_ID = "formId";

	/**
	 * the id of the requisition approval form or view.
	 */
	public static final String REQUISITION_APPROVAL_FORM_ID = "requisition_approval";

	public static final String CONFIRM_EXCEPTIONAL_REQUISITION_FORM_ID = "confirm_exceptional_requisition";

	public static final String CONFIRM_ADHOC_REQUISITION_FORM_ID = "confirm_adhoc_requisition";

	/**
	 * the id of the input procurement plan item view
	 */
	public static final String INPUT_PROCUREMENTPLAN_ITEM_FORM_ID = "input_procurement_plan_item";

	/**
	 * the id of the approve procurement plan item form or view
	 */
	public static final String APPROVE_PROCUREMENTPLAN_ITEM_FORM_ID = "approve_procurement_plan_item";

	/**
	 * the id of the approve local purchase order form or view.
	 */
	public static final String APPROVE_LPO_FORM_ID = "approve_lpo";

	/**
	 * the id of the request for quotation form or view
	 */
	public static final String REQUEST_FOR_QUOTATION_FORM_ID = "request_for_quotations";

	/**
	 * the id of the prepare procurement strategy step form identifier.
	 */
	public static final String PREPARE_PSS_FORM_ID = "prepare_pss";

	/**
	 * the id of the approve procurement strategy step form or view identifier.
	 */
	public static final String APPROVE_PSS_FORM_ID = "approve_pss";

	public static final String TASK_PARAMETER_COMMITTEE_ID = "committeeId";

	public static final String TASK_PARAMETER_PSS_ID = "pssId";

	public static final String PREPARE_PAS_FORM_ID = "prepare_pas";

	public static final String APPROVE_PAS_FORM_ID = "approve_pas";
}
