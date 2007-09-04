/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.dao.BusinessObjectDao;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;

public class ProposalDevelopmentServiceImpl implements ProposalDevelopmentService {
    private BusinessObjectDao businessObjectDao;

    public String getSponsorCode(String pkValue) {
        // TODO : still testing for initial DWR set up
        Map<String, String> primaryKeyMap=new HashMap();
        if (pkValue==null || pkValue.trim().equals("")) {
            pkValue="004501";
        }
        primaryKeyMap.put("sponsorCode", pkValue);
        Sponsor sponsor=(Sponsor)businessObjectDao.findByPrimaryKey(Sponsor.class, primaryKeyMap);
        if (sponsor==null) {
            return "004812";
        } else {
            return sponsor.getSponsorCode();
        }
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService#getSponsorName(java.lang.String)
     */
    public String getSponsorName(String sponsorCode) {
        String sponsorName = "not found";

        Map<String, String> primaryKeyMap=new HashMap<String, String>();
        if (StringUtils.isNotEmpty(sponsorCode)) {
            primaryKeyMap.put("sponsorCode", sponsorCode);
            Sponsor sponsor = (Sponsor)businessObjectDao.findByPrimaryKey(Sponsor.class, primaryKeyMap);
            if (sponsor !=null) {
                sponsorName = sponsor.getSponsorName();
            }
        }

        return sponsorName;
    }

    public BusinessObjectDao getBusinessObjectDao() {
        return businessObjectDao;
    }

    public void setBusinessObjectDao(BusinessObjectDao businessObjectDao) {
        this.businessObjectDao = businessObjectDao;
    }

}
