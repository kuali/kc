package org.kuali.kra.test.infrastructure.test;

import java.util.List;

import org.junit.Test;
import org.kuali.kra.bo.MessageOfTheDay;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.MessageOfTheDayService;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.service.BusinessObjectService;

public class DbTransactionTest extends KcUnitTestBase {
    private static final String MOTD_1 = "motd1";
    private static final String MOTD_2 = "motd2";
 
    @Test
    public void testRollback1() throws Throwable {
        rollbackTest(MOTD_1, MOTD_2);
    }
    
    @Test
    public void testRollback2() throws Throwable {
        rollbackTest(MOTD_2, MOTD_1);
    }
    
    public void rollbackTest(String newMessage, String oldMessage) {
        boolean containsNew = false;
        boolean containsOld = false;
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        MessageOfTheDayService motdService = KraServiceLocator.getService(MessageOfTheDayService.class);
        MessageOfTheDay newMotd = new MessageOfTheDay();
        newMotd.setMessage(newMessage);
        newMotd.setDisplayOrder(0L);
        newMotd.setActive(true);
        boService.save(newMotd);
        List<MessageOfTheDay> motds = motdService.getMessagesOfTheDay();
        assertTrue("BO did not save", !motds.isEmpty());
        for (MessageOfTheDay motd : motds) {
            if (newMessage.equals(motd.getMessage())) {
                containsNew = true;
            }
            else if (oldMessage.equals(motd.getMessage())) {
                containsOld = true;
            }
            if (containsNew && containsOld) break;
        }
        assertTrue("BO did not save", containsNew);
        assertFalse("Previous test rollback failed", containsOld);
    }
}