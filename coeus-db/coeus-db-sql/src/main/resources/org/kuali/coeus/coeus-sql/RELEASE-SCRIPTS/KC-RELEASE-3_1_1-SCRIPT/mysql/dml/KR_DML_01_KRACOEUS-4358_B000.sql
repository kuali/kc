DELIMITER /

INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardSponsorAuthHelp',1,'HELP','default.htm?turl=Documents/sponsorauthorization.htm','Sponsor Authorization Help','A',UUID())
/
INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardInstitutionalAuthHelp',1,'HELP','default.htm?turl=Documents/institutionalauthorization.htm','Institutional Authorization Help','A',UUID())
/
INSERT INTO krns_parm_t (appl_nmspc_cd, nmspc_cd, parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd,obj_id) 
   VALUES ('KC','KC-AWARD','Document','awardBudgetVersionsHelp',1,'HELP','default.htm?turl=Documents/budgetversions3.htm','Budget Versions Help','A',UUID())
/

COMMIT
/

DELIMITER ;
