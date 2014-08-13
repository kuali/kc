/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.abstrct;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.kuali.coeus.propdev.api.abstrct.ProposalAbstractContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Every Proposal can have zero or more Abstracts attached to it.
 * An Abstract is composed of a unique type code and a textual string
 * known as the Abstract Details.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Entity
@Table(name = "EPS_PROP_ABSTRACT")
@IdClass(ProposalAbstract.ProposalAbstractId.class)
public class ProposalAbstract extends KcPersistableBusinessObjectBase implements ProposalAbstractContract {

    /**
     * Each Abstract is associated with one and only one proposal
     * which is identified by its unique <code>proposalNumber</code>.
     */
    @Id
    @Column(name = "PROPOSAL_NUMBER")
    private String proposalNumber;

    /**
     * Identifies what type of abstract this is.
     */
    @Id
    @Column(name = "ABSTRACT_TYPE_CODE")
    private String abstractTypeCode;

    /**
	 * The user-defined textual string for the abstract.
	 */
    @Column(name = "ABSTRACT_DETAILS")
    @Lob
    private String abstractDetails;

    /**
	 * The AbstractType instance that the above <code>abstractTypeCode</code>  
	 * refers to.  It is stored here to make it easy for a JSP page to
	 * access the abstract type's description.
	 */
    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ABSTRACT_TYPE_CODE", referencedColumnName = "ABSTRACT_TYPE_CODE", insertable = false, updatable = false)
    private AbstractType abstractType;

    @Column(name = "TIMESTAMP_DISPLAY")
    private Timestamp timestampDisplay;

    @Column(name = "USER_DISPLAY")
    private String uploadUserDisplay;

    @Transient
    private String uploadUserFullName;

    /**
	 * Constructs a ProposalAbstract.
	 */
    public ProposalAbstract() {
        super();
        this.proposalNumber = null;
        this.setAbstractTypeCode("");
        this.setAbstractDetails("");
    }

    /**
	 * Gets the Proposal Number.
	 * @return the proposal number this abstract is associated with; 
	 *         null if not associated with any proposal.
	 */
    @Override
    public String getProposalNumber() {
        return proposalNumber;
    }

    /**
	 * Sets the proposal number that this abstract is associated with.
	 * @param proposalNumber a Proposal's unique number; may be null.
	 */
    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    /**
     * Gets the Abstract Type Code for this abstract.
     * @return the Abstract Type Code.
     */
    public String getAbstractTypeCode() {
        return abstractTypeCode;
    }

    /**
     * Sets the Abstract Type Code for this abstract.
     * @param abstractTypeCode one of the valid abstract type codes.  The database
     *        can be consulted to find the valid type codes.
     */
    public void setAbstractTypeCode(String abstractTypeCode) {
        this.abstractTypeCode = abstractTypeCode;
    }

    /**
	 * Gets the Details for this abstract.  
	 * @return the abstract's details.
	 */
    @Override
    public String getAbstractDetails() {
        return abstractDetails;
    }

    /**
	 * Sets the abstract's details.  Note that the details
	 * are not allowed to be null.  Null is automatically
	 * converted to an empty string.
	 * 
	 * @param abstractDetails a user-defined textual string.
	 */
    public void setAbstractDetails(String abstractDetails) {
        if (!StringUtils.equals(this.abstractDetails, abstractDetails)) {
            this.timestampDisplay = null;
            this.uploadUserDisplay = null;
        }
        if (abstractDetails == null) {
            this.abstractDetails = "";
        } else {
            this.abstractDetails = abstractDetails;
        }
    }

    /**
     * Gets the AbstractType for this abstract.
     * @return the abstract's AbstractType.
     */
    @Override
    public AbstractType getAbstractType() {
        return abstractType;
    }

    /**
     * Sets the abstract's AbstractType.
     * @param abstractType the AbstractType.
     */
    public void setAbstractType(AbstractType abstractType) {
        this.abstractType = abstractType;
        this.abstractTypeCode = abstractType != null ? abstractType.getCode() : null;
    }

    @Override
    public Timestamp getTimestampDisplay() {
        return timestampDisplay;
    }

    public void setTimestampDisplay(Timestamp timestampDisplay) {
        this.timestampDisplay = timestampDisplay;
    }

    @Override
    protected void prePersist() {
        super.prePersist();
        setUpdateDisplayFields();
    }

    @Override
    protected void preUpdate() {
        super.preUpdate();
        setUpdateDisplayFields();
    }

    /**
     * Set timestampDisplay and userDisplay prior to persistence
     */
    private void setUpdateDisplayFields() {
        if (uploadUserDisplay == null || timestampDisplay == null) {
            String updateUser = GlobalVariables.getUserSession().getPrincipalName();
            // Since the UPDATE_USER column is only VACHAR(60), we need to truncate this string if it's longer than 60 characters  
            if (updateUser.length() > 60) {
                updateUser = updateUser.substring(0, 60);
            }
            setUploadUserDisplay(updateUser);
            setTimestampDisplay(((DateTimeService) KcServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
        }
    }

    @Override
    public String getUploadUserDisplay() {
        return uploadUserDisplay;
    }

    public void setUploadUserDisplay(String uploadUserDisplay) {
        this.uploadUserDisplay = uploadUserDisplay;
    }

    public String getUploadUserFullName() {
        return uploadUserFullName;
    }

    public void setUploadUserFullName(String uploadUserFullName) {
        this.uploadUserFullName = uploadUserFullName;
    }

    public static final class ProposalAbstractId implements Serializable, Comparable<ProposalAbstractId> {

        private String abstractTypeCode;

        private String proposalNumber;

        public String getAbstractTypeCode() {
            return this.abstractTypeCode;
        }

        public void setAbstractTypeCode(String abstractTypeCode) {
            this.abstractTypeCode = abstractTypeCode;
        }

        public String getProposalNumber() {
            return this.proposalNumber;
        }

        public void setProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("abstractTypeCode", this.abstractTypeCode).append("proposalNumber", this.proposalNumber).toString();
        }

        @Override
        public boolean equals(Object other) {
            if (other == null)
                return false;
            if (other == this)
                return true;
            if (other.getClass() != this.getClass())
                return false;
            final ProposalAbstractId rhs = (ProposalAbstractId) other;
            return new EqualsBuilder().append(this.abstractTypeCode, rhs.abstractTypeCode).append(this.proposalNumber, rhs.proposalNumber).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(this.abstractTypeCode).append(this.proposalNumber).toHashCode();
        }

        @Override
        public int compareTo(ProposalAbstractId other) {
            return new CompareToBuilder().append(this.abstractTypeCode, other.abstractTypeCode).append(this.proposalNumber, other.proposalNumber).toComparison();
        }
    }
}
