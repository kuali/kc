package org.kuali.kra.rules;

import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.bo.KcPersonExtendedAttributes;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

public class KcPersonExtendedAttributesPersonIdExistenceRule extends KraMaintenanceDocumentRuleBase {
    
    private static final String personIdName = "principalId";
    
    public KcPersonExtendedAttributesPersonIdExistenceRule() {
        super();
    }
    
    @Override
    public boolean processSaveDocument(Document document) {
        MaintenanceDocument maintenanceDocument = (MaintenanceDocument) document;
        return super.processSaveDocument(document) && this.checkExistence(maintenanceDocument);
    }
    
    @Override
    protected boolean checkExistenceFromTable(Class clazz, Map fieldValues, String errorField, String errorParam) {
        boolean success = true;
        String idString = (String) fieldValues.get(personIdName);
        success = this.getPersonService().getPerson(idString) != null;
        
        if (!success) {
            GlobalVariables.getMessageMap().putError(KRADConstants.MAINTENANCE_NEW_MAINTAINABLE + errorField, 
                    RiceKeyConstants.ERROR_EXISTENCE, errorParam);
        }
        return success;
    }
    
     /**
     * 
     * This method is to check the existence of personId in table.
     * @param maintenanceDocument
     * @return
     */
    private boolean checkExistence(MaintenanceDocument maintenanceDocument) {
        boolean valid= true;
        if (LOG.isDebugEnabled()) {
            LOG.debug("new maintainable is: " + maintenanceDocument.getNewMaintainableObject().getClass());
        }
        KcPersonExtendedAttributes kcPesonExtendedAttributes = (KcPersonExtendedAttributes) maintenanceDocument.getNewMaintainableObject().getDataObject();

        Map pkMap = new HashMap();
        pkMap.put(personIdName, kcPesonExtendedAttributes.getPersonId());
        
        valid = checkExistenceFromTable(org.kuali.rice.kim.api.identity.Person.class, pkMap, personIdName, "KcPersonExtendedAttributes Id");

        return valid;

    }
        
}
