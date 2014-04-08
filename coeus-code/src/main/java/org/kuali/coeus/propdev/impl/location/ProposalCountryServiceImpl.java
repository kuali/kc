/*
 * Copyright 2005-2014 The Kuali Foundation
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