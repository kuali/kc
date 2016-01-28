/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.sys.impl.workflow;


import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.rice.kew.api.action.ActionTaken;
import org.kuali.rice.kew.api.action.ActionType;
import org.kuali.rice.kew.impl.document.WorkflowDocumentServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LastActionServiceImplTest {

    private static final String KR_PRINCIPAL_ID = "1";

    @Test
    public void test_null_actions() {
        LastActionServiceImpl las = new LastActionServiceImpl();
        las.setWorkflowDocumentService(new MockWorkflowDocumentService(null));
        Assert.assertNull(las.findLastUserActionTaken("1234"));
    }

    @Test
    public void test_null_action() {
        LastActionServiceImpl las = new LastActionServiceImpl();
        las.setWorkflowDocumentService(new MockWorkflowDocumentService(Collections.singletonList(null)));
        Assert.assertNull(las.findLastUserActionTaken("1234"));
    }

    @Test
    public void test_null_first_action() {
        final ActionTaken taken = ActionTaken.Builder.create("id", "documentId", "principalId", ActionType.ACKNOWLEDGE).build();

        List<ActionTaken> ats = new ArrayList<>();
        ats.add(null);
        ats.add(taken);

        LastActionServiceImpl las = new LastActionServiceImpl();
        las.setWorkflowDocumentService(new MockWorkflowDocumentService(ats));
        Assert.assertEquals(taken, las.findLastUserActionTaken("1234"));
    }

    @Test
    public void test_kr_action() {
        final ActionTaken taken = ActionTaken.Builder.create("id", "documentId", KR_PRINCIPAL_ID, ActionType.ACKNOWLEDGE).build();

        List<ActionTaken> ats = new ArrayList<>();
        ats.add(taken);

        LastActionServiceImpl las = new LastActionServiceImpl();
        las.setWorkflowDocumentService(new MockWorkflowDocumentService(ats));
        Assert.assertNull(las.findLastUserActionTaken("1234"));
    }

    @Test
    public void test_last_action_based_on_date() {
        List<ActionTaken> ats = new ArrayList<>();

        DateTime now = DateTime.now();
        final ActionTaken.Builder takenBuilder = ActionTaken.Builder.create("id", "documentId", "principalId", ActionType.ACKNOWLEDGE);

        takenBuilder.setActionDate(now);
        takenBuilder.setPrincipalId("laterPrincipal");
        final ActionTaken later = takenBuilder.build();


        takenBuilder.setActionDate(now.minus(100));
        takenBuilder.setPrincipalId("earlierPrincipal");
        final ActionTaken earlier = takenBuilder.build();

        ats.add(later);
        ats.add(earlier);

        LastActionServiceImpl las = new LastActionServiceImpl();
        las.setWorkflowDocumentService(new MockWorkflowDocumentService(ats));
        Assert.assertEquals(later, las.findLastUserActionTaken("1234"));
    }

    static class MockWorkflowDocumentService extends WorkflowDocumentServiceImpl {

        private List<ActionTaken> actionsTaken;

        MockWorkflowDocumentService(List<ActionTaken> actionsTaken) {
            this.actionsTaken = actionsTaken;
        }

        @Override
        public List<ActionTaken> getActionsTaken(String documentId) {
            return actionsTaken;
        }
    }
}
