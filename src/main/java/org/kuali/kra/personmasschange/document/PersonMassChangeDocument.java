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
package org.kuali.kra.personmasschange.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.personmasschange.bo.PersonMassChange;

public class PersonMassChangeDocument extends ResearchDocumentBase implements Serializable {

    public static final String DOCUMENT_TYPE_CODE = "PMC";
    
    private static final long serialVersionUID = 4841496352465715699L;

    private List<PersonMassChange> personMassChangeList;
    
    public PersonMassChangeDocument() {
        personMassChangeList = new ArrayList<PersonMassChange>();
        PersonMassChange newPersonMassChange = new PersonMassChange();
        newPersonMassChange.setPersonMassChangeDocument(this);
        personMassChangeList.add(newPersonMassChange);
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.document.ResearchDocumentBase#getDocumentTypeCode()
     */
    @Override
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }

    public List<PersonMassChange> getPersonMassChangeList() {
        return personMassChangeList;
    }

    public void setPersonMassChangeList(List<PersonMassChange> personMassChangeList) {
        this.personMassChangeList = personMassChangeList;
    }

    public PersonMassChange getPersonMassChange() {
        return personMassChangeList.isEmpty() ? null : personMassChangeList.get(0);
    }

    public void setPersonMassChange(PersonMassChange personMassChange) {
        personMassChangeList.set(0, personMassChange);
    }
    
}