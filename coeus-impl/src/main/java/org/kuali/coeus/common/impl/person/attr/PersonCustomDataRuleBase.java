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
package org.kuali.coeus.common.impl.person.attr;

import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.PropertyConstants;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PersonCustomDataRuleBase extends KcTransactionalDocumentRuleBase {
    
    protected Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        Map<String, CustomAttributeDocument> customAttributeDocuments = new HashMap<String, CustomAttributeDocument>();
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(PropertyConstants.DOCUMENT.TYPE_NAME.toString(), "PERS");
        Collection<CustomAttributeDocument> customAttributeDocumentList = getBusinessObjectService().findMatching(CustomAttributeDocument.class, fieldValues);
        for (CustomAttributeDocument customAttributeDocument : customAttributeDocumentList) {
            if (customAttributeDocument.isActive()) {
                customAttributeDocuments.put(customAttributeDocument.getId().toString(), customAttributeDocument);
            }
        }
        
        return customAttributeDocuments;
    }

}
