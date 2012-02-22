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
package org.kuali.kra.coi.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.CoiDiscDetail;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.coi.CoiDisclosureHistory;
import org.kuali.kra.coi.CoiDisclosureStatus;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.kra.service.impl.adapters.BusinessObjectServiceAdapter;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;

public class CoiDisclosureActionServiceTest extends KcUnitTestBase {

    private static final String PERSON_ID = "10000000001";
    private static final String ROLE_ID = "COIR";
    private static final String  ENTITY_NUMBER = "1";
    private static final String  ENTITY_NUMBER_2 = "2";
    private static final String  ENTITY_NAME_1 = "Entity 1";
    private static final String  ENTITY_NAME_2 = "Entity 2";
    Mockery context = new JUnit4Mockery();
    private CoiDisclosure coiDisclosure1 ;
    private CoiDisclosureHistory coiDisclosureHistory;
    @Test
   public void testApproveDisclosures() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        CoiDisclosureActionServiceImpl coiDisclosureActionService = new CoiDisclosureActionServiceImpl();
        coiDisclosureActionService = new CoiDisclosureActionServiceImpl();
        PersonFinIntDisclosure personFinIntDisclosure = createPersonFinIntDisclosure(PERSON_ID, ENTITY_NUMBER,ENTITY_NAME_1);

        // this will be new FE, and it will be added to coi FE
        PersonFinIntDisclosure personFinIntDisclosure1 = createPersonFinIntDisclosure(PERSON_ID, ENTITY_NUMBER_2,ENTITY_NAME_2);
//       final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
       coiDisclosure1 = getCoiDisclosure(1);
       coiDisclosure1.setCoiDisclosureId(1L);
       coiDisclosure1.setCurrentDisclosure(true);
       coiDisclosure1.getCoiDiscDetails().add(createNewCoiDiscDetail(CoiDisclosure.PROPOSAL_DISCL_MODULE_CODE, personFinIntDisclosure, CoiDisclosureEventType.DEVELOPMENT_PROPOSAL,1));
       CoiDisclosure coiDisclosure2 = getCoiDisclosure(2);
       coiDisclosure2.getCoiDiscDetails().add(createNewCoiDiscDetail(CoiDisclosure.MANUAL_DISCL_MODULE_CODE, personFinIntDisclosure1, CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL,2));
       coiDisclosure2.setCoiDisclosureId(2L);
       final List<CoiDisclosure> coiDisclosures = new ArrayList<CoiDisclosure>();
       final List<KraPersistableBusinessObjectBase> disclosures = new ArrayList<KraPersistableBusinessObjectBase>();
//       coiDisclosures.add(coiDisclosure2);
       coiDisclosures.add(coiDisclosure1);
       disclosures.add(coiDisclosure2);
       disclosures.add(coiDisclosure1);
       disclosures.add(createDisclosureHistory(coiDisclosure2));
//       context.checking(new Expectations() {
//           {
//               Map<String, Object> fieldValues = new HashMap<String, Object>();
//               fieldValues.put("coiDisclosureNumber", "1");
//               fieldValues.put("currentDisclosure", "Y");
//
//               one(businessObjectService).findMatching(CoiDisclosure.class, fieldValues);
//               will(returnValue(coiDisclosures));
//
//
//           }
//       });
       // this save is not working, so change it to use mockbusinessobjectservice
//       context.checking(new Expectations() {{
//           allowing(businessObjectService).save(disclosures);
//       }});
       

//       coiDisclosureService.setBusinessObjectService(businessObjectService);
       coiDisclosureActionService.setBusinessObjectService(new MockBusinessObjectService());
       coiDisclosureActionService.approveDisclosure(coiDisclosure2,CoiDisclosureStatus.APPROVED);
       Assert.assertFalse(coiDisclosure1.isCurrentDisclosure());
       Assert.assertTrue(coiDisclosure2.isCurrentDisclosure());
       Assert.assertEquals(coiDisclosure2.getDisclosureStatusCode(),CoiDisclosureStatus.APPROVED);
       // previous master disc's detail also copied over
       Assert.assertEquals(coiDisclosure2.getCoiDiscDetails().size(),2);
       // history record created
       Assert.assertEquals(coiDisclosureHistory.getDisclosureStatus(),CoiDisclosureStatus.APPROVED);
   }

    private CoiDisclosureHistory createDisclosureHistory(CoiDisclosure coiDisclosure) {
        CoiDisclosureHistory coiDisclosureHistory = new CoiDisclosureHistory();
        coiDisclosureHistory.setCoiDisclosureId(coiDisclosure.getCoiDisclosureId());
        coiDisclosureHistory.setCoiDisclosureNumber(coiDisclosure.getCoiDisclosureNumber());
        coiDisclosureHistory.setSequenceNumber(coiDisclosure.getSequenceNumber());
        coiDisclosureHistory.setDisclosureDispositionStatus(coiDisclosure.getDisclosureDispositionCode());
        coiDisclosureHistory.setDisclosureStatus(CoiDisclosureStatus.APPROVED);
        return coiDisclosureHistory;

    }

    // this save is not working, so change it to use mockbusinessobjectservice
    @SuppressWarnings("unchecked")
    public class MockBusinessObjectService extends BusinessObjectServiceAdapter {
        
        @Override
        public Collection findMatching(Class clazz, Map fieldValues) {
//            Collection results;
            final List<CoiDisclosure> coiDisclosures = new ArrayList<CoiDisclosure>();
            coiDisclosures.add(coiDisclosure1);
            return coiDisclosures;
            
        }
        
        @Override
        public List<? extends PersistableBusinessObject> save(List<? extends PersistableBusinessObject> businessObjects) {
           // do nothing            
            coiDisclosureHistory = (CoiDisclosureHistory)businessObjects.get(2);
            return businessObjects;
        }
      

    }
    private CoiDisclosure getCoiDisclosure(Integer sequenceNumber) {
        // anonymous inner class caused deepcopy notserializableexception
//        CoiDisclosure coiDisclosure = new CoiDisclosure() {
//            public void initCoiDisclosureNumber()  {
//                this.setCoiDisclosureNumber("1");
//            }
//            public DisclosurePerson getDisclosureReporter() {
//                DisclosurePerson reporter = new DisclosurePerson();
//                reporter.setPersonId(PERSON_ID);
//                reporter.setPersonRoleId(ROLE_ID);
//                reporter.setCoiDisclosureId(1L);
//                return reporter;
//                
//            }
//        };
      CoiDisclosure coiDisclosure = new CoiDisclosure();
        coiDisclosure.setCoiDisclosureNumber("1");
        coiDisclosure.setSequenceNumber(sequenceNumber);
        coiDisclosure.setCoiDiscDetails(new ArrayList<CoiDiscDetail>());
//        coiDisclosure.setUpdateTimestamp(KraServiceLocator.getService(DateTimeService.class).getCurrentTimestamp());
        return coiDisclosure;
    }

    private CoiDiscDetail createNewCoiDiscDetail(String moduleCode, PersonFinIntDisclosure personFinIntDisclosure, String projectType, Integer sequenceNumber) {
        CoiDiscDetail disclosureDetail = new CoiDiscDetail(personFinIntDisclosure);
        disclosureDetail.setModuleItemKey(moduleCode+"-"+sequenceNumber);
        disclosureDetail.setProjectIdFk(moduleCode+"-"+sequenceNumber);
        disclosureDetail.setModuleCode(moduleCode);
        disclosureDetail.setCoiDisclosureNumber("1");
        disclosureDetail.setSequenceNumber(sequenceNumber);
        disclosureDetail.setProjectType(projectType);
        disclosureDetail.setDescription("Sample Description"); // this is from coeus.
        return disclosureDetail;
        
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

}
