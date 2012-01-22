/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.service.PersistenceService;

/**
 * 
 * This class is a values finder for Sponsor Contacts.
 * 
 */
public class ContactsValuesFinder extends KeyValuesBase {
    
    /**
     * This method retrieves a Collection of AwardSponsorContact objects and puts them in a key-label pair in a particular pattern.
     * 
     * It puts awardContactId as the key and role description + "-" + contact organization name as value.
     *   
     * 
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("all")
    public List<KeyValue> getKeyValues() {
        
//        Collection<AwardSponsorContact> awardSponsorContacts = getKeyValuesService().findAll(AwardSponsorContact.class);
        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select "));
        
        Long awardId = ((AwardForm) KNSGlobalVariables.getKualiForm()).getAwardDocument().getAward().getAwardId();
        
        Map keyMap = new HashMap ();
        keyMap.put("awardId", awardId);
        Collection<AwardSponsorContact> awardSponsorContacts = getKeyValuesService().findMatching(AwardSponsorContact.class, keyMap);

//        refreshAwardSponsorContacts(awardSponsorContacts);
        
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
        return KraServiceLocator.getService(KeyValuesService.class);
    }
    
    /**
     * 
     * This method collects all AwardSponsorContacts and then does a one-transaction refreshReferenceObject
     * on Award BO.
     * 
     * @param awardSponsorContacts
     */
    private void refreshAwardSponsorContacts(Collection<AwardSponsorContact> awardSponsorContacts) {
        List<AwardSponsorContact> persistableObjects = new ArrayList<AwardSponsorContact>();
        List<String> referenceObjectNames = new ArrayList<String>();
        
        for(AwardSponsorContact awardSponsorContact : awardSponsorContacts){
            persistableObjects.add(awardSponsorContact);
            referenceObjectNames.add("award");            
        }
        
        if(persistableObjects.size()>0 && referenceObjectNames.size()>0 ){
            getPersistenceService().retrieveReferenceObjects(persistableObjects, referenceObjectNames);
        }
    }
    
    /**
     * 
     * This is a wrapper method for the retrieval of PersistenceService.
     * 
     * @return
     */
    protected PersistenceService getPersistenceService(){
        return KraServiceLocator.getService(PersistenceService.class);
    }
   
}
