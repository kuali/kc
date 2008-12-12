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
package org.kuali.kra.award.bo;

import java.util.LinkedHashMap;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.CommentType;

public class AwardComment extends KraPersistableBusinessObjectBase {
    
    private Long awardCommentId;
    private Integer commentTypeCode;
    private Boolean checklistPrintFlag;
    private String comments;
    private CommentType commentType;
    private Award award;
    private String awardNumber;
    private Integer sequenceNumber;
    
    /**
     * 
     * Constructs a AwardComment.java.
     */
    public AwardComment() {
        super();                
    }
    
    public Long getAwardCommentId() {
        return awardCommentId;
    }




    public void setAwardCommentId(Long awardCommentId) {
        this.awardCommentId = awardCommentId;
    }

    public Integer getCommentTypeCode() {
        return commentTypeCode;
    }

    public void setCommentTypeCode(Integer commentTypeCode) {
        this.commentTypeCode = commentTypeCode;
    }
    
    public String getAwardNumber() {
        return awardNumber;//temp
     }
    
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;//temp
     }


    public CommentType getCommentType() {
        return commentType;
    }




    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
    }



    public Award getAward() {
        return award;
    }
    
    

    public void setAward(Award award) {
        this.award = award;
    }




    public int getSequenceNumber() {
        return sequenceNumber;//temp
     }





    public void setSequenceNumber(Integer sequenceNumber) {
       this.sequenceNumber = sequenceNumber;//temp
    }




    public Boolean getChecklistPrintFlag() {
        return checklistPrintFlag;
    }




    public void setChecklistPrintFlag(Boolean checklistPrintFlag) {
        this.checklistPrintFlag = checklistPrintFlag;
    }




    public String getComments() {
        return comments;
    }




    public void setComments(String comments) {
        this.comments = comments;
    }




    @Override
    protected LinkedHashMap<String,Object> toStringMapper() {        
        LinkedHashMap<String,Object> hashMap = new LinkedHashMap<String,Object>();
        hashMap.put("awardCommentId", getAwardCommentId());
        hashMap.put("commentTypeCode", getCommentTypeCode());
        hashMap.put("awardNumber", getAwardNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        hashMap.put("checklistPrintFlag", getChecklistPrintFlag());
        hashMap.put("comments", getComments());
        return hashMap;
    }




   

}
