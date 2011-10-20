/*
 * Copyright 2005-2010 The Kuali Foundation
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

package org.kuali.kra.award.paymentreports.awardreports;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.award.AwardTemplateSyncScope;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncableProperty;
import org.kuali.kra.award.home.AwardSyncable;
import org.kuali.kra.award.home.AwardSyncableList;
import org.kuali.kra.award.home.AwardTemplateReportTermRecipient;
import org.kuali.kra.award.home.Distribution;
import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.award.paymentreports.FrequencyBase;
import org.kuali.kra.award.paymentreports.Report;
import org.kuali.kra.award.paymentreports.ReportClass;
import org.kuali.kra.award.paymentreports.awardreports.reporting.AwardReporting;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;


/**
 * 
 * This class represents the AwardReportTerm business object 
 * 
 */
public class AwardReportTerm extends AwardAssociate implements GenericAwardReportTerm { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3117988810554700250L;

    private Long awardReportTermId;
    private List<AwardReporting> awardReportings;
    private List<ReportTracking> reportTrackings;
    
    
    @AwardSyncableProperty(key=true) 
    @AwardSyncable( scopes = {AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT} ) 
    private String reportClassCode; 
    @AwardSyncableProperty(key=true)
    @AwardSyncable( scopes = {AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT} ) 
    private String reportCode; 
    @AwardSyncableProperty(key=true)
    @AwardSyncable( scopes = {AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT} ) 
    private String frequencyCode; 
    @AwardSyncableProperty(key=true)
    @AwardSyncable( scopes = {AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT} ) 
    private String frequencyBaseCode; 
    @AwardSyncableProperty
    @AwardSyncable( scopes = {AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT} ) 
    private String ospDistributionCode;
    @AwardSyncableProperty
    @AwardSyncable( scopes = {AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT} ) 
    private Date dueDate;

    private Distribution distribution; 
    private @AwardSyncable( scopes = {AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT} ) Frequency frequency; 
    private @AwardSyncable( scopes = {AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT} ) FrequencyBase frequencyBase; 
    private @AwardSyncable( scopes = {AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT} ) Report report; 
    private @AwardSyncable( scopes = {AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT} ) ReportClass reportClass;

    @AwardSyncableProperty
    @AwardSyncableList(parentPropertyName="awardReportTerm",syncClass=AwardReportTermRecipient.class,syncSourceClass=AwardTemplateReportTermRecipient.class,scopes={AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT}) 
    private List<AwardReportTermRecipient> awardReportTermRecipients;
    
    /**
     * 
     * Constructs a AwardReportTerm.java.
     */
    public AwardReportTerm() {
        awardReportTermRecipients = new ArrayList<AwardReportTermRecipient>();
        awardReportings = new ArrayList<AwardReporting>();
    } 
    
    /**
     * 
     * @return
     */
    public Long getAwardReportTermId() {
        return awardReportTermId;
    }

    /**
     * 
     * @param awardReportTermId
     */
    public void setAwardReportTermId(Long awardReportTermId) {
        this.awardReportTermId = awardReportTermId;
    }
    
    /**
     * 
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
        hashMap.put("reportClassCode", getReportClassCode());
        hashMap.put("reportCode", getReportCode());
        hashMap.put("frequencyCode", getFrequencyCode());
        hashMap.put("frequencyBaseCode", getFrequencyBaseCode());
        hashMap.put("ospDistributionCode", getOspDistributionCode());
        hashMap.put("dueDate", getDueDate());
        hashMap.put("awardReportTermId", getAwardReportTermId());        
        //hashMap.put("awardNumber", getAwardNumber());
        //hashMap.put("sequenceNumber", getSequenceNumber());
        return hashMap;
    }

    
    class ARTRComparator implements Comparator
    {    
        public int compare(Object artr1, Object artr2)
        {    
            try
            {
                String artr1Desc = ((AwardReportTermRecipient)artr1).getRolodex().getLastName();        
                String artr2Desc = ((AwardReportTermRecipient)artr2).getRolodex().getLastName();
                if (artr1Desc == null)
                {
                    artr1Desc = "";
                }
                if (artr2Desc == null)
                {
                    artr2Desc = "";
                }
                return artr1Desc.compareTo(artr2Desc);  
            }
            catch (Exception e)
            {
                return 0;
            }
        }
        
    }
    
    
    /**
     * 
     * 
     * @return
     */
    public List<AwardReportTermRecipient> getAwardReportTermRecipients() {
        Collections.sort(awardReportTermRecipients, new ARTRComparator());
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
        final int prime = 31;
        int result = super.hashCode();
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
        if (!super.equals(obj)){
            return false;
        }   
        if (!(obj instanceof AwardReportTerm)){
            return false;
        }   
        return true;
    }

    /**
     * Unlike <CODE>equals(Object)</CODE>, this method only compares the fields a user has to fill in in order to add
     * a new <CODE>AwardReportTerm</CODE>: Report Type, Frequency, Frequency Base, OSP File Copy, and Due Date.
     * @param otherReportTerm
     * @return
     */
    public boolean equalsInitialFields(GenericAwardReportTerm otherReportTerm) {
        boolean isEqual = true;

        if (otherReportTerm == null) {
            isEqual = false;
        }
        else if (!StringUtils.equals(getReportClassCode(), otherReportTerm.getReportClassCode())) {
            isEqual = false;
        } 
        else if (!StringUtils.equals(getReportCode(), otherReportTerm.getReportCode())) {
                isEqual = false;
        }
        else if (!StringUtils.equals(getFrequencyCode(), otherReportTerm.getFrequencyCode())) {
            isEqual = false;
        }
        else if (!StringUtils.equals(getFrequencyBaseCode(), otherReportTerm.getFrequencyBaseCode())) {
            isEqual = false;
        }
        else if (!StringUtils.equals(getOspDistributionCode(), otherReportTerm.getOspDistributionCode())) {
            isEqual = false;
        }
        else if (!ObjectUtils.equals(getDueDate(), otherReportTerm.getDueDate())) {
            isEqual = false;
        }
        
        return isEqual;
    }
    
    /**
     * Gets the awardReportings attribute. 
     * @return Returns the awardReportings.
     */
    public List<AwardReporting> getAwardReportings() {
        return awardReportings;
    }

    /**
     * Sets the awardReportings attribute value.
     * @param awardReportings The awardReportings to set.
     */
    public void setAwardReportings(List<AwardReporting> awardReportings) {
        this.awardReportings = awardReportings;
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#getSequenceOwner()
     */
   // public SequenceOwner getSequenceOwner() {
        //return getAward();
    //}

    /**
     * @see org.kuali.kra.SequenceAssociate#setSequenceOwner(org.kuali.kra.SequenceOwner)
     */
    //public void setSequenceOwner(SequenceOwner newlyVersionedOwner) {
       // setAward((Award) newlyVersionedOwner);
    //}

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.awardReportTermId = null;
    }

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

    public List<ReportTracking> getReportTrackings() {
        return reportTrackings;
    }

    public void setReportTrackings(List<ReportTracking> reportTrackings) {
        this.reportTrackings = reportTrackings;
    }   
}
