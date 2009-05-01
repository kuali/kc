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
package org.kuali.kra.irb;

import java.util.LinkedHashMap;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * 
 * This class is to maintain repetitive coeus legacy code, protocolNumber & sequenceNumber, for protocol Bos.
 */
@MappedSuperclass
public abstract class ProtocolAssociate extends KraPersistableBusinessObjectBase {
    private static final long serialVersionUID = -8385115657304261423L;
    
    @Column(name = "PROTOCOL_NUMBER")
    private String protocolNumber;

    @Column(name = "SEQUENCE_NUMBER")
    private Integer sequenceNumber;

    /**
     * 
     * Constructs a ProtocolAssociate.java.
     * 
     * Assures that the sequence number is always set. Note that 
     * when protocol implements versioning,  the KC versioning API 
     * will need to manage sequenceNumber.
     * 
     */
    public ProtocolAssociate() {
         this.setSequenceNumber(Integer.valueOf(0));
    }
    
    /**
     * Gets the sequence number.
     * @return sequence number.
     */
    public Integer getSequenceNumber() {
        return this.sequenceNumber;
    }

    /**
     * Sets the sequence number.
     * @param sequenceNumber sequence number.
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
    
    /**
     * Gets the protocol number.
     * @return protocol number.
     */
    public String getProtocolNumber() {
        return this.protocolNumber;
    }

    /**
     * Sets the protocol number.
     * @param protocolNumber protocol number.
     */
    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("protocolNumber", this.protocolNumber);
        map.put("sequenceNumber", this.sequenceNumber);
        return map;
    }

    /**
     * {@inheritDoc}
     */
// TODO : uncomment hashcode & equal methods when all bo implement their own hashcode & equal
//    @Override
//    public int hashCode() {
//        final int PRIME = 31;
//        int result = 1;
//        result = PRIME * result + ((this.protocolNumber == null) ? 0 : this.protocolNumber.hashCode());
//        result = PRIME * result + ((this.sequenceNumber == null) ? 0 : this.sequenceNumber.hashCode());
//        return result;
//    }

    /**
     * {@inheritDoc}
     */
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (!(obj instanceof ProtocolAssociate)) {
//            return false;
//        }
//        ProtocolAssociate other = (ProtocolAssociate) obj;
//        if (this.protocolNumber == null) {
//            if (other.protocolNumber != null) {
//                return false;
//            }
//        } else if (!this.protocolNumber.equals(other.protocolNumber)) {
//            return false;
//        }
//        if (this.sequenceNumber == null) {
//            if (other.sequenceNumber != null) {
//                return false;
//            }
//        } else if (!this.sequenceNumber.equals(other.sequenceNumber)) {
//            return false;
//        }
//        return true;
//    }
}
