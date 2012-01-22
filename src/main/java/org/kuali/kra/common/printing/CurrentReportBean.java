package org.kuali.kra.common.printing;

import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.core.api.util.type.KualiDecimal;
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
    private KualiDecimal awardAmount;

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
    private KualiDecimal academicYearEffort;

    /**
     * Source: Award (personId in KeyPersonnel) -> calendarYearEffort
     */
    private KualiDecimal calendarYearEffort;

    /**
     * Source: Award (personId in KeyPersonnel) -> summerYearEffort
     */
    private KualiDecimal summerEffort;

    private KualiDecimal totalEffort;
    private String sponsorAwardNumber;
    private KualiDecimal totalDirectCostTotal;
    private KualiDecimal totalIndirectCostTotal;
    private List<AwardCustomData> awardCustomDataList;
    private ParameterService parameterService;
    protected static final String ROOT_AWARD_NUMBER_PARAMETER = "rootAwardNumber";
    
    public CurrentReportBean(AwardPerson awardPerson) {
        this.roleCode = awardPerson.getRoleCode();
        this.academicYearEffort = awardPerson.getAcademicYearEffort();
        this.calendarYearEffort = awardPerson.getCalendarYearEffort();
        this.summerEffort = awardPerson.getSummerEffort();
        this.totalEffort = awardPerson.getTotalEffort();

        Award award = awardPerson.getAward();
        this.awardNumber = award.getAwardNumber();
        this.sponsorName = award.getSponsorName();
        this.sponsorAwardNumber = award.getSponsorCode();
        this.awardTitle = award.getTitle();
        this.awardAmount = award.calculateObligatedDistributedAmountTotal();
        this.projectStartDate = award.getAwardEffectiveDate();
        this.projectEndDate = award.findLatestFinalExpirationDate();
        parameterService = KraServiceLocator.getService(ParameterService.class);
        String directIndirectEnabledValue = parameterService.getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST");

        for(AwardAmountInfo awardAmountInfo:award.getAwardAmountInfos()){
            if(directIndirectEnabledValue.equals("1")){
                this.totalDirectCostTotal =  awardAmountInfo.getObligatedTotalDirect();
                this.totalIndirectCostTotal = awardAmountInfo.getObligatedTotalIndirect();
            }
        }
        awardCustomDataList = new ArrayList<AwardCustomData>();
        
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

    public KualiDecimal getAcademicYearEffort() {
        return academicYearEffort;
    }

    public KualiDecimal getAwardAmount() {
        return awardAmount;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public String getAwardTitle() {
        return awardTitle;
    }

    public KualiDecimal getCalendarYearEffort() {
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

    public KualiDecimal getSummerEffort() {
        return summerEffort;
    }

    @Override
    public int hashCode() {
        return awardNumber.hashCode();
    }

    protected List<Column> createColumns() {
        List<Column> columns = new ArrayList<Column>();
        columns.add(createColumn("Sponsor Award ID", "sponsorAwardNumber", sponsorAwardNumber, String.class));
        columns.add(createColumn("Sponsor", "sponsorName", sponsorName, String.class));
        columns.add(createColumn("Role", "roleCode", roleCode, String.class));
        columns.add(createColumn("Title", "awardTitle", awardTitle, String.class));
        String directIndirectEnabledValue = parameterService.getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST");
        if(directIndirectEnabledValue.equals("1")){
            columns.add(createColumn("Total Direct Cost","totalDirectCostTotal",totalDirectCostTotal,KualiDecimal.class));
            columns.add(createColumn("Total F&A Cost","totalIndirectCostTotal",totalIndirectCostTotal,KualiDecimal.class));
        }
        columns.add(createColumn("Award Amount", "awardAmount", awardAmount, KualiDecimal.class));
        columns.add(createColumn("Effective Date", "projectStartDate", projectStartDate, Date.class));
        columns.add(createColumn("End Date", "projectEndDate", projectEndDate, Date.class));
        columns.add(createColumn("Effort %", "totalEffort", totalEffort, KualiDecimal.class));
        columns.add(createColumn("Academic Year Effort %", "academicYearEffort", academicYearEffort, KualiDecimal.class));
        columns.add(createColumn("Summer Effort %", "summerEffort", summerEffort, KualiDecimal.class));
        columns.add(createColumn("Calendar Year Effort %", "calendarYearEffort", calendarYearEffort, KualiDecimal.class));
        if(awardCustomDataList.size()>0){
            for(AwardCustomData awardcutomdata :awardCustomDataList) {
                columns.add(createColumn(awardcutomdata.getCustomAttribute().getLabel(), "awardCustomDataList", awardcutomdata.getValue(), String.class));
            }
        }
        return columns;
    }

    public void setTotalEffort(KualiDecimal totalEffort) {
        this.totalEffort = totalEffort;
    }

    public KualiDecimal getTotalEffort() {
        return totalEffort;
    }

    public void setSponsorAwardNumber(String sponsorAwardNumber) {
        this.sponsorAwardNumber = sponsorAwardNumber;
    }

    public String getSponsorAwardNumber() {
        return sponsorAwardNumber;
    }

    public void setTotalDirectCostTotal(KualiDecimal totalDirectCostTotal) {
        this.totalDirectCostTotal = totalDirectCostTotal;
    }

    public KualiDecimal getTotalDirectCostTotal() {
        return totalDirectCostTotal;
    }

    public void setTotalIndirectCostTotal(KualiDecimal totalIndirectCostTotal) {
        this.totalIndirectCostTotal = totalIndirectCostTotal;
    }

    public KualiDecimal getTotalIndirectCostTotal() {
        return totalIndirectCostTotal;
    }

    public void setAwardCustomDataList(List<AwardCustomData> awardCustomDataList) {
        this.awardCustomDataList = awardCustomDataList;
    }

    public List<AwardCustomData> getAwardCustomDataList() {
        return awardCustomDataList;
    }


}
