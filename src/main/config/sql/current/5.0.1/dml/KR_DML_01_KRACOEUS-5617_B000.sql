INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PD', 'Document', 'proposaldevelopment.proposal.summary.title', SYS_GUID(), 1, 'CONFG', 'Proposal Summary', 'Default value of Proposal Summary title', 'A', 'KC')
/

select * from krcr_parm_t where parm_nm='propSummarySpecialReview';
update krcr_parm_t set val='4,5' where parm_nm='propSummarySpecialReview';

select * from krcr_parm_t where parm_nm like 'valid%';
