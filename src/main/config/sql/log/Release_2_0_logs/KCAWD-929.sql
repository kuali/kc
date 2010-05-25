insert into award_budget_status (award_budget_status_code,description,ver_nbr,update_timestamp,update_user,obj_id) 
	values ('12','Do Not Post',1,to_date('2010-01-25 09:11:00', 'YYYY-MM-DD HH24:MI:SS'),'KRADBA','A0369A8818C74721B1BF4F95F1AC4A23');
insert into krns_parm_t (appl_nmspc_cd,nmspc_cd,parm_dtl_typ_cd,parm_nm,ver_nbr,parm_typ_cd,txt,parm_desc_txt,cons_cd, obj_id) 
	values ('KUALI','KC-AB','D','awardBudgetStatusDoNotPost',1,'CONFG','12','This system parameter maps the AwardBudget status Do Not Post','A', '8220798A9106418787B7A3F038796D13');
