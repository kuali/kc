/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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