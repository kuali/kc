/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.budget.web.struts.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.core.document.Document;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.ActionFormUtilMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.ui.ExtraButton;
import org.kuali.core.web.ui.HeaderField;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.core.workflow.service.KualiWorkflowDocument;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetCostShare;
import org.kuali.kra.budget.bo.BudgetLineItem;
import org.kuali.kra.budget.bo.BudgetModularIdc;
import org.kuali.kra.budget.bo.BudgetModularSummary;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.BudgetProjectIncome;
import org.kuali.kra.budget.bo.BudgetSubAwards;
import org.kuali.kra.budget.bo.BudgetUnrecoveredFandA;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.web.struts.form.ProposalFormBase;
import org.kuali.rice.KNSServiceLocator;

import edu.iu.uis.eden.exception.WorkflowException;

public class BudgetForm extends ProposalFormBase {

    private static final long serialVersionUID = -8853937659597422800L;

    private static final String RETURN_TO_PROPOSAL_ALT_TEXT = "return to proposal";

    private static final String KRA_EXTERNALIZABLE_IMAGES_URI_KEY = "kra.externalizable.images.url";
    private static final String KR_EXTERNALIZABLE_IMAGES_URI_KEY = "kr.externalizable.images.url";
    private static final String RETURN_TO_PROPOSAL_METHOD_TO_CALL = "methodToCall.returnToProposal";
    public static final String VERSION_NUMBER_KEY = "DataDictionary.BudgetDocument.attributes.budgetVersionNumber";
    public static final String BUDGET_NAME_KEY = "DataDictionary.KraAttributeReferenceDummy.attributes.budgetName";
    
    private String newBudgetPersons;
    private String newBudgetRolodexes;
    private String newTbnPersons;
    
    private BudgetPeriod newBudgetPeriod;
    private List<BudgetLineItem> newBudgetLineItems;   
    private BudgetLineItem newPersonnelLineItem;   
    private Integer newBudgetPeriodNumber = Integer.valueOf(0);    
    
	private BudgetCostShare newBudgetCostShare;
	private BudgetProjectIncome newBudgetProjectIncome;
    private BudgetUnrecoveredFandA newBudgetUnrecoveredFandA;
    private BudgetModularIdc newBudgetModularIdc;
    private BudgetModularSummary budgetModularSummary;
    
    private BudgetJustificationWrapper budgetJustificationWrapper;
    
    private BudgetDecimal costSharingAmount;
    
    private List<ExtraButton> extraTopButtons;

    private Integer viewBudgetView;
    private Integer viewBudgetPeriod;
    private String viewLocation;
    
    private Integer modularSelectedPeriod;
            
    private boolean documentNextValueRefresh;
    private boolean saveAfterCopy;
    
    private String personnelBudgetViewMode;
    private BudgetLineItem selectedBudgetLineItem;
    private BudgetPersonnelDetails newBudgetPersonnelDetails;
    private Integer selectedBudgetLineItemIndex;
    private String prevOnOffCampusFlag;
    private boolean updateFinalVersion;
    
    private String ohRateClassCodePrevValue;
    private String urRateClassCodePrevValue;
    
    private String[] selectedBudgetPrintFormId;
    private String syncBudgetRate;
    private BudgetSubAwards newSubAward;
    private Integer personnelDetailLine;
    private FormFile subAwardFile;
    
    private String newGroupName;
    
    public String getOhRateClassCodePrevValue() {
        return ohRateClassCodePrevValue;
    }

    public void setOhRateClassCodePrevValue(String ohRateClassCodePrevValue) {
        this.ohRateClassCodePrevValue = ohRateClassCodePrevValue;
    }

    public boolean isDocumentNextValueRefresh() {
        return documentNextValueRefresh;
    }

    public void setDocumentNextValueRefresh(boolean documentNextValueRefresh) {
        this.documentNextValueRefresh = documentNextValueRefresh;
    }

    public Integer getViewBudgetPeriod() {
        return viewBudgetPeriod;
    }

    public void setViewBudgetPeriod(Integer viewBudgetPeriod) {
        this.viewBudgetPeriod = viewBudgetPeriod;
    }

    public String getViewLocation() {
        return viewLocation;
    }

    public void setViewLocation(String viewLocation) {
        this.viewLocation = viewLocation;
    }

    public BudgetForm() {
        super();
        this.setDocument(new BudgetDocument());
        initialize();        
    }

    /**
     * 
     * This method initialize all form variables
     */
    public void initialize() {
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
        this.setHeaderNavigationTabs((dataDictionaryService.getDataDictionary().getDocumentEntry(org.kuali.kra.budget.document.BudgetDocument.class.getName())).getHeaderTabNavigation());
        setNewBudgetPeriod(new BudgetPeriod());
        
        configureExtraTopButtons();
        
        newBudgetProjectIncome = new BudgetProjectIncome();
        newBudgetCostShare = new BudgetCostShare();
        newBudgetUnrecoveredFandA = new BudgetUnrecoveredFandA();            
        newBudgetLineItems = new ArrayList<BudgetLineItem>();
        newPersonnelLineItem = new BudgetLineItem();          
        setDocumentNextValueRefresh(true);
        budgetJustificationWrapper = new BudgetJustificationWrapper(getDocument().getBudgetJustification());
        newSubAward = new BudgetSubAwards();
        this.setAdditionalDocInfo1(new KeyLabelPair(BUDGET_NAME_KEY, Constants.EMPTY_STRING));
        this.setAdditionalDocInfo2(new KeyLabelPair(VERSION_NUMBER_KEY, Constants.EMPTY_STRING));
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        // if there are more ...
        for(Object displayedErrorsKey: getDisplayedErrors().keySet()) {
            getDisplayedErrors().put(displayedErrorsKey, false);
        }
    }

    public BudgetPeriod getNewBudgetPeriod() {
        return newBudgetPeriod;
    }

    public void setNewBudgetPeriod(BudgetPeriod newBudgetPeriod) {
        Integer budgetPeriod = 1;
        if(getDocument().getBudgetPeriods() != null) {
            budgetPeriod = getDocument().getBudgetPeriods().size() + 1;
        }
        newBudgetPeriod.setBudgetPeriod(budgetPeriod);
        this.newBudgetPeriod = newBudgetPeriod;
    }

    @Override
    public List<ExtraButton> getExtraButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        String externalImageURL = KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
        String generatePeriodImage = lookupKualiConfigurationService().getPropertyString(externalImageURL) + "buttonsmall_generatePeriods.gif";
        String calculatePeriodImage = lookupKualiConfigurationService().getPropertyString(externalImageURL) + "buttonsmall_calculatePeriods.gif";
        String defaultImage = lookupKualiConfigurationService().getPropertyString(externalImageURL) + "buttonsmall_defaultPeriods.gif";
        addExtraButton("methodToCall.generateAllPeriods", generatePeriodImage, "Generate All Periods");
        addExtraButton("methodToCall.questionCalculateAllPeriods",calculatePeriodImage, "Calculate All Periods");
        addExtraButton("methodToCall.defaultPeriods",defaultImage, "Default Periods");
        
        return extraButtons;
    }

    public List<ExtraButton> getExtraPersonnelBudgetButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        String externalImageURL = KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
        String returnToExpensesImage = lookupKualiConfigurationService().getPropertyString(externalImageURL) + "buttonsmall_returnToExpenses.gif";
        addExtraButton("methodToCall.returnToExpenses", returnToExpensesImage, "Return To Expenses");
        String calculateImage = lookupKualiConfigurationService().getPropertyString(externalImageURL) + "buttonsmall_calculate.gif";
        addExtraButton("methodToCall.calculateLineItem", calculateImage, "Calculate");
        return extraButtons;
    }
    public List<ExtraButton> getExtraExpensesButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        String externalImageURL = KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
        String calculateCurrentPeriodImage = lookupKualiConfigurationService().getPropertyString(externalImageURL) + "buttonsmall_calculateCurrent2.gif"; 
        //String viewPersonnelSalariesImage = lookupKualiConfigurationService().getPropertyString(externalImageURL) + "buttonsmall_viewpersal.gif"; 
        addExtraButton("methodToCall.calculateCurrentPeriod", calculateCurrentPeriodImage, "Calculate Current Period");
        //addExtraButton("methodToCall.viewPersonnelSalaries",viewPersonnelSalariesImage, "View Personnel Salaries");
        
        return extraButtons;
    }
    public List<ExtraButton> getRatesExtraButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        String externalImageURL = KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
        String syncAllImage = lookupKualiConfigurationService().getPropertyString(externalImageURL) + "buttonsmall_syncallrates.gif"; 
        String resetAllImage = lookupKualiConfigurationService().getPropertyString(externalImageURL) + "buttonsmall_resetallrates.gif"; 
        addExtraButton("methodToCall.syncAllRates", syncAllImage, "Sync All Rates");
        addExtraButton("methodToCall.resetAllRates",resetAllImage, "Reset All Rates");
        
        return extraButtons;
    }

    public List<ExtraButton> getExtraPersonnelButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        String externalImageURL = KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
        
//        String syncToProposalImage = lookupKualiConfigurationService().getPropertyString(externalImageURL) + "buttonsmall_synctoprop.gif"; 
//        addExtraButton("methodToCall.synchToProposal", syncToProposalImage, "Synch to Proposal");
        String calculateCurrentPeriodImage = lookupKualiConfigurationService().getPropertyString(externalImageURL) + "buttonsmall_calculateCurrent2.gif"; 
        addExtraButton("methodToCall.calculateCurrentPeriod", calculateCurrentPeriodImage, "Calculate Current Period");
        String viewPersonnelSalariesImage = lookupKualiConfigurationService().getPropertyString(externalImageURL) + "buttonsmall_viewpersal.gif"; 
        addExtraButton("methodToCall.viewPersonnelSalaries",viewPersonnelSalariesImage, "View Personnel Salaries");
        
        return extraButtons;
    }


    public List<ExtraButton> getExtraTotalsTopButtons() {
        extraTopButtons.clear();
        extraTopButtons.add(configureReturnToProposalTopButton()); 
        
        ExtraButton customExpandAllButton = new ExtraButton();
        String expandAllImage = lookupKualiConfigurationService().getPropertyString(KR_EXTERNALIZABLE_IMAGES_URI_KEY) + "tinybutton-expandall.gif"; 
        customExpandAllButton.setExtraButtonProperty("methodToCall.showAllTabs");
        customExpandAllButton.setExtraButtonSource(expandAllImage);
        customExpandAllButton.setExtraButtonAltText("show all panel content");
        customExpandAllButton.setExtraButtonOnclick("javascript: showAllPanels(); return false;");
        
        ExtraButton customCollapseAllButton = new ExtraButton();
        String hideAllImage = lookupKualiConfigurationService().getPropertyString(KR_EXTERNALIZABLE_IMAGES_URI_KEY) + "tinybutton-collapseall.gif"; 
        customCollapseAllButton.setExtraButtonProperty("methodToCall.hideAllTabs");
        customCollapseAllButton.setExtraButtonSource(hideAllImage);
        customCollapseAllButton.setExtraButtonAltText("hide all panel content");
        customCollapseAllButton.setExtraButtonOnclick("javascript: expandAll('false', false); return false");
        
        extraTopButtons.add(customExpandAllButton);
        extraTopButtons.add(customCollapseAllButton);
        return extraTopButtons;
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
        
        ExtraButton newButton = new ExtraButton();
        
        newButton.setExtraButtonProperty(property);
        newButton.setExtraButtonSource(source);
        newButton.setExtraButtonAltText(altText);
        
        extraButtons.add(newButton);
    }

    public List<ExtraButton> getExtraTopButtons() {
        return extraTopButtons;
    }

    /**
     * Determines if CostSharing edit form should be visible
     * @return
     */
    public boolean isCostSharingEditFormVisible() {
        BudgetDocument budgetDocument = getDocument();        
        return budgetDocument != null && budgetDocument.isCostSharingApplicable() && budgetDocument.isCostSharingAvailable(); 
    }
    
    /**
     * Determines if UnrecoveredFandAEdit edit form should be visible
     * @return
     */
    public boolean isUnrecoveredFandAEditFormVisible() {
        BudgetDocument budgetDocument = getDocument(); 
        return budgetDocument != null && budgetDocument.isUnrecoveredFandAApplicable() && budgetDocument.isUnrecoveredFandAAvailable(); 
    }
    
    public void setExtraTopButtons(List<ExtraButton> extraTopButtons) {
        this.extraTopButtons = extraTopButtons;
    }

    public String getNewBudgetPersons() {
        return newBudgetPersons;
    }

    public void setNewBudgetPersons(String newBudgetPersons) {
        this.newBudgetPersons = newBudgetPersons;
    }

    public String getNewBudgetRolodexes() {
        return newBudgetRolodexes;
    }

    /**
     * Get the new BudgetProjectIncome
     * @return
     */
    public BudgetProjectIncome getNewBudgetProjectIncome() {
        return newBudgetProjectIncome;
    }

    public Integer getNewBudgetPeriodNumber() {
        return newBudgetPeriodNumber;
    }

    public void setNewBudgetPeriodNumber(Integer newBudgetPeriodNo) {
        this.newBudgetPeriodNumber = newBudgetPeriodNo;
    }

    /**
     * Set the new BudgetProjectIncome
     * @param newBudgetProjectIncome
     */
    public void setNewBudgetProjectIncome(BudgetProjectIncome newBudgetProjectIncome) {
        this.newBudgetProjectIncome = newBudgetProjectIncome;
    }

    public void setNewBudgetRolodexes(String newBudgetRolodexes) {
        this.newBudgetRolodexes = newBudgetRolodexes; 
    }
    
    public String getNewTbnPersons() {
        return newTbnPersons;
    }

    public void setNewTbnPersons(String newTbnPersons) {
        this.newTbnPersons = newTbnPersons;
    }

    /**
     * 
     * This method to suppress copy/reload buttons for 'Totals' page
     */
    public void suppressButtonsForTotalPage() {
        this.getDocumentActionFlags().setCanCopy(false);
        this.getDocumentActionFlags().setCanReload(false);
    }

    /**
     * Get the new BudgetCostShare
     * @return
     */
    public BudgetCostShare getNewBudgetCostShare() {
        return newBudgetCostShare;
    }

    /**
     * Set the new BudgetCostShare
     * @param newBudgetCostShare
     */
    public void setNewBudgetCostShare(BudgetCostShare newBudgetCostShare) {
        this.newBudgetCostShare = newBudgetCostShare;
    }
    
    /**
     * Get the Header Dispatch.  This determines the action that will occur
     * when the user switches tabs for a budget.  If the user can modify
     * the budget, the budget is automatically saved.  If not (view-only),
     * then a reload will be executed instead.
     * @return the Header Dispatch action
     */
    public String getHeaderDispatch() {
        return this.getDocumentActionFlags().getCanSave() ? "save" : "reload";
    }

    /**
     * Get the new BudgetUnrecoveredFandA
     * @return
     */
    public BudgetUnrecoveredFandA getNewBudgetUnrecoveredFandA() {
        return newBudgetUnrecoveredFandA;
    }

    /**
     * Set the new BudgetUnrecoveredFandA
     * @param newBudgetUnrecoveredFandA
     */
    public void setNewBudgetUnrecoveredFandA(BudgetUnrecoveredFandA newBudgetUnrecoveredFandA) {
        this.newBudgetUnrecoveredFandA = newBudgetUnrecoveredFandA;
    }
    
    public Integer getModularSelectedPeriod() {
        return modularSelectedPeriod;
    }

    public void setModularSelectedPeriod(Integer modularSelectedPeriod) {
        this.modularSelectedPeriod = modularSelectedPeriod;
    }

    public BudgetModularIdc getNewBudgetModularIdc() {
        return newBudgetModularIdc;
    }

    public void setNewBudgetModularIdc(BudgetModularIdc newBudgetModularIdc) {
        this.newBudgetModularIdc = newBudgetModularIdc;
    }

    public BudgetModularSummary getBudgetModularSummary() {
        return budgetModularSummary;
    }
    
    public BudgetJustificationWrapper getBudgetJustification() {
        return budgetJustificationWrapper;
    }

    public void setBudgetModularSummary(BudgetModularSummary budgetModularSummary) {
        this.budgetModularSummary = budgetModularSummary;
    }        
    
    public BudgetDecimal getCostSharingAmount() {
        return costSharingAmount;
    }

    public void setCostSharingAmount(BudgetDecimal costSharingAmount) {
        this.costSharingAmount = costSharingAmount;
    }
    
    public Integer getViewBudgetView() {
        return viewBudgetView;
    }

    public void setViewBudgetView(Integer viewBudgetView) {
        this.viewBudgetView = viewBudgetView;
    }

    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
        
        if ("resetRates".equals(getMethodToCall()) || "resetAllRates".equals(getMethodToCall())) {
            GlobalVariables.getErrorMap().clear();
            getUnconvertedValues().clear();
        }
        
        if (getActionFormUtilMap() != null && getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).setCacheValueFinderResults(false);
        }
    }

    public List<BudgetLineItem> getNewBudgetLineItems() {
        return newBudgetLineItems;
    }

    public void setNewBudgetLineItems(List<BudgetLineItem> newBudgetLineItems) {
        this.newBudgetLineItems = newBudgetLineItems;
    }

    /**
     * Gets the personnelBudgetViewMode attribute. 
     * @return Returns the personnelBudgetViewMode.
     */
    public String getPersonnelBudgetViewMode() {
        return personnelBudgetViewMode;
    }

    /**
     * Sets the personnelBudgetViewMode attribute value.
     * @param personnelBudgetViewMode The personnelBudgetViewMode to set.
     */
    public void setPersonnelBudgetViewMode(String personnelBudgetViewMode) {
        this.personnelBudgetViewMode = personnelBudgetViewMode;
    }

    /**
     * Gets the selectedBudgetLineItem attribute. 
     * @return Returns the selectedBudgetLineItem.
     */
    public BudgetLineItem getSelectedBudgetLineItem() {
        return selectedBudgetLineItem;
    }

    /**
     * Sets the selectedBudgetLineItem attribute value.
     * @param selectedBudgetLineItem The selectedBudgetLineItem to set.
     */
    public void setSelectedBudgetLineItem(BudgetLineItem selectedBudgetLineItem) {
        this.selectedBudgetLineItem = selectedBudgetLineItem;
    }

    /**
     * Gets the newBudgetPersonnelDetails attribute. 
     * @return Returns the newBudgetPersonnelDetails.
     */
    public BudgetPersonnelDetails getNewBudgetPersonnelDetails() {
        return newBudgetPersonnelDetails;
    }

    /**
     * Sets the newBudgetPersonnelDetails attribute value.
     * @param newBudgetPersonnelDetails The newBudgetPersonnelDetails to set.
     */
    public void setNewBudgetPersonnelDetails(BudgetPersonnelDetails newBudgetPersonnelDetails) {
        this.newBudgetPersonnelDetails = newBudgetPersonnelDetails;
    }

    /**
     * Gets the selectedBudgetLineItemIndex attribute. 
     * @return Returns the selectedBudgetLineItemIndex.
     */
    public Integer getSelectedBudgetLineItemIndex() {
        return selectedBudgetLineItemIndex;
    }

    /**
     * Sets the selectedBudgetLineItemIndex attribute value.
     * @param selectedBudgetLineItemIndex The selectedBudgetLineItemIndex to set.
     */
    public void setSelectedBudgetLineItemIndex(Integer selectedBudgetLineItemIndex) {
        this.selectedBudgetLineItemIndex = selectedBudgetLineItemIndex;
    }
    
    /**
     * This method does what its name says
     */
    private void configureExtraTopButtons() {
        extraTopButtons = new ArrayList<ExtraButton>();
        extraTopButtons.add(configureReturnToProposalTopButton());
    }
    
    /**
     * This method does what its name says
     */
    private ExtraButton configureReturnToProposalTopButton() {
        ExtraButton returnToProposalButton = new ExtraButton();
        returnToProposalButton.setExtraButtonProperty(RETURN_TO_PROPOSAL_METHOD_TO_CALL);
        returnToProposalButton.setExtraButtonSource(buildExtraButtonSourceURI("tinybutton-retprop.gif"));
        returnToProposalButton.setExtraButtonAltText(RETURN_TO_PROPOSAL_ALT_TEXT);
        
        return returnToProposalButton;
    }

    /**
     * This method does what its name says
     * @param buttonFileName
     * @return
     */
    private String buildExtraButtonSourceURI(String buttonFileName) {
        return lookupKualiConfigurationService().getPropertyString(KRA_EXTERNALIZABLE_IMAGES_URI_KEY) + buttonFileName;
    }

    /**
     * This method does what its name says
     * @return
     */
    private KualiConfigurationService lookupKualiConfigurationService() {
        return KraServiceLocator.getService(KualiConfigurationService.class);
    }
    
    public String getPrevOnOffCampusFlag() {
        return prevOnOffCampusFlag;
    }

    public void setPrevOnOffCampusFlag(String prevOnOffCampusFlag) {
        this.prevOnOffCampusFlag = prevOnOffCampusFlag;
    }

    public boolean isUpdateFinalVersion() {
        return updateFinalVersion;
    }

    public void setUpdateFinalVersion(boolean updateFinalVersion) {
        this.updateFinalVersion = updateFinalVersion;
    }    
    
    @Override
    protected void populateHeaderFields(KualiWorkflowDocument workflowDocument) {
        BudgetDocument budgetDocument = getDocument();
        ProposalDevelopmentDocument proposalDocument = budgetDocument.getProposal();
        KualiWorkflowDocument parentWorkflowDocument = null;
        
        try {
            if(proposalDocument != null) {
                parentWorkflowDocument = proposalDocument.getDocumentHeader().getWorkflowDocument();
            }
        } catch (RuntimeException e) {
        }
        
        try {
            if(proposalDocument != null && parentWorkflowDocument == null) {
                Document retrievedDocument = KNSServiceLocator.getDocumentService().getByDocumentHeaderId(proposalDocument.getDocumentNumber());
                parentWorkflowDocument = retrievedDocument.getDocumentHeader().getWorkflowDocument();
            }
        } catch (WorkflowException e) {
        } 
        
        //Document Number
        HeaderField docNumber = new HeaderField("DataDictionary.DocumentHeader.attributes.documentNumber", proposalDocument != null? proposalDocument.getDocumentNumber() : null); 
        HeaderField docStatus = new HeaderField("DataDictionary.DocumentHeader.attributes.financialDocumentStatusCode", parentWorkflowDocument != null? parentWorkflowDocument.getStatusDisplayValue() : null);
        HeaderField docInitiator = new HeaderField("DataDictionary.AttributeReferenceDummy.attributes.initiatorNetworkId", 
                parentWorkflowDocument != null? parentWorkflowDocument.getInitiatorNetworkId() : null, 
                        parentWorkflowDocument != null? "<kul:inquiry boClassName='org.kuali.core.bo.user.UniversalUser' keyValues='${PropertyConstants.KUALI_USER_PERSON_UNIVERSAL_IDENTIFIER}=" + parentWorkflowDocument.getRouteHeader().getInitiator().getUuId() + "' render='true'>" + parentWorkflowDocument.getInitiatorNetworkId() + "</kul:inquiry>" : null);
        
        String createDateStr = null;
        if(parentWorkflowDocument != null && parentWorkflowDocument.getCreateDate() != null) {
            createDateStr = KNSServiceLocator.getDateTimeService().toString(parentWorkflowDocument.getCreateDate(), "hh:mm a MM/dd/yyyy");
        }
        
        HeaderField docCreateDate = new HeaderField("DataDictionary.AttributeReferenceDummy.attributes.createDate", createDateStr);

        getDocInfo().clear();
        getDocInfo().add(docNumber);
        getDocInfo().add(docStatus); 
        getDocInfo().add(docInitiator);
        getDocInfo().add(docCreateDate);
    }

    public String getUrRateClassCodePrevValue() {
        return urRateClassCodePrevValue;
    }

    public void setUrRateClassCodePrevValue(String urRateClassCodePrevValue) {
        this.urRateClassCodePrevValue = urRateClassCodePrevValue;
    }

    public String[] getSelectedBudgetPrintFormId() {
        return selectedBudgetPrintFormId;
    }

    /**
     * Sets the selectedBudgetPrintFormId attribute value.
     * @param selectedBudgetPrintFormId The selectedBudgetPrintFormId to set.
     */
    public void setSelectedBudgetPrintFormId(String[] selectedBudgetPrintFormId) {
        this.selectedBudgetPrintFormId = selectedBudgetPrintFormId;
    }


    public String getSyncBudgetRate() {
        return syncBudgetRate;
    }

    public void setSyncBudgetRate(String syncBudgetRate) {
        this.syncBudgetRate = syncBudgetRate;
    }

    public BudgetSubAwards getNewSubAward() {
        return newSubAward;
    }

    public void setNewSubAward(BudgetSubAwards newSubAward) {
        this.newSubAward = newSubAward;
    }

    public Integer getPersonnelDetailLine() {
        return personnelDetailLine;
    }

    public void setPersonnelDetailLine(Integer personnelDetailLine) {
        this.personnelDetailLine = personnelDetailLine;
    }

    /**
     * Gets the subAwardFile attribute. 
     * @return Returns the subAwardFile.
     */
    public FormFile getSubAwardFile() {
        return subAwardFile;
    }

    /**
     * Sets the subAwardFile attribute value.
     * @param subAwardFile The subAwardFile to set.
     */
    public void setSubAwardFile(FormFile subAwardFile) {
        this.subAwardFile = subAwardFile;
    }

    public boolean isSaveAfterCopy() {
        return saveAfterCopy;
    }

    public void setSaveAfterCopy(boolean val) {
        saveAfterCopy = val;
    }

    public BudgetLineItem getNewPersonnelLineItem() {
        return newPersonnelLineItem;
    }

    public void setNewPersonnelLineItem(BudgetLineItem newPersonnelLineItem) {
        this.newPersonnelLineItem = newPersonnelLineItem;
    }

    public String getNewGroupName() {
        return newGroupName;
    }

    public void setNewGroupName(String newGroupName) {
        this.newGroupName = newGroupName;
    }

    /**
     * {@inheritDocs}
     */
    @Override
    protected void setSaveDocumentControl(DocumentActionFlags tempDocumentActionFlags, @SuppressWarnings("unchecked") Map editMode) {
        tempDocumentActionFlags.setCanSave(false);

        if (this.hasModifyBudgetPermission(editMode)) {
            tempDocumentActionFlags.setCanSave(true);
        }
    }

    /**
     * This method checks if destination is the BudgetVersions page.
     * This method works only if called after form properties are updated
     * (ex: navigateTo).  Just because this method returns true does not
     * mean that the request will actually end up at the budget versions
     * page.  (ex: if on another page and a hard error occurs while trying
     * to get to the budget versions page).
     *
     * @return true if headed to the versions page.
     */
    public boolean toBudgetVersionsPage() {
        return "versions".equals(this.navigateTo)
        || ("BudgetVersionsAction".equals(this.actionName));
    }

    @Override
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET;
    }
    
    /**
     * Retrieves the {@link BudgetDocument BudgetDocument}.
     * @return {@link BudgetDocument BudgetDocument}
     */
    @Override
    public BudgetDocument getDocument() {
        //overriding and using covariant return to avoid casting
        //Document to BudgetDocument everywhere
        return (BudgetDocument) super.getDocument();
    }
}
