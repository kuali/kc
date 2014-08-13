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
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.personmasschange.bo.PersonMassChange;

import java.io.Serializable;

public class PersonMassChangeHelperBase implements Serializable {

    private static final long serialVersionUID = -2693177271931144987L;
    
    private transient KcPersonService kcPersonService;
    private transient RolodexService rolodexService;
    
    /**
     * Prepares the replacee fields to render the view.
     */
    public void prepareReplaceeView(PersonMassChange personMassChange, String replaceePersonId, Integer replaceeRolodexId) {
        if (StringUtils.isNotBlank(replaceePersonId)) {
            KcPerson person = getKcPersonService().getKcPersonByPersonId(replaceePersonId);
            personMassChange.setReplaceePersonId(person.getPersonId());
            personMassChange.setReplaceeFullName(person.getFullName());
            personMassChange.setReplaceeRolodexId(null);
        } else if (replaceeRolodexId != null) {
            personMassChange.setReplaceePersonId(null);
            RolodexContract rolodex = getRolodexService().getRolodex(Integer.valueOf(replaceeRolodexId));
            personMassChange.setReplaceeRolodexId(rolodex.getRolodexId());
            personMassChange.setReplaceeFullName(rolodex.getFullName());
        }
    }
    
    /**
     * Prepares the replacer fields to render the view.
     */
    public void prepareReplacerView(PersonMassChange personMassChange, String replacerPersonId, Integer replacerRolodexId) {
        if (StringUtils.isNotBlank(replacerPersonId)) {
            KcPerson person = getKcPersonService().getKcPersonByPersonId(replacerPersonId);
            personMassChange.setReplacerPersonId(person.getPersonId());
            personMassChange.setReplacerFullName(person.getFullName());
            personMassChange.setReplacerRolodexId(null);
        } else if (replacerRolodexId != null) {
            personMassChange.setReplacerPersonId(null);
            RolodexContract rolodex = getRolodexService().getRolodex(Integer.valueOf(replacerRolodexId));
            personMassChange.setReplacerRolodexId(rolodex.getRolodexId());
            personMassChange.setReplacerFullName(rolodex.getFullName());
        }
    }
    
    public KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public RolodexService getRolodexService() {
        if (rolodexService == null) {
            rolodexService = KcServiceLocator.getService(RolodexService.class);
        }
        return rolodexService;
    }
    
    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }

}
