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

COMMIT;