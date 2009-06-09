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

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.SequenceOwner;
/**
 * This class is business object representation of an Award Comment
 */

public class AwardComment extends AwardCommentBase implements SequenceAssociate {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3611932717292205490L;
    private Long awardCommentId;
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
     * @see org.kuali.kra.SequenceAssociate#getSequenceOwner()
     */
    public SequenceOwner getSequenceOwner() {
        return getAward();
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#setSequenceOwner(org.kuali.kra.SequenceOwner)
     */
    public void setSequenceOwner(SequenceOwner newlyVersionedOwner) {
        setAward((Award) newlyVersionedOwner);        
    }

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        awardCommentId = null;
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
     * @return
     */
    public Integer getSequenceNumber() {
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
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String,Object> toStringMapper() {        
        LinkedHashMap<String,Object> hashMap = super.toStringMapper();
        hashMap.put("awardCommentId", getAwardCommentId());
        hashMap.put("awardNumber", getAwardNumber());
        hashMap.put("sequenceNumber", getSequenceNumber());
        return hashMap;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((award == null) ? 0 : award.hashCode());
        result = prime * result + ((awardCommentId == null) ? 0 : awardCommentId.hashCode());
        result = prime * result + ((awardNumber == null) ? 0 : awardNumber.hashCode());
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
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AwardComment other = (AwardComment) obj;
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
        if (sequenceNumber == null) {
            if (other.sequenceNumber != null)
                return false;
        }
        else if (!sequenceNumber.equals(other.sequenceNumber))
            return false;
        return true;
    }
}
