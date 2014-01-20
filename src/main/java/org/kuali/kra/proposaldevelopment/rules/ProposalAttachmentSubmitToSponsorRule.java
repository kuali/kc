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
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.kra.infrastructure.KeyConstants.ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.ProposalDevelopmentUtils;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

public class ProposalAttachmentSubmitToSponsorRule extends ResearchDocumentRuleBase implements DocumentAuditRule {

    public static final String ATTACHMENTS_AUDIT_CLUSTER_KEY = "proposalAttachmentsAuditWarnings";

    private static final String AUDIT_PARM = ProposalDevelopmentUtils.AUDIT_INCOMPLETE_PROPOSAL_ATTATCHMENTS_PARM;
    private static final String AUDIT_PARAMETER_VALUE_NO = "N";

    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentProposalAttachmentsAuditRule.class);
    
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        DevelopmentProposal developmentProposal = ((ProposalDevelopmentDocument) document).getDevelopmentProposal();

        valid &= validateIncompleteAttachments(developmentProposal);

        return valid;
    }
    
    //Validate attachments are complete during a submit to sponsor.
    private boolean validateIncompleteAttachments(DevelopmentProposal developmentProposal) {
        boolean valid = true;
        Parameter attachmentAuditParam = getParameterService().getParameter(ProposalDevelopmentDocument.class, AUDIT_PARM);
        if(attachmentAuditParam == null) {
            LOG.warn("System parameter AUDIT_INCOMPLETE_PROPOSAL_ATTACHMENTS is missing or invalid");
            throw new RuntimeException("System parameter AUDIT_INCOMPLETE_PROPOSAL_ATTACHMENTS is missing or invalid"); 
        }

        String auditIncompleteAttachments = attachmentAuditParam.getValue();
        if(auditIncompleteAttachments.equals(AUDIT_PARAMETER_VALUE_NO)) {
            //incomplete attachments were allowed to enter workflow, but must be set to complete before submit to sponsor.
            List<Narrative> narratives = developmentProposal.getNarratives();
            int narrativeIndex = 0;
            for(Narrative narrative : narratives) {
                if(narrative.getModuleStatusCode().equals("I")) {
                    getAuditErrors()
                        .add(new AuditError("document.developmentProposalList[0].narrative[" + narrativeIndex + "].moduleStatusCode",
                                ERROR_PROPOSAL_ATTACHMENT_NOT_COMPLETE,
                                Constants.ATTACHMENTS_PAGE));
                    valid &= false;
                }
                narrativeIndex++;
            }
        }

        return valid;
    }

    /**
     * This method should only be called if an audit error is intending to be added because it will actually add a <code>{@link List<AuditError>}</code>
     * to the auditErrorMap.
     * 
     * @return List of AuditError instances
     */
    @SuppressWarnings("unchecked")
    private List<AuditError> getAuditErrors() {
        List<AuditError> auditErrors = new ArrayList<AuditError>();
        
        if (!KNSGlobalVariables.getAuditErrorMap().containsKey(ATTACHMENTS_AUDIT_CLUSTER_KEY)) {
            KNSGlobalVariables.getAuditErrorMap().put(ATTACHMENTS_AUDIT_CLUSTER_KEY, 
                    new AuditCluster(Constants.ABSTRACTS_AND_ATTACHMENTS_PANEL, auditErrors, Constants.AUDIT_ERRORS));
        } else {
            auditErrors = ((AuditCluster) KNSGlobalVariables.getAuditErrorMap().get(ATTACHMENTS_AUDIT_CLUSTER_KEY)).getAuditErrorList();
                }
        
        return auditErrors;
            }

}
