package co.kuali.coeus.data.migration.custom.coeus;


import co.kuali.coeus.data.migration.custom.CoeusConnectionDao;
import co.kuali.coeus.data.migration.custom.RiceAwareSqlExecutor;
import org.kuali.coeus.dc.subaward.status.SubawardStatusDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class V1610_005__FixSubawardStatus extends RiceAwareSqlExecutor {
    @Override
    public boolean executeInTransaction() {
        return true;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        try (Connection riceConnection = riceDataSource.getConnection()){
            final CoeusConnectionDao connDao = new CoeusConnectionDao(connection, riceConnection);

            final SubawardStatusDaoImpl ssdao = new SubawardStatusDaoImpl();
            ssdao.setConnectionDaoService(connDao);

            ssdao.fixSubawardStatus();
        }
    }
}
