package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@Entity
@Table(name="EPS_PROP_COLUMNS_TO_ALTER")
public class ProposalColumnsToAlter extends KraPersistableBusinessObjectBase {

	@Id
	@Column(name="COLUMN_NAME")
	private String columnName;
	@Column(name="COLUMN_LABEL")
	private String columnLabel;
	@Column(name="DATA_LENGTH")
	private Integer dataLength;
	@Column(name="DATA_TYPE")
	private String dataType;
	@Column(name="HAS_LOOKUP")
	private boolean hasLookup;
	@Column(name="LOOKUP_ARGUMENT")
	private String lookupClass;
	@Column(name="LOOKUP_RETURN")
	private String lookupReturn;
	@Transient
	private String lookupPkReturn;
	
	public ProposalColumnsToAlter(){
		super();
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnLabel() {
		return columnLabel;
	}

	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}

	public Integer getDataLength() {
		return dataLength;
	}

	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean getHasLookup() {
		return hasLookup;
	}

	public void setHasLookup(boolean hasLookup) {
		this.hasLookup = hasLookup;
	}

	public String getLookupClass() {
        return lookupClass;
    }

    public void setLookupClass(String lookupClass) {
        this.lookupClass = lookupClass;
    }

    public String getLookupReturn() {
        return lookupReturn;
    }

    public void setLookupReturn(String lookupReturn) {
        this.lookupReturn = lookupReturn;
    }

    public String getLookupPkReturn() {
        return lookupPkReturn;
    }

    public void setLookupPkReturn(String lookupPkReturn) {
        this.lookupPkReturn = lookupPkReturn;
    }

    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("columnName", getColumnName());
        hashMap.put("columnLabel", getColumnLabel());
        hashMap.put("dataLength", getDataLength());
        hashMap.put("dataType", getDataType());
        hashMap.put("hasLookup", getHasLookup());
        hashMap.put("lookupClass", getLookupClass());
        hashMap.put("lookupReturn", getLookupReturn());
        return hashMap;
    }

}

