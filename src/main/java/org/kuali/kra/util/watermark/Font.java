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
 * Font.java
 * Created on Apri1 11, 2011, 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.kuali.kra.util.watermark;


import java.awt.Color;
import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lowagie.text.pdf.BaseFont;
/**
 * 
 * This class for setting the font details.
 * Here we setting the color, font size, and type of FONT.
 */
public class Font {
    
    private static final Log LOG = LogFactory.getLog(Font.class);
    public static String BOLD;
    /**
     * fontName name
     */
    private String fontName;
    private Color color;
    private int size;
    
    /** Creates a new instance of Font */
    public Font() {
    }
    
    public Font(int size) {
        this.size = size;
    }
    
    public String getFont() {
        return fontName;
    }
/**
 * 
 * This method for setting the font size.
 * @default font size is 50
 */
    public void setFont(String font) {
        this.fontName = font;
    }

    public Color getColor() {
        return color;
    }
/**
 * 
 * This method for setting the color to font.
 * Default font color is Color.LIGHT_GRAY.
 * @param strColor
 */
    public void setColor(String strColor) {
        if (strColor == null) {
            color = WatermarkConstants.DEFAULT_COLOR;
            return ;
        }
        try {
            color = Color.decode(strColor);
        } catch (NumberFormatException nfe) {
            try {
                final Field f = Color.class.getField(strColor);
                color = (Color) f.get(null);
            } catch (Exception exception) {              
                color = WatermarkConstants.DEFAULT_WATERMARK_COLOR;
                LOG.error("Exception occured in WatermarkFont.. NumberFormatException: "+exception);   
            }
        }
        
    }
    
    public void setColor(Color color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    /**
     * 
     * This method for setting the Font details
     * Here set the basic FONT(TIMES_BOLD, WINANSI, EMBEDDED).
     * @return BaseFont
     */
    public BaseFont getBaseFont(){
        try {
            return BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);
        }catch (Exception exception) {
            LOG.error("Exception occured in Watermark getBaseFont. BaseFontException: "+exception); 
            return null;
        }
    }
    
}

