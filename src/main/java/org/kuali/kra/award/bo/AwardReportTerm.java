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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class represents the AwardReportTerm business object 
 * 
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
    private Date dueDate;

    private Distribution distribution; 
    private Frequency frequency; 
    private FrequencyBase frequencyBase; 
    private Report report; 
    private ReportClass reportClass;
    private Award award; 
    private List<AwardReportTermRecipient> awardReportTermRecipients; 
    
    /**
     * 
     * Constructs a AwardReportTerm.java.
     */
    public AwardReportTerm() {
        awardReportTermRecipients = new ArrayList<AwardReportTermRecipient>();
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
        // do nothing
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
        // do nothing
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
    public Award getAward() {
        return award;
    }

    /**
     *
     * @param award
     */
    public void setAward(Award award) {
        this.award = award;
        if(award == null) {
            sequenceNumber = null;
            awardNumber = null;
        } else {
            sequenceNumber = award.getSequenceNumber();
            awardNumber = award.getAwardNumber();
        }
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
        hashMap.put("dueDate", getDueDate());
        return hashMap;
    }

    /**
     * 
     * 
     * @return
     */
    public List<AwardReportTermRecipient> getAwardReportTermRecipients() {
        return awardReportTermRecipients;
    }

    /**
     *
     * @param awardReportTermRecipients
     */
    public void setAwardReportTermRecipients(List<AwardReportTermRecipient> awardReportTermRecipients) {
        this.awardReportTermRecipients = awardReportTermRecipients;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((awardNumber == null) ? 0 : awardNumber.hashCode());
        result = PRIME * result + ((dueDate == null) ? 0 : dueDate.hashCode());
        result = PRIME * result + ((frequencyBaseCode == null) ? 0 : frequencyBaseCode.hashCode());
        result = PRIME * result + ((frequencyCode == null) ? 0 : frequencyCode.hashCode());
        result = PRIME * result + ((ospDistributionCode == null) ? 0 : ospDistributionCode.hashCode());
        result = PRIME * result + ((reportClassCode == null) ? 0 : reportClassCode.hashCode());
        result = PRIME * result + ((reportCode == null) ? 0 : reportCode.hashCode());
        result = PRIME * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }   
        if (obj == null){
            return false;
        }   
        if (!(obj instanceof AwardReportTerm)){
            return false;
        }   
        
        return equals((AwardReportTerm) obj);
    }
    
    /**
     * 
     * Convenience method to check equality of another AwardFandaRate
     * @param awardReportTerm
     * @return
     */
    public boolean equals(AwardReportTerm awardReportTerm) {
        if (awardNumber == null) {
            if (awardReportTerm.awardNumber != null){
                return false;
            }   
        }else if (!awardNumber.equals(awardReportTerm.awardNumber)){
            return false;
        }   
        if (dueDate == null) {
            if (awardReportTerm.dueDate != null){
                return false;
            }   
        }else if (!dueDate.equals(awardReportTerm.dueDate)){
            return false;
        }    
        if (frequencyBaseCode == null) {
            if (awardReportTerm.frequencyBaseCode != null){
                return false;
            }   
        }else if (!frequencyBaseCode.equals(awardReportTerm.frequencyBaseCode)){
            return false;
        }   
        if (frequencyCode == null) {
            if (awardReportTerm.frequencyCode != null){
                return false;
            }   
        }else if (!frequencyCode.equals(awardReportTerm.frequencyCode)){
            return false;
        }   
        if (ospDistributionCode == null) {
            if (awardReportTerm.ospDistributionCode != null){
                return false;
            }
                
        }else if (!ospDistributionCode.equals(awardReportTerm.ospDistributionCode)){
            return false;
        }   
        if (reportClassCode == null) {
            if (awardReportTerm.reportClassCode != null){
                return false;
            }   
        }else if (!reportClassCode.equals(awardReportTerm.reportClassCode)){
            return false;
        }   
        if (reportCode == null) {
            if (awardReportTerm.reportCode != null){
                return false;
            }   
        }else if (!reportCode.equals(awardReportTerm.reportCode)){
            return false;
        }   
        if (sequenceNumber == null) {
            if (awardReportTerm.sequenceNumber != null){
                return false;
            }   
        }else if (!sequenceNumber.equals(awardReportTerm.sequenceNumber)){
            return false;
        }            
        return true;
    }
    
}