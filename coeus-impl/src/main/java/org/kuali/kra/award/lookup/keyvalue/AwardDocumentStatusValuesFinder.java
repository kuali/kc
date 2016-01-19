package org.kuali.kra.award.lookup.keyvalue;

import org.kuali.kra.award.lookup.AwardDocumentStatusConstants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

import java.util.ArrayList;
import java.util.List;

public class AwardDocumentStatusValuesFinder extends KeyValuesBase {

    private static final long serialVersionUID = 319440830553541682L;

    /**
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        List<KeyValue> KeyValues = new ArrayList<KeyValue>();

        for (AwardDocumentStatusConstants documentStatus : AwardDocumentStatusConstants.values()) {
            KeyValues.add(new ConcreteKeyValue(documentStatus.code(), documentStatus.description()));
        }
        return KeyValues;
    }

}
