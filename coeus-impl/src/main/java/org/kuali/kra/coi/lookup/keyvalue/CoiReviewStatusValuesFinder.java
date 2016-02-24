/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
