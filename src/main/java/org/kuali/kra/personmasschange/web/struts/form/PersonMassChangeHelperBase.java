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

public class PersonMassChangeHelperBase implements Serializable {

    private static final long serialVersionUID = -2693177271931144987L;
    
    private transient KcPersonService kcPersonService;
    private transient RolodexService rolodexService;
    
    /**
     * Prepares the replacee fields to render the view.
     */
    public void prepareReplaceeView(PersonMassChange personMassChange, String replaceePersonId, String replaceeRolodexId) {
        if (StringUtils.isNotBlank(replaceePersonId)) {
            KcPerson person = getKcPersonService().getKcPersonByPersonId(replaceePersonId);
            personMassChange.setReplaceePersonId(person.getPersonId());
            personMassChange.setReplaceeFullName(person.getFullName());
            personMassChange.setReplaceeRolodexId(null);
        } else if (StringUtils.isNotBlank(replaceeRolodexId)) {
            personMassChange.setReplaceePersonId(null);
            Rolodex rolodex = getRolodexService().getRolodex(Integer.valueOf(replaceeRolodexId));
            personMassChange.setReplaceeRolodexId(rolodex.getRolodexId().toString());
            personMassChange.setReplaceeFullName(rolodex.getFullName());
        }
    }
    
    /**
     * Prepares the replacer fields to render the view.
     */
    public void prepareReplacerView(PersonMassChange personMassChange, String replacerPersonId, String replacerRolodexId) {
        if (StringUtils.isNotBlank(replacerPersonId)) {
            KcPerson person = getKcPersonService().getKcPersonByPersonId(replacerPersonId);
            personMassChange.setReplacerPersonId(person.getPersonId());
            personMassChange.setReplacerFullName(person.getFullName());
            personMassChange.setReplacerRolodexId(null);
        } else if (StringUtils.isNotBlank(replacerRolodexId)) {
            personMassChange.setReplacerPersonId(null);
            Rolodex rolodex = getRolodexService().getRolodex(Integer.valueOf(replacerRolodexId));
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
