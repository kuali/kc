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
 * Created on Apri1 11, 2011, 5:10 PM
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.kuali.kra.util.watermark;




import java.awt.Color;

import com.lowagie.text.pdf.BaseFont;
import java.lang.reflect.Field;

public class Font {
    

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

    public void setFont(String font) {
        this.fontName = font;
    }

    public Color getColor() {
        return color;
    }

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
              
                color = WatermarkConstants.DEFAULT_COLOR;
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
    
    public BaseFont getBaseFont(){
        try {
//          return BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
            return BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);
        }catch (Exception exception) {
            return null;
        }
    }
    
}

