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
package org.kuali.kra.bo;

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.kra.service.impl.adapters.BusinessObjectServiceAdapter;
import org.kuali.kra.service.impl.adapters.IdentityServiceAdapter;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.entity.Entity;
import org.kuali.rice.krad.service.BusinessObjectService;

public class KcPersonFixtureFactory {

    /**
     *
     * @param personId
     * @return
     */
    public static KcPerson createKcPerson(String personId) {
        return createKcPerson(personId, getMockBusinessObjectService(), getMockIdentityService());
    }

    /**
     * This method returns "Joe Tester" as the KcPerson name. Kim integration doesn't allow the setting of the name 
     * @param personId
     * @param boService
     * @param identityService
     * @return
     */
    public static KcPerson createKcPerson(String personId, BusinessObjectService boService, IdentityService identityService) {
        KcPerson person = new KcPerson() {
            public String getFirstName() {
                return "Joe";
            }
            public String getLastName() {
                return "Tester";
            }
            public String getMiddleName() {
                return "";
            }
        };
        person.setBusinessObjectService(boService);
        person.setIdentityService(identityService);
        person.setPersonId(personId);

        return person;
    }

    private static BusinessObjectService getMockBusinessObjectService() {
        return new BusinessObjectServiceAdapter();
    }

    private static IdentityService getMockIdentityService() {
        return new IdentityServiceAdapter() {
            @Override
            public Entity getEntityByPrincipalId(String principalId) {
                Entity.Builder entityBuilder = Entity.Builder.create();
                entityBuilder.setId(principalId);
                return entityBuilder.build();
            }
        };
    }
}
