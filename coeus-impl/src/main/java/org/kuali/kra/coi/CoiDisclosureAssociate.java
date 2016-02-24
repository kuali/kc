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
package org.kuali.kra.coi;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.award.AwardAssociate;

public class CoiDisclosureAssociate extends KcPersistableBusinessObjectBase implements SequenceAssociate<CoiDisclosure> {


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

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((coiDisclosureNumber == null) ? 0 : coiDisclosureNumber.hashCode());
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
