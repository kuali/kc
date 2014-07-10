/*
 * Copyright 2005-2014 The Kuali Foundation
 *
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
package org.kuali.kra.bo;

import java.util.HashMap;
import java.util.Map;

import org.kuali.coeus.common.framework.custom.arg.ArgValueLookup;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.person.KcPerson;

/**
 * Defines utilities for unit testing
 */
public class CustomAttributeDocumentTestUtilities {
    public static final String GROUP_NAME_1 = "asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf";
    public static final String GROUP_NAME_2 = "Personnel Items for Review";
    public static final String GROUP_NAME_3 = "Project Details";
    private static final String GROUP_NAME_4 = "Other";
    public static final String BILLING_ELEMENT_VALUE = "This is billing element";
    public static final String GRADUATE_STUDENT_COUNT_VALUE = "5";
    public static final String LOCAL_REVIEW_DATE_VALUE = "02/08/2008";

    // custom attriutes
    public static Map<String, CustomAttributeDocument> setupTestCustomAttributeDocuments() {
        Map<String, CustomAttributeDocument> customAttributeDocuments = new HashMap<String, CustomAttributeDocument>();

        CustomAttribute customAttribute = buildCustomAttribute(1L, "billingElement", "Billing Element", "1", new Integer(40), GROUP_NAME_1);
        CustomAttributeDocument customAttributeDocument = buildCustomAttributeDocument("PRDV", true, customAttribute);
        customAttributeDocuments.put("1", customAttributeDocument);

        customAttribute = buildCustomAttribute(2L, "costSharingBudget", "Cost Sharing Budget", "1", new Integer(30), GROUP_NAME_1);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("2", customAttributeDocument);

        customAttribute = buildCustomAttribute(3L, "numberOfTrainees", "# of Trainees", "2", new Integer(6), GROUP_NAME_1);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("3", customAttributeDocument);

        customAttribute = buildCustomAttribute(4L, "graduateStudentCount", "Graduate Student Count", "2", new Integer(6), GROUP_NAME_2);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", true, customAttribute);
        customAttributeDocuments.put("4", customAttributeDocument);

        customAttribute = buildCustomAttribute(5L, "tenured", "Tenured", "1", new Integer(30), GROUP_NAME_2, KcPerson.class.getName(), "userName");
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("5", customAttributeDocument);

        customAttribute = buildCustomAttribute(6L, "exportControls", "Export Controls", "1", new Integer(30), GROUP_NAME_3);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("6", customAttributeDocument);

        customAttribute = buildCustomAttribute(7L, "inventions", "Inventions", "1", new Integer(30), GROUP_NAME_3, KcPerson.class.getName(), "userName");
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("7", customAttributeDocument);

        customAttribute = buildCustomAttribute(8L, "localReviewDate", "Local Review Date", "3", new Integer(10), GROUP_NAME_1);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("8", customAttributeDocument);

        customAttribute = buildCustomAttribute(11L, "ARRA_FUNDING", "ARRA Funding", "1", new Integer(3), GROUP_NAME_4,ArgValueLookup.class.getName(),"yes_no_flag");
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("9", customAttributeDocument);
        
        return customAttributeDocuments;
    }

    private static CustomAttributeDocument buildCustomAttributeDocument(String documentTypeCode, boolean required, CustomAttribute customAttribute) {
        CustomAttributeDocument customAttributeDocument = new CustomAttributeDocument();
        customAttributeDocument.setId(customAttribute.getId());
        customAttributeDocument.setDocumentTypeName(documentTypeCode);
        customAttributeDocument.setRequired(required);

        customAttributeDocument.setCustomAttribute(customAttribute);
        return customAttributeDocument;
    }

    private static CustomAttribute buildCustomAttribute(Long id, String name, String label, String dataTypeCode, Integer dataLength, String groupName) {
        return buildCustomAttribute(id, name, label, dataTypeCode, dataLength, groupName, null, null);
    }

    private static CustomAttribute buildCustomAttribute(Long id, String name, String label, String dataTypeCode, Integer dataLength, String groupName, String lookupClass, String lookupReturn) {
        CustomAttribute customAttribute = new CustomAttribute();

        customAttribute.setId(id);
        customAttribute.setName(name);
        customAttribute.setLabel(label);
        customAttribute.setDataTypeCode(dataTypeCode);
        customAttribute.setDataLength(dataLength);
        customAttribute.setGroupName(groupName);
        customAttribute.setLookupClass(lookupClass);
        customAttribute.setLookupReturn(lookupReturn);

        return customAttribute;
    }
    

}
