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
package org.kuali.coeus.propdev.impl.basic;

import java.util.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.compliance.core.SaveDocumentSpecialReviewEvent;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyCriteria;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.exception.DocumentAuthorizationException;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.service.DocumentDictionaryService;
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

   @MethodAccessible
   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=createProposal")
   public ModelAndView createProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {

       ProposalDevelopmentDocument proposalDevelopmentDocument = form.getProposalDevelopmentDocument();
       initialSave(proposalDevelopmentDocument);
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
       return getModelAndViewService().getModelAndViewWithInit(form, PROPDEV_DEFAULT_VIEW_ID);
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
            form.setDocument(document);
            WorkflowDocument workflowDocument = document.getDocumentHeader().getWorkflowDocument();
            form.setDocTypeName(workflowDocument.getDocumentTypeName());
            form.setProposalCopyCriteria(new ProposalCopyCriteria(document));

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
       return super.navigate(form, result, request, response);
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
   public ModelAndView docHandler(@ModelAttribute("KualiForm") DocumentFormBase form, @RequestParam(required = false) String auditActivated,
                                  @RequestParam(required = false) String navigateToPageId, @RequestParam(required = false) String defaultOpenTab) throws Exception {
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
            ModelAndView modelAndView = getTransactionalDocumentControllerService().docHandler(form);
            propDevForm.initialize();
            propDevForm.getCustomDataHelper().prepareCustomData();

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

            if (StringUtils.equals(form.getRequest().getParameter("viewDocument"),"true")) {
                propDevForm.setViewOnly(true);
            }

            return modelAndView;
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
}
