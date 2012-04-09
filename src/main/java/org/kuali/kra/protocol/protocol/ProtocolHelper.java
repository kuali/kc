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
package org.kuali.kra.protocol.protocol;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Contactable;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;
import org.kuali.kra.service.UnitService;

public class ProtocolHelper implements Serializable {

    private static final long serialVersionUID = -7963690780161657432L;

    /**
     * Each Helper must contain a reference to its document form so that it can access the actual document.
     */
    private ProtocolForm form;

    private String principalInvestigatorId;
    private String principalInvestigatorName;
    private String personId;
    private String rolodexId;

    private String lookupUnitNumber;
    private String lookupUnitName;

    private String leadUnitNumber;
    private String leadUnitName;
    private boolean nonEmployeeFlag;

    private boolean leadUnitAutoPopulated;

    private transient KcPersonService personService;
    private transient RolodexService rolodexService;


    public ProtocolHelper(ProtocolForm form) {
        this.form = form;
    }

    /**
     * This method prepares view for rendering UI. Note: Order of following methods must not be altered.
     * initializeConfigurationParams() must be before initializePermissions(getProtocol()) due to billable requirement.
     */
    public void prepareView() {
        prepareRequiredFields();
    }

    /**
     * This method either populates the protocol form fields from the BO or it propagates form fields from lookup etc. as
     * appropriate for the unsaved Protocol.
     */
    private void prepareRequiredFields() {
        Protocol theProtocol = getProtocol();
        if (theProtocol.getProtocolId() == null) {
            findPrincipalInvestigatorIdFromFields();
            findAndSetLeadUnitFromFields();
        }
        // TODO add else clause here
    }

    private Protocol getProtocol() {
        ProtocolDocument document = form.getProtocolDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
        }
        return document.getProtocol();
    }

    /**
     * This is used to calculate princiapal investigator ID from fields it's the values set rolodex id or person id depening on the
     * lookup type
     */
    private void findPrincipalInvestigatorIdFromFields() {
        if (StringUtils.isNotEmpty(getPersonId())) {
            setPrincipalInvestigatorId(getPersonId());
            setNonEmployeeFlag(false);
        }
        else if (StringUtils.isNotEmpty(getRolodexId())) {
            setPrincipalInvestigatorId(getRolodexId());
            setNonEmployeeFlag(true);
        }
    }

    /**
     * This is used to calculate lead unit info from fields it's the values set into form or (if unset during lookup) from the
     * lookup values returned for PI's home unit
     */
    private void findAndSetLeadUnitFromFields() {
        getProtocol().setLeadUnitNumber(getLeadUnitNumber());
        setLeadUnitName(getUnitService().getUnitName(getLeadUnitNumber()));
        verifyLeadUnitAutoPopulation();

        if ((StringUtils.isEmpty(getLeadUnitName()) && StringUtils.isEmpty(getLeadUnitNumber())) || isLeadUnitAutoPopulated()) {
            if (StringUtils.isNotEmpty(getLookupUnitNumber())) {
                setLeadUnitNumber(getLookupUnitNumber());
                setLeadUnitName(getLookupUnitName());
                setLeadUnitAutoPopulated(true);
            }
        }
        setLookupUnitNumber(null);
        setLookupUnitName(null);
    }
    
    private UnitService getUnitService() {
        return KraServiceLocator.getService(UnitService.class);
    }

    public void setLeadUnitAutoPopulated(boolean leadUnitAutoPopulated) {
        this.leadUnitAutoPopulated = leadUnitAutoPopulated;
    }
    
    private void verifyLeadUnitAutoPopulation() {
        if(StringUtils.isNotEmpty(getProtocol().getPrincipalInvestigatorId()) && StringUtils.isNotEmpty(getProtocol().getLeadUnitNumber())) {
            Unit piUnit = getPIUnit(getProtocol().getPrincipalInvestigatorId()) ;
            if(piUnit != null && !StringUtils.equals(piUnit.getUnitNumber(), getProtocol().getLeadUnitNumber())) {
                setLeadUnitAutoPopulated(false);
            }
        }
    }
    
    public boolean isLeadUnitAutoPopulated() {
        return leadUnitAutoPopulated;
    }
    
    private Unit getPIUnit(String piId) {
        Contactable pi = null;
        if(StringUtils.isNotBlank(piId)) {
            if(!nonEmployeeFlag) {
                pi = getPersonService().getKcPersonByPersonId(getProtocol().getPrincipalInvestigatorId());
            } else {
                pi = getRolodexService().getRolodex(Integer.parseInt(piId));
            }
        }
        return (pi == null? null : pi.getUnit());
    }
    
    private KcPersonService getPersonService() {
        if(personService == null) {
            personService = KraServiceLocator.getService(KcPersonService.class);
        }
        return personService;
    }
    
    protected RolodexService getRolodexService() {
        if (this.rolodexService == null) {
            this.rolodexService = KraServiceLocator.getService(RolodexService.class);        
        }
        return this.rolodexService;
    }

    public String getPrincipalInvestigatorId() {
        return principalInvestigatorId;
    }

    public void setPrincipalInvestigatorId(String principalInvestigatorId) {
        this.principalInvestigatorId = principalInvestigatorId;
    }

    public String getPrincipalInvestigatorName() {
        return principalInvestigatorName;
    }

    public void setPrincipalInvestigatorName(String principalInvestigatorName) {
        this.principalInvestigatorName = principalInvestigatorName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(String rolodexId) {
        this.rolodexId = rolodexId;
    }

    public String getLeadUnitNumber() {
        return leadUnitNumber;
    }

    public void setLeadUnitNumber(String leadUnitNumber) {
        this.leadUnitNumber = leadUnitNumber;
    }

    public String getLeadUnitName() {
        return leadUnitName;
    }

    public void setLeadUnitName(String leadUnitName) {
        this.leadUnitName = leadUnitName;
    }

    public boolean isNonEmployeeFlag() {
        return nonEmployeeFlag;
    }

    public void setNonEmployeeFlag(boolean nonEmployeeFlag) {
        this.nonEmployeeFlag = nonEmployeeFlag;
    }

    public String getLookupUnitName() {
        return lookupUnitName;
    }

    public void setLookupUnitName(String lookupUnitName) {
        this.lookupUnitName = lookupUnitName;
    }

    public String getLookupUnitNumber() {
        return lookupUnitNumber;
    }

    public void setLookupUnitNumber(String lookupUnitNumber) {
        this.lookupUnitNumber = lookupUnitNumber;
    }
}
