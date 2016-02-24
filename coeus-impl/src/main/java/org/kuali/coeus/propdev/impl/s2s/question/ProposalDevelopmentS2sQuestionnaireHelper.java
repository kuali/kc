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
package org.kuali.coeus.propdev.impl.s2s.question;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentQuestionnaireHelper;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;

import java.util.List;

public class ProposalDevelopmentS2sQuestionnaireHelper extends ProposalDevelopmentQuestionnaireHelper {

    private static final long serialVersionUID = 8595107639632039291L;
    private transient ProposalDevelopmentS2sQuestionnaireService proposalDevelopmentS2sQuestionnaireService;
    
    
    public ProposalDevelopmentS2sQuestionnaireHelper(ProposalDevelopmentDocumentForm form) {
        super(form);
    }
    
    @Override
    public String getModuleCode() {
        return CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE;
    }

    @Override
    public ModuleQuestionnaireBean getModuleQnBean() { 
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentS2sModuleQuestionnaireBean(getDocument().getDevelopmentProposal());
        return moduleQuestionnaireBean;
    }
  
    @SuppressWarnings("unchecked")
    
    /**
     * 
     * This method get/setup questionnaire answers when 'questionnaire' page is clicked.
     */
    public void populateAnswers() {
        List<AnswerHeader> headers = getProposalDevelopmentS2sQuestionnaireService().getProposalS2sAnswerHeaders(getDocument().getDevelopmentProposal());
        setAnswerHeaders(headers);
        resetHeaderLabels();
    }
    
  

    /**
     * Gets the proposalDevelopmentS2sQuestionnaireService attribute. 
     * @return Returns the proposalDevelopmentS2sQuestionnaireService.
     */
    public ProposalDevelopmentS2sQuestionnaireService getProposalDevelopmentS2sQuestionnaireService() {
        if (proposalDevelopmentS2sQuestionnaireService == null) {
            this.proposalDevelopmentS2sQuestionnaireService = KcServiceLocator.getService(ProposalDevelopmentS2sQuestionnaireService.class);
        }
        return proposalDevelopmentS2sQuestionnaireService;
    }


}
