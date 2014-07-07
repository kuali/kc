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
package org.kuali.kra.s2s.printing;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.fop.apps.*;
import org.kuali.coeus.propdev.api.s2s.S2SConfigurationService;
import org.kuali.kra.s2s.ConfigurationConstants;
import org.kuali.kra.s2s.S2SException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * This class provides the functionality for printing any {@link S2SPrintable} object. It uses the methods available in Printable
 * object to generate XML, fetch XSL style-sheets, then transforms the XML to a PDF after applying the style sheet.
 *
 */
@Component("s2SPrintingService")
public class S2SPrintingServiceImpl implements S2SPrintingService {

    private static final Log LOG = LogFactory.getLog(S2SPrintingServiceImpl.class);
    public static final String PDF_REPORT_CONTENT_TYPE = "application/pdf";
    public static final String PDF_FILE_EXTENSION = ".pdf";
    public char SPACE_SEPARATOR = 32;

    @Autowired
    @Qualifier("s2SConfigurationService")
    private S2SConfigurationService s2SConfigurationService;

    /**
     * This method receives a {@link S2SPrintable} object, generates XML for it, transforms into PDF after applying style-sheet and
     * returns the PDF bytes
     *
     * @param printableArtifact to be printed
     * @return PDF bytes
     */
    protected Map<String, byte[]> getPrintBytes(S2SPrintable printableArtifact) {
        try {
            Map<String, byte[]> streamMap = printableArtifact.renderXML();
            try{
                String loggingEnable = s2SConfigurationService.getValueAsString(ConfigurationConstants.PRINT_LOGGING_ENABLE);
                if (loggingEnable != null && Boolean.parseBoolean(loggingEnable))
                    logPrintDetails(streamMap);
            }catch(Exception ex){
                LOG.error(ex.getMessage());
            }

            Map<String, byte[]> pdfByteMap = new LinkedHashMap<String, byte[]>();

            FopFactory fopFactory = FopFactory.newInstance();

            int xslCount = 0;
            // Apply all the style sheets to the xml document and generate the
            // PDF bytes
            if (printableArtifact.getXSLTemplates() != null) {
                for (Source source : printableArtifact.getXSLTemplates()) {
                    xslCount++;
                    StreamSource xslt = (StreamSource) source;
                    if(xslt.getInputStream()==null || xslt.getInputStream().available()<=0){
                        LOG.error("Stylesheet is not available");
                    }else{
                        createPdfWithFOP(streamMap, pdfByteMap, fopFactory, xslCount, xslt, printableArtifact);
                    }
                }
            }
            else if (printableArtifact.getXSLTemplateWithBookmarks() != null) {
                Map<String, Source> templatesWithBookmarks = printableArtifact.getXSLTemplateWithBookmarks();
                for (Map.Entry<String, Source> templatesWithBookmark : templatesWithBookmarks.entrySet()) {
                    StreamSource xslt = (StreamSource) templatesWithBookmark.getValue();
                    createPdfWithFOP(streamMap, pdfByteMap, fopFactory, xslCount, xslt, templatesWithBookmark.getKey(),
                            printableArtifact);
                }

            }

            // Add all the attachments.
            if (printableArtifact.getAttachments() != null) {
                pdfByteMap.putAll(printableArtifact.getAttachments());
            }
            return pdfByteMap;
        }
        catch (FOPException|TransformerException|IOException e) {
            throw new S2SException(e.getMessage(), e);
        }
    }

    protected void createPdfWithFOP(Map<String, byte[]> streamMap, Map<String, byte[]> pdfByteMap, FopFactory fopFactory,
            int xslCount, StreamSource xslt, S2SPrintable printableArtifact) throws FOPException, TransformerException {
        createPdfWithFOP(streamMap, pdfByteMap, fopFactory, xslCount, xslt, null, printableArtifact);
    }

    protected void createPdfWithFOP(Map<String, byte[]> streamMap, Map<String, byte[]> pdfByteMap, FopFactory fopFactory,
            int xslCount, StreamSource xslt, String bookmark, S2SPrintable printableArtifact) throws FOPException,
            TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xslt);
        String applicationUrl = s2SConfigurationService.getValueAsString(ConfigurationConstants.APPLICATION_URL_KEY);
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        foUserAgent.setBaseURL(applicationUrl);
        for (Map.Entry<String, byte[]> xmlData : streamMap.entrySet()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlData.getValue());
            Source src = new StreamSource(inputStream);
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, outputStream);
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(src, res);
            byte[] pdfBytes = outputStream.toByteArray();
            if (pdfBytes != null && pdfBytes.length > 0) {
                String pdfMapKey = bookmark == null ? createBookMark(xslCount, xmlData.getKey()) : bookmark;
                pdfByteMap.put(pdfMapKey, pdfBytes);
            }
        }
    }


    protected String createBookMark(int xslCount, String bookmarkKey) {
        String pdfMapKey = bookmarkKey + (xslCount == 1 ? "" : " " + xslCount);
        return pdfMapKey;
    }

    /**
     * This method receives a {@link S2SPrintable} object, generates XML for it, transforms into PDF after applying style-sheet and
     * returns the PDF bytes as {@link S2SFile}
     *
     * @param printableArtifacts to be printed
     * @return PDF bytes
     */
    public S2SFile print(S2SPrintable printableArtifacts) {
        List<S2SPrintable> printables = new ArrayList<S2SPrintable>();
        printables.add(printableArtifacts);
        return print(printables);
    }

    /**
     * This method receives a {@link java.util.List} of {@link S2SPrintable} object, generates XML for it, transforms into PDF after applying
     * style-sheet and returns the PDF bytes as {@link S2SFile}
     *
     * @param printableArtifactList List of printableArtifact to be printed
     * @return {@link S2SFile} PDF bytes
     */
    public S2SFile print(List<S2SPrintable> printableArtifactList) {
        return print(printableArtifactList, false);
    }

    public S2SFile print(List<S2SPrintable> printableArtifactList, boolean headerFooterRequired) {
        S2SFile printablePdf = null;
        List<String> bookmarksList = new ArrayList<String>();
        List<byte[]> pdfBaosList = new ArrayList<byte[]>();
        for (S2SPrintable printableArtifact : printableArtifactList) {
            Map<String, byte[]> printBytes = getPrintBytes(printableArtifact);
            for (String bookmark : printBytes.keySet()) {
                byte[] pdfBytes = printBytes.get(bookmark);
                if (isPdfGoodToMerge(pdfBytes)) {
                    bookmarksList.add(bookmark);
                    pdfBaosList.add(pdfBytes);
                }
            }
        }

        printablePdf = new S2SFile();
        byte[] mergedPdfBytes = mergePdfBytes(pdfBaosList, bookmarksList, headerFooterRequired);

        // If there is a stylesheet issue, the pdf bytes will be null. To avoid an exception
        // initialize to an empty array before sending the content back
        if (mergedPdfBytes == null) {
            mergedPdfBytes = new byte[0];
        }

        printablePdf.setData(mergedPdfBytes);
        StringBuilder fileName = new StringBuilder();
        fileName.append(getReportName());
        fileName.append(PDF_FILE_EXTENSION);
        printablePdf.setName(fileName.toString());
        printablePdf.setType(PDF_REPORT_CONTENT_TYPE);
        return printablePdf;
    }

    protected boolean isPdfGoodToMerge(byte[] pdfBytes) {
        try {
            new PdfReader(pdfBytes);
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    protected String getReportName() {
        String dateString = new Date().toString();
        return StringUtils.deleteWhitespace(dateString);
    }

    /**
     * @param pdfBytesList List containing the PDF data bytes
     * @param bookmarksList List of bookmarks corresponding to the PDF bytes.
     */
    protected byte[] mergePdfBytes(List<byte[]> pdfBytesList, List<String> bookmarksList, boolean headerFooterRequired) {
        Document document = null;
        PdfWriter writer = null;
        ByteArrayOutputStream mergedPdfReport = new ByteArrayOutputStream();
        int totalNumOfPages = 0;
        PdfReader[] pdfReaderArr = new PdfReader[pdfBytesList.size()];
        int pdfReaderCount = 0;
        for (byte[] fileBytes : pdfBytesList) {
            LOG.debug("File Size " + fileBytes.length + " For " + bookmarksList.get(pdfReaderCount));
            PdfReader reader = null;
            try {
                reader = new PdfReader(fileBytes);
                pdfReaderArr[pdfReaderCount] = reader;
                pdfReaderCount = pdfReaderCount + 1;
                totalNumOfPages += reader.getNumberOfPages();
            }
            catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        HeaderFooter footer = null;
        if (headerFooterRequired) {
            Calendar calendar = Calendar.getInstance();
            String dateString = formateCalendar(calendar);
            StringBuilder footerPhStr = new StringBuilder();
            footerPhStr.append(" of ");
            footerPhStr.append(totalNumOfPages);
            footerPhStr.append(getWhitespaceString(WHITESPACE_LENGTH_76));
            footerPhStr.append(getWhitespaceString(WHITESPACE_LENGTH_76));
            footerPhStr.append(getWhitespaceString(WHITESPACE_LENGTH_60));
            footerPhStr.append(dateString);
            Font font = FontFactory.getFont(FontFactory.TIMES, 8, Font.NORMAL, Color.BLACK);
            Phrase beforePhrase = new Phrase("Page ", font);
            Phrase afterPhrase = new Phrase(footerPhStr.toString(), font);
            footer = new HeaderFooter(beforePhrase, afterPhrase);
            footer.setAlignment(Element.ALIGN_BASELINE);
            footer.setBorderWidth(0f);
        }
        for (int count = 0; count < pdfReaderArr.length; count++) {
            PdfReader reader = pdfReaderArr[count];
            int nop;
            if (reader == null) {
                LOG.debug("Empty PDF byetes found for " + bookmarksList.get(count));
                continue;
            }
            else {
                nop = reader.getNumberOfPages();
            }

            if (count == 0) {
                document = nop > 0 ? new Document(reader.getPageSizeWithRotation(1))
                        : new Document();
                try {
                    writer = PdfWriter.getInstance(document, mergedPdfReport);
                }
                catch (DocumentException e) {
                    LOG.error(e.getMessage(), e);
                    throw new S2SException(e.getMessage(), e);
                }
                if (footer != null) {
                    document.setFooter(footer);
                }
                document.open();
            }

            PdfContentByte cb = writer.getDirectContent();
            int pageCount = 0;
            while (pageCount < nop) {
                document.setPageSize(reader.getPageSize(++pageCount));
                document.newPage();
                if (footer != null) {
                    document.setFooter(footer);
                }
                PdfImportedPage page = writer.getImportedPage(reader, pageCount);

                cb.addTemplate(page, 1, 0, 0, 1, 0, 0);


                PdfOutline root = cb.getRootOutline();
                if (pageCount == 1) {
                    String pageName = bookmarksList.get(count);
                    cb.addOutline(new PdfOutline(root, new PdfDestination(PdfDestination.FITH), pageName), pageName);
                }
            }
        }
        if (document != null) {
            try {
                document.close();
                return mergedPdfReport.toByteArray();
            }
            catch (Exception e) {
                LOG.error("Exception occured because the generated PDF document has no pages", e);
            }
        }
        return null;
    }


    protected String formateCalendar(Calendar calendar) {
        DateFormat dateFormat = new SimpleDateFormat("M/d/yy h:mm a");
        return dateFormat.format(calendar.getTime());
    }

    protected String getWhitespaceString(int length) {
        StringBuilder sb = new StringBuilder();
        char[] whiteSpace = new char[length];
        Arrays.fill(whiteSpace, SPACE_SEPARATOR);
        sb.append(whiteSpace);
        return sb.toString();
    }

    protected void logPrintDetails(Map<String, byte[]> xmlStreamMap) {
        String loggingDirectory = s2SConfigurationService.getValueAsString(ConfigurationConstants.PRINT_LOGGING_DIRECTORY);
        if (loggingDirectory != null) {

            for (String key : xmlStreamMap.keySet()) {
                byte[] xmlBytes = xmlStreamMap.get(key);
                String xmlString = new String(xmlBytes);
                String dateString = new Timestamp(new Date().getTime()).toString();
                String reportName = StringUtils.deleteWhitespace(key);
                String createdTime = StringUtils.replaceChars(StringUtils.deleteWhitespace(dateString), ":", "_");
                File dir = new File(loggingDirectory);
                if(!dir.exists() || !dir.isDirectory()){
                    dir.mkdirs();
                }
                File file = new File(dir , reportName + createdTime + ".xml");
                try(BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
                    out.write(xmlString);
                } catch (IOException e) {
                        LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    public S2SConfigurationService getS2SConfigurationService() {
        return s2SConfigurationService;
    }

    public void setS2SConfigurationService(S2SConfigurationService s2SConfigurationService) {
        this.s2SConfigurationService = s2SConfigurationService;
    }
}
