/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.questionnaire;

import java.util.Comparator;

import org.kuali.kra.proposaldevelopment.bo.ProposalPersonComparator;

/**
 * 
 * This class runs the comparison of the helpers based on the comparison from personComp for each of the helper's internal person.
 */
public class ProposalPersonQuestionnaireHelperComparator implements Comparator<ProposalPersonQuestionnaireHelper> {

    /**
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(ProposalPersonQuestionnaireHelper helper1, ProposalPersonQuestionnaireHelper helper2) {
        ProposalPersonComparator personComp = new ProposalPersonComparator();
        return personComp.compare(helper1.getProposalPerson(), helper2.getProposalPerson());
    }

}
