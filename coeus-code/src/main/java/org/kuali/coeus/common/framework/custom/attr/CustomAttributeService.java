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
package org.kuali.coeus.common.framework.custom.attr;

import org.kuali.coeus.common.framework.custom.DocumentCustomData;

import java.util.List;
import java.util.Map;

public interface CustomAttributeService {

    /**
     * This method returns a Map of Custom Attribute Documents where the key is the
     * customAttributeId.
     *
     * @param documentTypeCode document type to get custom attributes for
     * @param documentNumber document number for this document
     * @return a List of custom attribute documents for this document
     */
    public Map<String, CustomAttributeDocument> getDefaultCustomAttributeDocuments(String documentTypeCode, List<? extends DocumentCustomData> customDataList);
    
    /**
     * 
     * This method is to set up the the custom attribute in attribute content
     * @param document
     * @param attributeName
     * @param networkId
     * @throws Exception
     */
    public void setCustomAttributeKeyValue(String documentNumber, Map<String, CustomAttributeDocument> customAttributeDocuments, String attributeName, String networkId);

    /**
     * 
     * This method is to get the custom attribute data type based on data type code
     * @param dataTypeCode
     * @return
     */
    public CustomAttributeDataType getCustomAttributeDataType(String dataTypeCode);

    /**
     * 
     * This method is to get the lookupfields of the lookupclass.
     * @param lookupClass
     * @return
     * @throws Exception
     */
    public List getLookupReturns(String lookupClass) throws Exception ;
    
    /**
     * 
     * This method is to get the lookupfields of the lookupclass, and converted to string ans separated by ",".
     * @param lookupClass
     * @return
     * @throws Exception
     */
    public String getLookupReturnsForAjaxCall(String lookupClass) throws Exception ;
}
