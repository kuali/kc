/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.DocumentAuditRule;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.service.SponsorService;

public class ProposalDevelopmentProposalAttachmentsAuditRule  implements DocumentAuditRule{
    
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)document;
            List<AuditError> auditErrors = new ArrayList<AuditError>();
            int i = 0;
            for(Narrative narrative: proposalDevelopmentDocument.getDevelopmentProposal().getNarratives()) {
    
             if (narrative.getModuleStatusCode().equals("I")){
                 valid = false;            
                 auditErrors.add(new AuditError("document.developmentProposalList[0].narrative["+i+"].moduleStatusCode", KeyConstants.ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE, Constants.ATTACHMENTS_PAGE));
             }
             i++;
            }
            if(getSponsorService().isSponsorNihMultiplePi(proposalDevelopmentDocument.getDevelopmentProposal())){
                boolean attachment = true;                 
                for( ProposalPerson proposalPerson: proposalDevelopmentDocument.getDevelopmentProposal().getInvestigators()){
                    if(proposalPerson.isMultiplePi()){  
                        for (Narrative narrative : proposalDevelopmentDocument.getDevelopmentProposal().getNarratives()) {                                       
                             if(narrative.getNarrativeTypeCode() != null 
                                     &&  Integer.parseInt(narrative.getNarrativeTypeCode()) == Constants.PHS_RESTRAININGPLAN_PILEADERSHIPPLAN_ATTACHMENT
                                     ||  Integer.parseInt(narrative.getNarrativeTypeCode()) == Constants.PHS_RESEARCHPLAN_MULTIPLEPILEADERSHIPPLAN){
                                attachment = false;
                                break;
                             }
                         }
                    }
                }   
                if(attachment) {
                    valid=false;
                    auditErrors.add(new AuditError("document.developmentProposalList[0].narrative", KeyConstants.ERROR_PROPOSAL_ATTACHMENT_NOT_FOUND, Constants.ATTACHMENTS_PAGE));
                }
            }  
            if (auditErrors.size() > 0) {
                GlobalVariables.getAuditErrorMap().put("proposalAttachmentsAuditWarnings", new AuditCluster(Constants.ABSTRACTS_AND_ATTACHMENTS_PANEL, auditErrors, Constants.AUDIT_ERRORS));
            }
            
            
            return valid;
    }
    private SponsorService getSponsorService() {
        return KraServiceLocator.getService(SponsorService.class);
    }
}
