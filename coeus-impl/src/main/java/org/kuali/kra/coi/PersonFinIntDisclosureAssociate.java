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
