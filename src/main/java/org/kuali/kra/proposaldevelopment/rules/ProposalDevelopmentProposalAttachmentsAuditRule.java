/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rules;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.document.Document;
import org.kuali.core.rule.DocumentAuditRule;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.bo.Narrative;

public class ProposalDevelopmentProposalAttachmentsAuditRule  implements DocumentAuditRule{
    
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
            List<AuditError> auditErrors = new ArrayList<AuditError>();
            int i = 0;
            for(Narrative narrative: proposalDevelopmentDocument.getNarratives()) {
    
             if (narrative.getModuleStatusCode().equals("I")){
                 valid = false;            
                 auditErrors.add(new AuditError("document.narrative["+i+"].moduleStatusCode", KeyConstants.ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE, Constants.ATTACHMENTS_PAGE));
             }
             i++;
            }
            if (auditErrors.size() > 0) {
                GlobalVariables.getAuditErrorMap().put("proposalAttachmentsAuditWarnings", new AuditCluster(Constants.ABSTRACTS_AND_ATTACHMENTS_PANEL, auditErrors, Constants.AUDIT_ERRORS));
            }
            
            
            return valid;
    }
}
