package org.kuali.kra.award.cgb;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.EnumValuesFinder;

public class InvoicingOptionsValuesFinder extends EnumValuesFinder {

	public InvoicingOptionsValuesFinder() {
		super(AwardCgbConstants.InvoicingOptions.Types.class);
	}
	
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> labels = super.getKeyValues();
        labels.add(0, new ConcreteKeyValue("", "Select"));
        return labels;
    }
	
    @Override
    protected String getEnumKey(Enum enm) {
        return ((AwardCgbConstants.InvoicingOptions.Types)enm).getCode();
    }

    @Override
    protected String getEnumLabel(Enum enm) {
        return ((AwardCgbConstants.InvoicingOptions.Types)enm).getName();
    }

}
