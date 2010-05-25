insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(62,'MODIFY_ANY_PROTOCOL','Modify Any Protocol Document', 2); 
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(10,62); 

commit;