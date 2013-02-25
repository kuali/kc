/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.infrastructure;

import java.util.Map;

import org.apache.ojb.broker.PersistenceBrokerException;
import org.apache.ojb.broker.accesslayer.RowReaderDefaultImpl;
import org.apache.ojb.broker.metadata.ClassDescriptor;
import org.kuali.kra.bo.Sponsor;

public class SponsorRowReaderImpl extends RowReaderDefaultImpl {

    public SponsorRowReaderImpl(ClassDescriptor cld) {
        super(cld);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected Object buildOrRefreshObject(Map row, ClassDescriptor targetClassDescriptor, Object targetObject) {
        Object o = super.buildOrRefreshObject(row, targetClassDescriptor, targetObject);
        if (o != null) {
            Sponsor sponsor = (Sponsor) o;
            return sponsor.isActive() ? sponsor : null;
        }
        return o;
    
    }
}
