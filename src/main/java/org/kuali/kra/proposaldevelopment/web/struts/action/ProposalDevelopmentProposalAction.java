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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import static java.util.Collections.sort;
import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.rice.krad.util.KRADConstants.QUESTION_INST_ATTRIBUTE_NAME;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.ScienceKeyword;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.CongressionalDistrict;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonComparator;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalCongressionalDistrictEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSiteEvent;
import org.kuali.kra.proposaldevelopment.rule.event.BasicProposalSiteEvent;
import org.kuali.kra.proposaldevelopment.rule.event.DeleteProposalCongressionalDistrictEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveProposalSitesEvent;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.web.struts.form.CongressionalDistrictHelper;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.service.SponsorService;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

public class ProposalDevelopmentProposalAction extends ProposalDevelopmentAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentProposalAction.class);
    private static final String CONFIRM_DELETE_PROPOSAL_SITE_KEY = "confirmDeleteProposalSite";
    private static final String CONFIRM_DELETE_CONG_DISTRICT_KEY = "confirmDeleteCongDistrict";
    private static final String CONFIRM_CLEAR_DELIVERY_INFO_ADDRESS_KEY="confirmClearDeliveryInfoAddress";
    

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        setKeywordsPanelFlag(request);
        ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm) form).getDocument();

        KraServiceLocator.getService(ProposalDevelopmentService.class).initializeUnitOrganizationLocation(
                proposalDevelopmentDocument);
        KraServiceLocator.getService(ProposalDevelopmentService.class).initializeProposalSiteNumbers(
                proposalDevelopmentDocument);
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        if (!proposalDevelopmentForm.isGrantsGovEnabled()
                && proposalDevelopmentForm.getDocument().getDevelopmentProposal().getS2sOpportunity() != null) {
            //if grants gov isn't enabled and we have an opportunity, clear it.
            proposalDevelopmentForm.setVersionNumberForS2sOpportunity(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().getVersionNumber());            
            proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity().setS2sOppForms(null);
            proposalDevelopmentDocument.getDevelopmentProposal().setS2sOpportunity(null);
            proposalDevelopmentDocument.getDevelopmentProposal().setProgramAnnouncementNumber(null);
            proposalDevelopmentDocument.getDevelopmentProposal().setProgramAnnouncementTitle(null);
        }
        
        SaveProposalSitesEvent ruleEvent = new SaveProposalSitesEvent("document.developmentProposal", proposalDevelopmentDocument);
        if (getKualiRuleService().applyRules(ruleEvent)) {
            return super.save(mapping, form, request, response);
        }
        else {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }
    
    @Override
    public ActionForward saveOnClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.saveOnClose(mapping, form, request, response);
        
        final ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        final ProposalDevelopmentDocument doc = proposalDevelopmentForm.getDocument();
        
        //required for save, particularly if the proposal has not yet been saved
        KraServiceLocator.getService(ProposalDevelopmentService.class).initializeUnitOrganizationLocation(
                doc);
        KraServiceLocator.getService(ProposalDevelopmentService.class).initializeProposalSiteNumbers(
                doc);
        
        return forward;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionForward actionForward = super.execute(mapping, form, request, response);
        setKeywordsPanelFlag(request);
        ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm) form).getDocument();
        if (proposalDevelopmentDocument.getDevelopmentProposal().getApplicantOrganization() != null
                && proposalDevelopmentDocument.getDevelopmentProposal().getProposalSites().size() == 0
                && StringUtils.isNotBlank(request.getParameter("methodToCall"))
                && request.getParameter("methodToCall").toString().equals("refresh")
                && StringUtils.isNotBlank(request.getParameter("refreshCaller"))
                && request.getParameter("refreshCaller").toString().equals("kualiLookupable")
                && StringUtils.isNotBlank(request.getParameter("document.organizationId"))) {
            // populate 1st location. Not sure yet
            ProposalSite propSite = new ProposalSite();
            propSite.setLocationName(proposalDevelopmentDocument.getDevelopmentProposal().getApplicantOrganization().getLocationName());
            propSite.setSiteNumber(proposalDevelopmentDocument
                    .getDocumentNextValue(Constants.PROPOSAL_LOCATION_SEQUENCE_NUMBER));
            proposalDevelopmentDocument.getDevelopmentProposal().addPerformanceSite(propSite);
        }
        // populate the Prime Sponsor Name if we have the code
        // this is necessary since the name is only on the form not the document
        // and it is only populated by a lookup or through AJAX/DWR
        String primeSponsorCode = proposalDevelopmentDocument.getDevelopmentProposal().getPrimeSponsorCode();
        if (StringUtils.isNotEmpty(primeSponsorCode)) {
            String primeSponsorName = (getSponsorService().getSponsorName(primeSponsorCode));
            if (StringUtils.isEmpty(primeSponsorName)) {
                primeSponsorName = "not found";
            }
            ((ProposalDevelopmentForm) form).setPrimeSponsorName(primeSponsorName);
        }
        else {
            // TODO: why do we have to do this?
            ((ProposalDevelopmentForm) form).setPrimeSponsorName(null);
        }

        if (proposalDevelopmentDocument.getDevelopmentProposal().getProposalPersons().size() > 0)
            sort(proposalDevelopmentDocument.getDevelopmentProposal().getProposalPersons(), new ProposalPersonComparator());

        if (proposalDevelopmentDocument.getDevelopmentProposal().getInvestigators().size() > 0)
            sort(proposalDevelopmentDocument.getDevelopmentProposal().getInvestigators(), new ProposalPersonComparator());
           
        updateNIHDescriptions(proposalDevelopmentDocument);
        
        return actionForward;
    }
    
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return super.route(mapping, form, request, response);
    }

    protected SponsorService getSponsorService() {
        return KraServiceLocator.getService(SponsorService.class);
    }

    /**
     *
     * This method sets the flag for keyword display panel - display keyword panel if parameter is set to true
     *
     * @param request
     */
    private void setKeywordsPanelFlag(HttpServletRequest request) {
        String keywordPanelDisplay = this.getParameterService().getParameterValueAsString(
                ProposalDevelopmentDocument.class,
                Constants.KEYWORD_PANEL_DISPLAY);
        request.getSession().setAttribute(Constants.KEYWORD_PANEL_DISPLAY, keywordPanelDisplay);
    }

    /**
     * This method adds a Proposal Site as a "other organization"
     */
    public ActionForward addOtherOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        
        ProposalSite newOtherOrganization = proposalDevelopmentForm.getNewOtherOrganization();
        newOtherOrganization.setLocationTypeCode(ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION);   // the validation rule needs the location type
        if (checkAndInitNewLocation("newOtherOrganization", proposalDevelopmentDocument, newOtherOrganization)) {
            proposalDevelopmentDocument.getDevelopmentProposal().addOtherOrganization(newOtherOrganization);
            
            // reset input fields on the form
            proposalDevelopmentForm.setNewOtherOrganization(new ProposalSite());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method adds a Proposal Site as a Performance Site.
     */
    public ActionForward addPerformanceSite(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();

        ProposalSite newPerformanceSite = proposalDevelopmentForm.getNewPerformanceSite();
        newPerformanceSite.setLocationTypeCode(ProposalSite.PROPOSAL_SITE_PERFORMANCE_SITE);   // the validation rule needs the location type
        if (checkAndInitNewLocation("newPerformanceSite", proposalDevelopmentDocument, newPerformanceSite)) {
            proposalDevelopmentDocument.getDevelopmentProposal().addPerformanceSite(newPerformanceSite);
            
            // reset input fields on the form
            proposalDevelopmentForm.setNewPerformanceSite(new ProposalSite());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method checks rules on a new {@link ProposalSite} and sets the proposal number.
     * @param errorPathPrefix
     * @param proposalDevelopmentDocument
     * @param newProposalSite
     * @return
     */
    private boolean checkAndInitNewLocation(String errorPathPrefix, ProposalDevelopmentDocument proposalDevelopmentDocument, ProposalSite newProposalSite) {
        if (getKualiRuleService().applyRules(
                new AddProposalSiteEvent(errorPathPrefix, proposalDevelopmentDocument, newProposalSite))) {
            newProposalSite.initializeDefaultCongressionalDistrict();
            newProposalSite.setSiteNumber(proposalDevelopmentDocument.getDocumentNextValue(Constants.PROPOSAL_LOCATION_SEQUENCE_NUMBER));
            return true;
        }
        
        return false;
    }

    public ActionForward clearPerformanceSiteAddress(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm) form).getDocument();
        
        BasicProposalSiteEvent clearEvent = new BasicProposalSiteEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument);
        if (getKualiRuleService().applyRules(clearEvent)) {
            int siteIndex = getSiteIndexForConfirmation(form, request);
            if (siteIndex >= 0) {
                proposalDevelopmentDocument.getDevelopmentProposal().clearPerformanceSiteAddress(siteIndex);
            }
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method shows a delete confirmation page. If the user clicks "yes", the selected Performance Site is deleted.
     * @see confirmDeletePerformanceSite
     */
    public ActionForward deletePerformanceSite(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return deleteProposalSite(mapping, form, request, response, "confirmDeletePerformanceSite", "Performance Site");
    }
    
    /**
     * This method shows a delete confirmation page. If the user clicks "yes", the selected Other Organization is deleted.
     * @see confirmDeleteOtherOrganization
     */
    public ActionForward deleteOtherOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return deleteProposalSite(mapping, form, request, response, "confirmDeleteOtherOrganization", "Organization");
    }
    
    /**
     * This method asks the user if they really want to delete a Proposal Site. If the answer is "yes",
     * <CODE>yesMethodName</CODE> is called.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param yesMethodName The method to call if the user confirms.
     * @param locationTypeName A descriptive name for the location type; used for the confirmation question
     * @return
     * @throws Exception
     */
    public ActionForward deleteProposalSite(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response, String yesMethodName, String locationTypeName) throws Exception {
        ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm) form).getDocument();
        
        BasicProposalSiteEvent deleteEvent = new BasicProposalSiteEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument);
        if (getKualiRuleService().applyRules(deleteEvent)) {
            StrutsConfirmation deleteConfirmation = buildParameterizedConfirmationQuestion(mapping, form, request, response,
                    CONFIRM_DELETE_PROPOSAL_SITE_KEY, KeyConstants.QUESTION_DELETE_CONFIRMATION, "this " + locationTypeName);
            return confirm(deleteConfirmation, yesMethodName, "");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method does the actual deletion of a Performance Site. It is called after the user confirms deletion. 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward confirmDeletePerformanceSite(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        int siteIndex = getSiteIndexForConfirmation(form, request, CONFIRM_DELETE_PROPOSAL_SITE_KEY);
        if (siteIndex >= 0) {
            ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm) form).getDocument();
            proposalDevelopmentDocument.getDevelopmentProposal().removePerformanceSite(siteIndex);
        }
        return mapping.findForward(MAPPING_BASIC);
    }

    /**
     * This method does the actual deletion of a Other Organization. It is called after the user confirms deletion. 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward confirmDeleteOtherOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        int siteIndex = getSiteIndexForConfirmation(form, request, CONFIRM_DELETE_PROPOSAL_SITE_KEY);
        if (siteIndex >= 0) {
            ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm) form).getDocument();
            proposalDevelopmentDocument.getDevelopmentProposal().removeOtherOrganization(siteIndex);
        }
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * This method returns the "site index" parameter from a <CODE>request</CODE> if the <CODE>confirmationKey</CODE>
     * matches the current struts confirmation, and if it passes validation. Otherwise, -1 is returned.
     * @param form
     * @param request
     * @param confirmationKey
     * @return The site index from the request, or -1 if the request contains no valid site index.
     */
    private int getSiteIndexForConfirmation(ActionForm form, HttpServletRequest request, String confirmationKey) {
        int siteIndex = -1;

        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        if (confirmationKey.equals(question)) {
            return getSiteIndexForConfirmation(form, request);
        }
        
        return siteIndex;
    }
    
    /**
     * This method returns the "site index" parameter from a <CODE>request</CODE> if the site index
     * passes validation. Otherwise, -1 is returned.
     * @param form
     * @param request
     * @return The site index from the request, or -1 if the request contains no valid site index.
     */
    private int getSiteIndexForConfirmation(ActionForm form, HttpServletRequest request) {
        int siteIndex = -1;

        ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm)form).getDocument();
        if (getKualiRuleService().applyRules(
                new BasicProposalSiteEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument))) {
            String siteIndexStr = getSiteIndex(request);
            siteIndex = new Integer(siteIndexStr);   // this is safe to do because the site index passed validation
        }
        
        return siteIndex;
    }
    
    public ActionForward confirmDeleteProposalSite(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Object question = request.getParameter(QUESTION_INST_ATTRIBUTE_NAME);
        
        if (CONFIRM_DELETE_PROPOSAL_SITE_KEY.equals(question)) { 
            ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm) form).getDocument();
            if (getKualiRuleService().applyRules(
                    new BasicProposalSiteEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument))) {
                String siteIndexStr = getSiteIndex(request);
                int siteIndex = new Integer(siteIndexStr);   // this is safe to do because the site index passed validation
                proposalDevelopmentDocument.getDevelopmentProposal().removePerformanceSite(siteIndex);
            }
        }
        
        return mapping.findForward(MAPPING_BASIC);
    }
    
    private boolean isDuplicateKeyword(String newScienceKeywordCode, List<PropScienceKeyword> keywords) {
        for (Iterator<PropScienceKeyword> iter = keywords.iterator(); iter.hasNext();) {
            PropScienceKeyword propScienceKeyword = (PropScienceKeyword) iter.next();
            String scienceKeywordCode = propScienceKeyword.getScienceKeywordCode();
            if (scienceKeywordCode.equalsIgnoreCase(newScienceKeywordCode)) {
                // duplicate keyword
                return true;
            }
        }
        return false;
    }

    public ActionForward selectAllScienceKeyword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        List<PropScienceKeyword> keywords = proposalDevelopmentDocument.getDevelopmentProposal().getPropScienceKeywords();
        for (Iterator<PropScienceKeyword> iter = keywords.iterator(); iter.hasNext();) {
            PropScienceKeyword propScienceKeyword = iter.next();
            propScienceKeyword.setSelectKeyword(true);
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward deleteSelectedScienceKeyword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        List<PropScienceKeyword> keywords = proposalDevelopmentDocument.getDevelopmentProposal().getPropScienceKeywords();
        for (ListIterator<PropScienceKeyword> iter = keywords.listIterator(); iter.hasNext();) {
            PropScienceKeyword propScienceKeyword = iter.next();
            if (propScienceKeyword.getSelectKeyword()) {
                iter.remove();
            }
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method adds a congressional district to the Applicant Organization.
     */
    public ActionForward addApplicantOrgCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm)form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        ProposalSite applicantOrganization = proposalDevelopmentDocument.getDevelopmentProposal().getApplicantOrganization();
        CongressionalDistrictHelper applicantOrganizationHelper = proposalDevelopmentForm.getApplicantOrganizationHelper();
        if (getKualiRuleService().applyRules(
                new AddProposalCongressionalDistrictEvent("applicantOrganizationHelper", proposalDevelopmentDocument,
                        applicantOrganization, applicantOrganizationHelper))) {
            return addCongressionalDistrict(mapping, applicantOrganization, applicantOrganizationHelper);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method adds a congressional district to the Performing Organization.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addPerformingOrgCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm)form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        
        ProposalSite performingOrganization = proposalDevelopmentDocument.getDevelopmentProposal().getPerformingOrganization();
        CongressionalDistrictHelper performingOrganizationHelper = proposalDevelopmentForm.getPerformingOrganizationHelper();
        if (getKualiRuleService().applyRules(
                new AddProposalCongressionalDistrictEvent("performingOrganizationHelper", proposalDevelopmentDocument,
                        performingOrganization, performingOrganizationHelper))) {
            return addCongressionalDistrict(mapping, performingOrganization, performingOrganizationHelper);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method adds a congressional district to the n-th Performance Site, where n is the
     * "site" parameter of the methodToCall attribute.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addPerformanceSiteCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        
        List<ProposalSite> performanceSites = proposalDevelopmentDocument.getDevelopmentProposal().getPerformanceSites();
        List<CongressionalDistrictHelper> proposalSiteHelpers = proposalDevelopmentForm.getPerformanceSiteHelpers();
        String siteIndexStr = getSiteIndex(request);
        if (getKualiRuleService().applyRules(
                new AddProposalCongressionalDistrictEvent("performanceSiteHelpers[" + siteIndexStr + "]", proposalDevelopmentDocument,
                        performanceSites, proposalSiteHelpers, siteIndexStr))) {
            return addCongressionalDistrict(mapping, performanceSites, proposalSiteHelpers, siteIndexStr);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method adds a congressional district to the n-th Other Organization, where n is the
     * "site" parameter of the methodToCall attribute.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addOtherOrgCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        
        List<ProposalSite> otherOrganizations = proposalDevelopmentDocument.getDevelopmentProposal().getOtherOrganizations();
        List<CongressionalDistrictHelper> otherOrganizationHelpers = proposalDevelopmentForm.getOtherOrganizationHelpers();
        String siteIndexStr = getSiteIndex(request);
        if (getKualiRuleService().applyRules(
                new AddProposalCongressionalDistrictEvent("otherOrganizationHelpers[" + siteIndexStr + "]", proposalDevelopmentDocument,
                        otherOrganizations, otherOrganizationHelpers, siteIndexStr))) {
            return addCongressionalDistrict(mapping, otherOrganizations, otherOrganizationHelpers, siteIndexStr);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private ActionForward addCongressionalDistrict(ActionMapping mapping, ProposalSite proposalSite, CongressionalDistrictHelper proposalSiteHelper) {
        String stateCode = proposalSiteHelper.getNewState();
        String districtNumber = proposalSiteHelper.getNewDistrictNumber();
        CongressionalDistrict newDistrict = new CongressionalDistrict();
        newDistrict.setCongressionalDistrict(stateCode, districtNumber);
        proposalSite.addCongressionalDistrict(newDistrict);
        
        proposalSiteHelper.setNewDistrictNumber("");
        proposalSiteHelper.setNewState("");
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private ActionForward addCongressionalDistrict(ActionMapping mapping, List<ProposalSite> proposalSites, List<CongressionalDistrictHelper> proposalSiteHelpers, String siteIndexStr) {
        int siteIndex = new Integer(siteIndexStr);
        return addCongressionalDistrict(mapping, proposalSites.get(siteIndex), proposalSiteHelpers.get(siteIndex));
    }

    private String getDistrictIndex(HttpServletRequest request) {
        return getMethodToCallParameter(request, "district");
    }
    
    private String getSiteIndex(HttpServletRequest request) {
        return getMethodToCallParameter(request, "site");
    }
    
    /**
     * This method reads a String value from the methodToCall form attribute.
     * @param request
     * @param parameterName
     * @return
     */
    private String getMethodToCallParameter(HttpServletRequest request, String parameterName) {
        String methodToCallAttribute = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        return StringUtils.substringBetween(methodToCallAttribute, "."+parameterName, ".");
    }

    /**
     * This method asks the user whether they really want to delete the district. If the answer is "yes",
     * the delete action is called.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @see confirmDeleteApplicantOrgCongDistrict
     */
    public ActionForward deleteApplicantOrgCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return deleteCongressionalDistrict(mapping, form, request, response, "confirmDeleteApplicantOrgCongDistrict");
    }

    /**
     * This method deletes a congressional district from the Applicant Organization if a valid district index was supplied.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward confirmDeleteApplicantOrgCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        
        ProposalSite applicantOrganization = proposalDevelopmentDocument.getDevelopmentProposal().getApplicantOrganization();
        String districtIndexStr = getDistrictIndex(request);
        if (getKualiRuleService().applyRules(
                new DeleteProposalCongressionalDistrictEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument,
                        applicantOrganization, districtIndexStr))) {
            return deleteCongressionalDistrict(mapping, applicantOrganization, request, proposalDevelopmentForm);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method asks the user whether they really want to delete the district. If the answer is "yes",
     * the delete action is called.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deletePerformingOrgCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return deleteCongressionalDistrict(mapping, form, request, response, "confirmDeletePerformingOrgCongDistrict");
    }
            
    /**
     * This method deletes a congressional district from the Performing Organization if a valid district index was supplied.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward confirmDeletePerformingOrgCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        
        ProposalSite performingOrganization = proposalDevelopmentDocument.getDevelopmentProposal().getPerformingOrganization();
        String districtIndexStr = getDistrictIndex(request);
        if (getKualiRuleService().applyRules(
                new DeleteProposalCongressionalDistrictEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument,
                        performingOrganization, districtIndexStr))) {
            return deleteCongressionalDistrict(mapping, performingOrganization, request, proposalDevelopmentForm);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
            
    /**
     * This method asks the user whether they really want to delete the district. If the answer is "yes",
     * the delete action is called.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deletePerformanceSiteCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return deleteCongressionalDistrict(mapping, form, request, response, "confirmDeletePerformanceSiteCongDistrict");
    }
    
    /**
     * This method deletes a congressional district from one of the Performance Sites if a valid district index was supplied.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward confirmDeletePerformanceSiteCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        
        List<ProposalSite> performanceSites = proposalDevelopmentDocument.getDevelopmentProposal().getPerformanceSites();
        String siteIndexStr = getSiteIndex(request);
        String districtIndexStr = getDistrictIndex(request);
        if (getKualiRuleService().applyRules(
                new DeleteProposalCongressionalDistrictEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument,
                        performanceSites, siteIndexStr, districtIndexStr))) {
            return deleteCongressionalDistrict(mapping, performanceSites, proposalDevelopmentForm, siteIndexStr, districtIndexStr);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method asks the user whether they really want to delete the district. If the answer is "yes",
     * the delete action is called.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteOtherOrgCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return deleteCongressionalDistrict(mapping, form, request, response, "confirmDeleteOtherOrgCongDistrict");
    }
    
    /**
     * This method deletes a congressional district from one of the Other Organizations.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward confirmDeleteOtherOrgCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        
        List<ProposalSite> otherOrganizations = proposalDevelopmentDocument.getDevelopmentProposal().getOtherOrganizations();
        String siteIndexStr = getSiteIndex(request);
        String districtIndexStr = getDistrictIndex(request);
        if (getKualiRuleService().applyRules(
                new DeleteProposalCongressionalDistrictEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument,
                        otherOrganizations, siteIndexStr, districtIndexStr))) {
            return deleteCongressionalDistrict(mapping, otherOrganizations, proposalDevelopmentForm, siteIndexStr, districtIndexStr);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward deleteCongressionalDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response, String yesMethodName) throws Exception {
        ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm) form).getDocument();
        
        BasicProposalSiteEvent deleteEvent = new BasicProposalSiteEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument);
        if (getKualiRuleService().applyRules(deleteEvent)) {
            StrutsConfirmation deleteConfirmation = buildParameterizedConfirmationQuestion(mapping, form, request, response,
                    CONFIRM_DELETE_CONG_DISTRICT_KEY, KeyConstants.QUESTION_DELETE_CONFIRMATION, "this Congressional District");
            return confirm(deleteConfirmation, yesMethodName, "");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method deletes the districtIndexStr-th congressional district from the siteIndexStr-th Proposal Site.
     * @param mapping
     * @param proposalSites
     * @param proposalDevelopmentForm
     * @param siteIndexStr
     * @param districtIndexStr
     * @return
     */
    public ActionForward deleteCongressionalDistrict(ActionMapping mapping, List<ProposalSite> proposalSites, ProposalDevelopmentForm proposalDevelopmentForm,
            String siteIndexStr, String districtIndexStr) {
        int siteIndex = new Integer(siteIndexStr);
        int districtIndex = new Integer(districtIndexStr);
        ProposalSite proposalSite = proposalSites.get(siteIndex);
        proposalSite.deleteCongressionalDistrict(districtIndex);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private ActionForward deleteCongressionalDistrict(ActionMapping mapping, ProposalSite proposalSite, HttpServletRequest request,
            ProposalDevelopmentForm proposalDevelopmentForm) {
        String districtIndexStr = getDistrictIndex(request);
        int districtIndex = new Integer(districtIndexStr);
        proposalSite.deleteCongressionalDistrict(districtIndex);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward clearApplicantOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        
        proposalDevelopmentDocument.getDevelopmentProposal().setApplicantOrganization(new ProposalSite());

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        DevelopmentProposal developmentProposal = proposalDevelopmentForm.getDocument().getDevelopmentProposal();

        // XXX does this code do anything that isn't already done in ProposalDevelopmentServiceImpl?
        // if performing org. not set, default to applicant org
        String performingOrganizationId = developmentProposal.getPerformingOrganization().getOrganizationId();
        if (StringUtils.isEmpty(performingOrganizationId)) {
            String applicationOrganizationId = developmentProposal.getApplicantOrganization().getOrganizationId();
            ProposalSite performingOrganization = developmentProposal.getPerformingOrganization();
            performingOrganization.setOrganizationId(applicationOrganizationId);
        }
        
        for(ProposalSite proposalSite: developmentProposal.getProposalSites()) {
            proposalSite.refreshReferenceObject("rolodex");
            proposalSite.refreshReferenceObject("organization");
        }
        proposalDevelopmentForm.getNewPerformanceSite().refreshReferenceObject("rolodex");
        proposalDevelopmentForm.getNewOtherOrganization().refreshReferenceObject("organization");
        
        // check to see if we are coming back from a lookup
        if (Constants.MULTIPLE_VALUE.equals(proposalDevelopmentForm.getRefreshCaller())) {
            // Multivalue lookup. Note that the multivalue keyword lookup results are returned persisted to avoid using session.
            // Since URLs have a max length of 2000 chars, field conversions can not be done.
            String lookupResultsSequenceNumber = proposalDevelopmentForm.getLookupResultsSequenceNumber();
            if (StringUtils.isNotBlank(lookupResultsSequenceNumber)) {
                Class lookupResultsBOClass = Class.forName(proposalDevelopmentForm.getLookupResultsBOClassName());
                Collection<PersistableBusinessObject> rawValues = KNSServiceLocator.getLookupResultsService()
                        .retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass,
                                GlobalVariables.getUserSession().getPrincipalId());
                if (lookupResultsBOClass.isAssignableFrom(ScienceKeyword.class)) {
                    for (Iterator<PersistableBusinessObject> iter = rawValues.iterator(); iter.hasNext();) {
                        ScienceKeyword scienceKeyword = (ScienceKeyword) iter.next();
                        PropScienceKeyword propScienceKeyword = new PropScienceKeyword(developmentProposal.getProposalNumber(), scienceKeyword);
                        // ignore / drop duplicates
                        if (!isDuplicateKeyword(propScienceKeyword.getScienceKeywordCode(), developmentProposal.getPropScienceKeywords())) {
                            developmentProposal.addPropScienceKeyword(propScienceKeyword);
                        }
                    }
                }
            }
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    @Override
    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }

    @Override
    public ActionForward headerTab(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdform = (ProposalDevelopmentForm) form;
        
        String command = request.getParameter("command");
        String docId = request.getParameter("docId");

        if(StringUtils.isNotEmpty(command) && command.equals("displayDocSearchView") && StringUtils.isNotEmpty(docId)) {
            //This means that the user has come thru the Document Search Page - Copy Action
            Document retrievedDocument = KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docId);
            pdform.setDocument(retrievedDocument);
        }
        
        return super.headerTab(mapping, form, request, response);
    }

    /**
     * 
     * Clears the Mailing Name and Address selected from Delivery info panel.
     * Now makes call to confirm the clear action before executing.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    
    public ActionForward clearMailingNameAddress(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
             
        ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm) form).getDocument();
        BasicProposalSiteEvent deleteEvent = new BasicProposalSiteEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument);
        
        if (getKualiRuleService().applyRules(deleteEvent)) {
            StrutsConfirmation deleteConfirmation = buildParameterizedConfirmationQuestion(mapping, form, request, response,
                    CONFIRM_CLEAR_DELIVERY_INFO_ADDRESS_KEY, KeyConstants.QUESTION_CONFIRM_CLEAR_DELIVERY_ADDRESS_INFO, "");
            return confirm(deleteConfirmation, "confirmClearMailingNameAddress", "");
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * Begins the operation to Clear the Mailing Name and Address selected from Delivery info panel.
     * Now makes call to confirm the clear action before executing.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    
    public ActionForward confirmClearMailingNameAddress(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        DevelopmentProposal developmentProposal = proposalDevelopmentForm.getDocument().getDevelopmentProposal();
           
        if (developmentProposal.getRolodex() != null) {
           
            developmentProposal.setRolodex(null);
            developmentProposal.setMailingAddressId(null);
        
        }
      return mapping.findForward(Constants.MAPPING_BASIC);
    }

}