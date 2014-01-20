-- add standard permissions for proposal log
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '10', 'KC-IP', 'Create Proposal Log', 'Initiate a new Proposal Log', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '16', 'KC-IP', 'Edit Proposal Log', 'Edit a Proposal Log', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '15', 'KC-IP', 'Save Proposal Log', 'Save a Proposal Log', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '5', 'KC-IP', 'Submit Proposal Log', 'Submit a Proposal Log', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '40', 'KC-IP', 'Open Proposal Log', 'Open a Proposal Log', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '14', 'KC-IP', 'Cancel Proposal Log', 'Cancel a Proposal Log', 'Y');

-- specify doc type qualifier for proposal log permissions
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Create Proposal Log'), '3', '13', 'ProposalLogMaintenanceDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Edit Proposal Log'), '3', '13', 'ProposalLogMaintenanceDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Save Proposal Log'), '3', '13', 'ProposalLogMaintenanceDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Submit Proposal Log'), '3', '13', 'ProposalLogMaintenanceDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Open Proposal Log'), '3', '13', 'ProposalLogMaintenanceDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Cancel Proposal Log'), '3', '13', 'ProposalLogMaintenanceDocument');

-- assign proposal log permissions to OSP Administrator role
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, '1114', (select PERM_ID from KRIM_PERM_T where NM='Create Proposal Log'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, '1114', (select PERM_ID from KRIM_PERM_T where NM='Edit Proposal Log'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, '1114', (select PERM_ID from KRIM_PERM_T where NM='Save Proposal Log'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, '1114', (select PERM_ID from KRIM_PERM_T where NM='Submit Proposal Log'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, '1114', (select PERM_ID from KRIM_PERM_T where NM='Open Proposal Log'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, '1114', (select PERM_ID from KRIM_PERM_T where NM='Cancel Proposal Log'), 'Y');

-- assign osp administrator role to borst
insert into krim_role_mbr_t (role_mbr_id, ver_nbr, obj_id, role_id, mbr_id, mbr_typ_cd) values (KRIM_ROLE_MBR_ID_S.nextval, 1, sys_guid(), '1114', '10000000043', 'P');

-- qualify role assignment at unit 000001 with descend on
insert into krim_role_mbr_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID='1114' and MBR_ID='10000000043'), '1002', '1001', '000001');
insert into krim_role_mbr_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID='1114' and MBR_ID='10000000043'), '1002', '1002', 'Y');

-- add a new type for Proposal Log Derived Role: PI
insert into krim_typ_t values (KRIM_TYP_ID_S.nextval, sys_guid(), 1, 'Derived Role - Proposal Log PI', 'proposalLogPiDerivedRoleTypeService', 'Y', 'KC-IP');

-- add new PI role based on new type
insert into krim_role_t (role_id, obj_id, ver_nbr, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind) 
values (KRIM_ROLE_ID_S.nextval, sys_guid(), 1, 'Proposal Log PI', 'KC-IP', 'Derived role from PI on Proposal Log', (select KIM_TYP_ID from KRIM_TYP_T where NM='Derived Role - Proposal Log PI'), 'Y');

-- assign Open Document permission to the new PI role
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Proposal Log PI'), (select PERM_ID from KRIM_PERM_T where NM='Open Proposal Log'), 'Y');

commit;
