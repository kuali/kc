CREATE OR REPLACE PROCEDURE "RECOMPILE" 
   (status_in IN VARCHAR2 := 'INVALID',
    name_in IN VARCHAR2 := '%',
    type_in IN VARCHAR2 := '%',
    schema_in IN VARCHAR2 := USER)
IS
   v_objtype VARCHAR2(100);

   CURSOR obj_cur IS
      SELECT owner, object_name, object_type
        FROM ALL_OBJECTS
       WHERE status LIKE UPPER (status_in)
         AND object_name LIKE UPPER (name_in)
         AND object_type LIKE UPPER (type_in)
         AND owner LIKE UPPER (schema_in)
      ORDER BY
         DECODE (object_type,
            'PACKAGE', 1, 'PACKAGE BODY', 2,
            'FUNCTION', 3, 'PROCEDURE', 4);

BEGIN
   FOR rec IN obj_cur
   LOOP
      IF rec.object_type = 'PACKAGE'
      THEN
         v_objtype := 'PACKAGE SPECIFICATION';
      ELSE
         v_objtype := rec.object_type;
      END IF;

      DBMS_DDL.ALTER_COMPILE (v_objtype, rec.owner, rec.object_name);

      DBMS_OUTPUT.PUT_LINE
         ('Compiled ' || v_objtype || ' of ' ||
          rec.owner || '.' || rec.object_name);
   END LOOP;
END;
/
