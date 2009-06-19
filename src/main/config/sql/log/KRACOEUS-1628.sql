-- These statements do two things - first, include 'KR-NS' as a searchable namespace code; second, allow all nervous system parameters to be edited by the WorkflowAdmin workgroup. 

update sh_parm_nmspc_t set ACTIVE_IND='Y' where SH_PARM_NMSPC_CD='KR-NS' 

update sh_parm_t set WRKGRP_NM='WorkflowAdmin' where WRKGRP_NM='KUALI_FMSOPS'