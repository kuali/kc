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
package org.kuali.kra.protocol.correspondence;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public abstract class BatchCorrespondenceDetailBase extends KcPersistableBusinessObjectBase implements Comparable<BatchCorrespondenceDetailBase> {

    private static final long serialVersionUID = 1L;

    private Integer batchCorrespondenceDetailId;

    private String batchCorrespondenceTypeCode;

    private String protoCorrespTypeCode;

    private Integer daysToEvent;

    private ProtocolCorrespondenceTypeBase protocolCorrespondenceType;

    public BatchCorrespondenceDetailBase() {
    }

    public Integer getBatchCorrespondenceDetailId() {
        return batchCorrespondenceDetailId;
    }

    public void setBatchCorrespondenceDetailId(Integer batchCorrespondenceDetailId) {
        this.batchCorrespondenceDetailId = batchCorrespondenceDetailId;
    }

    public String getBatchCorrespondenceTypeCode() {
        return batchCorrespondenceTypeCode;
    }

    public void setBatchCorrespondenceTypeCode(String batchCorrespondenceTypeCode) {
        this.batchCorrespondenceTypeCode = batchCorrespondenceTypeCode;
    }

    public String getProtoCorrespTypeCode() {
        return protoCorrespTypeCode;
    }

    public void setProtoCorrespTypeCode(String protoCorrespTypeCode) {
        this.protoCorrespTypeCode = protoCorrespTypeCode;
    }

    public Integer getDaysToEvent() {
        return daysToEvent;
    }

    public void setDaysToEvent(Integer daysToEvent) {
        this.daysToEvent = daysToEvent;
    }

    public ProtocolCorrespondenceTypeBase getProtocolCorrespondenceType() {
        return protocolCorrespondenceType;
    }

    public void setProtocolCorrespondenceType(ProtocolCorrespondenceTypeBase protocolCorrespondenceType) {
        this.protocolCorrespondenceType = protocolCorrespondenceType;
    }

    public int compareTo(BatchCorrespondenceDetailBase arg) {
        if(!this.getClass().isAssignableFrom(arg.getClass())) {
           throw new ClassCastException("Type mismatch while comparing two objects of type BatchCprrespondenceDetail"); 
        }
        int result = this.batchCorrespondenceTypeCode.compareTo(arg.batchCorrespondenceTypeCode);
        if (result == 0) {
            result = this.daysToEvent.compareTo(arg.daysToEvent);
        }
        if (result == 0) {
            result = this.protocolCorrespondenceType.getDescription().compareTo(arg.protocolCorrespondenceType.getDescription());
        }
        return result;
    }
    
}
