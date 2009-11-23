set define off
--create table bak11202009_person_ext_t as select * from person_ext_t;

DECLARE
	CURSOR cur IS SELECT DISTINCT e.ENTITY_ID, p.HOME_UNIT, p.PERSON_ID FROM KRIM_ENTITY_T e, PERSON_EXT_T p, KRIM_PRNCPL_T r 
         where p.person_id = r.prncpl_id and r.entity_id = e.entity_id;
         
  the_sql varchar2(9999);
  
  newEmpInfoId varchar(40);
  cnt number(2);
  
BEGIN
  FOR rec IN cur
  LOOP
    
    IF (rec.HOME_UNIT IS NULL OR LENGTH(RTRIM(LTRIM(rec.HOME_UNIT))) = 0) THEN
      dbms_output.put_line ('person id: ' || rec.PERSON_ID || ' does not have a home unit');
    END IF;

    --always gonna create a record even if no home unit exists.
    
    the_sql := 'SELECT COUNT(t.entity_emp_id) FROM krim_entity_emp_info_t t WHERE t.entity_id = :1';
    execute immediate the_sql INTO cnt USING rec.entity_id;
    IF cnt > 0 THEN
       the_sql := 'update krim_entity_emp_info_t t set t.prmry_dept_cd = :1 where t.entity_id = :2';
       execute immediate the_sql USING rec.HOME_UNIT, rec.ENTITY_ID;
    ELSE
      --Get new emp id
      the_sql := 'SELECT KRIM_ENTITY_EMP_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEmpInfoId;
      
      the_sql := 'insert into krim_entity_emp_info_t (entity_emp_id, obj_id, ver_nbr, entity_id, prmry_ind, actv_ind, last_updt_dt, prmry_dept_cd)
             values (:1, :2, :3, :4, :5, :6, :7, :8)';
      
      execute immediate the_sql USING newEmpInfoId, SYS_GUID(), '1', rec.entity_id, 'Y', 'Y', SYSDATE, rec.home_unit;
    END IF;

  END LOOP;
END;
/
ALTER TABLE PERSON_EXT_T DROP (HOME_UNIT);
