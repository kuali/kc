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
package org.kuali.coeus.propdev.impl.core;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.kuali.rice.kew.actionrequest.ActionRequestValue;
import org.kuali.rice.kew.actionrequest.service.ActionRequestService;
import org.kuali.rice.kew.actiontaken.ActionTakenValue;
import org.kuali.rice.kew.api.action.ActionRequestStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ProposalDevelopmentSubmitControllerTest {

    private static final String TONY_STARK = "tstark";
	public static final String BRUCE_WAYNE = "bwayne";
    public static final String CLARK_KENT = "ckent";
    public static final String PETER_PARKER = "pparker";
    public static final String ROGER_RABBIT = "rrabbit";
    
    protected ProposalDevelopmentSubmitController submitController;
    protected ActionRequestService actionRequestService;
    
    @Before
    public void setup() {
    	submitController = new ProposalDevelopmentSubmitController();
    	actionRequestService = mock(ActionRequestService.class);
    	submitController.setActionRequestService(actionRequestService);
    }
	
	ActionRequestValue addDelegate(ActionRequestValue actionRequestValue, String principalId, boolean primaryDelegate) {
		final ActionRequestValue delegateActionRequestValue = buildActionRequestValue(principalId, "A", "F", actionRequestValue.getDocumentId());
		delegateActionRequestValue.setDelegationTypeCode(primaryDelegate ? "P" : "S");
		actionRequestValue.getChildrenRequests().add(delegateActionRequestValue);
		delegateActionRequestValue.setParentActionRequest(actionRequestValue);
		return delegateActionRequestValue;
	}
	
	ActionRequestValue getRoleRequest(String roleName, String approvePolicy, String documentId, List<String> memberIds) {
		ActionRequestValue parentRequest = new ActionRequestValue();
		parentRequest.setStatus("A");
		parentRequest.setApprovePolicy(approvePolicy);
		parentRequest.setRoleName(roleName);
		parentRequest.setChildrenRequests(memberIds.stream().map(id -> addParentRequest(buildActionRequestValue(id, "A", approvePolicy, documentId), parentRequest)).collect(Collectors.toList()));
		return parentRequest;
	}
    
    public ActionRequestValue buildActionRequestValue(String principalId, String status, String approvePolicy, String documentId) {
        ActionRequestValue actionRequestValue = new ActionRequestValue();
        actionRequestValue.setStatus(status);
        actionRequestValue.setApprovePolicy(approvePolicy);
        actionRequestValue.setPrincipalId(principalId);
        ActionTakenValue actionTakenValue = new ActionTakenValue();
        actionTakenValue.setPrincipalId(principalId);
        actionTakenValue.setActionTakenId(String.valueOf((Math.random()*10000)));
        actionRequestValue.setDocumentId(documentId);
        actionRequestValue.setActionTaken(actionTakenValue);
        return actionRequestValue;
    }
    
    public ActionRequestValue addParentRequest(ActionRequestValue actionRequest, ActionRequestValue parentRequest) {
    	actionRequest.setParentActionRequest(parentRequest);
    	return actionRequest;
    }
    
    @Test
    public void testFilterApproversFromActionRequest_multiple_unique() {
        when(actionRequestService.findAllActionRequestsByDocumentId(any(String.class)))
        	.thenReturn(Arrays.asList(buildActionRequestValue(CLARK_KENT, "A", "F", "1"),
        		buildActionRequestValue(BRUCE_WAYNE, "A", "A", "1"),
        		buildActionRequestValue(PETER_PARKER, "A", "A", "1")));
        HashSet<String> approvers = submitController.getRelatedApproversFromActionRequest("1", PETER_PARKER);
        assertEquals(0, approvers.size());
    }
    
    @Test
    public void testFilterApproversFromActionRequest_multiple_with_primary_delegate() {
        final ActionRequestValue actionRequestWithDelegate = buildActionRequestValue(CLARK_KENT, "A", "F", "1");
        final ActionRequestValue delegateRequest = addDelegate(actionRequestWithDelegate, ROGER_RABBIT, true);
		final List<ActionRequestValue> actionRequests = Arrays.asList(actionRequestWithDelegate, 
				delegateRequest,
				buildActionRequestValue(BRUCE_WAYNE, "A", "A", "1"),
				buildActionRequestValue(PETER_PARKER, "A", "A", "1"));
		when(actionRequestService.findAllActionRequestsByDocumentId(any(String.class)))
        	.thenReturn(actionRequests);
        HashSet<String> approvers = submitController.getRelatedApproversFromActionRequest("1", CLARK_KENT);
        assertEquals(1, approvers.size());
        assertEquals(delegateRequest.getPrincipalId(), approvers.stream().findFirst().orElse(null));
    }

    @Test
    public void testFilterApproversFromActionRequest_multiple_with_secondary_delegate() {
        final ActionRequestValue actionRequestWithDelegate = buildActionRequestValue(CLARK_KENT, "A", "F", "1");
        final ActionRequestValue delegateRequest = addDelegate(actionRequestWithDelegate, ROGER_RABBIT, false);
        final ActionRequestValue secondDelegateRequest = addDelegate(actionRequestWithDelegate, TONY_STARK, false);
		final List<ActionRequestValue> actionRequests = Arrays.asList(actionRequestWithDelegate, 
				delegateRequest,
				secondDelegateRequest,
				buildActionRequestValue(BRUCE_WAYNE, "A", "A", "1"),
				buildActionRequestValue(PETER_PARKER, "A", "A", "1"));
		when(actionRequestService.findAllActionRequestsByDocumentId(any(String.class)))
        	.thenReturn(actionRequests);
        HashSet<String> approvers = submitController.getRelatedApproversFromActionRequest("1", CLARK_KENT);
        assertEquals(2, approvers.size());
        assertTrue(approvers.contains(delegateRequest.getPrincipalId()));
        assertTrue(approvers.contains(secondDelegateRequest.getPrincipalId()));
    }
    
    @Test
    public void testFilterApproversFromActionRequest_multiple_with_role_first_with_delegate_approval() {
        final ActionRequestValue actionRequestWithDelegate = buildActionRequestValue(CLARK_KENT, "A", "F", "1");
        addDelegate(actionRequestWithDelegate, ROGER_RABBIT, false);
        addDelegate(actionRequestWithDelegate, TONY_STARK, false);
        final ActionRequestValue roleRequest = getRoleRequest("Test Role", "F", "1", Arrays.asList(BRUCE_WAYNE, PETER_PARKER));
        actionRequestWithDelegate.setParentActionRequest(roleRequest);
        roleRequest.getChildrenRequests().add(actionRequestWithDelegate);
		final List<ActionRequestValue> actionRequests = new ArrayList<>(roleRequest.getChildrenRequests());
		actionRequests.add(roleRequest);
		actionRequests.addAll(actionRequestWithDelegate.getChildrenRequests());

		when(actionRequestService.findAllActionRequestsByDocumentId(any(String.class)))
        	.thenReturn(actionRequests);
        HashSet<String> approvers = submitController.getRelatedApproversFromActionRequest("1", ROGER_RABBIT);
        assertEquals(4, approvers.size());
        assertTrue(approvers.contains(CLARK_KENT));
        assertTrue(approvers.contains(TONY_STARK));
        assertTrue(approvers.contains(BRUCE_WAYNE));
        assertTrue(approvers.contains(PETER_PARKER));
    }
    
    @Test
    public void testFilterApproversFromActionRequest_multiple_with_role_first_with_other_delegates() {
        final ActionRequestValue actionRequestWithDelegate = buildActionRequestValue(CLARK_KENT, "A", "F", "1");
        addDelegate(actionRequestWithDelegate, ROGER_RABBIT, false);
        addDelegate(actionRequestWithDelegate, TONY_STARK, false);
        final ActionRequestValue roleRequest = getRoleRequest("Test Role", "F", "1", Arrays.asList(BRUCE_WAYNE, PETER_PARKER));
        actionRequestWithDelegate.setParentActionRequest(roleRequest);
        roleRequest.getChildrenRequests().add(actionRequestWithDelegate);
		final List<ActionRequestValue> actionRequests = new ArrayList<>(roleRequest.getChildrenRequests());
		actionRequests.add(roleRequest);
		actionRequests.addAll(actionRequestWithDelegate.getChildrenRequests());

		when(actionRequestService.findAllActionRequestsByDocumentId(any(String.class)))
        	.thenReturn(actionRequests);
        HashSet<String> approvers = submitController.getRelatedApproversFromActionRequest("1", BRUCE_WAYNE);
        assertEquals(4, approvers.size());
        assertTrue(approvers.contains(ROGER_RABBIT));
        assertTrue(approvers.contains(TONY_STARK));
        assertTrue(approvers.contains(CLARK_KENT));
        assertTrue(approvers.contains(PETER_PARKER));
        assertFalse(approvers.contains(BRUCE_WAYNE));
    }
    
    @Test
    public void testFilterApproversFromActionRequest_multiple_with_role_all_no_delegates() {
        final ActionRequestValue actionRequestWithDelegate = buildActionRequestValue(CLARK_KENT, "A", "A", "1");
        addDelegate(actionRequestWithDelegate, ROGER_RABBIT, false);
        addDelegate(actionRequestWithDelegate, TONY_STARK, false);
        final ActionRequestValue roleRequest = getRoleRequest("Test Role", "A", "1", Arrays.asList(BRUCE_WAYNE, PETER_PARKER));
        actionRequestWithDelegate.setParentActionRequest(roleRequest);
        roleRequest.getChildrenRequests().add(actionRequestWithDelegate);
		final List<ActionRequestValue> actionRequests = new ArrayList<>(roleRequest.getChildrenRequests());
		actionRequests.add(roleRequest);
		actionRequests.addAll(actionRequestWithDelegate.getChildrenRequests());

		when(actionRequestService.findAllActionRequestsByDocumentId(any(String.class)))
        	.thenReturn(actionRequests);
        HashSet<String> approvers = submitController.getRelatedApproversFromActionRequest("1", BRUCE_WAYNE);
        assertEquals(0, approvers.size());
    }
    
    @Test
    public void testFilterApproversFromActionRequest_multiple_with_role_all_delegates() {
        final ActionRequestValue actionRequestWithDelegate = buildActionRequestValue(CLARK_KENT, "A", "A", "1");
        addDelegate(actionRequestWithDelegate, ROGER_RABBIT, false);
        addDelegate(actionRequestWithDelegate, TONY_STARK, false);
        final ActionRequestValue roleRequest = getRoleRequest("Test Role", "A", "1", Arrays.asList(BRUCE_WAYNE, PETER_PARKER));
        actionRequestWithDelegate.setParentActionRequest(roleRequest);
        roleRequest.getChildrenRequests().add(actionRequestWithDelegate);
		final List<ActionRequestValue> actionRequests = new ArrayList<>(roleRequest.getChildrenRequests());
		actionRequests.add(roleRequest);
		actionRequests.addAll(actionRequestWithDelegate.getChildrenRequests());

		when(actionRequestService.findAllActionRequestsByDocumentId(any(String.class)))
        	.thenReturn(actionRequests);
        HashSet<String> approvers = submitController.getRelatedApproversFromActionRequest("1", CLARK_KENT);
        assertEquals(2, approvers.size());
        assertTrue(approvers.contains(ROGER_RABBIT));
        assertTrue(approvers.contains(TONY_STARK));
    }
    
    @Test
    public void testAllCurrentApprovers() {
        final ActionRequestValue actionRequestWithDelegate = buildActionRequestValue(CLARK_KENT, "A", "A", "1");
        addDelegate(actionRequestWithDelegate, ROGER_RABBIT, false);
        addDelegate(actionRequestWithDelegate, TONY_STARK, false);
        final ActionRequestValue roleRequest = getRoleRequest("Test Role", "A", "1", Arrays.asList(BRUCE_WAYNE, PETER_PARKER));
        actionRequestWithDelegate.setParentActionRequest(roleRequest);
        roleRequest.getChildrenRequests().add(actionRequestWithDelegate);
		final List<ActionRequestValue> actionRequests = new ArrayList<>(roleRequest.getChildrenRequests());
		actionRequests.add(roleRequest);
		actionRequests.addAll(actionRequestWithDelegate.getChildrenRequests());

		when(actionRequestService.findAllActionRequestsByDocumentId(any(String.class)))
        	.thenReturn(actionRequests);
        HashSet<String> approvers = submitController.getAllCurrentApprovers("1", BRUCE_WAYNE);
        assertEquals(4, approvers.size());
        assertTrue(approvers.contains(ROGER_RABBIT));
        assertTrue(approvers.contains(TONY_STARK));
        assertTrue(approvers.contains(CLARK_KENT));
        assertTrue(approvers.contains(PETER_PARKER));
        assertFalse(approvers.contains(BRUCE_WAYNE));
    }
    
    @Test
    public void testAllCurrentApprovers_alreadyApprovedRequets() {
        final ActionRequestValue actionRequestWithDelegate = buildActionRequestValue(CLARK_KENT, "A", "A", "1");
        addDelegate(actionRequestWithDelegate, ROGER_RABBIT, false);
        addDelegate(actionRequestWithDelegate, TONY_STARK, false);
        final ActionRequestValue roleRequest = getRoleRequest("Test Role", "A", "1", Arrays.asList(BRUCE_WAYNE, PETER_PARKER));
        actionRequestWithDelegate.setParentActionRequest(roleRequest);
        roleRequest.getChildrenRequests().add(actionRequestWithDelegate);
		final List<ActionRequestValue> actionRequests = new ArrayList<>(roleRequest.getChildrenRequests());
		actionRequests.add(roleRequest);
		actionRequests.addAll(actionRequestWithDelegate.getChildrenRequests());
		actionRequestWithDelegate.setStatus(ActionRequestStatus.DONE.getCode());
		actionRequestWithDelegate.getChildrenRequests().forEach(actionRequest -> actionRequest.setStatus(ActionRequestStatus.DONE.getCode()));

		when(actionRequestService.findAllActionRequestsByDocumentId(any(String.class)))
        	.thenReturn(actionRequests);
        HashSet<String> approvers = submitController.getAllCurrentApprovers("1", BRUCE_WAYNE);
        assertEquals(1, approvers.size());
        assertTrue(approvers.contains(PETER_PARKER));
    }
    
    protected List<ActionRequestValue> getActionRequestValuesFirstApprove() {
    	return Arrays.asList(buildActionRequestValue(CLARK_KENT, "A", "F", "1"),
        		buildActionRequestValue(BRUCE_WAYNE, "A", "A", "1"),
        		buildActionRequestValue(PETER_PARKER, "A", "A", "1"));
    }

    @Test
    public void testGetRejectedNotificationWhenFirstPolicy() {
        when(actionRequestService.findAllActionRequestsByDocumentId(any(String.class))).thenReturn(getActionRequestValuesFirstApprove());
        HashSet<String> approvers = submitController.getAllCurrentApprovers("1", PETER_PARKER);
        assertEquals(2, approvers.size());
    }

    @Test
    public void testGetOtherApproversForApprovedNotificationWhenAllPolicy() {
        when(actionRequestService.findAllActionRequestsByDocumentId(any(String.class))).thenReturn(getActionRequestValuesAllApprove());
        HashSet<String> approvers = submitController.getRelatedApproversFromActionRequest("1", PETER_PARKER);
        assertEquals(0, approvers.size());
    }

	List<ActionRequestValue> getActionRequestValuesAllApprove() {
		return Arrays.asList(buildActionRequestValue(CLARK_KENT, "A", "A", "1"),
        buildActionRequestValue(BRUCE_WAYNE, "A", "A", "1"),
        buildActionRequestValue(PETER_PARKER, "A", "A", "1"));
	}

    @Test
    public void testGetRejectedNotificationWhenAllPolicy() {
        when(actionRequestService.findAllActionRequestsByDocumentId(any(String.class))).thenReturn(getActionRequestValuesAllApprove());
        submitController.setActionRequestService(actionRequestService);
        HashSet<String> approvers = submitController.getAllCurrentApprovers("1", PETER_PARKER);
        assertEquals(2, approvers.size());
        assertTrue(approvers.contains(CLARK_KENT));
        assertTrue(approvers.contains(BRUCE_WAYNE));
    }

    @Test
    public void testGetRejectedNotificationWhenAllPolicyAndDiffLoggedInUser() {
        when(actionRequestService.findAllActionRequestsByDocumentId(any(String.class))).thenReturn(getActionRequestValuesAllApprove());
        HashSet<String> approvers = submitController.getAllCurrentApprovers("1", ROGER_RABBIT);
        assertEquals(3, approvers.size());
        assertTrue(approvers.contains(CLARK_KENT));
        assertTrue(approvers.contains(PETER_PARKER));
    }

}
