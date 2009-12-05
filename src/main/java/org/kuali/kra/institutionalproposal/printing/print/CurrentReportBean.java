package org.kuali.kra.institutionalproposal.printing.print;

import org.kuali.rice.kns.util.KualiDecimal;

import java.sql.Date;

/**
 * This class is a DTO bean for storing data for the Current Report
 *
 * Note 1: At the time of the development of the Current and Pending Reports, Contacts hadn't been implemented in InstitutionalProposal
 * As a workaround, any data coming from Key Personnel will come from the originating DevelopmentProposal. If there is no DevelopmentProposal, then the
 * AwardPersons are searched if there's a linked AwardFundingProposal. If there's no DevelopmentProposal and no linked Award, an UnsupportedOperationException
 * will be thrown during the creation of the report
 *
 *
 */
public class CurrentReportBean {
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
    private String awardAmount;

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
     * Source: Award (personId in KeyPersonnel) -> summerYearEffort
     */
    private KualiDecimal summerYearEffort;

    /**
     * Source: Award (personId in KeyPersonnel) -> calendarYearEffort
     */
    private KualiDecimal calendarYearEffort;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrentReportBean)) return false;

        CurrentReportBean that = (CurrentReportBean) o;

        if (!awardNumber.equals(that.awardNumber)) return false;

        return true;
    }

    public KualiDecimal getAcademicYearEffort() {
        return academicYearEffort;
    }

    public String getAwardAmount() {
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

    public KualiDecimal getSummerYearEffort() {
        return summerYearEffort;
    }

    @Override
    public int hashCode() {
        return awardNumber.hashCode();
    }

    public void setAcademicYearEffort(KualiDecimal academicYearEffort) {
        this.academicYearEffort = academicYearEffort;
    }

    public void setAwardAmount(String awardAmount) {
        this.awardAmount = awardAmount;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public void setAwardTitle(String awardTitle) {
        this.awardTitle = awardTitle;
    }

    public void setCalendarYearEffort(KualiDecimal calendarYearEffort) {
        this.calendarYearEffort = calendarYearEffort;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
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
}
