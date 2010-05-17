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
package org.kuali.kra.timeandmoney.document;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.history.TimeAndMoneyActionSummary;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.rice.kew.dto.DocumentRouteStatusChangeDTO;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.document.Copyable;
import org.kuali.rice.kns.document.SessionDocument;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * 
 * This class represents the Time and Money Document Object.
 * 
 */
public class TimeAndMoneyDocument extends ResearchDocumentBase implements  Copyable, SessionDocument, Permissionable{
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -2554022334215932544L;

    public static final String DOCUMENT_TYPE_CODE = "TAMD";
    
    private String rootAwardNumber;
    private String awardNumber;
    private Map<String, AwardHierarchyNode> awardHierarchyNodes;
    private Map<String, AwardHierarchy> awardHierarchyItems;
    private List<PendingTransaction> pendingTransactions;
    private List<AwardAmountTransaction> awardAmountTransactions;
    private Map<Object, Object> timeAndMoneyHistory;
    private List<TimeAndMoneyActionSummary> timeAndMoneyActionSummaryItems;
    private Award award;
    private AwardAmountTransaction newAwardAmountTransaction;
    private List<AwardDirectFandADistribution> awardDirectFandADistributions;
    
    /**
     * Constructs a AwardDocument object
     */
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
    
    /**
     * 
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#buildListOfDeletionAwareLists()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();       
        managedLists.add(pendingTransactions);
        return managedLists;
    }
    
    protected void init() {
        awardHierarchyNodes = new HashMap<String, AwardHierarchyNode>();
        awardHierarchyItems = new HashMap<String, AwardHierarchy>();
        pendingTransactions = new ArrayList<PendingTransaction>();
        awardAmountTransactions = new ArrayList<AwardAmountTransaction>();
//        AwardAmountTransaction aat = new AwardAmountTransaction();
//        aat.setAwardNumber("000000-00000");//need to initialize one element in this collection because the doc is saved on creation.
//        aat.setDocumentNumber(getDocumentNumber());
//        aat.setTransactionTypeCode(9);
//        awardAmountTransactions.add(aat);
        timeAndMoneyHistory = new LinkedHashMap<Object, Object>();
        timeAndMoneyActionSummaryItems = new ArrayList<TimeAndMoneyActionSummary>(); 
        awardDirectFandADistributions = new ArrayList<AwardDirectFandADistribution>();
        newAwardAmountTransaction = new AwardAmountTransaction();
    }
    
    @Override
    public void doRouteStatusChange(DocumentRouteStatusChangeDTO statusChangeEvent) {
        super.doRouteStatusChange(statusChangeEvent);
        if (StringUtils.equals(KEWConstants.ROUTE_HEADER_PROCESSED_CD, statusChangeEvent.getNewRouteStatus())){
            this.setAwardHierarchyNodes(((TimeAndMoneyDocument)GlobalVariables.getUserSession().retrieveObject(
                    GlobalVariables.getUserSession().getKualiSessionId() + Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION)).getAwardHierarchyNodes());
            getActivePendingTransactionsService().approveTransactions(this, awardAmountTransactions.get(0));
        }
    }
    
    protected ActivePendingTransactionsService getActivePendingTransactionsService(){
        return (ActivePendingTransactionsService) KraServiceLocator.getService(ActivePendingTransactionsService.class);
    }
    
    /**
     * @see org.kuali.kra.document.ResearchDocumentBase#getAllRolePersons()
     */
    @Override
    protected List<RolePersons> getAllRolePersons() {
        KraAuthorizationService kraAuthorizationService = getKraAuthorizationService(); 
        return kraAuthorizationService.getAllRolePersons(this);
    }
    
    protected KraAuthorizationService getKraAuthorizationService(){
        return (KraAuthorizationService) KraServiceLocator.getService(KraAuthorizationService.class);
    }

    public String getDocumentKey() {
        //KimIntegration : Verify
        //return Permissionable.TIME_AND_MONEY_KEY;
        return Permissionable.AWARD_KEY;
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
        return getDocumentHeader().getWorkflowDocument().stateIsEnroute() || getDocumentHeader().getWorkflowDocument().stateIsFinal();
    }
    
    public boolean isNew(){
        return documentNumber == null;
    }

    /**
     * Gets the awardNumber attribute. 
     * @return Returns the awardNumber.
     */
    public String getAwardNumber() {
        return awardNumber;
    }

    /**
     * Sets the awardNumber attribute value.
     * @param awardNumber The awardNumber to set.
     */
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    /**
     * Gets the awardHierarchyNodes attribute. 
     * @return Returns the awardHierarchyNodes.
     */
    public Map<String, AwardHierarchyNode> getAwardHierarchyNodes() {
        return awardHierarchyNodes;
    }

    /**
     * Sets the awardHierarchyNodes attribute value.
     * @param awardHierarchyNodes The awardHierarchyNodes to set.
     */
    public void setAwardHierarchyNodes(Map<String, AwardHierarchyNode> awardHierarchyNodes) {
        this.awardHierarchyNodes = awardHierarchyNodes;
    }    

    /**
     * Gets the pendingTransactions attribute. 
     * @return Returns the pendingTransactions.
     */
    public List<PendingTransaction> getPendingTransactions() {
        Collections.sort(pendingTransactions, new PendingTransactionComparator());
        return pendingTransactions;
    }

    /**
     * Sets the pendingTransactions attribute value.
     * @param pendingTransactions The pendingTransactions to set.
     */
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
     * Gets the timeAndMoneyHistory attribute. 
     * @return Returns the timeAndMoneyHistory.
     */
    public Map<Object, Object> getTimeAndMoneyHistory() {
        return timeAndMoneyHistory;
    }

    /**
     * Sets the timeAndMoneyHistory attribute value.
     * @param timeAndMoneyHistory The timeAndMoneyHistory to set.
     */
    public void setTimeAndMoneyHistory(Map<Object, Object> timeAndMoneyHistory) {
        this.timeAndMoneyHistory = timeAndMoneyHistory;
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
}
