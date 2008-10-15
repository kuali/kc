package org.kuali.kra.bo;

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.kra.kim.bo.KimRole;
import org.kuali.rice.jpa.annotations.Sequence;

@Entity
@Table(name="UNIT_ACL")
@Sequence(name="SEQ_UNIT_ACL_ID", property="id")
public class UnitAclEntry extends PersistableBusinessObjectBase {
    
    @Id
	@Column(name="ID")
	private Long id;
    @Column(name="PERSON_ID")
	private String personId;
    @Column(name="ROLE_ID")
	private Long roleId;
	@Column(name="UNIT_NUMBER")
	private String unitNumber;
    @Type(type="yes_no")
	@Column(name="SUBUNITS")
	private boolean subunits;
    @Type(type="yes_no")
	@Column(name="ACTIVE_FLAG")
	private boolean active;
	
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="PERSON_ID", insertable=false, updatable=false)
	private Person person;
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="UNIT_NUMBER", insertable=false, updatable=false)
	private Unit unit;
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="ROLE_ID", insertable=false, updatable=false)
 	private KimRole role;

    public UnitAclEntry() {
        subunits = false;
        active = true;
    }

	public Long getId() {
        return id;
    }
	
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
    
    public String getPersonName() {
        return person.getFullName();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    public String getRoleName() {
        return role.getName();
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    
    public String getUnitName() {
        return unit.getUnitName();
    }

    public boolean getSubunits() {
        return subunits;
    }

    public void setSubunits(boolean subunits) {
        this.subunits = subunits;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public KimRole getRole() {
        return role;
    }

    public void setRole(KimRole role) {
        this.role = role;
    }
    
    public String getRoleTypeName() {
        return role.getRoleTypeName();
    }

    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("unitNumber", getUnitNumber());
		return hashMap;
	}
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (!(obj instanceof UnitAclEntry))
            return false;
        UnitAclEntry other = (UnitAclEntry) obj;
        return id.equals(other.id);
    }

}

