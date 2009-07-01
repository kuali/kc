/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator.impl;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.service.S2SUtilService;

/**
 * This abstract class has methods that are common to all the versions of RRPersonalData form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RRPersonalDataBaseGenerator extends S2SBaseFormGenerator {

    public static final String KEYPERSON_TYPE_C0_INVESTIGATOR = "COI";
    protected S2SUtilService s2sUtilService;

    /**
     * 
     * Constructs a RRPersonalDataBaseGenerator.java.
     */
    public RRPersonalDataBaseGenerator() {
        s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
    }

}
