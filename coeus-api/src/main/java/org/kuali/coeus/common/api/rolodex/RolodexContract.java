package org.kuali.coeus.common.api.rolodex;

import org.kuali.coeus.sys.api.model.Inactivatable;

public interface RolodexContract extends Inactivatable {
    
    Integer getRolodexId();

    String getAddressLine1();

    String getAddressLine2();

    String getAddressLine3();

    String getCity();

    String getComments();

    String getCountryCode();

    String getCounty();

    Boolean getDeleteFlag();

    String getEmailAddress();

    String getFaxNumber();

    String getFirstName();

    String getLastName();

    String getMiddleName();

    String getOrganization();

    String getOwnedByUnit();

    String getPhoneNumber();

    String getPostalCode();

    String getPrefix();

    boolean getSponsorAddressFlag();

    String getSponsorCode();

    String getState();

    String getSuffix();

    String getTitle();

    String getCreateUser();

    String getFullName();
}
