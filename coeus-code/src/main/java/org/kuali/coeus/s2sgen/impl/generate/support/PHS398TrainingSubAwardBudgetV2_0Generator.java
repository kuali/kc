/*
 * Copyright 2005-2013 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.generate.support;

import gov.grants.apply.forms.phs398TrainingBudgetV10.PHS398TrainingBudgetDocument;
import gov.grants.apply.forms.phs398TrainingBudgetV10.PHS398TrainingBudgetDocument.PHS398TrainingBudget;
import gov.grants.apply.forms.phs398TrainingSubawardBudget20V20.PHS398TrainingSubawardBudget20Document;
import gov.grants.apply.forms.phs398TrainingSubawardBudget20V20.PHS398TrainingSubawardBudget20Document.PHS398TrainingSubawardBudget20;
import gov.grants.apply.forms.phs398TrainingSubawardBudget20V20.PHS398TrainingSubawardBudget20Document.PHS398TrainingSubawardBudget20.BudgetAttachments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardsContract;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class for generating the XML object for grants.gov RRSubAwardBudgetV2.1. Form is generated using XMLBean classes and is based on
 * RRSubAwardBudget schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("PHS398TrainingSubAwardBudgetV2_0Generator")
public class PHS398TrainingSubAwardBudgetV2_0Generator extends S2SAdobeFormAttachmentBaseGenerator {


    private static final String PHS398_TRAINING_BUDGET_20_NAMESPACE_URI = "http://apply.grants.gov/forms/PHS398_TrainingBudget-V1.0";

    @Value("http://apply.grants.gov/forms/PHS398_TrainingSubawardBudget_2_0-V2.0")
    private String namespace;

    @Value("PHS398_TrainingSubawardBudget_2_0")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/PHS398_TrainingSubawardBudget-V2.0.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.phs398TrainingSubawardBudget20V20")
    private String packageName;

    @Value("162")
    private int sortIndex;

    /**
     * 
     * This method is to get SubAward Budget details
     * 
     * @return rrSubawardBudgetDocument {@link XmlObject} of type RRSubawardBudgetDocument.
     */
    private PHS398TrainingSubawardBudget20Document getPHS398TrainingSubawardBudget() throws S2SException{

        PHS398TrainingSubawardBudget20Document phs398TrainingSubawardBudget20Document = PHS398TrainingSubawardBudget20Document.Factory.newInstance();
        PHS398TrainingSubawardBudget20 phs398TrainingSubawardBudget20 = PHS398TrainingSubawardBudget20.Factory.newInstance();

        BudgetAttachments budgetAttachments = BudgetAttachments.Factory.newInstance();
        List<BudgetSubAwardsContract> budgetSubAwardsList = getBudgetSubAwards(pdDoc,PHS398_TRAINING_BUDGET_20_NAMESPACE_URI,true);
        PHS398TrainingBudget[] budgetList = new PHS398TrainingBudget[budgetSubAwardsList.size()];
        phs398TrainingSubawardBudget20.setFormVersion(FormVersion.v2_0.getVersion());
        int attCount = 1;
        for (BudgetSubAwardsContract budgetSubAwards : budgetSubAwardsList) {
            switch (attCount) {
                case 1:
                    phs398TrainingSubawardBudget20.setATT1(prepareAttName(budgetSubAwards));
                    budgetList[0] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 2:
                    phs398TrainingSubawardBudget20.setATT2(prepareAttName(budgetSubAwards));
                    budgetList[1] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 3:
                    phs398TrainingSubawardBudget20.setATT3(prepareAttName(budgetSubAwards));
                    budgetList[2] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 4:
                    phs398TrainingSubawardBudget20.setATT4(prepareAttName(budgetSubAwards));
                    budgetList[3] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 5:
                    phs398TrainingSubawardBudget20.setATT5(prepareAttName(budgetSubAwards));
                    budgetList[4] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 6:
                    phs398TrainingSubawardBudget20.setATT6(prepareAttName(budgetSubAwards));
                    budgetList[5] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 7:
                    phs398TrainingSubawardBudget20.setATT7(prepareAttName(budgetSubAwards));
                    budgetList[6] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 8:
                    phs398TrainingSubawardBudget20.setATT8(prepareAttName(budgetSubAwards));
                    budgetList[7] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 9:
                    phs398TrainingSubawardBudget20.setATT9(prepareAttName(budgetSubAwards));
                    budgetList[8] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
                case 10:
                    phs398TrainingSubawardBudget20.setATT10(prepareAttName(budgetSubAwards));
                    budgetList[9] = getPHS398TrainingBudget(budgetSubAwards);
                    break;
            }
            addSubAwdAttachments(budgetSubAwards);
            attCount++;
        }
        budgetAttachments.setPHS398TrainingBudgetArray(budgetList);
        phs398TrainingSubawardBudget20.setBudgetAttachments(budgetAttachments);
        phs398TrainingSubawardBudget20Document.setPHS398TrainingSubawardBudget20(phs398TrainingSubawardBudget20);
        return phs398TrainingSubawardBudget20Document;
    }

    /**
     * 
     * This method is used to get PHS398TrainingBudget from BudgetSubAwards
     * 
     * @param budgetSubAwards(BudgetSubAwards) budget sub awards entry.
     * @return PHS398TrainingBudget corresponding to the BudgetSubAwards object.
     */
    private PHS398TrainingBudget getPHS398TrainingBudget(BudgetSubAwardsContract budgetSubAwards) throws S2SException{
        PHS398TrainingBudget rrBudget = PHS398TrainingBudget.Factory.newInstance();
        PHS398TrainingBudgetDocument rrBudgetDocument = PHS398TrainingBudgetDocument.Factory.newInstance();
        String subAwdXML = budgetSubAwards.getSubAwardXmlFileData();
        Document subAwdFormsDoc;
        try {
            subAwdFormsDoc = stringToDom(subAwdXML);
        }
        catch (S2SException e1) {
            return rrBudget;
        }
        Element subAwdFormsElement = subAwdFormsDoc.getDocumentElement();
        NodeList subAwdNodeList = subAwdFormsElement.getElementsByTagNameNS(PHS398_TRAINING_BUDGET_20_NAMESPACE_URI, "PHS398_TrainingBudget");
        Node subAwdNode = null;
        if (subAwdNodeList != null){
            if(subAwdNodeList.getLength() == 0) {
                return null;
            }
            subAwdNode = subAwdNodeList.item(0);
        }
        byte[] subAwdNodeBytes = null;
        InputStream bgtIS  = null;
        try {
            subAwdNodeBytes = docToBytes(nodeToDom(subAwdNode));
            bgtIS = new ByteArrayInputStream(subAwdNodeBytes);
            rrBudgetDocument = (PHS398TrainingBudgetDocument) XmlObject.Factory.parse(bgtIS);
            rrBudget = rrBudgetDocument.getPHS398TrainingBudget();
        }
        catch (S2SException e) {
            return rrBudget;
        }
        catch (XmlException e) {
            return rrBudget;
        }
        catch (IOException e) {
            return rrBudget;
        }finally{
            if(bgtIS!=null){
                try {bgtIS.close();}catch (IOException e) {} 
            }
        }
        return rrBudget;
    }


    /**
     * This method creates {@link XmlObject} of type {@link PHS398TrainingSubawardBudget20Document} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) throws S2SException{
        pdDoc=proposalDevelopmentDocument;
        return getPHS398TrainingSubawardBudget();
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public Resource getStylesheet() {
        return stylesheet;
    }

    public void setStylesheet(Resource stylesheet) {
        this.stylesheet = stylesheet;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

}
