package org.kuali.coeus.s2sgen.impl.person;


import org.kuali.coeus.common.api.org.OrganizationContract;
import org.kuali.coeus.common.api.person.KcPersonContract;
import org.kuali.coeus.common.api.person.KcPersonRepositoryService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.api.unit.UnitContract;
import org.kuali.coeus.common.api.unit.UnitRepositoryService;
import org.kuali.coeus.common.api.unit.admin.UnitAdministratorContract;
import org.kuali.coeus.instprop.api.admin.ProposalAdminDetailsContract;
import org.kuali.coeus.instprop.api.admin.ProposalAdminDetailsService;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;

import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("departmentalPersonService")
public class DepartmentalPersonServiceImpl implements DepartmentalPersonService {

    private static final String CONTACT_TYPE_O = "O";

    @Autowired
    @Qualifier("proposalAdminDetailsService")
    private ProposalAdminDetailsService proposalAdminDetailsService;

    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;

    @Autowired
    @Qualifier("kcPersonRepositoryService")
    private KcPersonRepositoryService kcPersonRepositoryService;

    @Autowired
    @Qualifier("unitRepositoryService")
    private UnitRepositoryService unitRepositoryService;

    @Autowired
    @Qualifier("s2SConfigurationService")
    private S2SConfigurationService s2SConfigurationService;

    /**
     * This method populates and returns the Departmental Person object for a given proposal document
     *
     * @param pdDoc Proposal Development Document.
     * @return DepartmentalPerson departmental Person object for a given proposal document.
     */
    @Override
    public DepartmentalPersonDto getDepartmentalPerson(ProposalDevelopmentDocumentContract pdDoc) {
        int count = 0;
        DepartmentalPersonDto depPerson = new DepartmentalPersonDto();
        List<? extends ProposalAdminDetailsContract> proposalAdminDetailsList = proposalAdminDetailsService.findProposalAdminDetailsByPropDevNumber(pdDoc.getDevelopmentProposal().getProposalNumber());
        count = proposalAdminDetailsList.size();
        if (count < 1) {
            // Proposal has not been submitted

            OrganizationContract organization = pdDoc.getDevelopmentProposal().getApplicantOrganization().getOrganization();
            RolodexContract rolodex = organization == null ? null : rolodexService.getRolodex(organization.getContactAddressId());
            if (rolodex != null) {
                depPerson.setFirstName(rolodex.getFirstName());
                depPerson.setMiddleName(rolodex.getMiddleName());
                depPerson.setLastName(rolodex.getLastName());
                StringBuilder fullName = new StringBuilder();
                if (rolodex.getFirstName() != null) {
                    fullName.append(rolodex.getFirstName());
                    fullName.append(" ");
                }
                if (rolodex.getMiddleName() != null) {
                    fullName.append(rolodex.getMiddleName());
                    fullName.append(" ");
                }
                if (rolodex.getLastName() != null) {
                    fullName.append(rolodex.getLastName());
                }
                depPerson.setFullName(fullName.toString());

                depPerson.setEmailAddress(rolodex.getEmailAddress());
                depPerson.setOfficePhone(rolodex.getPhoneNumber());
                depPerson.setPrimaryTitle(rolodex.getTitle());
                depPerson.setAddress1(rolodex.getAddressLine1());
                depPerson.setAddress2(rolodex.getAddressLine2());
                depPerson.setAddress3(rolodex.getAddressLine3());
                depPerson.setCity(rolodex.getCity());
                depPerson.setCounty(rolodex.getCounty());
                depPerson.setCountryCode(rolodex.getCountryCode());
                depPerson.setFaxNumber(rolodex.getFaxNumber());
                depPerson.setPostalCode(rolodex.getPostalCode());
                depPerson.setState(rolodex.getState());
                depPerson.setPersonId(Integer.toString(rolodex.getRolodexId()));
                depPerson.setDirDept(organization.getOrganizationName());
            }
        }
        else {
            ProposalAdminDetailsContract proposalAdminDetails = proposalAdminDetailsList.get(0);
            KcPersonContract person = this.kcPersonRepositoryService.findKcPersonByUserName(proposalAdminDetails.getSignedBy());

            if (person != null) {
                depPerson.setFirstName(person.getFirstName());
                depPerson.setMiddleName(person.getMiddleName());
                depPerson.setLastName(person.getLastName());
                depPerson.setFullName(person.getFullName());
                depPerson.setEmailAddress(person.getEmailAddress());
                depPerson.setOfficePhone(person.getPhoneNumber());
                depPerson.setPrimaryTitle(person.getPrimaryTitle());
                depPerson.setAddress1(person.getAddressLine1());
                depPerson.setAddress2(person.getAddressLine2());
                depPerson.setAddress3(person.getAddressLine3());
                depPerson.setCity(person.getCity());
                depPerson.setCounty(person.getCounty());
                depPerson.setCountryCode(person.getCountryCode());
                depPerson.setFaxNumber(person.getFaxNumber());
                depPerson.setPostalCode(person.getPostalCode());
                depPerson.setState(person.getState());
                depPerson.setPersonId(person.getPersonId());
                depPerson.setDirDept(person.getContactOrganizationName());
            }
        }
        return depPerson;
    }

    /**
     *
     * This method is used to get the details of Contact person
     *
     * @param pdDoc(ProposalDevelopmentDocument) proposal development document.
     * @return depPerson(DepartmentalPerson) corresponding to the contact type.
     */
    @Override
    public DepartmentalPersonDto getContactPerson(ProposalDevelopmentDocumentContract pdDoc) {
        String contactType = getContactType();
        boolean isNumber = true;
        try {
            Integer.parseInt(contactType);
        }
        catch (NumberFormatException e) {
            isNumber = false;
        }
        DepartmentalPersonDto depPerson = new DepartmentalPersonDto();
        if (isNumber) {
            UnitContract leadUnit = pdDoc.getDevelopmentProposal().getOwnedByUnit();
            if (leadUnit!=null) {
                KcPersonContract unitAdmin = null;
                for (UnitAdministratorContract admin : leadUnit.getUnitAdministrators()) {
                    if (contactType.equals(admin.getUnitAdministratorType().getCode())) {
                        unitAdmin = kcPersonRepositoryService.findKcPersonByPersonId(admin.getPersonId());
                        depPerson.setLastName(unitAdmin.getLastName());
                        depPerson.setFirstName(unitAdmin.getFirstName());
                        if (unitAdmin.getMiddleName() != null) {
                            depPerson.setMiddleName(unitAdmin.getMiddleName());
                        }
                        depPerson.setEmailAddress(unitAdmin.getEmailAddress());
                        depPerson.setOfficePhone(unitAdmin.getOfficePhone());
                        depPerson.setFaxNumber(unitAdmin.getFaxNumber());
                        depPerson.setPrimaryTitle(unitAdmin.getPrimaryTitle());
                        depPerson.setAddress1(unitAdmin.getAddressLine1());
                        depPerson.setAddress2(unitAdmin.getAddressLine2());
                        depPerson.setAddress3(unitAdmin.getAddressLine3());
                        depPerson.setCity(unitAdmin.getCity());
                        depPerson.setCounty(unitAdmin.getCounty());
                        depPerson.setCountryCode(unitAdmin.getCountryCode());
                        depPerson.setPostalCode(unitAdmin.getPostalCode());
                        depPerson.setState(unitAdmin.getState());
                        break;
                    }
                }
                if (unitAdmin == null) {
                    UnitContract parentUnit = unitRepositoryService.findTopUnit();
                    for (UnitAdministratorContract parentAdmin : parentUnit.getUnitAdministrators()) {
                        if (contactType.equals(parentAdmin.getUnitAdministratorType().getCode())) {
                            KcPersonContract parentUnitAdmin = kcPersonRepositoryService.findKcPersonByPersonId(parentAdmin.getPersonId());
                            depPerson.setLastName(parentUnitAdmin.getLastName());
                            depPerson.setFirstName(parentUnitAdmin.getFirstName());
                            if (parentUnitAdmin.getMiddleName() != null) {
                                depPerson.setMiddleName(parentUnitAdmin.getMiddleName());
                            }
                            depPerson.setEmailAddress(parentUnitAdmin.getEmailAddress());
                            depPerson.setOfficePhone(parentUnitAdmin.getOfficePhone());
                            depPerson.setFaxNumber(parentUnitAdmin.getFaxNumber());
                            depPerson.setPrimaryTitle(parentUnitAdmin.getPrimaryTitle());
                            depPerson.setAddress1(parentUnitAdmin.getAddressLine1());
                            depPerson.setAddress2(parentUnitAdmin.getAddressLine2());
                            depPerson.setAddress3(parentUnitAdmin.getAddressLine3());
                            depPerson.setCity(parentUnitAdmin.getCity());
                            depPerson.setCounty(parentUnitAdmin.getCounty());
                            depPerson.setCountryCode(parentUnitAdmin.getCountryCode());
                            depPerson.setPostalCode(parentUnitAdmin.getPostalCode());
                            depPerson.setState(parentUnitAdmin.getState());
                            break;
                        }
                    }
                }
            }


        }
        return depPerson;
    }

    /**
     *
     * This method returns the type of contact person for a proposal
     *
     * @return String contact type for the proposal
     */
    protected String getContactType() {
        String contactType = s2SConfigurationService.getValueAsString(ConfigurationConstants.PROPOSAL_CONTACT_TYPE);
        if (contactType == null || contactType.length() == 0) {
            contactType = CONTACT_TYPE_O;
        }
        return contactType;
    }

    public ProposalAdminDetailsService getProposalAdminDetailsService() {
        return proposalAdminDetailsService;
    }

    public void setProposalAdminDetailsService(ProposalAdminDetailsService proposalAdminDetailsService) {
        this.proposalAdminDetailsService = proposalAdminDetailsService;
    }

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }

    public KcPersonRepositoryService getKcPersonRepositoryService() {
        return kcPersonRepositoryService;
    }

    public void setKcPersonRepositoryService(KcPersonRepositoryService kcPersonRepositoryService) {
        this.kcPersonRepositoryService = kcPersonRepositoryService;
    }

    public UnitRepositoryService getUnitRepositoryService() {
        return unitRepositoryService;
    }

    public void setUnitRepositoryService(UnitRepositoryService unitRepositoryService) {
        this.unitRepositoryService = unitRepositoryService;
    }

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
    }
}
