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
 * WatermarkParser.java
 * Created on April 4, 2011, 11:30 AM
 * This method parsing the watermark.xml from resource 
 * and set the watermark details
 */

package org.kuali.kra.util.watermark;

import java.net.URL;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class WatermarkParser extends DefaultHandler implements WatermarkConstants {

    private WatermarkBean watermarkBean;
    private CommonBean commonBean;
    private static final String XML_CONTEXT_DIR = "/org/kuali/kra/util/watermark/";
    
    private String element;
    
    private String status;
    
    private String ELEMENT_FOUND = "element found";
    
    /** Creates a new instance of WatermarkParser */
    public WatermarkParser() {
        
    }
    
   /**
    *  
    * This method instance for the status, else returns null....
    * @param status
    * @return
    * @throws java.lang.Exception throws exception if any error occurs 
    */
    public WatermarkBean find(String status)throws Exception {
        if(status == null) return null;
        
        //Check if bean exists in cache
        WatermarkCache watermarkCache = WatermarkCache.getInstance();
        WatermarkBean cacheWatermarkBean = watermarkCache.findDecoration(Integer.parseInt(status));
        if(cacheWatermarkBean != null) {
            return cacheWatermarkBean;
        }
        
        this.status = status;
        
        javax.xml.parsers.SAXParserFactory saxParserFactory = javax.xml.parsers.SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
        org.xml.sax.XMLReader xmlReader = saxParser.getXMLReader();
        
        xmlReader.setContentHandler(this);
        xmlReader.setErrorHandler(this);

        try{                
          
            URL watermarkUrl = getClass().getResource("/Watermark.xml");
            //URL watermarkUrl = getClass().getResource(XML_CONTEXT_DIR+"/Watermark.xml");          
            xmlReader.parse(watermarkUrl.toString());
        }catch (SAXException sAXException) {
            if(sAXException.getMessage() != null && !sAXException.getMessage().equalsIgnoreCase(ELEMENT_FOUND)) {
               throw sAXException;
            }
        }
              
        watermarkCache.cacheDecoration(Integer.parseInt(status), watermarkBean);
        
        return watermarkBean;
    }
    
    
   /**
    * 
    * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
    */
    public void endElement(String namespaceURI, String localName, String decoratorName) throws SAXException {
        element = null;
        if(decoratorName.equalsIgnoreCase(DECORATION) && watermarkBean != null) {
            //Status Found And watermark bean Filled
            throw new SAXException(ELEMENT_FOUND);
        }
    }
    
   /**
    * <p>The Parser will invoke this method at the beginning of every
     * element in the XML document; there will be a corresponding
     * {@link #endElement endElement} event for every startElement event
     * (even when the element is empty). All of the element's content will be
     * reported, in order, before the corresponding endElement
     * event.</p>
     * @param namespaceURI namespaceURI
     * @param localName The local name (without prefix), or the
     * empty string if Namespace processing is not being performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @param atts The attributes attached to the element.  If
     *        there are no attributes, it shall be an empty
     *        Attributes object.
    
    */
    public void startElement(String namespaceURI, String localName, String decorationName, org.xml.sax.Attributes attributes) {
       
        if(decorationName.equalsIgnoreCase(STATUS)) {
            element = STATUS;
        }
        else if(watermarkBean != null && decorationName.equalsIgnoreCase(WATERMARK)) {
            element = WATERMARK;
            commonBean = new CommonBean();
            commonBean.setType(attributes.getValue(TYPE) == null ? WatermarkConstants.WATERMARK_TYPE_TEXT : attributes.getValue(TYPE));
            watermarkBean.setWatermark(commonBean);
            commonBean.setFont(getFont(attributes));
        }
    }
    
/**
   @param watermarkChar The characters.
     * @param start The start position in the character array.
     * @param length The number of characters to use from the character array.
     * @exception org.xml.sax.SAXException Any SAX exception, possibly
     * wrapping another exception.
     * @see org.xml.sax.ContentHandler#characters
 */
    public void characters(char[] watermarkChar, int start, int length) throws SAXException {
        if(element != null) {
            String watermarkString = new String(watermarkChar, start, length);
            if(element.equalsIgnoreCase(STATUS) && watermarkString != null && watermarkString.equalsIgnoreCase(status)) {
                watermarkBean = new WatermarkBean();
                watermarkBean.setStatus(watermarkString);
            }
            else if(element.equalsIgnoreCase(WATERMARK)) {
                commonBean.setText(watermarkString);
            }
        }
    }
    
    /**
     * creates a Font object if font properties are defined in the xml.
     * @param atts tag attributes
     * @return Font instance
     */
    private Font getFont(org.xml.sax.Attributes attributes) {
        //Font FONT = new Font(Font.TIMES_ROMAN, 52, Font.BOLD, new GrayColor(0.75f));
        Font font =  new Font(WatermarkConstants.DEFAULT_FONT_SIZE);
        if(element.equalsIgnoreCase(WATERMARK)) {
            font =  new Font(WatermarkConstants.DEFAULT_WATERMARK_FONT_SIZE);
        }
        String fontName, color, size;
        fontName = attributes.getValue(FONT);
        color = attributes.getValue(FONT_COLOR);
        size = attributes.getValue(FONT_SIZE);
        if(fontName != null || color != null || size != null) {
            font.setFont(fontName);           
            if(size != null && size.trim().length() > 0) {
                try{
                    font.setSize(Integer.parseInt(size));
                }catch (NumberFormatException numberFormatException) {
                    font.setSize(WatermarkConstants.DEFAULT_FONT_SIZE);
                }
            }else {
                font.setSize(WatermarkConstants.DEFAULT_FONT_SIZE);
                if (element.equalsIgnoreCase(WATERMARK)) {
                    font.setSize(WatermarkConstants.DEFAULT_WATERMARK_FONT_SIZE);                    
                }
            }
            if(element.equalsIgnoreCase(WATERMARK) && (color == null || color.trim().length() == 0)){
                font.setColor(WatermarkConstants.DEFAULT_WATERMARK_COLOR);
                //font.setColor(new GrayColor(0.75f));
            }else{
                font.setColor(color);
            }
        }
        return font;
    }
    
    
}
