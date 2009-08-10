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
package org.kuali.kra.proposaldevelopment.budget.bo;

import java.util.LinkedHashMap;
import org.kuali.kra.budget.core.BudgetAssociate;

public class BudgetSubAwardFiles extends BudgetAssociate {
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 9212512161341725983L;
	private Integer subAwardNumber;
	private byte[] subAwardXfdFileData;
	private String subAwardXfdFileName;
	private String subAwardXmlFileData;
	

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
		hashMap.put("subAwardNumber", getSubAwardNumber());
		hashMap.put("updateTimestamp", this.getUpdateTimestamp());
        hashMap.put("updateUser", this.getUpdateUser());
		return hashMap;
	}


    /**
     * Gets the subAwardNumber attribute. 
     * @return Returns the subAwardNumber.
     */
    public Integer getSubAwardNumber() {
        return subAwardNumber;
    }


    /**
     * Sets the subAwardNumber attribute value.
     * @param subAwardNumber The subAwardNumber to set.
     */
    public void setSubAwardNumber(Integer subAwardNumber) {
        this.subAwardNumber = subAwardNumber;
    }


    /**
     * Gets the subAwardXfdFileData attribute. 
     * @return Returns the subAwardXfdFileData.
     */
    public byte[] getSubAwardXfdFileData() {
        return subAwardXfdFileData;
    }


    /**
     * Sets the subAwardXfdFileData attribute value.
     * @param subAwardXfdFileData The subAwardXfdFileData to set.
     */
    public void setSubAwardXfdFileData(byte[] subAwardXfdFileData) {
        this.subAwardXfdFileData = subAwardXfdFileData;
    }


    /**
     * Gets the subAwardXfdFileName attribute. 
     * @return Returns the subAwardXfdFileName.
     */
    public String getSubAwardXfdFileName() {
        return subAwardXfdFileName;
    }


    /**
     * Sets the subAwardXfdFileName attribute value.
     * @param subAwardXfdFileName The subAwardXfdFileName to set.
     */
    public void setSubAwardXfdFileName(String subAwardXfdFileName) {
        this.subAwardXfdFileName = subAwardXfdFileName;
    }


    /**
     * Gets the subAwardXmlFileData attribute. 
     * @return Returns the subAwardXmlFileData.
     */
    public String getSubAwardXmlFileData() {
        return subAwardXmlFileData;
    }


    /**
     * Sets the subAwardXmlFileData attribute value.
     * @param subAwardXmlFileData The subAwardXmlFileData to set.
     */
    public void setSubAwardXmlFileData(String subAwardXmlFileData) {
        this.subAwardXmlFileData = subAwardXmlFileData;
    }

}
