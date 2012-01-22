/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.document.authorizer;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.kew.KraDocumentRejectionService;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.util.GlobalVariables;


/**
 * 
 * This class is to check authorization to answer protocol questionnaire.
 */
public class AnswerProposalQuestionnaireAuthorizer extends ProposalAuthorizer {

  
    /**
     * @see org.kuali.kra.proposaldevelopment.document.authorizer.ProposalAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.proposaldevelopment.document.authorization.ProposalTask)
     */
    public boolean isAuthorized(String userId, ProposalTask task) {
        ProposalDevelopmentDocument doc = task.getDocument();
        boolean hasBeenRejected=KraServiceLocator.getService(KraDocumentRejectionService.class).isDocumentOnInitialNode(doc);
        return !task.getDocument().isViewOnly()
                && !isPessimisticLocked(task.getDocument())
                && (!kraWorkflowService.isInWorkflow(task.getDocument()) || hasBeenRejected);
        // kcirb-876 : Answer questionnaire permission is not needed   
           //     && hasPermission(userId, task.getProtocol(), PermissionConstants.ANSWER_PROTOCOL_QUESTIONNAIRE);
    }
    
    protected boolean isPessimisticLocked(Document document) {
        boolean isLocked = false;
        for (PessimisticLock lock : document.getPessimisticLocks()) {
            // if lock is owned by current user, do not display message for it
            if (!lock.isOwnedByUser(GlobalVariables.getUserSession().getPerson())) {
                isLocked = true;
            }
        }
        return isLocked;
    }

}
