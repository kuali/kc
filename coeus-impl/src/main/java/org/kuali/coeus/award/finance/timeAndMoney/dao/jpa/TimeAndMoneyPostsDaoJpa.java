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

package org.kuali.coeus.award.finance.timeAndMoney.dao.jpa;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.award.finance.timeAndMoney.TimeAndMoneyPosts;
import org.kuali.coeus.award.finance.timeAndMoney.dao.TimeAndMoneyPostsDao;
import org.kuali.rice.core.api.criteria.OrderByField;
import org.kuali.rice.core.api.criteria.OrderDirection;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("timeAndMoneyPostsDao")
public class TimeAndMoneyPostsDaoJpa implements TimeAndMoneyPostsDao {

    public static final String DOCUMENT_NUMBER = "documentNumber";
    public static final String AWARD_FAMILY = "awardFamily";
    public static final String UPDATE_TIMESTAMP = "updateTimestamp";
    public static final String ACTIVE = "active";

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    public TimeAndMoneyPosts getTimeAndMoneyPostsByDocumentNumber(String documentNumber) {
        Map<String, String> keys = new HashMap<>();
        keys.put(DOCUMENT_NUMBER, documentNumber);
        return dataObjectService.findUnique(TimeAndMoneyPosts.class, QueryByCriteria.Builder.andAttributes(keys).build());
    }

    @Override
    public List<TimeAndMoneyPosts> getActiveTimeAndMoneyPosts() {
        Map<String, Boolean> keys = new HashMap<>();
        keys.put(ACTIVE, true);
        return dataObjectService.findMatching(TimeAndMoneyPosts.class, QueryByCriteria.Builder.andAttributes(keys).build()).getResults();
    }

    @Override
    public List<TimeAndMoneyPosts> getActiveTimeAndMoneyPostsForAwardHierarchy(String awardNumber) {
        String awardFamily = awardNumber.substring(0, StringUtils.indexOf(awardNumber, "-"));

        Map<String, Object> keys = new HashMap<>();
        keys.put(ACTIVE, true);
        keys.put(AWARD_FAMILY, awardFamily);
        List<OrderByField> orderByFields = new ArrayList<>();
        orderByFields.add(OrderByField.Builder.create(UPDATE_TIMESTAMP, OrderDirection.DESCENDING).build());

        return dataObjectService.findMatching(TimeAndMoneyPosts.class, QueryByCriteria.Builder.andAttributes(keys).
                setOrderByFields(orderByFields).build()).getResults();
    }

    @Override
    public TimeAndMoneyPosts getTimeAndMoneyPost(Long id) {
        return dataObjectService.find(TimeAndMoneyPosts.class, id);
    }

}
