insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(61, 'SUBMIT_PROTOCOL', 'Submit a Protocol to IRB for review', 2);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(10,61);
commit;
