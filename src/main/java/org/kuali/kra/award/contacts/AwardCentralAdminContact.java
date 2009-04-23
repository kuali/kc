/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.contacts;

/**
 * This class is a complete hack to satisfy the DataDictionary requirements in this DataDictionary method:
 * 
 * public DocumentEntry getDocumentEntry(String documentTypeDDKey ) {
        if (StringUtils.isBlank(documentTypeDDKey)) {
            throw new IllegalArgumentException("invalid (blank) documentTypeName");
        }
        if ( LOG.isDebugEnabled() ) {
            LOG.debug("calling getDocumentEntry by documentTypeName '" + documentTypeDDKey + "'");
        }

        DocumentEntry de = documentEntries.get(documentTypeDDKey);  
        
        if ( de == null ) {
            try {
                Class clazz = Class.forName( documentTypeDDKey );
                de = documentEntriesByBusinessObjectClass.get(clazz);
                if ( de == null ) {
                    de = documentEntriesByMaintainableClass.get(clazz);
                }
            } catch ( ClassNotFoundException ex ) {
                LOG.warn( "Unable to find document entry for key: " + documentTypeDDKey );
            }
        }
        
        return de;
    }
    
    Since the Award Central Admin contact type uses the same BO as the Unit Contact type, 
    the DD can't find the AwardCentralAdminContact DD entry when it does the 
    de = documentEntriesByBusinessObjectClass.get(clazz) lookup
    
    When will the world ever learn that every conceivable problem can't be solved through a centrally managed solution conceived 
    by a handful of people, even really smart people?
 */
public class AwardCentralAdminContact extends AwardUnitContact {
    private static final long serialVersionUID = 2430642816335021874L;

}