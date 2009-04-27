package org.kuali.kra.s2s;


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
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.service.S2SService;
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

import edu.mit.coeus.utils.S2SConstants;

@PerTestUnitTestData(
        @UnitTestData(order = {
                UnitTestData.Type.SQL_STATEMENTS, UnitTestData.Type.SQL_FILES }, 
                sqlStatements = {
                              @UnitTestSql("delete from OSP$SPONSOR_HIERARCHY"),
                              @UnitTestSql("delete from OSP$PARAMETER")
                              ,@UnitTestSql("commit")
                              }, 
                sqlFiles = {
                             @UnitTestFile(filename = "classpath:sql/s2s/data/load_SPONSOR_HIERARCHY.sql", delimiter = ";")
                             ,@UnitTestFile(filename = "classpath:sql/s2s/data/load_OSP$PARAMETER.sql", delimiter = ";")
                })
        )

public class S2SServiceTest extends KraTestBase implements S2SConstants{
    private static final Logger LOG = Logger.getLogger(S2SServiceTest.class);
    private String proposalNumber;
    private DocumentService documentService;
    @Before
	public void setUp() throws Exception {
		super.setUp();
		GlobalVariables.setUserSession(new UserSession("quickstart"));
        GlobalVariables.setErrorMap(new ErrorMap());
        GlobalVariables.setAuditErrorMap(new HashMap());
        documentService = KNSServiceLocator.getDocumentService();
//        configureSSL();
        configureSoap();
        ProposalDevelopmentDocument pd = (ProposalDevelopmentDocument) documentService.getNewDocument("ProposalDevelopmentDocument");
        savePropDoc(pd);
        ProposalDevelopmentDocument pd1 = (ProposalDevelopmentDocument)documentService.getByDocumentHeaderId(pd.getDocumentHeader().getDocumentNumber());
        pd1.getProposalPersons().add(getInvestigator(pd1.getProposalNumber()));
        setS2sOpportunity(pd1);
        getService(BusinessObjectService.class).save(pd1);
        proposalNumber = pd1.getProposalNumber().toString();
        assertNotNull(proposalNumber);
	}
    private void setS2sOpportunity(ProposalDevelopmentDocument pd) {
        
//        List<S2sOpportunity> l = getS2SService().searchOpportunity("00.000", "APP-S2S-TEST-SF424-V2", null);
        List<S2sOpportunity> l = getS2SService().searchOpportunity("00.000", null, null);
        S2sOpportunity opp  = l.get(0);
        opp.setProposalNumber(pd.getProposalNumber());
        opp.setS2sSubmissionTypeCode("2");
        opp.setUpdateTimestamp(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
        opp.setUpdateUser("quickst");
        pd.setS2sOpportunity(opp);
        pd.setCfdaNumber(opp.getCfdaNumber());
        pd.setProgramAnnouncementNumber(opp.getOpportunityId());
        pd.setProgramAnnouncementTitle(opp.getOpportunityTitle());
        pd.setMailingAddressId(1);
        addBudget(pd);
        List<S2sOppForms> oppForms = getS2SService().parseOpportunityForms(opp);
        for (S2sOppForms oppForm : oppForms) {
            if(oppForm.getAvailable() && oppForm.getFormName().indexOf("424")!=-1){
                List<S2sOppForms> formList = new ArrayList<S2sOppForms>();
                formList.add(oppForm);
                opp.setS2sOppForms(formList);
                break;
            }
        }
        
    }
    private void addBudget(ProposalDevelopmentDocument pd) {
        try {
            BudgetDocument budget = (BudgetDocument) documentService.getNewDocument("BudgetDocument");
//            BudgetVersionOverview budget = new BudgetVersionOverview();
            budget.setProposalNumber(pd.getProposalNumber());
            budget.setBudgetVersionNumber(1);
            budget.setBudgetStatus("1");
            budget.setTotalCost(new BudgetDecimal(10000));
            budget.setTotalDirectCost(new BudgetDecimal(9000));
            budget.setTotalIndirectCost(new BudgetDecimal(1000));
//            budget.setDocumentNumber(bd.getDocumentNumber());
            budget.setStartDate(pd.getRequestedStartDateInitial());
            budget.setEndDate(pd.getRequestedEndDateInitial());
            budget.setUpdateTimestamp(pd.getUpdateTimestamp());
            budget.setUpdateUser(pd.getUpdateUser());
            budget.setOhRateTypeCode("1");
            budget.setOhRateClassCode("1");
            budget.setUrRateClassCode("1");
            budget.setModularBudgetFlag(false);
            budget.setActivityTypeCode("1");
            budget.setOnOffCampusFlag("D");
            budget.setProposal(pd);
            
            getService(BusinessObjectService.class).save(budget);
//            pd.getBudgetVersionOverviews().add(budget);
        }
        catch (WorkflowException e) {
            // TODO Auto-generated catch block
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
        pd.setActivityTypeCode("1");
        pd.refreshReferenceObject("activityType");
//        Map pkMap = new HashMap();
//        pkMap.put("activityTypeCode", "1");
//        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
//        ActivityType at = (ActivityType)bos.findByPrimaryKey(ActivityType.class, pkMap);
//        pd.setActivityType(at);
        pd.setSponsorCode("000162");
        pd.setOwnedByUnitNumber("000001");
        pd.refreshReferenceObject("ownedByUnit");
//        Map pkUnitMap = new HashMap();
//        pkUnitMap.put("activityTypeCode", "1");
//        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
//        ActivityType at = (ActivityType)bos.findByPrimaryKey(ActivityType.class, pkMap);
        pd.setProposalTypeCode("1");
        pd.setCreationStatusCode("1");
        pd.setOrganizationId("000001");
        pd.setPerformingOrganizationId("000001");
        pd.setNoticeOfOpportunityCode("1");
        pd.setRequestedStartDateInitial(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        pd.setRequestedEndDateInitial(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        pd.setTitle("Test s2s service title");
//        pd.setCfdaNumber("00.000");
//        pd.setProgramAnnouncementNumber("APP-S2S-TEST-SF424-V2");
//        pd.setProgramAnnouncementTitle("SF424 Form Family Version 2.0");
        pd.setDeadlineType("P");
        pd.setDeadlineDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
        pd.setNsfCode("J.05");
        
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
    private S2SService getS2SService(){
        return getService(S2SService.class);
    }
    @Test
    public void searchOpportunityTest(){
        List l = getS2SService().searchOpportunity("00.000", null, null);
        assertTrue(l.size()>0);
    }
    @Test
    public void parseOpportunityFormsTest(){
        List<S2sOpportunity> l = getS2SService().searchOpportunity("00.000", null, null);
        assertNotNull(l);
        assertTrue(l.size()>0);
        S2sOpportunity opp = l.get(0);
        List<S2sOppForms> oppForms = getS2SService().parseOpportunityForms(opp);
        assertNotNull(oppForms);
        assertTrue(oppForms.size()>0);
    }
    @Test
    public void validateApplicationTest(){
        assertTrue(getS2SService().validateApplication(proposalNumber));
    }
    @Test
    public void submitApplicationTest(){
        assertTrue(getS2SService().submitApplication(proposalNumber));
        assertTrue(getS2SService().refreshGrantsGov(proposalNumber));
        Map pkMap = new HashMap();
        pkMap.put("proposalNumber", proposalNumber);
        pkMap.put("submissionNumber", 1);
        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
        S2sAppSubmission sub = (S2sAppSubmission)bos.findByPrimaryKey(S2sAppSubmission.class, pkMap);
        assertNotNull(sub);
    }
//    @Test
//    public void refreshGrantsGovTest(){
//        assertTrue(getS2SService().submitApplication(proposalNumber));
//        getS2SService().refreshGrantsGov(proposalNumber);
//    }
//    @Test
//    public void getApplicationStatusDetailsTest(){
//        assertTrue(getS2SService().submitApplication(proposalNumber));
//        Map pkMap = new HashMap();
//        pkMap.put("proposalNumber", proposalNumber);
//        pkMap.put("submissionNumber", 1);
//        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
//        S2sAppSubmission sub = (S2sAppSubmission)bos.findByPrimaryKey(S2sAppSubmission.class, pkMap);
//        assertNotNull(sub);
//        LOG.info(getS2SService().getStatusDetails(sub.getGgTrackingId(), proposalNumber));
//    }
//    @Test
//    public void testPrintForm(){
////        getS2SService().printForm();
//    }
//    @Test
//    public void testGetXmlFromPureEdge(){
//        //not required
//    }

    private void configureSoap() {
        java.lang.System.setProperty("javax.xml.soap.MessageFactory",
        "org.apache.axis.soap.MessageFactoryImpl");
        
        java.lang.System.setProperty("javax.xml.soap.SOAPConnectionFactory",
        "org.apache.axis.soap.SOAPConnectionFactoryImpl");
        
    }
}
