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
package org.kuali.coeus.common.framework.person;

import org.kuali.coeus.sys.api.model.Coded;
import org.kuali.coeus.sys.api.model.Identifiable;
import org.kuali.coeus.sys.api.model.IdentifiableNumeric;
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
public class PropAwardPersonRole extends KcPersistableBusinessObjectBase implements ContactRole, IdentifiableNumeric, Coded {

    public static final String PRINCIPAL_INVESTIGATOR = "PI";
    
    public static final String MULTI_PI = "MPI";

    public static final String CO_INVESTIGATOR = "COI";

    public static final String KEY_PERSON = "KP";

    private static final long serialVersionUID = -2184772940618843909L;

    @Id
    @Column(name = "PROP_PERSON_ROLE_ID")
    private Long id;
    
    @Column(name = "PROP_PERSON_ROLE_CODE")
    private String code;
    
    @Column(name = "SPONSOR_HIERARCHY_NAME")
    private String sponsorHierarchyName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CERTIFICATION_REQUIRED")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean certificationRequired = Boolean.TRUE;
    
    @Column(name="READ_ONLY_ROLE")
    private Boolean readOnly;

    @Column(name = "UNIT_DETAILS_REQUIRED")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean unitDetailsRequired = Boolean.TRUE;

    public final String getCode() {
        return this.code;
    }

    public final void setCode(String argProposalPersonRoleId) {
        this.code = argProposalPersonRoleId;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String argDescription) {
        this.description = argDescription;
    }
    
	public Boolean getReadOnly() {
		return readOnly;
	}

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
        return getCode();
    }

    @Override
    public String getRoleDescription() {
        return getDescription();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSponsorHierarchyName() {
		return sponsorHierarchyName;
	}

	public void setSponsorHierarchyName(String sponsorHierarchyName) {
		this.sponsorHierarchyName = sponsorHierarchyName;
	}
}
