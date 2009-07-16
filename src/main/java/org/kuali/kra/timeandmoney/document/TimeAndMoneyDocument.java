/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.bo.RolePersons;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.rice.kns.document.Copyable;
import org.kuali.rice.kns.document.SessionDocument;

/**
 * 
 * This class represents the Time and Money Document Object.
 * 
 */
public class TimeAndMoneyDocument extends ResearchDocumentBase implements  Copyable, SessionDocument, Permissionable{
    
    public static final String DOCUMENT_TYPE_CODE = "TAMD";
    
    private String awardNumber;
    private Map<String, AwardHierarchyNode> awardHierarchyNodes;
    private Map<String, AwardHierarchy> awardHierarchyItems;
    private List<PendingTransaction> pendingTransactions;
    
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
        return Permissionable.TIME_AND_MONEY_KEY;
    }

    public String getDocumentNumberForPermission() {
        // TODO Auto-generated method stub
        return documentNumber;
    }

    public List<String> getRoleNames() {
        List<String> roles = new ArrayList<String>();
        return roles;
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
}
