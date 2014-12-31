package org.kuali.kra.external.customercreation;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.EnumValuesFinder;

public class CustomerExistsValuesFinder extends EnumValuesFinder {

	public CustomerExistsValuesFinder() {
		super(CustomerConstants.CustomerOptions.Types.class);
	}

	@Override
	public List<KeyValue> getKeyValues() {
		List<KeyValue> labels = super.getKeyValues();
		labels.add(0, new ConcreteKeyValue("", "Select"));
		return labels;
	}

	@Override
	protected String getEnumKey(Enum enm) {
		return ((CustomerConstants.CustomerOptions.Types)enm).getCode();
	}

	@Override
	protected String getEnumLabel(Enum enm) {
		return ((CustomerConstants.CustomerOptions.Types)enm).getName();
	}




}
