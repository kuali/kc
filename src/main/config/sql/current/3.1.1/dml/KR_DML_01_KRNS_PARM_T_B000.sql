-- add mapping for Preaward authorization help and correct link for Budget Versions help
INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardSponsorAuthHelp',1,'HELP','default.htm?turl=Documents/sponsorauthorization.htm','Sponsor Authorization Help','A',sys_guid())
/
INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardInstitutionalAuthHelp',1,'HELP','default.htm?turl=Documents/institutionalauthorization.htm','Institutional Authorization Help','A',sys_guid())
/
INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardBudgetVersionsHelp',1,'HELP','default.htm?turl=Documents/budgetversions3.htm','Budget Versions Help','A',sys_guid())
/
-- parm for determining how to handle uncertified key personnel
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, obj_id)
   VALUES('KC', 'KC-AWARD', 'Document', 'awardUncertifiedKeyPersonnel', 'CONFG', '1', 'Determines whether award validation behavior when the award include uncertified Key Personnel. 0 = No validation, 1 = validation with warning message, 2 = validation with error message.', 'A', sys_guid())
/

COMMIT
/

