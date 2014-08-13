package org.kuali.kra.service;

import org.junit.Assert;
import org.kuali.coeus.common.framework.sponsor.Sponsorable;
import org.kuali.coeus.common.framework.sponsor.hierarchy.SponsorHierarchy;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.List;

class NihSponsorHandlingTestHelper {
    static final String SPONSOR_CODE_FOR_HIERARCHY_A = "005979"; // Google
    static final String SPONSOR_CODE_FOR_HIERARCHY_B = "000340"; // NIH
    
    private Sponsorable sponsorable;
    private BusinessObjectService boService;
    private SponsorHierarchyService spService;
    private static final String SPONSOR_HIERARCHY_NAME = "Sponsor Groups";

    NihSponsorHandlingTestHelper(Sponsorable sponsorable, BusinessObjectService boService, SponsorHierarchyService spService, KeyPersonnelService kpService) {
        this.sponsorable = sponsorable;
        this.boService = boService;
        this.spService = spService;
        init();
    }

    public void testIsSponsorNihMultiplePi_DevelopmentProposal_NoSponsorAdded() {
        verifyNonNihMultiplePiSponsorFound(getSponsorHierarchyNodes().get(0));
    }
    
    public void testIsSponsorNihMultiplePi_DevelopmentProposal_SponsorAdded() {
        setUpSponsorNihMultiplePi();
        verifyNihMultiplePiSponsorFound(getSponsorHierarchyNodes().get(1));
    }

    private void init() {
        prepareSponsorHierarchy();
    }

    @SuppressWarnings("unchecked")
    private List<SponsorHierarchy> getSponsorHierarchyNodes() {
        return new ArrayList<SponsorHierarchy>(boService.findAll(SponsorHierarchy.class));
    }

    private void prepareSponsorHierarchy() {
        SponsorHierarchy sponsorHierarchyNode = new SponsorHierarchy();
        sponsorHierarchyNode.setHierarchyName(SPONSOR_HIERARCHY_NAME);
        sponsorHierarchyNode.setSponsorCode(SPONSOR_CODE_FOR_HIERARCHY_A);
        boService.save(sponsorHierarchyNode);
    }
    
    public void setUpSponsorNihMultiplePi() {
        SponsorHierarchy sponsorHierarchyNode = new SponsorHierarchy();
        sponsorHierarchyNode.setHierarchyName(SponsorHierarchyService.SPONSOR_HIERARCHY_NIH_MULT_PI);
        sponsorHierarchyNode.setSponsorCode(SPONSOR_CODE_FOR_HIERARCHY_B);
        boService.save(sponsorHierarchyNode);
    }
    private void save(SponsorHierarchy node) {
        boService.save(node);
    }

    private void verifyNihMultiplePiSponsorFound(SponsorHierarchy sponsorHierarchy) {
        save(sponsorHierarchy);
        sponsorable.setSponsorCode(sponsorHierarchy.getSponsorCode());
        Assert.assertTrue(spService.isSponsorNihMultiplePi(sponsorable.getSponsorCode()));
    }

    private void verifyNonNihMultiplePiSponsorFound(SponsorHierarchy sponsorHierarchy) {
        save(sponsorHierarchy);
        sponsorable.setSponsorCode(sponsorHierarchy.getSponsorCode());
        Assert.assertFalse(spService.isSponsorNihMultiplePi(sponsorable.getSponsorCode()));
    }
}
