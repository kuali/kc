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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CoeusSubModule;
import org.kuali.kra.bo.S2sOppFormQuestionnaire;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.questionnaire.ProposalDevelopmentS2sModuleQuestionnaireBean;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentS2sQuestionnaireService;
import org.kuali.kra.questionnaire.QuestionnaireService;
import org.kuali.kra.questionnaire.QuestionnaireUsage;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.rice.krad.service.BusinessObjectService;

public class ProposalDevelopmentS2sQuestionnaireServiceImpl implements ProposalDevelopmentS2sQuestionnaireService {

    private BusinessObjectService businessObjectService;
    private QuestionnaireAnswerService questionnaireAnswerService;
    private QuestionnaireService questionnaireService;
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentS2sQuestionnaireServiceImpl.class);

  
   
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentS2sQuestionnaireService#getProposalAnswerHeaderForForm(org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal, java.lang.String, java.lang.String)
     */
    public List<AnswerHeader> getProposalAnswerHeaderForForm(DevelopmentProposal proposal, String oppNameSpace, String formName) {
        return getProposalS2sAnswerHeaders(proposal,oppNameSpace,formName);
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentS2sQuestionnaireService#getProposalS2sAnswerHeaders(org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal)
     */
    public List<AnswerHeader> getProposalS2sAnswerHeaders(DevelopmentProposal developmentProposal) {
        return getProposalS2sAnswerHeaders(developmentProposal,null,null);
    }
    
    /**
     * Gets the AnswerHeaders for a particular proposal.  This is filtered by the oppNameSpace and the formName.  If oppNameSpace and formName are null, no filtering is done on that field.
     * @param developmentProposal The proposal
     * @param oppNameSpace The name space of the opportuniy form to filter the answer headers on. If null no filtering is done.
     * @param form name The formName to filter the results by.  If formName is null no filtering is done.
     * @return
     */
    protected List<AnswerHeader> getProposalS2sAnswerHeaders(DevelopmentProposal developmentProposal,String oppNameSpace, String formName) {
        
        Set<AnswerHeader> result = new HashSet<AnswerHeader>(); 
        S2sOpportunity opp = developmentProposal.getS2sOpportunity();
        Set<AnswerHeader> results = new HashSet<AnswerHeader>();
        List<AnswerHeader> rawAnswerHeaders = getQuestionnaireAnswerService().getQuestionnaireAnswer(getModuleQnBean(developmentProposal));

        if (opp!=null) {
            for (AnswerHeader header : rawAnswerHeaders) {
                for (S2sOppForms oppForms : opp.getS2sOppForms()) {
                    if ((oppNameSpace == null || StringUtils.equals(oppForms.getOppNameSpace(), oppNameSpace))
                            && (formName==null||StringUtils.equals(oppForms.getFormName(), formName))) {
                        List<QuestionnaireUsage> usages = getQuestionnaireUsages(oppForms.getOppNameSpace(),oppForms.getFormName()); 
                        for (QuestionnaireUsage usage : usages) {
                            if (header.getQuestionnaire().getQuestionnaireId().equals(usage.getQuestionnaire().getQuestionnaireId())) {
                                results.add(header);
                            }
                    
                        }
                    }
                }
            }
        }
        
        return new ArrayList<AnswerHeader>(results);
    }
    
    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentS2sQuestionnaireService#getQuestionnaireUsages(java.lang.String, java.lang.String)
     */
    public List<QuestionnaireUsage> getQuestionnaireUsages(String oppNameSpace, String formName) {
        Set<QuestionnaireUsage> result = new HashSet<QuestionnaireUsage>();
        List<QuestionnaireUsage> usages = getQuestionnaireAnswerService().getPublishedQuestionnaire(CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE, 
                                                                                                    CoeusSubModule.PROPOSAL_S2S_SUBMODULE, 
                                                                                                    true);
        List<S2sOppFormQuestionnaire> s2sOppFormQuestionnaires = findOppFormToQuestionnaires(oppNameSpace,formName);
        
        for (QuestionnaireUsage usage : usages) {    
            for (S2sOppFormQuestionnaire oppFormQuestionnaire : s2sOppFormQuestionnaires) {
                if (oppFormQuestionnaire.getQuestionnaireId().toString().equals(usage.getQuestionnaire().getQuestionnaireId())) {
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
            LOG.debug(String.format("Found %s S2sOppFormQuestionnaire records for (%s,%s)",oppNameSpace,formName));
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
