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
