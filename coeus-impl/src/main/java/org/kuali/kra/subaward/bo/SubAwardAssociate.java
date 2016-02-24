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
package org.kuali.kra.subaward.bo;

import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import java.io.Serializable;

public abstract class SubAwardAssociate extends KcPersistableBusinessObjectBase implements SequenceAssociate<SubAward>, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long subAwardId;
    private String subAwardCode;
    private Integer sequenceNumber;
    private SubAward subAward;
    
   
    /**
	 * This is the Getter Method for subAwardCode  
	 * @return Returns the subAwardCode.
	 */
	public String getSubAwardCode() {
		return subAwardCode;
	}

	/**
	 * This is the Setter Method for subAwardCode
	 * @param subAwardCode The subAwardCode to set.
	 */
	public void setSubAwardCode(String subAwardCode) {
		this.subAwardCode = subAwardCode;
	}

	/**
	 * This is the Getter Method for sequenceNumber  
	 * @return Returns the sequenceNumber.
	 */
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * This is the Setter Method for sequenceNumber
	 * @param sequenceNumber The sequenceNumber to set.
	 */
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**.
	 * This is the Getter Method for subAward
	 * @return Returns the subAward.
	 */
	public SubAward getSubAward() {
		return subAward;
	}

	/**
     * @param subAward 
     */
    public void setSubAward(SubAward subAward) {
        this.subAward = subAward;
        if(subAward != null) {
        	setSubAwardId(subAward.getSubAwardId());
            setSequenceNumber(subAward.getSequenceNumber());
            setSubAwardCode(subAward.getSubAwardCode());
        } else {
        	setSubAwardId(null);
            setSequenceNumber(0);
            setSubAwardCode(null);
        }
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((subAwardCode == null) ? 0 : subAwardCode.hashCode());
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
        if (!(obj instanceof SubAwardAssociate)) {
            return false;
        }
        SubAwardAssociate other = (SubAwardAssociate) obj;
        if (subAwardCode == null) {
            if (other.subAwardCode != null) {
                return false;
            }
        } else if (!subAwardCode.equals(other.subAwardCode)) {
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
    public SubAward getSequenceOwner() {
        return getSubAward();
    }

    @Override
    public void setSequenceOwner(SubAward newlyVersionedOwner) {
        setSubAward(newlyVersionedOwner);   
    }

    public Long getSubAwardId() {
        return subAwardId;
    }

    public void setSubAwardId(Long subAwardId) {
        this.subAwardId = subAwardId;
    }

   

  

}
