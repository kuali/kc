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
package org.kuali.coeus.propdev.impl.custom;

import org.kuali.coeus.common.framework.custom.CustomDataHelperBase;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.rice.kew.api.WorkflowDocument;
import java.util.List;
import java.util.Map;

public class ProposalDevelopmentCustomDataHelper extends CustomDataHelperBase<CustomAttributeDocValue> {
    
    private static final long serialVersionUID = 4399783400354474904L;
    private ProposalDevelopmentDocument document;
    
    public ProposalDevelopmentCustomDataHelper(ProposalDevelopmentDocument document) {
        this.document = document;
    }

    @Override
    protected CustomAttributeDocValue getNewCustomData() {
       CustomAttributeDocValue customAttributeDocValue = new  CustomAttributeDocValue();
       customAttributeDocValue.setDocumentNumber(document.getDocumentNumber());
       return customAttributeDocValue;
    }

    @Override
    public List<CustomAttributeDocValue> getCustomDataList() {
        return document.getCustomDataList();
    }

    @Override
    public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        return document.getCustomAttributeDocuments();
    }

    @Override
    public boolean documentNotRouted() {
        WorkflowDocument doc = document.getDocumentHeader().getWorkflowDocument();
        return doc.isSaved() || doc.isInitiated();
    }

}
