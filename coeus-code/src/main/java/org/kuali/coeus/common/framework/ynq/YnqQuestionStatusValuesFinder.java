/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.framework.ynq;

import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YnqQuestionStatusValuesFinder extends UifKeyValuesFinderBase {

    private static final List<KeyValue> KEY_VALUES;
    static {
        final List<KeyValue> temp = new ArrayList<>();
        temp.add(new ConcreteKeyValue("A", "Active"));
        temp.add(new ConcreteKeyValue("I", "Inactive"));
        KEY_VALUES = Collections.unmodifiableList(temp);
    }

    @Override
    public List<KeyValue> getKeyValues() {
        return KEY_VALUES;
    }
}
