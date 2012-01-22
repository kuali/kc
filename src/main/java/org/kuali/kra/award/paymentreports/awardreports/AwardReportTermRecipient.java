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

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.award.AwardTemplateSyncScope;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncableProperty;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardSyncable;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Rolodex;

/**
 * 
 * This class represents the AwardReportTermRecipient business object
 * 
 */
public class AwardReportTermRecipient extends KraPersistableBusinessObjectBase implements SequenceAssociate<Award> {

    /**
     * Comment for <code>serialVersionUID</code>
     */
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

    /**
     * 
     * Constructs a AwardReportTerm.java.
     */
    public AwardReportTermRecipient() {
        numberOfCopies = 1;
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
     * @return
     */
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

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
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

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contactId == null) ? 0 : contactId.hashCode());
        result = prime * result + ((rolodexId == null) ? 0 : rolodexId.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
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

    /**
     * This method...
     * @param other
     * @return
     */
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
