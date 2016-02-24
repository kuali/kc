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
package org.kuali.kra.award.customdata;

import org.kuali.coeus.common.framework.custom.CustomDataHelperBase;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.kew.api.WorkflowDocument;

import java.util.List;
import java.util.Map;

/**
 * The CustomDataHelper is used to manage the Custom Data tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */

public class CustomDataHelper extends CustomDataHelperBase<AwardCustomData> {


    private static final long serialVersionUID = -2308402022153534376L;   
    
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
    
    /**
     * Get the Award.
     */
    private Award getAward() {
        AwardDocument document = awardForm.getAwardDocument();
        if (document == null || document.getAward() == null) {
            throw new IllegalArgumentException("invalid (null) AwardDocument in AwardForm");
        }
        return document.getAward();
    }

    @Override
    protected AwardCustomData getNewCustomData() {
        return new AwardCustomData();
    }

    @Override
    public List<AwardCustomData> getCustomDataList() {
        return getAward().getAwardCustomDataList();
    }

    @Override
    public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        return awardForm.getAwardDocument().getCustomAttributeDocuments();
    }

    @Override
    public boolean documentNotRouted() {
        WorkflowDocument doc = awardForm.getAwardDocument().getDocumentHeader().getWorkflowDocument();
        return doc.isSaved() || doc.isInitiated();
    }

}
