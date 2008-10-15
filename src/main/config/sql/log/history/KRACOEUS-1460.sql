/* add descend flag to roles and make changes to the data to correspond to this change */

alter table KIM_ROLES_T add (DESCEND_FLAG char(1) DEFAULT 'N');

insert into KIM_ROLE_TYPE_T (ROLE_TYPE_CODE, DESCRIPTION)
values ('D', 'Department');

update KIM_ROLES_T set ROLE_TYPE_CODE='D' where ROLE_TYPE_CODE='O';

update UNIT_ACL set SUBUNITS='N';

/* quickstart roles */
insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG, VER_NBR)
values (29, '000000003', 1, 'IN-CARR', 'N', 'Y', 1);

/* ljconno roles */
insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG, VER_NBR)
values (30, '000000004', 1, 'IN-CARR', 'N', 'Y', 1);

/* bhutchinson roles */
insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG, VER_NBR)
values (31, '000000005', 1, 'IN-CARR', 'N', 'Y', 1);

/* aslusar roles */
insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG, VER_NBR)
values (32, '000000006', 1, 'IN-CARR', 'N', 'Y', 1);

/* tdurkin roles */
insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG, VER_NBR)
values (33, '000000001', 1, 'IN-CARR', 'N', 'Y', 1);

/* pcberg roles */
insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG, VER_NBR)
values (34, '000000002', 1, 'IN-CARR', 'N', 'Y', 1);

/* jtester roles */
insert into UNIT_ACL (ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG, VER_NBR)
values (35, '000000008', 1, 'IN-CARR', 'N', 'Y', 1);

