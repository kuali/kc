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
package org.kuali.coeus.common.budget.impl.calculator;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.framework.query.QueryList;
import org.kuali.coeus.common.budget.api.rate.RateClassType;
import org.kuali.coeus.common.budget.framework.core.DateSortable;
import org.kuali.coeus.common.budget.framework.query.operator.*;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.CostElement;
import org.kuali.coeus.common.budget.framework.personnel.AppointmentType;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPerson;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.rate.ValidCeRateType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;

/**
 * This class is to calculate the salary
 */
public class SalaryCalculator {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SalaryCalculator.class);
    private static final String STRING_1 = "1";
    private static final int DEFAULT_WORKING_MONTHS = 12;
    private Budget budget;
    private BudgetPersonnelDetails personnelLineItem;
    private Date startDate;
    private Date endDate;
    private DateTimeService dateTimeService;
    private List<String> errorList;
    private List<String> warningList;
    private QueryList<BudgetRate> inflationRates;


    public SalaryCalculator(Budget budget, BudgetPersonnelDetails personnelLineItem) {
        this.budget = budget;
        this.personnelLineItem = personnelLineItem;
        this.startDate = personnelLineItem.getStartDate();
        this.endDate = personnelLineItem.getEndDate();
        errorList = new ArrayList<String>();
        warningList = new ArrayList<String>();
        this.dateTimeService = KcServiceLocator.getService(DateTimeService.class);
    }

    /**
     * 
     * This method for filtering the inflation rates with respect to start date and end date
     * 
     * @return list of inflation rates
     */
    private QueryList<BudgetRate> filterInflationRates() {
        CostElement costElement = personnelLineItem.getCostElementBO();
        if (costElement == null) {
            BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
            Map<String, String> pkMap = new HashMap<String, String>();
            pkMap.put("costElement", personnelLineItem.getCostElement());
            costElement = (CostElement) businessObjectService.findByPrimaryKey(CostElement.class, pkMap);
        }
        List<ValidCeRateType> costElementRates = costElement.getValidCeRateTypes();
        if (costElementRates == null || costElementRates.isEmpty()) {
            costElement.refreshReferenceObject("validCeRateTypes");
            costElementRates = costElement.getValidCeRateTypes();
        }
        ValidCeRateType inflationRateType = null;
        if (costElementRates != null){
            for (ValidCeRateType validCeRateType : costElementRates) {
                if (validCeRateType.getRateClassType().equals(RateClassType.INFLATION.getRateClassType())) {
                    inflationRateType = validCeRateType;
                    break;
                }
            }
        }
        Equals eInflationRC = null;
        Equals eInflationRT = null;
        And inflRCandRT = null;

        if (inflationRateType != null) {
            eInflationRC = new Equals("rateClassCode", inflationRateType.getRateClassCode());
            eInflationRT = new Equals("rateTypeCode", inflationRateType.getRateTypeCode());
            inflRCandRT = new And(eInflationRC, eInflationRT);
        }

        LesserThan ltEndDate = new LesserThan("startDate", this.endDate);
        Equals eEndDate = new Equals("startDate", this.endDate);
        Or ltOrEqEndDate = new Or(ltEndDate, eEndDate);

        GreaterThan gtStartDate = new GreaterThan("startDate", this.startDate);
        Equals eStartDate = new Equals("startDate", this.startDate);
        Or gtOrEqStartDate = new Or(gtStartDate, eStartDate);

        And gtOrEqStartDateAndltOrEqEndDate = new And(gtOrEqStartDate, ltOrEqEndDate);
        And dateAndRate = new And(inflRCandRT, gtOrEqStartDateAndltOrEqEndDate);

        Equals onOffCampus = new Equals("onOffCampusFlag", costElement.getOnOffCampusFlag());
        And dateAndRateAndOnOffCampusFlag = new And(dateAndRate, onOffCampus);

        if (personnelLineItem.getApplyInRateFlag()) {
            return getInflationRates() == null ? new QueryList<BudgetRate>(budget.getBudgetRates())
                    .filter(dateAndRateAndOnOffCampusFlag) : getInflationRates().filter(dateAndRateAndOnOffCampusFlag);
        }
        else {
            return new QueryList<BudgetRate>();
        }

    }

    private QueryList<BudgetPerson> filterBudgetPersons() {
        QueryList<BudgetPerson> filteredPersons = new QueryList<BudgetPerson>();
        List<BudgetPerson> savedBudgetPersons = new ArrayList<>(budget.getBudgetPersons());

        if (savedBudgetPersons.isEmpty()){
        	return filteredPersons;
        }

        int i = 0;
        boolean personInDocument = false;
        List<BudgetPerson> documentBudgetPersons = new ArrayList<BudgetPerson>();

        for(BudgetPerson savedPerson : savedBudgetPersons) {
            personInDocument = false;
            for (BudgetPerson docPerson : budget.getBudgetPersons()) {
                if (savedPerson.getPersonSequenceNumber().intValue() == docPerson.getPersonSequenceNumber().intValue()) {
                    documentBudgetPersons.add(i, docPerson);
                    personInDocument = true;
                }
            }

            if (!personInDocument) {
                documentBudgetPersons.add(i, savedPerson);
            }
            i++;
        }

        QueryList<BudgetPerson> budgetPersons = new QueryList<BudgetPerson>(documentBudgetPersons);

        Equals ePersonSeqNumber = new Equals("personSequenceNumber", personnelLineItem.getPersonSequenceNumber());
        QueryList<BudgetPerson> fltdBudgetPersonList = budgetPersons.filter(ePersonSeqNumber);
        if (fltdBudgetPersonList.isEmpty()){
            return filteredPersons;
        }
        BudgetPerson budgetPerson = fltdBudgetPersonList.get(0);
        Equals ePersonId = new Equals("personId", budgetPerson.getPersonId());
        Equals eJobCode = new Equals("jobCode", budgetPerson.getJobCode());
        Equals eRolodexId = new Equals("rolodexId", budgetPerson.getRolodexId());
        And personIdAndJobCode = new And(ePersonId, eJobCode);
        And personIdAndRoldexIdAndJobCode = new And(personIdAndJobCode, eRolodexId);
        LesserThan ltEndDate = new LesserThan("effectiveDate", this.endDate);
        Equals eEndDate = new Equals("effectiveDate", this.endDate);
        Or ltOrEqEndDate = new Or(ltEndDate, eEndDate);
        And personIdAndJobCodeAndltOrEqEndDate = new And(personIdAndRoldexIdAndJobCode, ltOrEqEndDate);

        filteredPersons = budgetPersons.filter(personIdAndJobCodeAndltOrEqEndDate);
        LOG.debug("budget persons list size after filtering persons list with oprator" + personIdAndJobCodeAndltOrEqEndDate
                + " is " + filteredPersons.size());

        LesserThan ltStartDate = new LesserThan("effectiveDate", this.startDate);
        Equals eStartDate = new Equals("effectiveDate", this.startDate);
        Or ltOrEqStartDate = new Or(ltStartDate, eStartDate);
        QueryList<BudgetPerson> tmpFltdPersons = filteredPersons.filter(ltOrEqStartDate);
        List<BudgetPerson> removeList = new ArrayList<BudgetPerson>();
        for (BudgetPerson bgPerson : tmpFltdPersons) {
            if (bgPerson.getEffectiveDate().compareTo(budgetPerson.getEffectiveDate()) == 0
                    && bgPerson.getPersonSequenceNumber().intValue() != budgetPerson.getPersonSequenceNumber().intValue()) {
                removeList.add(bgPerson);
            }
        }
        tmpFltdPersons.removeAll(removeList);
        boolean noCalcBase = false;
        if (tmpFltdPersons.isEmpty()) {
            noCalcBase = true;
        }
        LOG.debug("budget persons list size after filtering persons list after removing " + ltOrEqStartDate + " is "
                + filteredPersons.size());
        if (!tmpFltdPersons.isEmpty()) {
            filteredPersons.clear();
            tmpFltdPersons.sort("effectiveDate", false);
            filteredPersons.add(tmpFltdPersons.get(0));
            LOG.debug("actual filtered persons list size is" + filteredPersons.size());
        }
        if (noCalcBase) {
            StringBuffer warningMsg = new StringBuffer("Base salary information is not available for the person ");
            StringBuffer errMsg = new StringBuffer("Error finding the calculation base for the person ");
            errMsg.append(this.personnelLineItem.getPersonId());
            errMsg.append(" with Job Code ");
            errMsg.append(this.personnelLineItem.getJobCode());

            warningMsg.append(this.personnelLineItem.getPersonId());
            warningMsg.append(" with Job Code ");
            warningMsg.append(this.personnelLineItem.getJobCode());
            warningMsg.append(" for the period ");
            warningMsg.append(dateTimeService.toDateString(startDate));
            warningMsg.append(" to ");
            if (!filteredPersons.isEmpty()) {
                warningMsg.append(dateTimeService.toDateString(add(filteredPersons.get(0).getEffectiveDate(), -1)));
            }
            else {
                warningMsg.append(dateTimeService.toDateString(personnelLineItem.getEndDate()));
            }
            warningMsg.append("\n");
            warningMsg.append("Salary for this period will be set to 0\n");
            warningMsg.append("Please make changes to budget person details and recalculate the budget");
            warningList.add(warningMsg.toString());
            errorList.add(errMsg.toString());
        }
        return filteredPersons;
    }

    /**
     * Subtract no of days from the given date
     */
    private Date add(Date date, int days) {
        Calendar cal = dateTimeService.getCalendar(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 
     * This method is for calculating the salary for a personnel line item
     */
    public void calculate() {
        Boundary boundary = new Boundary(personnelLineItem.getStartDate(), personnelLineItem.getEndDate());
        calculate(boundary);
        personnelLineItem.setSalaryRequested(boundary.getApplicableCost());
        personnelLineItem.setCostSharingAmount(boundary.getApplicableCostSharing());
        personnelLineItem.setCostSharingPercent(calculateCostSharingPercentage());
    }

    private ScaleTwoDecimal calculateCostSharingPercentage() {
        ScaleTwoDecimal charged = personnelLineItem.getPercentCharged();
        ScaleTwoDecimal effort = personnelLineItem.getPercentEffort();
        return effort.subtract(charged);
    }


    /**
     * 
     * This method is for calculating the salary for a personnel line item within a boundary. This is used mainly for calculating
     * the breakup interval.
     * 
     * @param boundary
     */
    final void calculate(Boundary boundary) {
        this.startDate = boundary.getStartDate();
        this.endDate = boundary.getEndDate();
        ScaleTwoDecimal totalSalary = ScaleTwoDecimal.ZERO;
        List<SalaryDetails> brkupSalaryDetails = createSalBreakupIntervals();
        for (SalaryDetails salaryDetails : brkupSalaryDetails) {
            this.startDate = salaryDetails.getBoundary().getStartDate();
            this.endDate = salaryDetails.getBoundary().getEndDate();
            totalSalary = totalSalary.add(salaryDetails.calculateSalary());
        }
        ScaleTwoDecimal charged = personnelLineItem.getPercentCharged();
        ScaleTwoDecimal costSharing = totalSalary.percentage(calculateCostSharingPercentage());
        ScaleTwoDecimal requestedSalary = totalSalary.percentage(charged);
        boundary.setApplicableCost(requestedSalary);
        boundary.setApplicableCostSharing(costSharing);
    }


    /**
     * -Uses sorted Budget Persons list and Inflation rates list to create salary breakup periods, each period consisting of a
     * SalaryDetails -Call calculate method of each bean to calculate salary
     * 
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private QueryList<SalaryDetails> createSalBreakupIntervals() {
        QueryList combinedList = new QueryList();
        combinedList.addAll(filterBudgetPersons());
        combinedList.addAll(filterInflationRates());
        combinedList.sort("sortableDate");
        if (isAnniversarySalaryDateEnabled()) {
            combinedList = processAnniversarySalaryDateInflationRates(combinedList);
        }
        QueryList<SalaryDetails> breakUpIntervals = new QueryList<SalaryDetails>();
        BudgetPerson budgetPerson = null;
        BudgetRate budgetRate = null;
        BudgetRate prevBudgetProposalRate = null;
        Date tempStartDate = startDate;
        Date tempEndDate = endDate;
        Date rateChangeDate = null;
        SalaryDetails salaryDetails;
        SalaryDetails prevSalaryDetails = new SalaryDetails();
        for (int index = 0; index < combinedList.size(); index++) {
            Object changedObject = combinedList.get(index);
            boolean personFlag = changedObject instanceof BudgetPerson;
            if (personFlag) {
                budgetPerson = (BudgetPerson) changedObject;
                rateChangeDate = budgetPerson.getStartDate();
                prevSalaryDetails.setActualBaseSalary(budgetPerson.getCalculationBase());
                if (budgetPerson.getAppointmentType() == null) {
                    budgetPerson.refreshReferenceObject("appointmentType");
                }
                prevSalaryDetails.setWorkingMonths(budgetPerson.getAppointmentType().getDuration());

            }
            else {
                budgetRate = (BudgetRate) changedObject;
                rateChangeDate = budgetRate.getStartDate();
            }
            if (budgetPerson == null){
                continue;
            }
            int compareDateChange = rateChangeDate.compareTo(tempStartDate);
            if (compareDateChange > 0) {
                Calendar rateChangeCal = dateTimeService.getCalendar(rateChangeDate);
                rateChangeCal.add(Calendar.DATE, -1);
                tempEndDate = rateChangeCal.getTime();
                Boundary boundary = new Boundary(tempStartDate, tempEndDate);
                salaryDetails = new SalaryDetails();
                salaryDetails.setBoundary(boundary);
                if (!personFlag && budgetRate != null) {
                    salaryDetails.setActualBaseSalary(getPrevSalaryBase(budgetPerson, boundary));
                    if (prevBudgetProposalRate != null
                            && budgetPerson.getEffectiveDate().before(prevBudgetProposalRate.getStartDate())
                            && budgetPerson.getEffectiveDate().before(boundary.getStartDate())
                            && prevBudgetProposalRate.getStartDate().before(boundary.getEndDate())
                            && (prevBudgetProposalRate.getStartDate().equals(boundary.getStartDate()) || budget.getBudgetPeriods()
                                    .get(0).getEndDate().before(startDate))) {
                        salaryDetails.calculateActualBaseSalary(budgetRate.getApplicableRate());
                    }
                    else {
                        if (budgetRate != null
                                && budgetPerson.getEffectiveDate().before(budgetRate.getStartDate())
                                && budgetPerson.getEffectiveDate().before(startDate)
                                && budgetRate.getStartDate().before(boundary.getEndDate())
                                && (budgetRate.getStartDate().compareTo(startDate) <= 0 || budget.getBudgetPeriods().get(0)
                                        .getEndDate().before(startDate))) {
                            salaryDetails.calculateActualBaseSalary(budgetRate.getApplicableRate());
                        }
                    }
                    salaryDetails.setWorkingMonths(prevSalaryDetails.getWorkingMonths());
                    salaryDetails.setAltBudgetPerson(getSameJobPerson(boundary, budgetPerson));
                }
                if (personFlag && budgetPerson != null) {
                    salaryDetails.setActualBaseSalary(budgetPerson.getCalculationBase());
                    salaryDetails.setWorkingMonths(budgetPerson.getAppointmentType().getDuration());
                    salaryDetails.setAltBudgetPerson(getSameJobPerson(boundary, budgetPerson));
                }
                if (budgetPerson.getStartDate().compareTo(tempStartDate) <= 0) {
                    breakUpIntervals.add(salaryDetails);
                }
                prevBudgetProposalRate = budgetRate;
                prevSalaryDetails = salaryDetails;
                tempStartDate = rateChangeDate;
            }
        }
        salaryDetails = new SalaryDetails();
        Boundary boundary = new Boundary(tempStartDate, endDate);
        salaryDetails = new SalaryDetails();
        salaryDetails.setBoundary(boundary);
        if (budgetRate != null && budgetPerson != null && budgetPerson.getEffectiveDate().before(budgetRate.getStartDate())) {
            salaryDetails.calculateActualBaseSalary(budgetRate.getApplicableRate());
            salaryDetails.setWorkingMonths(prevSalaryDetails.getWorkingMonths());
        }
        if (budgetPerson != null) {
            salaryDetails.setActualBaseSalary(getPrevSalaryBase(budgetPerson, boundary));
            populateAppointmentType(budgetPerson);
            BudgetPerson newBudgetPerson = getBudgetPersonApplied(budgetPerson, boundary);
            if (budgetRate != null
                    && ((newBudgetPerson == null && budgetPerson.getEffectiveDate().before(budgetRate.getStartDate())) 
                            || (newBudgetPerson != null && newBudgetPerson
                            .getEffectiveDate().before(budgetRate.getStartDate())))) {
                salaryDetails.calculateActualBaseSalary(budgetRate.getApplicableRate());
            }

            if (newBudgetPerson != null) {
                newBudgetPerson.refreshReferenceObject("appointmentType");
                salaryDetails.setWorkingMonths(newBudgetPerson.getAppointmentType() == null ? DEFAULT_WORKING_MONTHS
                        : newBudgetPerson.getAppointmentType().getDuration());
            }
            else {
                salaryDetails.setWorkingMonths(budgetPerson.getAppointmentType() == null ? DEFAULT_WORKING_MONTHS : budgetPerson
                        .getAppointmentType().getDuration());
            }
            salaryDetails.setAltBudgetPerson(getSameJobPerson(boundary, budgetPerson));
        }
        breakUpIntervals.add(salaryDetails);
        return breakUpIntervals;

    }

    private QueryList<DateSortable> processAnniversarySalaryDateInflationRates(QueryList<DateSortable> combinedList) {
        QueryList<DateSortable> filteredCombinedList = new QueryList<DateSortable>();
        for (DateSortable dateSortable : combinedList) {
            if (dateSortable instanceof BudgetPerson) {
                BudgetPerson budgetPerson = (BudgetPerson) dateSortable;
                if (budgetPerson.getSalaryAnniversaryDate() == null) {
                    filteredCombinedList = combinedList;
                }
                else {
                    filteredCombinedList.add(dateSortable);
                    filteredCombinedList.addAll(createAnnualInflationRates(budgetPerson));
                }
            }
        }
        return filteredCombinedList;
    }

    private List<BudgetRate> createAnnualInflationRates(BudgetPerson budgetPerson) {
        List<BudgetRate> budgetRates = new ArrayList<BudgetRate>();
        List<BudgetRate> inflationRatesList = filterInflationRates(budgetPerson.getEffectiveDate(), this.endDate);
        if (inflationRatesList.isEmpty()) {
            return budgetRates;
        }
        BudgetRate inflationRate = getInflationRateToBeApplied(inflationRatesList, budgetPerson.getSalaryAnniversaryDate());
        BudgetRate budgetRate = null;
        if (inflationRate != null) {
            budgetRate = (BudgetRate) ObjectUtils.deepCopy(inflationRate);
            if (!budgetPerson.getSalaryAnniversaryDate().before(budgetPerson.getEffectiveDate())) {
                budgetRate.setStartDate(budgetPerson.getSalaryAnniversaryDate());
            }
            budgetRates.add(budgetRate);
        }
        Calendar salaryDateCalendar = dateTimeService.getCalendar(budgetPerson.getSalaryAnniversaryDate());
        int startYear = salaryDateCalendar.get(Calendar.YEAR);
        Calendar endCalendar = dateTimeService.getCalendar(endDate);
        int endYear = endCalendar.get(Calendar.YEAR);
        if (startYear != endYear) {
            while (salaryDateCalendar.get(Calendar.YEAR) <= endYear) {
                salaryDateCalendar.add(Calendar.YEAR, 1);
                Date nextInflationDate = salaryDateCalendar.getTime();
                if (nextInflationDate.after(endDate)) {
                    break;
                }
                BudgetRate inflationRateToBeApplied = getInflationRateToBeApplied(inflationRatesList, nextInflationDate);
                if (inflationRateToBeApplied != null) {
                    BudgetRate nextBudgetRate = (BudgetRate) ObjectUtils.deepCopy(inflationRateToBeApplied);
                    try {
                        nextBudgetRate.setStartDate(dateTimeService.convertToSqlDate(dateTimeService
                                .toDateString(nextInflationDate)));
                        budgetRates.add(nextBudgetRate);
                    }
                    catch (ParseException e) {
                        LOG.error(e.getMessage(), e);
                    }
                }
            }
        }
        return budgetRates;
    }

    private BudgetRate getInflationRateToBeApplied(List<BudgetRate> inflationRatesList, Date effectiveDate) {
        Equals eqEffectiveDate = new Equals("startDate", effectiveDate);
        LesserThan ltEffectiveDate = new LesserThan("startDate", effectiveDate);
        Or eqOrltEffectiveDate = new Or(eqEffectiveDate, ltEffectiveDate);
        QueryList<BudgetRate> qlInflationRates = new QueryList<BudgetRate>(inflationRatesList);
        QueryList<BudgetRate> qlFilteredRates = qlInflationRates.filter(eqOrltEffectiveDate);
        qlFilteredRates.sort("startDate", false);
        return qlFilteredRates.isEmpty() ? null : qlFilteredRates.get(0);
    }

    private boolean isAnniversarySalaryDateEnabled() {
        return getParameterService().getParameterValueAsString(Budget.class, Constants.ENABLE_SALARY_INFLATION_ANNIV_DATE)
                .equals(STRING_1);
    }

    private ParameterService getParameterService() {
        return KcServiceLocator.getService(ParameterService.class);
    }

    private void populateAppointmentType(BudgetPerson budgetPerson) {
        Map<String, String> qPersonMap = new HashMap<String, String>();
        qPersonMap.put("appointmentTypeCode", budgetPerson.getAppointmentTypeCode());
        AppointmentType appointmentType =  KcServiceLocator.getService(BusinessObjectService.class)
                .findByPrimaryKey(AppointmentType.class, qPersonMap);
        budgetPerson.setAppointmentType(appointmentType);
    }

    private BudgetPerson getSameJobPerson(Boundary boundary, BudgetPerson curBudgetPerson) {
        for (BudgetPerson budgetPerson : budget.getBudgetPersons()) {
            if (((curBudgetPerson.getPersonId() != null && curBudgetPerson.getPersonId().equals(budgetPerson.getPersonId()))
                    || (curBudgetPerson.getRolodexId() != null && curBudgetPerson.getRolodexId()
                            .equals(budgetPerson.getRolodexId())) || (curBudgetPerson.getTbnId() != null && curBudgetPerson
                    .getTbnId().equals(budgetPerson.getTbnId())))
                    && !budgetPerson.getPersonSequenceNumber().equals(curBudgetPerson.getPersonSequenceNumber())
                    && StringUtils.equals(budgetPerson.getJobCode(), curBudgetPerson.getJobCode())
                    && (budgetPerson.getEffectiveDate() != null
                            && budgetPerson.getEffectiveDate().after(curBudgetPerson.getEffectiveDate())
                            && budgetPerson.getEffectiveDate().compareTo(boundary.getStartDate()) >= 0 && budgetPerson
                            .getEffectiveDate().compareTo(boundary.getEndDate()) <= 0)) {
                return budgetPerson;
            }
        }
        return null;
    }
    /**
     * 
     * This inner class is for calculating the salary
     */
    public class SalaryDetails {
        private Boundary boundary;
        private Integer workingMonths;
        private ScaleTwoDecimal actualBaseSalary = ScaleTwoDecimal.ZERO;
        private ScaleTwoDecimal calculatedSalary = ScaleTwoDecimal.ZERO;
        private BudgetPerson altBudgetPerson;
        /**
         * 
         * This method is to calculate salary for a personnel line item
         * @return Calculated Salary
         */
        public ScaleTwoDecimal calculateSalary() {
            int paidMonths = (workingMonths == null) ? 12 : (workingMonths.intValue());
            double perMonthSalary = this.getActualBaseSalary().doubleValue() / paidMonths;
            Calendar startDateCalendar = dateTimeService.getCalendar(startDate);
            int startMonth = startDateCalendar.get(Calendar.MONTH);
            Calendar endDateCalendar = dateTimeService.getCalendar(endDate);
            double totalSalary = 0d;
            boolean salaryReset = false;
            while (startDateCalendar.compareTo(endDateCalendar) <= 0) {
                int noOfDaysInMonth = startDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                int noOfActualDays = 0;
                if (altBudgetPerson != null && !salaryReset) {
                    Calendar effdtCalendar = dateTimeService.getCalendar(altBudgetPerson.getEffectiveDate());
                    Calendar nextStartDateCalendar = dateTimeService.getCalendar(startDateCalendar.getTime());
                    nextStartDateCalendar.add(Calendar.MONTH, 1);
                    nextStartDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
                    if (effdtCalendar.compareTo(startDateCalendar) >= 0 && effdtCalendar.compareTo(nextStartDateCalendar) < 0) {
                        setActualBaseSalary(altBudgetPerson.getCalculationBase());
                        if (effdtCalendar.compareTo(startDateCalendar) > 0) {
                            noOfActualDays = effdtCalendar.get(Calendar.DAY_OF_MONTH)
                                    - startDateCalendar.get(Calendar.DAY_OF_MONTH);
                            totalSalary += (perMonthSalary / noOfDaysInMonth * noOfActualDays);
                            startDateCalendar.set(Calendar.DAY_OF_MONTH, effdtCalendar.get(Calendar.DAY_OF_MONTH));
                        }
                        altBudgetPerson.refreshReferenceObject("appointmentType");
                        paidMonths = (altBudgetPerson.getAppointmentType().getDuration() == null) ? 12 : (altBudgetPerson
                                .getAppointmentType().getDuration().intValue());
                        perMonthSalary = this.getActualBaseSalary().doubleValue() / paidMonths;
                        salaryReset = true;
                    }
                }
                if (startDateCalendar.get(Calendar.MONTH) == endDateCalendar.get(Calendar.MONTH)
                        && startDateCalendar.get(Calendar.YEAR) == endDateCalendar.get(Calendar.YEAR)) {
                    noOfActualDays = endDateCalendar.get(Calendar.DAY_OF_MONTH) - startDateCalendar.get(Calendar.DAY_OF_MONTH) + 1;
                }
                else if (startDateCalendar.get(Calendar.MONTH) == startMonth || startDateCalendar.get(Calendar.DAY_OF_MONTH) != 1) {
                    noOfActualDays = noOfDaysInMonth - startDateCalendar.get(Calendar.DAY_OF_MONTH) + 1;
                }
                else {
                    noOfActualDays = noOfDaysInMonth;
                }
                startDateCalendar.add(Calendar.MONTH, 1);
                startDateCalendar.set(Calendar.DAY_OF_MONTH, 1);
                totalSalary += (perMonthSalary / noOfDaysInMonth * noOfActualDays);
            }
            return calculatedSalary.add(new ScaleTwoDecimal(totalSalary));
        }

        /**
         * Calculate the salary by using base salary and applicable rate
         */
        public void calculateActualBaseSalary(ScaleTwoDecimal applicableRate) {
            ScaleTwoDecimal actualBaseSal = getActualBaseSalary();
            setActualBaseSalary(actualBaseSal.percentage(applicableRate).add(actualBaseSal));
        }

        /**
         * Getter for property boundary.
         * 
         * @return Value of property boundary.
         */
        public Boundary getBoundary() {
            return boundary;
        }

        /**
         * Setter for property boundary.
         * 
         * @param boundary New value of property boundary.
         */
        public void setBoundary(Boundary boundary) {
            this.boundary = boundary;
        }

        /**
         * Getter for property actualBaseSalary.
         * 
         * @return Value of property actualBaseSalary.
         */
        public ScaleTwoDecimal getActualBaseSalary() {
            return actualBaseSalary;
        }

        /**
         * Setter for property actualBaseSalary.
         * 
         * @param actualBaseSalary New value of property actualBaseSalary.
         */
        public void setActualBaseSalary(ScaleTwoDecimal actualBaseSalary) {
            this.actualBaseSalary = actualBaseSalary;
        }

        /**
         * Getter for property calculatedSalary.
         * 
         * @return Value of property calculatedSalary.
         */
        public ScaleTwoDecimal getCalculatedSalary() {
            return calculatedSalary;
        }

        /**
         * Setter for property calculatedSalary.
         * 
         * @param calculatedSalary New value of property calculatedSalary.
         */
        public void setCalculatedSalary(ScaleTwoDecimal calculatedSalary) {
            this.calculatedSalary = calculatedSalary;
        }

        /**
         * Overridden method of toString. It will form a string representation of each element associated with this class.
         * 
         * @return Concatinated string representation of each element
         */
        public String toString() {
            StringBuffer strBffr = new StringBuffer("");
            strBffr.append("Actual Base Salary=>" + actualBaseSalary);
            strBffr.append(";");
            strBffr.append("Duration=>" + workingMonths);
            strBffr.append(";");
            strBffr.append("Boundary=>" + boundary.toString());
            strBffr.append(";");
            strBffr.append("Calculated salary=>" + calculatedSalary);
            strBffr.append("\n");
            return strBffr.toString();
        }

        /**
         * Gets the workingMonths attribute.
         * 
         * @return Returns the workingMonths.
         */
        public Integer getWorkingMonths() {
            return workingMonths;
        }

        /**
         * Sets the workingMonths attribute value.
         * 
         * @param workingMonths The workingMonths to set.
         */
        public void setWorkingMonths(Integer workingMonths) {
            this.workingMonths = workingMonths;
        }

        public BudgetPerson getAltBudgetPerson() {
            return altBudgetPerson;
        }

        public void setAltBudgetPerson(BudgetPerson altBudgetPerson) {
            this.altBudgetPerson = altBudgetPerson;
        }
    }

    public void setInflationRates(QueryList<BudgetRate> inflationRates) {
        this.inflationRates = inflationRates;
    }

    public QueryList<BudgetRate> getInflationRates() {
        return inflationRates;
    }

    private ScaleTwoDecimal getPrevSalaryBase(BudgetPerson budgetPerson, Boundary boundary) {
        Date p1StartDate = budget.getBudgetPeriods().get(0).getStartDate();
        BudgetPerson newBudgetPerson = budgetPerson;
        for (BudgetPerson budgetPerson1 : budget.getBudgetPersons()) {
            if (((budgetPerson1.getPersonId() != null && budgetPerson1.getPersonId().equals(budgetPerson.getPersonId()))
                    || (budgetPerson1.getRolodexId() != null && budgetPerson1.getRolodexId().equals(budgetPerson.getRolodexId())) || (budgetPerson1
                    .getTbnId() != null && budgetPerson1.getTbnId().equals(budgetPerson.getTbnId())))
                    && !budgetPerson1.getPersonSequenceNumber().equals(newBudgetPerson.getPersonSequenceNumber())
                    && budgetPerson1.getJobCode() != null
                    && budgetPerson1.getJobCode().equals(newBudgetPerson.getJobCode())
                    && budgetPerson1.getEffectiveDate().after(newBudgetPerson.getEffectiveDate())
                    && budgetPerson1.getEffectiveDate().compareTo(boundary.getStartDate()) <= 0) {
                newBudgetPerson = budgetPerson1;
            }
        }
        BigDecimal calBase = newBudgetPerson.getCalculationBase().bigDecimalValue();
        if (budgetPerson.getEffectiveDate().before(p1StartDate)) {
            p1StartDate = budgetPerson.getEffectiveDate();
        }
        QueryList<BudgetRate> qlist = filterInflationRates(p1StartDate, startDate);
        for (BudgetRate budgetProposalrate : qlist) {
            if (budgetProposalrate.getStartDate().after(budgetPerson.getEffectiveDate())
                    && budgetProposalrate.getStartDate().before(startDate)) {
                calBase = calBase.add(calBase.multiply(budgetProposalrate.getApplicableRate().bigDecimalValue()).divide(new ScaleTwoDecimal(100.00).bigDecimalValue(), RoundingMode.HALF_UP));
            }
        }
        return new ScaleTwoDecimal(calBase);

    }

    private BudgetPerson getBudgetPersonApplied(BudgetPerson budgetPerson, Boundary boundary) {
        BudgetPerson newBudgetPerson = budgetPerson;
        for (BudgetPerson budgetPerson1 : budget.getBudgetPersons()) {
            if (((budgetPerson1.getPersonId() != null && budgetPerson1.getPersonId().equals(budgetPerson.getPersonId()))
                    || (budgetPerson1.getRolodexId() != null && budgetPerson1.getRolodexId().equals(budgetPerson.getRolodexId())) || (budgetPerson1
                    .getTbnId() != null && budgetPerson1.getTbnId().equals(budgetPerson.getTbnId())))
                    && !budgetPerson1.getPersonSequenceNumber().equals(newBudgetPerson.getPersonSequenceNumber())
                    && budgetPerson1.getJobCode() != null
                    && budgetPerson1.getJobCode().equals(newBudgetPerson.getJobCode())
                    && budgetPerson1.getEffectiveDate().after(newBudgetPerson.getEffectiveDate())
                    && budgetPerson1.getEffectiveDate().compareTo(boundary.getStartDate()) <= 0) {
                return budgetPerson1;
            }
        }
        return null;
    }

    private QueryList<BudgetRate> filterInflationRates(Date sDate, Date eDate) {
        CostElement costElement = personnelLineItem.getCostElementBO();
        if (costElement == null) {
            BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
            Map<String, String> pkMap = new HashMap<String, String>();
            pkMap.put("costElement", personnelLineItem.getCostElement());
            costElement = (CostElement) businessObjectService.findByPrimaryKey(CostElement.class, pkMap);
        }
        List<ValidCeRateType> costElementRates = costElement.getValidCeRateTypes();
        if (costElementRates == null || costElementRates.isEmpty()) {
            costElement.refreshReferenceObject("validCeRateTypes");
            costElementRates = costElement.getValidCeRateTypes();
        }
        ValidCeRateType inflationRateType = null;
        if (costElementRates != null){
            for (ValidCeRateType validCeRateType : costElementRates) {
                if (validCeRateType.getRateClassType().equals(RateClassType.INFLATION.getRateClassType())) {
                    inflationRateType = validCeRateType;
                    LOG.info("Costelement " + costElement + " gets inflation");
                    break;
                }
            }
        }
        Equals eInflationRC = null;
        Equals eInflationRT = null;
        And inflRCandRT = null;

        if (inflationRateType != null) {
            eInflationRC = new Equals("rateClassCode", inflationRateType.getRateClassCode());
            eInflationRT = new Equals("rateTypeCode", inflationRateType.getRateTypeCode());
            inflRCandRT = new And(eInflationRC, eInflationRT);
        }

        LesserThan ltStartDate = new LesserThan("startDate", sDate);
        And ltStartDateAndRate = new And(inflRCandRT, ltStartDate);
        Equals onOffCampus = new Equals("onOffCampusFlag", costElement.getOnOffCampusFlag());
        And ltStartDateAndRateAndOnOffCampusFlag = new And(ltStartDateAndRate, onOffCampus);

        LesserThan ltEndDate = new LesserThan("startDate", eDate);
        Equals eEndDate = new Equals("startDate", eDate);
        Or ltOrEqEndDate = new Or(ltEndDate, eEndDate);

        GreaterThan gtStartDate = new GreaterThan("startDate", sDate);
        Equals eStartDate = new Equals("startDate", sDate);
        Or gtOrEqStartDate = new Or(gtStartDate, eStartDate);

        And gtOrEqStartDateAndltOrEqEndDate = new And(gtOrEqStartDate, ltOrEqEndDate);
        And dateAndRate = new And(inflRCandRT, gtOrEqStartDateAndltOrEqEndDate);

        // Equals onOffCampus = new Equals("onOffCampusFlag", costElement.getOnOffCampusFlag());
        And dateAndRateAndOnOffCampusFlag = new And(dateAndRate, onOffCampus);

        if (personnelLineItem.getApplyInRateFlag()) {
            QueryList<BudgetRate> inflationRatesList;
            QueryList<BudgetRate> prevInflationRatesList;
            if (getInflationRates() == null) {
                inflationRatesList = new QueryList<BudgetRate>(budget.getBudgetRates()).filter(dateAndRateAndOnOffCampusFlag);
                prevInflationRatesList = new QueryList<BudgetRate>(budget.getBudgetRates())
                        .filter(ltStartDateAndRateAndOnOffCampusFlag);
            }
            else {
                inflationRatesList = getInflationRates().filter(dateAndRateAndOnOffCampusFlag);
                prevInflationRatesList = getInflationRates().filter(ltStartDateAndRateAndOnOffCampusFlag);
            }
            if (!prevInflationRatesList.isEmpty()) {
                prevInflationRatesList.sort("startDate", false);
                BudgetRate prevInflationRate = prevInflationRatesList.get(0);
                inflationRatesList.add(prevInflationRate);
                inflationRatesList.sort("startDate");
            }
            return inflationRatesList;
        }
        else {
            return new QueryList<BudgetRate>();
        }
    }
}
