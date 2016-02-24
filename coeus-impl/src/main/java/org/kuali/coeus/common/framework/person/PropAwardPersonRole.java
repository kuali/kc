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
package org.kuali.coeus.common.framework.person;

import org.kuali.coeus.sys.api.model.Coded;
import org.kuali.coeus.sys.api.model.IdentifiableNumeric;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;
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

    @PortableSequenceGenerator(name = "SEQ_EPS_PROP_PERSON_ROLE")
    @GeneratedValue(generator = "SEQ_EPS_PROP_PERSON_ROLE")
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
