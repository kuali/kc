/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.s2s;

import org.kuali.rice.config.Config;
import org.kuali.rice.core.Core;

import edu.mit.coeus.utils.mail.SmtpPropertyFinder;
import edu.mit.coeus.utils.mail.SmtpServerProperties;

/**
 * This class...
 */
public class KualiSmtpPropertyFinder implements SmtpPropertyFinder {

    /**
     * @see edu.mit.coeus.utils.mail.SmtpPropertyFinder#getSmtpServerProperties()
     */
    public SmtpServerProperties getSmtpServerProperties() {
        final Config config = Core.getCurrentContextConfig();
        SmtpServerProperties smtpServerProps = new SmtpServerProperties(){

            public String getMailAuth() {
                return config.getProperty(getMailAuthKey());
            }

            public String getMailAuthKey() {
                return "mail."+getMailTransportProtocol()+".auth";
            }

            public String getMailFrom() {
                return config.getProperty("mail.from");
            }

            public String getMailHost() {
                return config.getProperty(getMailHostKey());
            }

            public String getMailHostKey() {
                return "mail."+getMailTransportProtocol()+".host";
            }

            public String getMailPort() {
                return config.getProperty(getMailPortKey());
            }

            public String getMailPortKey() {
                return "mail."+getMailTransportProtocol()+".port";
            }

            public String getMailTransportProtocol() {
                return config.getProperty("mail.transport.protocol");
            }

            public String getMailUser() {
                return config.getProperty("mail.smtp.user");
            }

            public String getMailUserCredentials() {
                return config.getProperty("mail.user.credentials");
            }

            public String getMailEnabled() {
                return config.getProperty("edu.coeus.mail.enabled");
            }

            public String getMailMode() {
                return config.getProperty("edu.coeus.mail.mode");
            }

            public String getMailTestReceiverId() {
                return config.getProperty("edu.coeus.mail.testReceiverId");
            }
            
        };
        return smtpServerProps;
    }

}
