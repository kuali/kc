UPDATE KRNS_PARM_T SET PARM_NM = 'IntellectualPropertyReviewActivityHelpUrl' WHERE PARM_NM = 'InstitutionalProposalIPReviewActivityHelpUrl';

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KC-IP', 'D', 'PROPOSAL_LOG_PENDING_STATUS_CODE', 1, 'CONFG', '1', 'Code corresponding to Proposal Log status code Pending', 'A', UUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KC-IP', 'D', 'PROPOSAL_LOG_MERGED_STATUS_CODE', 1, 'CONFG', '2', 'Code corresponding to Proposal Log status code Merged', 'A', UUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KC-IP', 'D', 'PROPOSAL_LOG_SUBMITTED_STATUS_CODE', 1, 'CONFG', '3', 'Code corresponding to Proposal Log status code Submitted', 'A', UUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KC-IP', 'D', 'PROPOSAL_LOG_VOID_STATUS_CODE', 1, 'CONFG', '4', 'Code corresponding to Proposal Log status code Void', 'A', UUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KC-IP', 'D', 'PROPOSAL_LOG_TEMPORARY_STATUS_CODE', 1, 'CONFG', '5', 'Code corresponding to Proposal Log status code Temporary', 'A', UUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KC-IP', 'D', 'PROPOSAL_LOG_PERMANENT_TYPE_CODE', 1, 'CONFG', '1', 'Code corresponding to Proposal Log type code Permanent', 'A', UUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES('KC-IP', 'D', 'PROPOSAL_LOG_TEMPORARY_TYPE_CODE', 1, 'CONFG', '2', 'Code corresponding to Proposal Log type code Temporary', 'A', UUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
VALUES ('KC-IP', 'D', 'validFundingProposalStatusCodes', 1, 'CONFG', '1,2,6', 'comma delimited list of valid codes for proposal status on the institutional proposal tab', 'A', UUID()) ;

INSERT INTO KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID) 
VALUES ('KC-GEN', 'A', 'NSF_SPONSOR_CODE', 1, 'CONFG', '000500', 'The sponsor code of NSF.', 'A', UUID());

insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
values('KUALI', 'KC-AWARD', 'D', 'AWARD_CREATE_ACCOUNT', 1, 'CONFG', 'ON', 'Defines if the award financial account creation feature is on or off', 'A', UUID());

UPDATE KRNS_PARM_T SET TXT='N' where parm_nm = 'SHOW_BACK_DOOR_LOGIN_IND';

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR) 
VALUES ('KUALI', 'KC-PROTOCOL', 'D', 'irb.protocol.protocoltype.exempt', 'CONFG', '4', 'Protocol Type Exempt', 'A', UUID(), 1);

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolProtocolHelp',1,'HELP','default.htm?turl=Documents/protocol1.htm','Protocol Page Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolRequiredFieldsHelpUrl',1,'HELP','default.htm?turl=Documents/requiredfieldsforsavingdocument.htm','Protocol Required Fields for Saving Document Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolStatusAndDatesHelpUrl',1,'HELP','default.htm?turl=Documents/statusdates1.htm','Protocol Status and Dates Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAreaOfResearchHelpUrl',1,'HELP','default.htm?turl=Documents/areaofresearch.htm','Protocol Area of Research Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAdditionalInformationHelpUrl',1,'HELP','default.htm?turl=Documents/additionalinformation1.htm','Protocol Additional Information Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolOtherIdentifiersHelpUrl',1,'HELP','default.htm?turl=Documents/otheridentifiers.htm','Protocol Other Identifiers Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolOrganizationsHelpUrl',1,'HELP','default.htm?turl=Documents/organizations.htm','Protocol Organizations Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolFundingSourcesHelpUrl',1,'HELP','default.htm?turl=Documents/fundingsources.htm','Protocol Funding Sources Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolParticipantTypesHelpUrl',1,'HELP','default.htm?turl=Documents/participanttypes.htm','Protocol Participant Types Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolPersonnelHelp',1,'HELP','default.htm?turl=Documents/personnel.htm','Protocol Personnel Page Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolPersonnelHelpUrl',1,'HELP','default.htm?turl=Documents/addpersonnel.htm','Protocol Add Personnel Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolQuestionnaireHelp',1,'HELP','default.htm?turl=Documents/questionnaire.htm','Protocol Questionnaire Page Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolCustomDataHelp',1,'HELP','default.htm?turl=Documents/customdata1.htm','Protocol Custom Data Page Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolSpecialReviewHelp',1,'HELP','default.htm?turl=Documents/specialreview.htm','Protocol Special Review Page Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolPermissionsHelp',1,'HELP','default.htm?turl=Documents/permissions1.htm','Protocol Permissions Page Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolNotesAndAttachmentsHelp',1,'HELP','default.htm?turl=Documents/notesattachments.htm','Protocol Notes and Attachments Page Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAddProtocolAttachmentHelpUrl',1,'HELP','default.htm?turl=Documents/protocolattachments.htm','Protocol Add Protocol Attachments Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAddPersonnelAttachmentHelpUrl',1,'HELP','default.htm?turl=Documents/personnelattachments.htm','Protocol Add Personnel Attachments Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolNotesHelpUrl',1,'HELP','default.htm?turl=Documents/notes.htm','Protocol Notes Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolActionsHelp',1,'HELP','default.htm?turl=Documents/protocolactions.htm','Protocol Actions Page Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-PROTOCOL','D','protocolAvailableActionsHelpUrl',1,'HELP','default.htm?turl=Documents/requestanaction.htm','Protocol Available Actions Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-QUESTIONNAIRE','D','questionnaireQuestionnaireHelpUrl',1,'HELP','default.htm?turl=Documents/questionnaire1.htm','Questionnaire Page Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-QUESTIONNAIRE','D','questionnaireQuestionHelpUrl',1,'HELP','default.htm?turl=Documents/question.htm','Questionnaire Question Help','A',UUID());

INSERT INTO krns_nmspc_t (NMSPC_CD, VER_NBR, NM, ACTV_IND, OBJ_ID) 
VALUES ('KC-COMMITTEE', '1', 'KC IRB Committee', 'Y', UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeCommitteeHelp',1,'HELP','default.htm?turl=Documents/committeecommittee.htm','Committee Page Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeCommitteeHelpUrl',1,'HELP','default.htm?turl=Documents/committeecommittee1.htm','Committee Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeAreaOfResearchHelpUrl',1,'HELP','default.htm?turl=Documents/committeeareaofresearch.htm','Committee Area of Research Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeMembershipHelp',1,'HELP','default.htm?turl=Documents/committeemembership.htm','Committee Membership Page Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeMembershipHelpUrl',1,'HELP','default.htm?turl=Documents/committeemembership1.htm','Committee Membership Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeScheduleHelp',1,'HELP','default.htm?turl=Documents/committeeschedule.htm','Committee Schedule Page Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeScheduleHelpUrl',1,'HELP','default.htm?turl=Documents/committeeschedule1.htm','Committee Schedule Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeActionsHelp',1,'HELP','default.htm?turl=Documents/committeeactions.htm','Committee Actions Page Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','committeeBatchCorrespondenceHelpUrl',1,'HELP','default.htm?turl=Documents/committeebatchcorrespondence.htm','Committee Batch Correspondence Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingDetailsHelpUrl',1,'HELP','default.htm?turl=Documents/meetingdetails.htm','Meeting Details Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingProtocolSubmittedHelpUrl',1,'HELP','default.htm?turl=Documents/meetingprotocolsubmitted.htm','Meeting Protocol Submitted Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingOtherActionsHelpUrl',1,'HELP','default.htm?turl=Documents/meetingotheractions.htm','Meeting Other Actions Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingAttendanceHelpUrl',1,'HELP','default.htm?turl=Documents/meetingattendance.htm','Meeting Attendance Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingMinutesHelpUrl',1,'HELP','default.htm?turl=Documents/meetingminutes.htm','Meeting Minutes Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingGenerateAgendaHelpUrl',1,'HELP','default.htm?turl=Documents/meetinggenerateagenda.htm','Meeting Generate Agenda Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingGenerateMinutesHelpUrl',1,'HELP','default.htm?turl=Documents/meetinggenerateminutes.htm','Meeting Generate Minutes Help','A',UUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
VALUES ('KUALI','KC-COMMITTEE','D','meetingCorrespondenceHelpUrl',1,'HELP','default.htm?turl=Documents/meetingcorrespondence.htm','Meeting Correspondence Help','A',UUID());

COMMIT;