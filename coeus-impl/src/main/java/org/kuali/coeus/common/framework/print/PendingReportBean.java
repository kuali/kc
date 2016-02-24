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
package org.kuali.coeus.common.framework.print;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomData;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
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
    private ScaleTwoDecimal totalDirectCostTotal;

    /**
     * Source: InstitutionalProposal.totalIndirectCostTotal
     */
    private ScaleTwoDecimal totalIndirectCostTotal;

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
    private ScaleTwoDecimal academicYearEffort;

    /**
     * Source: InstitutionalProposal.projectPersons.calendarYearEffort
     */
    private ScaleTwoDecimal calendarYearEffort;

    /**
     * Source: InstitutionalProposal.projectPersons.summerEffort
     */
    private ScaleTwoDecimal summerEffort;

    /**
     * Source: InstitutionalProposal.projectPersons.totalEffort
     */
    private ScaleTwoDecimal totalEffort;
    
    private ParameterService parameterService;
    
    private List<InstitutionalProposalCustomData> institutionalProposalCustomDataList;

    protected ParameterService getParameterService(){
        if (parameterService ==null)
            parameterService = KcServiceLocator.getService(ParameterService.class);
        return parameterService;
    }


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
        String customGroupName = getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.CURRENT_PENDING_REPORT_GROUP_NAME);
      
        for(InstitutionalProposalCustomData custData:proposal.getInstitutionalProposalCustomDataList()){
                if(custData.getCustomAttributeId()!=null){
                    if(proposal.getInstitutionalProposalDocument().getCustomAttributeDocuments()!= null){
                    custData.setCustomAttribute(proposal.getInstitutionalProposalDocument().getCustomAttributeDocument(custData.getCustomAttributeId().toString()).getCustomAttribute());
                    }
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

    public ScaleTwoDecimal getAcademicYearEffort() {
        return academicYearEffort;
    }

    public ScaleTwoDecimal getCalendarYearEffort() {
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

    public ScaleTwoDecimal getSummerEffort() {
        return summerEffort;
    }

    public ScaleTwoDecimal getTotalDirectCostTotal() {
        return totalDirectCostTotal;
    }

    public ScaleTwoDecimal getTotalEffort() {
        return totalEffort;
    }

    public ScaleTwoDecimal getTotalIndirectCostTotal() {
        return totalIndirectCostTotal;
    }

    public ScaleTwoDecimal getTotalRequestedCost() {
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
        columns.add(createColumn("Total Direct Cost", "totalDirectCostTotal", totalDirectCostTotal, ScaleTwoDecimal.class));
        columns.add(createColumn("Total F&A Cost", "totalIndirectCostTotal", totalIndirectCostTotal, ScaleTwoDecimal.class));
        columns.add(createColumn("Total Requested Cost", "totalRequestedCost", getTotalRequestedCost(), ScaleTwoDecimal.class));

        columns.add(createColumn("Effective Date", "requestedStartDateInitial", requestedStartDateInitial, Date.class));
        columns.add(createColumn("End Date", "requestedEndDateTotal", requestedEndDateTotal, Date.class));
        columns.add(createColumn("% Effort", "totalEffort", totalEffort, ScaleTwoDecimal.class));
        columns.add(createColumn("Academic Year Effort %", "academicYearEffort", academicYearEffort, ScaleTwoDecimal.class));
        columns.add(createColumn("Summer Year Effort %", "summerYearEffort", summerEffort, ScaleTwoDecimal.class));
        columns.add(createColumn("Calendar Year Effort %", "calendarYearEffort", calendarYearEffort, ScaleTwoDecimal.class));
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
