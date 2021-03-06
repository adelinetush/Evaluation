/**
 * Copyright 2010 upTick Pty Ltd
 * 
 * Licensed under the terms of the GNU General Public License version 3 
 * as published by the Free Software Foundation. You may obtain a copy of the
 * License at: http://www.gnu.org/copyleft/gpl.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations 
 * under the License.
 */

package com.planetsystems.monitoring.client.widgets;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

//import au.com.uptick.serendipity.client.Serendipity;

public class ToolBar extends HLayout {
	
	public static final String NEW_BUTTON = "toolbar/new.png";
	  public static final String PRINT_PREVIEW_BUTTON = "toolbar/printpreview.png";
	  public static final String EXPORT_BUTTON = "toolbar/export.png";
	  public static final String MAIL_MERGE_BUTTON = "toolbar/mailmerge.png";
	  public static final String ASSIGN_BUTTON = "toolbar/assign.png";
	  public static final String DELETE_BUTTON = "toolbar/delete.png";
	  public static final String EMAIL_BUTTON = "toolbar/sendemail.png";
	  public static final String ATTACH_BUTTON = "toolbar/attach.png";
	  public static final String REFRESH_BUTTON = "toolbar/refresh.png";

	  public static final String WORKFLOW_BUTTON = "toolbar/workflow.png";
	  public static final String REPORTS_BUTTON = "toolbar/reports.png";

	  public static final String SAVE_BUTTON = "toolbar/save.png";
	  public static final String SAVE_AND_CLOSE_BUTTON = "toolbar/saveandclose.png";
	  public static final String HELP_BUTTON = "toolbar/help.png";
	  
	  public static final String EDIT_BUTTON = "toolbar/save.png";
	  public static final String LOAD_BUTTON = "icons/48/businessunits.gif";
	  public static final String UPLOAD_BUTTON = "icons/48/databaseupload.gif";

	  public static final String TOOLBAR_HEIGHT = "25px";
	  public static final String TOOLSTRIP_WIDTH = "*";

	  public static final String MARK_COMPLETE="toolbar/tick.png";
	  public static final String CLAIM_TASK="toolbar/task.png";
	  public static final String PERSONAL_TASK="toolbar/user_gray.png";
	  public static final String GROUP_TASK="toolbar/teams.png";
	  public static final String DEEGATE_TASK="toolbar/assign.png";
	  public static final String COMPLETE_TASK="toolbar/tick.png";
	  
	  public static final String ACTIVATE_BUTTON="toolbar/tick.png";
	  public static final String DEACTIVATE_BUTTON="toolbar/cancel.png";
	  
	  protected final ToolStrip toolStrip;

	  public ToolBar() {
	    super();

	    // initialise the ToolBar layout container
	    this.setStyleName("crm-ToolBar");
	    this.setHeight(TOOLBAR_HEIGHT);

	    // initialise the ToolBar's ToolStrip
	    toolStrip = new ToolStrip();
	    toolStrip.setHeight(TOOLBAR_HEIGHT);
	    toolStrip.setWidth(TOOLSTRIP_WIDTH);

	    // add the ToolStrip to the ToolBar's layout container
	    this.addMember(toolStrip);
	  }

	  public ToolStripButton addButton(String title, ClickHandler clickHandler) {
	    ToolStripButton button = new ToolStripButton();
	    button.setTitle(title);

	    if (clickHandler != null)
	      button.addClickHandler(clickHandler);

	    toolStrip.addButton(button);

	    return button;
	  }

	  public ToolStripButton addButton(String icon, String tooltip, ClickHandler clickHandler) {
	    ToolStripButton button = new ToolStripButton();
	    button.setIcon(icon);
	    button.setTooltip(tooltip);

	    if (clickHandler != null)
	      button.addClickHandler(clickHandler);

	    toolStrip.addButton(button);

	    return button;
	  }

	  public ToolStripButton addButton(String icon, String title, String tooltip, ClickHandler clickHandler) {
	    ToolStripButton button = new ToolStripButton();
	    button.setIcon(icon);
	    button.setTitle(title);
	    button.setTooltip(tooltip);

	    if (clickHandler != null)
	      button.addClickHandler(clickHandler);

	    toolStrip.addButton(button);

	    return button;
	  }

	  public Label addLabel(String contents) {
	    Label label = new Label();
	    label.setContents(contents);

	    toolStrip.addMember(label);

	    return label;
	  }


	  public void addSeparator() {
	    toolStrip.addSeparator();
	  }
  }

