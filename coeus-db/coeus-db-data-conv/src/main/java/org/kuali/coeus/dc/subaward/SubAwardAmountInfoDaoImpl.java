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
package org.kuali.coeus.dc.subaward;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.kuali.coeus.dc.common.db.ConnectionDaoService;
import org.kuali.coeus.dc.tm.TimeAndMoneyDocumentStatusDaoImpl;

public class SubAwardAmountInfoDaoImpl implements SubAwardAmountInfoDao {

	private static final String DELETE_AMOUNT_INFO = "delete from subaward_amount_info where subaward_amount_info_id = ?";
	private static final String SELECT_AMOUNT_INFOS = "select subaward_amount_info_id, obligated_amount, obligated_change, " +
			"anticipated_amount, anticipated_change, effective_date, comments, file_name, mime_type, modification_effective_date, " + 
			"modification_number, performance_start_date, performance_end_date from subaward_amount_info " +
			"where subaward_id = ? order by subaward_amount_info_id";
	private static final String SELECT_SUBAWARDS = "select subaward_id, subaward_code, sequence_number from subaward " + 
			"order by subaward_code, sequence_number";
	private static final String QUERY_DUPED_TABLE = "select count(*) from subaward_amount_info_dups";
	private static final String CREATE_DUPED_TABLE = "create table subaward_amount_info_dups as select * from subaward_amount_info where 1 = 0";
	private static final String INSERT_DUP_RECORD = "insert into subaward_amount_info_dups " + 
			"select * from subaward_amount_info where subaward_amount_info_id = ?";

	private static final Logger LOG = Logger.getLogger(TimeAndMoneyDocumentStatusDaoImpl.class.getName());
	
	private ConnectionDaoService connectionDaoService;

	@Override
	public void fixSubAwardAmountInfoHistory() {
		List<String> subAwardsWithErrors = new ArrayList<>();
		int numberOfAmountInfosDeleted = 0;
		int numberOfAmountInfosWithDifferences = 0;
		int numberOfSubAwardsWithNoErrors = 0;
		Connection connection = connectionDaoService.getCoeusConnection();
		try (PreparedStatement querySubAwards = connection.prepareStatement(SELECT_SUBAWARDS);
				PreparedStatement queryAmountInfoForVersion = connection.prepareStatement(SELECT_AMOUNT_INFOS);
				PreparedStatement deleteAmountInfo = connection.prepareStatement(DELETE_AMOUNT_INFO);
				PreparedStatement queryDupedTable = connection.prepareStatement(QUERY_DUPED_TABLE);
				PreparedStatement createDupedTable = connection.prepareStatement(CREATE_DUPED_TABLE);
				PreparedStatement insertDupRecord = connection.prepareStatement(INSERT_DUP_RECORD);) {
			try (ResultSet rs = queryDupedTable.executeQuery()) {
				//table exists so do nothing
			} catch (SQLException e) {
				createDupedTable.execute();
			}
			Map<String, List<SubAwardVersionInfo>> subAwards;
			try (ResultSet subAwardResultSet = querySubAwards.executeQuery()) {
				subAwards = getSubAwardVersions(subAwardResultSet);
			}
			
			for (Map.Entry<String, List<SubAwardVersionInfo>> entry : subAwards.entrySet()) {
				final List<AmountInfo> previousAmountInfos = new ArrayList<>();
				boolean hadError = false;
				for (SubAwardVersionInfo version : entry.getValue()) {
					List<AmountInfo> duplicateAmountInfos = new ArrayList<>();
					List<AmountInfo> currentAmountInfos = 
							getAmountInfosForSubawardVersion(version.subAwardId, queryAmountInfoForVersion);
					
					if (currentAmountInfos.size() < previousAmountInfos.size()) {
						LOG.fine("SUBAWARD-AMOUNTINFO:Current version of subaward " + entry.getKey() + " has fewer amount infos than the previous version. Assuming already cleaned.");
					} else {
						int i = 0;
						for (i = 0; i < previousAmountInfos.size() && i < currentAmountInfos.size(); i++) {
							Long subAwardAmountInfoId = currentAmountInfos.get(i).subAwardAmountInfoId;
							if (previousAmountInfos.get(i).equals(currentAmountInfos.get(i))) {
								if (!deleteAmountInfo(subAwardAmountInfoId, insertDupRecord, deleteAmountInfo)) {
									hadError = true;
								} else {
									duplicateAmountInfos.add(currentAmountInfos.get(i));
									numberOfAmountInfosDeleted++;
								}
							} else {
								LOG.fine("SUBAWARD-AMOUNTINFO:Differences detected for " + subAwardAmountInfoId + " based on original ordering, looking for exact match in any amount info.");
								final AmountInfo previousAmountInfo = previousAmountInfos.get(i);
								List<AmountInfo> matchingItems = findMatchingAmountInfos(previousAmountInfo, currentAmountInfos);
								if (matchingItems.size() == 1) {
									if (!deleteAmountInfo(matchingItems.get(0).subAwardAmountInfoId, insertDupRecord, deleteAmountInfo)) {
										hadError = true;
									} else {
										duplicateAmountInfos.add(matchingItems.get(0));
										numberOfAmountInfosDeleted++;
									}
								} else {
									LOG.severe("SUBAWARD-AMOUNTINFO:Cannot determine matching subaward amount info to delete. Found " + matchingItems.size() + " potential matches in subaward_code(" + entry.getKey() + ") subaward_id(" + version.subAwardId + ") for previous subaward_amount_info_id(" + previousAmountInfo.subAwardAmountInfoId + ")");
									numberOfAmountInfosWithDifferences++;
									hadError = true;
								}
							}
						}
						previousAmountInfos.addAll(currentAmountInfos.stream()
								.filter(amountInfo -> { return !duplicateAmountInfos.contains(amountInfo); }).collect(Collectors.toList()));
					}
				}
				if (hadError) {
					subAwardsWithErrors.add(entry.getKey());
				} else {
					numberOfSubAwardsWithNoErrors++;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		LOG.info("SUBAWARD-AMOUNTINFO:Number of Subawards With No Errors = " + numberOfSubAwardsWithNoErrors);
		LOG.info("SUBAWARD-AMOUNTINFO:Number of Amount Infos deleted = " + numberOfAmountInfosDeleted);
		LOG.severe("SUBAWARD-AMOUNTINFO:Number of amount infos not deleted due to differences = " + numberOfAmountInfosWithDifferences);
		LOG.severe("SUBAWARD-AMOUNTINFO:Number of subawards with errors = " + subAwardsWithErrors.size());
		LOG.severe("SUBAWARD-AMOUNTINFO:Subawards in error = " + subAwardsWithErrors.stream().collect(Collectors.joining(", ")));

	}

	protected List<AmountInfo> getAmountInfosForSubawardVersion(Long subAwardId,
			PreparedStatement queryAmountInfoForVersion) throws SQLException {
		List<AmountInfo> currentAmountInfos = new ArrayList<AmountInfo>();
		queryAmountInfoForVersion.setLong(1, subAwardId);
		try (ResultSet rs = queryAmountInfoForVersion.executeQuery()) {
			while(rs.next()) {
				currentAmountInfos.add(new AmountInfo(rs));
			}
		}
		return currentAmountInfos;
	}

	protected List<AmountInfo> findMatchingAmountInfos(final AmountInfo previousAmountInfo,
			List<AmountInfo> currentAmountInfos) {
		List<AmountInfo> matchingItems = currentAmountInfos.stream()
				.filter(amountInfo -> amountInfo.equals(previousAmountInfo)).collect(Collectors.toList());
		return matchingItems;
	}

	protected Map<String, List<SubAwardVersionInfo>> getSubAwardVersions(ResultSet subAwardResultSet)
			throws SQLException {
		Map<String, List<SubAwardVersionInfo>> subAwards = new HashMap<>();
		while (subAwardResultSet.next()) {
			String subAwardCode = subAwardResultSet.getString(2);
			Long subAwardId = subAwardResultSet.getLong(1);
			Integer sequenceNumber = subAwardResultSet.getInt(3);
			List<SubAwardVersionInfo> versions = subAwards.get(subAwardCode);
			if (versions == null) {
				subAwards.put(subAwardCode, new ArrayList<>(Collections.singletonList(new SubAwardVersionInfo(subAwardId, sequenceNumber))));
			} else {
				versions.add(new SubAwardVersionInfo(subAwardId, sequenceNumber));
			}
		}
		return subAwards;
	}

	protected boolean deleteAmountInfo(Long subAwardAmountInfoId, PreparedStatement insertDupRecord,
			PreparedStatement deleteAmountInfo) throws SQLException {
		LOG.fine("Going to delete " + subAwardAmountInfoId);
		insertDupRecord.setLong(1, subAwardAmountInfoId);
		if (insertDupRecord.executeUpdate() != 1) {
			LOG.severe("SUBAWARD-AMOUNTINFO:Insert into dup table did not return expected results. Refusing to delete amount info with subaward_amount_info_id = " + subAwardAmountInfoId);
			return false;
		}
		deleteAmountInfo.setLong(1, subAwardAmountInfoId);
		if (deleteAmountInfo.executeUpdate() != 1) {
			LOG.severe("SUBAWARD-AMOUNTINFO:Delete from amount info did not return expected results with subaward_amount_info_id = " + subAwardAmountInfoId);
			return false;
		}
		return true;
	}
	
	public ConnectionDaoService getConnectionDaoService() {
		return connectionDaoService;
	}

	public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
		this.connectionDaoService = connectionDaoService;
	}
	
	static class SubAwardVersionInfo {
		Long subAwardId;
		Integer sequenceNumber;
		public SubAwardVersionInfo(Long subAwardId, Integer sequenceNumber) {
			super();
			this.subAwardId = subAwardId;
			this.sequenceNumber = sequenceNumber;
		}
	}
	
	static class AmountInfo {
		Long subAwardAmountInfoId;
		BigDecimal obligatedAmount;
		BigDecimal obligatedChange;
		BigDecimal anticipatedAmount;
		BigDecimal anticipatedChange;
		Date effectiveDate;
		String comments;
		String fileName;
		String mimeType;
		Date modificationEffectiveDate;
		String modificationNumber;
		Date performanceStartDate;
		Date performanceEndDate;
		public AmountInfo(ResultSet rs) throws SQLException {
			subAwardAmountInfoId = rs.getLong(1);
			obligatedAmount = rs.getBigDecimal(2);
			obligatedChange = rs.getBigDecimal(3);
			anticipatedAmount = rs.getBigDecimal(4);
			anticipatedChange = rs.getBigDecimal(5);
			effectiveDate = rs.getDate(6);
			comments = rs.getString(7);
			fileName = rs.getString(8);
			mimeType = rs.getString(9);
			modificationEffectiveDate = rs.getDate(10);
			modificationNumber = rs.getString(11);
			performanceStartDate = rs.getDate(12);
			performanceEndDate = rs.getDate(13);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			AmountInfo other = (AmountInfo) obj;
			if (anticipatedChange == null) {
				if (other.anticipatedChange != null)
					return false;
			} else if (!anticipatedChange.equals(other.anticipatedChange))
				return false;
			if (comments == null) {
				if (other.comments != null)
					return false;
			} else if (!comments.trim().equals(other.comments != null ? other.comments.trim() : other.comments))
				return false;
			if (effectiveDate == null) {
				if (other.effectiveDate != null)
					return false;
			} else if (!effectiveDate.equals(other.effectiveDate))
				return false;
			if (fileName == null) {
				if (other.fileName != null)
					return false;
			} else if (!fileName.equals(other.fileName))
				return false;
			if (mimeType == null) {
				if (other.mimeType != null)
					return false;
			} else if (!mimeType.equals(other.mimeType))
				return false;
			if (modificationEffectiveDate == null) {
				if (other.modificationEffectiveDate != null)
					return false;
			} else if (!modificationEffectiveDate.equals(other.modificationEffectiveDate))
				return false;
			if (modificationNumber == null) {
				if (other.modificationNumber != null)
					return false;
			} else if (!modificationNumber.equals(other.modificationNumber))
				return false;
			if (obligatedChange == null) {
				if (other.obligatedChange != null)
					return false;
			} else if (!obligatedChange.equals(other.obligatedChange))
				return false;
			if (performanceEndDate == null) {
				if (other.performanceEndDate != null)
					return false;
			} else if (!performanceEndDate.equals(other.performanceEndDate))
				return false;
			if (performanceStartDate == null) {
				if (other.performanceStartDate != null)
					return false;
			} else if (!performanceStartDate.equals(other.performanceStartDate))
				return false;
			return true;
		}
		
		
	}

}
