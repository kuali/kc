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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.module.CoeusSubModule;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireService;
import org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireUsage;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.answer.ModuleQuestionnaireBean;
import org.kuali.coeus.common.questionnaire.framework.answer.QuestionnaireAnswerService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("proposalDevelopmentS2sQuestionnaireService")
public class ProposalDevelopmentS2sQuestionnaireServiceImpl implements ProposalDevelopmentS2sQuestionnaireService {

	private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentS2sQuestionnaireServiceImpl.class);

	@Autowired
	@Qualifier("businessObjectService")
	private BusinessObjectService businessObjectService;
	@Autowired
	@Qualifier("questionnaireAnswerService")
    private QuestionnaireAnswerService questionnaireAnswerService;
	@Autowired
	@Qualifier("questionnaireService")
    private QuestionnaireService questionnaireService;

	@Override
    public List<AnswerHeader> getProposalAnswerHeaderForForm(DevelopmentProposal proposal, String oppNameSpace, String formName) {
        return getProposalS2sAnswerHeaders(proposal,oppNameSpace,formName);
    }
    
    @Override
    public List<AnswerHeader> getProposalS2sAnswerHeaders(DevelopmentProposal developmentProposal) {
        return getProposalS2sAnswerHeaders(developmentProposal,null,null);
    }
    
    /**
     * Gets the AnswerHeaders for a particular proposal.  This is filtered by the oppNameSpace and the formName.  If oppNameSpace and formName are null, no filtering is done on that field.
     * @param developmentProposal The proposal
     * @param oppNameSpace The name space of the opportuniy form to filter the answer headers on. If null no filtering is done.
     * @param formName The formName to filter the results by.  If formName is null no filtering is done.
     * @return
     */
    protected List<AnswerHeader> getProposalS2sAnswerHeaders(DevelopmentProposal developmentProposal,String oppNameSpace, String formName) {
        
        S2sOpportunity opp = developmentProposal.getS2sOpportunity();
        Set<AnswerHeader> results = new HashSet<AnswerHeader>();
        List<AnswerHeader> rawAnswerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(getModuleQnBean(developmentProposal));

        if (opp!=null) {
            for (AnswerHeader header : rawAnswerHeaders) {
                for (S2sOppForms oppForms : opp.getS2sOppForms()) {
                    if ((oppNameSpace == null || StringUtils.equals(oppForms.getS2sOppFormsId().getOppNameSpace(), oppNameSpace))
                            && (formName==null||StringUtils.equals(oppForms.getFormName(), formName))) {
                        if (oppForms.getInclude() == null ? Boolean.FALSE : oppForms.getInclude()) {
                            List<QuestionnaireUsage> usages = getQuestionnaireUsages(oppForms.getS2sOppFormsId().getOppNameSpace(), oppForms.getFormName(), developmentProposal);
                            for (QuestionnaireUsage usage : usages) {
                                if (header.getQuestionnaire().getQuestionnaireSeqId().equals(usage.getQuestionnaire().getQuestionnaireSeqId())) {
                                    results.add(header);
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return new ArrayList<AnswerHeader>(results);
    }
    

    public List<QuestionnaireUsage> getQuestionnaireUsages(String oppNameSpace, String formName, DevelopmentProposal proposal) {
        Set<QuestionnaireUsage> result = new HashSet<QuestionnaireUsage>();
        ModuleQuestionnaireBean questionnaireBean = getModuleQnBean(proposal);
        questionnaireBean.setModuleSubItemCode(CoeusSubModule.PROPOSAL_S2S_SUBMODULE);
        List<QuestionnaireUsage> usages = getQuestionnaireAnswerService().getPublishedQuestionnaire(questionnaireBean);
        List<S2sOppFormQuestionnaire> s2sOppFormQuestionnaires = findOppFormToQuestionnaires(oppNameSpace,formName);
        
        for (QuestionnaireUsage usage : usages) {    
            for (S2sOppFormQuestionnaire oppFormQuestionnaire : s2sOppFormQuestionnaires) {
                if (oppFormQuestionnaire.getQuestionnaireId().toString().equals(usage.getQuestionnaire().getQuestionnaireSeqId())) {
                    result.add(usage);
                }
            }
        }
        return new ArrayList<QuestionnaireUsage>(result);
    }
    
    
    /**
     * Returns all of the S2sOppFormQuestionnaire records for a S2s form.
     * @param oppNameSpace The opportunity name space of the form
     * @param formName the name of the form
     * @return 
     */
    @SuppressWarnings("unchecked")
    protected List<S2sOppFormQuestionnaire> findOppFormToQuestionnaires(String oppNameSpace, String formName) {
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Looking up S2sOppFormQuestionnaire for (%s,%s)",oppNameSpace,formName));
        }
        Map<String,Object> params = new HashMap<String,Object>();
        params.put(S2sOppFormQuestionnaire.OPP_NAMESPACE_FIELD,oppNameSpace);
        params.put(S2sOppFormQuestionnaire.FORM_NAME_FIELD, formName);
        List<S2sOppFormQuestionnaire> oppFormToQuestionnaires = (List<S2sOppFormQuestionnaire>)getBusinessObjectService().findMatching(S2sOppFormQuestionnaire.class, params);
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Found S2sOppFormQuestionnaire records for (%s,%s)",oppNameSpace,formName));
            for (S2sOppFormQuestionnaire oppFormQuest : oppFormToQuestionnaires) {
                LOG.debug(oppFormQuest);
            }
        }
        return oppFormToQuestionnaires;
    }
    
    public ModuleQuestionnaireBean getModuleQnBean(DevelopmentProposal proposal) {
        ModuleQuestionnaireBean moduleQuestionnaireBean = new ProposalDevelopmentS2sModuleQuestionnaireBean(proposal);
        return moduleQuestionnaireBean;
    }
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * Gets the questionnaireAnswerService attribute. 
     * @return Returns the questionnaireAnswerService.
     */
    public QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return questionnaireAnswerService;
    }

    /**Grants.gov/Agency Specific Questions
     * Sets the questionnaireAnswerService attribute value.
     * @param questionnaireAnswerService The questionnaireAnswerService to set.
     */
    public void setQuestionnaireAnswerService(QuestionnaireAnswerService questionnaireAnswerService) {
        this.questionnaireAnswerService = questionnaireAnswerService;
    }


    /**
     * Gets the questionnaireService attribute. 
     * @return Returns the questionnaireService.
     */
    public QuestionnaireService getQuestionnaireService() {
        return questionnaireService;
    }


    /**
     * Sets the questionnaireService attribute value.
     * @param questionnaireService The questionnaireService to set.
     */
    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

 
}
