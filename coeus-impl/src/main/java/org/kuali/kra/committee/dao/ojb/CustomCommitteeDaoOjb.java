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
package org.kuali.kra.committee.dao.ojb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.accesslayer.LookupException;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeScheduleBase;
import org.kuali.kra.committee.dao.CustomCommitteeDao;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.springmodules.orm.ojb.OjbFactoryUtils;

public class CustomCommitteeDaoOjb extends PlatformAwareDaoBaseOjb implements CustomCommitteeDao {

	private static final String SCHEDULE_ID_QUERY = "UPDATE protocol_submission SET schedule_id_fk = ? where schedule_id = ?";
	private static final String COMMITTEE_QUERY = "UPDATE protocol_submission SET committee_id_fk = ? where committee_id = ?";

	public void updateSubmissionsToNewCommitteeVersion(CommitteeBase<?, ?, ? extends CommitteeScheduleBase> committee, List<? extends CommitteeScheduleBase> schedules) {
		if (committee == null) {
			throw new IllegalArgumentException("committee cannot be null");
		}

		updateCommitteeOnSubmission(committee);
		updateScheduleOnSubmission(committee);

	}

	protected void updateScheduleOnSubmission(CommitteeBase<?, ?, ? extends CommitteeScheduleBase> committee) {
		updateSubmission(committee, SCHEDULE_ID_QUERY, (com, stmt) -> {
			for(CommitteeScheduleBase schedule : committee.getCommitteeSchedules()) {
				stmt.setLong(1, schedule.getId());
				stmt.setString(2, schedule.getScheduleId());
				stmt.addBatch();
			}

			stmt.executeBatch();
		});
	}

	protected void updateCommitteeOnSubmission(CommitteeBase<?, ?, ? extends CommitteeScheduleBase> committee) {
		updateSubmission(committee, COMMITTEE_QUERY, (com, stmt) -> {
			stmt.setLong(1, committee.getId());
			stmt.setString(2, committee.getCommitteeId());
			stmt.executeUpdate();
		});
	}

	protected void updateSubmission(CommitteeBase<?, ?, ? extends CommitteeScheduleBase> committee, String query,  CommitteeStmtFunction function) {
		final PersistenceBroker broker = getPersistenceBroker(false);
		try (Connection conn = broker.serviceConnectionManager().getConnection();
			 PreparedStatement stmt = conn.prepareStatement(query)) {
			function.apply(committee, stmt);
		} catch (SQLException|LookupException e) {
			throw new RuntimeException(e);
		} finally {
			OjbFactoryUtils.releasePersistenceBroker(broker, this.getPersistenceBrokerTemplate().getPbKey());
		}
	}

	@FunctionalInterface
	private interface CommitteeStmtFunction {
		void apply(CommitteeBase<?, ?, ? extends CommitteeScheduleBase> committee, PreparedStatement stmt) throws SQLException;
	}
}