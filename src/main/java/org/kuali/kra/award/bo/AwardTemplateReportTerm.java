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
import java.util.List;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.kns.util.TypedArrayList;

public class AwardTemplateReportTerm extends KraPersistableBusinessObjectBase{ 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -2410943921568581512L;
    private Integer templateReportTermId; 
	private Integer templateCode; 
	private List<AwardTemplateReportTermRecipient> awardTemplateReportTermRecipients;
	
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
    private List awardReportTermRecipients;

	
	public AwardTemplateReportTerm() { 
	    awardTemplateReportTermRecipients = new TypedArrayList(AwardTemplateReportTermRecipient.class);
	} 
	
	public Integer getTemplateReportTermId() {
		return templateReportTermId;
	}

	public void setTemplateReportTermId(Integer templateReportTermId) {
		this.templateReportTermId = templateReportTermId;
	}

	public Integer getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(Integer templateCode) {
		this.templateCode = templateCode;
	}


	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("templateReportTermId", getTemplateReportTermId());
		hashMap.put("templateCode", getTemplateCode());
		hashMap.put("reportClassCode", getReportClassCode());
		hashMap.put("reportCode", getReportCode());
		hashMap.put("frequencyCode", getFrequencyCode());
		hashMap.put("frequencyBaseCode", getFrequencyBaseCode());
		hashMap.put("ospDistributionCode", getOspDistributionCode());
		hashMap.put("dueDate", getDueDate());
		return hashMap;
	}

    /**
     * Gets the awardTemplateReportTermRecipients attribute. 
     * @return Returns the awardTemplateReportTermRecipients.
     */
    public List<AwardTemplateReportTermRecipient> getAwardTemplateReportTermRecipients() {
        return awardTemplateReportTermRecipients;
    }

    /**
     * Sets the awardTemplateReportTermRecipients attribute value.
     * @param awardTemplateReportTermRecipients The awardTemplateReportTermRecipients to set.
     */
    public void setAwardTemplateReportTermRecipients(List<AwardTemplateReportTermRecipient> awardTemplateReportTermRecipients) {
        this.awardTemplateReportTermRecipients = awardTemplateReportTermRecipients;
        this.awardReportTermRecipients = awardTemplateReportTermRecipients;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((awardTemplateReportTermRecipients == null) ? 0 : awardTemplateReportTermRecipients.hashCode());
        result = prime * result + ((templateCode == null) ? 0 : templateCode.hashCode());
        result = prime * result + ((templateReportTermId == null) ? 0 : templateReportTermId.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AwardTemplateReportTerm other = (AwardTemplateReportTerm) obj;
        if (awardTemplateReportTermRecipients == null) {
            if (other.awardTemplateReportTermRecipients != null)
                return false;
        }
        else if (!awardTemplateReportTermRecipients.equals(other.awardTemplateReportTermRecipients))
            return false;
        if (templateCode == null) {
            if (other.templateCode != null)
                return false;
        }
        else if (!templateCode.equals(other.templateCode))
            return false;
        if (templateReportTermId == null) {
            if (other.templateReportTermId != null)
                return false;
        }
        else if (!templateReportTermId.equals(other.templateReportTermId))
            return false;
        return true;
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

    /**
     * Gets the awardReportTermRecipients attribute. 
     * @return Returns the awardReportTermRecipients.
     */
    public List getAwardReportTermRecipients() {
        return getAwardTemplateReportTermRecipients();
    }
	
}