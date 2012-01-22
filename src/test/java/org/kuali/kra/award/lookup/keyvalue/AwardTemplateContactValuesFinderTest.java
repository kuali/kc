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
package org.kuali.kra.award.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.home.AwardTemplateContact;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

public class AwardTemplateContactValuesFinderTest extends KcUnitTestBase {
    AwardTemplateContactValuesFinder awardTempContactValuesFinder;
    List<KeyValue> contactKeys;
    AwardTemplate template;
    List<AwardTemplateContact> contactList;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        template = new AwardTemplate();
        contactList = new ArrayList<AwardTemplateContact>();
        this.addContacts("ContactType1", 1);
        this.addContacts("ContactType2", 2);
        template.setTemplateContacts(contactList);
        awardTempContactValuesFinder = this.getAwardTemplateContactValuesFinder();
        contactKeys = new ArrayList<KeyValue>();
        contactKeys = awardTempContactValuesFinder.getKeyValues();
    }
    
    public void addContacts(String contactTypeCode, int rolodexId){
        AwardTemplateContact aContact = new AwardTemplateContact();
        ContactType aContactType = new ContactType();
        aContactType.setContactTypeCode(contactTypeCode);
        aContactType.setDescription(contactTypeCode + " Description");
        aContact.setContactType(aContactType);
        aContact.setRoleCode(contactTypeCode);
        aContact.setRolodexId(rolodexId);
        contactList.add(aContact);
    }
    
    private AwardTemplateContactValuesFinder getAwardTemplateContactValuesFinder() {

        return new AwardTemplateContactValuesFinder() { 
            public List<KeyValue> getKeyValues() {
                List<AwardTemplateContact> contacts = template.getTemplateContacts();
                List<KeyValue> keyValues = new ArrayList<KeyValue>();
                for (Iterator iter = contacts.iterator(); iter.hasNext();) {
                    AwardTemplateContact contact = (AwardTemplateContact) iter.next(); 
                    keyValues.add(new ConcreteKeyValue(contact.getRoleCode() + Constants.AWARD_TEMP_RECPNT_CONTACT_TYPE_CODE_ROLODEX_ID_SEPARATOR + contact.getRolodexId().toString(), 
                            contact.getContactType().getDescription() + Constants.AWARD_TEMP_RECPNT_CONTACT_TYPE_CODE_ROLODEX_ID_SEPARATOR + contact.getRolodexId().toString()));       
                }
                keyValues.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
                return keyValues;
            }
        };
    }


    @After
    public void tearDown() throws Exception {
        super.tearDown();
        awardTempContactValuesFinder = null;
        template = null;
        contactKeys = null;
        contactList = null;
    }

    @Test
    public final void testGetKeyValues() {
        
        int count = template.getTemplateContacts().size();
        Assert.assertEquals(count + 1, contactKeys.size());   // added "select" to the list
        
        for(KeyValue KeyValue:contactKeys){
            Assert.assertNotNull(KeyValue.getKey());
            Assert.assertNotNull(KeyValue.getValue());
            String keyString = KeyValue.getKey().toString();
            String labelString = KeyValue.getValue();
            
            if(!StringUtils.equals(labelString, "select")){ 
                if(keyString.contains("1")){
                    StringUtils.equals(keyString, "ContactType1***1");
                    StringUtils.equals(labelString, "ContactType1 Description***1");
                } else {
                    StringUtils.equals(keyString, "ContactType2***2");
                    StringUtils.equals(labelString, "ContactType2 Description***1");
                }
            }
        }
        
    }
}