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
package org.kuali.kra.iacuc.committee.lookup.keyvalue;

import org.kuali.kra.common.committee.bo.CommitteeBase;
import org.kuali.kra.common.committee.bo.CommitteeType;
import org.kuali.kra.common.committee.lookup.keyvalue.CommitteeIdValuesFinderBase;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;

public class IacucCommitteeIdValuesFinder extends CommitteeIdValuesFinderBase {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5083716441320247996L;

    @Override
    protected Class<? extends CommitteeBase> getCommitteeBOClassHook() {
        return IacucCommittee.class;
    }

    @Override
    protected String getCommitteeTypeCodeHook() {
        return CommitteeType.IACUC_TYPE_CODE;
    }

}
