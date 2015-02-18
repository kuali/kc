package org.kuali.kra.award.dao.ojb;

import java.util.Arrays;
import java.util.Collections;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.accesslayer.QueryCustomizerDefaultImpl;
import org.apache.ojb.broker.metadata.CollectionDescriptor;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.kra.award.home.Award;

public class AllFundingProposalQueryCustomizer extends QueryCustomizerDefaultImpl {
    @Override
    public Query customizeQuery(Object anObject,
            PersistenceBroker aBroker,
            CollectionDescriptor aCod, QueryByCriteria aQuery){
    	Criteria crit = new Criteria();
    	crit.addEqualTo("award.awardNumber", ((Award)anObject).getAwardNumber());
    	crit.addIn("award.awardSequenceStatus", Arrays.asList(new String[]{VersionStatus.ACTIVE.toString(), VersionStatus.PENDING.toString(), VersionStatus.ARCHIVED.toString()}));
    	crit.addEqualTo("active", Boolean.TRUE);
        aQuery.setCriteria(crit);
        return aQuery;
    }
}
