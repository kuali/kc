/*
 * Copyright 2005-2014 The Kuali Foundation
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

package org.kuali.coeus.common.framework.person.signature;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class PersonSignatureLocationHelper extends PDFTextStripper {

    private StringBuffer documentText = new StringBuffer();
    private List<String> signatureTags;
    private String currentSignatureTag;
    private boolean signatureTagExists = false;
    private List<PersonSignaturePrintHelper> personSignatureLocations;
    private HashMap<String, Vector<Float>> signatureTagPositions;
    
    public PersonSignatureLocationHelper(List<String> signatureTags) throws IOException {
        super.setSortByPosition( true );
        setPersonSignatureLocations(new ArrayList<PersonSignaturePrintHelper>());
        setSignatureTagPositions(new HashMap<String, Vector<Float>>());
        setSignatureTags(signatureTags);
    }

    protected void processTextPosition( TextPosition text ) {
        getDocumentText().append(text.getCharacter());
        matchSignatureTagAndRecordPosition(text);
        
        if(isSignatureTagFound()) {
            int totalSignatureStartChar = getCountMatchOfSignatureTagFirstCharacter();
            int positionInCollection = getStartCharacterXposition().size() - totalSignatureStartChar;
            float coordinateXofStart = getStartCharacterXposition().get(positionInCollection);
            setSignatureTagExists(true);
            PersonSignaturePrintHelper signaturePrintHelper = new PersonSignaturePrintHelper();
            signaturePrintHelper.setCoordinateX(coordinateXofStart);
            signaturePrintHelper.setCoordinateY(text.getTextPos().getYPosition());
            getPersonSignatureLocations().add(signaturePrintHelper);
        }
    }
    
    private void matchSignatureTagAndRecordPosition(TextPosition text) {
        for(String signatureTag : getSignatureTags()) {
            if(signatureTag.startsWith(text.getCharacter())) {
                Vector<Float> startCharacterXposition = getSignatureTagPositions().get(signatureTag);
                if(startCharacterXposition == null) {
                    startCharacterXposition = new Vector<Float>();
                }
                startCharacterXposition.add(text.getTextPos().getXPosition());
                getSignatureTagPositions().put(signatureTag, startCharacterXposition);
            }
        }
    }
    
    /**
     * This method is to find signature tag with the current text
     * @return
     */
    private boolean isSignatureTagFound() {
        boolean signatureTagFound = false;
        for(String signatureTag : getSignatureTags()) {
            if(getDocumentText().toString().endsWith(signatureTag)) {
                setCurrentSignatureTag(signatureTag);
                signatureTagFound = true;
                break;
            }
        }
        return signatureTagFound;
    }
    
    
    /**
     * This method is to find the count of first character in the signature tag
     * @return
     */
    protected int getCountMatchOfSignatureTagFirstCharacter() {
        String firstChar = Character.toString(getCurrentSignatureTag().charAt(0));
        return StringUtils.countMatches(getCurrentSignatureTag(), firstChar);
    }

    public StringBuffer getDocumentText() {
        return documentText;
    }

    public void setDocumentText(StringBuffer documentText) {
        this.documentText = documentText;
    }

    public List<String> getSignatureTags() {
        return signatureTags;
    }

    public void setSignatureTags(List<String> signatureTags) {
        this.signatureTags = signatureTags;
    }

    public boolean isSignatureTagExists() {
        return signatureTagExists;
    }

    public void setSignatureTagExists(boolean signatureTagExists) {
        this.signatureTagExists = signatureTagExists;
    }

    public List<PersonSignaturePrintHelper> getPersonSignatureLocations() {
        return personSignatureLocations;
    }

    public void setPersonSignatureLocations(List<PersonSignaturePrintHelper> personSignatureLocations) {
        this.personSignatureLocations = personSignatureLocations;
    }

    protected Vector<Float> getStartCharacterXposition() {
        return getSignatureTagPositions().get(getCurrentSignatureTag());
    }


    public HashMap<String, Vector<Float>> getSignatureTagPositions() {
        return signatureTagPositions;
    }

    public void setSignatureTagPositions(HashMap<String, Vector<Float>> signatureTagPositions) {
        this.signatureTagPositions = signatureTagPositions;
    }

    public String getCurrentSignatureTag() {
        return currentSignatureTag;
    }

    public void setCurrentSignatureTag(String currentSignatureTag) {
        this.currentSignatureTag = currentSignatureTag;
    }


}
