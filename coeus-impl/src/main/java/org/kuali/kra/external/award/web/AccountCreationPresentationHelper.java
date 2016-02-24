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
package org.kuali.kra.external.award.web;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.commitments.AwardFandaRateService;
import org.kuali.kra.award.home.ValidRates;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Encapsulates presentation tier data/logic related to Create Account functionality.
 */
public class AccountCreationPresentationHelper implements Serializable {
    

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
        AwardFandaRateService fandaRateService = KcServiceLocator.getService(AwardFandaRateService.class);
        List<ValidRates> validRates = fandaRateService.getValidRates(rate);
        for (Iterator<ValidRates> iter = validRates.iterator(); iter.hasNext();) {
            if (StringUtils.isBlank(iter.next().getIcrRateCode())) {
                iter.remove();
            }
        }
        return validRates;
    }

}
