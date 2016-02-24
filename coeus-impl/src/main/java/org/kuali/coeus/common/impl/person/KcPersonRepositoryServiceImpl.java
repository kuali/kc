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
package org.kuali.coeus.common.impl.person;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.person.KcPersonContract;
import org.kuali.coeus.common.api.person.KcPersonRepositoryService;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("kcPersonRepositoryService")
public class KcPersonRepositoryServiceImpl implements KcPersonRepositoryService {

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;

    @Override
    public KcPersonContract findKcPersonByUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            throw new IllegalArgumentException("userName is blank");
        }

        return kcPersonService.getKcPersonByUserName(userName);
    }

    @Override
    public KcPersonContract findKcPersonByPersonId(String personId) {
        if (StringUtils.isBlank(personId)) {
            throw new IllegalArgumentException("personId is blank");
        }

        return kcPersonService.getKcPersonByPersonId(personId);
    }

    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
}
