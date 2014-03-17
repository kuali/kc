/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.coi.lookup.keyvalue;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiReviewStatus;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CoiReviewStatusValuesFinder extends UifKeyValuesFinderBase {


    private static final long serialVersionUID = 6372894146228331241L;
    private static KeyValuesService keyValuesService;

    /*
     * get list of COI review status
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        Collection<CoiReviewStatus> allCoiReviewStatus = this.getKeyValuesService().findAll(CoiReviewStatus.class);
        for (CoiReviewStatus coiReviewStatus : allCoiReviewStatus) {
            if(!coiReviewStatus.isStatusUpdatedOnlyByAction()) {
                keyValues.add(new ConcreteKeyValue(coiReviewStatus.getReviewStatusCode(), coiReviewStatus.getDescription())); 
            }
        }
        return keyValues;
    }

    /**
     * 
     * This method returns an instance of CommitteeService.
     * @return KeyValuesService
     */
    public KeyValuesService getKeyValuesService() {
        if (keyValuesService == null) {
            keyValuesService = KcServiceLocator.getService(KeyValuesService.class);
        }
        return keyValuesService;
    }

}
