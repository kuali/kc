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
package org.kuali.kra.infrastructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.kuali.kra.KraTestBase;

import edu.iu.uis.eden.KEWServiceLocator;
import edu.iu.uis.eden.batch.FileXmlDocCollection;
import edu.iu.uis.eden.batch.XmlDoc;
import edu.iu.uis.eden.batch.XmlDocCollection;
import edu.iu.uis.eden.clientapp.WorkflowDocument;
import edu.iu.uis.eden.clientapp.vo.NetworkIdVO;
import edu.iu.uis.eden.exception.WorkflowException;
import edu.iu.uis.eden.exception.WorkflowRuntimeException;

public class KraHierarchyProviderWithMetaRuleTest extends KraTestBase  {
    // this matches the hierarchy of meta-rules defined
    // in KRAMetaRuleHierarchy.xml

//    private static final String KRA_TEST_UNIT_HIERARCHY =
//        "<stop id=\"000000\">" +
//          "<stop id=\"000001\">" +
//            "<stop id=\"IN-UNIV\">" +
//
//                "<stop id=\"BL-BL\">" +
//                    "<stop id=\"BL-RUGS\">" +
//                      "<stop id=\"BL-RCEN\">" +
//                        "<stop id=\"BL-IIDC\" />" +
//                      "</stop>" +
//                  "</stop>" +
//                "</stop>" +
//
//                "<stop id=\"IN-IN\">" +
//
//                    "<stop id=\"IN-MED\">" +
//                      "<stop id=\"IN-MDEP\">" +
//                        "<stop id=\"IN-CARD\">" +
//                          "<stop id=\"IN-CARR\" />" +
//                        "</stop>" +
//                    "</stop>" +
//
//                      "<stop id=\"IN-PED\">" +
//                         "<stop id=\"IN-PERS\" />" +
//                      "</stop>" +
//
//                  "</stop>" +
//                "</stop>" +
//
//             "</stop>" +
//          "</stop>" +
//        "</stop>";

    protected void approve(String user, Long docId) throws WorkflowException {
        log.info("Approving as " + user);
        WorkflowDocument doc = new WorkflowDocument(new NetworkIdVO(user), docId);
        doc.approve("approving as " + user);
    }

    @Test
    public void test() throws WorkflowException {
        //loadXmlFile("DefaultKewTestData.xml");
//        loadXmlFile("KRAMetaRuleHierarchy.xml");
//        
//        WorkflowDocument doc = new WorkflowDocument(new NetworkIdVO("quickstart"), "KRAMetaRuleHierarchyTest");
//        
//        //doc.getDocumentContent().setApplicationContent(HIERARCHY);
//        doc.routeDocument("initial route");
//
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());
//        // user 2 is before user3 because of ordering between business rules included by meta-rule
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "shenl", "jhopf", "ewestfal" }, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user2", "user3", "user1", "arh14", "rkirkend","natjohns", "pmckown", "temay", "dewey", "bmcgough", "jthomas", "xqi" }, false);
//        
//
//        // BL-IIDC
//        approve("shenl", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user2", "jhopf", "ewestfal" }, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "shenl", "user3", "user1", "arh14", "rkirkend", "natjohns", "pmckown", "temay", "dewey", "bmcgough", "jthomas","xqi" }, false);
//        
//        // IN-CARR
//        approve("jhopf", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user1", "user2", "ewestfal"}, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "jhopf", "user3", "shenl", "rkirkend", "arh14", "natjohns", "pmckown", "temay", "dewey", "bmcgough", "jthomas","xqi" }, false);
//        
//        // IN-PERS
//        approve("ewestfal", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user1", "user2", "user3" }, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "ewestfal", "arh14", "shenl", "jhopf", "natjohns", "pmckown", "temay", "dewey", "bmcgough", "jthomas","xqi" }, false);
//        
//        // IN-CARD
//        approve("user1", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user2", "user3", "temay"  }, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "rkirkend", "ewestfal", "shenl", "user1", "arh14", "natjohns", "pmckown", "dewey", "bmcgough", "jthomas","xqi" }, false);
//        
//        // IN-PED
//        approve("user3", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user2",  "temay" }, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user3", "rkirkend", "pmckown", "ewestfal", "shenl", "user1","natjohns", "pmckown", "dewey", "bmcgough", "jthomas", "xqi" }, false);
//        // IN-MDEP
//        approve("temay", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user2",  "pmckown" }, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user3", "rkirkend", "ewestfal", "shenl", "user1", "natjohns", "temay", "dewey", "bmcgough", "jthomas","xqi" }, false);
//        // BL-RCEN
//        approve("user2", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "arh14",   "pmckown"}, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user3",  "ewestfal", "rkirkend","shenl", "user2", "user1", "natjohns", "temay", "dewey", "bmcgough", "jthomas","xqi" }, false);
//        
//        // bl-rugs (both arh14 and rkirkend)
//        approve("arh14", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "jitrue", "pmckown" }, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "arh14", "user3","rkirkend", "ewestfal", "natjohns",  "shenl", "user2", "arh14", "temay", "dewey", "bmcgough", "jthomas","xqi" }, false);
//
//        approve("jitrue", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "rkirkend", "pmckown" }, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "arh14", "user3", "ewestfal", "natjohns",  "shenl", "user2", "arh14", "temay", "dewey", "bmcgough", "jthomas","xqi" }, false);
//
////        approve("tbazler", doc.getRouteHeaderId());
////        
////        TestUtilities.logActionRequests(doc.getRouteHeaderId());
////        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "rkirkend", "pmckown" }, true);
////        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "arh14", "user3", "ewestfal", "natjohns",  "shenl", "user2", "arh14", "temay", "dewey", "bmcgough", "jthomas","xqi" }, false);
//
//        approve("rkirkend", doc.getRouteHeaderId());
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "natjohns", "pmckown" }, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "arh14", "rkirkend", "user3", "ewestfal",  "shenl", "user2", "arh14", "temay", "dewey", "bmcgough", "jthomas","xqi" }, false);
//        
//        //bl-bl
//        approve("natjohns", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());        
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] {"pmckown"}, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user1", "bmcgough", "arh14", "user3", "rkirkend", "ewestfal", "shenl", "user2","natjohns", "temay", "dewey", "bmcgough", "jthomas", "xqi" }, false);
//        //in-med
//        approve("pmckown", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());        
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] {"dewey"}, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user1", "bmcgough", "arh14", "user3", "rkirkend", "ewestfal", "shenl", "user2","natjohns", "pmckown", "temay", "xqi", "bmcgough", "jthomas", }, false);
//
//        // in-in
//        approve("dewey", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());        
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "bmcgough"}, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user1", "arh14", "user3", "rkirkend", "ewestfal", "shenl", "user2","natjohns", "pmckown", "temay", "dewey", "xqi", "jthomas", }, false);
//
//        // in-univ
//        approve("bmcgough", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());        
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "jthomas"}, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user1", "arh14", "user3", "rkirkend", "ewestfal", "shenl", "user2","natjohns", "pmckown", "temay", "dewey", "bmcgough", "xqi", }, false);
// 
//        // 000001
//        approve("jthomas", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());        
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "xqi"}, true);
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "user1", "arh14", "user3", "rkirkend", "ewestfal", "shenl", "user2" , "natjohns", "pmckown", "temay", "dewey", "bmcgough", "jthomas"}, false);
//
//        //0000000
//        approve("xqi", doc.getRouteHeaderId());
//        
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());
//        TestUtilities.assertApprovals(doc.getRouteHeaderId(), new String[] { "xqi", "user1", "arh14", "user3", "rkirkend", "ewestfal", "shenl", "user2" }, false);
//
//        TestUtilities.logActionRequests(doc.getRouteHeaderId());
//
//        doc = new WorkflowDocument(new NetworkIdVO("quickstart"), doc.getRouteHeaderId());
//        assertTrue(doc.stateIsFinal());
    }
    
    protected void loadXmlFile(String fileName) {
        if (fileName.indexOf('/') < 0) {
            this.loadXmlFile(getClass(), fileName);
        } else {
            loadXmlStream(getClass().getClassLoader().getResourceAsStream(fileName));
        }
    }

    protected void loadXmlFile(Class clazz, String fileName) {
        InputStream xmlFile = TestUtilities.loadResource(clazz, fileName);
        if (xmlFile == null) {
            throw new WorkflowRuntimeException("Didn't find file " + fileName);
        }
        loadXmlStream(xmlFile);
    }

    protected void loadXmlFileFromFileSystem(String fileName) throws IOException {
        loadXmlStream(new FileInputStream(fileName));
    }

    protected void loadXmlStream(InputStream xmlStream) {
        try {
            List<XmlDocCollection> xmlFiles = new ArrayList<XmlDocCollection>();
            XmlDocCollection docCollection = getFileXmlDocCollection(xmlStream, "WorkflowUnitTestTemp");
            //XmlDocCollection docCollection = new StreamXmlDocCollection(xmlStream);
            xmlFiles.add(docCollection);
            KEWServiceLocator.getXmlIngesterService().ingest(xmlFiles);
            for (Iterator iterator = docCollection.getXmlDocs().iterator(); iterator.hasNext();) {
                XmlDoc doc = (XmlDoc) iterator.next();
                if (!doc.isProcessed()) {
                    fail("Failed to ingest xml doc: " + doc.getName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Caught exception parsing xml file", e);
        }

    }
    protected FileXmlDocCollection getFileXmlDocCollection(InputStream xmlFile, String tempFileName) throws IOException {
        if (xmlFile == null) {
            throw new RuntimeException("Didn't find the xml file " + tempFileName);
        }
        File temp = File.createTempFile(tempFileName, ".xml");
        FileOutputStream fos = new FileOutputStream(temp);
        int data = -1;
        while ((data = xmlFile.read()) != -1) {
            fos.write(data);
        }
        fos.close();
        return new FileXmlDocCollection(temp);
    }

}

