/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.budget.calculator;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static org.kuali.kra.budget.BudgetDecimal.ZERO;
import static org.kuali.rice.KNSServiceLocator.getDateTimeService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DateTimeService;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.AppointmentType;
import org.kuali.kra.budget.bo.BudgetPerson;
import org.kuali.kra.budget.bo.BudgetPersonnelDetails;
import org.kuali.kra.budget.bo.BudgetProposalRate;
import org.kuali.kra.budget.bo.CostElement;
import org.kuali.kra.budget.bo.ValidCeRateType;
import org.kuali.kra.budget.calculator.query.And;
import org.kuali.kra.budget.calculator.query.Equals;
import org.kuali.kra.budget.calculator.query.GreaterThan;
import org.kuali.kra.budget.calculator.query.LesserThan;
import org.kuali.kra.budget.calculator.query.Or;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;

/**
 * This class...
 */
public class SalaryCalculator {
    private static final int DEFAULT_WORKING_MONTHS = 12;
    private BudgetDocument budgetDocument;
    private BudgetPersonnelDetails personnelLineItem;
    private Date startDate;
    private Date endDate;
    private DateTimeService dateTimeService;
    private List<String> errorList;
    private List<String> warningList;
    private QueryList<BudgetProposalRate> inflationRates;
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(SalaryCalculator.class);

    /**
     * 
     * Constructs a SalaryCalculator.java.
     * @param budgetDocument
     * @param personnelLineItem
     */
    public SalaryCalculator(BudgetDocument budgetDocument, BudgetPersonnelDetails personnelLineItem) {
        this.budgetDocument = budgetDocument;
        this.personnelLineItem = personnelLineItem;
        this.startDate = personnelLineItem.getStartDate();
        this.endDate = personnelLineItem.getEndDate();
        this.dateTimeService = getDateTimeService();
        errorList = new ArrayList<String>();
        warningList = new ArrayList<String>();
    }


    /**
     * 
     * This method for filtering the inflation rates with respect to start date and end date
     * @return list of inflation rates
     */
    private QueryList<BudgetProposalRate> filterInflationRates() {
        CostElement costElement = personnelLineItem.getCostElementBO();
        List<ValidCeRateType> costElementRates = costElement.getValidCeRateTypes();
        if (costElementRates.isEmpty()) {
            costElement.refreshReferenceObject("validCeRateTypes");
        }
        ValidCeRateType inflationRateType = null;
        for (ValidCeRateType validCeRateType : costElementRates) {
            if (validCeRateType.getRateClassType().equals(RateClassType.INFLATION.getRateClassType())) {
                inflationRateType = validCeRateType;
                LOG.info("Costelement " + costElement + " gets inflation");
                break;
            }
        }
        Equals eInflationRC = new Equals("rateClassCode", inflationRateType.getRateClassCode());
        Equals eInflationRT = new Equals("rateTypeCode", inflationRateType.getRateTypeCode());
        And inflRCandRT = new And(eInflationRC, eInflationRT);

        LesserThan ltEndDate = new LesserThan("startDate", this.endDate);
        Equals eEndDate = new Equals("startDate", this.endDate);
        Or ltOrEqEndDate = new Or(ltEndDate, eEndDate);

        GreaterThan gtStartDate = new GreaterThan("startDate", this.startDate);
        Equals eStartDate = new Equals("startDate", this.startDate);
        Or gtOrEqStartDate = new Or(gtStartDate, eStartDate);

        And gtOrEqStartDateAndltOrEqEndDate = new And(gtOrEqStartDate, ltOrEqEndDate);
        And dateAndRate = new And(inflRCandRT, gtOrEqStartDateAndltOrEqEndDate);
        
        return getInflationRates()==null?new QueryList<BudgetProposalRate>():getInflationRates().filter(dateAndRate);

    }

    /**
     * 
     * This method filter the budget persons with respect to start date and end date
     * 
     * @return list of BudgetPerson
     */
    private QueryList<BudgetPerson> filterBudgetPersons() {
        QueryList<BudgetPerson> filteredPersons = new QueryList<BudgetPerson>();
        if(budgetDocument.getBudgetPersons().isEmpty()) return filteredPersons;
        QueryList<BudgetPerson> budgetPersons = new QueryList<BudgetPerson>(budgetDocument.getBudgetPersons());
        Equals ePersonSeqNumber = new Equals("personSequenceNumber", personnelLineItem.getPersonSequenceNumber());
        QueryList<BudgetPerson> fltdBudgetPersonList = budgetPersons.filter(ePersonSeqNumber);
        if(fltdBudgetPersonList.isEmpty()) return filteredPersons;
        BudgetPerson budgetPerson = fltdBudgetPersonList.get(0);
        Equals ePersonId = new Equals("personId", budgetPerson.getPersonId());
        Equals eJobCode = new Equals("jobCode", budgetPerson.getJobCode());
        Equals eRolodexId = new Equals("rolodexId",budgetPerson.getRolodexId());
        And personIdAndJobCode = new And(ePersonId, eJobCode);
        And personIdAndRoldexIdAndJobCode = new And(personIdAndJobCode,eRolodexId);
        LesserThan ltEndDate = new LesserThan("effectiveDate", this.endDate);
        Equals eEndDate = new Equals("effectiveDate", this.endDate);
        Or ltOrEqEndDate = new Or(ltEndDate, eEndDate);
        And personIdAndJobCodeAndltOrEqEndDate = new And(personIdAndRoldexIdAndJobCode, ltOrEqEndDate);
//        And personIdAndJobCodeAndltOrEqEndDate = new And(personIdAndJobCode, ltOrEqEndDate);
        filteredPersons = budgetPersons.filter(personIdAndJobCodeAndltOrEqEndDate);
        LOG.info("budget persons list size after filtering persons list with oprator" + personIdAndJobCodeAndltOrEqEndDate + " is "
                + filteredPersons.size());

        LesserThan ltStartDate = new LesserThan("effectiveDate", this.startDate);
        Equals eStartDate = new Equals("effectiveDate", this.startDate);
        Or ltOrEqStartDate = new Or(ltStartDate, eStartDate);
        QueryList<BudgetPerson> tmpFltdPersons = filteredPersons.filter(ltOrEqStartDate);
        boolean noCalcBase = false;
        if (tmpFltdPersons.isEmpty()) {
            noCalcBase = true;
        }
        filteredPersons.removeAll(tmpFltdPersons);
        LOG.info("budget persons list size after filtering persons list after removing " + ltOrEqStartDate + " is "
                + filteredPersons.size());
        if(!tmpFltdPersons.isEmpty()){
            tmpFltdPersons.sort("effectiveDate", false);
    //        baseSalary = tmpFltdPersons.get(0).getCalculationBase();
            filteredPersons.add(tmpFltdPersons.get(0));
            LOG.info("actual filtered persons list size is" + filteredPersons.size());
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
        Boundary boundary = new Boundary(startDate,endDate);
        calculate(boundary);
        personnelLineItem.setSalaryRequested(boundary.getApplicableCost().setScale());
        personnelLineItem.setCostSharingAmount(boundary.getApplicableCostSharing().setScale());
        personnelLineItem.setCostSharingPercent(calculateCostSharingPercentage());
    }
    private BudgetDecimal calculateCostSharingPercentage() {
        BudgetDecimal charged = personnelLineItem.getPercentCharged();
        BudgetDecimal effort = personnelLineItem.getPercentEffort();
        return effort.subtract(charged);
    }


    /**
     * 
     * This method is for calculating the salary for a personnel line item within a boundary.
     * This is used mainly for calculating the breakup interval. 
     * @param boundary
     */
    final void calculate(Boundary boundary) {
        this.startDate = boundary.getStartDate();
        this.endDate = boundary.getEndDate();
        BudgetDecimal totalSalary = ZERO;
        List<SalaryDetails> brkupSalaryDetails = createSalBreakupIntervals();
        for (SalaryDetails salaryDetails : brkupSalaryDetails) {
            totalSalary = totalSalary.add(salaryDetails.calculateSalary());
        }
        BudgetDecimal charged = personnelLineItem.getPercentCharged();
        BudgetDecimal costSharing = totalSalary.percentage(calculateCostSharingPercentage());
        BudgetDecimal requestedSalary = totalSalary.percentage(charged);
        boundary.setApplicableCost(requestedSalary);
        boundary.setApplicableCostSharing(costSharing);
    }
    
    
    /**
     * -Uses sorted Budget Persons list and Inflation rates list to create salary breakup periods, each period consisting of a
     * SalaryDetails 
     * -Call calculate method of each bean to calculate salary
     * 
     */
    private QueryList<SalaryDetails> createSalBreakupIntervals() {
        QueryList combinedList = new QueryList();
        combinedList.addAll(filterBudgetPersons());
        combinedList.addAll(filterInflationRates());
        // initializing the break up intervals
        QueryList<SalaryDetails> breakUpIntervals = new QueryList<SalaryDetails>();
        BudgetPerson budgetPerson = null;
        BudgetProposalRate budgetProposalRate = null;
        Date tempStartDate = startDate;
        Date tempEndDate = endDate;
        Date rateChangeDate;
        /*
         * Creating an instance for storing the base values
         */
        SalaryDetails salaryDetails;
        SalaryDetails prevSalaryDetails = new SalaryDetails();
        for (int index = 0; index < combinedList.size(); index++) {
            Object changedObject = combinedList.get(index);
            boolean personFlag = changedObject instanceof BudgetPerson;
            if(personFlag){
                budgetPerson = (BudgetPerson)changedObject;
                rateChangeDate = budgetPerson.getStartDate();
            }else{
                budgetProposalRate = (BudgetProposalRate)changedObject;
                rateChangeDate = budgetProposalRate.getStartDate();
            }
            if(budgetPerson==null) continue;//need to get the calc base first. So continue till you find a person node from the combined list
            int compareDateChange = rateChangeDate.compareTo(tempStartDate);
            if (compareDateChange > 0) {
//                if(compareDateChange>0){
                    // rate changed so get the previous day date and use it as endDate
                    Calendar rateChangeCal = dateTimeService.getCalendar(rateChangeDate);
                    rateChangeCal.add(Calendar.DATE, -1);
                    tempEndDate = rateChangeCal.getTime();
//                }
                Boundary boundary = new Boundary(tempStartDate, tempEndDate);
                salaryDetails = new SalaryDetails();
                salaryDetails.setBoundary(boundary);
                if(!personFlag && budgetProposalRate!=null){
                    salaryDetails.calculateActualBaseSalary(budgetProposalRate.getApplicableRate());
                    salaryDetails.setWorkingMonths(prevSalaryDetails.getWorkingMonths());
                }
                if(personFlag && budgetPerson!=null){
                    salaryDetails.setActualBaseSalary(budgetPerson.getCalculationBase());
                    salaryDetails.setWorkingMonths(budgetPerson.getAppointmentType().getDuration());
                }
                breakUpIntervals.add(salaryDetails);
                prevSalaryDetails = salaryDetails;
                tempStartDate = rateChangeDate;
            }
        }
        salaryDetails = new SalaryDetails();
        Boundary boundary = new Boundary(tempStartDate, endDate);
        salaryDetails = new SalaryDetails();
        salaryDetails.setBoundary(boundary);
        if(budgetProposalRate!=null){
            salaryDetails.calculateActualBaseSalary(budgetProposalRate.getApplicableRate());
            salaryDetails.setWorkingMonths(prevSalaryDetails.getWorkingMonths());
        }
        if(budgetPerson!=null){
            salaryDetails.setActualBaseSalary(budgetPerson.getCalculationBase());
            populateAppointmentType(budgetPerson);
            salaryDetails.setWorkingMonths(budgetPerson.getAppointmentType()==null?
                        DEFAULT_WORKING_MONTHS:
                        budgetPerson.getAppointmentType().getDuration());
        }
        breakUpIntervals.add(salaryDetails);
        return breakUpIntervals;

    } // end createSalBreakupIntervals

    private void populateAppointmentType(BudgetPerson budgetPerson) {
        if(budgetPerson.getAppointmentType()==null){
            Map<String,String> qPersonMap = new HashMap<String,String>();
            qPersonMap.put("appointmentTypeCode", budgetPerson.getAppointmentTypeCode());
            AppointmentType appointmentType = (AppointmentType)KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(AppointmentType.class, 
                        qPersonMap);
            budgetPerson.setAppointmentType(appointmentType);
        }
    }

    public class SalaryDetails {
        private Boundary boundary;
        private Integer workingMonths;
        private BudgetDecimal actualBaseSalary = ZERO;
        private BudgetDecimal calculatedSalary = ZERO;

        /*
         * Does calculate the calculatedSalary based on the Boundary, Appointment Type & Base Salary.
         * 
         */
        public BudgetDecimal calculateSalary() {

            int paidMonths = (workingMonths == null) ? 12 : (workingMonths.intValue());
            BudgetDecimal perMonthSalary = this.getActualBaseSalary().divide(new BudgetDecimal(paidMonths));
            Calendar startDateCalendar = dateTimeService.getCalendar(startDate);
            int startMonth = startDateCalendar.get(MONTH);
            Calendar endDateCalendar = dateTimeService.getCalendar(endDate);
            int endMonth = endDateCalendar.get(MONTH);
            
            while(startDateCalendar.before(endDateCalendar)){
                int noOfDaysInMonth = startDateCalendar.getActualMaximum(DAY_OF_MONTH);
                int noOfActualDays = 0;
                if(startDateCalendar.get(MONTH)==startMonth){
                    noOfActualDays = noOfDaysInMonth-startDateCalendar.get(DAY_OF_MONTH)+1;
                }else if(startDateCalendar.get(MONTH)==endMonth){
                    noOfActualDays = endDateCalendar.get(DAY_OF_MONTH);
                }else{
                    noOfActualDays = noOfDaysInMonth;
                }
                startDateCalendar.add(MONTH, 1);
                startDateCalendar.set(DAY_OF_MONTH, 1);
                calculatedSalary = calculatedSalary.add(perMonthSalary.divide(
                                new BudgetDecimal(noOfDaysInMonth)).multiply(new BudgetDecimal(noOfActualDays)));
            }
            return calculatedSalary;
        }

        /**
         *  Calculate the salary by using base salary and applicable rate
         */
        public void calculateActualBaseSalary(BudgetDecimal applicableRate) {
            BudgetDecimal actualBaseSal = getActualBaseSalary();
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
        public BudgetDecimal getActualBaseSalary() {
            return actualBaseSalary;
        }

        /**
         * Setter for property actualBaseSalary.
         * 
         * @param actualBaseSalary New value of property actualBaseSalary.
         */
        public void setActualBaseSalary(BudgetDecimal actualBaseSalary) {
            this.actualBaseSalary = actualBaseSalary;
        }

        /**
         * Getter for property calculatedSalary.
         * 
         * @return Value of property calculatedSalary.
         */
        public BudgetDecimal getCalculatedSalary() {
            return calculatedSalary;
        }

        /**
         * Setter for property calculatedSalary.
         * 
         * @param calculatedSalary New value of property calculatedSalary.
         */
        public void setCalculatedSalary(BudgetDecimal calculatedSalary) {
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
         * @return Returns the workingMonths.
         */
        public Integer getWorkingMonths() {
            return workingMonths;
        }

        /**
         * Sets the workingMonths attribute value.
         * @param workingMonths The workingMonths to set.
         */
        public void setWorkingMonths(Integer workingMonths) {
            this.workingMonths = workingMonths;
        }

        // end calculateSalary

    } // end SalaryCalculationBean

    public void setInflationRates(QueryList<BudgetProposalRate> inflationRates) {
        this.inflationRates = inflationRates;
    }
    public QueryList<BudgetProposalRate> getInflationRates() {
        return inflationRates;
    }
}
