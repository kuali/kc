/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.personmasschange.bo.PersonMassChange;

public class PersonMassChangeHomeHelper extends PersonMassChangeHelperBase {

    private static final long serialVersionUID = 185913414312936865L;

    private PersonMassChangeForm form;
    
    private String replaceePersonId;
    private Integer replaceeRolodexId;
    
    private String replacerPersonId;
    private Integer replacerRolodexId;
    
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

    public Integer getReplaceeRolodexId() {
        return replaceeRolodexId;
    }

    /**
     * Sets the {@code replaceeRolodexId} while nulling the other id values.
     * 
     * @param replaceeRolodexId the replacee rolodex id
     */
    public void setReplaceeRolodexId(Integer replaceeRolodexId) {
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

    public Integer getReplacerRolodexId() {
        return replacerRolodexId;
    }

    /**
     * Sets the {@code replacerRolodexId} while nulling the other id values.
     * 
     * @param replacerRolodexId the replacer rolodex id
     */
    public void setReplacerRolodexId(Integer replacerRolodexId) {
        this.replacerPersonId = null;
        this.replacerRolodexId = replacerRolodexId;
    }
    
    /**
     * Prepares the fields to render the view.
     */
    public void prepareView() {
        PersonMassChange personMassChange = form.getPersonMassChangeDocument().getPersonMassChange();
        
        if (StringUtils.isBlank(getReplaceePersonId()) && getReplaceeRolodexId() == null) {
            if (StringUtils.isNotBlank(personMassChange.getReplaceePersonId())) {
                setReplaceePersonId(personMassChange.getReplaceePersonId());
            } else if (personMassChange.getReplaceeRolodexId() != null) {
                setReplaceeRolodexId(personMassChange.getReplaceeRolodexId());
            }
        }
        
        prepareReplaceeView(personMassChange, getReplaceePersonId(), getReplaceeRolodexId());
        
        if (StringUtils.isBlank(getReplacerPersonId()) && getReplacerRolodexId() == null) {
            if (StringUtils.isNotBlank(personMassChange.getReplacerPersonId())) {
                setReplacerPersonId(personMassChange.getReplacerPersonId());
            } else if (personMassChange.getReplacerRolodexId() != null) {
                setReplacerRolodexId(personMassChange.getReplacerRolodexId());
            }
        }
        
        prepareReplacerView(personMassChange, getReplacerPersonId(), getReplacerRolodexId());
    }

}