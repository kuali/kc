/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.xalan.processor.TransformerFactoryImpl;
import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.PrintableAttachment;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.PrintingService;
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
import java.sql.Timestamp;
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
    private DateTimeService dateTimeService;

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

            final boolean loggingEnable = kualiConfigurationService.getPropertyValueAsBoolean(Constants.PRINT_LOGGING_ENABLE);
            if (loggingEnable) {
                logPrintDetails(streamMap);
            }


            Map<String, byte[]> pdfByteMap = new LinkedHashMap<>();

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
                        createPdfWithFOP(streamMap, pdfByteMap, fopFactory, xslCount, xslt);
                    }
                }
            }
            else if (printableArtifact.getXSLTemplateWithBookmarks() != null) {
                Map<String, Source> templatesWithBookmarks = printableArtifact.getXSLTemplateWithBookmarks();
                for (Map.Entry<String, Source> templatesWithBookmark : templatesWithBookmarks.entrySet()) {
                    StreamSource xslt = (StreamSource) templatesWithBookmark.getValue();
                    createPdfWithFOP(streamMap, pdfByteMap, fopFactory, xslCount, xslt, templatesWithBookmark.getKey());
                }

            }

            // Add all the attachments.
            if (printableArtifact.getAttachments() != null) {
                pdfByteMap.putAll(printableArtifact.getAttachments());
            }
            return pdfByteMap;
        }
        catch (FOPException|TransformerException|IOException e) {
            LOG.error(e.getMessage(), e);
            throw new PrintingException(e.getMessage(), e);
        }

    }

    protected void createPdfWithFOP(Map<String, byte[]> streamMap, Map<String, byte[]> pdfByteMap, FopFactory fopFactory,
            int xslCount, StreamSource xslt) throws FOPException, TransformerException {
        createPdfWithFOP(streamMap, pdfByteMap, fopFactory, xslCount, xslt, null);
    }

    protected void createPdfWithFOP(Map<String, byte[]> streamMap, Map<String, byte[]> pdfByteMap, FopFactory fopFactory,
            int xslCount, StreamSource xslt, String bookmark) throws FOPException,
            TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute(TransformerFactoryImpl.FEATURE_SOURCE_LOCATION, Boolean.TRUE);
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
        return bookmarkKey + (xslCount == 1 ? "" : " " + xslCount);
    }

    /**
     * This method receives a {@link Printable} object, generates XML for it, transforms into PDF after applying style-sheet and
     * returns the PDF bytes as {@link AttachmentDataSource}
     * 
     * @param printableArtifacts to be printed
     * @return {@link AttachmentDataSource} PDF bytes
     * @throws PrintingException in case of any errors occur during printing process
     */
    @Override
    public AttachmentDataSource print(Printable printableArtifacts) throws PrintingException {
        List<Printable> printables = new ArrayList<>();
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
    @Override
    public AttachmentDataSource print(List<Printable> printableArtifactList) throws PrintingException {
        PrintableAttachment printablePdf;
        List<String> bookmarksList = new ArrayList<>();
        List<byte[]> pdfBaosList = new ArrayList<>();
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
        byte[] mergedPdfBytes = mergePdfBytes(pdfBaosList, bookmarksList);
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

        final boolean loggingEnable = kualiConfigurationService.getPropertyValueAsBoolean(Constants.PRINT_PDF_LOGGING_ENABLE);
        if (loggingEnable) {
            logPdfPrintDetails(printablePdf);
        }

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

    protected byte[] mergePdfBytes(List<byte[]> pdfBytesList, List<String> bookmarksList)
            throws PrintingException {
        Document document = null;
        PdfWriter writer = null;
        ByteArrayOutputStream mergedPdfReport = new ByteArrayOutputStream();

        PdfReader[] pdfReaderArr = new PdfReader[pdfBytesList.size()];
        int pdfReaderCount = 0;
        for (byte[] fileBytes : pdfBytesList) {
            LOG.debug("File Size " + fileBytes.length + " For " + bookmarksList.get(pdfReaderCount));
            PdfReader reader;
            try {
                reader = new PdfReader(fileBytes);
                pdfReaderArr[pdfReaderCount] = reader;
                pdfReaderCount = pdfReaderCount + 1;

            }
            catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
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

                document.open();
            }

            PdfContentByte cb = writer.getDirectContent();
            int pageCount = 0;
            while (pageCount < nop) {
                document.setPageSize(reader.getPageSize(++pageCount));
                document.newPage();

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

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }


    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    protected void logPrintDetails(Map<String, byte[]> xmlStreamMap) {
        byte[] xmlBytes;
        String xmlString;
        String loggingDirectory = kualiConfigurationService.getPropertyValueAsString(Constants.PRINT_LOGGING_DIRECTORY);
        Iterator<String> it = xmlStreamMap.keySet().iterator();
        if (loggingDirectory != null) {
            BufferedWriter out=null;
            try {
                while (it.hasNext()) {
                    String key = it.next();
                    xmlBytes = xmlStreamMap.get(key);
                    xmlString = new String(xmlBytes);
                    String dateString = getDateTimeService().getCurrentTimestamp().toString();
                    String reportName = StringUtils.deleteWhitespace(key);
                    String createdTime = StringUtils.replaceChars(StringUtils.deleteWhitespace(dateString), ":", "_");
                    File dir = getLoggingDir(loggingDirectory);
                    File file = new File(dir , reportName + "_" + createdTime + ".xml");

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

    protected void logPdfPrintDetails(AttachmentDataSource pdf) {
        final String loggingDirectory = kualiConfigurationService.getPropertyValueAsString(Constants.PRINT_LOGGING_DIRECTORY);
        if (loggingDirectory != null && pdf != null && pdf.getData() != null) {
            final String dateString = new Timestamp(new Date().getTime()).toString();
            final String createdTime = StringUtils.replaceChars(StringUtils.deleteWhitespace(dateString), ":", "_");
            final String fileName = StringUtils.replaceChars(StringUtils.deleteWhitespace(pdf.getName().replace(".pdf", "")), ":", "_") + "_" + createdTime + ".pdf";

            try {
                FileUtils.writeByteArrayToFile(new File(getLoggingDir(loggingDirectory),fileName), pdf.getData());
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    protected File getLoggingDir(String loggingDirectory) {
        File dir = new File(loggingDirectory);
        if(!dir.exists() || !dir.isDirectory()){
            dir.mkdirs();
        }
        return dir;
    }

    public void setKualiConfigurationService(ConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

    public ConfigurationService getKualiConfigurationService() {
        return kualiConfigurationService;
    }

}
