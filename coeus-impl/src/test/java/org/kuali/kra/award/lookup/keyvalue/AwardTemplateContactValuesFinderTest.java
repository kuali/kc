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
package org.kuali.kra.award.lookup.keyvalue;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.home.AwardTemplateContact;
import org.kuali.kra.award.home.ContactType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AwardTemplateContactValuesFinderTest  {
    AwardTemplateContactValuesFinder awardTempContactValuesFinder;
    List<KeyValue> contactKeys;
    AwardTemplate template;
    List<AwardTemplateContact> contactList;

    @Before
    public void setUp() throws Exception {
        //super.setUp();
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
        //super.tearDown();
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
