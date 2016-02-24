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
