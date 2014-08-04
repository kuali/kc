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

import gov.grants.apply.forms.rrBudget1013V13.RRBudget1013Document;
import gov.grants.apply.forms.rrBudget1013V13.RRBudget1013Document.RRBudget1013;
import gov.grants.apply.forms.rrSubawardBudget101013V13.RRSubawardBudget101013Document;
import gov.grants.apply.forms.rrSubawardBudget101013V13.RRSubawardBudget101013Document.RRSubawardBudget101013;
import gov.grants.apply.forms.rrSubawardBudget101013V13.RRSubawardBudget101013Document.RRSubawardBudget101013.BudgetAttachments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardsContract;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.propdev.api.core.ProposalDevelopmentDocumentContract;
import org.kuali.coeus.s2sgen.impl.generate.FormGenerator;
import org.kuali.coeus.s2sgen.impl.generate.FormVersion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class for generating the XML object for grants.gov   RR_SubawardBudget10_10_1_3 Form is generated using XMLBean classes and is based on
 * RRSubAwardBudget schema.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@FormGenerator("RRSubAwardBudget10_10V1_3Generator")
public class RRSubAwardBudget10_10V1_3Generator extends S2SAdobeFormAttachmentBaseGenerator {
    
    protected static final String RR_BUDGET_10_1_3_NAMESPACE_URI = "http://apply.grants.gov/forms/RR_Budget10_1_3-V1.3";
    protected static final String RR_BUDGET_10_1_3_LOCAL_NAME = "RR_Budget10_1_3";

    @Value("http://apply.grants.gov/forms/RR_SubawardBudget10_10_1_3-V1.3")
    private String namespace;

    @Value("RR_SubawardBudget10_10_1_3")
    private String formName;

    @Value("classpath:org/kuali/coeus/s2sgen/impl/generate/support/RR_SubawardBudget10_10-V1.3.fo.xsl")
    private Resource stylesheet;

    @Value("gov.grants.apply.forms.rrSubawardBudget101013V13")
    private String packageName;

    @Value("178")
    private int sortIndex;

    /**
     * 
     * This method is to get SubAward Budget details
     * 
     * @return rrSubawardBudgetDocument {@link XmlObject} of type RRSubawardBudgetDocument.
     */
    private RRSubawardBudget101013Document getRRSubawardBudgetDocument() {

        RRSubawardBudget101013Document rrSubawardBudgetDocument = RRSubawardBudget101013Document.Factory.newInstance();
        RRSubawardBudget101013 rrSubawardBudget = RRSubawardBudget101013.Factory.newInstance();
        BudgetAttachments budgetAttachments = BudgetAttachments.Factory.newInstance();
        List<BudgetSubAwardsContract> budgetSubAwardsList = getBudgetSubAwards(pdDoc,RR_BUDGET_10_1_3_NAMESPACE_URI,false);
        RRBudget1013[] budgetList = new RRBudget1013[budgetSubAwardsList.size()];
        rrSubawardBudget.setFormVersion(FormVersion.v1_3.getVersion());

        int attIndex = 1;
        for (BudgetSubAwardsContract budgetSubAwards : budgetSubAwardsList) {
            RRBudget1013 rrBudget = getRRBudget(budgetSubAwards).getRRBudget1013();
            switch (attIndex) {
                case 1:
                    rrSubawardBudget.setATT1(prepareAttName(budgetSubAwards));
                    budgetList[0] = rrBudget;
                    break;
                case 2:
                    rrSubawardBudget.setATT2(prepareAttName(budgetSubAwards));
                    budgetList[1] = rrBudget;
                    break;
                case 3:
                    rrSubawardBudget.setATT3(prepareAttName(budgetSubAwards));
                    budgetList[2] = rrBudget;
                    break;
                case 4:
                    rrSubawardBudget.setATT4(prepareAttName(budgetSubAwards));
                    budgetList[3] = rrBudget;
                    break;
                case 5:
                    rrSubawardBudget.setATT5(prepareAttName(budgetSubAwards));
                    budgetList[4] = rrBudget;
                    break;
                case 6:
                    rrSubawardBudget.setATT6(prepareAttName(budgetSubAwards));
                    budgetList[5] = rrBudget;
                    break;
                case 7:
                    rrSubawardBudget.setATT7(prepareAttName(budgetSubAwards));
                    budgetList[6] = rrBudget;
                    break;
                case 8:
                    rrSubawardBudget.setATT8(prepareAttName(budgetSubAwards));
                    budgetList[7] = rrBudget;
                    break;
                case 9:
                    rrSubawardBudget.setATT9(prepareAttName(budgetSubAwards));
                    budgetList[8] = rrBudget;
                    break;
                case 10:
                    rrSubawardBudget.setATT10(prepareAttName(budgetSubAwards));
                    budgetList[9] = rrBudget;
                    break;               
            }
            addSubAwdAttachments(budgetSubAwards);
            attIndex++;
            
        }
        budgetAttachments.setRRBudget1013Array(budgetList);
        rrSubawardBudget.setBudgetAttachments(budgetAttachments);
        rrSubawardBudgetDocument.setRRSubawardBudget101013(rrSubawardBudget);
        return rrSubawardBudgetDocument;
    }

    /**
     * 
     * This method is used to get RRBudget from BudgetSubAwards
     * 
     * @param budgetSubAwards(BudgetSubAwards) budget sub awards entry.
     * @return RRBudget corresponding to the BudgetSubAwards object.
     */
    private RRBudget1013Document getRRBudget(BudgetSubAwardsContract budgetSubAwards) {
        RRBudget1013Document rrBudget = RRBudget1013Document.Factory.newInstance();
        String subAwdXML = budgetSubAwards.getSubAwardXmlFileData();
        Document subAwdFormsDoc;
        try {
            subAwdFormsDoc = stringToDom(subAwdXML);
        }catch (S2SException e1) {
            return rrBudget;
        }
        Element subAwdFormsElement = subAwdFormsDoc.getDocumentElement();
        NodeList subAwdNodeList = subAwdFormsElement.getElementsByTagNameNS(RR_BUDGET_10_1_3_NAMESPACE_URI, RR_BUDGET_10_1_3_LOCAL_NAME);
        Node subAwdNode = null;
        if (subAwdNodeList != null){
            if(subAwdNodeList.getLength() == 0) {
                return null;
            }
            subAwdNode = subAwdNodeList.item(0);
        }
        byte[] subAwdNodeBytes = null;
        try {
            subAwdNodeBytes = docToBytes(nodeToDom(subAwdNode));
            InputStream bgtIS = new ByteArrayInputStream(subAwdNodeBytes);
            rrBudget = (RRBudget1013Document) XmlObject.Factory.parse(bgtIS);
        }
        catch (S2SException e) {
            return rrBudget;
        }
        catch (XmlException e) {
            return rrBudget;
        }
        catch (IOException e) {
            return rrBudget;
        }
        return rrBudget;
    }


    /**
     * This method creates {@link XmlObject} of type {@link RRSubawardBudget101013Document} by populating data from the given
     * {@link ProposalDevelopmentDocumentContract}
     * 
     * @param proposalDevelopmentDocument for which the {@link XmlObject} needs to be created
     * @return {@link XmlObject} which is generated using the given {@link ProposalDevelopmentDocumentContract}
     */
    public XmlObject getFormObject(ProposalDevelopmentDocumentContract proposalDevelopmentDocument) {
        pdDoc=proposalDevelopmentDocument;
        return getRRSubawardBudgetDocument();
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
