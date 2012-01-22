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
package org.kuali.kra.committee.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondence;
import org.kuali.kra.committee.service.impl.CommitteeBatchCorrespondenceServiceImpl;
import org.kuali.kra.committee.test.CommitteeTestHelper;
import org.kuali.kra.common.notification.service.KcNotificationService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDao;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplateService;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.service.impl.mocks.MockKcPersonService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.kra.util.DateUtils;

/**
 * 
 * This class is for correspondence service test
 * 'extends KcUnitTestBase' is needed for instantiating ProtocolDOcument
 */
public class CommitteeBatchCorrespondenceServiceTest extends KcUnitTestBase {

    private static final String PROTOCOL_NUMBER = "1";
    private static final int SEQUENCE_NUMBER = 0;
    
    private Mockery context = new JUnit4Mockery();
    private CommitteeBatchCorrespondenceServiceImpl committeeBatchCorrespondenceServiceImpl;
    
    @Before
    public void setUp() {
        committeeBatchCorrespondenceServiceImpl = new CommitteeBatchCorrespondenceServiceImpl();
        committeeBatchCorrespondenceServiceImpl.setKcNotificationService(getMockKcNotificationService());
    }

    /**
     * This method tests the creation of batch correspondence
     * @throws Exception
     */
    @Test
    public void testGenerateBatchCorrespondenceForRenewalReminders() throws Exception {
        String batchCorrespondenceTypeCode = Constants.PROTOCOL_RENEWAL_REMINDERS;
        Committee committee = 
            ((List <Committee>)getBusinessObjectService().findAll(Committee.class)).get(0);
        final String committeeId = committee.getCommitteeId();
            final Date startDate = Date.valueOf("2010-06-01");
        final Date endDate = Date.valueOf("2010-06-15");

        committeeBatchCorrespondenceServiceImpl.setBusinessObjectService(new CommitteeTestHelper.MockBusinessObjectService());
        committeeBatchCorrespondenceServiceImpl.setKcPersonService(new MockKcPersonService());
        
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
        
      CommitteeBatchCorrespondence committeeBatchCorrespondence = committeeBatchCorrespondenceServiceImpl.generateBatchCorrespondence(batchCorrespondenceTypeCode, committeeId, startDate, endDate);

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
            ((List <Committee>)getBusinessObjectService().findAll(Committee.class)).get(0);
        final String committeeId = committee.getCommitteeId();
        final Date startDate = Date.valueOf("2010-06-01");
        final Date endDate = Date.valueOf("2010-06-15");

        committeeBatchCorrespondenceServiceImpl.setBusinessObjectService(new CommitteeTestHelper.MockBusinessObjectService());
        committeeBatchCorrespondenceServiceImpl.setKcPersonService(new MockKcPersonService());
        
        final ProtocolDao protocolDao = context.mock(ProtocolDao.class);
        final List<Protocol> protocols = initProtocols();
        context.checking(new Expectations() {{
            oneOf(protocolDao).getIrbNotifiedProtocols(committeeId, startDate, endDate); will(returnValue(protocols));
        }});
        committeeBatchCorrespondenceServiceImpl.setProtocolDao(protocolDao);
        
        final ProtocolCorrespondenceTemplateService protocolCorrespondenceTemplateService = context.mock(ProtocolCorrespondenceTemplateService.class);
        context.checking(new Expectations() {{
            oneOf(protocolCorrespondenceTemplateService).getProtocolCorrespondenceTemplate(with(any(String.class)), 
                    with(any(String.class))); will(returnValue(new ProtocolCorrespondenceTemplate()));
        }});
        committeeBatchCorrespondenceServiceImpl.setProtocolCorrespondenceTemplateService(protocolCorrespondenceTemplateService);
        
      CommitteeBatchCorrespondence committeeBatchCorrespondence = committeeBatchCorrespondenceServiceImpl.generateBatchCorrespondence(batchCorrespondenceTypeCode, committeeId, startDate, endDate);

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
        protocol1.setProtocolActions(new ArrayList<ProtocolAction>());
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
        protocol2.setProtocolActions(new ArrayList<ProtocolAction>());
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
    
}