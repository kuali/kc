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

    private static final String HIGHEST_SEQ_SUBAWARD_DOC_NUM = "select t.DOCUMENT_NUMBER, t.sequence_number from subaward t where " +
            "t.sequence_number = (select max(sequence_number) from subaward u where u.subaward_code = t.subaward_code) and " +
            "t.subaward_code = ?";

    private static final String SUBAWARDS_LESS_THAN_SEQ = "select t.DOCUMENT_NUMBER, t.sequence_number from subaward t where " +
            "sequence_number < ? and subaward_code = ? order by t.SEQUENCE_NUMBER desc";

    private static final String KEW_DOC_STATUS = "select DOC_HDR_STAT_CD from KREW_DOC_HDR_T where DOC_HDR_ID = ?";

    private static final String UPDATE_SINGLE_SEQ = "update subaward set SUBAWARD_SEQUENCE_STATUS = ? where sequence_number = ? and subaward_code = ?";

    private static final String UPDATE_SINGLE_SEQ_VH = "update VERSION_HISTORY set VERSION_STATUS = ? where SEQ_OWNER_SEQ_NUMBER = ? and SEQ_OWNER_VERSION_NAME_VALUE = ? and SEQ_OWNER_CLASS_NAME = 'org.kuali.kra.subaward.bo.SubAward'";

    private static final String FINAL_CD = "F";
    private static final String PROCESSED_CD = "P";
    private static final String DISAPPROVED_CD = "D";
    private static final String CANCELED_CD = "X";
    private static final String CANCELED_DISAPPROVED_CD = "C";
    private static final String ACTIVE = "ACTIVE";
    private static final String CANCELED = "CANCELED";
    private static final String PENDING = "PENDING";
    private static final String ARCHIVED = "ARCHIVED";

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

                         final String kewStatus = getKewStatus(documentNumber);
                         if (FINAL_CD.equals(kewStatus) || PROCESSED_CD.equals(kewStatus) || kewStatus == null) {
                             updateSingleSeq(subawardCode, sequenceNumber, ACTIVE);
                             updateLessThanSeq(subawardCode, sequenceNumber, false);
                         } else if (DISAPPROVED_CD.equals(kewStatus) || CANCELED_CD.equals(kewStatus) || CANCELED_DISAPPROVED_CD.equals(kewStatus)) {
                             updateSingleSeq(subawardCode, sequenceNumber, CANCELED);
                             updateLessThanSeq(subawardCode, sequenceNumber, true);
                         } else {
                             updateSingleSeq(subawardCode, sequenceNumber, PENDING);
                             updateLessThanSeq(subawardCode, sequenceNumber, true);
                         }

                     }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateSingleSeq(String subawardCode, int sequenceNumber, String status) {
        final Connection connection = connectionDaoService.getCoeusConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SINGLE_SEQ);
             PreparedStatement vhStatement = connection.prepareStatement(UPDATE_SINGLE_SEQ_VH)) {
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

    private void updateLessThanSeq(String subawardCode, int sequenceNumber, boolean findActive) {
        final Connection connection = connectionDaoService.getCoeusConnection();
        try (PreparedStatement statement = setString(2, subawardCode, setInt(1, sequenceNumber, connection.prepareStatement(SUBAWARDS_LESS_THAN_SEQ)));
            ResultSet result = statement.executeQuery()) {
            boolean activeFound = false;
            while(result.next()) {
                final String currentDocumentNumber = result.getString(1);
                final int currentSequenceNumber = result.getInt(2);
                final String kewStatus = getKewStatus(currentDocumentNumber);
                if ((FINAL_CD.equals(kewStatus) || PROCESSED_CD.equals(kewStatus) || kewStatus == null) && (findActive && !activeFound)) {
                    updateSingleSeq(subawardCode, currentSequenceNumber, ACTIVE);
                    activeFound = true;
                } else if (DISAPPROVED_CD.equals(kewStatus) || CANCELED_CD.equals(kewStatus) || CANCELED_DISAPPROVED_CD.equals(kewStatus)) {
                    updateSingleSeq(subawardCode, currentSequenceNumber, CANCELED);
                } else {
                    updateSingleSeq(subawardCode, currentSequenceNumber, ARCHIVED);
                }
            }
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
