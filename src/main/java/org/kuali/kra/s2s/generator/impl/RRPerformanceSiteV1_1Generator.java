/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.s2s.generator.impl;

import gov.grants.apply.forms.rrPerformanceSiteV11.RRPerformanceSiteDocument;
import gov.grants.apply.forms.rrPerformanceSiteV11.SiteLocationDataType;
import gov.grants.apply.forms.rrPerformanceSiteV11.RRPerformanceSiteDocument.RRPerformanceSite;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * This Class is used to generate XML object for grants.gov RRPerformanceSiteV1.1. This form is generated using XMLBean classes and
 * is based on RRPerformanceSitev1.1 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RRPerformanceSiteV1_1Generator extends RRPerformanceSiteBaseGenerator {

    private static final Logger LOG = Logger.getLogger(RRPerformanceSiteV1_0Generator.class);

    /**
     * 
     * This method gets RRPerformanceSite informations like OrganizationName,Address,PerformanceSite Attachment based on the
     * proposal Development document.
     * 
     * @return rrPerformanceSite {@link XmlObject} of type RRPerformanceSiteDocument.
     */
    private RRPerformanceSiteDocument getRRPerformanceSite() {
        RRPerformanceSiteDocument rrPerformanceSiteDocument = RRPerformanceSiteDocument.Factory.newInstance();
        RRPerformanceSite rrPerformanceSite = RRPerformanceSite.Factory.newInstance();
        rrPerformanceSite.setFormVersion(S2SConstants.FORMVERSION_1_1);
        SiteLocationDataType siteLocation = SiteLocationDataType.Factory.newInstance();
        if (pdDoc.getDevelopmentProposal().getPerformingOrganization() != null) {
            siteLocation.setOrganizationName(pdDoc.getDevelopmentProposal().getPerformingOrganization().getOrganizationName());
        }
        Rolodex rolodex = null;
        if (pdDoc.getDevelopmentProposal().getPerformingOrganization() != null) {
            rolodex = pdDoc.getDevelopmentProposal().getPerformingOrganization().getRolodex();
        }
        siteLocation.setAddress(globLibV20Generator.getAddressDataType(rolodex));
        rrPerformanceSite.setPrimarySite(siteLocation);
        int otherSiteCount = 0;
        SiteLocationDataType[] siteLocationDataTypeArray = null;
        if (pdDoc.getDevelopmentProposal().getProposalLocations() != null) {
            siteLocationDataTypeArray = new SiteLocationDataType[pdDoc.getDevelopmentProposal().getProposalLocations().size()];
            for (ProposalLocation proposalLocation : pdDoc.getDevelopmentProposal().getProposalLocations()) {
                SiteLocationDataType siteLocationOther = SiteLocationDataType.Factory.newInstance();
                Rolodex rolodex2 = proposalLocation.getRolodex();
                if (rolodex2 != null) {
                    siteLocationOther.setOrganizationName(pdDoc.getDevelopmentProposal().getPerformingOrganization().getOrganizationName());
                    siteLocationOther.setAddress(globLibV20Generator.getAddressDataType(rolodex2));
                    siteLocationDataTypeArray[otherSiteCount] = siteLocationOther;
                    otherSiteCount++;
                    LOG.info("otherSiteCount:" + otherSiteCount);
                }
            }
        }
        rrPerformanceSite.setOtherSiteArray(siteLocationDataTypeArray);

        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && Integer.parseInt(narrative.getNarrativeTypeCode()) == PERFORMANCE_SITES_ATTACHMENT) {
                rrPerformanceSite.setAttachedFile(getAttachedFileType(narrative));
            }
        }
        rrPerformanceSiteDocument.setRRPerformanceSite(rrPerformanceSite);
        return rrPerformanceSiteDocument;
    }

    /**
     * This method creates {@link XmlObject} of type {@link RRPerformanceSiteDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        return getRRPerformanceSite();
    }

    /**
     * This method typecasts the given {@link XmlObject} to the required generator type and returns back the document of that
     * generator type.
     * 
     * @param xmlObject which needs to be converted to the document type of the required generator
     * @return {@link XmlObject} document of the required generator type
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(XmlObject)
     */
    public XmlObject getFormObject(XmlObject xmlObject) {
        RRPerformanceSite rrPerformanceSite = RRPerformanceSite.Factory.newInstance();
        RRPerformanceSiteDocument rrDocument = RRPerformanceSiteDocument.Factory.newInstance();
        rrDocument.setRRPerformanceSite(rrPerformanceSite);
        return rrDocument;
    }
}
