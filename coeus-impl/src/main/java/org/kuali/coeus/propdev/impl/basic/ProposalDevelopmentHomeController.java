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
package org.kuali.coeus.propdev.impl.basic;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.compliance.core.SaveDocumentSpecialReviewEvent;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyCriteria;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.coeus.propdev.impl.docperm.ProposalUserRoles;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.s2s.S2sSubmissionService;
import org.kuali.coeus.propdev.impl.s2s.connect.OpportunitySchemaParserService;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.exception.DocumentAuthorizationException;
import org.kuali.rice.krad.rules.rule.event.SaveDocumentEvent;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.service.DocumentDictionaryService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

@Controller
public class ProposalDevelopmentHomeController extends ProposalDevelopmentControllerBase {

    @Autowired
    @Qualifier("dataDictionaryService")
    private DataDictionaryService dataDictionaryService;

    @Autowired
    @Qualifier("documentDictionaryService")
    private DocumentDictionaryService documentDictionaryService;

    @Autowired
    @Qualifier("kcEntityManager")
    private EntityManager entityManager;

    @Autowired
    @Qualifier("opportunitySchemaParserService")
    private OpportunitySchemaParserService opportunitySchemaParserService;

    @Autowired
    @Qualifier("s2sSubmissionService")
    private S2sSubmissionService s2sSubmissionService;

    @Autowired
    @Qualifier("kualiRuleService")
    private KualiRuleService kualiRuleService;


    @MethodAccessible
   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=createProposal")
   public ModelAndView createProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocument proposalDevelopmentDocument = form.getProposalDevelopmentDocument();

        // Check for valid info entered before creating a new document
        boolean valid = getKualiRuleService().applyRules(new SaveDocumentEvent(proposalDevelopmentDocument));
        if (!valid) {
            return getModelAndViewService().getModelAndView(form);
        }

        addCreateDetails(proposalDevelopmentDocument);
        initialSave(proposalDevelopmentDocument);

       // this is needed if the proposal is being created from an opportunity, in which case
       // the opportunity content needs to be generated before save
       generateOpportunity(proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity());
       save(form, result, request, response);
       initializeProposalUsers(form.getProposalDevelopmentDocument());
       form.setWorkingUserRoles(getProposalDevelopmentPermissionsService().getPermissions(form.getProposalDevelopmentDocument()));
       getDataObjectService().wrap(form.getDevelopmentProposal()).fetchRelationship("proposalType");
       //setting to null so the previous page id(PropDev-InitiatePage) doesn't override the default
       form.setPageId(null);
       form.getDevelopmentProposal().setProposalStateTypeCode(ProposalState.IN_PROGRESS);
       getDataObjectService().wrap(form.getDevelopmentProposal()).fetchRelationship("proposalState");
       getPessimisticLockService().releaseAllLocksForUser(form.getDocument().getPessimisticLocks(),getGlobalVariableService().getUserSession().getPerson());
       form.getDocument().refreshPessimisticLocks();
       generateForms(form.getDevelopmentProposal());
       return getModelAndViewService().getModelAndViewWithInit(form, PROPDEV_DEFAULT_VIEW_ID);
   }

    private void addCreateDetails(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        proposalDevelopmentDocument.getDevelopmentProposal().setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
        proposalDevelopmentDocument.getDevelopmentProposal().setCreateUser(getGlobalVariableService().getUserSession().getLoggedInUserPrincipalName());
    }

    protected void generateForms(DevelopmentProposal proposal) {
        if (ObjectUtils.isNotNull(proposal.getS2sOpportunity())) {
            s2sSubmissionService.setMandatoryForms(proposal, proposal.getS2sOpportunity());
        }
    }

    protected void generateOpportunity(S2sOpportunity opportunity) {
        if (ObjectUtils.isNotNull(opportunity)) {
            s2sSubmissionService.setOpportunityContent(opportunity);
        }
    }

    /**
     * Starts a view of the proposal development document, the only difference between this method and the docHandler
     * method is that this will allow for non-document views to retrieve and show the data by not going through
     * TransactionalDocumentControllerService.
     */
    @MethodAccessible
    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=viewUtility")
    public ModelAndView viewUtility(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
                                    @RequestParam(value="userName",required = false) String userName) throws Exception {
        ProposalDevelopmentDocument document = null;
        boolean isDeleted = false;

        if (!ObjectUtils.isNull(form.getDocId())) {
            document = (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(form.getDocId());
            isDeleted = document.isProposalDeleted();
        }

        if (isDeleted) {
            Properties props = new Properties();
            props.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, KRADConstants.START_METHOD);
            props.put(UifConstants.UrlParams.VIEW_ID, "PropDev-DeletedView");
            return getModelAndViewService().performRedirect(form, "proposalDevelopment", props);
        } else {
            form.initialize();
            form.setDocument(document);
            WorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
            form.setDocTypeName(workflowDocument.getDocumentTypeName());
            form.setProposalCopyCriteria(new ProposalCopyCriteria(document));
            ((ProposalDevelopmentViewHelperServiceImpl)form.getView().getViewHelperService()).populateQuestionnaires(form);

             if (!this.getDocumentDictionaryService().getDocumentAuthorizer(document).canOpen(document,
                        getGlobalVariableService().getUserSession().getPerson())) {
                    throw new DocumentAuthorizationException(getGlobalVariableService().getUserSession().getPerson().getPrincipalName(),
                            "open", document.getDocumentNumber());
             }

            if (StringUtils.isNotEmpty(userName)) {
                for (ProposalPerson person : form.getDevelopmentProposal().getProposalPersons()) {
                    if (StringUtils.equals(person.getUserName(),userName)) {
                        form.setProposalPersonQuestionnaireHelper(person.getQuestionnaireHelper());
                        break;
                    }
                }
            }

            return getModelAndViewService().getModelAndView(form);
        }
    }

   @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=save")
   public ModelAndView save(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
       return super.save(form);
   }


    @Transactional @RequestMapping(value ="/proposalDevelopment", params = "methodToCall=navigate")
    public ModelAndView navigate(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        refreshDocumentLevelPermissions(form);

        return super.navigate(form, result, request, response);
    }

    protected void refreshDocumentLevelPermissions(ProposalDevelopmentDocumentForm form) {
        final List<ProposalUserRoles> currentRoles = form.getWorkingUserRoles();
        final List<ProposalUserRoles> newRoles = getProposalDevelopmentPermissionsService().getPermissions(form.getProposalDevelopmentDocument());

        if (isDocumentLevelRolesDirty(currentRoles, newRoles)) {
            form.setEvaluateFlagsAndModes(true);
        }
    }

    protected boolean isDocumentLevelRolesDirty(List<ProposalUserRoles> currentRoles, List<ProposalUserRoles> newRoles) {
        return !currentRoles.equals(newRoles);
    }

   @MethodAccessible
   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=getSponsor")
   public @ResponseBody Sponsor sponsorByCode(@RequestParam("sponsorCode") String sponsorCode) {
       final Sponsor sponsor =  getDataObjectService().find(Sponsor.class, sponsorCode);
       //clear references that are likely circular
       if (sponsor != null) {
           entityManager.detach(sponsor);
           sponsor.setUnit(null);
           sponsor.setRolodex(null);
       }
       return sponsor;
   }

   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveDetails")
   public ModelAndView saveDetails(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       return super.save(form, result, request, response);
   }

   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveOpportunity")
   public ModelAndView saveOpportunity(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       return super.save(form, result, request, response);
   }


   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveCompliance")
   public ModelAndView saveComplaince(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
        return super.save(pdForm, result, request, response, SaveDocumentSpecialReviewEvent.class);
   }

   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveProposalAttachments")
   public ModelAndView saveProposalAttachments(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }

   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveInternalAttachments")
   public ModelAndView saveInternalAttachments(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }

   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=savePersonnelAttachments")
   public ModelAndView savePersonnelAttachments(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }
   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveAbstracts")
   public ModelAndView saveAbstracts(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }
   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveNotes")
   public ModelAndView saveNotes(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }


    @MethodAccessible
   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=docHandler")
   public ModelAndView docHandler(@ModelAttribute("KualiForm") DocumentFormBase form, @RequestParam(required = false) String auditActivated, @RequestParam(required = false) String viewOnly,
                                  @RequestParam(required = false) String navigateToPageId, @RequestParam(required = false) String defaultOpenTab
                                  ) throws Exception {
        ProposalDevelopmentDocument document;
       boolean isDeleted = false;
       if(!ObjectUtils.isNull(form.getDocId())) {
           document = (ProposalDevelopmentDocument) getDocumentService().getByDocumentHeaderId(form.getDocId());
           isDeleted = document.isProposalDeleted();
       }
       if (auditActivated != null){
           ((ProposalDevelopmentDocumentForm)form).setAuditActivated(Boolean.parseBoolean(auditActivated));
       }
       if (isDeleted) {
            Properties props = new Properties();
            props.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, KRADConstants.START_METHOD);
            props.put(UifConstants.UrlParams.VIEW_ID, "PropDev-DeletedView");
            return getModelAndViewService().performRedirect(form, "proposalDevelopment", props);
       }
       else {
           boolean summaryView = false;

           // Change command when sent from an action list show
           if (form.getCommand().equals("displayActionListInlineView")) {
               summaryView = true;
               // Command used to get document info
               form.setCommand("displayActionListView");
           }
           ProposalDevelopmentDocumentForm propDevForm = (ProposalDevelopmentDocumentForm) form;


           try {
            ModelAndView modelAndView = getTransactionalDocumentControllerService().docHandler(form);
            propDevForm.initialize();
            propDevForm.getCustomDataHelper().prepareCustomData();

            setS2sOpportunityFromLookup(form, propDevForm);


               propDevForm.setWorkingUserRoles(getProposalDevelopmentPermissionsService().getPermissions(propDevForm.getProposalDevelopmentDocument()));

           if (summaryView) {
                form.setView(this.getDataDictionaryService().getViewById("PropDev-SummaryView"));
                form.setViewId("PropDev-SummaryView");
                return modelAndView;
            }

            if (CollectionUtils.isNotEmpty(propDevForm.getDevelopmentProposal().getProposalChangedDataList())) {
               getGlobalVariableService().getMessageMap().putInfoForSectionId("PropDev-DetailsPage", "info.dataoverride.occured");
            }

           if (propDevForm.getDocument().getDocumentHeader().getWorkflowDocument().isEnroute()) {
               ((ProposalDevelopmentViewHelperServiceImpl) form.getViewHelperService()).prepareSummaryPage(propDevForm);
               propDevForm.getView().setEntryPageId(ProposalDevelopmentConstants.KradConstants.SUBMIT_PAGE);
           }

           if (StringUtils.isNotBlank(navigateToPageId)) {
               propDevForm.getView().setEntryPageId(navigateToPageId);
           }

           if (StringUtils.isNotBlank(defaultOpenTab)) {
               propDevForm.setDefaultOpenTab(defaultOpenTab);
           }

            if (!propDevForm.getDocument().getDocumentHeader().getWorkflowDocument().isEnroute()
                    && (StringUtils.equals(form.getRequest().getParameter("viewDocument"),"true") || StringUtils.equals(viewOnly, "true")) ) {
                propDevForm.setViewOnly(true);
            }

            return modelAndView;
           }catch (AuthorizationException e) {
               return getModelAndViewService().getMessageView(form, "Authorization Exception Report", "Error Message: [color=red]"+ e.getMessage() + "[/color]");
           }
       }
   }

    protected void setS2sOpportunityFromLookup(DocumentFormBase form, ProposalDevelopmentDocumentForm propDevForm) throws ParseException {
        if (StringUtils.isNotBlank(form.getRequest().getParameter(ProposalDevelopmentConstants.S2sConstants.OPPORTUNITY_ID))) {

            S2sOpportunity opportunity = new S2sOpportunity();
            Calendar openingDate = Calendar.getInstance();
            if (ObjectUtils.isNotNull(form.getRequest().getParameter(ProposalDevelopmentConstants.S2sConstants.OPENING_DATE))) {
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.MM_DD_YYYY_HH_MM_A_DATE_FORMAT);
                openingDate.setTime(sdf.parse(form.getRequest().getParameter(ProposalDevelopmentConstants.S2sConstants.OPENING_DATE)));

            }
            opportunity.setOpeningDate(openingDate);
            opportunity.setCompetetionId(form.getRequest().getParameter(ProposalDevelopmentConstants.S2sConstants.COMPETETION_ID));
            opportunity.setInstructionUrl(form.getRequest().getParameter(ProposalDevelopmentConstants.S2sConstants.INSTRUCTION_URL));
            opportunity.setOpportunityId(form.getRequest().getParameter(ProposalDevelopmentConstants.S2sConstants.OPPORTUNITY_ID));
            final String opportunityTitle = form.getRequest().getParameter(ProposalDevelopmentConstants.S2sConstants.OPPORTUNITY_TITLE);
            String trimmedTitle = StringUtils.substring(opportunityTitle, 0, ProposalDevelopmentConstants.S2sConstants.OPP_TITLE_MAX_LENGTH);
            opportunity.setOpportunityTitle(trimmedTitle);
            opportunity.setProviderCode(form.getRequest().getParameter(ProposalDevelopmentConstants.S2sConstants.PROVIDER_CODE));
            opportunity.setSchemaUrl(form.getRequest().getParameter(ProposalDevelopmentConstants.S2sConstants.SCHEMA_URL));
            opportunity.setDevelopmentProposal(propDevForm.getProposalDevelopmentDocument().getDevelopmentProposal());

            propDevForm.getProposalDevelopmentDocument().getDevelopmentProposal().setS2sOpportunity(opportunity);

        }
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=editCollectionLine")
    public ModelAndView editCollectionLine(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);

        if(form.getEditableCollectionLines().containsKey(selectedCollectionPath)) {
            form.getEditableCollectionLines().get(selectedCollectionPath).add(selectedLine);
        } else {
            List<String> newKeyList = new ArrayList<String>();
            newKeyList.add(selectedLine);
            form.getEditableCollectionLines().put(selectedCollectionPath,newKeyList);
        }

        return getRefreshControllerService().refresh(form);
    }

    public DataDictionaryService getDataDictionaryService() {
        return dataDictionaryService;
    }

    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    public DocumentDictionaryService getDocumentDictionaryService() {
        return documentDictionaryService;
    }

    public void setDocumentDictionaryService(DocumentDictionaryService documentDictionaryService) {
        this.documentDictionaryService = documentDictionaryService;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public S2sSubmissionService getS2sSubmissionService() {
        return s2sSubmissionService;
    }

    public void setS2sSubmissionService(S2sSubmissionService s2sSubmissionService) {
        this.s2sSubmissionService = s2sSubmissionService;
    }

    public OpportunitySchemaParserService getOpportunitySchemaParserService() {
        return opportunitySchemaParserService;
    }

    public void setOpportunitySchemaParserService(OpportunitySchemaParserService opportunitySchemaParserService) {
        this.opportunitySchemaParserService = opportunitySchemaParserService;
    }

    public KualiRuleService getKualiRuleService() {
        return kualiRuleService;
    }

    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }
}
