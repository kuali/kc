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

import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Type;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.jpa.annotations.Sequence;

/**
 * A KIM Role defines a role within a given organization, e.g. President,
 * Fiscal Officer, etc.  Roles are assigned one or more Permissions that
 * limit what a person can do.  Persons are assigned to roles either directly
 * or indirectly through groups.  Once assigned to a role, a person obtains
 * all of the permissions in that role.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Entity
@Table(name="KIM_ROLES_T")
@Sequence(name="SEQ_KIM_ROLES_ID", property="id")
public class KimRole extends PersistableBusinessObjectBase {

	private static final long serialVersionUID = -8535955276605020423L;
	
	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="ROLE_TYPE_CODE")
	private String roleTypeCode;
	
    @Type(type="yes_no")
	@Column(name="DESCEND_FLAG")
	private boolean descend;
	
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="ROLE_TYPE_CODE", insertable=false, updatable=false)
	private KimRoleType roleType;
	
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.kim.bo.KimRolePermission.class, mappedBy="role")
	private List<KimRolePermission> rolePermissions;

	/**
	 * Default constructor. Initializes <code>rolePermissions</code>
	 */
	public KimRole() {
	    rolePermissions = new TypedArrayList(KimRolePermission.class);
	}
	
    /**
     * Get the Role's ID.
     * @return the Role's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the Role's ID.
     * @param id the Role's ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the Role's name.
     * @return the Role's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Role's name.
     * @param name the Role's name
     */
    public void setName(String name) {
        this.name = name;
    }
    
	/**
	 * Get the Role's description.
	 * @return the Role's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the Role's description.
	 * @param description the Role's description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getRoleTypeCode() {
	     return roleTypeCode;
	}

	public void setRoleTypeCode(String roleTypeCode) {
	    this.roleTypeCode = roleTypeCode;
	}

	public KimRoleType getRoleType() {
	    return roleType;
    }

    public void setRoleType(KimRoleType roleType) {
        this.roleType = roleType;
    }
    
    public String getRoleTypeName() {
       return roleType.getDescription();
    }

    public boolean getDescend() {
        return descend;
    }

    public void setDescend(boolean descend) {
        this.descend = descend;
    }
    
    public List<KimPermission> getPermissions() {
        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
        HashMap<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("active", true);
        fieldValues.put("roleId", id);
        Collection<KimRolePermission> rolePermissions = bos.findMatching(KimRolePermission.class, fieldValues);
        List<KimPermission> permissions = new ArrayList<KimPermission>();
        for (KimRolePermission rolePermission : rolePermissions) {
            HashMap<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put("id", rolePermission.getPermissionId());
            KimPermission permission = (KimPermission) bos.findByPrimaryKey(KimPermission.class, primaryKeys);
            permissions.add(permission);
        }
        return permissions;
    }

    /**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("id", getId());
        map.put("name", getName());
        map.put("roleTypeCode", getRoleTypeCode());
        map.put("description", getDescription());
        return map;
	}

	/**
	 * @see org.kuali.core.bo.PersistableBusinessObjectBase#refresh()
	 */
	public void refresh() {
		// not going to implement unless necessary
	}
	
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (!obj.getClass().equals(this.getClass()))
            return false;
        KimRole role = (KimRole) obj;
        return id.equals(role.id);
    }

    /**
     * Gets the rolePermissions attribute. 
     * @return Returns the rolePermissions.
     */
    public List<KimRolePermission> getRolePermissions() {
        return rolePermissions;
    }

    /**
     * Sets the rolePermissions attribute value.
     * @param rolePermissions The rolePermissions to set.
     */
    public void setRolePermissions(List<KimRolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
    
    public boolean isUnassigned() {
        return StringUtils.equals(name, RoleConstants.UNASSIGNED);
    }

    public boolean isStandardProposalRole() {
        return StringUtils.equals(name, RoleConstants.AGGREGATOR) ||
               StringUtils.equals(name, RoleConstants.NARRATIVE_WRITER) ||
               StringUtils.equals(name, RoleConstants.BUDGET_CREATOR) ||
               StringUtils.equals(name, RoleConstants.VIEWER);
    }

}

