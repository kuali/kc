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
package org.kuali.kra.irb.customdata;

import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.protocol.customdata.ProtocolCustomDataHelperBase;

import java.util.List;
import java.util.Map;

/**
 * The CustomDataHelper is used to manage the Custom Data tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public class CustomDataHelper extends ProtocolCustomDataHelperBase<CustomAttributeDocValue> { 
    

    private static final long serialVersionUID = 3956588282238741445L;

    /**
     * Constructs a CustomDataHelper.
     * @param form the form
     */
    public CustomDataHelper(ProtocolForm form) {
        super(form);
    }
  
    @Override
    public boolean canModifyCustomData() {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_OTHERS, (Protocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);    
    }

    @Override
    protected String getDocumentTypeCode() {
        return ProtocolDocument.DOCUMENT_TYPE_CODE;
    }

    @Override
    protected CustomAttributeDocValue getNewCustomData() {
        return new CustomAttributeDocValue();
    }

    @Override
    public List<CustomAttributeDocValue> getCustomDataList() {
        return ((ProtocolDocument) form.getProtocolDocument()).getCustomDataList();
    }

    @Override
    public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        return form.getProtocolDocument().getCustomAttributeDocuments();
    }
}
