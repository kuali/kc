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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.CustomDataHelperBase;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.person.attr.KcPersonExtendedAttributes;
import org.kuali.coeus.common.framework.person.attr.PersonCustomData;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.api.document.Document;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.kuali.rice.kew.api.document.WorkflowDocumentService;
import org.kuali.rice.kim.api.identity.Person;
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
        ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
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
        ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
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
                        field.setQuickFinderClassNameImpl(Person.class.getName());
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
    
	public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
		return getCustomDataHelper().getCustomAttributeDocuments();
	}
	
    protected static class CustomDataHelper extends CustomDataHelperBase<PersonCustomData> implements Serializable {

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

    public CustomDataHelper getCustomDataHelper() {
        return customDataHelper;
    }

    protected void setCustomDataHelper(CustomDataHelper customDataHelper) {
        this.customDataHelper = customDataHelper;
    }
}
