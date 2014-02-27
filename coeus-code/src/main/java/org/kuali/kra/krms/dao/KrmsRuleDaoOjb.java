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
package org.kuali.kra.krms.dao;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.service.util.OjbCollectionAware;
import org.kuali.rice.krms.impl.repository.RuleBo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class KrmsRuleDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, KrmsRuleDao  {

    
     @Override
    public List<RuleBo> getRuless(Map<String, String> fieldValues) {
       // (String id, String namespace, String description, String name, String typeId, String propId, boolean active)        
         Criteria crit = new Criteria();
         List fieldNames = Arrays.asList("id","namespace","name","description","typeId","propId","active");
        for (String key : fieldValues.keySet()) {
            if (fieldNames.contains(key) && fieldValues.get(key) != null && (StringUtils.isNotBlank(fieldValues.get(key)))) {
            if (StringUtils.equals("active", key)) {
                crit.addLike("active", (String)fieldValues.get(key));
            } else {
                String propertyName = getDbPlatform().getUpperCaseFunction() + "("+key+")";
                String value = (String)fieldValues.get(key);
                value = StringUtils.replace(value, "*", "%", -1);
                crit.addLike(propertyName, value.toUpperCase());

            }
            }
        }
        Query q = QueryFactory.newQuery(RuleBo.class, crit, true);
        return (List<RuleBo>) getPersistenceBrokerTemplate().getCollectionByQuery(q);
      }
    
}
