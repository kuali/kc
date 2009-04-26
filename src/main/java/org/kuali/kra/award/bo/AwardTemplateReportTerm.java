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

import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.rice.kns.util.TypedArrayList;

public class AwardTemplateReportTerm extends AwardReportTermBase { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -2410943921568581512L;
    private Integer templateReportTermId; 
	private Integer templateCode; 
	private List<AwardTemplateReportTermRecipient> awardTemplateReportTermRecipients;
	
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
	
}