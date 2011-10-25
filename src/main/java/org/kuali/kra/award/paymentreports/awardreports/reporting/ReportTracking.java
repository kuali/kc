/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.paymentreports.awardreports.reporting;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Distribution;
import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.award.paymentreports.FrequencyBase;
import org.kuali.kra.award.paymentreports.Report;
import org.kuali.kra.award.paymentreports.ReportClass;
import org.kuali.kra.award.paymentreports.ReportStatus;
import org.kuali.kra.bo.Contactable;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class...
 */
public class ReportTracking extends KraPersistableBusinessObjectBase implements Comparable<ReportTracking> {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7660491024739306314L;
    private Long awardReportTrackingId;
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
    private Date lastUpdateDate;
    
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
    
    private transient KcPersonService kcPersonService;
    private transient BusinessObjectService businessObjectService;

    public Long getAwardReportTrackingId() {
        return awardReportTrackingId;
    }

    public void setAwardReportTrackingId(Long awardReportTrackingId) {
        this.awardReportTrackingId = awardReportTrackingId;
    }

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

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap result = new LinkedHashMap();
        result.put("awardReportTrackingId", getAwardReportTrackingId());
        result.put("awardNumber", getAwardNumber());
        result.put("reportClassCode", getReportClassCode());
        result.put("reportCode", getReportCode());
        result.put("frequencyCode", getFrequencyCode());
        result.put("frequencyBaseCode", getFrequencyBaseCode());
        return result;
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
            kcPersonService = KraServiceLocator.getService(KcPersonService.class);
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
            piRolodex = (NonOrganizationalRolodex) getBusinessObjectService().findBySinglePrimaryKey(NonOrganizationalRolodex.class, getPiRolodexId());
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
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
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

    /**
     * This method implements comparable based on the due date;
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(ReportTracking o) {
        return o == null || o.getDueDate() == null || this.getDueDate() == null ? 0 
                : this.getDueDate().compareTo(o.getDueDate());
    }
}
