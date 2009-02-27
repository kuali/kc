INSERT INTO sh_parm_nmspc_t
(SH_PARM_NMSPC_CD,SH_PARM_NMSPC_NM,ACTIVE_IND, OBJ_ID, VER_NBR)
values('KC-AWARD', 'Award','Y',sys_guid(),'1');

INSERT into SH_PARM_T 
( SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_NM,  SH_PARM_TYP_CD, SH_PARM_TXT, SH_PARM_DESC, SH_PARM_CONS_CD, WRKGRP_NM, ACTIVE_IND, OBJ_ID, VER_NBR)
Values 
('KC-AWARD','D','mit.idc.validation.enabled','CONFG','1','MitIdcValidationEnabled is configurable at impl time','A','WorkflowAdmin','Y',sys_guid(),'1');

COMMIT;