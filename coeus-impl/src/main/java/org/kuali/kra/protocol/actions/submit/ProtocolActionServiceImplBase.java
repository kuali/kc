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
package org.kuali.kra.protocol.actions.submit;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDao;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.followup.FollowupActionService;
import org.kuali.kra.protocol.drools.util.DroolsRuleHandler;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.IOException;
import java.util.*;


/**
 * 
 * This class is to provide the 'protocol' action pre validation and post update.
 * pre-validation include canperform and authorization check.
 * post-update will update protocol status or submission status.
 */
public abstract class ProtocolActionServiceImplBase implements ProtocolActionService {

    protected static final String FYI = "F";

    protected static final String AMEND = "A";

    protected static final String RENEW = "R";

    protected static final String NONE = "NONE";

    protected BusinessObjectService businessObjectService;

    protected KcAuthorizationService kraAuthorizationService;

    protected UnitAuthorizationService unitAuthorizationService;
         
    protected FollowupActionService<?> followupActionService;
   
    private ProtocolDao<? extends ProtocolBase> protocolDao;

    protected DroolsRuleHandler canPerformRuleHandler;
    
    protected List<String> actions = new ArrayList<String>();
    protected List<DroolsRuleHandler> rulesList;
    protected static final Map<String, String> ACTION_SUBMISSION_MAPPINGS;
       
    static {
            Map<String, String> actionSubmissionMappings = new HashMap<String, String>();
            actionSubmissionMappings.put(ProtocolActionType.DATA_ANALYSIS_ONLY, ProtocolSubmissionType.REQUEST_FOR_DATA_ANALYSIS_ONLY);
            actionSubmissionMappings.put(ProtocolActionType.CLOSED_FOR_ENROLLMENT, ProtocolSubmissionType.REQUEST_TO_CLOSE_ENROLLMENT);
            actionSubmissionMappings.put(ProtocolActionType.SUSPENDED, ProtocolSubmissionType.REQUEST_FOR_SUSPENSION);
            actionSubmissionMappings.put(ProtocolActionType.CLOSED_ADMINISTRATIVELY_CLOSED, ProtocolSubmissionType.REQUEST_TO_CLOSE);
            actionSubmissionMappings.put(ProtocolActionType.REOPEN_ENROLLMENT, ProtocolSubmissionType.REQUEST_TO_REOPEN_ENROLLMENT);

            ACTION_SUBMISSION_MAPPINGS = Collections.unmodifiableMap(actionSubmissionMappings);
    }

    public ProtocolActionServiceImplBase(){
        this.actions = Arrays.asList(getActionCodesArrayHook());
    }
    
    protected abstract String[] getActionCodesArrayHook();
    
    

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

   
    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }

    public void setProtocolDao(ProtocolDao<? extends ProtocolBase> protocolDao) {
        this.protocolDao = protocolDao;
    }
    

    public void setFollowupActionService(FollowupActionService<?> followupActionService) {
        this.followupActionService = followupActionService;
    }

    
    
    @Override
    public boolean isActionAllowed(String actionTypeCode, ProtocolBase protocol) {
        return canPerformAction(actionTypeCode, protocol) || protocol.isFollowupAction(actionTypeCode);
    }

    @Override
    public List<String> getActionsAllowed(ProtocolBase protocol) {

        List<String> actionList = new ArrayList<String>();
        for (String actionTypeCode : actions) {
            if (canPerformAction(actionTypeCode, protocol) ) {
                actionList.add(actionTypeCode);
            }
        }
        return actionList;
    }

    
    protected abstract boolean hasPermissionLeadUnit(String actionTypeCode, ProtocolBase protocol, ActionRightMapping rightMapper);
    
    protected abstract boolean hasPermissionToSubmit(String actionTypeCode, ProtocolBase protocol, ActionRightMapping rightMapper);
    
    private List<String> getPersonnelIds(ProtocolBase protcol) {
        List<String> PersonnelIds = new ArrayList<String>();
       
            for (ProtocolPersonBase person : protcol.getProtocolPersons()) {
                if (StringUtils.isNotBlank(person.getPersonId())) {
                    PersonnelIds.add(person.getPersonId());
                }
                else {
                    PersonnelIds.add(person.getRolodexId().toString());
                }
            
        }
        return PersonnelIds;
    }
   
    
   public boolean isProtocolPersonnel(ProtocolBase protocol) {
        Person person = GlobalVariables.getUserSession().getPerson();
        return getPersonnelIds(protocol).contains(person.getPrincipalId());        
    }
   
    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId(); 
    }

    /**
     * This method is to check whether 'actionTypeCode' can be performed based on protocol's status code or submission code or other
     * condition specified in rule.
     */
    public boolean canPerformAction(String actionTypeCode, ProtocolBase protocol) {
// is this really necessary any more??   getLOGHook().info(actionTypeCode);
        String submissionStatusCode = protocol.getProtocolSubmission().getSubmissionStatusCode();
        String submissionTypeCode = protocol.getProtocolSubmission().getSubmissionTypeCode();
        String protocolReviewTypeCode = protocol.getProtocolSubmission().getProtocolReviewTypeCode();
        String protocolStatusCode = protocol.getProtocolStatusCode();
        String scheduleId = protocol.getProtocolSubmission().getScheduleId();
        Integer submissionNumber = protocol.getProtocolSubmission().getSubmissionNumber();
        ProtocolActionMappingBase protocolAction = getNewProtocolActionMappingInstanceHook(actionTypeCode, submissionStatusCode, submissionTypeCode,
            protocolReviewTypeCode, protocolStatusCode, scheduleId, submissionNumber);
        protocolAction.setBusinessObjectService(businessObjectService);
        protocolAction.setDao(protocolDao);
        protocolAction.setProtocol(protocol);
        rulesList.get(getPerformActionRuleIndexHook()).executeRules(protocolAction);
        return protocolAction.isAllowed();
    }

   
    protected abstract Log getLOGHook();
    
    protected abstract int getPerformActionRuleIndexHook();    

    protected abstract ProtocolActionMappingBase getNewProtocolActionMappingInstanceHook(String actionTypeCode, String submissionStatusCode,
            String submissionTypeCode, String protocolReviewTypeCode, String protocolStatusCode, String scheduleId,
            Integer submissionNumber);

    
    
    /**
     * @see org.kuali.kra.protocol.actions.submit.ProtocolActionService#updateProtocolStatus(org.kuali.kra.protocol.actions.ProtocolActionBase,
     *      org.kuali.kra.protocol.ProtocolBase)
     */
    public void updateProtocolStatus(ProtocolActionBase protocolActionBo, ProtocolBase protocol) {
        String protocolNumberUpper = protocol.getProtocolNumber().toUpperCase();
        String specialCondition = protocolNumberUpper.contains(AMEND) ? AMEND : (protocolNumberUpper.contains(RENEW) ? RENEW : (protocolNumberUpper.contains(FYI) ? FYI : NONE));

        ProtocolActionUpdateMapping protocolAction = getNewProtocolActionUpdateMappingHook(protocolActionBo.getProtocolActionTypeCode(),
            protocol.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode(), protocol.getProtocolStatusCode(),
            specialCondition);
        
        protocol.refreshReferenceObject("protocolSubmission");
        protocolAction.setProtocol(protocol);
        protocolAction.setProtocolSubmission(getSubmissionForAction(protocolAction.getActionTypeCode(), protocol));
        protocolAction.setSubmissionTypeCode(protocolAction.getProtocolSubmission().getProtocolSubmissionType().getSubmissionTypeCode());
        protocolAction.setProtocolAction(protocolActionBo);
        rulesList.get(getUpdateRuleIndexHook()).executeRules(protocolAction);
        businessObjectService.save(protocol);
    }
    
    protected ProtocolSubmissionBase getSubmissionForAction(String actionTypeCode, ProtocolBase protocol) {
        if (ACTION_SUBMISSION_MAPPINGS.containsKey(actionTypeCode) && protocol.getProtocolSubmissions() != null) {
            for (ProtocolSubmissionBase submission : Lists.reverse(protocol.getProtocolSubmissions())) {
                if (submission.getSubmissionStatusCode().equals(ProtocolSubmissionStatus.PENDING) 
                        && ACTION_SUBMISSION_MAPPINGS.get(actionTypeCode).equals(submission.getSubmissionTypeCode())) {
                    return submission;
                }
            }
        }
        return protocol.getProtocolSubmission();
    }
        
    protected abstract ProtocolActionUpdateMapping getNewProtocolActionUpdateMappingHook(String protocolActionTypeCode,
            String submissionTypeCode, String protocolStatusCode, String specialCondition);
    
    protected abstract int getUpdateRuleIndexHook();
    
    
    public abstract  void resetProtocolStatus(ProtocolActionBase protocolActionBo, ProtocolBase protocol);    

    @Override
    public boolean isActionOpenForFollowup(String protocolActionTypeCode, ProtocolBase protocol) {
        return followupActionService.isActionOpenForFollowup(protocolActionTypeCode, protocol);
    }
    
    /**
     * Compile rules if rulehandler is not set.
     */
    public DroolsRuleHandler getCanPerformRuleHandler() {
        // compiling is slow for this rule, so try to just compile once
        if (canPerformRuleHandler == null) {
            canPerformRuleHandler = new DroolsRuleHandler(getPerformActionFileNameHook());
        }
        return canPerformRuleHandler;
    }
    
    protected abstract String getPerformActionFileNameHook();

    /**
     * 
     * This method sets the rule file.
     * @param ruleFiles
     * @throws IOException
     */
    public void setRuleFiles(List<String> ruleFiles) throws IOException {
        this.loadRules(ruleFiles);
    }

    protected void loadRules(List<String> ruleFiles) {
        rulesList = new ArrayList<DroolsRuleHandler>();
        for (String ruleFile : ruleFiles) {
            rulesList.add(new DroolsRuleHandler(ruleFile));
        }
    }
}
