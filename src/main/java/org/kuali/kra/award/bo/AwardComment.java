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

/**
 * This class is business object representation of an Award Comment
 */
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

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((award == null) ? 0 : award.hashCode());
        result = prime * result + ((awardCommentId == null) ? 0 : awardCommentId.hashCode());
        result = prime * result + ((awardNumber == null) ? 0 : awardNumber.hashCode());
        result = prime * result + ((checklistPrintFlag == null) ? 0 : checklistPrintFlag.hashCode());
        result = prime * result + ((commentType == null) ? 0 : commentType.hashCode());
        result = prime * result + ((commentTypeCode == null) ? 0 : commentTypeCode.hashCode());
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AwardComment other = (AwardComment) obj;
        if (award == null) {
            if (other.award != null)
                return false;
        }
        else if (!award.equals(other.award))
            return false;
        if (awardCommentId == null) {
            if (other.awardCommentId != null)
                return false;
        }
        else if (!awardCommentId.equals(other.awardCommentId))
            return false;
        if (awardNumber == null) {
            if (other.awardNumber != null)
                return false;
        }
        else if (!awardNumber.equals(other.awardNumber))
            return false;
        if (checklistPrintFlag == null) {
            if (other.checklistPrintFlag != null)
                return false;
        }
        else if (!checklistPrintFlag.equals(other.checklistPrintFlag))
            return false;
        if (commentType == null) {
            if (other.commentType != null)
                return false;
        }
        else if (!commentType.equals(other.commentType))
            return false;
        if (commentTypeCode == null) {
            if (other.commentTypeCode != null)
                return false;
        }
        else if (!commentTypeCode.equals(other.commentTypeCode))
            return false;
        if (comments == null) {
            if (other.comments != null)
                return false;
        }
        else if (!comments.equals(other.comments))
            return false;
        if (sequenceNumber == null) {
            if (other.sequenceNumber != null)
                return false;
        }
        else if (!sequenceNumber.equals(other.sequenceNumber))
            return false;
        return true;
    }




   

}
