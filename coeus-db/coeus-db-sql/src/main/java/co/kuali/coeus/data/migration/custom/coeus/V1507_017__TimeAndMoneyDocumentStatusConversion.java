package co.kuali.coeus.data.migration.custom.coeus;


import co.kuali.coeus.data.migration.custom.CoeusConnectionDao;
import co.kuali.coeus.data.migration.custom.RiceAwareSqlExecutor;

import java.sql.Connection;
import java.sql.SQLException;

import org.kuali.coeus.dc.tm.KewDocHeaderDaoImpl;
import org.kuali.coeus.dc.tm.TimeAndMoneyDocumentStatusDaoImpl;

public class V1507_017__TimeAndMoneyDocumentStatusConversion extends RiceAwareSqlExecutor {
    @Override
    public boolean executeInTransaction() {
        return true;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        try (Connection riceConnection = riceDataSource.getConnection()){
            CoeusConnectionDao connDao = new CoeusConnectionDao(connection, riceConnection);

            KewDocHeaderDaoImpl kewDocHeaderDao = new KewDocHeaderDaoImpl();
            kewDocHeaderDao.setConnectionDaoService(connDao);
            
            TimeAndMoneyDocumentStatusDaoImpl dao = new TimeAndMoneyDocumentStatusDaoImpl();
            dao.setConnectionDaoService(connDao);
            dao.setKewDocHeaderDao(kewDocHeaderDao);
            dao.updateTimeAndMoneyDocumentStatusFromKew();
        }
    }
}
