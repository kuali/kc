package org.kuali.coeus.s2sgen.impl.person;


import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;

import org.kuali.coeus.s2sgen.impl.citizenship.CitizenshipType;
import org.kuali.coeus.s2sgen.impl.citizenship.CitizenshipTypeService;
import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("s2SProposalPersonService")
public class S2SProposalPersonServiceImpl implements S2SProposalPersonService {

    @Autowired
    @Qualifier("s2SConfigurationService")
    private S2SConfigurationService s2SConfigurationService;

    @Autowired
    @Qualifier("citizenshipTypeService")
    private CitizenshipTypeService citizenshipTypeService;

    /**
     * This method limits the number of key persons to n, returns list of key persons, first n in case firstN is true, or all other
     * than first n, in case of firstN being false
     *
     * @param proposalPersons list of {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}
     * @param firstN value that determines whether the returned list should contain first n persons or the rest of persons
     * @param n number of key persons that are considered as not extra persons
     * @return list of {@link org.kuali.coeus.propdev.api.person.ProposalPersonContract}
     */
    @Override
    public List<ProposalPersonContract> getNKeyPersons(List<? extends ProposalPersonContract> proposalPersons, boolean firstN, int n) {
        ProposalPersonContract proposalPerson, previousProposalPerson;
        int size = proposalPersons.size();

        for (int i = size - 1; i > 0; i--) {
            proposalPerson = proposalPersons.get(i);
            previousProposalPerson = proposalPersons.get(i - 1);
            if (proposalPerson.getPersonId() != null && previousProposalPerson.getPersonId() != null
                    && proposalPerson.getPersonId().equals(previousProposalPerson.getPersonId())) {
                proposalPersons.remove(i);
            }
            else if (proposalPerson.getRolodexId() != null && previousProposalPerson.getRolodexId() != null
                    && proposalPerson.getRolodexId().equals(previousProposalPerson.getRolodexId())) {
                proposalPersons.remove(i);
            }
        }

        size = proposalPersons.size();
        if (firstN) {
            List<ProposalPersonContract> firstNPersons = new ArrayList<ProposalPersonContract>();

            // Make sure we don't exceed the size of the list.
            if (size > n) {
                size = n;
            }
            // remove extras
            for (int i = 0; i < size; i++) {
                firstNPersons.add(proposalPersons.get(i));
            }
            return firstNPersons;
        }
        else {
            // return extra people
            List<ProposalPersonContract> extraPersons = new ArrayList<ProposalPersonContract>();
            for (int i = n; i < size; i++) {
                extraPersons.add(proposalPersons.get(i));
            }
            return extraPersons;
        }
    }

    /**
     * This method is to get PrincipalInvestigator from person list
     *
     * @param pdDoc Proposal development document.
     * @return ProposalPerson PrincipalInvestigator for the proposal.
     */
    @Override
    public ProposalPersonContract getPrincipalInvestigator(ProposalDevelopmentDocumentContract pdDoc) {
        ProposalPersonContract proposalPerson = null;
        if (pdDoc != null) {
            for (ProposalPersonContract person : pdDoc.getDevelopmentProposal().getProposalPersons()) {
                if (person.isPrincipalInvestigator()) {
                    proposalPerson = person;
                }
            }
        }
        return proposalPerson;
    }

    /**
     * Finds all the Investigators associated with the provided pdDoc.
     */
    @Override
    public List<ProposalPersonContract> getCoInvestigators(ProposalDevelopmentDocumentContract pdDoc) {
        List<ProposalPersonContract> investigators = new ArrayList<ProposalPersonContract>();
        if (pdDoc != null) {
            for (ProposalPersonContract person : pdDoc.getDevelopmentProposal().getProposalPersons()) {
                //multi-pis are still considered co-i within S2S.
                if (person.isCoInvestigator() || person.isMultiplePi()) {
                    investigators.add(person);
                }
            }
        }
        return investigators;
    }

    /**
     * Finds all the key Person associated with the provided pdDoc.
     */
    @Override
    public List<ProposalPersonContract> getKeyPersons(ProposalDevelopmentDocumentContract pdDoc) {
        List<ProposalPersonContract> keyPersons = new ArrayList<ProposalPersonContract>();
        if (pdDoc != null) {
            for (ProposalPersonContract person : pdDoc.getDevelopmentProposal().getProposalPersons()) {
                if (person.isKeyPerson()) {
                    keyPersons.add(person);
                }
            }
        }
        return keyPersons;
    }

    /**
     * Implementation should return one of the enums defined in PHS398CareerDevelopmentAwardSup11V11 form schema. For now, it
     * returns US RESIDENT as default
     */
    @Override
    public CitizenshipType getCitizenship(ProposalPersonContract proposalPerson) {
        String citizenSource = "1";
        String piCitizenShipValue = s2SConfigurationService.getValueAsString(ConfigurationConstants.PI_CUSTOM_DATA);
        if (piCitizenShipValue != null) {
            citizenSource = piCitizenShipValue;
        }
        if (citizenSource.equals("0")) {
            CitizenshipType citizenShipType = citizenshipTypeService.getCitizenshipDataFromExternalSource();
            return citizenShipType;
        }
        else {
            Integer citizenShip;
            Boolean allowOverride = s2SConfigurationService.getValueAsBoolean(
                    ConfigurationConstants.ALLOW_PROPOSAL_PERSON_TO_OVERRIDE_KC_PERSON_EXTENDED_ATTRIBUTES);
            if (allowOverride) {
                citizenShip = proposalPerson.getCitizenshipType().getCode();
            }
            else {
                citizenShip = proposalPerson.getPerson().getCitizenshipTypeCode();
            }
            CitizenshipType retVal = null;
            String citizenShipCode = String.valueOf(citizenShip);
            if (citizenShipCode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.NON_US_CITIZEN_WITH_TEMPORARY_VISA_TYPE_CODE))) {
                return CitizenshipType.NON_US_CITIZEN_WITH_TEMPORARY_VISA;
            }
            else if (citizenShipCode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.PERMANENT_RESIDENT_OF_US_TYPE_CODE))) {
                return CitizenshipType.PERMANENT_RESIDENT_OF_US;
            }
            else if (citizenShipCode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.US_CITIZEN_OR_NONCITIZEN_NATIONAL_TYPE_CODE))) {
                return CitizenshipType.US_CITIZEN_OR_NONCITIZEN_NATIONAL;
            }
            else if (citizenShipCode.equals(s2SConfigurationService.getValueAsString(
                    ConfigurationConstants.PERMANENT_RESIDENT_OF_US_PENDING))) {
                return CitizenshipType.PERMANENT_RESIDENT_OF_US_PENDING;
            }
            else {
                throw new IllegalArgumentException("Invalid citizenship type provided");
            }

        }
    }
}
