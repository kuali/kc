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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.framework.compliance.core.SaveDocumentSpecialReviewEvent;
import org.kuali.coeus.propdev.impl.attachment.ProposalDevelopmentAttachmentService;
import org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationItem;
import org.kuali.coeus.propdev.impl.docperm.ProposalDevelopmentPermissionsHelper;
import org.kuali.coeus.propdev.impl.docperm.ProposalRoleTemplateService;
import org.kuali.coeus.propdev.impl.keyword.PropScienceKeyword;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.controller.KcCommonControllerService;
import org.kuali.coeus.sys.framework.controller.UifExportControllerService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.propdev.impl.auth.perm.ProposalDevelopmentPermissionsService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.DocumentBase;
import org.kuali.rice.krad.document.TransactionalDocumentControllerService;
import org.kuali.rice.krad.exception.ValidationException;
import org.kuali.rice.krad.rules.rule.event.DocumentEventBase;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krad.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ProposalDevelopmentControllerBase {

    protected static final String PROPDEV_DEFAULT_VIEW_ID = "PropDev-DefaultView";

    @Autowired
    @Qualifier("uifExportControllerService")
    private UifExportControllerService uifExportControllerService;

    @Autowired
    @Qualifier("kcCommonControllerService")
    private KcCommonControllerService kcCommonControllerService;

    @Autowired
    @Qualifier("collectionControllerService")
    private CollectionControllerService collectionControllerService;

    @Autowired
    @Qualifier("fileControllerService")
    private FileControllerService fileControllerService;

    @Autowired
    @Qualifier("modelAndViewService")
    private ModelAndViewService modelAndViewService;

    @Autowired
    @Qualifier("navigationControllerService")
    private NavigationControllerService navigationControllerService;

    @Autowired
    @Qualifier("queryControllerService")
    private QueryControllerService queryControllerService;

    @Autowired
    @Qualifier("refreshControllerService")
    private RefreshControllerService refreshControllerService;

    @Autowired
    @Qualifier("transactionalDocumentControllerService")
    private TransactionalDocumentControllerService transactionalDocumentControllerService;

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kraAuthorizationService;

    @Autowired
    @Qualifier("proposalDevelopmentService")
    private ProposalDevelopmentService proposalDevelopmentService;

    @Autowired
    @Qualifier("proposalDevelopmentPermissionsService")
    private ProposalDevelopmentPermissionsService proposalDevelopmentPermissionsService;

    @Autowired
    @Qualifier("proposalDevelopmentAttachmentService")
    private ProposalDevelopmentAttachmentService proposalDevelopmentAttachmentService;

    @Autowired
    @Qualifier("legacyDataAdapter")
    private LegacyDataAdapter legacyDataAdapter;
    
    @Autowired
    @Qualifier("proposalRoleTemplateService")
    private ProposalRoleTemplateService proposalRoleTemplateService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;
    
    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    protected DocumentFormBase createInitialForm(HttpServletRequest request) {
        return new ProposalDevelopmentDocumentForm();
    }
    
    @ModelAttribute(value = "KualiForm")
    public UifFormBase initForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UifFormBase form =  getKcCommonControllerService().initForm(this.createInitialForm(request), request, response);
        return form;
    }
     
    /**
     * Create the original set of Proposal Users for a new Proposal Development Document.
     * The creator the proposal is assigned to the AGGREGATOR role.
     */
     protected void initializeProposalUsers(ProposalDevelopmentDocument doc) {

         // Assign the creator of the proposal to the AGGREGATOR role.
         String userId = GlobalVariables.getUserSession().getPrincipalId();
         if (!kraAuthorizationService.hasDocumentLevelRole(userId, RoleConstants.AGGREGATOR, doc))
             kraAuthorizationService.addDocumentLevelRole(userId, RoleConstants.AGGREGATOR, doc);

         // Add the users defined in the role templates for the proposal's lead unit
         proposalRoleTemplateService.addUsers(doc);
     }

     protected void initialSave(ProposalDevelopmentDocument proposalDevelopmentDocument) {
         preSave(proposalDevelopmentDocument);
         proposalDevelopmentService.initializeUnitOrganizationLocation(
                 proposalDevelopmentDocument);
         proposalDevelopmentService.initializeProposalSiteNumbers(
                 proposalDevelopmentDocument);
     }

     protected void preSave(ProposalDevelopmentDocument proposalDevelopmentDocument) {
         if (proposalDevelopmentDocument.isDefaultDocumentDescription()) {
             proposalDevelopmentDocument.setDefaultDocumentDescription();
         }
     }

     public ModelAndView save(ProposalDevelopmentDocumentForm form, BindingResult result,
             HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 return save(form);
     }
     
     public ModelAndView save(ProposalDevelopmentDocumentForm form) throws Exception {
         ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) form.getDocument();

         if (StringUtils.equalsIgnoreCase(form.getPageId(), Constants.PROP_DEV_PERMISSIONS_PAGE)) {
             saveDocumentPermissions(form);
         }

         if (StringUtils.equalsIgnoreCase(form.getPageId(), Constants.CREDIT_ALLOCATION_PAGE)) {
             ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).populateCreditSplits(form);
         }

         preSave(proposalDevelopmentDocument);

         proposalDevelopmentService.initializeUnitOrganizationLocation(
                 proposalDevelopmentDocument);
         proposalDevelopmentService.initializeProposalSiteNumbers(
                 proposalDevelopmentDocument);

         getProposalDevelopmentAttachmentService().prepareAttachmentsForSave(form.getDevelopmentProposal());
         ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).setOrdinalPosition(form.getDevelopmentProposal().getProposalPersons());
         saveAnswerHeaders(form, form.getPageId());

         getTransactionalDocumentControllerService().save(form);

         String pageId = form.getActionParamaterValue(UifParameters.NAVIGATE_TO_PAGE_ID);
         ModelAndView view = null;
         if (StringUtils.isNotBlank(pageId) && getGlobalVariableService().getMessageMap().hasNoErrors()) {
        	 form.setDirtyForm(false);
             view = getModelAndViewService().getModelAndView(form, pageId);
         } else {
             view = getModelAndViewService().getModelAndView(form);
         }
         
         return view;
     }

     public ModelAndView save(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
             HttpServletRequest request, HttpServletResponse response, Class<? extends DocumentEventBase> eventClazz) throws Exception {
         ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
         ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) pdForm.getDocument();
         proposalDevelopmentService.initializeUnitOrganizationLocation(
                 proposalDevelopmentDocument);
         proposalDevelopmentService.initializeProposalSiteNumbers(
                 proposalDevelopmentDocument);
         ModelAndView view = null;

         saveAnswerHeaders(pdForm,request.getParameter(UifParameters.PAGE_ID));

         if (eventClazz == null) {
             getTransactionalDocumentControllerService().save(form);
         } else {
             performCustomSave(proposalDevelopmentDocument, SaveDocumentSpecialReviewEvent.class);
         }
         String pageId = form.getActionParamaterValue(UifParameters.NAVIGATE_TO_PAGE_ID);
         if (StringUtils.isNotBlank(pageId) && getGlobalVariableService().getMessageMap().hasNoErrors()) {
        	 form.setDirtyForm(false);
             view = getModelAndViewService().getModelAndView(form, pageId);
         } else {
             view = getModelAndViewService().getModelAndView(form);
         }
         initializeProposalUsers(proposalDevelopmentDocument);

         return view;
     }
     
     protected void performCustomSave(DocumentBase document, Class<? extends DocumentEventBase> eventClazz) {
         try {
             getDocumentService().saveDocument(document, eventClazz);
             GlobalVariables.getMessageMap().putInfo(KRADConstants.GLOBAL_MESSAGES, RiceKeyConstants.MESSAGE_SAVED);
         } catch (ValidationException e) {
             // if errors in map, swallow exception so screen will draw with errors
             // if not then throw runtime because something bad happened
             if (GlobalVariables.getMessageMap().hasNoErrors()) {
                 throw new RiceRuntimeException("Validation Exception with no error message.", e);
             }
         } catch (Exception e) {
             throw new RiceRuntimeException(
                     "Exception trying to save document: " + document
                             .getDocumentNumber(), e);
         }
     }
     
     protected ModelAndView navigate(ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	return save(form);
     }

    public void saveAnswerHeaders(ProposalDevelopmentDocumentForm pdForm) {
        for (ProposalPerson person : pdForm.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons()) {
            if (person.getQuestionnaireHelper() != null && person.getQuestionnaireHelper().getAnswerHeaders() != null
                    && !person.getQuestionnaireHelper().getAnswerHeaders().isEmpty()) {
                for (AnswerHeader answerHeader : person.getQuestionnaireHelper().getAnswerHeaders()) {
                    getLegacyDataAdapter().save(answerHeader);
                }
            }
        }
    }

    public void addEditableCollectionLine(ProposalDevelopmentDocumentForm form, String selectedCollectionPath){
        if(form.getEditableCollectionLines().containsKey(selectedCollectionPath)) {
            updateEditableCollectionLines(form, selectedCollectionPath);
        } else {
            List<String> newKeyList = new ArrayList<String>();
            newKeyList.add("0");
            form.getEditableCollectionLines().put(selectedCollectionPath,newKeyList);
        }

    }

    public void updateEditableCollectionLines(ProposalDevelopmentDocumentForm form, String selectedCollectionPath){
        List<String> indexes = new ArrayList<String>();
        indexes.add("0");
        for (String index : form.getEditableCollectionLines().get(selectedCollectionPath)) {
            Integer newIndex= Integer.parseInt(index) + 1;
            indexes.add(newIndex.toString());
        }
        form.getEditableCollectionLines().get(selectedCollectionPath).clear();
        form.getEditableCollectionLines().get(selectedCollectionPath).addAll(indexes);
    }

    protected KcAuthorizationService getKraAuthorizationService() {
        return kraAuthorizationService;
    }

    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    protected ProposalDevelopmentService getProposalDevelopmentService() {
        return proposalDevelopmentService;
    }

    public void setProposalDevelopmentService(ProposalDevelopmentService proposalDevelopmentService) {
        this.proposalDevelopmentService = proposalDevelopmentService;
    }

    public ProposalDevelopmentAttachmentService getProposalDevelopmentAttachmentService() {
        return proposalDevelopmentAttachmentService;
    }

    public void setProposalDevelopmentAttachmentService(ProposalDevelopmentAttachmentService proposalDevelopmentAttachmentService) {
        this.proposalDevelopmentAttachmentService = proposalDevelopmentAttachmentService;
    }

    protected TransactionalDocumentControllerService getTransactionalDocumentControllerService() {
        return transactionalDocumentControllerService;
    }

    public void setTransactionalDocumentControllerService(TransactionalDocumentControllerService transactionalDocumentControllerService) {
        this.transactionalDocumentControllerService = transactionalDocumentControllerService;
    }
    
    protected DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    protected LegacyDataAdapter getLegacyDataAdapter() {
        return legacyDataAdapter;
    }

    public void setLegacyDataAdapter(LegacyDataAdapter legacyDataAdapter) {
        this.legacyDataAdapter = legacyDataAdapter;
    }

	protected ProposalRoleTemplateService getProposalRoleTemplateService() {
		return proposalRoleTemplateService;
	}

	public void setProposalRoleTemplateService(
			ProposalRoleTemplateService proposalRoleTemplateService) {
		this.proposalRoleTemplateService = proposalRoleTemplateService;
	}    

	protected DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

	public void saveAnswerHeaders(ProposalDevelopmentDocumentForm pdForm,String pageId) {
        if (StringUtils.equalsIgnoreCase(pageId, Constants.KEY_PERSONNEL_PAGE)) {
            for (ProposalPerson person : pdForm.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons()) {
                if (person.getQuestionnaireHelper() != null && person.getQuestionnaireHelper().getAnswerHeaders() != null
                        && !person.getQuestionnaireHelper().getAnswerHeaders().isEmpty()) {
                    for (AnswerHeader answerHeader : person.getQuestionnaireHelper().getAnswerHeaders()) {
                        getLegacyDataAdapter().save(answerHeader);
                    }
                }
            }
        } else if (StringUtils.equalsIgnoreCase(pageId, Constants.QUESTIONS_PAGE)) {
            for (AnswerHeader answerHeader : pdForm.getQuestionnaireHelper().getAnswerHeaders()) {
                getLegacyDataAdapter().save(answerHeader);
            }
            for (AnswerHeader answerHeader : pdForm.getS2sQuestionnaireHelper().getAnswerHeaders()) {
                getLegacyDataAdapter().save(answerHeader);
            }
        }
	}

    /**
     * Method calls the permissions service, where it will determine if any user permissions need to be added and/or removed.
     *
     * @param pdForm ProposalDevelopmentDocumentForm that contains the permissions helper
     */
    public void saveDocumentPermissions(ProposalDevelopmentDocumentForm pdForm) {
        List<String> editableLines = pdForm.getEditableCollectionLines().get(Constants.PERMISSION_PROPOSAL_USERS_COLLECTION_PROPERTY_KEY);
        if (editableLines != null && editableLines.size() > 0) {
            getGlobalVariableService().getMessageMap().putErrorForSectionId(Constants.PERMISSION_PROPOSAL_USERS_COLLECTION_ID_KEY, KeyConstants.ERROR_UNFINISHED_PERMISSIONS);
        }
        else {
            ProposalDevelopmentPermissionsHelper helper = pdForm.getPermissionsHelper();
            getProposalDevelopmentPermissionsService().savePermissions(pdForm.getProposalDevelopmentDocument(), helper.getUserRoles(), helper.getWorkingUserRoles());
        }
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(List.class, "document.developmentProposal.propScienceKeywords", new PropScienceKeywordEditor());
    }

    protected class PropScienceKeywordEditor extends CustomCollectionEditor {
        public PropScienceKeywordEditor() {
            super(List.class);
        }

        protected Object convertElement(Object element) {
            if (element instanceof String) {
                return new PropScienceKeyword(null, getScienceKeyword(element));
            }
            return null;
        }
    }

    public boolean proposalValidToRoute(ProposalDevelopmentDocumentForm form) {
        boolean isValid = true;
        form.setAuditActivated(true);
        List<ProposalDevelopmentDataValidationItem> dataValidationItems = ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService())
                .populateDataValidation(form,form.getView().getViewIndex());
        if(dataValidationItems != null && dataValidationItems.size() > 0 ) {
            for(ProposalDevelopmentDataValidationItem validationItem : dataValidationItems) {
                if (StringUtils.equalsIgnoreCase(validationItem.getSeverity(), Constants.AUDIT_ERRORS)) {
                    isValid = false;
                    form.setDataValidationItems(dataValidationItems);
                    break;
                }
            }
        }
        getGlobalVariableService().getMessageMap().clearErrorMessages();
        return isValid;
    }

    protected ScienceKeyword getScienceKeyword(Object element) {
        return getDataObjectService().findUnique(ScienceKeyword.class, QueryByCriteria.Builder.forAttribute("code", element).build());
    }

    public UifExportControllerService getUifExportControllerService() {
        return uifExportControllerService;
    }

    public void setUifExportControllerService(UifExportControllerService uifExportControllerService) {
        this.uifExportControllerService = uifExportControllerService;
    }

    public KcCommonControllerService getKcCommonControllerService() {
        return kcCommonControllerService;
    }

    public void setKcCommonControllerService(KcCommonControllerService kcCommonControllerService) {
        this.kcCommonControllerService = kcCommonControllerService;
    }

    public CollectionControllerService getCollectionControllerService() {
        return collectionControllerService;
    }

    public void setCollectionControllerService(CollectionControllerService collectionControllerService) {
        this.collectionControllerService = collectionControllerService;
    }

    public FileControllerService getFileControllerService() {
        return fileControllerService;
    }

    public void setFileControllerService(FileControllerService fileControllerService) {
        this.fileControllerService = fileControllerService;
    }

    public ModelAndViewService getModelAndViewService() {
        return modelAndViewService;
    }

    public void setModelAndViewService(ModelAndViewService modelAndViewService) {
        this.modelAndViewService = modelAndViewService;
    }

    public NavigationControllerService getNavigationControllerService() {
        return navigationControllerService;
    }

    public void setNavigationControllerService(NavigationControllerService navigationControllerService) {
        this.navigationControllerService = navigationControllerService;
    }

    public QueryControllerService getQueryControllerService() {
        return queryControllerService;
    }

    public void setQueryControllerService(QueryControllerService queryControllerService) {
        this.queryControllerService = queryControllerService;
    }

    public RefreshControllerService getRefreshControllerService() {
        return refreshControllerService;
    }

    public void setRefreshControllerService(RefreshControllerService refreshControllerService) {
        this.refreshControllerService = refreshControllerService;
    }

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}

    protected ProposalDevelopmentPermissionsService getProposalDevelopmentPermissionsService() {
        return proposalDevelopmentPermissionsService;
    }

    public void setProposalDevelopmentPermissionsService(ProposalDevelopmentPermissionsService proposalDevelopmentPermissionsService) {
        this.proposalDevelopmentPermissionsService = proposalDevelopmentPermissionsService;
    }
}
