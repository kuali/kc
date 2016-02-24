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
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;

public class PersonFinIntDisclosureAssociate extends KcPersistableBusinessObjectBase implements SequenceAssociate<PersonFinIntDisclosure> {


    private static final long serialVersionUID = -5261867441953663528L;
    
    private String entityNumber;
    private Integer sequenceNumber;
    private PersonFinIntDisclosure personFinIntDisclosure;
    private Long personFinIntDisclosureId;

    
    public Long getPersonFinIntDisclosureId() {
        return personFinIntDisclosureId;
    }

    public void setPersonFinIntDisclosureId(Long personFinIntDisclosureId) {
        this.personFinIntDisclosureId = personFinIntDisclosureId;
    }

    public PersonFinIntDisclosureAssociate(PersonFinIntDisclosure personFinIntDisclosure) {
        this.personFinIntDisclosure = personFinIntDisclosure;
    }

    public PersonFinIntDisclosureAssociate() {
        super();
    }
    
    public void setPersonFinIntDisclosure(PersonFinIntDisclosure personFinIntDisclosure) {
        this.personFinIntDisclosure = personFinIntDisclosure;
        if (personFinIntDisclosure != null) {
            setSequenceNumber(personFinIntDisclosure.getSequenceNumber());
            setEntityNumber(personFinIntDisclosure.getEntityNumber());
        } else {
            setSequenceNumber(0);
            setEntityNumber("");
        }
    }
    
    public String getEntityNumber() {
        if ((StringUtils.isBlank(entityNumber) ||  personFinIntDisclosure != null && StringUtils.isNotBlank(personFinIntDisclosure.getEntityNumber()))) {
            setEntityNumber(personFinIntDisclosure.getEntityNumber());
        }
        return entityNumber;    
     }


    public void setEntityNumber(String entityNumber) {
        this.entityNumber = entityNumber;
    }


    public PersonFinIntDisclosure getPersonFinIntDisclosure() {
        return personFinIntDisclosure;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((entityNumber == null) ? 0 : entityNumber.hashCode());
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
        PersonFinIntDisclosureAssociate other = (PersonFinIntDisclosureAssociate) obj;
        if (entityNumber == null) {
            if (other.entityNumber != null) {
                return false;
            }
        } else if (!entityNumber.equals(other.entityNumber)) {
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
    public PersonFinIntDisclosure getSequenceOwner() {
        return getPersonFinIntDisclosure();
    }

    /*
     * thisi s called before setting personFinIntDisclosure
     */
    @Override
    public void setSequenceOwner(PersonFinIntDisclosure newlyVersionedOwner) {
        setPersonFinIntDisclosure(newlyVersionedOwner);      
    }

}
