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
package org.kuali.kra.maintenance;

import java.util.List;

import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.rice.kns.datadictionary.MaintainableSectionDefinition;
import org.kuali.rice.kns.datadictionary.MaintenanceDocumentEntry;
import org.kuali.rice.kns.document.Document;

public class KraMaintenanceDocumentEntry extends MaintenanceDocumentEntry {
    public Class<? extends Document> getStandardDocumentBaseClass() {
            return KraMaintenanceDocument.class;
        }
    
        /**
         * @see org.kuali.rice.kns.datadictionary.DocumentEntry#setDocumentClass(java.lang.Class)
         */
        @Override
        public void setDocumentClass(Class<? extends Document> documentClass) {
            if (!KraMaintenanceDocument.class.isAssignableFrom(documentClass)) {
                throw new IllegalArgumentException("document class '" + documentClass + "' needs to have a superclass of '" + KraMaintenanceDocument.class + "'");
            }
            super.setDocumentClass(documentClass);
        }
    
        @Override
        public List<MaintainableSectionDefinition> getMaintainableSections() {
            // TODO Auto-generated method stub
    //        if (Questionnaire.class.isAssignableFrom(getBusinessObjectClass())) {
    //            // this will not work because lots of places expect maintainablesections not null
    //            return null;
    //        }
            return super.getMaintainableSections();
        }
    
        @Override
        public void setMaintainableSections(List<MaintainableSectionDefinition> maintainableSections) {
          if (Questionnaire.class.isAssignableFrom(getBusinessObjectClass())) {
          // this will not work because lots of places expect maintainablesections not null
             super.maintainableSections = maintainableSections;
          } else {
            super.setMaintainableSections(maintainableSections);
          }
        }


}
