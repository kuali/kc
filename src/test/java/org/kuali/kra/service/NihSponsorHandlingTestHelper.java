package org.kuali.kra.service;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.rice.kns.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.List;

class NihSponsorHandlingTestHelper {
    static final String SPONSOR_CODE_FOR_HIERARCHY_A = "005979"; // Google
    static final String SPONSOR_CODE_FOR_HIERARCHY_B = "004658"; // Upjohn

    private static final String NIH = "NIH";
    private static final String NOT_NIH = "Not NIH";

    private Sponsorable sponsorable;
    private BusinessObjectService boService;
    private SponsorService spService;
    private KeyPersonnelService kpService;
    private static final String SPONSOR_HIERARCHY_NAME = "Sponsor Groups";

    NihSponsorHandlingTestHelper(Sponsorable sponsorable, BusinessObjectService boService, SponsorService spService, KeyPersonnelService kpService) {
        this.sponsorable = sponsorable;
        this.boService = boService;
        this.spService = spService;
        this.kpService = kpService;
        init();
    }

    public void testIsSponsorNih_DevelopmentProposal_NoLevelValuesSpecified() {
        assignLevelValues(getSponsorHierarchyNodes().get(0),  new String[] {null, null, null, null, null, null, null, null, null, null});
        verifyNonNihSponsorFound(getSponsorHierarchyNodes().get(0));
    }

    public void testIsSponsorNih_DevelopmentProposal_NoLevelWithNIH() {
        assignLevelValues(getSponsorHierarchyNodes().get(0),  new String[] {NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH});
        verifyNonNihSponsorFound(getSponsorHierarchyNodes().get(0));
    }

    public void testIsSponsorNih_DevelopmentProposal_Level1_NIH_AnyNode() {
        assignLevelValues(getSponsorHierarchyNodes().get(0),  new String[] {NIH, null, null, null, null, null, null, null, null, null});
        assignLevelValues(getSponsorHierarchyNodes().get(1),  new String[] {null, null, null, null, null, null, null, null, null, null});
        verifyNihSponsorFound(getSponsorHierarchyNodes().get(0));
        verifyNonNihSponsorFound(getSponsorHierarchyNodes().get(1));

        assignLevelValues(getSponsorHierarchyNodes().get(0),  new String[] {null, null, null, null, null, null, null, null, null, null});
        assignLevelValues(getSponsorHierarchyNodes().get(1),  new String[] {NIH, null, null, null, null, null, null, null, null, null});
        verifyNonNihSponsorFound(getSponsorHierarchyNodes().get(0));
        verifyNihSponsorFound(getSponsorHierarchyNodes().get(1));
    }

    public void testIsSponsorNih_DevelopmentProposal_Level2_NIH() {
        assignLevelValues(getSponsorHierarchyNodes().get(0),  new String[] {NOT_NIH, NIH, null, null, null, null, null, null, null, null});
        verifyNihSponsorFound(getSponsorHierarchyNodes().get(0));
    }

    public void testIsSponsorNih_DevelopmentProposal_Level3_NIH() {
        assignLevelValues(getSponsorHierarchyNodes().get(0),  new String[] {NOT_NIH, NOT_NIH, NIH, null, null, null, null, null, null, null});
        verifyNihSponsorFound(getSponsorHierarchyNodes().get(0));
    }

    public void testIsSponsorNih_DevelopmentProposal_Level4_NIH() {
        assignLevelValues(getSponsorHierarchyNodes().get(0),  new String[] {NOT_NIH, NOT_NIH, NOT_NIH, NIH, null, null, null, null, null, null});
        verifyNihSponsorFound(getSponsorHierarchyNodes().get(0));
    }

    public void testIsSponsorNih_DevelopmentProposal_Level5_NIH() {
        assignLevelValues(getSponsorHierarchyNodes().get(0),  new String[] {NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NIH, null, null, null, null, null});
        verifyNihSponsorFound(getSponsorHierarchyNodes().get(0));
    }

    public void testIsSponsorNih_DevelopmentProposal_Level6_NIH() {
        assignLevelValues(getSponsorHierarchyNodes().get(0),  new String[] {NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NIH, null, null, null, null});
        verifyNihSponsorFound(getSponsorHierarchyNodes().get(0));
    }

    public void testIsSponsorNih_DevelopmentProposal_Level7_NIH() {
        assignLevelValues(getSponsorHierarchyNodes().get(0),  new String[] {NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NIH, null, null, null});
        verifyNihSponsorFound(getSponsorHierarchyNodes().get(0));
    }

    public void testIsSponsorNih_DevelopmentProposal_Level8_NIH() {
        assignLevelValues(getSponsorHierarchyNodes().get(0),  new String[] {NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NIH, null, null});
        verifyNihSponsorFound(getSponsorHierarchyNodes().get(0));
    }

    public void testIsSponsorNih_DevelopmentProposal_Level9_NIH() {
        assignLevelValues(getSponsorHierarchyNodes().get(0),  new String[] {NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NIH, null});
        verifyNihSponsorFound(getSponsorHierarchyNodes().get(0));
    }

    public void testIsSponsorNih_DevelopmentProposal_Level10_NIH() {
        assignLevelValues(getSponsorHierarchyNodes().get(0),  new String[] {NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NIH});
        verifyNihSponsorFound(getSponsorHierarchyNodes().get(0));
    }

    public void testNihDescriptionsAssigned() {
        assignLevelValues(getSponsorHierarchyNodes().get(0),  new String[] {NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NOT_NIH, NIH});
        verifyNihSponsorFound(getSponsorHierarchyNodes().get(0));
        sponsorable.setNihDescription(kpService.loadKeyPersonnelRoleDescriptions(true));
        Assert.assertEquals(3, sponsorable.getNihDescription().size());
        Assert.assertEquals("PI/Contact", sponsorable.getNihDescription().get("PI"));
        Assert.assertEquals("PI/Multiple", sponsorable.getNihDescription().get("COI"));
        Assert.assertEquals("Key Person", sponsorable.getNihDescription().get("KP"));
    }

    private void assignLevelValues(SponsorHierarchy sponsorHierarchyNode, String[] values) {
        sponsorHierarchyNode.setLevel1(values[0]);
        sponsorHierarchyNode.setLevel2(values[1]);
        sponsorHierarchyNode.setLevel3(values[2]);
        sponsorHierarchyNode.setLevel4(values[3]);
        sponsorHierarchyNode.setLevel5(values[4]);
        sponsorHierarchyNode.setLevel6(values[5]);
        sponsorHierarchyNode.setLevel7(values[6]);
        sponsorHierarchyNode.setLevel8(values[7]);
        sponsorHierarchyNode.setLevel9(values[8]);
        sponsorHierarchyNode.setLevel10(values[9]);
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

        sponsorHierarchyNode = new SponsorHierarchy();
        sponsorHierarchyNode.setHierarchyName(SPONSOR_HIERARCHY_NAME);
        sponsorHierarchyNode.setSponsorCode(SPONSOR_CODE_FOR_HIERARCHY_B);
        boService.save(sponsorHierarchyNode);
    }

    private void save(SponsorHierarchy node) {
        boService.save(node);
    }

    private void verifyNihSponsorFound(SponsorHierarchy sponsorHierarchy) {
        save(sponsorHierarchy);
        sponsorable.setSponsorCode(sponsorHierarchy.getSponsorCode());
        Assert.assertTrue(spService.isSponsorNih(sponsorable));
    }

    private void verifyNonNihSponsorFound(SponsorHierarchy sponsorHierarchy) {
        save(sponsorHierarchy);
        sponsorable.setSponsorCode(sponsorHierarchy.getSponsorCode());
        Assert.assertFalse(spService.isSponsorNih(sponsorable));
    }
}
