update krim_role_rsp_actn_t t set frc_actn = 'Y' where role_rsp_id in 
    (select role_rsp_id from krim_role_rsp_t where role_id = (select role_id from krim_role_t where nmspc_cd = 'KC-PROTOCOL' and role_nm = 'IRB Online Reviewer') 
                                               and rsp_id = (select rsp_id from krim_rsp_t where nmspc_cd = 'KC-WKFLW' and nm = 'IRB Reviewer Approve Online Review'));
