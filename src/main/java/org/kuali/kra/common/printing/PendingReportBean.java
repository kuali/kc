package org.kuali.kra.common.printing;

import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.kns.util.KualiDecimal;
import org.kuali.rice.kns.web.ui.Column;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a DTO bean for storing data for the Pending Report
 */
public class PendingReportBean extends ReportBean {
    /**
     * Source: InstitutionalProposal.proposalNumber
     */
    private String proposalNumber;

    /**
     * Source: InstitutionalProposal.sponsorCode -> Sponsor.sponsorName
     */
    private String sponsorName;

    /**
     * Source: InstitutionalProposal.projectPersons.roleCode
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
     * Source: InstitutionalProposal.projectPersons.academicYearEffort
     */
    private KualiDecimal academicYearEffort;

    /**
     * Source: InstitutionalProposal.projectPersons.calendarYearEffort
     */
    private KualiDecimal calendarYearEffort;

    /**
     * Source: InstitutionalProposal.projectPersons.summerEffort
     */
    private KualiDecimal summerEffort;

    /**
     * Source: InstitutionalProposal.projectPersons.totalEffort
     */
    private KualiDecimal totalEffort;

    public PendingReportBean(InstitutionalProposalPerson ipPerson) {
        InstitutionalProposal proposal = ipPerson.getInstitutionalProposal();
        this.proposalNumber = proposal.getProposalNumber();
        this.sponsorName = proposal.getSponsorName();
        this.roleCode = ipPerson.getRoleCode();
        this.proposalTitle = proposal.getTitle();
        this.totalDirectCostTotal = proposal.getTotalDirectCostTotal();
        this.totalIndirectCostTotal = proposal.getTotalIndirectCostTotal();
        this.requestedStartDateInitial = proposal.getRequestedStartDateInitial();
        this.requestedEndDateTotal = proposal.getRequestedEndDateTotal();
        this.academicYearEffort = ipPerson.getAcademicYearEffort()==null ? KualiDecimal.ZERO : ipPerson.getAcademicYearEffort();
        this.calendarYearEffort = ipPerson.getCalendarYearEffort()==null ? KualiDecimal.ZERO : ipPerson.getCalendarYearEffort();
        this.summerEffort = ipPerson.getSummerEffort()==null ? KualiDecimal.ZERO : ipPerson.getSummerEffort();
        this.totalEffort = ipPerson.getTotalEffort()==null ? KualiDecimal.ZERO : ipPerson.getTotalEffort();
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

    public KualiDecimal getSummerEffort() {
        return summerEffort;
    }

    public KualiDecimal getTotalDirectCostTotal() {
        return totalDirectCostTotal;
    }

    public KualiDecimal getTotalEffort() {
        return totalEffort;
    }

    public KualiDecimal getTotalIndirectCostTotal() {
        return totalIndirectCostTotal;
    }

    public KualiDecimal getTotalRequestedCost() {
        return totalDirectCostTotal.add(totalIndirectCostTotal);
    }

    @Override
    public int hashCode() {
        return proposalNumber.hashCode();
    }

    protected List<Column> createColumns() {
        List<Column> columns = new ArrayList<Column>();
        columns.add(createColumn("Proposal Number", "proposalNumber", proposalNumber));
        columns.add(createColumn("Agency", "sponsorName", sponsorName));
        columns.add(createColumn("Role", "roleCode", roleCode));
        columns.add(createColumn("Title", "proposalTitle", proposalTitle));
        columns.add(createColumn("Total Direct Cost", "totalDirectCostTotal", totalDirectCostTotal));
        columns.add(createColumn("Total F&A Cost", "totalIndirectCostTotal", totalIndirectCostTotal));
        columns.add(createColumn("Total Requested Cost", "totalRequestedCost", getTotalRequestedCost()));

//        String startDate = (requestedStartDateInitial != null) ? DATE_FORMATTER.format(requestedStartDateInitial) : "";
//        columns.add(createColumn("Effective Date", "requestedStartDateInitial", startDate));
//
//        String endDate = (requestedEndDateTotal != null) ? DATE_FORMATTER.format(requestedEndDateTotal) : "";
//        columns.add(createColumn("End Date", "requestedEndDateTotal", endDate));

        columns.add(createColumn("Effective Date", "requestedStartDateInitial", requestedStartDateInitial));
        columns.add(createColumn("End Date", "requestedEndDateTotal", requestedEndDateTotal));
        columns.add(createColumn("% Effort", "totalEffort", totalEffort));
        columns.add(createColumn("Academic Year Effort", "academicYearEffort", academicYearEffort));
        columns.add(createColumn("Summer Year Effort", "summerYearEffort", summerEffort));
        columns.add(createColumn("Calendar Year Effort", "calendarYearEffort", calendarYearEffort));
        return columns;
    }
}
