/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.person.attr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.CustomDataHelperBase;
import org.kuali.coeus.common.framework.person.attr.KcPersonExtendedAttributes;
import org.kuali.coeus.common.framework.person.attr.PersonCustomData;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.api.document.Document;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.kns.web.ui.Section;

public class KcPersonExtendedAttributesMaintainableImpl extends KraMaintainableImpl implements Maintainable {
    
    private CustomDataHelper customDataHelper;
    
    public KcPersonExtendedAttributesMaintainableImpl() {
        customDataHelper = new CustomDataHelper(this);
    }

    public CustomDataHelper getCustomDataHelper() {
        return customDataHelper;
    }

    public void setCustomDataHelper(CustomDataHelper customDataHelper) {
        this.customDataHelper = customDataHelper;
    }
    
    @Override 
    public void processAfterNew(MaintenanceDocument document, Map<String, String[]> requestParameters) {
        loadCustomData();
    }
    
    @Override
    public void processAfterEdit(MaintenanceDocument document, Map<String, String[]> requestParameters) {
        loadCustomData();
    }
    
    @Override
    public void processAfterCopy(MaintenanceDocument document, Map<String, String[]> requestParameters) {
        loadCustomData();
    }
    
    @Override
    public void processAfterRetrieve() {
        loadCustomData();
    }
    
    private void loadCustomData() {
        KcPersonExtendedAttributes kcPersonExtendedAttributes = (KcPersonExtendedAttributes) getDataObject();

        getCustomDataHelper().prepareCustomData();
    }

    @Override
    public void prepareForSave() {
        KcPersonExtendedAttributes kcPersonExtendedAttributes = (KcPersonExtendedAttributes) getDataObject();

        if (!isValidPrincipalId(kcPersonExtendedAttributes.getPersonId())) {
            reportInvalidPrincipalId(kcPersonExtendedAttributes);
        }
        
        if (!isValidCitizenshipTypeCode(kcPersonExtendedAttributes)) {
            reportInvalidCitizenshipTypeCode(kcPersonExtendedAttributes);
        }

        super.prepareForSave();
    }
    
    @Override
    public void saveDataObject() {
        KcPersonExtendedAttributes kcPersonExtendedAttributes = (KcPersonExtendedAttributes) getDataObject();
        
        if (kcPersonExtendedAttributes.getCitizenshipType() == null && kcPersonExtendedAttributes.getCitizenshipTypeCode() != null) {
            kcPersonExtendedAttributes.refreshReferenceObject("citizenshipType");
        }
        
        super.saveDataObject();
        
        getCustomDataHelper().setCustomAttributeContent(this.getDocumentNumber(), "PersonCustomDataAttribute");
    }

    private void reportInvalidPrincipalId(KcPersonExtendedAttributes kcPersonExtendedAttributes) {
        ErrorReporter errorReporter = new ErrorReporter();
        errorReporter.reportError("document.newMaintainableObject.personId", KeyConstants.PRINCIPALID_NOT_EXIST,
                "Principal ID does not exist.");

    }

    private boolean isValidPrincipalId(String principalId) {
        boolean valid = true;
        org.kuali.rice.kim.api.identity.PersonService personService = KimApiServiceLocator.getPersonService();

        if (StringUtils.isEmpty(principalId)) {
            valid = false;
        } else {
            if (personService.getPerson(principalId) == null) {
                valid = false;
            }
        }

        return valid;
    }
    
    private void reportInvalidCitizenshipTypeCode(KcPersonExtendedAttributes kcPersonExtendedAttributes) {
        ErrorReporter errorReporter = new ErrorReporter();
        errorReporter.reportError("document.newMaintainableObject.citizenshipTypeCode", KeyConstants.ERROR_MISSING_CITIZENSHIP_TYPE,
                "Please select a citizenship type.");

    }
    
    private boolean isValidCitizenshipTypeCode(KcPersonExtendedAttributes kcPersonExtendedAttributes) {
        Integer citizenshipType = kcPersonExtendedAttributes.getCitizenshipTypeCode();
        return citizenshipType != null;
    }

    @Override
    public List getSections(MaintenanceDocument document, Maintainable oldMaintainable) {
        List<Section> sections = super.getSections(document, oldMaintainable);

        for (Section section : sections) {
            for (Row row : section.getRows()) {
                for (Field field : row.getFields()) {
                    if (StringUtils.isNotEmpty(field.getPropertyName()) && field.getPropertyName().equalsIgnoreCase("personId")) {
                        field.setFieldConversions("principalId:personId");
                        field.setQuickFinderClassNameImpl("org.kuali.rice.kim.api.identity.Person");
                        field.setFieldDirectInquiryEnabled(true);
                        field.setInquiryParameters("personId:principalId");
                        break;
                    }
                }
            }
        }

        return filterPersonCustomDataSection(sections);
    }
    
    private List<Section> filterPersonCustomDataSection(List<Section> sections) {
        List<Section> filteredSections = new ArrayList<Section>();
        
        for (Section section : sections) {
            if (!StringUtils.equals(section.getSectionTitle(), "Edit Person Custom Data")) {
                filteredSections.add(section);
            }
        }
        
        return filteredSections;
    }

    public boolean documentNotRouted() {
        WorkflowDocumentService documentService = KewApiServiceLocator.getWorkflowDocumentService();
        Document doc = documentService.getDocument(getDocumentNumber());
        return doc.getStatus() == DocumentStatus.SAVED || doc.getStatus() == DocumentStatus.INITIATED;
    }
    
    public class CustomDataHelper extends CustomDataHelperBase<PersonCustomData> implements Serializable {

        private static final long serialVersionUID = -6829522940099878931L;
        
        private KcPersonExtendedAttributesMaintainableImpl maintainableImpl;
        private Map<String, CustomAttributeDocument> customAttributeDocuments;
        
        public CustomDataHelper(KcPersonExtendedAttributesMaintainableImpl maintainableImpl) {
            this.maintainableImpl = maintainableImpl;
            customAttributeDocuments = getCustomAttributeService().getDefaultCustomAttributeDocuments("PERS", 
                    maintainableImpl.getDataObject() != null ? ((KcPersonExtendedAttributes) maintainableImpl.getDataObject()).getPersonCustomDataList() : new ArrayList<PersonCustomData>());
        }

        @Override
        protected PersonCustomData getNewCustomData() {
            return new PersonCustomData();
        }

        @Override
        public List<PersonCustomData> getCustomDataList() {
            if (maintainableImpl.getDataObject() != null) {
                return ((KcPersonExtendedAttributes) maintainableImpl.getDataObject()).getPersonCustomDataList();
            } else {
                return new ArrayList<PersonCustomData>();
            }
        }

        public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
            return customAttributeDocuments;
        }

        public void setCustomAttributeDocuments(Map<String, CustomAttributeDocument> customAttributeDocuments) {
            this.customAttributeDocuments = customAttributeDocuments;
        }

        @Override
        public boolean documentNotRouted() {
            return maintainableImpl.documentNotRouted();
        }
    }
}