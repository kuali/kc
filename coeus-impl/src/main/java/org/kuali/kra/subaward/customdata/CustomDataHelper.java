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
package org.kuali.kra.subaward.customdata;

import org.kuali.coeus.common.framework.custom.CustomDataHelperBase;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.rice.kew.api.WorkflowDocument;

import java.util.List;
import java.util.Map;

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

    @Override
    public boolean canModifyCustomData() {

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

    @Override
    public boolean documentNotRouted() {
        WorkflowDocument doc = subAwardForm.getSubAwardDocument().getDocumentHeader().getWorkflowDocument();
        return doc.isSaved() || doc.isInitiated();
    }
}
