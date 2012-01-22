package org.kuali.kra.common.printing;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomData;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.web.ui.Column;

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
    
    private ParameterService parameterService;
    
    private List<InstitutionalProposalCustomData> institutionalProposalCustomDataList;

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
        this.academicYearEffort = ipPerson.getAcademicYearEffort();
        this.calendarYearEffort = ipPerson.getCalendarYearEffort();
        this.summerEffort = ipPerson.getSummerEffort();
        this.totalEffort = ipPerson.getTotalEffort();
        
        institutionalProposalCustomDataList = new ArrayList<InstitutionalProposalCustomData>();
        parameterService = KraServiceLocator.getService(ParameterService.class);
        String customGroupName = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.CURRENT_PENDING_REPORT_GROUP_NAME);
      
        for(InstitutionalProposalCustomData custData:proposal.getInstitutionalProposalCustomDataList()){
                if(custData.getCustomAttributeId()!=null){
                    custData.setCustomAttribute(proposal.getInstitutionalProposalDocument().getCustomAttributeDocument(custData.getCustomAttributeId().toString()).getCustomAttribute());
                    if(customGroupName.equals(custData.getCustomAttribute().getGroupName()))
                        institutionalProposalCustomDataList.add(custData);
                }
        }

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
        columns.add(createColumn("Proposal Number", "proposalNumber", proposalNumber, String.class));
        columns.add(createColumn("Agency", "sponsorName", sponsorName, String.class));
        columns.add(createColumn("Role", "roleCode", roleCode, String.class));
        columns.add(createColumn("Title", "proposalTitle", proposalTitle, String.class));
        columns.add(createColumn("Total Direct Cost", "totalDirectCostTotal", totalDirectCostTotal, KualiDecimal.class));
        columns.add(createColumn("Total F&A Cost", "totalIndirectCostTotal", totalIndirectCostTotal, KualiDecimal.class));
        columns.add(createColumn("Total Requested Cost", "totalRequestedCost", getTotalRequestedCost(), KualiDecimal.class));

//        String startDate = (requestedStartDateInitial != null) ? DATE_FORMATTER.format(requestedStartDateInitial) : "";
//        columns.add(createColumn("Effective Date", "requestedStartDateInitial", startDate));
//
//        String endDate = (requestedEndDateTotal != null) ? DATE_FORMATTER.format(requestedEndDateTotal) : "";
//        columns.add(createColumn("End Date", "requestedEndDateTotal", endDate));

        columns.add(createColumn("Effective Date", "requestedStartDateInitial", requestedStartDateInitial, Date.class));
        columns.add(createColumn("End Date", "requestedEndDateTotal", requestedEndDateTotal, Date.class));
        columns.add(createColumn("% Effort", "totalEffort", totalEffort, KualiDecimal.class));
        columns.add(createColumn("Academic Year Effort %", "academicYearEffort", academicYearEffort, KualiDecimal.class));
        columns.add(createColumn("Summer Year Effort %", "summerYearEffort", summerEffort, KualiDecimal.class));
        columns.add(createColumn("Calendar Year Effort %", "calendarYearEffort", calendarYearEffort, KualiDecimal.class));
        if(institutionalProposalCustomDataList.size()>0){
            for(InstitutionalProposalCustomData institutionalProposalCustomData :institutionalProposalCustomDataList) {
                columns.add(createColumn(institutionalProposalCustomData.getCustomAttribute().getLabel(), "institutionalProposalCustomDataList", institutionalProposalCustomData.getValue(), String.class));
            }
        }
        return columns;
    }

    public void setInstitutionalProposalCustomDataList(List<InstitutionalProposalCustomData> institutionalProposalCustomDataList) {
        this.institutionalProposalCustomDataList = institutionalProposalCustomDataList;
    }

    public List<InstitutionalProposalCustomData> getInstitutionalProposalCustomDataList() {
        return institutionalProposalCustomDataList;
    }
}
