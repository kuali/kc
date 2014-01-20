ALTER TABLE AWARD_BUDGET_EXT 
    ADD BUDGET_INITIATOR VARCHAR2(60); 
ALTER TABLE AWARD_BUDGET_EXT 
    ADD DESCRIPTION VARCHAR2(255); 

insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
		values ('KC-AB','D','awardBudgetTypeNew','CONFG','1','This system parameter maps the AwardBudget type New','A');
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
		values ('KC-AB','D','awardBudgetTypeRebudget','CONFG','2','This system parameter maps the AwardBudget type Rebudget','A');
commit;