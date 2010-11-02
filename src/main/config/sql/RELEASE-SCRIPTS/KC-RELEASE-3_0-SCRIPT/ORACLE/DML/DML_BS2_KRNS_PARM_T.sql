UPDATE KRNS_PARM_T SET PARM_NM = 'IntellectualPropertyReviewActivityHelpUrl' WHERE PARM_NM = 'InstitutionalProposalIPReviewActivityHelpUrl';

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KC-IP', 'D', 'PROPOSAL_LOG_PENDING_STATUS_CODE', 1, 'CONFG', '1', 'Code corresponding to Proposal Log status code Pending', 'A', SYS_GUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KC-IP', 'D', 'PROPOSAL_LOG_MERGED_STATUS_CODE', 1, 'CONFG', '2', 'Code corresponding to Proposal Log status code Merged', 'A', SYS_GUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KC-IP', 'D', 'PROPOSAL_LOG_SUBMITTED_STATUS_CODE', 1, 'CONFG', '3', 'Code corresponding to Proposal Log status code Submitted', 'A', SYS_GUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KC-IP', 'D', 'PROPOSAL_LOG_VOID_STATUS_CODE', 1, 'CONFG', '4', 'Code corresponding to Proposal Log status code Void', 'A', SYS_GUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KC-IP', 'D', 'PROPOSAL_LOG_TEMPORARY_STATUS_CODE', 1, 'CONFG', '5', 'Code corresponding to Proposal Log status code Temporary', 'A', SYS_GUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KC-IP', 'D', 'PROPOSAL_LOG_PERMANENT_TYPE_CODE', 1, 'CONFG', '1', 'Code corresponding to Proposal Log type code Permanent', 'A', SYS_GUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KC-IP', 'D', 'PROPOSAL_LOG_TEMPORARY_TYPE_CODE', 1, 'CONFG', '2', 'Code corresponding to Proposal Log type code Temporary', 'A', SYS_GUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES ('KC-IP', 'D', 'validFundingProposalStatusCodes', 1, 'CONFG', '1,2,6', 'comma delimited list of valid codes for proposal status on the institutional proposal tab', 'A', SYS_GUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID) 
VALUES ('KC-GEN', 'A', 'NSF_SPONSOR_CODE', 1, 'CONFG', '000500', 'The sponsor code of NSF.', 'A', SYS_GUID());

insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
values('KUALI', 'KC-AWARD', 'D', 'AWARD_CREATE_ACCOUNT', 1, 'CONFG', 'ON', 'Defines if the award financial account creation feature is on or off', 'A', SYS_GUID());

UPDATE KRNS_PARM_T SET TXT='N' where parm_nm = 'SHOW_BACK_DOOR_LOGIN_IND';

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR) 
VALUES ('KUALI', 'KC-PROTOCOL', 'D', 'irb.protocol.protocoltype.exempt', 'CONFG', '4', 'Protocol Type Exempt', 'A', SYS_GUID(), 1);

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolProtocolHelp',1,'HELP','default.htm?turl=Documents/protocol1.htm','Protocol Page Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolRequiredFieldsHelpUrl',1,'HELP','default.htm?turl=Documents/requiredfieldsforsavingdocument.htm','Protocol Required Fields for Saving Document Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolStatusAndDatesHelpUrl',1,'HELP','default.htm?turl=Documents/statusdates1.htm','Protocol Status and Dates Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAreaOfResearchHelpUrl',1,'HELP','default.htm?turl=Documents/areaofresearch.htm','Protocol Area of Research Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAdditionalInformationHelpUrl',1,'HELP','default.htm?turl=Documents/additionalinformation1.htm','Protocol Additional Information Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolOtherIdentifiersHelpUrl',1,'HELP','default.htm?turl=Documents/otheridentifiers.htm','Protocol Other Identifiers Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolOrganizationsHelpUrl',1,'HELP','default.htm?turl=Documents/organizations.htm','Protocol Organizations Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolFundingSourcesHelpUrl',1,'HELP','default.htm?turl=Documents/fundingsources.htm','Protocol Funding Sources Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolParticipantTypesHelpUrl',1,'HELP','default.htm?turl=Documents/participanttypes.htm','Protocol Participant Types Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolPersonnelHelp',1,'HELP','default.htm?turl=Documents/personnel3.htm','Protocol Personnel Page Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolPersonnelHelpUrl',1,'HELP','default.htm?turl=Documents/addpersonnel.htm','Protocol Add Personnel Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolQuestionnaireHelp',1,'HELP','default.htm?turl=Documents/questionnaire.htm','Protocol Questionnaire Page Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolCustomDataHelp',1,'HELP','default.htm?turl=Documents/customdata1.htm','Protocol Custom Data Page Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolSpecialReviewHelp',1,'HELP','default.htm?turl=Documents/specialreview.htm','Protocol Special Review Page Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolPermissionsHelp',1,'HELP','default.htm?turl=Documents/permissions1.htm','Protocol Permissions Page Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolNotesAndAttachmentsHelp',1,'HELP','default.htm?turl=Documents/notesattachments.htm','Protocol Notes and Attachments Page Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAddProtocolAttachmentHelpUrl',1,'HELP','default.htm?turl=Documents/protocolattachments.htm','Protocol Add Protocol Attachments Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAddPersonnelAttachmentHelpUrl',1,'HELP','default.htm?turl=Documents/personnelattachments.htm','Protocol Add Personnel Attachments Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolNotesHelpUrl',1,'HELP','default.htm?turl=Documents/notes.htm','Protocol Notes Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolActionsHelp',1,'HELP','default.htm?turl=Documents/protocolactions.htm','Protocol Actions Page Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAvailableActionsHelpUrl',1,'HELP','default.htm?turl=Documents/requestanaction.htm','Protocol Available Actions Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-QUESTIONNAIRE','D','questionnaireQuestionnaireHelpUrl',1,'HELP','default.htm?turl=Documents/questionnaire1.htm','Questionnaire Page Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-QUESTIONNAIRE','D','questionnaireQuestionHelpUrl',1,'HELP','default.htm?turl=Documents/question.htm','Questionnaire Question Help','A',SYS_GUID());

INSERT INTO krns_nmspc_t (NMSPC_CD, VER_NBR, NM, ACTV_IND, OBJ_ID) 
VALUES ('KC-COMMITTEE', '1', 'KC IRB Committee', 'Y', SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeCommitteeHelp',1,'HELP','default.htm?turl=Documents/committee1.htm','Committee Page Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeCommitteeHelpUrl',1,'HELP','default.htm?turl=Documents/committee2.htm','Committee Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeAreaOfResearchHelpUrl',1,'HELP','default.htm?turl=Documents/areaofresearch1.htm','Committee Area of Research Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeMembershipHelp',1,'HELP','default.htm?turl=Documents/members.htm','Committee Membership Page Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeMembershipHelpUrl',1,'HELP','default.htm?turl=Documents/memberdetailssection.htm','Committee Membership Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeScheduleHelp',1,'HELP','default.htm?turl=Documents/schedule.htm','Committee Schedule Page Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeScheduleHelpUrl',1,'HELP','default.htm?turl=Documents/schedule1.htm','Committee Schedule Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeActionsHelp',1,'HELP','default.htm?turl=Documents/actions1.htm','Committee Actions Page Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeBatchCorrespondenceHelpUrl',1,'HELP','default.htm?turl=Documents/batchcorrespondence.htm','Committee Batch Correspondence Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingDetailsHelpUrl',1,'HELP','default.htm?turl=Documents/meetingdetails.htm','Meeting Details Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingProtocolSubmittedHelpUrl',1,'HELP','default.htm?turl=Documents/protocolsubmitted.htm','Meeting Protocol Submitted Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingOtherActionsHelpUrl',1,'HELP','default.htm?turl=Documents/otheractions.htm','Meeting Other Actions Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingAttendanceHelpUrl',1,'HELP','default.htm?turl=Documents/attendance.htm','Meeting Attendance Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingMinutesHelpUrl',1,'HELP','default.htm?turl=Documents/minutes.htm','Meeting Minutes Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingGenerateAgendaHelpUrl',1,'HELP','default.htm?turl=Documents/agenda.htm','Meeting Generate Agenda Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingGenerateMinutesHelpUrl',1,'HELP','default.htm?turl=Documents/minutes1.htm','Meeting Generate Minutes Help','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingCorrespondenceHelpUrl',1,'HELP','default.htm?turl=Documents/correspondence.htm','Meeting Correspondence Help','A',SYS_GUID());

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
values ('KC-B','D','SUBCONTRACTOR_F_AND_A_GT_25K','CONFG','420630','Subcontract F&A greater than 25K','A', SYS_GUID());

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
values ('KC-B','D','SUBCONTRACTOR_F_AND_A_LT_25K','CONFG','420610','Subcontract F&A less than 25K','A',SYS_GUID());

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
values ('KC-B','D','BROAD_F_AND_A','CONFG','421502','Broad F&A','A',SYS_GUID());

COMMIT;