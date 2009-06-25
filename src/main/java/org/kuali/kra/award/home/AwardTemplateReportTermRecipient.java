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
package org.kuali.kra.award.home;


import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Rolodex;

public class AwardTemplateReportTermRecipient extends KraPersistableBusinessObjectBase { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3067969440328090438L;
    private Integer templateReportTermRecipientId; 
	private Integer templateReportTermId; 
	private AwardTemplateReportTerm awardTemplateReportTerm;
	
    private String contactTypeCode; 
    private Integer rolodexId;
    private Integer numberOfCopies; 
    
    private ContactType contactType;
    private Rolodex rolodex; 

	
	public AwardTemplateReportTermRecipient() { 

	} 
	
	public Integer getTemplateReportTermRecipientId() {
		return templateReportTermRecipientId;
	}

	public void setTemplateReportTermRecipientId(Integer templateReportTermRecipientId) {
		this.templateReportTermRecipientId = templateReportTermRecipientId;
	}

	public Integer getTemplateReportTermId() {
		return templateReportTermId;
	}

	public void setTemplateReportTermId(Integer templateReportTermId) {
		this.templateReportTermId = templateReportTermId;
	}

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("templateReportTermRecipientId", getTemplateReportTermRecipientId());
		hashMap.put("templateReportTermId", getTemplateReportTermId());
		hashMap.put("contactTypeCode", getContactTypeCode());
		hashMap.put("rolodexId", getRolodexId());
		hashMap.put("numberOfCopies", getNumberOfCopies());
		return hashMap;
	}

    /**
     * Gets the awardTemplateReportTerm attribute. 
     * @return Returns the awardTemplateReportTerm.
     */
    public AwardTemplateReportTerm getAwardTemplateReportTerm() {
        return awardTemplateReportTerm;
    }

    /**
     * Sets the awardTemplateReportTerm attribute value.
     * @param awardTemplateReportTerm The awardTemplateReportTerm to set.
     */
    public void setAwardTemplateReportTerm(AwardTemplateReportTerm awardTemplateReportTerm) {
        this.awardTemplateReportTerm = awardTemplateReportTerm;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((awardTemplateReportTerm == null) ? 0 : awardTemplateReportTerm.hashCode());
        result = prime * result + ((templateReportTermId == null) ? 0 : templateReportTermId.hashCode());
        result = prime * result + ((templateReportTermRecipientId == null) ? 0 : templateReportTermRecipientId.hashCode());
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
        AwardTemplateReportTermRecipient other = (AwardTemplateReportTermRecipient) obj;
        if (awardTemplateReportTerm == null) {
            if (other.awardTemplateReportTerm != null)
                return false;
        }
        else if (!awardTemplateReportTerm.equals(other.awardTemplateReportTerm))
            return false;
        if (templateReportTermId == null) {
            if (other.templateReportTermId != null)
                return false;
        }
        else if (!templateReportTermId.equals(other.templateReportTermId))
            return false;
        if (templateReportTermRecipientId == null) {
            if (other.templateReportTermRecipientId != null)
                return false;
        }
        else if (!templateReportTermRecipientId.equals(other.templateReportTermRecipientId))
            return false;
        return true;
    }

    /**
     * Gets the contactTypeCode attribute. 
     * @return Returns the contactTypeCode.
     */
    public String getContactTypeCode() {
        return contactTypeCode;
    }

    /**
     * Sets the contactTypeCode attribute value.
     * @param contactTypeCode The contactTypeCode to set.
     */
    public void setContactTypeCode(String contactTypeCode) {
        this.contactTypeCode = contactTypeCode;
    }

    /**
     * Gets the rolodexId attribute. 
     * @return Returns the rolodexId.
     */
    public Integer getRolodexId() {
        return rolodexId;
    }

    /**
     * Sets the rolodexId attribute value.
     * @param rolodexId The rolodexId to set.
     */
    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    /**
     * Gets the numberOfCopies attribute. 
     * @return Returns the numberOfCopies.
     */
    public Integer getNumberOfCopies() {
        return numberOfCopies;
    }

    /**
     * Sets the numberOfCopies attribute value.
     * @param numberOfCopies The numberOfCopies to set.
     */
    public void setNumberOfCopies(Integer numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    /**
     * Gets the contactType attribute. 
     * @return Returns the contactType.
     */
    public ContactType getContactType() {
        return contactType;
    }

    /**
     * Sets the contactType attribute value.
     * @param contactType The contactType to set.
     */
    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    /**
     * Gets the rolodex attribute. 
     * @return Returns the rolodex.
     */
    public Rolodex getRolodex() {
        return rolodex;
    }

    /**
     * Sets the rolodex attribute value.
     * @param rolodex The rolodex to set.
     */
    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }
	
}