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
package org.kuali.kra.award.paymentreports.awardreports;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.impl.validation.ErrorReporterImpl;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

import java.util.ArrayList;
import java.util.List;

public class AwardReportTermRecipientRuleImplTest {

    AwardReportTermRecipientRuleImpl awardReportTermRecipientRuleImpl;
    List<AwardReportTermRecipient> awardReportTermRecipientItems;
    
    @Before
    public void setUp() throws Exception {
        awardReportTermRecipientRuleImpl = new AwardReportTermRecipientRuleImpl();
        awardReportTermRecipientRuleImpl.setErrorReporter(new ErrorReporterImpl());
        awardReportTermRecipientItems = new ArrayList<AwardReportTermRecipient>();
        GlobalVariables.setMessageMap(new MessageMap());
    }

    @After
    public void tearDown() throws Exception {
        awardReportTermRecipientRuleImpl = null;
        awardReportTermRecipientItems = null;
    }
    
    /**
     * 
     * This method should find an error condition.
     */
    @Test
    public final void testIsUnique1(){
        AwardReportTermRecipient awardReportTermRecipientItem = new AwardReportTermRecipient();
        awardReportTermRecipientItem.setContactTypeCode("5");
        awardReportTermRecipientItem.setRolodexId(5);        
        awardReportTermRecipientItems.add(awardReportTermRecipientItem);        
        Assert.assertFalse(awardReportTermRecipientRuleImpl.isUnique(awardReportTermRecipientItems, awardReportTermRecipientItem));
    }
    
    /**
     * 
     * This method should not find an error condition.
     */
    @Test
    public final void testIsUnique2(){
        AwardReportTermRecipient awardReportTermRecipientItem = new AwardReportTermRecipient();
        awardReportTermRecipientItem.setContactTypeCode("5");
        awardReportTermRecipientItem.setRolodexId(5);        
        awardReportTermRecipientItems.add(awardReportTermRecipientItem);        
        awardReportTermRecipientItem = new AwardReportTermRecipient();
        awardReportTermRecipientItem.setContactTypeCode("3");
        awardReportTermRecipientItem.setRolodexId(3);
        Assert.assertTrue(awardReportTermRecipientRuleImpl.isUnique(awardReportTermRecipientItems, awardReportTermRecipientItem));
    }
    
    @Test
    public final void testAreRequiredFieldsComplete(){
        AwardReportTermRecipient awardReportTermRecipientItem = new AwardReportTermRecipient();        
        Assert.assertFalse(awardReportTermRecipientRuleImpl.areRequiredFieldsComplete(awardReportTermRecipientItem));
        awardReportTermRecipientItem.setContactId(new Long(1));
        awardReportTermRecipientItem.setRolodexId(new Integer(101));
        Assert.assertTrue(awardReportTermRecipientRuleImpl.areRequiredFieldsComplete(awardReportTermRecipientItem));
        awardReportTermRecipientItem.setRolodexId(5);
        Assert.assertTrue(awardReportTermRecipientRuleImpl.areRequiredFieldsComplete(awardReportTermRecipientItem));
    }
    
    @Test
    public final void testValidateContactAndOrganizationAreBothNotSelected(){
        AwardReportTermRecipient awardReportTermRecipientItem = new AwardReportTermRecipient();
        awardReportTermRecipientItem.setRolodexId(5);
        awardReportTermRecipientItem.setContactId(new Long(1));
        Assert.assertFalse(awardReportTermRecipientRuleImpl.validateContactAndOrganizationAreBothNotSelected(awardReportTermRecipientItem));
        awardReportTermRecipientItem.setRolodexId(null);
        awardReportTermRecipientItem.setContactId(new Long(1));
        Assert.assertTrue(awardReportTermRecipientRuleImpl.validateContactAndOrganizationAreBothNotSelected(awardReportTermRecipientItem));
        awardReportTermRecipientItem.setRolodexId(5);
        awardReportTermRecipientItem.setContactId(null);
        Assert.assertTrue(awardReportTermRecipientRuleImpl.validateContactAndOrganizationAreBothNotSelected(awardReportTermRecipientItem));
        
    }

}
