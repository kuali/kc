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
package org.kuali.kra.budget.core;

import gov.grants.apply.forms.rrKeyPersonExpandedV10.PersonProfileDataType;

import java.util.Map;

import org.apache.ojb.broker.PersistenceBrokerException;
import org.apache.ojb.broker.accesslayer.RowReaderDefaultImpl;
import org.apache.ojb.broker.metadata.ClassDescriptor;
import org.apache.ojb.broker.metadata.ObjectReferenceDescriptor;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * This class...
 */
public class BudgetParentRowReaderImpl extends RowReaderDefaultImpl {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -633262608528458894L;

    private ClassDescriptor cld;
    public BudgetParentRowReaderImpl(ClassDescriptor cld) {
        super(cld);
        this.cld = cld;
    }
    @Override
    protected ClassDescriptor selectClassDescriptor(Map row) throws PersistenceBrokerException {
        
        String parentDocumentTypeCode = (String) row.get("PARENT_DOCUMENT_TYPE_CODE");
        ObjectReferenceDescriptor referenceDescriptor = cld.getObjectReferenceDescriptorByName("parentDocument");
        ClassDescriptor classDescriptor = referenceDescriptor.getClassDescriptor();
        ClassDescriptor result = null;
        if(ProposalDevelopmentDocument.DOCUMENT_TYPE_CODE.equals(parentDocumentTypeCode)){
//            classDescriptor.setClassOfObject(ProposalDevelopmentDocument.class);
//            result = cld.getRepository().getDescriptorFor(ProposalDevelopmentDocument.class);
        }
        if(AwardDocument.DOCUMENT_TYPE_CODE.equals(parentDocumentTypeCode)){
//            classDescriptor.setClassOfObject(AwardDocument.class);
//            result = cld.getRepository().getDescriptorFor(AwardDocument.class);
        }
//        referenceDescriptor.setClassDescriptor(result);
        return cld;
    }
}
