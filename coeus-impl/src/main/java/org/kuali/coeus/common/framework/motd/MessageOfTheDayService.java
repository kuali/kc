/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.framework.motd;

import java.util.List;


public interface MessageOfTheDayService {

    /**
     * The method gets the message of the day.  Only active messages are returned.  Will never return null.
     * The messages will be sorted ascending by display order.
     *
     * @return a list of active messages or an empty list if none are found.
     */
    List<MessageOfTheDay> getMessagesOfTheDay();

 }
