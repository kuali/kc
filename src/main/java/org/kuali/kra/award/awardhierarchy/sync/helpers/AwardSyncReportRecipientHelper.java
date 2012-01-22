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
package org.kuali.kra.award.awardhierarchy.sync.helpers;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncException;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

/**
 * Award Hierarchy Sync Helper for Report Recipient.
 */
public class AwardSyncReportRecipientHelper extends AwardSyncHelperBase {
    
    @SuppressWarnings("unchecked")
    @Override
    public void applySyncChange(Award award, AwardSyncChange change) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, InvocationTargetException, 
        ClassNotFoundException, NoSuchMethodException, InstantiationException, AwardSyncException {
        Collection reports = award.getAwardReportTermItems();
        AwardReportTerm report = (AwardReportTerm) getAwardSyncUtilityService().findMatchingBo(reports, change.getXmlExport().getKeys());
        if (StringUtils.equals(change.getSyncType(), AwardSyncType.ADD_SYNC.getSyncValue())) {
            if (report != null) {
                this.setValuesOnSyncable(report, change.getXmlExport().getValues(), change);
            } else {
                throw new AwardSyncException(Constants.AWARD_SYNC_NOT_APPLICABLE, true);
            }
        } else {
            if (report != null) {
                Object o = change.getXmlExport().getValues().get("awardReportTermRecipients");
                if (o instanceof List) {
                    List<AwardSyncXmlExport> recipientChanges = (List<AwardSyncXmlExport>) o;
                    for (AwardSyncXmlExport recipientChange : recipientChanges) {
                        AwardReportTermRecipient recipient = (AwardReportTermRecipient) getAwardSyncUtilityService().findMatchingBo((Collection) report.getAwardReportTermRecipients(), recipientChange.getKeys());
                        if (recipient != null) {
                            report.getAwardReportTermRecipients().remove(recipient);
                        }
                    }
                } else if (o instanceof AwardSyncXmlExport) {
                    AwardSyncXmlExport recipientChange = (AwardSyncXmlExport) o;
                    AwardReportTermRecipient recipient = (AwardReportTermRecipient) getAwardSyncUtilityService().findMatchingBo((Collection) report.getAwardReportTermRecipients(), recipientChange.getKeys());
                    if (recipient != null) {
                        report.getAwardReportTermRecipients().remove(recipient);
                    }
                } else {
                    throw new AwardSyncException("Unrecognized data", false);
                }
            } else {
                throw new AwardSyncException(Constants.AWARD_SYNC_NOT_APPLICABLE, true);
            }
        }
    }
        
    @Override
    protected String getObjectDesc(PersistableBusinessObject syncableObject, String attrName) {
        return "Recipient";
    }
    
    @Override
    protected String getDataDesc(PersistableBusinessObject syncableObject, String attrName) {
        AwardReportTermRecipient recipient = (AwardReportTermRecipient) syncableObject;
        String retval =  "";
        if (recipient.getContactType() != null) {
            retval += recipient.getContactType().getDescription();
        }
        retval += DELIMITER;
        if (recipient.getRolodex().getFullName() != null) {
            retval += recipient.getRolodex().getFullName();
        } else {
            retval += recipient.getRolodex().getOrganization();
        }
        return retval;
    } 
}
