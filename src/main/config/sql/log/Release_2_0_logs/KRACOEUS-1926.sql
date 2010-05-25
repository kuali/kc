update person set user_name='bhutchinso' where user_name='bhutchinson';

update KIM_PERSONS_T set username='bhutchinso'  where username='bhutchinson';

ALTER TABLE 
   Person 
MODIFY 
   ( 
   user_name varchar2(10) not null);