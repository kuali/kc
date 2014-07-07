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

import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;
import org.kuali.coeus.s2sgen.impl.person.DepartmentalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * This abstract class has methods that are common to all the versions of
 * GGLobbying form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */

public abstract class GGLobbyingFormBaseGenerator extends S2SBaseFormGenerator {

    protected static final String EMPTY_STRING = "";
    protected static final int ORGANIZATON_NAME_MAX_LENGTH = 120;
    protected static final int PRIMARY_TITLE_MAX_LENGTH = 45;

    @Autowired
    @Qualifier("departmentalPersonService")
    protected DepartmentalPersonService departmentalPersonService;

    public DepartmentalPersonService getDepartmentalPersonService() {
        return departmentalPersonService;
    }

    public void setDepartmentalPersonService(DepartmentalPersonService departmentalPersonService) {
        this.departmentalPersonService = departmentalPersonService;
    }
}
