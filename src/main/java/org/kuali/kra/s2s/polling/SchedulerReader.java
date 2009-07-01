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
package org.kuali.kra.s2s.polling;

import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.kuali.kra.s2s.S2SException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 * This class parses the configuration file S2SScheduler.xml and passes all the tasks to be scheduled to the SchedulerServiceImpl
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class SchedulerReader {
    private static Hashtable<String, Element> scheduleNodes;
    private static final String CONFIG_FILE_NAME = "/S2SScheduler.xml";

    /** Creates a new instance of GGPollingReader */
    public SchedulerReader() {
    }

    /**
     * 
     * This method checks if the configuration file has been loaded into collection class. If its loaded it returns the collection.
     * Otherwise, it will load the collection class with the configuration file node and returns it.
     * 
     * @return
     * @throws S2SException
     */
    public static Hashtable<String, Element> getScheduleNodes() throws S2SException {
        if (scheduleNodes == null) {
            synchronized (SchedulerReader.class) {
                loadBindings();
            }
        }
        return scheduleNodes;
    }

    /**
     * 
     * This method parses and loads the configuration file information into the collection class.
     * 
     * @throws S2SException
     */
    private static void loadBindings() throws S2SException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new SchedulerReader().getClass().getResourceAsStream(CONFIG_FILE_NAME));

        }
        catch (Exception ex) {
            throw new S2SException(ex.getMessage());
        }
        Element docElement = document.getDocumentElement();
        NodeList childNodes = docElement.getChildNodes();
        scheduleNodes = new Hashtable<String, Element>();
        for (int index = 0; index < childNodes.getLength(); index++) {
            if (!(childNodes.item(index) instanceof Element)){                
                continue;
            }
            Element childNode = (Element) childNodes.item(index);
            scheduleNodes.put(childNode.getNodeName(), childNode);
        }
    }
}
