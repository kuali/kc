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
package org.kuali.coeus.propdev.impl.location;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.location.api.country.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("proposalCountryService")
public class ProposalCountryServiceImpl implements ProposalCountryService {

    private static final Log LOG = LogFactory.getLog(ProposalCountryServiceImpl.class);

    @Autowired
    @Qualifier("countryService")
    private CountryService countryService;
    
    public String convertAltCountryCodeToRealCountryCode(String currentCountryCode) {
        try {
            return getCountryService().getCountryByAlternateCode(currentCountryCode).getCode();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return "";
        }
    }

    public CountryService getCountryService() {
        return countryService;
    }

    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }
    
}
