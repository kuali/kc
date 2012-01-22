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

import org.drools.core.util.StringUtils;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.home.AwardTemplateContact;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.lookup.keyvalue.ExtendedPersistableBusinessObjectValuesFinder;
import org.kuali.kra.lookup.keyvalue.PrefixValuesFinder;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;

public class AwardTemplateContactValuesFinder extends ExtendedPersistableBusinessObjectValuesFinder {

    /**
     * 
     * @return the list of &lt;key, value&gt; pairs of the current award template contacts id
     * and roleCode
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        MaintenanceDocument doc = getDocument();
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
                if (!StringUtils.isEmpty(contact.getRolodex().getFullName())) {
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
    
    /**
     * Get the Award Document for the current session.  The
     * document is within the current form.
     * 
     * @return the current document or null if not found
     */
    private MaintenanceDocument getDocument() {
        MaintenanceDocument doc = null;
        KualiMaintenanceForm form = (KualiMaintenanceForm) KNSGlobalVariables.getKualiForm();
        if (form != null) {
            doc = (MaintenanceDocument) form.getDocument();
        }
        return doc;
    }
}
