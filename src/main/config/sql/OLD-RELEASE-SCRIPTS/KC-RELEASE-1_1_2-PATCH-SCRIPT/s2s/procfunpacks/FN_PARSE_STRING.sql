create or replace FUNCTION FN_PARSE_STRING 
    (v_str   in varchar2,
      v_delim in varchar2 default ',')
    return      pr_result_Table
    as
    l_str     long default v_str || v_delim;
    l_n     number;
    l_data     pr_result_Table := pr_result_Table();
    begin
   loop
        l_n := instr( l_str, v_delim );
        exit when (nvl(l_n,0) = 0);
        l_data.extend;
        l_data( l_data.count ) := ltrim(rtrim(substr(l_str,1,l_n-1)));
        l_str := substr( l_str, l_n+length(v_delim) );
   end loop;
   return l_data;
   end;
 /