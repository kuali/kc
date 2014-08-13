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

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiNoteType;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.List;

public class CoiNoteTypeValuesFinder extends UifKeyValuesFinderBase {



    private static final long serialVersionUID = 2585370127869963041L;

        /**
         * This method returns all active note types 
         * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
         */
        @Override
        public List<KeyValue> getKeyValues() {
            List<KeyValue> keyValues = new ArrayList<KeyValue>();
            keyValues.add(new ConcreteKeyValue("", "select"));

            List<CoiNoteType> coiNoteTypes = (List<CoiNoteType>) getBusinessObjectService().findAllOrderBy(CoiNoteType.class, "SORT_ID", true);
            if (CollectionUtils.isNotEmpty(coiNoteTypes)) {
                for (CoiNoteType coiNoteType : coiNoteTypes) {
                    if (coiNoteType.isActive()) {
                        keyValues.add(new ConcreteKeyValue(coiNoteType.getNoteTypeCode(), coiNoteType.getDescription()));
                    }
                }
            }
            
            return keyValues;
        }

        /**
         * 
         * This method returns a reference to the business object service
         * @return the business object service
         */
        private BusinessObjectService getBusinessObjectService() {
            return KcServiceLocator.getService(BusinessObjectService.class);
        }    
    }