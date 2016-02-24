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
