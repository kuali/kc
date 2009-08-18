package org.kuali.kra.s2s.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.generator.util.S2STestUtils;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.bo.DocumentHeader;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KualiDecimal;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;
import org.kuali.rice.test.data.UnitTestSql;

@PerTestUnitTestData(@UnitTestData(order = { UnitTestData.Type.SQL_STATEMENTS, UnitTestData.Type.SQL_FILES }, sqlStatements = {
        @UnitTestSql("delete from OSP$SPONSOR_HIERARCHY"), @UnitTestSql("delete from OSP$PARAMETER") }, sqlFiles = {
        @UnitTestFile(filename = "classpath:sql/s2s/data/load_SPONSOR_HIERARCHY.sql", delimiter = ";"),
        @UnitTestFile(filename = "classpath:sql/s2s/data/load_OSP$PARAMETER.sql", delimiter = ";") }))
public class S2SServiceTest extends KraTestBase{
    private static final Logger LOG = Logger.getLogger(S2SServiceTest.class);
    private String proposalNumber;
    private DocumentService documentService;
    private ProposalDevelopmentDocument pd1;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setAuditErrorMap(new HashMap());
        documentService = KNSServiceLocator.getDocumentService();
        ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument) documentService
                .getNewDocument("ProposalDevelopmentDocument");
        savePropDoc(pd);
        pd1 = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(pd.getDocumentHeader().getDocumentNumber());
        pd1.getDevelopmentProposal().getProposalPersons().add(getInvestigator(pd1.getDevelopmentProposal().getProposalNumber()));
        setS2sOpportunity(pd1);
        getService(BusinessObjectService.class).save(pd1);
        pd1.refreshNonUpdateableReferences();
        proposalNumber = pd1.getDevelopmentProposal().getProposalNumber().toString();
        assertNotNull(proposalNumber);
    }

    private void setS2sOpportunity(ProposalDevelopmentDocument pd) {

        List<S2sOpportunity> l = null;
        try {
            l = getS2SService().searchOpportunity("00.000", S2STestUtils.TEST_OPPORTUNITY_ID, null);
        }
        catch (S2SException e) {
            LOG.error(e.getMessage(), e);
        }
        S2sOpportunity opp = l.get(0);
        opp.setProposalNumber(pd.getDevelopmentProposal().getProposalNumber());
        opp.setS2sSubmissionTypeCode("2");
        opp.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
        opp.setUpdateUser("quickst");
        pd.getDevelopmentProposal().setS2sOpportunity(opp);
        pd.getDevelopmentProposal().setCfdaNumber(opp.getCfdaNumber());
        pd.getDevelopmentProposal().setProgramAnnouncementNumber(opp.getOpportunityId());
        pd.getDevelopmentProposal().setProgramAnnouncementTitle(opp.getOpportunityTitle());
        if (opp.getCompetetionId().equals(""))
            opp.setCompetetionId(null);
        pd.getDevelopmentProposal().setMailingAddressId(1);
        addBudget(pd);
        List<S2sOppForms> oppForms = getS2SService().parseOpportunityForms(opp);
        for (S2sOppForms oppForm : oppForms) {
            if (oppForm.getAvailable() && oppForm.getFormName().indexOf("424") != -1) {
                List<S2sOppForms> formList = new ArrayList<S2sOppForms>();
                formList.add(oppForm);
                opp.setS2sOppForms(formList);
                break;
            }
        }

    }

    private void addBudget(ProposalDevelopmentDocument pd) {
        try {
            BudgetDocument budgetDoc = (BudgetDocument) documentService.getNewDocument("BudgetDocument");
            BudgetDocumentVersion budgetDocumentVersion = new BudgetDocumentVersion();
            BudgetVersionOverview budgetOverview = budgetDocumentVersion.getBudgetVersionOverview();
            budgetOverview.setBudgetStatus("1");
//            budgetOverview.setProposalNumber(pd.getDevelopmentProposal().getProposalNumber());
            budgetOverview.setBudgetVersionNumber(1);
            budgetOverview.setFinalVersionFlag(true);
            budgetOverview.setOhRateTypeCode("1");
            budgetOverview.setStartDate(pd.getDevelopmentProposal().getRequestedStartDateInitial());
            budgetOverview.setEndDate(pd.getDevelopmentProposal().getRequestedEndDateInitial());
            budgetOverview.setTotalCost(new BudgetDecimal(10000));
            budgetOverview.setDocumentNumber(budgetDoc.getDocumentNumber());
            pd.getBudgetDocumentVersions().add(budgetDocumentVersion);

            Budget budget = budgetDoc.getBudget();

            budgetDoc.setParentDocumentKey(proposalNumber);
            budgetDoc.setParentDocument(pd);
            budget.setBudgetVersionNumber(1);
            budget.setBudgetStatus("1");
            budget.setTotalCost(new BudgetDecimal(10000));
            budget.setTotalDirectCost(new BudgetDecimal(9000));
            budget.setTotalIndirectCost(new BudgetDecimal(1000));
            budget.setStartDate(pd.getDevelopmentProposal().getRequestedStartDateInitial());
            budget.setEndDate(pd.getDevelopmentProposal().getRequestedEndDateInitial());
            budget.setUpdateTimestamp(pd.getUpdateTimestamp());
            budget.setUpdateUser(pd.getUpdateUser());
            budget.setOhRateTypeCode("1");
            budget.setOhRateClassCode("1");
            budget.setUrRateClassCode("1");
            budget.setModularBudgetFlag(false);
            budget.setActivityTypeCode("1");
            budget.setOnOffCampusFlag("D");
            budget.getBudgetDocument().setParentDocument(pd);
            getService(BusinessObjectService.class).save(budget);
        }
        catch (WorkflowException e) {
            e.printStackTrace();
        }
    }

    private ProposalPerson getInvestigator(String proposalNumber2) {
        ProposalPerson pp = new ProposalPerson();
        pp.setProposalNumber(proposalNumber2);
        pp.setProposalPersonNumber(1);
        pp.setProposalPersonRoleId("PI");
        pp.setPersonId("000000001");
        pp.setLastName("Durkin");
        pp.setFirstName("Terry");
        pp.setFullName("Terry Durkin");
        pp.setUserName("tdurkin");
        pp.setEmailAddress("tdurkin@indiana.edu");
        pp.setGender("M");
        pp.setOfficeLocation("Indiana University");
        pp.setOfficePhone("1233445");
        pp.setPrimaryTitle("Project Manager");
        pp.setHomeUnit("000001");
        pp.setAddressLine1("address 1");
        pp.setAddressLine2("address 2");
        pp.setAddressLine3("address 3");
        pp.setCity("Bloomington");
        pp.setCounty("county");
        pp.setState("IN");
        pp.setPostalCode("12345");
        pp.setCountryCode("USA");
        pp.setEraCommonsUserName("EraCommonsUserName");
        pp.setConflictOfInterestFlag(false);
        pp.setPercentageEffort(new KualiDecimal(100));
        pp.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
        pp.setUpdateUser("quickst");
        pp.setOptInUnitStatus("Y");
        pp.setOptInCertificationStatus("Y");
        addUnits(pp);
        return pp;
    }

    private void addUnits(ProposalPerson pp) {
        ProposalPersonUnit unit = new ProposalPersonUnit();
        unit.setUnitNumber("000001");
        unit.setLeadUnit(true);
        unit.setProposalNumber(pp.getProposalNumber());
        unit.setProposalPersonNumber(pp.getProposalPersonNumber());
        pp.getUnits().add(unit);
    }

    private void savePropDoc(ProposalDevelopmentDocument pd) {
        DevelopmentProposal developmentProposal = pd.getDevelopmentProposal();
        
        developmentProposal.setActivityTypeCode("1");
        developmentProposal.refreshReferenceObject("activityType");
        developmentProposal.setSponsorCode("000162");
        developmentProposal.setOwnedByUnitNumber("000001");
        developmentProposal.refreshReferenceObject("ownedByUnit");
        developmentProposal.setProposalTypeCode("1");
        developmentProposal.setCreationStatusCode("1");
        developmentProposal.setApplicantOrganizationId("000001");
        developmentProposal.setPerformingOrganizationId("000001");
        developmentProposal.setNoticeOfOpportunityCode("1");
        developmentProposal.setRequestedStartDateInitial(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        developmentProposal.setRequestedEndDateInitial(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        developmentProposal.setTitle("Test s2s service title");
        developmentProposal.setDeadlineType("P");
        developmentProposal.setDeadlineDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        developmentProposal.setNsfCode("J.05");

        pd.setUpdateUser("quickst");
        pd.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
        DocumentHeader docHeader = pd.getDocumentHeader();
        docHeader.setDocumentDescription("Test s2s service description");
        String docNumber = docHeader.getDocumentNumber();
        assertNotNull(docNumber);
        getService(BusinessObjectService.class).save(pd);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    private S2SService getS2SService() {
        return getService(S2SService.class);
    }

    @Test
    public void searchOpportunityTest() {
        List<S2sOpportunity> opportunityList = null;
        try {
            opportunityList = getS2SService().searchOpportunity("00.000", null, null);
        }
        catch (S2SException e) {
            LOG.error(e.getMessage(), e);
        }
        assertTrue(opportunityList.size() > 0);
    }

    @Test
    public void parseOpportunityFormsTest() {
        List<S2sOpportunity> opportunityList = null;
        try {
            opportunityList = getS2SService().searchOpportunity("00.000", S2STestUtils.TEST_OPPORTUNITY_ID, null);
        }
        catch (S2SException e) {
            LOG.error(e.getMessage(), e);
        }
        assertNotNull(opportunityList);
        assertTrue(opportunityList.size() > 0);
        S2sOpportunity opp = opportunityList.get(0);
        List<S2sOppForms> oppForms = getS2SService().parseOpportunityForms(opp);
        assertNotNull(oppForms);
        assertTrue(oppForms.size() > 0);
    }

    @Test
    public void validateApplicationTest() {
        try {
            assertTrue(getS2SService().validateApplication(pd1));
        }
        catch (S2SException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Test
    public void submitApplicationTest() {
        try {
            assertTrue(getS2SService().submitApplication(pd1));
            assertTrue(getS2SService().refreshGrantsGov(pd1));
        }
        catch (S2SException e) {
            LOG.error(e.getMessage(), e);
        }
        Map pkMap = new HashMap();
        pkMap.put("proposalNumber", proposalNumber);
        pkMap.put("submissionNumber", 1);
        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
        S2sAppSubmission sub = (S2sAppSubmission) bos.findByPrimaryKey(S2sAppSubmission.class, pkMap);
        assertNotNull(sub);
    }
}
