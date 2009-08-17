/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.questionnaire;

import org.kuali.rice.kns.lookup.KualiLookupableImpl;

public class QuestionnaireLookupableImpl extends KualiLookupableImpl {

//    @Override
//    public String getCreateNewUrl() {
//        // TODO Auto-generated method stub
//        String url =  super.getCreateNewUrl();
//        String appContext = ConfigContext.getCurrentContextConfig().getProperty(Constants.APP_CONTEXT_KEY);
//        url = url.replace(url.substring(0, url.indexOf("<img")), "<a href=\"/"+appContext+"/questionnaireNew.do\">");
//        return url;
//    }
    public String getCreateNewUrl() {
        String url =  super.getCreateNewUrl();
        url = url.replace("maintenance","../maintenanceQn");
        return url;
    }

}
