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
package org.kuali.coeus.propdev.impl.questionnaire;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;

public class ProposalDevelopmentQuestionnaireHelper extends QuestionnaireHelperBase {

    private static final long serialVersionUID = 8595107639632039291L;

    private ProposalDevelopmentDocumentForm proposalDevelopmentDocumentForm;
    private ProposalDevelopmentDocument document;

    public ProposalDevelopmentQuestionnaireHelper(ProposalDevelopmentDocumentForm form) {
        this.proposalDevelopmentDocumentForm = form;
        this.document = form.getProposalDevelopmentDocument();
    }
    
    public ProposalDevelopmentQuestionnaireHelper(ProposalDevelopmentDocument document) {
        this.document = document;
    }
    
    @Override
    public String getModuleCode() {
        return CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE;
    }

    @Override
    public ModuleQuestionnaireBean getModuleQnBean() {
        return new ProposalDevelopmentModuleQuestionnaireBean(document.getDevelopmentProposal());
    }

    protected ProposalDevelopmentDocument getDocument() {
        return document;
    }

    public void setDocument(ProposalDevelopmentDocument document) {
        this.document = document;
    }

    public ProposalDevelopmentDocumentForm getProposalDevelopmentDocumentForm() {
        return proposalDevelopmentDocumentForm;
    }

    public void setProposalDevelopmentForm(ProposalDevelopmentDocumentForm proposalDevelopmentDocumentForm) {
        this.proposalDevelopmentDocumentForm = proposalDevelopmentDocumentForm;
    }
}
