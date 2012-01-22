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
package org.kuali.kra.irb.onlinereview;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.actions.reviewcomments.ReviewAttachmentsBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsBean;
import org.kuali.kra.irb.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.DocumentHelperService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.PessimisticLockService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;


public class OnlineReviewsActionHelper implements Serializable {

    public static final String REVIEWER_COMMENTS_MAP_KEY = "reviewerComments";
    public static final String REVIEWER_ATTACHMENTS_MAP_KEY = "reviewerAttachments";
    public static final String DOCUMENT_MAP_KEY = "document";
    //public static final String REVIEWER_PERSON_MAP_KEY = "reviewerPerson";
    public static final String FORM_MAP_KEY = "kualiForm";
    
    private static final long serialVersionUID = 1L;
    private ProtocolForm form;
    
    //new reviewer data
    private Long newProtocolReviewCommitteeMembershipId;
    private String newReviewerTypeCode;
    private Date newReviewDateRequested;
    private Date newReviewDateDue;
    private String newReviewDocumentDescription;
    private String newReviewExplanation;
    private String newReviewOrganizationDocumentNumber;
    
    
    private Map<String,Map<String,Object>> documentHelperMap;
    private List<ProtocolOnlineReviewDocument> protocolOnlineReviewDocuments;
    private List<ReviewCommentsBean> reviewCommentsBeans;
    private List<ReviewAttachmentsBean> reviewAttachmentsBeans;
    //private List<Object> reviewerPersons;
    private boolean initComplete = false;

    private transient KcPersonService kcPersonService;
    
    private static org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(OnlineReviewsActionHelper.class);
    private static final String REVIEW_DOCUMENT_DESCRIPTION_FORMAT = "Review Protocol:%s, PI:%s";
    private boolean hideReviewerName;
    private boolean hideReviewerNameForAttachment;
    
    /**
     * Constructs a OnlineReviewActionHelper.java.
     * @param form
     */
    public OnlineReviewsActionHelper(ProtocolForm form) {
        this.form = form;
        this.newReviewDateRequested = new Date((new java.util.Date()).getTime());
        
        init(false);
    }
    
    public void init(boolean force) {
        if (!initComplete || force) {
            ProtocolSubmission currentSubmission = form.getProtocolDocument().getProtocol().getProtocolSubmission();
            if (currentSubmission != null) {
                ProtocolDocument protocolDocument = form.getProtocolDocument();
                ProtocolPerson principalInvestigator = protocolDocument.getProtocol().getPrincipalInvestigator();
                
                this.newReviewDateRequested = new Date((new java.util.Date()).getTime());
                this.protocolOnlineReviewDocuments = getProtocolOnlineReviewService()
                .getProtocolReviewDocumentsForCurrentSubmission(protocolDocument.getProtocol()); 
                this.reviewCommentsBeans = new ArrayList<ReviewCommentsBean>();
                this.reviewAttachmentsBeans = new ArrayList<ReviewAttachmentsBean>();
                this.documentHelperMap = new LinkedHashMap<String,Map<String,Object>>();

                if (principalInvestigator != null ) {
                    String piLastName = principalInvestigator.getLastName();
                    this.newReviewDocumentDescription = getProtocolOnlineReviewService().getProtocolOnlineReviewDocumentDescription(protocolDocument.getProtocol().getProtocolNumber(),piLastName);
                } 
                List<String> reviewerIds = getReviewerIds();
                for (ProtocolOnlineReviewDocument pDoc : protocolOnlineReviewDocuments) {
                    Map<String,Object> pDocMap = new LinkedHashMap<String,Object>();
                    documentHelperMap.put(pDoc.getDocumentNumber(), pDocMap);
                    pDocMap.put(DOCUMENT_MAP_KEY, pDoc);
                    ProtocolOnlineReviewForm poForm;
                    try {
                        poForm = new ProtocolOnlineReviewForm();
                        poForm.setDocument(pDoc);
                        populateAuthorizationFields(poForm,pDoc);
                        pDocMap.put(FORM_MAP_KEY, poForm);
                    }
                    catch (Exception e) {
                        LOG.error(String.format("Exception generated creating new instance of ProtocolOnlineReviewForm with document %s",pDoc.getDocumentNumber()),e);
                        throw new RuntimeException(String.format("Exception generated creating new instance of ProtocolOnlineReviewForm with document %s",pDoc.getDocumentNumber()),e);
                    }
                   
                    ReviewCommentsBean commentsBean = new ReviewCommentsBean(Constants.EMPTY_STRING);
                    commentsBean.setReviewComments(pDoc.getProtocolOnlineReview().getCommitteeScheduleMinutes());
                  //  commentsBean.setHideReviewerName(getReviewerCommentsService().setHideReviewerName(commentsBean.getReviewComments()));
                    pDocMap.put(REVIEWER_COMMENTS_MAP_KEY, commentsBean);
                    reviewCommentsBeans.add(commentsBean);
                    
                    ReviewAttachmentsBean attachmentsBean = new ReviewAttachmentsBean("onlineReviewsActionHelper");
                    attachmentsBean.setReviewAttachments(pDoc.getProtocolOnlineReview().getReviewAttachments());
                  //  commentsBean.setHideReviewerName(getReviewerCommentsService().setHideReviewerName(commentsBean.getReviewComments()));
                    pDocMap.put(REVIEWER_ATTACHMENTS_MAP_KEY, attachmentsBean);
                    reviewAttachmentsBeans.add(attachmentsBean);
                }
                initComplete = true;
            }
            
        }
        for (ReviewCommentsBean commentsBean : reviewCommentsBeans) {
            commentsBean.setHideReviewerName(getReviewerCommentsService().setHideReviewerName(commentsBean.getReviewComments()));            
        }
        for (ReviewAttachmentsBean attachmentsBean : reviewAttachmentsBeans) {
            attachmentsBean.setHideReviewerName(getReviewerCommentsService().setHideReviewerName(attachmentsBean.getReviewAttachments()));            
        }
 
    }
    
    private List<String> getReviewerIds() {
        List<String> reviewerIds = new ArrayList<String>();
        for (ProtocolOnlineReviewDocument pDoc : protocolOnlineReviewDocuments) {
            reviewerIds.add(pDoc.getProtocolOnlineReview().getProtocolReviewer().getPersonId());
        }
        return reviewerIds;

    }
    
    /**
     * This method...
     * @return
     */
    public List<ProtocolOnlineReview> getCurrentProtocolOnlineReviews() {
        List<ProtocolOnlineReview> reviews = new ArrayList<ProtocolOnlineReview>();
        for (Iterator<Map<String,Object>> it = documentHelperMap.values().iterator(); it.hasNext();) {
            reviews.add(((ProtocolOnlineReviewDocument)((it.next()).get("document"))).getProtocolOnlineReview());
        }
        return reviews;
    }
    
    /**
     * This method...
     * @return
     */
    public List<CommitteeMembership> getAvailableCommitteeMembersForCurrentSubmission() {
        List<CommitteeMembership> members = getProtocolOnlineReviewService()
                                            .getAvailableCommitteeMembersForCurrentSubmission(form.getProtocolDocument().getProtocol());
        return members;
    }
    
    /**
     * This method...
     * @return
     */
    public List<ProtocolOnlineReviewDocument> getProtocolOnlineReviewsForCurrentSubmission() {
        return protocolOnlineReviewDocuments;
    }
    
    /**
     * This method...
     * @return
     */
//    public List<ProtocolOnlineReviewForm> getProtocolOnlineReviewFormsForCurrentSubmission() throws Exception {
//        return protocolOnlineReviewForms;
//    }

    /**
     * Gets the newReviewOrganizationDocumentNumber attribute. 
     * @return Returns the newReviewOrganizationDocumentNumber.
     */
    public String getNewReviewOrganizationDocumentNumber() {
        return newReviewOrganizationDocumentNumber;
    }

    /**
     * Sets the newReviewOrganizationDocumentNumber attribute value.
     * @param newReviewOrganizationDocumentNumber The newReviewOrganizationDocumentNumber to set.
     */
    public void setNewReviewOrganizationDocumentNumber(String newReviewOrganizationDocumentNumber) {
        this.newReviewOrganizationDocumentNumber = newReviewOrganizationDocumentNumber;
    }
    

    /**
     * Gets the newReviewDateRequested attribute. 
     * @return Returns the newReviewDateRequested.
     */
    public java.sql.Date getNewReviewDateRequested() {
        return newReviewDateRequested;
    }

    /**
     * Sets the newReviewDateRequested attribute value.
     * @param newReviewDateRequested The newReviewDateRequested to set.
     */
    public void setNewReviewDateRequested(java.sql.Date newReviewDateRequested) {
        this.newReviewDateRequested = newReviewDateRequested;
    }

    /**
     * Gets the newReviewDateDue attribute. 
     * @return Returns the newReviewDateDue.
     */
    public java.sql.Date getNewReviewDateDue() {
        return newReviewDateDue;
    }

    /**
     * Sets the newReviewDateDue attribute value.
     * @param newReviewDateDue The newReviewDateDue to set.
     */
    public void setNewReviewDateDue(java.sql.Date newReviewDateDue) {
        this.newReviewDateDue = newReviewDateDue;
    }

    /**
     * Gets the newReviewerTypeCode attribute. 
     * @return Returns the newReviewerTypeCode.
     */
    public String getNewReviewerTypeCode() {
        return newReviewerTypeCode;
    }

    /**
     * Sets the newReviewerTypeCode attribute value.
     * @param newReviewerTypeCode The newReviewerTypeCode to set.
     */
    public void setNewReviewerTypeCode(String newReviewerTypeCode) {
        this.newReviewerTypeCode = newReviewerTypeCode;
    }

    /**
     * Gets the newReviewDocumentDescription attribute. 
     * @return Returns the newReviewDocumentDescription.
     */
    public String getNewReviewDocumentDescription() {
        return newReviewDocumentDescription;
    }

    /**
     * Sets the newReviewDocumentDescription attribute value.
     * @param newReviewDocumentDescription The newReviewDocumentDescription to set.
     */
    public void setNewReviewDocumentDescription(String newReviewDocumentDescription) {
        this.newReviewDocumentDescription = newReviewDocumentDescription;
    }
    
    /**
     * Gets the newReviewExplanation attribute. 
     * @return Returns the newReviewExplanation.
     */
    public String getNewReviewExplanation() {
        return newReviewExplanation;
    }

    /**
     * Sets the newReviewExplanation attribute value.
     * @param newReviewExplanation The newReviewExplanation to set.
     */
    public void setNewReviewExplanation(String newReviewExplanation) {
        this.newReviewExplanation = newReviewExplanation;
    }

    /**
     * Gets the protocolOnlineReviewDocuments attribute. 
     * @return Returns the protocolOnlineReviewDocuments.
     */
    public List<ProtocolOnlineReviewDocument> getProtocolOnlineReviewDocuments() {
        return protocolOnlineReviewDocuments;
    }

    /**
     * Sets the protocolOnlineReviewDocuments attribute value.
     * @param protocolOnlineReviewDocuments The protocolOnlineReviewDocuments to set.
     */
    public void setProtocolOnlineReviewDocuments(List<ProtocolOnlineReviewDocument> protocolOnlineReviewDocuments) {
        this.protocolOnlineReviewDocuments = protocolOnlineReviewDocuments;
    }

    private static ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return KraServiceLocator.getService(ProtocolOnlineReviewService.class);
    }

    /**
     * Gets the newProtocolReviewPersonId attribute. 
     * @return Returns the newProtocolReviewPersonId.
     */
    public Long getNewProtocolReviewCommitteeMembershipId() {
        return newProtocolReviewCommitteeMembershipId;
    }

    /**
     * Sets the newProtocolReviewPersonId attribute value.
     * @param newProtocolReviewPersonId The newProtocolReviewPersonId to set.
     */
    public void setNewProtocolReviewCommitteeMembershipId(Long newProtocolReviewCommitteeMembershipId) {
        this.newProtocolReviewCommitteeMembershipId = newProtocolReviewCommitteeMembershipId;
    }

    /**
     * Gets the reviewerComments attribute. 
     * @return Returns the reviewerComments.
     */
    public List<ReviewCommentsBean> getReviewCommentsBeans() {
        return reviewCommentsBeans;
    }

    /**
     * Sets the reviewerComments attribute value.
     * @param reviewerComments The reviewerComments to set.
     */
    public void setReviewCommentsBeans(List<ReviewCommentsBean> reviewCommentsBeans) {
        this.reviewCommentsBeans = reviewCommentsBeans;
    }

    
    /**
     * Gets the documentHelperMap attribute. 
     * @return Returns the documentHelperMap.
     */
    public Map<String, Map<String, Object>> getDocumentHelperMap() {
        init(false);
        return documentHelperMap;
    }

    /**
     * Sets the documentHelperMap attribute value.
     * @param documentHelperMap The documentHelperMap to set.
     */
    public void setDocumentHelperMap(Map<String, Map<String, Object>> documentHelperMap) {
        this.documentHelperMap = documentHelperMap;
    }
    
    public Map<String,Object> getHelperMapByDocumentNumber(String documentNumber) {
        Map<String,Object> helperMap = documentHelperMap.get(documentNumber);
        if (helperMap==null) {
            throw new IllegalArgumentException(String.format("Document %s does not exist in the helper map.", documentNumber));
        }
        return helperMap;
    }
   
    public long getIndexByDocumentNumber(String documentNumber) {
        ProtocolOnlineReviewDocument document = getDocumentFromHelperMap(documentNumber);
        return protocolOnlineReviewDocuments.indexOf(document);
    }
    
    public ProtocolOnlineReviewDocument getDocumentFromHelperMap(String documentNumber) {
        ProtocolOnlineReviewDocument protocolDocument = (ProtocolOnlineReviewDocument)getHelperMapByDocumentNumber(documentNumber).get(DOCUMENT_MAP_KEY);
        if (protocolDocument==null) {
            throw new IllegalStateException(String.format("Document %s was not stored in the helper map.", documentNumber));
        }
        if (protocolDocument.getProtocolOnlineReview().getDateRequested() == null) {
            protocolDocument.getProtocolOnlineReview().setDateRequested(new Date((new java.util.Date()).getTime()));
        }
        return protocolDocument;
    }
    
    public ReviewCommentsBean getReviewCommentsBeanFromHelperMap(String documentNumber) {
        ReviewCommentsBean bean = (ReviewCommentsBean) getHelperMapByDocumentNumber(documentNumber).get(REVIEWER_COMMENTS_MAP_KEY);
        if (bean == null) {
            throw new IllegalStateException(String.format("ReviewerCommentsBean for document %s was not stored in the helper map.", documentNumber));
        }
        return bean;
    }

    public ReviewAttachmentsBean getReviewAttachmentsBeanFromHelperMap(String documentNumber) {
        ReviewAttachmentsBean bean = (ReviewAttachmentsBean) getHelperMapByDocumentNumber(documentNumber).get(REVIEWER_ATTACHMENTS_MAP_KEY);
        if (bean == null) {
            throw new IllegalStateException(String.format("ReviewAttachmentsBean for document %s was not stored in the helper map.", documentNumber));
        }
        return bean;
    }

    public int getDocumentIndexByReviewer(String personId, boolean nonEmployeeFlag) {
        int idx = 0;
        for (ProtocolOnlineReviewDocument reviewDocument : protocolOnlineReviewDocuments ) {
            ProtocolReviewer reviewer = reviewDocument.getProtocolOnlineReview().getProtocolReviewer();
            if (reviewer.isPersonIdProtocolReviewer(personId,nonEmployeeFlag)) 
            {
                break;
            }
            idx++;
        }
        return idx;
    }
    
    public String getDocumentNumberByReviewer(String personId,boolean nonEmployeeFlag) {
        int idx = getDocumentIndexByReviewer(personId,nonEmployeeFlag);
        return protocolOnlineReviewDocuments.get(idx).getDocumentNumber();
    }
    
    public ProtocolOnlineReviewDocument getDocumentByReviewer(String personId,boolean nonEmployeeFlag) {
        return getDocumentFromHelperMap(getDocumentNumberByReviewer(personId,nonEmployeeFlag));
    }
    
    public String getDocumentNumberForCurrentUser() {
        return getDocumentNumberByReviewer(GlobalVariables.getUserSession().getPrincipalId(),false);
    }
    
    public ProtocolOnlineReviewDocument getDocumentForCurrentUser() {
        return getDocumentByReviewer(GlobalVariables.getUserSession().getPrincipalId(),false);
    }
    
    public int getDocumentIndexForCurrentUser() {
        return getDocumentIndexByReviewer(GlobalVariables.getUserSession().getPrincipalId(),false);
    }
    
    private KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }
    
    private DocumentHelperService getDocumentHelperService() {
        return KraServiceLocator.getService(DocumentHelperService.class);
    }
    
    private DataDictionaryService getDataDictionaryService() {
        return KraServiceLocator.getService(DataDictionaryService.class);
    }
    
    private boolean requiresLock(Document document) {
        return getDataDictionaryService().getDataDictionary().getDocumentEntry(document.getClass().getName()).getUsePessimisticLocking();
    }
    
    protected Map convertSetToMap(Set s){
        Map map = new HashMap();
        Iterator i = s.iterator();
        while(i.hasNext()) {
            Object key = i.next();
           map.put(key,KRADConstants.KUALI_DEFAULT_TRUE_VALUE);
        }
        return map;
    }
    
    private PessimisticLockService getPessimisticLockService() {
        return KraServiceLocator.getService(PessimisticLockService.class);
    }
    
    private RolodexService getRolodexService() {
        return KraServiceLocator.getService(RolodexService.class);
    }
    
    protected void populateAuthorizationFields(ProtocolOnlineReviewForm form, ProtocolOnlineReviewDocument document) {
        if (form.isFormDocumentInitialized()) {
            
            Person user = GlobalVariables.getUserSession().getPerson();
            KcTransactionalDocumentAuthorizerBase documentAuthorizer = (KcTransactionalDocumentAuthorizerBase) getDocumentHelperService().getDocumentAuthorizer(document);
            Set<String> editModes = new HashSet<String>();
            
            form.setupLockRegions();     
            String activeLockRegion = (String)GlobalVariables.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
            
            if (!documentAuthorizer.canOpen(document, user)) {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            }
            else {
                document.setViewOnly(form.isViewOnly());
                
                /*
                 * Documents that require a pessimistic lock need to be treated differently.  If a user
                 * can edit the document, they need to obtain the lock, but it is possible that another
                 * user already has the lock.  So, we try to get the lock using FULL_ENTRY.  If the
                 * edit mode is downgraded to VIEW_ONLY, we flag the document as such.
                 */
                //disabling for right now.
                if(false) {
                // if (requiresLock(document) && documentAuthorizer.canEdit(document, user)) {
                    editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
                            
                    Map<String, String> editMode = convertSetToMap(editModes);
                    
                    //Check the locks held by the user - detect user's navigation away from one lock region to another
                    //refresh locks as stale ones can exist in the document due to it being in the form
                    document.refreshPessimisticLocks();
                    for(PessimisticLock lock: document.getPessimisticLocks()) {
                        if(StringUtils.isNotEmpty(lock.getLockDescriptor()) && StringUtils.isNotEmpty(activeLockRegion) && !lock.getLockDescriptor().contains(activeLockRegion)) {
                            getPessimisticLockService().releaseAllLocksForUser(document.getPessimisticLocks(), user, lock.getLockDescriptor());
                        }
                    }
                    editMode = getPessimisticLockService().establishLocks(document, editMode, user);
                    //ensure locks are current
                    document.refreshPessimisticLocks();
                     
                    //Task Authorizers should key off the document viewonly flag to determine
                    //if the document is available for writing or if its locked.
                    if (editMode.containsKey(AuthorizationConstants.EditMode.VIEW_ONLY)) {
                        document.setViewOnly(true);
                        //if budget document we need to set the parent document view only as well for authorization consistency.
            
                    }
                }
                editModes = documentAuthorizer.getEditModes(document, user, null);
                Set<String> documentActions = documentAuthorizer.getDocumentActions(document, user, null);
                
                form.setDocumentActions(convertSetToMap(documentActions));
            }
            form.setEditingMode(convertSetToMap(editModes));
        }
    }

    private ReviewCommentsService getReviewerCommentsService() {
        return KraServiceLocator.getService(ReviewCommentsService.class);
    }

    public boolean isHideReviewerName() {
        return hideReviewerName;
    }

    public void setHideReviewerName(boolean hideReviewerName) {
        this.hideReviewerName = hideReviewerName;
    }

    public List<ReviewAttachmentsBean> getReviewAttachmentsBeans() {
        return reviewAttachmentsBeans;
    }

    public void setReviewAttachmentsBeans(List<ReviewAttachmentsBean> reviewAttachmentsBeans) {
        this.reviewAttachmentsBeans = reviewAttachmentsBeans;
    }

    public boolean isHideReviewerNameForAttachment() {
        return hideReviewerNameForAttachment;
    }

    public void setHideReviewerNameForAttachment(boolean hideReviewerNameForAttachment) {
        this.hideReviewerNameForAttachment = hideReviewerNameForAttachment;
    }
    
}

