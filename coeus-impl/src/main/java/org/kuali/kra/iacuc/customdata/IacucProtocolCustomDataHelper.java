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
package org.kuali.kra.iacuc.customdata;

import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.customdata.ProtocolCustomDataHelperBase;

import java.util.List;
import java.util.Map;

/**
 * The CustomDataHelper is used to manage the Custom Data tab web page.
 * It contains the data, forms, and methods needed to render the page.
 */
public class IacucProtocolCustomDataHelper extends ProtocolCustomDataHelperBase<IacucProtocolCustomData> { 


    private static final long serialVersionUID = -5964117436714994219L;

    /**
     * Constructs a CustomDataHelper.
     * @param form the form
     */
    public IacucProtocolCustomDataHelper(IacucProtocolForm form) {
        super(form);
    }

    public boolean canModifyCustomData() {
        ProtocolTaskBase task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_OTHERS, (IacucProtocol) getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }

    protected String getDocumentTypeCode() {
        return IacucProtocolDocument.DOCUMENT_TYPE_CODE;
    }
    
    @Override
    public void prepareCustomData() {
        this.initializePermissions();        
        super.prepareCustomData();
    }

    @Override
    protected IacucProtocolCustomData getNewCustomData() {
        return new IacucProtocolCustomData();
    }
    
    @Override
    public List<IacucProtocolCustomData> getCustomDataList() {
        return ((IacucProtocol)getProtocol()).getIacucProtocolCustomDataList();
    }

    @Override
    public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        return form.getProtocolDocument().getCustomAttributeDocuments();
    }
}
