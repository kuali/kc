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
package org.kuali.coeus.award.api;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.award.dto.AwardBudgetExtDto;
import org.kuali.coeus.award.dto.AwardBudgetGeneralInfoDto;
import org.kuali.coeus.award.dto.AwardDto;
import org.kuali.coeus.award.dto.BudgetPeriodDto;
import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.BudgetCommonService;
import org.kuali.coeus.common.budget.framework.core.BudgetCommonServiceFactory;
import org.kuali.coeus.common.budget.framework.core.BudgetParent;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonService;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelBudgetService;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.rest.NotImplementedException;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.kra.award.budget.*;
import org.kuali.kra.award.budget.calculator.AwardBudgetCalculationService;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.dao.AwardDao;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value="/api/v2")
@Controller("awardBudgetController")
public class AwardBudgetController extends RestController {

    public static final String POST_ACTION = "post";
    @Autowired
    @Qualifier("awardDao")
    private AwardDao awardDao;

    @Autowired
    @Qualifier("commonApiService")
    private CommonApiService commonApiService;

    @Autowired
    @Qualifier("awardBudgetService")
    private AwardBudgetService budgetService;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("awardBudgetService")
    private AwardBudgetService awardBudgetService;

    @Autowired
    @Qualifier("budgetPersonService")
    private BudgetPersonService budgetPersonService;

    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;

    @Autowired
    @Qualifier("awardBudgetCalculationService")
    private AwardBudgetCalculationService awardBudgetCalculationService;

    @Autowired
    @Qualifier("budgetPersonnelBudgetService")
    private BudgetPersonnelBudgetService budgetPersonnelBudgetService;

    @Autowired
    @Qualifier("awardBudgetPeriodCalculationService")
    private AwardBudgetPeriodCalculationService awardBudgetPeriodCalculationService;

    @Autowired
    @Qualifier("auditHelper")
    private AuditHelper auditHelper;

    @RequestMapping(method= RequestMethod.POST, value="/awards/{awardId}/budgets",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    AwardBudgetExtDto createBudget(@PathVariable Long awardId, @RequestBody AwardBudgetExtDto awardBudgetExtDto) throws WorkflowException, InvocationTargetException, IllegalAccessException {
        commonApiService.clearErrors();
        AwardDocument awardDocument = getAwardDocumentById(awardId);
        checkIfBudgetCanBeCreated(awardDocument, awardBudgetExtDto.getName());
        AwardBudgetExt newBudget = (AwardBudgetExt) budgetService.addBudgetVersion(awardDocument, awardBudgetExtDto.getName(), Collections.EMPTY_MAP);
        Integer budgetVersionNumber = newBudget.getBudgetVersionNumber();
        if(newBudget == null) {
            throw new UnprocessableEntityException("A new budget could not be created.");
        }
        AwardBudgetDocument budgetDocument = (AwardBudgetDocument) documentService.getByDocumentHeaderId(newBudget.getDocumentNumber());
        updateBudgetData(newBudget, awardBudgetExtDto);
        newBudget.setAwardId(awardId);
        translateBudgetPeriods(newBudget, awardBudgetExtDto);
        newBudget.setBudgetVersionNumber(budgetVersionNumber);
        awardBudgetService.calculateBudgetOnSave(newBudget);

        updateLineItemCalcAmountsIfRatesDisabled(newBudget, awardBudgetExtDto);
        calculateBudgetPeriods(newBudget);
        awardBudgetCalculationService.calculateBudget(newBudget);

        overrideFandAandFringeRatesIfAvailable(newBudget, awardBudgetExtDto);
        getBudgetCommonService(newBudget.getBudgetParent()).calculateBudgetOnSave(newBudget);

        List<AwardBudgetExt> budgets = new ArrayList<>();
        budgets.add(newBudget);
        budgetDocument.setBudgets(budgets);
        commonApiService.saveDocument(budgetDocument);
        return commonApiService.convertObject(newBudget, AwardBudgetExtDto.class);
    }

    protected BudgetCommonService<BudgetParent> getBudgetCommonService(BudgetParent budgetParent) {
        return BudgetCommonServiceFactory.createInstance(budgetParent);
    }

    protected void calculateBudgetPeriods(AwardBudgetExt newBudget) {
        newBudget.getBudgetPeriods().stream().forEach(budgetPeriod -> {
            awardBudgetPeriodCalculationService.calculateBudgetPeriod(Boolean.TRUE, newBudget, budgetPeriod);
        });
    }

    protected void overrideFandAandFringeRatesIfAvailable(AwardBudgetExt newBudget, AwardBudgetExtDto awardBudgetExtDto) {
        newBudget.getBudgetPeriods().stream().forEach(budgetPeriod -> {
            BudgetPeriodDto matchingPeriodDto = awardBudgetExtDto.getBudgetPeriods().stream().filter(budgetPeriodDto ->
                    budgetPeriodDto.getPeriodNumber().intValue() == budgetPeriod.getBudgetPeriod()).findFirst().get();
            AwardBudgetPeriodExt awardBudgetPeriod = (AwardBudgetPeriodExt) budgetPeriod;
            if (matchingPeriodDto.getTotalIndirectCost().isNonZero() || matchingPeriodDto.getTotalFringeAmount().isNonZero()) {
                awardBudgetPeriod.setRateOverrideFlag(true);
                awardBudgetPeriod.setTotalFringeAmount(awardBudgetExtDto.getBudgetPeriods().get(0).getTotalFringeAmount());
                awardBudgetPeriod.setPrevTotalFringeAmount(ScaleTwoDecimal.ZERO);
                awardBudgetPeriod.setTotalIndirectCost(awardBudgetExtDto.getBudgetPeriods().get(0).getTotalIndirectCost());
            }
        });
    }

    private void updateLineItemCalcAmountsIfRatesDisabled(AwardBudgetExt newBudget, AwardBudgetExtDto awardBudgetExtDto) {
        if(!awardBudgetExtDto.isApplyLineItemRates()) {
            newBudget.getBudgetPeriods().get(0).getBudgetLineItems().stream().flatMap(
                    budgetLineItem -> budgetLineItem.getBudgetLineItemCalculatedAmounts().stream())
                    .forEach(budgetLineItemCalculatedAmount -> budgetLineItemCalculatedAmount.setApplyRateFlag(false));

        }
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/award-budgets/{budgetId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void cancelDocument(@PathVariable Long budgetId) {
        commonApiService.clearErrors();
        Budget budget = businessObjectService.findBySinglePrimaryKey(Budget.class, budgetId);
        if (budget != null) {
            AwardBudgetDocument awardBudgetDocument = (AwardBudgetDocument) commonApiService.getDocumentFromDocId(Long.parseLong(budget.getDocumentNumber()));
            WorkflowDocument workflowDoc = awardBudgetDocument.getDocumentHeader().getWorkflowDocument();
            if (workflowDoc.isEnroute()
                    && StringUtils.equals(globalVariableService.getUserSession().getPrincipalId(), workflowDoc.getRoutedByPrincipalId())) {
                workflowDoc.superUserCancel("Cancelled by Routed By User");
            } else {
                try {
                    documentService.cancelDocument(awardBudgetDocument, KewApiConstants.ROUTE_HEADER_CANCEL_CD);
                } catch (WorkflowException e) {
                    throw new UnprocessableEntityException("Award budget with " + budgetId + " is not in a state to be cancelled.");
                }
            }
        }
    }

    @RequestMapping(method= RequestMethod.PUT, value="/award-budgets/{budgetId}/status",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void changeBudgetStatus(@PathVariable Long budgetId, @RequestBody AwardBudgetActionDto actionDto) throws Exception {
        commonApiService.clearErrors();
        Budget budget = businessObjectService.findBySinglePrimaryKey(Budget.class, budgetId);
        if (budget != null) {
            AwardBudgetDocument awardBudgetDocument = (AwardBudgetDocument) commonApiService.getDocumentFromDocId(Long.parseLong(budget.getDocumentNumber()));
            String actionToTake = actionDto.getActionToTake();
            if(actionToTake.equalsIgnoreCase(POST_ACTION)) {
                takePostAction(budgetId, awardBudgetDocument);
            } else {
                throw new NotImplementedException("The action of " + actionToTake + "has not yet been implemented.");
            }

        } else {
            throw new ResourceNotFoundException("Budget with budget id " + budgetId + " not found.");
        }
    }

    public void takePostAction(Long budgetId, AwardBudgetDocument awardBudgetDocument) throws Exception {
        if(!(awardBudgetDocument.getDocumentHeader().getWorkflowDocument().isFinal() ||
                awardBudgetDocument.getDocumentHeader().getWorkflowDocument().isProcessed())) {
            throw new UnprocessableEntityException("The budget " +  budgetId + " is not in final status and cannot be posted.");
        }
        if (awardBudgetService.isFinancialIntegrationOn()) {
            awardBudgetService.postWithFinancialIntegration(awardBudgetDocument);
        } else {
            awardBudgetService.post(awardBudgetDocument);
        }
    }

    @RequestMapping(method= RequestMethod.PUT, value="/award-budgets/{budgetId}", params="submit",
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void routeAwardBudget(@PathVariable Long budgetId) {
        commonApiService.clearErrors();
        Budget budget = businessObjectService.findBySinglePrimaryKey(Budget.class, budgetId);
        if (budget != null) {
            AwardBudgetDocument awardBudgetDocument = (AwardBudgetDocument) commonApiService.getDocumentFromDocId(Long.parseLong(budget.getDocumentNumber()));
            AwardBudgetExt awardBudgetExt = awardBudgetDocument.getAwardBudget();
            Award currentAward = awardBudgetService.getActiveOrNewestAward(awardBudgetExt.getAward().getAwardNumber());
            ScaleTwoDecimal newCostLimit = awardBudgetService.getTotalCostLimit(currentAward);
            if (newCostLimit.equals(budget.getTotalCostLimit())
                    && awardBudgetService.limitsMatch(currentAward.getAwardBudgetLimits(), awardBudgetExt.getAwardBudgetLimits())) {
                boolean valid = auditHelper.auditUnconditionally(awardBudgetDocument);
                if (valid) {
                    awardBudgetService.processSubmision(awardBudgetDocument);
                    commonApiService.routeDocument(awardBudgetDocument);
                } else {
                    throw new UnprocessableEntityException("Budget document with budget id " +  budgetId + " cannot be routed because of audit rules failures.");
                }
            } else {
                throw new UnprocessableEntityException("Total requested cost of the budget does not match the Budget Change Total Cost Limit.");
            }
        } else {
            throw new ResourceNotFoundException("Budget with budget id " + budgetId + " not found.");
        }
    }

    @RequestMapping(method= RequestMethod.GET, value="/award-budgets/{budgetId}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    AwardBudgetExtDto getAwardBudget(@PathVariable String budgetId) {
        AwardBudgetExt budget = getAwardDao().getAwardBudget(budgetId);
        if(budget == null) {
            throw new ResourceNotFoundException("Budget with budget id " + budgetId + " not found.");
        }
        return commonApiService.convertObject(budget, AwardBudgetExtDto.class);
    }

    @RequestMapping(method= RequestMethod.GET, value="/award-budgets", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    List<AwardBudgetExtDto> getAwardBudgetByStatus(@RequestParam(value = "budgetStatusCode", required = true) Integer budgetStatusCode) {
        List<AwardBudgetExt> budgets = getAwardDao().getAwardBudgetByStatusCode(budgetStatusCode);
        return budgets.stream().map(budget ->
                        commonApiService.convertObject(budget, AwardBudgetExtDto.class)
        ).collect(Collectors.toList());
    }

    @RequestMapping(method= RequestMethod.PUT, value="/award-budgets/{budgetId}/general-info/", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    void modifyAwardBudget(@RequestBody AwardBudgetGeneralInfoDto generalInfoDto, @PathVariable String budgetId) {
        commonApiService.clearErrors();
        AwardBudgetExt budget = getAwardDao().getAwardBudget(budgetId);
        if(budget == null) {
            throw new ResourceNotFoundException("Budget with budget id " + budgetId + " not found.");
        }
        commonApiService.updateDataObjectFromDto(budget, generalInfoDto);
        businessObjectService.save(budget);
    }

    protected void updateBudgetData(AwardBudgetExt newBudget, AwardBudgetExtDto awardBudgetExtDto) {
        newBudget.setBudgetInitiator(awardBudgetExtDto.getBudgetInitiator());
        newBudget.setAwardBudgetTypeCode(awardBudgetExtDto.getAwardBudgetTypeCode());
        newBudget.setDescription(awardBudgetExtDto.getDescription());
        newBudget.setOnOffCampusFlag(awardBudgetExtDto.getOnOffCampusFlag());
        newBudget.setEndDate(awardBudgetExtDto.getEndDate());
        newBudget.setStartDate(awardBudgetExtDto.getStartDate());
        newBudget.setOhRateTypeCode(awardBudgetExtDto.getOhRateTypeCode());
        newBudget.setOhRateClassCode(awardBudgetExtDto.getOhRateClassCode());
        newBudget.setComments(awardBudgetExtDto.getComments());
        newBudget.setModularBudgetFlag(awardBudgetExtDto.getModularBudgetFlag());
        newBudget.setUrRateClassCode(awardBudgetExtDto.getUrRateClassCode());
    }

    protected void translateBudgetPeriods(AwardBudgetExt newBudget, AwardBudgetExtDto awardBudgetExtDto) {
        List<BudgetPeriodDto> budgetPeriodDtos = awardBudgetExtDto.getBudgetPeriods();
        List<BudgetPeriod> budgetPeriods = budgetPeriodDtos.stream().map(budgetPeriodDto -> {
            AwardBudgetPeriodExt budgetPeriod = commonApiService.convertObject(budgetPeriodDto, AwardBudgetPeriodExt.class);
            budgetPeriod.setBudgetLineItems(new ArrayList<>());
            budgetPeriod.setBudget(newBudget);
            if (budgetPeriodDto.getPeriodNumber() == null) {
                throw new UnprocessableEntityException("Please enter a period number for the budget periods.");
            }
            budgetPeriod.setBudgetPeriod(budgetPeriodDto.getPeriodNumber());

            List<BudgetLineItem> budgetLineItems = budgetPeriodDto.getBudgetLineItems().stream().map(budgetLineItemDto -> {
                AwardBudgetLineItemExt budgetLineItem = commonApiService.convertObject(budgetLineItemDto, AwardBudgetLineItemExt.class);
                BudgetCategory newBudgetCategory = new BudgetCategory();
                newBudgetCategory.setBudgetCategoryTypeCode(budgetLineItemDto.getBudgetCategoryTypeCode());
                newBudgetCategory.refreshNonUpdateableReferences();
                budgetLineItem.setBudgetCategory(newBudgetCategory);
                if (!isPersonnelLineItem(budgetLineItem)) {
                    awardBudgetService.populateNewBudgetLineItem(budgetLineItem, budgetPeriod);
                    budgetLineItem.setBudgetPersonnelDetailsList(new ArrayList<>());
                } else {
                    budgetLineItem.setBudgetPersonnelDetailsList(new ArrayList<>());
                    budgetPersonnelBudgetService.addBudgetPersonnelToPeriod(budgetPeriod, budgetLineItem, new BudgetPersonnelDetails());
                }

                budgetLineItem.setBudgetLineItemCalculatedAmounts(new ArrayList<>());
                return budgetLineItem;
            }).collect(Collectors.toList());

            budgetPeriod.setBudgetLineItems(budgetLineItems);
            return budgetPeriod;
        }).collect(Collectors.toList());

        newBudget.setBudgetPersons(new ArrayList<>());
        awardBudgetExtDto.getBudgetPersons().stream().forEach(awardBudgetPersonDto -> {
                    BudgetPerson person = commonApiService.convertObject(awardBudgetPersonDto, BudgetPerson.class);
                    budgetPersonService.addBudgetPerson(newBudget, person);
                    person.setBudget(newBudget);
                }
        );
        newBudget.setAwardBudgetLimits(new ArrayList<>());
        newBudget.setBudgetPersonnelDetailsList(new ArrayList<>());
        newBudget.setBudgetPeriods(budgetPeriods);
    }

    protected boolean isPersonnelLineItem(BudgetLineItem budgetLineItem) {
        String personnelBudgetCategoryTypeCode = budgetCalculationService.getPersonnelBudgetCategoryTypeCode();
        return budgetLineItem.getBudgetCategory().getBudgetCategoryTypeCode().equalsIgnoreCase(personnelBudgetCategoryTypeCode);
    }

    protected boolean checkIfBudgetCanBeCreated(AwardDocument awardDocument, String name) {
        if (!budgetService.isBudgetVersionNameValid(awardDocument.getBudgetParent(), name) ||
                budgetService.checkForOutstandingBudgets(awardDocument.getAward())) {
            throw new UnprocessableEntityException(commonApiService.getValidationErrors());
        }
        return true;
    }

    protected AwardDocument getAwardDocument(Long documentNumber) {
        Document document = getDocument(documentNumber);
        AwardDocument awardDocument;
        if (document instanceof AwardDocument) {
            awardDocument = (AwardDocument) document;
        } else {
            throw new ResourceNotFoundException("The docId used in the request was not of type " + document.getClass().getName());
        }
        return awardDocument;
    }

    protected AwardDocument getAwardDocumentById(Long awardId) {
        Award award = awardDao.getAward(awardId);
        if(award == null) {
            throw new ResourceNotFoundException("Award with award id " + awardId + " not found.");
        }
        return (AwardDocument) commonApiService.getDocumentFromDocId(Long.parseLong(award.getAwardDocument().getDocumentNumber()));
    }

    protected Document getDocument(Long documentNumber) {
        return commonApiService.getDocumentFromDocId(documentNumber);
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setCommonApiService(CommonApiService commonApiService) {
        this.commonApiService = commonApiService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected List<String> getDtoProperties(Class dtoClass) throws IntrospectionException {
        return Arrays.asList(Introspector.getBeanInfo(dtoClass).getPropertyDescriptors()).stream()
                .map(PropertyDescriptor::getName)
                .filter(prop -> !"class".equals(prop))
                .collect(Collectors.toList());
    }

    protected List<AwardDto> translateAwards(boolean includeBudgets, List<Award> awards) {
        List<AwardDto> awardDtos = awards.stream().map(award -> {
                AwardDto awardDto = commonApiService.convertObject(award, AwardDto.class);
                if (!includeBudgets) {
                    awardDto.setBudgets(new ArrayList<>());
                }
                awardDto.setDocNbr(award.getAwardDocument().getDocumentNumber());
                awardDto.setStatusCode(award.getStatusCode());
                return awardDto;
            }).collect(Collectors.toList());
        return awardDtos;
    }

    public AwardDao getAwardDao() {
        return awardDao;
    }

}
