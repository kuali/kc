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
import org.kuali.coeus.sys.framework.keyvalue.PrefixValuesFinder;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.home.AwardTemplateContact;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.document.MaintenanceDocument;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AwardTemplateContactValuesFinder extends FormViewAwareUifKeyValuesFinderBase {

    /**
     * 
     * @return the list of &lt;key, value&gt; pairs of the current award template contacts id
     * and roleCode
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        MaintenanceDocument doc = (MaintenanceDocument) getDocument();
        AwardTemplate awardTemplate = (AwardTemplate) doc.getNoteTarget();
        List<AwardTemplateContact> contacts = awardTemplate.getTemplateContacts();
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        for (Iterator iter = contacts.iterator(); iter.hasNext();) {
            AwardTemplateContact contact = (AwardTemplateContact) iter.next();     
            contact.refreshReferenceObject("contactType");
            
            String key = contact.getRoleCode() + Constants.AWARD_TEMP_RECPNT_CONTACT_TYPE_CODE_ROLODEX_ID_SEPARATOR + contact.getRolodexId().toString();

            StringBuffer sb = new StringBuffer(contact.getContactType().getDescription());
            if (contact.getRolodex() != null) {
                sb.append(" : ");
                if (StringUtils.isNotBlank(contact.getRolodex().getFullName())) {
                    sb.append(contact.getRolodex().getFullName());
                } else {
                    sb.append(contact.getRolodex().getOrganization());
                }
            }
            String label = sb.toString();
            
            keyValues.add(new ConcreteKeyValue(key, label)); 
        }
        keyValues.add(0, new ConcreteKeyValue(PrefixValuesFinder.getPrefixKey(), PrefixValuesFinder.getDefaultPrefixValue()));
        return keyValues;
    }
}
