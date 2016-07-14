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
package org.kuali.kra.timeandmoney.document;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.award.finance.timeAndMoney.dao.TimeAndMoneyPostsDao;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TimeAndMoneyPermissionConstants;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.AwardVersionHistory;
import org.kuali.kra.timeandmoney.history.TimeAndMoneyActionSummary;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyVersionService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.*;

/**
 * 
 * This class represents the Time and Money Document Object.
 * 
 */
public class TimeAndMoneyDocument extends KcTransactionalDocumentBase implements Copyable, SessionDocument, Permissionable, Comparable {
    

    private static final long serialVersionUID = -2554022334215932544L;
    private static final Log LOG = LogFactory.getLog(TimeAndMoneyDocument.class);

    public static final String DOCUMENT_TYPE_CODE = "TAMD";
    
    private String rootAwardNumber;
    private String awardNumber;
    private String documentStatus;
    private Map<String, AwardHierarchyNode> awardHierarchyNodes;
    private Map<String, AwardHierarchy> awardHierarchyItems;
    private List<PendingTransaction> pendingTransactions;
    private List<AwardAmountTransaction> awardAmountTransactions;
    private List<TimeAndMoneyActionSummary> timeAndMoneyActionSummaryItems;
    private Award award;
    private AwardAmountTransaction newAwardAmountTransaction;
    private List<AwardVersionHistory> awardVersionHistoryList;
    private List<String> order;
    private List<AwardAmountInfo> awardAmountInfos;

    private transient TimeAndMoneyVersionService timeAndMoneyVersionService;

    public TimeAndMoneyDocument(){        
        super();        
        init();
    }
        
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }

    /**
     * This method tests if document has been previously persisted.
     * @return
     */
    public boolean isInitialSave() {
        return getObjectId() == null; 
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();       
        managedLists.add(pendingTransactions);
        return managedLists;
    }
    
    protected void init() {
        awardHierarchyNodes = new TreeMap<>();
        awardHierarchyItems = new HashMap<>();
        pendingTransactions = new ArrayList<>();
        awardAmountTransactions = new ArrayList<>();
        timeAndMoneyActionSummaryItems = new ArrayList<>();
        newAwardAmountTransaction = new AwardAmountTransaction();
        awardVersionHistoryList = new ArrayList<>();
        order = new ArrayList<>();
        documentStatus = VersionStatus.PENDING.toString();
    }

    public boolean isAuthorizedToPostTimeAndMoney(String principalId) {
        return isPostTimeAndMoneyFeatureEnabled() && getDocumentHeader().getWorkflowDocument().isFinal()
                && !isPosted(getDocumentNumber())
                && hasPostTimeAndMoneyPermission(principalId);
    }

    public boolean isPosted(String documentNumber) {
            return getTimeAndMoneyPostsDao().getTimeAndMoneyPostsByDocumentNumber(documentNumber) != null;
    }

    public TimeAndMoneyPostsDao getTimeAndMoneyPostsDao() {
        return KcServiceLocator.getService(TimeAndMoneyPostsDao.class);
    }

    public boolean hasPostTimeAndMoneyPermission(String principalId) {
        return getPermissionService().hasPermission(principalId, Constants.KC_SYS, TimeAndMoneyPermissionConstants.POST_TIME_AND_MONEY);
    }

    protected boolean isPostTimeAndMoneyFeatureEnabled() {
        return getParameterService().getParameterValueAsBoolean(
                Constants.MODULE_NAMESPACE_TIME_AND_MONEY,
                ParameterConstants.ALL_COMPONENT,
                Constants.TM_POST_ENABLED);
    }

    protected ParameterService getParameterService() {
        return KcServiceLocator.getService(ParameterService.class);
    }

    protected PermissionService getPermissionService() {
        return KcServiceLocator.getService(PermissionService.class);
    }

    @Override
    public void doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) {
        super.doRouteStatusChange(statusChangeEvent);
        if (StringUtils.equals(KewApiConstants.ROUTE_HEADER_PROCESSED_CD, statusChangeEvent.getNewRouteStatus())){
            this.setAwardHierarchyItems(getAwardHierarchyService().getAwardHierarchy(rootAwardNumber, getOrder()));
            this.setAwardNumber(rootAwardNumber);  
            Award tmpAward = getCurrentAward(this);
            this.setAward(tmpAward);
            if(tmpAward != null) {
                getAwardHierarchyService().populateAwardHierarchyNodesForTandMDoc(this.getAwardHierarchyItems(), this.getAwardHierarchyNodes(), tmpAward.getAwardNumber(), tmpAward.getSequenceNumber().toString(), this);
            } else {
                getAwardHierarchyService().populateAwardHierarchyNodesForTandMDoc(this.getAwardHierarchyItems(), this.getAwardHierarchyNodes(), null, null, this);
            }
            getActivePendingTransactionsService().approveTransactions(this, awardAmountTransactions.get(0));
            getTimeAndMoneyVersionService().updateDocumentStatus(this, VersionStatus.ACTIVE);
            if (LOG.isDebugEnabled()) {
            	LOG.debug("TimeAndMoneyDocument in Processed status and saved with document status of " + this.getDocumentStatus());
            }
        } else if (StringUtils.equals(KewApiConstants.ROUTE_HEADER_FINAL_CD, statusChangeEvent.getNewRouteStatus())) {
        	//this should have occurred when the document went to ROUTE_HEADER_PROCESSED_CD, but in some instances this hasn't happened causing unusable awards and T&M docs so double check here. 
        	if (!VersionStatus.ACTIVE.name().equals(this.getDocumentStatus())) {
                if (LOG.isWarnEnabled()) {
                	LOG.warn("TimeAndMoneyDocument in final status but document status still " + this.getDocumentStatus() + " so setting to ACTIVE again");
                }

                getTimeAndMoneyVersionService().updateDocumentStatus(this, VersionStatus.ACTIVE);
        	}
        }
    }
    
    /*
     * This method retrieves AwardHierarchyService
     */
    protected AwardHierarchyService getAwardHierarchyService(){        
        return (AwardHierarchyService) KcServiceLocator.getService(AwardHierarchyService.class);
    }
    
    private Award getCurrentAward(TimeAndMoneyDocument timeAndMoneyDocument) {
        Award tmpAward = timeAndMoneyDocument.getAward();
        if(tmpAward == null) {
            tmpAward = getAwardVersionService().getWorkingAwardVersion(timeAndMoneyDocument.getAwardNumber());
        }
        
        return tmpAward;
    }
    
    public AwardVersionService getAwardVersionService() {
        return KcServiceLocator.getService(AwardVersionService.class);
    }
    
    protected ActivePendingTransactionsService getActivePendingTransactionsService(){
        return (ActivePendingTransactionsService) KcServiceLocator.getService(ActivePendingTransactionsService.class);
    }

    public String getDocumentKey() {
        //KimIntegration : Verify
        //return Permissionable.TIME_AND_MONEY_KEY;
        return PermissionableKeys.AWARD_KEY;
    }

    public String getDocumentNumberForPermission() {
        //KimIntegration : Verify
        //return documentNumber;
        return getRootAwardNumber();
    }

    public List<String> getRoleNames() {
        List<String> roles = new ArrayList<String>();
        return roles;
    }
    
    public boolean getDocumentRouteStatus() {
        return getDocumentHeader().getWorkflowDocument().isEnroute() || getDocumentHeader().getWorkflowDocument().isFinal();
    }
    
    public boolean isNew(){
        return documentNumber == null;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public String getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }

    public Map<String, AwardHierarchyNode> getAwardHierarchyNodes() {
        return awardHierarchyNodes;
    }

    public void setAwardHierarchyNodes(Map<String, AwardHierarchyNode> awardHierarchyNodes) {
        this.awardHierarchyNodes = awardHierarchyNodes;
    }    

    public List<PendingTransaction> getPendingTransactions() {
        Collections.sort(pendingTransactions, new PendingTransactionComparator());
        return pendingTransactions;
    }

    public void setPendingTransactions(List<PendingTransaction> pendingTransactions) {
        this.pendingTransactions = pendingTransactions;
    }
    
    public void add(PendingTransaction newPendingTransaction){        
        this.getPendingTransactions().add(newPendingTransaction);
    }

    /**
     * Gets the awardHierarchyItems attribute. 
     * @return Returns the awardHierarchyItems.
     */
    public Map<String, AwardHierarchy> getAwardHierarchyItems() {
        return awardHierarchyItems;
    }

    /**
     * Sets the awardHierarchyItems attribute value.
     * @param awardHierarchyItems The awardHierarchyItems to set.
     */
    public void setAwardHierarchyItems(Map<String, AwardHierarchy> awardHierarchyItems) {
        this.awardHierarchyItems = awardHierarchyItems;
    }

    /**
     * Gets the awardAmountTransactions attribute. 
     * @return Returns the awardAmountTransactions.
     */
    public List<AwardAmountTransaction> getAwardAmountTransactions() {
        return awardAmountTransactions;
    }

    /**
     * Sets the awardAmountTransactions attribute value.
     * @param awardAmountTransactions The awardAmountTransactions to set.
     */
    public void setAwardAmountTransactions(List<AwardAmountTransaction> awardAmountTransactions) {
        this.awardAmountTransactions = awardAmountTransactions;
    }

    /**
     * Gets the timeAndMoneyActionSummaryItems attribute. 
     * @return Returns the timeAndMoneyActionSummaryItems.
     */
    public List<TimeAndMoneyActionSummary> getTimeAndMoneyActionSummaryItems() {
        return timeAndMoneyActionSummaryItems;
    }

    /**
     * Sets the timeAndMoneyActionSummaryItems attribute value.
     * @param timeAndMoneyActionSummaryItems The timeAndMoneyActionSummaryItems to set.
     */
    public void setTimeAndMoneyActionSummaryItems(List<TimeAndMoneyActionSummary> timeAndMoneyActionSummaryItems) {
        this.timeAndMoneyActionSummaryItems = timeAndMoneyActionSummaryItems;
    }

    /**
     * Gets the award attribute. 
     * @return Returns the award.
     */
    public Award getAward() {
        return award;
    }

    /**
     * Sets the award attribute value.
     * @param award The award to set.
     */
    public void setAward(Award award) {
        this.award = award;
    }

    /**
     * Gets the newAwardAmountTransaction attribute. 
     * @return Returns the newAwardAmountTransaction.
     */
    public AwardAmountTransaction getNewAwardAmountTransaction() {
        return newAwardAmountTransaction;
    }

    /**
     * Sets the newAwardAmountTransaction attribute value.
     * @param newAwardAmountTransaction The newAwardAmountTransaction to set.
     */
    public void setNewAwardAmountTransaction(AwardAmountTransaction newAwardAmountTransaction) {
        this.newAwardAmountTransaction = newAwardAmountTransaction;
    }

    /**
     * Gets the rootAwardNumber attribute. 
     * @return Returns the rootAwardNumber.
     */
    public String getRootAwardNumber() {
        return rootAwardNumber;
    }

    /**
     * Sets the rootAwardNumber attribute value.
     * @param rootAwardNumber The rootAwardNumber to set.
     */
    public void setRootAwardNumber(String rootAwardNumber) {
        this.rootAwardNumber = rootAwardNumber;
    }
    
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if (ObjectUtils.isNull(this.getVersionNumber())) {
            this.setVersionNumber(new Long(0));
        }
    }

    public String getNamespace() {
        //FIXME:KimMigration - Verify the Namespace
         return Constants.MODULE_NAMESPACE_AWARD;
    }

    public String getLeadUnitNumber() {
        if(getAward() != null)
            return getAward().getLeadUnitNumber(); 
        else
            return null;
    }

    public String getDocumentRoleTypeCode() {
        //FIXME: verify
        return RoleConstants.AWARD_ROLE_TYPE;
    }

    /**
     * Gets the awardVersionHistoryList attribute. 
     * @return Returns the awardVersionHistoryList.
     */
    public List<AwardVersionHistory> getAwardVersionHistoryList() {
        return awardVersionHistoryList;
    }

    /**
     * Sets the awardVersionHistoryList attribute value.
     * @param awardVersionHistoryList The awardVersionHistoryList to set.
     */
    public void setAwardVersionHistoryList(List<AwardVersionHistory> awardVersionHistoryList) {
        this.awardVersionHistoryList = awardVersionHistoryList;
    }
    
    /**
     * Gets the order attribute. 
     * @return Returns the order.
     */
    public List<String> getOrder() {
        return order;
    }

    /**
     * Sets the order attribute value.
     * @param order The order to set.
     */
    public void setOrder(List<String> order) {
        this.order = order;
    }
    
    /**
     * This method is to check whether rice async routing is ok now.   
     * Close to hack.  called by holdingpageaction
     * Different document type may have different routing set up, so each document type
     * can implement its own isProcessComplete
     * @return
     */
    public boolean isProcessComplete() {
        boolean isComplete = false;
        
        if (getDocumentHeader().hasWorkflowDocument()) {
            String docRouteStatus = getDocumentHeader().getWorkflowDocument().getStatus().getCode();
            if (KewApiConstants.ROUTE_HEADER_FINAL_CD.equals(docRouteStatus)) {
                isComplete = true;
            }
        }
           
        return isComplete;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        TimeAndMoneyDocument comparator = (TimeAndMoneyDocument)o;
        String myKey = StringUtils.leftPad(getDocumentNumber(), 40);
        String otherKey = StringUtils.leftPad(comparator.getDocumentNumber(), 40);
        return myKey.compareTo(otherKey);
    }

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return new ArrayList();
    }

    public String getDocumentBoNumber() {
      return "TIME AND MONEY-" + getAwardNumber();
    }

    public TimeAndMoneyVersionService getTimeAndMoneyVersionService() {
        if (timeAndMoneyVersionService == null) {
            timeAndMoneyVersionService = KcServiceLocator.getService(TimeAndMoneyVersionService.class);
        }
        return timeAndMoneyVersionService;
    }

    public void setTimeAndMoneyVersionService(TimeAndMoneyVersionService timeAndMoneyVersionService) {
        this.timeAndMoneyVersionService = timeAndMoneyVersionService;
    }

	public List<AwardAmountInfo> getAwardAmountInfos() {
		return awardAmountInfos;
	}

	public void setAwardAmountInfos(List<AwardAmountInfo> awardAmountInfos) {
		this.awardAmountInfos = awardAmountInfos;
	}
}
