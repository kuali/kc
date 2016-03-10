package co.kuali.coeus.data.migration.custom.coeus;


import co.kuali.coeus.data.migration.custom.CoeusConnectionDao;
import co.kuali.coeus.data.migration.custom.RiceAwareSqlExecutor;

import java.sql.Connection;
import java.sql.SQLException;

import org.kuali.coeus.dc.award.amntinfo.AwardAmountInfoDuplicatesDaoImpl;

public class V1603_006__RemoveDuplicateAmountInfos extends RiceAwareSqlExecutor {
    @Override
    public boolean executeInTransaction() {
        return true;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        try (Connection riceConnection = riceDataSource.getConnection()){
            CoeusConnectionDao connDao = new CoeusConnectionDao(connection, riceConnection);
	    	AwardAmountInfoDuplicatesDaoImpl amountInfoDao = new AwardAmountInfoDuplicatesDaoImpl();
	    	amountInfoDao.setConnectionDaoService(connDao);
	        amountInfoDao.fixAwardAmountInfoDuplicates();
        }
    }
}
