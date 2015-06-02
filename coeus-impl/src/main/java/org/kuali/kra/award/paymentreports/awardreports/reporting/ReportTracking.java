/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.paymentreports.awardreports.reporting;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.contact.Contactable;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.Distribution;
import org.kuali.kra.award.paymentreports.*;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.Date;
import java.sql.Timestamp;


public class ReportTracking extends KcPersistableBusinessObjectBase implements Comparable<ReportTracking> {


    private static final long serialVersionUID = -7660491024739306314L;
    private Long awardReportTermId;

    private String awardNumber;

    private String piPersonId;

    private Integer piRolodexId;

    private String piName;

    private String leadUnitNumber;

    private String reportClassCode;

    private String reportCode;

    private String frequencyCode;

    private String frequencyBaseCode;

    private String ospDistributionCode;

    private String statusCode;

    private Date baseDate;

    private Date dueDate;

    private Integer overdue;

    private Date activityDate;

    private String comments;

    private String preparerId;

    private String preparerName;

    private String sponsorCode;

    private String sponsorAwardNumber;

    private String title;

    private String lastUpdateUser;
    private Timestamp lastUpdateDate;

    private ReportClass reportClass;

    private Report report;

    private Frequency frequency;

    private FrequencyBase frequencyBase;

    private ReportStatus reportStatus;

    private Distribution distribution;

    private Unit leadUnit;

    private Sponsor sponsor;

    private transient KcPerson piPerson;

    private transient Rolodex piRolodex;

    private transient KcPerson preparer;

    //item count is used during the search and returning grouped results.
    private transient int itemCount;
    private transient KcPersonService kcPersonService;
    //used to make notifications easier 
    private transient Award award;

    private transient BusinessObjectService businessObjectService;
    private boolean multiEditSelected;


    public Long getAwardReportTermId() {
        return awardReportTermId;
    }

    public void setAwardReportTermId(Long awardReportTermId) {
        this.awardReportTermId = awardReportTermId;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public String getPiName() {
        return piName;
    }

    public void setPiName(String piName) {
        this.piName = piName;
    }

    public String getLeadUnitNumber() {
        return leadUnitNumber;
    }

    public void setLeadUnitNumber(String leadUnitNumber) {
        this.leadUnitNumber = leadUnitNumber;
    }

    public String getReportClassCode() {
        return reportClassCode;
    }

    public void setReportClassCode(String reportClassCode) {
        this.reportClassCode = reportClassCode;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getFrequencyCode() {
        return frequencyCode;
    }

    public void setFrequencyCode(String frequencyCode) {
        this.frequencyCode = frequencyCode;
    }

    public String getFrequencyBaseCode() {
        return frequencyBaseCode;
    }

    public void setFrequencyBaseCode(String frequencyBaseCode) {
        this.frequencyBaseCode = frequencyBaseCode;
    }

    public Date getBaseDate() {
        return baseDate;
    }

    public void setBaseDate(Date baseDate) {
        this.baseDate = baseDate;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getOspDistributionCode() {
        return ospDistributionCode;
    }

    public void setOspDistributionCode(String ospDistributionCode) {
        this.ospDistributionCode = ospDistributionCode;
    }

    public Integer getOverdue() {
        return overdue;
    }

    public void setOverdue(Integer overdue) {
        this.overdue = overdue;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPreparerId() {
        return preparerId;
    }

    public void setPreparerId(String preparerId) {
        this.preparerId = preparerId;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getSponsorAwardNumber() {
        return sponsorAwardNumber;
    }

    public void setSponsorAwardNumber(String sponsorAwardNumber) {
        this.sponsorAwardNumber = sponsorAwardNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public ReportClass getReportClass() {
        return reportClass;
    }

    public void setReportClass(ReportClass reportClass) {
        this.reportClass = reportClass;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public FrequencyBase getFrequencyBase() {
        return frequencyBase;
    }

    public void setFrequencyBase(FrequencyBase frequencyBase) {
        this.frequencyBase = frequencyBase;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    protected KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public String getPiPersonId() {
        return piPersonId;
    }

    public void setPiPersonId(String piPersonId) {
        this.piPersonId = piPersonId;
    }

    public Integer getPiRolodexId() {
        return piRolodexId;
    }

    public void setPiRolodexId(Integer piRolodexId) {
        this.piRolodexId = piRolodexId;
    }

    public String getPreparerName() {
        return preparerName;
    }

    public void setPreparerName(String preparerName) {
        this.preparerName = preparerName;
    }

    public KcPerson getPiPerson() {
        if (piPerson == null && StringUtils.isNotBlank(getPiPersonId())) {
            piPerson = getKcPersonService().getKcPersonByPersonId(getPiPersonId());
        }
        return piPerson;
    }

    public Rolodex getPiRolodex() {
        if (piRolodex == null && getPiRolodexId() != null) {
            piRolodex =  getBusinessObjectService().findBySinglePrimaryKey(NonOrganizationalRolodex.class, getPiRolodexId());
        }
        return piRolodex;
    }

    public Contactable getPrimaryInvestigator() {
        if (getPiPerson() != null) {
            return getPiPerson();
        } else if (getPiRolodex() != null) {
            return getPiRolodex();
        } else {
            return null;
        }
    }

    public KcPerson getPreparer() {
        if (preparer == null && StringUtils.isNotBlank(getPreparerId())) {
            preparer = getKcPersonService().getKcPersonByPersonId(getPreparerId());
        }
        return preparer;
    }

    public String getPreparerFullname() {
        return getPreparer() != null ? getPreparer().getFullName() : "";
    }

    protected BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setPreparer(KcPerson preparer) {
        this.preparer = preparer;
        if (preparer != null) {
            preparerId = preparer.getPersonId();
            preparerName = preparer.getFullName();
        } else {
            preparerId = null;
            preparerName = null;
        }
    }

    public Unit getLeadUnit() {
        return leadUnit;
    }

    public void setLeadUnit(Unit leadUnit) {
        this.leadUnit = leadUnit;
    }

    public boolean getMultiEditSelected() {
        return multiEditSelected;
    }

    public void setMultiEditSelected(boolean multiEditSelected) {
        this.multiEditSelected = multiEditSelected;
    }

    /**
     * This method implements comparable based on the due date;
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(ReportTracking o) {
        return o == null || o.getDueDate() == null || this.getDueDate() == null ? 0 
                : this.getDueDate().compareTo(o.getDueDate());
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    
    /**
     * 
     * This method checks the fields available on the UI and if any of those fields are different than the DB version, it returns true.
     */
    public boolean hasBeenUpdated(ReportTracking dbReportTracking) {
        boolean retVal = false;
        if (dbReportTracking != null) {
            if (!StringUtils.equalsIgnoreCase(this.getPreparerId(), dbReportTracking.getPreparerId())) {
                retVal = true;
            } else if (!StringUtils.equalsIgnoreCase(this.getStatusCode(), dbReportTracking.getStatusCode())) {
                retVal = true;
            } else if (!StringUtils.equalsIgnoreCase(this.getComments(), dbReportTracking.getComments())) {
                retVal = true;
            } else if (!dateEquals(this.activityDate, dbReportTracking.activityDate)) {
                retVal = true;
            }
        }
        return retVal;
    }
    
    private boolean dateEquals(Date date1, Date date2) {
        boolean retVal = false;
        if (date1 == null && date2 == null) {
            retVal = true;
        } else if (date1 != null && date2 != null && date1.equals(date2)) {
            return true;
        }
        return retVal;
    }
    
    /**
     * 
     * This method returns true if the report is in pending status, the reports regeneration type is add only.
     * Note, that there is no way of determining if this report is based on the same frequency date as is currently set in the award,
     * so this record MAY be generated on the next save.
     */
    public boolean getDisplayDeleteButton() {
        boolean retVal = false;
        //only pending reports
        if (StringUtils.equals("1", getStatusCode())) {
            //only should delete report tracking records that won't be automatically deleted with the regeneration routines.
            if (this.getFrequencyBase() != null && StringUtils.equals(ReportRegenerationType.ADDONLY.getDescription(), 
                    this.getFrequencyBase().getReportRegenerationType().getDescription())) {
                retVal = true;
            }
        }
        return retVal;
    }

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }
}
