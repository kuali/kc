package co.kuali.coeus.data.migration.custom.coeus;


import co.kuali.coeus.data.migration.custom.CoeusConnectionDao;
import co.kuali.coeus.data.migration.custom.SqlExecutor;

import java.sql.Connection;
import java.sql.SQLException;

import org.kuali.coeus.dc.subaward.amntinfo.SubAwardAmountInfoDaoImpl;

public class V1511_014__SubAwardAmountInfoConversion implements SqlExecutor {
    @Override
    public boolean executeInTransaction() {
        return true;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
    	CoeusConnectionDao connDao = new CoeusConnectionDao(connection, null);
        SubAwardAmountInfoDaoImpl subAwardDao = new SubAwardAmountInfoDaoImpl();
        subAwardDao.setConnectionDaoService(connDao);
        subAwardDao.fixSubAwardAmountInfoHistory();
    }
}
