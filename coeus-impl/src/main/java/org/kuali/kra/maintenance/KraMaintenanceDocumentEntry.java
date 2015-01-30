/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.maintenance;

import org.kuali.coeus.common.questionnaire.framework.core.Questionnaire;
import org.kuali.rice.kns.datadictionary.MaintainableSectionDefinition;
import org.kuali.rice.kns.datadictionary.MaintenanceDocumentEntry;
import org.kuali.rice.krad.document.Document;

import java.util.List;

/**
 * 
 * This class provides a hook to KraMaintenanceDocument instead the default MaintenanceDocumentBase.
 * DD bean is in KraDataDictionaryBaseTypes.xml
 */
public class KraMaintenanceDocumentEntry extends MaintenanceDocumentEntry {
    public Class<? extends Document> getStandardDocumentBaseClass() {
            return KraMaintenanceDocument.class;
        }
    
        /**
         * @see org.kuali.rice.krad.datadictionary.DocumentEntry#setDocumentClass(java.lang.Class)
         */
        @Override
        public void setDocumentClass(Class<? extends Document> documentClass) {
            if (!KraMaintenanceDocument.class.isAssignableFrom(documentClass)) {
                throw new IllegalArgumentException("document class '" + documentClass + "' needs to have a superclass of '" + KraMaintenanceDocument.class + "'");
            }
            super.setDocumentClass(documentClass);
        }

        @Override
        public void setMaintainableSections(List<MaintainableSectionDefinition> maintainableSections) {
          if (Questionnaire.class.isAssignableFrom(getBusinessObjectClass())) {
             super.maintainableSections = maintainableSections;
          } else {
            super.setMaintainableSections(maintainableSections);
          }
        }

}
