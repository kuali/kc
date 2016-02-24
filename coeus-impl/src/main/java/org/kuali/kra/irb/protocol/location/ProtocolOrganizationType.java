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
package org.kuali.kra.irb.protocol.location;

import org.kuali.kra.protocol.protocol.location.ProtocolOrganizationTypeBase;

/**
 * 
 * This class represents the Protocol Organization Type Business Object.
 */
public class ProtocolOrganizationType extends ProtocolOrganizationTypeBase {


    private static final long serialVersionUID = 148098563046181725L;

    private String protocolOrganizationTypeCode;

    private String description;

    /**
	 * Constructs a ProtocolOrganizationType.java.
	 */
    public ProtocolOrganizationType() {
    }


    public String getProtocolOrganizationTypeCode() {
        return protocolOrganizationTypeCode;
    }


    public void setProtocolOrganizationTypeCode(String protocolOrganizationTypeCode) {
        this.protocolOrganizationTypeCode = protocolOrganizationTypeCode;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }
}
