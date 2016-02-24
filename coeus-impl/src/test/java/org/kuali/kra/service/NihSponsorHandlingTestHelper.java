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
