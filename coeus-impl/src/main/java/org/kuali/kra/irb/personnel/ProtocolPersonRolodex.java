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
package org.kuali.kra.irb.personnel;

import org.kuali.kra.protocol.personnel.ProtocolPersonRolodexBase;

/**
 * This class represents Rolodex contact details
 * Class extends Rolodex
 * This business object is used to list contact details of external person
 * in protocol personnel tab.
 * Details not found when compared to Person object are listed here.
 */
public class ProtocolPersonRolodex extends ProtocolPersonRolodexBase {


    private static final long serialVersionUID = 6280277069306606395L;

    private String userName;

    private String officeLocation;

    @SuppressWarnings("unused")
    private String officePhone;

    private String secondaryOfficeLocation;

    private String school;

    private String primaryTitle;

    private String directoryTitle;

    private String homeUnit;

    private String pagerNumber;

    private String mobilePhoneNumber;

    private String eraCommonsUserName;

    @SuppressWarnings("unused")
    private String fullName;

    public ProtocolPersonRolodex() {
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
        this.officePhone = officePhone;
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

    public String getFullName() {
        return super.getFullName();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
