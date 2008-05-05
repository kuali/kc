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
package org.kuali.kra.test.htmlunit;


import java.util.ArrayList;
import java.util.List;

//import com.gargoylesoftware.htmlunit.WebTestCase;

/**
 * Tests for {@link IFrame}.
 *
 * @version $Revision: 1.1 $
 * @author Marc Guillemot
 */
public class IframeIssueTest {
//public class IframeIssueTest extends WebTestCase {

    /**
     * Create an instance
     * @param name The name of the test
     */
    public IframeIssueTest(final String name) {
        //super(name);
    }

//    public void testIframe() throws Exception {
//        final String content
//        = "<html><head><title>First</title><script>\n"
//        + "function setFocusedIframeDimensions(iframeName, focusHeight, resetToDefaultWidth ) { \n"
//        +"try { \n"
//        +"passedFrameName = iframeName; \n"
//        +"passedFocusHeight = focusHeight; \n"
//        +"//if (currentHeight == '') { \n"
//         +" currentHeight = 500; \n"
//        +"//} \n"
//        +"var iframe_portlet_container_table = document.getElementById('iframe_portlet_container_table'); \n"
//        +"var iframeWin = window.frames[iframeName]; \n"
//        +"var iframeEl = document.getElementById? document.getElementById(iframeName): document.all? document.all[iframeName]: null; \n"
//        +"if ( iframeEl && iframeWin && iframe_portlet_container_table) { \n"
//         +" var docHeight = getDocHeight(iframeWin.document);  \n"    
//        +"  if ( resetToDefaultWidth ) { \n"
//        +"    iframe_portlet_container_table.width = '100%'; \n"
//        +"  } \n"
//        +"  var frameScrollWidth = iframeEl.contentWindow.document.documentElement.scrollWidth; \n"
//          +"if (docHeight > 150) { \n"
//          +"  if ( Math.abs( frameScrollWidth - iframe_portlet_container_table.scrollWidth ) > 10 ) { \n"
//           +"   if ( frameScrollWidth > iframe_portlet_container_table.scrollWidth ) { \n"
//           +"       iframe_portlet_container_table.width = frameScrollWidth + 30; \n"
//           +"   } \n"
//           +" } \n"
//            +"if ((Math.abs(docHeight - currentHeight)) > 20 ) { \n"
//            +"  if (safari > -1) { \n"
//             +"   if ((Math.abs(docHeight - currentHeight)) > 59 ) { \n"
//             +"     iframeEl.style.height = docHeight + 30 + 'px'; \n"
//             +"     currentHeight = getDocHeight(iframeWin.document); \n"
//             +"   } \n"
//             +" } else {  \n"
//             +"   if (parseInt(iframeEl.style.height) < currentHeight || parseInt(iframeEl.style.height) < docHeight) { \n"
//             +"     iframeEl.style.height = docHeight + 30 + 'px'; \n"
//             +"     currentHeight = getDocHeight(iframeWin.document); \n"
//             +"   } else { \n"
//             +"     if (docHeight < currentHeight &&  (currentHeight - 150 > docHeight)) { \n"
//             +"       iframeEl.style.height = docHeight + 30 + 'px'; \n"
//              +"      currentHeight = getDocHeight(iframeWin.document); \n"
//              +"    } \n"
//              +"  } \n"
//             +" } \n"
//           +" } \n"
//          +"} \n"
//        +"} \n"
//      +"} \n"
//      +"catch(err) \n"
//      +"{ \n"
//      +"    window.status = err.description; \n"
//      +"} \n"
//      + "}\n" 
//      +"window.setInterval(doTheResize,500); \n"
//      +"function doTheResize() {  \n"
//      +" //if (passedFrameName != '' && passedFocusHeight != ''){ \n"
//      +"     setFocusedIframeDimensions('iframeportlet', 200, false ); \n"
//      +" //} \n"
//     +"} \n"
//     +"function setIframeAnchor(iframeName) {\n"
//     +" var iframeWin = window.frames[iframeName];\n"
//      +"if (iframeWin && iframeWin.location.href.indexOf('#') > -1) {\n"
//      +"  iframeWin.location.replace(iframeWin.location);\n"
//      +"}  \n"
//    +"}\n"
//
//        +       "</script></head>"
//        + "<body>"
//        +"<form name=\"KualiForm\" id=\"kualiForm\" method=\"post\" action=\"/kra-dev/proposalDevelopmentActions.do\" enctype='' onsubmit=\"return hasFormAlreadyBeenSubmitted();\">"
//        + "<iframe src=\"http://yahoo.com/proposalDevelopmentProposal.do?methodToCall=docHandler&amp;command=initiate&amp;docTypeName=ProposalDevelopmentDocument\" onload='setFocusedIframeDimensions(\"iframeportlet\", 500, true);setIframeAnchor(\"iframeportlet\")' name=\"iframeportlet\" id=\"iframeportlet\" hspace=\"0\" vspace=\"0\" style=\"height: 500px;\" title=\"E-Doc\" frameborder=\"0\" height=\"500\" scrolling=\"auto\" width=\"100%\"></iframe></form></body></html>";
//        final List collectedAlerts = new ArrayList();
//        loadPage(content, collectedAlerts);
//        final String[] expectedAlerts = {"false"};
//        createTestPageForRealBrowserIfNeeded(content, expectedAlerts);
//        assertEquals(expectedAlerts, collectedAlerts);
//        
//
//    }
}
