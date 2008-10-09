package org.kuali.kra.proposaldevelopment.bo;

import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

@IdClass(org.kuali.kra.proposaldevelopment.bo.id.ProposalChangedDataId.class)
@Entity
@Table(name="EPS_PROP_CHANGED_DATA")
public class ProposalChangedData extends KraPersistableBusinessObjectBase {
    @Id
    @Column(name="PROPOSAL_NUMBER")
    private String proposalNumber;
    
    @Id
    @Column(name="COLUMN_NAME")
    private String columnName;
    
	@Id
	@Column(name="CHANGE_NUMBER")
	private Integer changeNumber;
	
	@Transient
	private String attributeName;
	
	@Column(name="CHANGED_VALUE")
	private String changedValue;
	
	@Column(name="COMMENTS")
	private String comments;
	
	@Column(name="DISPLAY_VALUE")
	private String displayValue;
	
	@Column(name="OLD_DISPLAY_VALUE")
	private String oldDisplayValue;

	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="COLUMN_NAME", insertable=false, updatable=false)
	private ProposalColumnsToAlter editableColumn;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
	@JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false)
	private ProposalDevelopmentDocument proposalDevelopmentDocument;
	
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

    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return proposalDevelopmentDocument;
    }

    public void setProposalDevelopmentDocument(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.proposalDevelopmentDocument = proposalDevelopmentDocument;
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

