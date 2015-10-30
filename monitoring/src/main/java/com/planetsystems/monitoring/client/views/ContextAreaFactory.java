package com.planetsystems.monitoring.client.views;

import com.smartgwt.client.widgets.Canvas;

public interface ContextAreaFactory {
	
  Canvas create();

  String getID();

  String getDescription();
}