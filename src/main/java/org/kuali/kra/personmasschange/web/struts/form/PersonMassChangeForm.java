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
package org.kuali.kra.personmasschange.web.struts.form;

import java.util.Map;

import org.kuali.kra.personmasschange.document.PersonMassChangeDocument;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;
import org.kuali.rice.kns.util.KNSConstants;

public class PersonMassChangeForm extends KraTransactionalDocumentFormBase {

    private static final long serialVersionUID = 6324968980895876372L;
    
    private PersonMassChangeHelper personMassChangeHelper;
    
    public PersonMassChangeForm() {
        initialize();
    }
    
    public void initialize() {
        setPersonMassChangeHelper(new PersonMassChangeHelper(this));
    }

    public PersonMassChangeHelper getPersonMassChangeHelper() {
        return personMassChangeHelper;
    }

    public void setPersonMassChangeHelper(PersonMassChangeHelper personMassChangeHelper) {
        this.personMassChangeHelper = personMassChangeHelper;
    }

    @Override
    protected String getDefaultDocumentTypeName() {
        return "PersonMassChangeDocument";
    }
    
    @Override
    protected String getLockRegion() {
        return "PERSONMASSCHANGE";
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void setSaveDocumentControl(Map editMode) {
        getDocumentActions().put(KNSConstants.KUALI_ACTION_CAN_SAVE, KNSConstants.KUALI_DEFAULT_TRUE_VALUE);
    }
    
    @Override
    public PersonMassChangeDocument getDocument() {
        return (PersonMassChangeDocument) super.getDocument();
    }

}