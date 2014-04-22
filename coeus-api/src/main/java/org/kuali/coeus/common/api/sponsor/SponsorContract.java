package org.kuali.coeus.common.api.sponsor;

import org.kuali.coeus.sys.api.model.Inactivatable;

public interface SponsorContract extends Inactivatable {

    String getSponsorCode();

    String getAcronym();

    String getAuditReportSentForFy();

    String getCageNumber();

    String getCountryCode();

    String getDodacNumber();

    String getDunAndBradstreetNumber();

    String getDunsPlusFourNumber();

    String getOwnedByUnit();

    String getPostalCode();

    Integer getRolodexId();

    String getSponsorName();

    String getState();

    String getCreateUser();

    SponsorTypeContract getSponsorType();
}
