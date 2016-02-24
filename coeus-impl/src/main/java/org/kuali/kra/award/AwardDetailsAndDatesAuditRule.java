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
package org.kuali.kra.award;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AwardDetailsAndDatesAuditRule implements DocumentAuditRule {


    public static final String SPONSOR_CODE = "document.awardList[0].sponsorCode";
    public static final String PRIME_SPONSOR_CODE = "document.awardList[0].primeSponsorCode";
    public static final String HOME_PAGE_AUDIT_ERRORS = "homePageAuditErrors";
    public static final String HOME_PAGE_AUDIT_WARNINGS = "homePageAuditWarnings";
    public static final String EFFECTIVE_DATE = "document.awardList[0].awardEffectiveDate";

    @Override
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        Award award = ((AwardDocument)document).getAward();
        valid &= validateSponsorCode(award.getSponsorCode(), SPONSOR_CODE, KeyConstants.ERROR_AWARD_SPONSOR_ID);

        if (StringUtils.isNotEmpty(award.getPrimeSponsorCode())) {
            valid &= validateSponsorCode(award.getPrimeSponsorCode(), PRIME_SPONSOR_CODE, KeyConstants.ERROR_AWARD_PRIME_SPONSOR_ID);
        }
        validateAwardEffectiveDate(award.getAwardEffectiveDate());
        return valid;
    }

    protected void validateAwardEffectiveDate(Date awardEffectiveDate) {
        if (awardEffectiveDate == null) {
            getAuditErrors(HOME_PAGE_AUDIT_WARNINGS, Constants.AUDIT_WARNINGS).add(
                    new AuditError(EFFECTIVE_DATE, KeyConstants.WARNING_AWARD_PROJECT_START_DATE_NULL, Constants.MAPPING_AWARD_HOME_PAGE + "." + Constants.MAPPING_AWARD_HOME_DETAILS_AND_DATES_PAGE_ANCHOR));
        }
    }

    protected boolean validateSponsorCode(String sponsorCode, String errorKey, String messageKey) {
        if (!isValidSponsorCode(sponsorCode)) {
          getAuditErrors(HOME_PAGE_AUDIT_ERRORS,Constants.AUDIT_ERRORS).add(
                  new AuditError(errorKey, messageKey, Constants.MAPPING_AWARD_HOME_PAGE + "." + Constants.MAPPING_AWARD_HOME_DETAILS_AND_DATES_PAGE_ANCHOR, new String[]{sponsorCode}));

            return false;
        }
        return true;
    }

    protected boolean isValidSponsorCode(String sponsorCode) {
        return getBusinessObjectService().countMatching(Sponsor.class, Collections.singletonMap(Constants.SPONSOR_CODE, sponsorCode)) > 0;
    }

    protected List<AuditError> getAuditErrors(String key, String severity) {
        if (!getGlobalVariableService().getAuditErrorMap().containsKey(key)) {
            getGlobalVariableService().getAuditErrorMap().put(key, new AuditCluster(Constants.MAPPING_AWARD_HOME_DETAILS_AND_DATES_PAGE_NAME, new ArrayList<AuditError>(), severity));
        }
        return getGlobalVariableService().getAuditErrorMap().get(key).getAuditErrorList();

    }
    
    private GlobalVariableService getGlobalVariableService() {
        return KcServiceLocator.getService(GlobalVariableService.class);
    }

    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }


}
