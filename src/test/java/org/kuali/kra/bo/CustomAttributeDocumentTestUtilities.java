/*
 * Copyright 2005-2013 The Kuali Foundation
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.sql.DataSource;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.kuali.kra.bo.CustomAttribute;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.rice.kew.actionrequest.ActionRequestValue;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.WorkflowDocumentFactory;
import org.kuali.rice.kew.api.action.ActionItem;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.engine.node.RouteNodeInstance;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

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
    public static final String TENURED_VALUE = "tdurkin";
    
    // custom attriutes
    public static Map<String, CustomAttributeDocument> setupTestCustomAttributeDocuments() {
        Map<String, CustomAttributeDocument> customAttributeDocuments = new HashMap<String, CustomAttributeDocument>();

        CustomAttribute customAttribute = buildCustomAttribute(1, "billingElement", "Billing Element", "1", new Integer(40), GROUP_NAME_1);
        CustomAttributeDocument customAttributeDocument = buildCustomAttributeDocument("PRDV", true, customAttribute);
        customAttributeDocuments.put("1", customAttributeDocument);

        customAttribute = buildCustomAttribute(2, "costSharingBudget", "Cost Sharing Budget", "1", new Integer(30), GROUP_NAME_1);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("2", customAttributeDocument);

        customAttribute = buildCustomAttribute(3, "numberOfTrainees", "# of Trainees", "2", new Integer(6), GROUP_NAME_1);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("3", customAttributeDocument);

        customAttribute = buildCustomAttribute(4, "graduateStudentCount", "Graduate Student Count", "2", new Integer(6), GROUP_NAME_2);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", true, customAttribute);
        customAttributeDocuments.put("4", customAttributeDocument);

        customAttribute = buildCustomAttribute(5, "tenured", "Tenured", "1", new Integer(30), GROUP_NAME_2, "org.kuali.kra.bo.KcPerson", "userName");
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("5", customAttributeDocument);

        customAttribute = buildCustomAttribute(6, "exportControls", "Export Controls", "1", new Integer(30), GROUP_NAME_3);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("6", customAttributeDocument);

        customAttribute = buildCustomAttribute(7, "inventions", "Inventions", "1", new Integer(30), GROUP_NAME_3, "org.kuali.kra.bo.KcPerson", "userName");
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("7", customAttributeDocument);

        customAttribute = buildCustomAttribute(8, "localReviewDate", "Local Review Date", "3", new Integer(10), GROUP_NAME_1);
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("8", customAttributeDocument);

        customAttribute = buildCustomAttribute(11, "ARRA_FUNDING", "ARRA Funding", "1", new Integer(3), GROUP_NAME_4,"org.kuali.kra.bo.ArgValueLookup","yes_no_flag");
        customAttributeDocument = buildCustomAttributeDocument("PRDV", false, customAttribute);
        customAttributeDocuments.put("9", customAttributeDocument);
        
        return customAttributeDocuments;
    }

    /**
     * This method...
     * @return
     */
    private static CustomAttributeDocument buildCustomAttributeDocument(String documentTypeCode, boolean required, CustomAttribute customAttribute) {
        CustomAttributeDocument customAttributeDocument = new CustomAttributeDocument();
        customAttributeDocument.setCustomAttributeId(customAttribute.getId());
        customAttributeDocument.setDocumentTypeName(documentTypeCode);
        customAttributeDocument.setRequired(required);

        customAttributeDocument.setCustomAttribute(customAttribute);
        return customAttributeDocument;
    }

    private static CustomAttribute buildCustomAttribute(Integer id, String name, String label, String dataTypeCode, Integer dataLength, String groupName) {
        return buildCustomAttribute(id, name, label, dataTypeCode, dataLength, groupName, null, null);
    }

    /**
     * This method...
     * @return
     */
    private static CustomAttribute buildCustomAttribute(Integer id, String name, String label, String dataTypeCode, Integer dataLength, String groupName, String lookupClass, String lookupReturn) {
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
