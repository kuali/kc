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
package org.kuali.kra.proposaldevelopment.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;

/**
 * Represents the Proposal Person Role <code>{@link org.kuali.rice.krad.bo.BusinessObject}</code>
 *
 * @see org.kuali.rice.krad.bo.BusinessObject
 * @see org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument
 * @author $Author: gmcgrego $
 * @version $Revision: 1.8 $
 */
@Entity
@Table(name = "EPS_PROP_PERSON_ROLE")
public class ProposalPersonRole extends KcPersistableBusinessObjectBase implements ContactRole {

    public static final String PRINCIPAL_INVESTIGATOR = "PI";

    public static final String CO_INVESTIGATOR = "COI";

    public static final String KEY_PERSON = "KP";

    private static final long serialVersionUID = -2184772940618843909L;

    @Id
    @Column(name = "PROP_PERSON_ROLE_ID")
    private String proposalPersonRoleId;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CERTIFICATION_REQUIRED")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean certificationRequired = Boolean.TRUE;

    @Transient
    private Boolean readOnly;

    @Column(name = "UNIT_DETAILS_REQUIRED")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean unitDetailsRequired = Boolean.TRUE;

    /**
     * Gets the value of proposalPersonRoleId
     *
     * @return the value of proposalPersonRoleId
     */
    public final String getProposalPersonRoleId() {
        return this.proposalPersonRoleId;
    }

    /**
     * Sets the value of proposalPersonRoleId
     *
     * @param argProposalPersonRoleId Value to assign to this.proposalPersonRoleId
     */
    public final void setProposalPersonRoleId(String argProposalPersonRoleId) {
        this.proposalPersonRoleId = argProposalPersonRoleId;
    }

    /**
     * Gets the value of description
     *
     * @return the value of description
     */
    public final String getDescription() {
        return this.description;
    }

    /**
     * Sets the value of description
     *
     * @param argDescription Value to assign to this.description
     */
    public final void setDescription(String argDescription) {
        this.description = argDescription;
    }

    /**
     * Gets the readOnly attribute. 
     * @return Returns the readOnly.
     */
    public Boolean getReadOnly() {
        return readOnly;
    }

    /**
     * Sets the readOnly attribute value.
     * @param readOnly The readOnly to set.
     */
    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Boolean getUnitDetailsRequired() {
        return unitDetailsRequired;
    }

    public void setUnitDetailsRequired(Boolean unitDetailsRequired) {
        this.unitDetailsRequired = unitDetailsRequired;
    }

    public Boolean getCertificationRequired() {
        return certificationRequired;
    }

    public void setCertificationRequired(Boolean certificationRequired) {
        this.certificationRequired = certificationRequired;
    }

    @Override
    public String getRoleCode() {
        return getProposalPersonRoleId();
    }

    @Override
    public String getRoleDescription() {
        return getDescription();
    }

    /**
     * This method determines if ProposalPersonRole is PI
     * @return
     */
    public boolean isPrincipalInvestigatorRole() {
        return ContactRole.PI_CODE.equals(getRoleCode());
    }
}
