/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.bo;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.kns.web.ui.Section;

/**
 * This class...
 */
public class KcPersonExtendedAttributesMaintainableImpl extends KraMaintainableImpl implements Maintainable {

    private static final long serialVersionUID = -1883173375053057062L;

    @Override
    public void prepareForSave() {
        KcPersonExtendedAttributes kcPersonExtendedAttributes = (KcPersonExtendedAttributes) this.getDataObject();

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
        KcPersonExtendedAttributes kcPersonExtendedAttributes = (KcPersonExtendedAttributes) this.getDataObject();
        if (kcPersonExtendedAttributes.getCitizenshipType() == null && kcPersonExtendedAttributes.getCitizenshipTypeCode() != null) {
            kcPersonExtendedAttributes.refreshReferenceObject("citizenshipType");
        }
        super.saveDataObject();
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

        return sections;
    }
}