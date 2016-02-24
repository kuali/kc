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
package org.kuali.kra.protocol.onlinereview;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewAttachmentsBeanBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBeanBase;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsService;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewer;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.service.DocumentHelperService;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.service.PessimisticLockService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.io.Serializable;
import java.sql.Date;
import java.util.*;


public abstract class OnlineReviewsActionHelperBase implements Serializable {

    public static final String REVIEWER_COMMENTS_MAP_KEY = "reviewerComments";
    public static final String REVIEWER_ATTACHMENTS_MAP_KEY = "reviewerAttachments";
    public static final String DOCUMENT_MAP_KEY = "document";
    public static final String FORM_MAP_KEY = "kualiForm";
    
    private static final long serialVersionUID = 1L;
    private ProtocolFormBase form;
    
    //new reviewer data
    private Long newProtocolReviewCommitteeMembershipId;
    private String newReviewerTypeCode;
    private Date newReviewDateRequested;
    private Date newReviewDateDue;
    private String newReviewDocumentDescription;
    private String newReviewExplanation;
    private String newReviewOrganizationDocumentNumber;
    
    
    private Map<String,Map<String,Object>> documentHelperMap;
    private List<ProtocolOnlineReviewDocumentBase> protocolOnlineReviewDocuments;
    private List<ReviewCommentsBeanBase> reviewCommentsBeans;
    private List<ReviewAttachmentsBeanBase> reviewAttachmentsBeans;
    private boolean initComplete = false;


    private static org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(OnlineReviewsActionHelperBase.class);
    private boolean hideReviewerName;
    private boolean hideReviewerNameForAttachment;
    
    /**
     * Constructs a OnlineReviewActionHelper.java.
     * @param form
     */
    public OnlineReviewsActionHelperBase(ProtocolFormBase form) {
        this.form = form;
        this.newReviewDateRequested = new Date((new java.util.Date()).getTime());
        
        init(false);
    }
    
    public void init(boolean force) {
        if (!initComplete || force) {
            ProtocolSubmissionBase currentSubmission = form.getProtocolDocument().getProtocol().getProtocolSubmission();
            if (currentSubmission != null) {
                ProtocolDocumentBase protocolDocument = form.getProtocolDocument();
                ProtocolPersonBase principalInvestigator = protocolDocument.getProtocol().getPrincipalInvestigator();
                
                this.newReviewDateRequested = new Date((new java.util.Date()).getTime());
                this.protocolOnlineReviewDocuments = getProtocolOnlineReviewService()
                .getProtocolReviewDocumentsForCurrentSubmission(protocolDocument.getProtocol()); 
                this.reviewCommentsBeans = new ArrayList<ReviewCommentsBeanBase>();
                this.reviewAttachmentsBeans = new ArrayList<ReviewAttachmentsBeanBase>();
                this.documentHelperMap = new LinkedHashMap<String,Map<String,Object>>();

                if (principalInvestigator != null ) {
                    String piLastName = principalInvestigator.getLastName();
                    this.newReviewDocumentDescription = getProtocolOnlineReviewService().getProtocolOnlineReviewDocumentDescription(protocolDocument.getProtocol().getProtocolNumber(),piLastName);
                } 
                List<String> reviewerIds = getReviewerIds();
                for (ProtocolOnlineReviewDocumentBase pDoc : protocolOnlineReviewDocuments) {
                    Map<String,Object> pDocMap = new LinkedHashMap<String,Object>();
                    documentHelperMap.put(pDoc.getDocumentNumber(), pDocMap);
                    pDocMap.put(DOCUMENT_MAP_KEY, pDoc);
                    ProtocolOnlineReviewFormBase poForm;
                    try {
                        poForm = getNewProtocolOnlineReviewFormInstanceHook();
                        poForm.setDocument(pDoc);
                        populateAuthorizationFields(poForm,pDoc);
                        pDocMap.put(FORM_MAP_KEY, poForm);
                    }
                    catch (Exception e) {
                        LOG.error(String.format("Exception generated creating new instance of ProtocolOnlineReviewFormBase with document %s",pDoc.getDocumentNumber()),e);
                        throw new RuntimeException(String.format("Exception generated creating new instance of ProtocolOnlineReviewFormBase with document %s",pDoc.getDocumentNumber()),e);
                    }
                   
                    ReviewCommentsBeanBase commentsBean = getNewReviewCommentsBeanInstanceHook(Constants.EMPTY_STRING);
                    commentsBean.setReviewComments(pDoc.getProtocolOnlineReview().getCommitteeScheduleMinutes());
                    pDocMap.put(REVIEWER_COMMENTS_MAP_KEY, commentsBean);
                    reviewCommentsBeans.add(commentsBean);
                    
                    ReviewAttachmentsBeanBase attachmentsBean = getNewReviewAttachmentsBeanHook("onlineReviewsActionHelper");                    
                    attachmentsBean.setReviewAttachments(pDoc.getProtocolOnlineReview().getReviewAttachments());                   
                    pDocMap.put(REVIEWER_ATTACHMENTS_MAP_KEY, attachmentsBean);
                    reviewAttachmentsBeans.add(attachmentsBean);
                }
                initComplete = true;
            }
            
        }

        for (ReviewCommentsBeanBase commentsBean : reviewCommentsBeans) {
            commentsBean.setHideReviewerName(getReviewerCommentsService().setHideReviewerName(commentsBean.getReviewComments()));            
        }
        for (ReviewAttachmentsBeanBase attachmentsBean : reviewAttachmentsBeans) {
            attachmentsBean.setHideReviewerName(getReviewerCommentsService().setHideReviewerName(attachmentsBean.getReviewAttachments()));            
        }
 
    }
    
    protected abstract ProtocolOnlineReviewFormBase getNewProtocolOnlineReviewFormInstanceHook() throws Exception;
    

    protected abstract ReviewAttachmentsBeanBase getNewReviewAttachmentsBeanHook(String errorPropertyKey);

    protected abstract ReviewCommentsBeanBase getNewReviewCommentsBeanInstanceHook(String errorPropertyKey);

    private List<String> getReviewerIds() {
        List<String> reviewerIds = new ArrayList<String>();
        for (ProtocolOnlineReviewDocumentBase pDoc : protocolOnlineReviewDocuments) {
            reviewerIds.add(pDoc.getProtocolOnlineReview().getProtocolReviewer().getPersonId());
        }
        return reviewerIds;

    }
    

    public List<ProtocolOnlineReviewBase> getCurrentProtocolOnlineReviews() {
        List<ProtocolOnlineReviewBase> reviews = new ArrayList<ProtocolOnlineReviewBase>();
        for (Iterator<Map<String,Object>> it = documentHelperMap.values().iterator(); it.hasNext();) {
            reviews.add(((ProtocolOnlineReviewDocumentBase)((it.next()).get("document"))).getProtocolOnlineReview());
        }
        return reviews;
    }
    

    public List<CommitteeMembershipBase> getAvailableCommitteeMembersForCurrentSubmission() {
        List<CommitteeMembershipBase> members = getProtocolOnlineReviewService()
        .getAvailableCommitteeMembersForCurrentSubmission(form.getProtocolDocument().getProtocol());
        return members;
    }
    

    public List<ProtocolOnlineReviewDocumentBase> getProtocolOnlineReviewsForCurrentSubmission() {
        return protocolOnlineReviewDocuments;
    }
    
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
    public List<ProtocolOnlineReviewDocumentBase> getProtocolOnlineReviewDocuments() {
        return protocolOnlineReviewDocuments;
    }

    /**
     * Sets the protocolOnlineReviewDocuments attribute value.
     * @param protocolOnlineReviewDocuments The protocolOnlineReviewDocuments to set.
     */
    public void setProtocolOnlineReviewDocuments(List<ProtocolOnlineReviewDocumentBase> protocolOnlineReviewDocuments) {
        this.protocolOnlineReviewDocuments = protocolOnlineReviewDocuments;
    }

    private ProtocolOnlineReviewService getProtocolOnlineReviewService() {
        return KcServiceLocator.getService(getProtocolOnlineReviewServiceClassHook());
    }
    
    protected abstract Class<? extends ProtocolOnlineReviewService> getProtocolOnlineReviewServiceClassHook();

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
    public List<ReviewCommentsBeanBase> getReviewCommentsBeans() {
        return reviewCommentsBeans;
    }

    /**
     * Sets the reviewerComments attribute value.
     * @param reviewerComments The reviewerComments to set.
     */
    public void setReviewCommentsBeans(List<ReviewCommentsBeanBase> reviewCommentsBeans) {
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
        ProtocolOnlineReviewDocumentBase document = getDocumentFromHelperMap(documentNumber);
        return protocolOnlineReviewDocuments.indexOf(document);
    }
    
    public ProtocolOnlineReviewDocumentBase getDocumentFromHelperMap(String documentNumber) {
        ProtocolOnlineReviewDocumentBase protocolDocument = (ProtocolOnlineReviewDocumentBase)getHelperMapByDocumentNumber(documentNumber).get(DOCUMENT_MAP_KEY);
        if (protocolDocument==null) {
            throw new IllegalStateException(String.format("Document %s was not stored in the helper map.", documentNumber));
        }
        if (protocolDocument.getProtocolOnlineReview().getDateRequested() == null) {
            protocolDocument.getProtocolOnlineReview().setDateRequested(new Date((new java.util.Date()).getTime()));
        }
        return protocolDocument;
    }
    
    public ReviewCommentsBeanBase getReviewCommentsBeanFromHelperMap(String documentNumber) {
        ReviewCommentsBeanBase bean = (ReviewCommentsBeanBase) getHelperMapByDocumentNumber(documentNumber).get(REVIEWER_COMMENTS_MAP_KEY);
        if (bean == null) {
            throw new IllegalStateException(String.format("ReviewerCommentsBean for document %s was not stored in the helper map.", documentNumber));
        }
        return bean;
    }

    public ReviewAttachmentsBeanBase getReviewAttachmentsBeanFromHelperMap(String documentNumber) {
        ReviewAttachmentsBeanBase bean = (ReviewAttachmentsBeanBase) getHelperMapByDocumentNumber(documentNumber).get(REVIEWER_ATTACHMENTS_MAP_KEY);
        if (bean == null) {
            throw new IllegalStateException(String.format("ReviewAttachmentsBeanBase for document %s was not stored in the helper map.", documentNumber));
        }
        return bean;
    }

    public int getDocumentIndexByReviewer(String personId, boolean nonEmployeeFlag) {
        int idx = 0;
        for (ProtocolOnlineReviewDocumentBase reviewDocument : protocolOnlineReviewDocuments ) {
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
    
    public ProtocolOnlineReviewDocumentBase getDocumentByReviewer(String personId,boolean nonEmployeeFlag) {
        return getDocumentFromHelperMap(getDocumentNumberByReviewer(personId,nonEmployeeFlag));
    }
    
    public String getDocumentNumberForCurrentUser() {
        return getDocumentNumberByReviewer(GlobalVariables.getUserSession().getPrincipalId(),false);
    }
    
    public ProtocolOnlineReviewDocumentBase getDocumentForCurrentUser() {
        return getDocumentByReviewer(GlobalVariables.getUserSession().getPrincipalId(),false);
    }
    
    public int getDocumentIndexForCurrentUser() {
        return getDocumentIndexByReviewer(GlobalVariables.getUserSession().getPrincipalId(),false);
    }
    
    private DocumentHelperService getDocumentHelperService() {
        return KcServiceLocator.getService(DocumentHelperService.class);
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
        return KcServiceLocator.getService(PessimisticLockService.class);
    }
    
    protected void populateAuthorizationFields(ProtocolOnlineReviewFormBase form, ProtocolOnlineReviewDocumentBase document) {
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
        return KcServiceLocator.getService(getReviewCommentsServiceClassHook());
    }

    protected abstract Class<? extends ReviewCommentsService> getReviewCommentsServiceClassHook();
    

    public boolean isHideReviewerName() {
        return hideReviewerName;
    }

    public void setHideReviewerName(boolean hideReviewerName) {
        this.hideReviewerName = hideReviewerName;
    }

    public List<ReviewAttachmentsBeanBase> getReviewAttachmentsBeans() {
        return reviewAttachmentsBeans;
    }

    public void setReviewAttachmentsBeans(List<ReviewAttachmentsBeanBase> reviewAttachmentsBeans) {
        this.reviewAttachmentsBeans = reviewAttachmentsBeans;
    }

    public boolean isHideReviewerNameForAttachment() {
        return hideReviewerNameForAttachment;
    }

    public void setHideReviewerNameForAttachment(boolean hideReviewerNameForAttachment) {
        this.hideReviewerNameForAttachment = hideReviewerNameForAttachment;
    }
    
}

