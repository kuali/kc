package org.kuali.kra.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.kra.bo.KcPersonExtendedAttributes;
import org.kuali.kra.bo.KcPersonExtendedAttributesMaintainableImpl;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.event.SaveCustomDataEvent;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.event.ApproveDocumentEvent;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.HashMap;
import java.util.Map;

public class KcPersonExtendedAttributesMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    
    private static final String PRINCIPAL_ID = "principalId";
    private static final String CUSTOM_DATA_ERROR_PREFIX = KRADConstants.MAINTENANCE_NEW_MAINTAINABLE + "businessObject.personCustomDataList";
    private static final int FIELD_ERA_COMMONS_USERNAME_MIN_LENGTH = 6;
    
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
                maintainableImpl.getCustomDataHelper().getCustomAttributeDocuments()));
        rulePassed &= checkEraCommonsUserName(kcPersonExtendedAttributes);
        
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
    private boolean checkEraCommonsUserName(KcPersonExtendedAttributes kcPersonExtendedAttributes) {
        boolean valid = true;

        if(StringUtils.isNotBlank(kcPersonExtendedAttributes.getEraCommonUserName()) && kcPersonExtendedAttributes.getEraCommonUserName().length() < FIELD_ERA_COMMONS_USERNAME_MIN_LENGTH){
            GlobalVariables.getMessageMap().putError(KRADConstants.MAINTENANCE_NEW_MAINTAINABLE +"eRACommonsUserName", KeyConstants.ERROR_MINLENGTH,
                    new String[] {"eRA Commons User Name" , ""+ FIELD_ERA_COMMONS_USERNAME_MIN_LENGTH}); 
            
            valid = false;
        }
        return valid;
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
        
}