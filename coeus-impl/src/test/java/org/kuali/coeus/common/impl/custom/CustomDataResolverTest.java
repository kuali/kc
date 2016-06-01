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
package org.kuali.coeus.common.impl.custom;

import junit.framework.Assert;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.kra.award.customdata.AwardCustomData;
import org.kuali.kra.award.home.Award;

import java.util.*;

public class CustomDataResolverTest {

    private static final String CUSTOM_ATTRIBUTE_ID = "Custom Attribute Id";

    class MockCustomDataResolver extends CustomDataResolver {

        public MockCustomDataResolver(String outputName, Set<String> params, String moduleNamePrereq) {
            super(outputName, params, moduleNamePrereq);
        }
    }

    @Test
    public void customDataResolverTest() {
        Set<String> params = new HashSet<>();
        params.add("8");
        Map<String, Object> resolvedPrereqs = new HashMap<>();
        final Award award = new Award();
        List<AwardCustomData> awardCustomDataList = new ArrayList<>();
        AwardCustomData awardCustomData = new AwardCustomData();
        awardCustomData.setCustomAttributeId(8L);
        awardCustomData.setValue("billing element");
        awardCustomDataList.add(awardCustomData);

        AwardCustomData awardCustomData1 = new AwardCustomData();
        awardCustomData1.setCustomAttributeId(9L);
        awardCustomData1.setValue("test");
        awardCustomDataList.add(awardCustomData1);
        award.setAwardCustomDataList(awardCustomDataList);

        resolvedPrereqs.put("award", award);
        MockCustomDataResolver mock = new MockCustomDataResolver("outputName", params, "award");

        Map<String, String> parameters = new HashMap<>();
        parameters.put(CUSTOM_ATTRIBUTE_ID, "8");
        Assert.assertTrue(mock.resolve(resolvedPrereqs, parameters).equals("billing element"));

        parameters = new HashMap<>();
        parameters.put(CUSTOM_ATTRIBUTE_ID, "10");
        Assert.assertTrue(mock.resolve(resolvedPrereqs, parameters) == null);

    }
}
