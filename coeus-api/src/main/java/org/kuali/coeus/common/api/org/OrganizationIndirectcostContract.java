package org.kuali.coeus.common.api.org;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import java.util.Date;

public interface OrganizationIndirectcostContract {

    Integer getIdcNumber();

    String getOrganizationId();

    ScaleTwoDecimal getApplicableIndirectcostRate();

    Date getEndDate();

    String getIdcComment();

    Integer getIdcRateTypeCode();

    Date getRequestedDate();

    Date getStartDate();
}
