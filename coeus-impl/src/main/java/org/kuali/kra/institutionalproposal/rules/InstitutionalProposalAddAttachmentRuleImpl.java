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
package org.kuali.kra.institutionalproposal.rules;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.attachments.InstitutionalProposalAttachment;

public class InstitutionalProposalAddAttachmentRuleImpl extends KcTransactionalDocumentRuleBase implements InstitutionalProposalAddAttachmentRule {

    private static final String ATTACHMENT_TYPE_CODE = "institutionalProposalAttachmentBean.newAttachment.attachmentTypeCode";
    private static final String NEW_FILE = "institutionalProposalAttachmentBean.newAttachment.newFile";
    public static final String INDEXED_ATTACHMENT_TYPE_CODE = "document.institutionalProposalList[0].instProposalAttachments[%d].attachmentTypeCode";

    @Override
    public boolean processAddInstitutionalProposalAttachmentBusinessRules(InstitutionalProposalAddAttachmentRuleEvent institutionalProposalRuleEvent) {
        InstitutionalProposalAttachment proposalAttachment = institutionalProposalRuleEvent.getInstitutionalProposalAttachmentForValidation();
        boolean valid=true;
        if( proposalAttachment.getAttachmentTypeCode()  == null ) {
            valid = false;
                reportError(ATTACHMENT_TYPE_CODE, KeyConstants.INSTITUTIONAL_PROPOSAL_ATTACHMENT_TYPE_CODE_REQUIRED);
        }
        
        if(!getKcAttachmentService().doesNewFileExist(proposalAttachment.getNewFile())) {
            valid = false;
            reportError(NEW_FILE, KeyConstants.INSTITUTIONAL_PROPOSAL_ATTACHMENT_FILE_REQUIRED);
        }
        return valid;
    }
    
    @Override
    public boolean processSaveInstitutionalProposalAttachment(InstitutionalProposalAddAttachmentRuleEvent institutionalProposalRuleEvent, int i) {
        InstitutionalProposalAttachment proposalAttachment = institutionalProposalRuleEvent.getInstitutionalProposalDocument().getInstitutionalProposal().getInstProposalAttachment(i);
        boolean valid=true;
        if( proposalAttachment.getAttachmentTypeCode()  == null ) {
            valid = false;
            reportError(String.format(INDEXED_ATTACHMENT_TYPE_CODE,i), KeyConstants.INSTITUTIONAL_PROPOSAL_ATTACHMENT_TYPE_CODE_REQUIRED);
        }
         return valid;
    }

    private KcAttachmentService getKcAttachmentService() {
        return KcServiceLocator.getService(KcAttachmentService.class);
    }
    
    
}