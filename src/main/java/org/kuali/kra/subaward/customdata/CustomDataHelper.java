/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.subaward.customdata;

import java.util.List;
import java.util.Map;

import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.common.customattributes.CustomDataHelperBase;
import org.kuali.kra.document.ResearchDocumentBase;

/**
 * This class is using for CustomDataHelper...
 */
public class CustomDataHelper extends CustomDataHelperBase<SubAwardCustomData> {
    private static final long serialVersionUID = -2308402022153534376L;
   
    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private SubAwardForm subAwardForm;

    /**
     * Constructs a CustomDataHelper.
     * @param from the subAwardForm
     */
    public CustomDataHelper(SubAwardForm subAwardForm) {
        this.subAwardForm = subAwardForm;
    }

    /**
     * @see org.kuali.kra.common.customattributes.CustomDataHelperBase#canModifyCustomData()
     */
    @Override
    public boolean canModifyCustomData() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected SubAwardCustomData getNewCustomData() {
        return new SubAwardCustomData(); 
    }

    @Override
    public List<SubAwardCustomData> getCustomDataList() {
        return subAwardForm.getSubAward().getSubAwardCustomDataList();
    }

    @Override
    public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        return subAwardForm.getSubAwardDocument().getCustomAttributeDocuments();
    }

}
