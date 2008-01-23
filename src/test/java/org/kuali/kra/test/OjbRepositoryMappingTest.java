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
package org.kuali.kra.test;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.helpers.DefaultHandler;
/**
 * Unit Tests for validating an OJB repository XML file. The objective is to validate without initializing OJB. If OJB starts up and the repository.xml
 * file is bad, then it will fast-fail. This is an undesirable effect. What is needed is to know that OJB will fail beforehand.
 * 
 */
public class OjbRepositoryMappingTest {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(OjbRepositoryMappingTest.class);

    @Before
    public void setUp() {
        
    }
    
    @After 
    public void tearDown() {
        
    }
    
    @Test
    public void xmlValidation() throws Exception { 
        LOG.info("Starting XML validation");
        final URL dtdUrl = getClass().getClassLoader().getResource("repository.dtd");
        final URL repositoryUrl = getClass().getClassLoader().getResource("repository.xml");
        
        LOG.info("Found dtd url " + dtdUrl);
        LOG.info("Found repository url " + repositoryUrl);

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setValidating(true);
        saxParserFactory.setNamespaceAware(false);
        
        SAXParser parser = saxParserFactory.newSAXParser();
        parser.parse(repositoryUrl.getFile(), new DefaultHandler());
    }
}
