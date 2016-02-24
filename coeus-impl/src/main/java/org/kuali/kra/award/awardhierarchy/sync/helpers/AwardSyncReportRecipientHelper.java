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
package org.kuali.kra.award.awardhierarchy.sync.helpers;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncException;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

/**
 * Award Hierarchy Sync Helper for Report Recipient.
 */
public class AwardSyncReportRecipientHelper extends AwardSyncHelperBase {
    
    @SuppressWarnings("unchecked")
    @Override
    public void applySyncChange(Award award, AwardSyncChange change) 
        throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, 
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
