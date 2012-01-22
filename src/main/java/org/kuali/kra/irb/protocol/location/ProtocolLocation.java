/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.protocol.location;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.irb.ProtocolAssociate;

/**
 * This class represents the Protocol Location Business Object.
 */
public class ProtocolLocation extends ProtocolAssociate {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6509347537504066578L;

    private Integer protocolLocationId;

    private String protocolOrganizationTypeCode;

    private String organizationId;

    private Integer rolodexId;

    private Rolodex rolodex;

    private Organization organization;

    private ProtocolOrganizationType protocolOrganizationType;

    /**
	 * Constructs a ProtocolLocation.java.
	 */
    public ProtocolLocation() {
    }

    public Integer getProtocolLocationId() {
        return protocolLocationId;
    }

    public void setProtocolLocationId(Integer protocolLocationId) {
        this.protocolLocationId = protocolLocationId;
    }

    public String getProtocolOrganizationTypeCode() {
        return protocolOrganizationTypeCode;
    }

    public void setProtocolOrganizationTypeCode(String protocolOrganizationTypeCode) {
        this.protocolOrganizationTypeCode = protocolOrganizationTypeCode;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public ProtocolOrganizationType getProtocolOrganizationType() {
        return protocolOrganizationType;
    }

    public void setProtocolOrganizationType(ProtocolOrganizationType protocolOrganizationType) {
        this.protocolOrganizationType = protocolOrganizationType;
    }

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    /** {@inheritDoc} */
    public void resetPersistenceState() {
        this.setProtocolLocationId(null);
    }
}
