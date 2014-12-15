package org.kuali.kra.external.sponsor;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.external.customercreation.CustomerCreationClient;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.bo.DocumentHeader;

public class SponsorMaintainableImpl extends org.kuali.kra.bo.SponsorMaintainableImpl {

	private static final long serialVersionUID = -1043877629735250382L;

	@Override
	public void doRouteStatusChange(DocumentHeader documentHeader) {
        WorkflowDocument workflowDocument = documentHeader.getWorkflowDocument();
        Sponsor sponsor = (Sponsor) getDataObject();
        sponsor.refreshReferenceObject("rolodex");
        if (workflowDocument.isProcessed()
        		&& StringUtils.equals(sponsor.getCustomerExists(), "N")) {
        	Person initiator = this.getPersonService().getPerson(workflowDocument.getInitiatorPrincipalId());
        	List<String> errors = KraServiceLocator.getService(CustomerCreationClient.class).createCustomer((Sponsor) getDataObject(), initiator.getPrincipalName());
        	if (errors != null && !errors.isEmpty()) {
        		throw new RuntimeException("Error creating the remote customer from the sponsor: " + errors.get(0));
        	}
        }
        super.doRouteStatusChange(documentHeader);
	}
}

