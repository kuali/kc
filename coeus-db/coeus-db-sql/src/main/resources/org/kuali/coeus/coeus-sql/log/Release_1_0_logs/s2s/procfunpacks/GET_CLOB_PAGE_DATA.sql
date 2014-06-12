CREATE OR REPLACE PROCEDURE get_clob_page_data
   ( AW_SPONSOR_CODE IN OSP$SPONSOR_FORM_TEMPLATES.SPONSOR_CODE%type,
     AW_PACKAGE_NUMBER IN OSP$SPONSOR_FORM_TEMPLATES.PACKAGE_NUMBER%type,
	 AW_PAGE_NUMBER IN OSP$SPONSOR_FORM_TEMPLATES.PAGE_NUMBER%type,
		CUR_SPONSOR_FORM_TEMPLATES IN OUT result_sets.cur_generic) is

begin

open CUR_SPONSOR_FORM_TEMPLATES for
  SELECT *
     FROM OSP$SPONSOR_FORM_TEMPLATES
     WHERE SPONSOR_CODE = AW_SPONSOR_CODE
	   AND PACKAGE_NUMBER = AW_PACKAGE_NUMBER
	   AND PAGE_NUMBER = AW_PAGE_NUMBER ;

end;
/
