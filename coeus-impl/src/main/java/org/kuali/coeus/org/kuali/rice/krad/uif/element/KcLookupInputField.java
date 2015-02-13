package org.kuali.coeus.org.kuali.rice.krad.uif.element;

import org.kuali.rice.krad.datadictionary.AttributeDefinition;
import org.kuali.rice.krad.lookup.LookupInputField;

public class KcLookupInputField extends LookupInputField {

    @Override
    public void copyFromAttributeDefinition(AttributeDefinition attributeDefinition) {
        this.setUppercaseValue(attributeDefinition.getForceUppercase());
        super.copyFromAttributeDefinition(attributeDefinition);
    }
}
