package com.planetsystems.monitoring.core;


/**
 * @author Planet Dev002
 * 
 */
public final class ProcnetConstants {

	/**
	 * defines the application key for the requisition type resolver jar file.
	 */
	public static final String APP_SETTING_KEY_REQUISITION_TYPE_RESOLVER_JAR = "REQUISITION_TYPE_RESOLVER_JAR";

	public static final String APP_SETTING_KEY_TAX_RATE = "TAX_RATE";
	/**
	 * the default email address from which all emails in the system will be
	 * coming from unless one is provided for in the application settings.
	 * 
	 * @see #GLOBAL_PROPERTY_APPLICATION_EMAIL_ADDRESS
	 */
	
	@Deprecated()
	public static String DEFAULT_FROM_EMAIL_ADDRESS = "procnet@planetsystems.co";
	/**
	 * defines the identifier of the default organisation unit.
	 */
	public static final String DEFAULT_ORGANISATION_UNIT_ID = "CE7C490B-A820-4283-BA18-13CE48BE98AF";
	/**
	 * Defines the application property name to access the application email
	 * address. <br/>
	 * To get the value for this property name, use
	 * {@link ApplicationContextProvider#getProperty(propertyName)}
	 */
	public static final String GLOBAL_PROPERTY_APPLICATION_EMAIL_ADDRESS = "procnet@planetsystems.co";
	public static final String GLOBAL_PROPERTY_APPLICATION_URL = "application.url";
	public static final String GLOBAL_PROPERTY_APPLICATION_URL_DEFAULT_VALUE = "http://192.168.0.75:8080/procnet-unra";
	public static final int GLOBAL_PROPERY_MINIMUM_PASSWORD_LENGTH = 8;

	/**
	 * the maximum number of records to return when queried through pages.
	 */
	public static final int MAX_NUM_PAGE_RECORD = 25;

	/**
	 * OS key
	 */
	public static final String OPERATING_SYSTEM_KEY = "os.name";
	/**
	 * Name of the OS running the system
	 */
	public static final String OPERATING_SYSTEM = System
			.getProperty(OPERATING_SYSTEM_KEY);

	/**
	 * os name FreeBSD
	 */
	public static final String OPERATING_SYSTEM_FREEBSD = "FreeBSD";
	/**
	 * os name Linux
	 */
	public static final String OPERATING_SYSTEM_LINUX = "Linux";

	/**
	 * os name Mac OS X
	 */
	public static final String OPERATING_SYSTEM_OSX = "Mac OS X";

	/**
	 * OS name Windows Vista
	 */
	public static final String OPERATING_SYSTEM_WINDOWS_VISTA = "Windows Vista";

	/**
	 * OS name Windows XP
	 */
	public static final String OPERATING_SYSTEM_WINDOWS_XP = "Windows XP";

	/**
	 * the property key used to access the name path of the user's home
	 * directory currently running this application
	 */
	public static final String USER_HOME_DIRECTORY_KEY = "user.home";

	/**
	 * default constructor. does not allow instantiation
	 */
	private ProcnetConstants() {
	}
}
