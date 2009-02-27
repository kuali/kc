insert into KIM_PERMISSIONS_T (ID, NAMESPACE_ID, NAME, DESCRIPTION) values (14, 2, 'ADD_PROPOSAL_VIEWER', 'Assign User to Proposal Viewer Role');
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID, PERMISSION_ID) values (2, 14);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID, PERMISSION_ID) values (7, 14);
