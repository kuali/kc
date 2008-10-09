package org.kuali.kra.bo;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import org.kuali.rice.jpa.annotations.Sequence;

import java.util.LinkedHashMap;


@Entity
@Table(name="NSF_CODES")
@Sequence(name="SEQUENCE_NSF_CODES", property="nsfSequenceNumber")
public class NsfCode extends KraPersistableBusinessObjectBase {
	
    @Id
	@Column(name="NSF_SEQUENCE_NUMBER")
	private Integer nsfSequenceNumber;
	@Column(name="NSF_CODE")
	private String nsfCode;
	@Column(name="DESCRIPTION")
	private String description;
	
    public Integer getNsfSequenceNumber() {
        return nsfSequenceNumber;
    }
    public void setNsfSequenceNumber(Integer nsfSequenceNumber) {
        this.nsfSequenceNumber = nsfSequenceNumber;
    }
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNsfCode() {
		return nsfCode;
	}
	public void setNsfCode(String nsfCode) {
		this.nsfCode = nsfCode;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("nsfSequenceNumber", this.getNsfSequenceNumber());
		propMap.put("nsfCode", this.getNsfCode());
		propMap.put("description", this.getDescription());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		return propMap;
	}
}

