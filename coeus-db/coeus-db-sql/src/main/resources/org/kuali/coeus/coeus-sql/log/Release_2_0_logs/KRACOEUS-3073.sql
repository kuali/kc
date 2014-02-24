update krim_grp_t set grp_nm = 'ProposalAdmin' where grp_nm = 'Proposal Admin' and nmspc_cd = 'KC-WKFLW';
update krim_rsp_attr_data_t set attr_val = 'OSPInitial' where attr_val = 'OspInitial'; 
update krim_role_t t set t.role_nm = 'PI' where role_nm = 'PrimaryInvestigator' and nmspc_cd = 'KC-WKFLW';
update krim_role_t t set t.role_nm = 'COI' where role_nm = 'CoInvestigator' and nmspc_cd = 'KC-WKFLW';
update krim_role_t t set t.role_nm = 'KP' where role_nm = 'KeyPerson' and nmspc_cd = 'KC-WKFLW';
commit;