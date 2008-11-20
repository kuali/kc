insert into KIM_NAMESPACES_T (ID,NAME,DESCRIPTION) values(1,'KIM','KIM Namespace');
insert into KIM_NAMESPACES_T (ID,NAME,DESCRIPTION) values(2,'KRA','KRA Namespace');

insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(1,'CREATE_PROPOSAL','Create Proposal Development Document', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(2,'MODIFY_PROPOSAL','Modify Proposal Development Document', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(3,'VIEW_PROPOSAL','View Proposal Development Document', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(4,'MODIFY_NARRATIVE','Create/Modify Proposal Narrative', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(5,'VIEW_NARRATIVE','View Proposal Narrative', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(6,'MODIFY_BUDGET','Create/Modify Proposal Budget', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(7,'VIEW_BUDGET','View Proposal Budget', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(8,'MAINTAIN_PROPOSAL_ACCESS','Assign Users to Proposal Roles', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(9,'ALTER_PROPOSAL_DATA','Change proposal master data once the proposal is locked', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(10,'PRINT_PROPOSAL','Print proposal on a sponsor specific path', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(11,'CERTIFY','Certify', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(12,'SUBMIT_TO_SPONSOR','Submit a Proposal to Grants.gov', 2);
insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(13,'SUBMIT_PROPOSAL','Submit a Proposal for approval', 2);

insert into KIM_ROLE_TYPE_T (ROLE_TYPE_CODE, DESCRIPTION) values ('P', 'Proposal');
insert into KIM_ROLE_TYPE_T (ROLE_TYPE_CODE, DESCRIPTION) values ('O', 'OSP');
insert into KIM_ROLE_TYPE_T (ROLE_TYPE_CODE, DESCRIPTION) values ('D', 'Department');

insert into KIM_ROLES_T (ID,NAME,DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG) values(1,'Proposal Creator','Proposal Creator', 'D', 'N');
insert into KIM_ROLES_T (ID,NAME,DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG) values(2,'Aggregator','Proposal Aggregator', 'P', 'N');
insert into KIM_ROLES_T (ID,NAME,DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG) values(3,'Narrative Writer','Proposal Narrative Writer', 'P', 'N');
insert into KIM_ROLES_T (ID,NAME,DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG) values(4,'Budget Creator','Proposal Budget Creator', 'P', 'N');
insert into KIM_ROLES_T (ID,NAME,DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG) values(5,'Viewer','Proposal Viewer', 'P', 'N');
insert into KIM_ROLES_T (ID,NAME,DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG) values(6,'unassigned','Unassigned - no permissions', 'P', 'N');
insert into KIM_ROLES_T (ID,NAME,DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG) values(7,'OSP Administrator','OSP Administrator', 'O', 'Y');
insert into KIM_ROLES_T (ID,NAME,DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG) values(8,'Proposal Submission','Proposal Submission', 'O', 'Y');

insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(1,1);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,2);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,3);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,4);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,5);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,6);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,7);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,8);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,13);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(3,2);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(3,3);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(3,4);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(3,5);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(3,7);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(4,2);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(4,3);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(4,5);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(4,6);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(4,7);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(5,3);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(5,5);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(5,7);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,11);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(7,3);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(7,9);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(8,9);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(8,10);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,10);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(3,10);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(4,10);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(5,10);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(8,12);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(7,5);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(7,7);

insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (1, 'quickstart', 'fK69ATFsAydwQuteang+xMva+Tc=');
insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (2, 'ljoconno', 'fK69ATFsAydwQuteang+xMva+Tc=');
insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (3, 'bhutchin', 'fK69ATFsAydwQuteang+xMva+Tc=');
insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (4, 'aslusar', 'fK69ATFsAydwQuteang+xMva+Tc=');
insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (5, 'tdurkin', 'fK69ATFsAydwQuteang+xMva+Tc=');
insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (6, 'pcberg', 'fK69ATFsAydwQuteang+xMva+Tc=');
insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (7, 'jtester', 'fK69ATFsAydwQuteang+xMva+Tc=');
insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (8, 'KULUSER', 'fK69ATFsAydwQuteang+xMva+Tc=');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (1, '000000003', 1, '000001', 'N', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (2, '000000003', 1, 'BL-IIDC', 'N', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (3, '000000003', 1, 'IN-CARD', 'Y', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (4, '000000004', 1, '000001', 'N', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (5, '000000004', 1, 'BL-IIDC', 'N', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (6, '000000004', 1, 'IN-CARD', 'Y', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (7, '000000005', 1, '000001', 'N', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (8, '000000005', 1, 'BL-IIDC', 'N', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (9, '000000005', 1, 'IN-CARD', 'Y', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (10, '000000006', 1, '000001', 'N', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (11, '000000006', 1, 'BL-IIDC', 'N', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (12, '000000006', 1, 'IN-CARD', 'Y', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (13, '000000001', 1, '000001', 'N', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (14, '000000001', 1, 'BL-IIDC', 'N', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (15, '000000001', 1, 'IN-CARD', 'Y', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (16, '000000002', 1, '000001', 'N', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (17, '000000002', 1, 'BL-IIDC', 'N', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (18, '000000002', 1, 'IN-CARD', 'Y', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (19, '000000008', 1, '000001', 'N', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (20, '000000008', 1, 'BL-IIDC', 'N', 'Y');

insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG)
values (21, '000000008', 1, 'IN-CARD', 'Y', 'Y');

