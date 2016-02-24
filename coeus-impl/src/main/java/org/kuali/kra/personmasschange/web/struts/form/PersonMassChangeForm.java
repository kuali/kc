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
package org.kuali.kra.personmasschange.web.struts.form;

import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentFormBase;
import org.kuali.kra.personmasschange.document.PersonMassChangeDocument;
import org.kuali.rice.krad.util.KRADConstants;

import java.util.Map;

public class PersonMassChangeForm extends KcTransactionalDocumentFormBase {

    private static final long serialVersionUID = 6324968980895876372L;
    
    private PersonMassChangeHomeHelper personMassChangeHomeHelper;
    private PersonMassChangeViewHelper personMassChangeViewHelper;
    
    public PersonMassChangeForm() {
        initialize();
    }
    
    public void initialize() {
        setPersonMassChangeHomeHelper(new PersonMassChangeHomeHelper(this));
        setPersonMassChangeViewHelper(new PersonMassChangeViewHelper(this));
    }

    public PersonMassChangeHomeHelper getPersonMassChangeHomeHelper() {
        return personMassChangeHomeHelper;
    }

    public void setPersonMassChangeHomeHelper(PersonMassChangeHomeHelper personMassChangeHomeHelper) {
        this.personMassChangeHomeHelper = personMassChangeHomeHelper;
    }

    public PersonMassChangeViewHelper getPersonMassChangeViewHelper() {
        return personMassChangeViewHelper;
    }

    public void setPersonMassChangeViewHelper(PersonMassChangeViewHelper personMassChangeViewHelper) {
        this.personMassChangeViewHelper = personMassChangeViewHelper;
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
        getDocumentActions().put(KRADConstants.KUALI_ACTION_CAN_SAVE, KRADConstants.KUALI_DEFAULT_TRUE_VALUE);
    }
    
    public PersonMassChangeDocument getPersonMassChangeDocument() {
        return (PersonMassChangeDocument) super.getDocument();
    }

}
