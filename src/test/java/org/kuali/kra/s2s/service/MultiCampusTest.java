/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.s2s.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.s2s.S2SSSLProtocolSocketFactory;

public class MultiCampusTest extends KraTestBase {
    private static final Logger LOG = Logger.getLogger(MultiCampusTest.class);

    @Test
    public void multiCampusTest() {
        S2SSSLProtocolSocketFactory socketFactory = new S2SSSLProtocolSocketFactory("localhost", true);
        try {
            assertNotNull(socketFactory.createSocket("at07web.grants.gov", 443, InetAddress.getLocalHost(), 443,
                    new HttpConnectionParams()));
        }
        catch (ConnectTimeoutException e) {
            LOG.error(e.getMessage(), e);
        }
        catch (UnknownHostException e) {
            LOG.error(e.getMessage(), e);
        }
        catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
