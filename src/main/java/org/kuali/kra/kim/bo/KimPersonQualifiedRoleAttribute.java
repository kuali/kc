/*
 * Copyright 2007 The Kuali Foundation
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
package org.kuali.kra.kim.bo;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import org.kuali.rice.jpa.annotations.Sequence;

import java.util.LinkedHashMap;

/**
 * The KIM Person Qualified Role Attribute represents one Qualified Role Attribute
 * on a Role-Person association.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
@Entity
@Table(name="KIM_PERSON_QUAL_ATTR_T")
@Sequence(name="SEQ_KIM_PERSON_QUAL_ATTR_ID", property="id")
public class KimPersonQualifiedRoleAttribute extends KimQualifiedRoleAttribute {

    private static final long serialVersionUID = -3834313283054550673L;
    @Id
    @Column(name="ID")
    private Long id;

    @Column(name="ROLE_PERSON_ID")
	private Long rolePersonId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumns({@JoinColumn(name="ROLE_PERSON_ID", insertable = false, updatable = false)})
    private KimQualifiedRolePerson kimQualifiedRolePerson;
    
    /**
     * Get the Role-Person's ID.
     * @return the Role-Person's ID
     */
    public Long getRolePersonId() {
        return rolePersonId;
    }

    /**
     * Set the Role-Person's ID
     * @param rolePersonId the Role-Person's ID
     */
    public void setRolePersonId(Long rolePersonId) {
        this.rolePersonId = rolePersonId;
    }

    public KimQualifiedRolePerson getKimQualifiedRolePerson() {
        return kimQualifiedRolePerson;
    }

    public void setKimQualifiedRolePerson(KimQualifiedRolePerson kimQualifiedRolePerson) {
        this.kimQualifiedRolePerson = kimQualifiedRolePerson;
    }

    /**
     * @see org.kuali.kra.kim.bo.KimQualifiedRoleAttribute#toStringMapper()
     */
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> propMap = super.toStringMapper();
        propMap.put("rolePersonId", getRolePersonId());
        return propMap;
    }
}

