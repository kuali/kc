/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.timeandmoney.document.authorization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizerBase;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class is the Time and Money Document Authorizer.  It determines the edit modes and
 * document actions for all time and money documents.
 */
public class TimeAndMoneyDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {
     
    @Override
    protected void addRoleQualification(
            Object primaryBusinessObjectOrDocument,
            Map<String, String> attributes) {
        super.addRoleQualification(primaryBusinessObjectOrDocument, attributes);
        TimeAndMoneyDocument timeAndMoneyDocument = (TimeAndMoneyDocument) primaryBusinessObjectOrDocument;
        if (timeAndMoneyDocument.getAward() != null 
                && timeAndMoneyDocument.getAward().getLeadUnit() != null) {
            attributes.put(KcKimAttributes.UNIT_NUMBER, timeAndMoneyDocument.getAward().getLeadUnit().getUnitNumber());
        } else {
            attributes.put(KcKimAttributes.UNIT_NUMBER, "*");
        }
    }
    
    public Award getWorkingAwardVersion(String goToAwardNumber) {
        Award award = null;
        award = getPendingAwardVersion(goToAwardNumber);
        if (award == null) {
            award = getActiveAwardVersion(goToAwardNumber);
        }
        return award;
    }
    
    /*
     * This method retrieves the pending award version.
     * 
     * @param doc
     * @param goToAwardNumber
     */
    @SuppressWarnings("unchecked")
    public Award getPendingAwardVersion(String goToAwardNumber) {
        
        Award award = null;
        BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);
        List<Award> awards = (List<Award>)businessObjectService.findMatchingOrderBy(Award.class, getHashMapToFindActiveAward(goToAwardNumber), "sequenceNumber", true);
        if(!(awards.size() == 0)) {
            award = awards.get(awards.size() - 1);
        }
      
        return award;
    }
    
   
    private Award getActiveAwardVersion(String goToAwardNumber) {
        VersionHistoryService vhs = KraServiceLocator.getService(VersionHistoryService.class);  
        VersionHistory vh = vhs.findActiveVersion(Award.class, goToAwardNumber);
        Award award = null;
        
        if(vh!=null){
            award = (Award) vh.getSequenceOwner();
        }else{
            BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);
            award = ((List<Award>)businessObjectService.findMatching(Award.class, getHashMapToFindActiveAward(goToAwardNumber))).get(0);              
        }
        return award;
    }
    private Map<String, String> getHashMapToFindActiveAward(String goToAwardNumber) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("awardNumber", goToAwardNumber);
        return map;
    }
    
    
//    /**
//     * @see org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer#getEditModes(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person, java.util.Set)
//     */
//    public Set<String> getEditModes(Document document, Person user, Set<String> currentEditModes) {
//        Set<String> editModes = new HashSet<String>();
//        
//        TimeAndMoneyDocument timeAndMoneyDocument = (TimeAndMoneyDocument) document;
//        
//        if (timeAndMoneyDocument.getDocumentNumber() == null) {
//            if (canCreateTimeAndMoney(user.getPrincipalId())) {
//                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
//            } else {
//                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
//            }
//        }
//        else {
//            if (canExecuteTimeAndMoneyTask(user.getPrincipalId(), timeAndMoneyDocument, TaskName.MODIFY_TIME_AND_MONEY)) {  
//                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
//            }
//            else if (canExecuteTimeAndMoneyTask(user.getPrincipalId(), timeAndMoneyDocument, TaskName.VIEW_TIME_AND_MONEY)) {
//                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
//            }
//            else {
//                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
//            }
//        }
//        
//        return editModes;
//    }
//    
//    /**
//     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canInitiate(java.lang.String, org.kuali.rice.kim.api.identity.Person)
//     */
//    public boolean canInitiate(String documentTypeName, Person user) {
//        return canCreateTimeAndMoney(user.getPrincipalId());
//    }
//  
//    /**
//     * @see org.kuali.rice.kns.document.authorization.DocumentAuthorizer#canOpen(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
//     */
//    public boolean canOpen(Document document, Person user) {
//        TimeAndMoneyDocument timeAndMoneyDocument = (TimeAndMoneyDocument) document;
//        if (timeAndMoneyDocument.getDocumentNumber() == null) {
//            return canCreateTimeAndMoney(user.getPrincipalId());
//        }
//        return canExecuteTimeAndMoneyTask(user.getPrincipalId(), timeAndMoneyDocument, TaskName.VIEW_TIME_AND_MONEY);
//    }
//    
//    /**
//     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canEdit(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
//     */
//    @Override
//    public boolean canEdit(Document document, Person user) {
//        return canExecuteTimeAndMoneyTask(user.getPrincipalId(), (TimeAndMoneyDocument) document, TaskName.MODIFY_TIME_AND_MONEY);
//    }
//    
//    /**
//     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canSave(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
//     */
//    @Override
//    protected boolean canSave(Document document, Person user) {
//        return canEdit(document, user);
//    }
//    
//    /**
//     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canReload(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
//     */
//    @Override
//    protected boolean canReload(Document document, Person user) {
//        return canEdit(document, user);
//    }
//    
//    /**
//     * @see org.kuali.kra.authorization.KcTransactionalDocumentAuthorizerBase#canCopy(org.kuali.rice.krad.document.Document, org.kuali.rice.kim.api.identity.Person)
//     */
//    @Override
//    protected boolean canCopy(Document document, Person user) {
//        return false;
//    }
//    
//    /**
//     * Can the user approve the given document?
//     * @param document the document
//     * @param user the user
//     * @return true if the user can approve the document; otherwise false
//     */
//    @Override
//    protected boolean canApprove(Document document, Person user) {
//        return isEnroute(document) && isAuthorizedByTemplate(
//                 document,
//                 KRADConstants.KUALI_RICE_WORKFLOW_NAMESPACE,
//                 KimConstants.PermissionTemplateNames.APPROVE_DOCUMENT,
//                 user.getPrincipalId());
//    }
//    
//    /**
//     * Can the user disapprove the given document?
//     * @param document the document
//     * @param user the user
//     * @return true if the user can disapprove the document; otherwise false
//     */
//    @Override
//    protected boolean canDisapprove(Document document, Person user) {
//        return canApprove(document, user);
//    }
//    
//    /**
//     * Can the user blanket approve the given document?
//     * @param document the document
//     * @param user the user
//     * @return true if the user can blanket approve the document; otherwise false
//     */
//    @Override
//    protected boolean canBlanketApprove(Document document, Person user) {
//        return !isFinal(document) && isAuthorizedByTemplate(
//                document,
//                KRADConstants.KUALI_RICE_WORKFLOW_NAMESPACE,
//                KimConstants.PermissionTemplateNames.BLANKET_APPROVE_DOCUMENT,
//                user.getPrincipalId());
//    }
//    
//    /**
//     * Does the user have permission to create a award?
//     * @param user the user
//     * @return true if the user can create a award; otherwise false
//     */
//    private boolean canCreateTimeAndMoney(String userId) {
//        ApplicationTask task = new ApplicationTask(TaskName.CREATE_TAMD);
//        return getTaskAuthorizationService().isAuthorized(userId, task);
//    }
//    
//    /**
//     * Does the user have permission to execute the given task for a award?
//     * @param username the user's username
//     * @param doc the award document
//     * @param taskName the name of the task
//     * @return true if has permission; otherwise false
//     */
//    private boolean canExecuteTimeAndMoneyTask(String userId, TimeAndMoneyDocument doc, String taskName) {
//        TimeAndMoneyTask task = new TimeAndMoneyTask(taskName, doc);
//        return getTaskAuthorizationService().isAuthorized(userId, task);
//    }
}
