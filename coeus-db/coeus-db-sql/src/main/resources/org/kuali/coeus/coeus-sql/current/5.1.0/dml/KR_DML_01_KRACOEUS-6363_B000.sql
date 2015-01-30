--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolProtocolHelp', 1, 'HELP', 
			'default.htm?turl=Documents/iacucprotocol.htm', 'IACUC Protocol Page Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolRequiredFieldsHelpUrl', 1, 'HELP', 
			'default.htm?turl=Documents/requiredfieldsforsavingdocument2.htm', 'IACUC Protocol Required Fields for Saving Document Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolStatusAndDatesHelpUrl', 1, 'HELP', 
			'default.htm?turl=Documents/statusdates1.htm', 'IACUC Protocol Status and Dates Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolAreaOfResearchHelpUrl', 1, 'HELP', 
			'default.htm?turl=Documents/areaofresearch2.htm', 'IACUC Protocol Area of Research Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolAdditionalInformationHelpUrl', 1, 'HELP', 
			'default.htm?turl=Documents/additionalinformation2.htm', 'IACUC Protocol Additional Information Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolOtherIdentifiersHelpUrl', 1, 'HELP', 
			'default.htm?turl=Documents/otheridentifiers1.htm', 'IACUC Protocol Other Identifiers Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolOrganizationsHelpUrl', 1, 'HELP', 
			'default.htm?turl=Documents/organizations1.htm', 'IACUC Protocol Organizations Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolFundingSourcesHelpUrl', 1, 'HELP', 
			'default.htm?turl=Documents/fundingsources1.htm', 'IACUC Protocol Funding Sources Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolPersonnelHelp', 1, 'HELP', 
			'default.htm?turl=Documents/personnel3.htm', 'IACUC Protocol Personnel Page Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolAddPersonnelHelp', 1, 'HELP', 
			'default.htm?turl=Documents/addpersonnel1.htm', 'IACUC Protocol Add Personnel Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'iacucProtocolPersonnelAttachmentSectionHelp', 1, 'HELP', 
			'default.htm?turl=Documents/personnelattachments2.htm ', 'IACUC Protocol Personnel Attachment Section Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'iacucProtocolThreeRsPageHelp', 1, 'HELP', 
			'default.htm?turl=Documents/thethreers.htm', 'IACUC Protocol Three Rs Page Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'iacucProtocolThreeRsPrinciplesHelp', 1, 'HELP', 
			'default.htm?turl=Documents/thethreers1.htm', 'IACUC Protocol Three Rs Principles Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'iacucProtocolAlternateSearchHelp', 1, 'HELP', 
			'default.htm?turl=Documents/alternatesearch.htm', 'IACUC Protocol Alternate Search Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolQuestionnaireHelp', 1, 'HELP', 
			'default.htm?turl=Documents/questionnaire3.htm', 'IACUC Protocol Questionnaire Page Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'iacucProtocolSpeciesGroupsHelp', 1, 'HELP', 
			'default.htm?turl=Documents/speciesgroups.htm', 'IACUC Protocol Species Groups Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'iacucProtocolProceduresHelp', 1, 'HELP', 
			'default.htm?turl=Documents/procedures.htm', 'IACUC Protocol Procedures Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'iacucProtocolOverviewAndTimelineHelp', 1, 'HELP', 
			'default.htm?turl=Documents/overviewandtimeline.htm', 'IACUC Protocol Overview And Timeline Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'iacucProtocolIncludedCategoriesHelp', 1, 'HELP', 
			'default.htm?turl=Documents/includedcategories.htm', 'IACUC Protocol Included Categories Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'iacucProtocolProceduresSpecificSectionHelp', 1, 'HELP', 
			'default.htm?turl=Documents/procedurespecificsectionegdrugsanalgesics.htm', 'IACUC Protocol Procedures Specific Section Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'iacucProtocolExceptionHelp', 1, 'HELP', 
			'default.htm?turl=Documents/protocolexception.htm', 'IACUC Protocol Exception Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolCustomDataHelp', 1, 'HELP', 
			'default.htm?turl=Documents/customdata6.htm', 'IACUC Protocol Custom Data Page Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolPermissionsHelp', 1, 'HELP', 
			'default.htm?turl=Documents/permissions2.htm', 'IACUC Protocol Permissions Page Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolNotesAndAttachmentsHelp', 1, 'HELP', 
			'default.htm?turl=Documents/notesattachments3.htm', 'IACUC Protocol Notes and Attachments Page Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'iacucProtocolAttachmentsHelp', 1, 'HELP', 
			'default.htm?turl=Documents/protocolattachments1.htm', 'IACUC Protocol Attachments Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolNotesHelpUrl', 1, 'HELP', 
			'default.htm?turl=Documents/notes9.htm', 'IACUC Protocol Notes Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolActionsHelp', 1, 'HELP', 
			'default.htm?turl=Documents/protocolactions1.htm', 'IACUC Protocol Actions Page Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolAvailableActionsHelpUrl', 1, 'HELP', 
			'default.htm?turl=Documents/requestanaction1.htm', 'IACUC Protocol Available Actions Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolPrintHelp', 1, 'HELP', 
			'default.htm?turl=Documents/print4.htm', 'IACUC Protocol Print Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolSummaryAndHistoryHelp', 1, 'HELP', 
			'default.htm?turl=Documents/summaryhistory1.htm', 'IACUC Protocol Summary and History Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolSummaryHelp', 1, 'HELP', 
			'default.htm?turl=Documents/summary3.htm', 'IACUC Protocol Summary Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolSubmissionDetailsHelp', 1, 'HELP', 
			'default.htm?turl=Documents/submissiondetails1.htm', 'IACUC Protocol Submission Details Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolHistoryHelp', 1, 'HELP', 
			'default.htm?turl=Documents/history1.htm', 'IACUC Protocol History Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolActionCopyHelp', 1, 'HELP', 
			'default.htm?turl=Documents/copytonewdocument2.htm', 'IACUC Copy Protocol Help', 'A', SYS_GUID())
/

INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
	VALUES ('KC', 'KC-IACUC', 'Document', 'protocolDataValidationHelp', 1, 'HELP', 
			'default.htm?turl=Documents/datavalidation5.htm', 'IACUC Protocol Data Validation Help', 'A', SYS_GUID())
/

