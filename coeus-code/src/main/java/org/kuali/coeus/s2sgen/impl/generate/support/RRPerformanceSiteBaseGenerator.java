/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.generate.support;

import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This abstract class has methods that are common to all the versions of RRPerformanceSite form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RRPerformanceSiteBaseGenerator extends S2SBaseFormGenerator {

    public static final String EMPTY_STRING = "";
    public static final int PERFORMANCE_SITES_ATTACHMENT = 40;
    protected static final int PERFORMING_ORG_LOCATION_TYPE_CODE = 2;
    protected static final int OTHER_ORG_LOCATION_TYPE_CODE = 3;
    protected static final int PERFORMANCE_SITE_LOCATION_TYPE_CODE = 4;

    @Autowired
    @Qualifier("rolodexService")
    protected RolodexService rolodexService;

    /**
     * 
     * This method checks the string value passed and returns empty string if it is null, else returns string value
     * 
     * @param string (String) string to be checked for null.
     * @return string (String) empty string if sting value is null else string value.
     */
    public String checkNull(String string) {
        if (string == null) {
            return EMPTY_STRING;
        }
        else {
            return string;
        }
    }

    public RolodexService getRolodexService() {
        return rolodexService;
    }

    public void setRolodexService(RolodexService rolodexService) {
        this.rolodexService = rolodexService;
    }
}
