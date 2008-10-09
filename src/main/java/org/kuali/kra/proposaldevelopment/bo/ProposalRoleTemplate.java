package org.kuali.kra.proposaldevelopment.bo;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.kim.bo.KimRole;
import org.kuali.rice.jpa.annotations.Sequence;

@Entity
@Table(name="PROP_ROLE_TEMPLATE")
@Sequence(name="SEQ_PROP_ROLE_TEMPLATE_ID", property="id")
public class ProposalRoleTemplate extends KraPersistableBusinessObjectBase {
    
    @Id
	@Column(name="ID")
	private Long id;
    @Column(name="PERSON_ID")
	private String personId;
    @Column(name="ROLE_NAME")
	private String roleName;
	@Column(name="UNIT_NUMBER")
	private String unitNumber;
	@Column(name="ACTIVE_FLAG")
	private Boolean active;
	
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="PERSON_ID", insertable=false, updatable=false)
	private Person person;
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="UNIT_NUMBER", insertable=false, updatable=false)
	private Unit unit;

    public ProposalRoleTemplate() {
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
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
        if (!(obj instanceof ProposalRoleTemplate))
            return false;
        ProposalRoleTemplate other = (ProposalRoleTemplate) obj;
        return id.equals(other.id);
    }

}

