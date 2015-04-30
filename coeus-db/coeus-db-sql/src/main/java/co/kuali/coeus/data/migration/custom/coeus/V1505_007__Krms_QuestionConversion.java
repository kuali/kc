package co.kuali.coeus.data.migration.custom.coeus;


import co.kuali.coeus.data.migration.custom.CoeusConnectionDao;
import co.kuali.coeus.data.migration.custom.RiceAwareSqlExecutor;
import org.kuali.coeus.dc.questseq.QuestSeqDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class V1505_007__Krms_QuestionConversion extends RiceAwareSqlExecutor {
    @Override
    public boolean executeInTransaction() {
        return true;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        try (Connection riceConnection = riceDataSource.getConnection()){
            riceConnection.setAutoCommit(false);
            CoeusConnectionDao connDao = new CoeusConnectionDao(connection, riceConnection);

            QuestSeqDaoImpl questSeqDao = new QuestSeqDaoImpl();
            questSeqDao.setConnectionDaoService(connDao);
            questSeqDao.convertQuestSeqKrmsValues();

            riceConnection.commit();
        }
    }
}
