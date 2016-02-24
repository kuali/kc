/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
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


    public String getAwardNumber() {
        return awardNumber;
    }


    public Integer getSequenceNumber() {
        return sequenceNumber;
    }


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

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((awardNumber == null) ? 0 : awardNumber.hashCode());
        result = PRIME * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
        return result;
    }

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

    @Override
    public Award getSequenceOwner() {
        return getAward();
    }

    @Override
    public void setSequenceOwner(Award newlyVersionedOwner) {
        setAward(newlyVersionedOwner);
    }
}
