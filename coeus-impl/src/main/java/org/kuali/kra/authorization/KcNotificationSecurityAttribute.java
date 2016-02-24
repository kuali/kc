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
package org.kuali.kra.authorization;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.api.action.ActionItem;
import org.kuali.rice.kew.api.actionlist.ActionListService;
import org.kuali.rice.kew.api.document.Document;
import org.kuali.rice.kew.framework.document.security.DocumentSecurityAttribute;

public class KcNotificationSecurityAttribute implements DocumentSecurityAttribute {


    private static final long serialVersionUID = 3547172238972658576L;

    private ActionListService actionListService;
    
    /**
     * This method checks if the supplied document is in the current user's action list and returns true if so, otherwise returns false.
     * 
     * @see org.kuali.rice.kew.framework.document.security.DocumentSecurityAttribute#isAuthorizedForDocument(java.lang.String, org.kuali.rice.kew.api.document.Document)
     */
    @Override
    public boolean isAuthorizedForDocument(String principalId, Document document) {
        boolean retVal= false;
        String documentId = document.getDocumentId();
        // get the action items for this document and check if any of them have the same principal id as the current user
        List<ActionItem> actionItemsForDocument = this.getActionListService().getAllActionItems(documentId);
        for(ActionItem actionItem: actionItemsForDocument) {
            if(StringUtils.equals(principalId, actionItem.getPrincipalId())) {
                retVal = true;
                break;                    
            }
        }
        return retVal;
    }
    
    protected ActionListService getActionListService() {
        if(this.actionListService == null) {
            this.actionListService = KewApiServiceLocator.getActionListService();
        }
        return this.actionListService;
    }

    public void setActionListService(ActionListService actionListService) {
        this.actionListService = actionListService;
    }
}
