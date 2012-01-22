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
package org.kuali.kra.award.awardhierarchy.sync.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.AwardDocumentRule;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncException;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncLog;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncStatus;
import org.kuali.kra.award.awardhierarchy.sync.helpers.AwardSyncHelper;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.document.authorization.AwardDocumentAuthorizer;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.bo.AdHocRoutePerson;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.PessimisticLockService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Award Hierarchy Sync Service Implementation.
 */
public class AwardSyncServiceImpl implements AwardSyncService {

    protected static final String VALIDATION_SUCCESS_MESSAGE = "Valid";
    protected static final String VALIDATION_FAILURE_MESSAGE = "Invalid";
    protected static final String SYNC_SAVED_MESSAGE = "Saved";
    protected static final String SYNC_SUCCESS_MESSAGE = "Completed";
    protected static final String SYNC_FAILURE_MESSAGE = "Failed";
    protected static final String CHANGE_LOG_SUCCESS = "Success";
    
    protected static final String IGNORED_MESSAGE_KEYS = "error.award.person.credit.split.,error.award.person.unit.credit.split.";
    protected final Log LOG = LogFactory.getLog(AwardSyncServiceImpl.class);
    
    private AwardSyncHelpersService awardSyncHelpersService;
    private AwardSyncCreationService awardSyncCreationService;
    private AwardSyncUtilityService awardSyncUtilityService;
    private AwardHierarchyService awardHierarchyService;
    private AwardService awardService;
    private VersionHistoryService versionHistoryService;
    private BusinessObjectService businessObjectService;
    private DocumentService documentService;
    private AwardSyncSelectorService awardSyncSelectorService;
    private TaskExecutor syncExecutor;
    @SuppressWarnings("unchecked")
    private PersonService personService;
    private PessimisticLockService pessimisticLockService;
    private KraWorkflowService kraWorkflowService;
        
    /**
     * @see org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncService#validateHierarchyChanges(org.kuali.kra.award.home.Award)
     */
    public void validateHierarchyChanges(Award award) {
        runSync(award, SyncType.VALIDATE);
    }
    
    /**
     * @see org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncService#applyAwardSyncChangesToHierarchy(org.kuali.kra.award.home.Award)
     */
    public void applyAwardSyncChangesToHierarchy(Award award) {
        runSync(award, SyncType.SYNC);
    }  
    
    /**
     * @see org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncService#getAwardLockingHierarchyForSync(org.kuali.kra.award.document.AwardDocument, java.lang.String)
     */
    public AwardDocument getAwardLockingHierarchyForSync(AwardDocument awardDocument, String principalId) {
        AwardHierarchy hierarchy = getAwardHierarchyService().loadAwardHierarchy(awardDocument.getAward().getAwardNumber());
        if (hierarchy != null) {
            Person person = getPersonService().getPerson(principalId);
            while (hierarchy.getParent() != null) {
                hierarchy = hierarchy.getParent();
                Award parentAward = getPendingAward(hierarchy.getAwardNumber());
                if (parentAward != null && !parentAward.getSyncChanges().isEmpty()) {
                    try {
                        AwardDocument parentDocument = 
                            (AwardDocument) getDocumentService().getByDocumentHeaderId(parentAward.getAwardDocument().getDocumentNumber());
                        if (getKraWorkflowService().isEnRoute(parentDocument)
                                && !(parentDocument.getDocumentHeader().getWorkflowDocument().getRoutedByPrincipalId().equals(principalId))) {
                            return parentDocument;
                        }
                    } catch (WorkflowException e) {
                        LOG.error("Error loading document to check for award sync lock", e);
                    }
                }
            }
        }
        return null;
    }        
    
    /**
     * Run validation or full sync depending on syncType on the award hierarchy under award.
     * @param award
     * @param syncType
     */
    protected void runSync(final Award award, final SyncType syncType) {
        long start = System.currentTimeMillis();
        LOG.debug("Award Hierarchy Sync Starting");
        UserSession oldSession = replaceSessionWithRoutedBy(award);
        try {
            List<SyncRunnable> runnables = new ArrayList<SyncRunnable>();
            runInTransaction(new TransactionRunnable() {
                public void run() {
                    clearSyncStatuses(award);
                    setParentAwardStatus(award, true, 
                            syncType == SyncType.VALIDATE ? "Validation In Progress" : "Sync Descendants In Progress");                
                }
            });
            award.refresh();
            //populate XmlExport object for each change from xml in BO.
            List<AwardSyncChange> changes = award.getSyncChanges();
            for (AwardSyncChange change : changes) {
                change.setXmlExport(getAwardSyncCreationService().getXmlExport(change));
            }
            
            AwardHierarchy hierarchy = getAwardHierarchyService().loadAwardHierarchyBranch(award.getAwardNumber());
            for (AwardHierarchy curHierarchy : hierarchy.getChildren()) {
                runSyncOnHierarchy(award, curHierarchy, changes, syncType, runnables);
            }
            waitTillRunablesFinished(runnables);
            award.refresh();
            if (syncType == SyncType.SYNC) {
                sendSyncFYIs(award, getAllPrincipalsToNotify(runnables), runnables);
            }
            runInTransaction(new TransactionRunnable() {
                public void run() {
                    setParentAwardStatus(award, true, 
                        syncType == SyncType.VALIDATE ? "Validation Complete" : "Sync Descendants Complete");
                }
            });
            long elapsed = System.currentTimeMillis() - start;
            LOG.info("Award Hierarchy Sync Finished : " + elapsed + "ms");            
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            GlobalVariables.setUserSession(oldSession);
        }
    }
    
    /**
     * Run the sync recursively down the hierarchy.
     * @param parentAward
     * @param hierarchy
     * @param changes
     * @param syncType
     * @param runnables
     */
    protected void runSyncOnHierarchy(Award parentAward, AwardHierarchy hierarchy, 
            List<AwardSyncChange> changes, SyncType syncType, List<SyncRunnable> runnables) {
        this.runSyncInThread(parentAward, hierarchy, syncType, changes, runnables);
        for (AwardHierarchy curHierarchy : hierarchy.getChildren()) {
            runSyncOnHierarchy(parentAward, curHierarchy, changes, syncType, runnables);
        }
    }
    
    /**
     * Clears sync status from parent award as long as it has an award number(not the parent status),
     * and it is not yet complete.
     * @param award
     * @param runnables
     */
    protected void clearSyncStatuses(Award award) {
        //clear any old logs and reset old statuses
        List<AwardSyncLog> oldLogs = new ArrayList<AwardSyncLog>();
        for (AwardSyncStatus status : award.getSyncStatuses()) {
            if (status.getAwardNumber() != null && !status.isSyncComplete()) {
                status.setStatus(null);
                status.setSyncComplete(false);
                status.setSuccess(false);
                oldLogs.addAll(status.getChangeLogs());
                oldLogs.addAll(status.getValidationLogs());
                status.getChangeLogs().clear();
                status.getValidationLogs().clear();
            }
        }
        getBusinessObjectService().delete(oldLogs);
        getBusinessObjectService().save(award.getSyncStatuses());
    }    
    
    /**
     * Sends FYIs to all principals listed in principalsToNotify for award.
     * @param award
     * @param principalsToNotify
     * @param runnables
     * @throws WorkflowException 
     */
    protected void sendSyncFYIs(final Award award, final List<String> principalsToNotify, final List<SyncRunnable> runnables) throws WorkflowException {
        final List<AdHocRouteRecipient> adHocPersons = new ArrayList<AdHocRouteRecipient>();
        for (String principalId : principalsToNotify) {
            Person person = getPersonService().getPerson(principalId);
            if (person != null) {
                AdHocRoutePerson adHocPerson = new AdHocRoutePerson();
                adHocPerson.setActionRequested(KewApiConstants.ACTION_REQUEST_FYI_REQ);
                adHocPerson.setType(AdHocRoutePerson.PERSON_TYPE);
                adHocPerson.setId(person.getPrincipalName());
                adHocPersons.add(adHocPerson);
            }
        }
        this.sendAdHocRequestsInThread(award.getAwardDocument(), "Sync Descendents FYI", adHocPersons, runnables);
    }
    
    /**
     * Sets the parent award status for the sync. This is done by creating and updating a sync status that has no award.
     * @param parentAward
     * @param success
     * @param newStatus
     * @param runnables
     */
    protected void setParentAwardStatus(Award parentAward, boolean success, String newStatus) {
        AwardSyncStatus status = getParentAwardStatus(parentAward);
        if (status != null) {
            status.setStatus(newStatus);
            status.setSuccess(success);
        } else {
            status = new AwardSyncStatus();
            status.setParentAwardId(parentAward.getAwardId());
            status.setStatus(newStatus);
            status.setSuccess(success);
            parentAward.getSyncStatuses().add(status);
        }
        getBusinessObjectService().save(status);
    }
    
    
    /**
     * Finds the parents award status log entry, the one without an award listed.
     * @param parentAward
     * @return
     */
    protected AwardSyncStatus getParentAwardStatus(Award parentAward) {
        parentAward.refreshReferenceObject("syncStatuses");
        for (AwardSyncStatus status : parentAward.getSyncStatuses()) {
            if (status.getAwardNumber() == null) {
                return status;
            }
        }
        return null;
    }
    
    /**
     * Find or create a new award status for the award number.
     * @param parentAward
     * @param awardNumber
     * @return
     */
    protected AwardSyncStatus findAwardSyncStatus(Award parentAward, String awardNumber) {
        for (AwardSyncStatus status : parentAward.getSyncStatuses()) {
            if (StringUtils.equals(awardNumber, status.getAwardNumber())) {
                return status;
            }
        }
        AwardSyncStatus status = new AwardSyncStatus();
        status.setParentAwardId(parentAward.getAwardId());
        status.setAwardNumber(awardNumber);
        return status;
    }
    
    /**
     * Replace the UserSession with one for the user who routed the parent award.
     * @param parentAward
     * @return
     */
    protected UserSession replaceSessionWithRoutedBy(Award parentAward) {
        String routedByUserId = parentAward.getAwardDocument().getDocumentHeader().getWorkflowDocument().getRoutedByPrincipalId();
        Person person = getPersonService().getPerson(routedByUserId);
        UserSession oldSession = GlobalVariables.getUserSession();
        GlobalVariables.setUserSession(new UserSession(person.getPrincipalName()));
        return oldSession;
    }
    
    /**
     * Check and validate the status of the award. Must have an active version and
     * there must not be a pending version.
     * @param awardNumber
     * @param awardStatus
     * @param failureMessage
     * @return
     */
    protected Award checkAwardVersions(String awardNumber, AwardSyncStatus awardStatus, String failureMessage) {
        Award oldAward = getPendingAward(awardNumber);
        if (oldAward != null) {
            logFailure(awardStatus, failureMessage, "Award has an outstanding pending version."); 
            awardStatus.setAwardId(oldAward.getAwardId());
        } else {
            oldAward = getActiveAward(awardNumber);
            if (oldAward == null) {
                logFailure(awardStatus, failureMessage, "Award does not have an active version.");
            } else {
                awardStatus.setAwardId(oldAward.getAwardId());
            }
        }
        return oldAward;
    }
    
    /**
     * Run the actual sync changes against the specific award in the hierarchy.
     * @param parentAward
     * @param hierarchy
     * @param syncType
     * @param changes
     * @param principalsToNotify
     * @throws RuntimeException
     */
    protected void runSyncChanges(Award parentAward, AwardHierarchy hierarchy, final SyncType syncType, 
            List<AwardSyncChange> changes, final List<String> principalsToNotify) {
        long start = System.currentTimeMillis();
        LOG.debug("Award Hierarchy Sync Started for " + hierarchy.getAwardNumber());
        boolean result = true;
        UserSession oldSession = null;
        String successMessage = null;
        String failureMessage = null;
        if (syncType == SyncType.VALIDATE) {
            successMessage = VALIDATION_SUCCESS_MESSAGE;
            failureMessage = VALIDATION_FAILURE_MESSAGE;
        } else if (syncType == SyncType.SYNC) {
            successMessage = SYNC_SAVED_MESSAGE;
            failureMessage = SYNC_FAILURE_MESSAGE;
        }
        final AwardSyncStatus awardStatus = findAwardSyncStatus(parentAward, hierarchy.getAwardNumber());
        try {
            //make sure our session is that of the document submitter, we usually won't have a session here at all, but
            //save and restore the existing session in case.
            oldSession = replaceSessionWithRoutedBy(parentAward);
            if (!awardStatus.isSyncComplete()) {
                Award oldAward = checkAwardVersions(hierarchy.getAwardNumber(), awardStatus, failureMessage);
                if (oldAward != null) {
                    if (getAwardSyncSelectorService().isAwardInvolvedInSync(oldAward, changes)) {
                        //check to see if this award has already had this award sync applied and was completed successfully.
                        //If it has then the workflow step has probably been restarted due to an error and we will not
                        //redo the sync, otherwise clear status and logs from validation or previous runs.
                        if (!hasAwardPermission(awardStatus, oldAward, 
                                parentAward.getAwardDocument().getDocumentHeader().getWorkflowDocument().getRoutedByPrincipalId())) {
                            logFailure(awardStatus, failureMessage, "Sync submitter does not have modify permission on Award.");
                            return;
                        }
                        
                        final AwardDocument awardDocument;
                        Award award = null;
                        if (syncType == SyncType.SYNC) {
                            awardDocument = versionAndPrepareAwardDocument(parentAward, oldAward, awardStatus);
                        } else {
                            awardDocument = loadAwardDocument(oldAward);
                        }
                        if (awardDocument == null) {
                            logFailure(awardStatus, failureMessage, "Unable to get document.");
                            return;
                        }
                        award = awardDocument.getAward();
                        result &= applyAndValidateChanges(award, awardStatus, changes);
                        if (result) {
                            finalizeAwardStatus(awardDocument, awardStatus, successMessage, syncType, principalsToNotify);
                            finalizeAward(awardDocument, awardStatus);
                        } else {
                            awardStatus.setStatus(failureMessage);
                            awardStatus.setSuccess(false);
                        }
                    } else {
                        awardStatus.setStatus("No changes");
                        awardStatus.setSuccess(true);
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Error applying sync", e);
            awardStatus.addValidationLog("Error applying sync. See system log for details.", false, null);
            awardStatus.setStatus(failureMessage);
            awardStatus.setSuccess(false);
            throw new RuntimeException(e);
        } finally {
            //persist status in another transaction to avoid any rollbacks
            saveInTransaction(awardStatus);
            GlobalVariables.setUserSession(oldSession);
            long elapsed = System.currentTimeMillis() - start;
            LOG.info("Finished award sync - " + hierarchy.getAwardNumber() + " : " + elapsed + "ms");
        }
    }
    
    /**
     * Apply the list of changes against the award and then validate the changes.
     * @param award
     * @param awardStatus
     * @param changes
     * @return
     */
    protected boolean applyAndValidateChanges(Award award, AwardSyncStatus awardStatus, List<AwardSyncChange> changes) {
        boolean result = true;
        for (AwardSyncChange change : changes) {
            if (getAwardSyncSelectorService().isChangeApplicableToAward(award, change)) {
                result &= applyChange(award, change, awardStatus);
            }
        }
        try {
            result &= validateModifiedAward(award.getAwardDocument(), awardStatus);
        } catch (Exception e) {
            LOG.error("Unable to validate award during sync.", e);
            result = false;
            awardStatus.addValidationLog("Validation failed due to unexpected error. See system log for details.", false, null);
        }
        return result;
    }
    
    /**
     * Finalize the award sync. If running validation this only requires setting the
     * AwardSyncStatus to final. If running full sync the award is saved, FYI list generated
     * and award blanket approved.
     * @param awardDocument
     * @param awardStatus
     * @param successMessage
     * @param syncType
     * @param principalsToNotify
     * @throws WorkflowException
     */
    protected void finalizeAwardStatus(AwardDocument awardDocument, AwardSyncStatus awardStatus, 
            String successMessage, SyncType syncType, List<String> principalsToNotify) throws WorkflowException {
        if (syncType == SyncType.SYNC) {
            getDocumentService().saveDocument(awardDocument);
            principalsToNotify.addAll(getNotificationList(awardDocument));
            awardStatus.setSyncComplete(true);
            awardStatus.setAwardId(awardDocument.getAward().getAwardId());
            awardStatus.refreshReferenceObject("award");
        }
        awardStatus.setStatus(successMessage);
        awardStatus.setSuccess(true);        
    }
    
    /**
     * Attempts to load the award document for the specified award. This ensures that the workflow document is not null and 
     * can be used for validation.
     * @param awardStatus
     * @param award
     * @param errorMessage
     * @param runnables
     * @return
     */
    protected AwardDocument loadAwardDocument(Award award) {
        try {
            AwardDocument awardDocument = 
                (AwardDocument) getDocumentService().getByDocumentHeaderId(award.getAwardDocument().getDocumentNumber());
            //minor hack for the pseudo award document that can possibly contain hundred of awards that would
            //then all be validated.
            awardDocument.getAwardList().clear();
            awardDocument.getAwardList().add(award);
            return awardDocument;
        } catch (WorkflowException e) {
            LOG.error("Unable to load award document.", e);
            return null;
        }
    }
    
    /**
     * Set the status to failure and set the status to the message and add the log message.
     * @param awardStatus
     * @param statusMessage
     * @param logMessage
     */
    protected void logFailure(AwardSyncStatus awardStatus, String statusMessage, String logMessage) {
        awardStatus.setStatus(statusMessage);
        awardStatus.setSuccess(false);
        awardStatus.addValidationLog(logMessage, false, null);
    }
    
    /**
     * Ensure that the person specified by principalId has modify permission on the award specified.
     * @param awardStatus
     * @param award
     * @param principalId
     * @param errorMessage
     * @param runnables
     * @return
     */
    protected boolean hasAwardPermission(AwardSyncStatus awardStatus, Award award, String principalId) {
        Person person = getPersonService().getPerson(principalId);
        return new AwardDocumentAuthorizer().canEdit(award.getAwardDocument(), person);
    }
    
    /**
     * Run the {@link AwardDocumentRule#processSaveDocument} and {@link AwardDocumentRule#processRunAuditBusinessRules}
     * against award. Add all messages generated from running the rules to logList as {@link AwardSyncLog}. 
     * Return false if any of the error keys generated by the rules are not in the ignoredMessageKeys list.
     * @param award
     * @param logs
     * @return
     */
    protected boolean validateModifiedAward(AwardDocument award, AwardSyncStatus awardStatus) {
        AwardDocumentRule rule = new AwardDocumentRule();
        boolean result = true;
        if (!rule.processSaveDocument(award)) {
            getAwardSyncUtilityService().getLogsFromSaveErrors(awardStatus);
        }
        if (!rule.processRunAuditBusinessRules(award)) {
            getAwardSyncUtilityService().getLogsFromAuditErrors(awardStatus);
        }
        String[] ignoredErrors = IGNORED_MESSAGE_KEYS.split(",");
        for (AwardSyncLog log : awardStatus.getValidationLogs()) {
            if (!StringUtils.startsWithAny(log.getMessageKey(), ignoredErrors)) {
                result = false;
            } else {
                log.setSuccess(true);
            }
        }
        return result;
    }
    
    /**
     * Apply the specific change to the award using the specified syncHelper.
     * @param award
     * @param syncHelper
     * @param change
     * @throws Exception
     */
    protected void applyChange(Award award, AwardSyncHelper syncHelper, AwardSyncChange change) throws Exception {
        if (getAwardSyncSelectorService().isChangeApplicableToAward(award, change)) {
            syncHelper.applySyncChange(award, change);
        }
    }
    
    /**
     * Get the syncHelper from the syncHelpers for the given className. If the className is a full qualified class name,
     * only the class name itself is used.
     * @param className
     * @return
     */
    protected AwardSyncHelper getSyncHelper(String className) {
        return getAwardSyncHelpersService().getSyncHelper(className);
    }
    
    /**
     * Get the active version of an award if it exists.
     * @param awardNumber
     * @return
     */
    protected Award getActiveAward(String awardNumber) {
        VersionHistory vh = getVersionHistoryService().findActiveVersion(Award.class, awardNumber);
        if (vh != null) {
            return (Award) vh.getSequenceOwner();    
        } else {
            return null;
        }
    }
    
    /**
     * Get the pending version for the award if it exists.
     * @param awardNumber
     * @return
     */
    protected Award getPendingAward(String awardNumber) {
        List<VersionHistory> histories = getVersionHistoryService().loadVersionHistory(Award.class, awardNumber);
        for (VersionHistory history : histories) {
            if (history.getStatus() == VersionStatus.PENDING) {
                return (Award) history.getSequenceOwner();
            }
        }
        return null;
    }  
    
    /**
     * Wait forever until all runnables are finished.
     * @param runnables
     * @throws AwardSyncException 
     */
    protected void waitTillRunablesFinished(List<SyncRunnable> runnables) throws AwardSyncException {
        int numberOfWaits = 0;
        while (!areRunnablesFinished(runnables)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }
            if (numberOfWaits++ > 10000) {
                throw new AwardSyncException("Runnables have failed to complete in a reasonable amount of time.", false);
            }
        }
    }
    
    /**
     * 
     * Collects all prinicipalsToNotify from {@link #RunSyncOnAward} runnables in the list
     * of runnables.
     * @param runnables
     * @return
     */
    protected List<String> getAllPrincipalsToNotify(List<SyncRunnable> runnables) {
        List<String> allPrincipalsToNotify = new ArrayList<String>();
        for (SyncRunnable runnable : runnables) {
            if (runnable instanceof RunSyncOnAward) {
                for (String principalId : ((RunSyncOnAward) runnable).getPrincipalsToNotify()) {
                    if (!allPrincipalsToNotify.contains(principalId)) {
                        allPrincipalsToNotify.add(principalId);
                    }
                }
            }
        }
        
        return allPrincipalsToNotify;
    }
    
    /**
     * 
     * Loops through all runnables and check their finished flags.
     * @param runnables
     * @return
     */
    protected synchronized boolean areRunnablesFinished(List<SyncRunnable> runnables) {
        boolean finished = true;
        ListIterator<SyncRunnable> iter = runnables.listIterator();
        while (iter.hasNext()) {
            SyncRunnable runnable = iter.next();
            if (!runnable.getFinished()) {
                finished = false;
            }
        }
        return finished;
    }
    
    /**
     * Apply single sync change to award.
     * @param parentAward
     * @param award
     * @param change
     * @param logList
     * @return
     */
    protected boolean applyChange(Award award, AwardSyncChange change, AwardSyncStatus awardStatus) {
        boolean result = true;
        try {
            applyChange(award, getSyncHelper(change.getClassName()), change);
            awardStatus.addChangeLog(change, CHANGE_LOG_SUCCESS, true);
        } catch (AwardSyncException e) {
            result &= e.isSuccess();
            awardStatus.addChangeLog(change, e.getStatusMessage(), e.isSuccess());
        } catch (Exception e) {
            LOG.error("Error applying changes to award " + award.getAwardNumber(), e);
            result = false;
            awardStatus.addChangeLog(change, "Error", false);
        }
        return result;
    }
    
    /**
     * Create new version of oldAward and prepare document for sync by setting document
     * description, transaction type, etc.
     * @param parentAward
     * @param oldAward
     * @param logList
     * @return
     */
    protected AwardDocument versionAndPrepareAwardDocument(Award parentAward, Award oldAward, AwardSyncStatus awardStatus) {
        AwardDocument awardDocument = oldAward.getAwardDocument();
        //hack to work around placeholder award document that can have many many awards attached to it
        //and we need the specific award to be the only one on the document.
        awardDocument.getAwardList().clear();
        awardDocument.getAwardList().add(oldAward);
        AwardDocument newAwardDocument = null;
        try {
            newAwardDocument = getAwardService().createNewAwardVersion(awardDocument);
        } catch (Exception e) {
            LOG.error("Sync Failure while trying to version Award " + oldAward.getAwardNumber(), e);
            awardStatus.setStatus(SYNC_FAILURE_MESSAGE + "(Versioning Error)");
            awardStatus.setSuccess(false);
            throw new RuntimeException(e);
        }
        Award award = newAwardDocument.getAward();
        newAwardDocument.getDocumentHeader().setDocumentDescription("Created by Award " 
                + parentAward.getAwardNumber() + " Ver " + parentAward.getSequenceNumber());
        award.setAwardTransactionTypeCode(parentAward.getAwardTransactionTypeCode());
        award.setNoticeDate(parentAward.getNoticeDate());
        award.getAwardCurrentActionComments().setComments("Synchronize Descendants from Award " + parentAward.getAwardNumber());
        return newAwardDocument;
    }
    
    /**
     * Save award and generate a list of workflow recipients as if this document
     * were normally routed to later be used in an FYI list of the parent award.
     * @param awardDocument
     * @return
     * @throws WorkflowException
     */
    protected List<String> getNotificationList(AwardDocument awardDocument) throws WorkflowException {
        List<String> principalsToNotify = new ArrayList<String>();
        Award award = awardDocument.getAward();
        //save as a normal award
        award.setSyncChild(false);
        getDocumentService().saveDocument(awardDocument);
        principalsToNotify.addAll(getAwardSyncUtilityService().buildListForFYI(awardDocument));
        //set to hierarchy sync child to avoid any requests being sent during blanket approve
        //and resave as it will recreate the workflow doc for the correct user
        award.setSyncChild(true);
        getDocumentService().saveDocument(awardDocument);
        return principalsToNotify;
    }
    
    /**
     * Blanket Approve the award document.
     * @param newAwardDocument
     * @throws WorkflowException
     */
    protected void finalizeAward(AwardDocument awardDocument, AwardSyncStatus awardSyncStatus) {
        try {
            if (awardSyncStatus.isSuccess() && awardSyncStatus.isSyncComplete()) {
                awardDocument = (AwardDocument) getDocumentService().getByDocumentHeaderId(awardDocument.getDocumentNumber());
                getDocumentService().blanketApproveDocument(awardDocument, "Award Hierarchy Sync Routed Document", null);
                awardSyncStatus.setStatus(SYNC_SUCCESS_MESSAGE);
            }
        } catch (Exception e) {
            awardSyncStatus.setSyncComplete(false);
            logFailure(awardSyncStatus, SYNC_FAILURE_MESSAGE, "Failure occured while approving document.");
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Save or delete object in a separate thread using {@link #SaveBo}.
     * @param object
     * @param delete
     * @param runnables
     */
    protected void saveInTransaction(final Object object) {
        TransactionTemplate template = new TransactionTemplate((PlatformTransactionManager) KraServiceLocator.getService("transactionManager"));
        template.setPropagationBehavior(Propagation.REQUIRES_NEW.value());
        template.execute(new TransactionCallback() {
            @SuppressWarnings("unchecked")
            public Object doInTransaction(TransactionStatus status) {
                if (object instanceof PersistableBusinessObject) {
                    getBusinessObjectService().save((PersistableBusinessObject) object);
                } else if (object instanceof List) {
                    getBusinessObjectService().save((List) object);
                }
                return null;
            }
        });
    }
    
    /**
     * Run the runnable in a separate transaction, commiting when finished or rolling back
     * if an exception is generated.
     * @param runnable
     */
    protected void runInTransaction(final TransactionRunnable runnable) {
        final UserSession session = GlobalVariables.getUserSession();
        syncExecutor.execute(new SyncRunnable() {
            public void run() {
                GlobalVariables.setUserSession(session);
                TransactionTemplate template = 
                    new TransactionTemplate((PlatformTransactionManager) KraServiceLocator.getService("transactionManager"));
                template.setPropagationBehavior(Propagation.REQUIRES_NEW.value());
                template.execute(new TransactionCallback() {
                    public Object doInTransaction(TransactionStatus status) {
                        runnable.run();
                        return null;
                    }
                });
            }
        });
    }
        
    /**
     * 
     * Run {@link AwardSyncServiceImpl#applyChanges} through the use of {@link #RunSyncOnAward}.
     * @param parentAward
     * @param currentVersion
     * @param changes
     * @param runnables
     */
    protected void runSyncInThread(Award parentAward, AwardHierarchy hierarchy, SyncType syncType,
            List<AwardSyncChange> changes, List<SyncRunnable> runnables) {
        SyncRunnable newRunnable = new RunSyncOnAward(parentAward, hierarchy, syncType, changes);
        runnables.add(newRunnable);
        getSyncExecutor().execute(newRunnable);
    }
    
    
    /**
     * 
     * Send ad hoc requests in a thread. This is used to make sure the requests are
     * processed before the document moves to the next step in workflow and then the 
     * requests get ignored.
     * @param document
     * @param annotation
     * @param recipients
     * @param runnables
     */
    protected void sendAdHocRequestsInThread(AwardDocument document, String annotation, List<AdHocRouteRecipient> recipients, List<SyncRunnable> runnables) {
        SyncRunnable newRunnable = new SendAdHocRequests(document, annotation, recipients, GlobalVariables.getUserSession());
        runnables.add(newRunnable);
        getSyncExecutor().execute(newRunnable);

    }    
    
    /**
     * Validation or running full sync
     */
    protected enum SyncType {
        SYNC(), VALIDATE();
    }
    
    /**
     * Abstract class that is used to generate runnables to run in transactions.
     */
    protected abstract class TransactionRunnable {
        public abstract void run();
    }
    
    /**
     * Parent for thread based runnables related to award sync to provide a finished flag that
     * supports ensuring all threads are finished.
     */
    protected abstract class SyncRunnable implements Runnable {
        protected boolean finished;
        public void run() {
            setFinished(true);
        }
        public synchronized boolean getFinished() {
            return finished;
        }
        public synchronized void setFinished(boolean finished) {
            this.finished = finished;
        }
    }
    
    /**
     * 
     * Runnable that calls {@link AwardSyncServiceImpl#applyChanges} in a separate thread.
     */
    protected class RunSyncOnAward extends SyncRunnable {
        protected Award parentAward;
        protected AwardHierarchy hierarchy;
        protected SyncType syncType;
        protected List<AwardSyncChange> changes;
        protected List<String> principalsToNotify = new ArrayList<String>();
        
        public RunSyncOnAward(Award parentAward, AwardHierarchy hierarchy, SyncType syncType, List<AwardSyncChange> changes) {
            this.parentAward = parentAward;
            this.hierarchy = hierarchy;
            this.syncType = syncType;
            this.changes = changes;
        }
        
        @Override
        public synchronized void run() {
            try {
                TransactionTemplate template = new TransactionTemplate((PlatformTransactionManager) KraServiceLocator.getService("transactionManager"));
                template.setPropagationBehavior(Propagation.REQUIRES_NEW.value());
                template.execute(new TransactionCallback() {
                    public Object doInTransaction(TransactionStatus status) {
                        runSyncChanges(parentAward, hierarchy, syncType, changes, principalsToNotify);
                        return null;
                    }
                });
            }
            catch (Exception e) {
                //error message has been caught and handled, but must be thrown to rollback
                //transaction.
            } finally {
                super.run();
            }
        }
        
        public synchronized List<String> getPrincipalsToNotify() {
            return principalsToNotify;
        }
        
    }    
    
    /**
     * Runnable that sends a list of ad hoc requests in a separate thread.
     */
    protected class SendAdHocRequests extends SyncRunnable {
        protected AwardDocument awardDocument;
        protected String annotation;
        protected List<AdHocRouteRecipient> recipients;
        protected UserSession session;
        
        public SendAdHocRequests(AwardDocument awardDocument, String annotation, List<AdHocRouteRecipient> recipients, UserSession session) {
            this.awardDocument = awardDocument;
            this.annotation = annotation;
            this.recipients = recipients;
            this.session = session;
        }
        
        public void run() {
            UserSession oldSession = GlobalVariables.getUserSession();
            try {
                GlobalVariables.setUserSession(session);
                getDocumentService().sendAdHocRequests(awardDocument, annotation, recipients);
            }
            catch (WorkflowException e) {
                LOG.error("Error sending Ad Hoc requests for Award Sync", e);
            } finally {
                super.run();
                GlobalVariables.setUserSession(oldSession);
            }
        }
    }    

    protected AwardHierarchyService getAwardHierarchyService() {
        return awardHierarchyService;
    }

    public void setAwardHierarchyService(AwardHierarchyService awardHierarchyService) {
        this.awardHierarchyService = awardHierarchyService;
    }

    protected AwardService getAwardService() {
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

    protected VersionHistoryService getVersionHistoryService() {
        return versionHistoryService;
    }

    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }

    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    protected TaskExecutor getSyncExecutor() {
        return syncExecutor;
    }

    public void setSyncExecutor(TaskExecutor syncExecutor) {
        this.syncExecutor = syncExecutor;
    }

    protected AwardSyncSelectorService getAwardSyncSelectorService() {
        return awardSyncSelectorService;
    }

    public void setAwardSyncSelectorService(AwardSyncSelectorService awardSyncSelectorService) {
        this.awardSyncSelectorService = awardSyncSelectorService;
    }

    @SuppressWarnings("unchecked")
    protected PersonService getPersonService() {
        return personService;
    }

    @SuppressWarnings("unchecked")
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    protected PessimisticLockService getPessimisticLockService() {
        return pessimisticLockService;
    }

    public void setPessimisticLockService(PessimisticLockService pessimisticLockService) {
        this.pessimisticLockService = pessimisticLockService;
    }

    protected KraWorkflowService getKraWorkflowService() {
        return kraWorkflowService;
    }

    public void setKraWorkflowService(KraWorkflowService kraWorkflowService) {
        this.kraWorkflowService = kraWorkflowService;
    }

    protected AwardSyncHelpersService getAwardSyncHelpersService() {
        return awardSyncHelpersService;
    }

    public void setAwardSyncHelpersService(AwardSyncHelpersService awardSyncHelpersService) {
        this.awardSyncHelpersService = awardSyncHelpersService;
    }

    protected AwardSyncCreationService getAwardSyncCreationService() {
        return awardSyncCreationService;
    }

    public void setAwardSyncCreationService(AwardSyncCreationService awardSyncCreationService) {
        this.awardSyncCreationService = awardSyncCreationService;
    }

    protected AwardSyncUtilityService getAwardSyncUtilityService() {
        return awardSyncUtilityService;
    }

    public void setAwardSyncUtilityService(AwardSyncUtilityService awardSyncUtilityService) {
        this.awardSyncUtilityService = awardSyncUtilityService;
    }
}

