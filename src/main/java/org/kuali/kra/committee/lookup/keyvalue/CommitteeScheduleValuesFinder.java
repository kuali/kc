/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.committee.lookup.keyvalue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.sql.DATE;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.struts.form.KualiForm;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.web.struts.form.ProtocolForm;

/**
 * Finds the available set of supported Abstract Types.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class CommitteeScheduleValuesFinder extends KeyValuesBase {
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
    /**
     * @return the list of &lt;key, value&gt; pairs of committees.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select"));
        
        Committee committee = getCommittee(getCommitteeId());
        if (committee != null) {
            List<CommitteeSchedule> schedules = committee.getCommitteeSchedules();
            for (CommitteeSchedule schedule : schedules) {
                if (isOkayToScheduleReview(committee, schedule)) {
                    keyValues.add(new KeyLabelPair(schedule.getScheduleId(), getDescription(schedule)));
                }
            }
        }
        return keyValues;
    }
    
    private boolean isOkayToScheduleReview(Committee committee, CommitteeSchedule schedule) {
        Date now = new Date(System.currentTimeMillis());
        return true;
        
    }

    private String getDescription(CommitteeSchedule schedule) {
        Date date = schedule.getScheduledDate();
        long time = schedule.getActualTime().getTime() - date.getTime();
        Date t = new Date(time);
        return dateFormat.format(date) + " " + 
               schedule.getPlace() + " " + 
               timeFormat.format(schedule.getActualTime());
    }

    @SuppressWarnings("unchecked")
    private Committee getCommittee(String committeeId) {
        Committee committee = null;
        if (!StringUtils.isBlank(committeeId)) {
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class); 
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("committeeId", committeeId);
            Collection<Committee> committees = businessObjectService.findMatching(Committee.class, fieldValues);
            if (committees.size() == 1) {
                committee = committees.iterator().next();
            }
        }
        return committee;
    }
    
    private String getCommitteeId() {
        String committeeId = "";
        KualiForm form = GlobalVariables.getKualiForm();
        if (form instanceof ProtocolForm) {
            ProtocolForm protocolForm = (ProtocolForm) form;
            committeeId = protocolForm.getActionHelper().getProtocolSubmitAction().getCommitteeId();
        }
        return committeeId;
    }
}
