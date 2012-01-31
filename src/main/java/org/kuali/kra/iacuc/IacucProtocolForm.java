/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc;

import java.util.Map;

import org.kuali.kra.common.customattributes.CustomDataForm;
import org.kuali.kra.common.customattributes.CustomDataHelperBase;
import org.kuali.kra.common.notification.web.struts.form.NotificationHelper;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsForm;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase;
import org.kuali.kra.irb.actions.ActionHelper;
import org.kuali.kra.irb.customdata.CustomDataHelper;
import org.kuali.kra.irb.noteattachment.NotesAttachmentsHelper;
import org.kuali.kra.irb.notification.IRBNotificationContext;
import org.kuali.kra.irb.onlinereview.OnlineReviewsActionHelper;
import org.kuali.kra.irb.permission.PermissionsHelper;
import org.kuali.kra.irb.personnel.PersonnelHelper;
import org.kuali.kra.irb.protocol.ProtocolHelper;
import org.kuali.kra.irb.protocol.reference.ProtocolReferenceBean;
import org.kuali.kra.irb.questionnaire.QuestionnaireHelper;
import org.kuali.kra.irb.specialreview.SpecialReviewHelper;
import org.kuali.kra.web.struts.form.Auditable;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class IacucProtocolForm extends KraTransactionalDocumentFormBase implements PermissionsForm, CustomDataForm, Auditable {
    
    private IacucProtocolHelper protocolHelper;

    /**
     * When true, the online review header will not be displayed when it is disabled.
     */
    public IacucProtocolForm() throws Exception {
        super();
//        initialize();
        this.registerEditableProperty("methodToCall");
    }
    
    public void initialize() throws Exception {
        setProtocolHelper(new IacucProtocolHelper(this));
    }

    @Override
    public String getActionName() {
        return "iacucProtocol";
    }

    /** {@inheritDoc} */
    @Override
    protected String getDefaultDocumentTypeName() {
        return "IacucProtocolDocument";
    }


    /**
     * Gets a {@link IacucProtocolDocument ProtocolDocument}.
     * @return {@link IacucProtocolDocument ProtocolDocument}
     */
    @Override
    public IacucProtocolDocument getDocument() {
        return (IacucProtocolDocument) super.getDocument();
    }

    @Override
    public boolean isAuditActivated() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setAuditActivated(boolean auditActivated) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public CustomDataHelperBase getCustomDataHelper() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PermissionsHelperBase getPermissionsHelper() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String getLockRegion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void setSaveDocumentControl(Map editMode) {
        // TODO Auto-generated method stub
        
    }

    public IacucProtocolHelper getProtocolHelper() {
        return protocolHelper;
    }

    public void setProtocolHelper(IacucProtocolHelper protocolHelper) {
        this.protocolHelper = protocolHelper;
    }

    
}
