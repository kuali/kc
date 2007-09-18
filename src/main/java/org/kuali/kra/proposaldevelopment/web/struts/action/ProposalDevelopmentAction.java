/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.RiceConstants;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ScienceKeyword;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.KNSServiceLocator;

public class ProposalDevelopmentAction extends KraTransactionalDocumentActionBase {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentAction.class);
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String keywordPanelDisplay = KNSServiceLocator.getKualiConfigurationService().getApplicationParameterValue(RiceConstants.ParameterGroups.SYSTEM, Constants.KEYWORD_PANEL_DISPLAY);
        request.setAttribute(Constants.KEYWORD_PANEL_DISPLAY, keywordPanelDisplay);
        return super.execute(mapping, form, request, response);
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        return super.save(mapping, form, request, response);
    }

    public ActionForward proposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("proposal");
    }
    
    public ActionForward keyPersonnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("keyPersonnel");
    }
    
    public ActionForward template(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("template");
    }
    
    public ActionForward notes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("notes");
    }
    
    public ActionForward specialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("specialReview");
    }
    
    public ActionForward abstractPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("abstractPage");
    }
    
    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("customData");
    }
    
    public ActionForward actions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("actions");
    }

    public ActionForward addScienceKeyword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm)form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        List<PropScienceKeyword> keywords = proposalDevelopmentDocument.getPropScienceKeywords();
        String newScienceKeywordCode = proposalDevelopmentDocument.getNewScienceKeywordCode();
        String newDescription = proposalDevelopmentDocument.getNewDescription();
        String defaultNewDescription = proposalDevelopmentDocument.getDefaultNewDescription();
        if((!newDescription.equalsIgnoreCase(defaultNewDescription)) && (!isDuplicateKeyword(newScienceKeywordCode, keywords))) {
            PropScienceKeyword propScienceKeyword = new PropScienceKeyword();
            propScienceKeyword.setScienceKeywordCode(newScienceKeywordCode);
            
            ScienceKeyword scienceKeyword = new ScienceKeyword();
            scienceKeyword.setScienceKeywordCode(newScienceKeywordCode);
            scienceKeyword.setDescription(newDescription);
            
            propScienceKeyword.setScienceKeyword(scienceKeyword);
            keywords.add(propScienceKeyword);
            
            proposalDevelopmentDocument.setPropScienceKeywords(keywords);

            /* initialize new keyword */
            proposalDevelopmentDocument.setNewScienceKeywordCode(null);
            proposalDevelopmentDocument.setNewDescription(defaultNewDescription);
        }

        return mapping.findForward("proposal");
    }

    private boolean isDuplicateKeyword(String newScienceKeywordCode, List<PropScienceKeyword> keywords) {
        for(int i=0; i<keywords.size(); i++) {
            PropScienceKeyword propScienceKeyword = (PropScienceKeyword)keywords.get(i);
            String scienceKeywordCode = propScienceKeyword.getScienceKeywordCode();
            if(scienceKeywordCode.equalsIgnoreCase(newScienceKeywordCode)) {
                // duplicate keyword
                return true;
            }
        }
        return false;
    }
    
    public ActionForward selectAllScienceKeyword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm)form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        List<PropScienceKeyword> keywords = proposalDevelopmentDocument.getPropScienceKeywords();
        for(int i=0; i<keywords.size(); i++) {
            PropScienceKeyword propScienceKeyword = (PropScienceKeyword)keywords.get(i);
            propScienceKeyword.setSelectKeyword(true);
        }

        return mapping.findForward("proposal");
    }

    public ActionForward deleteSelectedScienceKeyword(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm)form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        List<PropScienceKeyword> keywords = proposalDevelopmentDocument.getPropScienceKeywords();
        List<PropScienceKeyword> newKeywords = new ArrayList();
        for(int i=0; i<keywords.size(); i++) {
            PropScienceKeyword propScienceKeyword = (PropScienceKeyword)keywords.get(i);
            if(!propScienceKeyword.getSelectKeyword()) {
                newKeywords.add(propScienceKeyword);
            }
        }
        proposalDevelopmentDocument.setPropScienceKeywords(newKeywords);

        return mapping.findForward("proposal");
    }

    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.refresh(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();

        // check to see if we are coming back from a lookup
        if (Constants.MULTIPLE_VALUE.equals(proposalDevelopmentForm.getRefreshCaller())) {
            // Multivalue lookup. Note that the multivalue keyword lookup results are returned persisted to avoid using session.
            // Since URLs have a max length of 2000 chars, field conversions can not be done.
            String lookupResultsSequenceNumber = proposalDevelopmentForm.getLookupResultsSequenceNumber();
            if (StringUtils.isNotBlank(lookupResultsSequenceNumber)) {
                Class lookupResultsBOClass = Class.forName(proposalDevelopmentForm.getLookupResultsBOClassName());
                Collection<PersistableBusinessObject> rawValues = KNSServiceLocator.getLookupResultsService().retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass, GlobalVariables.getUserSession().getUniversalUser().getPersonUniversalIdentifier());
                if(lookupResultsBOClass.isAssignableFrom(ScienceKeyword.class)) {
                    for(Iterator iter = rawValues.iterator(); iter.hasNext(); ) {
                        ScienceKeyword scienceKeyword = (ScienceKeyword) iter.next();
                        PropScienceKeyword propScienceKeyword = new PropScienceKeyword(proposalDevelopmentDocument.getProposalNumber(), scienceKeyword);
                        // ignore / drop duplicates
                        if(!isDuplicateKeyword(propScienceKeyword.getScienceKeywordCode(), proposalDevelopmentDocument.getPropScienceKeywords())) {
                            proposalDevelopmentDocument.addPropScienceKeyword(propScienceKeyword);
                        }
                    }
                }
            }
        } 
        return mapping.findForward("proposal");
    }
    
}
