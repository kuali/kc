/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.service.PrintingService;
import org.kuali.kra.printing.service.WatermarkService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.KualiConfigurationService;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfDestination;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfOutline;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

/**
 * This class provides the functionality for printing any {@link Printable}
 * object. It uses the methods available in Printable object to generate XML,
 * fetch XSL style-sheets, then transforms the XML to a PDF after applying the
 * style sheet.
 * 
 */
public class PrintingServiceImpl implements PrintingService {


    private static final Log LOG = LogFactory.getLog(PrintingServiceImpl.class);

	private DateTimeService dateTimeService = null;
	private WatermarkService watermarkService;
	
	private KualiConfigurationService kualiConfigurationService;
	

    /**
	 * This method receives a {@link Printable} object, generates XML for it,
	 * transforms into PDF after applying style-sheet and returns the PDF bytes
	 * as {@link AttachmentDataSource}
	 * 
	 * @param printableArtifact
	 *            to be printed
	 * @return {@link AttachmentDataSource} PDF bytes
	 * @throws PrintingException
	 *             in case of any errors occur during printing process
	 */
	protected Map<String, byte[]> getPrintBytes(Printable printableArtifact)
			throws PrintingException {
		try {
	        Map<String, byte[]> streamMap = printableArtifact.renderXML();
			String loggingEnable = kualiConfigurationService.getPropertyString(Constants.PRINT_LOGGING_ENABLE);
			if(loggingEnable!=null && Boolean.parseBoolean(loggingEnable))
			   logPrintDetails(streamMap);
			
			Map<String, byte[]> pdfByteMap = new LinkedHashMap<String, byte[]>();

			FopFactory fopFactory = FopFactory.newInstance();

            int xslCount = 0;
			// Apply all the style sheets to the xml document and generate the
			// PDF bytes
			if (printableArtifact.getXSLTemplates() != null) {
				for (Source source : printableArtifact.getXSLTemplates()) {
					xslCount++;
					StreamSource xslt = (StreamSource) source;
					createPdfWithFOP(streamMap, pdfByteMap, fopFactory, xslCount, xslt,printableArtifact);

				}
			}else if(printableArtifact.getXSLTemplateWithBookmarks()!=null){
			    Map<String,Source> templatesWithBookmarks = printableArtifact.getXSLTemplateWithBookmarks();
			    for (Map.Entry<String, Source> templatesWithBookmark : templatesWithBookmarks.entrySet()){
                    StreamSource xslt = (StreamSource) templatesWithBookmark.getValue();
                    createPdfWithFOP(streamMap, pdfByteMap, fopFactory, xslCount, xslt,templatesWithBookmark.getKey(),printableArtifact);
                 }
                
			}

			// Add all the attachments.
			if (printableArtifact.getAttachments() != null) {
				pdfByteMap.putAll(printableArtifact.getAttachments());
			}
			return pdfByteMap;
		} catch (FOPException e) {
			LOG.error(e.getMessage(), e);
			throw new PrintingException(e.getMessage(), e);
		} catch (TransformerConfigurationException e) {
			LOG.error(e.getMessage(), e);
			throw new PrintingException(e.getMessage(), e);
		} catch (TransformerException e) {
			LOG.error(e.getMessage(), e);
			throw new PrintingException(e.getMessage(), e);
		} 
		
	}

    /**
     * This method...
     * @param streamMap
     * @param pdfByteMap
     * @param fopFactory
     * @param xslCount
     * @param transformer
     * @throws FOPException
     * @throws TransformerException
     */
    protected void createPdfWithFOP(Map<String, byte[]> streamMap, Map<String, byte[]> pdfByteMap, FopFactory fopFactory,
            int xslCount, StreamSource xslt,Printable printableArtifact) throws FOPException, TransformerException {
        createPdfWithFOP(streamMap, pdfByteMap, fopFactory, xslCount, xslt,null,printableArtifact);
    }
    protected void createPdfWithFOP(Map<String, byte[]> streamMap, Map<String, byte[]> pdfByteMap, FopFactory fopFactory,
            int xslCount, StreamSource xslt,String bookmark,Printable printableArtifact) throws FOPException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xslt);
        for (Map.Entry<String, byte[]> xmlData : streamMap.entrySet()) {
        	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        	ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlData.getValue());
        	Source src = new StreamSource(inputStream);
        	Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF,outputStream);
        	Result res = new SAXResult(fop.getDefaultHandler());
        	transformer.transform(src, res);
        	byte[] pdfBytes = outputStream.toByteArray();
        	if(pdfBytes!=null && pdfBytes.length>0){
                String pdfMapKey = bookmark==null?createBookMark(xslCount, xmlData.getKey()):bookmark;
        	    pdfByteMap.put(pdfMapKey,pdfBytes );
        	}
        }
    }

    /**
     * This method...
     * @param xslCount
     * @param xmlData
     * @return
     */
    protected String createBookMark(int xslCount, String bookmarkKey) {
        String pdfMapKey = bookmarkKey+ (xslCount == 1 ? "" : " "+xslCount);
        return pdfMapKey;
    }

	/**
	 * This method receives a {@link Printable} object, generates XML for it,
	 * transforms into PDF after applying style-sheet and returns the PDF bytes
	 * as {@link AttachmentDataSource}
	 * 
	 * @param printableArtifact
	 *            to be printed
	 * @return {@link AttachmentDataSource} PDF bytes
	 * @throws PrintingException
	 *             in case of any errors occur during printing process
	 */
	public AttachmentDataSource print(Printable printableArtifacts)
			throws PrintingException {
		List<Printable> printables = new ArrayList<Printable>();
		printables.add(printableArtifacts);
		return print(printables);
	}

	/**
	 * This method receives a {@link List} of {@link Printable} object,
	 * generates XML for it, transforms into PDF after applying style-sheet and
	 * returns the PDF bytes as {@link AttachmentDataSource}
	 * 
	 * @param List
	 *            of printableArtifact to be printed
	 * @return {@link AttachmentDataSource} PDF bytes
	 * @throws PrintingException
	 *             in case of any errors occur during printing process
	 */
    public AttachmentDataSource print(List<Printable> printableArtifactList) throws PrintingException {
        return print(printableArtifactList, false);
    }
    public AttachmentDataSource print(List<Printable> printableArtifactList, boolean headerFooterRequired)
            throws PrintingException {
        PrintableAttachment printablePdf = null;
        List<String> bookmarksList = new ArrayList<String>();
        List<byte[]> pdfBaosList = new ArrayList<byte[]>();
        for (Printable printableArtifact : printableArtifactList) {
            Map<String, byte[]> printBytes = getPrintBytes(printableArtifact);
            for (String bookmark : printBytes.keySet()) {
                byte[] pdfBytes = printBytes.get(bookmark);
                if(isPdfGoodToMerge(pdfBytes)){
                    bookmarksList.add(bookmark);
                    pdfBaosList.add(pdfBytes);
                }
            }
        }
        
        printablePdf = new PrintableAttachment();
        byte[] mergedPdfBytes = mergePdfBytes(pdfBaosList, bookmarksList,headerFooterRequired);
        Printable printableArtifactObject;
        /*if(printableArtifactList!=null && printableArtifactList.size()>0){
            printableArtifactObject = printableArtifactList.get(0);     
            try {  
                if(printableArtifactObject.isWatermarkEnabled()){    
                   mergedPdfBytes = watermarkService.applyWatermark( mergedPdfBytes,printableArtifactObject.getWatermarkable().getWatermark());
                }
             }
             catch (Exception e) {
                 LOG.error("Exception Occured in printServiceImpl. Water mark Exception: ",e);    
             }  
		}
        */  //Commented for 1327 #1
        // If there is a stylesheet issue, the pdf bytes will be null. To avoid an exception
        // initialize to an empty array before sending the content back
		if (mergedPdfBytes == null) {
			mergedPdfBytes = new byte[0];
		}
		
		printablePdf.setContent(mergedPdfBytes);
		StringBuilder fileName = new StringBuilder();
		fileName.append(getReportName());
		fileName.append(Constants.PDF_FILE_EXTENSION);
		printablePdf.setFileName(fileName.toString());
		printablePdf.setContentType(Constants.PDF_REPORT_CONTENT_TYPE);
		return printablePdf;
	}

	protected boolean isPdfGoodToMerge(byte[] pdfBytes) {
	    try {
            new PdfReader(pdfBytes);
            return true;
        }catch (IOException e) {
            return false;
        }
    }

    public String getReportName() {
        String dateString = getDateTimeService().getCurrentDate().toString();
        return StringUtils.deleteWhitespace(dateString);
	}

	/**
	 * @param pdfBytesList
	 *            List containing the PDF data bytes
	 * @param bookmarksList
	 *            List of bookmarks corresponding to the PDF bytes.
	 * @return
	 * @throws PrintingException
	 */
    
    protected byte[] mergePdfBytes(List<byte[]> pdfBytesList,
		List<String> bookmarksList,boolean headerFooterRequired) throws PrintingException {
		Document document = null;
		PdfWriter writer = null;
		ByteArrayOutputStream mergedPdfReport = new ByteArrayOutputStream();
		int totalNumOfPages = 0;
		PdfReader[] pdfReaderArr = new PdfReader[pdfBytesList.size()];
		int pdfReaderCount = 0;
        for (byte[] fileBytes : pdfBytesList) {
            LOG.debug("File Size " + fileBytes.length +" For " +  bookmarksList.get(pdfReaderCount));
		    PdfReader reader = null;
			try {
				reader = new PdfReader(fileBytes);
				pdfReaderArr[pdfReaderCount] = reader;
				pdfReaderCount = pdfReaderCount + 1;
				totalNumOfPages += reader.getNumberOfPages();
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}
        HeaderFooter footer = null;
        if(headerFooterRequired){
    		Calendar calendar = dateTimeService.getCurrentCalendar();
    		String dateString = formateCalendar(calendar);
    		StringBuilder footerPhStr = new StringBuilder();
    		footerPhStr.append(" of ");
    		footerPhStr.append(totalNumOfPages);
    		footerPhStr.append(getWhitespaceString(WHITESPACE_LENGTH_76));
    		footerPhStr.append(getWhitespaceString(WHITESPACE_LENGTH_76));
    		footerPhStr.append(getWhitespaceString(WHITESPACE_LENGTH_60));
    		footerPhStr.append(dateString);
    		Font font = FontFactory.getFont(FontFactory.TIMES, 8, Font.NORMAL,
    				Color.BLACK);
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
			} else {
				nop = reader.getNumberOfPages();
			}

			if (count == 0) {
				document = nop > 0 ? new com.lowagie.text.Document(reader
						.getPageSizeWithRotation(1))
						: new com.lowagie.text.Document();
				try {
					writer = PdfWriter.getInstance(document, mergedPdfReport);
				} catch (DocumentException e) {
					LOG.error(e.getMessage(), e);
					throw new PrintingException(e.getMessage(), e);
				}
				if(footer!=null){
				    document.setFooter(footer);
				}
				// writer.setPageEvent(new Watermark());  //  add watermark object here
				document.open();
			}
						
			PdfContentByte cb = writer.getDirectContent();
			int pageCount = 0;
			while (pageCount < nop) {
				document.setPageSize(reader.getPageSize(++pageCount));
				document.newPage();
                if(footer!=null){
                    document.setFooter(footer);
                }
				PdfImportedPage page = writer
						.getImportedPage(reader, pageCount);

				cb.addTemplate(page, 1, 0, 0, 1, 0, 0);
				

				PdfOutline root = cb.getRootOutline();
				if (pageCount == 1) {
					String pageName = bookmarksList.get(count);
					cb.addOutline(new PdfOutline(root, new PdfDestination(
							PdfDestination.FITH), pageName), pageName);
				}
			}
		}
		if (document != null) {
			try {
				document.close();
				return mergedPdfReport.toByteArray();
			} catch (Exception e) {
				LOG
						.error(
								"Exception occured because the generated PDF document has no pages",
								e);
			}
		}
		return null;
	}

	
	
	
	protected String formateCalendar(Calendar calendar) {
		DateFormat dateFormat = new SimpleDateFormat("M/d/yy h:mm a");
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 
	 * This class populates the bytes of PDF document to be generated
	 */
	protected class PrintableAttachment extends AttachmentDataSource {
		private byte[] streamData;

		public byte[] getContent() {
			return streamData;
		}

		public void setContent(byte[] streamData) {
			this.streamData = streamData;
		}
	}

	/**
	 * @return the dateTimeService
	 */
	public DateTimeService getDateTimeService() {
		return dateTimeService;
	}

	/**
	 * @param dateTimeService
	 *            the dateTimeService to set
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
	protected void logPrintDetails(Map<String, byte[]> xmlStreamMap)throws PrintingException{
	    byte[] xmlBytes = null;
        String xmlString=null;
        String loggingDirectory = kualiConfigurationService.getPropertyString(Constants.PRINT_LOGGING_DIRECTORY);
	    Iterator<String> it = xmlStreamMap.keySet().iterator();
	    if(loggingDirectory!=null){
	    try {
	    while (it.hasNext()) {
             String key = (String) it.next();
             xmlBytes=xmlStreamMap.get(key);
             xmlString= new String(xmlBytes);
             String dateString = getDateTimeService().getCurrentTimestamp().toString();
             String reportName=StringUtils.deleteWhitespace(key);
             String createdTime=StringUtils.replaceChars(StringUtils.deleteWhitespace(dateString), ":", "_");
             
             File file = new File(loggingDirectory+reportName+createdTime+".xml");
             
             BufferedWriter out = new BufferedWriter(new FileWriter(file));
             out.write(xmlString);
             out.close();
         }
	    }catch (IOException e){
            LOG.error(e.getMessage(), e);
            throw new PrintingException(e.getMessage(), e); 
        }
	    }
	}

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }

    public KualiConfigurationService getKualiConfigurationService() {
        return kualiConfigurationService;
    }

    /**
     * Gets the watermarkService attribute. 
     * @return Returns the watermarkService.
     */
    public WatermarkService getWatermarkService() {
        return watermarkService;
    }

    /**
     * Sets the watermarkService attribute value.
     * @param watermarkService The watermarkService to set.
     */
    public void setWatermarkService(WatermarkService watermarkService) {
        this.watermarkService = watermarkService;
    }
}
