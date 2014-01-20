insert into KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
	values 
	('KRA-PD','D','s2sschedulercronExpressionstarttime','CONFG','01-JAN-2010 00:00 AM','Starttime for s2s scheduler cron job to start','A');

insert into KRNS_PARM_T 
	(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD) 
	values 
	('KRA-PD','D','PI_CITIZENSHIP_FROM_CUSTOM_DATA','CONFG','01-JAN-2010 00:00 AM','It defines where the citizenship info should fetch from','A');
commit;