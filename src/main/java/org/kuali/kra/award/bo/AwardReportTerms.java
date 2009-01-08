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

public class AwardReportTerms extends KraPersistableBusinessObjectBase { 
    
    private Integer awardReportTermsId; 
    private Integer awardId; 
    private String awardNumber; 
    private Integer sequenceNumber; 
    private Integer reportClassCode; 
    private Integer reportCode; 
    private Integer frequencyCode; 
    private Integer frequencyBaseCode; 
    private Integer ospDistributionCode; 
    private Integer contactTypeCode; 
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
    
    public AwardReportTerms() { 

    } 
    
    public Integer getAwardReportTermsId() {
        return awardReportTermsId;
    }

    public void setAwardReportTermsId(Integer awardReportTermsId) {
        this.awardReportTermsId = awardReportTermsId;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getReportClassCode() {
        return reportClassCode;
    }

    public void setReportClassCode(Integer reportClassCode) {
        this.reportClassCode = reportClassCode;
    }

    public Integer getReportCode() {
        return reportCode;
    }

    public void setReportCode(Integer reportCode) {
        this.reportCode = reportCode;
    }

    public Integer getFrequencyCode() {
        return frequencyCode;
    }

    public void setFrequencyCode(Integer frequencyCode) {
        this.frequencyCode = frequencyCode;
    }

    public Integer getFrequencyBaseCode() {
        return frequencyBaseCode;
    }

    public void setFrequencyBaseCode(Integer frequencyBaseCode) {
        this.frequencyBaseCode = frequencyBaseCode;
    }

    public Integer getOspDistributionCode() {
        return ospDistributionCode;
    }

    public void setOspDistributionCode(Integer ospDistributionCode) {
        this.ospDistributionCode = ospDistributionCode;
    }

    public Integer getContactTypeCode() {
        return contactTypeCode;
    }

    public void setContactTypeCode(Integer contactTypeCode) {
        this.contactTypeCode = contactTypeCode;
    }

    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(Integer numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    /*public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
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

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public ReportClass getReportClass() {
        return reportClass;
    }

    public void setReportClass(ReportClass reportClass) {
        this.reportClass = reportClass;
    }

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }*/

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("awardReportTermsId", getAwardReportTermsId());
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

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
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

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public ReportClass getReportClass() {
        return reportClass;
    }

    public void setReportClass(ReportClass reportClass) {
        this.reportClass = reportClass;
    }

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }
    
}