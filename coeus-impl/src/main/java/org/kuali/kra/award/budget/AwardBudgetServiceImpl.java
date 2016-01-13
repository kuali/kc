/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.budget;


import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.income.BudgetProjectIncome;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemCalculatedAmount;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelCalculatedAmount;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModular;
import org.kuali.coeus.propdev.impl.budget.subaward.BudgetSubAwardPeriodDetail;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.award.budget.calculator.AwardBudgetCalculationService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.query.operator.And;
import org.kuali.coeus.common.budget.framework.query.operator.Equals;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetConstants;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.BudgetRatesService;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.coeus.common.budget.framework.summary.BudgetSummaryService;
import org.kuali.coeus.common.budget.framework.version.AddBudgetVersionEvent;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionRule;
import org.kuali.coeus.common.budget.impl.core.AbstractBudgetService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.DocumentBase;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * This class is to process all basic services required for AwardBudget
 */
public class AwardBudgetServiceImpl extends AbstractBudgetService<Award> implements AwardBudgetService {
	private static final Log LOG = LogFactory.getLog(AwardBudgetServiceImpl.class);
    private final static String BUDGET_VERSION_ERROR_PREFIX = "document.parentDocument.budgetDocumentVersion";
    private static final String INST_PROPOSAL_ID = "instProposalId";
    public static final String AWARD_BUDGET_STATUS_CODE = "awardBudgetStatusCode";
    public static final String BUDGET_VERSION_NUMBER = "budgetVersionNumber";
    public static final String BUDGET_ID = "budgetId";
    public static final String BUDGET_LINE_ITEM_ID = "budgetLineItemId";
    public static final String BUDGET_PERIOD_ID = "budgetPeriodId";
    public static final String SUBMIT_COST_SHARING_FLAG = "submitCostSharingFlag";
    public static final String BUDGET_LINE_ITEM_CALCULATED_AMOUNTS = "budgetLineItemCalculatedAmounts";
    public static final String BUDGET_PERSONNEL_DETAILS_LIST = "budgetPersonnelDetailsList";
    public static final String BUDGET_RATE_AND_BASE_LIST = "budgetRateAndBaseList";
    public static final String BUDGET_SUB_AWARD = "budgetSubAward";
    public static final String BUDGET_PERSONNEL_LINE_ITEM_ID = "budgetPersonnelLineItemId";
    public static final String BUDGET_PERSONNEL_CALCULATED_AMOUNTS = "budgetPersonnelCalculatedAmounts";
    public static final String BUDGET_PERSONNEL_RATE_AND_BASE_LIST = "budgetPersonnelRateAndBaseList";
    public static final String VALID_TO_APPLY_IN_RATE = "validToApplyInRate";
    public static final String PERSON_SEQUENCE_NUMBER = "personSequenceNumber";
    public static final String AWARD_NUMBER = "awardNumber";
    public static final String AWARD_ID = "awardId";
    public static final String PROPOSAL_NUMBER = "proposalNumber";
    public static final String UNNAMED = "UNNAMED";
    public static final String PERSONNEL_FRINGE_TOTALS = "personnelFringeTotals";
    public static final String CALCULATED_COST = "calculatedCost";
    public static final String RATE_CLASS_TYPE = "rateClassType";
    public static final String AWARD_FANDA_RATE = "awardFandaRate";
    public static final String RATE_CLASS_CODE = "rateClassCode";
    public static final String RATE_TYPE_CODE = "rateTypeCode";
    public static final String ON_OFF_CAMPUS_FLAG = "onOffCampusFlag";
    public static final String COST_ELEMENT_BO = "costElementBO";
    public static final String BUDGET_CATEGORY = "budgetCategory";
    private static final String SET_BUDGET_ID = "setBudgetId";

    private DocumentService documentService;
    private BudgetSummaryService budgetSummaryService;
    private AwardBudgetCalculationService awardBudgetCalculationService;
    private VersionHistoryService versionHistoryService;
    private AwardService awardService;
    private BudgetVersionRule addBudgetVersionRule;
    private DataObjectService dataObjectService;
    private LegacyDataAdapter legacyDataAdapter;
    private BudgetRatesService budgetRatesService;
    
    @Override
    public void post(AwardBudgetDocument awardBudgetDocument) {
        processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_POSTED);
        saveDocument(awardBudgetDocument);
    }

    public AwardBudgetDocument copyBudgetVersion(AwardBudgetDocument budgetDocument, boolean onlyOnePeriod) throws WorkflowException {
        AwardDocument awardDocument = (AwardDocument)budgetDocument.getBudget().getBudgetParent().getDocument();
		String parentDocumentNumber = awardDocument.getDocumentNumber();
        budgetDocument.toCopy();
        awardDocument.getDocumentHeader().setDocumentNumber(parentDocumentNumber);
        awardDocument.setDocumentNumber(parentDocumentNumber);
        if(budgetDocument.getBudget() == null) {
            throw new RuntimeException("Not able to find any Budget Version associated with this document");
        }
        Budget budget = budgetDocument.getBudget();
        AwardBudgetExt copiedBudget = (AwardBudgetExt) copyBudgetVersion(budget,onlyOnePeriod);
        budgetDocument.getBudgets().add(copiedBudget);
        budgetDocument = (AwardBudgetDocument) documentService.saveDocument(budgetDocument);
        
        Map<String, Object> objectMap = new HashMap<>();
        fixProperty(budget, SET_BUDGET_ID, Long.class, budgetDocument.getBudget().getBudgetId(), objectMap);
        objectMap.clear();
        
        budgetDocument = saveBudgetDocument(budgetDocument, false);
        AwardDocument savedAwardDocument = (AwardDocument)budgetDocument.getBudget().getBudgetParent().getDocument();
        savedAwardDocument.refreshBudgetDocumentVersions();
    	return budgetDocument;
    }

    public Budget copyBudgetVersionSuper(Budget budget, boolean onlyOnePeriod){
        if (onlyOnePeriod) {
            //Copy full first version, then include empty periods for remainder
            List<BudgetPeriod> oldBudgetPeriods = budget.getBudgetPeriods();
            for ( int i = 1 ; i < oldBudgetPeriods.size(); i++ ) {
                BudgetPeriod period = oldBudgetPeriods.get(i);
                period.getBudgetLineItems().clear();
                period.setCostSharingAmount(new ScaleTwoDecimal(0.0));
                period.setExpenseTotal(new ScaleTwoDecimal(0.0));
                period.setTotalCost(new ScaleTwoDecimal(0.0));
                period.setTotalCostLimit(new ScaleTwoDecimal(0.0));
                period.setTotalDirectCost(new ScaleTwoDecimal(0.0));
                period.setTotalIndirectCost(new ScaleTwoDecimal(0.0));
                period.setUnderrecoveryAmount(new ScaleTwoDecimal(0.0));
            }

            /**
             * KRACOEUS-6312
             * Zero out any applicable BudgetSubAwardPeriodDetail lines.
             */
            if (budget.getBudgetSubAwards() != null && budget.getBudgetSubAwards().size() > 0) {
                List<BudgetSubAwardPeriodDetail> budetSubawardPeriodDetail = budget.getBudgetSubAwards().get(0).getBudgetSubAwardPeriodDetails();
                for ( int i = 1 ; i < budetSubawardPeriodDetail.size(); i++ ) {
                    BudgetSubAwardPeriodDetail period = budetSubawardPeriodDetail.get(i);
                    period.setAmountsModified(true);
                    period.setCostShare(new ScaleTwoDecimal(0.0));
                    period.setDirectCost(new ScaleTwoDecimal(0.0));
                    period.setIndirectCost(new ScaleTwoDecimal(0.0));
                    period.setTotalCost(new ScaleTwoDecimal(0.0));
                }
            }
        }

        budget.setBudgetVersionNumber(budget.getBudgetParent().getNextBudgetVersionNumber());
        try {
            Map<String, Object> objectMap = new HashMap<>();
            fixProperty(budget, SET_BUDGET_ID, Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setBudgetPeriodId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setBudgetLineItemId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setBudgetLineItemCalculatedAmountId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setBudgetPersonnelLineItemId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setBudgetPersonnelCalculatedAmountId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setBudgetPersonnelRateAndBaseId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setBudgetRateAndBaseId", Long.class, null, objectMap);
            objectMap.clear();
            fixProperty(budget, "setVersionNumber", Integer.class, null, objectMap);
            objectMap.clear();


        }catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Work around for 1-to-1 Relationship between BudgetPeriod & BudgetModular
        Map<String, BudgetModular> tmpBudgetModulars = new HashMap<>();
        for(BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
            BudgetModular tmpObject = null;
            if(budgetPeriod.getBudgetModular() != null) {
                tmpObject = (BudgetModular) org.kuali.rice.krad.util.ObjectUtils.deepCopy(budgetPeriod.getBudgetModular());
            }
            tmpBudgetModulars.put(""+budgetPeriod.getBudget().getVersionNumber() + budgetPeriod.getBudgetPeriod(), tmpObject);
            budgetPeriod.setBudgetModular(null);
        }

        copyLineItemToPersonnelDetails(budget);
        budget.setVersionNumber(null);
        // setting this to null so copied budget can be posted.
        budget.setBudgetAdjustmentDocumentNumber(null);
        List<BudgetProjectIncome> projectIncomes = budget.getBudgetProjectIncomes();
        budget.setBudgetProjectIncomes(new ArrayList<>());
        if (projectIncomes != null && !projectIncomes.isEmpty()) {
            updateProjectIncomes(budget, projectIncomes);
        }
        for(BudgetPeriod tmpBudgetPeriod: budget.getBudgetPeriods()) {
            BudgetModular tmpBudgetModular = tmpBudgetModulars.get(""+tmpBudgetPeriod.getBudget().getVersionNumber() + tmpBudgetPeriod.getBudgetPeriod());
            if(tmpBudgetModular != null) {
                tmpBudgetModular.setBudgetPeriodObj(tmpBudgetPeriod);
                tmpBudgetPeriod.setBudgetModular(tmpBudgetModular);
            }
        }

        return budget;
    }

    /**
     * Recurse through all of the BOs and if a BO has a ProposalNumber property,
     * set its value to the new proposal number.
     * @param object the object
     */
    private void fixProperty(Object object, String methodName, Class clazz, Object propertyValue, Map<String, Object> objectMap){
        if(org.kuali.rice.krad.util.ObjectUtils.isNotNull(object)) {
            if (object instanceof PersistableBusinessObject) {
                PersistableBusinessObject objectWId = (PersistableBusinessObject) object;
                if (objectMap.get(objectWId.getObjectId()) != null) return;
                objectMap.put(((PersistableBusinessObject) object).getObjectId(), object);

                Method[] methods = object.getClass().getMethods();
                for (Method method : methods) {
                    if (method.getName().equals(methodName)) {
                        if (!(object instanceof DocumentBase)) {
                            try {
                                if(clazz.equals(Long.class))
                                    method.invoke(object, (Long) propertyValue);
                                else
                                    method.invoke(object, (Integer) propertyValue);
                            } catch (Throwable e) { }
                        }
                    } else if (isPropertyGetterMethod(method, methods)) {
                        Object value = null;
                        try {
                            value = method.invoke(object);
                        } catch (Throwable e) {
                            //We don't need to propagate this exception
                        }

                        if(value != null) {
                            if (value instanceof Collection) {
                                Collection<?> c = (Collection<?>) value;
                                for (Object entry : c) {
                                    fixProperty(entry, methodName, clazz, propertyValue, objectMap);
                                }
                            } else {
                                fixProperty(value, methodName, clazz, propertyValue, objectMap);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Is the given method a getter method for a property?  Must conform to
     * the following:
     * <ol>
     * <li>Must start with the <b>get</b></li>
     * <li>Must have a corresponding setter method</li>
     * <li>Must have zero arguments.</li>
     * </ol>
     * @param method the method to check
     * @param methods the other methods in the object
     * @return true if it is property getter method; otherwise false
     */
    private boolean isPropertyGetterMethod(Method method, Method methods[]) {
        if (method.getName().startsWith("get") && method.getParameterTypes().length == 0) {
            String setterName = method.getName().replaceFirst("get", "set");
            for (Method m : methods) {
                if (m.getName().equals(setterName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * This method is to handle budgetprojectincomes independently.
     * budgetprojectincomes is a collection of 'budget', but it also reference to budgetperiod.
     * During copy budgetperiodid is set to null.  If save with budgetdocument, then budgetperiodid will not be set.
     * So, has to use this to set manually.
     */
    private void updateProjectIncomes(Budget budget, List<BudgetProjectIncome> projectIncomes) {
        for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
            for (BudgetProjectIncome projectIncome : projectIncomes) {
                if (budgetPeriod.getBudgetPeriod().equals(projectIncome.getBudgetPeriodNumber())) {
                    projectIncome.setBudgetId(budget.getBudgetId());
                    projectIncome.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
                }
            }
        }
    }

    @Override
    public Budget copyBudgetVersion(Budget budget, boolean onlyOnePeriod) {
    	AwardBudgetExt copy = (AwardBudgetExt) copyBudgetVersionSuper(budget, onlyOnePeriod);
    	for (BudgetPeriod period : copy.getBudgetPeriods()) {
    		AwardBudgetPeriodExt awardPeriod = (AwardBudgetPeriodExt) period;
    		awardPeriod.getAwardBudgetPeriodFnAAmounts().clear();
    		awardPeriod.getAwardBudgetPeriodFringeAmounts().clear();
    	}
    	return copy;
    }

    @Override
    public void toggleStatus(AwardBudgetDocument awardBudgetDocument) {
        String currentStatusCode = awardBudgetDocument.getAwardBudget().getAwardBudgetStatusCode();
        if (currentStatusCode.equals(getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_TO_BE_POSTED))) {
            processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_DO_NOT_POST);
        }
        else if (currentStatusCode.equals(getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_DO_NOT_POST))) {
            processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_TO_BE_POSTED);
        }
        saveDocument(awardBudgetDocument);
    }

    protected void saveDocument(AwardBudgetDocument awardBudgetDocument) {
        try {
            getDocumentService().saveDocument(awardBudgetDocument);
        }catch (WorkflowException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void processApproval(AwardBudgetDocument awardBudgetDocument) {
        WorkflowDocument workFlowDocument = getWorkflowDocument(awardBudgetDocument);
        if(workFlowDocument.isFinal()){
            processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_TO_BE_POSTED);
            saveDocument(awardBudgetDocument);
        }
    }

    @Override
    public void processDisapproval(AwardBudgetDocument awardBudgetDocument) {
        processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_REJECTED);
    }

    @Override
    public void processSubmision(AwardBudgetDocument awardBudgetDocument) {
        processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_SUBMITTED);
    }
    
    protected void processStatusChange(AwardBudgetDocument awardBudgetDocument,String routingStatus){
        WorkflowDocument workflowDocument = getWorkflowDocument(awardBudgetDocument);
        String submittedStatusCode = getParameterValue(routingStatus);
        String submittedStatus = findStatusDescription(submittedStatusCode);
        awardBudgetDocument.getAwardBudget().setAwardBudgetStatusCode(submittedStatusCode);
        workflowDocument.setApplicationDocumentStatus(submittedStatus);
    }

    protected String getParameterValue(String awardBudgetParameter) {
        return  getParameterService().getParameterValueAsString(AwardBudgetDocument.class, awardBudgetParameter);
    }


    protected String findStatusDescription(String statusCode) {
        AwardBudgetStatus budgetStatus = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetStatus.class, statusCode);
        return budgetStatus.getDescription();
    }

    @Override
    public AwardBudgetDocument rebudget(AwardDocument awardDocument,String documentDescription) throws WorkflowException{
        return createNewBudgetDocument(documentDescription, awardDocument.getAward(), true);
    }

    /**
     * Get the corresponding workflow document.  
     * @param doc the document
     * @return the workflow document or null if there is none
     */
    protected WorkflowDocument getWorkflowDocument(Document doc) {
        WorkflowDocument workflowDocument = null;
        if (doc != null) {
            DocumentHeader header = doc.getDocumentHeader();
            if (header != null) {
                try {
                    workflowDocument = header.getWorkflowDocument();
                } 
                catch (RuntimeException ex) {
                    // do nothing; there is no workflow document
                }
            }
        }
        return workflowDocument;
    }

    @Override
    public Budget getNewBudgetVersion(BudgetParentDocument<Award> parentBudgetDocument, String documentDescription, Map<String, Object> options){
    	AwardDocument awardDocument = (AwardDocument) parentBudgetDocument;
    	AwardBudgetDocument budgetDocument;
		try {
			budgetDocument = getNewBudgetVersionDocument(parentBudgetDocument, documentDescription, options);
		} catch (WorkflowException e) {
			throw new RuntimeException(e);
		}
		if (budgetDocument != null) {
			AwardBudgetExt budget = budgetDocument.getBudget();
                if (budget.getBudgetPeriods().isEmpty() && budget.getStartDate() != null) {
	            budget.setBudgetPeriods(getBudgetSummaryService().generateBudgetPeriods(budget));
	        }
			awardDocument.getAward().getCurrentVersionBudgets().add(budget);
			awardDocument.getAward().getBudgets().add(budget);
            try {
                budgetDocument = (AwardBudgetDocument) documentService.saveDocument(budgetDocument);
            } catch (WorkflowException e) {
                throw new RuntimeException(e);
            }

			return budgetDocument.getBudget();
		} else {
			return null;
		}
    }

    @Override
    public AwardBudgetDocument getNewBudgetVersionDocument(BudgetParentDocument<Award> parentBudgetDocument, String documentDescription, Map<String, Object> options)
    throws WorkflowException {
        
        AwardDocument parentDocument = (AwardDocument)parentBudgetDocument;
        if (checkForOutstandingBudgets(parentDocument.getAward())) {
            return null;
        }
        
        return createNewBudgetDocument(documentDescription, parentDocument.getAward(), false);
    }

    protected AwardBudgetDocument createNewBudgetDocument(String documentDescription, Award award,boolean rebudget)
            throws WorkflowException {
        boolean success = new AwardBudgetVersionRule().processAddBudgetVersion(
                new AddBudgetVersionEvent(BUDGET_VERSION_ERROR_PREFIX, award, documentDescription));
        if (!success) {
            return null;
        }
        Integer budgetVersionNumber = award.getNextBudgetVersionNumber();
        AwardBudgetDocument awardBudgetDocument;
        if (isPostedBudgetExist(award)) {
            ScaleTwoDecimal obligatedChangeAmount = getTotalCostLimit(award);
            AwardBudgetExt previousPostedBudget = getLatestPostedBudget(award);
            AwardBudgetDocument postedBudgetDocument = previousPostedBudget.getBudgetDocument();
            awardBudgetDocument =  copyBudgetVersion(postedBudgetDocument);
            copyObligatedAmountToLineItems(awardBudgetDocument,obligatedChangeAmount);
        } else {
            awardBudgetDocument = (AwardBudgetDocument) documentService.getNewDocument(AwardBudgetDocument.class);
        }

        awardBudgetDocument.getDocumentHeader().setDocumentDescription(documentDescription);
        
        AwardBudgetExt awardBudget = awardBudgetDocument.getAwardBudget();
        awardBudget.setBudgetVersionNumber(budgetVersionNumber);
        awardBudget.setBudgetDocument(awardBudgetDocument);
        awardBudget.setAward(award);
        awardBudget.setAwardId(award.getAwardId());
        awardBudget.setName(documentDescription);
        awardBudget.setDocumentNumber(awardBudgetDocument.getDocumentNumber());
        AwardBudgetExt lastBudgetVersion = getLastBudgetVersion(award);
        awardBudget.setOnOffCampusFlag(lastBudgetVersion == null ? BudgetConstants.DEFAULT_CAMPUS_FLAG : lastBudgetVersion.getOnOffCampusFlag());
        if (awardBudgetDocument.getDocumentHeader() != null && awardBudgetDocument.getDocumentHeader().hasWorkflowDocument()) {
            awardBudget.setBudgetInitiator(awardBudgetDocument.getDocumentHeader().getWorkflowDocument().getInitiatorPrincipalId());
        }
        
        awardBudget.setStartDate(award.getRequestedStartDateInitial());
        awardBudget.setEndDate(award.getRequestedEndDateInitial());
        if(awardBudget.getOhRatesNonEditable()){
            awardBudget.setOhRateClassCode(getAwardParameterValue(Constants.AWARD_BUDGET_DEFAULT_FNA_RATE_CLASS_CODE));
            awardBudget.setUrRateClassCode(getAwardParameterValue( Constants.AWARD_BUDGET_DEFAULT_UNDERRECOVERY_RATE_CLASS_CODE));
        }else{
            awardBudget.setOhRateClassCode(getBudgetParameterValue(Constants.BUDGET_DEFAULT_OVERHEAD_RATE_CODE));
            awardBudget.setUrRateClassCode(getBudgetParameterValue( Constants.BUDGET_DEFAULT_UNDERRECOVERY_RATE_CODE));
        }
        awardBudget.setOhRateTypeCode(getBudgetParameterValue( Constants.BUDGET_DEFAULT_OVERHEAD_RATE_TYPE_CODE));
        awardBudget.setModularBudgetFlag(getParameterService().getParameterValueAsBoolean(Budget.class, Constants.BUDGET_DEFAULT_MODULAR_FLAG));
        awardBudget.setAwardBudgetStatusCode(getAwardParameterValue( KeyConstants.AWARD_BUDGET_STATUS_IN_PROGRESS));
        // do not want the Budget adjustment doc number to be copied over to the new budget.
        // this should be null so the budget can be posted again to the financial system.
        awardBudget.setBudgetAdjustmentDocumentNumber("");
        awardBudget.setRateClassTypesReloaded(true);
        setBudgetLimits(awardBudgetDocument, award);
        if (isPostedBudgetExist(award)) {
            if (awardBudget.getTotalCostLimit().equals(ScaleTwoDecimal.ZERO)) {
                rebudget = true;
            }
        }
        recalculateBudget(awardBudgetDocument.getBudget());
        saveBudgetDocument(awardBudgetDocument,rebudget);
        awardBudgetDocument = (AwardBudgetDocument) documentService.getByDocumentHeaderId(awardBudgetDocument.getDocumentNumber());

        return awardBudgetDocument;
    }

    private String getBudgetParameterValue(String parameter) {
        return getParameterService().getParameterValueAsString(Budget.class, parameter);
    }

    private String getAwardParameterValue(String parameter) {
        return getParameterService().getParameterValueAsString(AwardBudgetDocument.class, parameter);
    }
    
    public void setBudgetLimits(AwardBudgetDocument awardBudgetDocument, Award award) {
        AwardBudgetExt awardBudget = awardBudgetDocument.getAwardBudget();
        awardBudget.setTotalCostLimit(getTotalCostLimit(award));
        awardBudget.setObligatedTotal(new ScaleTwoDecimal(award.getBudgetTotalCostLimit().bigDecimalValue()));
        awardBudget.getAwardBudgetLimits().clear();
        for (AwardBudgetLimit limit : award.getAwardBudgetLimits()) {
            awardBudget.getAwardBudgetLimits().add(new AwardBudgetLimit(limit));
        }
    }
    
    protected void copyObligatedAmountToLineItems(AwardBudgetDocument awardBudgetDocument,ScaleTwoDecimal obligatedChangeAmount) {
        AwardBudgetExt newAwardBudgetFromPosted = awardBudgetDocument.getAwardBudget();
        List<BudgetPeriod> awardBudgetPeriods = newAwardBudgetFromPosted.getBudgetPeriods();
        for (BudgetPeriod budgetPeriod : awardBudgetPeriods) {
            AwardBudgetPeriodExt awardBudgetPeriod = (AwardBudgetPeriodExt) budgetPeriod;
            List<BudgetLineItem> lineItems = awardBudgetPeriod.getBudgetLineItems();
            for (BudgetLineItem budgetLineItem : lineItems) {
                AwardBudgetLineItemExt awardBudgetLineItem = (AwardBudgetLineItemExt) budgetLineItem;
                List<BudgetPersonnelDetails> personnelDetailsList = awardBudgetLineItem.getBudgetPersonnelDetailsList();
                for (BudgetPersonnelDetails budgetPersonnelDetails : personnelDetailsList) {
                    AwardBudgetPersonnelDetailsExt awardBudgetPersonnelDetails = (AwardBudgetPersonnelDetailsExt) budgetPersonnelDetails;
                    List<BudgetPersonnelCalculatedAmount> personnelCalcAmounts = awardBudgetPersonnelDetails.getBudgetCalculatedAmounts();
                    for (BudgetPersonnelCalculatedAmount budgetPersonnelCalculatedAmountExt : personnelCalcAmounts) {
                        AwardBudgetPersonnelCalculatedAmountExt awardBudgetPersonnelCalculatedAmountExt = (AwardBudgetPersonnelCalculatedAmountExt) budgetPersonnelCalculatedAmountExt;
                        awardBudgetPersonnelCalculatedAmountExt.setObligatedAmount(
                                awardBudgetPersonnelCalculatedAmountExt.getObligatedAmount().add(
                                        awardBudgetPersonnelCalculatedAmountExt.getCalculatedCost().add(
                                                awardBudgetPersonnelCalculatedAmountExt.getCalculatedCostSharing())));
                        awardBudgetPersonnelCalculatedAmountExt.setCalculatedCost(ScaleTwoDecimal.ZERO);
                        awardBudgetPersonnelCalculatedAmountExt.setCalculatedCostSharing(ScaleTwoDecimal.ZERO);
                    }
                    awardBudgetPersonnelDetails.setObligatedAmount(
                         awardBudgetPersonnelDetails.getObligatedAmount().add(
                            awardBudgetPersonnelDetails.getSalaryRequested().add(
                                    awardBudgetPersonnelDetails.getCostSharingAmount())));
                    awardBudgetPersonnelDetails.setPercentCharged(ScaleTwoDecimal.ZERO);
                    awardBudgetPersonnelDetails.setPercentEffort(ScaleTwoDecimal.ZERO);
                    awardBudgetPersonnelDetails.setSalaryRequested(ScaleTwoDecimal.ZERO);
                    awardBudgetPersonnelDetails.setCostSharingAmount(ScaleTwoDecimal.ZERO);
                }
                List<BudgetLineItemCalculatedAmount> calcAmounts = awardBudgetLineItem.getBudgetCalculatedAmounts();
                for (BudgetLineItemCalculatedAmount budgetLineItemCalculatedAmount : calcAmounts) {
                    AwardBudgetLineItemCalculatedAmountExt awardBudgetLineItemCalculatedAmount = (AwardBudgetLineItemCalculatedAmountExt) budgetLineItemCalculatedAmount;
                    awardBudgetLineItemCalculatedAmount.setObligatedAmount(
                            awardBudgetLineItemCalculatedAmount.getObligatedAmount().add(
                                    awardBudgetLineItemCalculatedAmount.getCalculatedCost().add(
                                            awardBudgetLineItemCalculatedAmount.getCalculatedCostSharing())));
                    awardBudgetLineItemCalculatedAmount.setCalculatedCost(ScaleTwoDecimal.ZERO);
                    awardBudgetLineItemCalculatedAmount.setCalculatedCostSharing(ScaleTwoDecimal.ZERO);
                }
                awardBudgetLineItem.setObligatedAmount(
                        awardBudgetLineItem.getObligatedAmount().add(
                        awardBudgetLineItem.getLineItemCost().add(
                                awardBudgetLineItem.getCostSharingAmount())));
                awardBudgetLineItem.setLineItemCost(ScaleTwoDecimal.ZERO);
                awardBudgetLineItem.setCostSharingAmount(ScaleTwoDecimal.ZERO);
            }
            awardBudgetPeriod.setObligatedAmount(awardBudgetPeriod.getObligatedAmount().add(awardBudgetPeriod.getTotalCost()));
            awardBudgetPeriod.setTotalCost(ScaleTwoDecimal.ZERO);
            awardBudgetPeriod.setTotalDirectCost(ScaleTwoDecimal.ZERO);
            awardBudgetPeriod.setTotalIndirectCost(ScaleTwoDecimal.ZERO);
            awardBudgetPeriod.setTotalCostLimit(obligatedChangeAmount);
        }
    }

    protected AwardBudgetExt getLatestPostedBudget(Award award) {
        QueryList<AwardBudgetExt> budgets = new QueryList<>();
        for (AwardBudgetExt budget : award.getBudgets()) {
        	budgets.add(budget);
        }
        
        Equals eqPostedStatus = new Equals(AWARD_BUDGET_STATUS_CODE,getAwardPostedStatusCode());
        QueryList<AwardBudgetExt> postedVersions = budgets.filter(eqPostedStatus);
        if(!postedVersions.isEmpty()){
            postedVersions.sort(BUDGET_VERSION_NUMBER,false);
            return postedVersions.get(0);
        }
        return null;
    }

    @Override
    public ScaleTwoDecimal getTotalCostLimit(Award award) {
        ScaleTwoDecimal obligatedTotal = award.getObligatedDistributableTotal();
        ScaleTwoDecimal costLimit = award.getTotalCostBudgetLimit();
        ScaleTwoDecimal postedTotalAmount = getPostedTotalAmount(award);
        if (costLimit == null || costLimit.isGreaterEqual(obligatedTotal)) {
            return new ScaleTwoDecimal(obligatedTotal.bigDecimalValue()).subtract(postedTotalAmount);
        } else {
            return new ScaleTwoDecimal(costLimit.bigDecimalValue()).subtract(postedTotalAmount);
        }
    }
    
    /**
     * Gets the total posted amount from previously posted budgets
     */
    protected ScaleTwoDecimal getPostedTotalAmount(Award award) {
        String postedStatusCode = getAwardPostedStatusCode();
        ScaleTwoDecimal postedTotalAmount = ScaleTwoDecimal.ZERO;
        for (AwardBudgetExt budget : award.getBudgets()) {
            if(budget.getAwardBudgetStatusCode().equals(postedStatusCode)){
                postedTotalAmount = postedTotalAmount.add(budget.getTotalCost());
            }
        }
        return postedTotalAmount;
    }

    /*
     * A utility method to check whther a budget has been posted for this award, then it can be 
     * used as one of the condition to set rebudget type.
     */
    protected boolean isPostedBudgetExist(Award award) {
        boolean exist = false;
        String postedStatusCode = getAwardPostedStatusCode();
        for (AwardBudgetExt budget : award.getBudgets()) {
            if(budget.getAwardBudgetStatusCode().equals(postedStatusCode)){
                exist = true;
                break;
            }
        }
        return exist;
    }

    protected String getAwardPostedStatusCode() {
        return getParameterService().getParameterValueAsString(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_STATUS_POSTED);
    }

    protected AwardBudgetExt getLastBudgetVersion(Award award) {
        List<AwardBudgetExt> awardBudgetDocumentVersions = award.getBudgets();
        AwardBudgetExt latestBudget = null;
        int versionSize = awardBudgetDocumentVersions.size();
        if(versionSize>0){
        	latestBudget = awardBudgetDocumentVersions.get(versionSize-1);
        }
        return latestBudget;
    }

    protected AwardBudgetDocument saveBudgetDocument(AwardBudgetDocument awardBudgetDocument,boolean rebudget) throws WorkflowException {
        AwardBudgetExt budgetExt = awardBudgetDocument.getAwardBudget();

        String awardBudgetTypeID = getParameterValue(rebudget ? KeyConstants.AWARD_BUDGET_TYPE_REBUDGET : KeyConstants.AWARD_BUDGET_TYPE_NEW);
        AwardBudgetType awardBudgetType = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetType.class, awardBudgetTypeID);
        budgetExt.setAwardBudgetTypeCode(awardBudgetType.getAwardBudgetTypeCode());
        budgetExt.setDescription(awardBudgetType.getDescription());
        budgetExt.setAwardBudgetType(awardBudgetType);
        
        processStatusChange(awardBudgetDocument, KeyConstants.AWARD_BUDGET_STATUS_IN_PROGRESS);
        saveDocument(awardBudgetDocument);
        return awardBudgetDocument;
    }

    
    protected void copyProposalBudgetLineItemsToAwardBudget(AwardBudgetPeriodExt awardBudgetPeriod, BudgetPeriod proposalBudgetPeriod) {
    	boolean warnOfRateEffectiveDateChange = false;
    	final Budget awardBudget = awardBudgetPeriod.getBudget();
		Date currentEffectiveDate = getEffectiveRateStartDate(awardBudget);
        List<BudgetLineItem> awardBudgetLineItems = awardBudgetPeriod.getBudgetLineItems();
        List<BudgetLineItem> lineItems = proposalBudgetPeriod.getBudgetLineItems();
        for (BudgetLineItem budgetLineItem : lineItems) {
            String[] ignoreProperties = {BUDGET_ID, BUDGET_LINE_ITEM_ID, BUDGET_PERIOD_ID, SUBMIT_COST_SHARING_FLAG,
                    BUDGET_LINE_ITEM_CALCULATED_AMOUNTS, BUDGET_PERSONNEL_DETAILS_LIST, BUDGET_RATE_AND_BASE_LIST, BUDGET_SUB_AWARD};
            AwardBudgetLineItemExt awardBudgetLineItem = new AwardBudgetLineItemExt(); 
            BeanUtils.copyProperties(budgetLineItem, awardBudgetLineItem, ignoreProperties);
            awardBudgetLineItem.setLineItemNumber(awardBudget.getNextValue(Constants.BUDGET_LINEITEM_NUMBER));
            awardBudgetLineItem.setBudgetId(awardBudgetPeriod.getBudgetId());
            boolean changeLineItemDates = false;
            changeLineItemDates = adjustLineItemDatesIfNecessary(awardBudgetLineItem, awardBudgetPeriod.getStartDate(), awardBudgetPeriod.getEndDate());
            
            List<BudgetPersonnelDetails> awardBudgetPersonnelLineItems = awardBudgetLineItem.getBudgetPersonnelDetailsList();
            List<BudgetPersonnelDetails> budgetPersonnelLineItems = budgetLineItem.getBudgetPersonnelDetailsList();
            for (BudgetPersonnelDetails budgetPersonnelDetails : budgetPersonnelLineItems) {
            	budgetPersonnelDetails.setBudgetLineItemId(budgetLineItem.getBudgetLineItemId());
                budgetPersonnelDetails.setBudgetLineItem(budgetLineItem);
                AwardBudgetPersonnelDetailsExt awardBudgetPerDetails = new AwardBudgetPersonnelDetailsExt();
                BeanUtils.copyProperties(budgetPersonnelDetails, awardBudgetPerDetails, 
                        BUDGET_PERSONNEL_LINE_ITEM_ID, BUDGET_LINE_ITEM_ID, BUDGET_ID, SUBMIT_COST_SHARING_FLAG,
                                BUDGET_PERSONNEL_CALCULATED_AMOUNTS, BUDGET_PERSONNEL_RATE_AND_BASE_LIST, VALID_TO_APPLY_IN_RATE);
                awardBudgetPerDetails.setPersonNumber(awardBudget.getNextValue(Constants.BUDGET_PERSON_LINE_NUMBER));
                BudgetPerson oldBudgetPerson = budgetPersonnelDetails.getBudgetPerson();
                BudgetPerson currentBudgetPerson = findMatchingPersonInBudget(awardBudget, 
                		oldBudgetPerson, budgetPersonnelDetails.getJobCode());
                if (currentBudgetPerson == null) {
                	currentBudgetPerson = new BudgetPerson();
                	BeanUtils.copyProperties(oldBudgetPerson, currentBudgetPerson, BUDGET_ID, PERSON_SEQUENCE_NUMBER);
                	currentBudgetPerson.setBudgetId(awardBudgetPeriod.getBudgetId());
                	currentBudgetPerson.setPersonSequenceNumber(awardBudget.getNextValue(Constants.PERSON_SEQUENCE_NUMBER));
                	awardBudget.getBudgetPersons().add(currentBudgetPerson);
                	if (currentBudgetPerson.getEffectiveDate() != null 
                			&& currentEffectiveDate.after(currentBudgetPerson.getEffectiveDate())) {
                		warnOfRateEffectiveDateChange = true;
                	}
                }
                awardBudgetPerDetails.setBudgetPerson(currentBudgetPerson);
                awardBudgetPerDetails.setPersonSequenceNumber(currentBudgetPerson.getPersonSequenceNumber());
                awardBudgetPerDetails.setBudget(awardBudget);
                awardBudgetPerDetails.setBudgetId(awardBudgetPeriod.getBudgetId());
                awardBudgetPerDetails.setCostElement(awardBudgetLineItem.getCostElement());
                changeLineItemDates &= adjustLineItemDatesIfNecessary(awardBudgetPerDetails, awardBudgetLineItem.getStartDate(), awardBudgetLineItem.getEndDate());
                awardBudgetPersonnelLineItems.add(awardBudgetPerDetails);
            }
            if (changeLineItemDates) {
                GlobalVariables.getMessageMap().putWarning(KRADConstants.GLOBAL_ERRORS, 
                        KeyConstants.WARNING_AWARD_BUDGET_LINE_ITEM_DATES_UPDATED, 
                        awardBudgetLineItem.getBudgetCategory() != null ? awardBudgetLineItem.getBudgetCategory().getDescription() : "", 
                        		awardBudgetLineItem.getCostElementName());
            }
            if (warnOfRateEffectiveDateChange) {
                GlobalVariables.getMessageMap().putWarning(KRADConstants.GLOBAL_ERRORS, 
                        KeyConstants.WARNING_AWARD_BUDGET_PERSON_EFFECTIVE_DATE);            	
            }
            awardBudgetLineItems.add(awardBudgetLineItem);
            populateCalculatedAmount(awardBudgetPeriod, awardBudgetLineItem);
        }
    }

	Date getEffectiveRateStartDate(final Budget awardBudget) {
		Date salaryEffectiveDate = getBudgetRatesService().getBudgetPersonSalaryEffectiveDate(awardBudget);
		if (salaryEffectiveDate == null || salaryEffectiveDate.after(awardBudget.getStartDate())) {
			salaryEffectiveDate = awardBudget.getStartDate();
		}
		return salaryEffectiveDate;
	}

	protected boolean adjustLineItemDatesIfNecessary(BudgetLineItemBase awardBudgetLineItem, java.sql.Date startDate, java.sql.Date endDate) {
		boolean changeLineItemDates = false;
		if (startDate != null) {
			if (startDate.after(awardBudgetLineItem.getStartDate())) {
				awardBudgetLineItem.setStartDate(startDate);
				changeLineItemDates = true;
			}
			if (startDate.after(awardBudgetLineItem.getEndDate())) {
				awardBudgetLineItem.setEndDate(endDate);
				changeLineItemDates = true;
			}
		}
		if (endDate != null) {
			if (endDate.before(awardBudgetLineItem.getEndDate())) {
				awardBudgetLineItem.setEndDate(endDate);
				changeLineItemDates = true;
			}
			if (endDate.before(awardBudgetLineItem.getStartDate())) {
				awardBudgetLineItem.setStartDate(startDate);
				changeLineItemDates = true;
			}
		}
		return changeLineItemDates;
	}

	protected void populateCalculatedAmount(AwardBudgetPeriodExt awardBudgetPeriod, AwardBudgetLineItemExt awardBudgetLineItem) {
		getAwardBudgetCalculationService().populateCalculatedAmount(awardBudgetPeriod.getBudget(), awardBudgetLineItem);
	}
    
    protected BudgetPerson findMatchingPersonInBudget(Budget budget, BudgetPerson oldBudgetPerson, String jobCode) {
        for (BudgetPerson budgetPerson : budget.getBudgetPersons()) {
        	if (budgetPerson.isSamePerson(oldBudgetPerson) && StringUtils.equals(budgetPerson.getJobCode(), jobCode)) {
        		return budgetPerson;
        	}
        }
        return null;
    }

    /**
     * Copies budget version from previous one
     */
    public AwardBudgetDocument copyBudgetVersion(AwardBudgetDocument budgetDocument) throws WorkflowException {
        return copyBudgetVersion(budgetDocument, false);
    }

	@Override
	public Budget copyBudgetVersion(Budget budget) {
		return copyBudgetVersion(budget, false);
	}

    public void copyLineItemsFromProposalPeriods(Collection<BudgetPeriod> rawValues, BudgetPeriod awardBudgetPeriod) throws WorkflowException {
        if (!(awardBudgetPeriod instanceof AwardBudgetPeriodExt)) {
        	throw new IllegalArgumentException("awardBudgetPeriod is not an AwardBudgetPeriodExt");
        }
    	//calling awardBudgetPeriod.getBudget() will load Budget.class instead of AwardBudgetExt.class
        //this will cause classcastexceptions later as the budget with that id is technically an AwardBudgetExt
        //this is all due to an ojb bug. So here we make sure OJB caches the budget as an AwardBudgetExt correctly.
        AwardBudgetExt budget = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetExt.class, awardBudgetPeriod.getBudgetId());
        awardBudgetPeriod.getBudgetLineItems().clear();


        for (BudgetPeriod proposalPeriod : rawValues) {
            copyProposalBudgetLineItemsToAwardBudget((AwardBudgetPeriodExt)awardBudgetPeriod, proposalPeriod);
        }
        getDocumentService().saveDocument(((AwardBudgetExt)awardBudgetPeriod.getBudget()).getBudgetDocument());        
        getBudgetSummaryService().calculateBudget(awardBudgetPeriod.getBudget());
    }

    protected <T extends BusinessObject> List<T> findMatching(Class<T> clazz, String key, Object value){
        Map<String,Object> fieldValues = new HashMap<>();
        fieldValues.put(key, value);
        return (List<T>) getBusinessObjectService().findMatching(clazz, fieldValues);
    }
    
    @Override
    public List<BudgetPeriod> findBudgetPeriodsFromLinkedProposal(String awardNumber) {
    	return findBudgetPeriodsFromLinkedProposal(awardService.getActiveOrNewestAward(awardNumber));
    }
    
    public List<BudgetPeriod> findBudgetPeriodsFromLinkedProposal(Award award) {
    	Set<Long> budgetIdsAdded = new HashSet<>();
        List<BudgetPeriod> budgetPeriods = new ArrayList<>();
        for (AwardFundingProposal fundingProposal : award.getAllFundingProposals()) {
            if (fundingProposal.isActive()) {
                List<InstitutionalProposal> instProposals = 
                    findMatching(InstitutionalProposal.class, PROPOSAL_NUMBER, fundingProposal.getProposal().getProposalNumber());
                for (InstitutionalProposal instProp : instProposals) {
                    List<ProposalAdminDetails> proposalAdminDetails = findMatching(ProposalAdminDetails.class,
                            INST_PROPOSAL_ID, instProp.getProposalId());
                    for (ProposalAdminDetails proposalAdminDetail : proposalAdminDetails) {
                        String developmentProposalNumber = proposalAdminDetail.getDevProposalNumber();
                        DevelopmentProposal proposalDevelopment = 
                        		getDataObjectService().find(DevelopmentProposal.class, developmentProposalNumber);
                        ProposalDevelopmentBudgetExt budget = proposalDevelopment.getFinalBudget();
                        if (!budgetIdsAdded.contains(budget.getBudgetId())) { 
                        	budgetIdsAdded.add(budget.getBudgetId());
                            //if this result set is being used by @see org.kuali.kra.lookup.BudgetPeriodLookupableHelperServiceImpl
                            //we need to populate these additional fields so always populate them.
                            for (BudgetPeriod budgetPeriod : budget.getBudgetPeriods()) {
                                budgetPeriod.setInstitutionalProposalNumber(instProp.getProposalNumber());
                                budgetPeriod.setInstitutionalProposalVersion(instProp.getSequenceNumber());
                                budgetPeriods.add(budgetPeriod);
                            }
                        }
                    }
                }
            }
        }
        return budgetPeriods;
    }
    

    public boolean checkForOutstandingBudgets(Award award) {
        boolean result = false;
        
        for (AwardBudgetExt awardBudget : award.getBudgets()) {
            if (!(StringUtils.equals(awardBudget.getAwardBudgetStatusCode(), getPostedBudgetStatus())
                    || StringUtils.equals(awardBudget.getAwardBudgetStatusCode(), getRejectedBudgetStatus())
                    || StringUtils.equals(awardBudget.getAwardBudgetStatusCode(), getCancelledBudgetStatus()))) {
                result = true;
                GlobalVariables.getMessageMap().putError(BUDGET_VERSION_ERROR_PREFIX, 
                        KeyConstants.ERROR_AWARD_UNFINALIZED_BUDGET_EXISTS, (StringUtils.isBlank(awardBudget.getName()) ? UNNAMED : awardBudget.getName()));
            }
        }
        
        return result;
    }
    
    @Override
    public List<String> getInactiveBudgetStatus() {
        List<String> result = new ArrayList<>();
        result.add(getRejectedBudgetStatus());
        result.add(getCancelledBudgetStatus());
        result.add(getDoNotPostBudgetStatus());
        result.add(getDisapprovedBudgetStatus());
        return result;
    }
    
    @Override
    public void populateBudgetLimitSummary(BudgetLimitSummaryHelper summary, Award award) {
        
        AwardBudgetExt currentBudget = getCurrentBudget(award);
        if (summary.getCurrentBudget() == null 
                || !ObjectUtils.equals(summary.getCurrentBudget(), currentBudget)) {
            getAwardBudgetCalculationService().calculateBudgetSummaryTotals(currentBudget, false);
            summary.setCurrentBudget(currentBudget);
        }
        AwardBudgetExt prevBudget = getPreviousBudget(award);
        if (summary.getPreviousBudget() == null
                || !ObjectUtils.equals(summary.getPreviousBudget(), prevBudget)) {
            getAwardBudgetCalculationService().calculateBudgetSummaryTotals(prevBudget, true);
            summary.setPreviousBudget(prevBudget);
        }
    }

    /**
     * Returns the current budget for the award. Must be inprogress, submitted or to be posted
     * to be the current budget.
     */
    protected AwardBudgetExt getCurrentBudget(Award award) {
        return getNewestBudgetByStatus(award, 
                Arrays.asList(Constants.BUDGET_STATUS_CODE_IN_PROGRESS, Constants.BUDGET_STATUS_CODE_SUBMITTED, Constants.BUDGET_STATUS_CODE_TO_BE_POSTED));
    }
    
    /**
     * Returns the previous budget for this award document which will be the newest posted budget
     */
    protected AwardBudgetExt getPreviousBudget(Award award) {
        return getNewestBudgetByStatus(award, Arrays.asList(getPostedBudgetStatus()));
    }         
    
    protected AwardBudgetExt getNewestBudgetByStatus(Award award, List<String> statuses) { 
        AwardBudgetExt result = null;
        for (AwardBudgetExt version : award.getBudgets()) {
            if (statuses.contains(version.getAwardBudgetStatusCode())) {
                if (result == null || version.getBudgetVersionNumber() > result.getBudgetVersionNumber()) {
                    result = version;
                }
            }
        }

        if (result == null) {
            result = new AwardBudgetExt();
        }
        return result;        
    }
    
    public List<AwardBudgetExt> getAllBudgetsForAward(Award award) {
        HashSet<AwardBudgetExt> result = new HashSet<>();
        List<VersionHistory> versions = getVersionHistoryService().loadVersionHistory(Award.class, award.getAwardNumber());
        for (VersionHistory version : versions) {
            if (version.getSequenceOwnerSequenceNumber() <= award.getSequenceNumber() && !(version.getSequenceOwner() == null) && !(((Award) version.getSequenceOwner()).getAwardDocument() == null)) {
                result.addAll(((Award) version.getSequenceOwner()).getCurrentVersionBudgets());
            }
        }
        List<AwardBudgetExt> listResult = new ArrayList<>(result);
        Collections.sort(listResult);
        return listResult;
    }
    
    public Award getActiveOrNewestAward(String awardNumber) {
        return awardService.getActiveOrNewestAward(awardNumber);
    }
    
    protected String getPostedBudgetStatus() {
        return getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_POSTED);
    }
    
    protected String getRejectedBudgetStatus() {
        return getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_REJECTED);
    }
    
    protected String getCancelledBudgetStatus() {
        return Constants.BUDGET_STATUS_CODE_CANCELLED;    
    }
    
    protected String getDisapprovedBudgetStatus() {
        return Constants.BUDGET_STATUS_CODE_DISAPPROVED;
    }
    
    protected String getDoNotPostBudgetStatus() {
        return getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_DO_NOT_POST);
    }

    public boolean isRateOverridden(BudgetPeriod budgetPeriod){
        return ((AwardBudgetPeriodExt)budgetPeriod).getRateOverrideFlag();
    }

    private ScaleTwoDecimal getPeriodFringeTotal(BudgetPeriod budgetPeriod, Budget budget) {
        if (budget == null ||
                budget.getBudgetSummaryTotals() == null ||
                budget.getBudgetSummaryTotals().get(PERSONNEL_FRINGE_TOTALS) == null ||
                budgetPeriod == null ||
                !CollectionUtils.validIndexForList(budgetPeriod.getBudgetPeriod() - 1, budget.getBudgetSummaryTotals().get(PERSONNEL_FRINGE_TOTALS))) {
            return ScaleTwoDecimal.ZERO;
        }
        return budget.getBudgetSummaryTotals().get(PERSONNEL_FRINGE_TOTALS).get(budgetPeriod.getBudgetPeriod() - 1);
    }

    public void recalculateBudget(Budget budget) {
        List<BudgetPeriod> awardBudgetPeriods = budget.getBudgetPeriods();
        for (BudgetPeriod budgetPeriod : awardBudgetPeriods) {
            removeBudgetSummaryPeriodCalcAmounts(budgetPeriod);
        }
        awardBudgetCalculationService.calculateBudget(budget);
        awardBudgetCalculationService.calculateBudgetSummaryTotals(budget);
    }
    public void recalculateBudgetPeriod(Budget budget,BudgetPeriod budgetPeriod) {
        removeBudgetSummaryPeriodCalcAmounts(budgetPeriod);
        awardBudgetCalculationService.calculateBudgetPeriod(budget, budgetPeriod);
    }

    public void calculateBudgetOnSave(Budget budget) {
    	awardBudgetCalculationService.calculateBudget(budget);
    	awardBudgetCalculationService.calculateBudgetSummaryTotals(budget);
        List<BudgetPeriod> awardBudgetPeriods = budget.getBudgetPeriods();
        for (BudgetPeriod awardBudgetPeriod : awardBudgetPeriods) {
            AwardBudgetPeriodExt budgetPeriod = (AwardBudgetPeriodExt)awardBudgetPeriod;
            ScaleTwoDecimal periodFringeTotal = getPeriodFringeTotal(budgetPeriod, budget);
            ScaleTwoDecimal totalFringeAmount = budgetPeriod.getTotalFringeAmount();
            ScaleTwoDecimal fringeAmountDiff = totalFringeAmount.subtract(periodFringeTotal);
        	ScaleTwoDecimal totalDirect = budgetPeriod.getTotalDirectCost().add(fringeAmountDiff);
			if(!totalDirect.equals(budgetPeriod.getTotalDirectCost())){
				budgetPeriod.setTotalDirectCost(totalDirect);
			}
            budgetPeriod.setTotalCost(budgetPeriod.getTotalDirectCost().add(budgetPeriod.getTotalIndirectCost()));
        }
        setBudgetCostsFromPeriods(budget);
    }
    

	public void populateSummaryCalcAmounts(Budget budget,BudgetPeriod budgetPeriod) {
        AwardBudgetPeriodExt awardBudgetPeriod = (AwardBudgetPeriodExt)budgetPeriod;
        List<AwardBudgetPeriodSummaryCalculatedAmount> awardBudgetPeriodFringeAmounts = awardBudgetPeriod.getAwardBudgetPeriodFringeAmounts();
        awardBudgetPeriodFringeAmounts.clear();
        if(awardBudgetPeriodFringeAmounts.isEmpty()){
            Map<String,List<ScaleTwoDecimal>> objectCodePersonnelFringe = budget.getObjectCodePersonnelFringeTotals();
            if(objectCodePersonnelFringe!=null){

                for (String costElement : objectCodePersonnelFringe.keySet()) {
                    String[] costElementAndPersonId = costElement.split(",");

                    List<ScaleTwoDecimal> fringeTotals = objectCodePersonnelFringe.get(costElement);
                    AwardBudgetPeriodSummaryCalculatedAmount oldAwardBudgetPeriodSummaryCalculatedAmount = 
                            getSummaryCalculatedAmountFromList(awardBudgetPeriodFringeAmounts,costElementAndPersonId[0]);
                    if(oldAwardBudgetPeriodSummaryCalculatedAmount==null){
                        AwardBudgetPeriodSummaryCalculatedAmount awardBudgetPeriodSummaryCalculatedAmount = 
                            createNewAwardBudgetPeriodSummaryCalculatedAmount(awardBudgetPeriod,costElementAndPersonId[0],RateClassType.EMPLOYEE_BENEFITS.getRateClassType(),
                                                fringeTotals.get(budgetPeriod.getBudgetPeriod()-1));
                        awardBudgetPeriodFringeAmounts.add(awardBudgetPeriodSummaryCalculatedAmount);
                    }else{
                        oldAwardBudgetPeriodSummaryCalculatedAmount.setCalculatedCost(
                                oldAwardBudgetPeriodSummaryCalculatedAmount.getCalculatedCost().add(fringeTotals.get(budgetPeriod.getBudgetPeriod()-1)));
                    }
                }
            }
            QueryList<AwardBudgetPeriodSummaryCalculatedAmount> ebCalculatedAmounts = filterEBRates(awardBudgetPeriod);
            awardBudgetPeriod.setTotalFringeAmount(ebCalculatedAmounts.sumObjects(CALCULATED_COST));
        }
    }
    private AwardBudgetPeriodSummaryCalculatedAmount getSummaryCalculatedAmountFromList(List<AwardBudgetPeriodSummaryCalculatedAmount> awardBudgetPeriodFringeAmounts,
                                                                                            String costElement) {
        for (AwardBudgetPeriodSummaryCalculatedAmount awardBudgetPeriodSummaryCalculatedAmount : awardBudgetPeriodFringeAmounts) {
            if(awardBudgetPeriodSummaryCalculatedAmount.getCostElement().equals(costElement)){
                return awardBudgetPeriodSummaryCalculatedAmount;
            }
        }
        return null;
    }

    /**
     * This method returns the query list after filtering all eb rates.
     */
    private QueryList<AwardBudgetPeriodSummaryCalculatedAmount> filterEBRates(AwardBudgetPeriodExt budgetPeriod) {
        QueryList<AwardBudgetPeriodSummaryCalculatedAmount> qlAwardBudgetPeriodSummaryCalculatedAmounts = 
                                                        new QueryList<>(budgetPeriod.getAwardBudgetPeriodFringeAmounts());
        Equals ebClassType = new Equals(RATE_CLASS_TYPE,RateClassType.EMPLOYEE_BENEFITS.getRateClassType());
        return qlAwardBudgetPeriodSummaryCalculatedAmounts.filter(ebClassType);
    }
    
    private AwardBudgetPeriodSummaryCalculatedAmount createNewAwardBudgetPeriodSummaryCalculatedAmount(AwardBudgetPeriodExt budgetPeriodExt,
                                            String costElement,String rateClassType,ScaleTwoDecimal calculatedCost) {
        AwardBudgetPeriodSummaryCalculatedAmount awardBudgetPeriodSummaryCalculatedAmount = new AwardBudgetPeriodSummaryCalculatedAmount();
        awardBudgetPeriodSummaryCalculatedAmount.setBudgetPeriodId(budgetPeriodExt.getBudgetPeriodId());
        awardBudgetPeriodSummaryCalculatedAmount.setCalculatedCost(calculatedCost);
        awardBudgetPeriodSummaryCalculatedAmount.setCostElement(costElement);
        awardBudgetPeriodSummaryCalculatedAmount.setRateClassType(rateClassType);
        return awardBudgetPeriodSummaryCalculatedAmount;
    }

    /**
     * 
     * This method sets the budget document's costs from the budget periods' costs.
     * This method modifies the passed in budget document.
     * 
     * @param budget the budget document to set the costs on.
     */
    protected void setBudgetCostsFromPeriods(final Budget budget) {
        assert budget != null : "The document is null";

        budget.setTotalDirectCost(budget.getSumDirectCostAmountFromPeriods());
        budget.setTotalIndirectCost(budget.getSumIndirectCostAmountFromPeriods());
        budget.setTotalCost(budget.getSumTotalCostAmountFromPeriods());
        budget.setUnderrecoveryAmount(budget.getSumUnderreoveryAmountFromPeriods());
        budget.setCostSharingAmount(budget.getSumCostSharingAmountFromPeriods());
    }

    public void removeBudgetSummaryPeriodCalcAmounts(BudgetPeriod budgetPeriod) {
        AwardBudgetPeriodExt awardBudgetPeriod = (AwardBudgetPeriodExt)budgetPeriod;
        awardBudgetPeriod.setTotalFringeAmount(null);
        awardBudgetPeriod.getAwardBudgetPeriodFringeAmounts().clear();
        awardBudgetPeriod.getAwardBudgetPeriodFnAAmounts().clear();
        awardBudgetPeriod.setRateOverrideFlag(false);
    }

    @Override
    public boolean validateAddingNewBudget(BudgetParentDocument<Award> parentDocument) {
    	AwardDocument awardDocument = (AwardDocument)parentDocument;
        return !checkForOutstandingBudgets(awardDocument.getAward());
    }
    
    @Override
    public boolean checkRateChange(Collection<BudgetRate> savedBudgetRates,Award award){
        award.refreshReferenceObject(AWARD_FANDA_RATE);
        List<AwardFandaRate> awardFandaRates = award.getAwardFandaRate();
        boolean changeFlag = false;
        for (AwardFandaRate awardFnARate : awardFandaRates) {
            RateType fnaRateType = awardFnARate.getFandaRateType();
            Equals eqRateClasCode = new Equals(RATE_CLASS_CODE, fnaRateType.getRateClassCode());
            Equals eqRateTypeCode = new Equals(RATE_TYPE_CODE, fnaRateType.getRateTypeCode());
            Equals eqCampusFlag = new Equals(ON_OFF_CAMPUS_FLAG, awardFnARate.getOnOffCampusFlag());
            And rateClassAndRateType = new And(eqRateClasCode,eqRateTypeCode);
            And rateClassAndRateTypeAndCampusFlag = new And(rateClassAndRateType,eqCampusFlag);
            QueryList<BudgetRate> matchedProposalRate = new QueryList<>(savedBudgetRates).filter(rateClassAndRateTypeAndCampusFlag);
            if(matchedProposalRate.isEmpty() || matchedProposalRate.size()>1 ||
                    !matchedProposalRate.get(0).getApplicableRate().equals(awardFnARate.getApplicableFandaRate())) {
                changeFlag = true;
            }
        }
        Equals eqRateClasCode = new Equals(RATE_CLASS_CODE, getBudgetParameterValue(Constants.AWARD_BUDGET_EB_RATE_CLASS_CODE));
        Equals eqRateTypeCode = new Equals(RATE_TYPE_CODE, getBudgetParameterValue(Constants.AWARD_BUDGET_EB_RATE_TYPE_CODE));
        And rateClassAndRateType = new And(eqRateClasCode,eqRateTypeCode);
        QueryList<BudgetRate> matchAwardEBCampusRates = new QueryList<>(savedBudgetRates).filter(rateClassAndRateType);
        for (BudgetRate budgetEBRate : matchAwardEBCampusRates) {
            if(budgetEBRate.getOnOffCampusFlag()) {
                if(award.getSpecialEbRateOnCampus()!=null && !award.getSpecialEbRateOnCampus().equals(budgetEBRate.getApplicableRate())){
                    changeFlag = true;
                }
            }else{
                if(award.getSpecialEbRateOffCampus()!=null && !award.getSpecialEbRateOffCampus().equals(budgetEBRate.getApplicableRate())){
                    changeFlag = true;
                }
            }
        }
        if((award.getSpecialEbRateOnCampus()!=null ||  award.getSpecialEbRateOffCampus()!=null) && matchAwardEBCampusRates.isEmpty()){
            changeFlag = true;
        }
        return changeFlag;
    }
    
    @Override
    public boolean isBudgetVersionNameValid(BudgetParent parent, String name) {
    	try {
			return getAddBudgetVersionRule().processAddBudgetVersion(new AddBudgetVersionEvent(BUDGET_VERSION_ERROR_PREFIX, parent, name));
		} catch (WorkflowException e) {
			LOG.error(e);
			return false;
		}
    }

	public BudgetVersionRule getAddBudgetVersionRule() {
		if (addBudgetVersionRule == null) {
			addBudgetVersionRule = new AwardBudgetVersionRule();
		}
		return addBudgetVersionRule;
	}

	public void setAddBudgetVersionRule(BudgetVersionRule addBudgetVersionRule) {
		this.addBudgetVersionRule = addBudgetVersionRule;
	}

    @Override
    public void populateNewBudgetLineItem(BudgetLineItem newBudgetLineItem, BudgetPeriod budgetPeriod) {
        Budget budget = budgetPeriod.getBudget();
        newBudgetLineItem.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
        newBudgetLineItem.setBudgetPeriodBO(budgetPeriod);
        newBudgetLineItem.setBudgetPeriodId(budgetPeriod.getBudgetPeriodId());
        newBudgetLineItem.setStartDate(budgetPeriod.getStartDate());
        newBudgetLineItem.setEndDate(budgetPeriod.getEndDate());
        newBudgetLineItem.setBudgetId(budget.getBudgetId());
        newBudgetLineItem.setLineItemNumber(budget.getNextValue(Constants.BUDGET_LINEITEM_NUMBER));
        newBudgetLineItem.setApplyInRateFlag(true);
        newBudgetLineItem.setSubmitCostSharingFlag(budget.getSubmitCostSharingFlag());
        refreshBudgetLineCostElement(newBudgetLineItem);
        // on/off campus flag enhancement
        String onOffCampusFlag = budget.getOnOffCampusFlag();
        if (onOffCampusFlag.equalsIgnoreCase(BudgetConstants.DEFAULT_CAMPUS_FLAG)) {
            newBudgetLineItem.setOnOffCampusFlag(newBudgetLineItem.getCostElementBO().getOnOffCampusFlag());
        } else {
            newBudgetLineItem.setOnOffCampusFlag(onOffCampusFlag.equalsIgnoreCase(Constants.ON_CAMUS_FLAG));
        }
        newBudgetLineItem.setBudgetCategoryCode(newBudgetLineItem.getCostElementBO().getBudgetCategoryCode());
        newBudgetLineItem.setLineItemSequence(newBudgetLineItem.getLineItemNumber());
        refreshBudgetLineBudgetCategory(newBudgetLineItem);

        if(isBudgetFormulatedCostEnabled()){
            List<String> formulatedCostElements = getFormulatedCostElements();
            if(formulatedCostElements.contains(newBudgetLineItem.getCostElement())){
                newBudgetLineItem.setFormulatedCostElementFlag(true);
            }
        }
    }

    protected void refreshBudgetLineCostElement(BudgetLineItem newBudgetLineItem) {
        if(StringUtils.isNotEmpty(newBudgetLineItem.getCostElement())) {
            getLegacyDataAdapter().refreshReferenceObject(newBudgetLineItem, COST_ELEMENT_BO);
        }
    }

    protected void refreshBudgetLineBudgetCategory(BudgetLineItem newBudgetLineItem) {
        if(StringUtils.isNotEmpty(newBudgetLineItem.getBudgetCategoryCode())) {
            getLegacyDataAdapter().refreshReferenceObject(newBudgetLineItem, BUDGET_CATEGORY);
        }
    }

    protected VersionHistoryService getVersionHistoryService() {
        return versionHistoryService;
    }

    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }

    protected AwardBudgetCalculationService getAwardBudgetCalculationService() {
        return awardBudgetCalculationService;
    }

    public void setAwardBudgetCalculationService(AwardBudgetCalculationService awardBudgetCalculationService) {
        this.awardBudgetCalculationService = awardBudgetCalculationService;
    }

    protected AwardService getAwardService() {
        return awardService;
    }

    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public BudgetSummaryService getBudgetSummaryService() {
        return budgetSummaryService;
    }

    public void setBudgetSummaryService(BudgetSummaryService budgetSummaryService) {
        this.budgetSummaryService = budgetSummaryService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public LegacyDataAdapter getLegacyDataAdapter() {
        return legacyDataAdapter;
    }

    public void setLegacyDataAdapter(LegacyDataAdapter legacyDataAdapter) {
        this.legacyDataAdapter = legacyDataAdapter;
    }

	public BudgetRatesService getBudgetRatesService() {
		return budgetRatesService;
	}

	public void setBudgetRatesService(BudgetRatesService budgetRatesService) {
		this.budgetRatesService = budgetRatesService;
	}
}
