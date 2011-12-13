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
package org.kuali.kra.timeandmoney;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistributionBean;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.kra.timeandmoney.transactions.TransactionBean;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.ParameterConstants;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

public class TimeAndMoneyForm extends KraTransactionalDocumentFormBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    public static final String COLUMN = ":";
    private static final int NUMBER_30 = 30;
    public static final String UPDATE_TIMESTAMP_DD_NAME = "DataDictionary.Award.attributes.updateTimestamp";
    public static final String SPONSOR_DD_NAME = "DataDictionary.Sponsor.attributes.sponsorName";
    private static final long serialVersionUID = 2737159069734793860L;
    private TransactionBean transactionBean;
    private AwardDirectFandADistributionBean awardDirectFandADistributionBean;
    private String goToAwardNumber;
    private List<String> order;
    private List<Integer> columnSpan;
    private List<String> obligationStartDates;
    private List<String> obligationExpirationDates;
    private List<String> finalExpirationDates;
    private List<AwardHierarchyNode> awardHierarchyNodeItems;
    private String awardHierarchy;
    private String awardNumber;
    private String addRA;    
    private String deletedRas;
    private String controlForAwardHierarchyView;
    private String currentOrPendingView;
    private String directIndirectViewEnabled;
    private Map<String, String> previousNodeMap;
    private Map<String, String> nextNodeMap;
    private Award awardForSummaryPanelDisplay;

    private transient ParameterService parameterService;
    
    private String currentAwardNumber;
    private String currentSeqNumber;
    
    private Map<String, String> awardHierarchyToggle;
    private String awardHierarchyScrollPosition;
    
    public String getCurrentAwardNumber() {
        return currentAwardNumber;
    }

    public void setCurrentAwardNumber(String currentAwardNumber) {
        this.currentAwardNumber = currentAwardNumber;
    }

    public String getCurrentSeqNumber() {
        return currentSeqNumber;
    }

    public void setCurrentSeqNumber(String currentSeqNumber) {
        this.currentSeqNumber = currentSeqNumber;
    }

    public TimeAndMoneyForm() {
        super();
        initialize();        
    }
    
    public void initialize() {
        transactionBean = new TransactionBean(this);
        awardDirectFandADistributionBean = new AwardDirectFandADistributionBean(this);
        order = new ArrayList<String>();
        columnSpan = new ArrayList<Integer>();
        obligationStartDates = new ArrayList<String>();        
        obligationExpirationDates = new ArrayList<String>();
        finalExpirationDates = new ArrayList<String>();
        awardHierarchyNodeItems = new ArrayList<AwardHierarchyNode>();
        for(int i=0;i<100;i++){
            obligationStartDates.add(null);
            obligationExpirationDates.add(null);
            finalExpirationDates.add(null);
            awardHierarchyNodeItems.add(new AwardHierarchyNode());
        }
        setControlForAwardHierarchyView("2");
        setCurrentOrPendingView("0");
        setDirectIndirectViewEnabled(getParameterService().getParameterValue(Constants.PARAMETER_MODULE_AWARD, Constants.PARAMETER_COMPONENT_DOCUMENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST"));
        previousNodeMap = new HashMap<String, String>();
        nextNodeMap = new HashMap<String, String>();
        awardHierarchyToggle = new TreeMap<String, String>();
    }
    
    /** {@inheritDoc} */
    @Override
    protected String getDefaultDocumentTypeName() {
        return "TimeAndMoneyDocument";
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
     * @see org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase#populate(javax.servlet.http.HttpServletRequest)
     * Overriding populate method so that we can register editable properties in form base.  htmlControlAttribute registers
     * these fields and the form base does validation on them.  We are using jQuery for Award Hierarchy view in Award and T&M, and
     * we need to register these properties explicitly before we call populate.
     */
    @Override
    public void populate(HttpServletRequest request) {
        this.registerEditableProperty("controlForAwardHierarchyView");
        this.registerEditableProperty("currentOrPendingView");
        this.registerEditableProperty("directIndirectViewEnabled");
        registerHierarchyNodeDates();
        super.populate(request);
    }
    
    /**
     * This method registers the dates for each node that is being sent to the request on save.  Only nodes that are expanded in the 
     * view will be sent.
     */
    public void registerHierarchyNodeDates() {
        int temp = 1;
        for (AwardHierarchyNode awardHierarchyNode : awardHierarchyNodeItems) {
            this.registerEditableProperty("awardHierarchyNodeItems[" + temp + "].currentFundEffectiveDate");
            this.registerEditableProperty("awardHierarchyNodeItems[" + temp + "].finalExpirationDate");
            this.registerEditableProperty("awardHierarchyNodeItems[" + temp + "].obligationExpirationDate");
            this.registerEditableProperty("awardHierarchyNodeItems[" + temp + "].amountObligatedToDate");
            this.registerEditableProperty("awardHierarchyNodeItems[" + temp + "].anticipatedTotalAmount");
            this.registerEditableProperty("awardHierarchyNodeItems[" + temp + "].obligatedTotalDirect");
            this.registerEditableProperty("awardHierarchyNodeItems[" + temp + "].obligatedTotalIndirect");
            this.registerEditableProperty("awardHierarchyNodeItems[" + temp + "].anticipatedTotalDirect");
            this.registerEditableProperty("awardHierarchyNodeItems[" + temp + "].anticipatedTotalIndirect");
            temp++;
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
    
    /**
     * 
     * This method is a wrapper method for getting DataDictionary Service using the Service Locator.
     * @return
     */
    protected DataDictionaryService getDataDictionaryService(){
        return (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
    }    
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }
    
    /**
     * Gets the ActivePendingTransactionsService attribute. 
     * @return Returns the ActivePendingTransactionsService.
     */
    public ActivePendingTransactionsService getActivePendingTransactionsService() {
        return KraServiceLocator.getService(ActivePendingTransactionsService.class);
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
    
    
    public boolean isCancelOrFinalStatus () {
        return this.getDocument().getDocumentHeader().getWorkflowDocument().stateIsCanceled() ||
                this.getDocument().getDocumentHeader().getWorkflowDocument().stateIsFinal();
    }

    public boolean isInSingleNodeHierarchy () {
        boolean returnValue = false;
        if (getOrder().size() == 1) {
            returnValue = true;
            setControlForAwardHierarchyView("2");
        }
        return returnValue;
    }
    
    public boolean isInMultipleNodeHierarchy () {
        boolean returnValue = false;
        if (getOrder().size() > 1) {
            returnValue = true;
        }
        return returnValue;
    }
    
    public boolean isRootNode() {
        boolean returnVal = false;
        String awardNumber = getAwardForSummaryPanelDisplay().getAwardNumber();
        if(getPreviousNodeMap().get(awardNumber).equals(Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)) {
            returnVal = true;
        }
        return returnVal;
    }
    
    public boolean isLastNode() {
        boolean returnVal = false;
        String awardNumber = getAwardForSummaryPanelDisplay().getAwardNumber();
        if(getNextNodeMap().get(awardNumber).equals(Constants.LAST_NODE_NEXT_VALUE)) {
            returnVal = true;
        }
        return returnVal;
    }
    
    

    /**
     * Gets the awardForSummaryPanelDisplay attribute. 
     * @return Returns the awardForSummaryPanelDisplay.
     */
    public Award getAwardForSummaryPanelDisplay() {
        return awardForSummaryPanelDisplay;
    }

    /**
     * Sets the awardForSummaryPanelDisplay attribute value.
     * @param awardForSummaryPanelDisplay The awardForSummaryPanelDisplay to set.
     */
    public void setAwardForSummaryPanelDisplay(Award awardForSummaryPanelDisplay) {
        this.awardForSummaryPanelDisplay = awardForSummaryPanelDisplay;
    }

    /**
     * Gets the obligationStartDates attribute. 
     * @return Returns the obligationStartDates.
     */
    public List<String> getObligationStartDates() {
        return obligationStartDates;
    }

    /**
     * Sets the obligationStartDates attribute value.
     * @param obligationStartDates The obligationStartDates to set.
     */
    public void setObligationStartDates(List<String> obligationStartDates) {
        this.obligationStartDates = obligationStartDates;
    }

    /**
     * Gets the obligationExpirationDates attribute. 
     * @return Returns the obligationExpirationDates.
     */
    public List<String> getObligationExpirationDates() {
        return obligationExpirationDates;
    }

    /**
     * Sets the obligationExpirationDates attribute value.
     * @param obligationExpirationDates The obligationExpirationDates to set.
     */
    public void setObligationExpirationDates(List<String> obligationExpirationDates) {
        this.obligationExpirationDates = obligationExpirationDates;
    }

    /**
     * Gets the finalExpirationDates attribute. 
     * @return Returns the finalExpirationDates.
     */
    public List<String> getFinalExpirationDates() {
        return finalExpirationDates;
    }

    /**
     * Sets the finalExpirationDates attribute value.
     * @param finalExpirationDates The finalExpirationDates to set.
     */
    public void setFinalExpirationDates(List<String> finalExpirationDates) {
        this.finalExpirationDates = finalExpirationDates;
    }

    /**
     * Gets the awardHierarchyNodeItems attribute. 
     * @return Returns the awardHierarchyNodeItems.
     */
    public List<AwardHierarchyNode> getAwardHierarchyNodeItems() {
        return awardHierarchyNodeItems;
    }

    /**
     * Sets the awardHierarchyNodeItems attribute value.
     * @param awardHierarchyNodeItems The awardHierarchyNodeItems to set.
     */
    public void setAwardHierarchyNodeItems(List<AwardHierarchyNode> awardHierarchyNodeItems) {
        this.awardHierarchyNodeItems = awardHierarchyNodeItems;
    }
    
    
    
    
    /**
     * Gets the previousNodeMap attribute. 
     * @return Returns the previousNodeMap.
     */
    public Map<String, String> getPreviousNodeMap() {
        return previousNodeMap;
    }

    /**
     * Sets the previousNodeMap attribute value.
     * @param previousNodeMap The previousNodeMap to set.
     */
    public void setPreviousNodeMap(Map<String, String> previousNodeMap) {
        this.previousNodeMap = previousNodeMap;
    }

    /**
     * Gets the nextNodeMap attribute. 
     * @return Returns the nextNodeMap.
     */
    public Map<String, String> getNextNodeMap() {
        return nextNodeMap;
    }

    /**
     * Sets the nextNodeMap attribute value.
     * @param nextNodeMap The nextNodeMap to set.
     */
    public void setNextNodeMap(Map<String, String> nextNodeMap) {
        this.nextNodeMap = nextNodeMap;
    }

    
    public String getAwardHierarchy() throws ParseException {
        awardHierarchy = "";
        if(StringUtils.isBlank(awardNumber)){
            awardNumber = this.getTimeAndMoneyDocument().getRootAwardNumber();
        }
        
        if (awardNumber!=null && StringUtils.isNotBlank(addRA) && addRA.equals("E")){
            setAwardHierarchy(getAwardHierarchyUIService().getSubAwardHierarchiesForTreeView(awardNumber, currentAwardNumber, currentSeqNumber));
        } else if (awardNumber!=null && StringUtils.isNotBlank(addRA) && addRA.equals("N")){
            setAwardHierarchy(getAwardHierarchyUIService().getRootAwardNode(awardNumber, currentAwardNumber, currentSeqNumber));
        }
        return awardHierarchy;
    }
    
    
    
    public void setAwardHierarchy(String awardHierarchy) {
        this.awardHierarchy = awardHierarchy;
    }

    /**
     * Gets the currentOrPendingView attribute. 
     * @return Returns the currentOrPendingView.
     */
    public String getCurrentOrPendingView() {
        return currentOrPendingView;
    }
    

    /**
     * Sets the currentOrPendingView attribute value.
     * @param currentOrPendingView The currentOrPendingView to set.
     */
    public void setCurrentOrPendingView(String currentOrPendingView) {
        this.currentOrPendingView = currentOrPendingView;
    }
    
    public List<ExtraButton> getExtraTopButtons() {
        extraButtons.clear();
        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
        String generatePeriodImage = lookupKualiConfigurationService().getPropertyString(externalImageURL) + "tinybutton1-returntoaward.gif";
        
        addExtraButton("methodToCall.returnToAward", generatePeriodImage, "Return to Award");
        
        return extraButtons;
    }
    
    /**
     * This method does what its name says
     * @return
     */
    private KualiConfigurationService lookupKualiConfigurationService() {
        return KraServiceLocator.getService(KualiConfigurationService.class);
    }
    
    
    /**
     * 
     * @see org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase#populateHeaderFields(org.kuali.rice.kns.workflow.service.KualiWorkflowDocument)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void populateHeaderFields(KualiWorkflowDocument workflowDocument) {
        // super.populateHeaderFields(workflowDocument);

        TimeAndMoneyDocument timeAndMoneyDocument = getTimeAndMoneyDocument();
        if(timeAndMoneyDocument.getAward() == null) {
//            Map<String, String> map = new HashMap<String,String>();
//            map.put("awardNumber", timeAndMoneyDocument.getRootAwardNumber());
//            List<Award> awardList = (List<Award>) getBusinessObjectService().findMatching(Award.class, map);
//            timeAndMoneyDocument.setAward(awardList.get(0)); 
               Award award = getActivePendingTransactionsService().getWorkingAwardVersion(timeAndMoneyDocument.getRootAwardNumber());
               timeAndMoneyDocument.setAward(award);
        }
        AwardDocument awardDocument = timeAndMoneyDocument.getAward().getAwardDocument();
        getDocInfo().clear();
        getDocInfo().add(
                new HeaderField("DataDictionary.KraAttributeReferenceDummy.attributes.principalInvestigator", awardDocument
                        .getAward().getPrincipalInvestigatorName()));

        String docIdAndStatus = COLUMN;
        if (workflowDocument != null) {
            docIdAndStatus = timeAndMoneyDocument.getDocumentNumber() + COLUMN + workflowDocument.getStatusDisplayValue();
        }
        getDocInfo().add(new HeaderField("DataDictionary.Award.attributes.docIdStatus", docIdAndStatus));
        String unitName = awardDocument.getAward().getUnitName();
        if (StringUtils.isNotBlank(unitName) && unitName.length() > NUMBER_30) {
            unitName = unitName.substring(0, NUMBER_30);
        }
        getDocInfo().add(new HeaderField("DataDictionary.AwardPersonUnit.attributes.leadUnit", unitName));
        getDocInfo().add(new HeaderField("DataDictionary.Award.attributes.awardIdAccount", getAwardIdAccount(awardDocument)));

        setupSponsor(awardDocument);
        setupLastUpdate(awardDocument);

    }
    
    private String getAwardIdAccount(AwardDocument awardDocument) {
        String awardNum = awardDocument.getAward().getAwardNumber();
        String account = awardDocument.getAward().getAccountNumber() != null ? awardDocument.getAward().getAccountNumber()
                : Constants.EMPTY_STRING;
        return awardNum + COLUMN + account;
    }

    private void setupLastUpdate(AwardDocument awardDocument) {
        String createDateStr = null;
        String updateUser = null;
        if (awardDocument.getUpdateTimestamp() != null) {
            createDateStr = KNSServiceLocator.getDateTimeService().toString(awardDocument.getUpdateTimestamp(), "MM/dd/yy");
            updateUser = awardDocument.getUpdateUser().length() > NUMBER_30 ? awardDocument.getUpdateUser().substring(0, NUMBER_30)
                    : awardDocument.getUpdateUser();
            getDocInfo().add(
                    new HeaderField(UPDATE_TIMESTAMP_DD_NAME, createDateStr + " by " + updateUser));
        } else {
            getDocInfo().add(new HeaderField(UPDATE_TIMESTAMP_DD_NAME, Constants.EMPTY_STRING));
        }

    }


    private void setupSponsor(AwardDocument awardDocument) {
        if (awardDocument.getAward().getSponsor() == null) {
            getDocInfo().add(new HeaderField(SPONSOR_DD_NAME, ""));
        } else {
            String sponsorName = awardDocument.getAward().getSponsorName();
            if (StringUtils.isNotBlank(sponsorName) && sponsorName.length() > NUMBER_30) {
                sponsorName = sponsorName.substring(0, NUMBER_30);
            }
            getDocInfo().add(new HeaderField(SPONSOR_DD_NAME, sponsorName));
        }

    }
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
    
    /**
     * Gets the directIndirectViewEnabled attribute. 
     * @return Returns the directIndirectViewEnabled.
     */
    public String getDirectIndirectViewEnabled() {
        return directIndirectViewEnabled;
    }

    /**
     * Sets the directIndirectViewEnabled attribute value.
     * @param directIndirectViewEnabled The directIndirectViewEnabled to set.
     */
    public void setDirectIndirectViewEnabled(String directIndirectViewEnabled) {
        this.directIndirectViewEnabled = directIndirectViewEnabled;
    }
    
    protected KraWorkflowService getKraWorkflowService() {
        return KraServiceLocator.getService(KraWorkflowService.class);
    }
    
    @SuppressWarnings("unchecked")
    public boolean getDisplayEditButton() throws Exception {
        boolean displayEditButton = Boolean.FALSE;
        

        Map<String, Object> fieldValues = new HashMap<String, Object>();
        String rootAwardNumber = getTimeAndMoneyDocument().getRootAwardNumber();
        fieldValues.put("rootAwardNumber", rootAwardNumber);
        //DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);

        List<TimeAndMoneyDocument> timeAndMoneyDocuments = 
            (List<TimeAndMoneyDocument>)businessObjectService.findMatchingOrderBy(TimeAndMoneyDocument.class, fieldValues, "documentNumber", true);
        //BO service does not return workflow data, so we must call document service to retrieve the document to test if it is in workflow
        //TimeAndMoneyDocument t = timeAndMoneyDocuments.get(timeAndMoneyDocuments.size() -1);
        
        TimeAndMoneyDocument lastFinalDoc = getLastFinalTandMDocument(timeAndMoneyDocuments);
        if(lastFinalDoc != null) {
            displayEditButton = (lastFinalDoc.getDocumentHeader().getWorkflowDocument().stateIsFinal() && 
                    lastFinalDoc.getDocumentNumber().equals(this.getTimeAndMoneyDocument().getDocumentNumber()));
        }
        //TimeAndMoneyDocument timeAndMoneyDocument = (TimeAndMoneyDocument) documentService.getByDocumentHeaderId(t.getDocumentNumber());
//        displayEditButton = (timeAndMoneyDocument.getDocumentHeader().getWorkflowDocument().stateIsFinal() ||
//                            timeAndMoneyDocument.getDocumentHeader().getWorkflowDocument().stateIsCanceled()) && 
//                            timeAndMoneyDocument.getDocumentNumber().equals(this.getTimeAndMoneyDocument().getDocumentNumber());
//        if(!getKraWorkflowService().isInWorkflow(timeAndMoneyDocument)){
//            displayEditButton = Boolean.FALSE;
//        }
        return displayEditButton;
    }
    
    protected TimeAndMoneyDocument getLastFinalTandMDocument(List<TimeAndMoneyDocument> timeAndMoneyDocuments) throws WorkflowException {
        TimeAndMoneyDocument returnVal = null;
        DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
        while(timeAndMoneyDocuments.size() > 0) {
            TimeAndMoneyDocument docWithWorkFlowData = 
                (TimeAndMoneyDocument) documentService.getByDocumentHeaderId(timeAndMoneyDocuments.get(timeAndMoneyDocuments.size() - 1).getDocumentNumber());
            if(docWithWorkFlowData.getDocumentHeader().getWorkflowDocument().stateIsCanceled()) {
                timeAndMoneyDocuments.remove(timeAndMoneyDocuments.size() - 1);
            }else {
                returnVal = docWithWorkFlowData;
                break;
            }
        }
        return returnVal;
    }
    
    @Override
    public List<ExtraButton> getExtraButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
        String reloadImage = lookupKualiConfigurationService().getPropertyString(externalImageURL) + "buttonsmall_reload.gif";
        addExtraButton("methodToCall.reload", reloadImage, "Reload");
        
        return extraButtons;
    }
    
    /**
     * This is a utility method to add a new button to the extra buttons
     * collection.
     *   
     * @param property
     * @param source
     * @param altText
     */ 
    protected void addExtraButton(String property, String source, String altText){
        addExtraButton(property, source, altText,null);
    }
    /**
     * This is a utility method to add a new button to the extra buttons
     * collection.
     *   
     * @param property
     * @param source
     * @param altText
     */ 
    protected void addExtraButton(String property, String source, String altText,String extraButtonOnclick){
        
        ExtraButton newButton = new ExtraButton();
        
        newButton.setExtraButtonProperty(property);
        newButton.setExtraButtonSource(source);
        newButton.setExtraButtonAltText(altText);
        if(extraButtonOnclick!=null){
            newButton.setExtraButtonOnclick(extraButtonOnclick);
        }
        
        extraButtons.add(newButton);
    }
    
    public int getIndexOfAwardAmountInfoForDisplay() throws WorkflowException {
        return awardForSummaryPanelDisplay.getIndexOfAwardAmountInfoForDisplayFromTimeAndMoneyDocNumber(getTimeAndMoneyDocument().getDocumentNumber());
    }

    public Map<String, String> getAwardHierarchyToggle() {
        return awardHierarchyToggle;
    }

    public void setAwardHierarchyToggle(Map<String, String> awardHierarchyToggle) {
        this.awardHierarchyToggle = awardHierarchyToggle;
    }

    public String getAwardHierarchyScrollPosition() {
        return awardHierarchyScrollPosition;
    }

    public void setAwardHierarchyScrollPosition(String awardHierarchyScrollPosition) {
        this.awardHierarchyScrollPosition = awardHierarchyScrollPosition;
    }
    
    
    
    

    

}
