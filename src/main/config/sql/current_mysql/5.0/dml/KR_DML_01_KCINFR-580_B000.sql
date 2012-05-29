DELIMITER /
-- create default PeopleFlow type for KC documents
delete from krew_typ_t where TYP_ID='KC1000'
/

insert into krew_typ_t (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR) values ('KC1000', 'KC Basic PeopleFlow', 'KC-WKFLW', 'kcPeopleFlowTypeService', 'Y', 1)
/
DELIMITER ;
