package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class ProposalChangedData extends KraPersistableBusinessObjectBase {

	private Integer changeNumber;
	private String columnName;
	private String attributeName;
	private String proposalNumber;
	private String changedValue;
	private String comments;
	private String displayValue;
	private String oldDisplayValue;

	private ProposalColumnsToAlter editableColumn;
	
	public ProposalChangedData(){
		super();
	}

	public Integer getChangeNumber() {
		return changeNumber;
	}

	public void setChangeNumber(Integer changeNumber) {
		this.changeNumber = changeNumber;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public String getChangedValue() {
		return changedValue;
	}

	public void setChangedValue(String changedValue) {
		this.changedValue = changedValue;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getOldDisplayValue() {
		return oldDisplayValue;
	}

	public void setOldDisplayValue(String oldDisplayValue) {
		this.oldDisplayValue = oldDisplayValue;
	}


    public ProposalColumnsToAlter getEditableColumn() {
        return editableColumn;
    }

    public void setEditableColumn(ProposalColumnsToAlter editableColumn) {
        this.editableColumn = editableColumn;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override 
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("changeNumber", getChangeNumber());
        hashMap.put("columnName", getColumnName());
        hashMap.put("proposalNumber", getProposalNumber());
        hashMap.put("changedValue", getChangedValue());
        hashMap.put("comments", getComments());
        hashMap.put("displayValue", getDisplayValue());
        hashMap.put("oldDisplayValue", getOldDisplayValue());
        return hashMap;
    }
    
}
