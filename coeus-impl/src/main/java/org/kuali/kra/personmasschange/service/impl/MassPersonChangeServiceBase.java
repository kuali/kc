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
package org.kuali.kra.personmasschange.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.bo.AbstractProjectPerson;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public abstract class MassPersonChangeServiceBase {
    
    protected static final String PMC_LOCKED_FIELD = "personMassChangeDocumentLocked";

    protected final ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
    
    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    
    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

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

	protected DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
}
