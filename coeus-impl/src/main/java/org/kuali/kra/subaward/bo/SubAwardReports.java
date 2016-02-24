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
