package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class PropScienceKeyword extends KraPersistableBusinessObjectBase {
	private Integer proposalNumber;
	private String scienceKeywordCode;
    private ScienceKeyword scienceKeyword;
    private Boolean selectKeyword = false;
    

	public Integer getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(Integer proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public String getScienceKeywordCode() {
		return scienceKeywordCode;
	}

	public void setScienceKeywordCode(String scienceKeywordCode) {
		this.scienceKeywordCode = scienceKeywordCode;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("proposalNumber", getProposalNumber());
		hashMap.put("scienceCode", getScienceKeywordCode());
        //hashMap.put("selectKeyword", getSelectKeyword());
		return hashMap;
	}

	public ScienceKeyword getScienceKeyword() {
        return scienceKeyword;
    }

    public void setScienceKeyword(ScienceKeyword scienceKeyword) {
        this.scienceKeyword = scienceKeyword;
    }
	
    public Boolean getSelectKeyword() {
        return selectKeyword;
    }

    public void setSelectKeyword(Boolean selectKeyword) {
        this.selectKeyword = selectKeyword;
    }
    
}
