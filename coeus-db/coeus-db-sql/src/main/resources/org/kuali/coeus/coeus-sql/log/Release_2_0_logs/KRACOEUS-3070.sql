INSERT INTO krns_PARM_T 
	(nmspc_cd, parm_dtl_typ_cd, parm_nm, OBJ_ID, VER_NBR, parm_typ_cd, txt, parm_desc_txt, cons_cd) 
	VALUES 
	('KRA-PD','D','FEDERAL_ID_COMES_FROM_CURRENT_AWARD',sys_guid(),1,'CONFG','N','Determines whether the Grants.Gov Federal ID must be populated from the current award.','A');
INSERT
INTO krns_parm_t(nmspc_cd,    parm_dtl_typ_cd,    parm_nm,    obj_id,    ver_nbr,    parm_typ_cd,    txt,    parm_desc_txt,    cons_cd)
VALUES('KRA-PD',    'D',    'proposaldevelopment.proposaltype.resubmission',    sys_guid(),    1,    'CONFG',    '2',    'ProposalTypeCode of RESUBMISSION',    'A');
commit;