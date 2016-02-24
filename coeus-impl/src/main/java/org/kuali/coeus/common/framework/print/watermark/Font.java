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
/*
 * Font.java
 * Created on Apri1 11, 2011, 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.kuali.coeus.common.framework.print.watermark;


import com.lowagie.text.pdf.BaseFont;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.awt.*;
import java.lang.reflect.Field;
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

