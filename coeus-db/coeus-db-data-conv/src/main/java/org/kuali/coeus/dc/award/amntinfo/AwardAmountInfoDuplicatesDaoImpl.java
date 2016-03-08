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
package org.kuali.coeus.dc.award.amntinfo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.kuali.coeus.dc.common.db.ConnectionDaoService;

public class AwardAmountInfoDuplicatesDaoImpl implements AwardAmountInfoDuplicatesDao {

	private static final String DELETE_AMOUNT_INFO = "delete from award_amount_info where award_amount_info_id = ?";
	private static final String SELECT_AMOUNT_INFOS = 	"select award_amount_info_id, award_id, award_number, sequence_number, tnm_document_number, " + 
			"anticipated_change_direct, anticipated_change_indirect, anticipated_total_direct, anticipated_total_indirect, " + 
			"anticipated_total_amount, ant_distributable_amount, anticipated_change, " +
			"obligated_total_direct, obligated_total_indirect, amount_obligated_to_date, obli_distributable_amount, " +
			"obligated_change, obligated_change_direct, obligated_change_indirect, " +
			"final_expiration_date, current_fund_effective_date, obligation_expiration_date, " +
			"transaction_id, entry_type, eom_process_flag " +
			"from award_amount_info where award_number = ? and transaction_id is not null and tnm_document_number is not null " +
			"order by transaction_id desc, sequence_number desc";
	private static final String UPDATE_AWARD_AMOUNT_INFO_DATES = "update award_amount_info set final_expiration_date = ?, current_fund_effective_date = ?, obligation_expiration_date = ? " +
		" where award_amount_info_id = ?";
	private static final String UPDATE_AWARD_AMOUNT_INFO_CHANGE = "update award_amount_info set ANTICIPATED_CHANGE = ?, ANTICIPATED_CHANGE_DIRECT = ?, ANTICIPATED_CHANGE_INDIRECT = ?, " +
		" OBLIGATED_CHANGE = ?, OBLIGATED_CHANGE_DIRECT = ?, OBLIGATED_CHANGE_INDIRECT = ?, ANT_DISTRIBUTABLE_AMOUNT = ?, OBLI_DISTRIBUTABLE_AMOUNT = ? where award_amount_info_id = ?";
	private static final String SELECT_AWARD_NUMBERS = "select distinct award_number from award_amount_info";
	private static final String QUERY_DUPED_TABLE = "select count(*) from award_amount_info_dups";
	private static final String CREATE_DUPED_TABLE = "create table award_amount_info_dups as select * from award_amount_info where 1 = 0";
	private static final String QUERY_MOD_TABLE = "select count(*) from award_amount_info_dup_mods";
	private static final String CREATE_MOD_TABLE = "create table award_amount_info_dup_mods as select * from award_amount_info where 1 = 0";
	private static final String INSERT_DUP_RECORD = "insert into award_amount_info_dups " + 
			"select * from award_amount_info where award_amount_info_id = ?";
	private static final String INSERT_MOD_RECORD = "insert into award_amount_info_dup_mods select * from award_amount_info where award_amount_info_id = ?";
	private static final String SELECT_AWARD_DOC_NBR = "select document_number from award where award_id = ?";
	private static final String VALIDATE_DOCUMENT_STATUS = "select doc_hdr_stat_cd from krew_doc_hdr_t where doc_hdr_id = ?";
	private static final List<String> UNSUCCESSFUL_STATUSES = Arrays.asList("D", "X", "C");
	private static final List<String> PENDING_STATUSES = Arrays.asList("I", "S", "R", "E");
	
	private static final Logger LOG = Logger.getLogger(AwardAmountInfoDuplicatesDaoImpl.class.getName());

	private ConnectionDaoService connectionDaoService;
	
	@Override
	public void fixAwardAmountInfoDuplicates() {
		int numberOfDupsRemoved = 0;
		List<AmountInfo> dupsNotEqual = new ArrayList<>();
		List<AmountInfo> totalsNotEqual = new ArrayList<>();
		List<AmountInfo> amountInfosAffected = new ArrayList<>();
		Connection conn = connectionDaoService.getCoeusConnection();
		Connection riceConn = connectionDaoService.getRiceConnection();
		try (PreparedStatement deleteAmountInfo = conn.prepareStatement(DELETE_AMOUNT_INFO);
				PreparedStatement selectAmountInfos = conn.prepareStatement(SELECT_AMOUNT_INFOS);
				PreparedStatement selectAwardNumbers = conn.prepareStatement(SELECT_AWARD_NUMBERS);
				PreparedStatement queryForDupTable = conn.prepareStatement(QUERY_DUPED_TABLE);
				PreparedStatement createDupedTable = conn.prepareStatement(CREATE_DUPED_TABLE);
				PreparedStatement insertDupedRecord = conn.prepareStatement(INSERT_DUP_RECORD);
				PreparedStatement selectAwardDocumentNumber = conn.prepareStatement(SELECT_AWARD_DOC_NBR);
				PreparedStatement validateDocumentStatus = riceConn.prepareStatement(VALIDATE_DOCUMENT_STATUS);
				PreparedStatement updateAwardAmountInfoDates = conn.prepareStatement(UPDATE_AWARD_AMOUNT_INFO_DATES);
				PreparedStatement updateAwardAmountInfoChangeAmnts = conn.prepareStatement(UPDATE_AWARD_AMOUNT_INFO_CHANGE);
				PreparedStatement queryForModTable = conn.prepareStatement(QUERY_MOD_TABLE);
				PreparedStatement createModTable = conn.prepareStatement(CREATE_MOD_TABLE);
				PreparedStatement insertModRecord = conn.prepareStatement(INSERT_MOD_RECORD);) {
			try (ResultSet rs = queryForDupTable.executeQuery()) {
				//table exists so do nothing
			} catch (SQLException e) {
				createDupedTable.execute();
			}
			try (ResultSet rs= queryForModTable.executeQuery()) {
				//table exists
			} catch (SQLException e) {
				createModTable.execute();
			}
			try (ResultSet awardNumbersRs = selectAwardNumbers.executeQuery()) {
				while (awardNumbersRs.next()) {
					Map<Long, AmountInfo> amountInfos = new HashMap<>();
					String awardNumber = awardNumbersRs.getString(1);
					selectAmountInfos.setString(1, awardNumber);
					try (ResultSet amountInfosRs = selectAmountInfos.executeQuery()) {
						while (amountInfosRs.next()) {
							AmountInfo currentAmountInfo = new AmountInfo(amountInfosRs);
							if (amountInfos.containsKey(currentAmountInfo.transactionId) 
								&& amountInfos.get(currentAmountInfo.transactionId).createdBySameTMDoc(currentAmountInfo)) {
								
								final AmountInfo newerAmountInfo = amountInfos.get(currentAmountInfo.transactionId);
								if (!newerAmountInfo.equals(currentAmountInfo)) {
									dupsNotEqual.add(currentAmountInfo);
									insertModRecord.setLong(1, newerAmountInfo.awardAmountInfoId);
									insertModRecord.execute();
									LOG.warning("Deleting duplicate award_amount_infos with transaction id = " + currentAmountInfo.transactionId + 
											" but it is not equal to later amount info. award_amount_info_ids(" + 
											currentAmountInfo.awardAmountInfoId + ", " + 
											newerAmountInfo.awardAmountInfoId + "). ");
									updateNewerAmountInfoDates(newerAmountInfo, currentAmountInfo, updateAwardAmountInfoDates);
									if (newerAmountInfo.totalAmountsEqual(currentAmountInfo)) {
										//update change and distributable amounts
										updateNewerAmountInfoChangeAmnts(newerAmountInfo, currentAmountInfo,
											updateAwardAmountInfoChangeAmnts);
									} else {
										totalsNotEqual.add(currentAmountInfo);
									}
								} else if (PENDING_STATUSES.contains(newerAmountInfo.docStatus)) {
									LOG.warning("Deleting duplicate award_amount_info for PENDING award with transaction id = " + currentAmountInfo.transactionId + " and award_amount_info_id = " + currentAmountInfo.awardAmountInfoId);
								} else {
									LOG.fine("Deleting duplicate award_amount_info with transaction id = " + currentAmountInfo.transactionId + " and award_amount_info_id = " + currentAmountInfo.awardAmountInfoId);
								}
								deleteAmountInfo(currentAmountInfo, deleteAmountInfo, insertDupedRecord);
								numberOfDupsRemoved++;
								
								amountInfosAffected.add(currentAmountInfo);
							}
							String docHeaderStatusCode = getAwardDocumentStatus(currentAmountInfo.awardId, 
									selectAwardDocumentNumber, validateDocumentStatus);
							if (docHeaderStatusCode == null) {
								LOG.severe("Unable to find award record associated with award_id " + 
										currentAmountInfo.awardId + " refusing to consider the award_amount_info_id=" + 
										currentAmountInfo.awardAmountInfoId + " for dups status");
							} else {
								if (UNSUCCESSFUL_STATUSES.contains(docHeaderStatusCode)) {
									LOG.info("Document status for award_id " + currentAmountInfo.awardId + " indicates it was canceled. Ignoring award_amount_info records.");
								} else {
									currentAmountInfo.docStatus = docHeaderStatusCode;
									amountInfos.put(currentAmountInfo.transactionId, currentAmountInfo);
								}
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		LOG.info("Removed " + numberOfDupsRemoved + " duplicate award_amount_info transactions removed from previous award versions.");
		if (!dupsNotEqual.isEmpty()) {
			List<String> potentialAwardsChanged = dupsNotEqual.stream().map((amountInfo) -> amountInfo.awardNumber).distinct().sorted().collect(Collectors.toList());
			LOG.warning("The following " + dupsNotEqual.size() + " transaction ids had duplicates that were not equal. These have still been removed as they were likely modified in the award after the T&M doc finalization, but confirm that these records still reflect correct amounts. " +
					dupsNotEqual.stream().map((amountInfo) -> String.valueOf(amountInfo.transactionId)).collect(Collectors.joining(", ")));
			LOG.warning("The following " + potentialAwardsChanged.size() + " awards had transactions removed that were different. Verify they are still correct. (" + 
					potentialAwardsChanged.stream().collect(Collectors.joining(", ")) + ")");
		}
		if (!totalsNotEqual.isEmpty()) {
			List<String> potentialAwardsChanged = totalsNotEqual.stream().map((amountInfo) -> amountInfo.awardNumber).distinct().sorted().collect(Collectors.toList());
			LOG.warning("The following " + totalsNotEqual.size() + " transaction ids had duplicates where the total amounts were not equal. These have still been removed as they were likely modified in the award after the T&M doc finalization, but confirm that these records still reflect correct amounts. " +
				totalsNotEqual.stream().map((amountInfo) -> String.valueOf(amountInfo.transactionId)).collect(Collectors.joining(", ")));
			LOG.warning("The following " + potentialAwardsChanged.size() + " awards had transactions removed where the totals were not equal. Verify they are still correct. (" + 
					potentialAwardsChanged.stream().collect(Collectors.joining(", ")) + ")");	
		}
		if (!amountInfosAffected.isEmpty()) {
			LOG.warning("The following awards were affected by duplicate transactions. (" + amountInfosAffected.stream().map(amountInfo -> amountInfo.awardNumber).collect(Collectors.joining(", ")) + ")");
		}
	}


	protected void updateNewerAmountInfoChangeAmnts(final AmountInfo newerAmountInfo, AmountInfo currentAmountInfo,
		PreparedStatement updateAwardAmountInfoChangeAmnts) throws SQLException {
		updateAwardAmountInfoChangeAmnts.setBigDecimal(1, currentAmountInfo.anticipatedChange);
		updateAwardAmountInfoChangeAmnts.setBigDecimal(2, currentAmountInfo.anticipatedChangeDirect);
		updateAwardAmountInfoChangeAmnts.setBigDecimal(3, currentAmountInfo.anticipatedChangeIndirect);
		updateAwardAmountInfoChangeAmnts.setBigDecimal(4, currentAmountInfo.obligatedChange);
		updateAwardAmountInfoChangeAmnts.setBigDecimal(5, currentAmountInfo.obligatedChangeDirect);
		updateAwardAmountInfoChangeAmnts.setBigDecimal(6, currentAmountInfo.obligatedChangeIndirect);
		updateAwardAmountInfoChangeAmnts.setBigDecimal(7, currentAmountInfo.anticipatedDistributableAmount);
		updateAwardAmountInfoChangeAmnts.setBigDecimal(8, currentAmountInfo.obligatedDistributableAmount);
		updateAwardAmountInfoChangeAmnts.setLong(9, newerAmountInfo.awardAmountInfoId);
		updateAwardAmountInfoChangeAmnts.execute();
	}


	protected void deleteAmountInfo(AmountInfo currentAmountInfo, PreparedStatement deleteAmountInfo, PreparedStatement insertDupedRecord)
		throws SQLException {
		insertDupedRecord.setLong(1, currentAmountInfo.awardAmountInfoId);
		insertDupedRecord.executeUpdate();
		
		deleteAmountInfo.setLong(1, currentAmountInfo.awardAmountInfoId);
		deleteAmountInfo.executeUpdate();
	}


	protected void updateNewerAmountInfoDates(final AmountInfo newerAmountInfo, AmountInfo olderAmountInfo,
		PreparedStatement updateAwardAmountInfoDates) throws SQLException {
		updateAwardAmountInfoDates.setDate(1, olderAmountInfo.finalExpirationDate);
		updateAwardAmountInfoDates.setDate(2, olderAmountInfo.currentFundEffectiveDate);
		updateAwardAmountInfoDates.setDate(3, olderAmountInfo.obligationExpirationDate);
		updateAwardAmountInfoDates.setLong(4, newerAmountInfo.awardAmountInfoId);
		updateAwardAmountInfoDates.execute();
	}


	String getAwardDocumentStatus(final Long awardId, PreparedStatement selectAwardDocumentNumber,
			PreparedStatement validateDocumentStatus) throws SQLException {
		selectAwardDocumentNumber.setLong(1, awardId);
		String documentNumber = null;
		String docHeaderStatusCode = null;
		try (ResultSet rs = selectAwardDocumentNumber.executeQuery()) {
			if (rs.next()) {
				documentNumber = rs.getString(1);
			}
		}
		if (documentNumber != null) {
			validateDocumentStatus.setString(1, documentNumber);
			try (ResultSet rs = validateDocumentStatus.executeQuery()) {
				if (rs.next()) {
					docHeaderStatusCode = rs.getString(1);
				}
			}
		}
		return docHeaderStatusCode;
	}


	public ConnectionDaoService getConnectionDaoService() {
		return connectionDaoService;
	}


	public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
		this.connectionDaoService = connectionDaoService;
	}

	static class AmountInfo {
		Long awardAmountInfoId;
		Long awardId;
		String awardNumber;
		Integer sequenceNumber;
		String tnmDocumentNumber;
		BigDecimal anticipatedChangeDirect;
		BigDecimal anticipatedChangeIndirect;
		BigDecimal anticipatedTotalDirect;
		BigDecimal anticipatedTotalIndirect;
		BigDecimal anticipatedTotalAmount;
		BigDecimal anticipatedDistributableAmount;
		BigDecimal anticipatedChange;
		BigDecimal obligatedTotalDirect;
		BigDecimal obligatedTotalIndirect;
		BigDecimal amountObligatedToDate;
		BigDecimal obligatedDistributableAmount;
		BigDecimal obligatedChange;
		BigDecimal obligatedChangeDirect;
		BigDecimal obligatedChangeIndirect;
		Date finalExpirationDate;
		Date currentFundEffectiveDate;
		Date obligationExpirationDate;
		Long transactionId;
		String entryType;
		String eomProcessFlag;
		String docStatus;
		
		public AmountInfo(ResultSet rs) throws SQLException {
			awardAmountInfoId = rs.getLong(1);
			awardId = rs.getLong(2);
			awardNumber = rs.getString(3);
			sequenceNumber = rs.getInt(4);
			tnmDocumentNumber = rs.getString(5);
			anticipatedChangeDirect = rs.getBigDecimal(6);
			anticipatedChangeIndirect = rs.getBigDecimal(7);
			anticipatedTotalDirect = rs.getBigDecimal(8);
			anticipatedTotalIndirect = rs.getBigDecimal(9);
			anticipatedTotalAmount = rs.getBigDecimal(10);
			anticipatedDistributableAmount = rs.getBigDecimal(11);
			anticipatedChange = rs.getBigDecimal(12);
			obligatedTotalDirect = rs.getBigDecimal(13);
			obligatedTotalIndirect = rs.getBigDecimal(14);
			amountObligatedToDate = rs.getBigDecimal(15);
			obligatedDistributableAmount = rs.getBigDecimal(16);
			obligatedChange = rs.getBigDecimal(17);
			obligatedChangeDirect = rs.getBigDecimal(18);
			obligatedChangeIndirect = rs.getBigDecimal(19);
			finalExpirationDate = rs.getDate(20);
			currentFundEffectiveDate = rs.getDate(21);
			obligationExpirationDate = rs.getDate(22);
			transactionId = rs.getLong(23);
			entryType = rs.getString(24);
			eomProcessFlag = rs.getString(25);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((amountObligatedToDate == null) ? 0 : amountObligatedToDate.hashCode());
			result = prime * result + ((anticipatedChange == null) ? 0 : anticipatedChange.hashCode());
			result = prime * result + ((anticipatedChangeDirect == null) ? 0 : anticipatedChangeDirect.hashCode());
			result = prime * result + ((anticipatedChangeIndirect == null) ? 0 : anticipatedChangeIndirect.hashCode());
			result = prime * result
					+ ((anticipatedDistributableAmount == null) ? 0 : anticipatedDistributableAmount.hashCode());
			result = prime * result + ((anticipatedTotalAmount == null) ? 0 : anticipatedTotalAmount.hashCode());
			result = prime * result + ((anticipatedTotalDirect == null) ? 0 : anticipatedTotalDirect.hashCode());
			result = prime * result + ((anticipatedTotalIndirect == null) ? 0 : anticipatedTotalIndirect.hashCode());
			result = prime * result + ((awardAmountInfoId == null) ? 0 : awardAmountInfoId.hashCode());
			result = prime * result + ((currentFundEffectiveDate == null) ? 0 : currentFundEffectiveDate.hashCode());
			result = prime * result + ((entryType == null) ? 0 : entryType.hashCode());
			result = prime * result + ((eomProcessFlag == null) ? 0 : eomProcessFlag.hashCode());
			result = prime * result + ((finalExpirationDate == null) ? 0 : finalExpirationDate.hashCode());
			result = prime * result + ((obligatedChange == null) ? 0 : obligatedChange.hashCode());
			result = prime * result + ((obligatedChangeDirect == null) ? 0 : obligatedChangeDirect.hashCode());
			result = prime * result + ((obligatedChangeIndirect == null) ? 0 : obligatedChangeIndirect.hashCode());
			result = prime * result
					+ ((obligatedDistributableAmount == null) ? 0 : obligatedDistributableAmount.hashCode());
			result = prime * result + ((obligatedTotalDirect == null) ? 0 : obligatedTotalDirect.hashCode());
			result = prime * result + ((obligatedTotalIndirect == null) ? 0 : obligatedTotalIndirect.hashCode());
			result = prime * result + ((obligationExpirationDate == null) ? 0 : obligationExpirationDate.hashCode());
			result = prime * result + ((tnmDocumentNumber == null) ? 0 : tnmDocumentNumber.hashCode());
			return result;
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
			if (amountObligatedToDate == null) {
				if (other.amountObligatedToDate != null)
					return false;
			} else if (!amountObligatedToDate.equals(other.amountObligatedToDate))
				return false;
			if (anticipatedChange == null) {
				if (other.anticipatedChange != null)
					return false;
			} else if (!anticipatedChange.equals(other.anticipatedChange))
				return false;
			if (anticipatedChangeDirect == null) {
				if (other.anticipatedChangeDirect != null)
					return false;
			} else if (!anticipatedChangeDirect.equals(other.anticipatedChangeDirect))
				return false;
			if (anticipatedChangeIndirect == null) {
				if (other.anticipatedChangeIndirect != null)
					return false;
			} else if (!anticipatedChangeIndirect.equals(other.anticipatedChangeIndirect))
				return false;
			if (anticipatedDistributableAmount == null) {
				if (other.anticipatedDistributableAmount != null)
					return false;
			} else if (!anticipatedDistributableAmount.equals(other.anticipatedDistributableAmount))
				return false;
			if (anticipatedTotalAmount == null) {
				if (other.anticipatedTotalAmount != null)
					return false;
			} else if (!anticipatedTotalAmount.equals(other.anticipatedTotalAmount))
				return false;
			if (anticipatedTotalDirect == null) {
				if (other.anticipatedTotalDirect != null)
					return false;
			} else if (!anticipatedTotalDirect.equals(other.anticipatedTotalDirect))
				return false;
			if (anticipatedTotalIndirect == null) {
				if (other.anticipatedTotalIndirect != null)
					return false;
			} else if (!anticipatedTotalIndirect.equals(other.anticipatedTotalIndirect))
				return false;
			if (currentFundEffectiveDate == null) {
				if (other.currentFundEffectiveDate != null)
					return false;
			} else if (!currentFundEffectiveDate.equals(other.currentFundEffectiveDate))
				return false;
			if (entryType == null) {
				if (other.entryType != null)
					return false;
			} else if (!entryType.equals(other.entryType))
				return false;
			if (eomProcessFlag == null) {
				if (other.eomProcessFlag != null)
					return false;
			} else if (!eomProcessFlag.equals(other.eomProcessFlag))
				return false;
			if (finalExpirationDate == null) {
				if (other.finalExpirationDate != null)
					return false;
			} else if (!finalExpirationDate.equals(other.finalExpirationDate))
				return false;
			if (obligatedChange == null) {
				if (other.obligatedChange != null)
					return false;
			} else if (!obligatedChange.equals(other.obligatedChange))
				return false;
			if (obligatedChangeDirect == null) {
				if (other.obligatedChangeDirect != null)
					return false;
			} else if (!obligatedChangeDirect.equals(other.obligatedChangeDirect))
				return false;
			if (obligatedChangeIndirect == null) {
				if (other.obligatedChangeIndirect != null)
					return false;
			} else if (!obligatedChangeIndirect.equals(other.obligatedChangeIndirect))
				return false;
			if (obligatedDistributableAmount == null) {
				if (other.obligatedDistributableAmount != null)
					return false;
			} else if (!obligatedDistributableAmount.equals(other.obligatedDistributableAmount))
				return false;
			if (obligatedTotalDirect == null) {
				if (other.obligatedTotalDirect != null)
					return false;
			} else if (!obligatedTotalDirect.equals(other.obligatedTotalDirect))
				return false;
			if (obligatedTotalIndirect == null) {
				if (other.obligatedTotalIndirect != null)
					return false;
			} else if (!obligatedTotalIndirect.equals(other.obligatedTotalIndirect))
				return false;
			if (obligationExpirationDate == null) {
				if (other.obligationExpirationDate != null)
					return false;
			} else if (!obligationExpirationDate.equals(other.obligationExpirationDate))
				return false;
			if (tnmDocumentNumber == null) {
				if (other.tnmDocumentNumber != null)
					return false;
			} else if (!tnmDocumentNumber.equals(other.tnmDocumentNumber))
				return false;
			return true;
		}
		
		public boolean createdBySameTMDoc(AmountInfo other) {
			if (tnmDocumentNumber == null) {
				if (other.tnmDocumentNumber != null)
					return false;
			} else if (!tnmDocumentNumber.equals(other.tnmDocumentNumber))
				return false;
			return true;
		}
		
		public boolean totalAmountsEqual(AmountInfo other) {
			if (amountObligatedToDate == null) {
				if (other.amountObligatedToDate != null)
					return false;
			} else if (!amountObligatedToDate.equals(other.amountObligatedToDate))
				return false;
			if (anticipatedTotalAmount == null) {
				if (other.anticipatedTotalAmount != null)
					return false;
			} else if (!anticipatedTotalAmount.equals(other.anticipatedTotalAmount))
				return false;
			if (anticipatedTotalDirect == null) {
				if (other.anticipatedTotalDirect != null)
					return false;
			} else if (!anticipatedTotalDirect.equals(other.anticipatedTotalDirect))
				return false;
			if (anticipatedTotalIndirect == null) {
				if (other.anticipatedTotalIndirect != null)
					return false;
			} else if (!anticipatedTotalIndirect.equals(other.anticipatedTotalIndirect))
				return false;
			if (obligatedTotalDirect == null) {
				if (other.obligatedTotalDirect != null)
					return false;
			} else if (!obligatedTotalDirect.equals(other.obligatedTotalDirect))
				return false;
			if (obligatedTotalIndirect == null) {
				if (other.obligatedTotalIndirect != null)
					return false;
			} else if (!obligatedTotalIndirect.equals(other.obligatedTotalIndirect))
				return false;
			return true;
		}
	}
	
}	
