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
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.web.ui.Column;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a DTO bean for storing data for the Current Report
 *
 * Note 1: At the time of the development of the Current and Pending Reports, Contacts hadn't been implemented in InstitutionalProposal
 * As a workaround, any data coming from Key Personnel will come from the originating DevelopmentProposal. If there is no DevelopmentProposal, then the
 * AwardPersons are searched if there's a linked AwardFundingProposal. If there's no DevelopmentProposal and no linked Award, an UnsupportedOperationException
 * will be thrown during the creation of the report
 *
 * Rice foolishly requires beans used in DisplayTag to be BusinessObjects, so this class implements an interface whose behavior is completely inapplicable
 */
public class CurrentReportBean extends ReportBean {
    /**
     * Award.awardNumber
     */
    private String awardNumber;
    /**
     * Source: Award.sponsor.sponsorName
     */
    private String sponsorName;

    /**
     * Source: Award (personId in KeyPersonnel) -> roleCode
     */
    private String roleCode;

    /**
     * Source: Award.title
     */
    private String awardTitle;

    /**
     * Source: The mocks suggest Award.timeAndMoney.obligatedAmount would be useful, but at the time of the development of the Current report, Award Time and
     * Money hadn't been completed. So, we'll sum the awardAmountInfo.obliDistributableAmount values for a total.
     */
    private ScaleTwoDecimal awardAmount;

    /**
     * Source: Award.awardEffectiveDate
     */
    private Date projectStartDate;

    /**
     * Source: The mocks suggest Award.timeAndMoney.projectEndDate would be useful, but at the time of the development of the Current report, Award Time and
     * Money hadn't been completed. So, we'll use the latest awardAmountInfo.obligationExpirationDate values for the projectEndDate. 
     */
    private Date projectEndDate;

    /**
     * Source: Award (personId in KeyPersonnel) -> academicYearEffort
     */
    private ScaleTwoDecimal academicYearEffort;

    /**
     * Source: Award (personId in KeyPersonnel) -> calendarYearEffort
     */
    private ScaleTwoDecimal calendarYearEffort;

    /**
     * Source: Award (personId in KeyPersonnel) -> summerYearEffort
     */
    private ScaleTwoDecimal summerEffort;

    private ScaleTwoDecimal totalEffort;
    private String sponsorAwardNumber;
    private ScaleTwoDecimal totalDirectCostTotal;
    private ScaleTwoDecimal totalIndirectCostTotal;
    private List<AwardCustomData> awardCustomDataList;
    private ParameterService parameterService;

    public CurrentReportBean(AwardPerson awardPerson) {
        this.roleCode = awardPerson.getRoleCode();
        this.academicYearEffort = awardPerson.getAcademicYearEffort();
        this.calendarYearEffort = awardPerson.getCalendarYearEffort();
        this.summerEffort = awardPerson.getSummerEffort();
        this.totalEffort = awardPerson.getTotalEffort();

        Award award = awardPerson.getAward();
        this.awardNumber = award.getAwardNumber();
        this.sponsorName = award.getSponsorName();
        this.sponsorAwardNumber = award.getSponsorAwardNumber();
        this.awardTitle = award.getTitle();
        this.awardAmount = award.getObligatedTotal();
        this.projectStartDate = award.getAwardEffectiveDate();
        this.projectEndDate = award.findLatestFinalExpirationDate();
        parameterService = KcServiceLocator.getService(ParameterService.class);
        String directIndirectEnabledValue = parameterService.getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST");

        for(AwardAmountInfo awardAmountInfo:award.getAwardAmountInfos()){
            if(directIndirectEnabledValue.equals("1")){
                this.totalDirectCostTotal =  awardAmountInfo.getObligatedTotalDirect();
                this.totalIndirectCostTotal = awardAmountInfo.getObligatedTotalIndirect();
            }
        }
        awardCustomDataList = new ArrayList<>();
        
        String customGroupName = parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.CURRENT_PENDING_REPORT_GROUP_NAME);
        for(AwardCustomData awardcutomdata :award.getAwardCustomDataList()) {
            if(awardcutomdata.getCustomAttribute()!=null && awardcutomdata.getCustomAttribute().getGroupName().equals(customGroupName)){
                awardCustomDataList.add(awardcutomdata);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrentReportBean)) return false;
        return awardNumber.equals(((CurrentReportBean) o).awardNumber);

    }

    public ScaleTwoDecimal getAcademicYearEffort() {
        return academicYearEffort;
    }

    public ScaleTwoDecimal getAwardAmount() {
        return awardAmount;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public String getAwardTitle() {
        return awardTitle;
    }

    public ScaleTwoDecimal getCalendarYearEffort() {
        return calendarYearEffort;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
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

    @Override
    public int hashCode() {
        return awardNumber.hashCode();
    }

    @SuppressWarnings("deprecation")
    protected List<Column> createColumns() {
        List<Column> columns = new ArrayList<>();
        columns.add(createColumn("Sponsor Award ID", "sponsorAwardNumber", sponsorAwardNumber, String.class));
        columns.add(createColumn("Sponsor", "sponsorName", sponsorName, String.class));
        columns.add(createColumn("Role", "roleCode", roleCode, String.class));
        columns.add(createColumn("Title", "awardTitle", awardTitle, String.class));
        String directIndirectEnabledValue = parameterService.getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST");
        if(directIndirectEnabledValue.equals("1")){
            columns.add(createColumn("Total Direct Cost","totalDirectCostTotal",totalDirectCostTotal,ScaleTwoDecimal.class));
            columns.add(createColumn("Total F&A Cost","totalIndirectCostTotal",totalIndirectCostTotal,ScaleTwoDecimal.class));
        }
        columns.add(createColumn("Award Amount", "awardAmount", awardAmount, ScaleTwoDecimal.class));
        columns.add(createColumn("Effective Date", "projectStartDate", projectStartDate, Date.class));
        columns.add(createColumn("End Date", "projectEndDate", projectEndDate, Date.class));
        columns.add(createColumn("Effort %", "totalEffort", totalEffort, ScaleTwoDecimal.class));
        columns.add(createColumn("Academic Year Effort %", "academicYearEffort", academicYearEffort, ScaleTwoDecimal.class));
        columns.add(createColumn("Summer Effort %", "summerEffort", summerEffort, ScaleTwoDecimal.class));
        columns.add(createColumn("Calendar Year Effort %", "calendarYearEffort", calendarYearEffort, ScaleTwoDecimal.class));
        if(awardCustomDataList.size()>0){
            for(AwardCustomData awardcutomdata :awardCustomDataList) {
                columns.add(createColumn(awardcutomdata.getCustomAttribute().getLabel(), "awardCustomDataList", awardcutomdata.getValue(), String.class));
            }
        }
        return columns;
    }

    public void setTotalEffort(ScaleTwoDecimal totalEffort) {
        this.totalEffort = totalEffort;
    }

    public ScaleTwoDecimal getTotalEffort() {
        return totalEffort;
    }

    public void setSponsorAwardNumber(String sponsorAwardNumber) {
        this.sponsorAwardNumber = sponsorAwardNumber;
    }

    public String getSponsorAwardNumber() {
        return sponsorAwardNumber;
    }

    public void setTotalDirectCostTotal(ScaleTwoDecimal totalDirectCostTotal) {
        this.totalDirectCostTotal = totalDirectCostTotal;
    }

    public ScaleTwoDecimal getTotalDirectCostTotal() {
        return totalDirectCostTotal;
    }

    public void setTotalIndirectCostTotal(ScaleTwoDecimal totalIndirectCostTotal) {
        this.totalIndirectCostTotal = totalIndirectCostTotal;
    }

    public ScaleTwoDecimal getTotalIndirectCostTotal() {
        return totalIndirectCostTotal;
    }

    public void setAwardCustomDataList(List<AwardCustomData> awardCustomDataList) {
        this.awardCustomDataList = awardCustomDataList;
    }

    public List<AwardCustomData> getAwardCustomDataList() {
        return awardCustomDataList;
    }


}
