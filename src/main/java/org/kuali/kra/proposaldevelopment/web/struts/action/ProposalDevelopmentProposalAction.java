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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import static java.util.Collections.sort;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

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
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.ScienceKeyword;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.CongressionalDistrict;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonComparator;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalCongressionalDistrictEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddProposalSiteEvent;
import org.kuali.kra.proposaldevelopment.rule.event.DeleteProposalCongressionalDistrictEvent;
import org.kuali.kra.proposaldevelopment.rule.event.DeleteProposalSiteEvent;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.proposaldevelopment.web.struts.form.CongressionalDistrictHelper;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.SponsorService;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;

public class ProposalDevelopmentProposalAction extends ProposalDevelopmentAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentProposalAction.class);

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        setKeywordsPanelFlag(request);
        ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm) form).getDocument();

        KraServiceLocator.getService(ProposalDevelopmentService.class).initializeUnitOrganzationLocation(
                proposalDevelopmentDocument);

        return super.save(mapping, form, request, response);
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionForward actionForward = super.execute(mapping, form, request, response);
        KeyPersonnelService kpservice=KraServiceLocator.getService(KeyPersonnelService.class);
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
            String primeSponsorName = (KraServiceLocator.getService(SponsorService.class).getSponsorName(primeSponsorCode));
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
           
        kpservice.isSponsorNIH(proposalDevelopmentDocument);
        
        return actionForward;
    }

    /**
     * 
     * This method sets the flag for keyword display panel - display keyword panel if parameter is set to true
     * 
     * @param request
     */
    private void setKeywordsPanelFlag(HttpServletRequest request) {
        String keywordPanelDisplay = KraServiceLocator.getService(KualiConfigurationService.class).getParameterValue(
                Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT,
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
        if (checkAndInitNewLocation(proposalDevelopmentDocument, newOtherOrganization)) {
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
        if (checkAndInitNewLocation(proposalDevelopmentDocument, newPerformanceSite)) {
            proposalDevelopmentDocument.getDevelopmentProposal().addPerformanceSite(newPerformanceSite);
            
            // reset input fields on the form
            proposalDevelopmentForm.setNewPerformanceSite(new ProposalSite());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private boolean checkAndInitNewLocation(ProposalDevelopmentDocument proposalDevelopmentDocument, ProposalSite newProposalSite) {
        if (getKualiRuleService().applyRules(
                new AddProposalSiteEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument, newProposalSite))) {
            newProposalSite.setSiteNumber(proposalDevelopmentDocument.getDocumentNextValue(Constants.PROPOSAL_LOCATION_SEQUENCE_NUMBER));
            return true;
        }
        
        return false;
    }

    public ActionForward deletePerformanceSite(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm) form).getDocument();
        
        if (getKualiRuleService().applyRules(
                new DeleteProposalSiteEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument))) {
            String siteIndexStr = getSiteIndex(request);
            int siteIndex = new Integer(siteIndexStr);
            proposalDevelopmentDocument.getDevelopmentProposal().removePerformanceSite(siteIndex);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method deletes a Proposal Site from the list of Other Organizations
     */
    public ActionForward deleteOtherOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm) form).getDocument();
        
        if (getKualiRuleService().applyRules(
                new DeleteProposalSiteEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument))) {
            String siteIndexStr = getSiteIndex(request);
            int siteIndex = new Integer(siteIndexStr);
            proposalDevelopmentDocument.getDevelopmentProposal().removeOtherOrganization(siteIndex);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
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
        List<PropScienceKeyword> newKeywords = new ArrayList<PropScienceKeyword>();
        for (Iterator<PropScienceKeyword> iter = keywords.iterator(); iter.hasNext();) {
            PropScienceKeyword propScienceKeyword = iter.next();
            if (!propScienceKeyword.getSelectKeyword()) {
                newKeywords.add(propScienceKeyword);
            }
        }
        proposalDevelopmentDocument.getDevelopmentProposal().setPropScienceKeywords(newKeywords);

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
                new AddProposalCongressionalDistrictEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument,
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
                new AddProposalCongressionalDistrictEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument,
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
                new AddProposalCongressionalDistrictEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument,
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
                new AddProposalCongressionalDistrictEvent(Constants.EMPTY_STRING, proposalDevelopmentDocument,
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
        String methodToCallAttribute = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        return StringUtils.substringBetween(methodToCallAttribute, "."+parameterName, ".");
    }

    /**
     * This method deletes a congressional district from the Applicant Organization.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteApplicantOrgCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
     * This method deletes a congressional district from the Performing Organization.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deletePerformingOrgCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
     * This method deletes a congressional district from one of the Performance Sites.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deletePerformanceSiteCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
     * This method deletes a congressional district from one of the Other Organizations.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteOtherOrgCongDistrict(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
        proposalDevelopmentDocument.getDevelopmentProposal().setApplicantOrganization((ProposalSite)null);

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward clearPerformingOrganization(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        proposalDevelopmentDocument.getDevelopmentProposal().setApplicantOrganization((ProposalSite)null);

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        
        // if performing org. not set, default to applicant org
        String performingOrganizationId = proposalDevelopmentDocument.getDevelopmentProposal().getPerformingOrganization().getOrganizationId();
        if (proposalDevelopmentDocument != null && performingOrganizationId != null 
				&& proposalDevelopmentDocument.getDevelopmentProposal().getOwnedByUnit() != null && proposalDevelopmentDocument.getDevelopmentProposal().getOwnedByUnit().getOrganizationId() != null 
				&& ((StringUtils.equalsIgnoreCase(performingOrganizationId, proposalDevelopmentDocument.getDevelopmentProposal().getOwnedByUnit().getOrganizationId())) 
						|| (StringUtils.equalsIgnoreCase(performingOrganizationId.trim(), "")))) {
            String applicationOrganizationId = proposalDevelopmentDocument.getDevelopmentProposal().getApplicantOrganization().getOrganizationId();
            ProposalSite performingOrganization = proposalDevelopmentDocument.getDevelopmentProposal().getPerformingOrganization();
            performingOrganization.setOrganizationId(applicationOrganizationId);
            proposalDevelopmentDocument.getDevelopmentProposal().setPerformingOrganization(performingOrganization);
        }
        
        for(ProposalSite proposalSite: proposalDevelopmentDocument.getDevelopmentProposal().getProposalSites()) {
            proposalSite.refreshReferenceObject("rolodex");
            proposalSite.refreshReferenceObject("organization");
        }
        
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
                        PropScienceKeyword propScienceKeyword = new PropScienceKeyword(proposalDevelopmentDocument
                                .getDevelopmentProposal().getProposalNumber(), scienceKeyword);
                        // ignore / drop duplicates
                        if (!isDuplicateKeyword(propScienceKeyword.getScienceKeywordCode(), proposalDevelopmentDocument
                                .getDevelopmentProposal().getPropScienceKeywords())) {
                            proposalDevelopmentDocument.getDevelopmentProposal().addPropScienceKeyword(propScienceKeyword);
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
            Document retrievedDocument = KNSServiceLocator.getDocumentService().getByDocumentHeaderId(docId);
            pdform.setDocument(retrievedDocument);
        }
        
        return super.headerTab(mapping, form, request, response);
    }

    /**
     * 
     * Clears the Mailing Name and Address selected from Delivery info panel.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward clearMailingNameAddress(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        DevelopmentProposal developmentProposal = proposalDevelopmentForm.getDocument().getDevelopmentProposal();
        if (developmentProposal.getRolodex() != null) {
        
            Rolodex rolodex = developmentProposal.getRolodex();
            rolodex.setAddressLine1("");
            rolodex.setAddressLine2("");
            rolodex.setAddressLine3("");
            rolodex.setCity("");
            rolodex.setOrganization("");
            rolodex.setState("");
        
        }
      return mapping.findForward(Constants.MAPPING_BASIC);
    }

}
