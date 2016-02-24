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
package org.kuali.kra.maintenance;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;
import org.kuali.rice.kns.rules.MaintenanceDocumentRule;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.maintenance.MaintainableImpl;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 *  Base class for testing <code>{@link MaintenanceDocument}</code> instances
 */
public abstract class MaintenanceRuleTestBase extends KcIntegrationTestBase {
    private static final Log LOG = LogFactory.getLog(MaintenanceRuleTestBase.class); 
    private static final String DOCUMENT_ERRORS = "document.document*,document.explanation*,document.reversal*,document.selected*,document.header*";
        
    /**
     * This method creates a minimal MaintenanceDocument instance, and populates it with the provided businessObject for the
     * newMaintainable, and null for the oldMaintainable.
     * 
     * @param newSubAccount - populated subAccount for the newMaintainable
     * @return a populated MaintenanceDocument instance
     * @throws Exception 
     */
    protected MaintenanceDocument newMaintDoc(PersistableBusinessObject newBo) throws Exception {
        return newMaintDoc(null, newBo);
    }

    /**
     * This method creates a minimal MaintenanceDocument instance, and populates it with the provided businessObjects for the
     * newMaintainable and oldMaintainable.
     * 
     * @param oldSubAccount - populated subAccount for the oldMaintainable
     * @param newSubAccount - populated subAccount for the newMaintainable
     * @return a populated MaintenanceDocument instance
     * @throws Exception 
     */
    protected MaintenanceDocument newMaintDoc(PersistableBusinessObject oldBo, PersistableBusinessObject newBo) throws Exception {

        // disallow null value for newBo
        if (null == newBo) {
            throw new IllegalArgumentException("Invalid value (null) for newBo.  " + "This must always be a valid, populated BusinessObject instance.");
        }

        // get a new MaintenanceDocument from Spring
        MaintenanceDocument document = null;
        try {
            document = (MaintenanceDocument) KRADServiceLocatorWeb.getDocumentService().getNewDocument(getMaintenanceDocumentDictionaryService().getDocumentTypeName(newBo.getClass()));
        }
        catch (WorkflowException e) {
            throw new RuntimeException(e);
        }

        // add all the pieces
        document.getDocumentHeader().setDocumentDescription("test");
        if (null == oldBo) {
            document.setOldMaintainableObject(getNewMaintainableImpl(null));
        }
        else {
            document.setOldMaintainableObject(getNewMaintainableImpl(oldBo));
            document.getOldMaintainableObject().setBoClass(oldBo.getClass());
        }
        document.setNewMaintainableObject(getNewMaintainableImpl(newBo));
        document.getNewMaintainableObject().setBoClass(newBo.getClass());
        return document;
    }
    
    protected MaintainableImpl getNewMaintainableImpl(PersistableBusinessObject bo) {
        if (bo == null) {
            return new KualiMaintainableImpl();
        } else {
            return new KualiMaintainableImpl(bo);
        }
    }

    /**
     * This method creates a new instance of the specified ruleClass, and then injects the maintenanceDocument and associated
     * business objects.
     * 
     * @param maintDoc - the populated MaintenanceDocument instance
     * @param ruleClass - the class of rule to instantiate
     * @return a populated and ready-to-test rule, of the specified class
     */
    protected <T extends MaintenanceDocumentRule> T setupMaintDocRule(MaintenanceDocument maintDoc, Class<T> ruleClass) {

        T rule;
        try {
            rule = ruleClass.newInstance();
        }
        catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        rule.setupBaseConvenienceObjects(maintDoc);

        // confirm that we're starting with no errors
        assertEquals(0, GlobalVariables.getMessageMap().getErrorMessages().size());

        return rule;
    }

    /**
     * This method tests whether the field error exists and returns the result of this test.
     * 
     * @param fieldName
     * @param errorKey
     * @return True if the error exists in the GlobalErrors, false if not.
     */
    protected boolean doesFieldErrorExist(String fieldName, String errorKey) {
        return GlobalVariables.getMessageMap().fieldHasMessage(MaintenanceDocumentRuleBase.MAINTAINABLE_ERROR_PREFIX + fieldName, errorKey);
    }

    /**
     * This method tests whether the existence check on the error matches what is expected by what is passed into expectedResult.
     * This method will fail the assertion if the presence of the error is not what is expected.
     * 
     * @param fieldName
     * @param errorKey
     * @param expectedResult - True if the error is expected, False if it is not.
     */
    protected void assertFieldErrorExistence(String fieldName, String errorKey, boolean expectedResult) {
        boolean result = doesFieldErrorExist(fieldName, errorKey);
        assertEquals("Existence check for Error on fieldName/errorKey: " + fieldName + "/" + errorKey + ". " + GlobalVariables.getMessageMap(), expectedResult, result);
    }

    /**
     * This method tests whether a given combination of fieldName and errorKey exists in the GlobalVariables.getMessageMap(). The
     * assert will fail if the fieldName &amp; errorKey combination doesnt exist. NOTE that fieldName should NOT include the prefix
     * errorPath.
     * 
     * @param fieldName - fieldName as it would be provided when adding the error
     * @param errorKey - errorKey as it would be provided when adding the error
     */
    protected void assertFieldErrorExists(String fieldName, String errorKey) {
        boolean result = GlobalVariables.getMessageMap().fieldHasMessage(MaintenanceDocumentRuleBase.MAINTAINABLE_ERROR_PREFIX + fieldName, errorKey);
        assertTrue("FieldName (" + fieldName + ") should contain errorKey: " + errorKey, result);
    }
    
    /**
     * Locate from Spring the <code>{@link MaintenanceDocumentDictionaryService}</code> singleton
     * 
     * @return MaintenanceDocumentDictionaryService
     */
    protected MaintenanceDocumentDictionaryService getMaintenanceDocumentDictionaryService() {
        return KcServiceLocator.getService(MaintenanceDocumentDictionaryService.class);
    }
    
    /**
     * Locate from Spring the <code>{@link DictionaryValidationService}</code> singleton
     *
     * @return DictionaryValidationService
     */
    protected DictionaryValidationService getDictionaryValidationService() {
        return KNSServiceLocator.getKNSDictionaryValidationService();
    }
}
