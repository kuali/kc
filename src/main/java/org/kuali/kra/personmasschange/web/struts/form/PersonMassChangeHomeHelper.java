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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.personmasschange.bo.PersonMassChange;

public class PersonMassChangeHomeHelper extends PersonMassChangeHelperBase {

    private static final long serialVersionUID = 185913414312936865L;

    private PersonMassChangeForm form;
    
    private String replaceePersonId;
    private String replaceeRolodexId;
    
    private String replacerPersonId;
    private String replacerRolodexId;
    
    public PersonMassChangeHomeHelper(PersonMassChangeForm form) {
        this.form = form;
    }
    
    public String getReplaceePersonId() {
        return replaceePersonId;
    }

    /**
     * Sets the {@code replaceePersonId} while nulling the other id values.
     * 
     * @param replaceePersonId the replacee person id
     */
    public void setReplaceePersonId(String replaceePersonId) {
        this.replaceePersonId = replaceePersonId;
        this.replaceeRolodexId = null;
    }

    public String getReplaceeRolodexId() {
        return replaceeRolodexId;
    }

    /**
     * Sets the {@code replaceeRolodexId} while nulling the other id values.
     * 
     * @param replaceeRolodexId the replacee rolodex id
     */
    public void setReplaceeRolodexId(String replaceeRolodexId) {
        this.replaceePersonId = null;
        this.replaceeRolodexId = replaceeRolodexId;
    }
    
    public String getReplacerPersonId() {
        return replacerPersonId;
    }

    /**
     * Sets the {@code replacerPersonId} while nulling the other id values.
     * 
     * @param replacerPersonId the replacer person id
     */
    public void setReplacerPersonId(String replacerPersonId) {
        this.replacerPersonId = replacerPersonId;
        this.replacerRolodexId = null;
    }

    public String getReplacerRolodexId() {
        return replacerRolodexId;
    }

    /**
     * Sets the {@code replacerRolodexId} while nulling the other id values.
     * 
     * @param replacerRolodexId the replacer rolodex id
     */
    public void setReplacerRolodexId(String replacerRolodexId) {
        this.replacerPersonId = null;
        this.replacerRolodexId = replacerRolodexId;
    }
    
    /**
     * Prepares the fields to render the view.
     */
    public void prepareView() {
        PersonMassChange personMassChange = form.getDocument().getPersonMassChange();
        
        if (StringUtils.isBlank(getReplaceePersonId()) && StringUtils.isBlank(getReplaceeRolodexId())) {
            if (StringUtils.isNotBlank(personMassChange.getReplaceePersonId())) {
                setReplaceePersonId(personMassChange.getReplaceePersonId());
            } else if (StringUtils.isNotBlank(personMassChange.getReplaceeRolodexId())) {
                setReplaceeRolodexId(personMassChange.getReplaceeRolodexId());
            }
        }
        
        prepareReplaceeView(personMassChange, getReplaceePersonId(), getReplaceeRolodexId());
        
        if (StringUtils.isBlank(getReplacerPersonId()) && StringUtils.isBlank(getReplacerRolodexId())) {
            if (StringUtils.isNotBlank(personMassChange.getReplacerPersonId())) {
                setReplacerPersonId(personMassChange.getReplacerPersonId());
            } else if (StringUtils.isNotBlank(personMassChange.getReplacerRolodexId())) {
                setReplacerRolodexId(personMassChange.getReplacerRolodexId());
            }
        }
        
        prepareReplacerView(personMassChange, getReplacerPersonId(), getReplacerRolodexId());
    }

}