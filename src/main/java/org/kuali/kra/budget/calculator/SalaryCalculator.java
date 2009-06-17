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
package org.kuali.kra.budget.calculator;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static org.kuali.kra.budget.BudgetDecimal.ZERO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.AppointmentType;
import org.kuali.kra.budget.bo.BudgetPeriod;
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
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;

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
        errorList = new ArrayList<String>();
        warningList = new ArrayList<String>();
    }
    
    protected void init() {
        this.dateTimeService = KraServiceLocator.getService(DateTimeService.class);
    }


    /**
     * 
     * This method for filtering the inflation rates with respect to start date and end date
     * @return list of inflation rates
     */
    private QueryList<BudgetProposalRate> filterInflationRates() {
        CostElement costElement = personnelLineItem.getCostElementBO();
        if(costElement==null){
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
            Map<String,String> pkMap = new HashMap<String,String>();
            pkMap.put("costElement", personnelLineItem.getCostElement());
            costElement = (CostElement)businessObjectService.findByPrimaryKey(CostElement.class, pkMap);
        }
        List<ValidCeRateType> costElementRates = costElement.getValidCeRateTypes();
        if (costElementRates==null || costElementRates.isEmpty()) {
            costElement.refreshReferenceObject("validCeRateTypes");
            costElementRates = costElement.getValidCeRateTypes();
        }
        ValidCeRateType inflationRateType = null;
        if(costElementRates!=null)
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
        
        Equals onOffCampus = new Equals("onOffCampusFlag", costElement.getOnOffCampusFlag());
        And dateAndRateAndOnOffCampusFlag = new And(dateAndRate, onOffCampus);

        if (personnelLineItem.getApplyInRateFlag()) {
            return getInflationRates()==null?new QueryList<BudgetProposalRate>(budgetDocument.getBudgetProposalRates()).filter(dateAndRateAndOnOffCampusFlag):getInflationRates().filter(dateAndRateAndOnOffCampusFlag);
        } else {
            return new QueryList<BudgetProposalRate>();
        }

    }

    /**
     * 
     * This method filter the budget persons with respect to start date and end date
     * 
     * @return list of BudgetPerson
     */
    private QueryList<BudgetPerson> filterBudgetPersons() {
        // TODO : this may have issues for tbn person.  Whose perid=null, and some of them has same jobcd
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
        And personIdAndJobCodeAndltOrEqEndDateAndPersonSeqNumber = new And(personIdAndRoldexIdAndJobCode, ePersonSeqNumber);

//      And personIdAndJobCodeAndltOrEqEndDate = new And(personIdAndJobCode, ltOrEqEndDate);
        //filteredPersons = budgetPersons.filter(personIdAndJobCodeAndltOrEqEndDateAndPersonSeqNumber);
        //LOG.info("budget persons list size after filtering persons list with oprator" + personIdAndJobCodeAndltOrEqEndDateAndPersonSeqNumber + " is "
        //        + filteredPersons.size());
        filteredPersons = budgetPersons.filter(personIdAndJobCodeAndltOrEqEndDate);
        LOG.info("budget persons list size after filtering persons list with oprator" + personIdAndJobCodeAndltOrEqEndDate + " is "
              + filteredPersons.size());

        LesserThan ltStartDate = new LesserThan("effectiveDate", this.startDate);
        Equals eStartDate = new Equals("effectiveDate", this.startDate);
        Or ltOrEqStartDate = new Or(ltStartDate, eStartDate);
        QueryList<BudgetPerson> tmpFltdPersons = filteredPersons.filter(ltOrEqStartDate);
        List <BudgetPerson> removeList = new ArrayList<BudgetPerson>();
        for (BudgetPerson bgPerson : tmpFltdPersons) {
            if (bgPerson.getEffectiveDate().compareTo(budgetPerson.getEffectiveDate()) == 0 && bgPerson.getPersonSequenceNumber().intValue() != budgetPerson.getPersonSequenceNumber().intValue()) {
                removeList.add(bgPerson);
            }
        }
        tmpFltdPersons.removeAll(removeList);
        boolean noCalcBase = false;
        if (tmpFltdPersons.isEmpty()) {
            noCalcBase = true;
        }
        //filteredPersons.removeAll(tmpFltdPersons);
        LOG.info("budget persons list size after filtering persons list after removing " + ltOrEqStartDate + " is "
                + filteredPersons.size());
        if(!tmpFltdPersons.isEmpty()){
            filteredPersons.clear();
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
        Boundary boundary = new Boundary(personnelLineItem.getStartDate(),personnelLineItem.getEndDate());
        calculate(boundary);
        personnelLineItem.setSalaryRequested(boundary.getApplicableCost());
        personnelLineItem.setCostSharingAmount(boundary.getApplicableCostSharing());
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
            this.startDate = salaryDetails.getBoundary().getStartDate();
            this.endDate = salaryDetails.getBoundary().getEndDate();
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
        BudgetProposalRate prevBudgetProposalRate = null;
        Date tempStartDate = startDate;
        Date tempEndDate = endDate;
        Date rateChangeDate=null;
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
                prevSalaryDetails.setActualBaseSalary(budgetPerson.getCalculationBase());
                if (budgetPerson.getAppointmentType() == null) {
                    budgetPerson.refreshReferenceObject("appointmentType");
                }
                prevSalaryDetails.setWorkingMonths(budgetPerson.getAppointmentType().getDuration());

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
                    // may have problem here, prevSalaryDetails may not be set yet.
                    // before apply the inflation applicable rate
                    salaryDetails.setActualBaseSalary(getPrevSalaryBase(budgetPerson, boundary));
                    //salaryDetails.calculateActualBaseSalary(budgetProposalRate.getApplicableRate());
                    if(prevBudgetProposalRate!=null && budgetPerson.getEffectiveDate().before(prevBudgetProposalRate.getStartDate()) && budgetPerson.getEffectiveDate().before(boundary.getStartDate()) && prevBudgetProposalRate.getStartDate().before(boundary.getEndDate()) && (prevBudgetProposalRate.getStartDate().equals(boundary.getStartDate()) || budgetDocument.getBudgetPeriods().get(0).getEndDate().before(startDate) )){
                        salaryDetails.calculateActualBaseSalary(budgetProposalRate.getApplicableRate());
                    } else {
                        if(budgetProposalRate!=null && budgetPerson.getEffectiveDate().before(budgetProposalRate.getStartDate()) && budgetPerson.getEffectiveDate().before(startDate) && budgetProposalRate.getStartDate().before(boundary.getEndDate()) && (budgetProposalRate.getStartDate().compareTo(startDate) <= 0 || budgetDocument.getBudgetPeriods().get(0).getEndDate().before(startDate) )){
                            salaryDetails.calculateActualBaseSalary(budgetProposalRate.getApplicableRate());
                        }
                    }
                    salaryDetails.setWorkingMonths(prevSalaryDetails.getWorkingMonths());
                    salaryDetails.setAltBudgetPerson(getSameJobPerson(boundary, budgetPerson));
                }
                if(personFlag && budgetPerson!=null){
                    salaryDetails.setActualBaseSalary(budgetPerson.getCalculationBase());
                    salaryDetails.setWorkingMonths(budgetPerson.getAppointmentType().getDuration());
                    salaryDetails.setAltBudgetPerson(getSameJobPerson(boundary, budgetPerson));
                }
                if (budgetPerson.getStartDate().compareTo(tempStartDate) <= 0) {
                    breakUpIntervals.add(salaryDetails);
                }    
                prevBudgetProposalRate=budgetProposalRate;
                prevSalaryDetails = salaryDetails;
                tempStartDate = rateChangeDate;
            }
        }
        salaryDetails = new SalaryDetails();
        Boundary boundary = new Boundary(tempStartDate, endDate);
        salaryDetails = new SalaryDetails();
        salaryDetails.setBoundary(boundary);
        if(budgetProposalRate!=null && budgetPerson.getEffectiveDate().before(budgetProposalRate.getStartDate())){
            salaryDetails.calculateActualBaseSalary(budgetProposalRate.getApplicableRate());
            salaryDetails.setWorkingMonths(prevSalaryDetails.getWorkingMonths());
        }
        if(budgetPerson!=null){
            //salaryDetails.setActualBaseSalary(budgetPerson.getCalculationBase());
            salaryDetails.setActualBaseSalary(getPrevSalaryBase(budgetPerson, boundary));
            populateAppointmentType(budgetPerson);
            BudgetPerson newBudgetPerson = getBudgetPersonApplied(budgetPerson, boundary);
            if(budgetProposalRate!=null && ((newBudgetPerson == null && budgetPerson.getEffectiveDate().before(budgetProposalRate.getStartDate())) || (newBudgetPerson != null && newBudgetPerson.getEffectiveDate().before(budgetProposalRate.getStartDate()) )) ){
                salaryDetails.calculateActualBaseSalary(budgetProposalRate.getApplicableRate());
            }

            if (newBudgetPerson != null) {
                newBudgetPerson.refreshReferenceObject("appointmentType");
                salaryDetails.setWorkingMonths(newBudgetPerson.getAppointmentType()==null?
                        DEFAULT_WORKING_MONTHS:
                            newBudgetPerson.getAppointmentType().getDuration());
        
            } else {
                salaryDetails.setWorkingMonths(budgetPerson.getAppointmentType()==null?
                        DEFAULT_WORKING_MONTHS:
                        budgetPerson.getAppointmentType().getDuration());
            }
            salaryDetails.setAltBudgetPerson(getSameJobPerson(boundary, budgetPerson));
        }
        breakUpIntervals.add(salaryDetails);
        return breakUpIntervals;

    } // end createSalBreakupIntervals

    private void populateAppointmentType(BudgetPerson budgetPerson) {
        // JIRA-1467 : reset apointment type need to check too
       // if(budgetPerson.getAppointmentType()!=null){
            Map<String,String> qPersonMap = new HashMap<String,String>();
            qPersonMap.put("appointmentTypeCode", budgetPerson.getAppointmentTypeCode());
            AppointmentType appointmentType = (AppointmentType)KraServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(AppointmentType.class, 
                        qPersonMap);
            budgetPerson.setAppointmentType(appointmentType);
       // }
    }

    private BudgetPerson getSameJobPerson(Boundary boundary, BudgetPerson curBudgetPerson) {
        // may need a list because potential several promotions in a short period of time
        // same job/person within the boundary.
        for (BudgetPerson budgetPerson : budgetDocument.getBudgetPersons()) {
            if (((curBudgetPerson.getPersonId() != null && curBudgetPerson.getPersonId().equals(budgetPerson.getPersonId())) || (curBudgetPerson.getRolodexId() != null && curBudgetPerson.getRolodexId().equals(budgetPerson.getRolodexId())) || (curBudgetPerson.getTbnId() != null && curBudgetPerson.getTbnId().equals(budgetPerson.getTbnId())) )
                 && !budgetPerson.getPersonSequenceNumber().equals(curBudgetPerson.getPersonSequenceNumber()) && budgetPerson.getJobCode().equals(curBudgetPerson.getJobCode()) && budgetPerson.getEffectiveDate().after(curBudgetPerson.getEffectiveDate()) && budgetPerson.getEffectiveDate().compareTo(boundary.getStartDate()) >= 0 && budgetPerson.getEffectiveDate().compareTo(boundary.getEndDate()) <= 0) {
                return budgetPerson;
            }
        }
        return null;
    }
    
    public class SalaryDetails {
        private Boundary boundary;
        private Integer workingMonths;
        private BudgetDecimal actualBaseSalary = ZERO;
        private BudgetDecimal calculatedSalary = ZERO;
        private BudgetPerson altBudgetPerson;

        /*
         * Does calculate the calculatedSalary based on the Boundary, Appointment Type & Base Salary.
         * 
         */
        public BudgetDecimal calculateSalary() {

            int paidMonths = (workingMonths == null) ? 12 : (workingMonths.intValue());
            double perMonthSalary = this.getActualBaseSalary().doubleValue()/paidMonths;
//            BudgetDecimal perMonthSalary = this.getActualBaseSalary().divide(new BudgetDecimal(paidMonths));
            Calendar startDateCalendar = dateTimeService.getCalendar(startDate);
            int startMonth = startDateCalendar.get(MONTH);
            Calendar endDateCalendar = dateTimeService.getCalendar(endDate);
            int endMonth = endDateCalendar.get(MONTH);
            double totalSalary = 0d;
            boolean salaryReset = false;
            while(startDateCalendar.compareTo(endDateCalendar) <= 0){
                int noOfDaysInMonth = startDateCalendar.getActualMaximum(DAY_OF_MONTH);
                int noOfActualDays = 0;
                // if person get promotion in the middle of calculation
                if (altBudgetPerson != null && !salaryReset) {
                    Calendar effdtCalendar = dateTimeService.getCalendar(altBudgetPerson.getEffectiveDate());
                    Calendar nextStartDateCalendar = dateTimeService.getCalendar(startDateCalendar.getTime());
                    nextStartDateCalendar.add(MONTH, 1);
                    nextStartDateCalendar.set(DAY_OF_MONTH, 1);
                    if (effdtCalendar.compareTo(startDateCalendar) >= 0 && effdtCalendar.compareTo(nextStartDateCalendar) < 0) {
                        setActualBaseSalary(altBudgetPerson.getCalculationBase());
                        if (effdtCalendar.compareTo(startDateCalendar) > 0) {
                            noOfActualDays = effdtCalendar.get(DAY_OF_MONTH)-startDateCalendar.get(DAY_OF_MONTH);
                            totalSalary+=(perMonthSalary/noOfDaysInMonth*noOfActualDays);
                            startDateCalendar.set(DAY_OF_MONTH, effdtCalendar.get(DAY_OF_MONTH));
                        }
                        altBudgetPerson.refreshReferenceObject("appointmentType");
                        paidMonths = (altBudgetPerson.getAppointmentType().getDuration() == null) ? 12 : (altBudgetPerson.getAppointmentType().getDuration().intValue());
                        perMonthSalary = this.getActualBaseSalary().doubleValue()/paidMonths;     
                        salaryReset = true;
                    }
                }
                if (startDateCalendar.get(MONTH) == endDateCalendar.get(MONTH) && startDateCalendar.get(Calendar.YEAR) == endDateCalendar.get(Calendar.YEAR)) {
                    noOfActualDays = endDateCalendar.get(DAY_OF_MONTH)-startDateCalendar.get(DAY_OF_MONTH)+1;
                } else if(startDateCalendar.get(MONTH)==startMonth || startDateCalendar.get(DAY_OF_MONTH) != 1){
                    noOfActualDays = noOfDaysInMonth-startDateCalendar.get(DAY_OF_MONTH)+1;
//                }else if(startDateCalendar.get(MONTH)==endMonth){
//                    noOfActualDays = endDateCalendar.get(DAY_OF_MONTH);
                }else{
                    noOfActualDays = noOfDaysInMonth;
                }
                startDateCalendar.add(MONTH, 1);
                startDateCalendar.set(DAY_OF_MONTH, 1);
                totalSalary+=(perMonthSalary/noOfDaysInMonth*noOfActualDays);
//                calculatedSalary = calculatedSalary.add(perMonthSalary.divide(
//                                new BudgetDecimal(noOfDaysInMonth)).multiply(new BudgetDecimal(noOfActualDays)));
            }
            return calculatedSalary.add(new BudgetDecimal(totalSalary));
        }

        /**
         *  Calculate the salary by using base salary and applicable rate
         */
        public void calculateActualBaseSalary(BudgetDecimal applicableRate) {
            BudgetDecimal actualBaseSal = getActualBaseSalary();
            setActualBaseSalary(actualBaseSal.percentage(applicableRate).add(actualBaseSal));
            // only calculate the applicable rate portion
            // the salarybase is calculated in budgetperson's salarydetails ?
            //setActualBaseSalary(actualBaseSal.percentage(applicableRate));
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

        public BudgetPerson getAltBudgetPerson() {
            return altBudgetPerson;
        }

        public void setAltBudgetPerson(BudgetPerson altBudgetPerson) {
            this.altBudgetPerson = altBudgetPerson;
        }

        // end calculateSalary

    } // end SalaryCalculationBean

    public void setInflationRates(QueryList<BudgetProposalRate> inflationRates) {
        this.inflationRates = inflationRates;
    }
    public QueryList<BudgetProposalRate> getInflationRates() {
        return inflationRates;
    }
    
    private BudgetDecimal getPrevSalaryBase(BudgetPerson budgetPerson, Boundary boundary) {
        //int daysToEndDate = KraServiceLocator.getService(DateTimeService.class).dateDiff(newStartDate, parentEndDate, false);
        Date p1StartDate = budgetDocument.getBudgetPeriods().get(0).getStartDate();
        BudgetPerson newBudgetPerson = budgetPerson;
        // in case same person job get promotion
        // 01/01/2008 - 12/31/2008, promoted at 02/01/2008
        // for boundary 07/01/2008-12/31/2008, it has to use 03/01 record
        for (BudgetPerson budgetPerson1 : budgetDocument.getBudgetPersons()) {
            if (((budgetPerson1.getPersonId() != null && budgetPerson1.getPersonId().equals(budgetPerson.getPersonId())) || (budgetPerson1.getRolodexId() != null && budgetPerson1.getRolodexId().equals(budgetPerson.getRolodexId())) || (budgetPerson1.getTbnId() != null && budgetPerson1.getTbnId().equals(budgetPerson.getTbnId())) )
                  &&  !budgetPerson1.getPersonSequenceNumber().equals(newBudgetPerson.getPersonSequenceNumber()) && budgetPerson1.getJobCode() != null && budgetPerson1.getJobCode().equals(newBudgetPerson.getJobCode()) && budgetPerson1.getEffectiveDate().after(newBudgetPerson.getEffectiveDate()) && budgetPerson1.getEffectiveDate().compareTo(boundary.getStartDate()) <= 0) {
                newBudgetPerson =  budgetPerson1;
            }
        }

        
        BudgetDecimal calBase = newBudgetPerson.getCalculationBase();
        /* 
         * Start with the period that the effective date is in
         * check the budget period before the current period and that has inflation rate
         * increase the calculation base by that base
         * 
         */ 
        for (BudgetPeriod budgetPeriod : budgetDocument.getBudgetPeriods()) {
            if (budgetPeriod.getEndDate().compareTo(newBudgetPerson.getEffectiveDate()) >= 0) {
                QueryList<BudgetProposalRate> qlist = filterInflationRates(budgetPeriod.getStartDate(), budgetPeriod.getEndDate());
                for (BudgetProposalRate budgetProposalrate : qlist) {
                    if (!budgetProposalrate.getStartDate().equals(p1StartDate) && budgetProposalrate.getStartDate().after(newBudgetPerson.getEffectiveDate())) {
                        if (budgetPeriod.getEndDate().before(startDate) || budgetProposalrate.getStartDate().before(boundary.getStartDate())) {
                            calBase = calBase.add(calBase.multiply(budgetProposalrate.getApplicableRate()).divide(new BudgetDecimal(100.00)));
                        }
                    }
                }
            }
        }
        return calBase;
        
    }
        
    private BudgetPerson getBudgetPersonApplied(BudgetPerson budgetPerson, Boundary boundary) {
        BudgetPerson newBudgetPerson = budgetPerson;
        // in case same person job get promotion
        // 01/01/2008 - 12/31/2008, promoted at 02/01/2008
        // for boundary 07/01/2008-12/31/2008, it has to use 03/01 record
        for (BudgetPerson budgetPerson1 : budgetDocument.getBudgetPersons()) {
            if (((budgetPerson1.getPersonId() != null && budgetPerson1.getPersonId().equals(budgetPerson.getPersonId())) || (budgetPerson1.getRolodexId() != null && budgetPerson1.getRolodexId().equals(budgetPerson.getRolodexId())) || (budgetPerson1.getTbnId() != null && budgetPerson1.getTbnId().equals(budgetPerson.getTbnId())) )
                  &&  !budgetPerson1.getPersonSequenceNumber().equals(newBudgetPerson.getPersonSequenceNumber()) && budgetPerson1.getJobCode() != null && budgetPerson1.getJobCode().equals(newBudgetPerson.getJobCode()) && budgetPerson1.getEffectiveDate().after(newBudgetPerson.getEffectiveDate()) && budgetPerson1.getEffectiveDate().compareTo(boundary.getStartDate()) <= 0) {
                return budgetPerson1;
            }
        }

        return null;
    }
    
    private QueryList<BudgetProposalRate> filterInflationRates(Date sDate, Date eDate) {
        CostElement costElement = personnelLineItem.getCostElementBO();
        if(costElement==null){
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
            Map<String,String> pkMap = new HashMap<String,String>();
            pkMap.put("costElement", personnelLineItem.getCostElement());
            costElement = (CostElement)businessObjectService.findByPrimaryKey(CostElement.class, pkMap);
        }
        List<ValidCeRateType> costElementRates = costElement.getValidCeRateTypes();
        if (costElementRates==null || costElementRates.isEmpty()) {
            costElement.refreshReferenceObject("validCeRateTypes");
            costElementRates = costElement.getValidCeRateTypes();
        }
        ValidCeRateType inflationRateType = null;
        if(costElementRates!=null)
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

        LesserThan ltEndDate = new LesserThan("startDate", eDate);
        Equals eEndDate = new Equals("startDate", eDate);
        Or ltOrEqEndDate = new Or(ltEndDate, eEndDate);

        GreaterThan gtStartDate = new GreaterThan("startDate", sDate);
        Equals eStartDate = new Equals("startDate", sDate);
        Or gtOrEqStartDate = new Or(gtStartDate, eStartDate);

        And gtOrEqStartDateAndltOrEqEndDate = new And(gtOrEqStartDate, ltOrEqEndDate);
        And dateAndRate = new And(inflRCandRT, gtOrEqStartDateAndltOrEqEndDate);
        
        Equals onOffCampus = new Equals("onOffCampusFlag", costElement.getOnOffCampusFlag());
        And dateAndRateAndOnOffCampusFlag = new And(dateAndRate, onOffCampus);

        //return getInflationRates()==null?new QueryList<BudgetProposalRate>(budgetDocument.getBudgetProposalRates()).filter(dateAndRateAndOnOffCampusFlag):getInflationRates().filter(dateAndRateAndOnOffCampusFlag);
        if (personnelLineItem.getApplyInRateFlag()) {
            return getInflationRates()==null?new QueryList<BudgetProposalRate>(budgetDocument.getBudgetProposalRates()).filter(dateAndRateAndOnOffCampusFlag):getInflationRates().filter(dateAndRateAndOnOffCampusFlag);
        } else {
            return new QueryList<BudgetProposalRate>();
        }

        
    }
    
    
    private Date addDate(Date date, int days) {
        Calendar c1 = Calendar.getInstance(); 
        c1.setTime(new java.util.Date(date.getTime()));
        c1.add(Calendar.DATE,days);
        return new java.sql.Date(c1.getTime().getTime());
        
    }
    
}
