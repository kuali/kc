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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.krad.migration.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.kra.proposaldevelopment.service.ProposalCountryService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.location.api.services.LocationApiServiceLocator;
import org.kuali.rice.location.api.state.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KCStateValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    
    private String countryCode = "";

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> labels = new ArrayList<KeyValue>();
        List<State> baseCodes;
        if (StringUtils.isEmpty(countryCode)) {
            findCurrentPersonCountryCode();
            if (StringUtils.isEmpty(countryCode)) {
                labels.add(new ConcreteKeyValue("", ""));
                return labels;
            } else {
                baseCodes = LocationApiServiceLocator.getStateService().findAllStatesInCountry(countryCode);
            }
        } else { 
            baseCodes = LocationApiServiceLocator.getStateService().findAllStatesInCountry(countryCode);
        }
        
        List<State> codes = new ArrayList<State>( baseCodes );
        Collections.sort(codes, new Comparator<State> () {
            @Override
            public int compare(State o1, State o2) {
                int countryCompare = o1.getCountryCode().compareTo(o2.getCountryCode());
                if (countryCompare == 0) {
                    int stateCompare = o1.getName().compareTo(o2.getName());
                    return stateCompare;
                } else {
                    return countryCompare;
                }
            }
        });
        
        List<KeyValue> newLabels = new ArrayList<KeyValue>();
        newLabels.add(new ConcreteKeyValue("", ""));
        for (State state : codes) {
            if(state.isActive()) {
                newLabels.add(new ConcreteKeyValue(state.getCode(), state.getCountryCode() + " - " + state.getName()));
            }
        }
        labels = newLabels;
        
        clearInternalCache();
        return labels;
    }
    
    public static List<KeyValue> getKeyValues(String countryCodePassedIn) {
        List<KeyValue> labels = new ArrayList<KeyValue>();
        if (StringUtils.isNotEmpty(countryCodePassedIn)) {
            String determinedCountryCode;
            if (countryCodePassedIn.length() == 2) {
                determinedCountryCode = countryCodePassedIn;
            } else {
                ProposalCountryService proposalCountryService = KraServiceLocator.getService(ProposalCountryService.class);
                determinedCountryCode = proposalCountryService.convertAltCountryCodeToRealCountryCode(countryCodePassedIn);
            }
            
            
            List<State> baseCodes = LocationApiServiceLocator.getStateService().findAllStatesInCountry(determinedCountryCode);
            List<State> codes = new ArrayList<State>( baseCodes );
            Collections.sort(codes, new Comparator<State> () {
                @Override
                public int compare(State o1, State o2) {
                    int countryCompare = o1.getCountryCode().compareTo(o2.getCountryCode());
                    if (countryCompare == 0) {
                        int stateCompare = o1.getName().compareTo(o2.getName());
                        return stateCompare;
                    } else {
                        return countryCompare;
                    }
                }
            });
            
            List<KeyValue> newLabels = new ArrayList<KeyValue>();
            newLabels.add(new ConcreteKeyValue("", ""));
            for (State state : codes) {
                if(state.isActive()) {
                    newLabels.add(new ConcreteKeyValue(state.getCode(), state.getCountryCode() + " - " + state.getName()));
                }
            }
            labels = newLabels;
            
        }
        
        return labels;
    }
    
    protected void findCurrentPersonCountryCode() {
        ProposalDevelopmentForm pdf = (ProposalDevelopmentForm) getFormOrView();
        if (pdf != null) {
            String currentPersonCountryCode = pdf.getCurrentPersonCountryCode();
            if (StringUtils.isNotEmpty(currentPersonCountryCode)) {
                if (currentPersonCountryCode.length() == 2) {
                    countryCode = currentPersonCountryCode;
                } else {
                    ProposalCountryService proposalCountryService = KraServiceLocator.getService(ProposalCountryService.class);
                    countryCode = proposalCountryService.convertAltCountryCodeToRealCountryCode(currentPersonCountryCode);
                }
            } else {
                countryCode = "";
            }
        } else {
            countryCode = "";
        }
    }
    
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    /**
     * {@inheritDoc}
     */
    public void clearInternalCache() {
        super.clearInternalCache();
        countryCode = "";
    }
}