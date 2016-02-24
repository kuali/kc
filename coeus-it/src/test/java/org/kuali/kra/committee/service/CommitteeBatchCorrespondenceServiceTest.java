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
package org.kuali.kra.committee.service;

import org.apache.commons.lang3.time.DateUtils;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondence;
import org.kuali.kra.committee.service.impl.CommitteeBatchCorrespondenceServiceImpl;
import org.kuali.kra.committee.test.CommitteeTestHelper;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplateService;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kns.service.KNSServiceLocator;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
/**
 * 
 * This class is for correspondence service test
 * 'extends KcIntegrationTestBase' is needed for instantiating ProtocolDOcument
 */
public class CommitteeBatchCorrespondenceServiceTest extends KcIntegrationTestBase {

    private static final String PROTOCOL_NUMBER = "1";
    private static final int SEQUENCE_NUMBER = 0;
    
    private Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};
    private CommitteeBatchCorrespondenceServiceImpl committeeBatchCorrespondenceServiceImpl;
    
    @Before
    public void setUp() {
        committeeBatchCorrespondenceServiceImpl = new CommitteeBatchCorrespondenceServiceImpl();
        committeeBatchCorrespondenceServiceImpl.setKcNotificationService(getMockKcNotificationService());
        committeeBatchCorrespondenceServiceImpl.setDateTimeService(getMockDateTimeService());
    }

    /**
     * This method tests the creation of batch correspondence
     * @throws Exception
     */
    @Test
    public void testGenerateBatchCorrespondenceForRenewalReminders() throws Exception {
        String batchCorrespondenceTypeCode = Constants.PROTOCOL_RENEWAL_REMINDERS;
        Committee committee = 
            ((List <Committee>)KNSServiceLocator.getBusinessObjectService().findAll(Committee.class)).get(0);
        final String committeeId = committee.getCommitteeId();
            final Date startDate = Date.valueOf("2010-06-01");
        final Date endDate = Date.valueOf("2010-06-15");

        committeeBatchCorrespondenceServiceImpl.setBusinessObjectService(new CommitteeTestHelper.MockBusinessObjectService());
        
        final ProtocolDao protocolDao = context.mock(ProtocolDao.class);
        final List<Protocol> protocols = initProtocols();
        context.checking(new Expectations() {{
            oneOf(protocolDao).getExpiringProtocols(committeeId, startDate, endDate); will(returnValue(protocols));
        }});
        committeeBatchCorrespondenceServiceImpl.setProtocolDao(protocolDao);
        
        final ProtocolCorrespondenceTemplateService protocolCorrespondenceTemplateService = context.mock(ProtocolCorrespondenceTemplateService.class);
        context.checking(new Expectations() {{
            oneOf(protocolCorrespondenceTemplateService).getProtocolCorrespondenceTemplate(with(any(String.class)), 
                    with(any(String.class))); will(returnValue(new ProtocolCorrespondenceTemplate()));
        }});
        committeeBatchCorrespondenceServiceImpl.setProtocolCorrespondenceTemplateService(protocolCorrespondenceTemplateService);
        
      CommitteeBatchCorrespondence committeeBatchCorrespondence = (CommitteeBatchCorrespondence) committeeBatchCorrespondenceServiceImpl.generateBatchCorrespondence(batchCorrespondenceTypeCode, committeeId, startDate, endDate);

      // assert CommitteeBatchCorrespondence
      assertEquals(committeeId, committeeBatchCorrespondence.getCommitteeId());
      assertEquals(batchCorrespondenceTypeCode, committeeBatchCorrespondence.getBatchCorrespondenceTypeCode());
      assertEquals(startDate, committeeBatchCorrespondence.getTimeWindowStart());
      assertEquals(endDate, committeeBatchCorrespondence.getTimeWindowEnd());
      
      // assert CommitteeBatchCorrespondenceDetail
      assertEquals(1, committeeBatchCorrespondence.getCommitteeBatchCorrespondenceDetails().size());
      assertEquals(committeeBatchCorrespondence.getCommitteeBatchCorrespondenceId(), committeeBatchCorrespondence.getCommitteeBatchCorrespondenceDetails().get(0).getCommitteeBatchCorrespondenceId());
      assertEquals("Renewal Reminder Letter #1", committeeBatchCorrespondence.getCommitteeBatchCorrespondenceDetails().get(0).getProtocolAction().getComments());
    }

    /**
     * This method tests the creation of batch correspondence
     * @throws Exception
     */
   @Test
    public void testGenerateBatchCorrespondenceForIrbNotifications() throws Exception {
        String batchCorrespondenceTypeCode = Constants.REMINDER_TO_IRB_NOTIFICATIONS;
        Committee committee = 
            ((List <Committee>) KNSServiceLocator.getBusinessObjectService().findAll(Committee.class)).get(0);
        final String committeeId = committee.getCommitteeId();
        final Date startDate = Date.valueOf("2010-06-01");
        final Date endDate = Date.valueOf("2010-06-15");

        committeeBatchCorrespondenceServiceImpl.setBusinessObjectService(new CommitteeTestHelper.MockBusinessObjectService());
        
        final ProtocolDao protocolDao = context.mock(ProtocolDao.class);
        final List<Protocol> protocols = initProtocols();
        context.checking(new Expectations() {{
            oneOf(protocolDao).getNotifiedProtocols(committeeId, startDate, endDate); will(returnValue(protocols));
        }});
        committeeBatchCorrespondenceServiceImpl.setProtocolDao(protocolDao);
        
        final ProtocolCorrespondenceTemplateService protocolCorrespondenceTemplateService = context.mock(ProtocolCorrespondenceTemplateService.class);
        context.checking(new Expectations() {{
            oneOf(protocolCorrespondenceTemplateService).getProtocolCorrespondenceTemplate(with(any(String.class)), 
                    with(any(String.class))); will(returnValue(new ProtocolCorrespondenceTemplate()));
        }});
        committeeBatchCorrespondenceServiceImpl.setProtocolCorrespondenceTemplateService(protocolCorrespondenceTemplateService);
        
      CommitteeBatchCorrespondence committeeBatchCorrespondence = (CommitteeBatchCorrespondence) committeeBatchCorrespondenceServiceImpl.generateBatchCorrespondence(batchCorrespondenceTypeCode, committeeId, startDate, endDate);

      // assert CommitteeBatchCorrespondence
      assertEquals(committeeId, committeeBatchCorrespondence.getCommitteeId());
      assertEquals(batchCorrespondenceTypeCode, committeeBatchCorrespondence.getBatchCorrespondenceTypeCode());
      assertEquals(startDate, committeeBatchCorrespondence.getTimeWindowStart());
      assertEquals(endDate, committeeBatchCorrespondence.getTimeWindowEnd());
      
      // assert CommitteeBatchCorrespondenceDetail
      assertEquals(1, committeeBatchCorrespondence.getCommitteeBatchCorrespondenceDetails().size());
      assertEquals(committeeBatchCorrespondence.getCommitteeBatchCorrespondenceId(), committeeBatchCorrespondence.getCommitteeBatchCorrespondenceDetails().get(0).getCommitteeBatchCorrespondenceId());
      assertEquals("Reminder to IRB Notification #1", committeeBatchCorrespondence.getCommitteeBatchCorrespondenceDetails().get(0).getProtocolAction().getComments());
    }

    private List<Protocol> initProtocols() {
        List<Protocol> protocols = new ArrayList<Protocol>();
        Protocol protocol1 = new Protocol() {  //ProtocolTestUtil.getProtocol(this.context)
            private static final long serialVersionUID = 1L;

            @Override
            public void refreshReferenceObject(String referenceObjectName) {}
            
            @Override
            public Integer getNextValue(String key) {
                return 2;
            }
            
            @Override
            public ProtocolPerson getPrincipalInvestigator() {
                ProtocolPerson pPerson = new ProtocolPerson();
                pPerson.setFullName("PI Test");
                return pPerson;
            }
            
            public void refreshNonUpdateableReferences() {
            }
        };
        protocol1.setProtocolNumber(PROTOCOL_NUMBER);
        protocol1.setSequenceNumber(SEQUENCE_NUMBER);
        protocol1.setExpirationDate(new Date(DateUtils.addDays(new Date(System.currentTimeMillis()), 17).getTime()));
        protocol1.setProtocolActions(new ArrayList<ProtocolActionBase>());
        ProtocolAction protocolAction1 = new ProtocolAction();
        protocolAction1.setActionDate(new Timestamp(DateUtils.addDays(new Date(System.currentTimeMillis()), -2).getTime()));
        protocolAction1.setUpdateTimestamp(protocolAction1.getActionDate());
        protocol1.getProtocolActions().add(protocolAction1);
        protocol1.setProtocolDocument(getProtocolDocument("1"));
        protocols.add(protocol1);

        Protocol protocol2 = new Protocol() {  //ProtocolTestUtil.getProtocol(this.context)
            private static final long serialVersionUID = 1L;

            @Override
            public void refreshReferenceObject(String referenceObjectName) {}
            
            @Override
            public Integer getNextValue(String key) {
                return 2;
            }
            
            @Override
            public ProtocolPerson getPrincipalInvestigator() {
                ProtocolPerson pPerson = new ProtocolPerson();
                pPerson.setFullName("PI Test");
                return pPerson;
            }
            
            public void refreshNonUpdateableReferences() {
            }
        };
        protocol2.setProtocolNumber(PROTOCOL_NUMBER);
        protocol2.setSequenceNumber(SEQUENCE_NUMBER);
        protocol2.setExpirationDate(new Date(DateUtils.addDays(new Date(System.currentTimeMillis()), 130).getTime()));
        protocol2.setProtocolActions(new ArrayList<ProtocolActionBase>());
        ProtocolAction protocolAction2 = new ProtocolAction();
        protocolAction2.setActionDate(new Timestamp(DateUtils.addDays(new Date(System.currentTimeMillis()), -16).getTime()));
        protocolAction2.setUpdateTimestamp(protocolAction2.getActionDate());
        protocol2.getProtocolActions().add(protocolAction2);
        protocol2.setProtocolDocument(getProtocolDocument("2"));
        protocols.add(protocol2);

        return protocols;
    }

    private ProtocolDocument getProtocolDocument(String documentNumber) {
        ProtocolDocument document = new ProtocolDocument();
        document.setDocumentNumber(documentNumber);
        return document;
    }
    
    private KcNotificationService getMockKcNotificationService() {
        final KcNotificationService service = context.mock(KcNotificationService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    private DateTimeService getMockDateTimeService() {
        final DateTimeService service = context.mock(DateTimeService.class);
        
        context.checking(new Expectations() {{
            ignoring(service);
        }});
        
        return service;
    }
    
}
