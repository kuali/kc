/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.coi.actions;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.common.notification.impl.NotificationHelper;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.*;
import org.kuali.kra.coi.certification.SubmitDisclosureAction;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.coi.notesandattachments.notes.CoiDisclosureNotepad;
import org.kuali.kra.coi.notification.*;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.sql.Date;
import java.util.*;

/**
 * 
 * This class implement methods defined in CoiDisclosureActionService.
 * This is mostly for disclosure actions page.
 */
public class CoiDisclosureActionServiceImpl implements CoiDisclosureActionService {

    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private KcNotificationService kcNotificationService;
    private static final Log LOG = LogFactory.getLog(CoiDisclosureActionServiceImpl.class);
    private static final String MODULE_ITEM_CODE = "moduleItemCode";
    private static final String MODULE_ITEM_KEY = "moduleItemKey";
    
    /**
     * copy disc details from previous master disclosure if it exists.
     * create a disclosure history methods.
     * set current disclosure flag for this approved disclosure, and reset it for previous mater disclosure
     * @throws WorkflowException 
     * @see org.kuali.kra.coi.actions.CoiDisclosureActionService#approveDisclosure(org.kuali.kra.coi.CoiDisclosure, java.lang.String)
     */
    public void approveDisclosure(CoiDisclosure coiDisclosure, String coiDispositionCode) throws WorkflowException {
        CoiDisclosure masterCoiDisclosure = getMasterDisclosure(coiDisclosure.getCoiDisclosureNumber());
        List<KcPersistableBusinessObjectBase> disclosures = new ArrayList<KcPersistableBusinessObjectBase>();
        coiDisclosure.setDisclosureDispositionCode(coiDispositionCode);
        coiDisclosure.setDisclosureStatusCode(CoiDisclosureStatus.APPROVED);
        
        // Update the corresponding discl project
        updateCoiDisclProjectStatus(coiDisclosure, CoiDisclosureStatus.APPROVED);
        
        disclosures.add(coiDisclosure);
        
        //if this isn't an update master or annual update, then move the answer headers
        //to the appropriate project before making this disclosure the master.
        if (!coiDisclosure.isUpdateEvent() && !coiDisclosure.isAnnualEvent()) {
            fixAnswerHeader(coiDisclosure);
        }
        
        if (masterCoiDisclosure != null) {
            if (coiDisclosure.isUpdateEvent() || (coiDisclosure.isAnnualEvent() && coiDisclosure.isAnnualUpdate())) {
                syncCollections(masterCoiDisclosure, coiDisclosure);
            } else {
                copyCollections(masterCoiDisclosure, coiDisclosure);
            }
            masterCoiDisclosure.setCurrentDisclosure(false);
            disclosures.add(masterCoiDisclosure);

        } 
        
        setDisclosureReviewStatus(coiDisclosure, CoiReviewStatus.REVIEW_COMPLETE);
        
        coiDisclosure.setCurrentDisclosure(true);
        documentService.approveDocument(coiDisclosure.getCoiDisclosureDocument(), "Document approved.", new ArrayList<AdHocRouteRecipient>());

        disclosures.add(createDisclosureHistory(coiDisclosure));
        businessObjectService.save(disclosures);
        sendNotification(coiDisclosure, CoiActionType.APPROVED_EVENT, "Approved");        
    }
    
    /**
     * This disapproves the document and sets the disclosure and disposition statuses.
     * @see org.kuali.kra.coi.actions.CoiDisclosureActionService#disapproveDisclosure(org.kuali.kra.coi.CoiDisclosure, java.lang.String)
     */
    public void disapproveDisclosure(CoiDisclosure coiDisclosure, String coiDispositionCode) throws Exception {
        
        coiDisclosure.setDisclosureDispositionCode(coiDispositionCode);
        coiDisclosure.setDisclosureStatusCode(CoiDisclosureStatus.DISAPPROVED);
        
        // Update the corresponding discl project
        updateCoiDisclProjectStatus(coiDisclosure, CoiDisclosureStatus.DISAPPROVED);
      
        setDisclosureReviewStatus(coiDisclosure, CoiReviewStatus.REVIEW_COMPLETE);

        businessObjectService.save(coiDisclosure);
        businessObjectService.save(createDisclosureHistory(coiDisclosure));
        documentService.disapproveDocument(coiDisclosure.getCoiDisclosureDocument(), "Document approved.");       
        sendNotification(coiDisclosure, CoiActionType.DISAPPROVED_EVENT, "Disapproved");        
    }

    public KcNotificationService getKcNotificationService() {
        return kcNotificationService;
    }

    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }

    public ActionForward addCoiUserRole(ActionMapping mapping, ActionForm form, CoiDisclosure coiDisclosure, CoiUserRole coiUserRole) {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);

        coiDisclosure.getCoiUserRoles().add(coiUserRole);
        setDisclosureReviewStatus(coiDisclosure, CoiReviewStatus.ASSIGNED_TO_REVIEWER);
        businessObjectService.save(coiDisclosure);
        return sendNotification(mapping, form, forward, coiUserRole, "Assigned");
    }
    
    public ActionForward deleteCoiUserRole(ActionMapping mapping, ActionForm form, CoiDisclosure coiDisclosure, int index) {
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);

        if (index >= 0 && index < coiDisclosure.getCoiUserRoles().size()) {
            CoiUserRole coiUserRole = coiDisclosure.getCoiUserRoles().remove(index);
            resetDisclosureReviewStatus(coiDisclosure);
            businessObjectService.save(coiDisclosure);
            return sendNotification(mapping, form, forward, coiUserRole, "Removed");
        }
        return forward;
    }

    /*
     * retrieve current master disclosure
     */
    @SuppressWarnings("unchecked")
    private CoiDisclosure getMasterDisclosure(String coiDisclosureNumber) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("coiDisclosureNumber", coiDisclosureNumber);
        fieldValues.put("currentDisclosure", "Y");

        List<CoiDisclosure> disclosures = (List<CoiDisclosure>)businessObjectService.findMatching(CoiDisclosure.class, fieldValues);
        if (CollectionUtils.isNotEmpty(disclosures)) {
            return disclosures.get(0);
        } else {
            return null;
        }
    }
    
    private void  copyCollections(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {
        
        copyDisclosureProjects(masterCoiDisclosure, coiDisclosure);
        copyDisclosureNotePads(masterCoiDisclosure, coiDisclosure);
        copyDisclosureAttachments(masterCoiDisclosure, coiDisclosure);
        copyDisclosureQuestionnaire(masterCoiDisclosure, coiDisclosure);
    }
    
    protected void fixAnswerHeader(CoiDisclosure coiDisclosure) {
        String moduleSubKey = coiDisclosure.getCoiDisclProjects().get(0).getCoiProjectId();
        for (AnswerHeader answerHeader : retrieveAnswerHeaders(coiDisclosure)) {
            answerHeader.setModuleSubItemKey(moduleSubKey);
            businessObjectService.save(answerHeader);
        }
        
    }
    
    private void copyDisclosureQuestionnaire(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {
        // versioning questionnaire answer
            try {
            CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument) KcServiceLocator
                    .getService(DocumentService.class).getByDocumentHeaderId(masterCoiDisclosure.getCoiDisclosureDocument().getDocumentNumber());
            // Get XML of workflow document
            masterCoiDisclosure.setCoiDisclosureDocument(coiDisclosureDocument);
            } catch (Exception e) {
                
            }
            List<AnswerHeader> newAnswerHeaders = versioningQuestionnaireAnswer(masterCoiDisclosure);
         if (!newAnswerHeaders.isEmpty()) {
             for (AnswerHeader answerHeader : newAnswerHeaders) {
                 answerHeader.setModuleItemKey(coiDisclosure.getCoiDisclosureId().toString());
             }
            businessObjectService.save(newAnswerHeaders);
        }
    
    }
    
    private List<AnswerHeader> versioningQuestionnaireAnswer(CoiDisclosure coiDisclosure) {
        List<AnswerHeader> newAnswerHeaders = new ArrayList<AnswerHeader>();
        for (AnswerHeader answerHeader : retrieveAnswerHeaders(coiDisclosure)) {
               AnswerHeader copiedAnswerHeader = (AnswerHeader) ObjectUtils.deepCopy(answerHeader);
                copiedAnswerHeader.setId(null);
                for (Answer answer : copiedAnswerHeader.getAnswers()) {
                    answer.setId(null);
                }
                newAnswerHeaders.add(copiedAnswerHeader);
        }
        return newAnswerHeaders;
    }

    private List<AnswerHeader> retrieveAnswerHeaders(CoiDisclosure coiDisclosure) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(MODULE_ITEM_CODE, CoeusModule.COI_DISCLOSURE_MODULE_CODE);
        fieldValues.put(MODULE_ITEM_KEY, coiDisclosure.getCoiDisclosureId().toString());
        return (List<AnswerHeader>) businessObjectService.findMatching(AnswerHeader.class, fieldValues);
    }

    private void copyDisclosureDetails(List<CoiDiscDetail> originalDiscDetails, CoiDisclProject copiedDisclProject) {
        List<CoiDiscDetail> copiedDiscDetails = new ArrayList<CoiDiscDetail>();
        for (CoiDiscDetail coiDiscDetail : originalDiscDetails) {
            CoiDiscDetail copiedDiscDetail = (CoiDiscDetail) ObjectUtils.deepCopy(coiDiscDetail);
            copiedDiscDetail.setCopiedCoiDiscDetailId(copiedDiscDetail.getCoiDiscDetailId());
            copiedDiscDetail.setSequenceNumber(copiedDisclProject.getSequenceNumber());
            copiedDiscDetail.setCoiDiscDetailId(null);
            copiedDiscDetail.setCoiDisclProjectId(null);
            if (copiedDiscDetail.getOriginalCoiDisclosureId() == null) {
                copiedDiscDetail.setOriginalCoiDisclosureId(copiedDisclProject.getCoiDisclosureId());
            }
            copiedDiscDetails.add(copiedDiscDetail);
        }
        copiedDisclProject.setCoiDiscDetails(copiedDiscDetails);
        if (copiedDisclProject.getOriginalCoiDisclosureId() == null) {
        copiedDisclProject.setOriginalCoiDisclosureId(copiedDisclProject.getCoiDisclosureId());
        }
    }
    
    //TODO: finish project copy and work in subsequent details gettting copied
    private void copyDisclosureProjects(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {
        List<CoiDisclProject> copiedDisclProjects = new ArrayList<CoiDisclProject>();
        for (CoiDisclProject coiDisclProject : masterCoiDisclosure.getCoiDisclProjects()) {
//            if (!coiDisclProject.getCoiDisclosureEventType().isExcludeFromMasterDisclosure()) {
                List<CoiDiscDetail> coiDiscDetails = coiDisclProject.getCoiDiscDetails();
                // coiDisclProject.setCoiDiscDetails(null);
                CoiDisclProject copiedDisclProject = (CoiDisclProject) ObjectUtils.deepCopy(coiDisclProject);
                copiedDisclProject.setSequenceNumber(coiDisclosure.getSequenceNumber());
                copiedDisclProject.setCoiDisclProjectsId(null);

                // copy disc details
                copyDisclosureDetails(coiDiscDetails, copiedDisclProject);
                copiedDisclProjects.add(copiedDisclProject);
                copiedDisclProject.setCoiDisclosureId(null);
//            }
        }
        coiDisclosure.getCoiDisclProjects().addAll(copiedDisclProjects);
    }

    private void copyDisclosureNotePads(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {
        // may also need to add note/attachment to new master disclosure
//        CoiDisclosure copiedDisclosure = (CoiDisclosure) ObjectUtils.deepCopy(masterCoiDisclosure);
        for (CoiDisclosureNotepad coiDisclosureNotepad : masterCoiDisclosure.getCoiDisclosureNotepads()) {
//            if (!isDisclosureNotePadExist(coiDisclosure, coiDisclosureNotepad)) {
                // TODO implement the if check when originaldisclosureid is added to notepad
                CoiDisclosureNotepad copiedCoiDisclosureNotepad = (CoiDisclosureNotepad) ObjectUtils.deepCopy(coiDisclosureNotepad);
                copiedCoiDisclosureNotepad.setSequenceNumber(coiDisclosure.getSequenceNumber());
                copiedCoiDisclosureNotepad.setId(null);
                if (copiedCoiDisclosureNotepad.getOriginalCoiDisclosureId() == null) {
                    copiedCoiDisclosureNotepad.setOriginalCoiDisclosureId(masterCoiDisclosure.getCoiDisclosureId());
                }
                coiDisclosure.getCoiDisclosureNotepads().add(copiedCoiDisclosureNotepad);
//            }
        }
    }

    private void copyDisclosureAttachments(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {
        // may also need to add note/attachment to new master disclosure
//        CoiDisclosure copiedDisclosure = (CoiDisclosure) ObjectUtils.deepCopy(masterCoiDisclosure);
        for (CoiDisclosureAttachment coiDisclosureAttachment : masterCoiDisclosure.getCoiDisclosureAttachments()) {
//            if (!isDisclosureNotePadExist(coiDisclosure, coiDisclosureNotepad)) {
                // TODO implement the if check when originaldisclosureid is added to notepad
                CoiDisclosureAttachment copiedCoiDisclosureAttachment = (CoiDisclosureAttachment) ObjectUtils.deepCopy(coiDisclosureAttachment);
                copiedCoiDisclosureAttachment.setSequenceNumber(coiDisclosure.getSequenceNumber());
                copiedCoiDisclosureAttachment.setAttachmentId(null);
                copiedCoiDisclosureAttachment.setFile(coiDisclosureAttachment.getFile());
                copiedCoiDisclosureAttachment.setFileId(coiDisclosureAttachment.getFileId());
                if (copiedCoiDisclosureAttachment.getOriginalCoiDisclosureId() == null) {
                    copiedCoiDisclosureAttachment.setOriginalCoiDisclosureId(masterCoiDisclosure.getCoiDisclosureId());
                }
                coiDisclosure.getCoiDisclosureAttachments().add(copiedCoiDisclosureAttachment);
//            }
        }
    }
    
    /*
     * create a disclosure history record for the disclosure being approved
     */
    private CoiDisclosureHistory createDisclosureHistory(CoiDisclosure coiDisclosure) {
        CoiDisclosureHistory coiDisclosureHistory = new CoiDisclosureHistory();
        coiDisclosureHistory.setCoiDisclosureId(coiDisclosure.getCoiDisclosureId());
        coiDisclosureHistory.setCoiDisclosureNumber(coiDisclosure.getCoiDisclosureNumber());
        coiDisclosureHistory.setSequenceNumber(coiDisclosure.getSequenceNumber());
        coiDisclosureHistory.setDisclosureDispositionStatus(coiDisclosure.getDisclosureDispositionCode());
        coiDisclosureHistory.setDisclosureStatus(coiDisclosure.getDisclosureStatusCode());
        return coiDisclosureHistory;

    }

    /**
     * This method submits a disclosure to workflow
     * @param coiDisclosureDocument
     * @param submitDisclosureAction
     */
     public void submitToWorkflow(CoiDisclosureDocument coiDisclosureDocument, CoiDisclosureForm coiDisclosureForm, SubmitDisclosureAction submitDisclosureAction) {
         CoiDisclosure disclosure = coiDisclosureDocument.getCoiDisclosure();
         setDisclosureReviewStatus(disclosure, CoiReviewStatus.SUBMITTED_FOR_REVIEW);
         try {
            documentService.routeDocument(coiDisclosureDocument, "Disclosure has been certified and submitted.", new ArrayList<AdHocRouteRecipient>());
        } catch (WorkflowException e) {
            String errorString = "WorkflowException certifying Disclosure for user col %s" + coiDisclosureDocument.getCoiDisclosure().getAuthorPersonName(); 
            LOG.error(errorString, e);
            throw new RuntimeException(errorString, e);
        }
    }

    public ActionForward sendCertificationNotifications(CoiDisclosureDocument coiDisclosureDocument, CoiDisclosureForm coiDisclosureForm, SubmitDisclosureAction submitDisclosureAction, ActionMapping mapping) {
        DisclosureCertifiedNotificationRenderer renderer = new DisclosureCertifiedNotificationRenderer(coiDisclosureDocument.getCoiDisclosure(), CoiDisclosure.CERTIFIED);
        DisclosureCertifiedNotificationRequestBean disclosureCertifiedNotificationBean = getDisclosureCertifiedRequestBean(coiDisclosureDocument.getCoiDisclosure(), submitDisclosureAction.getReviewers());
        
        CoiNotificationContext context = new CoiNotificationContext(coiDisclosureDocument.getCoiDisclosure(), 
                                                                    disclosureCertifiedNotificationBean.getActionType(), 
                                                                    disclosureCertifiedNotificationBean.getDescription(), renderer);
        return checkToSendNotification(mapping, mapping.findForward(Constants.MAPPING_BASIC), coiDisclosureForm, renderer, context, disclosureCertifiedNotificationBean);
    }
    
    private DisclosureCertifiedNotificationRequestBean getDisclosureCertifiedRequestBean(CoiDisclosure coiDisclosure, List<CoiUserRole> userRoles) {
        DisclosureCertifiedNotificationRequestBean newBean = new DisclosureCertifiedNotificationRequestBean(coiDisclosure, userRoles);
        return newBean;
    }

    private ActionForward checkToSendNotification(ActionMapping mapping, ActionForward forward, CoiDisclosureForm coiDisclosureForm, 
                                                  DisclosureCertifiedNotificationRenderer renderer, CoiNotificationContext context, 
                                                  DisclosureCertifiedNotificationRequestBean notificationRequestBean) {
        
        if (coiDisclosureForm.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
            coiDisclosureForm.getNotificationHelper().initializeDefaultValues(context);
            return mapping.findForward("protocolNotificationEditor");
        } else {
            getKcNotificationService().sendNotificationAndPersist(context, new CoiNotification(), coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure());
            return null;
        }
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    public DocumentService getDocumentService() {
        return documentService;
    }

    public void updateCoiDisclProjectStatus(CoiDisclosure coiDisclosure, String disclosureStatus) {
        List<CoiDisclProject> disclProjects = coiDisclosure.getCoiDisclProjects();
        
        for (CoiDisclProject tmpProj : disclProjects) {
            if (StringUtils.equals(tmpProj.getDisclosureEventType(), coiDisclosure.getEventTypeCode())
                && StringUtils.equals(tmpProj.getModuleItemKey(), coiDisclosure.getModuleItemKey()) ) {
                tmpProj.setDisclosureStatusCode(disclosureStatus);
            }
        }      
    }
    
    public void updateCoiDisclProjectDisposition(CoiDisclosure coiDisclosure, String dispositionCode) {
        List<CoiDisclProject> disclProjects = coiDisclosure.getCoiDisclProjects();
        Integer dispositionStatus = Integer.valueOf(dispositionCode);
        for (CoiDisclProject tmpProj : disclProjects) {
            if (tmpProj.getDisclosureDispositionCode() == null || dispositionStatus > tmpProj.getDisclosureDispositionCode()) {
                tmpProj.setDisclosureDispositionCode(dispositionStatus);
            }
        }      
    }    
    
    /**
     * 
     * This method is called to ensure that any approved manual
     * @param masterCoiDisclosure
     * @param updateDisclosure
     */
    private void syncCollections(CoiDisclosure masterCoiDisclosure, CoiDisclosure updateDisclosure) {
        syncDisclosureProjects(masterCoiDisclosure, updateDisclosure);
        syncDisclosureNotepads(masterCoiDisclosure, updateDisclosure);
        syncDisclosureAttachments(masterCoiDisclosure, updateDisclosure);
        syncDisclosureQuestionnaire(masterCoiDisclosure, updateDisclosure);        
    }
    
    private void syncDisclosureProjects(CoiDisclosure masterCoiDisclosure, CoiDisclosure updateDisclosure) {
 
        //Lets see the map with the update items first...
        Map<String, CoiDisclProject> masterProjectMap = new HashMap<String, CoiDisclProject>();
        for (CoiDisclProject updateDisclProject : updateDisclosure.getCoiDisclProjects()) {
            masterProjectMap.put(getCoiDisclProjectMapKey(updateDisclProject),updateDisclProject);
        }
        
        //Lets put the current master list of discl projects into a map for easier processing
        for (CoiDisclProject masterDisclProject : masterCoiDisclosure.getCoiDisclProjects()) {
            if (!masterProjectMap.containsKey(getCoiDisclProjectMapKey(masterDisclProject))) {
                CoiDisclProject copiedDisclProject = (CoiDisclProject) ObjectUtils.deepCopy(masterDisclProject);
                copiedDisclProject.setSequenceNumber(updateDisclosure.getSequenceNumber());
                copiedDisclProject.setCoiDisclProjectsId(null);

                // copy disc details
                copiedDisclProject.setCoiDisclosureId(updateDisclosure.getCoiDisclosureId());                
                copyDisclosureDetails(masterDisclProject.getCoiDiscDetails(), copiedDisclProject);
                masterProjectMap.put(getCoiDisclProjectMapKey(masterDisclProject), copiedDisclProject);                
            }
        }
        
        //Now lets convert the entry set to a list and set it on the update disclosure
        List<CoiDisclProject> mergedProjectList = new ArrayList<CoiDisclProject>();
        mergedProjectList.addAll(masterProjectMap.values());
        
        updateDisclosure.setCoiDisclProjects(mergedProjectList);

    }
    
    private String getCoiDisclProjectMapKey(CoiDisclProject disclProject) {
        return disclProject.getCoiProjectId() + "^" + disclProject.getModuleItemKey() + "^" + disclProject.getDisclosureEventType();
    }
    
    private void syncDisclosureNotepads(CoiDisclosure masterCoiDisclosure, CoiDisclosure updateDisclosure) {
        List<CoiDisclosureNotepad> mergedNotepadList = new ArrayList<CoiDisclosureNotepad>();
        
        //Lets add the original disclosure id from notes from a previous disclosure that are found in 
        //the update to a set to check against later
        Set<Long> origDisclIds = new HashSet<Long>();
        for (CoiDisclosureNotepad updateNotepad : updateDisclosure.getCoiDisclosureNotepads()) {
            if (updateNotepad.getOriginalCoiDisclosureId() != null) {
                origDisclIds.add(updateNotepad.getOriginalCoiDisclosureId());
            }
            
            //All notes from the update are guaranteed to be the latest notes
            mergedNotepadList.add(updateNotepad);
        }
        
        //Now lets iterate over the notepads in the master, any original disclosure ids that are not
        //found in the update set or if they are null (attached to the current master) we need to 
        //add them to the list
        for (CoiDisclosureNotepad masterNotepad : masterCoiDisclosure.getCoiDisclosureNotepads()) {
            if (masterNotepad.getOriginalCoiDisclosureId() == null ||
                !origDisclIds.contains(masterNotepad.getOriginalCoiDisclosureId())) {
                CoiDisclosureNotepad copiedCoiDisclosureNotepad = (CoiDisclosureNotepad) ObjectUtils.deepCopy(masterNotepad);
                copiedCoiDisclosureNotepad.setSequenceNumber(updateDisclosure.getSequenceNumber());
                copiedCoiDisclosureNotepad.setId(null);
                if (copiedCoiDisclosureNotepad.getOriginalCoiDisclosureId() == null) {
                    copiedCoiDisclosureNotepad.setOriginalCoiDisclosureId(masterCoiDisclosure.getCoiDisclosureId());
                }                
                mergedNotepadList.add(copiedCoiDisclosureNotepad);
            }
        }
        
        //Lets set our merged list in the update
        updateDisclosure.setCoiDisclosureNotepads(mergedNotepadList);       
    }
    
    private void syncDisclosureAttachments(CoiDisclosure masterCoiDisclosure, CoiDisclosure updateDisclosure) {
        List<CoiDisclosureAttachment> mergedAttachmentList = new ArrayList<CoiDisclosureAttachment>();
        
        //Lets add the original disclosure id from attachments from a previous disclosure that are found in 
        //the update to a set to check against later
        Set<Long> origDisclIds = new HashSet<Long>();
        for (CoiDisclosureAttachment updateAttachment : updateDisclosure.getCoiDisclosureAttachments()) {
            if (updateAttachment.getOriginalCoiDisclosureId() != null) {
                origDisclIds.add(updateAttachment.getOriginalCoiDisclosureId());
            }
            
            //All attachments from the update are guaranteed to be the latest attachments
            mergedAttachmentList.add(updateAttachment);
        }
        
        //Now lets iterate over the attachments in the master, any original disclosure ids that are not
        //found in the update set or if they are null (attached to the current master) we need to 
        //add them to the list
        for (CoiDisclosureAttachment masterAttachment : masterCoiDisclosure.getCoiDisclosureAttachments()) {
            if (masterAttachment.getOriginalCoiDisclosureId() == null ||
                !origDisclIds.contains(masterAttachment.getOriginalCoiDisclosureId())) {
                CoiDisclosureAttachment copiedCoiDisclosureAttachment = (CoiDisclosureAttachment) ObjectUtils.deepCopy(masterAttachment);
                copiedCoiDisclosureAttachment.setSequenceNumber(updateDisclosure.getSequenceNumber());
                copiedCoiDisclosureAttachment.setAttachmentId(null);
                copiedCoiDisclosureAttachment.setFile(masterAttachment.getFile());
                copiedCoiDisclosureAttachment.setFileId(masterAttachment.getFileId());
                if (copiedCoiDisclosureAttachment.getOriginalCoiDisclosureId() == null) {
                    copiedCoiDisclosureAttachment.setOriginalCoiDisclosureId(masterCoiDisclosure.getCoiDisclosureId());
                }                
                mergedAttachmentList.add(copiedCoiDisclosureAttachment);
            }
        }
        
        //Lets set our merged list in the update
        updateDisclosure.setCoiDisclosureAttachments(mergedAttachmentList);           
    }
    
    private void syncDisclosureQuestionnaire(CoiDisclosure masterCoiDisclosure, CoiDisclosure updateDisclosure) {
        List<AnswerHeader> updateAnswerHeaders = retrieveAnswerHeaders(updateDisclosure);
        List<AnswerHeader> masterAnswerHeaders = retrieveAnswerHeaders(masterCoiDisclosure);
        
        for (AnswerHeader masterHeader : masterAnswerHeaders) {
            boolean existsAlready = false;
            for (AnswerHeader updateHeader : updateAnswerHeaders) {
                if (StringUtils.equals(updateHeader.getModuleSubItemCode(), masterHeader.getModuleSubItemCode())
                        && StringUtils.equals(updateHeader.getModuleSubItemKey(), masterHeader.getModuleSubItemKey())
                        && updateHeader.getQuestionnaireId().equals(masterHeader.getQuestionnaireId())) {
                    existsAlready = true;
                }
            }
            if (!existsAlready) {
                AnswerHeader copiedAnswerHeader = (AnswerHeader) ObjectUtils.deepCopy(masterHeader);
                copiedAnswerHeader.setId(null);
                copiedAnswerHeader.setModuleItemKey(updateDisclosure.getCoiDisclosureId().toString());
                for (Answer answer : copiedAnswerHeader.getAnswers()) {
                    answer.setId(null);
                }
                updateAnswerHeaders.add(copiedAnswerHeader);
            }
        }
        
        businessObjectService.save(updateAnswerHeaders);       
    }

    public ActionForward sendNotification(ActionMapping mapping, ActionForm form, ActionForward forward, 
            CoiUserRole coiUserRole, String actionTaken) {
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm)form;
        CoiDisclosureDocument coiDisclosureDocument = coiDisclosureForm.getCoiDisclosureDocument();
        CoiNotificationContext context = new CoiNotificationContext(coiDisclosureDocument.getCoiDisclosure(), 
            CoiActionType.ASSIGN_REVIEWER, "Assign Reviewer", new AssignReviewerNotificationRenderer(coiDisclosureDocument.getCoiDisclosure(), actionTaken));

        NotificationHelper<CoiNotificationContext> coiNotificationHelper = coiDisclosureForm.getNotificationHelper();
        coiNotificationHelper.initializeDefaultValues(context);
        NotificationTypeRecipient notificationRecipient = new NotificationTypeRecipient();
        notificationRecipient.setPersonId(coiUserRole.getPerson().getPersonId());
        
        coiNotificationHelper.getNotificationRecipients().add(notificationRecipient);

        if (coiNotificationHelper.getPromptUserForNotificationEditor(context)) {
            forward = mapping.findForward("coiDisclosureNotificationEditor");
        } else {
            getKcNotificationService().sendNotificationAndPersist(context, new CoiNotification(), coiNotificationHelper.getNotificationRecipients(), coiDisclosureDocument.getCoiDisclosure());
        }
        return forward;
    }

    public void sendNotification(CoiDisclosure disclosure, String actionType, String actionTaken) {
        CoiNotificationContext context = new CoiNotificationContext(disclosure, actionType, actionTaken, new CoiNotificationRenderer(disclosure));
        getKcNotificationService().sendNotificationAndPersist(context, new CoiNotification(), disclosure);
    }
    
    protected void setDisclosureReviewStatus(CoiDisclosure disclosure, String reviewStatusCode) {
        disclosure.setReviewStatusCode(reviewStatusCode);
        disclosure.refreshReferenceObject("coiReviewStatus");
    }

    protected void resetDisclosureReviewStatus(CoiDisclosure disclosure) {
        if(disclosure.getCoiUserRoles().size() == 0) {
            if(StringUtils.equals(disclosure.getCoiDisclosureStatus().getCoiDisclosureStatusCode(), CoiDisclosureStatus.ROUTED_FOR_REVIEW)) {
                setDisclosureReviewStatus(disclosure, CoiReviewStatus.SUBMITTED_FOR_REVIEW);
            }else {
                setDisclosureReviewStatus(disclosure, CoiReviewStatus.IN_PROGRESS);
            }
        }
    }

    public void tagUserRolesToCompleteReview(List<CoiUserRole> completeUserRoles) {
        String loggedInUser = GlobalVariables.getUserSession().getPerson().getPrincipalId();
        for(CoiUserRole coiUserRole : completeUserRoles) {
            if(!coiUserRole.getPerson().getPersonId().equals(loggedInUser)) {
                coiUserRole.setMarkedToCompleteReview(false);
            }
        }
    }

    public void completeCoiReview(CoiDisclosure disclosure) {
        List<CoiUserRole> coiUserRoles = disclosure.getCoiUserRoles();
        String reviewStatus = CoiReviewStatus.ASSIGNED_REVIEW_COMPLETE;
        for(CoiUserRole coiUserRole : coiUserRoles) {
            if(coiUserRole.isMarkedToCompleteReview()) {
                String oldCoiRecomendedTypeCode = coiUserRole.getOldCoiRecomendedTypeCode() == null  ? "" : coiUserRole.getOldCoiRecomendedTypeCode();
                String newCoiRecomendedTypeCode = coiUserRole.getCoiRecomendedTypeCode() == null  ? "" : coiUserRole.getCoiRecomendedTypeCode();
                if(!oldCoiRecomendedTypeCode.equals(newCoiRecomendedTypeCode)) {
                    if(ObjectUtils.isNotNull(coiUserRole.getCoiRecomendedTypeCode())) {
                        coiUserRole.setReviewCompleted(true);
                        coiUserRole.setDateCompleted(new Date(System.currentTimeMillis()));
                    }else {
                        coiUserRole.setReviewCompleted(false);
                        coiUserRole.setDateCompleted(null);
                    }
                    coiUserRole.setEditable(true);
                }
                for (CoiDisclProject project: disclosure.getCoiDisclProjects()) {
                    for (CoiDiscDetail detail: project.getCoiDiscDetails()) {
                        if (org.apache.commons.lang3.ObjectUtils.equals(detail.getOldEntityDispositionCode(), detail.getEntityDispositionCode())) {
                            detail.setUpdateUser(GlobalVariables.getUserSession().getPerson().getPrincipalName());
                            businessObjectService.save(detail);
                            detail.setOldEntityDispositionCode();
                        }
                    }
                }
            }
            if (coiUserRole.isReviewCompleted()) {
                sendNotification(disclosure, CoiActionType.REVIEW_COMPLETE_EVENT, "Review Completed");        
            } else {
                reviewStatus = CoiReviewStatus.ASSIGNED_TO_REVIEWER;
            }
        }
        setDisclosureReviewStatus(disclosure, reviewStatus);
        businessObjectService.save(disclosure);
    }

    public void updateDisclosureReviewStatus(CoiDisclosure coiDisclosure) {
        businessObjectService.save(coiDisclosure);
        coiDisclosure.refreshReferenceObject("coiReviewStatus");
    }
    
    public boolean isDisclosureReviewComplete(List<CoiUserRole> completeUserRoles) {
        boolean allReviewsComplete = true;
        for(CoiUserRole coiUserRole : completeUserRoles) {
            if(!coiUserRole.isReviewCompleted()) {
                allReviewsComplete = false;
                break;
            }
        }
        return allReviewsComplete;
    }   
    
}
