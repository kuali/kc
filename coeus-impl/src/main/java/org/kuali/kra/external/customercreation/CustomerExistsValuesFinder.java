package org.kuali.kra.external.customercreation;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

public class CustomerExistsValuesFinder extends UifKeyValuesFinderBase {

	private static final long serialVersionUID = -4517043740405273073L;

	@Override
	public List<KeyValue> getKeyValues() {
		List<KeyValue> results = new ArrayList<KeyValue>();
		results.add(new ConcreteKeyValue("Y", "Use Existing Customer"));
		results.add(new ConcreteKeyValue("N", "Create New Customer"));
		results.add(new ConcreteKeyValue("NA", "No Customer"));
		return results;
	}
}
