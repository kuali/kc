/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.personnel;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.ojb.broker.PBKey;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.kra.award.budget.AwardBudgetPersonnelDetailsExt;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.nonpersonnel.AbstractBudgetCalculatedAmount;
import org.kuali.kra.budget.nonpersonnel.BudgetLineItemBase;
import org.kuali.kra.infrastructure.DeepCopyIgnore;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.springmodules.orm.ojb.OjbFactoryUtils;

public class BudgetPersonnelDetails extends BudgetLineItemBase {

    @DeepCopyIgnore
    private Long budgetPersonnelLineItemId;

    private Integer personNumber;

    private BudgetDecimal costSharingPercent = BudgetDecimal.ZERO;

    private String jobCode;

    private Boolean nonEmployeeFlag;

    private BudgetDecimal percentCharged = BudgetDecimal.ZERO;

    private BudgetDecimal percentEffort = BudgetDecimal.ZERO;

    private String periodTypeCode;

    private String personId;

    private BudgetDecimal salaryRequested = BudgetDecimal.ZERO;

    private Integer sequenceNumber;

    private Integer personSequenceNumber;

    private BudgetPerson budgetPerson;

    private List<BudgetPersonnelCalculatedAmount> budgetPersonnelCalculatedAmounts;

    private List<BudgetPersonnelRateAndBase> budgetPersonnelRateAndBaseList;

    private String effdtAfterStartdtMsg;

    public BudgetPersonnelDetails() {
        budgetPersonnelCalculatedAmounts = new ArrayList<BudgetPersonnelCalculatedAmount>();
        budgetPersonnelRateAndBaseList = new ArrayList<BudgetPersonnelRateAndBase>();
    }

    public Integer getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(Integer personNumber) {
        this.personNumber = personNumber;
    }

    public BudgetDecimal getCostSharingPercent() {
        return costSharingPercent;
    }

    public void setCostSharingPercent(BudgetDecimal costSharingPercent) {
        this.costSharingPercent = costSharingPercent;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public BudgetDecimal getPercentCharged() {
        return BudgetDecimal.returnZeroIfNull(percentCharged);
    }

    public void setPercentCharged(BudgetDecimal percentCharged) {
        this.percentCharged = percentCharged;
    }

    public BudgetDecimal getPercentEffort() {
        return BudgetDecimal.returnZeroIfNull(percentEffort);
    }

    public void setPercentEffort(BudgetDecimal percentEffort) {
        this.percentEffort = percentEffort;
    }

    public String getPeriodTypeCode() {
        return periodTypeCode;
    }

    public void setPeriodTypeCode(String periodTypeCode) {
        this.periodTypeCode = periodTypeCode;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public BudgetDecimal getSalaryRequested() {
        return salaryRequested;
    }

    public void setSalaryRequested(BudgetDecimal salaryRequested) {
        this.salaryRequested = salaryRequested;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets the nonEmployeeFlag attribute. 
     * @return Returns the nonEmployeeFlag.
     */
    public Boolean getNonEmployeeFlag() {
        return getBudgetPerson() == null ? false : getBudgetPerson().getNonEmployeeFlag();
    }

    /**
     * Sets the nonEmployeeFlag attribute value.
     * @param nonEmployeeFlag The nonEmployeeFlag to set.
     */
    public void setNonEmployeeFlag(Boolean nonEmployeeFlag) {
        this.nonEmployeeFlag = nonEmployeeFlag;
    }

    /**
     * Gets the personSequenceNumber attribute. 
     * @return Returns the personSequenceNumber.
     */
    public Integer getPersonSequenceNumber() {
        return personSequenceNumber;
    }

    /**
     * Sets the personSequenceNumber attribute value.
     * @param personSequenceNumber The personSequenceNumber to set.
     */
    public void setPersonSequenceNumber(Integer personSequenceNumber) {
        this.personSequenceNumber = personSequenceNumber;
    }

    /**
     * Gets the budgetPerson attribute. 
     * @return Returns the budgetPerson.
     */
    public BudgetPerson getBudgetPerson() {
        return budgetPerson;
    }

    /**
     * Sets the budgetPerson attribute value.
     * @param budgetPerson The budgetPerson to set.
     */
    public void setBudgetPerson(BudgetPerson budgetPerson) {
        this.budgetPerson = budgetPerson;
    }

    /**
     * Gets the budgetPersonnelCalculatedAmounts attribute. 
     * @return Returns the budgetPersonnelCalculatedAmounts.
     */
    public List<BudgetPersonnelCalculatedAmount> getBudgetPersonnelCalculatedAmounts() {
        return budgetPersonnelCalculatedAmounts;
    }

    /**
     * Sets the budgetPersonnelCalculatedAmounts attribute value.
     * @param budgetPersonnelCalculatedAmounts The budgetPersonnelCalculatedAmounts to set.
     */
    public void setBudgetPersonnelCalculatedAmounts(List<BudgetPersonnelCalculatedAmount> budgetPersonnelCalculatedAmounts) {
        this.budgetPersonnelCalculatedAmounts = budgetPersonnelCalculatedAmounts;
    }

    @Override
    public List getBudgetCalculatedAmounts() {
        return getBudgetPersonnelCalculatedAmounts();
    }

    /**
     * Gets the budgetPersonnelRateAndBaseList attribute. 
     * @return Returns the budgetPersonnelRateAndBaseList.
     */
    public List<BudgetPersonnelRateAndBase> getBudgetPersonnelRateAndBaseList() {
        return budgetPersonnelRateAndBaseList;
    }

    /**
     * Sets the budgetPersonnelRateAndBaseList attribute value.
     * @param budgetPersonnelRateAndBaseList The budgetPersonnelRateAndBaseList to set.
     */
    public void setBudgetPersonnelRateAndBaseList(List<BudgetPersonnelRateAndBase> budgetPersonnelRateAndBaseList) {
        this.budgetPersonnelRateAndBaseList = budgetPersonnelRateAndBaseList;
    }

    public String getEffdtAfterStartdtMsg() {
        this.refreshReferenceObject("budgetPerson");
        if (getStartDate() != null && budgetPerson.getEffectiveDate().after(getStartDate())) {
            return "Earning Period Start Date is before " + budgetPerson.getPersonName() + "'s Salary Effective Date. Salary is calculated based on Effective Date.";
        }
        return "";
    }

    public void setEffdtAfterStartdtMsg(String effdtAfterStartdtMsg) {
        this.effdtAfterStartdtMsg = effdtAfterStartdtMsg;
    }

    public KualiDecimal getPersonMonths() {
        KualiDecimal result = null;
        if (getStartDate() != null && getEndDate() != null) {
            Calendar startDateCalendar = Calendar.getInstance();
            startDateCalendar.setTime(getStartDate());
            int startMonth = startDateCalendar.get(MONTH);
            Calendar endDateCalendar = Calendar.getInstance();
            endDateCalendar.setTime(getEndDate());
            double personMonths = 0d;
            while (startDateCalendar.compareTo(endDateCalendar) <= 0) {
                double noOfDaysInMonth = startDateCalendar.getActualMaximum(DAY_OF_MONTH);
                double noOfActualDays = 0;
                if (startDateCalendar.get(MONTH) == endDateCalendar.get(MONTH) && startDateCalendar.get(Calendar.YEAR) == endDateCalendar.get(Calendar.YEAR)) {
                    noOfActualDays = endDateCalendar.get(DAY_OF_MONTH) - startDateCalendar.get(DAY_OF_MONTH) + 1;
                } else if (startDateCalendar.get(MONTH) == startMonth) {
                    noOfActualDays = noOfDaysInMonth - startDateCalendar.get(DAY_OF_MONTH) + 1;
                } else {
                    noOfActualDays = noOfDaysInMonth;
                }
                startDateCalendar.add(MONTH, 1);
                startDateCalendar.set(DAY_OF_MONTH, 1);
                personMonths += (noOfActualDays / noOfDaysInMonth);
            }
            result = new KualiDecimal(new BudgetDecimal(personMonths).multiply(getPercentEffort()).divide(new BudgetDecimal(100)).bigDecimalValue());
        }
        return result;
    }

    /**
     * Gets the budgetPersonnelLineItemId attribute. 
     * @return Returns the budgetPersonnelLineItemId.
     */
    public Long getBudgetPersonnelLineItemId() {
        return budgetPersonnelLineItemId;
    }

    /**
     * Sets the budgetPersonnelLineItemId attribute value.
     * @param budgetPersonnelLineItemId The budgetPersonnelLineItemId to set.
     */
    public void setBudgetPersonnelLineItemId(Long budgetPersonnelLineItemId) {
        this.budgetPersonnelLineItemId = budgetPersonnelLineItemId;
    }

    /**
     * 
     * This method creates new instance of BudgetPersonnelCalculatedAmount
     * @return
     */
    public AbstractBudgetCalculatedAmount getNewBudgetPersonnelCalculatedAmount() {
        return new BudgetPersonnelCalculatedAmount();
    }
}
