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

import gov.grants.apply.forms.rrPerformanceSiteV10.RRPerformanceSiteDocument;
import gov.grants.apply.forms.rrPerformanceSiteV10.SiteLocationDataType;
import gov.grants.apply.forms.rrPerformanceSiteV10.RRPerformanceSiteDocument.RRPerformanceSite;
import gov.grants.apply.forms.rrPerformanceSiteV10.SiteLocationDataType.Address;
import gov.grants.apply.system.attachmentsV10.AttachedFileDataType;
import gov.grants.apply.system.universalCodesV10.CountryCodeType;

import java.util.List;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.util.S2SConstants;

/**
 * This Class is used to generate XML object for grants.gov RRPerformanceSiteV1.0. This form is generated using XMLBean classes and
 * is based on RRPerformanceSite schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RRPerformanceSiteV1_0Generator extends RRPerformanceSiteBaseGenerator {

    /**
     * 
     * This method gets RRPerformanceSite informations like OrganizationName,Address,PerformanceSite Attachment based on the
     * proposal Development document.
     * 
     * @return rrPerformanceSite {@link XmlObject} of type RRPerformanceSiteDocument.
     */
    private RRPerformanceSiteDocument getRRPerformanceSite() {
        RRPerformanceSiteDocument rrPerformanceSiteDocument = RRPerformanceSiteDocument.Factory.newInstance();
        RRPerformanceSite rrPerformanceSite = rrPerformanceSiteDocument.addNewRRPerformanceSite();
        rrPerformanceSite.setFormVersion(S2SConstants.FORMVERSION_1_0);
        
        List<ProposalSite> propsoalSites = pdDoc.getDevelopmentProposal().getProposalSites();
        SiteLocationDataType siteLocation = null;
        Organization organization = null;
        Rolodex rolodex = null;
        
        for (ProposalSite proposalSite : propsoalSites) {
            switch(proposalSite.getLocationTypeCode()){
                case(PERFORMING_ORG_LOCATION_TYPE_CODE):
                    siteLocation = rrPerformanceSite.addNewPrimarySite();
                    organization = proposalSite.getOrganization();
                    rolodex = organization.getRolodex();
                    break;
                case(OTHER_ORG_LOCATION_TYPE_CODE):
                    siteLocation = rrPerformanceSite.addNewOtherSite();
                    organization = proposalSite.getOrganization();
                    rolodex = organization.getRolodex();
                    break;
                case(PERFORMANCE_SITE_LOCATION_TYPE_CODE):
                    siteLocation = rrPerformanceSite.addNewOtherSite();
                    rolodex = proposalSite.getRolodex();
                    break;
            }
            if(siteLocation!=null){
                Address addressType = siteLocation.addNewAddress();
                setAddress(addressType,rolodex);
                siteLocation.setOrganizationName(proposalSite.getLocationName());
            }
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
        return rrPerformanceSiteDocument;
    }

    /**
     * This method is to set the rolodex details to AddressType
     * @param address
     * @param rolodex
     */
    private void setAddress(Address address, Rolodex rolodex) {
        if (rolodex != null) {
            address.setStreet1(rolodex.getAddressLine1());
            address.setStreet2(rolodex.getAddressLine2());
            address.setCity(checkNull(rolodex.getCity()));
            address.setCounty(rolodex.getCounty());
            address.setState(rolodex.getState());
            address.setZipCode(rolodex.getPostalCode());
            if (rolodex.getCountryCode() != null) {
                CountryCodeType.Enum country = CountryCodeType.Enum.forString(rolodex.getCountryCode());
                address.setCountry(country);
            }
        }else {
            address.setStreet1("");
            address.setCity("");
        }
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
        RRPerformanceSite rrPerformanceSite = (RRPerformanceSite) xmlObject;
        RRPerformanceSiteDocument rrDocument = RRPerformanceSiteDocument.Factory.newInstance();
        rrDocument.setRRPerformanceSite(rrPerformanceSite);
        return rrDocument;
    }
}
