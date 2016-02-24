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
package org.kuali.coeus.sys.framework.rule;

import org.apache.commons.collections4.keyvalue.DefaultMapEntry;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.coeus.sys.framework.validation.SoftError;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.TransactionalDocument;
import org.kuali.rice.krad.rules.DocumentRuleBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.KRADPropertyConstants;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Base implementation class for KRA document business rules
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.13 $
 */
public abstract class KcTransactionalDocumentRuleBase extends DocumentRuleBase {
    public static final String DOCUMENT_ERROR_PATH = "document";
    public static final boolean VALIDATION_REQUIRED = true;
    public static final boolean CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME = false;

    private ErrorReporter errorReporter;
    private DataObjectService dataObjectService;
    private BusinessObjectService businessObjectService;
    private DictionaryValidationService knsDictionaryValidationService;

    /**
     * Delegates to {@link ErrorReporter#reportError(String, String, String...) ErrorReporter#reportError(String, String, String...)}
     * to keep api compatibility.
     * @see ErrorReporter#reportError(String, String, String...)
     */
    protected void reportError(String propertyName, String errorKey, String... errorParams) {
        this.getErrorReporter().reportError(propertyName, errorKey, errorParams);
    }
    
    /**
     * Delegates to {@link ErrorReporter#reportError(String, String, String...) ErrorReporter#reportError(String, String, String...)}
     * to keep api compatibility.
     * @see ErrorReporter#reportError(String, String, String...)
     */
    protected void reportWarning(String propertyName, String errorKey, String... errorParams) {
        this.getErrorReporter().reportWarning(propertyName, errorKey, errorParams);
    }
    
    /**
     * Delegates to {@link ErrorReporter#reportAuditError(AuditError, String, String, String) ErrorReporter#reportAuditError(AuditError, String, String, String)}
     * to keep api compatibility.
     * @see ErrorReporter#reportAuditError(AuditError, String, String, String)
     */
    protected void addAuditError(AuditError error, String errorKey, String clusterLabel, String clusterCategory) {
        this.getErrorReporter().reportAuditError(error, errorKey, clusterLabel, clusterCategory);
    }
    
    /**
     * Delegates to {@link ErrorReporter#reportSoftError(String, String, String...) ErrorReporter#reportSoftError(String, String, String...)}
     * to keep api compatibility.
     * @see ErrorReporter#reportSoftError(String, String, String...)
     */
    protected void reportSoftError(String propertyName, String errorKey, String... errorParams) {
        this.getErrorReporter().reportSoftError(propertyName, errorKey, errorParams);
    }

    /**
     * Delegates to {@link ErrorReporter#getSoftErrors() ErrorReporter#getSoftErrors()}
     * to keep api compatibility.
     * @see ErrorReporter#getSoftErrors()
     */
    public Map<String, Collection<SoftError>> getSoftErrors() {
        return this.getErrorReporter().getSoftErrors();
    }

    /**
     * Convenience method for creating a <code>{@link SimpleEntry}</code> out of a key/value pair
     * 
     * @param key
     * @param value
     * @return SimpleImmutableEntry
     */
    protected Entry<String, String> keyValue(String key, Object value) {

        @SuppressWarnings("unchecked") //Commons Collections does not support Generics
        final Entry<String, String> entry
            = (value == null) ? new DefaultMapEntry(key, "") :  new DefaultMapEntry(key, value.toString());

        return entry;
    }
    /**
     * The opposite of <code>{@link #isValid(Class, java.util.Map.Entry[])}</code>
     * 
     * @param boClass the class of the business object to validate
     * @param entries varargs array of <code>{@link SimpleEntry}</code> key/value pair instances
     * @return true if invalid; false if valid
     * @see #isValid(Class, java.util.Map.Entry[])
     */
    protected boolean isInvalid(Class<?> boClass, Entry<String, String> ... entries) {
        return !isValid(boClass, entries);
    }
    
    /**
     * Is the given code valid?  Query the database for a matching code
     * If found, it is valid; otherwise it is invalid.
     * 
     * @param boClass the class of the business object to validate
     * @param entries varargs array of <code>{@link SimpleEntry}</code> key/value pair instances
     * @return true if invalid; false if valid
     * @see #isValid(Class, java.util.Map.Entry[])
     */
    protected boolean isValid(Class<?> boClass, Entry<String,String> ... entries) {
        boolean retval = false;
        
        if (entries != null && entries.length > 0) {
            Map<String,String> fieldValues = new HashMap<String,String>();
            
            for (Entry<String,String> entry : entries) {
                fieldValues.put(entry.getKey(), entry.getValue());
            }

                if (getBusinessObjectService().countMatching(boClass, fieldValues) > 0) {
                    retval = true;
            }
        }
        return retval;
    }

    /*
     * Overriding the rice method since we need to use the knsDD validation service instead of the KRAd one.
     * We use KNS DD components like validation patterns that the Krad validator does not even check
     * because KRAD only checks for constraints and all validation patterns are constraints in KRAD.
     */
    @Override
    public boolean processSaveDocument(Document document) {
        boolean isValid = true;

        isValid = isDocumentOverviewValid(document);

        GlobalVariables.getMessageMap().addToErrorPath(KRADConstants.DOCUMENT_PROPERTY_NAME);
        // Using the KNS DD validation service here instead of KRAD
        getKnsDictionaryValidationService().validateDocumentAndUpdatableReferencesRecursively(document, getMaxDictionaryValidationDepth(),
                VALIDATION_REQUIRED, CHOMP_LAST_LETTER_S_FROM_COLLECTION_NAME);
        // leaving this in because there is no DocumentDictionaryService in KNS to check for existence. This might just be
        //handled by the call above.
        getDictionaryValidationService().validateDefaultExistenceChecksForTransDoc((TransactionalDocument) document);

        GlobalVariables.getMessageMap().removeFromErrorPath(KRADConstants.DOCUMENT_PROPERTY_NAME);

        isValid &= GlobalVariables.getMessageMap().hasNoErrors();
        isValid &= processCustomSaveDocumentBusinessRules(document);

        return isValid;
    }
    
    /*
     * Overriding the rice method and removing the docHeader description check here since 
     * it is already being done in the kns DD validation step above to avoid dual error messages.
     */
    @Override
    public boolean isDocumentOverviewValid(Document document) {
        // add in the documentHeader path
        GlobalVariables.getMessageMap().addToErrorPath(KRADConstants.DOCUMENT_PROPERTY_NAME);
        GlobalVariables.getMessageMap().addToErrorPath(KRADConstants.DOCUMENT_HEADER_PROPERTY_NAME);

        validateSensitiveDataValue(KRADPropertyConstants.EXPLANATION, document.getDocumentHeader().getExplanation(),
                getDataDictionaryService().getAttributeLabel(DocumentHeader.class, KRADPropertyConstants.EXPLANATION));
        validateSensitiveDataValue(KRADPropertyConstants.DOCUMENT_DESCRIPTION,
                document.getDocumentHeader().getDocumentDescription(), getDataDictionaryService().getAttributeLabel(
                DocumentHeader.class, KRADPropertyConstants.DOCUMENT_DESCRIPTION));

        // drop the error path keys off now
        GlobalVariables.getMessageMap().removeFromErrorPath(KRADConstants.DOCUMENT_HEADER_PROPERTY_NAME);
        GlobalVariables.getMessageMap().removeFromErrorPath(KRADConstants.DOCUMENT_PROPERTY_NAME);

        return GlobalVariables.getMessageMap().hasNoErrors();
    }

    protected final BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService){
        this.businessObjectService = businessObjectService;
    }

    public ErrorReporter getErrorReporter() {
        if (this.errorReporter == null) {
            this.errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        }

        return this.errorReporter;
    }

    public void setErrorReporter(ErrorReporter errorReporter) {
        this.errorReporter = errorReporter;
    }
    
    protected DictionaryValidationService getKnsDictionaryValidationService() {
        if (this.knsDictionaryValidationService == null) {
            this.knsDictionaryValidationService = KNSServiceLocator.getKNSDictionaryValidationService();       
        }
        return this.knsDictionaryValidationService;
    }

    public void setKnsDictionaryValidationService(DictionaryValidationService knsDictionaryValidationService) {
        this.knsDictionaryValidationService = knsDictionaryValidationService;
    }
    
    protected DataObjectService getDataObjectService() {
        if (dataObjectService == null) {
            dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }
        return dataObjectService;
    }

    protected void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
