package org.kuali.kra.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.common.printing.PendingReportBean;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.InstitutionalProposalFixtureFactory;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonUnit;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

import java.util.List;

/**
 *
 */
@org.junit.Ignore("This test is not meant to be run against the 2.0 release")
public class CurrentAndPendingReportDaoOjbTest extends KraTestBase {
    private PendingReportDao dao;
    private InstitutionalProposal[] proposals;
    private InstitutionalProposalPerson[] contacts;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        proposals = new InstitutionalProposal[3];
        contacts = new InstitutionalProposalPerson[3];
        proposals[0] = InstitutionalProposalFixtureFactory.createInstitutionalProposal();
        proposals[1] = InstitutionalProposalFixtureFactory.createInstitutionalProposal();
        proposals[1].setProposalNumber("00000002");
        proposals[2] = InstitutionalProposalFixtureFactory.createInstitutionalProposal();
        proposals[2].setProposalNumber("00000003");

        contacts[0] = InstitutionalProposalFixtureFactory.createInstitutionalProposalPerson(ContactRole.PI_CODE);
        setProposalPersonUnit(contacts[0]);

        contacts[1] = InstitutionalProposalFixtureFactory.createInstitutionalProposalPerson(ContactRole.PI_CODE);
        contacts[1].setPersonId("10000000004");
        setProposalPersonUnit(contacts[1]);

        contacts[2] = InstitutionalProposalFixtureFactory.createInstitutionalProposalPerson(ContactRole.PI_CODE);
        contacts[2].setPersonId("10000000002");
        setProposalPersonUnit(contacts[2]);
        
        dao = KraServiceLocator.getService(PendingReportDao.class);
    }

    private void setProposalPersonUnit(InstitutionalProposalPerson contact) {
        InstitutionalProposalPersonUnit ipUnit = new InstitutionalProposalPersonUnit();
        ipUnit.setUnitNumber("000162");
        ipUnit.setLeadUnit(true);
        contact.add(ipUnit);
    }

    @After
    public void tearDown() throws Exception {
        dao = null;
        proposals = null;
        contacts = null;
        super.tearDown();
    }

    @Test
    public void testQueryingForPendingSupport_NoData() throws Exception {
        List<PendingReportBean> data = dao.queryForPendingSupport("000000");
        Assert.assertNotNull(data);
        Assert.assertEquals(0, data.size());
    }

    @Test
    public void testQueryingForPendingSupport_OneBean() throws Exception {
        proposals[0] = addRowOfPendingSupportData(proposals[0], contacts[0]);
        List<PendingReportBean> data = dao.queryForPendingSupport(contacts[0].getPersonId());
        Assert.assertNotNull(data);
        Assert.assertEquals(1, data.size());
        PendingReportBean bean = data.get(0);
        checkPendingReportBean(proposals[0], contacts[0], bean);
    }

    @Test
    public void testQueryingForPendingSupport_TwoBeansFromThreeIPDocuments() throws Exception {
        proposals[0] = addRowOfPendingSupportData(proposals[0], contacts[0]);
        proposals[1] = addRowOfPendingSupportData(proposals[1], contacts[1]);
        proposals[2] = addRowOfPendingSupportData(proposals[2], contacts[2]);
        List<PendingReportBean> data = dao.queryForPendingSupport(contacts[0].getPersonId());
        Assert.assertNotNull(data);
        Assert.assertEquals(2, data.size());
        checkPendingReportBean(proposals[0], contacts[0], data.get(0));
        checkPendingReportBean(proposals[2], contacts[2], data.get(1));
    }

    private InstitutionalProposal addRowOfPendingSupportData(InstitutionalProposal proposal, InstitutionalProposalPerson contact) throws Exception {
        InstitutionalProposalDocument document = (InstitutionalProposalDocument) getDocumentService().getNewDocument(InstitutionalProposalDocument.class);
        document.getDocumentHeader().setDocumentDescription("A test InstitutionalProposalDocument");
        document.setInstitutionalProposal(proposal);
        document = (InstitutionalProposalDocument) getDocumentService().saveDocument(document);
        proposal = document.getInstitutionalProposal();
        proposal.setInstitutionalProposalDocument(document);
        proposal.add(contact);
        getDocumentService().saveDocument(document);
        return proposal;
    }

    private void checkPendingReportBean(InstitutionalProposal refProposal, InstitutionalProposalPerson refContact, PendingReportBean bean) {
        Assert.assertEquals(refProposal.getProposalNumber(), bean.getProposalNumber());
        Assert.assertEquals(refProposal.getRequestedStartDateInitial(), bean.getRequestedStartDateInitial());
        Assert.assertEquals(refContact.getAcademicYearEffort(), bean.getAcademicYearEffort());
        Assert.assertEquals(refContact.getCalendarYearEffort(), bean.getCalendarYearEffort());
        Assert.assertEquals(refProposal.getTitle(), bean.getProposalTitle());
        Assert.assertEquals(refProposal.getRequestedEndDateTotal(), bean.getRequestedEndDateTotal());
        Assert.assertEquals(refContact.getRoleCode(), bean.getRoleCode());
        Assert.assertEquals(refProposal.getSponsorName(), bean.getSponsorName());
        Assert.assertEquals(refContact.getSummerEffort(), bean.getSummerEffort());
        Assert.assertEquals(refProposal.getTotalDirectCostTotal(), bean.getTotalDirectCostTotal());
        Assert.assertEquals(refContact.getTotalEffort(), bean.getTotalEffort());
        Assert.assertEquals(refProposal.getTotalIndirectCostTotal(), bean.getTotalIndirectCostTotal());
        Assert.assertEquals(refProposal.getTotalIndirectCostTotal().add(refProposal.getTotalDirectCostTotal()), bean.getTotalRequestedCost());
    }
}
