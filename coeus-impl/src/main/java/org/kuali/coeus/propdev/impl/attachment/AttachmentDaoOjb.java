/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiographyAttachment;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.service.util.OjbCollectionAware;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.Iterator;

@Component("attachmentDao")
public class AttachmentDaoOjb  extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, AttachmentDao {

    @Override
    public Iterator getPersonnelTimeStampAndUploadUser(Integer proposalPersonNumber, String proposalNumber, Integer biographyNumber) {
        
        Criteria crit = new Criteria();
        crit.addEqualTo("proposalPersonNumber", proposalPersonNumber);
        crit.addEqualTo("proposalNumber", proposalNumber);
        crit.addEqualTo("biographyNumber", biographyNumber);
        ReportQueryByCriteria q = QueryFactory.newReportQuery(ProposalPersonBiographyAttachment.class, crit);
        q.setAttributes(new String[] { "updateTimestamp", "updateUser" });
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
        q.setJdbcTypes(new int[] {Types.TIMESTAMP, Types.VARCHAR});

        return getPersistenceBrokerTemplate().getReportQueryIteratorByQuery(q);
    }

}
