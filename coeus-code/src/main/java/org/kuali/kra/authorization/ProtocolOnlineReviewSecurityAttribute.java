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
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.api.action.ActionItem;
import org.kuali.rice.kew.api.actionlist.ActionListService;
import org.kuali.rice.kew.api.document.Document;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.framework.document.security.DocumentSecurityAttribute;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kns.service.DocumentHelperService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.document.DocumentAuthorizer;


public class ProtocolOnlineReviewSecurityAttribute implements DocumentSecurityAttribute {

    private DocumentHelperService documentHelperService;
    private ActionListService actionListService;

    /*
     * Cannot use canopen doc perm here because it does not exist for ONLR docs
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

}
