/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.bo;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * This class represents base class for AwardReportTerm
 */
@SuppressWarnings("serial")
public abstract class AwardReportTermBase extends KraPersistableBusinessObjectBase{

    
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
    private List<? extends AwardReportTermRecipientBase> awardReportTermRecipients;
    /**
     * Gets the reportClassCode attribute. 
     * @return Returns the reportClassCode.
     */
    public String getReportClassCode() {
        return reportClassCode;
    }
    /**
     * Sets the reportClassCode attribute value.
     * @param reportClassCode The reportClassCode to set.
     */
    public void setReportClassCode(String reportClassCode) {
        this.reportClassCode = reportClassCode;
    }
    /**
     * Gets the reportCode attribute. 
     * @return Returns the reportCode.
     */
    public String getReportCode() {
        return reportCode;
    }
    /**
     * Sets the reportCode attribute value.
     * @param reportCode The reportCode to set.
     */
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }
    /**
     * Gets the frequencyCode attribute. 
     * @return Returns the frequencyCode.
     */
    public String getFrequencyCode() {
        return frequencyCode;
    }
    /**
     * Sets the frequencyCode attribute value.
     * @param frequencyCode The frequencyCode to set.
     */
    public void setFrequencyCode(String frequencyCode) {
        this.frequencyCode = frequencyCode;
    }
    /**
     * Gets the frequencyBaseCode attribute. 
     * @return Returns the frequencyBaseCode.
     */
    public String getFrequencyBaseCode() {
        return frequencyBaseCode;
    }
    /**
     * Sets the frequencyBaseCode attribute value.
     * @param frequencyBaseCode The frequencyBaseCode to set.
     */
    public void setFrequencyBaseCode(String frequencyBaseCode) {
        this.frequencyBaseCode = frequencyBaseCode;
    }
    /**
     * Gets the ospDistributionCode attribute. 
     * @return Returns the ospDistributionCode.
     */
    public String getOspDistributionCode() {
        return ospDistributionCode;
    }
    /**
     * Sets the ospDistributionCode attribute value.
     * @param ospDistributionCode The ospDistributionCode to set.
     */
    public void setOspDistributionCode(String ospDistributionCode) {
        this.ospDistributionCode = ospDistributionCode;
    }
    /**
     * Gets the dueDate attribute. 
     * @return Returns the dueDate.
     */
    public Date getDueDate() {
        return dueDate;
    }
    /**
     * Sets the dueDate attribute value.
     * @param dueDate The dueDate to set.
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    /**
     * Gets the distribution attribute. 
     * @return Returns the distribution.
     */
    public Distribution getDistribution() {
        return distribution;
    }
    /**
     * Sets the distribution attribute value.
     * @param distribution The distribution to set.
     */
    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }
    /**
     * Gets the frequency attribute. 
     * @return Returns the frequency.
     */
    public Frequency getFrequency() {
        return frequency;
    }
    /**
     * Sets the frequency attribute value.
     * @param frequency The frequency to set.
     */
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }
    /**
     * Gets the frequencyBase attribute. 
     * @return Returns the frequencyBase.
     */
    public FrequencyBase getFrequencyBase() {
        return frequencyBase;
    }
    /**
     * Sets the frequencyBase attribute value.
     * @param frequencyBase The frequencyBase to set.
     */
    public void setFrequencyBase(FrequencyBase frequencyBase) {
        this.frequencyBase = frequencyBase;
    }
    /**
     * Gets the report attribute. 
     * @return Returns the report.
     */
    public Report getReport() {
        return report;
    }
    /**
     * Sets the report attribute value.
     * @param report The report to set.
     */
    public void setReport(Report report) {
        this.report = report;
    }
    /**
     * Gets the reportClass attribute. 
     * @return Returns the reportClass.
     */
    public ReportClass getReportClass() {
        return reportClass;
    }
    /**
     * Sets the reportClass attribute value.
     * @param reportClass The reportClass to set.
     */
    public void setReportClass(ReportClass reportClass) {
        this.reportClass = reportClass;
    }
    /**
     * Gets the awardReportTermRecipients attribute. 
     * @return Returns the awardReportTermRecipients.
     */
    public List<? extends AwardReportTermRecipientBase> getAwardReportTermRecipients() {
        return awardReportTermRecipients;
    }
    /**
     * Sets the awardReportTermRecipients attribute value.
     * @param awardReportTermRecipients The awardReportTermRecipients to set.
     */
    public void setAwardReportTermRecipients(List<AwardReportTermRecipient> awardReportTermRecipients) {
        this.awardReportTermRecipients = awardReportTermRecipients;
    } 
    @SuppressWarnings("unchecked")
    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("reportClassCode", getReportClassCode());
        hashMap.put("reportCode", getReportCode());
        hashMap.put("frequencyCode", getFrequencyCode());
        hashMap.put("frequencyBaseCode", getFrequencyBaseCode());
        hashMap.put("ospDistributionCode", getOspDistributionCode());
        hashMap.put("dueDate", getDueDate());
        return hashMap;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((dueDate == null) ? 0 : dueDate.hashCode());
        result = PRIME * result + ((frequencyBaseCode == null) ? 0 : frequencyBaseCode.hashCode());
        result = PRIME * result + ((frequencyCode == null) ? 0 : frequencyCode.hashCode());
        result = PRIME * result + ((ospDistributionCode == null) ? 0 : ospDistributionCode.hashCode());
        result = PRIME * result + ((reportClassCode == null) ? 0 : reportClassCode.hashCode());
        result = PRIME * result + ((reportCode == null) ? 0 : reportCode.hashCode());
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
        if (!(obj instanceof AwardReportTermBase)){
            return false;
        }            
        final AwardReportTermBase other = (AwardReportTermBase) obj;
        return equals(other);
    }
    /**
     * This method...
     * @param other
     * @return
     */
    private boolean equals(final AwardReportTermBase other) {
        if (dueDate == null) {
            if (other.dueDate != null){
                return false;
            }                
        }else if (!dueDate.equals(other.dueDate)){
            return false;
        }   
        if (frequencyBaseCode == null) {
            if (other.frequencyBaseCode != null){
                return false;
            }   
        }else if (!frequencyBaseCode.equals(other.frequencyBaseCode)){
            return false;
        }   
        if (frequencyCode == null) {
            if (other.frequencyCode != null){
                return false;
            }                
        }else if (!frequencyCode.equals(other.frequencyCode)){
            return false;
        }            
        if (ospDistributionCode == null) {
            if (other.ospDistributionCode != null){
                return false;
            }                
        }else if (!ospDistributionCode.equals(other.ospDistributionCode)){
            return false;
        }
            
        if (reportClassCode == null) {
            if (other.reportClassCode != null){
                return false;
            }   
        }else if (!reportClassCode.equals(other.reportClassCode)){
            return false;
        }   
        if (reportCode == null) {
            if (other.reportCode != null){
                return false;
            }   
        }else if (!reportCode.equals(other.reportCode)){
            return false;
        }   
        return true;
    }
    
    
}
