package org.kuali.kra.external.sponsor;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.external.customercreation.CustomerConstants;
import org.kuali.kra.external.customercreation.CustomerCreationClient;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.bo.DocumentHeader;

public class SponsorMaintainableImpl extends org.kuali.coeus.common.impl.sponsor.SponsorMaintainableImpl {

    private CustomerCreationClient customerCreationClient;
	private static final long serialVersionUID = -1043877629735250382L;

	@Override
	public void doRouteStatusChange(DocumentHeader documentHeader) {
        WorkflowDocument workflowDocument = documentHeader.getWorkflowDocument();
        Sponsor sponsor = (Sponsor) getDataObject();
        sponsor.refreshReferenceObject("rolodex");
        if (workflowDocument.isProcessed() && StringUtils.equals(sponsor.getCustomerExists(), CustomerConstants.CustomerOptions.Types.NEW.getCode())) {
        	Person initiator = this.getPersonService().getPerson(workflowDocument.getInitiatorPrincipalId());
        	List<String> errors = getCustomerCreationClient().createCustomer((Sponsor) getDataObject(), initiator.getPrincipalName());
        	if (errors != null && !errors.isEmpty()) {
        		throw new RuntimeException("Error creating the remote customer from the sponsor: " + errors.get(0));
        	}
        }
        super.doRouteStatusChange(documentHeader);
	}

    public void setCustomerCreationClient(CustomerCreationClient customerCreationClient) {
        this.customerCreationClient = customerCreationClient;
    }

    public CustomerCreationClient getCustomerCreationClient() {
        if (this.customerCreationClient == null) {
            this.customerCreationClient = KcServiceLocator.getService(CustomerCreationClient.class);
        }
        return this.customerCreationClient;
    }
}

