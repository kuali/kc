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

package org.kuali.coeus.coi.impl;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.coi.framework.DisclosureProjectStatus;
import org.kuali.kra.infrastructure.Constants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class DisclosureStatusRetrievalServiceImplTest {

    class MockDisclosureStatusRetrievalServiceImplMock extends DisclosureStatusRetrievalServiceImpl {
        DisclosureProjectStatus disclosureProjectStatus;

        public MockDisclosureStatusRetrievalServiceImplMock(DisclosureProjectStatus disclosureProjectStatus) {
           this.disclosureProjectStatus = disclosureProjectStatus;
        }

       @Override
        protected ResponseEntity<List<DisclosureProjectStatus>> getDisclosureStatusFromCoi(String url, HttpEntity<String> entity, HttpMethod method) {
           List<DisclosureProjectStatus> statuses = new ArrayList<>();
           statuses.add(disclosureProjectStatus);
            ResponseEntity<List<DisclosureProjectStatus>> responseEntity = new ResponseEntity<>(statuses, HttpStatus.OK);
            return responseEntity;
        }

        @Override
        protected String getCoiApiUrl() {
            return "https://goblins-tst.kuali.co/api/coi/project-disclosure-statuses/";
        }

        protected String getAuthToken() {
            return "Bearer " + "Unleash the Kraken!";
        }
    }

    @Test
    public void testDisclosureRetrievalService() {
        DisclosureProjectStatus disclosureProjectStatus = new DisclosureProjectStatus();
        disclosureProjectStatus.setStatus("Not yet disclosed");
        disclosureProjectStatus.setUserId("username1");
        MockDisclosureStatusRetrievalServiceImplMock mock = new MockDisclosureStatusRetrievalServiceImplMock(disclosureProjectStatus);
        List<DisclosureProjectStatus> status = mock.getDisclosureStatusesForProject(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, "1");

        Assert.assertTrue(status != null);
        Assert.assertEquals(status.get(0).getStatus(), "Not yet disclosed");
        Assert.assertEquals(status.get(0).getUserId(), "username1");

        disclosureProjectStatus = null;

        mock = new MockDisclosureStatusRetrievalServiceImplMock(disclosureProjectStatus);
        status = mock.getDisclosureStatusesForProject(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, "1");
        Assert.assertTrue(status.get(0) == null);

    }

}
