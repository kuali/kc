/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.List;

import org.kuali.kra.bo.KcPersonExtendedAttributes;
import org.kuali.kra.bo.PersonAppointment;
import org.kuali.kra.bo.PersonDegree;
import org.kuali.rice.krad.bo.PersistableAttachment;

/**
 * 
 * This class manages the copy of each proposal person's extended attributes, and are edit-able.
 */
public class ProposalPersonExtendedAttributes extends KcPersonExtendedAttributes implements PersistableAttachment {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4756287061318140215L;

    private static final String UNSUPPORTED_OPPERATION_ERROR_MESSAGE = "ProposalPersonExtendedAttributes intentionally not supporting this method.";

    private String proposalNumber;

    private Integer proposalPersonNumber;

    private String proposalPersonRoleId;

    private ProposalPerson proposalPerson;

    /**
     * 
     * Constructs a ProposalPersonExtendedAttributes.java. Default constructor.
     */
    public ProposalPersonExtendedAttributes() {
    }

    /**
     * 
     * Constructs a ProposalPersonExtendedAttributes.java.
     * @param person
     */
    public ProposalPersonExtendedAttributes(ProposalPerson person) {
        this();
        if (person == null) {
            throw new IllegalArgumentException("ProposalPerson must not be null!");
        }
        this.setProposalNumber(person.getProposalNumber());
        this.setProposalPersonNumber(person.getProposalPersonNumber());
        this.setProposalPersonRoleId(person.getProposalPersonRoleId());
        this.setProposalPerson(person);
        this.setCitizenshipTypeCode(1);
    }

    /**
     * 
     * Constructs a ProposalPersonExtendedAttributes.java. This constructor will generally be used.
     * @param person
     * @param personExtendedAttributes
     */
    public ProposalPersonExtendedAttributes(ProposalPerson person, KcPersonExtendedAttributes personExtendedAttributes) {
        this(person);
        if (personExtendedAttributes == null) {
            throw new IllegalArgumentException("KcPersonExtendedAttributes must not be null");
        }
        this.setPersonId(personExtendedAttributes.getPersonId());
        this.setAgeByFiscalYear(personExtendedAttributes.getAgeByFiscalYear());
        this.setRace(personExtendedAttributes.getRace());
        this.setEducationLevel(personExtendedAttributes.getEducationLevel());
        this.setDegree(personExtendedAttributes.getDegree());
        this.setMajor(personExtendedAttributes.getMajor());
        this.setHandicappedFlag(personExtendedAttributes.getHandicappedFlag());
        this.setHandicapType(personExtendedAttributes.getHandicapType());
        this.setVeteranFlag(personExtendedAttributes.getVeteranFlag());
        this.setVeteranType(personExtendedAttributes.getVeteranType());
        this.setVisaCode(personExtendedAttributes.getVisaCode());
        this.setVisaType(personExtendedAttributes.getVisaType());
        this.setVisaRenewalDate(personExtendedAttributes.getVisaRenewalDate());
        this.setHasVisa(personExtendedAttributes.getHasVisa());
        this.setOfficeLocation(personExtendedAttributes.getOfficeLocation());
        this.setSecondaryOfficeLocation(personExtendedAttributes.getSecondaryOfficeLocation());
        this.setSchool(personExtendedAttributes.getSchool());
        this.setYearGraduated(personExtendedAttributes.getYearGraduated());
        this.setDirectoryDepartment(personExtendedAttributes.getDirectoryDepartment());
        this.setPrimaryTitle(personExtendedAttributes.getPrimaryTitle());
        this.setDirectoryTitle(personExtendedAttributes.getDirectoryTitle());
        this.setVacationAccrualFlag(personExtendedAttributes.getVacationAccrualFlag());
        this.setOnSabbaticalFlag(personExtendedAttributes.getOnSabbaticalFlag());
        this.setIdProvided(personExtendedAttributes.getIdProvided());
        this.setIdVerified(personExtendedAttributes.getIdVerified());
        this.setCounty(personExtendedAttributes.getCounty());
        this.setBiosketchDescription(personExtendedAttributes.getBiosketchDescription());
        this.setFileName(personExtendedAttributes.getFileName());
        this.setContentType(personExtendedAttributes.getContentType());
        this.setAttachmentContent(personExtendedAttributes.getAttachmentContent());
        this.setCitizenshipTypeCode(personExtendedAttributes.getCitizenshipTypeCode());
        this.setCitizenshipType(personExtendedAttributes.getCitizenshipType());
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public Integer getProposalPersonNumber() {
        return proposalPersonNumber;
    }

    public void setProposalPersonNumber(Integer proposalPersonNumber) {
        this.proposalPersonNumber = proposalPersonNumber;
    }

    public String getProposalPersonRoleId() {
        return proposalPersonRoleId;
    }

    public void setProposalPersonRoleId(String proposalPersonRoleId) {
        this.proposalPersonRoleId = proposalPersonRoleId;
    }

    public ProposalPerson getProposalPerson() {
        return proposalPerson;
    }

    public void setProposalPerson(ProposalPerson proposalPerson) {
        this.proposalPerson = proposalPerson;
    }

    /**
     * 
     * @see org.kuali.kra.bo.KcPersonExtendedAttributes#setPersonDegrees(java.util.List)
     */
    @Override
    public void setPersonDegrees(List<PersonDegree> personDegrees) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPPERATION_ERROR_MESSAGE);
    }

    /**
     * 
     * @see org.kuali.kra.bo.KcPersonExtendedAttributes#getPersonDegrees()
     */
    @Override
    public List<PersonDegree> getPersonDegrees() {
        return super.getPersonDegrees();
    }

    /**
     * 
     * @see org.kuali.kra.bo.KcPersonExtendedAttributes#getPersonAppointments()
     */
    @Override
    public List<PersonAppointment> getPersonAppointments() {
        return super.getPersonAppointments();
    }

    /**
     * 
     * @see org.kuali.kra.bo.KcPersonExtendedAttributes#setPersonAppointments(java.util.List)
     */
    @Override
    public void setPersonAppointments(List<PersonAppointment> personAppointments) {
        throw new UnsupportedOperationException(UNSUPPORTED_OPPERATION_ERROR_MESSAGE);
    }
}
