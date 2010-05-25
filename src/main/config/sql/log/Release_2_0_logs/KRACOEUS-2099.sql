insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(14,'CREATE_PROTOCOL','Create Protocol Document', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(15,'MODIFY_PROTOCOL','Modify Protocol Document', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(16,'VIEW_PROTOCOL','View Protocol Document', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(17,'MAINTAIN_PROTOCOL_ACCESS','Assign Users to Protocol Roles', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(18,'ADD_PROTOCOL_NOTES','Add Protocol Notes', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(19,'CREATE_AMMENDMENT','Create a new ammendment for a protocol', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(20,'CREATE_RENEWAL','Create a new renewal for a protocol', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(21,'MAINTAIN_PROTOCOL_RELATED_PROJ','Maintain protocol links to awards and proposals', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(22,'EDIT_PROTOCOL_BILLABLE','Edit Billable field', 2);

insert into KIM_ROLE_TYPE_T (ROLE_TYPE_CODE, DESCRIPTION) values ('R', 'Protocol');

insert into KIM_ROLES_T (ID,NAME,DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG) values(9,'Protocol Creator','Protocol Creator', 'D', 'N');
insert into KIM_ROLES_T (ID,NAME,DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG) values(10,'Protocol Aggregator','Protocol Aggregator', 'R', 'N');
insert into KIM_ROLES_T (ID,NAME,DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG) values(11,'Protocol Viewer','Protocol Viewer', 'R', 'N');

insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(9,14);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(10,15);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(10,16);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(10,17);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(10,18);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(10,19);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(10,20);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(10,21);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(10,22);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(11,16);

commit;