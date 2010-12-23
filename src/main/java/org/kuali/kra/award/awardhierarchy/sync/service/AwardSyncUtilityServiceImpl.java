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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncLog;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncStatus;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.kew.actionrequest.ActionRequestValue;
import org.kuali.rice.kew.dto.ActionRequestDTO;
import org.kuali.rice.kew.dto.DTOConverter;
import org.kuali.rice.kew.dto.DocumentDetailDTO;
import org.kuali.rice.kew.dto.ReportCriteriaDTO;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.service.WorkflowUtility;
import org.kuali.rice.kim.service.KIMServiceLocator;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.TypedArrayList;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * Utility methods to help with Award Hierarchy Descendants Sync.
 */
public class AwardSyncUtilityServiceImpl implements AwardSyncUtilityService {
    
    private WorkflowUtility workflowUtility;
    private KualiConfigurationService kualiConfigurationService;


    /**
     * @see org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncUtilityService#getLogsFromSaveErrors(org.kuali.kra.award.home.Award, org.kuali.kra.award.home.Award)
     */
    @SuppressWarnings("unchecked")
    public List<AwardSyncLog> getLogsFromSaveErrors(AwardSyncStatus awardStatus) {
        List<AwardSyncLog> result = new ArrayList<AwardSyncLog>();
        Map<String, TypedArrayList> errors = GlobalVariables.getMessageMap().getErrorMessages();
        for (Map.Entry<String, TypedArrayList> entry : errors.entrySet()) {
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
        for (Object object : GlobalVariables.getAuditErrorMap().values()) {
            AuditCluster cluster = (AuditCluster) object;
            for (AuditError error : (List<AuditError>) cluster.getAuditErrorList()) {
                awardStatus.addValidationLog(expandErrorString(error.getMessageKey(), error.getParams()), 
                        false, error.getMessageKey());
            }
        }
        GlobalVariables.getAuditErrorMap().clear();
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
        KualiConfigurationService kualiConfiguration = getKualiConfigurationService();
        String questionText = kualiConfiguration.getPropertyString(errorKey);

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

        KualiWorkflowDocument document = awardDocument.getDocumentHeader().getWorkflowDocument();
        ReportCriteriaDTO reportCriteria = new ReportCriteriaDTO(document.getRouteHeaderId());
        
        // run the simulation via WorkflowUtility
        DocumentDetailDTO documentDetail = getWorkflowUtility().routingReport(reportCriteria);

        // fabricate our ActionRequestValueS from the results
        List<ActionRequestValue> actionRequests = 
            reconstituteActionRequestValues(documentDetail);
        
        List<String> actionIds = new ArrayList<String>();
        for (ActionRequestValue request : actionRequests) {
            if (request.isGroupRequest()) {
                actionIds.addAll(
                        KIMServiceLocator.getIdentityManagementService().getGroupMemberPrincipalIds(request.getGroupId()));
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
     * This method creates ActionRequestValue objects from the DocumentDetailDTO output from 
     * {@link WorkflowUtility#routingReport(ReportCriteriaDTO)}Report()
     * 
     * @param documentDetail contains the DTOs from which the ActionRequestValues are reconstituted
     * @return the ActionRequestValueS that have been created
     */
    protected List<ActionRequestValue> reconstituteActionRequestValues(DocumentDetailDTO documentDetail) {
        
        ActionRequestDTO[] actionRequestVOs = documentDetail.getActionRequests();
        List<ActionRequestValue> futureActionRequests = new ArrayList<ActionRequestValue>();
        if (actionRequestVOs != null) {
            for (ActionRequestDTO actionRequestVO : actionRequestVOs) {
                if (actionRequestVO != null) {
                    ActionRequestValue converted = DTOConverter.convertActionRequestDTO(actionRequestVO);
                    futureActionRequests.add(converted);
                }
            }
        }
        return futureActionRequests;
    }

    protected WorkflowUtility getWorkflowUtility() {
        return workflowUtility;
    }

    public void setWorkflowUtility(WorkflowUtility workflowUtility) {
        this.workflowUtility = workflowUtility;
    }

    protected KualiConfigurationService getKualiConfigurationService() {
        return kualiConfigurationService;
    }

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }    
}
