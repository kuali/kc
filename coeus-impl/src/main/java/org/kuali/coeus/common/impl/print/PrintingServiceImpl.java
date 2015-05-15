/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.impl.print;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintableAttachment;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
import org.kuali.coeus.common.framework.print.watermark.WatermarkService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * This class provides the functionality for printing any {@link Printable} object. It uses the methods available in Printable
 * object to generate XML, fetch XSL style-sheets, then transforms the XML to a PDF after applying the style sheet.
 * 
 */
@Component("printingService")
public class PrintingServiceImpl implements PrintingService {


    private static final Log LOG = LogFactory.getLog(PrintingServiceImpl.class);

    @Autowired
    @Qualifier("dateTimeService")
    private DateTimeService dateTimeService = null;

    @Autowired
    @Qualifier("watermarkService")
    private WatermarkService watermarkService;

    @Autowired
    @Qualifier("kualiConfigurationService")
    private ConfigurationService kualiConfigurationService;


    /**
     * This method receives a {@link Printable} object, generates XML for it, transforms into PDF after applying style-sheet and
     * returns the PDF bytes as {@link AttachmentDataSource}
     * 
     * @param printableArtifact to be printed
     * @return {@link AttachmentDataSource} PDF bytes
     * @throws PrintingException in case of any errors occur during printing process
     */
    protected Map<String, byte[]> getPrintBytes(Printable printableArtifact) throws PrintingException {
        try {
            Map<String, byte[]> streamMap = printableArtifact.renderXML();
            try{
                String loggingEnable = kualiConfigurationService.getPropertyValueAsString(Constants.PRINT_LOGGING_ENABLE);
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
        catch (FOPException e) {
            LOG.error(e.getMessage(), e);
            throw new PrintingException(e.getMessage(), e);
        }
        catch (TransformerConfigurationException e) {
            LOG.error(e.getMessage(), e);
            throw new PrintingException(e.getMessage(), e);
        }
        catch (TransformerException e) {
            LOG.error(e.getMessage(), e);
            throw new PrintingException(e.getMessage(), e);
        }
        catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new PrintingException(e.getMessage(), e);
        }

    }

    protected void createPdfWithFOP(Map<String, byte[]> streamMap, Map<String, byte[]> pdfByteMap, FopFactory fopFactory,
            int xslCount, StreamSource xslt, Printable printableArtifact) throws FOPException, TransformerException {
        createPdfWithFOP(streamMap, pdfByteMap, fopFactory, xslCount, xslt, null, printableArtifact);
    }

    protected void createPdfWithFOP(Map<String, byte[]> streamMap, Map<String, byte[]> pdfByteMap, FopFactory fopFactory,
            int xslCount, StreamSource xslt, String bookmark, Printable printableArtifact) throws FOPException,
            TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xslt);
        String externalizableImagesUrl= getKualiConfigurationService().getPropertyValueAsString(Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY);
        transformer.setParameter("externalImagesUrl",externalizableImagesUrl);
        String applicationUrl = getKualiConfigurationService().getPropertyValueAsString(KRADConstants.APPLICATION_URL_KEY);
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        foUserAgent.setBaseURL(applicationUrl);      
        for (Map.Entry<String, byte[]> xmlData : streamMap.entrySet()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlData.getValue());
            Source src = new StreamSource(inputStream);
            //Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, outputStream);
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
     * This method receives a {@link Printable} object, generates XML for it, transforms into PDF after applying style-sheet and
     * returns the PDF bytes as {@link AttachmentDataSource}
     * 
     * @param printableArtifacts to be printed
     * @return {@link AttachmentDataSource} PDF bytes
     * @throws PrintingException in case of any errors occur during printing process
     */
    public AttachmentDataSource print(Printable printableArtifacts) throws PrintingException {
        List<Printable> printables = new ArrayList<Printable>();
        printables.add(printableArtifacts);
        return print(printables);
    }

    /**
     * This method receives a {@link List} of {@link Printable} object, generates XML for it, transforms into PDF after applying
     * style-sheet and returns the PDF bytes as {@link AttachmentDataSource}
     * 
     * @param printableArtifactList List of printableArtifact to be printed
     * @return {@link AttachmentDataSource} PDF bytes
     * @throws PrintingException in case of any errors occur during printing process
     */
    public AttachmentDataSource print(List<Printable> printableArtifactList) throws PrintingException {
        return print(printableArtifactList, false);
    }

    public AttachmentDataSource print(List<Printable> printableArtifactList, boolean headerFooterRequired) throws PrintingException {
        PrintableAttachment printablePdf = null;
        List<String> bookmarksList = new ArrayList<String>();
        List<byte[]> pdfBaosList = new ArrayList<byte[]>();
        for (Printable printableArtifact : printableArtifactList) {
            Map<String, byte[]> printBytes = getPrintBytes(printableArtifact);
            for (String bookmark : printBytes.keySet()) {
                byte[] pdfBytes = printBytes.get(bookmark);
                if (isPdfGoodToMerge(pdfBytes)) {
                    bookmarksList.add(bookmark);
                    pdfBaosList.add(pdfBytes);
                }
            }
        }

        printablePdf = new PrintableAttachment();
        byte[] mergedPdfBytes = mergePdfBytes(pdfBaosList, bookmarksList, headerFooterRequired);
        // If there is a stylesheet issue, the pdf bytes will be null. To avoid an exception
        // initialize to an empty array before sending the content back
        if (mergedPdfBytes == null) {
            mergedPdfBytes = new byte[0];
        }

        printablePdf.setData(mergedPdfBytes);
        StringBuilder fileName = new StringBuilder();
        fileName.append(getReportName());
        fileName.append(Constants.PDF_FILE_EXTENSION);
        printablePdf.setName(fileName.toString());
        printablePdf.setType(Constants.PDF_REPORT_CONTENT_TYPE);
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
        String dateString = getDateTimeService().getCurrentDate().toString();
        return StringUtils.deleteWhitespace(dateString);
    }

    /**
     * @param pdfBytesList List containing the PDF data bytes
     * @param bookmarksList List of bookmarks corresponding to the PDF bytes.
     * @return
     * @throws PrintingException
     */

    protected byte[] mergePdfBytes(List<byte[]> pdfBytesList, List<String> bookmarksList, boolean headerFooterRequired)
            throws PrintingException {
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
            Calendar calendar = dateTimeService.getCurrentCalendar();
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
                document = nop > 0 ? new com.lowagie.text.Document(reader.getPageSizeWithRotation(1))
                        : new com.lowagie.text.Document();
                try {
                    writer = PdfWriter.getInstance(document, mergedPdfReport);
                }
                catch (DocumentException e) {
                    LOG.error(e.getMessage(), e);
                    throw new PrintingException(e.getMessage(), e);
                }
                if (footer != null) {
                    document.setFooter(footer);
                }
                // writer.setPageEvent(new Watermark()); // add watermark object here
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

    /**
     * @return the dateTimeService
     */
    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    /**
     * @param dateTimeService the dateTimeService to set
     */
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    protected String getWhitespaceString(int length) {
        StringBuffer sb = new StringBuffer();
        char[] whiteSpace = new char[length];
        Arrays.fill(whiteSpace, Constants.SPACE_SEPARATOR);
        sb.append(whiteSpace);
        return sb.toString();
    }

    protected void logPrintDetails(Map<String, byte[]> xmlStreamMap) throws PrintingException {
        byte[] xmlBytes = null;
        String xmlString = null;
        String loggingDirectory = kualiConfigurationService.getPropertyValueAsString(Constants.PRINT_LOGGING_DIRECTORY);
        Iterator<String> it = xmlStreamMap.keySet().iterator();
        if (loggingDirectory != null) {
            BufferedWriter out=null;
            try {
                while (it.hasNext()) {
                    String key = (String) it.next();
                    xmlBytes = xmlStreamMap.get(key);
                    xmlString = new String(xmlBytes);
                    String dateString = getDateTimeService().getCurrentTimestamp().toString();
                    String reportName = StringUtils.deleteWhitespace(key);
                    String createdTime = StringUtils.replaceChars(StringUtils.deleteWhitespace(dateString), ":", "_");
                    File dir = new File(loggingDirectory);
                    if(!dir.exists() || !dir.isDirectory()){
                        dir.mkdirs();
                    }
                    File file = new File(dir , reportName + createdTime + ".xml");

                    out = new BufferedWriter(new FileWriter(file));
                    out.write(xmlString);
                }
            }
            catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }finally{
                try {
                    if(out!=null){
                        out.flush();
                        out.close();
                    }
                }
                catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    public void setKualiConfigurationService(ConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

    public ConfigurationService getKualiConfigurationService() {
        return kualiConfigurationService;
    }

    /**
     * Gets the watermarkService attribute.
     * 
     * @return Returns the watermarkService.
     */
    public WatermarkService getWatermarkService() {
        return watermarkService;
    }

    /**
     * Sets the watermarkService attribute value.
     * 
     * @param watermarkService The watermarkService to set.
     */
    public void setWatermarkService(WatermarkService watermarkService) {
        this.watermarkService = watermarkService;
    }
}
