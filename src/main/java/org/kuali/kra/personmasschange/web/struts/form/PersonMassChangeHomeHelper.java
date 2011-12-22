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

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;

public class PersonMassChangeHomeHelper implements Serializable {

    private static final long serialVersionUID = -2635806838524822236L;

    private PersonMassChangeForm form;
    
    private String replaceePersonId;
    private String replaceeRolodexId;
    
    private String replacerPersonId;
    private String replacerRolodexId;
    
    private transient KcPersonService kcPersonService;
    private transient RolodexService rolodexService;
    
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
        
        if (StringUtils.isNotBlank(getReplaceePersonId())) {
            KcPerson person = getKcPersonService().getKcPersonByPersonId(getReplaceePersonId());
            personMassChange.setReplaceePersonId(person.getPersonId());
            personMassChange.setReplaceeFullName(person.getFullName());
            personMassChange.setReplaceeRolodexId(null);
        } else if (StringUtils.isNotBlank(getReplaceeRolodexId())) {
            personMassChange.setReplaceePersonId(null);
            Rolodex rolodex = getRolodexService().getRolodex(Integer.valueOf(getReplaceeRolodexId()));
            personMassChange.setReplaceeRolodexId(rolodex.getRolodexId().toString());
            personMassChange.setReplaceeFullName(rolodex.getFullName());
        }
        
        if (StringUtils.isNotBlank(getReplacerPersonId())) {
            KcPerson person = getKcPersonService().getKcPersonByPersonId(getReplacerPersonId());
            personMassChange.setReplacerPersonId(person.getPersonId());
            personMassChange.setReplacerFullName(person.getFullName());
            personMassChange.setReplacerRolodexId(null);
        } else if (StringUtils.isNotBlank(getReplacerRolodexId())) {
            personMassChange.setReplacerPersonId(null);
            Rolodex rolodex = getRolodexService().getRolodex(Integer.valueOf(getReplacerRolodexId()));
            personMassChange.setReplacerRolodexId(rolodex.getRolodexId().toString());
            personMassChange.setReplacerFullName(rolodex.getFullName());
        }
    }
    
    public KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public RolodexService getRolodexService() {
        if (rolodexService == null) {
            rolodexService = KraServiceLocator.getService(RolodexService.class);
        }
        
        return rolodexService;
    }
    
    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }

}