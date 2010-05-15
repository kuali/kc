/*
 * Copyright 2005-2010 The Kuali Foundation.
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

import java.util.List;
import java.util.ArrayList;

import gov.grants.apply.forms.rrPerformanceSiteV11.RRPerformanceSiteDocument;
import gov.grants.apply.forms.rrPerformanceSiteV11.SiteLocationDataType;
import gov.grants.apply.forms.rrPerformanceSiteV11.RRPerformanceSiteDocument.RRPerformanceSite;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
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
            siteLocation.setOrganizationName(pdDoc.getDevelopmentProposal().getPerformingOrganization().getLocationName());
        }
        Rolodex rolodex = null;
        if (pdDoc.getDevelopmentProposal().getPerformingOrganization() != null) {
            rolodex = pdDoc.getDevelopmentProposal().getPerformingOrganization().getRolodex();
        }
        siteLocation.setAddress(globLibV20Generator.getAddressDataType(rolodex));
        rrPerformanceSite.setPrimarySite(siteLocation);
        int otherSiteCount = 0;
        List<SiteLocationDataType> siteLocationDataTypeArray = new ArrayList<SiteLocationDataType>();
        for (ProposalSite proposalSite: getProposalSitesFromProposal(ProposalSite.PROPOSAL_SITE_PERFORMANCE_SITE)) {
            SiteLocationDataType siteLocationOther = SiteLocationDataType.Factory.newInstance();
            Rolodex rolodex2 = proposalSite.getRolodex();
            if (rolodex2 != null) {
                siteLocationOther.setOrganizationName(rolodex2.getOrganization());
                siteLocationOther.setAddress(globLibV20Generator.getAddressDataType(rolodex2));
                siteLocationDataTypeArray.add(siteLocationOther);
                otherSiteCount++;
                LOG.info("otherSiteCount:" + otherSiteCount);
            }
        }
        if(!siteLocationDataTypeArray.isEmpty()){
            rrPerformanceSite.setOtherSiteArray(siteLocationDataTypeArray.toArray(new SiteLocationDataType[]{}));
        }

        for (Narrative narrative : pdDoc.getDevelopmentProposal().getNarratives()) {
            if (narrative.getNarrativeTypeCode() != null
                    && Integer.parseInt(narrative.getNarrativeTypeCode()) == PERFORMANCE_SITES_ATTACHMENT) {
            	AttachedFileDataType attachedFileDataType = getAttachedFileType(narrative);
            	if(attachedFileDataType != null){
            		rrPerformanceSite.setAttachedFile(attachedFileDataType);
            		break;
            	}
            }
        }
        rrPerformanceSiteDocument.setRRPerformanceSite(rrPerformanceSite);
        return rrPerformanceSiteDocument;
    }

    /**
     * This method...
     * @return
     */
    private List<ProposalSite> getProposalSitesFromProposal(int locationTypeCode) {
    	List<ProposalSite> proposalSiteList=new ArrayList<ProposalSite>();
    	for(ProposalSite proposalSite:pdDoc.getDevelopmentProposal().getProposalSites()){
    		if(proposalSite.getLocationTypeCode()== locationTypeCode){
    			proposalSiteList.add(proposalSite);
    		}
    	}
        return proposalSiteList;
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
