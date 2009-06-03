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
package org.kuali.kra.printing.service.impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FormattingResults;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.apps.PageSequenceResults;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.service.PrintingService;

/** {inheritdoc} */
public class PrintingServiceImpl implements PrintingService {


    protected OutputStream writeToPdf(InputStream xmlFo) {
        File fo = new File("src/main/webapp/static/printing/data/KCTestPrintableTestData.pdf");

        OutputStream out = null;

        try {
            FOUserAgent foUserAgent = PrintingUtilities.getFopFactory().newFOUserAgent();
            // configure foUserAgent as desired

            // Setup output stream.  Note: Using BufferedOutputStream
            // for performance reasons (helpful with FileOutputStreams).
            out = new FileOutputStream(fo);
            out = new BufferedOutputStream(out);

            // Construct fop with desired output format
            Fop fop = PrintingUtilities.getFopFactory().newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // Setup JAXP using identity transformer
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(); // identity transformer

            // Setup input stream
            Source src = new StreamSource(xmlFo);

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            transformer.transform(src, res);
            // Result processing
            FormattingResults foResults = fop.getResults();
            java.util.List pageSequences = foResults.getPageSequences();
            for (java.util.Iterator it = pageSequences.iterator(); it.hasNext();) {
                PageSequenceResults pageSequenceResults = (PageSequenceResults)it.next();
                System.out.println("PageSequence "
                        + (String.valueOf(pageSequenceResults.getID()).length() > 0
                                ? pageSequenceResults.getID() : "<no id>")
                        + " generated " + pageSequenceResults.getPageCount() + " pages.");
            }
            System.out.println("Generated " + foResults.getPageCount() + " pages in total.");

        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(-1);
        } finally {
                try {
                    out.close();
                }
                catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return out;


    }

    protected OutputStream writeXmlFo(Printable printableArtifact) {
        TransformerFactory factory = TransformerFactory.newInstance();

        //TODO initialize size for perf later
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ArrayList<Source> transforms = printableArtifact.getXSLT();
        for (Source xslt : transforms) {
            Transformer transformer = null;
            try {
                transformer = factory.newTransformer((StreamSource) xslt);
            }
            catch (TransformerConfigurationException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            InputStream xml = printableArtifact.renderXML();
            Source src = new StreamSource(xml);

            Result res = new StreamResult(out);
            try {
                transformer.transform(src, res);
            }
            catch (TransformerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                }
                catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        
        return out;
    }
    
    /** {inheritdoc} */
    public OutputStream print(Printable printableArtifact) {
        OutputStream outStream = writeXmlFo(printableArtifact);
        InputStream is = new ByteArrayInputStream(((ByteArrayOutputStream) outStream).toByteArray());
        return writeToPdf(is);
    }

}
