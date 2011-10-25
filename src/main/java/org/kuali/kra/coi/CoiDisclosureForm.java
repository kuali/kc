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
package org.kuali.kra.coi;

import java.util.Map;

import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.coi.disclosure.DisclosureHelper;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityHelper;
import org.kuali.kra.web.struts.form.Auditable;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;

public class CoiDisclosureForm extends KraTransactionalDocumentFormBase implements Auditable  {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5620344612882618024L;
    private DisclosureHelper disclosureHelper;
    private boolean auditActivated;

    public CoiDisclosureForm() {
        super();
        initialize();
    }

    /**
     * This method initialize all form variables
     */
    public void initialize() {
       setDisclosureHelper(new DisclosureHelper(this));
    }
    
   @Override
    protected String getDefaultDocumentTypeName() {
        // TODO Auto-generated method stub
        return "CoiDisclosureDocument";
    }

    @Override
    protected String getLockRegion() {
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_COIDISCLOSURE;
    }

    @Override
    protected void setSaveDocumentControl(Map editMode) {
        // TODO Auto-generated method stub
        
    }

    public boolean isAuditActivated() {
        return this.auditActivated;
    }

    public void setAuditActivated(boolean auditActivated) {
        this.auditActivated = auditActivated;
        
    }

    public DisclosureHelper getDisclosureHelper() {
        return disclosureHelper;
    }

    public void setDisclosureHelper(DisclosureHelper disclosureHelper) {
        this.disclosureHelper = disclosureHelper;
    }

}
