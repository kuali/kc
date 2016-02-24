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
package org.kuali.kra.institutionalproposal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.fiscalyear.FiscalYearMonthService;
import org.kuali.coeus.common.impl.krms.KcKrmsJavaFunctionTermServiceBase;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalJavaFunctionKrmsTermService;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;

import java.util.Objects;

public class InstitutionalProposalJavaFunctionKrmsTermServiceImpl extends KcKrmsJavaFunctionTermServiceBase implements InstitutionalProposalJavaFunctionKrmsTermService {

    private FiscalYearMonthService fiscalYearMonthService;

    public Boolean isCurrentFiscalMonth(InstitutionalProposal ip) {
        String currentFiscalYear = getFiscalYearMonthService().getCurrentFiscalYear().toString();
        String currentFiscalMonth = StringUtils.leftPad(getFiscalYearMonthService().getCurrentFiscalMonth().toString(), 2, '0');
        return Objects.equals(currentFiscalMonth, ip.getFiscalMonth()) && Objects.equals(currentFiscalYear, ip.getFiscalYear());
    }

    public FiscalYearMonthService getFiscalYearMonthService() {
        return fiscalYearMonthService;
    }

    public void setFiscalYearMonthService(FiscalYearMonthService fiscalYearMonthService) {
        this.fiscalYearMonthService = fiscalYearMonthService;
    }

    public Boolean hasSpecialReviewOfType(InstitutionalProposal ip, String specialReviewType) {
        return ip.getSpecialReviews().stream().anyMatch(ipReview -> doesSpecialReviewMatch(ipReview, specialReviewType));
    }

    public boolean doesSpecialReviewMatch(InstitutionalProposalSpecialReview specialReview, String specialReviewType) {
        return (StringUtils.equals(specialReview.getSpecialReviewTypeCode(), specialReviewType) ||
                specialReview.getSpecialReviewType() != null && StringUtils.equals(specialReview.getSpecialReviewType().getDescription(), specialReviewType));
    }

}
