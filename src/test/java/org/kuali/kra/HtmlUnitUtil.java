/*
 * Copyright 2006-2009 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.BaseFrame;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlImageInput;
import com.gargoylesoftware.htmlunit.html.HtmlInlineFrame;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class HtmlUnitUtil extends Assert {
    private static final String CAS_LOGIN_PAGE_TITLE = "Central Authentication Service";
    private static final String DEFAULT_USERID = "quickstart";
    
    public static HtmlAnchor getAnchorByTitle(HtmlPage page, String anchorTitle) {
        List<HtmlAnchor> anchors = page.getAnchors();
        for (HtmlAnchor anchor : anchors) {
            if (anchor.getTitleAttribute().equals(anchorTitle)) {
                return anchor;
            }
        }

        return null;
    }
    
    public static final HtmlPage clickOnAnchorByTitle(HtmlPage page, String anchorTitle, String nextPageTitle) throws IOException {
        return clickOnAnchorByTitle(page, anchorTitle, nextPageTitle, DEFAULT_USERID); 
    }
    
    public static final HtmlPage clickOnAnchorByTitle(HtmlPage page, String anchorTitle, String nextPageTitle, String userName) throws IOException {
        HtmlAnchor link = getAnchorByTitle(page, anchorTitle);
        HtmlPage nextPage = (HtmlPage) link.click();
        nextPage = checkForLoginPage(nextPage, userName);
        
        if (nextPageTitle != null && nextPageTitle.equals(nextPage.getTitleText())) {
            return nextPage;
        }
        
        return null;
    }
    
    private static HtmlForm getFirstForm(HtmlPage page) {
        if(page.getForms().size() > 0)
            return (HtmlForm) page.getForms().get(0);
        
        return null;
    }

    public static HtmlSelect getSelectField(HtmlPage page, String selectFieldId) {
        HtmlElement element = getElement(page, selectFieldId);
        if(element != null && element instanceof HtmlSelect) {
            return (HtmlSelect) element;
        }
        
        return null;
    }
    
    private static HtmlTextInput getTextField(HtmlPage page, String textFieldId) {
        HtmlElement element = getElement(page, textFieldId);
        if(element != null && element instanceof HtmlTextInput) {
            return (HtmlTextInput) element;
        }
        
        return null;
    }
    
    private static HtmlTextArea getTextAreaField(HtmlPage page, String textFieldId) {
        HtmlElement element = getElement(page, textFieldId);
        if(element != null && element instanceof HtmlTextArea) {
            return (HtmlTextArea) element;
        }
        
        return null;
    }
    
    protected static final HtmlElement getElement(HtmlPage page, String id) {
        HtmlElement element = getElementById(page, id);
        if (element == null) {
            element = getElementByName(page, id);
            if (element == null) {
                element = getElementByTitle(page, id);
            }
        }
        return element;
    }

    protected static final HtmlElement getElementById(HtmlPage page, String id) {
        HtmlElement element = null;
        try {
            element = page.getHtmlElementById(id);
        } catch (ElementNotFoundException ex) {
            List<HtmlPage> innerPages = getInnerPages(page);
            for (HtmlPage innerPage : innerPages) {
                element = getElementById(innerPage, id);
                if (element != null) break;
            }
        }
        return element;
    }

    public static final List<HtmlPage> getInnerPages(HtmlPage page) {
        List<HtmlPage> innerPages = new ArrayList<HtmlPage>();

        List frames = page.getFrames();
        for (int i = 0; i < frames.size(); i++) {
            FrameWindow frame = (FrameWindow) frames.get(i);
            BaseFrame baseFrame = frame.getFrameElement();
            if (baseFrame instanceof HtmlInlineFrame) {
                HtmlInlineFrame iframe = (HtmlInlineFrame) baseFrame;
                Page epage = iframe.getEnclosedPage();
                if (epage instanceof HtmlPage) {
                    innerPages.add((HtmlPage) epage);
                }
            }
        }
        return innerPages;
    }
    
    public static final HtmlElement getElementByName(HtmlPage page, String name) {
        return getElementByName(page, name, false);
    }
    
    protected static final HtmlElement getElementByName(HtmlPage page, String name, boolean startsWith) {
        HtmlElement element = getElementByName(page.getDocumentElement(), name, startsWith);

        if (element == null) {
            List<HtmlPage> innerPages = getInnerPages(page);
            for (HtmlPage innerPage : innerPages) {
                element = getElementByName(innerPage, name, startsWith);
                if (element != null) break;
            }
        }

        return element;
    }

    protected static final HtmlElement getElementByName(HtmlElement element, String name, boolean startsWith) {
        Iterator iterator = element.getAllHtmlChildElements();
        while (iterator.hasNext()) {
            HtmlElement e = (HtmlElement) iterator.next();
            String value = e.getAttributeValue("name");
            if (!startsWith && name.equals(value)) {
                return e;
            } else if (startsWith && value != null && value.startsWith(name)) {
                return e;
            }
        }
        return null;
    }

    protected static final HtmlElement getElementByTitle(HtmlPage page, String title) {
        HtmlElement element = getElementByTitle(page.getDocumentElement(), title);

        if (element == null) {
            List<HtmlPage> innerPages = getInnerPages(page);
            for (HtmlPage innerPage : innerPages) {
                element = getElementByTitle(innerPage, title);
                if (element != null) break;
            }
        }
        return element;
    }
    
    protected static final HtmlElement getElementByTitle(HtmlElement element, String title) {
        Iterator iterator = element.getAllHtmlChildElements();
        while (iterator.hasNext()) {
            HtmlElement e = (HtmlElement) iterator.next();
            String value = e.getAttributeValue("title");
            if (title.equals(value)) {
                return e;
            }
        }
        return null;
    }
    
    public static HtmlImageInput getImageInput(HtmlPage page, String imageId) {
        HtmlForm form = getFirstForm(page);
        return (HtmlImageInput) form.getInputByName(imageId);
    }
    
    public static HtmlSubmitInput getSubmitInput(HtmlPage page, String inputId) {
        HtmlForm form = getFirstForm(page);
        HtmlSubmitInput submitButton = null;
        
        try {
            submitButton = (HtmlSubmitInput) form.getInputByName(inputId);
        }
        catch (ElementNotFoundException e) {
            submitButton = (HtmlSubmitInput) form.getInputByValue(inputId);
        }
        
        return submitButton;
    }
    
    public static HtmlPage clickSubmitButton(HtmlPage page, String buttonName) throws Exception {
        final HtmlSubmitInput button = getSubmitInput(page, buttonName);
        return (HtmlPage) button.click();
    }
    
    public static void setTextFieldValue(HtmlPage page, String textFieldId, String textValue) {
        HtmlTextInput textField = getTextField(page, textFieldId);
        if(textField != null) {
            textField.setValueAttribute(textValue);
        }
    }
    
    public static void setTextAreaValue(HtmlPage page, String textAreaFieldId, String textValue) {
        HtmlTextArea textAreaField = getTextAreaField(page, textAreaFieldId);
        if(textAreaField != null) {
            textAreaField.setText(textValue);
        }
    }
    
    public static void setSelectBoxValue(HtmlPage page, String selectBoxId, String selectedValue) {
        HtmlSelect selectField = getSelectField(page, selectBoxId);
        if(selectField != null) {
            selectField.setSelectedAttribute(selectedValue, true);
        }
    }
    
    private static HtmlPage checkForLoginPage(HtmlPage page, String userName) throws IOException {
        if (page.getTitleText().equals(CAS_LOGIN_PAGE_TITLE)) {
            setTextFieldValue(page, "username", userName);
            HtmlSubmitInput loginBtn = getSubmitInput(page, "Login");
            page = (HtmlPage) loginBtn.click();
            if (page.getTitleText().equals(CAS_LOGIN_PAGE_TITLE)) {
                page = (HtmlPage) loginBtn.click();
            }
        }
        return page;  
    }
}
