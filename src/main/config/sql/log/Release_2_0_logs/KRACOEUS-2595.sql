
DECLARE
    cursor c1 is select user_name, first_name, last_name from PERSON, KIM_PERSONS_T where PERSON.user_name=KIM_PERSONS_T.username and PERSON.user_name not in (select prncpl_id from KRIM_PRNCPL_T);
    this_guid varchar2(36);
    this_entity number;
    this_entity_nm_id number;
   
BEGIN

    FOR user_rec in c1
    LOOP
        this_guid := SYS_GUID();
        select max(entity_id) into this_entity from KRIM_PRNCPL_T;
        this_entity := this_entity + 1;
        select max(entity_nm_id) into this_entity_nm_id from KRIM_ENTITY_NM_T;
        this_entity_nm_id := this_entity_nm_id + 1;
        insert into KRIM_ENTITY_T (obj_id, ver_nbr, entity_id, actv_ind) values (this_guid, '1', this_entity, 'Y');
        insert into KRIM_PRNCPL_T (obj_id, ver_nbr, prncpl_nm, prncpl_id, entity_id, actv_ind) values (this_guid, '1', user_rec.user_name, user_rec.user_name, this_entity, 'Y');
        insert into KRIM_ENTITY_ENT_TYP_T (obj_id, ver_nbr, entity_id, actv_ind, ent_typ_cd) values (this_guid, '1', this_entity, 'Y', 'PERSON');
        insert into KRIM_ENTITY_NM_T (obj_id, entity_id, entity_nm_id, nm_typ_cd, first_nm, last_nm) values (this_guid, this_entity, this_entity_nm_id, 'PFRD', user_rec.first_name, user_rec.last_name); 
    END LOOP;

END;
/