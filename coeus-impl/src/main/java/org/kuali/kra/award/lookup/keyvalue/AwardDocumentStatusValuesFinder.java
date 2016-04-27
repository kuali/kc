package org.kuali.kra.award.lookup.keyvalue;

import org.kuali.kra.award.lookup.AwardDocumentStatusConstants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

import java.util.ArrayList;
import java.util.List;

public class AwardDocumentStatusValuesFinder extends KeyValuesBase {
    
    private static List<KeyValue> values;
    static {
        values = new ArrayList<KeyValue>();

        for (AwardDocumentStatusConstants documentStatus : AwardDocumentStatusConstants.values()) {
            values.add(new ConcreteKeyValue(documentStatus.code(), documentStatus.description()));
        }
    }
    
    public List<KeyValue> getKeyValues() {
        return values;
    }

}
