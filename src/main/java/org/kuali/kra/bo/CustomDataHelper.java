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
package org.kuali.kra.bo;

import org.kuali.kra.common.customattributes.CustomDataHelperBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.document.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomDataHelper extends CustomDataHelperBase<PersonCustomData> implements Serializable {

    private static final long serialVersionUID = -6829522940099878931L;
    
    private KcPersonExtendedAttributesMaintainableImpl maintainableImpl;
    private Map<String, CustomAttributeDocument> customAttributeDocuments;
    
    public CustomDataHelper(KcPersonExtendedAttributesMaintainableImpl maintainableImpl) {
        this.maintainableImpl = maintainableImpl;
        customAttributeDocuments = getCustomAttributeService().getDefaultCustomAttributeDocuments("PERS", 
                maintainableImpl.getDataObject() != null ? ((KcPersonExtendedAttributes) maintainableImpl.getDataObject()).getPersonCustomDataList() : new ArrayList<PersonCustomData>());
    }

    @Override
    protected PersonCustomData getNewCustomData() {
        return new PersonCustomData();
    }

    @Override
    public List<PersonCustomData> getCustomDataList() {
        if (maintainableImpl.getDataObject() != null) {
            return ((KcPersonExtendedAttributes) maintainableImpl.getDataObject()).getPersonCustomDataList();
        } else {
            return new ArrayList<PersonCustomData>();
        }
    }

    public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        return customAttributeDocuments;
    }

    public void setCustomAttributeDocuments(Map<String, CustomAttributeDocument> customAttributeDocuments) {
        this.customAttributeDocuments = customAttributeDocuments;
    }

    @Override
    public boolean documentNotRouted() {
        return maintainableImpl.documentNotRouted();
    }
}