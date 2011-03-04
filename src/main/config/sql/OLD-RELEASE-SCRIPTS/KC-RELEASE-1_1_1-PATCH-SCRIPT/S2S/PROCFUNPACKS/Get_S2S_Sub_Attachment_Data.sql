CREATE OR REPLACE PROCEDURE GET_S2S_SUB_ATTACHMENT_DATA (
	AW_PROPOSAL_NUMBER  IN  OSP$S2S_APP_ATTACHMENTS.PROPOSAL_NUMBER%TYPE,
    AW_CONTENT_ID IN  OSP$S2S_APP_ATTACHMENTS.CONTENT_ID%TYPE,
    CUR_GENERIC     IN OUT RESULT_SETS.CUR_GENERIC )

IS

li_select_clause VARCHAR2(2000);
li_mod_str varchar2(10);
li_mod_num varchar2(200);
li_person_id varchar2(20);
li_bn_str varchar2(20);
li_bn varchar(10);
li_ModuleNumLength number;
li_index int;
--ID-900021567_BN-1_BIOSKETCH
BEGIN
li_mod_str := substr(AW_CONTENT_ID,1,3);
if (instr(AW_CONTENT_ID,'M',-1,1))=1 then
    li_ModuleNumLength := (instr(AW_CONTENT_ID, '_') - 3);

	li_mod_num:=substr(AW_CONTENT_ID,3,li_ModuleNumLength);

	li_select_clause := 'select a.content_id CONTENT_ID,
					 						 a.hash_code HASH_CODE,
											 a.content_type CONTENT_TYPE,
											 b.narrative_pdf CONTENT
	from OSP$S2S_APP_ATTACHMENTS a, osp$narrative_pdf b
	where a.content_id = '''||AW_CONTENT_ID||''' and
		  a.PROPOSAL_NUMBER = '''||AW_PROPOSAL_NUMBER||''' and
		  a.PROPOSAL_NUMBER = b.PROPOSAL_NUMBER and
		  b.module_number  ='''||li_mod_num||'''';

elsif(instr(AW_CONTENT_ID,'ID'))=1 then
	li_person_id:=substr(AW_CONTENT_ID,0,instr(AW_CONTENT_ID,'_')-1);
	li_bn_str:= substr(AW_CONTENT_ID,length(li_person_id)+2);
	li_bn:=substr(li_bn_str,0,instr(li_bn_str,'_')-1);

	li_select_clause := 'select a.content_id CONTENT_ID,
					 						 a.hash_code HASH_CODE,
											 a.content_type CONTENT_TYPE,
											 b.BIO_PDF CONTENT
	from OSP$S2S_APP_ATTACHMENTS a, OSP$EPS_PROP_PERSON_BIO_PDF b
	where a.content_id = '''||AW_CONTENT_ID||''' and
		  a.PROPOSAL_NUMBER = '''||AW_PROPOSAL_NUMBER||''' and
		  a.PROPOSAL_NUMBER = b.PROPOSAL_NUMBER and
		  ''ID-''||b.PERSON_ID='''||li_person_id||''' and
		  ''BN-''||b.BIO_NUMBER ='''||li_bn||'''';
elsif((instr(AW_CONTENT_ID,'cid:'))=1 or (instr(AW_CONTENT_ID,'RR_Budget_P4:'))=1) then
-- 	person_id:=substr(AW_CONTENT_ID,0,instr(AW_CONTENT_ID,'_')-1);
-- 	bn_str:= substr(AW_CONTENT_ID,length(person_id)+2);
-- 	bn:=substr(bn_str,0,instr(bn_str,'_')-1);

	li_select_clause := 'select ''Subaward_Budget_''||ROWNUM CONTENT_ID,
					 						 a.hash_code HASH_CODE,
											 a.content_type CONTENT_TYPE,
											 b.ATTACHMENT CONTENT
	from OSP$S2S_APP_ATTACHMENTS a, OSP$BUDGET_SUB_AWARD_ATT b
	where a.content_id = '''||AW_CONTENT_ID||''' and
		  a.PROPOSAL_NUMBER = '''||AW_PROPOSAL_NUMBER||''' and
		  a.PROPOSAL_NUMBER = b.PROPOSAL_NUMBER and
		  a.content_id = b.content_id';
else
	li_select_clause := 'select a.content_id CONTENT_ID,
					 						 a.hash_code HASH_CODE,
											 a.content_type CONTENT_TYPE,
											 null CONTENT
								from OSP$S2S_APP_ATTACHMENTS a, osp$narrative_pdf b
							where a.content_id = '''||AW_CONTENT_ID||''' and
								  a.PROPOSAL_NUMBER = '''||AW_PROPOSAL_NUMBER||''' and
								  a.PROPOSAL_NUMBER = b.PROPOSAL_NUMBER';

end if;
--dbms_output.put_line(li_select_clause);

open cur_generic for li_select_clause;

--  OPEN CUR_GENERIC FOR
--  	   DBMS_SQL.PARSE(cur,li_select_clause,DBMS_SQL.NATIVE);
--	   DBMS_SQL.EXECUTE_AND_FETCH(cur);

END;
/