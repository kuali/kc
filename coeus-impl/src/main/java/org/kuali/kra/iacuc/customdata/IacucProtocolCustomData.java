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
package org.kuali.kra.iacuc.customdata;

import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.sys.api.model.IdentifiableNumeric;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class IacucProtocolCustomData extends KcPersistableBusinessObjectBase implements DocumentCustomData, IdentifiableNumeric {

    private static final long serialVersionUID = 8074330420210212533L;

    private Long iacucProtocolCustomDataId;
    private Long protocolId;
    private Long customAttributeId;
    private String value;

    private CustomAttribute customAttribute;

    public Long getIacucProtocolCustomDataId() {
        return iacucProtocolCustomDataId;
    }

    public void setIacucProtocolCustomDataId(Long iacucProtocolCustomDataId) {
        this.iacucProtocolCustomDataId = iacucProtocolCustomDataId;
    }

    public Long getprotocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public Long getCustomAttributeId() {
        return customAttributeId;
    }

    public void setCustomAttributeId(Long customAttributeId) {
        this.customAttributeId = customAttributeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CustomAttribute getCustomAttribute() {
        return customAttribute;
    }

    public void setCustomAttribute(CustomAttribute customAttribute) {
        this.customAttribute = customAttribute;
    }

	@Override
	public Long getId() {
		return customAttributeId;
	}

}