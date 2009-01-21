/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class CommentType extends KraPersistableBusinessObjectBase {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5649376154094364142L;
    private Integer commentTypeCode;
    private String description;
    private Boolean templateFlag;
    private Boolean checklistFlag;
    private Boolean awardCommentScreenFlag;

    public Integer getCommentTypeCode() {
        return commentTypeCode;
    }

    public void setCommentTypeCode(Integer commentTypeCode) {
        this.commentTypeCode = commentTypeCode;
    }

    /**
     * This method...
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method...
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method...
     * @return
     */
    public Boolean getTemplateFlag() {
        return templateFlag;
    }

    /**
     * This method...
     * @param templateFlag
     */
    public void setTemplateFlag(Boolean templateFlag) {
        this.templateFlag = templateFlag;
    }

    /**
     * This method...
     * @return
     */
    public Boolean getChecklistFlag() {
        return checklistFlag;
    }

    /**
     * This method...
     * @param checklistFlag
     */
    public void setChecklistFlag(Boolean checklistFlag) {
        this.checklistFlag = checklistFlag;
    }

    /**
     * This method...
     * @return
     */
    public Boolean getAwardCommentScreenFlag() {
        return awardCommentScreenFlag;
    }

    /**
     * This method...
     * @param awardCommentScreenFlag
     */
    public void setAwardCommentScreenFlag(Boolean awardCommentScreenFlag) {
        this.awardCommentScreenFlag = awardCommentScreenFlag;
    }
    

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String,Object> toStringMapper() {        
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
        hashMap.put("commentTypeCode", getCommentTypeCode());
        hashMap.put("description", getDescription());
        hashMap.put("templateFlag", getTemplateFlag()); 
        hashMap.put("checklistFlag", getChecklistFlag());
        hashMap.put("awardCommentScreenFlag", getAwardCommentScreenFlag());
        return hashMap;
    }

}
