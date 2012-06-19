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

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.coi.CoiDisclosureStatus;
import org.kuali.kra.coi.CoiDispositionStatus;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rules.TemplateRuleTest;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.api.util.type.KualiDecimal;

public class AddManualProjectRuleTest extends KcUnitTestBase {

    @Test
    public void testAddProposalEventOK() {
        new TemplateRuleTest<AddManualProjectEvent, AddManualProjectRule>() {
            @Override
            protected void prerequisite() {
                CoiDisclProject coiDisclProject = new CoiDisclProject() ;
                
                coiDisclProject.setDisclosureEventType(CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL);
                
                coiDisclProject.setCoiProjectId("test id");
                coiDisclProject.setCoiProjectTitle("test title");
                coiDisclProject.setModuleItemKey(coiDisclProject.getCoiProjectId());
                coiDisclProject.setShortTextField1("test project role");
                coiDisclProject.setLongTextField1("test sponsor");

                coiDisclProject.setNumberField1(new KualiDecimal(1000.00));
                coiDisclProject.setDateField1(new java.sql.Date(new Date().getTime()));
                CoiDisclosure coiDisclosure = new CoiDisclosure();
                coiDisclosure.setCoiDisclProjects(new ArrayList<CoiDisclProject>());
                coiDisclProject.setCoiDisclosure(coiDisclosure);
                Date dt = DateUtils.addDays(new Date(), 10);
                coiDisclProject.setDateField2(new java.sql.Date(dt.getTime()));
                
                coiDisclProject.setSelectBox1("1");
                
                coiDisclProject.setDisclosureDispositionCode(CoiDispositionStatus.IN_PROGRESS);
                coiDisclProject.setDisclosureStatusCode(CoiDisclosureStatus.IN_PROGRESS);
                
                event = new AddManualProjectEvent(Constants.EMPTY_STRING, coiDisclProject);
                rule = new AddManualProjectRule();
                expectedReturnValue = true;
            }
        };
    }


    @Test
    public void testAddAwardEventOK() {
        new TemplateRuleTest<AddManualProjectEvent, AddManualProjectRule>() {
            @Override
            protected void prerequisite() {
                CoiDisclProject coiDisclProject = new CoiDisclProject() ;

                coiDisclProject.setDisclosureEventType(CoiDisclosureEventType.MANUAL_AWARD);

                coiDisclProject.setCoiProjectId("test id");
                coiDisclProject.setCoiProjectTitle("test title");
                coiDisclProject.setModuleItemKey(coiDisclProject.getCoiProjectId());
                coiDisclProject.setDateField1(new java.sql.Date(new Date().getTime()));
                coiDisclProject.setDisclosureDispositionCode(CoiDispositionStatus.IN_PROGRESS);
                coiDisclProject.setDisclosureStatusCode(CoiDisclosureStatus.IN_PROGRESS);                
                CoiDisclosure coiDisclosure = new CoiDisclosure();
                coiDisclosure.setCoiDisclProjects(new ArrayList<CoiDisclProject>());
                coiDisclProject.setCoiDisclosure(coiDisclosure);
                event = new AddManualProjectEvent(Constants.EMPTY_STRING, coiDisclProject);
                rule = new AddManualProjectRule();
                expectedReturnValue = true;
            }
        };
    }


    @Test
    public void testAddProtocolEventOK() {
        new TemplateRuleTest<AddManualProjectEvent, AddManualProjectRule>() {
            @Override
            protected void prerequisite() {
                CoiDisclProject coiDisclProject = new CoiDisclProject() ;
                
                coiDisclProject.setDisclosureEventType(CoiDisclosureEventType.MANUAL_IRB_PROTOCOL);

                coiDisclProject.setCoiProjectId("test id");
                coiDisclProject.setCoiProjectTitle("test title");
                coiDisclProject.setModuleItemKey(coiDisclProject.getCoiProjectId());
                coiDisclProject.setDisclosureDispositionCode(CoiDispositionStatus.IN_PROGRESS);
                coiDisclProject.setDisclosureStatusCode(CoiDisclosureStatus.IN_PROGRESS);                
                CoiDisclosure coiDisclosure = new CoiDisclosure();
                coiDisclosure.setCoiDisclProjects(new ArrayList<CoiDisclProject>());
                coiDisclProject.setCoiDisclosure(coiDisclosure);
                
                coiDisclProject.setSelectBox1("1");
                
                event = new AddManualProjectEvent(Constants.EMPTY_STRING, coiDisclProject);
                rule = new AddManualProjectRule();
                expectedReturnValue = true;
            }
        };
    }

    
    @Test
    public void testAddTravelEventOK() {
        new TemplateRuleTest<AddManualProjectEvent, AddManualProjectRule>() {
            @Override
            protected void prerequisite() {
                CoiDisclProject coiDisclProject = new CoiDisclProject() ;
                
                coiDisclProject.setDisclosureEventType(CoiDisclosureEventType.MANUAL_TRAVEL);

                coiDisclProject.setCoiProjectId("event id");
                coiDisclProject.setCoiProjectTitle("event title");
                coiDisclProject.setModuleItemKey(coiDisclProject.getCoiProjectId());
                coiDisclProject.setDisclosureDispositionCode(CoiDispositionStatus.IN_PROGRESS);
                coiDisclProject.setDisclosureStatusCode(CoiDisclosureStatus.IN_PROGRESS);                
                CoiDisclosure coiDisclosure = new CoiDisclosure();
                coiDisclosure.setCoiDisclProjects(new ArrayList<CoiDisclProject>());
                coiDisclProject.setCoiDisclosure(coiDisclosure);
                
                coiDisclProject.setShortTextField1("destination");
                coiDisclProject.setLongTextField1("entity name");
                coiDisclProject.setLongTextField2("sponsor");
                coiDisclProject.setLongTextField3("purpose");

                coiDisclProject.setNumberField1(new KualiDecimal(1000));
                coiDisclProject.setDateField1(new java.sql.Date(new Date().getTime()));
                Date dt = DateUtils.addDays(new Date(), 10);
                coiDisclProject.setDateField2(new java.sql.Date(dt.getTime()));
                
                event = new AddManualProjectEvent(Constants.EMPTY_STRING, coiDisclProject);
                rule = new AddManualProjectRule();
                expectedReturnValue = true;
            }
        };
    }

}
