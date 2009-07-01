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

import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.service.S2SUtilService;

/**
 * This abstract class has methods that are common to all the versions of RRKeyPerson form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RRKeyPersonBaseGenerator extends S2SBaseFormGenerator {

    protected static final String OTHER = "Other (Specify)";
    protected static final String BIOSKETCH = "BIOSKETCH";
    protected static final String BIOSKETCH_TYPE = "1";
    protected static final String CURRENT_PENDING_TYPE = "2";
    protected static final int PROFILE_TYPE = 18;
    protected static final String KEYPERSON = "KP";
    protected static final String CO_INVESTIGATOR = "COI";
    protected static final int MAX_KEY_PERSON_COUNT = 8;
    protected S2SUtilService s2sUtilService;

    protected List<ProposalPerson> extraPersons = null;
    protected String pIPersonOrRolodexId = null;
    protected static final int DIRECTORY_TITLE_MAX_LENGTH = 45;
    protected static final int DEPARTMENT_NAME_MAX_LENGTH = 30;
    protected static final int ROLE_DESCRIPTION_MAX_LENGTH = 40;

    /**
     * 
     * Constructs a RRKeyPersonBaseGenerator.java.
     */
    public RRKeyPersonBaseGenerator() {
        s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
    }
}
