/* Create the SUBMIT_PROPOSAL permission and assign it to the Aggregator role */

insert into KIM_PERMISSIONS_T (ID,NAME,DESCRIPTION,NAMESPACE_ID) values(13,'SUBMIT_PROPOSAL','Submit a Proposal for approval', 2);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(2,13);
