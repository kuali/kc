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
package org.kuali.kra.timeandmoney;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentFormBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistributionBean;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.award.service.AwardHierarchyUIService;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.transactions.TransactionBean;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.util.AutoPopulatingList;

import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.util.*;

public class TimeAndMoneyForm extends KcTransactionalDocumentFormBase {


    private static final String DOCUMENT_STATUS = "documentStatus";
	private static final String ROOT_AWARD_NUMBER = "rootAwardNumber";
	public static final String COLUMN = ":";
    private static final int NUMBER_30 = 30;
    public static final String UPDATE_TIMESTAMP_DD_NAME = "DataDictionary.Award.attributes.updateTimestamp";
    public static final String SPONSOR_DD_NAME = "DataDictionary.Sponsor.attributes.sponsorName";
    private static final long serialVersionUID = 2737159069734793860L;
    private TransactionBean transactionBean;
    private AwardDirectFandADistributionBean awardDirectFandADistributionBean;
    private String goToAwardNumber;
    private List<String> order;
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
    
    public final static String PENDING = "1";
    public final static String CURRENT = "0";
    
    private List<String> fieldsInError;
    
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
        obligationStartDates = new AutoPopulatingList<String>(String.class);
        obligationExpirationDates = new AutoPopulatingList<String>(String.class);
        finalExpirationDates = new AutoPopulatingList<String>(String.class);
        awardHierarchyNodeItems = new AutoPopulatingList<AwardHierarchyNode>(AwardHierarchyNode.class);
        
        setControlForAwardHierarchyView("2");
        setToCurrentView();
        setDirectIndirectViewEnabled(getParameterService().getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, Constants.PARAMETER_COMPONENT_DOCUMENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST"));
        previousNodeMap = new HashMap<String, String>();
        nextNodeMap = new HashMap<String, String>();
        awardHierarchyToggle = new TreeMap<String, String>();
        fieldsInError = new ArrayList<String>();
    }
    
    @Override
    protected String getDefaultDocumentTypeName() {
        return "TimeAndMoneyDocument";
    }
    
    /**
     * 
     * This method initializes either the document or the form based on the command value.
     */
    public void initializeFormOrDocumentBasedOnCommand(){
        if (KewApiConstants.INITIATE_COMMAND.equals(getCommand())) {
            getTimeAndMoneyDocument().initialize();
        }else{
            initialize();
        }
    }
    
    /**
     * @see org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase#populate(javax.servlet.http.HttpServletRequest)
     * Overriding populate method so that we can register editable properties in form base.  htmlControlAttribute registers
     * these fields and the form base does validation on them.  We are using jQuery for Award Hierarchy view in Award and T&amp;M, and
     * we need to register these properties explicitly before we call populate.
     */
    @Override
    public void populate(HttpServletRequest request) {
        this.registerEditableProperty("controlForAwardHierarchyView");
        this.registerEditableProperty("currentOrPendingView");
        this.registerEditableProperty("directIndirectViewEnabled");
        super.populate(request);
    }
    
    public boolean isPropertyEditable(String propertyName) {
        if (propertyName.startsWith("awardHierarchyNodeItems[")) {
            return true;
        } else {
            return super.isPropertyEditable(propertyName);
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

        
    }
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }
    
    public AwardVersionService getAwardVersionService() {
        return KcServiceLocator.getService(AwardVersionService.class);
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

    private AwardHierarchyUIService getAwardHierarchyUIService() {
        return KcServiceLocator.getService(AwardHierarchyUIService.class);
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
        return this.getDocument().getDocumentHeader().getWorkflowDocument().isCanceled() ||
                this.getDocument().getDocumentHeader().getWorkflowDocument().isFinal();
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
    
    public void setToCurrentView() {
        this.currentOrPendingView = CURRENT;
    }

    public void setToPendingView() {
        this.currentOrPendingView = PENDING;
    }
    
    public boolean getDisableCurrentValues () {
        if (!isAwardObligatedAndAnticipatedAmountsEditable()) {
            return true;
        } else {
            return StringUtils.equals(CURRENT, getCurrentOrPendingView()) && !getTimeAndMoneyDocument().getPendingTransactions().isEmpty();
        }
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
        String generatePeriodImage = lookupKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "tinybutton1-returntoaward.gif";
        
        addExtraButton("methodToCall.returnToAward", generatePeriodImage, "Return to Award");
        
        return extraButtons;
    }
    

    private ConfigurationService lookupKualiConfigurationService() {
        return CoreApiServiceLocator.getKualiConfigurationService();
    }
    
    
    @Override
    @SuppressWarnings("unchecked")
    public void populateHeaderFields(WorkflowDocument workflowDocument) {

        TimeAndMoneyDocument timeAndMoneyDocument = getTimeAndMoneyDocument();
        if(timeAndMoneyDocument.getAward() == null) {
               Award award = getAwardVersionService().getWorkingAwardVersion(timeAndMoneyDocument.getRootAwardNumber());
               timeAndMoneyDocument.setAward(award);
        }
        AwardDocument awardDocument = timeAndMoneyDocument.getAward().getAwardDocument();
        getDocInfo().clear();
        getDocInfo().add(
                new HeaderField("DataDictionary.KraAttributeReferenceDummy.attributes.principalInvestigator", awardDocument
                        .getAward().getPrincipalInvestigatorName()));

        String docIdAndStatus = COLUMN;
        if (workflowDocument != null) {
            docIdAndStatus = timeAndMoneyDocument.getDocumentNumber() + COLUMN + workflowDocument.getStatus().getLabel();
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
            createDateStr = CoreApiServiceLocator.getDateTimeService().toString(awardDocument.getUpdateTimestamp(), "MM/dd/yy");
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
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
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
    
    protected KcWorkflowService getKraWorkflowService() {
        return KcServiceLocator.getService(KcWorkflowService.class);
    }
    
    @SuppressWarnings("unchecked")
    public boolean getDisplayEditButton() throws Exception {
        boolean displayEditButton = Boolean.FALSE;
        

        Map<String, Object> fieldValues = new HashMap<String, Object>();
        String rootAwardNumber = getTimeAndMoneyDocument().getRootAwardNumber();
        fieldValues.put(ROOT_AWARD_NUMBER, rootAwardNumber);
        fieldValues.put(DOCUMENT_STATUS, VersionStatus.ACTIVE.toString());
        TimeAndMoneyDocument activeTimeAndMoney = 
        		KcServiceLocator.getService(BusinessObjectService.class).findMatching(TimeAndMoneyDocument.class, fieldValues)
        		.stream().findFirst().orElse(null);

        
        if(activeTimeAndMoney != null) {
            displayEditButton = (activeTimeAndMoney.getDocumentNumber().equals(this.getTimeAndMoneyDocument().getDocumentNumber()));
        }
        return displayEditButton;
    }
    
    @Override
    public List<ExtraButton> getExtraButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
        String reloadImage = lookupKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_reload.gif";
        //addExtraButton("methodToCall.reload", reloadImage, "Reload");
        addExtraButton("methodToCall.reload", reloadImage, null);
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

    public List<String> getFieldsInError() {
        return fieldsInError;
    }

    public void setFieldsInError(List<String> fieldsInError) {
        this.fieldsInError = fieldsInError;
    }
    
    public boolean getIsFieldInErrorList(String fieldName) {
        boolean returnValue = this.getFieldsInError().contains(fieldName);
        return returnValue;
    }
    
    public String getFieldsInErrorList() {
        StringBuffer sb = new StringBuffer("foo");
        for (String s : this.getFieldsInError()) {
            sb.append(",").append(s);
        }
        return sb.toString();
    }

    public boolean isAwardObligatedAndAnticipatedAmountsEditable() {
        return getParameterService().getParameterValueAsBoolean(Constants.PARAMETER_MODULE_AWARD,
                ParameterConstants.DOCUMENT_COMPONENT, Constants.MAKE_AWD_CUM_ANTICIPATED_OBL_EDITABLE);
    }
}
