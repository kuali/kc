/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.protocol;

import java.util.Map;

import org.kuali.kra.common.customattributes.CustomDataForm;
import org.kuali.kra.common.customattributes.CustomDataHelperBase;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsForm;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase;
import org.kuali.kra.protocol.protocol.ProtocolHelper;
import org.kuali.kra.questionnaire.QuestionableFormInterface;
import org.kuali.kra.web.struts.form.Auditable;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;

public abstract class ProtocolForm extends KraTransactionalDocumentFormBase implements PermissionsForm, CustomDataForm, Auditable,
        QuestionableFormInterface {

    private static final long serialVersionUID = 4646326030098259702L;

    private ProtocolHelper protocolHelper;

    public ProtocolForm() throws Exception {
        super();
        initialize();
        this.registerEditableProperty("methodToCall");
    }

    /**
     * 
     * This method initialize all form variables
     * 
     * @throws Exception
     */
    public void initialize() throws Exception {
        setProtocolHelper(createNewProtocolHelperInstanceHook(this));
    }

    protected abstract ProtocolHelper createNewProtocolHelperInstanceHook(ProtocolForm protocolForm);
    
    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) getDocument();
    }
    
    
    public void setProtocolHelper(ProtocolHelper protocolHelper) {
        this.protocolHelper = protocolHelper;
    }

    public ProtocolHelper getProtocolHelper() {
        return protocolHelper;
    }

    public String getQuestionnaireFieldStarter() {
        return "questionnaireHelper.answerHeaders[";
    }

    public String getQuestionnaireFieldMiddle() {
        return DEFAULT_MIDDLE;
    }

    public String getQuestionnaireFieldEnd() {
        return DEFAULT_END;
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
    protected void setSaveDocumentControl(Map editMode) {

    }


}
