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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.coi.framework.*;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.compliance.core.SaveDocumentSpecialReviewEvent;
import org.kuali.coeus.common.framework.compliance.exemption.ExemptionType;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.propdev.impl.auth.perm.ProposalDevelopmentPermissionsService;
import org.kuali.coeus.propdev.impl.coi.CoiConstants;
import org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants;
import org.kuali.coeus.propdev.impl.docperm.ProposalRoleTemplateService;
import org.kuali.coeus.propdev.impl.docperm.ProposalUserRoles;
import org.kuali.coeus.propdev.impl.keyword.PropScienceKeyword;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationContext;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationRenderer;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonCoiIntegrationService;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyService;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentQuestionnaireHelper;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReviewExemption;
import org.kuali.coeus.sys.framework.controller.KcCommonControllerService;
import org.kuali.coeus.sys.framework.controller.UifExportControllerService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.impl.validation.DataValidationItem;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.criteria.QueryResults;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.DocumentBase;
import org.kuali.rice.krad.document.TransactionalDocumentControllerService;
import org.kuali.rice.krad.exception.ValidationException;
import org.kuali.rice.krad.rules.rule.event.DocumentEventBase;
import org.kuali.rice.krad.service.*;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.MessageMap;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

public abstract class ProposalDevelopmentControllerBase {

    protected static final String PROPDEV_DEFAULT_VIEW_ID = "PropDev-DefaultView";

    public static final String ERROR_CERTIFICATION_PERSON_ALREADY_ANSWERED = "error.certification.person.alreadyAnswered";
    public static final String ERROR_CERTIFICATION_ALREADY_ANSWERED = "error.certification.alreadyAnswered";
    public static final String DEVELOPMENT_PROPOSAL_NUMBER = "developmentProposal.proposalNumber";
    public static final String COI_DISCLOSURE_REQUIRED_ACTION_TYPE_CODE = "109";
    public static final String COI_DISCLOSURE_REQUIRED_NOTIFICATION = "COI disclosure required notification";
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
    @Qualifier("legacyDataAdapter")
    private LegacyDataAdapter legacyDataAdapter;
    
    @Autowired
    @Qualifier("proposalRoleTemplateService")
    private ProposalRoleTemplateService proposalRoleTemplateService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    
    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("proposalPersonBiographyService")
    private ProposalPersonBiographyService proposalPersonBiographyService;

    @Autowired
    @Qualifier("documentAdHocService")
    private DocumentAdHocService documentAdHocService;

    @Autowired
    @Qualifier("auditHelper")
    private AuditHelper auditHelper;

    @Autowired
    @Qualifier("pessimisticLockService")
    private PessimisticLockService pessimisticLockService;

    @Autowired
    @Qualifier("kcNotificationService")
    private KcNotificationService kcNotificationService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("dateTimeService")
    private DateTimeService dateTimeService;

    @Autowired
    @Qualifier("propDevProjectRetrievalService")
    private ProjectRetrievalService propDevProjectRetrievalService;
    
    @Autowired
    @Qualifier("proposalPersonCoiIntegrationService")
    ProposalPersonCoiIntegrationService proposalPersonCoiIntegrationService;

    @Autowired
    @Qualifier("proposalTypeService")
    private ProposalTypeService proposalTypeService;

    private ProjectPublisher projectPublisher;

    public ProjectPublisher getProjectPublisher() {
        //since COI is loaded last and @Lazy does not work, we have to use the ServiceLocator
        if (projectPublisher == null) {
            projectPublisher = KcServiceLocator.getService(ProjectPublisher.class);
        }

        return projectPublisher;
    }

    public void setProjectPublisher(ProjectPublisher projectPublisher) {
        this.projectPublisher = projectPublisher;
    }

    protected DocumentFormBase createInitialForm(HttpServletRequest request) {
        return new ProposalDevelopmentDocumentForm();
    }
    
    @ModelAttribute(value = "KualiForm")
    public UifFormBase initForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return  getKcCommonControllerService().initForm(this.createInitialForm(request), request, response);
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

         if (StringUtils.equalsIgnoreCase(form.getPageId(), ProposalDevelopmentDataValidationConstants.ATTACHMENT_PAGE_ID)) {
             ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).populateAttachmentReferences(form.getDevelopmentProposal());
         }

         if (getGlobalVariableService().getMessageMap().getErrorCount() == 0 && form.getEditableCollectionLines() != null) {
            form.getEditableCollectionLines().clear();
         }
         if (StringUtils.equalsIgnoreCase(form.getPageId(), ProposalDevelopmentDataValidationConstants.DETAILS_PAGE_ID)) {
             handleSponsorChange(proposalDevelopmentDocument);
             if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null) {
                 handleProposalTypeChange(proposalDevelopmentDocument.getDevelopmentProposal());
             }
         }

         preSave(proposalDevelopmentDocument);

         proposalDevelopmentService.initializeUnitOrganizationLocation(proposalDevelopmentDocument);
         proposalDevelopmentService.initializeProposalSiteNumbers(proposalDevelopmentDocument);

         for (ProposalPersonBiography biography : form.getDevelopmentProposal().getPropPersonBios()) {
             getProposalPersonBiographyService().prepareProposalPersonBiographyForSave(form.getDevelopmentProposal(),biography);
         }

         ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).setOrdinalPosition(form.getDevelopmentProposal().getProposalPersons());
         saveAnswerHeaders(form, form.getPageId());

         getTransactionalDocumentControllerService().save(form);
         if (form.isAuditActivated()){
             getAuditHelper().auditConditionally(form);
         }


         populateAdHocRecipients(form.getProposalDevelopmentDocument());

         if (StringUtils.equalsIgnoreCase(form.getPageId(), Constants.CREDIT_ALLOCATION_PAGE)) {
             ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).populateCreditSplits(form);
         }

         if (StringUtils.equalsIgnoreCase(form.getPageId(), Constants.QUESTIONS_PAGE)) {
             ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).populateQuestionnaires(form);
         }
         String pageId = form.getActionParamaterValue(UifParameters.NAVIGATE_TO_PAGE_ID);
         final ModelAndView view;
         if (StringUtils.isNotBlank(pageId) && getGlobalVariableService().getMessageMap().hasNoErrors()) {
        	 form.setDirtyForm(false);
             view = getModelAndViewService().getModelAndView(form, pageId);
         } else {
             view = getModelAndViewService().getModelAndView(form);
         }

         if (form.getProposalDevelopmentDocument().getDevelopmentProposal() != null
                 && form.getProposalDevelopmentDocument().getDevelopmentProposal().getPropSpecialReviews() != null) {
             form.getProposalDevelopmentDocument().getDevelopmentProposal().getPropSpecialReviews().stream()
                     .filter(specialReview -> !specialReview.isLinkedToProtocol())
                     .forEach(specialReview -> form.getSpecialReviewHelper().prepareProtocolLinkViewFields(specialReview));
         }
         final Project project = getPropDevProjectRetrievalService().retrieveProject(form.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalNumber());
         if (project != null) {
             getProjectPublisher().publishProject(project);
         }
         return view;
     }

    private void handleProposalTypeChange(DevelopmentProposal developmentProposal) {
        if (developmentProposal.getS2sOpportunity() != null) {
            String defaultS2sSubmissionTypeCode = getProposalTypeService().getDefaultSubmissionTypeCode(developmentProposal.getProposalTypeCode());
            developmentProposal.getS2sOpportunity().setS2sSubmissionTypeCode(defaultS2sSubmissionTypeCode);
            getDataObjectService().wrap(developmentProposal.getS2sOpportunity()).fetchRelationship("s2sSubmissionType");
        }
    }

    public ModelAndView save(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
             HttpServletRequest request, HttpServletResponse response, Class<? extends DocumentEventBase> eventClazz) throws Exception {
         ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
         ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) pdForm.getDocument();
         proposalDevelopmentService.initializeUnitOrganizationLocation(
                 proposalDevelopmentDocument);
         proposalDevelopmentService.initializeProposalSiteNumbers(
                 proposalDevelopmentDocument);

         saveAnswerHeaders(pdForm,request.getParameter(UifParameters.PAGE_ID));

         if (eventClazz == null) {
             getTransactionalDocumentControllerService().save(form);
         } else {
             performCustomSave(proposalDevelopmentDocument, SaveDocumentSpecialReviewEvent.class);
         }

         populateAdHocRecipients(pdForm.getProposalDevelopmentDocument());

         String pageId = form.getActionParamaterValue(UifParameters.NAVIGATE_TO_PAGE_ID);
         final ModelAndView view;
         if (StringUtils.isNotBlank(pageId) && getGlobalVariableService().getMessageMap().hasNoErrors()) {
        	 form.setDirtyForm(false);
             view = getModelAndViewService().getModelAndView(form, pageId);
         } else {
             view = getModelAndViewService().getModelAndView(form);
         }

         if (pdForm.getProposalDevelopmentDocument().getDevelopmentProposal() != null
                 && pdForm.getProposalDevelopmentDocument().getDevelopmentProposal().getPropSpecialReviews() != null) {
             pdForm.getProposalDevelopmentDocument().getDevelopmentProposal().getPropSpecialReviews().stream()
                     .filter(specialReview -> !specialReview.isLinkedToProtocol())
                     .forEach(specialReview -> pdForm.getSpecialReviewHelper().prepareProtocolLinkViewFields(specialReview));
         }
        final Project project = getPropDevProjectRetrievalService().retrieveProject(pdForm.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalNumber());
        if (project != null) {
            getProjectPublisher().publishProject(project);
        }

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
         if (form.getDevelopmentProposal().getS2sOpportunity() != null && !getProposalDevelopmentService().isGrantsGovEnabledForProposal(form.getDevelopmentProposal())) {
             ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).clearOpportunity(form.getDevelopmentProposal());
         }
         populateAdHocRecipients(form.getProposalDevelopmentDocument());
         String navigateToPageId = form.getActionParamaterValue(UifParameters.NAVIGATE_TO_PAGE_ID);
         if (isNavigateToDeliveryInfoPage(navigateToPageId)) {
             if (form.getDevelopmentProposal().getS2sOpportunity() != null) {
                 getGlobalVariableService().getMessageMap().putInfo(ProposalDevelopmentConstants.KradConstants.DELIVERY_INFO_PAGE, KeyConstants.DELIVERY_INFO_NOT_NEEDED);
             }
         }
         if (form.isCanEditView()) {
             return save(form);
         } else {
             return getNavigationControllerService().navigate(form);
         }

     }


    protected boolean isNavigateToDeliveryInfoPage(String navigateToPageId) {
        return StringUtils.equals(navigateToPageId, ProposalDevelopmentConstants.KradConstants.DELIVERY_INFO_PAGE);
    }

    public void addEditableCollectionLine(ProposalDevelopmentDocumentForm form, String selectedCollectionPath){
        if(form.getEditableCollectionLines().containsKey(selectedCollectionPath)) {
            updateEditableCollectionLines(form, selectedCollectionPath);
        } else {
            List<String> newKeyList = new ArrayList<>();
            newKeyList.add("0");
            form.getEditableCollectionLines().put(selectedCollectionPath,newKeyList);
        }

    }

    public void updateEditableCollectionLines(ProposalDevelopmentDocumentForm form, String selectedCollectionPath){
        List<String> indexes = new ArrayList<>();
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

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void saveAnswerHeaders(ProposalDevelopmentDocumentForm pdForm, String pageId) {
        if (StringUtils.equalsIgnoreCase(pageId, Constants.KEY_PERSONNEL_PAGE) ||
                StringUtils.equalsIgnoreCase(pageId, Constants.CERTIFICATION_PAGE)) {
            saveUpdatePersonAnswerHeaders(pdForm.getProposalDevelopmentDocument().getDevelopmentProposal(), pageId);
        } else if (StringUtils.equalsIgnoreCase(pageId, Constants.QUESTIONS_PAGE)) {
            saveUpdateQuestionnaireAnswerHeaders(pdForm.getQuestionnaireHelper(), pageId);
            saveUpdateQuestionnaireAnswerHeaders(pdForm.getS2sQuestionnaireHelper(), pageId);
        }
    }

    private void saveUpdatePersonAnswerHeaders(DevelopmentProposal developmentProposal, String pageId) {
        boolean allCertificationsWereComplete = true;
        boolean allCertificationAreNowComplete = true;

        for (ProposalPerson person : developmentProposal.getProposalPersons()) {
            if (person.getQuestionnaireHelper() != null && person.getQuestionnaireHelper().getAnswerHeaders() != null
                    && !person.getQuestionnaireHelper().getAnswerHeaders().isEmpty()) {
                boolean requiresUpdate = false;
                for (AnswerHeader answerHeader : person.getQuestionnaireHelper().getAnswerHeaders()) {
                    boolean wasComplete = answerHeader.isCompleted();
                    allCertificationsWereComplete &= wasComplete;

                    AnswerHeader currentAnswerHeader = retrieveCurrentAnswerHeader(answerHeader.getId());
                    if (currentAnswerHeader != null
                            && currentAnswerHeader.getVersionNumber() > answerHeader.getVersionNumber()) {
                        // Show error message if a newer version than the one being saved exists
                        getGlobalVariableService().getMessageMap().putError(pageId, ERROR_CERTIFICATION_PERSON_ALREADY_ANSWERED,
                                answerHeader.getQuestionnaire().getName(), person.getFullName());
                        updatePersonCertificationInfo(person, developmentProposal.getProjectId());
                        requiresUpdate = true;
                    } else {
                        getLegacyDataAdapter().save(answerHeader);

                        person.getQuestionnaireHelper().populateAnswers();
                        boolean isComplete = person.getQuestionnaireHelper().getAnswerHeaders().get(0).isCompleted();
                        allCertificationAreNowComplete &= isComplete;
                        if (isComplete && !wasComplete) {
                            person.setCertifiedBy(getGlobalVariableService().getUserSession().getPrincipalId());
                            person.setCertifiedTime(getDateTimeService().getCurrentTimestamp());
                            if (getParameterService().getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE,Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, Constants.PROP_PERSON_COI_STATUS_FLAG) &&
                                    !getProposalPersonCoiIntegrationService().getProposalPersonCoiStatus(person).equals(CoiConstants.DISCLOSURE_NOT_REQUIRED)) {
                            	sendCoiDisclosureRequiredNotification(developmentProposal,person);
                            }
                        } else if (wasComplete && !isComplete) {
                            person.setCertifiedBy(null);
                            person.setCertifiedTime(null);
                        }

                        checkForCertifiedByProxy(developmentProposal, person, isComplete && !wasComplete);
                    }
                }

                // Update questionnaire with most up-to-date information if required - ie, questionnaire was modified
                // by someone else
                if (requiresUpdate) {
                    person.getQuestionnaireHelper().populateAnswers();
                }
            }
        }

        boolean allowsSendCertificationCompleteNotification = getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, Constants.PARAMETER_COMPONENT_DOCUMENT, ProposalDevelopmentConstants.Parameters.NOTIFY_ALL_CERTIFICATIONS_COMPLETE);
        if (!allCertificationsWereComplete && allCertificationAreNowComplete && allowsSendCertificationCompleteNotification) {
            ProposalDevelopmentNotificationContext context = new ProposalDevelopmentNotificationContext(developmentProposal, "105", "All Proposal Persons Certification Completed");
            ((ProposalDevelopmentNotificationRenderer) context.getRenderer()).setDevelopmentProposal(developmentProposal);
            getKcNotificationService().sendNotification(context);
        }
    }

    protected void sendCoiDisclosureRequiredNotification(DevelopmentProposal developmentProposal,ProposalPerson person) {
    	ProposalDevelopmentNotificationContext context = new ProposalDevelopmentNotificationContext(developmentProposal, COI_DISCLOSURE_REQUIRED_ACTION_TYPE_CODE, COI_DISCLOSURE_REQUIRED_NOTIFICATION);
        ((ProposalDevelopmentNotificationRenderer) context.getRenderer()).setDevelopmentProposal(developmentProposal);
    	KcNotification notification = getKcNotificationService().createNotificationObject(context);
        if (notification.getMessage() != null) {
           getKcNotificationService().sendNotification(context,notification,createRecipientFromPerson(person));
        }
    }
    
    protected List<NotificationTypeRecipient> createRecipientFromPerson(ProposalPerson person) {
    	List<NotificationTypeRecipient> notificationRecipients = new ArrayList<>();
        NotificationTypeRecipient recipient = new NotificationTypeRecipient();
        recipient.setPersonId(person.getPersonId());
        recipient.setFullName(person.getFullName());
        notificationRecipients.add(recipient);
        return notificationRecipients;
    }
    
    private void saveUpdateQuestionnaireAnswerHeaders(ProposalDevelopmentQuestionnaireHelper questionnaireHelper, String pageId) {
        boolean requiresUpdate = false;
        for (AnswerHeader answerHeader : questionnaireHelper.getAnswerHeaders()) {
            AnswerHeader currentAnswerHeader = retrieveCurrentAnswerHeader(answerHeader.getId());
            if (currentAnswerHeader != null
                    && currentAnswerHeader.getVersionNumber() > answerHeader.getVersionNumber()) {
                // Show error message if a newer version than the one being saved exists
                getGlobalVariableService().getMessageMap().putError(pageId, ERROR_CERTIFICATION_ALREADY_ANSWERED,
                        answerHeader.getQuestionnaire().getName());

                requiresUpdate = true;
            }
            else {
                getLegacyDataAdapter().save(answerHeader);
            }
        }

        // Update questionnaire with most up-to-date information if required - ie, questionnaire was modified
        // by someone else
        if (requiresUpdate) {
            questionnaireHelper.populateAnswers();
        }
    }

    private AnswerHeader retrieveCurrentAnswerHeader(Long id) {
        if (id != null) {
            return getBusinessObjectService().findByPrimaryKey(AnswerHeader.class, Collections.singletonMap("id", id));
        }

        return null;
    }

    private void updatePersonCertificationInfo(ProposalPerson person, String proposalNumber) {
        // Update certified by and certified time with current info
        Map<String, Object> criteria = new HashMap<>();
        criteria.put(DEVELOPMENT_PROPOSAL_NUMBER, proposalNumber);
        QueryResults<ProposalPerson> currentPersons = getDataObjectService().findMatching(ProposalPerson.class, QueryByCriteria.Builder.andAttributes(criteria).build());
        for (ProposalPerson currentPerson : currentPersons.getResults()) {
            if (currentPerson.getUniqueId().equals(person.getUniqueId())) {
                person.setCertifiedBy(currentPerson.getCertifiedBy());
                person.setCertifiedTime(currentPerson.getCertifiedTime());
                break;
            }
        }
    }

    public void checkForCertifiedByProxy(DevelopmentProposal developmentProposal, ProposalPerson person, boolean recentlyCompleted) {
        boolean selfCertifyOnly = getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,Constants.PARAMETER_COMPONENT_DOCUMENT,ProposalDevelopmentConstants.Parameters.KEY_PERSON_CERTIFICATION_SELF_CERTIFY_ONLY);
        if (selfCertifyOnly) {
            String proxyId = getGlobalVariableService().getUserSession().getPrincipalId();
            if (!StringUtils.equals(person.getPersonId(), proxyId) && recentlyCompleted) {
                ProposalDevelopmentNotificationContext context = new ProposalDevelopmentNotificationContext(developmentProposal,"106","Proposal Person Certification Completed");
                ((ProposalDevelopmentNotificationRenderer) context.getRenderer()).setDevelopmentProposal(developmentProposal);
                KcNotification notification = getKcNotificationService().createNotificationObject(context);
                NotificationTypeRecipient recipient = new NotificationTypeRecipient();
                recipient.setPersonId(person.getPersonId());
                getKcNotificationService().sendNotification(context,notification,Collections.singletonList(recipient));
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
        } else if (arePermissionsValid(pdForm.getProposalDevelopmentDocument(),pdForm.getWorkingUserRoles())) {
            getProposalDevelopmentPermissionsService().savePermissions(pdForm.getProposalDevelopmentDocument(), getProposalDevelopmentPermissionsService().getPermissions(pdForm.getProposalDevelopmentDocument()), pdForm.getWorkingUserRoles());
        }
    }

    protected boolean arePermissionsValid(ProposalDevelopmentDocument document, List<ProposalUserRoles> proposalUsers) {
        boolean retVal = true;
        for (ProposalUserRoles proposalUser : proposalUsers) {
            retVal &= getProposalDevelopmentPermissionsService().validateUpdatePermissions(document,proposalUsers,proposalUser);
        }
        return retVal;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(List.class, "document.developmentProposal.propScienceKeywords", new PropScienceKeywordEditor());
        binder.registerCustomEditor(List.class, "document.developmentProposal.propSpecialReviews.specialReviewExemptions", new PropSpecialReviewExemptionTypeEditor());

        // For add line binding
        binder.registerCustomEditor(List.class, "newCollectionLines.specialReviewExemptions", new PropSpecialReviewExemptionTypeEditor());
    }

    protected class PropScienceKeywordEditor extends CustomCollectionEditor {
        public PropScienceKeywordEditor() {
            super(List.class, true);
        }

        protected Object convertElement(Object element) {
            if (element != null && element instanceof String) {
                return new PropScienceKeyword(null, getScienceKeyword(element));
            }

            return element;
        }

        public String getAsText() {
            if (this.getValue() != null) {
                @SuppressWarnings("unchecked")
                final Collection<PropScienceKeyword> keywords = (Collection<PropScienceKeyword>) this.getValue();
                StringBuilder result = new StringBuilder();
                for(PropScienceKeyword keyword : keywords) {
                    result.append(keyword.getScienceKeyword().getCode());
                    result.append(",");
                }

                if (result.length() > 0) {
                    return result.substring(0, result.length() - 1);
                } else {
                	return "";
                }
            }
            return null;
        }
    }

    /**
     * Editor to convert (to and from) a String list of exemption type codes to ProposalSpecialReviewExemption objects
     */
    protected class PropSpecialReviewExemptionTypeEditor extends CustomCollectionEditor {
 		public PropSpecialReviewExemptionTypeEditor() {
 			super(List.class, true);
 		}

 		protected Object convertElement(Object element) {
 			if (element != null && element instanceof String) {
 				return new ProposalSpecialReviewExemption(null, getExemptionType(element));
 			}

            return element;
 		}

        public String getAsText() {
            if (this.getValue() != null) {
                @SuppressWarnings("unchecked")
                final Collection<ProposalSpecialReviewExemption> exemptions = (Collection<ProposalSpecialReviewExemption>) this.getValue();
                StringBuilder result = new StringBuilder();
                for(ProposalSpecialReviewExemption exemption : exemptions) {
                    result.append(exemption.getExemptionTypeCode());
                    result.append(",");
                }

                if (result.length() > 0) {
                    return result.substring(0, result.length() - 1);
                }
            }
            return null;
        }
 	}

    protected ExemptionType getExemptionType(Object element) {
 	   return getDataObjectService().findUnique(ExemptionType.class, QueryByCriteria.Builder.forAttribute("code", element).build());
    }

    public AuditHelper.ValidationState getValidationState(ProposalDevelopmentDocumentForm form) {
        AuditHelper.ValidationState severityLevel = AuditHelper.ValidationState.OK;
        form.setAuditActivated(true);
        List<DataValidationItem> dataValidationItems = ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService())
                .populateDataValidation(form);
        if(dataValidationItems != null && dataValidationItems.size() > 0 ) {
            for(DataValidationItem validationItem : dataValidationItems) {
                if (StringUtils.endsWith(validationItem.getSeverity(),Constants.AUDIT_ERRORS)) {
                    severityLevel = AuditHelper.ValidationState.ERROR;
                    break;
                }
                if (StringUtils.equals(validationItem.getSeverity(), Constants.AUDIT_WARNINGS)){
                    severityLevel = AuditHelper.ValidationState.WARNING;
                }
            }
            form.setDataValidationItems(dataValidationItems);
        }
        getGlobalVariableService().getMessageMap().clearErrorMessages();
        return severityLevel;
    }

    /**
     * During navigation and routing the ad hoc recipients which are transient get removed.  To solve this, repopulate them in the document before each save.
     * This will stop the system from removing the current recipients from the database.
     *
     * Extra logic added to assist in avoiding the null document number issue.
     */
    public void populateAdHocRecipients(ProposalDevelopmentDocument proposalDevelopmentDocument){
        if (StringUtils.isEmpty(proposalDevelopmentDocument.getDocumentNumber())
                && proposalDevelopmentDocument.getDocumentHeader() != null && StringUtils.isNotEmpty(proposalDevelopmentDocument.getDocumentHeader().getDocumentNumber())){
            proposalDevelopmentDocument.setDocumentNumber(proposalDevelopmentDocument.getDocumentHeader().getDocumentNumber());
        }
        if (StringUtils.isNotEmpty(proposalDevelopmentDocument.getDocumentNumber())){
            getDocumentAdHocService().addAdHocs(proposalDevelopmentDocument);
        }

    }

    /**
     *
     * If the sponsor has changed, default the key personnel role codes to COI if the role can't be found
     */
    public void handleSponsorChange(ProposalDevelopmentDocument proposalDevelopmentDocument){
        for (int i=0; i< proposalDevelopmentDocument.getDevelopmentProposal().getProposalPersons().size();i++){
            ProposalPerson person = proposalDevelopmentDocument.getDevelopmentProposal().getProposalPersons().get(i);
            if (person.getRole() == null){
                person.setProposalPersonRoleId(PropAwardPersonRole.CO_INVESTIGATOR);
                String propertyName = ProposalDevelopmentConstants.PropertyConstants.PROPOSAL_PERSONS;
                getGlobalVariableService().getMessageMap().putInfo(propertyName + "[" + i + "].proposalPersonRoleId", KeyConstants.INFO_PERSONNEL_INVALID_ROLE, person.getDevelopmentProposal().getSponsorCode(), person.getFullName());
            }
        }
    }

    public void populateDeferredMessages(ProposalDevelopmentDocumentForm proposalDevelopmentDocumentForm){
        if (proposalDevelopmentDocumentForm.getDeferredMessages() != null
                && proposalDevelopmentDocumentForm.getDeferredMessages().hasMessages()){
            MessageMap messageMap = proposalDevelopmentDocumentForm.getDeferredMessages();
            MessageMap currentMessageMap = getGlobalVariableService().getMessageMap();
            messageMap.getErrorMessages().putAll(currentMessageMap.getErrorMessages());
            messageMap.getInfoMessages().putAll(currentMessageMap.getInfoMessages());
            messageMap.getWarningMessages().putAll(currentMessageMap.getWarningMessages());
            getGlobalVariableService().setMessageMap(messageMap);
        }
        proposalDevelopmentDocumentForm.setDeferredMessages(null);
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

    public ProposalPersonBiographyService getProposalPersonBiographyService() {
        return proposalPersonBiographyService;
    }

    public void setProposalPersonBiographyService(ProposalPersonBiographyService proposalPersonBiographyService) {
        this.proposalPersonBiographyService = proposalPersonBiographyService;
    }

    public DocumentAdHocService getDocumentAdHocService() {
        return documentAdHocService;
    }

    public void setDocumentAdHocService(DocumentAdHocService documentAdHocService) {
        this.documentAdHocService = documentAdHocService;
    }

    public AuditHelper getAuditHelper() {
        return auditHelper;
    }

    public void setAuditHelper(AuditHelper auditHelper) {
        this.auditHelper = auditHelper;
    }

    public KcNotificationService getKcNotificationService() {
        return kcNotificationService;
    }

    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public PessimisticLockService getPessimisticLockService() {
        return pessimisticLockService;
    }

    public void setPessimisticLockService(PessimisticLockService pessimisticLockService) {
        this.pessimisticLockService = pessimisticLockService;
    }

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public ProjectRetrievalService getPropDevProjectRetrievalService() {
        return propDevProjectRetrievalService;
    }

    public void setPropDevProjectRetrievalService(ProjectRetrievalService propDevProjectRetrievalService) {
        this.propDevProjectRetrievalService = propDevProjectRetrievalService;
    }
    
    public ProposalPersonCoiIntegrationService getProposalPersonCoiIntegrationService() {
		return proposalPersonCoiIntegrationService;
	}

	public void setProposalPersonCoiIntegrationService(
			ProposalPersonCoiIntegrationService proposalPersonCoiIntegrationService) {
		this.proposalPersonCoiIntegrationService = proposalPersonCoiIntegrationService;
	}
    public ProposalTypeService getProposalTypeService() {
        return proposalTypeService;
    }

    public void setProposalTypeService(ProposalTypeService proposalTypeService) {
        this.proposalTypeService = proposalTypeService;
    }


}
