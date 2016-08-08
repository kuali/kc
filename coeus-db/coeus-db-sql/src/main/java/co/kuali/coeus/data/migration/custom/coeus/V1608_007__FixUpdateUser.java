package co.kuali.coeus.data.migration.custom.coeus;


import co.kuali.coeus.data.migration.custom.CoeusConnectionDao;
import co.kuali.coeus.data.migration.custom.RiceAwareSqlExecutor;
import org.kuali.coeus.dc.award.amntinfo.AwardAmountInfoDuplicatesDaoImpl;
import org.kuali.coeus.dc.updateuser.LastActionUserDaoImpl;
import org.kuali.coeus.dc.updateuser.award.AwardUpdateUserDaoImpl;
import org.kuali.coeus.dc.updateuser.ip.InstitutionalProposalUpdateUserDaoImpl;
import org.kuali.coeus.dc.updateuser.subaward.SubawardUpdateUserDaoImpl;
import org.kuali.coeus.dc.updateuser.tm.TimeAndMoneyUpdateUserDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class V1608_007__FixUpdateUser extends RiceAwareSqlExecutor {
    @Override
    public boolean executeInTransaction() {
        return true;
    }

    @Override
    public void execute(Connection connection) throws SQLException {
        try (Connection riceConnection = riceDataSource.getConnection()){
            final CoeusConnectionDao connDao = new CoeusConnectionDao(connection, riceConnection);

            final LastActionUserDaoImpl lauDao = new LastActionUserDaoImpl();
            lauDao.setConnectionDaoService(connDao);

            final AwardUpdateUserDaoImpl auuDao = new AwardUpdateUserDaoImpl();
            auuDao.setConnectionDaoService(connDao);
            auuDao.setLastActionUserDao(lauDao);

            final TimeAndMoneyUpdateUserDaoImpl tamuuDao = new TimeAndMoneyUpdateUserDaoImpl();
            tamuuDao.setConnectionDaoService(connDao);
            tamuuDao.setLastActionUserDao(lauDao);

            final InstitutionalProposalUpdateUserDaoImpl ipuuDao = new InstitutionalProposalUpdateUserDaoImpl();
            ipuuDao.setConnectionDaoService(connDao);
            ipuuDao.setLastActionUserDao(lauDao);

            final SubawardUpdateUserDaoImpl sauu = new SubawardUpdateUserDaoImpl();
            sauu.setConnectionDaoService(connDao);
            sauu.setLastActionUserDao(lauDao);

            auuDao.fixUpdateUsers();
            tamuuDao.fixUpdateUsers();
            ipuuDao.fixUpdateUsers();
            sauu.fixUpdateUsers();
        }
    }
}
