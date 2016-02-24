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
package org.kuali.coeus.award;

import org.kuali.coeus.award.finance.AccountStatus;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public class AccountInformationBean {
    private String status;
    private ScaleTwoDecimal budgeted;
    private ScaleTwoDecimal pending;
    private ScaleTwoDecimal income;
    private ScaleTwoDecimal expense;
    private ScaleTwoDecimal available;


    public AccountInformationBean() {
            this.status = AccountStatus.AVAILABLE.name();
            this.setIncome(ScaleTwoDecimal.ZERO);
            this.setPending(ScaleTwoDecimal.ZERO);
            this.setAvailable(ScaleTwoDecimal.ZERO);
            this.setExpense(ScaleTwoDecimal.ZERO);
            this.setBudgeted(ScaleTwoDecimal.ZERO);
    }

    public ScaleTwoDecimal getBudgeted() {
        return budgeted;
    }

    public void setBudgeted(ScaleTwoDecimal budgeted) {
        this.budgeted = budgeted;
    }

    public ScaleTwoDecimal getPending() {
        return pending;
    }

    public void setPending(ScaleTwoDecimal pending) {
        this.pending = pending;
    }

    public ScaleTwoDecimal getIncome() {
        return income;
    }

    public void setIncome(ScaleTwoDecimal income) {
        this.income = income;
    }

    public ScaleTwoDecimal getExpense() {
        return expense;
    }

    public void setExpense(ScaleTwoDecimal expense) {
        this.expense = expense;
    }

    public ScaleTwoDecimal getAvailable() {
        return available;
    }

    public void setAvailable(ScaleTwoDecimal available) {
        this.available = available;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
