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
package org.kuali.kra.coi.disclosure;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.FundingSourceType;
import org.kuali.kra.coi.CoiDiscDetail;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.coi.CoiDisclosureHistory;
import org.kuali.kra.coi.CoiDisclosureStatus;
import org.kuali.kra.coi.CoiDispositionStatus;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityService;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

public class CoiDisclosureServiceTest extends KcUnitTestBase {

    private static final String PERSON_ID = "10000000001";
    private static final String ROLE_ID = "COIR";
    private static final String UNIT_NUMBER = "000001";
    private static final String UNIT_NAME = "University";
    private static final String BL_UNIT_NUMBER = "BL-BL";
    private static final String BL_UNIT_NAME = "Bloomington";
    private static final String  ENTITY_NUMBER = "1";
    private static final String  ENTITY_NUMBER_2 = "2";
    private static final String  ENTITY_NAME_1 = "Entity 1";
    private static final String  ENTITY_NAME_2 = "Entity 2";
    private static final String  PROJECT_ID = "100";
    Mockery context = new JUnit4Mockery();

    @Test
    public void testGetDisclosureReporter() throws Exception {
        CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        DisclosurePerson reporter = new DisclosurePerson();
        reporter.setPersonId(PERSON_ID);
        reporter.setPersonRoleId(ROLE_ID);
        reporter.setCoiDisclosureId(1L);

        reporter.setDisclosurePersonUnits(new ArrayList<DisclosurePersonUnit>());
        DisclosurePersonUnit disclosurePersonUnit = new DisclosurePersonUnit() {
            public String getUnitName() {
                return UNIT_NAME;
            }
        };
        disclosurePersonUnit.setUnitNumber(UNIT_NUMBER);
        disclosurePersonUnit.setUnitName(UNIT_NAME);
        disclosurePersonUnit.setLeadUnitFlag(true);
        reporter.getDisclosurePersonUnits().add(disclosurePersonUnit);

        final List<DisclosurePerson> reporters = new ArrayList<DisclosurePerson>();
        reporters.add(reporter);
        context.checking(new Expectations() {
            {
                Map fieldValues = new HashMap();
                fieldValues.put("personId", PERSON_ID);
                fieldValues.put("personRoleId", ROLE_ID);
                fieldValues.put("coiDisclosureId", 1L);
                one(businessObjectService).findMatching(DisclosurePerson.class, fieldValues);
                will(returnValue(reporters));


            }
        });
        coiDisclosureService.setBusinessObjectService(businessObjectService);
        // financialEntityService.setKcPersonService(getMockKcPersonService());

        DisclosurePerson disclosureReporter = coiDisclosureService.getDisclosureReporter(PERSON_ID, 1L);
        Assert.assertEquals(disclosureReporter.getPersonId(), PERSON_ID);
        Assert.assertEquals(disclosureReporter.getPersonRoleId(), ROLE_ID);
        Assert.assertEquals(disclosureReporter.getCoiDisclosureId().toString(), "1");

    }

    @Test
    public void testAddDisclosureReporterUnit() throws Exception {
        CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        DisclosurePerson reporter = new DisclosurePerson();
        reporter.setPersonId(PERSON_ID);
        reporter.setPersonRoleId(ROLE_ID);
        reporter.setCoiDisclosureId(1L);

        reporter.setDisclosurePersonUnits(new ArrayList<DisclosurePersonUnit>());
        DisclosurePersonUnit disclosurePersonUnit = new DisclosurePersonUnit() {
            public String getUnitName() {
                return UNIT_NAME;
            }
        };
        disclosurePersonUnit.setUnitNumber(UNIT_NUMBER);
        disclosurePersonUnit.setUnitName(UNIT_NAME);
        disclosurePersonUnit.setLeadUnitFlag(true);
        reporter.getDisclosurePersonUnits().add(disclosurePersonUnit);

        final List<DisclosurePerson> reporters = new ArrayList<DisclosurePerson>();
        reporters.add(reporter);

        DisclosurePersonUnit newUnit = new DisclosurePersonUnit() {
            public String getUnitName() {
                return BL_UNIT_NAME;
            }
        };
        newUnit.setUnitNumber(BL_UNIT_NUMBER);
        newUnit.setUnitName(BL_UNIT_NAME);
        // set the new unit as lead unit
        newUnit.setLeadUnitFlag(true);

        coiDisclosureService.setBusinessObjectService(businessObjectService);

        coiDisclosureService.addDisclosureReporterUnit(reporter, newUnit);
        Assert.assertEquals(reporter.getDisclosurePersonUnits().size(), 2);
        Assert.assertEquals(reporter.getDisclosurePersonUnits().get(0).getUnitName(), UNIT_NAME);
        Assert.assertEquals(reporter.getDisclosurePersonUnits().get(0).getUnitNumber(), UNIT_NUMBER);
        Assert.assertFalse(reporter.getDisclosurePersonUnits().get(0).isLeadUnitFlag());
        Assert.assertEquals(reporter.getDisclosurePersonUnits().get(1).getUnitName(), BL_UNIT_NAME);
        Assert.assertEquals(reporter.getDisclosurePersonUnits().get(1).getUnitNumber(), BL_UNIT_NUMBER);
        Assert.assertTrue(reporter.getDisclosurePersonUnits().get(1).isLeadUnitFlag());

    }

    @Test
    public void testDeleteDisclosureReporterUnit() throws Exception {
        CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        DisclosurePerson reporter = new DisclosurePerson();
        reporter.setPersonId(PERSON_ID);
        reporter.setPersonRoleId(ROLE_ID);
        reporter.setCoiDisclosureId(1L);

        reporter.setDisclosurePersonUnits(new ArrayList<DisclosurePersonUnit>());
        DisclosurePersonUnit disclosurePersonUnit = new DisclosurePersonUnit() {
            public String getUnitName() {
                return UNIT_NAME;
            }
        };
        disclosurePersonUnit.setUnitNumber(UNIT_NUMBER);
        disclosurePersonUnit.setUnitName(UNIT_NAME);
        disclosurePersonUnit.setLeadUnitFlag(false);
        reporter.getDisclosurePersonUnits().add(disclosurePersonUnit);

        final List<DisclosurePerson> reporters = new ArrayList<DisclosurePerson>();
        reporters.add(reporter);

        DisclosurePersonUnit unit1 = new DisclosurePersonUnit() {
            public String getUnitName() {
                return BL_UNIT_NAME;
            }
        };
        unit1.setUnitNumber(BL_UNIT_NUMBER);
        unit1.setUnitName(BL_UNIT_NAME);
        unit1.setLeadUnitFlag(true);
        reporter.getDisclosurePersonUnits().add(unit1);

        coiDisclosureService.setBusinessObjectService(businessObjectService);

        coiDisclosureService.deleteDisclosureReporterUnit(reporter, reporter.getDisclosurePersonUnits(), 1);
        Assert.assertEquals(reporter.getDisclosurePersonUnits().size(), 1);
        Assert.assertEquals(reporter.getDisclosurePersonUnits().get(0).getUnitName(), UNIT_NAME);
        Assert.assertEquals(reporter.getDisclosurePersonUnits().get(0).getUnitNumber(), UNIT_NUMBER);
        Assert.assertTrue(reporter.getDisclosurePersonUnits().get(0).isLeadUnitFlag());

    }


    @Test
    public void testResetLeadUnit() throws Exception {
        CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        DisclosurePerson reporter = new DisclosurePerson();
        reporter.setPersonId(PERSON_ID);
        reporter.setPersonRoleId(ROLE_ID);
        reporter.setCoiDisclosureId(1L);

        reporter.setDisclosurePersonUnits(new ArrayList<DisclosurePersonUnit>());
        DisclosurePersonUnit disclosurePersonUnit = new DisclosurePersonUnit() {
            public String getUnitName() {
                return UNIT_NAME;
            }
        };
        disclosurePersonUnit.setUnitNumber(UNIT_NUMBER);
        disclosurePersonUnit.setUnitName(UNIT_NAME);
        disclosurePersonUnit.setLeadUnitFlag(false);
        reporter.getDisclosurePersonUnits().add(disclosurePersonUnit);

        final List<DisclosurePerson> reporters = new ArrayList<DisclosurePerson>();
        reporters.add(reporter);

        DisclosurePersonUnit unit1 = new DisclosurePersonUnit() {
            public String getUnitName() {
                return BL_UNIT_NAME;
            }
        };
        unit1.setUnitNumber(BL_UNIT_NUMBER);
        unit1.setUnitName(BL_UNIT_NAME);
        unit1.setLeadUnitFlag(true);
        reporter.getDisclosurePersonUnits().add(unit1);
        // change the lead unit back to the first unit
        reporter.setSelectedUnit(0);
        coiDisclosureService.setBusinessObjectService(businessObjectService);

        coiDisclosureService.resetLeadUnit(reporter);
        Assert.assertEquals(reporter.getDisclosurePersonUnits().size(), 2);
        Assert.assertEquals(reporter.getDisclosurePersonUnits().get(0).getUnitName(), UNIT_NAME);
        Assert.assertEquals(reporter.getDisclosurePersonUnits().get(0).getUnitNumber(), UNIT_NUMBER);
        Assert.assertTrue(reporter.getDisclosurePersonUnits().get(0).isLeadUnitFlag());
        Assert.assertEquals(reporter.getDisclosurePersonUnits().get(1).getUnitName(), BL_UNIT_NAME);
        Assert.assertEquals(reporter.getDisclosurePersonUnits().get(1).getUnitNumber(), BL_UNIT_NUMBER);
        Assert.assertFalse(reporter.getDisclosurePersonUnits().get(1).isLeadUnitFlag());

    }


    @Test
    public void testinitializeAnnualDisclosureDetails() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
       CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final FinancialEntityService financialEntityService = context.mock(FinancialEntityService.class);
        PersonFinIntDisclosure personFinIntDisclosure = createPersonFinIntDisclosure(PERSON_ID, ENTITY_NUMBER,ENTITY_NAME_1);

        final List<PersonFinIntDisclosure> activeEntities = new ArrayList<PersonFinIntDisclosure>();
        activeEntities.add(personFinIntDisclosure);
        context.checking(new Expectations() {
            {
                one(financialEntityService).getFinancialEntities(PERSON_ID, true);
                will(returnValue(activeEntities));


            }
        });
        getEmptyAwardPersons(businessObjectService);
        getEmptyIPPersons(businessObjectService);
        getEmptyProtocolPersons(businessObjectService);
        getProposalPersons(businessObjectService);
        coiDisclosureService.setBusinessObjectService(businessObjectService);
        CoiDisclosure coiDisclosure = new CoiDisclosure() {
            public void initCoiDisclosureNumber()  {
                this.setCoiDisclosureNumber("1");
            }
            public DisclosurePerson getDisclosureReporter() {
                DisclosurePerson reporter = new DisclosurePerson();
                reporter.setPersonId(PERSON_ID);
                reporter.setPersonRoleId(ROLE_ID);
                reporter.setCoiDisclosureId(1L);
                return reporter;
                
            }
        };
        coiDisclosure.setEventTypeCode(CoiDisclosureEventType.ANNUAL);

        coiDisclosureService.setBusinessObjectService(businessObjectService);
        coiDisclosureService.setFinancialEntityService(financialEntityService);
        coiDisclosureService.initializeDisclosureDetails(coiDisclosure);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().size(), 1);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().get(0).getEntityNumber(), ENTITY_NUMBER);

    }

    private void getProposalPersons(final BusinessObjectService businessObjectService) {
        final List<ProposalPerson> proposalPersons = new ArrayList<ProposalPerson>();
        ProposalPerson proposalPerson = new ProposalPerson();
        proposalPerson.setPersonId(PERSON_ID);
        
        DevelopmentProposal proposal = new DevelopmentProposal();
        proposal.setProposalNumber("1");
        proposal.setSponsorCode("000340");
        proposal.setProposalTypeCode("1");
        proposalPerson.setDevelopmentProposal(proposal);
        proposalPersons.add(proposalPerson);
        context.checking(new Expectations() {
            {
                Map<String, Object> fieldValues = new HashMap<String, Object>();
                fieldValues.put("personId", PERSON_ID);
                one(businessObjectService).findMatching(ProposalPerson.class, fieldValues);
                will(returnValue(proposalPersons));

            }
        });
        
    }

    private void getEmptyProtocolPersons(final BusinessObjectService businessObjectService) {
        final List<ProtocolPerson> protocolPersons = new ArrayList<ProtocolPerson>();
        context.checking(new Expectations() {
            {
                Map<String, Object> fieldValues = new HashMap<String, Object>();
                fieldValues.put("personId", PERSON_ID);
                one(businessObjectService).findMatching(ProtocolPerson.class, fieldValues);
                will(returnValue(protocolPersons));

            }
        });
        
    }
    private void getEmptyAwardPersons(final BusinessObjectService businessObjectService) {
        final List<AwardPerson> awardPersons = new ArrayList<AwardPerson>();
        context.checking(new Expectations() {
            {
                Map<String, Object> fieldValues = new HashMap<String, Object>();
                fieldValues.put("personId", PERSON_ID);
                one(businessObjectService).findMatchingOrderBy(AwardPerson.class, fieldValues, "awardNumber", true);;
                will(returnValue(awardPersons));

            }
        });
        
    }
    
    private void getEmptyIPPersons(final BusinessObjectService businessObjectService) {
        final List<InstitutionalProposalPerson> proposalPersons = new ArrayList<InstitutionalProposalPerson>();
        context.checking(new Expectations() {
            {
                Map<String, Object> fieldValues = new HashMap<String, Object>();
                fieldValues.put("personId", PERSON_ID);
                one(businessObjectService).findMatching(InstitutionalProposalPerson.class, fieldValues);
                will(returnValue(proposalPersons));

            }
        });
        
    }

    @Test
    public void testUpdateDisclosureDetails() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
       CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final FinancialEntityService financialEntityService = context.mock(FinancialEntityService.class);
        PersonFinIntDisclosure personFinIntDisclosure = createPersonFinIntDisclosure(PERSON_ID, ENTITY_NUMBER,ENTITY_NAME_1);

        // this will be new FE, and it will be added to coi FE
        PersonFinIntDisclosure personFinIntDisclosure1 = createPersonFinIntDisclosure(PERSON_ID, ENTITY_NUMBER_2,ENTITY_NAME_2);

        final List<PersonFinIntDisclosure> activeEntities = new ArrayList<PersonFinIntDisclosure>();
        activeEntities.add(personFinIntDisclosure);
        activeEntities.add(personFinIntDisclosure1);
        context.checking(new Expectations() {
            {
                one(financialEntityService).getFinancialEntities(PERSON_ID, true);
                will(returnValue(activeEntities));


            }
        });
        final DevelopmentProposal proposal = new DevelopmentProposal();
        proposal.setProposalNumber(PROJECT_ID);
        proposal.setSponsorCode("000340");
        proposal.setProposalTypeCode("1");
        context.checking(new Expectations() {
            {
                Map<String, Object> primaryKeys = new HashMap<String, Object>();
                primaryKeys.put("proposalNumber", PROJECT_ID);
                one(businessObjectService).findByPrimaryKey(DevelopmentProposal.class, primaryKeys);
                will(returnValue(proposal));

            }
        });

        coiDisclosureService.setBusinessObjectService(businessObjectService);
        coiDisclosureService.setFinancialEntityService(financialEntityService);
        CoiDisclosure coiDisclosure = new CoiDisclosure() {
            public void initCoiDisclosureNumber()  {
                this.setCoiDisclosureNumber("1");
            }
            public DisclosurePerson getDisclosureReporter() {
                DisclosurePerson reporter = new DisclosurePerson();
                reporter.setPersonId(PERSON_ID);
                reporter.setPersonRoleId(ROLE_ID);
                reporter.setCoiDisclosureId(1L);
                return reporter;
                
            }
        };
        coiDisclosure.setEventTypeCode(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL);
        coiDisclosure.setCoiDiscDetails(new ArrayList<CoiDiscDetail>());
        coiDisclosure.setPersonId("10000000001");
        coiDisclosure.getCoiDiscDetails().add(createNewCoiDiscDetail(CoiDisclosure.PROPOSAL_DISCL_MODULE_CODE, personFinIntDisclosure, CoiDisclosureEventType.DEVELOPMENT_PROPOSAL));
        coiDisclosureService.setBusinessObjectService(businessObjectService);
        coiDisclosureService.setFinancialEntityService(financialEntityService);
        coiDisclosureService.updateDisclosureDetails(coiDisclosure);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().size(), 2);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().get(0).getEntityNumber(), ENTITY_NUMBER);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().get(1).getEntityNumber(), ENTITY_NUMBER_2);

    }

    private CoiDiscDetail createNewCoiDiscDetail(String moduleCode, PersonFinIntDisclosure personFinIntDisclosure, String projectType) {
        CoiDiscDetail disclosureDetail = new CoiDiscDetail(personFinIntDisclosure);
        disclosureDetail.setModuleItemKey(PROJECT_ID);
        // TODO : this is how coeus set. not sure ?
        disclosureDetail.setModuleCode(moduleCode);
        disclosureDetail.setCoiDisclosureNumber("1");
        disclosureDetail.setSequenceNumber(1);
        disclosureDetail.setProjectType(projectType);
        disclosureDetail.setDescription("Sample Description"); // this is from coeus.
        return disclosureDetail;
        
    }

    @Test
    public void testinitializeDisclosureDetails() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
       CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final FinancialEntityService financialEntityService = context.mock(FinancialEntityService.class);
        PersonFinIntDisclosure personFinIntDisclosure = createPersonFinIntDisclosure(PERSON_ID, ENTITY_NUMBER,ENTITY_NAME_1);

        final List<PersonFinIntDisclosure> activeEntities = new ArrayList<PersonFinIntDisclosure>();
        activeEntities.add(personFinIntDisclosure);
        context.checking(new Expectations() {
            {
                one(financialEntityService).getFinancialEntities(PERSON_ID, true);
                will(returnValue(activeEntities));


            }
        });
        final DevelopmentProposal proposal = new DevelopmentProposal();
        proposal.setProposalNumber(PROJECT_ID);
        proposal.setSponsorCode("000340");
        proposal.setProposalTypeCode("1");
        context.checking(new Expectations() {
            {
                Map<String, Object> primaryKeys = new HashMap<String, Object>();
                primaryKeys.put("proposalNumber", PROJECT_ID);
                one(businessObjectService).findByPrimaryKey(DevelopmentProposal.class, primaryKeys);
                will(returnValue(proposal));

            }
        });

        coiDisclosureService.setBusinessObjectService(businessObjectService);
        coiDisclosureService.setFinancialEntityService(financialEntityService);
        CoiDisclosure coiDisclosure = new CoiDisclosure() {
            public void initCoiDisclosureNumber()  {
                this.setCoiDisclosureNumber("1");
            }
            public DisclosurePerson getDisclosureReporter() {
                DisclosurePerson reporter = new DisclosurePerson();
                reporter.setPersonId(PERSON_ID);
                reporter.setPersonRoleId(ROLE_ID);
                reporter.setCoiDisclosureId(1L);
                return reporter;
                
            }
        };
        coiDisclosure.setEventTypeCode(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL);

        coiDisclosureService.setBusinessObjectService(businessObjectService);
        coiDisclosureService.setFinancialEntityService(financialEntityService);
        coiDisclosureService.initializeDisclosureDetails(coiDisclosure, PROJECT_ID);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().size(), 1);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().get(0).getEntityNumber(), ENTITY_NUMBER);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().get(0).getProjectType(), CoiDisclosureEventType.DEVELOPMENT_PROPOSAL);

    }

    @Test
    public void testUpdateManualDisclosureDetails() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
       CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        final FinancialEntityService financialEntityService = context.mock(FinancialEntityService.class);
        PersonFinIntDisclosure personFinIntDisclosure = createPersonFinIntDisclosure(PERSON_ID, ENTITY_NUMBER,ENTITY_NAME_1);

        // this will be new FE, and it will be added to coi FE
        PersonFinIntDisclosure personFinIntDisclosure1 = createPersonFinIntDisclosure(PERSON_ID, ENTITY_NUMBER_2,ENTITY_NAME_2);

        final List<PersonFinIntDisclosure> activeEntities = new ArrayList<PersonFinIntDisclosure>();
        activeEntities.add(personFinIntDisclosure);
        activeEntities.add(personFinIntDisclosure1);
        context.checking(new Expectations() {
            {
                one(financialEntityService).getFinancialEntities(PERSON_ID, true);
                will(returnValue(activeEntities));


            }
        });
        final CoiDisclProject coiDisclProject = new CoiDisclProject();
        coiDisclProject.setCoiDisclosureId(1L);
        coiDisclProject.setShortTextField1(PROJECT_ID);

        CoiDiscDetail coiDisDetail = createNewCoiDiscDetail(CoiDisclosure.MANUAL_DISCL_MODULE_CODE, personFinIntDisclosure, CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL);
        CoiDisclosure coiDisclosure = new CoiDisclosure() {
            public void initCoiDisclosureNumber()  {
                this.setCoiDisclosureNumber("1");
            }
            public DisclosurePerson getDisclosureReporter() {
                DisclosurePerson reporter = new DisclosurePerson();
                reporter.setPersonId(PERSON_ID);
                reporter.setPersonRoleId(ROLE_ID);
                reporter.setCoiDisclosureId(1L);
                return reporter;
                
            }
        };
      //  coiDisclosure.setModuleCode(CoiDisclosure.MANUAL_DISCL_MODULE_CODE);
        coiDisclosure.setEventTypeCode(CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL);
        coiDisclosure.setCoiDiscDetails(new ArrayList<CoiDiscDetail>());
        coiDisclosure.getCoiDiscDetails().add(coiDisDetail);
        coiDisclProject.setCoiDisclosure(coiDisclosure);
        coiDisclosure.setPersonId(PERSON_ID);
        coiDisDetail.setPersonFinIntDisclosure(personFinIntDisclosure);
        final List<CoiDiscDetail> coiDisDetails = new ArrayList<CoiDiscDetail>();
        coiDisDetails.add(coiDisDetail);
        context.checking(new Expectations() {
            {
                Map <String, Object> fieldValues = new HashMap<String, Object>();
                fieldValues.put("coiDisclosureId", coiDisclProject.getCoiDisclosureId());
                fieldValues.put("moduleCode", CoiDisclosure.MANUAL_DISCL_MODULE_CODE);
                fieldValues.put("moduleItemKey", coiDisclProject.getShortTextField1());
                one(businessObjectService).findMatching(CoiDiscDetail.class, fieldValues);
                will(returnValue(coiDisDetails));

            }
        });

        coiDisclosureService.setBusinessObjectService(businessObjectService);
        coiDisclosureService.setFinancialEntityService(financialEntityService);
        coiDisclosureService.updateDisclosureDetails(coiDisclProject);
        Assert.assertEquals(coiDisclProject.getCoiDiscDetails().size(), 2);
        Assert.assertEquals(coiDisclProject.getCoiDiscDetails().get(0).getEntityNumber(), ENTITY_NUMBER);
        Assert.assertEquals(coiDisclProject.getCoiDiscDetails().get(1).getEntityNumber(), ENTITY_NUMBER_2);

    }
    @Test
    public void testSetManualDisclDetailsForSave() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
       CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
       PersonFinIntDisclosure personFinIntDisclosure = createPersonFinIntDisclosure(PERSON_ID, ENTITY_NUMBER,ENTITY_NAME_1);

       // this will be new FE, and it will be added to coi FE
       PersonFinIntDisclosure personFinIntDisclosure1 = createPersonFinIntDisclosure(PERSON_ID, ENTITY_NUMBER_2,ENTITY_NAME_2);

        final CoiDisclProject coiDisclProject = new CoiDisclProject();
        coiDisclProject.setCoiDisclosureId(1L);
        coiDisclProject.setShortTextField1(PROJECT_ID);

        CoiDiscDetail coiDisDetail = createNewCoiDiscDetail(CoiDisclosure.MANUAL_DISCL_MODULE_CODE, personFinIntDisclosure, CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL);
        CoiDiscDetail coiDisDetail1 = createNewCoiDiscDetail(CoiDisclosure.MANUAL_DISCL_MODULE_CODE, personFinIntDisclosure1, CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL);
        CoiDisclosure coiDisclosure = new CoiDisclosure() {
            public void initCoiDisclosureNumber()  {
                this.setCoiDisclosureNumber("1");
            }
            public DisclosurePerson getDisclosureReporter() {
                DisclosurePerson reporter = new DisclosurePerson();
                reporter.setPersonId(PERSON_ID);
                reporter.setPersonRoleId(ROLE_ID);
                reporter.setCoiDisclosureId(1L);
                return reporter;
                
            }
        };
 //       coiDisclosure.setModuleCode(CoiDisclosure.MANUAL_DISCL_MODULE_CODE);
        coiDisclosure.setEventTypeCode(CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL);
        coiDisclProject.setCoiDisclosure(coiDisclosure);
        final List<CoiDiscDetail> coiDisDetails = new ArrayList<CoiDiscDetail>();
        coiDisDetails.add(coiDisDetail);
        coiDisDetails.add(coiDisDetail1);
        coiDisclProject.setCoiDiscDetails(coiDisDetails);
        coiDisclosure.setCoiDisclProjects(new ArrayList<CoiDisclProject>());
        coiDisclosure.getCoiDisclProjects().add(coiDisclProject);
        coiDisclosureService.setDisclDetailsForSave(coiDisclosure);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().size(), 2);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().get(0).getEntityNumber(), ENTITY_NUMBER);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().get(1).getEntityNumber(), ENTITY_NUMBER_2);

    }

    private PersonFinIntDisclosure createPersonFinIntDisclosure(String personId, String entityNumber, String entityName) {
        PersonFinIntDisclosure personFinIntDisclosure = new PersonFinIntDisclosure();
        personFinIntDisclosure.setPersonId(personId);
        personFinIntDisclosure.setStatusCode(1);
        personFinIntDisclosure.setCurrentFlag(true);
        personFinIntDisclosure.setEntityNumber(entityNumber);
        personFinIntDisclosure.setEntityName(entityName);
        return personFinIntDisclosure;
    }
    @Test
    public void testSetAnnualDisclDetailsForSave() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
       CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
        PersonFinIntDisclosure personFinIntDisclosure = createPersonFinIntDisclosure(PERSON_ID, ENTITY_NUMBER,ENTITY_NAME_1);

        // this will be new FE, and it will be added to coi FE
        PersonFinIntDisclosure personFinIntDisclosure1 = createPersonFinIntDisclosure(PERSON_ID, ENTITY_NUMBER_2,ENTITY_NAME_2);

        final CoiDisclEventProject coiDisclProject = new CoiDisclEventProject();
        coiDisclProject.setEventType(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL);
 
        CoiDiscDetail coiDisDetail = createNewCoiDiscDetail(CoiDisclosure.ANNUAL_DISCL_MODULE_CODE, personFinIntDisclosure, CoiDisclosureEventType.DEVELOPMENT_PROPOSAL);
        CoiDiscDetail coiDisDetail1 = createNewCoiDiscDetail(CoiDisclosure.ANNUAL_DISCL_MODULE_CODE, personFinIntDisclosure1, CoiDisclosureEventType.DEVELOPMENT_PROPOSAL);
        CoiDisclosure coiDisclosure = new CoiDisclosure() {
            public void initCoiDisclosureNumber()  {
                this.setCoiDisclosureNumber("1");
            }
            public DisclosurePerson getDisclosureReporter() {
                DisclosurePerson reporter = new DisclosurePerson();
                reporter.setPersonId(PERSON_ID);
                reporter.setPersonRoleId(ROLE_ID);
                reporter.setCoiDisclosureId(1L);
                return reporter;
                
            }
        };
        coiDisclosure.setEventTypeCode(CoiDisclosureEventType.ANNUAL);
        final List<CoiDiscDetail> coiDisDetails = new ArrayList<CoiDiscDetail>();
        coiDisDetails.add(coiDisDetail);
        coiDisDetails.add(coiDisDetail1);
        coiDisclProject.setCoiDiscDetails(coiDisDetails);
        coiDisclosure.setCoiDisclEventProjects(new ArrayList<CoiDisclEventProject>());
        coiDisclosure.getCoiDisclEventProjects().add(coiDisclProject);
        coiDisclosureService.setDisclDetailsForSave(coiDisclosure);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().size(), 2);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().get(0).getEntityNumber(), ENTITY_NUMBER);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().get(1).getEntityNumber(), ENTITY_NUMBER_2);

    }

    @Test
   public void testVersionDisclosures() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
       final VersioningService versioningService = context.mock(VersioningService.class);
       final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
       final CoiDisclosure coiDisclosure1 = getCoiDisclosure(1);
       final CoiDisclosure coiDisclosure2 = getCoiDisclosure(2);
       final CoiDisclosure coiDisclosure3 = getCoiDisclosure(3);
       final List<CoiDisclosure> coiDisclosures = new ArrayList<CoiDisclosure>();
       coiDisclosures.add(coiDisclosure2);
       coiDisclosures.add(coiDisclosure1);
       context.checking(new Expectations() {
           {
               one(versioningService).createNewVersion(coiDisclosure2);
               will(returnValue(coiDisclosure3));


           }
       });
       context.checking(new Expectations() {
           {
               Map<String, Object> fieldValues = new HashMap<String, Object>();
               fieldValues.put("personId", GlobalVariables.getUserSession().getPrincipalId());

               one(businessObjectService).findMatchingOrderBy(CoiDisclosure.class, fieldValues, "sequenceNumber", false);
               will(returnValue(coiDisclosures));
               


           }
       });

       coiDisclosureService.setBusinessObjectService(businessObjectService);
       coiDisclosureService.setVersioningService(versioningService);
       CoiDisclosure newCoiDisclosure = coiDisclosureService.versionCoiDisclosure();
       Assert.assertEquals(newCoiDisclosure.getSequenceNumber(), new Integer(3));
   }

    private CoiDisclosure getCoiDisclosure(Integer sequenceNumber) {
        CoiDisclosure coiDisclosure = new CoiDisclosure() {
            public void initCoiDisclosureNumber()  {
                this.setCoiDisclosureNumber("1");
            }
            public DisclosurePerson getDisclosureReporter() {
                DisclosurePerson reporter = new DisclosurePerson();
                reporter.setPersonId(PERSON_ID);
                reporter.setPersonRoleId(ROLE_ID);
                reporter.setCoiDisclosureId(1L);
                return reporter;
                
            }
        };
        coiDisclosure.setCoiDisclosureNumber("1");
        coiDisclosure.setSequenceNumber(sequenceNumber);
        return coiDisclosure;
    }
    
    
    @Test
   public void testGetProposals() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
       final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
       final List<ProposalPerson> proposalPersons = new ArrayList<ProposalPerson>();
       ProposalPerson proposalPerson = new ProposalPerson();
       proposalPerson.setPersonId(PERSON_ID);
       
       DevelopmentProposal proposal = new DevelopmentProposal();
       proposal.setProposalNumber("1");
       proposal.setSponsorCode("000340");
       proposal.setProposalTypeCode("1");
       proposalPerson.setDevelopmentProposal(proposal);
       proposalPersons.add(proposalPerson);
       context.checking(new Expectations() {
           {
               Map<String, Object> fieldValues = new HashMap<String, Object>();
               fieldValues.put("personId", PERSON_ID);
               one(businessObjectService).findMatching(ProposalPerson.class, fieldValues);
               will(returnValue(proposalPersons));

           }
       });
       
       final List<DevelopmentProposal> proposals = new ArrayList<DevelopmentProposal>();

       coiDisclosureService.setBusinessObjectService(businessObjectService);
       List<DevelopmentProposal> retValues = coiDisclosureService.getProposals(PERSON_ID);
       Assert.assertEquals(retValues.size(), 1);
       Assert.assertEquals(retValues.get(0).getProposalNumber(), "1");
   }

    @Test
   public void testGetProtocols() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
       final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
       final List<ProtocolPerson> persons = new ArrayList<ProtocolPerson>();
       ProtocolPerson person = new ProtocolPerson();
       person.setPersonId(PERSON_ID);
       
       Protocol protocol = new Protocol();
       protocol.setProtocolNumber("1");
       protocol.setProtocolStatusCode("100");
       
       protocol.setActive(true);
       ProtocolFundingSource fundingSource = new ProtocolFundingSource();
       fundingSource.setFundingSourceTypeCode(FundingSourceType.SPONSOR);
       fundingSource.setFundingSourceNumber("000340");
       protocol.setProtocolFundingSources(new ArrayList<ProtocolFundingSource>());
       protocol.getProtocolFundingSources().add(fundingSource);
       person.setProtocol(protocol);
       persons.add(person);
       context.checking(new Expectations() {
           {
               Map<String, Object> fieldValues = new HashMap<String, Object>();
               fieldValues.put("personId", PERSON_ID);
               one(businessObjectService).findMatching(ProtocolPerson.class, fieldValues);
               will(returnValue(persons));

           }
       });
       
       final List<Protocol> protocols = new ArrayList<Protocol>();

       coiDisclosureService.setBusinessObjectService(businessObjectService);
       List<Protocol> retValues = coiDisclosureService.getProtocols(PERSON_ID);
       Assert.assertEquals(retValues.size(), 1);
       Assert.assertEquals(retValues.get(0).getProtocolNumber(), "1");
   }

    @Test
   public void testGetAwards() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
       final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
       final List<AwardPerson> awardPersons = new ArrayList<AwardPerson>();
       AwardPerson awardPerson = new AwardPerson();
       awardPerson.setPersonId(PERSON_ID);
       
       Award award = new Award();
       award.setAwardNumber("1");
       award.setAwardId(1L);
       award.setSponsorCode("000340");
       award.setStatusCode(1);
       awardPerson.setAward(award);
       awardPersons.add(awardPerson);
       context.checking(new Expectations() {
           {
               Map<String, Object> fieldValues = new HashMap<String, Object>();
               fieldValues.put("personId", PERSON_ID);
               one(businessObjectService).findMatchingOrderBy(AwardPerson.class, fieldValues, "awardNumber", true);;
               will(returnValue(awardPersons));

           }
       });
       final List<Award> awards = new ArrayList<Award>();
       awards.add(award);
       context.checking(new Expectations() {
           {
               Map<String, Object> values = new HashMap<String, Object>();
               values.put("awardNumber", "1");
               one(businessObjectService).findMatchingOrderBy(Award.class, values, "sequenceNumber", false);
               will(returnValue(awards));

           }
       });
       
       final List<Award> proposals = new ArrayList<Award>();

       coiDisclosureService.setBusinessObjectService(businessObjectService);
       List<Award> retValues = coiDisclosureService.getAwards(PERSON_ID);
       Assert.assertEquals(retValues.size(), 1);
       Assert.assertEquals(retValues.get(0).getAwardNumber(), "1");
   }


    @Test
    public void testGetMasterDisclosureDetail() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
       CoiDisclosureServiceImpl coiDisclosureService = new CoiDisclosureServiceImpl();
        PersonFinIntDisclosure personFinIntDisclosure = createPersonFinIntDisclosure(PERSON_ID, ENTITY_NUMBER,ENTITY_NAME_1);

        // this will be new FE, and it will be added to coi FE
        PersonFinIntDisclosure personFinIntDisclosure1 = createPersonFinIntDisclosure(PERSON_ID, ENTITY_NUMBER_2,ENTITY_NAME_2);
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);

        // create previous master disclosure
        CoiDisclosure coiDisclosure0 = createCoiDisclosure();
        CoiDiscDetail coiDisDetail0 = createNewCoiDiscDetail(CoiDisclosure.MANUAL_DISCL_MODULE_CODE, personFinIntDisclosure, CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL);
        coiDisDetail0.setModuleItemKey("1");
        coiDisDetail0.setCoiDiscDetailId(1L);
        final List<CoiDiscDetail> coiDisDetails0 = new ArrayList<CoiDiscDetail>();
        coiDisDetails0.add(coiDisDetail0);
        coiDisclosure0.setCoiDiscDetails(coiDisDetails0);


//        final CoiDisclEventProject coiDisclProject = new CoiDisclEventProject();
//        coiDisclProject.setEventType(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL);
 
        CoiDiscDetail coiDisDetail = createNewCoiDiscDetail(CoiDisclosure.MANUAL_DISCL_MODULE_CODE, personFinIntDisclosure, CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL);
        coiDisDetail.setModuleItemKey("1");
        coiDisDetail.setCoiDiscDetailId(1L);
        coiDisDetail.setCopiedCoiDiscDetailId(1L);
        coiDisDetail.setOriginalCoiDisclosureId(1L);
        CoiDiscDetail coiDisDetail1 = createNewCoiDiscDetail(CoiDisclosure.MANUAL_DISCL_MODULE_CODE, personFinIntDisclosure1, CoiDisclosureEventType.MANUAL_AWARD);
        coiDisDetail1.setCoiDiscDetailId(2L);
        coiDisDetail1.setModuleItemKey("2");
        // current disclosure
       CoiDisclosure coiDisclosure = createCoiDisclosure();
       coiDisclosure.setCoiDisclosureId(2L);
       coiDisclosure.setEventTypeCode(CoiDisclosureEventType.AWARD);
       final List<CoiDiscDetail> coiDisDetails = new ArrayList<CoiDiscDetail>();
       coiDisDetails.add(coiDisDetail);
       coiDisDetails.add(coiDisDetail1);
       coiDisclosure.setCoiDiscDetails(coiDisDetails);
        
        
        final List<CoiDisclProject> coiDisclProjects = new ArrayList<CoiDisclProject>();
        CoiDisclProject coiDisclProject = createCoiDIsclProject(1L);
        coiDisclProjects.add(coiDisclProject);
        
        final List<CoiDisclProject> coiDisclProjects1 = new ArrayList<CoiDisclProject>();
        CoiDisclProject coiDisclProject1 = createCoiDIsclProject(2L);
        coiDisclProjects1.add(coiDisclProject1);
        
        final List<CoiDisclosureHistory> histories = new ArrayList<CoiDisclosureHistory>();
        CoiDisclosureHistory history1 = createCoidisclosureHistory(1L, 1);
        CoiDisclosureHistory history2 = createCoidisclosureHistory(2L, 2);
        history1.setCoiDisclosure(coiDisclosure0);
        history2.setCoiDisclosure(coiDisclosure);
        histories.add(history1);
        histories.add(history2);
        context.checking(new Expectations() {
            {
                Map <String, Object> fieldValues = new HashMap<String, Object>();
                fieldValues.put("coiDisclosureNumber", "1");
                // this is unique
                fieldValues.put("coiProjectId", "1");
                atLeast(1).of(businessObjectService).findMatching(CoiDisclProject.class, fieldValues);;
                will(returnValue(coiDisclProjects));
                Map <String, Object> fieldValues1 = new HashMap<String, Object>();
                fieldValues1.put("coiDisclosureNumber", "1");
                // this is unique
                fieldValues1.put("coiProjectId", "2");
                atLeast(1).of(businessObjectService).findMatching(CoiDisclProject.class, fieldValues1);
                will(returnValue(coiDisclProjects1));
                Map<String, Object> fieldValues2 = new HashMap<String, Object>();
                fieldValues2.put("coiDisclosureNumber", "1");
                fieldValues2.put("disclosureDispositionStatus", CoiDispositionStatus.APPROVED);
                one(businessObjectService).findMatchingOrderBy(CoiDisclosureHistory.class, fieldValues2,
                        "sequenceNumber", true);
                will(returnValue(histories));

            }
        });

        coiDisclosureService.setBusinessObjectService(businessObjectService);
//        coiDisclosure.setCoiDisclEventProjects(new ArrayList<CoiDisclProject>());
//        coiDisclosure.getCoiDisclEventProjects().add(coiDisclProject);
        MasterDisclosureBean masterDisclosureBean = coiDisclosureService.getMasterDisclosureDetail(coiDisclosure);
        Assert.assertEquals(coiDisclosure.getCoiDiscDetails().size(), 2);
        Assert.assertEquals(masterDisclosureBean.getAllProjects().size(), 2);
        Assert.assertEquals(masterDisclosureBean.getManualAwardProjects().size(), 1);
        Assert.assertEquals(masterDisclosureBean.getManualProposalProjects().size(), 1);

    }

    private CoiDisclProject createCoiDIsclProject(Long coiDisclosureId) {
        CoiDisclProject coiDisclProject = new CoiDisclProject();
        coiDisclProject.setCoiDisclosureId(coiDisclosureId);
        coiDisclProject.setCoiDisclosureNumber("1");
        coiDisclProject.setShortTextField1("1");
        return coiDisclProject;
    
    }
    
    private CoiDisclosure createCoiDisclosure() {
        CoiDisclosure coiDisclosure = new CoiDisclosure() {
            public void initCoiDisclosureNumber()  {
                this.setCoiDisclosureNumber("1");
            }
            public DisclosurePerson getDisclosureReporter() {
                DisclosurePerson reporter = new DisclosurePerson();
                reporter.setPersonId(PERSON_ID);
                reporter.setPersonRoleId(ROLE_ID);
                reporter.setCoiDisclosureId(1L);
                return reporter;
                
            }
        };
        return coiDisclosure;
    }
    
    private CoiDisclosureHistory createCoidisclosureHistory(Long coiDisclosureId, Integer sequenceNumber) {
        CoiDisclosureHistory history = new CoiDisclosureHistory() {
            @Override
            public void refreshReferenceObject(String referenceObjectName) {
                // do nothing
                
            }
        };
        history.setCoiDisclosureId(coiDisclosureId);
        history.setCoiDisclosureHistoryId(coiDisclosureId);
        history.setCoiDisclosureNumber("1");
        history.setSequenceNumber(sequenceNumber);
        history.setDisclosureStatus(CoiDisclosureStatus.RESOLVED);
        history.setDisclosureDispositionStatus(CoiDispositionStatus.APPROVED);
        history.setUpdateTimestamp(new Timestamp(new java.util.Date().getTime()));
        return history;
    }
}
