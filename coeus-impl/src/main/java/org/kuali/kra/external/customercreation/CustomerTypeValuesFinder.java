package org.kuali.kra.external.customercreation;

import java.util.List;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

public class CustomerTypeValuesFinder extends UifKeyValuesFinderBase {

	private static final long serialVersionUID = 7559615736150621950L;
	
	private CustomerCreationClient customerCreationClient;

    @Override
    public List<KeyValue> getKeyValues() {
    	return getCustomerCreationClient().getCustomerTypes();
    }

	public CustomerCreationClient getCustomerCreationClient() {
		if (customerCreationClient == null) {
			customerCreationClient = KcServiceLocator.getService(CustomerCreationClient.class);
		}
		return customerCreationClient;
	}

	public void setCustomerCreationClient(
			CustomerCreationClient customerCreationClient) {
		this.customerCreationClient = customerCreationClient;
	}
	
}
