/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.external.award.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.commitments.AwardFandaRateService;
import org.kuali.kra.award.home.ValidRates;
import org.kuali.kra.infrastructure.KraServiceLocator;

/**
 * Encapsulates presentation tier data/logic related to Create Account functionality.
 */
public class AccountCreationPresentationHelper implements Serializable {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7566225295106786401L;
    
    private List<ValidRates> validRateCandidates;
    private String selectedIcrRateCode;
    
    public AccountCreationPresentationHelper() {
        super();
        initialize();
    }
    
    protected void initialize() {
        this.validRateCandidates = new ArrayList<ValidRates>();
    }

    public List<ValidRates> getValidRateCandidates() {
        return validRateCandidates;
    }

    public void setValidRateCandidates(List<ValidRates> validRateCandidates) {
        this.validRateCandidates = validRateCandidates;
    }

    public String getSelectedIcrRateCode() {
        return selectedIcrRateCode;
    }

    public void setSelectedIcrRateCode(String selectedIcrRateCode) {
        this.selectedIcrRateCode = selectedIcrRateCode;
    }
    
    /**
     * Return matching Valid Rates entries for the given AwardFandaRate.
     * @param rate
     * @return
     */
    public List<ValidRates> getMatchingValidRates(AwardFandaRate rate) {
        AwardFandaRateService fandaRateService = KraServiceLocator.getService(AwardFandaRateService.class);
        List<ValidRates> validRates = fandaRateService.getValidRates(rate);
        for (Iterator<ValidRates> iter = validRates.iterator(); iter.hasNext();) {
            if (StringUtils.isBlank(iter.next().getIcrRateCode())) {
                iter.remove();
            }
        }
        return validRates;
    }

}
