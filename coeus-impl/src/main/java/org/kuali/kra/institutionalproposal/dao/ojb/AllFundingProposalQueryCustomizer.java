/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.institutionalproposal.dao.ojb;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.accesslayer.QueryCustomizerDefaultImpl;
import org.apache.ojb.broker.metadata.CollectionDescriptor;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

public class AllFundingProposalQueryCustomizer extends QueryCustomizerDefaultImpl {
    private static final String ACTIVE = "active";
	private static final String PROPOSAL_SEQUENCE_STATUS = "proposal.proposalSequenceStatus";
    private static final String PROPOSAL_NUMBER = "proposal.proposalNumber";


    @Override
    public Query customizeQuery(Object anObject,
            PersistenceBroker aBroker,
            CollectionDescriptor aCod, QueryByCriteria aQuery){
    	final Criteria crit = new Criteria();
    	crit.addEqualTo(PROPOSAL_NUMBER, ((InstitutionalProposal) anObject).getProposalNumber());
     	crit.addIn(PROPOSAL_SEQUENCE_STATUS, Stream.of(VersionStatus.ACTIVE.toString(), VersionStatus.PENDING.toString(), VersionStatus.ARCHIVED.toString()).collect(Collectors.toList()));
    	crit.addEqualTo(ACTIVE, Boolean.TRUE);
        aQuery.setCriteria(crit);
        return aQuery;
    }
}
