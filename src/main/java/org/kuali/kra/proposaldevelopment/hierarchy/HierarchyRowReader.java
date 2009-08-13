/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.hierarchy;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.apache.ojb.broker.accesslayer.RowReaderDefaultImpl;
import org.apache.ojb.broker.metadata.ClassDescriptor;

/**
 * This class...
 */
public class HierarchyRowReader extends RowReaderDefaultImpl {
    
    public HierarchyRowReader(ClassDescriptor cld) {
        super(cld);
    }

    /**
     * @see org.apache.ojb.broker.accesslayer.RowReaderDefaultImpl#selectClassDescriptor(java.util.Map)
     */
    @Override
    protected ClassDescriptor selectClassDescriptor(Map row) throws PersistenceBrokerException {
        ClassDescriptor result;
        if ((Boolean)(row.get("IS_HIERARCHY"))) {
            result = this.getClassDescriptor().getRepository().getDescriptorFor("org.kuali.kra.proposaldevelopment.hierarchy.bo.ProposalHierarchy");
        }
        else {
            result = this.getClassDescriptor().getRepository().getDescriptorFor("org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal");
        }
        return result;
    }
    


}
