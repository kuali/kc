/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.CustomAttributeDataType;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.document.ResearchDocumentBase;

public interface CustomAttributeService {

    /**
     * This method returns a Map of Custom Attribute Documents where the key is the
     * customAttributeId.
     *
     * @param documentTypeCode document type to get custom attributes for
     * @param documentNumber document number for this document
     * @return a List of custom attribute documents for this document
     */
    public Map<String, CustomAttributeDocument> getDefaultCustomAttributesForDocumentType(String documentTypeCode, String documentNumber);

    /**
     * This method persists the custom attribute values for a ResearchDocumentBase.
     *
     * @param document ResearchDocumentBase to persist custom attributes for.
     */
    public void saveCustomAttributeValues(ResearchDocumentBase document);

    /**
     * 
     * This method is to get all custom attribute data types
     * @return
     */
    public Collection<CustomAttributeDataType> getCustomAttributeDataTypes();
    
    /**
     * 
     * This method is to get the custom attribute data type based on data type code
     * @param dataTypeCode
     * @return
     */
    public CustomAttributeDataType getCustomAttributeDataType(String dataTypeCode);

    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     *
     * @param bos BusinessObjectService
     */
    
    public void setCustomAttributeKeyValue(ResearchDocumentBase document, String attributeName, String networkId) throws Exception;

    public List getLookupReturns(String lookupClass) throws Exception ;
    public String getLookupReturnsForAjaxCall(String lookupClass) throws Exception ;

    /**
     * 
     * This method is to set up the key/value pair for custom attributes
     * @param bos
     */
    public void setBusinessObjectService(BusinessObjectService bos);

    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     *
     * @return BusinessObjectService
     */
    public BusinessObjectService getBusinessObjectService();
}
