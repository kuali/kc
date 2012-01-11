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
package org.kuali.kra.coi;

import java.util.LinkedHashMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.award.AwardAssociate;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.Protocol;

public class CoiDisclosureAssociate extends KraPersistableBusinessObjectBase implements SequenceAssociate<CoiDisclosure> {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4604078758009003461L;

    private String coiDisclosureNumber;
    private Integer sequenceNumber;
    private CoiDisclosure coiDisclosure;
    private Long coiDisclosureId;

    
    public Long getCoiDisclosureId() {
        return coiDisclosureId;
    }

    public void setCoiDisclosureId(Long coiDisclosureId) {
        this.coiDisclosureId = coiDisclosureId;
    }

    public CoiDisclosureAssociate(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }

    public CoiDisclosureAssociate() {
        super();
    }
    
    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
        if (coiDisclosure != null) {
            setSequenceNumber(coiDisclosure.getSequenceNumber());
            setCoiDisclosureNumber(coiDisclosure.getCoiDisclosureNumber());
        } else {
            setSequenceNumber(0);
            setCoiDisclosureNumber("");
        }
    }
    
    public String getCoiDisclosureNumber() {
        if ((StringUtils.isBlank(coiDisclosureNumber) ||  coiDisclosure != null && StringUtils.isNotBlank(coiDisclosure.getCoiDisclosureNumber()))) {
            setCoiDisclosureNumber(coiDisclosure.getCoiDisclosureNumber());
        }
        return coiDisclosureNumber;    
     }


    public void setCoiDisclosureNumber(String coiDisclosureNumber) {
        this.coiDisclosureNumber = coiDisclosureNumber;
    }


    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * @see org.kuali.core.bo.BusinessObjectBase#toStringMapper()
     */
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("coiDisclosureNumber", coiDisclosureNumber);
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
        result = PRIME * result + ((coiDisclosureNumber == null) ? 0 : coiDisclosureNumber.hashCode());
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
        CoiDisclosureAssociate other = (CoiDisclosureAssociate) obj;
        if (coiDisclosureNumber == null) {
            if (other.coiDisclosureNumber != null) {
                return false;
            }
        } else if (!coiDisclosureNumber.equals(other.coiDisclosureNumber)) {
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
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    @Override
    public void resetPersistenceState() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public CoiDisclosure getSequenceOwner() {
        return getCoiDisclosure();
    }

    /*
     * thisi s called before setting coiDisclosure
     */
    @Override
    public void setSequenceOwner(CoiDisclosure newlyVersionedOwner) {
        setCoiDisclosure(newlyVersionedOwner);      
    }

}
