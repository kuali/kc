/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.api.sponsor.Sponsorable;
import org.kuali.coeus.common.framework.sponsor.hierarchy.SponsorHierarchy;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.impl.sponsor.hierarchy.SponsorHierarchyServiceImpl;
import org.kuali.kra.service.impl.adapters.BusinessObjectServiceAdapter;
import org.kuali.kra.service.impl.adapters.KeyPersonnelServiceAdapter;
import org.kuali.kra.service.impl.adapters.ParameterServiceAdapter;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;

public class NihSponsorHandlingUnitTest {
    private NihSponsorHandlingTestHelper helper;
    private List<PropAwardPersonRole> roles;

    private static final String NIH = "NIH";
    private static final String GROUP_HIERARCHY_NAME = "Sponsor Groups";
    private static final String PI_PARM_NAME = "personrole.nih.pi";
    private static final String COI_PARM_NAME = "personrole.nih.coi";
    private static final String KEY_PERSON_PARM_NAME = "personrole.nih.kp";
    private static final String KEY_PERSON_DESCRIPTION = "Key Person";
    private static final String NIH_COI_DESCRIPTION = "PI/Multiple";
    private static final String NIH_PI_DESCRIPTION = "PI/Contact";
    private static final String NONNIH_COI_DESCRIPTION = "Co-Investigator";
    private static final String NONNIH_PI_DESCRIPTION = "Principal Investigator";

    @Before
    public void setUp() {
        roles = defineRoles();
        Sponsorable sponsorable = new Award();
        sponsorable.setSponsorCode(NihSponsorHandlingTestHelper.SPONSOR_CODE_FOR_HIERARCHY_A);
        BusinessObjectService bos = getBusinessObjectService();
        helper = new NihSponsorHandlingTestHelper(sponsorable, bos, getSponsorHierarchyService(bos), getKeyPersonnelService());
    }

    @After
    public void tearDown() {
        helper = null;
        roles = null;
    }
    
    @Test
    public void testIsSponsorNihMultiplePi_DevelopmentProposal_NoSponsorAdded() {
      helper.testIsSponsorNihMultiplePi_DevelopmentProposal_NoSponsorAdded();
    }
  
    @Test
    public void testIsSponsorNihMultiplePi_DevelopmentProposal_SponsorAdded() {
      helper.testIsSponsorNihMultiplePi_DevelopmentProposal_SponsorAdded();
    }

    @Test
    public void testIsPrimeSponsorNihMultiplePi_DevelopmentProposal_SponsorAdded() {
      helper.testIsPrimeSponsorNihMultiplePi_DevelopmentProposal_SponsorAdded();
    }

    private List<PropAwardPersonRole> defineRoles() {
        List<PropAwardPersonRole> roles = new ArrayList<PropAwardPersonRole>();
        PropAwardPersonRole role = new PropAwardPersonRole();
        role.setCode("PI");
        role.setDescription(NONNIH_PI_DESCRIPTION);
        roles.add(role);

        role = new PropAwardPersonRole();
        role.setCode("COI");
        role.setDescription(NONNIH_COI_DESCRIPTION);
        roles.add(role);

        role = new PropAwardPersonRole();
        role.setCode("KP");
        role.setDescription(KEY_PERSON_DESCRIPTION);
        roles.add(role);
        return roles;
    }

    private BusinessObjectService getBusinessObjectService() {
        final Map<String, SponsorHierarchy> sponsorHierarchies = new HashMap<String, SponsorHierarchy>();

        return new BusinessObjectServiceAdapter() {
            public Collection findAll(Class klass) {
                if(PropAwardPersonRole.class.equals(klass)) {
                    return roles;
                } else if(SponsorHierarchy.class.equals(klass)) {
                    return sponsorHierarchies.values();
                } else {
                    return null;
                }
            }
            public Collection findMatching(Class klass, Map fieldValues) {
                return SponsorHierarchy.class.equals(klass) ? sponsorHierarchies.values() : null;
            }
            public PersistableBusinessObject save(PersistableBusinessObject bo) {
                if(bo instanceof SponsorHierarchy) {
                    SponsorHierarchy sh = (SponsorHierarchy) bo;
                    sponsorHierarchies.put(String.format("%s:%s", sh.getSponsorCode(), sh.getHierarchyName()), sh);
                }
                return bo;
            }
            public int countMatching(Class clazz, Map fieldValues) {
                if(SponsorHierarchy.class.equals(clazz)){
                    String aValue = (String) fieldValues.get("hierarchyName");
                    if (sponsorHierarchies.values().toString().contains(aValue)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
                return 0;
            }
        };
    }

    public ParameterService getParameterService() {
        return new ParameterServiceAdapter() {
            public String getParameterValue(String namespaceCode, String detailTypeCode, String parameterName) {
                if(Constants.KC_GENERIC_PARAMETER_NAMESPACE.equals(namespaceCode) &&
                   ParameterConstants.ALL_COMPONENT.equals(detailTypeCode)) {

                    if(Constants.SPONSOR_HIERARCHY_NAME.equals(parameterName)) {
                        return GROUP_HIERARCHY_NAME;
                    } else if(Constants.SPONSOR_LEVEL_HIERARCHY.equals(parameterName)) {
                        return NIH;
                    } else if(PI_PARM_NAME.equals(parameterName)) {
                        return NIH_PI_DESCRIPTION;
                    } else if(COI_PARM_NAME.equals(parameterName)) {
                        return NIH_COI_DESCRIPTION;
                    } else if(KEY_PERSON_PARM_NAME.equals(parameterName)) {
                        return KEY_PERSON_DESCRIPTION;
                    } else {
                        return null;
                    }
                } else {
                    return super.getParameterValueAsString(namespaceCode, detailTypeCode, parameterName);
                }
            }
        };
    }

    private KeyPersonnelService getKeyPersonnelService() {
        return new KeyPersonnelServiceAdapter() {
            public Map<String, String> loadKeyPersonnelRoleDescriptions(boolean sponsorIsNih) {
                Map<String, String> results = new HashMap<String, String>();
                if(sponsorIsNih) {
                    results.put("PI", NIH_PI_DESCRIPTION);
                    results.put("COI", NIH_COI_DESCRIPTION);
                    results.put("KP", KEY_PERSON_DESCRIPTION);
                } else {
                    results.put("PI", NONNIH_PI_DESCRIPTION);
                    results.put("COI", NONNIH_COI_DESCRIPTION);
                    results.put("KP", KEY_PERSON_DESCRIPTION);
                }
                return results;
            }
        };
    }

    private SponsorHierarchyService getSponsorHierarchyService(BusinessObjectService bos) {
        SponsorHierarchyServiceImpl impl = new SponsorHierarchyServiceImpl();
        impl.setBusinessObjectService(bos);
        return impl;
    }
}
