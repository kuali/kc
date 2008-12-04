 -- New Flags for budget cost sharing and unrecovered f and a allocation enforcement

INSERT
INTO sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, obj_id, ver_nbr, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, wrkgrp_nm, active_ind)
VALUES('KRA-B', 'D', 'budgetCostSharingEnforcementFlag', '205246DF7F364A3083357960305030C5',1, 'CONFG', 'Y', 'Flag indicating if Cost Sharing allocation should be enforced', 'A', 'WorkflowAdmin', 'Y');

INSERT
INTO sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, obj_id, ver_nbr, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, wrkgrp_nm, active_ind)
VALUES('KRA-B', 'D', 'budgetUnrecoveredFandAEnforcementFlag', 'C9E4C6277E234C45BF0E1D041B2491CF',1, 'CONFG', 'Y', 'Flag indicating if Unrecovered F and A allocation should be enforced', 'A', 'WorkflowAdmin', 'Y');
