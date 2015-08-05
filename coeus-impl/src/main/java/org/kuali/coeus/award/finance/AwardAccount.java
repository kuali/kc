package org.kuali.coeus.award.finance;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.*;

@Entity
@Table(name="AWARD_ACCOUNT")
public class AwardAccount extends KcPersistableBusinessObjectBase {

    @PortableSequenceGenerator(name = "SEQ_AWARD_ACCOUNT_ID")
    @GeneratedValue(generator = "SEQ_AWARD_ACCOUNT_ID")
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @Column(name = "CREATED_BY_AWARD_ID")
    private Long createdByAwardId;
    @Column(name="STATUS")
    private String status;
    @Column(name="BUDGETED")
    private ScaleTwoDecimal budgeted;
    @Column(name="PENDING")
    private ScaleTwoDecimal pending;
    @Column(name="INCOME")
    private ScaleTwoDecimal income;
    @Column(name="EXPENSE")
    private ScaleTwoDecimal expense;
    @Column(name="AVAILABLE")
    private ScaleTwoDecimal available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getCreatedByAwardId() {
        return createdByAwardId;
    }

    public void setCreatedByAwardId(Long createdByAwardId) {
        this.createdByAwardId = createdByAwardId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
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
}
