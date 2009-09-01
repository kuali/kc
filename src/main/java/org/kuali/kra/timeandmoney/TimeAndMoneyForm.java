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
package org.kuali.kra.timeandmoney;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistributionBean;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.transactions.TransactionBean;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.datadictionary.DocumentEntry;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.util.GlobalVariables;

public class TimeAndMoneyForm extends KraTransactionalDocumentFormBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2737159069734793860L;
    private TransactionBean transactionBean;
    private AwardDirectFandADistributionBean awardDirectFandADistributionBean;
    private String goToAwardNumber;
    private List<String> order;
    private List<Integer> columnSpan;
    private String[] obligationStartDates;
    private String[] obligationExpirationDates;
    private String[] finalExpirationDates;
    private String awardHierarchy;
    private String awardNumber;
    private String addRA;    
    private String deletedRas;
    private String controlForAwardHierarchyView;
    
    public TimeAndMoneyForm() {
        super();        
        this.setDocument(new TimeAndMoneyDocument());
        initialize();        
    }
    
    public void initialize() {
        initializeHeaderNavigationTabs();
        transactionBean = new TransactionBean(this);
        awardDirectFandADistributionBean = new AwardDirectFandADistributionBean(this);
        order = new ArrayList<String>();
        columnSpan = new ArrayList<Integer>();
        obligationStartDates = new String[100];
        obligationExpirationDates = new String[100];
        finalExpirationDates = new String[100];
    }
    
    /**
     * 
     * This method initializes either the document or the form based on the command value.
     */
    public void initializeFormOrDocumentBasedOnCommand(){
        if (KEWConstants.INITIATE_COMMAND.equals(getCommand())) {
            getTimeAndMoneyDocument().initialize();
        }else{
            initialize();
        }
    }
    
    /**
     * 
     * This method returns the TimeAndMoneyDocument object.
     * @return
     */
    public TimeAndMoneyDocument getTimeAndMoneyDocument() {
        return (TimeAndMoneyDocument) super.getDocument();
    }

    @Override
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_TIME_AND_MONEY;
    }

    @Override
    protected void setSaveDocumentControl(Map editMode) {
        // TODO Auto-generated method stub
        
    }
    
 // TODO Overriding for 1.1 upgrade 'till we figure out how to actually use this
    public boolean shouldMethodToCallParameterBeUsed(String methodToCallParameterName, String methodToCallParameterValue, HttpServletRequest request) {
        
        return true;
    }
    
    /**
     * 
     * This method initializes the loads the header navigation tabs.
     */
    protected void initializeHeaderNavigationTabs(){
        DataDictionaryService dataDictionaryService = getDataDictionaryService();
        DocumentEntry docEntry = dataDictionaryService.getDataDictionary().getDocumentEntry(
                org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument.class.getName());
        List<HeaderNavigation> navList = docEntry.getHeaderNavigationList();
        HeaderNavigation[] list = new HeaderNavigation[navList.size()];
        navList.toArray(list);
        super.setHeaderNavigationTabs(list); 
    }
    
    /**
     * 
     * This method is a wrapper method for getting DataDictionary Service using the Service Locator.
     * @return
     */
    protected DataDictionaryService getDataDictionaryService(){
        return (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
    }    
    
    /**
     * Gets the transactionBean attribute. 
     * @return Returns the transactionBean.
     */
    public TransactionBean getTransactionBean() {
        return transactionBean;
    }

    /**
     * Sets the transactionBean attribute value.
     * @param transactionBean The transactionBean to set.
     */
    public void setTransactionBean(TransactionBean transactionBean) {
        this.transactionBean = transactionBean;
    }

    /**
     * Gets the goToAwardNumber attribute. 
     * @return Returns the goToAwardNumber.
     */
    public String getGoToAwardNumber() {
        return goToAwardNumber;
    }

    /**
     * Sets the goToAwardNumber attribute value.
     * @param goToAwardNumber The goToAwardNumber to set.
     */
    public void setGoToAwardNumber(String goToAwardNumber) {
        this.goToAwardNumber = goToAwardNumber;
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
     * Gets the columnSpan attribute. 
     * @return Returns the columnSpan.
     */
    public List<Integer> getColumnSpan() {
        return columnSpan;
    }

    /**
     * Sets the columnSpan attribute value.
     * @param columnSpan The columnSpan to set.
     */
    public void setColumnSpan(List<Integer> columnSpan) {
        this.columnSpan = columnSpan;
    }    
    
    public String getAwardHierarchy() {
        awardHierarchy = "";
        if (StringUtils.isNotBlank(addRA) && addRA.equals("Y")) {
            if (getAwardHierarchyUIService().doesAwardHierarchyExist(awardNumber, deletedRas)) {
                setAwardHierarchy("<h3>true</h3>");
            }else {
                setAwardHierarchy("<h3>false</h3>");
            }
        } else if (StringUtils.isNotBlank(addRA) && addRA.equals("S")) {
            //KraServiceLocator.getService(AwardHierarchyUIService.class).saveResearchAreas(sqlScripts);
            String error = (String) GlobalVariables.getUserSession().retrieveObject("raError");
            if (StringUtils.isNotBlank(error)) {
                setAwardHierarchy("<h3>" + error + "</h3>");
                GlobalVariables.getUserSession().addObject("raError", (Object) null);
            } else {
                setAwardHierarchy("<h3>Success</h3>");
            }
        } else if (awardNumber!=null && StringUtils.isNotBlank(addRA) && addRA.equals("E")){
            setAwardHierarchy(getAwardHierarchyUIService().getSubAwardHierarchiesForTreeView(awardNumber));
        } else if (awardNumber!=null && StringUtils.isNotBlank(addRA) && addRA.equals("N")){
            setAwardHierarchy(getAwardHierarchyUIService().getRootAwardNode(awardNumber));
        }
        return awardHierarchy;
    }

    /**
     * This method...
     * @return
     */
    private AwardHierarchyUIService getAwardHierarchyUIService() {
        return KraServiceLocator.getService(AwardHierarchyUIService.class);
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
     * Gets the addRA attribute. 
     * @return Returns the addRA.
     */
    public String getAddRA() {
        return addRA;
    }

    /**
     * Sets the addRA attribute value.
     * @param addRA The addRA to set.
     */
    public void setAddRA(String addRA) {
        this.addRA = addRA;
    }

    /**
     * Gets the deletedRas attribute. 
     * @return Returns the deletedRas.
     */
    public String getDeletedRas() {
        return deletedRas;
    }

    /**
     * Sets the deletedRas attribute value.
     * @param deletedRas The deletedRas to set.
     */
    public void setDeletedRas(String deletedRas) {
        this.deletedRas = deletedRas;
    }

    /**
     * Sets the awardHierarchy attribute value.
     * @param awardHierarchy The awardHierarchy to set.
     */
    public void setAwardHierarchy(String awardHierarchy) {
        this.awardHierarchy = awardHierarchy;
    }
    
    /**
     * Gets the awardDirectFandADistributionBean attribute. 
     * @return Returns the awardDirectFandADistributionBean.
     */
    public AwardDirectFandADistributionBean getAwardDirectFandADistributionBean() {
        return awardDirectFandADistributionBean;
    }

    /**
     * Sets the awardDirectFandADistributionBean attribute value.
     * @param awardDirectFandADistributionBean The awardDirectFandADistributionBean to set.
     */
    public void setAwardDirectFandADistributionBean(AwardDirectFandADistributionBean awardDirectFandADistributionBean) {
        this.awardDirectFandADistributionBean = awardDirectFandADistributionBean;
    }

    /**
     * Gets the controlForAwardHierarchyView attribute. 
     * @return Returns the controlForAwardHierarchyView.
     */
    public String getControlForAwardHierarchyView() {
        return controlForAwardHierarchyView;
    }

    /**
     * Sets the controlForAwardHierarchyView attribute value.
     * @param controlForAwardHierarchyView The controlForAwardHierarchyView to set.
     */
    public void setControlForAwardHierarchyView(String controlForAwardHierarchyView) {
        this.controlForAwardHierarchyView = controlForAwardHierarchyView;
    }

    /**
     * Gets the obligationStartDates attribute. 
     * @return Returns the obligationStartDates.
     */
    public String[] getObligationStartDates() {
        return obligationStartDates;
    }

    /**
     * Sets the obligationStartDates attribute value.
     * @param obligationStartDates The obligationStartDates to set.
     */
    public void setObligationStartDates(String[] obligationStartDates) {
        this.obligationStartDates = obligationStartDates;
    }

    /**
     * Gets the obligationExpirationDates attribute. 
     * @return Returns the obligationExpirationDates.
     */
    public String[] getObligationExpirationDates() {
        return obligationExpirationDates;
    }

    /**
     * Sets the obligationExpirationDates attribute value.
     * @param obligationExpirationDates The obligationExpirationDates to set.
     */
    public void setObligationExpirationDates(String[] obligationExpirationDates) {
        this.obligationExpirationDates = obligationExpirationDates;
    }

    /**
     * Gets the finalExpirationDates attribute. 
     * @return Returns the finalExpirationDates.
     */
    public String[] getFinalExpirationDates() {
        return finalExpirationDates;
    }

    /**
     * Sets the finalExpirationDates attribute value.
     * @param finalExpirationDates The finalExpirationDates to set.
     */
    public void setFinalExpirationDates(String[] finalExpirationDates) {
        this.finalExpirationDates = finalExpirationDates;
    }

}
