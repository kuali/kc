-- KIM Permissions
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(63,'MODIFY_ANY_PROTOCOL','Modify Any Protocol Document', 2); 

-- KIM Roles

-- KIM Role Permissions
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(10,63); 

commit