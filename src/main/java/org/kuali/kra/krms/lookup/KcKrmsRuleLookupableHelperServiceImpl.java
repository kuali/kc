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
package org.kuali.kra.krms.lookup;

import org.kuali.kra.krms.KcKrmsRule;
import org.kuali.kra.krms.dao.KrmsRuleDao;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.krms.impl.repository.RuleBo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KcKrmsRuleLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    KrmsRuleDao krmsRuleDao;
    @Override
    public List<KcKrmsRule> getSearchResults(Map<String, String> fieldValues) {
 //       super.getSearchResults(fieldValues);
//        List<RuleBo> rules = (List<RuleBo>)getBusinessObjectService().findAll(RuleBo.class);
        List<RuleBo> rules = (List<RuleBo>)krmsRuleDao.getRuless(fieldValues);
        List<KcKrmsRule> kcKrmsRules = new ArrayList<KcKrmsRule>();
        for (RuleBo rule : rules) {
            kcKrmsRules.add(new KcKrmsRule(rule.getId(), rule.getNamespace(), rule.getDescription(), rule.getName(), rule.getTypeId(), rule.getPropId(), rule.isActive()));
        }
        return kcKrmsRules; 
    }

//    public Class getBusinessObjectClass() {
//        return RuleBo.class;
//    }

    public void setKrmsRuleDao(KrmsRuleDao krmsRuleDao) {
        this.krmsRuleDao = krmsRuleDao;
    }

}
