package org.kuali.kra.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.service.impl.SponsorServiceImpl;
import org.kuali.kra.service.impl.adapters.BusinessObjectServiceAdapter;
import org.kuali.kra.service.impl.adapters.KeyPersonnelServiceAdapter;
import org.kuali.kra.service.impl.adapters.ParameterServiceAdapter;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;

public class NihSponsorHandlingUnitTest {
    private NihSponsorHandlingTestHelper helper;
    private List<ProposalPersonRole> roles;

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
        helper = new NihSponsorHandlingTestHelper(sponsorable, bos, getSponsorService(bos), getKeyPersonnelService());
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

    private List<ProposalPersonRole> defineRoles() {
        List<ProposalPersonRole> roles = new ArrayList<ProposalPersonRole>();
        ProposalPersonRole role = new ProposalPersonRole();
        role.setProposalPersonRoleId("PI");
        role.setDescription(NONNIH_PI_DESCRIPTION);
        roles.add(role);

        role = new ProposalPersonRole();
        role.setProposalPersonRoleId("COI");
        role.setDescription(NONNIH_COI_DESCRIPTION);
        roles.add(role);

        role = new ProposalPersonRole();
        role.setProposalPersonRoleId("KP");
        role.setDescription(KEY_PERSON_DESCRIPTION);
        roles.add(role);
        return roles;
    }

    private BusinessObjectService getBusinessObjectService() {
        final Map<String, SponsorHierarchy> sponsorHierarchies = new HashMap<String, SponsorHierarchy>();

        return new BusinessObjectServiceAdapter() {
            public Collection findAll(Class klass) {
                if(ProposalPersonRole.class.equals(klass)) {
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

    private SponsorService getSponsorService(BusinessObjectService bos) {
        SponsorServiceImpl impl = new SponsorServiceImpl();
        impl.setBusinessObjectService(bos);
        impl.setParameterService(getParameterService());
        return impl;
    }
}