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

insert into KIM_ROLES_T (ID,NAME,DESCRIPTION) values(1,'Proposal Creator','Proposal Creator');
insert into KIM_ROLES_T (ID,NAME,DESCRIPTION) values(2,'Aggregator','Proposal Aggregator');
insert into KIM_ROLES_T (ID,NAME,DESCRIPTION) values(3,'Narrative Writer','Proposal Narrative Writer');
insert into KIM_ROLES_T (ID,NAME,DESCRIPTION) values(4,'Budget Creator','Proposal Budget Creator');
insert into KIM_ROLES_T (ID,NAME,DESCRIPTION) values(5,'Viewer','Proposal Viewer');

insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(1,1);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,2);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,3);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,4);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,5);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,6);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,7);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,8);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(3,2);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(3,3);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(3,4);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(3,5);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(4,2);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(4,3);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(4,6);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(4,7);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(5,3);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(5,5);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(5,7);

insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (1, 'quickstart', '');
insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (2, 'ljoconno', '');
insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (3, 'bhutchin', '');
insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (4, 'aslusar', '');
insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (5, 'tdurkin', '');
insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (6, 'pcberg', '');
insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (7, 'jtester', '');
insert into KIM_PERSONS_T (ID, USERNAME, PASSWORD) values (8, 'gthomas', '');


insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (1, 1, 1);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (1, 1, 'kra.unitNumber', '000001');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (2, 1, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (2, 1, 1);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (3, 2, 'kra.unitNumber', 'BL-IIDC');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (4, 2, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (3, 1, 1);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (5, 3, 'kra.unitNumber', 'IN-CARD');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (6, 3, 'kra.subunits', 'Y');


insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (4, 1, 2);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (7, 4, 'kra.unitNumber', '000001');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (8, 4, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (5, 1, 2);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (9, 5, 'kra.unitNumber', 'BL-IIDC');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (10, 5, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (6, 1, 2);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (11, 6, 'kra.unitNumber', 'IN-CARD');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (12, 6, 'kra.subunits', 'Y');


insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (7, 1, 3);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (13, 7, 'kra.unitNumber', '000001');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (14, 7, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (8, 1, 3);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (15, 8, 'kra.unitNumber', 'BL-IIDC');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (16, 8, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (9, 1, 3);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (17, 9, 'kra.unitNumber', 'IN-CARD');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (18, 9, 'kra.subunits', 'Y');


insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (10, 1, 4);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (19, 10, 'kra.unitNumber', '000001');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (20, 10, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (11, 1, 4);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (21, 11, 'kra.unitNumber', 'BL-IIDC');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (22, 11, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (12, 1, 4);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (23, 12, 'kra.unitNumber', 'IN-CARD');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (24, 12, 'kra.subunits', 'Y');


insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (13, 1, 5);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (25, 13, 'kra.unitNumber', '000001');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (26, 13, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (14, 1, 5);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (27, 14, 'kra.unitNumber', 'BL-IIDC');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (28, 14, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (15, 1, 5);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (29, 15, 'kra.unitNumber', 'IN-CARD');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (30, 15, 'kra.subunits', 'Y');


insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (16, 1, 6);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (31, 16, 'kra.unitNumber', '000001');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (32, 16, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (17, 1, 6);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (33, 17, 'kra.unitNumber', 'BL-IIDC');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (34, 17, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (18, 1, 6);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (35, 18, 'kra.unitNumber', 'IN-CARD');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (36, 18, 'kra.subunits', 'Y');


insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (19, 1, 7);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (37, 19, 'kra.unitNumber', '000001');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (38, 19, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (20, 1, 7);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (39, 20, 'kra.unitNumber', 'BL-IIDC');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (40, 20, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (21, 1, 7);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (41, 21, 'kra.unitNumber', 'IN-CARD');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (42, 21, 'kra.subunits', 'Y');


insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (22, 1, 8);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (43, 22, 'kra.unitNumber', '000001');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (44, 22, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (23, 1, 8);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (45, 23, 'kra.unitNumber', 'BL-IIDC');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (46, 23, 'kra.subunits', 'N');

insert into KIM_ROLES_PERSONS_QUAL_T (ID, ROLE_ID, PERSON_ID) values (24, 1, 8);
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (47, 24, 'kra.unitNumber', 'IN-CARD');
insert into KIM_PERSON_QUAL_ATTR_T (ID, ROLE_PERSON_ID, ATTRIBUTE_NAME, ATTRIBUTE_VALUE) values (48, 24, 'kra.subunits', 'Y');

