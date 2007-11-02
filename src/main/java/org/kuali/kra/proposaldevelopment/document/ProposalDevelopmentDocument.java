/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.document;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.kuali.core.document.Copyable;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.PropPerDocType;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiographyAttachment;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalUserRoles;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;

public class ProposalDevelopmentDocument extends ResearchDocumentBase implements Copyable {

    private Integer proposalNumber;
    private String proposalTypeCode;
    private String continuedFrom;
    private String sponsorCode;
    private String activityTypeCode;
    private String ownedByUnitNumber;
    private Date requestedStartDateInitial;
    private Date requestedEndDateInitial;
    private String title;
    private String currentAwardNumber;
    private Date deadlineDate;
    private String noticeOfOpportunityCode;
    private String deadlineType;
    private String cfdaNumber;
    private String programAnnouncementNumber;
    private String primeSponsorCode;
    private String sponsorProposalNumber;
    private String originalProposalNumber;
    private String nsfCode;
    private Boolean subcontracts;
    private String agencyDivisionCode;
    private String agencyProgramCode;
    private String programAnnouncementTitle;
    private String mailBy;
    private String mailType;
    private String mailAccountNumber;
    private String mailDescription;
    private Integer mailingAddressId;
    private String numberOfCopies;
    private String organizationId;
    private String performingOrganizationId;
    private List<ProposalLocation> proposalLocations;
    private Organization organization;
    // TODO: just for organization panel. not a real reference
    private Organization performingOrganization;
    // TODO: just for delivery panel. not a real reference
    private Rolodex rolodex;
    private List<ProposalSpecialReview> propSpecialReviews;
    private List<PropScienceKeyword> propScienceKeywords;
    private List<ProposalPerson> proposalPersons;
    private ProposalPerson principalInvestigator;
    private String newScienceKeywordCode;
    private String newDescription;
    private Sponsor sponsor;
    private Integer nextProposalPersonNumber;
    private List<Narrative> narratives;
    private List<ProposalUserRoles> proposalUserRoles;
    private List<ProposalAbstract> proposalAbstracts;
    private List<Narrative> institutes;
    private List<ProposalPersonBiography> propPersonBios;
    private List<ProposalPerson> investigators;
    private Unit ownedByUnit;

    
    public ProposalDevelopmentDocument() {
        super();
        propScienceKeywords = new TypedArrayList(PropScienceKeyword.class);
        newDescription = getDefaultNewDescription();
        proposalLocations = new ArrayList<ProposalLocation>();
        propSpecialReviews = new ArrayList<ProposalSpecialReview>();
        proposalPersons = new ArrayList<ProposalPerson>();
        nextProposalPersonNumber = new Integer(1);
        narratives = new ArrayList<Narrative>();
        proposalUserRoles = new ArrayList<ProposalUserRoles>();
        proposalAbstracts = new ArrayList<ProposalAbstract>();
        institutes = new ArrayList<Narrative>();
        propPersonBios = new ArrayList<ProposalPersonBiography>();
    }

    public void initialize() {
        ProposalDevelopmentService proposalDevelopmentService = KraServiceLocator.getService(ProposalDevelopmentService.class);
        List<Unit> userUnits = proposalDevelopmentService.getDefaultModifyProposalUnitsForUser(GlobalVariables.getUserSession().getLoggedInUserNetworkId());
        if (userUnits.size() == 1) {
            this.setOwnedByUnitNumber(userUnits.get(0).getUnitNumber());
            proposalDevelopmentService.initializeUnitOrganzationLocation(this);
        }
    }
    
    /**
     * Gets the value of proposalPersons
     *
     * @return the value of proposalPersons
     */
    public List<ProposalPerson> getProposalPersons() {
        return this.proposalPersons;
    }

    public void setInvestigators(List<ProposalPerson> investigators) {
        this.investigators = investigators;
    }

    public List<ProposalPerson> getInvestigators() {
        // TODO : investigators are not saved, so new load will not have value
        if ((investigators==null || investigators.isEmpty()) && proposalPersons!=null && !proposalPersons.isEmpty()) {
            if (investigators==null) {
                investigators=new ArrayList<ProposalPerson>();
            }
            for (ProposalPerson proposalPerson : proposalPersons) {
                if (proposalPerson.isInvestigator()) {
                    investigators.add(proposalPerson);
                }                
            }
            
        }
        return investigators;
    }
    
    /**
     * Sets the value of proposalPersons
     *
     * @param argProposalPersons Value to assign to this.proposalPersons
     */
    public void setProposalPersons(List<ProposalPerson> argProposalPersons) {
        this.proposalPersons = argProposalPersons;
    }

    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    public String getOwnedByUnitNumber() {
        return ownedByUnitNumber;
    }

    public void setOwnedByUnitNumber(String ownedByUnit) {
        this.ownedByUnitNumber = ownedByUnit;
    }

    public String getProposalTypeCode() {
        return proposalTypeCode;
    }

    public void setProposalTypeCode(String proposalTypeCode) {
        this.proposalTypeCode = proposalTypeCode;
    }

    /**
     * Gets the continuedFrom attribute.
     * @return Returns the continuedFrom.
     */
    public String getContinuedFrom() {
        return continuedFrom;
    }

    /**
     * Sets the continuedFrom attribute value.
     * @param continuedFrom The continuedFrom to set.
     */
    public void setContinuedFrom(String continuedFrom) {
        this.continuedFrom = continuedFrom;
    }

    public Date getRequestedEndDateInitial() {
        return requestedEndDateInitial;
    }

    public void setRequestedEndDateInitial(Date requestedEndDateInitial) {
        this.requestedEndDateInitial = requestedEndDateInitial;
    }

    public Date getRequestedStartDateInitial() {
        return requestedStartDateInitial;
    }

    public void setRequestedStartDateInitial(Date requestedStartDateInitial) {
        this.requestedStartDateInitial = requestedStartDateInitial;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(Integer proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getCurrentAwardNumber() {
        return currentAwardNumber;
    }

    public void setCurrentAwardNumber(String currentAwardNumber) {
        this.currentAwardNumber = currentAwardNumber;
    }

    /**
     * Gets the agencyDivisionCode attribute.
     *
     * @return Returns the agencyDivisionCode.
     */
    public String getAgencyDivisionCode() {
        return agencyDivisionCode;
    }

    /**
     * Sets the agencyDivisionCode attribute value.
     *
     * @param agencyDivisionCode The agencyDivisionCode to set.
     */
    public void setAgencyDivisionCode(String agencyDivisionCode) {
        this.agencyDivisionCode = agencyDivisionCode;
    }

    /**
     * Gets the agencyProgramCode attribute.
     *
     * @return Returns the agencyProgramCode.
     */
    public String getAgencyProgramCode() {
        return agencyProgramCode;
    }

    /**
     * Sets the agencyProgramCode attribute value.
     *
     * @param agencyProgramCode The agencyProgramCode to set.
     */
    public void setAgencyProgramCode(String agencyProgramCode) {
        this.agencyProgramCode = agencyProgramCode;
    }

    /**
     * Gets the cfdaNumber attribute.
     *
     * @return Returns the cfdaNumber.
     */
    public String getCfdaNumber() {
        return cfdaNumber;
    }

    /**
     * Sets the cfdaNumber attribute value.
     *
     * @param cfdaNumber The cfdaNumber to set.
     */
    public void setCfdaNumber(String cfdaNumber) {
        this.cfdaNumber = cfdaNumber;
    }

    /**
     * Gets the deadlineDate attribute.
     *
     * @return Returns the deadlineDate.
     */
    public Date getDeadlineDate() {
        return deadlineDate;
    }

    /**
     * Sets the deadlineDate attribute value.
     *
     * @param deadlineDate The deadlineDate to set.
     */
    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    /**
     * Gets the deadlineType attribute.
     *
     * @return Returns the deadlineType.
     */
    public String getDeadlineType() {
        return deadlineType;
    }

    /**
     * Sets the deadlineType attribute value.
     *
     * @param deadlineType The deadlineType to set.
     */
    public void setDeadlineType(String deadlineType) {
        this.deadlineType = deadlineType;
    }

    /**
     * Gets the noticeOfOpportunityCode attribute.
     *
     * @return Returns the noticeOfOpportunityCode.
     */
    public String getNoticeOfOpportunityCode() {
        return noticeOfOpportunityCode;
    }

    /**
     * Sets the noticeOfOpportunityCode attribute value.
     *
     * @param noticeOfOpportunityCode The noticeOfOpportunityCode to set.
     */
    public void setNoticeOfOpportunityCode(String noticeOfOpportunityCode) {
        this.noticeOfOpportunityCode = noticeOfOpportunityCode;
    }

    /**
     * Gets the nsfCode attribute.
     *
     * @return Returns the nsfCode.
     */
    public String getNsfCode() {
        return nsfCode;
    }

    /**
     * Sets the nsfCode attribute value.
     *
     * @param nsfCode The nsfCode to set.
     */
    public void setNsfCode(String nsfCode) {
        this.nsfCode = nsfCode;
    }

    /**
     * Gets the primeSponsorCode attribute.
     *
     * @return Returns the primeSponsorCode.
     */
    public String getPrimeSponsorCode() {
        return primeSponsorCode;
    }

    /**
     * Sets the primeSponsorCode attribute value.
     *
     * @param primeSponsorCode The primeSponsorCode to set.
     */
    public void setPrimeSponsorCode(String primeSponsorCode) {
        this.primeSponsorCode = primeSponsorCode;
    }

    /**
     * Gets the programAnnouncementNumber attribute.
     *
     * @return Returns the programAnnouncementNumber.
     */
    public String getProgramAnnouncementNumber() {
        return programAnnouncementNumber;
    }

    /**
     * Sets the programAnnouncementNumber attribute value.
     *
     * @param programAnnouncementNumber The programAnnouncementNumber to set.
     */
    public void setProgramAnnouncementNumber(String programAnnouncementNumber) {
        this.programAnnouncementNumber = programAnnouncementNumber;
    }

    /**
     * Gets the programAnnouncementTitle attribute.
     *
     * @return Returns the programAnnouncementTitle.
     */
    public String getProgramAnnouncementTitle() {
        return programAnnouncementTitle;
    }

    /**
     * Sets the programAnnouncementTitle attribute value.
     *
     * @param programAnnouncementTitle The programAnnouncementTitle to set.
     */
    public void setProgramAnnouncementTitle(String programAnnouncementTitle) {
        this.programAnnouncementTitle = programAnnouncementTitle;
    }

    /**
     * Gets the sponsorProposalNumber attribute.
     *
     * @return Returns the sponsorProposalNumber.
     */
    public String getSponsorProposalNumber() {
        return sponsorProposalNumber;
    }

    /**
     * Sets the sponsorProposalNumber attribute value.
     *
     * @param sponsorProposalNumber The sponsorProposalNumber to set.
     */
    public void setSponsorProposalNumber(String sponsorProposalNumber) {
        this.sponsorProposalNumber = sponsorProposalNumber;
    }

    /**
     * Gets the subcontracts attribute.
     *
     * @return Returns the subcontracts.
     */
    public Boolean getSubcontracts() {
        return subcontracts;
    }

    /**
     * Sets the subcontracts attribute value.
     *
     * @param subcontracts The subcontracts to set.
     */
    public void setSubcontracts(Boolean subcontracts) {
        this.subcontracts = subcontracts;
    }

    public String getMailBy() {
        return mailBy;
    }

    public void setMailBy(String mailBy) {
        this.mailBy = mailBy;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public String getMailAccountNumber() {
        return mailAccountNumber;
    }

    public void setMailAccountNumber(String mailAccountNumber) {
        this.mailAccountNumber = mailAccountNumber;
    }

    public String getMailDescription() {
        return mailDescription;
    }

    public void setMailDescription(String mailDescription) {
        this.mailDescription = mailDescription;
    }

    public Integer getMailingAddressId() {
        return mailingAddressId;
    }

    public void setMailingAddressId(Integer mailingAddressId) {
        this.mailingAddressId = mailingAddressId;
    }

    public String getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(String numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPerformingOrganizationId() {
        return performingOrganizationId;
    }

    public void setPerformingOrganizationId(String performingOrganizationId) {
        // TODO : not sure yet
        if ((performingOrganizationId==null || performingOrganizationId.trim().equals("")) && organizationId!=null) {
            this.performingOrganizationId = organizationId;
        } else {
            this.performingOrganizationId = performingOrganizationId;
        }
    }

    public List<ProposalLocation> getProposalLocations() {
        return proposalLocations;
    }

    public void setProposalLocations(List<ProposalLocation> proposalLocations) {
        this.proposalLocations = proposalLocations;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Organization getPerformingOrganization() {
        return performingOrganization;
    }

    public void setPerformingOrganization(Organization performingOrganization) {
        this.performingOrganization = performingOrganization;
    }

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    public void setPropScienceKeywords(List<PropScienceKeyword> propScienceKeywords) {
        this.propScienceKeywords = propScienceKeywords;
    }

    public List<PropScienceKeyword> getPropScienceKeywords() {
        return propScienceKeywords;
    }

    public void addPropScienceKeyword(PropScienceKeyword propScienceKeyword) {
        getPropScienceKeywords().add(propScienceKeyword);
    }

    public String getNewScienceKeywordCode() {
        return newScienceKeywordCode;
    }
    public void setNewScienceKeywordCode(String newScienceKeywordCode) {
        this.newScienceKeywordCode = newScienceKeywordCode;
    }
    public String getNewDescription() {
        return newDescription;
    }
    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public String getDefaultNewDescription() {
        return "(select)";
    }

    public List<ProposalSpecialReview> getPropSpecialReviews() {
        return propSpecialReviews;
    }

    public void setPropSpecialReviews(List<ProposalSpecialReview> propSpecialReviews) {
        this.propSpecialReviews = propSpecialReviews;
    }

    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getProposalLocations());
        managedLists.add(getPropSpecialReviews());
        managedLists.add(getProposalPersons());
        managedLists.add(getNarratives());
        managedLists.add(getPropScienceKeywords());
        managedLists.add(getProposalAbstracts());
        return managedLists;

    }

    /**
     * Gets the originalProposalNumber attribute.
     * @return Returns the originalProposalNumber.
     */
    public String getOriginalProposalNumber() {
        return originalProposalNumber;
    }

    /**
     * Sets the originalProposalNumber attribute value.
     * @param originalProposalNumber The originalProposalNumber to set.
     */
    public void setOriginalProposalNumber(String originalProposalNumber) {
        this.originalProposalNumber = originalProposalNumber;
    }

    /**
     * Gets the sponsor attribute.
     * @return Returns the sponsor.
     */
    public Sponsor getSponsor() {
        return sponsor;
    }

    /**
     * Sets the sponsor attribute value.
     * @param sponsor The sponsor to set.
     */
    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }


    /**
     * Accessor for the principal investigator of this proposal
     *
     * @return ProposalPerson
     */
    public ProposalPerson getPrincipalInvestigator() {
        return principalInvestigator;
    }

    /**
     * Accessor for the principal investigator of this proposal
     *
     * @param person
     */
    public void setPrincipalInvestigator(ProposalPerson person) {
        principalInvestigator = person;
    }

    public void setNextProposalPersonNumber(Integer n) {
        nextProposalPersonNumber = n;
    }
    
    public Integer getNextProposalPersonNumber() {
        return nextProposalPersonNumber;
    }
    
    /**
     * Adds a new proposal person to the collection in the document
     *
     * @param p person to add
     */
    public void addProposalPerson(ProposalPerson p) {
        p.setProposalPersonNumber(this.getProposalNextValue(Constants.PROPOSAL_PERSON_NUMBER));
        getProposalPersons().add(p);
        //setNextProposalPersonNumber(getNextProposalPersonNumber().intValue() + 1);
    }

    public ProposalPerson getProposalPerson(int index) {
        while (getProposalPersons().size() <= index) {
            getProposalPersons().add(new ProposalPerson());
        }
        
        return getProposalPersons().get(index);
    }

    /**
     * Get the list of Proposal Attachments (Narratives) for this Proposal.
     * @return the proposal's list of narratives.
     */
    public List<Narrative> getNarratives() {
        return narratives;
    }
    /**
     * Set the list of Proposal Attachments (Narratives) for this Proposal.
     * @param narratives the proposal's new list of narratives.
     */
    public void setNarratives(List<Narrative> narratives) {
        this.narratives = narratives;
    }

//    public Narrative getNewNarrative() {
//        return newNarrative;
//    }
//
//    public void setNewNarrative(Narrative narrative) {
//        this.newNarrative = narrative;
//    }
//
    /**
     * Get the list of User Roles for this Proposal.
     * @return proposal user roles.
     */
    public List<ProposalUserRoles> getProposalUserRoles() {
        return proposalUserRoles;
    }

    /**
     * Set the list of User Roles for this Proposal.
     * @param proposal user roles
     */
    public void setProposalUserRoles(List<ProposalUserRoles> proposalUserRoles) {
        this.proposalUserRoles = proposalUserRoles;
    }

    /**
     * Get the list of Abstracts for this Proposal.
     * @return the proposal's list of abstracts.
     */
    public List<ProposalAbstract> getProposalAbstracts() {
        return proposalAbstracts;
    }

    /**
     * Set the list of Abstracts for this Proposal.
     * @param proposalAbstracts the proposal's new list of abstracts.
     */
    public void setProposalAbstracts(List<ProposalAbstract> proposalAbstracts) {
        this.proposalAbstracts = proposalAbstracts;
    }

    public List<Narrative> getInstitutes() {
        return institutes;
    }

    public void setInstitutes(List<Narrative> institutes) {
        this.institutes = institutes;
    }

    public List<ProposalPersonBiography> getPropPersonBios() {
        return propPersonBios;
    }

    public void setPropPersonBios(List<ProposalPersonBiography> propPersonBios) {
        this.propPersonBios = propPersonBios;
    }

    /**
     * Gets the ownedByUnit attribute. 
     * @return Returns the ownedByUnit.
     */
    public Unit getOwnedByUnit() {
        return ownedByUnit;
    }

    /**
     * Sets the ownedByUnit attribute value.
     * @param ownedByUnit The ownedByUnit to set.
     */
    public void setOwnedByUnit(Unit ownedByUnit) {
        this.ownedByUnit = ownedByUnit;
    }
    
    /**
     * 
     * This method adds new personnel attachment to biography and biography attachment bo with proper set up.
     * @param proposalPersonBiography
     * @throws Exception
     */
    public void addProposalPersonBiography(ProposalPersonBiography proposalPersonBiography) throws Exception {
        proposalPersonBiography.setProposalNumber(getProposalNumber());
        proposalPersonBiography.setUpdateUser(getUpdateUser());
        proposalPersonBiography.setUpdateTimestamp(getUpdateTimestamp());
        proposalPersonBiography.setBiographyNumber(getProposalNextValue(Constants.PROP_PERSON_BIO_NUMBER));
        proposalPersonBiography.setPropPerDocType(new PropPerDocType());
        proposalPersonBiography.getPropPerDocType().setDocumentTypeCode(proposalPersonBiography.getDocumentTypeCode());
        proposalPersonBiography.refreshReferenceObject("propPerDocType");
        FormFile personnelAttachmentFile = proposalPersonBiography.getPersonnelAttachmentFile();
        proposalPersonBiography.setFileName(personnelAttachmentFile.getFileName());
        byte[] fileData = personnelAttachmentFile.getFileData();
        if (fileData.length > 0) {
            ProposalPersonBiographyAttachment personnelAttachment = new ProposalPersonBiographyAttachment();
            personnelAttachment.setFileName(personnelAttachmentFile.getFileName());
            personnelAttachment.setProposalNumber(proposalPersonBiography.getProposalNumber());
            personnelAttachment.setProposalPersonNumber(proposalPersonBiography.getProposalPersonNumber());
            personnelAttachment.setBiographyData(personnelAttachmentFile.getFileData());
            personnelAttachment.setContentType(personnelAttachmentFile.getContentType());
            if (proposalPersonBiography.getPersonnelAttachmentList().isEmpty())
                proposalPersonBiography.getPersonnelAttachmentList().add(personnelAttachment);
            else
                proposalPersonBiography.getPersonnelAttachmentList().set(0, personnelAttachment);
        }
        getPropPersonBios().add(proposalPersonBiography);

    }

}
