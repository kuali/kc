package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ProposalColumnsToAlter extends KraPersistableBusinessObjectBase {

	private String columnName;
	private String columnLabel;
	private Integer dataLength;
	private String dataType;
	private boolean hasLookup;
	private String lookupClass;
	private String lookupReturn;
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
