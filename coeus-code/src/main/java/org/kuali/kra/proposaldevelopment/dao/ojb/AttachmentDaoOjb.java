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
package org.kuali.kra.proposaldevelopment.dao.ojb;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiographyAttachment;
import org.kuali.kra.proposaldevelopment.dao.AttachmentDao;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.service.util.OjbCollectionAware;

import java.sql.Types;
import java.util.Iterator;

/**
 * 
 * This class created to get the timestamp and upload user for attachments.
 * They have to come from *_attachment tables.  Since we don't want document to carry
 * big "attachment" file around, so we try not to use retrieve collection.
 */

public class AttachmentDaoOjb  extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, AttachmentDao {

    @Override
    public Iterator getPersonnelTimeStampAndUploadUser(Integer proposalPersonNumber, String proposalNumber, Integer biographyNumber) {
        
        Criteria crit = new Criteria();
        crit.addEqualTo("proposalPersonNumber", proposalPersonNumber);
        crit.addEqualTo("proposalNumber", proposalNumber);
        crit.addEqualTo("biographyNumber", biographyNumber);
        ReportQueryByCriteria q = QueryFactory.newReportQuery(ProposalPersonBiographyAttachment.class, crit);
        q.setAttributes(new String[] { "updateTimestamp", "updateUser" });
        // it retrieved updateTimestamp as 'Date'. so has to set up the following.  The repository looks fine.  not sure why ?
        q.setJdbcTypes(new int[] {Types.TIMESTAMP, Types.VARCHAR});

        return getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
    }

    @Override
    public Iterator getNarrativeTimeStampAndUploadUser(Integer moduleNumber, String proposalNumber) {
        
        Criteria crit = new Criteria();
        crit.addEqualTo("moduleNumber", moduleNumber);
        crit.addEqualTo("proposalNumber", proposalNumber);
        ReportQueryByCriteria q = QueryFactory.newReportQuery(NarrativeAttachment.class, crit);
        q.setAttributes(new String[] { "updateTimestamp", "updateUser" });
        // it retrieved updateTimestamp as 'Date'. so has to set up the following.  The repository looks fine.  not sure why ?
        q.setJdbcTypes(new int[] {Types.TIMESTAMP, Types.VARCHAR});

        return getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
    }

}
