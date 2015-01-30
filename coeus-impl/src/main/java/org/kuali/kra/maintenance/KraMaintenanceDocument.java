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
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.krad.rules.rule.event.DocumentEvent;

import java.util.ArrayList;

/**
 * 
 * This class is primarily for Questionnaire maintenance, so that QuestionnaireQuestions & QuestionnaireUsages
 * can be include in xmldocumentcontent.
 * It has a classdescriptor in repository.xml
 */
public class KraMaintenanceDocument extends MaintenanceDocumentBase {

    private static final long serialVersionUID = -730971565265598319L;


    public KraMaintenanceDocument() {
        super();
    }

    public KraMaintenanceDocument(String documentTypeName) {
        super(documentTypeName);
    }


    @Override
    public void prepareForSave(DocumentEvent event) {
        super.prepareForSave(event);
		populateDocumentAttachment();
		
        MaintenanceDocumentDictionaryService maintenanceDocumentDictionaryService = KNSServiceLocator.getMaintenanceDocumentDictionaryService();
        String docTypeName = maintenanceDocumentDictionaryService.getDocumentTypeName(this.getNewMaintainableObject()
                .getBusinessObject().getClass());
        MaintenanceDocumentEntry maintenanceDocumentEntry = maintenanceDocumentDictionaryService
                .getMaintenanceDocumentEntry(docTypeName);
        /* this is to force to include the collections in xml content when businessobjectserializationservice is called 
         * This is more like a hack solution.  Maybe, rice has a better approach ?
         * if collection is not included in xml content, then it can not be retrieved when doc search, so it totally does not make sense
         * is there a way to make the jsp inclusion work?
         */
        if (Questionnaire.class.isAssignableFrom(this.getNewMaintainableObject().getDataObject().getClass())) {
            maintenanceDocumentEntry.setMaintainableSections(null);
        }
        populateXmlDocumentContentsFromMaintainables();
        if (Questionnaire.class.isAssignableFrom(this.getNewMaintainableObject().getDataObject().getClass())) {
            maintenanceDocumentEntry.setMaintainableSections(new ArrayList<MaintainableSectionDefinition>());
        }
        
        if (this.getVersionNumber() == null) {
            this.setVersionNumber(Long.valueOf(0));
        }

    }


}
