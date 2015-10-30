package com.planetsystems.monitoring.client;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

public class InternationalizationTest {

	public static void main(String[] args){

		
		ResourceBundle bundle1 = ResourceBundle.getBundle("views");
		displayValues(bundle1);

		Locale defaultLocale = Locale.getDefault();
		ResourceBundle bundle2 = ResourceBundle.getBundle("TestBundle", defaultLocale);
		displayValues(bundle2);

		Locale swedishLocale = new Locale("sv", "SE");
		ResourceBundle bundle3 = ResourceBundle.getBundle("TestBundle", swedishLocale);
		displayValues(bundle3);

		Locale nonexistentLocale = new Locale("xx", "XX");
		ResourceBundle bundle4 = ResourceBundle.getBundle("TestBundle", nonexistentLocale);
		displayValues(bundle4);
		
		
	}

	public static void displayValues(ResourceBundle bundle) {
		System.out.println("hello message:" + bundle.getString("my.hello"));
		System.out.println("goodbye message:" + bundle.getString("my.goodbye"));
		System.out.println("question message:" + bundle.getString("my.question"));
		System.out.println();
	}

}