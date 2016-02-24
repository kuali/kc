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
package org.kuali.kra.iacuc.personnel;

import org.kuali.kra.protocol.personnel.ProtocolPersonRolodexBase;

public class IacucProtocolPersonRolodex extends ProtocolPersonRolodexBase {

    // TODO: none of this data is being mapped to any columns in any table, so its not being saved, nor being read from anywhere. 
    // it seems all of these fields, or rather all these getter/setters serve only to provide compatibility with kcperson getters/setters
    // when displaying read-only contact information for non-employee memberships.
    

    private static final long serialVersionUID = 7383899899322228494L;
    
    private String userName;

    private String officeLocation;


    private String secondaryOfficeLocation;

    private String school;

    private String primaryTitle;

    private String directoryTitle;

    private String homeUnit;

    private String pagerNumber;

    private String mobilePhoneNumber;

    private String eraCommonsUserName;


    public IacucProtocolPersonRolodex() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getOfficePhone() {
        return super.getPhoneNumber();
    }

    public void setOfficePhone(String officePhone) {
        super.setPhoneNumber(officePhone);
    }

    public String getSecondaryOfficeLocation() {
        return secondaryOfficeLocation;
    }

    public void setSecondaryOfficeLocation(String secondaryOfficeLocation) {
        this.secondaryOfficeLocation = secondaryOfficeLocation;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public String getDirectoryTitle() {
        return directoryTitle;
    }

    public void setDirectoryTitle(String directoryTitle) {
        this.directoryTitle = directoryTitle;
    }

    public String getHomeUnit() {
        return homeUnit;
    }

    public void setHomeUnit(String homeUnit) {
        this.homeUnit = homeUnit;
    }

    public String getPagerNumber() {
        return pagerNumber;
    }

    public void setPagerNumber(String pagerNumber) {
        this.pagerNumber = pagerNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getEraCommonsUserName() {
        return eraCommonsUserName;
    }

    public void setEraCommonsUserName(String eraCommonsUserName) {
        this.eraCommonsUserName = eraCommonsUserName;
    }
}
