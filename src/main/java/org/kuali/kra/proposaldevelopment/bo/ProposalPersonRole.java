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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * Represents the Proposal Person Role <code>{@link org.kuali.core.bo.BusinessObject}</code>
 *
 * @see org.kuali.core.bo.BusinessObject
 * @see org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument
 * @author $Author: gmcgrego $
 * @version $Revision: 1.8 $
 */
@Entity
@Table(name="EPS_PROP_PERSON_ROLE")
public class ProposalPersonRole extends KraPersistableBusinessObjectBase {
    @Id
	@Column(name="PROP_PERSON_ROLE_ID")
	private String proposalPersonRoleId;
    @Column(name="DESCRIPTION")
	private String description;
    @Column(name="CERTIFICATION_REQUIRED")
	private String certificationRequired;
    @Transient
    private Boolean readOnly;
    @Column(name="UNIT_DETAILS_REQUIRED")
	private String unitDetailsRequired;

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


    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("proposalPersonRoleId", getProposalPersonRoleId());
        propMap.put("description", getDescription());
        propMap.put("certificationRequired", getCertificationRequired());
        return propMap;
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

    public String getUnitDetailsRequired() {
        return unitDetailsRequired;
    }

    public void setUnitDetailsRequired(String unitDetailsRequired) {
        this.unitDetailsRequired = unitDetailsRequired;
    }

    public String getCertificationRequired() {
        return certificationRequired;
    }

    public void setCertificationRequired(String certificationRequired) {
        this.certificationRequired = certificationRequired;
    }
}



