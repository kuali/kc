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
package org.kuali.kra.award.awardhierarchy.sync.service;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.validation.ErrorHandlingUtilService;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncLog;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncStatus;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.Constants;
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
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.util.ErrorMessage;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Utility methods to help with Award Hierarchy Descendants Sync.
 */
public class AwardSyncUtilityServiceImpl implements AwardSyncUtilityService {
    
    private WorkflowDocumentActionsService workflowUtility;
    private ConfigurationService kualiConfigurationService;
    private ErrorHandlingUtilService errorHandlingUtilService;


    @SuppressWarnings("unchecked")
    public List<AwardSyncLog> getLogsFromSaveErrors(AwardSyncStatus awardStatus) {
        List<AwardSyncLog> result = new ArrayList<AwardSyncLog>();
        Map<String, ? extends List<ErrorMessage>> errors = GlobalVariables.getMessageMap().getErrorMessages();
        for (Map.Entry<String, ? extends List<ErrorMessage>> entry : errors.entrySet()) {
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
     
    @Override
    @SuppressWarnings("unchecked")
    public List<AwardSyncLog> getLogsFromAuditErrors(AwardSyncStatus awardStatus) {
        List<AwardSyncLog> result = new ArrayList<AwardSyncLog>();
        for (Object object : GlobalVariables.getAuditErrorMap().values()) {
            AuditCluster cluster = (AuditCluster) object;
            boolean logSuccess = StringUtils.equals(cluster.getCategory(), Constants.AUDIT_WARNINGS);
            for (AuditError error : (List<AuditError>) cluster.getAuditErrorList()) {
                awardStatus.addValidationLog(expandErrorString(error.getMessageKey(), error.getParams()), 
                        logSuccess, error.getMessageKey());
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
    	return errorHandlingUtilService.resolveErrorKey(errorKey, params);
    }
    

    @Override
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
        throws NoSuchFieldException, IllegalAccessException, 
        InvocationTargetException, ClassNotFoundException {
        boolean matchesAll = true;
        Class clazz = object.getClass();
        for (Map.Entry<String, Object> entry : keyValues.entrySet()) {
            boolean matches = false;
            for (PropertyDescriptor propDescriptor : PropertyUtils.getPropertyDescriptors(clazz)) {
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
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     */
    public PersistableBusinessObject findMatchingBo(Collection<? extends PersistableBusinessObject> items, Map<String, Object> keyValues) 
        throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
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

	public ErrorHandlingUtilService getErrorHandlingUtilService() {
		return errorHandlingUtilService;
	}

	public void setErrorHandlingUtilService(
			ErrorHandlingUtilService errorHandlingUtilService) {
		this.errorHandlingUtilService = errorHandlingUtilService;
	}    
}
