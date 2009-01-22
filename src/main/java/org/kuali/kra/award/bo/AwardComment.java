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
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3611932717292205490L;
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
    
    /**
     * This method...
     * @return
     */
    public Long getAwardCommentId() {
        return awardCommentId;
    }

    /**
     * This method...
     * @param awardCommentId
     */
    public void setAwardCommentId(Long awardCommentId) {
        this.awardCommentId = awardCommentId;
    }

    /**
     * This method...
     * @return
     */
    public Integer getCommentTypeCode() {
        return commentTypeCode;
    }

    /**
     * This method...
     * @param commentTypeCode
     */
    public void setCommentTypeCode(Integer commentTypeCode) {
        this.commentTypeCode = commentTypeCode;
    }
    
    /**
     * This method...
     * @return
     */
    public String getAwardNumber() {
        return awardNumber;
     }
    
    /**
     * This method...
     * @param awardNumber
     */
    public void setAwardNumber(String awardNumber) {
        //do nothing
     }


    /**
     * This method...
     * @return
     */
    public CommentType getCommentType() {
        return commentType;
    }




    /**
     * This method...
     * @param commentType
     */
    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
    }



    /**
     * This method...
     * @return
     */
    public Award getAward() {
        return award;
    }
    
    

    /**
     * This method...
     * @param award
     */
    public void setAward(Award award) {
        this.award = award;
        if(award == null) {
            sequenceNumber = null;
            awardNumber = null;
        } else {
            sequenceNumber = (award.getSequenceNumber());
            awardNumber = (award.getAwardNumber());
        }
    }




    /**
     * This method...
     * @return
     */
    public int getSequenceNumber() {
        return sequenceNumber;//temp
     }





    /**
     * This method...
     * @param sequenceNumber
     */
    public void setSequenceNumber(Integer sequenceNumber) {
       //do nothing
    }




    /**
     * This method...
     * @return
     */
    public Boolean getChecklistPrintFlag() {
        return checklistPrintFlag;
    }




    /**
     * This method...
     * @param checklistPrintFlag
     */
    public void setChecklistPrintFlag(Boolean checklistPrintFlag) {
        this.checklistPrintFlag = checklistPrintFlag;
    }




    /**
     * This method...
     * @return
     */
    public String getComments() {
        return comments;
    }




    /**
     * This method...
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }




    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
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
