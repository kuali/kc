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
package org.kuali.kra.personmasschange.service.impl;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.AbstractProjectPerson;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.RolodexService;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public abstract class MassPersonChangeServiceBase {
    
    protected static final String PMC_LOCKED_FIELD = "personMassChangeDocumentLocked";

    protected final ErrorReporter errorReporter = new ErrorReporter();
    
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;
    
    @Autowired
    @Qualifier("rolodexService")
    private RolodexService rolodexService;

    protected boolean isReplacerValidPersonChangeCandidate(PersonMassChange personMassChange, List<? extends AbstractProjectPerson> persons) {
        boolean isValid = true;
        for (AbstractProjectPerson person : persons) {
            if ((personMassChange.getReplacerPersonId() != null 
                        && StringUtils.equals(personMassChange.getReplacerPersonId(), person.getPersonId()))
                    || (personMassChange.getReplacerRolodexId() != null 
                        && ObjectUtils.equals(personMassChange.getReplacerRolodexId(), person.getRolodexId()))) {
                reportReplacerExists(person);
                isValid = false;
            }
        }
        return isValid;
    }
    
    protected boolean isPersonChangeCandidate(PersonMassChange personMassChange, List<? extends AbstractProjectPerson> persons, String... personRoles) {
        boolean isPersonChangeCandidate = false;
        
        for (AbstractProjectPerson person : persons) {
            if (isPersonInRole(person, personRoles)) {
                if (isPersonIdMassChange(personMassChange, person.getPersonId()) || isRolodexIdMassChange(personMassChange, person.getRolodexId())) {
                    isPersonChangeCandidate = true;
                    break;
                }
            }
        }
        
        return isPersonChangeCandidate;
    }
    
    protected boolean isPersonInRole(AbstractProjectPerson person, String... personRoles) {
        boolean isPersonInRole = false;
        
        for (String personRole : personRoles) {
            if (StringUtils.equals(person.getRoleCode(), personRole)) {
                isPersonInRole = true;
                break;
            }
        }
        
        return isPersonInRole;
    } 
    
    protected boolean isPersonIdMassChange(PersonMassChange personMassChange, String personId) {
        String replaceePersonId = personMassChange.getReplaceePersonId();
        return replaceePersonId != null && replaceePersonId.equals(personId);
    }
    
    protected boolean isRolodexIdMassChange(PersonMassChange personMassChange, Integer rolodexId) {
        Integer replaceeRolodexId = personMassChange.getReplaceeRolodexId();
        return replaceeRolodexId != null && replaceeRolodexId.equals(rolodexId);
    }    
    
    protected void reportReplacerExists(AbstractProjectPerson person) {
        errorReporter.reportWarning(getWarningKey(), KeyConstants.ERROR_PERSON_MASS_CHANGE_REPLACER_EXISTS, 
                getDocumentName(), getDocumentId(person.getParent()), person.getFullName());
    }
    
    
    /**
     * Returns the warning key for this mass person change. (ex. awardWarnings)
     * @return
     */
    protected abstract String getWarningKey();
    
    /**
     * The name for the parent we are changing. Used to display to the user. (ex. award, development proposal...)
     * @return
     */
    protected abstract String getDocumentName();
    
    /**
     * Gets the parent's id. Like the award or proposal number.
     * @return
     */
    protected abstract String getDocumentId(PersistableBusinessObject parent);
    
    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    protected KcPersonService getKcPersonService() {
        return kcPersonService;
    }
    
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    
    protected RolodexService getRolodexService() {
        return rolodexService;
    }
    
    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }
}
