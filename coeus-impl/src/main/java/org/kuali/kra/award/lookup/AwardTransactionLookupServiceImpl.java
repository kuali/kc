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
package org.kuali.kra.award.lookup;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.*;
import java.util.stream.Collectors;

public class AwardTransactionLookupServiceImpl implements AwardTransactionLookupService {

    private static final String AWARD_NUMBER = "awardNumber";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String INITIAL = "Initial";

    private BusinessObjectService businessObjectService;
    private static final Log LOG = LogFactory.getLog(AwardTransactionLookupServiceImpl.class);
    
    @Override
    public Map<Integer, String> getApplicableTransactionIds(String awardNumber, Integer sequenceNumber) {
        if(isAuthorizedToAccess(awardNumber)){
            if (StringUtils.isNotBlank(awardNumber) && awardNumber.contains(Constants.COLON)) {
                awardNumber = StringUtils.split(awardNumber, Constants.COLON)[0];
            }
            final List<Long> transactionIds = new ArrayList<>();
            final Award award = getAwardVersion(awardNumber, sequenceNumber);

            transactionIds.addAll(award.getAwardAmountInfos().stream()
                    .filter(amountInfo -> amountInfo.getTransactionId() != null)
                    .map(AwardAmountInfo::getTransactionId).collect(Collectors.toList()));

            final Map<Integer, String> retval = new TreeMap<>(Comparator.<Integer>naturalOrder().reversed());
            transactionIds.stream().filter(id -> id != null)
                    .forEach(id -> retval.put(getAwardAmountInfoIndex(award, id), id.toString()));

            if (sequenceNumber == 1) {
                retval.put(0, INITIAL);
            }
            return retval;
        }

        return Collections.emptyMap();
    }
    
    protected int getAwardAmountInfoIndex(Award award, Long transactionId) {
        for (int i = 0; i < award.getAwardAmountInfos().size(); i++) {
            if (Objects.equals(award.getAwardAmountInfos().get(i).getTransactionId(), transactionId)) {
                return i;
            }
        }
        return 0;
    }
    
    protected Award getAwardVersion(String awardNumber, int sequenceNumber) {
        Map<String, Object> values = new HashMap<>();
        values.put(AWARD_NUMBER, awardNumber);
        values.put(SEQUENCE_NUMBER, sequenceNumber);
        Collection<Award> awards = businessObjectService.findMatching(Award.class, values);
        return awards.iterator().next();
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    /*
     * a utility method to check if dwr/ajax call really has authorization
     * 'updateProtocolFundingSource' also accessed by non ajax call
     */
    private boolean isAuthorizedToAccess(String awardNumber) {
        boolean isAuthorized = true;
        if(awardNumber.contains(Constants.COLON)){
            if (GlobalVariables.getUserSession() != null) {
                // TODO : this is a quick hack for KC 3.1.1 to provide authorization check for dwr/ajax call. dwr/ajax will be replaced by
                // jquery/ajax in rice 2.0
                String[] invalues = StringUtils.split(awardNumber, Constants.COLON);
                String docFormKey = invalues[1];
                if (StringUtils.isBlank(docFormKey)) {
                    isAuthorized = false;
                } else {
                    Object formObj = GlobalVariables.getUserSession().retrieveObject(docFormKey);
                    if (formObj == null || !(formObj instanceof AwardForm)) {
                        isAuthorized = false;
                    } else {
                        @SuppressWarnings("unchecked")
                        Map<String, String> editModes = ((AwardForm)formObj).getEditingMode();
                        isAuthorized = BooleanUtils.toBoolean(editModes.get(AuthorizationConstants.EditMode.FULL_ENTRY))
                        || BooleanUtils.toBoolean(editModes.get(AuthorizationConstants.EditMode.VIEW_ONLY));
                    }
                }
            } else {
                // TODO : it seemed that tomcat has this issue intermittently ?
                LOG.info("dwr/ajax does not have session ");
            }
        }
        return isAuthorized;
    }
}
