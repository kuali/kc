DECLARE 
  max_id number;
  max_sq number;
BEGIN
  select max(id) into max_id from test1;
  select max(id) into max_sq from test2;
  if max_id is null then max_id:=0; end if;
  if max_sq is null then max_sq:=0; end if;
  select GREATEST (max_id, max_sq)  into max_id from dual;
  select TEST_S.NEXTVAL into max_sq from dual;
  IF max_sq < max_id THEN 
   max_sq := max_id - max_sq;
   EXECUTE IMMEDIATE 'alter sequence TEST_S increment by ' || max_sq; 
   EXECUTE IMMEDIATE 'select TEST_S.NEXTVAL from dual' INTO max_id;
   EXECUTE IMMEDIATE 'alter sequence TEST_S increment by 1';
  END IF;
END;