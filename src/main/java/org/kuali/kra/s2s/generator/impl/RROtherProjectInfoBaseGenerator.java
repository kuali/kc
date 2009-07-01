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
 * This abstract class has methods that are common to all the versions of RROtherProjectInfo form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class RROtherProjectInfoBaseGenerator extends S2SBaseFormGenerator {
    protected S2SUtilService s2sUtilService;
    // Special Review Id's
    public static final int HUMAN_SUBJECT_SUPPLEMENT = 1;
    public static final int VERTEBRATE_ANIMALS_SUPPLEMENT = 2;
    // Its an YnQ fields that are required for RROtherProjectInfo
    public static final String PROPRIETARY_INFORMATION_INDICATOR = "P002";
    public static final String ENVIRONMENTAL_IMPACT_YNQ = "P003";
    public static final String ENVIRONMENTAL_EXEMPTION_YNQ = "P007";
    public static final String INTERNATIONAL_ACTIVITIES_YNQ = "P008";
    // Attachments
    public static final int EQUIPMENT_ATTACHMENT = 3;
    public static final int FACILITIES_ATTACHMENT = 2;
    public static final int NARRATIVE_ATTACHMENT = 1;
    public static final int BIBLIOGRAPHY_ATTACHMENT = 4;
    public static final int ABSTRACT_PROJECT_SUMMARY_ATTACHMENT = 5;
    public static final int OTHER_ATTACHMENT = 8;
    public static final int SUPPLIMENTARY_ATTACHMENT = 15;
    public static final String SPECIAL_REVIEW_HUMAN_SUBJECTS = "1";
    public static final String SPECIAL_REVIEW_ANIMAL_USAGE = "2";
    public static final int APPROVAL_TYPE_EXCEMPT = 4;

    /**
     * 
     * Constructs a RROtherProjectInfoBaseGenerator.java.
     */
    public RROtherProjectInfoBaseGenerator() {
        s2sUtilService = KraServiceLocator.getService(S2SUtilService.class);
    }
}
