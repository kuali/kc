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
 * This abstract class has methods that are common to all the versions of PHS398CoverPageSupplement form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class PHS398CoverPageSupplementBaseGenerator extends S2SBaseFormGenerator {

    public static final String IS_NEW_INVESTIGATOR = "13";
    public static final String ACTIVITY_TYPE_CLINICAL_TRIAL = "8";
    public static final String PHASE_III_CLINICAL_TRIAL = "17";
    public static final String IS_HUMAN_STEM_CELLS_INVOLVED = "18";
    
    protected S2SUtilService s2sUtilService;
    protected static final int MAX_NUMBER_OF_DEGREES = 3;
    protected static final int PERSON_DEGREE_MAX_LENGTH = 10;

    /**
     * 
     * Constructs a PHS398CoverPageSupplementBaseGenerator.java.
     */
    public PHS398CoverPageSupplementBaseGenerator() {
        s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
    }

}
