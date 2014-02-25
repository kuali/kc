-- add standard permissions for institutional proposal
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '10', 'KC-IP', 'Create Institutional Proposal', 'Initiate a new Institutional Proposal', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '16', 'KC-IP', 'Edit Institutional Proposal', 'Edit a Institutional Proposal', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '15', 'KC-IP', 'Save Institutional Proposal', 'Save a Institutional Proposal', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '5', 'KC-IP', 'Submit Institutional Proposal', 'Submit a Institutional Proposal', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '40', 'KC-IP', 'Open Institutional Proposal', 'Open a Institutional Proposal', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '14', 'KC-IP', 'Cancel Institutional Proposal', 'Cancel a Institutional Proposal', 'Y');

-- specify doc type qualifier for institutional proposal permissions
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Create Institutional Proposal'), '3', '13', 'InstitutionalProposalDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Edit Institutional Proposal'), '3', '13', 'InstitutionalProposalDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Save Institutional Proposal'), '3', '13', 'InstitutionalProposalDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Submit Institutional Proposal'), '3', '13', 'InstitutionalProposalDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), '3', '13', 'InstitutionalProposalDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Cancel Institutional Proposal'), '3', '13', 'InstitutionalProposalDocument');

-- add standard permissions for IP Review
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '10', 'KC-IP', 'Create Intellectual Property Review', 'Initiate a new Intellectual Property Review', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '16', 'KC-IP', 'Edit Intellectual Property Review', 'Edit a Intellectual Property Review', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '15', 'KC-IP', 'Save Intellectual Property Review', 'Save a Intellectual Property Review', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '5', 'KC-IP', 'Submit Intellectual Property Review', 'Submit a Intellectual Property Review', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '40', 'KC-IP', 'Open Intellectual Property Review', 'Open a Intellectual Property Review', 'Y');
insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, '14', 'KC-IP', 'Cancel Intellectual Property Review', 'Cancel a Intellectual Property Review', 'Y');

-- specify doc type qualifier for IP Review permissions
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Create Intellectual Property Review'), '3', '13', 'IntellectualPropertyReviewMaintenanceDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Edit Intellectual Property Review'), '3', '13', 'IntellectualPropertyReviewMaintenanceDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Save Intellectual Property Review'), '3', '13', 'IntellectualPropertyReviewMaintenanceDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Submit Intellectual Property Review'), '3', '13', 'IntellectualPropertyReviewMaintenanceDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Open Intellectual Property Review'), '3', '13', 'IntellectualPropertyReviewMaintenanceDocument');
insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Cancel Intellectual Property Review'), '3', '13', 'IntellectualPropertyReviewMaintenanceDocument');

-- create new role: Institutional Proposal Viewer
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values (KRIM_ROLE_ID_S.nextval, sys_guid(), 1, 'Institutional Proposal Viewer', 'KC-IP', 'View Institutional Proposals', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'), 'Y');

-- create new role: Institutional Proposal Maintainer
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values (KRIM_ROLE_ID_S.nextval, sys_guid(), 1, 'Institutional Proposal Maintainer', 'KC-IP', 'Maintain Institutional Proposals', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'), 'Y');

-- create new role: IP Review Maintainer
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values (KRIM_ROLE_ID_S.nextval, sys_guid(), 1, 'Intellectual Property Review Maintainer', 'KC-IP', 'Maintain Intellectual Property Review', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'), 'Y');

-- assign institutional proposal (view) & IP (view) permissions to View Institutional Proposal role
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Viewer'), (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Viewer'), (select PERM_ID from KRIM_PERM_T where NM='Open Intellectual Property Review'), 'Y');

-- assign institutional proposal (mod) & IP permissions (mod) to Modify Institutional Proposal role
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Create Institutional Proposal'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Edit Institutional Proposal'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Save Institutional Proposal'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Submit Institutional Proposal'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Cancel Institutional Proposal'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Create Intellectual Property Review'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Edit Intellectual Property Review'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Save Intellectual Property Review'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Submit Intellectual Property Review'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Open Intellectual Property Review'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Cancel Intellectual Property Review'), 'Y');

-- assign institutional proposal (view) & IP (mod) permissions to IP Review Maintainer role
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Intellectual Property Review Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Intellectual Property Review Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Create Intellectual Property Review'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Intellectual Property Review Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Edit Intellectual Property Review'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Intellectual Property Review Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Save Intellectual Property Review'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Intellectual Property Review Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Submit Intellectual Property Review'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Intellectual Property Review Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Open Intellectual Property Review'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Intellectual Property Review Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Cancel Intellectual Property Review'), 'Y');

-- assign Institutional Proposal Maintainer role to quickstart
insert into krim_role_mbr_t (role_mbr_id, ver_nbr, obj_id, role_id, mbr_id, mbr_typ_cd) values (KRIM_ROLE_MBR_ID_S.nextval, 1, sys_guid(), (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), '10000000001', 'P');

-- qualify role assignment at unit 000001 with descend on
insert into krim_role_mbr_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID=(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer') and MBR_ID='10000000001'), '1002', '1001', '000001');
insert into krim_role_mbr_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID=(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer') and MBR_ID='10000000001'), '1002', '1002', 'Y');

-- assign Institutional Proposal Viewer role to borst
insert into krim_role_mbr_t (role_mbr_id, ver_nbr, obj_id, role_id, mbr_id, mbr_typ_cd) values (KRIM_ROLE_MBR_ID_S.nextval, 1, sys_guid(), (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Viewer'), '10000000043', 'P');

-- qualify role assignment at unit 000001 with descend on
insert into krim_role_mbr_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID=(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Viewer') and MBR_ID='10000000043'), '1002', '1001', '000001');
insert into krim_role_mbr_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID=(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Viewer') and MBR_ID='10000000043'), '1002', '1002', 'Y');

-- assign IP Review Maintainer role to chew
insert into krim_role_mbr_t (role_mbr_id, ver_nbr, obj_id, role_id, mbr_id, mbr_typ_cd) values (KRIM_ROLE_MBR_ID_S.nextval, 1, sys_guid(), (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Intellectual Property Review Maintainer'), '10000000005', 'P');

-- qualify role assignment at unit 000001 with descend on
insert into krim_role_mbr_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID=(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Intellectual Property Review Maintainer') and MBR_ID='10000000005'), '1002', '1001', '000001');
insert into krim_role_mbr_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID=(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Intellectual Property Review Maintainer') and MBR_ID='10000000005'), '1002', '1002', 'Y');

-- add a new type for Derived Role: Unit Administrator
insert into krim_typ_t values (KRIM_TYP_ID_S.nextval, sys_guid(), 1, 'Derived Role - Unit Administrator', 'unitAdministratorDerivedRoleTypeService', 'Y', 'KC-IP');

-- add new Unit Administrator role based on Unit Administrator type
insert into krim_role_t (role_id, obj_id, ver_nbr, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind) 
values (KRIM_ROLE_ID_S.nextval, sys_guid(), 1, 'Unit Administrator', 'KC-IP', 'Derived role based on Unit', (select KIM_TYP_ID from KRIM_TYP_T where NM='Derived Role - Unit Administrator'), 'Y');

-- assign Open Document permissions to the new Unit Administrator role
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Unit Administrator'), (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Unit Administrator'), (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), 'Y');

COMMIT;