package org.kuali.kra.bo;

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
     *
     * @param personId
     * @param boService
     * @return
     */
    public static KcPerson createKcPerson(String personId, BusinessObjectService boService) {
        return createKcPerson(personId, boService, getMockIdentityService());
    }

    /**
     *
     * @param personId
     * @param identityService
     * @return
     */
    public static KcPerson createKcPerson(String personId, IdentityService identityService) {
        return createKcPerson(personId, getMockBusinessObjectService(), identityService);
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
