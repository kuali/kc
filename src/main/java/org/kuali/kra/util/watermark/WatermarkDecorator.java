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
/*
 * WaterMarkDecorator.java
 * Created on April 2, 2011, 11:30 AM

 */
package org.kuali.kra.util.watermark;

import com.lowagie.text.pdf.PdfContentByte;




import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.printing.service.impl.PrintingServiceImpl;
import org.kuali.rice.kns.service.DateTimeService;

/**
 * setting  pdf with watermark
 */
public class WatermarkDecorator implements Decorator{
    
    private static final Log LOG = LogFactory.getLog(PrintingServiceImpl.class);

    private DateTimeService dateTimeService = null;

    private CommonBean watermarkBean;
    
    private Color fillColor = null;
    private Image image = null;
    private boolean widthCalculated = false;
    float x, y, x1, y1, angle;
    
    /**
     * Creates a new instance of WaterMarkDecorator
     * @param watermarkBean watermark decorations
     */
    public WatermarkDecorator(CommonBean watermarkBean){
        this.watermarkBean = watermarkBean;
        fillColor = watermarkBean.getFont().getColor() == null ? WatermarkConstants.DEFAULT_COLOR : watermarkBean.getFont().getColor();
        
        if(watermarkBean.getType().equalsIgnoreCase(WatermarkConstants.WATERMARK_TYPE_IMAGE)) {
            try{
                URL url = getClass().getResource(watermarkBean.getText());
                image = Image.getInstance(url);
            }catch (BadElementException badElementException) {                
                LOG.error(badElementException.getMessage()+ badElementException+ "WatermarkDecorator decorate");
            }catch (MalformedURLException malformedURLException) {               
                LOG.error(malformedURLException.getMessage()+ malformedURLException+ "WatermarkDecorator decorate");
            }catch (IOException iOException) {               
               LOG.info("WaterMarkDecoratordecorate");
            }
        }
        
    }
    
    /**
     * decorates pdf with watermark
     * @param pdfContentByte pdfContentByte
     * @param pageWidth pdf page width
     * @param pageHeight pdfPage Height
     */
    public void decorate(PdfContentByte pdfContentByte, int pageWidth, int pageHeight) {
        try{
            if(watermarkBean.getType().equalsIgnoreCase(WatermarkConstants.WATERMARK_TYPE_IMAGE) && image != null) {
                //Image image = Image.getInstance(watermarkBean.getText());
                float height = image.getPlainHeight();
                float width = image.getPlainWidth();
                image.setAbsolutePosition((pageWidth - width)/2, (pageHeight - height)/2);
                pdfContentByte.addImage(image);
                return ;
            }

            pdfContentByte.beginText();            
            pdfContentByte.setFontAndSize(watermarkBean.getFont().getBaseFont(), watermarkBean.getFont().getSize());
            pdfContentByte.setColorFill(fillColor);
            
            if(!widthCalculated) {
                int textWidth = (int)pdfContentByte.getEffectiveStringWidth(watermarkBean.getText(), false);
                int diagonal = (int)Math.sqrt((pageWidth * pageWidth) + (pageHeight * pageHeight));
                int pivotPoint = (diagonal - textWidth)/2;
                
                angle = (float)Math.atan((float)pageHeight/pageWidth);
               
                x = (float)(pivotPoint * pageWidth)/diagonal;
                y = (float)(pivotPoint * pageHeight)/diagonal;
               
                float x1, y1;
                x1 = (float)(((float)watermarkBean.getFont().getSize()/2) * Math.sin(angle));
                y1 = (float)(((float)watermarkBean.getFont().getSize()/2) * Math.cos(angle));
                widthCalculated = true;
            }
            
            pdfContentByte.showTextAligned(Element.ALIGN_LEFT, watermarkBean.getText(), x+x1, y-y1, (float)Math.toDegrees(angle));
            pdfContentByte.endText();
            
        }catch (BadElementException badElementException) {  
            
            LOG.error("WaterMarkDecorator  :  parsing error "+ badElementException.getMessage());
        }catch (DocumentException documentException) {
            
            LOG.error("WaterMarkDecorator : "+documentException.getMessage());      
        }
    }
        
}
