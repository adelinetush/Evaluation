package com.planetsystems.monitoring.client.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

public class UiUtils {
    
   

   

    

    /**
     * move the window to a url that is relative to the current gwt module.
     *
     * @param relativeURL
     */
    public static void goToRelativePage(final String relativeURL) {
        Window.Location.assign(GWT.getHostPageBaseURL() + relativeURL);
    }

    

   
   
}