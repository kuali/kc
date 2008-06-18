package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ProposalColumnsToAlter extends KraPersistableBusinessObjectBase {

	private String columnName;
	private String columnLabel;
	private Integer dataLength;
	private String dataType;
	private boolean hasLookup;
	private String lookupArgument;
	private String lookupWindow;
	private String lookupReturn;
	
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

	public String getLookupArgument() {
		return lookupArgument;
	}

	public void setLookupArgument(String lookupArgument) {
		this.lookupArgument = lookupArgument;
	}

	public String getLookupWindow() {
		return lookupWindow;
	}

	public void setLookupWindow(String lookupWindow) {
		this.lookupWindow = lookupWindow;
	}

    public String getLookupReturn() {
        return lookupReturn;
    }

    public void setLookupReturn(String lookupReturn) {
        this.lookupReturn = lookupReturn;
    }
    
	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("columnName", getColumnName());
		hashMap.put("columnLabel", getColumnLabel());
		hashMap.put("dataLength", getDataLength());
		hashMap.put("dataType", getDataType());
		hashMap.put("hasLookup", getHasLookup());
		hashMap.put("lookupArgument", getLookupArgument());
		hashMap.put("lookupWindow", getLookupWindow());
		return hashMap;
	}

}
