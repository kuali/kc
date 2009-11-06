package org.kuali.kra.bo;

import org.kuali.kra.service.impl.adapters.BusinessObjectServiceAdapter;
import org.kuali.kra.service.impl.adapters.IdentityServiceAdapter;
import org.kuali.rice.kim.bo.entity.dto.KimEntityInfo;
import org.kuali.rice.kim.service.IdentityService;
import org.kuali.rice.kns.service.BusinessObjectService;

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
     * 
     * @param personId
     * @param boService
     * @param identityService
     * @return
     */
    public static KcPerson createKcPerson(String personId, BusinessObjectService boService, IdentityService identityService) {
        KcPerson person = new KcPerson();
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
            public KimEntityInfo getEntityInfoByPrincipalId(String principalId) {
                KimEntityInfo entity = new KimEntityInfo();
                entity.setEntityId(principalId);
                return entity;
            }
        };
    }
}
