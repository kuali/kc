package org.kuali.coeus.propdev.impl.core;

import junit.framework.Assert;
import org.junit.Test;
import org.kuali.rice.kew.actionrequest.ActionRequestValue;
import org.kuali.rice.kew.actiontaken.ActionTakenValue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ProposalDevelopmentSubmitControllerTest {

    public static final String BRUCE_WAYNE = "bwayne";
    public static final String CLARK_KENT = "ckent";
    public static final String PETER_PARKER = "pparker";
    public static final String ROGER_RABBIT = "rrabbit";

    class ProposalDevelopmentSubmitControllerMock extends ProposalDevelopmentSubmitController {

        @Override
        public List<ActionTakenValue> getActionsTakenByDocumentId(String documentNumber) {
            ActionTakenValue actionTakenValue1 = new ActionTakenValue();
            actionTakenValue1.setPrincipalId(BRUCE_WAYNE);
            actionTakenValue1.setActionTakenId("235");

            List<ActionTakenValue> actionTakenValues = new ArrayList<>();
            actionTakenValues.add(actionTakenValue1);
            return actionTakenValues;
        }

        @Override
        public List<ActionRequestValue> getAllActionRequestsByDocumentId(String documentNumber) {
            List<ActionRequestValue> actionRequestValues = new ArrayList<>();
            ActionRequestValue actionRequestValue1 = new ActionRequestValue();
            actionRequestValue1.setStatus("A");
            actionRequestValue1.setApprovePolicy("F");
            actionRequestValue1.setPrincipalId(BRUCE_WAYNE);
            ActionTakenValue actionTakenValue1 = new ActionTakenValue();
            actionTakenValue1.setPrincipalId(BRUCE_WAYNE);
            actionTakenValue1.setActionTakenId("235");
            actionRequestValue1.setDocumentId("1");
            actionRequestValue1.setActionTaken(actionTakenValue1);
            actionRequestValues.add(actionRequestValue1);

            ActionRequestValue actionRequestValue2 = new ActionRequestValue();
            actionRequestValue2.setStatus("A");
            actionRequestValue2.setApprovePolicy("F");
            actionRequestValue2.setPrincipalId(CLARK_KENT);
            ActionTakenValue actionTakenValue2 = new ActionTakenValue();
            actionTakenValue2.setPrincipalId(CLARK_KENT);
            actionTakenValue2.setActionTakenId("678");
            actionRequestValue2.setDocumentId("1");
            actionRequestValue2.setActionTaken(actionTakenValue2);
            actionRequestValues.add(actionRequestValue2);
            actionRequestValues.add(actionRequestValue2);

            ActionRequestValue actionRequestValue3 = new ActionRequestValue();
            actionRequestValue3.setStatus("A");
            actionRequestValue3.setApprovePolicy("F");
            actionRequestValue3.setPrincipalId(PETER_PARKER);
            ActionTakenValue actionTakenValue3 = new ActionTakenValue();
            actionTakenValue3.setPrincipalId(PETER_PARKER);
            actionTakenValue3.setActionTakenId("979");
            actionRequestValue3.setDocumentId("1");
            actionRequestValue3.setActionTaken(actionTakenValue3);
            actionRequestValues.add(actionRequestValue3);

            return actionRequestValues;
        }
    }

    @Test
    public void testGetOtherApproversForApprovedNotificationWhenFirstPolicy() {
        ProposalDevelopmentSubmitController submitController = new ProposalDevelopmentSubmitControllerMock();
        final Boolean checkFirstOrAllFlag = Boolean.TRUE;
        HashSet<String> approvers = submitController.filterApproversFromActionRequest("1", PETER_PARKER, checkFirstOrAllFlag);
        Assert.assertTrue(approvers.size() == 1);
        Assert.assertTrue(approvers.contains(CLARK_KENT));
    }

    @Test
    public void testGetRejectedNotificationWhenFirstPolicy() {
        ProposalDevelopmentSubmitController submitController = new ProposalDevelopmentSubmitControllerMock();
        final Boolean checkFirstOrAllFlag = Boolean.FALSE;
        HashSet<String> approvers = submitController.filterApproversFromActionRequest("1", PETER_PARKER, checkFirstOrAllFlag);
        Assert.assertTrue(approvers.size() == 1);
        Assert.assertTrue(approvers.contains(CLARK_KENT));
    }

    class ProposalDevelopmentSubmitControllerMock2 extends ProposalDevelopmentSubmitController {

        @Override
        public List<ActionTakenValue> getActionsTakenByDocumentId(String documentNumber) {
            ActionTakenValue actionTakenValue1 = new ActionTakenValue();
            actionTakenValue1.setPrincipalId(BRUCE_WAYNE);
            actionTakenValue1.setActionTakenId("235");

            List<ActionTakenValue> actionTakenValues = new ArrayList<>();
            actionTakenValues.add(actionTakenValue1);
            return actionTakenValues;
        }

        @Override
        public List<ActionRequestValue> getAllActionRequestsByDocumentId(String documentNumber) {
            List<ActionRequestValue> actionRequestValues = new ArrayList<>();
            ActionRequestValue actionRequestValue1 = new ActionRequestValue();
            actionRequestValue1.setStatus("A");
            actionRequestValue1.setApprovePolicy("A");
            actionRequestValue1.setPrincipalId(BRUCE_WAYNE);
            ActionTakenValue actionTakenValue1 = new ActionTakenValue();
            actionTakenValue1.setPrincipalId(BRUCE_WAYNE);
            actionTakenValue1.setActionTakenId("235");
            actionRequestValue1.setDocumentId("1");
            actionRequestValue1.setActionTaken(actionTakenValue1);
            actionRequestValues.add(actionRequestValue1);

            ActionRequestValue actionRequestValue2 = new ActionRequestValue();
            actionRequestValue2.setStatus("A");
            actionRequestValue2.setApprovePolicy("A");
            actionRequestValue2.setPrincipalId(CLARK_KENT);
            ActionTakenValue actionTakenValue2 = new ActionTakenValue();
            actionTakenValue2.setPrincipalId(CLARK_KENT);
            actionTakenValue2.setActionTakenId("678");
            actionRequestValue2.setDocumentId("1");
            actionRequestValue2.setActionTaken(actionTakenValue2);
            actionRequestValues.add(actionRequestValue2);
            actionRequestValues.add(actionRequestValue2);

            ActionRequestValue actionRequestValue3 = new ActionRequestValue();
            actionRequestValue3.setStatus("A");
            actionRequestValue3.setApprovePolicy("A");
            actionRequestValue3.setPrincipalId(PETER_PARKER);
            ActionTakenValue actionTakenValue3 = new ActionTakenValue();
            actionTakenValue3.setPrincipalId(PETER_PARKER);
            actionTakenValue3.setActionTakenId("979");
            actionRequestValue3.setDocumentId("1");
            actionRequestValue3.setActionTaken(actionTakenValue3);
            actionRequestValues.add(actionRequestValue3);

            return actionRequestValues;
        }
    }

    @Test
    public void testGetOtherApproversForApprovedNotificationWhenAllPolicy() {
        ProposalDevelopmentSubmitController submitController = new ProposalDevelopmentSubmitControllerMock2();
        final Boolean checkFirstOrAllFlag = Boolean.TRUE;
        HashSet<String> approvers = submitController.filterApproversFromActionRequest("1", PETER_PARKER, checkFirstOrAllFlag);
        Assert.assertTrue(approvers.size() == 0);
    }

    @Test
    public void testGetRejectedNotificationWhenAllPolicy() {
        ProposalDevelopmentSubmitController submitController = new ProposalDevelopmentSubmitControllerMock2();
        final Boolean checkFirstOrAllFlag = Boolean.FALSE;
        HashSet<String> approvers = submitController.filterApproversFromActionRequest("1", PETER_PARKER, checkFirstOrAllFlag);
        Assert.assertTrue(approvers.size() == 1);
        Assert.assertTrue(approvers.contains(CLARK_KENT));
    }

    @Test
    public void testGetOtherApproversForApprovedNotificationWhenAllPolicyAndDiffLoggedInUser() {
        ProposalDevelopmentSubmitController submitController = new ProposalDevelopmentSubmitControllerMock2();
        final Boolean checkFirstOrAllFlag = Boolean.TRUE;
        HashSet<String> approvers = submitController.filterApproversFromActionRequest("1", ROGER_RABBIT, checkFirstOrAllFlag);
        Assert.assertTrue(approvers.size() == 0);
    }

    @Test
    public void testGetRejectedNotificationWhenAllPolicyAndDiffLoggedInUser() {
        ProposalDevelopmentSubmitController submitController = new ProposalDevelopmentSubmitControllerMock2();
        final Boolean checkFirstOrAllFlag = Boolean.FALSE;
        HashSet<String> approvers = submitController.filterApproversFromActionRequest("1", ROGER_RABBIT, checkFirstOrAllFlag);
        Assert.assertTrue(approvers.size() == 2);
        Assert.assertTrue(approvers.contains(CLARK_KENT));
        Assert.assertTrue(approvers.contains(PETER_PARKER));
    }

}
