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
package org.kuali.kra.proposaldevelopment.bo;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Every Proposal can have zero or more Abstracts attached to it.
 * An Abstract is composed of a unique type code and a textual string
 * known as the Abstract Details.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalAbstract extends KraPersistableBusinessObjectBase {

    ;

    /**
     * Each Abstract is associated with one and only one proposal
     * which is identified by its unique <code>proposalNumber</code>.
     */
    private String proposalNumber;

    /**
     * Identifies what type of abstract this is.
     */
    private String abstractTypeCode;

    /**
	 * The user-defined textual string for the abstract.
	 */
    private String abstractDetails;

    /**
	 * The AbstractType instance that the above <code>abstractTypeCode</code>  
	 * refers to.  It is stored here to make it easy for a JSP page to
	 * access the abstract type's description.
	 */
    private AbstractType abstractType;

    private Timestamp timestampDisplay;

    private String uploadUserDisplay;

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
        // When the abstract type code changes, the corresponding 
        // abstractType field must also be updated.  A refresh will 
        // cause the abstract type to be read from the database. By 
        // the magic of OJB, the data member is automatically updated. 
        if (this.abstractTypeCode == null || this.abstractTypeCode.equals("")) {
            abstractType = null;
        } else {
            this.refreshReferenceObject("abstractType");
        }
    }

    /**
	 * Gets the Details for this abstract.  
	 * @return the abstract's details.
	 */
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
    public AbstractType getAbstractType() {
        return abstractType;
    }

    /**
     * Sets the abstract's AbstractType.
     * @param abstractType the AbstractType.
     */
    public void setAbstractType(AbstractType abstractType) {
        this.abstractType = abstractType;
        this.abstractTypeCode = abstractType.getAbstractTypeCode();
    }

    public Timestamp getTimestampDisplay() {
        return timestampDisplay;
    }

    public void setTimestampDisplay(Timestamp timestampDisplay) {
        this.timestampDisplay = timestampDisplay;
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#beforeInsert()
     */
    @Override
    protected void prePersist() {
        super.prePersist();
        setUpdateDisplayFields();
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#beforeInsert()
     */
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
            setTimestampDisplay(((DateTimeService) KraServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
        }
    }

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
}
