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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.attr.KcPersonExtendedAttributes;
import org.kuali.coeus.common.framework.person.attr.PersonBiosketch;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.common.framework.custom.SaveCustomDataEvent;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.event.ApproveDocumentEvent;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KcPersonExtendedAttributesMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    
    private static final String PRINCIPAL_ID = "principalId";
    private static final String CUSTOM_DATA_ERROR_PREFIX = KRADConstants.MAINTENANCE_NEW_MAINTAINABLE + "businessObject.personCustomDataList";
    
    @Override
    public boolean processSaveDocument(Document document) {
        boolean rulePassed = true;
        
        rulePassed &= super.processSaveDocument(document);
        
        MaintenanceDocument maintenanceDocument = (MaintenanceDocument) document;
        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        KcPersonExtendedAttributesMaintainableImpl maintainableImpl = (KcPersonExtendedAttributesMaintainableImpl) maintenanceDocument.getNewMaintainableObject();
        KcPersonExtendedAttributes kcPersonExtendedAttributes = (KcPersonExtendedAttributes) maintenanceDocument.getNewMaintainableObject().getDataObject();

        rulePassed &= checkExistence(kcPersonExtendedAttributes);
        rulePassed &= processRules(new SaveCustomDataEvent(CUSTOM_DATA_ERROR_PREFIX, document, 
                kcPersonExtendedAttributes.getPersonCustomDataList(),
                maintainableImpl.getCustomAttributeDocuments()));
        rulePassed &= checkForAttachmentData(kcPersonExtendedAttributes.getAttachments());
        checkEraCommonsUserName(kcPersonExtendedAttributes);
        return rulePassed;
    }
    
    @Override
    public boolean processRouteDocument(Document document) {
        boolean rulePassed = true;
        
        rulePassed &= super.processRouteDocument(document);
        
        MaintenanceDocument maintenanceDocument = (MaintenanceDocument) document;
        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        KcPersonExtendedAttributes kcPersonExtendedAttributes = (KcPersonExtendedAttributes) maintenanceDocument.getNewMaintainableObject().getDataObject();
        
        rulePassed &= new PersonCustomDataAuditRule().processRunAuditBusinessRules(maintenanceDocument);

        return rulePassed;
    }
    
    @Override
    public boolean processApproveDocument(ApproveDocumentEvent approveEvent) {
        boolean rulePassed = true;
        
        rulePassed &= super.processApproveDocument(approveEvent);
        
        Document document = approveEvent.getDocument();
        
        MaintenanceDocument maintenanceDocument = (MaintenanceDocument) document;
        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        KcPersonExtendedAttributes kcPersonExtendedAttributes = (KcPersonExtendedAttributes) maintenanceDocument.getNewMaintainableObject().getDataObject();
        
        rulePassed &= new PersonCustomDataAuditRule().processRunAuditBusinessRules(maintenanceDocument);

        return rulePassed;
    }

    @Override
    public boolean processAddCollectionLineBusinessRules(MaintenanceDocument document, String collectionName,
                                                         PersistableBusinessObject bo) {
        if (bo instanceof PersonBiosketch) {
            if (((PersonBiosketch)bo).getAttachmentFile() == null) {
                reportMissingAttachment("document.newMaintainableObject.add.attachments.attachmentFile");
                return false;
            }
        }
        return super.processAddCollectionLineBusinessRules(document,collectionName,bo);
    }
    
     /**
     * This method is to check the existence of personId in table.
     * 
     * @param kcPersonExtendedAttributes
     * @return
     */
    private boolean checkExistence(KcPersonExtendedAttributes kcPersonExtendedAttributes) {
        boolean valid = true;

        Map pkMap = new HashMap();
        pkMap.put(PRINCIPAL_ID, kcPersonExtendedAttributes.getPersonId());
        
        valid = checkExistenceFromTable(org.kuali.rice.kim.api.identity.Person.class, pkMap, PRINCIPAL_ID, "KcPersonExtendedAttributes Id");

        return valid;
    }
    
    /**
     * This method is to check the minimum length of eRACommonUserName.
     * 
     * @param kcPersonExtendedAttributes
     * @return
     */
    private void checkEraCommonsUserName(KcPersonExtendedAttributes kcPersonExtendedAttributes) {
    	if (StringUtils.isNotBlank(kcPersonExtendedAttributes.getEraCommonUserName()) && kcPersonExtendedAttributes.getEraCommonUserName().length() < Constants.ERA_COMMONS_USERNAME_MIN_LENGTH) {
    		GlobalVariables.getMessageMap().putWarning(KRADConstants.MAINTENANCE_NEW_MAINTAINABLE +"eRACommonsUserName", KeyConstants.ERROR_MINLENGTH,
    				new String[] {"eRA Commons User Name" , ""+ Constants.ERA_COMMONS_USERNAME_MIN_LENGTH}); 
    	}
    }
    
    @Override
    protected boolean checkExistenceFromTable(Class clazz, Map fieldValues, String errorField, String errorParam) {
        boolean success = true;
        String idString = (String) fieldValues.get(PRINCIPAL_ID);
        success = getPersonService().getPerson(idString) != null;
        
        if (!success) {
            GlobalVariables.getMessageMap().putError(KRADConstants.MAINTENANCE_NEW_MAINTAINABLE + errorField, 
                    RiceKeyConstants.ERROR_EXISTENCE, errorParam);
        }
        return success;
    }

    protected boolean checkForAttachmentData(List<PersonBiosketch> attachments) {
        int index = 0;
        for (PersonBiosketch biosketch : attachments) {
            if (biosketch.getAttachmentFile() == null && biosketch.getAttachmentContent() == null) {
                reportMissingAttachment("document.newMaintainableObject.attachments[" + index + "].attachmentFile");
                return false;
            }
            index++;
        }
        return true;
    }

    private void reportMissingAttachment(String errorPath) {
        ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
        errorReporter.reportError(errorPath, KeyConstants.ERROR_REQUIRED,
                "Attachment File (Attachment File)");
    }
        
}
