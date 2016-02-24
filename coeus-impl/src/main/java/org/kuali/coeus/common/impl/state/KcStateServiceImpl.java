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
package org.kuali.coeus.common.impl.state;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.state.KcStateService;
import org.kuali.coeus.common.api.state.StateContract;
import org.kuali.rice.location.api.state.State;
import org.kuali.rice.location.api.state.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("kcStateService")
public class KcStateServiceImpl implements KcStateService {

    @Autowired
    @Qualifier("stateService")
    private StateService stateService;

    @Override
    public StateContract getState(String countryCode, String code) {
        if (StringUtils.isBlank(countryCode)) {
            throw new IllegalArgumentException("countryCode is blank");
        }

        if (StringUtils.isBlank(countryCode)) {
            throw new IllegalArgumentException("code is blank");
        }

        return toDto(stateService.getState(countryCode, code));
    }

    protected StateDto toDto(State state) {
        if (state != null) {
            final StateDto dto = new StateDto();
            dto.setName(state.getName());
            dto.setCode(state.getCode());
            dto.setCountryCode(state.getCountryCode());
            return dto;
        }
        return null;
    }
    public StateService getStateService() {
        return stateService;
    }

    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }
}
