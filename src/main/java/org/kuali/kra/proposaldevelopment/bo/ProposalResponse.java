/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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

/**
 * Business object representation of Proposal Response
 * @author $Author: gmcgrego $
 * @version $Revision: 1.4 $
 */
public class ProposalResponse extends KraPersistableBusinessObjectBase {
	
	private String proposalResponseCode;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProposalResponseCode() {
		return proposalResponseCode;
	}
	public void setProposalResponseCode(String proposalResponseCode) {
		this.proposalResponseCode = proposalResponseCode;
	}

	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap propMap = new LinkedHashMap();
		propMap.put("scienceCode", this.getProposalResponseCode());
		propMap.put("description", this.getDescription());
		propMap.put("updateTimestamp", this.getUpdateTimestamp());
		propMap.put("updateUser", this.getUpdateUser());
		return propMap;
	}
}
