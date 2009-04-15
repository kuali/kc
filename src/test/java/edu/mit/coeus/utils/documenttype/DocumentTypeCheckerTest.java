/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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
package edu.mit.coeus.utils.documenttype;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

public class DocumentTypeCheckerTest {
    private static final String DOCUMENT_TYPE_XML = "documentType.xml";
    private static final String DOCUMENT_TYPE_XML_LOCATION = "edu/mit/coeus/utils/documenttype/" + DOCUMENT_TYPE_XML;
    
    private static final String REGEX_FOR_URL_OF_XML_FILE_IN_JAR = "^jar:file:/.*jar!.*\\.xml$";
    
    @Test
    public void testDocumentTypeXMLResource_XMLFilePathSet() throws Exception {
        Assert.assertNotNull(DOCUMENT_TYPE_XML_LOCATION);
    }
    
    @Test(expected=FileNotFoundException.class)
    public void testDocumentTypeXMLResource_NotAvailableInFileSystem() throws Exception {
        FileReader xmlFileReader = null;
        try {
            xmlFileReader = new FileReader(DOCUMENT_TYPE_XML_LOCATION);
        } finally {
            if(xmlFileReader != null) { xmlFileReader.close(); }
        }
    }
	
    @Test()
    public void testDocumentTypeXMLResource_AvailableInJAR() throws Exception {
        System.out.println(getClass().getClassLoader().getResource(DOCUMENT_TYPE_XML_LOCATION).toExternalForm());
        Assert.assertTrue(getClass().getClassLoader().getResource(DOCUMENT_TYPE_XML_LOCATION).toExternalForm().matches(REGEX_FOR_URL_OF_XML_FILE_IN_JAR));      
    }
    
    @Test()
    public void testDocumentTypeXMLResource_ReadableFromJAR() throws Exception {
        InputStream in = null;
        try {
            in = getClass().getClassLoader().getResourceAsStream(DOCUMENT_TYPE_XML_LOCATION);
            Assert.assertNotNull(in);
        } finally {
            if (in != null) { in.close(); }
        }
    }
}
