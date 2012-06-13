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
package org.kuali.kra.krms.service;

import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;

/**
 * This interface is to declare all methods which are used as KRMS Terms in KC
 */
public interface KcKrmsJavaFunctionTermService {
    public String specifiedGGForm(DevelopmentProposal developmentProposal,String formName);
    public String multiplePI(DevelopmentProposal developmentProposal);
    public String s2sBudgetRule(DevelopmentProposal developmentProposal, String formNames);
    public String monitoredSponsorRule(DevelopmentProposal developmentProposal, String monitoredSponsorHirearchies);
    public String s2sReplanRule(DevelopmentProposal developmentProposal, String narativeTypes, String maxNumber);
    public String grantsFormRule(DevelopmentProposal developmentProposal, String formName);
    public String biosketchFileNameRule(DevelopmentProposal developmentProposal, String restrictedSpecialCharacters);
    public String ospAdminPropPersonRule(DevelopmentProposal developmentProposal);
    public String costElementVersionLimit(DevelopmentProposal developmentProposal, String versionNumber, String costElementName, String limit);
    public String divisionCodeRule(DevelopmentProposal developmentProposal);
    public String divisionCodeIsFellowship(DevelopmentProposal developmentProposal, String fellowshipCodes);
}
