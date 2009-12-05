package org.kuali.kra.institutionalproposal.printing.print;

import org.kuali.rice.kns.util.KualiDecimal;

import java.sql.Date;

/**
 * This class is a DTO bean for storing data for the Pending Report
 *
 * Note: At the time of the development of the Current and Pending Reports, Contacts hadn't been implemented in InstitutionalProposal
 * As a workaround, any data coming from Key Personnel will come from the originating DevelopmentProposal. If there is no DevelopmentProposal, then the
 * AwardPersons are searched if there's a linked AwardFundingProposal. If there's no DevelopmentProposal and no linked Award, an UnsupportedOperationException
 * will be thrown during the creation of the report
 */
public class PendingReportBean {
    /**
     * Source: InstitutionalProposal.proposalNumber
     */
    private String proposalNumber;

    /**
     * Source: InstitutionalProposal.sponsorCode -> Sponsor.sponsorName
     */
    private String sponsorName;

    /**
     * Source: InstitutionalProposal (personId in KeyPersonnel) -> roleCode
     */
    private String roleCode;

    /**
     * Source: InstitutionalProposal.title
     */
    private String proposalTitle;

    /**
     * Source: InstitutionalProposal.totalDirectCostTotal
     */
    private KualiDecimal totalDirectCostTotal;

    /**
     * Source: InstitutionalProposal.totalIndirectCostTotal
     */
    private KualiDecimal totalIndirectCostTotal;

    /**
     * Source: InstitutionalProposal.requestedStartDateInitial
     */
    private Date requestedStartDateInitial;

    /**
     * Source: InstitutionalProposal.requestedEndDateTotal
     */
    private Date requestedEndDateTotal;

    /**
     * Source: InstitutionalProposal (personId in KeyPersonnel) -> academicYearEffort
     */
    private KualiDecimal academicYearEffort;

    /**
     * Source: InstitutionalProposal (personId in KeyPersonnel) -> summerYearEffort
     */
    private KualiDecimal summerYearEffort;

    /**
     * Source: InstitutionalProposal (personId in KeyPersonnel) -> calendarYearEffort
     */
    private KualiDecimal calendarYearEffort;

    public PendingReportBean() {
        super();
    }

    public PendingReportBean(String proposalNumber, String sponsorName, String roleCode, String proposalTitle, KualiDecimal totalDirectCostTotal,
                             KualiDecimal totalIndirectCostTotal, Date requestedStartDateInitial, Date requestedEndDateTotal) {
        this();
        this.proposalNumber = proposalNumber;
        this.sponsorName = sponsorName;
        this.roleCode = roleCode;
        this.proposalTitle = proposalTitle;
        this.totalDirectCostTotal = totalDirectCostTotal;
        this.totalIndirectCostTotal = totalIndirectCostTotal;
        this.requestedStartDateInitial = requestedStartDateInitial;
        this.requestedEndDateTotal = requestedEndDateTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PendingReportBean)) return false;

        PendingReportBean that = (PendingReportBean) o;

        if (!proposalNumber.equals(that.proposalNumber)) return false;

        return true;
    }

    public KualiDecimal getAcademicYearEffort() {
        return academicYearEffort;
    }

    public KualiDecimal getCalendarYearEffort() {
        return calendarYearEffort;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public String getProposalTitle() {
        return proposalTitle;
    }

    public Date getRequestedEndDateTotal() {
        return requestedEndDateTotal;
    }

    public Date getRequestedStartDateInitial() {
        return requestedStartDateInitial;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public KualiDecimal getSummerYearEffort() {
        return summerYearEffort;
    }

    public KualiDecimal getTotalDirectCostTotal() {
        return totalDirectCostTotal;
    }

    public KualiDecimal getTotalIndirectCostTotal() {
        return totalIndirectCostTotal;
    }

    @Override
    public int hashCode() {
        return proposalNumber.hashCode();
    }

    public void setAcademicYearEffort(KualiDecimal academicYearEffort) {
        this.academicYearEffort = academicYearEffort;
    }

    public void setCalendarYearEffort(KualiDecimal calendarYearEffort) {
        this.calendarYearEffort = calendarYearEffort;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public void setProposalTitle(String proposalTitle) {
        this.proposalTitle = proposalTitle;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public void setSummerYearEffort(KualiDecimal summerYearEffort) {
        this.summerYearEffort = summerYearEffort;
    }

    public void setRequestedEndDateTotal(Date requestedEndDateTotal) {
        this.requestedEndDateTotal = requestedEndDateTotal;
    }

    public void setRequestedStartDateInitial(Date requestedStartDateInitial) {
        this.requestedStartDateInitial = requestedStartDateInitial;
    }

    public void setTotalDirectCostTotal(KualiDecimal totalDirectCostTotal) {
        this.totalDirectCostTotal = totalDirectCostTotal;
    }

    public void setTotalIndirectCostTotal(KualiDecimal totalIndirectCostTotal) {
        this.totalIndirectCostTotal = totalIndirectCostTotal;
    }
}
