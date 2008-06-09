update SH_PARM_T SET SH_PARM_TYP_CD='HELP' where SH_PARM_NM LIKE '%Help' OR SH_PARM_NM LIKE '%HelpUrl';

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetLineItemHelpUrl','HELP','blah://blah','Budget Details Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetModularHelpUrl','HELP','blah://blah','Budget Modular Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetPersonnelDetailsHelpUrl','HELP','blah://blah','Budget Personnel Details Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-B','D','budgetCostShareHelpUrl','HELP','blah://blah','Budget Cost Share Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentProposalTypeHelpUrl','HELP','blah://blah','Proposal Type Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentCustomAttributeHelpUrl','HELP','blah://blah','Custom Attribute Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentCopyCriteriaHelpUrl','HELP','blah://blah','Proposal Copy Criteria Help','A','Y');

insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','proposalDevelopmentMailByHelpUrl','HELP','blah://blah','Delivery Info Help','A','Y');