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
package org.kuali.kra.subaward.document.authorizer;

import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.document.authorization.SubAwardTask;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.document.authorization.MaintenanceDocumentAuthorizerBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.util.Map;

public class SubAwardInvoiceMaintenanceDocumentAuthorizer extends MaintenanceDocumentAuthorizerBase {

    private static final long serialVersionUID = -3808004509488121973L;
    
    @Override
    public boolean canDisapprove(Document document, Person user) {
        return super.canApprove(document, user);
    }
    
    @Override
    public boolean canApprove(Document document, Person user) {
        boolean result = super.canApprove(document, user);
        //if we can approve, check to see if we have modify subaward privs and if so, disallow approving(should only be able to disapprove).
        if (result) {
            MaintenanceDocument maintDoc = (MaintenanceDocument) document;
            try {
                SubAward subAward = getBusinessObjectService().findBySinglePrimaryKey(SubAward.class, 
                        ((SubAwardAmountReleased) maintDoc.getNewMaintainableObject().getDataObject()).getSubAwardId());
                SubAwardDocument subAwardDoc;
                    subAwardDoc = (SubAwardDocument) getDocumentService().getByDocumentHeaderId(subAward.getSubAwardDocument().getDocumentNumber());
    
                result &= !getTaskAuthorizationService().isAuthorized(user.getPrincipalId(), new SubAwardTask(TaskName.MODIFY_SUBAWARD, subAwardDoc));
            } catch (WorkflowException e) {
                
            }
        }
        return result;
    }
    
    @Override
    protected void addRoleQualification(Object primaryDataObjectOrDocument, Map<String, String> attributes) {
        super.addRoleQualification(primaryDataObjectOrDocument, attributes);
        SubAwardAmountReleased invoice = (SubAwardAmountReleased) ((MaintenanceDocument)primaryDataObjectOrDocument).getNewMaintainableObject().getDataObject();
        if (invoice != null && invoice.getSubAwardId() != null) {
            attributes.put(KcKimAttributes.SUBAWARD, invoice.getSubAwardId().toString());
        }
    }
    
    
    /**
     * Get the TaskAuthorizationService.
     * @return
     */
    protected final TaskAuthorizationService getTaskAuthorizationService(){
        return (TaskAuthorizationService) KcServiceLocator.getService(TaskAuthorizationService.class);
    }    
    protected final BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }
    protected final DocumentService getDocumentService() {
        return KcServiceLocator.getService(DocumentService.class);
    }
}
