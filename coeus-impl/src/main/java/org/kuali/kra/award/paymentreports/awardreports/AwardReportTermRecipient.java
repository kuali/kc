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
package org.kuali.kra.award.paymentreports.awardreports;

import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.AwardTemplateSyncScope;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncableProperty;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardSyncable;
import org.kuali.kra.award.home.ContactType;

/**
 * 
 * This class represents the AwardReportTermRecipient business object
 * 
 */
public class AwardReportTermRecipient extends KcPersistableBusinessObjectBase implements SequenceAssociate<Award> {


    private static final long serialVersionUID = -3941499915900100395L;

    private Long awardReportTermRecipientId;

    private Long awardReportTermId;

    private Long contactId;

    @AwardSyncableProperty(key = true)
    @AwardSyncable(scopes = { AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT })
    private String contactTypeCode;

    @AwardSyncableProperty(key = true)
    @AwardSyncable(scopes = { AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT })
    private Integer rolodexId;

    @AwardSyncableProperty
    @AwardSyncable(scopes = { AwardTemplateSyncScope.CONTAINING_CLASS_INHERIT })
    private Integer numberOfCopies;

    private ContactType contactType;

    private Rolodex rolodex;

    @AwardSyncableProperty(parent = true, parentProperty = "awardReportTermRecipients")
    private AwardReportTerm awardReportTerm;


    public AwardReportTermRecipient() {
        numberOfCopies = 1;
    }


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


    public Long getAwardReportTermRecipientId() {
        return awardReportTermRecipientId;
    }

    /**
     *
     * @param awardReportTermRecipientId
     */
    public void setAwardReportTermRecipientId(Long awardReportTermRecipientId) {
        this.awardReportTermRecipientId = awardReportTermRecipientId;
    }


    public AwardReportTerm getAwardReportTerm() {
        return awardReportTerm;
    }

    /**
     *
     * @param awardReportTerm
     */
    public void setAwardReportTerm(AwardReportTerm awardReportTerm) {
        this.awardReportTerm = awardReportTerm;
    }


    public Long getContactId() {
        return contactId;
    }

    /**
     *
     * @param contactId
     */
    public void setContactId(Long contactId) {
        this.contactId = contactId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contactId == null) ? 0 : contactId.hashCode());
        result = prime * result + ((rolodexId == null) ? 0 : rolodexId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AwardReportTermRecipient)) {
            return false;
        }
        return equals((AwardReportTermRecipient) obj);
    }

    private boolean equals(AwardReportTermRecipient other) {
        if (contactTypeCode == null) {
            if (other.contactTypeCode != null) {
                return false;
            }
        } else if (!contactTypeCode.equals(other.contactTypeCode)) {
            return false;
        }
        if (rolodexId == null) {
            if (other.rolodexId != null) {
                return false;
            }
        } else if (!rolodexId.equals(other.rolodexId)) {
            return false;
        }
        return true;
    }

    public Award getSequenceOwner() {
        return getAwardReportTerm() != null ? getAwardReportTerm().getAward() : null;
    }

    public void setSequenceOwner(Award newlyVersionedOwner) {
        if (getAwardReportTerm() != null) {
            getAwardReportTerm().setAward(newlyVersionedOwner);
        }
    }

    public Integer getSequenceNumber() {
        return getAwardReportTerm() != null ? getAwardReportTerm().getSequenceNumber() : 0;
    }

    public void resetPersistenceState() {
        this.awardReportTermRecipientId = null;
    }
}
