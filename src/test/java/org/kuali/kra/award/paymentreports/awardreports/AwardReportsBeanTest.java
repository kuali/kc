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
package org.kuali.kra.award.paymentreports.awardreports;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AwardReportsBeanTest {

    public static final Integer MOCK_ROLODEX_ID_FOR_CONTACT = 20083;
    AwardReportTermRecipient awardReportTermRecipient1;
    AwardReportsBean awardReportsBean;
    
    @Before
    public void setUp() throws Exception {
        awardReportTermRecipient1 = new AwardReportTermRecipient();
        awardReportsBean = new AwardReportsBean();
    }

    @After
    public void tearDown() throws Exception {
        awardReportTermRecipient1 = null;
        awardReportsBean = null;
    }

    @Test
    public final void testPopulateContactTypeAndRolodex() {
        awardReportTermRecipient1.setContactId(new Long(1));
        awardReportsBean.populateContactTypeAndRolodex(awardReportTermRecipient1);
        Assert.assertEquals("6", awardReportTermRecipient1.getContactTypeCode());
        Assert.assertEquals(MOCK_ROLODEX_ID_FOR_CONTACT, awardReportTermRecipient1.getRolodexId());
        awardReportTermRecipient1.setContactId(new Long(2));
        awardReportsBean.populateContactTypeAndRolodex(awardReportTermRecipient1);
        Assert.assertEquals("5", awardReportTermRecipient1.getContactTypeCode());
        awardReportTermRecipient1.setContactId(new Long(3));
        awardReportsBean.populateContactTypeAndRolodex(awardReportTermRecipient1);
        Assert.assertEquals("4", awardReportTermRecipient1.getContactTypeCode());
        awardReportTermRecipient1.setContactId(new Long(4));
        awardReportsBean.populateContactTypeAndRolodex(awardReportTermRecipient1);
        Assert.assertEquals("3", awardReportTermRecipient1.getContactTypeCode());
        awardReportTermRecipient1.setContactId(new Long(5));
        awardReportsBean.populateContactTypeAndRolodex(awardReportTermRecipient1);
        Assert.assertEquals("2", awardReportTermRecipient1.getContactTypeCode());
        awardReportTermRecipient1.setContactId(new Long(6));
        awardReportsBean.populateContactTypeAndRolodex(awardReportTermRecipient1);
        Assert.assertEquals("9", awardReportTermRecipient1.getContactTypeCode());
    }

}
