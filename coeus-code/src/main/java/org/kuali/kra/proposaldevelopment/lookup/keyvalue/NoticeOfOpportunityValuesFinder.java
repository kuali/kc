/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.NoticeOfOpportunity;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This class is the Values Finder for Notice of Opportunity.
 */
public class NoticeOfOpportunityValuesFinder extends UifKeyValuesFinderBase {

    /**
     * Returns the key/values list (code/description) for Notice of Opportunity.
     *
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        KeyValuesService keyValuesService = (KeyValuesService) KcServiceLocator.getService("keyValuesService");
        Collection noticesOfOpportunity = keyValuesService.findAll(NoticeOfOpportunity.class);
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        for (Iterator iter = noticesOfOpportunity.iterator(); iter.hasNext();) {
            NoticeOfOpportunity noticeOfOpportunity = (NoticeOfOpportunity) iter.next();
            keyValues.add(new ConcreteKeyValue(noticeOfOpportunity.getNoticeOfOpportunityCode(), noticeOfOpportunity.getDescription()));
        }
        return keyValues;
    }
}
