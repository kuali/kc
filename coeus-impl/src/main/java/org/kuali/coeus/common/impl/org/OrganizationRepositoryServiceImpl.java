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
package org.kuali.coeus.common.impl.org;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.org.OrganizationRepositoryService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("organizationRepositoryService")
public class OrganizationRepositoryServiceImpl implements OrganizationRepositoryService {

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;

    @Override
    public String getCognizantFedAgency(OrganizationContract organization) {
        if (organization == null) {
            throw new IllegalArgumentException("organization is null");
        }

        final StringBuilder fedAgency = new StringBuilder();
        if (organization.getCognizantAuditor() != null) {
            RolodexContract rolodex = rolodexService.getRolodex(organization.getCognizantAuditor());
            if (rolodex != null) {
                fedAgency.append(rolodex.getOrganization());
                fedAgency.append(", ");
                fedAgency.append(StringUtils.trimToEmpty(rolodex.getFirstName()));
                fedAgency.append(" ");
                fedAgency.append(StringUtils.trimToEmpty(rolodex.getLastName()));
                fedAgency.append(" ");
                if (rolodex.getPhoneNumber() != null) {
                    if (rolodex.getPhoneNumber().length() < 180) {
                        fedAgency.append(rolodex.getPhoneNumber());
                    } else {
                        fedAgency.append(rolodex.getPhoneNumber().substring(0, 180));
                    }
                }
            }
        }
        return fedAgency.toString();
    }

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }
}
