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
package org.kuali.kra.subaward.bo;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;

public class SubAwardReports extends SubAwardAssociate implements Comparable<SubAwardReports> {
    
    private String  subAwardReportId;
    private Long subAwardId;
    private String  subAwardCode;
    private Integer sequenceNumber;
    private SubAwardReportType typeCode;
    private String subAwardReportTypeCode;

    /**
     * Constructs a SubAwardReports.java.
     */
    public SubAwardReports(final SubAward subaward) {
        this.setSubAward(subaward);
    }
    
    public SubAwardReports() {
        super();
    }
    
    /**
     * Gets the subAwardId attribute. 
     * @return Returns the subAwardId.
     */
    public Long getSubAwardId() {
        return subAwardId;
    }

    /**
     * Sets the subAwardId attribute value.
     * @param subAwardId The subAwardId to set.
     */
    public void setSubAwardId(Long subAwardId) {
        this.subAwardId = subAwardId;
    }
    /**
     * Gets the subAwardCode attribute. 
     * @return Returns the subAwardCode.
     */
    public String getSubAwardCode() {
        return subAwardCode;
    }
    /**
     * Sets the subAwardCode attribute value.
     * @param subAwardCode The subAwardCode to set.
     */
    public void setSubAwardCode(String subAwardCode) {
        this.subAwardCode = subAwardCode;
    }
    
    /**
     * Gets the subAwardReportId attribute. 
     * @return Returns the subAwardReportId.
     */
    public String getSubAwardReportId() {
        return subAwardReportId;
    }

    /**
     * Sets the subAwardReportId attribute value.
     * @param subAwardReportId The subAwardReportId to set.
     */
    public void setSubAwardReportId(String subAwardReportId) {
        this.subAwardReportId = subAwardReportId;
    }

    /**
     * Gets the sequenceNumber attribute. 
     * @return Returns the sequenceNumber.
     */
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }
    /**
     * Sets the sequenceNumber attribute value.
     * @param sequenceNumber The sequenceNumber to set.
     */
    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
    /**
     * Gets the typeCode attribute. 
     * @return Returns the typeCode.
     */
    public SubAwardReportType getTypeCode() {
        return typeCode;
    }
    /**
     * Sets the typeCode attribute value.
     * @param typeCode The typeCode to set.
     */
    public void setTypeCode(SubAwardReportType typeCode) {
        this.typeCode = typeCode;
    }
    /**
     * Gets the subAwardReportTypeCode attribute. 
     * @return Returns the subAwardReportTypeCode.
     */
    public String getSubAwardReportTypeCode() {
        return subAwardReportTypeCode;
    }
    /**
     * Sets the subAwardReportTypeCode attribute value.
     * @param subAwardReportTypeCode The subAwardReportTypeCode to set.
     */
    public void setSubAwardReportTypeCode(String subAwardReportTypeCode) {
        this.subAwardReportTypeCode = subAwardReportTypeCode;
    }
    
    /**
     * 
     * This method returns the full name of the update user.
     * @return
     */
    public String getUpdateUserName() {
        Person updateUser = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName(this.getUpdateUser());
        return updateUser != null ? updateUser.getName() : this.getUpdateUser();
    }
    
    @Override
    public void resetPersistenceState() {
        this.setSubAwardReportId(null);
    }
    
    @Override
    public int compareTo(SubAwardReports o) {
        return this.getSubAwardReportId().compareTo(o.getSubAwardReportId());
       
    }
    
    /**
     * 
     * @see org.kuali.kra.bo.KraPersistableBusinessObjectBase#beforeUpdate(org.apache.ojb.broker.PersistenceBroker)
     */
    @Override
    protected void preUpdate() {
        super.preUpdate();
        if (this.getVersionNumber() == null) {
            this.setVersionNumber(new Long(0));
        }
    }

    
}
