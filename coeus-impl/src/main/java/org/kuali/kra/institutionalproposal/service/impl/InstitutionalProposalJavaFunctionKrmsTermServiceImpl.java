package org.kuali.kra.institutionalproposal.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.fiscalyear.FiscalYearMonthService;
import org.kuali.coeus.common.impl.krms.KcKrmsJavaFunctionTermServiceBase;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalJavaFunctionKrmsTermService;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;

public class InstitutionalProposalJavaFunctionKrmsTermServiceImpl extends KcKrmsJavaFunctionTermServiceBase implements InstitutionalProposalJavaFunctionKrmsTermService {

    private FiscalYearMonthService fiscalYearMonthService;

    public Boolean isCurrentFiscalMonth(InstitutionalProposal ip) {
        String currentFiscalMonth = getFiscalYearMonthService().getCurrentFiscalMonthForDisplay().toString();
        String currentFiscalYear = getFiscalYearMonthService().getCurrentFiscalYear().toString();
        if (currentFiscalMonth != null && currentFiscalMonth.length() == 1) {
            currentFiscalMonth = "0" + currentFiscalMonth;
        }
        if (currentFiscalMonth == null && ip.getFiscalMonth() == null) {
            return false;
        }
        if (currentFiscalYear == null && ip.getFiscalYear() == null) {
            return false;
        }
        return StringUtils.equals(currentFiscalMonth, ip.getFiscalMonth()) && StringUtils.equals(currentFiscalYear, ip.getFiscalYear());
    }

    public FiscalYearMonthService getFiscalYearMonthService() {
        return fiscalYearMonthService;
    }

    public void setFiscalYearMonthService(FiscalYearMonthService fiscalYearMonthService) {
        this.fiscalYearMonthService = fiscalYearMonthService;
    }

    public Boolean hasSpecialReviewOfType(InstitutionalProposal ip, String specialReviewType) {
        for (InstitutionalProposalSpecialReview specialReview : ip.getSpecialReviews()) {
            if (StringUtils.equals(specialReview.getSpecialReviewTypeCode(), specialReviewType)) {
                return true;
            }
            else if (specialReview.getSpecialReviewType() != null && StringUtils.equals(specialReview.getSpecialReviewType().getDescription(), specialReviewType)) {
                return true;
            }
        }
        return false;
    }

}
