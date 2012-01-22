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
package org.kuali.kra.award.awardhierarchy.sync.service;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncLog;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncStatus;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.kew.actionrequest.ActionRequestValue;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.action.ActionRequest;
import org.kuali.rice.kew.api.action.RoutingReportCriteria;
import org.kuali.rice.kew.api.action.WorkflowDocumentActionsService;
import org.kuali.rice.kew.api.document.DocumentDetail;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.DocumentRouteHeaderValue;
import org.kuali.rice.kew.service.KEWServiceLocator;
import org.kuali.rice.kim.api.services.KimApiServiceLocator;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.springframework.util.AutoPopulatingList;

/**
 * Utility methods to help with Award Hierarchy Descendants Sync.
 */
public class AwardSyncUtilityServiceImpl implements AwardSyncUtilityService {
    
    private WorkflowDocumentActionsService workflowUtility;
    private ConfigurationService kualiConfigurationService;


    /**
     * @see org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncUtilityService#getLogsFromSaveErrors(org.kuali.kra.award.home.Award, org.kuali.kra.award.home.Award)
     */
    @SuppressWarnings("unchecked")
    public List<AwardSyncLog> getLogsFromSaveErrors(AwardSyncStatus awardStatus) {
        List<AwardSyncLog> result = new ArrayList<AwardSyncLog>();
        Map<String, AutoPopulatingList<ErrorMessage>> errors = GlobalVariables.getMessageMap().getErrorMessages();
        for (Map.Entry<String, AutoPopulatingList<ErrorMessage>> entry : errors.entrySet()) {
            Iterator<ErrorMessage> iter = entry.getValue().iterator();
            while (iter.hasNext()) {
                ErrorMessage curMessage = iter.next();
                awardStatus.addValidationLog(expandErrorString(curMessage.getErrorKey(), curMessage.getMessageParameters()), 
                        false, curMessage.getErrorKey());
            }
        }
        errors.clear();
        return result;
    }
     
    /**
     * 
     * @see org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncUtilityService#getLogsFromAuditErrors(org.kuali.kra.award.home.Award, org.kuali.kra.award.home.Award)
     */
    @SuppressWarnings("unchecked")
    public List<AwardSyncLog> getLogsFromAuditErrors(AwardSyncStatus awardStatus) {
        List<AwardSyncLog> result = new ArrayList<AwardSyncLog>();
        for (Object object : KNSGlobalVariables.getAuditErrorMap().values()) {
            AuditCluster cluster = (AuditCluster) object;
            for (AuditError error : (List<AuditError>) cluster.getAuditErrorList()) {
                awardStatus.addValidationLog(expandErrorString(error.getMessageKey(), error.getParams()), 
                        false, error.getMessageKey());
            }
        }
        KNSGlobalVariables.getAuditErrorMap().clear();
        return result;        
    }
    
    /**
     * 
     * Take the error key and expand as would happen when displaying error
     * to the client.
     * @param errorKey
     * @param params
     * @return
     */
    protected String expandErrorString(String errorKey, String[] params) {
        ConfigurationService kualiConfiguration = getKualiConfigurationService();
        String questionText = kualiConfiguration.getPropertyValueAsString(errorKey);

        for (int i = 0; i < params.length; i++) {
            questionText = StringUtils.replace(questionText, "{" + i + "}", params[i]);
        }
        return questionText;    
    }
    

    /**
     * 
     * @see org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncUtilityService#buildListForFYI(org.kuali.kra.award.document.AwardDocument)
     */
    public List<String> buildListForFYI(AwardDocument awardDocument) throws WorkflowException {

        WorkflowDocument document = awardDocument.getDocumentHeader().getWorkflowDocument();
        RoutingReportCriteria reportCriteria = RoutingReportCriteria.Builder.createByDocumentId(document.getDocumentId()).build();
        // gather the IDs for action requests that predate the simulation
        DocumentRouteHeaderValue routeHeader = KEWServiceLocator.getRouteHeaderService().getRouteHeader(document.getDocumentId());
        Set<String> preexistingActionRequestIds = getActionRequestIds(routeHeader);
        
        // run the simulation via WorkflowUtility
        DocumentDetail documentDetail = workflowUtility.executeSimulation(reportCriteria);

        // fabricate our ActionRequestValueS from the results
        List<ActionRequestValue> actionRequests = 
            reconstituteActionRequestValues(documentDetail, preexistingActionRequestIds);
        
        List<String> actionIds = new ArrayList<String>();
        for (ActionRequestValue request : actionRequests) {
            if (request.isGroupRequest()) {
                actionIds.addAll(
                        KimApiServiceLocator.getGroupService().getMemberPrincipalIds(request.getGroupId()));
            }
            if (request.isUserRequest()) {
                actionIds.add(request.getPrincipalId());
            }
        }
        return actionIds;
    } 
    
    @SuppressWarnings("unchecked")
    public boolean doKeyValuesMatch(PersistableBusinessObject object, Map<String, Object> keyValues) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, 
        InvocationTargetException, ClassNotFoundException {
        boolean matchesAll = true;
        Class clazz = object.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        for (Map.Entry<String, Object> entry : keyValues.entrySet()) {
            boolean matches = false;
            for (PropertyDescriptor propDescriptor : beanInfo.getPropertyDescriptors()) {
                if (StringUtils.equals(propDescriptor.getName(), entry.getKey())) {
                    Method getter = propDescriptor.getReadMethod();
                    Object value = getter.invoke(object);
                    if (ObjectUtils.equals(value, entry.getValue())) {
                        matches = true;
                    }
                }
            }
            matchesAll &= matches;
        }
        return matchesAll;
    }  
    
    /**
     * Finds a BO in the collection whose keys match those in the keyValue map.
     * @param items
     * @param keyValues
     * @return
     * @throws NoSuchFieldException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     */
    public PersistableBusinessObject findMatchingBo(Collection<? extends PersistableBusinessObject> items, Map<String, Object> keyValues) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        PersistableBusinessObject matchedBo = null;
        for (PersistableBusinessObject curObject : items) {
            if (doKeyValuesMatch(curObject, keyValues)) {
                matchedBo = curObject;
                break;
            }
        }
        return matchedBo;
    }
    
    /**
     * Copied from org.kuali.rice.kew.routelog.web.RouteLogAction.
     */
    @SuppressWarnings("unchecked")
    private Set<String> getActionRequestIds(DocumentRouteHeaderValue document) {
        Set<String> actionRequestIds = new HashSet<String>();

        List<ActionRequestValue> actionRequests = 
            KEWServiceLocator.getActionRequestService().findAllActionRequestsByDocumentId(document.getDocumentId());
        
        if (actionRequests != null) {
            for (ActionRequestValue actionRequest : actionRequests) {
                if (actionRequest.getActionRequestId() != null) {
                    actionRequestIds.add(actionRequest.getActionRequestId());
                }
            }
        }
        return actionRequestIds;
    }    
    
    /**
     * Copied from org.kuali.rice.kew.routelog.web.RouteLogAction.
     * This method creates ActionRequestValue objects from the DocumentDetail output from 
     * {@link WorkflowUtility#routingReport(ReportCriteriaDTO)}Report()
     * 
     * @param documentDetail contains the DTOs from which the ActionRequestValues are reconstituted
     * @return the ActionRequestValueS that have been created
     */
    protected List<ActionRequestValue> reconstituteActionRequestValues(DocumentDetail documentDetail, Set<String> preexistingActionRequestIds) {
        List<ActionRequest> actionRequestVOs = documentDetail.getActionRequests();
        List<ActionRequestValue> futureActionRequests = new ArrayList<ActionRequestValue>();
        if (actionRequestVOs != null) {
            for (ActionRequest actionRequestVO : actionRequestVOs) {
                if (actionRequestVO != null) {
                    ActionRequestValue converted = ActionRequestValue.from(actionRequestVO);
                    futureActionRequests.add(converted);
                }
            }
        }
        return futureActionRequests;
    }

    public void setWorkflowUtility(WorkflowDocumentActionsService workflowUtility) {
        this.workflowUtility = workflowUtility;
    }   

    protected ConfigurationService getKualiConfigurationService() {
        return kualiConfigurationService;
    }

    public void setKualiConfigurationService(ConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }    
}
