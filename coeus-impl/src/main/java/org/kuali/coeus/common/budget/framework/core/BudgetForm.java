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
package org.kuali.coeus.common.budget.framework.core;

import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.common.budget.framework.distribution.BudgetCostShare;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncome;
import org.kuali.coeus.common.budget.framework.distribution.BudgetUnrecoveredFandA;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetFormulatedCostDetail;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetJustificationWrapper;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.personnel.HierarchyPersonnelSummary;
import org.kuali.coeus.common.framework.costshare.CostShareFunctions;
import org.kuali.coeus.common.framework.costshare.CostShareService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwards;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularIdc;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularSummary;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyProposalSummary;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BudgetForm extends BudgetVersionFormBase implements CostShareFunctions {
    
    private static final long serialVersionUID = -8853937659597422800L;
    
    private static final String KR_EXTERNALIZABLE_IMAGES_URI_KEY = "kr.externalizable.images.url";
    public static final String VERSION_NUMBER_KEY = "DataDictionary.Budget.attributes.budgetVersionNumber";
    public static final String BUDGET_NAME_KEY = "DataDictionary.KraAttributeReferenceDummy.attributes.budgetName";
    

    private String newBudgetPersons;
    private String newBudgetRolodexes;
    private String newTbnPersons;
    
    private BudgetPeriod newBudgetPeriod;
    private List<BudgetLineItem> newBudgetLineItems;   
    private Integer newBudgetPeriodNumber = Integer.valueOf(0);
    
    private BudgetCostShare newBudgetCostShare;
    private BudgetProjectIncome newBudgetProjectIncome;
    private BudgetUnrecoveredFandA newBudgetUnrecoveredFandA;
    private BudgetModularIdc newBudgetModularIdc;
    private BudgetModularSummary budgetModularSummary;
    
    private BudgetJustificationWrapper budgetJustificationWrapper;
    
    private ScaleTwoDecimal costSharingAmount;
    
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
    
    private String newGroupName;
    
    
    private String[] selectedToPrintComment;
    
    private transient boolean viewDivFlag = false;
    
    private transient boolean viewDivBooleanFlag = true;
    
    private transient Integer personIndex = 0;
    
    private transient String enableBudgetSalaryByPeriod;
    
    private BudgetFormulatedCostDetail newBudgetFormulatedCost;
    
    private transient ProposalHierarchyService proposalHierarchyService;
    
    /**
     * Gets the selectedToPrintComment attribute. 
     * @return Returns the selectedToPrintComment.
     */
    public String[] getSelectedToPrintComment() {
        return selectedToPrintComment;
    }

    /**
     * Sets the selectedToPrintComment attribute value.
     * @param selectedToPrintComment The selectedToPrintComment to set.
     */
    public void setSelectedToPrintComment(String[] selectedToPrintComment) {
        this.selectedToPrintComment = selectedToPrintComment;
    }

    private List<HierarchyPersonnelSummary> hierarchyPersonnelSummaries;
    private List<HierarchyProposalSummary> hierarchyProposalSummaries;
    
 
    public List<HierarchyPersonnelSummary> getHierarchyPersonnelSummaries() {
        return hierarchyPersonnelSummaries;
    }

    public void setHierarchyPersonnelSummaries(List<HierarchyPersonnelSummary> hierarchyPersonnelSummaries) {
        this.hierarchyPersonnelSummaries = hierarchyPersonnelSummaries;
    }

    /**
     * Sets the hierarchyProposalSummaries attribute value.
     * @param hierarchyProposalSummaries The hierarchyProposalSummaries to set.
     */
    public void setHierarchyProposalSummaries(List<HierarchyProposalSummary> hierarchyProposalSummaries) {
        this.hierarchyProposalSummaries = hierarchyProposalSummaries;
    }

    /**
     * Gets the hierarchyProposalSummaries attribute. 
     * @return Returns the hierarchyProposalSummaries.
     */
    public List<HierarchyProposalSummary> getHierarchyProposalSummaries() {
        return hierarchyProposalSummaries;
    }

    
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
        //Its actually calling from Action's docHandler. So, no need to call in here
//        initialize();    
    }
    
    @Override
    protected String getDefaultDocumentTypeName() {
        return "BudgetDocument";
    }

    /**
     * 
     * This method initialize all form variables
     */
    public void initialize() {
        Budget budget = getBudgetDocument().getBudget();
        BudgetPeriod newBudgetPeriod =  budget.getNewBudgetPeriod();
        newBudgetPeriod.setBudget(budget);
        setNewBudgetPeriod(newBudgetPeriod);
        
        configureExtraTopButtons();
        
        newBudgetProjectIncome = new BudgetProjectIncome();
        newBudgetCostShare = new BudgetCostShare();
        newBudgetUnrecoveredFandA = new BudgetUnrecoveredFandA();            
        newBudgetLineItems = new ArrayList<BudgetLineItem>();
//        newPersonnelLineItem = budget.getNewBudgetLineItem();    
        newBudgetPersonnelDetails = budget.getNewBudgetPersonnelLineItem();
        setDocumentNextValueRefresh(true);
        budgetJustificationWrapper = new BudgetJustificationWrapper(getBudgetDocument().getBudget().getBudgetJustification());
        newSubAward = new BudgetSubAwards();
        newBudgetModularIdc = new BudgetModularIdc();
        newBudgetFormulatedCost = new BudgetFormulatedCostDetail();
        this.getDocInfo().add(new HeaderField(BUDGET_NAME_KEY, Constants.EMPTY_STRING));
        this.getDocInfo().add(new HeaderField(VERSION_NUMBER_KEY, Constants.EMPTY_STRING));


        setHierarchyProposalSummaries(new ArrayList<HierarchyProposalSummary>());
        Collections.sort(this.getBudgetDocument().getBudget().getBudgetSubAwards());
    }
    
    public AwardBudgetDocument getBudgetDocument() {
        return (AwardBudgetDocument) this.getDocument();
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
        Budget budget = getBudgetDocument().getBudget();
        if(budget.getBudgetPeriods() != null) {
            budgetPeriod = getBudgetDocument().getBudget().getBudgetPeriods().size() + 1;
        }
        newBudgetPeriod.setBudgetPeriod(budgetPeriod);
        newBudgetPeriod.setBudget(budget);
        this.newBudgetPeriod = newBudgetPeriod;
    }

    @Override
    public List<ExtraButton> getExtraButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
        String generatePeriodImage = lookupKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_generatePeriods.gif";
        String calculatePeriodImage = lookupKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_calculatePeriods.gif";
        String defaultImage = lookupKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_defaultPeriods.gif";
        addExtraButton("methodToCall.generateAllPeriods", generatePeriodImage, "Generate All Periods");
        addExtraButton("methodToCall.questionCalculateAllPeriods",calculatePeriodImage, "Calculate All Periods");
        addExtraButton("methodToCall.defaultPeriods",defaultImage, "Default Periods");
        
        return extraButtons;
    }

    public List<ExtraButton> getExtraPersonnelBudgetButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
        String returnToExpensesImage = lookupKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_returnToExpenses.gif";
        addExtraButton("methodToCall.returnToExpenses", returnToExpensesImage, "Return To Expenses");
        String calculateImage = lookupKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_calculate.gif";
        addExtraButton("methodToCall.calculateLineItem", calculateImage, "Calculate");
        return extraButtons;
    }
    public List<ExtraButton> getExtraExpensesButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
        String calculateCurrentPeriodImage = lookupKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_calculateCurrent2.gif"; 
        addExtraButton("methodToCall.calculateCurrentPeriod", calculateCurrentPeriodImage, "Calculate Current Period");

        return extraButtons;
    }
    public List<ExtraButton> getRatesExtraButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;
        String syncAllImage = lookupKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_syncallrates.gif"; 
        String resetAllImage = lookupKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_resetallrates.gif"; 
        addExtraButton("methodToCall.syncAllRates", syncAllImage, "Sync All Rates");
        addExtraButton("methodToCall.resetAllRates",resetAllImage, "Reset All Rates");
        
        return extraButtons;
    }

    public List<ExtraButton> getExtraPersonnelButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;

        String calculateCurrentPeriodImage = lookupKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_calculateCurrent2.gif"; 
        addExtraButton("methodToCall.calculateCurrentPeriod", calculateCurrentPeriodImage, "Calculate Current Period");
        String viewPersonnelSalariesImage = lookupKualiConfigurationService().getPropertyValueAsString(externalImageURL) + "buttonsmall_viewpersal.gif"; 
        addExtraButton("methodToCall.viewPersonnelSalaries",viewPersonnelSalariesImage, "View Personnel Salaries","excludeSubmitRestriction=true");
        
        return extraButtons;
    }


    public List<ExtraButton> getExtraTotalsTopButtons() {
        extraTopButtons.clear();
        extraTopButtons.add(configureReturnToParentTopButton()); 
        
        ExtraButton customExpandAllButton = new ExtraButton();
        String expandAllImage = lookupKualiConfigurationService().getPropertyValueAsString(KR_EXTERNALIZABLE_IMAGES_URI_KEY) + "tinybutton-expandall.gif"; 
        customExpandAllButton.setExtraButtonProperty("methodToCall.showAllTabs");
        customExpandAllButton.setExtraButtonSource(expandAllImage);
        customExpandAllButton.setExtraButtonAltText("show all panel content");
        customExpandAllButton.setExtraButtonOnclick("javascript: showAllPanels(); return false;");
        
        ExtraButton customCollapseAllButton = new ExtraButton();
        String hideAllImage = lookupKualiConfigurationService().getPropertyValueAsString(KR_EXTERNALIZABLE_IMAGES_URI_KEY) + "tinybutton-collapseall.gif"; 
        customCollapseAllButton.setExtraButtonProperty("methodToCall.hideAllTabs");
        customCollapseAllButton.setExtraButtonSource(hideAllImage);
        customCollapseAllButton.setExtraButtonAltText("hide all panel content");
        customCollapseAllButton.setExtraButtonOnclick("javascript: expandAll('false', false); return false");
        
        extraTopButtons.add(customExpandAllButton);
        extraTopButtons.add(customCollapseAllButton);
        return extraTopButtons;
    }
    
    private ExtraButton configureReturnToParentTopButton() {
        BudgetParent budgetParent = getBudgetDocument().getBudget().getBudgetParent();
        return budgetParent!=null?budgetParent.getDocument().configureReturnToParentTopButton():new ExtraButton();
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

    public List<ExtraButton> getExtraTopButtons() {
        return extraTopButtons;
    }

    /**
     * Determines if CostSharing edit form should be visible
     * @return
     */
    public boolean isCostSharingEditFormVisible() {
        AwardBudgetDocument awardBudgetDocument = getBudgetDocument();  
        Budget budget = awardBudgetDocument != null ? awardBudgetDocument.getBudget() : null;
        return budget != null && budget.isCostSharingApplicable() && budget.isCostSharingAvailable(); 
    }
    
    /**
     * Determines if UnrecoveredFandAEdit edit form should be visible
     * @return
     */
    public boolean isUnrecoveredFandAEditFormVisible() {
        AwardBudgetDocument awardBudgetDocument = getBudgetDocument(); 
        Budget budget = awardBudgetDocument != null?awardBudgetDocument.getBudget():null;
        return budget != null && budget.isUnrecoveredFandAApplicable() && budget.isUnrecoveredFandAAvailable(); 
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
        if (getDocumentActions().containsKey((KRADConstants.KUALI_ACTION_CAN_COPY))) {
            documentActions.remove(KRADConstants.KUALI_ACTION_CAN_COPY);
        }
        if (getDocumentActions().containsKey((KRADConstants.KUALI_ACTION_CAN_RELOAD))) {
            documentActions.remove(KRADConstants.KUALI_ACTION_CAN_RELOAD);
        }
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
    
    public ScaleTwoDecimal getCostSharingAmount() {
        return costSharingAmount;
    }

    public void setCostSharingAmount(ScaleTwoDecimal costSharingAmount) {
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
            GlobalVariables.getMessageMap().clearErrorMessages();
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
        
        extraTopButtons.add(configureReturnToParentTopButton());
    }
    
    public List<ExtraButton> getExtraActionsButtons() {
        return new ArrayList<ExtraButton>();
    }
    /**
     * This method does what its name says
     * @param buttonFileName
     * @return
     */
    protected String buildExtraButtonSourceURI(String buttonFileName) {
        return lookupKualiConfigurationService().getPropertyValueAsString(Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY) + buttonFileName;
    }


    private ConfigurationService lookupKualiConfigurationService() {
        return CoreApiServiceLocator.getKualiConfigurationService();
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
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
        AwardBudgetDocument awardBudgetDocument = getBudgetDocument();
        BudgetParentDocument parentDocument = awardBudgetDocument.getBudget().getBudgetParent().getDocument();
        WorkflowDocument parentWorkflowDocument = null;
        
        try {
            if(parentDocument != null) {
                parentWorkflowDocument = parentDocument.getDocumentHeader().getWorkflowDocument();
            }
        } catch (RuntimeException e) {
        }
        
        try {
            if(parentDocument != null && parentWorkflowDocument == null) {
                Document retrievedDocument = KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(parentDocument.getDocumentNumber());
                parentWorkflowDocument = retrievedDocument.getDocumentHeader().getWorkflowDocument();
            }
        } catch (WorkflowException e) {
        } 
        
        // Replaced setting local vars with method calls to ease overriding
        getDocInfo().clear();
        getDocInfo().add(getHeaderDocNumber());
        getDocInfo().add(getHeaderDocStatus(parentWorkflowDocument)); 
        getDocInfo().add(getHeaderDocInitiator(parentWorkflowDocument));
        getDocInfo().add(getHeaderDocCreateDate(parentWorkflowDocument));
        
        String budgetName = Constants.EMPTY_STRING;
        String budgetVersionNumber = Constants.EMPTY_STRING;
        if (awardBudgetDocument != null && parentDocument != null) {
            Budget budget = awardBudgetDocument.getBudget();
            budgetName = budget.getName();
            if (budget.getBudgetVersionNumber() != null) {
                budgetVersionNumber = Integer.toString(budget.getBudgetVersionNumber());
            }
        }
        getDocInfo().add(new HeaderField(BUDGET_NAME_KEY, budgetName));
        getDocInfo().add(new HeaderField(VERSION_NUMBER_KEY, budgetVersionNumber));
    }

    protected HeaderField getHeaderDocNumber() {
        BudgetParentDocument parentDocument = getBudgetDocument().getBudget().getBudgetParent().getDocument();
        return new HeaderField("DataDictionary.DocumentHeader.attributes.documentNumber", parentDocument != null? parentDocument.getDocumentNumber() : null); 
    }

    protected HeaderField getHeaderDocStatus (WorkflowDocument parentWorkflowDocument) {
        return new HeaderField("DataDictionary.AttributeReference.attributes.workflowDocumentStatus", parentWorkflowDocument != null? parentWorkflowDocument.getStatus().getLabel() : null);
    }
    
    protected HeaderField getHeaderDocInitiator(WorkflowDocument parentWorkflowDocument) {
        KcPerson initiator = null;
        if (parentWorkflowDocument != null) {
            initiator = KcPerson.fromPersonId(parentWorkflowDocument.getInitiatorPrincipalId());
        }
        return new HeaderField("DataDictionary.AttributeReference.attributes.initiatorNetworkId", 
                initiator != null ? initiator.getUserName() : null);
    }
    
    protected HeaderField getHeaderDocCreateDate(WorkflowDocument parentWorkflowDocument) {
        String createDateStr = null;
        if(parentWorkflowDocument != null && parentWorkflowDocument.getDateCreated() != null) {
        createDateStr = CoreApiServiceLocator.getDateTimeService().toString(parentWorkflowDocument.getDateCreated().toDate(), "hh:mm a MM/dd/yyyy");
        }
        return new HeaderField("DataDictionary.AttributeReference.attributes.createDate", createDateStr);
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
    
    @Override
    public boolean isSaveAfterCopy() {
        return saveAfterCopy;
    }

    @Override
    public void setSaveAfterCopy(boolean val) {
        saveAfterCopy = val;
    }

//    public BudgetLineItem getNewPersonnelLineItem() {
//        return newPersonnelLineItem;
//    }
//
//    public void setNewPersonnelLineItem(BudgetLineItem newPersonnelLineItem) {
//        this.newPersonnelLineItem = newPersonnelLineItem;
//    }

    public String getNewGroupName() {
        return newGroupName;
    }

    public void setNewGroupName(String newGroupName) {
        this.newGroupName = newGroupName;
    }
    
    public String getActionPrefix(){
        return "budget";
    }

    @Override
    protected void setSaveDocumentControl(Map editMode) {
        
        if (hasModifyBudgetPermission(editMode) && !getDocumentActions().containsKey(KRADConstants.KUALI_ACTION_CAN_SAVE)) {
            getDocumentActions().put(KRADConstants.KUALI_ACTION_CAN_SAVE, KRADConstants.KUALI_DEFAULT_TRUE_VALUE);
            return;
        }
        
        if (!hasModifyBudgetPermission(editMode) && getDocumentActions().containsKey(KRADConstants.KUALI_ACTION_CAN_SAVE)) {
            getDocumentActions().remove(KRADConstants.KUALI_ACTION_CAN_SAVE);
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
     * This method makes sure that the Rates tab is not displayed for proposals
     * in a hierarchy.
     * 
     * @return Returns the headerNavigationTabs filtered based on hierarchy status.
     * @see org.kuali.rice.kns.web.struts.form.KualiForm#getHeaderNavigationTabs()
     */
    @Override
    public HeaderNavigation[] getHeaderNavigationTabs() {
        HeaderNavigation[] tabs = super.getHeaderNavigationTabs();
        BudgetParentDocument parentDocument = getBudgetDocument().getBudget().getBudgetParent().getDocument();
        boolean hideRatesTab = false;
        boolean hideHierarchyTab = true;
        if(parentDocument != null && parentDocument.getClass() == ProposalDevelopmentDocument.class){
            hideHierarchyTab = !((ProposalDevelopmentDocument)parentDocument).getDevelopmentProposal().isInHierarchy();
        }
        
        if (parentDocument instanceof ProposalDevelopmentDocument) {
            if (((ProposalDevelopmentDocument)parentDocument).getDevelopmentProposal().isParent()) {
                hideRatesTab = true;
            }
        }
        if (hideRatesTab || hideHierarchyTab) {
            List<HeaderNavigation> newTabs = new ArrayList<HeaderNavigation>();
            for (HeaderNavigation tab : tabs) {
                if((tab.getHeaderTabNavigateTo().equals("rates") && hideRatesTab) || (tab.getHeaderTabNavigateTo().equals("hierarchy") && hideHierarchyTab)){
                    //do not add the tab is it's rates or hierarchy tab and needs to be hided
                }else{
                    newTabs.add(tab); 
                 }
            }
            tabs = newTabs.toArray(new HeaderNavigation[newTabs.size()]);
        }
        return tabs;
    }
    
    @Override
    public String getProjectPeriodLabel() {
        String label = KcServiceLocator.getService(CostShareService.class).getCostShareLabel();
        return label;
    }
    
    /**
     * 
     * This method determines if the budget rates are editable.  Note, this function should be overriden if this form
     * gets extended.  Such as in the case of AwardBudgetForm, as that form has different requirements for editing budget rates.
     * @return
     */
    public boolean getCanModifyBudgetRates() {
        boolean retVal = this.getEditingMode().containsKey("modifyProposalBudgetRates");
        return retVal;
    }

    public void setViewDivBooleanFlag(boolean viewDivBooleanFlag) {
        this.viewDivBooleanFlag = viewDivBooleanFlag;
    }

    public boolean isViewDivBooleanFlag() {
        return viewDivBooleanFlag;
    }

    public void setViewDivFlag(boolean viewDivFlag) {
        this.viewDivFlag = viewDivFlag;
    }

    public boolean isViewDivFlag() {
        return viewDivFlag;
    }

    public void setPersonIndex(Integer personIndex) {
        this.personIndex = personIndex;
    }

    public Integer getPersonIndex() {
        return personIndex;
    }

    public void setEnableBudgetSalaryByPeriod(String enableBudgetSalaryByPeriod) {
        this.enableBudgetSalaryByPeriod = enableBudgetSalaryByPeriod;
    }

    public String getEnableBudgetSalaryByPeriod() {
        return enableBudgetSalaryByPeriod;
    }

    public java.util.Date getBudgetStartDate() {
        return this.getBudgetDocument().getBudget().getBudgetStartDate();
    }
    
    public java.util.Date getBudgetEndDate() {
        return this.getBudgetDocument().getBudget().getBudgetEndDate();
    }

    public BudgetFormulatedCostDetail getNewBudgetFormulatedCost() {
        return newBudgetFormulatedCost;
    }

    public void setNewBudgetFormulatedCost(BudgetFormulatedCostDetail newBudgetFormulatedCost) {
        this.newBudgetFormulatedCost = newBudgetFormulatedCost;
    }

    protected ProposalHierarchyService getProposalHierarchyService() {
        if (proposalHierarchyService == null) {
            proposalHierarchyService = KcServiceLocator.getService(ProposalHierarchyService.class);
        }
        return proposalHierarchyService;
    }

    public void setProposalHierarchyService(ProposalHierarchyService proposalHierarchyService) {
        this.proposalHierarchyService = proposalHierarchyService;
    }
    
    public Budget getBudget() {
    	return this.getBudgetDocument().getBudget();
    }
}
