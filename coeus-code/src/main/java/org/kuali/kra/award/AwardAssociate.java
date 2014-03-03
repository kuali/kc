/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.common.framework.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.home.Award;

import java.io.Serializable;

/**
 * This class handles the legacy sequenceNumber/awardNumber data from Coeus
 */
public abstract class AwardAssociate extends KcPersistableBusinessObjectBase implements SequenceAssociate<Award>, Serializable {

    private static final long serialVersionUID = -1966175324490120727L;

    private String awardNumber;

    private Integer sequenceNumber;

    private Award award;

    /**
     * @return
     */
    public String getAwardNumber() {
        return awardNumber;
    }

    /**
     * @return
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * @return
     */
    public Award getAward() {
        return award;
    }

    /**
     * @param awardNumber
     */
    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    /**
     * @param sequenceNumber
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * @param award
     */
    public void setAward(Award award) {
        this.award = award;
        if (award != null) {
            setSequenceNumber(award.getSequenceNumber());
            setAwardNumber(award.getAwardNumber());
        } else {
            setSequenceNumber(0);
            setAwardNumber("");
        }
    }
    
    /**
     * If the award's award number is not equal to the award number we will persist, 
     * then update it based on the award.
     * @see org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase#prePersist()
     */
    @Override
    protected void prePersist() {
        super.prePersist();
        if (award != null && !StringUtils.equals(award.getAwardNumber(), getAwardNumber())) {
            setSequenceNumber(award.getSequenceNumber());
            setAwardNumber(award.getAwardNumber());            
        }
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((awardNumber == null) ? 0 : awardNumber.hashCode());
        result = PRIME * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AwardAssociate)) {
            return false;
        }
        AwardAssociate other = (AwardAssociate) obj;
        if (awardNumber == null) {
            if (other.awardNumber != null) {
                return false;
            }
        } else if (!awardNumber.equals(other.awardNumber)) {
            return false;
        }
        if (sequenceNumber == null) {
            if (other.sequenceNumber != null) {
                return false;
            }
        } else if (!sequenceNumber.equals(other.sequenceNumber)) {
            return false;
        }
        return true;
    }

    /**
     * @see org.kuali.coeus.common.framework.sequence.associate.SequenceAssociate#getSequenceOwner()
     */
    public Award getSequenceOwner() {
        return getAward();
    }

    /**
     * @see org.kuali.coeus.common.framework.sequence.associate.SequenceAssociate#setSequenceOwner(org.kuali.kra.SequenceOwner)
     */
    public void setSequenceOwner(Award newlyVersionedOwner) {
        setAward(newlyVersionedOwner);
    }
}
