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
package org.kuali.kra.external.budget.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kfs.integration.cg.dto.BudgetAdjustmentCreationStatusDTO;
import org.kuali.kfs.integration.cg.dto.BudgetAdjustmentParametersDTO;
import org.kuali.kfs.integration.cg.dto.Details;
import org.kuali.kfs.module.external.kc.service.BudgetAdjustmentService;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.rate.RateType;
import org.kuali.kra.external.budget.BudgetAdjustmentClient;
import org.kuali.kra.external.budget.BudgetAdjustmentServiceHelper;
import org.kuali.kra.external.budget.FinancialObjectCodeMapping;
import org.kuali.kra.external.budget.RateClassRateType;
import org.kuali.kra.external.unit.service.InstitutionalUnitService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.ObjectUtils;

import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;
import java.util.*;


/**
 * This is the base class for the client that connects to the Budget Adjustment Service on the
 * financial system.
 * 
 */

public abstract class BudgetAdjustmentClientBase implements BudgetAdjustmentClient {

    protected static final String SOAP_SERVICE_NAME = "budgetAdjustmentServiceSOAP";
    protected static final QName SERVICE_NAME = new QName(Constants.FINANCIAL_SYSTEM_SERVICE_NAMESPACE, SOAP_SERVICE_NAME);
   
    private static final Log LOG = LogFactory.getLog(BudgetAdjustmentClientBase.class);

    private DocumentService documentService;
    private ParameterService parameterService;
    private BudgetCalculationService budgetCalculationService;
    private BusinessObjectService businessObjectService;
    private InstitutionalUnitService institutionalUnitService;
    private BudgetAdjustmentServiceHelper budgetAdjustmentServiceHelper;
    private ConfigurationService configurationService;

    /**
     * This method gets either the SOAP handle or the KSB handle depending on the configuration. 
     * @return
     */
    protected abstract BudgetAdjustmentService getServiceHandle();
    

    @Override
    public void createBudgetAdjustmentDocument(AwardBudgetDocument awardBudgetDocument) throws Exception {
        BudgetAdjustmentParametersDTO parametersDTO = new BudgetAdjustmentParametersDTO();
        boolean complete = setBudgetAdjustmentParameters(awardBudgetDocument, parametersDTO);    
        if (complete) {           
            try {
                BudgetAdjustmentService port = getServiceHandle();            
                LOG.info("Invoking createBudgetAdjustment...");           
                BudgetAdjustmentCreationStatusDTO budgetAdjustmentStatus = port.createBudgetAdjustment(parametersDTO);
            
                if (budgetAdjustmentStatus.getStatus().equalsIgnoreCase("success")) {
                 
                    // This should never happen.
                    if (budgetAdjustmentStatus.getDocumentNumber() == null) {
                        GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_MESSAGES, KeyConstants.DOCUMENT_NUMBER_NULL);
                        awardBudgetDocument.refresh();
                        String completeErrorMessage = "Document number returned from KFS budget adjustment service is null.";
                        GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, 
                                KeyConstants.BUDGET_ADJUSTMENT_DOCUMENT_NOT_CREATED, completeErrorMessage); 
                        LOG.warn(completeErrorMessage);
                   
                    } else {
                        awardBudgetDocument.getBudget().setBudgetAdjustmentDocumentNumber(budgetAdjustmentStatus.getDocumentNumber());
                        documentService.saveDocument(awardBudgetDocument);
                    }
                    //if there are error messages but the document was saved in KFS
                    if (ObjectUtils.isNotNull(budgetAdjustmentStatus.getErrorMessages()) 
                        && !budgetAdjustmentStatus.getErrorMessages().isEmpty()) {
                        String completeErrorMessage = "";
                        List<String> errorMessages = budgetAdjustmentStatus.getErrorMessages();
                        for (String errorMessage : errorMessages) {
                            completeErrorMessage += errorMessage;
                        }
                        GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, 
                                                                 KeyConstants.BUDGET_DOCUMENT_SAVED_WITH_ERRORS,
                                                                 completeErrorMessage);
                    }
                } else {
                    String completeErrorMessage = "";
                    List<String> errorMessages = budgetAdjustmentStatus.getErrorMessages();
                    for (String errorMessage : errorMessages) {
                        completeErrorMessage += errorMessage;
                    }
                    GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, 
                                                             KeyConstants.BUDGET_ADJUSTMENT_DOCUMENT_NOT_CREATED, completeErrorMessage); 
                }
            } catch (WebServiceException e) {
                String errorMessage = "Cannot connect to the service. The service may be down, please try again later.";
                GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.CANNOT_CONNECT_TO_SERVICE);
                LOG.error(errorMessage + e.getMessage(), e);
            } 
        } 
    }
   
    /**
     * This method adds the accounting lines to the DTO
     * @param parametersDTO 
     * @return
     * @throws Exception
     */
    public boolean setBudgetAdjustmentParameters(AwardBudgetDocument awardBudgetDocument, BudgetAdjustmentParametersDTO parametersDTO) throws Exception {
        boolean complete = true;    
        complete &= createBudgetAdjustmentDocumentHeader(awardBudgetDocument, parametersDTO);                  
        budgetCalculationService.calculateBudgetSummaryTotals(awardBudgetDocument.getAwardBudget());
        
        Map<String, ScaleTwoDecimal> accountingLines = new HashMap<String, ScaleTwoDecimal>();
        
        complete &= setNonPersonnelAccountingLines(awardBudgetDocument, accountingLines);
        
        // non personnel calculated direct cost
        complete &= setNonPersonnelCalculatedDirectCostAccountingLines(awardBudgetDocument, accountingLines);
        
        // salary
        complete &= setPersonnelSalaryAccountingLines(awardBudgetDocument, accountingLines);
       
        // calculated direct cost
        complete &= setPersonnnelCalculatedDirectCost(awardBudgetDocument, accountingLines);
        
        // Indirect cost 
        complete &= setIndirectCostAccountingLine(awardBudgetDocument, accountingLines);    
                
        // fringe
        complete &= setPersonnelFringeAccountingLines(awardBudgetDocument, accountingLines);
        
        createAccountingLines(accountingLines, awardBudgetDocument, parametersDTO);

        if (accountingLines.isEmpty()) {
            int period = awardBudgetDocument.getBudget().getBudgetPeriods().size();
            GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, KeyConstants.EMPTY_ACCOUNTING_LINES, period + "");
            complete &= false;
        }
        
        return complete;
    }
   
    
    /**
     * Sets PersonnelCalculatedDirect cost
     * @param accountingLines 
     * @return
     */
    protected boolean setPersonnnelCalculatedDirectCost(AwardBudgetDocument awardBudgetDocument, Map<String, ScaleTwoDecimal> accountingLines) {
        Budget currentBudget = awardBudgetDocument.getBudget();
        AwardBudgetExt previousBudget = getPrevBudget(awardBudgetDocument);
        boolean complete = true;
        Map<RateClassRateType, ScaleTwoDecimal> netPersonnelCalculatedDirectCost =
            getBudgetAdjustmentServiceHelper().getPersonnelCalculatedDirectCost(currentBudget, previousBudget);

        for (RateClassRateType rate : netPersonnelCalculatedDirectCost.keySet()) {
           LOG.info("Personnel calculated direct cost: " + rate.getRateType() + "-" + rate.getRateClass() + " = " + netPersonnelCalculatedDirectCost.get(rate));
            String financialObjectCode = getFinancialObjectCode(awardBudgetDocument, rate.getRateClass(), rate.getRateType());
            if (ObjectUtils.isNull(financialObjectCode)) {
                complete &= false;
            } else {
                if (!accountingLines.containsKey(financialObjectCode)) {
                    accountingLines.put(financialObjectCode, netPersonnelCalculatedDirectCost.get(rate));
                } else {
                    accountingLines.put(financialObjectCode, 
                                        accountingLines.get(financialObjectCode).add(netPersonnelCalculatedDirectCost.get(rate)));
                }
            }
        }
        return complete;
    }

    /**
     * This method sets the indirect cost.
     * @param accountingLines 
     * @return
     */
    protected boolean setIndirectCostAccountingLine(AwardBudgetDocument awardBudgetDocument, Map<String, ScaleTwoDecimal> accountingLines) {
        Budget currentBudget = awardBudgetDocument.getBudget();
        AwardBudgetExt previousBudget = getPrevBudget(awardBudgetDocument);
        boolean complete = true;
        Map<RateClassRateType, ScaleTwoDecimal> netIndirectCost = getBudgetAdjustmentServiceHelper().getIndirectCost(currentBudget, previousBudget);
        for (RateClassRateType rate : netIndirectCost.keySet()) {
            Details details = new Details();
            details.setCurrentAmount(netIndirectCost.get(rate).toString());
            LOG.info("Indirect cost: " + rate.getRateType() + "-" + rate.getRateClass() + " = " + netIndirectCost.get(rate));
            String financialObjectCode = getFinancialObjectCode(awardBudgetDocument, rate.getRateClass(), rate.getRateType());
            if (ObjectUtils.isNull(financialObjectCode)) {
                complete &= false; 
            } else {
                if (!accountingLines.containsKey(financialObjectCode)) {
                    accountingLines.put(financialObjectCode, netIndirectCost.get(rate));
                } else {
                    accountingLines.put(financialObjectCode, 
                                        accountingLines.get(financialObjectCode).add(netIndirectCost.get(rate)));
                }
            }
        }
        return complete;
    }
    
    /**
     * This method sets the fringe line.
     * @param accountingLines 
     * @return
     */
    protected boolean setPersonnelFringeAccountingLines(AwardBudgetDocument awardBudgetDocument, Map<String, ScaleTwoDecimal> accountingLines) {
        Budget currentBudget = awardBudgetDocument.getBudget();
        AwardBudgetExt previousBudget = getPrevBudget(awardBudgetDocument);
        boolean complete = true;
        Map<RateClassRateType, ScaleTwoDecimal> netFringeCost = getBudgetAdjustmentServiceHelper().getPersonnelFringeCost(currentBudget, previousBudget);
        for (RateClassRateType rate : netFringeCost.keySet()) {
            LOG.info("Personnel fringe cost: " + rate.getRateType() + "-" + rate.getRateClass() + " = " + netFringeCost.get(rate));

            String financialObjectCode = getFinancialObjectCode(awardBudgetDocument, rate.getRateClass(), rate.getRateType());
            if (ObjectUtils.isNull(financialObjectCode)) {
                complete &= false;
            } else {
                if (!accountingLines.containsKey(financialObjectCode)) {
                    accountingLines.put(financialObjectCode, netFringeCost.get(rate));
                } else {
                    accountingLines.put(financialObjectCode, 
                                        accountingLines.get(financialObjectCode).add(netFringeCost.get(rate)));
                }
              
            }
        }
        return complete;
    }
    
    /**
     * This method sets the personnel salary accounting line.
     * @param accountingLines 
     * @return
     * @throws Exception
     */
    protected boolean  setPersonnelSalaryAccountingLines(AwardBudgetDocument awardBudgetDocument, Map<String, ScaleTwoDecimal> accountingLines) throws Exception {
        Budget currentBudget = awardBudgetDocument.getBudget();
        AwardBudgetExt previousBudget = getPrevBudget(awardBudgetDocument);
        boolean complete = true;
        SortedMap<String, ScaleTwoDecimal> netCost = getBudgetAdjustmentServiceHelper().getPersonnelSalaryCost(currentBudget, previousBudget);
        for (String name : netCost.keySet()) {
            String financialObjectCode = getFinancialObjectCode(name);
            if (ObjectUtils.isNull(financialObjectCode)) {
                complete &= false;
            } else {
                if (!accountingLines.containsKey(financialObjectCode)) {
                    accountingLines.put(financialObjectCode, netCost.get(name));
                } else {
                    accountingLines.put(financialObjectCode, 
                                        accountingLines.get(financialObjectCode).add(netCost.get(name)));
                }
              
            }
        }    
        return complete;
    }
    
    /**
     * This method sets the non personnel calculated direct cost.
     * @param accountingLines 
     * @return
     */
    protected boolean setNonPersonnelCalculatedDirectCostAccountingLines(AwardBudgetDocument awardBudgetDocument, Map<String, ScaleTwoDecimal> accountingLines) {
        boolean complete = true;
        Budget currentBudget = awardBudgetDocument.getBudget();
        AwardBudgetExt previousBudget = getPrevBudget(awardBudgetDocument);
        SortedMap<RateType, ScaleTwoDecimal> netExpense = getBudgetAdjustmentServiceHelper().getNonPersonnelCalculatedDirectCost(currentBudget, previousBudget);
        SortedMap<RateType, List<ScaleTwoDecimal>> currentNonPersonnelCalcDirectCost = awardBudgetDocument.
                                                                                     getAwardBudget().
                                                                                     getNonPersonnelCalculatedExpenseTotals();
        
        for (RateType rateType : netExpense.keySet()) {
            LOG.info("NonPersonnel calculated direct cost: " + rateType.getRateTypeCode() + "-" + rateType.getRateClassCode() + " = " + netExpense.get(rateType));

            // check if rate class type is O instead
            if (!rateType.getRateClass().getRateClassTypeCode().equalsIgnoreCase("O")) {
                List<ScaleTwoDecimal> expenses = currentNonPersonnelCalcDirectCost.get(rateType);
                Details details = new Details();
                details.setCurrentAmount(netExpense.get(rateType).toString());
                // only need abs value of amount
                String financialObjectCode = getFinancialObjectCode(awardBudgetDocument, rateType.getRateClassCode(), rateType.getRateTypeCode());
                if (ObjectUtils.isNull(financialObjectCode)) {
                    complete &= false;
                } else {
                    if (!accountingLines.containsKey(financialObjectCode)) {
                        accountingLines.put(financialObjectCode, netExpense.get(rateType));
                    } else {
                        accountingLines.put(financialObjectCode, 
                                            accountingLines.get(financialObjectCode).add(netExpense.get(rateType)));
                    }
                  
                }

            }

        }
        return complete;
    }
    

    /**
     * This method sets the no personnel accounting line.
     * @param accountingLines 
     * @return
     */
    protected boolean setNonPersonnelAccountingLines(AwardBudgetDocument awardBudgetDocument, Map<String, ScaleTwoDecimal> accountingLines) {
        Budget currentBudget = awardBudgetDocument.getBudget();
        AwardBudgetExt previousBudget = getPrevBudget(awardBudgetDocument);
        HashMap<String, ScaleTwoDecimal> nonPersonnelCost = getBudgetAdjustmentServiceHelper().getNonPersonnelCost(currentBudget, previousBudget);
        boolean complete = true;
        for (String costElement : nonPersonnelCost.keySet()) {
            if (ObjectUtils.isNotNull(getFinancialObjectCode(costElement))) {
                ScaleTwoDecimal currentAmount = nonPersonnelCost.get(costElement).abs();
                // only add line item if amount is non-zero
                if (currentAmount.isNonZero()) {
                   
                    String financialObjectCode = getFinancialObjectCode(costElement);
                    if (!accountingLines.containsKey(financialObjectCode)) {
                        accountingLines.put(financialObjectCode, nonPersonnelCost.get(costElement));
                    } else {
                        accountingLines.put(financialObjectCode, 
                                            accountingLines.get(financialObjectCode).add(nonPersonnelCost.get(costElement)));
                    }
                    LOG.info("NonPersonnelCalculatedDirectCost OC: " + financialObjectCode + " = " + accountingLines.get(financialObjectCode));
                }
            } else {
                GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, 
                                                         KeyConstants.FINANCIAL_OBJECT_CODE_MAPPING_NOT_FOUND, 
                                                         "Object Code: " + costElement); 
                complete &= false;
            }
        } 
        return complete;
    }
    
    /**
     * This method returns the previous version of the award budget.
     * @return
     */
    protected AwardBudgetExt getPrevBudget(AwardBudgetDocument awardBudgetDocument) {
        int currentVersionNumber = awardBudgetDocument.getBudget().getBudgetVersionNumber();
        AwardBudgetExt prevBudget = getPrevBudget((AwardDocument) awardBudgetDocument.getBudget().getBudgetParent().getDocument());
        if (ObjectUtils.isNotNull(prevBudget.getBudgetVersionNumber()) && prevBudget.getBudgetVersionNumber() < currentVersionNumber) {
            budgetCalculationService.calculateBudgetSummaryTotals(prevBudget);
            return prevBudget;
        }
        return null;  
    }
    
    protected String getParameterValue(String awardBudgetParameter) {
        return  parameterService.getParameterValueAsString(AwardBudgetDocument.class, awardBudgetParameter);
    }
    
    protected String getPostedBudgetStatus() {
        return getParameterValue(KeyConstants.AWARD_BUDGET_STATUS_POSTED);
    }
    
    /**
     * This method returns previous budget
     * @param awardDocument
     * @return
     */
    protected AwardBudgetExt getPrevBudget(AwardDocument awardDocument) {
        return getNewestBudgetByStatus(awardDocument, Arrays.asList(new String[]{getPostedBudgetStatus()}));
    }         
    
    /**
     * This method returns the newest budget by status
     * @param awardDocument
     * @param statuses
     * @return
     */
    protected AwardBudgetExt getNewestBudgetByStatus(AwardDocument awardDocument, List<String> statuses) {
    	AwardBudgetExt result = null;
        for (AwardBudgetExt curVersion : awardDocument.getAward().getBudgets()) {
            if (statuses.contains(curVersion.getAwardBudgetStatusCode())) {
                if (result == null || curVersion.getBudgetVersionNumber() > result.getBudgetVersionNumber()) {
                	result = curVersion;
                }
            }
        }
        if (result == null) {
            result = new AwardBudgetExt();
        }
        return result;        
    }

    /**
     * This method sets the budget adjustment document header.
     * @return
     */
    protected boolean createBudgetAdjustmentDocumentHeader(AwardBudgetDocument awardBudgetDocument, BudgetAdjustmentParametersDTO parametersDTO) {
        //use award doc number
        parametersDTO.setOrgDocNumber("");   
        // budget version number
        Award award = (Award) awardBudgetDocument.getBudget().getBudgetParent();
        parametersDTO.setSponsorType(award.getSponsor().getSponsorTypeCode());
        //Just logging message - creating a new budget adjustment document from KC
        String COMMENT = "Generated from award budget -" 
                         + awardBudgetDocument.getDocumentNumber();
        parametersDTO.setDescription(COMMENT);

        parametersDTO.setPrincipalId(GlobalVariables.getUserSession().getPrincipalId());

        return true;
    }

    /**
     * This method creates the accounting lines for the BA.
     */
    protected void createAccountingLines(Map<String, ScaleTwoDecimal> accountingLines, AwardBudgetDocument awardBudgetDocument, BudgetAdjustmentParametersDTO parametersDTO) {
        
        for (String objectCode : accountingLines.keySet()) {
            if (accountingLines.get(objectCode).isNonZero()) {
                Details details = new Details();
                details.setCurrentAmount(accountingLines.get(objectCode).toString());
                details.setObjectCode(objectCode);
                details.setChart(getAwardChart(awardBudgetDocument));
                details.setAccount(getAwardAccount(awardBudgetDocument));
                details.setProjectCode("");
                details.setSubAccount("");
                parametersDTO.getDetails().add(details);
                LOG.info("ObjectCode: " + objectCode + "Amount: " + accountingLines.get(objectCode));
            }
        }
        
    }
  
    
    /**
     * This method returns the chart of accounts code associated with the account.
     * @param awardBudgetDocument
     * @return
     */
    protected String getAwardChart(AwardBudgetDocument awardBudgetDocument) {
        Award award = (Award) awardBudgetDocument.getBudget().getBudgetParent();
        return award.getFinancialChartOfAccountsCode();
    }
   
    /**
     * This is obtained using the mapping table.
     * @param rateClassCode
     * @param rateTypeCode
     * @return
     */
    protected String getFinancialObjectCode(AwardBudgetDocument awardBudgetDocument, String rateClassCode, String rateTypeCode) {
        // Do not use activity type in criteria, it is not required.
        Award award = (Award) awardBudgetDocument.getBudget().getBudgetParent();
        String activityTypeCode = award.getActivityTypeCode();
        String awardUnitNumber = award.getUnitNumber();
        List<FinancialObjectCodeMapping> results = getFinancialObjectCodesFromMappingTable(rateClassCode, rateTypeCode, awardUnitNumber);
        if (results.isEmpty()) {
            //if not, go up the unit hierarchy and check to see if something is listed there
            List<String> parentUnits = institutionalUnitService.getParentUnits(awardUnitNumber);
            for (String currentUnitNumber : parentUnits) {                
                List<FinancialObjectCodeMapping> parentUnitResults = getFinancialObjectCodesFromMappingTable(
                                                                        rateClassCode, rateTypeCode, currentUnitNumber);
                if (!parentUnitResults.isEmpty()) {
                    return parentUnitResults.get(0).getFinancialObjectCode();
                }
            }
           
            GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS,  KeyConstants.FINANCIAL_OBJECT_CODE_MAPPING_NOT_FOUND, 
                                                    " Rate Class Code: " + rateClassCode + " Rate Type Code: " + rateTypeCode 
                                                    + " Unit: " + award.getUnitNumber() + " Activity Type: " + award.getActivityTypeCode()); 

            LOG.error("Mapping not found for rateClasssCode: " + rateClassCode + "rateTypeCode: " + rateTypeCode + "unitnumber: ");
            return null;
        } else {
            // if results were returned, check if award activityType is in it
            for (FinancialObjectCodeMapping result : results) {
                if (ObjectUtils.isNotNull(result.getActivityTypeCode()) 
                    && result.getActivityTypeCode().equalsIgnoreCase(activityTypeCode)) {
                    return result.getFinancialObjectCode();
                }
            }
            // if the correct activity type was not in it, just send any one 
            // (there should really just be one result for the combination)
            
            LOG.info("Returning" + results.get(0).getFinancialObjectCode());
            return results.get(0).getFinancialObjectCode();
        }
    }
    
    /**
     * This is obtained from the cost element table.
     * @param costElementName
     * @return
     */
    protected String getFinancialObjectCode(String costElementName) {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("costElement", costElementName);
        CostElement costElement = (CostElement) getBusinessObjectService().findByPrimaryKey(CostElement.class, criteria);
        if (ObjectUtils.isNull(costElement.getFinancialObjectCode())) {
            GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, 
                                                     KeyConstants.FINANCIAL_OBJECT_CODE_MAPPING_NOT_FOUND, 
                                                     "Object Code: " + costElementName);
            LOG.error("No financial system object code mapped to object code " + costElementName + " .");
        }
        return costElement.getFinancialObjectCode();
    }
    
    /**
     * This method gets the KFs object code from the mapping table.
     * @param rateClassCode
     * @param rateTypeCode
     * @param unitNumber
     * @return
     */
    protected List<FinancialObjectCodeMapping> getFinancialObjectCodesFromMappingTable(String rateClassCode, String rateTypeCode, String unitNumber) {
        Map criteria = new HashMap();
        criteria.put("rateClassCode", rateClassCode);
        criteria.put("rateTypeCode", rateTypeCode);
        criteria.put("unitNumber", unitNumber);

        // Do not use activity type in criteria, it is not required.
        List<FinancialObjectCodeMapping> results = new ArrayList<FinancialObjectCodeMapping>(
                businessObjectService.findMatching(FinancialObjectCodeMapping.class, criteria));
        return results;
    }
    
   
    /**
     * This method returns the award account number.
     * @param awardBudgetDocument
     * @return
     */
    protected String getAwardAccount(AwardBudgetDocument awardBudgetDocument) {
        Award award = (Award) awardBudgetDocument.getBudget().getBudgetParent();
        return award.getAccountNumber();
    }
    
    protected BudgetAdjustmentServiceHelper getBudgetAdjustmentServiceHelper() {
        return budgetAdjustmentServiceHelper;
    }
    
    public void setBudgetAdjustmentServiceHelper(BudgetAdjustmentServiceHelper budgetAdjustmentServiceHelper) {
        this.budgetAdjustmentServiceHelper = budgetAdjustmentServiceHelper;
    }
    
    public void setInstitutionalUnitService(InstitutionalUnitService institutionalUnitService) {
        this.institutionalUnitService = institutionalUnitService;
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;   
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    /*
     * 
     */
    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }
    
    public ParameterService getParameterService() {
        return parameterService;
    }
    
    /*
     * Sets the parameterService attribute value. Injected by Spring.
     * 
     * @param parameterService The parameterService to set.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    
    /**
     * Sets the documentService attribute value. Injected by Spring.
     * 
     * @param documentService The documentService to set.
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}


