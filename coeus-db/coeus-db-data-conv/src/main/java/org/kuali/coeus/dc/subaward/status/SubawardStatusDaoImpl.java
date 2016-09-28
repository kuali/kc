package org.kuali.coeus.dc.subaward.status;


import org.kuali.coeus.dc.common.db.ConnectionDaoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.kuali.coeus.dc.common.db.PreparedStatementUtils.*;

public class SubawardStatusDaoImpl implements SubawardStatusDao {


    private static final Logger LOG = Logger.getLogger(SubawardStatusDaoImpl.class.getName());

    private static final String ALL_DISTINCT_SUBAWARDS = "select distinct subaward_code from subaward t";

    private static final String BACKUP_SUBAWARD = "CREATE TABLE SUBAWARD_BAK_1610_005 AS SELECT * FROM SUBAWARD";
    private static final String BACKUP_VERSION_HISTORY = "CREATE TABLE VH_SUBAWARD_BAK_1610_005 AS SELECT * FROM VERSION_HISTORY WHERE SEQ_OWNER_CLASS_NAME = 'org.kuali.kra.subaward.bo.SubAward'";

    private static final String HIGHEST_SEQ_SUBAWARD_DOC_NUM = "select t.DOCUMENT_NUMBER, t.sequence_number, t.SUBAWARD_SEQUENCE_STATUS from subaward t where " +
            "t.sequence_number = (select max(sequence_number) from subaward u where u.subaward_code = t.subaward_code) and " +
            "t.subaward_code = ?";

    private static final String KEW_DOC_STATUS = "select DOC_HDR_STAT_CD from KREW_DOC_HDR_T where DOC_HDR_ID = ?";

    private static final String UPDATE_SINGLE_SEQ = "update subaward set SUBAWARD_SEQUENCE_STATUS = ? where sequence_number = ? and subaward_code = ?";
    private static final String UPDATE_LESS_THAN_SEQ = "update subaward set SUBAWARD_SEQUENCE_STATUS = ? where sequence_number < ? and subaward_code = ?";

    private static final String UPDATE_SINGLE_SEQ_VH = "update VERSION_HISTORY set VERSION_STATUS = ? where SEQ_OWNER_SEQ_NUMBER = ? and SEQ_OWNER_VERSION_NAME_VALUE = ? and SEQ_OWNER_CLASS_NAME = 'org.kuali.kra.subaward.bo.SubAward'";
    private static final String UPDATE_LESS_THAN_SEQ_VH = "update VERSION_HISTORY set VERSION_STATUS = ? where SEQ_OWNER_SEQ_NUMBER < ? and SEQ_OWNER_VERSION_NAME_VALUE = ? and SEQ_OWNER_CLASS_NAME = 'org.kuali.kra.subaward.bo.SubAward'";

    private ConnectionDaoService connectionDaoService;

    @Override
    public void fixSubawardStatus() {
        final Connection connection = connectionDaoService.getCoeusConnection();

        try(PreparedStatement statement = connection.prepareStatement(BACKUP_SUBAWARD)) {
            statement.execute();
            LOG.info("created backup of SUBAWARD table");
        } catch (SQLException e) {
            LOG.log(Level.WARNING, "failed to create backup of SUBAWARD table", e);
        }

        try(PreparedStatement statement = connection.prepareStatement(BACKUP_VERSION_HISTORY)) {
            statement.execute();
            LOG.info("created backup of VERSION_HISTORY table for SUBAWARD records");
        } catch (SQLException e) {
            LOG.log(Level.WARNING, "failed to create backup of VERSION_HISTORY table for SUBAWARD records", e);
        }

        try (PreparedStatement subawardsStatement = connection.prepareStatement(ALL_DISTINCT_SUBAWARDS);
             ResultSet subawardsResult = subawardsStatement.executeQuery()) {
            while (subawardsResult.next()) {
                final String subawardCode = subawardsResult.getString(1);
                try (PreparedStatement highestStatement = setString(1, subawardCode, connection.prepareStatement(HIGHEST_SEQ_SUBAWARD_DOC_NUM));
                     ResultSet highestResult = highestStatement.executeQuery()) {
                     if (highestResult.next()) {
                         final String documentNumber = highestResult.getString(1);
                         final int sequenceNumber = highestResult.getInt(2);
                         final String currentStatus = highestResult.getString(3);
                         final String kewStatus = getKewStatus(documentNumber);

                         if ("F".equals(kewStatus) || "P".equals(kewStatus) || kewStatus == null) {
                             if (!"ACTIVE".equals(currentStatus)) {
                                 updateSingleSeq(subawardCode, sequenceNumber, "ACTIVE");
                             }
                             updateLessThanSeq(subawardCode, sequenceNumber, "ARCHIVED");
                         } else if ("D".equals(kewStatus) || "X".equals(kewStatus) || "C".equals(kewStatus)) {
                             if (!"CANCELED".equals(currentStatus)) {
                                 updateSingleSeq(subawardCode, sequenceNumber, "CANCELED");
                             }
                             updateSingleSeq(subawardCode, sequenceNumber - 1, "ACTIVE");
                             updateLessThanSeq(subawardCode, sequenceNumber - 1, "ARCHIVED");
                         } else {
                             if (!"PENDING".equals(currentStatus)) {
                                 updateSingleSeq(subawardCode, sequenceNumber, "PENDING");
                             }
                             updateSingleSeq(subawardCode, sequenceNumber - 1, "ACTIVE");
                             updateLessThanSeq(subawardCode, sequenceNumber - 1, "ARCHIVED");
                         }

                     }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateSingleSeq(String subawardCode, int sequenceNumber, String status) {
        updateSeq(subawardCode, sequenceNumber, status, UPDATE_SINGLE_SEQ, UPDATE_SINGLE_SEQ_VH);
    }

    private void updateLessThanSeq(String subawardCode, int sequenceNumber, String status) {
        updateSeq(subawardCode, sequenceNumber, status, UPDATE_LESS_THAN_SEQ, UPDATE_LESS_THAN_SEQ_VH);
    }

    private void updateSeq(String subawardCode, int sequenceNumber, String status, String query, String vhQuery) {
        final Connection connection = connectionDaoService.getCoeusConnection();
        try (PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatement vhStatement = connection.prepareStatement(vhQuery)) {
            statement.setString(1, status);
            statement.setInt(2, sequenceNumber);
            statement.setString(3, subawardCode);
            statement.executeUpdate();

            vhStatement.setString(1, status);
            vhStatement.setInt(2, sequenceNumber);
            vhStatement.setString(3, subawardCode);
            vhStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getKewStatus(String documentNumber) {
        if (documentNumber == null || documentNumber.trim().equals("")) {
            return null;
        }

        final Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement statement = setString(1, documentNumber, connection.prepareStatement(KEW_DOC_STATUS));
            ResultSet result = statement.executeQuery()) {
            if (result.next()) {
                return result.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public ConnectionDaoService getConnectionDaoService() {
        return connectionDaoService;
    }

    public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
        this.connectionDaoService = connectionDaoService;
    }
}
