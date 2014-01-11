DELIMITER /
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','propSummaryDisclaimerText',UUID(),1,'CONFG','Approval signifies that the proposed project fits within the academic framework and resources of the unit, requirements for new or renovated facilitiesspace have been discussed with the appropriate people, contributions listed will be met by the departmentcollege unless otherwise approved, that Conflict of Interest requirements have been addressed, and that Sponsored Programs may process the proposal','Display Proposal Summary Disclaimer Text','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','enableProposalSummaryPanel',UUID(),1,'CONFG','Y','Flag to display Proposal Summary panel','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','enableBudgetSummaryPanel',UUID(),1,'CONFG','Y','Flag to display Budget Summary panel','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','enableKeyPersonnelPanel',UUID(),1,'CONFG','Y','Flag to display Key Personnel panel','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','enableSummaryPrintPanel',UUID(),1,'CONFG','Y','Flag to display Summary Print Forms panel','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','enableSummaryKeywordsPanel',UUID(),1,'CONFG','Y','Flag to display Summary Keywords panel','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','enableSummaryAttachmentsPanel',UUID(),1,'CONFG','Y','Flag to display Summary Attachments panel','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','enableSummaryQuestionsPanel',UUID(),1,'CONFG','Y','Flag to display Summary Questions panel','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','enableCustomDataInfoPanel',UUID(),1,'CONFG','Y','Flag to display Custom Data Infor panel','A','KC')
/
INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','enableSpecialReviewPanel',UUID(),1,'CONFG','Y','Flag to display Special Review panel','A','KC')
/
commit
/
DELIMITER ;
