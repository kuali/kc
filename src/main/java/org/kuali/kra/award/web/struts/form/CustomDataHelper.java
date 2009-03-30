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
package org.kuali.kra.award.web.struts.form;

import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.common.customattributes.CustomDataHelperBase;

/**
 * The CustomDataHelper is used to manage the Custom Data tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public class CustomDataHelper extends CustomDataHelperBase {

    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private AwardForm awardForm;
    
    /**
     * Constructs a CustomDataHelper.
     * @param from the awardForm
     */
    public CustomDataHelper(AwardForm awardForm) {
        this.awardForm = awardForm;
    }
    
    /*
     * Get the Award.
     */
    private Award getAward() {
        AwardDocument document = awardForm.getAwardDocument();
        if (document == null || document.getAward() == null) {
            throw new IllegalArgumentException("invalid (null) AwardDocument in AwardForm");
        }
        return document.getAward();
    }
    
    /**
     * @see org.kuali.kra.common.customattributes.CustomDataHelperBase#canModifyCustomData()
     */
    @Override
    public boolean canModifyCustomData() {
        // TODO Auto-generated method stub
        return false;
    }

}
