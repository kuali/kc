/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.dao.ojb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryFactory;
import org.kuali.kra.bo.PersonSignature;
import org.kuali.kra.dao.PersonSignatureDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;

public class PersonSignatureDaoOjb extends PlatformAwareDaoBaseOjb implements PersonSignatureDao {


    /**
     * @see org.kuali.kra.dao.PersonSignatureDao#getPersonSignatureForPersonIds(java.util.Collection)
     */
    @SuppressWarnings("unchecked")
    public List<PersonSignature> getPersonSignatureForPersonIds( Collection<String> personIds) {
        Criteria criteria = new Criteria();
        criteria.addIn(Constants.PERSON_SIGNATURE_PERSON_ID, personIds);
        criteria.addEqualTo(Constants.PERSON_SIGNATURE_ACTIVE, Boolean.TRUE);
        Query query = QueryFactory.newQuery(PersonSignature.class, criteria);
        Collection<PersonSignature> personSignatures = getPersistenceBrokerTemplate().getCollectionByQuery(query);
        return new ArrayList<PersonSignature>( personSignatures );
    }

}
