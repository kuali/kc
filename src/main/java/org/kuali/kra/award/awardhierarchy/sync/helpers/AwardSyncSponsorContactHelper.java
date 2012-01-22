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
package org.kuali.kra.award.awardhierarchy.sync.helpers;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

/**
 * Award Hierarchy Sync Helper for Sponsor Contacts.
 */
public class AwardSyncSponsorContactHelper extends AwardSyncHelperBase {
    
    @SuppressWarnings("unchecked")
    @Override
    public void applySyncChange(Award award, AwardSyncChange change) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, InvocationTargetException, 
        ClassNotFoundException, NoSuchMethodException, InstantiationException {
        Collection sponsorContacts = award.getSponsorContacts();
        AwardSponsorContact sponsorContact = (AwardSponsorContact) getAwardSyncUtilityService().findMatchingBo(sponsorContacts, change.getXmlExport().getKeys());
        if (StringUtils.equals(change.getSyncType(), AwardSyncType.ADD_SYNC.getSyncValue())) {
            if (sponsorContact != null) {
                this.setValuesOnSyncable(sponsorContact, change.getXmlExport().getValues(), change);
            } else {
                sponsorContact = new AwardSponsorContact();
                setValuesOnSyncable(sponsorContact, change.getXmlExport().getKeys(), change);
                setValuesOnSyncable(sponsorContact, change.getXmlExport().getValues(), change);
                award.add(sponsorContact);
            }
        } else {
            if (sponsorContact != null) {
                sponsorContacts.remove(sponsorContact);
            }
        }
    }
    
    @Override
    protected String getObjectDesc(PersistableBusinessObject syncableObject, String attrName) {
        return "Sponsor Contact";
    }
    
    @Override
    protected String getDataDesc(PersistableBusinessObject syncableObject, String attrName) {
        AwardSponsorContact sponsorContact = (AwardSponsorContact) syncableObject;
        String retval = sponsorContact.getContactType().getDescription() + " : ";
        if (sponsorContact.getRolodex().getFullName() != null) {
            retval += sponsorContact.getFullName();
        } else {
            retval += sponsorContact.getContactOrganizationName();
        }
        return retval;
    }     
}
