DECLARE 
  max_id number;
  max_sq number;
BEGIN
  select max(PROPOSAL_PERSON_ID) into max_id from PROPOSAL_PERSONS;
  select max(PROPOSAL_PERSON_UNIT_ID) into max_sq from PROPOSAL_PERSON_UNITS;
  if max_id is null then max_id:=0; end if;
  if max_sq is null then max_sq:=0; end if;
  select GREATEST (max_id, max_sq)  into max_id from dual;
  select SEQ_PROPOSAL_PROPOSAL_ID.NEXTVAL into max_sq from dual;
  IF max_sq < max_id THEN 
   max_sq := max_id - max_sq;
   EXECUTE IMMEDIATE 'alter sequence SEQ_PROPOSAL_PROPOSAL_ID increment by ' || max_sq; 
   EXECUTE IMMEDIATE 'select SEQ_PROPOSAL_PROPOSAL_ID.NEXTVAL from dual' INTO max_id;
   EXECUTE IMMEDIATE 'alter sequence SEQ_PROPOSAL_PROPOSAL_ID increment by 1';
  END IF;
END;