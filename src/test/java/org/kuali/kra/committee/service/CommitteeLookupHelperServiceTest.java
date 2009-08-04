/*
 * Copyright 2006-2008 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.committee.lookup.CommitteeLookupableHelperServiceImpl;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;

public class CommitteeLookupHelperServiceTest extends KraTestBase {
    private static final int NUMBER_LOOKUP_CRITERIA_FIELDS = 9;
    private static final String EDIT_URL = "../committeeCommittee.do?committeeId=100&docTypeName=CommitteeDocument&methodToCall=docHandler&command=initiate";
    CommitteeLookupableHelperServiceImpl committeeLookupableHelperServiceImpl;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        committeeLookupableHelperServiceImpl = (CommitteeLookupableHelperServiceImpl) KraServiceLocator
                .getService("committeeLookupableHelperService");
        committeeLookupableHelperServiceImpl.setBusinessObjectClass(Committee.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        committeeLookupableHelperServiceImpl = null;
        GlobalVariables.setUserSession(null);
    }

    /**
     * 
     * This method is to test getrows.  The lookup fields will be updated and make sure 
     * a few of the drop down fields are sert as dropdown_refresh 
     */
    @Test
    public void testGetRows() {

        List<Row> rows = committeeLookupableHelperServiceImpl.getRows();
        assertEquals(rows.size(), NUMBER_LOOKUP_CRITERIA_FIELDS);
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals("researchAreaCode")) {
                    assertDropDownField(field, "researchAreaCode", "org.kuali.kra.bo.ResearchArea");
                } else if (field.getPropertyName().equals("memberName")) {
                     assertDropDownField(field, "personName", "org.kuali.kra.committee.bo.CommitteeMembership");
                }
            }
        }
    }

    /**
     * 
     * This method to check the 'edit' link is correct
     */
    @Test
    public void testGetCustomActionUrl() {
        List pkNames = new ArrayList();
        pkNames.add("committeeId");
        Committee committee = new Committee();
        committee.setCommitteeId("100");
        CommitteeDocument document = new CommitteeDocument();
        document.setDocumentNumber("101");
        committee.setCommitteeDocument(document);
        List<HtmlData> actionUrls = committeeLookupableHelperServiceImpl.getCustomActionUrls(committee,pkNames);
        assertEquals(((HtmlData.AnchorHtmlData) actionUrls.get(0)).getHref(), EDIT_URL);                
    
    }

    /*
     * 
     * This method is to set up protocol for get inquiry url test
     * @return
     */
    private void assertDropDownField(Field field, String keyName, String className) {
        assertEquals(field.getFieldConversions(), keyName + ":" + field.getPropertyName());
        assertEquals(field.getLookupParameters(), field.getPropertyName() + ":" + keyName);
        assertEquals(field.getInquiryParameters(), field.getPropertyName() + ":" + keyName);
        assertEquals(field.getQuickFinderClassNameImpl(), className);

    }

}
