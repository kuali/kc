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
package org.kuali.kra.irb.correspondence;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class BatchCorrespondenceDetail extends KraPersistableBusinessObjectBase implements Comparable<BatchCorrespondenceDetail> {

    private static final long serialVersionUID = 1L;

    private Integer batchCorrespondenceDetailId;

    private String batchCorrespondenceTypeCode;

    private String protoCorrespTypeCode;

    private Integer daysToEvent;

    private ProtocolCorrespondenceType protocolCorrespondenceType;

    public BatchCorrespondenceDetail() {
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

    public ProtocolCorrespondenceType getProtocolCorrespondenceType() {
        return protocolCorrespondenceType;
    }

    public void setProtocolCorrespondenceType(ProtocolCorrespondenceType protocolCorrespondenceType) {
        this.protocolCorrespondenceType = protocolCorrespondenceType;
    }

    public int compareTo(BatchCorrespondenceDetail arg) {
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
