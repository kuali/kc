/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.kra.award.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Rolodex;

/**
 * 
 * This class represents the AwardReportTerm business object and is mapped
 * to AWARD_REPORT_TERMS table
 */
public class AwardReportTerm extends KraPersistableBusinessObjectBase { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3117988810554700250L;
    private Integer awardReportTermId; 
    private Integer awardId; 
    private String awardNumber; 
    private Integer sequenceNumber; 
    private String reportClassCode; 
    private String reportCode; 
    private String frequencyCode; 
    private String frequencyBaseCode; 
    private String ospDistributionCode; 
    private String contactTypeCode; 
    private Integer rolodexId; 
    private Date dueDate; 
    private Integer numberOfCopies; 
    
    private ContactType contactType; 
    private Distribution distribution; 
    private Frequency frequency; 
    private FrequencyBase frequencyBase; 
    private Report report; 
    private ReportClass reportClass; 
    private Rolodex rolodex; 
    private Award award; 
    
    /**
     * 
     * Constructs a AwardReportTerm.java.
     */
    public AwardReportTerm() { 

    } 
    
    /**
     * 
     * @return
     */
    public Integer getAwardReportTermId() {
        return awardReportTermId;
    }

    /**
     * 
     * @param awardReportTermId
     */
    public void setAwardReportTermId(Integer awardReportTermId) {
        this.awardReportTermId = awardReportTermId;
    }

    /**
     * 
     * @return
     */
    public Integer getAwardId() {
        return awardId;
    }

    /**
     * 
     * @param awardId
     */
    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    /**
     * 
     * @return
     */
    public String getAwardNumber() {
        return awardNumber;
    }

    /**
     * 
     * @param awardNumber
     */
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    /**
     * 
     * @return
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * 
     * @param sequenceNumber
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     *
     * @return
     */
    public String getReportClassCode() {
        return reportClassCode;
    }

    /**
     *
     * @param reportClassCode
     */
    public void setReportClassCode(String reportClassCode) {
        this.reportClassCode = reportClassCode;
    }

    /**
     *
     * @return
     */
    public String getReportCode() {
        return reportCode;
    }

    /**
     *
     * @param reportCode
     */
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    /**
     *
     * @return
     */
    public String getFrequencyCode() {
        return frequencyCode;
    }

    /**
     *
     * @param frequencyCode
     */
    public void setFrequencyCode(String frequencyCode) {
        this.frequencyCode = frequencyCode;
    }

    /**
     *
     * @return
     */
    public String getFrequencyBaseCode() {
        return frequencyBaseCode;
    }

    /**
     *
     * @param frequencyBaseCode
     */
    public void setFrequencyBaseCode(String frequencyBaseCode) {
        this.frequencyBaseCode = frequencyBaseCode;
    }

    /**
     *
     * @return
     */
    public String getOspDistributionCode() {
        return ospDistributionCode;
    }

    /**
     *
     * @param ospDistributionCode
     */
    public void setOspDistributionCode(String ospDistributionCode) {
        this.ospDistributionCode = ospDistributionCode;
    }

    /**
     *
     * @return
     */
    public String getContactTypeCode() {
        return contactTypeCode;
    }

    /**
     *
     * @param contactTypeCode
     */
    public void setContactTypeCode(String contactTypeCode) {
        this.contactTypeCode = contactTypeCode;
    }

    /**
     *
     * @return
     */
    public Integer getRolodexId() {
        return rolodexId;
    }

    /**
     *
     * @param rolodexId
     */
    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    /**
     *
     * @return
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     *
     * @param dueDate
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     *
     * @return
     */
    public Integer getNumberOfCopies() {
        return numberOfCopies;
    }

    /**
     *
     * @param numberOfCopies
     */
    public void setNumberOfCopies(Integer numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
    
    /**
     *
     * @return
     */
    public Award getAward() {
        return award;
    }

    /**
     *
     * @param award
     */
    public void setAward(Award award) {
        this.award = award;
    }    

    /**
     *
     * @return
     */
    public ContactType getContactType() {
        return contactType;
    }

    /**
     *
     * @param contactType
     */
    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    /**
     *
     * @return
     */
    public Distribution getDistribution() {
        return distribution;
    }

    /**
     *
     * @param distribution
     */
    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    /**
     *
     * @return
     */
    public Frequency getFrequency() {
        return frequency;
    }

    /**
     *
     * @param frequency
     */
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    /**
     *
     * @return
     */
    public FrequencyBase getFrequencyBase() {
        return frequencyBase;
    }

    /**
     *
     * @param frequencyBase
     */
    public void setFrequencyBase(FrequencyBase frequencyBase) {
        this.frequencyBase = frequencyBase;
    }

    /**
     *
     * @return
     */
    public Report getReport() {
        return report;
    }

    /**
     *
     * @param report
     */
    public void setReport(Report report) {
        this.report = report;
    }

    /**
     *
     * @return
     */
    public ReportClass getReportClass() {
        return reportClass;
    }

    /**
     *
     * @param reportClass
     */
    public void setReportClass(ReportClass reportClass) {
        this.reportClass = reportClass;
    }

    /**
     *
     * @return
     */
    public Rolodex getRolodex() {
        return rolodex;
    }

    /**
     *
     * @param rolodex
     */
    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }
    
    /**
     * 
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("awardReportTermId", getAwardReportTermId());
        hashMap.put("awardId", getAwardId());
        hashMap.put("awardNumber", getAwardNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        hashMap.put("reportClassCode", getReportClassCode());
        hashMap.put("reportCode", getReportCode());
        hashMap.put("frequencyCode", getFrequencyCode());
        hashMap.put("frequencyBaseCode", getFrequencyBaseCode());
        hashMap.put("ospDistributionCode", getOspDistributionCode());
        hashMap.put("contactTypeCode", getContactTypeCode());
        hashMap.put("rolodexId", getRolodexId());
        hashMap.put("dueDate", getDueDate());
        hashMap.put("numberOfCopies", getNumberOfCopies());
        return hashMap;
    }
    
}