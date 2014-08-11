/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.attachmentsV11.AttachmentsDocument;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument.LobbyingActivitiesDisclosure;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument.LobbyingActivitiesDisclosure.*;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument.LobbyingActivitiesDisclosure.IndividualsPerformingServices.Individual;
import gov.grants.apply.forms.sflllV10.LobbyingActivitiesDisclosureDocument.LobbyingActivitiesDisclosure.ReportEntity.Prime;
import gov.grants.apply.forms.sflllV10.ReportDataType;
import gov.grants.apply.forms.sflllV10.ReportEntityDataType;
import gov.grants.apply.system.globalLibraryV10.HumanNameDataType;
import gov.grants.apply.system.globalLibraryV10.YesNoDataType;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.sponsor.SponsorContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonDto;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;


import java.util.Calendar;

/**
 * 
 * This class is used to generate XML Document object for grants.gov SFLLLV1.0. This form is generated using XMLBean API's generated
 * by compiling SFLLLV1.0 schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("SFLLLV1_0Generator")
public class SFLLLV1_0Generator extends SFLLLBaseGenerator {

    private DepartmentalPersonDto aorInfo;

    @Value("http://apply.grants.gov/forms/SFLLL-V1.0")
    private String namespace;

    @Value("SFLLL-V1.0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/SFLLL-V1.0.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.sflllV10")
    private String packageName;

    @Value(DEFAULT_SORT_INDEX)
    private int sortIndex;

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
        lobbyingDisclosure.setFormVersion(FormVersion.v1_0.getVersion());
        lobbyingDisclosure.setFederalActionType(FederalActionType.GRANT);
        lobbyingDisclosure.setFederalActionStatus(FederalActionStatus.BID_OFFER);
        lobbyingDisclosure.setReportType(ReportDataType.INITIAL_FILING);
        lobbyingDisclosure.setReportEntity(getReportEntity());
        lobbyingDisclosure.setFederalAgencyDepartment("");
        SponsorContract sponsor = pdDoc.getDevelopmentProposal().getSponsor();
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
            String primeSponsorCode = pdDoc.getDevelopmentProposal().getPrimeSponsor().getSponsorCode();

            if (primeSponsorCode != null) {
                SponsorContract primeSponsor = pdDoc.getDevelopmentProposal().getPrimeSponsor();
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
        if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle() != null) {
            String announcementTitle;
            if (pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle()
                    .length() > PROGRAM_ANNOUNCEMENT_TITLE_MAX_LENGTH) {
                announcementTitle = pdDoc.getDevelopmentProposal()
                        .getProgramAnnouncementTitle().substring(0,PROGRAM_ANNOUNCEMENT_TITLE_MAX_LENGTH);
            } else {
                announcementTitle = pdDoc.getDevelopmentProposal().getProgramAnnouncementTitle();
            }
            fedProgramName.setFederalProgramDescription(announcementTitle);
        }
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
        OrganizationContract organization = null;
        organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();

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
        RolodexContract rolodex = null;
        rolodex = pdDoc.getDevelopmentProposal().getApplicantOrganization().getRolodex();
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
        signatureBlock.setSignedDate(Calendar.getInstance());
        return signatureBlock;
    }


    /**
     * This method creates {@link XmlObject} of type {@link AttachmentsDocument} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        this.pdDoc = proposalDevelopmentDocument;
        aorInfo = departmentalPersonService.getDepartmentalPerson(pdDoc);
        return getLobbyingActivitiesDisclosure();
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public Resource getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Resource stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
