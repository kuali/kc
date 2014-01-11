--new namespaces for help links
INSERT INTO KRNS_NMSPC_T (NMSPC_CD, NM, ACTV_IND)
VALUES('KC-T', 'Time And Money', 'Y') ;
INSERT INTO KRNS_NMSPC_T (NMSPC_CD, NM, ACTV_IND)
VALUES('KC-GEN', 'General Kuali Coeus', 'Y') ;
--new system parameters for help links
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-IP','D','InstitutionalProposalHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-IP','D','InstitutionalProposalConstactsHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Contacts Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-IP','D','InstitutionalProposalCustomDataHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Custom Data Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-IP','D','InstitutionalProposalSpecialReviewHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Special Review Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-IP','D','InstitutionalProposalIPReviewHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Intellectual Property Review Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-IP','D','InstitutionalProposalDistributionHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Distribution Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-IP','D','InstitutionalProposalActionsHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Actions Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-IP','D','InstitutionalProposalIPReviewActivityHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Intellectual Property Reivew Activity Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardHelpUrl',sys_guid(),1,'HELP','default.htm','Award Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardHomeHelp',sys_guid(),1,'HELP','default.htm','Award Home Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardContactsHelp',sys_guid(),1,'HELP','default.htm','Award Contacts Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardCommitmentsHelp',sys_guid(),1,'HELP','default.htm','Award Commitments Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardTimeAndMoneyHelp',sys_guid(),1,'HELP','default.htm','Award Time and Money Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardPaymentsReportsAndTermsHelp',sys_guid(),1,'HELP','default.htm','Award Payments Reports and Terms Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardSpecialReviewHelp',sys_guid(),1,'HELP','default.htm','Award Special Review Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardCustomDataHelp',sys_guid(),1,'HELP','default.htm','Award Custom Data Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardQuestionsHelp',sys_guid(),1,'HELP','default.htm','Award Questions Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardPermissionsHelp',sys_guid(),1,'HELP','default.htm','Award Permissions Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardNoteAndAttachmentsHelp',sys_guid(),1,'HELP','default.htm','Award Note and Attachments Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardActionsHelp',sys_guid(),1,'HELP','default.htm','Award Actions Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardApprovedEquipmentHelpUrl',sys_guid(),1,'HELP','default.htm','Award Approved Equipment Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardApprovedForeignTravelHelpUrl',sys_guid(),1,'HELP','default.htm','Award Approved Foreign Travel Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardApprovedSubawardHelpUrl',sys_guid(),1,'HELP','default.htm','Award Approved Subaward Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardCommentHelpUrl',sys_guid(),1,'HELP','default.htm','Award Comment Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardContactHelpUrl',sys_guid(),1,'HELP','default.htm','Award Contact Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardCostShareHelpUrl',sys_guid(),1,'HELP','default.htm','Award Cost Share Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardCustomDataHelpUrl',sys_guid(),1,'HELP','default.htm','Award Custom Data Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardPersonCreditSplitHelpUrl',sys_guid(),1,'HELP','default.htm','Award Person Credit Split Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','CommentTypeHelpUrl',sys_guid(),1,'HELP','default.htm','Award Comment Type Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','CostShareTypeHelpUrl',sys_guid(),1,'HELP','default.htm','Award Cost Share Type Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardFandaRateHelpUrl',sys_guid(),1,'HELP','default.htm','Award F and A Rate Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardReportTermHelpUrl',sys_guid(),1,'HELP','default.htm','Award Report Term Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardCloseoutHelpUrl',sys_guid(),1,'HELP','default.htm','Award Report Term Help','A');	
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-AWARD','D','awardAttachmentsHelpUrl',sys_guid(),1,'HELP','default.htm','Award Attachments Help','A');		
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-T','D','timeAndMoneyHelp',sys_guid(),1,'HELP','default.htm','Time And Money Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-T','D','TransactionHelp',sys_guid(),1,'HELP','default.htm','Transaction Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-T','D','PendingTransactionHelp',sys_guid(),1,'HELP','default.htm','Pending Transaction Help','A');
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-T','D','awardHierarchyNodeHelpUrl',sys_guid(),1,'HELP','default.htm','Award Hierarchy Help','A');	
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-T','D','awardHierarchyHelpUrl',sys_guid(),1,'HELP','default.htm','Award Hierarchy Help','A');	
INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KC-GEN','D','permissionsHelpUrl',sys_guid(),1,'HELP','default.htm','Institutional Proposal Intellectual Property Reivew Activity Help','A');
COMMIT;