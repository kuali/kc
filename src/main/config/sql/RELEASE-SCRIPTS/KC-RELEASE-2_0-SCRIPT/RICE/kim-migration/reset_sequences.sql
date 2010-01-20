CREATE OR REPLACE procedure reset_sequences(sequence_name in VARCHAR2) is
 
    l_generic_seq_max_value NUMBER;
    l_generic_seq_next_value NUMBER;
    l_table_name VARCHAR2(30);
    l_key_column_name VARCHAR2(30);
    l_gen_seq_sql VARCHAR2(4000);
    
    v_code NUMBER;
    v_errm VARCHAR2(64);

  BEGIN
    l_table_name := replace(lower(sequence_name), '_id_s', '_t');
    l_key_column_name := replace(lower(sequence_name), 'krim', 'kim');
    l_key_column_name := replace(l_key_column_name, '_s', '');
    dbms_output.put_line(l_key_column_name);

    begin 
    l_gen_seq_sql := 'SELECT MAX('|| l_key_column_name || ') FROM ' || l_table_name;
    EXECUTE IMMEDIATE l_gen_seq_sql INTO l_generic_seq_max_value;
    exception when others then 
         v_code := SQLCODE;
         v_errm := SUBSTR(SQLERRM, 1 , 64);
         DBMS_OUTPUT.PUT_LINE('Error code ' || v_code || ': ' || v_errm);
         --attempt again
         l_key_column_name := replace(l_key_column_name, 'kim_', '');
         l_gen_seq_sql := 'SELECT MAX('|| l_key_column_name || ') FROM ' || l_table_name;
         dbms_output.put_line(l_gen_seq_sql);
         EXECUTE IMMEDIATE l_gen_seq_sql INTO l_generic_seq_max_value;
    end;
    
    l_gen_seq_sql := 'select ' || sequence_name || '.nextval from dual';
    EXECUTE IMMEDIATE l_gen_seq_sql into l_generic_seq_next_value;
    
    while(l_generic_seq_next_value <= l_generic_seq_max_value) loop 
        EXECUTE IMMEDIATE l_gen_seq_sql into l_generic_seq_next_value ;
    end loop;
    
    dbms_output.put_line('Current value :' || l_generic_seq_next_value || ' Max Value: ' || l_generic_seq_max_value);  
    
    END;
/
