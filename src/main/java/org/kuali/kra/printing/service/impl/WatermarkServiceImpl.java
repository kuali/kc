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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.printing.service.WatermarkService;
import org.kuali.kra.util.watermark.CommonBean;
import org.kuali.kra.util.watermark.Decorator;
import org.kuali.kra.util.watermark.WatermarkBean;
import org.kuali.kra.util.watermark.WatermarkDecorator;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class WatermarkServiceImpl implements WatermarkService {
    
    private static final Log LOG = LogFactory.getLog(PrintingServiceImpl.class);
  
    
    public byte[] applyWatermark(byte[] pdfBytes, WatermarkBean  watermarkBean) throws Exception {
     
        byte[] pdfFileData = pdfBytes;
           
        try {
            if (watermarkBean != null) {
                ByteArrayOutputStream byteArrayOutputStream = attachWatermarking(watermarkBean, pdfFileData);
                pdfFileData = byteArrayOutputStream.toByteArray();
            }
        } catch (Exception e) {
            LOG.error("Exception occured in WatermarkServiceImpl.. Water mark Exception: ",e);            
        }
  
        return pdfFileData;
    }
  
    
    /**
     * decorates the pdf
     * @param decoratorBean encapsulates all decorations for the pdf
     * @param pdfContent pdfContent 
     * @throws DocumentException throws this exception if cannot decorate the pdf
     * @throws java.io.IOException throws this exception if cannot open/read the pdf contents
     */
    @SuppressWarnings("unchecked")
    public ByteArrayOutputStream attachWatermarking(WatermarkBean watermarkBean, byte pdfContent[])throws DocumentException, IOException {
        List listDecorations = new ArrayList();  
        
        if(watermarkBean.getWatermark() != null && !watermarkBean.getWatermark().isEmpty()) {
            for(int index = 0; index < watermarkBean.getWatermark().size(); index++) {
                listDecorations.add(new WatermarkDecorator((CommonBean)watermarkBean.getWatermark().get(index)));
            }
            
        }
        
        PdfReader reader = new PdfReader(pdfContent);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfStamper pdfStamp = new PdfStamper(reader, byteArrayOutputStream);
        decorateWatermark(pdfStamp, listDecorations);
        
        return byteArrayOutputStream;
    }
    
    
    
    /**
     * 
     * This method for Decorates the PDF...
     * @param pdfStamper - wrapper for pdf content byte and assists in decorating PDF
     * @param lstDecorations
     * @throws throws this exception if any error occurs while decorating the document
     * @throws java.io.IOException throws this exception if cannot open/read the file for decoration
     * 
     */
    @SuppressWarnings("unchecked")
    public void decorateWatermark(PdfStamper pdfStamper, List listDecorations)throws DocumentException, IOException {
        PdfReader pDFReader = pdfStamper.getReader();        
        int pageCount = pDFReader.getNumberOfPages();
        int pdfPageNumber = 0;
        
        Decorator decorator;
        
        PdfContentByte pdfContents;
        Rectangle rectangle;
        int pageHeight, pageWidth;
        while (pdfPageNumber < pageCount) {
            pdfPageNumber++;
            pdfContents = pdfStamper.getOverContent(pdfPageNumber);
            rectangle = pDFReader.getPageSizeWithRotation(pdfPageNumber);
            pageHeight = (int)rectangle.getHeight();
            pageWidth = (int)rectangle.getWidth();
            
            for(int index=0; index < listDecorations.size(); index++) {
                decorator = (Decorator)listDecorations.get(index);
                if(decorator instanceof WatermarkDecorator) {
                    pdfContents = pdfStamper.getUnderContent(pdfPageNumber);
                }
                decorator.decorate(pdfContents, pageWidth, pageHeight);
                
            }
        }
        
        pdfStamper.close();
        
    }
        
}
