/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.bo;

import java.util.LinkedHashMap;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class PropScienceKeyword extends KraPersistableBusinessObjectBase {
	private String proposalNumber;
	private String scienceKeywordCode;
	private String scienceKeywordDescription;
    private ScienceKeyword scienceKeyword;
    private Boolean selectKeyword = false;

    /**
     * Default constructor.
     */
    public PropScienceKeyword() {

    }
    
    /**
     * Constructs a PropScienceKeyword.
     * @param proposalNumber
     * @param scienceKeyword
     */
    public PropScienceKeyword(String proposalNumber, ScienceKeyword scienceKeyword) {
        this.proposalNumber = proposalNumber;
        this.scienceKeywordDescription = scienceKeyword.getDescription();
        this.scienceKeywordCode = scienceKeyword.getScienceKeywordCode();
        this.scienceKeyword = scienceKeyword;
    }

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
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
