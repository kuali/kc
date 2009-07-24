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

import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument;
import gov.grants.apply.forms.sflllV10.ReportDataType;
import gov.grants.apply.forms.sflllV10.ReportEntityDataType;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument.LobbyingActivitiesDisclosure;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument.LobbyingActivitiesDisclosure.FederalActionStatus;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument.LobbyingActivitiesDisclosure.FederalActionType;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument.LobbyingActivitiesDisclosure.FederalProgramName;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument.LobbyingActivitiesDisclosure.IndividualsPerformingServices;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument.LobbyingActivitiesDisclosure.LobbyingRegistrant;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument.LobbyingActivitiesDisclosure.ReportEntity;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument.LobbyingActivitiesDisclosure.SignatureBlock;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument.LobbyingActivitiesDisclosure.IndividualsPerformingServices.Individual;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument.LobbyingActivitiesDisclosure.ReportEntity.Prime;
import gov.grants.apply.system.globalLibraryV10.HumanNameDataType;
import gov.grants.apply.system.globalLibraryV10.YesNoDataType;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.generator.bo.DepartmentalPerson;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class is used to generate XML Document object for grants.gov SFLLLV1.0. This form is generated using XMLBean API's generated
 * by compiling SFLLLV1.0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SFLLLV1_0Generator extends SFLLLBaseGenerator {

    private DepartmentalPerson aorInfo;

    /**
     * 
     * This method returns LobbyingActivitiesDisclosureDocument object based on proposal development document which contains the
     * LobbyingActivitiesDisclosureDocument informations
     * FederalActionType,FederalActionStatus,ReportType,ReportEntity,FederalAgencyDepartment,
     * FederalProgramName,LobbyingRegistrant,IndividualsPerformingServices,SignatureBlock and LobbyingActivitiesDisclosure for a
     * particular proposal
     * 
     * @return lobbyingADDocument {@link XmlObject} of type LobbyingActivitiesDisclosureDocument.
     */
    private LobbyingActivitiesDisclosureDocument getLobbyingActivitiesDisclosure() {

        LobbyingActivitiesDisclosureDocument lobbyingADDocument = LobbyingActivitiesDisclosureDocument.Factory.newInstance();
        LobbyingActivitiesDisclosure lobbyingDisclosure = LobbyingActivitiesDisclosure.Factory.newInstance();
        lobbyingDisclosure.setFormVersion(S2SConstants.FORMVERSION_1_0);
        lobbyingDisclosure.setFederalActionType(FederalActionType.GRANT);
        lobbyingDisclosure.setFederalActionStatus(FederalActionStatus.BID_OFFER);
        lobbyingDisclosure.setReportType(ReportDataType.INITIAL_FILING);
        lobbyingDisclosure.setReportEntity(getReportEntity());
        lobbyingDisclosure.setFederalAgencyDepartment("");
        Sponsor sponsor = pdDoc.getDevelopmentProposal().getSponsor();
        if (sponsor != null) {
            if (sponsor.getSponsorName() != null) {
                if (sponsor.getSponsorName().length() > SPONSOR_NAME_MAX_LENGTH) {
                    lobbyingDisclosure.setFederalAgencyDepartment(sponsor.getSponsorName().substring(0, SPONSOR_NAME_MAX_LENGTH));
                }
                else {
                    lobbyingDisclosure.setFederalAgencyDepartment(sponsor.getSponsorName());
                }
            }
        }
        else {
            String primeSponsorCode = pdDoc.getDevelopmentProposal().getPrimeSponsorCode();

            if (primeSponsorCode != null) {
                BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
                Map<String, String> sponsorMap = new HashMap<String, String>();
                sponsorMap.put(KEY_SPONSOR_CODE, primeSponsorCode);
                Sponsor primeSponsor = (Sponsor) businessObjectService.findByPrimaryKey(Sponsor.class, sponsorMap);
                if (primeSponsor.getSponsorName() != null) {
                    if (primeSponsor.getSponsorName().length() > SPONSOR_NAME_MAX_LENGTH) {
                        lobbyingDisclosure.setFederalAgencyDepartment(primeSponsor.getSponsorName().substring(0,
                                SPONSOR_NAME_MAX_LENGTH));
                    }
                    else {
                        lobbyingDisclosure.setFederalAgencyDepartment(primeSponsor.getSponsorName());
                    }
                }
            }
        }
        lobbyingDisclosure.setFederalProgramName(getFedProgramName());
        lobbyingDisclosure.setLobbyingRegistrant(getLobbyingRegistrant());
        lobbyingDisclosure.setIndividualsPerformingServices(getIndividualsPerformingServices());
        lobbyingDisclosure.setSignatureBlock(getSignatureBlock());
        lobbyingADDocument.setLobbyingActivitiesDisclosure(lobbyingDisclosure);
        return lobbyingADDocument;
    }

    /**
     * 
     * This method returns OrganizationName information for the LobbyingRegistrant.
     * 
     * @return LobbyingRegistrant organization name
     */
    private LobbyingRegistrant getLobbyingRegistrant() {

        LobbyingRegistrant lobbyingRegistrant = LobbyingRegistrant.Factory.newInstance();
        lobbyingRegistrant.setOrganizationName(NOT_APPLICABLE);
        return lobbyingRegistrant;
    }

    /**
     * 
     * This method returns IndividualsPerformingServices information which contains an array of individuals with individuals name
     * details.
     * 
     * @return IndividualsPerformingServices Individuals Performing Services
     */
    private IndividualsPerformingServices getIndividualsPerformingServices() {
        // we don't have info for Individuals Performing Services. This is a required field, so we set it to N/A and add 1 element
        // to array
        IndividualsPerformingServices individualServices = IndividualsPerformingServices.Factory.newInstance();
        Individual individual = Individual.Factory.newInstance();
        HumanNameDataType humanName = HumanNameDataType.Factory.newInstance();
        humanName.setFirstName(NOT_APPLICABLE);
        humanName.setLastName(NOT_APPLICABLE);
        Individual[] individualArray = new Individual[1];
        individual.setName(humanName);
        individualArray[0] = individual;
        individualServices.setIndividualArray(individualArray);
        return individualServices;
    }


    /**
     * 
     * This method returns FederalProgramName informations including ProgramAnnouncementTitle and CFDANumber for the
     * FederalProgramName.
     * 
     * @return FederalProgramName Federal Program details.
     */
    private FederalProgramName getFedProgramName() {

        FederalProgramName fedProgramName = FederalProgramName.Factory.newInstance();
        fedProgramName.setFederalProgramDescription(pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle());
        fedProgramName.setCFDANumber(pdDoc.getDevelopmentProposal().getCfdaNumber());

        return fedProgramName;
    }

    /**
     * 
     * This method returns ReportEntity informations including ReportEntityType,ReportEntityIsPrime and
     * OrganizationName,CongressionalDistrict information for the Prime.
     * 
     * @return ReportEntity object containing organization and Congressional District details for prime.
     */
    private ReportEntity getReportEntity() {

        ReportEntity reportEntity = ReportEntity.Factory.newInstance();
        reportEntity.setReportEntityType(ReportEntityDataType.PRIME);
        reportEntity.setReportEntityIsPrime(YesNoDataType.YES);
        Prime prime = Prime.Factory.newInstance();
        Organization organization = null;
        organization = pdDoc.getDevelopmentProposal().getOrganization();

        if (organization != null) {
            if (organization.getOrganizationName() != null) {
                if (organization.getOrganizationName().length() > ORGANIZATON_NAME_MAX_LENGTH) {
                    prime.setOrganizationName(organization.getOrganizationName().substring(0, ORGANIZATON_NAME_MAX_LENGTH));
                }
                else {
                    prime.setOrganizationName(organization.getOrganizationName());
                }
            }
            if (organization.getCongressionalDistrict() != null) {
                if (organization.getCongressionalDistrict().length() > CONGRESSIONAL_DISTRICT_MAX_LENGTH) {
                    prime.setCongressionalDistrict(organization.getCongressionalDistrict().substring(0,
                            CONGRESSIONAL_DISTRICT_MAX_LENGTH));
                }
                else {
                    prime.setCongressionalDistrict(organization.getCongressionalDistrict());
                }
            }
        }
        prime.setReportEntityType(ReportEntityDataType.PRIME);
        Rolodex rolodex = null;
        rolodex = pdDoc.getDevelopmentProposal().getOrganization().getRolodex();
        prime.setAddress(globLibV10Generator.getAddressDataType(rolodex));
        reportEntity.setPrime(prime);
        return reportEntity;
    }

    /**
     * 
     * This method returns SignatureBlock informations including Name,Title and Signature for the SignatureBlock.
     * 
     * @return SignatureBlock authorized representative details.
     */
    private SignatureBlock getSignatureBlock() {

        SignatureBlock signatureBlock = SignatureBlock.Factory.newInstance();
        signatureBlock.setName(globLibV10Generator.getHumanNameDataType(aorInfo));

        if (aorInfo.getPrimaryTitle() != null) {
            if (aorInfo.getPrimaryTitle().length() > PRIMARY_TITLE_MAX_LENGTH) {
                signatureBlock.setTitle(aorInfo.getPrimaryTitle().substring(0, PRIMARY_TITLE_MAX_LENGTH));
            }
            else {
                signatureBlock.setTitle(aorInfo.getPrimaryTitle());
            }
        }
        signatureBlock.setSignature(aorInfo.getFullName());
        signatureBlock.setSignedDate(s2sUtilService.getCurrentCalendar());
        return signatureBlock;
    }


    /**
     * This method creates {@link XmlObject} of type {@link AttachmentsDocument} by populating data from the given
     * {@link ProposalDevelopmentDocument}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocument}
     * @see org.kuali.kra.s2s.generator.S2SFormGenerator#getFormObject(ProposalDevelopmentDocument)
     */
    public XmlObject getFormObject(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        aorInfo = s2sUtilService.getDepartmentalPerson(pdDoc);
        return getLobbyingActivitiesDisclosure();
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
        LobbyingActivitiesDisclosureDocument lobbyingDisclosureDocument = LobbyingActivitiesDisclosureDocument.Factory
                .newInstance();
        LobbyingActivitiesDisclosure lobbyingActivitiesDisclosure = (LobbyingActivitiesDisclosure) xmlObject;
        lobbyingDisclosureDocument.setLobbyingActivitiesDisclosure(lobbyingActivitiesDisclosure);
        return lobbyingDisclosureDocument;
    }
}
