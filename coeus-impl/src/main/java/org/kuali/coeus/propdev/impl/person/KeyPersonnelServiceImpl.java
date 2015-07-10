/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.framework.person.editable.PersonEditableService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.attr.PersonBiosketch;
import org.kuali.coeus.common.framework.person.attr.PersonDegree;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.ynq.Ynq;
import org.kuali.coeus.common.framework.ynq.YnqService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyAttachment;
import org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplit;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalCreditSplitListDto;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalPersonCreditSplit;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalUnitCreditSplit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * A Service implementation for persisted modifications of Key Personnel related business objects
 *
 * @see org.kuali.coeus.propdev.impl.person.ProposalPerson
 * @author $Author: gmcgrego $
 * @version $Revision: 1.34 $
 */
@Component("keyPersonnelService")
public class KeyPersonnelServiceImpl implements KeyPersonnelService, Constants {

    private static final Log LOG = LogFactory.getLog(KeyPersonnelServiceImpl.class);

    private static final String ROLODEX_PERSON = "Unknown";

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("ynqService")
    private YnqService ynqService;
    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;
    @Autowired
    @Qualifier("sponsorHierarchyService")
    private SponsorHierarchyService sponsorHierarchyService;
    
    @Autowired
    @Qualifier("personEditableService")
    private PersonEditableService personEditableService;
    @Autowired
    @Qualifier("proposalPersonService")
    ProposalPersonService proposalPersonService;

    protected ProposalPersonService getProposalPersonService (){return proposalPersonService;}
    public  void setProposalPersonService (ProposalPersonService proposalPersonService){
        this.proposalPersonService = proposalPersonService;
    }

    /**
     * Populates Key Personnel into a <code>{@link ProposalDevelopmentDocument}</code>
     * 
     * @param document
     */
    public void populateDocument(ProposalDevelopmentDocument document) {
        populateCreditSplit(document);

        /* check for new certification questions */
        for (ProposalPerson person : document.getDevelopmentProposal().getProposalPersons()) {
            getYnqService().getPersonYNQ(person, document);
        }
    }

    public void populateCreditSplit(ProposalDevelopmentDocument document) {
        if(hasBeenRoutedOrCanceled(document)){
            Collection<InvestigatorCreditType> availableCreditTypes=getAllInvestigatorCreditTypes();
            Set<InvestigatorCreditType> usedCreditTypes = new HashSet<InvestigatorCreditType>();
            for (ProposalPerson person : document.getDevelopmentProposal().getInvestigators()) {
                for(ProposalPersonCreditSplit creditSplit : person.getCreditSplits()){
                    for(InvestigatorCreditType currentCreditType : availableCreditTypes){
                        if(currentCreditType.getCode().equals(creditSplit.getInvCreditTypeCode())){
                            usedCreditTypes.add(currentCreditType);
                        }
                    }
                }
            }
            document.getDevelopmentProposal().setInvestigatorCreditTypes(usedCreditTypes);
        }else
        {
            document.getDevelopmentProposal().setInvestigatorCreditTypes(getInvestigatorCreditTypes());
        }
        if (document.getDevelopmentProposal().getInvestigators().isEmpty() && !document.getDevelopmentProposal().getProposalPersons().isEmpty()) {
            if(!(document.getDocumentHeader().getWorkflowDocument().getStatus().getCode().equals("R"))){
                populateActiveCredittypesPerson(document);
            }
        }
    }

    /**
     * It populates the Active credit type in the proposalpersoncreditsplit and unitcreditsplit
     *
     * @param document
     * @return true or false
     */
    public void populateActiveCredittypesPerson(ProposalDevelopmentDocument document){
        Collection<InvestigatorCreditType> invcrdttype=getInvestigatorCreditTypes();
        for (ProposalPerson person : document.getDevelopmentProposal().getInvestigators()) {
            for(InvestigatorCreditType invcredtype:invcrdttype){
                boolean creditTypeFound = false;
                for(ProposalPersonCreditSplit proposalpersoncrdt:person.getCreditSplits()){
                    if((invcredtype.getCode().equals(proposalpersoncrdt.getInvCreditTypeCode()))){
                        creditTypeFound = true;
                        break;
                    }
                }
                if (!creditTypeFound ) {
                    ProposalPersonCreditSplit creditSplit = new ProposalPersonCreditSplit();
                    creditSplit.setProposalPerson(person);
                    creditSplit.setInvCreditTypeCode(invcredtype.getCode());
                    creditSplit.setCredit(new ScaleTwoDecimal(0));
                    person.getCreditSplits().add(creditSplit);
                }
            }
            for(ProposalPersonUnit unitsplit:person.getUnits()){
                for(InvestigatorCreditType invcrdtype:invcrdttype){
                    boolean creditTypeFound = false;
                    for(ProposalUnitCreditSplit unitcreditsplit:unitsplit.getCreditSplits()){
                        if((invcrdtype.getCode().equals(unitcreditsplit.getInvCreditTypeCode()))){
                            creditTypeFound = true;
                            break;
                        }
                    }
                    if (!creditTypeFound ) {
                        ProposalUnitCreditSplit creditSplit = new ProposalUnitCreditSplit();
                        creditSplit.setProposalPersonUnit(unitsplit);
                        creditSplit.setInvCreditTypeCode(invcrdtype.getCode());
                        creditSplit.setCredit(new ScaleTwoDecimal(0));
                        unitsplit.getCreditSplits().add(creditSplit);
                    }
                }
            }

        }
    }

    public void addProposalPerson(ProposalPerson proposalPerson, ProposalDevelopmentDocument document) {
    	getPersonEditableService().populateContactFields(proposalPerson);
        document.getDevelopmentProposal().addProposalPerson(proposalPerson);
        
        LOG.info("Added Proposal Person with proposalNumber = " + document.getDevelopmentProposal().getProposalNumber() + " and proposalPersonNumber = " + proposalPerson.getProposalPersonNumber());
        // handle lead unit for investigators respective to coi or pi
        if (proposalPerson.isPrincipalInvestigator()) {
            assignLeadUnit(proposalPerson, document.getDevelopmentProposal().getOwnedByUnitNumber());
        } else {
            // Lead Unit information needs to be removed in case the person used to be a PI
            ProposalPersonUnit unit = proposalPerson.getUnit(document.getDevelopmentProposal().getOwnedByUnitNumber());
            if (unit != null) {
                unit.setLeadUnit(false);
            }                
        }
        if(proposalPerson.getHomeUnit()!=null){
            proposalPerson.refreshReferenceObject("homeUnitRef");
            String divisionName = getProposalPersonService().getProposalPersonDivisionName(proposalPerson);
            proposalPerson.setDivision(divisionName);
        }
        else
        {   
            proposalPerson.setDivision(ROLODEX_PERSON);
        } 
        if (proposalPerson.getProposalPersonRoleId().equals(PRINCIPAL_INVESTIGATOR_ROLE)  ||
            proposalPerson.getProposalPersonRoleId().equals(MULTI_PI_ROLE) ||
            proposalPerson.getProposalPersonRoleId().equals(CO_INVESTIGATOR_ROLE)) {
            if (isNotBlank(proposalPerson.getHomeUnit()) && isValidHomeUnit(proposalPerson, proposalPerson.getHomeUnit())){
                addUnitToPerson(proposalPerson, createProposalPersonUnit(proposalPerson.getHomeUnit(), proposalPerson));
            }
        }
        if (proposalPerson.getCitizenshipTypeCode() != null) {
            getDataObjectService().wrap(proposalPerson).fetchRelationship("citizenshipType");
        }
        populateProposalPerson(proposalPerson, document);
    }
    
    /**
     * Determines whether the person has valid unit
     * 
     * @param person
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public boolean isValidHomeUnit(ProposalPerson person, String unitId){
        Map valueMap = new HashMap();
        valueMap.put("unitNumber", unitId);
        Collection<Unit> units = getBusinessObjectService().findMatching(Unit.class, valueMap);
        
        return CollectionUtils.isNotEmpty(units);
    }

    @Override
    public void populateProposalPerson(ProposalPerson person, ProposalDevelopmentDocument document) {
        /* populate certification questions for new person */
        person = getYnqService().getPersonYNQ(person, document);

        if (person.isInvestigator()) {
            if (!document.getDevelopmentProposal().getInvestigators().contains(person)) {
                document.getDevelopmentProposal().getInvestigators().add(person);
            }
            populateCreditTypes(person);
        }

        person.setRoleChanged(false);

        try {
            if (person.getPersonId() != null 
                    && person.getPerson().getExtendedAttributes() != null) {
                KcPerson origPerson = person.getPerson();
                for (PersonDegree degree : origPerson.getExtendedAttributes().getPersonDegrees()) {
                    ProposalPersonDegree newDegree = new ProposalPersonDegree();
                    newDegree.setProposalPerson(person);
                    newDegree.setDegree(degree.getDegree());
                    newDegree.setDegreeCode(degree.getDegreeCode());
                    newDegree.setFieldOfStudy(degree.getFieldOfStudy());
                    newDegree.setGraduationYear(degree.getGraduationYear());
                    newDegree.setSchool(degree.getSchool());
                    newDegree.setSchoolId(degree.getSchoolId());
                    newDegree.setSchoolIdCode(degree.getSchoolIdCode());
                    newDegree.setDegreeSequenceNumber(document.getDocumentNextValue(Constants.PROPOSAL_PERSON_DEGREE_SEQUENCE_NUMBER));
                    person.addDegree(newDegree);                
                }
                if (origPerson.getExtendedAttributes().getAttachments() != null) {
                    for (PersonBiosketch attachment : origPerson.getExtendedAttributes().getAttachments()) {
                        ProposalPersonBiography bio = new ProposalPersonBiography();
                        bio.setProposalPersonNumber(person.getProposalPersonNumber());
                        bio.setDocumentTypeCode(getDefaultPersonAttachmentDocType());
                        bio.setDescription(attachment.getDescription());
                        bio.setName(attachment.getFileName());
                        bio.setType(attachment.getContentType());
                        bio.setDevelopmentProposal(document.getDevelopmentProposal());
                        
                        ProposalPersonBiographyAttachment personnelAttachment = new ProposalPersonBiographyAttachment();
                        personnelAttachment.setName(attachment.getFileName());
                        personnelAttachment.setProposalPersonBiography(bio);
                        personnelAttachment.setProposalNumber(document.getDevelopmentProposal().getProposalNumber());
                        personnelAttachment.setProposalPersonNumber(person.getProposalPersonNumber());
                        personnelAttachment.setData(attachment.getAttachmentContent());
                        personnelAttachment.setType(attachment.getContentType());
                        bio.setPersonnelAttachment(personnelAttachment);

                        document.getDevelopmentProposal().addProposalPersonBiography(bio);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            //catching the possibility that person.getPerson can not
            //find a EntityContract for this person id.
        }
    }

    /**
     * Initializes credit splits for new investigators
     * 
     * @param person
     */
    protected void populateCreditTypes(ProposalPerson person) {
        if (!person.getCreditSplits().isEmpty()) {
            return;
        }

        for (InvestigatorCreditType creditType : (Collection<InvestigatorCreditType>) getInvestigatorCreditTypes()) {
            ProposalPersonCreditSplit creditSplit = new ProposalPersonCreditSplit();
            creditSplit.setProposalPerson(person);
            creditSplit.setInvCreditTypeCode(creditType.getCode());
            creditSplit.setCredit(new ScaleTwoDecimal(0));
            person.getCreditSplits().add(creditSplit);
        }

    }

    /**
     * Queries persistent storage for a <code>{@link Collection}</code> of <code>{@link InvestigatorCreditType}</code>
     * instances.
     * 
     * @return Collection&lt;InvestigatorCreditType&gt; of active credit types
     */
    public Collection<InvestigatorCreditType> getInvestigatorCreditTypes() {
        Map<String,String> valueMap = new HashMap<String, String>();
        BusinessObjectService bos =getBusinessObjectService();
        valueMap.put("active", "true");
        return bos.findMatching(InvestigatorCreditType.class, valueMap);
    }

    /**
     * Queries persistent storage for a <code>{@link Collection}</code> of <code>{@link InvestigatorCreditType}</code>
     * instances.
     * 
     * @return Collection&lt;InvestigatorCreditType&gt; of all credit types
     */
    public Collection<InvestigatorCreditType> getAllInvestigatorCreditTypes() {
        return getBusinessObjectService().findAll(InvestigatorCreditType.class);
    }


    /**
     * Everytime something changes that will effect credit split values, this gets called to generate a graph of the
     * new data.
     *
     * @param document
     * @return Map
     */
    public Map calculateCreditSplitTotals(ProposalDevelopmentDocument document) {
        Map<String, Map<String,ScaleTwoDecimal>> retval = new HashMap<String,Map<String,ScaleTwoDecimal>>();

        // Initialize investigator credit types if there aren't any
        if (document.getDevelopmentProposal().getInvestigatorCreditTypes() == null || document.getDevelopmentProposal().getInvestigatorCreditTypes().size() == 0) {
            document.getDevelopmentProposal().setInvestigatorCreditTypes(getInvestigatorCreditTypes());
        }

        Collection<InvestigatorCreditType> creditTypes = document.getDevelopmentProposal().getInvestigatorCreditTypes();

        for (ProposalPerson investigator : document.getDevelopmentProposal().getInvestigators()) {
            Map<String,ScaleTwoDecimal> creditTypeTotals = retval.get(investigator.getProposalPersonNumber().toString());
            Map<String,ScaleTwoDecimal> investigatorCreditTypeTotals = retval.get(PROPOSAL_PERSON_INVESTIGATOR);

            if (creditTypeTotals == null) {
                creditTypeTotals = new HashMap<String,ScaleTwoDecimal>();
                retval.put(investigator.getProposalPersonNumber().toString(), creditTypeTotals);
            }
            if (investigatorCreditTypeTotals == null) {
                investigatorCreditTypeTotals = new HashMap<String,ScaleTwoDecimal>();
                retval.put(PROPOSAL_PERSON_INVESTIGATOR, investigatorCreditTypeTotals);
            }

            // Initialize everything to zero
            for (InvestigatorCreditType creditType : creditTypes) {                
                ScaleTwoDecimal totalCredit = creditTypeTotals.get(creditType.getCode());

                if (totalCredit == null) {
                    totalCredit = new ScaleTwoDecimal(0);
                    creditTypeTotals.put(creditType.getCode(), totalCredit);
                }
                ScaleTwoDecimal investigatorTotalCredit = investigatorCreditTypeTotals.get(creditType.getCode());

                if (investigatorTotalCredit == null) {
                    investigatorTotalCredit = new ScaleTwoDecimal(0);
                    investigatorCreditTypeTotals.put(creditType.getCode(), investigatorTotalCredit);
                }
                // set investigator credit total 
                for (CreditSplit creditSplit : investigator.getCreditSplits()) {
                    if (creditSplit.getInvCreditTypeCode().equals(creditType.getCode())) {
                        investigatorCreditTypeTotals.put(creditType.getCode(), investigatorTotalCredit.add(creditSplit.getCredit()));
                    }
                }
            }

            for (ProposalPersonUnit unit : investigator.getUnits()) {
                for (CreditSplit creditSplit : unit.getCreditSplits()) {
                    ScaleTwoDecimal totalCredit = creditTypeTotals.get(creditSplit.getInvCreditTypeCode());

                    if (totalCredit == null) {
                        totalCredit = new ScaleTwoDecimal(0);
                        creditTypeTotals.put(creditSplit.getInvCreditTypeCode(), totalCredit);
                    }
                    creditTypeTotals.put(creditSplit.getInvCreditTypeCode(),totalCredit.add(creditSplit.getCredit()));
                }
            }
        }

        return retval;
    }

    /**
     * Retrieve the injected <code>{@link BusinessObjectService}</code>
     * 
     * @return businessObjectService
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * assign the <code>{@link BusinessObjectService}</code> to use.
     * 
     * @param boservice <code>{@link BusinessObjectService}</code> instance to assign
     */
    public void setBusinessObjectService(BusinessObjectService boservice) {
        businessObjectService = boservice;
    }

    @Override
    public void addUnitToPerson(ProposalPerson person, ProposalPersonUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Cannot add null units to a ProposalPerson instance");
        }

        if (!person.containsUnit(unit.getUnitNumber())) {
        	unit.setProposalPerson(person);

            person.addUnit(unit);
            unit.refreshReferenceObject("unit");
        }
    }

    /**
     * Assigns the lead unit of the proposal to the given principal investigator
     */
    public void assignLeadUnit(ProposalPerson person, String unitNumber) {
        if (person.containsUnit(unitNumber)) {
            person.getUnit(unitNumber).setLeadUnit(true);
            return;
        }

        ProposalPersonUnit unit = createProposalPersonUnit(unitNumber, person);
        unit.setLeadUnit(true);
        addUnitToPerson(person, unit);
    }

    /**
     * Uses a <code>{@link Unit}</code> obtained from the <code>{@link Unit}</code> lookup
     * to create a <code>{@link ProposalPersonUnit}</code> instance.
     *
     * @param unitId
     * @return ProposalPersonUnit
     */
    public ProposalPersonUnit createProposalPersonUnit(String unitId, ProposalPerson person) {
        ProposalPersonUnit retval = new ProposalPersonUnit();
        Map valueMap = new HashMap();
        valueMap.put("unitNumber", unitId);
        Collection<Unit> units = getBusinessObjectService().findMatching(Unit.class, valueMap);

        for (Unit found : units) {
            retval.setUnitNumber(found.getUnitNumber());
            retval.setUnit(found);
        } 
        retval.getCreditSplits().addAll(createCreditSplits(retval));

        return retval;        
    }

    public List<ProposalUnitCreditSplit> createCreditSplits(ProposalPersonUnit unit) {
        List<ProposalUnitCreditSplit> retVal = new ArrayList<ProposalUnitCreditSplit>();
        for (InvestigatorCreditType creditType : getInvestigatorCreditTypes()) {
            ProposalUnitCreditSplit creditSplit = new ProposalUnitCreditSplit();
            creditSplit.setProposalPersonUnit(unit);
            creditSplit.setInvCreditTypeCode(creditType.getCode());
            creditSplit.setCredit(new ScaleTwoDecimal(0));
            retVal.add(creditSplit);
        }
        return retVal;
    }
    /**
     * Accessor method for dependency injection
     * 
     * @param ynqService
     */
    public void setYnqService(YnqService ynqService) {
        this.ynqService = ynqService;
    }

    public YnqService getYnqService() {
        return ynqService;
    }

    /**
     * Uses the {@link ParameterService} to determine if the application-level configuration parameter is enabled
     *
     * @see KeyPersonnelService#isCreditSplitEnabled()
     */
    public boolean isCreditSplitEnabled() {
        return getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, CREDIT_SPLIT_ENABLED_RULE_NAME);
    }
    
    public String getDefaultPersonAttachmentDocType() {
        return getParameterService().getParameterValueAsString(ProposalDevelopmentDocument.class, PROPOSAL_PERSON_BIOGRAPHY_DEFAULT_DOC_TYPE);
    }

    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service.
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    protected ParameterService getParameterService (){return parameterService;}

    protected SponsorHierarchyService getSponsorHierarchyService() {
        return sponsorHierarchyService;
    }
    
    protected boolean hasBeenRoutedOrCanceled(ProposalDevelopmentDocument document) {
        WorkflowDocument workflowDoc = document.getDocumentHeader().getWorkflowDocument();
        return !workflowDoc.isInitiated() && !workflowDoc.isSaved();
    }
    
    public void setSponsorHierarchyService(SponsorHierarchyService sponsorHierarchyService) {
        this.sponsorHierarchyService = sponsorHierarchyService;
    }
	protected PersonEditableService getPersonEditableService() {
		return personEditableService;
	}
	public void setPersonEditableService(PersonEditableService personEditableService) {
		this.personEditableService = personEditableService;
	}

    @Override
    public List<ProposalCreditSplitListDto> createCreditSplitListItems(List<ProposalPerson> investigators) {
        List<ProposalCreditSplitListDto> creditSplitListItems = new ArrayList<ProposalCreditSplitListDto>();
        Map<String,ProposalPersonCreditSplit> totalInvestigatorSplits = new HashMap<String,ProposalPersonCreditSplit>();
        for (ProposalPerson investigator : investigators) {
            ProposalCreditSplitListDto investigatorLine = new ProposalCreditSplitListDto();
            investigatorLine.setDescription(investigator.getFullName());
            investigatorLine.setLineType("investigator");
            investigatorLine.getCreditSplits().addAll(investigator.getCreditSplits());
            creditSplitListItems.add(investigatorLine);
            for(ProposalPersonCreditSplit invesitgatorCreditSplit : investigator.getCreditSplits()){
                if (totalInvestigatorSplits.containsKey(invesitgatorCreditSplit.getInvCreditTypeCode())) {
                    ProposalPersonCreditSplit creditSplit = totalInvestigatorSplits.get(invesitgatorCreditSplit.getInvCreditTypeCode());
                    creditSplit.setCredit(creditSplit.getCredit().add(invesitgatorCreditSplit.getCredit()));
                } else {
                    ProposalPersonCreditSplit creditSplit = new ProposalPersonCreditSplit();
                    creditSplit.setCredit(invesitgatorCreditSplit.getCredit());
                    totalInvestigatorSplits.put(invesitgatorCreditSplit.getInvCreditTypeCode(),creditSplit);
                }
            }

            Map<String,ProposalUnitCreditSplit> totalUnitSplits = new HashMap<String,ProposalUnitCreditSplit>();
            for(ProposalPersonUnit unit : investigator.getUnits()) {
                ProposalCreditSplitListDto unitLine = new ProposalCreditSplitListDto();
                unitLine.setDescription(unit.getUnitNumber() + " - " + unit.getUnit().getUnitName());
                unitLine.getCreditSplits().addAll(unit.getCreditSplits());
                unitLine.setLineType("unit");
                creditSplitListItems.add(unitLine);
                for(ProposalUnitCreditSplit unitCreditSplit : unit.getCreditSplits()){
                    if (totalUnitSplits.containsKey(unitCreditSplit.getInvCreditTypeCode())) {
                        ProposalUnitCreditSplit creditSplit = totalUnitSplits.get(unitCreditSplit.getInvCreditTypeCode());
                        creditSplit.setCredit(creditSplit.getCredit().add(unitCreditSplit.getCredit()));
                    } else {
                        ProposalUnitCreditSplit creditSplit = new ProposalUnitCreditSplit();
                        creditSplit.setCredit(unitCreditSplit.getCredit());
                        totalUnitSplits.put(unitCreditSplit.getInvCreditTypeCode(),creditSplit);
                    }
                }
            }

            if (totalUnitSplits.size() > 0) {
                ProposalCreditSplitListDto unitTotalLine = new ProposalCreditSplitListDto();
                unitTotalLine.setDescription("Unit Total:");
                unitTotalLine.setLineType("unit-total");
                for (Map.Entry<String,ProposalUnitCreditSplit> entry : totalUnitSplits.entrySet()) {
                    unitTotalLine.getCreditSplits().add(0,entry.getValue());
                }
                creditSplitListItems.add(unitTotalLine);
            }
        }

        if (CollectionUtils.isNotEmpty(investigators)) {
            ProposalCreditSplitListDto investigatorTotalLine = new ProposalCreditSplitListDto();
            investigatorTotalLine.setDescription("Investigator Total:");
            investigatorTotalLine.setLineType("investigator-total");
            for (Map.Entry<String,ProposalPersonCreditSplit> entry : totalInvestigatorSplits.entrySet()) {
                investigatorTotalLine.getCreditSplits().add(0,entry.getValue());
            }
            creditSplitListItems.add(investigatorTotalLine);
        }
        return creditSplitListItems;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
