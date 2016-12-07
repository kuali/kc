/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.framework.person.editable.PersonEditableService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.attr.PersonBiosketch;
import org.kuali.coeus.common.framework.person.attr.PersonDegree;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.common.framework.unit.Unit;
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
import java.util.stream.Collectors;

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
    public static final String INVESTIGATOR = "investigator";
    public static final String UNIT = "unit";
    public static final String UNIT_TOTAL = "Unit Total:";
    public static final String UNIT_TOTAL_TYPE = "unit-total";
    public static final String INVESTIGATOR_TOTAL = "Investigator Total:";
    public static final String INVESTIGATOR_TOTAL_TYPE = "investigator-total";

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
                    person.getCreditSplits().add(getProposalPersonCreditSplit(person, invcredtype.getCode()));
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
                        unitsplit.getCreditSplits().add(getProposalUnitCreditSplit(unitsplit, invcrdtype.getCode()));
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
            ProposalPersonCreditSplit creditSplit = getProposalPersonCreditSplit(person, creditType.getCode());
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
            unit.refreshReferenceObject(UNIT);
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
            retVal.add(getProposalUnitCreditSplit(unit, creditType.getCode()));
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
    public List<ProposalCreditSplitListDto> createCreditSplitListItems(ProposalDevelopmentDocument document) {
        List<ProposalPerson> investigators = document.getDevelopmentProposal().getInvestigators();
        if(!hasBeenRoutedOrCanceled(document)) {
            handleNewCreditTypes(investigators, getInvestigatorCreditTypes());
        }
        return createCreditSplitListDtos(investigators);
    }

    public void handleNewCreditTypes(List<ProposalPerson> investigators, Collection<InvestigatorCreditType> creditTypes) {
        investigators.stream().forEach(person -> {
            List<InvestigatorCreditType> newCreditTypes = creditTypes.stream().filter(creditType -> {
                return person.getCreditSplits().stream().noneMatch(creditSplit -> creditSplit.getInvCreditTypeCode().equals(creditType.getCode()));
            }).collect(Collectors.toList());

            newCreditTypes.stream().forEach(newCreditType -> {
                person.getCreditSplits().add(getProposalPersonCreditSplit(person, newCreditType.getCode()));
            });

            person.getUnits().stream().forEach(unit -> {
                List<InvestigatorCreditType> newUnitCreditTypes = creditTypes.stream().filter(creditType -> {
                    return unit.getCreditSplits().stream().noneMatch(creditSplit -> creditSplit.getInvCreditTypeCode().equals(creditType.getCode()));
                }).collect(Collectors.toList());

                newUnitCreditTypes.stream().forEach(newUnitCreditType -> {
                    unit.getCreditSplits().add(getProposalUnitCreditSplit(unit, newUnitCreditType.getCode()));
                });
            });
        });
    }

    private ProposalPersonCreditSplit getProposalPersonCreditSplit(ProposalPerson person, String code) {
        ProposalPersonCreditSplit creditSplit = new ProposalPersonCreditSplit();
        creditSplit.setProposalPerson(person);
        creditSplit.setInvCreditTypeCode(code);
        creditSplit.setCredit(new ScaleTwoDecimal(0));
        return creditSplit;
    }

    private ProposalUnitCreditSplit getProposalUnitCreditSplit(ProposalPersonUnit unit, String code) {
        ProposalUnitCreditSplit creditSplit = new ProposalUnitCreditSplit();
        creditSplit.setProposalPersonUnit(unit);
        creditSplit.setInvCreditTypeCode(code);
        creditSplit.setCredit(new ScaleTwoDecimal(0));
        return creditSplit;
    }

    public List<ProposalCreditSplitListDto> createCreditSplitListDtos(List<ProposalPerson> investigators) {
        List<ProposalCreditSplitListDto> creditSplitListItems = new ArrayList<ProposalCreditSplitListDto>();
        Map<String,CreditSplit> totalInvestigatorSplits = new HashMap<>();
        for (ProposalPerson investigator : investigators) {
            populateInvestigatorLineItems(creditSplitListItems, totalInvestigatorSplits, investigator);
            populateUnitLineItems(creditSplitListItems, investigator, new HashMap<>());
        }
        populateInvestigatorTotalLineItem(investigators, creditSplitListItems, totalInvestigatorSplits);
        return creditSplitListItems;
    }

    protected void populateInvestigatorTotalLineItem(List<ProposalPerson> investigators, List<ProposalCreditSplitListDto> creditSplitListItems, Map<String, CreditSplit> totalInvestigatorSplits) {
        if (CollectionUtils.isNotEmpty(investigators)) {
            List<CreditSplit> totalInvestigatorCreditSplits = totalInvestigatorSplits.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
            ProposalCreditSplitListDto investigatorTotalLine = createProposalCreditSplitListDto(totalInvestigatorCreditSplits,INVESTIGATOR_TOTAL,INVESTIGATOR_TOTAL_TYPE);
            creditSplitListItems.add(investigatorTotalLine);
        }
    }

    protected void populateUnitLineItems(List<ProposalCreditSplitListDto> creditSplitListItems, ProposalPerson investigator, Map<String, CreditSplit> totalUnitSplits) {
        for(ProposalPersonUnit unit : investigator.getUnits()) {
            List<CreditSplit> unitCreditSplits = new ArrayList<>();
            unitCreditSplits.addAll(unit.getCreditSplits());
            creditSplitListItems.add(createProposalCreditSplitListDto(unitCreditSplits,
                    unit.getUnit().getUnitNumber() + " - " + unit.getUnit().getUnitName(),UNIT));
            for(ProposalUnitCreditSplit unitCreditSplit : unit.getCreditSplits()){
                populateTotalsMap(totalUnitSplits,unitCreditSplit);
            }
        }

        if (totalUnitSplits.size() > 0) {
            List<CreditSplit> totalUnitCreditSplits = totalUnitSplits.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
            ProposalCreditSplitListDto unitTotalLine =createProposalCreditSplitListDto(totalUnitCreditSplits,UNIT_TOTAL,UNIT_TOTAL_TYPE);
            creditSplitListItems.add(unitTotalLine);
        }
    }

    protected void populateInvestigatorLineItems(List<ProposalCreditSplitListDto> creditSplitListItems, Map<String, CreditSplit> totalInvestigatorSplits, ProposalPerson investigator) {
        List<CreditSplit> creditSplits = new ArrayList<>();
        creditSplits.addAll(investigator.getCreditSplits());
        creditSplitListItems.add(createProposalCreditSplitListDto(creditSplits, investigator.getFullName(),INVESTIGATOR));
        for(CreditSplit investigatorCreditSplit : creditSplits){
            populateTotalsMap(totalInvestigatorSplits, investigatorCreditSplit);
        }
    }

    protected void populateTotalsMap(Map<String, CreditSplit> totalsMap, CreditSplit creditSplitToAdd) {
        if (totalsMap.containsKey(creditSplitToAdd.getInvCreditTypeCode())) {
            CreditSplit creditSplit = totalsMap.get(creditSplitToAdd.getInvCreditTypeCode());
            creditSplit.setCredit(creditSplit.getCredit().add(creditSplitToAdd.getCredit()));
        } else {
            CreditSplit creditSplit = new ProposalPersonCreditSplit();
            creditSplit.setCredit(creditSplitToAdd.getCredit());
            creditSplit.setInvCreditTypeCode(creditSplitToAdd.getInvCreditTypeCode());
            totalsMap.put(creditSplitToAdd.getInvCreditTypeCode(),creditSplit);
        }
    }

    protected ProposalCreditSplitListDto createProposalCreditSplitListDto(List<CreditSplit> creditSplits, String description, String lineType) {
        ProposalCreditSplitListDto proposalCreditSplitListDto = new ProposalCreditSplitListDto();
        proposalCreditSplitListDto.setDescription(description);
        proposalCreditSplitListDto.setLineType(lineType);
        Collections.sort(creditSplits, new CreditSplitComparator());
        proposalCreditSplitListDto.getCreditSplits().addAll(creditSplits);
        return proposalCreditSplitListDto;
    }

    private class CreditSplitComparator implements Comparator<CreditSplit> {
        @Override
        public int compare(CreditSplit o1, CreditSplit o2) {
            return o1.getInvCreditTypeCode().compareTo(o2.getInvCreditTypeCode());
        }
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
