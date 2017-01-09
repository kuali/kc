package org.kuali.kra.subaward.lookup;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

import java.util.ArrayList;
import java.util.List;

public class SubAwardDocumentStatusValuesFinder extends KeyValuesBase {

    private static final long serialVersionUID = -3074955977161691637L;

    /**
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        List<KeyValue> KeyValues = new ArrayList<KeyValue>();

        for (SubAwardDocumentStatusConstants documentStatus : SubAwardDocumentStatusConstants.values()) {
            KeyValues.add(new ConcreteKeyValue(documentStatus.code(), documentStatus.description()));
        }
        return KeyValues;
    }

}
