/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.sys.impl.workflow;

import org.kuali.rice.kew.actionlist.CustomActionListAttribute;
import org.kuali.rice.kew.api.action.ActionItem;
import org.kuali.rice.kew.api.action.ActionSet;
import org.kuali.rice.kew.api.actionlist.DisplayParameters;


/**
 * This class is our custom action list for Notifications in KEW.  It's wired in as the implementation to use as part of the NotificationData.xml 
 * bootstrap file for KEW.
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class CopyCustomActionListAttribute implements CustomActionListAttribute {
    private static final String CUSTOM_COPY_ACTION = "P";

    @Override
    public DisplayParameters getDocHandlerDisplayParameters(String principalId, ActionItem actionItem) throws Exception {
    	DisplayParameters dp = DisplayParameters.Builder.create(new Integer(300)).build();
    	return dp;
    }

    @Override
    public ActionSet getLegalActions(String principalId, ActionItem actionItem) throws Exception {
    	ActionSet as = ActionSet.Builder.create().build();
    	as.addAction(CUSTOM_COPY_ACTION);
        as.addFyi();
        as.addAcknowledge();
    	return as;
    }
}
