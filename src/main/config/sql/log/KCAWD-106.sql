INSERT INTO SH_PARM_T 
	(SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,SH_PARM_NM,OBJ_ID,VER_NBR,SH_PARM_TYP_CD,SH_PARM_TXT,SH_PARM_DESC,SH_PARM_CONS_CD,WRKGRP_NM,ACTIVE_IND) 
	VALUES 
	('KC-AWARD','D','scheduleGenerationPeriodInYearsWhenFrequencyBaseCodeIsFinalExpirationDate',sys_guid(),1,'CONFG','1','Schedule Generation Period In Years When Frequency Base Code Is Final Expiration Date','A','WorkflowAdmin','Y');
commit;