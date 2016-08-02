package org.kuali.coeus.dc.updateuser;

import org.kuali.coeus.dc.common.db.ConnectionDaoService;
import org.kuali.coeus.dc.updateuser.award.AwardUpdateUserDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public abstract class AbstractUpdateUserDao implements UpdateUserDao {

    private static final Logger LOG = Logger.getLogger(AwardUpdateUserDaoImpl.class.getName());
    private static final String KR_USER = "kr";

    private ConnectionDaoService connectionDaoService;
    private LastActionUserDao lastActionUserDao;

    private final ConcurrentHashMap<String, LastActionInfo> lastActionInfoCache = new ConcurrentHashMap<>();

    protected boolean useFirstLastActionInfo(LastActionInfo first, LastActionInfo second) {
        if (first == null || KR_USER.equalsIgnoreCase(first.getPrincipalName())) {
            return false;
        } else if (second == null || KR_USER.equalsIgnoreCase(second.getPrincipalName())) {
            return true;
        } else {
            return first.getDate().after(second.getDate());
        }

    }

    protected void executeUserUpdate(String selectSql, String updateSql) {
        Connection connection = connectionDaoService.getCoeusConnection();
        try (PreparedStatement select = connection.prepareStatement(selectSql);
             ResultSet result = select.executeQuery();
             PreparedStatement update = connection.prepareStatement(updateSql)) {

            while (result.next()) {
                final String documentNumber = result.getString(1);
                final String recordKey = result.getString(2);
                final LastActionInfo lastActionInfo = lastActionInfoCache.computeIfAbsent(documentNumber, lastActionUserDao::getLastActionInfo);
                executeUpdate(update, documentNumber, recordKey, lastActionInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void executeUpdate(PreparedStatement update, String documentNumber, String recordKey, LastActionInfo lastActionInfo) throws SQLException {
        if (lastActionInfo != null && lastActionInfo.getPrincipalName() != null && !KR_USER.equalsIgnoreCase(lastActionInfo.getPrincipalName())) {
            update.setString(1, lastActionInfo.getPrincipalName());
            update.setString(2, recordKey);
            update.executeUpdate();
        } else {
            LOG.warning("Cannot find an updated last action user for document " + documentNumber);
        }
    }

    public ConnectionDaoService getConnectionDaoService() {
        return connectionDaoService;
    }

    public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
        this.connectionDaoService = connectionDaoService;
    }

    public LastActionUserDao getLastActionUserDao() {
        return lastActionUserDao;
    }

    public void setLastActionUserDao(LastActionUserDao lastActionUserDao) {
        this.lastActionUserDao = lastActionUserDao;
    }

    public ConcurrentHashMap<String, LastActionInfo> getLastActionInfoCache() {
        return lastActionInfoCache;
    }
}
