package org.kuali.coeus.coi.impl;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.coi.framework.DisclosureProjectStatus;
import org.kuali.kra.infrastructure.Constants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DisclosureStatusRetrievalServiceImplTest {

    class MockDisclosureStatusRetrievalServiceImplMock extends DisclosureStatusRetrievalServiceImpl {
        DisclosureProjectStatus disclosureProjectStatus;

        public MockDisclosureStatusRetrievalServiceImplMock(DisclosureProjectStatus disclosureProjectStatus) {
           this.disclosureProjectStatus = disclosureProjectStatus;
        }

        @Override
        protected ResponseEntity<DisclosureProjectStatus> getDisclosureStatusFromCoi(String url, HttpEntity<String> entity, HttpMethod method) {
            ResponseEntity<DisclosureProjectStatus> responseEntity = new ResponseEntity<DisclosureProjectStatus>(disclosureProjectStatus, HttpStatus.OK);
            return responseEntity;
        }

        @Override
        protected String getCoiStandaloneBaseUrl() {
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
        DisclosureProjectStatus status = mock.getDisclosureStatusForPerson(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, "1", "username1");

        Assert.assertTrue(status != null);
        Assert.assertEquals(status.getStatus(), "Not yet disclosed");
        Assert.assertEquals(status.getUserId(), "username1");

        disclosureProjectStatus = null;

        mock = new MockDisclosureStatusRetrievalServiceImplMock(disclosureProjectStatus);
        status = mock.getDisclosureStatusForPerson(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, "1", "username1");
        Assert.assertTrue(status == null);



    }

}
