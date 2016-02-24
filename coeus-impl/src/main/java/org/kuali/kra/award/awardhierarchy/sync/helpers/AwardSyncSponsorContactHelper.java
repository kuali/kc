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
package org.kuali.kra.award.awardhierarchy.sync.helpers;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * Award Hierarchy Sync Helper for Sponsor Contacts.
 */
public class AwardSyncSponsorContactHelper extends AwardSyncHelperBase {
    
    @SuppressWarnings("unchecked")
    @Override
    public void applySyncChange(Award award, AwardSyncChange change) 
        throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, 
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
