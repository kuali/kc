/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.framework.mail;


import java.util.List;
import java.util.Set;


public interface KcEmailService {

    public void sendEmail(String from, Set<String> toAddresses, String subject, Set<String> ccAddresses,
            Set<String> bccAddresses, String body, boolean htmlMessage);
    
    public void sendEmailWithAttachments(String from, Set<String> toAddresses, String subject, Set<String> ccAddresses,
            Set<String> bccAddresses, String body, boolean htmlMessage, List<EmailAttachment> attachments);
    
    public String getDefaultFromAddress();
}
