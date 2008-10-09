package org.kuali.kra.proposaldevelopment.bo;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * The Proposal State represents the current state of a proposal.
 */
@Entity
@Table(name="PROPOSAL_STATE")
public class ProposalState extends KraPersistableBusinessObjectBase {
	
	public static final String IN_PROGRESS = "1";
	public static final String APPROVAL_PENDING = "2";
	public static final String APPROVAL_GRANTED = "3";
	public static final String APPROVAL_NOT_INITIATED_SUBMITTED = "4";
	public static final String APPROVAL_PENDING_SUBMITTED = "5";
	public static final String APPROVED_AND_SUBMITTED = "6";
	public static final String DISAPPROVED = "7";
	public static final String APPROVED_POST_SUBMISSION = "8";
	public static final String DISAPPROVED_POST_SUBMISSION = "9";
	public static final String CANCELED = "10";
	public static final String DOCUMENT_ERROR = "11";
	
    @Id
	@Column(name="STATE_TYPE_CODE")
	private String stateTypeCode;
	@Column(name="DESCRIPTION")
	private String description;
	
	/**
	 * Get the State Type Code.
	 * @return the state type code
	 */
	public String getStateTypeCode() {
		return stateTypeCode;
	}
	
	/**
	 * Set the State Type Code
	 * @param stateTypeCode the new state type code
	 */
	public void setStateTypeCode(String stateTypeCode) {
		this.stateTypeCode = stateTypeCode;
	}
	
	/**
	 * Get the human-readable description of the status.
	 * @return the textual description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the textual description.
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
	 */
	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap map = new LinkedHashMap();
		map.put("stateTypeCode", this.getStateTypeCode());
		map.put("description", this.getDescription());
		map.put("updateTimestamp", this.getUpdateTimestamp());
		map.put("updateUser", this.getUpdateUser());
		return map;
	}
}

