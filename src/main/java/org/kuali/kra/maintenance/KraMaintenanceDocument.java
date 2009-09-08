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

import java.util.ArrayList;

import org.kuali.kra.questionnaire.Questionnaire;
import org.kuali.rice.kns.datadictionary.MaintainableSectionDefinition;
import org.kuali.rice.kns.datadictionary.MaintenanceDocumentEntry;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.rule.event.KualiDocumentEvent;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;

public class KraMaintenanceDocument extends MaintenanceDocumentBase {

    public KraMaintenanceDocument() {
        super();
    }

    public KraMaintenanceDocument(String documentTypeName) {
        super(documentTypeName);
    }


    @Override
    public void prepareForSave(KualiDocumentEvent event) {
        super.prepareForSave(event);
        populateDocumentAttachment();

        MaintenanceDocumentDictionaryService maintenanceDocumentDictionaryService = KNSServiceLocator
                .getMaintenanceDocumentDictionaryService();
        String docTypeName = maintenanceDocumentDictionaryService.getDocumentTypeName(this.getNewMaintainableObject()
                .getBusinessObject().getClass());
        MaintenanceDocumentEntry maintenanceDocumentEntry = maintenanceDocumentDictionaryService
                .getMaintenanceDocumentEntry(docTypeName);
        /* this is to force to include the collections in xml content when businessobjectserializationservice is called 
         * This is more like a hack solution.  Maybe, rice has a better approach ?
         * if collection is not included in xml content, then it can not be retrieved when doc search, so it totally does not make sense
         * is there a way to make the jsp inclusion work?
         */
        if (Questionnaire.class.isAssignableFrom(this.getNewMaintainableObject().getBusinessObject().getClass())) {
            maintenanceDocumentEntry.setMaintainableSections(null);
        }
        populateXmlDocumentContentsFromMaintainables();
        if (Questionnaire.class.isAssignableFrom(this.getNewMaintainableObject().getBusinessObject().getClass())) {
            maintenanceDocumentEntry.setMaintainableSections(new ArrayList<MaintainableSectionDefinition>());
        }
    }


}
