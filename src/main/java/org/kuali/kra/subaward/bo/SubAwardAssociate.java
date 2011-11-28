/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.subaward.bo;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;



public abstract class SubAwardAssociate extends KraPersistableBusinessObjectBase implements SequenceAssociate<SubAward>, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String subAwardCode;
    private Integer sequenceNumber;
    private SubAward subAward;
    
    /**
     * @return
     */
    public String getSubAwardCode() {
        return subAwardCode;
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
    public SubAward getSubAward() {
        return subAward;
    }

    /**
     * @param subAwardCode
     */
    public void setSubAwardCode(String subAwardCode) {
        this.subAwardCode = subAwardCode;
    }

    /**
     * @param sequenceNumber
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * @param subAward
     */
    public void setSubAward(SubAward subAward) {
        this.subAward = subAward;
        if(subAward != null) {
            setSequenceNumber(subAward.getSequenceNumber());
            setSubAwardCode(subAward.getSubAwardCode());
        } else {
            setSequenceNumber(0);
            setSubAwardCode("");
        }
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("subAwardCode", subAwardCode);
        map.put("sequenceNumber", sequenceNumber);
        return map;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((subAwardCode == null) ? 0 : subAwardCode.hashCode());
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

    /**
     * @see org.kuali.kra.SequenceAssociate#getSequenceOwner()
     */
    public SubAward getSequenceOwner() {
        return getSubAward();
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#setSequenceOwner(org.kuali.kra.SequenceOwner)
     */
    public void setSequenceOwner(SubAward newlyVersionedOwner) {
        setSubAward(newlyVersionedOwner);   
    }

   

  

}
