/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.award.home;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;

public class AwardTemplateReportTermRecipient extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = -3067969440328090438L;

    private Integer templateReportTermRecipientId;

    private Integer templateReportTermId;

    private AwardTemplateReportTerm awardTemplateReportTerm;

    private String contactTypeCode;

    private Integer rolodexId;

    private Integer numberOfCopies;

    private ContactType contactType;

    private Rolodex rolodex;

    private String contactTypeCodeAndRolodexId;

    private String rolodexNameOrganization;

    public AwardTemplateReportTermRecipient() {
    }

    public Integer getTemplateReportTermRecipientId() {
        return templateReportTermRecipientId;
    }

    public void setTemplateReportTermRecipientId(Integer templateReportTermRecipientId) {
        this.templateReportTermRecipientId = templateReportTermRecipientId;
    }

    public String getRolodexNameOrganization() {
        if (StringUtils.isEmpty(rolodexNameOrganization)) {
            this.refreshReferenceObject("rolodex");
            if (this.getRolodex() == null) {
                rolodexNameOrganization = "";
            } else {
                rolodexNameOrganization = this.getRolodex().getFullName() + "/" + this.getRolodex().getOrganization();
            }
        }
        return rolodexNameOrganization;
    }

    public void setRolodexNameOrganization(String rolodexNameOrganization) {
        this.rolodexNameOrganization = rolodexNameOrganization;
    }

    public String getContactTypeCodeAndRolodexId() {
        if (contactTypeCodeAndRolodexId == null) {
            this.refreshReferenceObject("contactType");
            if (this.getContactType() == null || this.getRolodexId() == null) {
                contactTypeCodeAndRolodexId = "";
            } else {
                String aString = this.getContactType().getContactTypeCode();
                if (aString.equals("-1")) {
                    contactTypeCodeAndRolodexId = "";
                } else {
                    contactTypeCodeAndRolodexId = this.getContactType().getContactTypeCode() + Constants.AWARD_TEMP_RECPNT_CONTACT_TYPE_CODE_ROLODEX_ID_SEPARATOR + this.getRolodexId().toString();
                }
            }
        }
        return contactTypeCodeAndRolodexId;
    }

    public void setcontactTypeCodeAndRolodexId(String contactTypeCodeAndRolodexId) {
        this.contactTypeCodeAndRolodexId = contactTypeCodeAndRolodexId;
    }

    public Integer getTemplateReportTermId() {
        return templateReportTermId;
    }

    public void setTemplateReportTermId(Integer templateReportTermId) {
        this.templateReportTermId = templateReportTermId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((awardTemplateReportTerm == null) ? 0 : awardTemplateReportTerm.hashCode());
        result = prime * result + ((templateReportTermId == null) ? 0 : templateReportTermId.hashCode());
        result = prime * result + ((templateReportTermRecipientId == null) ? 0 : templateReportTermRecipientId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        AwardTemplateReportTermRecipient other = (AwardTemplateReportTermRecipient) obj;
        if (awardTemplateReportTerm == null) {
            if (other.awardTemplateReportTerm != null) return false;
        } else if (!awardTemplateReportTerm.equals(other.awardTemplateReportTerm)) return false;
        if (templateReportTermId == null) {
            if (other.templateReportTermId != null) return false;
        } else if (!templateReportTermId.equals(other.templateReportTermId)) return false;
        if (templateReportTermRecipientId == null) {
            if (other.templateReportTermRecipientId != null) return false;
        } else if (!templateReportTermRecipientId.equals(other.templateReportTermRecipientId)) return false;
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
