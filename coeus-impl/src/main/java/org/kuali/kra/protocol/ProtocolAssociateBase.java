/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.protocol;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * 
 * This class is to maintain repetitive coeus legacy code, protocolNumber &amp; sequenceNumber, for protocol Bos.
 * 
 * Much of this class is duplicated with ProtocolAssociateBase but there is no
 * way around that due to the limitations in rice, ojb, etc.
 */
public abstract class ProtocolAssociateBase extends KcPersistableBusinessObjectBase implements SequenceAssociate<ProtocolBase> {

    private static final long serialVersionUID = -4180835808867997880L;

    private String protocolNumber;

    private Integer sequenceNumber = Integer.valueOf(0);

    private Long protocolId;

    private ProtocolBase protocol;

    /**
     * Constructs a ProtocolAssociateBase setting protocol related fields.
     * 
     * @param protocol the ProtocolBase
     */
    public ProtocolAssociateBase(ProtocolBase protocol) {
        this.setProtocol(protocol);
    }

    /**
     * Constructs a ProtocolAssociateBase.
     * 
     * Assures that the sequence number is always set. Note that 
     * when protocol implements versioning,  the KC versioning API 
     * will need to manage sequenceNumber.
     * 
     */
    public ProtocolAssociateBase() {
        super();
    }

    /**
     * Gets the protocolId attribute. 
     * @return Returns the protocolId.
     */
    public Long getProtocolId() {
        return this.protocolId;
    }

    /**
     * Sets the protocolId attribute value.
     * @param protocolId The protocolId to set.
     */
    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    /**
     * Gets the protocol attribute. 
     * @return Returns the protocol.
     */
    public ProtocolBase getProtocol() {
        return this.protocol;
    }

    /**
     * Sets the protocol attribute value.
     * @param protocol The protocol to set.
     */
    public void setProtocol(ProtocolBase protocol) {
        this.protocol = protocol;
        this.initProtocolInfo(protocol);
    }

    /**
     * Sets the protocol id and protocolNumber from the passed in protocol.
     * @param aProtocol the ProtocolBase
     */
    private void initProtocolInfo(ProtocolBase aProtocol) {
        this.setProtocolId(aProtocol != null ? aProtocol.getProtocolId() : null);
        this.setProtocolNumber(aProtocol != null ? aProtocol.getProtocolNumber() : null);
        this.setSequenceNumber(aProtocol != null ? aProtocol.getSequenceNumber() : null);
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
        if ((StringUtils.isBlank(protocolNumber) || "0".equals(protocolNumber)) && protocol != null && StringUtils.isNotBlank(protocol.getProtocolNumber())) {
            setProtocolNumber(protocol.getProtocolNumber());
        }
        return this.protocolNumber;
    }

    /**
     * Sets the protocol number.
     * @param protocolNumber protocol number.
     */
    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.protocolNumber == null) ? 0 : this.protocolNumber.hashCode());
        result = prime * result + ((this.sequenceNumber == null) ? 0 : this.sequenceNumber.hashCode());
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProtocolAssociateBase other = (ProtocolAssociateBase) obj;
        if (this.protocolNumber == null) {
            if (other.protocolNumber != null) {
                return false;
            }
        } else if (!this.protocolNumber.equals(other.protocolNumber)) {
            return false;
        }
        if (this.sequenceNumber == null) {
            if (other.sequenceNumber != null) {
                return false;
            }
        } else if (!this.sequenceNumber.equals(other.sequenceNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public ProtocolBase getSequenceOwner() {
        return this.getProtocol();
    }

    @Override
    public void setSequenceOwner(ProtocolBase newlyVersionedOwner) {
        this.setProtocol(newlyVersionedOwner);
    }

    /**
     * This inits the object to an unpersisted state by calling {@link #resetPersistenceState()}
     * Also, sets the protocol, protocol id, sequence number, and protocol number.
     * @param aProtocol the protocol to init the object with.
     */
    public final void init(ProtocolBase aProtocol) {
        this.resetPersistenceState();
        this.setProtocol(aProtocol);
        this.postInitHook(aProtocol);
        this.setSequenceNumber(0);
    }

    /**
     * This method is designed to allow subclasses to perform additional initialization not performed by the final init method.
     * This method is called by {@link #init(ProtocolBase)} after all other initialization is performed.
     * @param aProtocol the protocol initialization is requested with.
     */
    public void postInitHook(ProtocolBase aProtocol) {
    }
}
