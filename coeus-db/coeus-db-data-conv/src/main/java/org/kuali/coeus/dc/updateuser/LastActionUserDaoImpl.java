package org.kuali.coeus.dc.updateuser;

import org.kuali.coeus.dc.common.db.ConnectionDaoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Logger;

import static org.kuali.coeus.dc.common.db.PreparedStatementUtils.setString;

public class LastActionUserDaoImpl implements LastActionUserDao {
    private static final Logger LOG = Logger.getLogger(LastActionUserDaoImpl.class.getName());

    //highest last action taken for a doc
    private static final String LAST_ACTION_PRINCIPAL_ID_SQL = "SELECT DISTINCT DT.PRNCPL_ID, dt2.ACTN_DT " +
    "FROM KREW_ACTN_TKN_T dt " +
    "INNER JOIN (SELECT DOC_HDR_ID, MAX(ACTN_DT) ACTN_DT " +
    "FROM KREW_ACTN_TKN_T " +
    "GROUP BY DOC_HDR_ID) dt2 ON dt.DOC_HDR_ID = dt2.DOC_HDR_ID AND dt.ACTN_DT = dt2.ACTN_DT " +
    "where dt.DOC_HDR_ID = ?";

    private static final String PRINCIPAL_NM_SQL = "select t.PRNCPL_NM ACTIVE_PRNCPL_NM, u.PRNCPL_NM CACHED_PRNCPL_NM from krim_prncpl_t t " +
            "LEFT OUTER JOIN krim_entity_cache_t u on t.PRNCPL_ID = u.PRNCPL_ID " +
            "where u.PRNCPL_ID = ? or t.PRNCPL_ID = ?" +
            "UNION " +
            "select t.PRNCPL_NM ACTIVE_PRNCPL_NM, u.PRNCPL_NM CACHED_PRNCPL_NM from krim_prncpl_t t " +
            "RIGHT OUTER JOIN krim_entity_cache_t u on t.PRNCPL_ID = u.PRNCPL_ID " +
            "where u.PRNCPL_ID = ? or t.PRNCPL_ID = ?";

    private ConnectionDaoService connectionDaoService;

    @Override
    public LastActionInfo getLastActionInfo(String documentId) {
        Connection connection = connectionDaoService.getRiceConnection();

        LastActionInfo info = new LastActionInfo();
        info.setDocumentId(documentId);

        try (PreparedStatement stmt = setString(1, documentId, connection.prepareStatement(LAST_ACTION_PRINCIPAL_ID_SQL));
             ResultSet result = stmt.executeQuery()) {

            if (result.next()) {
                final Date date = result.getTime(2);
                info.setDate(date);

                final String principalId = result.getString(1);
                if (principalId == null || "".equals(principalId.trim())) {
                    LOG.warning("Last Action PrincipalId is blank in KEW for documentId: " + documentId);
                } else {
                    info.setPrincipalName(getPrincipalName(principalId));
                }
            } else {
                LOG.warning("Last Action PrincipalId is not found in KEW for documentId: " + documentId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return info;
    }

    private String getPrincipalName(String principalId) {
        Connection connection = connectionDaoService.getRiceConnection();

        try (PreparedStatement stmt = setString(4, principalId,
                setString(3, principalId,
                        setString(2, principalId,
                                setString(1, principalId, connection.prepareStatement(PRINCIPAL_NM_SQL)))));
             ResultSet result = stmt.executeQuery()) {

            if (result.next()) {
                final String activePrincipalName = result.getString(1);
                final String cachedPrincipalName = result.getString(2);
                if (activePrincipalName == null || "".equals(activePrincipalName.trim())) {
                    LOG.info("An active principal name is not found for the principal id " + principalId + " checking for a cached principal name");
                    if (cachedPrincipalName == null || "".equals(cachedPrincipalName.trim())) {
                        LOG.info("A cached principal name is not found for the principal id " + principalId);
                    } else {
                        return cachedPrincipalName;
                    }
                } else {
                    return activePrincipalName;
                }
            } else {
                LOG.warning("The principal name could not be found for the principal id " + principalId);
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
