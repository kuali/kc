/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.lookup.keyvalue;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;

import java.util.*;

/**
 * 
 * This class is a values finder for Sponsor Contacts.
 * 
 */
public class ContactsValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    
    /**
     * This method retrieves a Collection of AwardSponsorContact objects and puts them in a key-label pair in a particular pattern.
     * 
     * It puts awardContactId as the key and role description + "-" + contact organization name as value.
     *   
     * 
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        

        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select "));
        
        Long awardId = ((AwardDocument) getDocument()).getAward().getAwardId();
        
        Map keyMap = new HashMap ();
        keyMap.put("awardId", awardId);
        Collection<AwardSponsorContact> awardSponsorContacts = getKeyValuesService().findMatching(AwardSponsorContact.class, keyMap);
        
        for(AwardSponsorContact awardSponsorContact : awardSponsorContacts){                      
            if (awardSponsorContact.getContactRole() == null) {
                awardSponsorContact.refreshReferenceObject("contactRole");
            }
            String desc = awardSponsorContact.getContactRole().getRoleDescription() + " - ";
            if (StringUtils.isNotBlank(awardSponsorContact.getFullName())) {
                desc += awardSponsorContact.getFullName();
            } else {
                desc += awardSponsorContact.getContactOrganizationName();
            }
            keyValues.add(new ConcreteKeyValue(awardSponsorContact.getAwardContactId().toString(), desc));    
        }        
                
        return keyValues;
    }
    
    /**
     * 
     * This is a wrapper method for the retrieval of KeyValuesService.
     * 
     * @return
     */
    protected KeyValuesService getKeyValuesService(){
        return KcServiceLocator.getService(KeyValuesService.class);
    }
   
}
