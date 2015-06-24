package org.kuali.kra.committee.dao;

import java.util.List;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;

public interface CustomCommitteeDao {

	void updateSubmissionsToNewCommitteeVersion(CommitteeBase<?, ?, ? extends CommitteeScheduleBase> committee, List<? extends CommitteeScheduleBase> schedules);

}