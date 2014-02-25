declare
    l_new_seq INTEGER;
begin
    execute immediate 'DROP SEQUENCE KRIM_ROLE_PERM_ID_S';
    select nvl(max(to_number(ROLE_PERM_ID)),'9999') + 1 into l_new_seq from KRIM_ROLE_PERM_T where ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator Only') or ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Budget Creator Only');
    execute immediate 'CREATE SEQUENCE KRIM_ROLE_PERM_ID_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
end;
/
