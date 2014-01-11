--Important:

--This script will do the following:
  --1) Drop all columns where data is now stored in new kim
  --2) rename Person table to more appropriate name
  --3) Drop Coeus compatible views

-- *This script does not yet migrate data that is not supported by new KIM (entexted entity data)
--    b/c it is not understood where this data will go.

--This script assumes that the KRIM tables are clean otherwise constraint
--violations may occur.

--Any errors detected during data transformation will generate a message within the dbms.

--Enjoy :-)

DECLARE
  
  the_sql varchar2(9999);
  
BEGIN
  the_sql := 'ALTER TABLE PERSON DROP (ssn, last_name, first_name, middle_name, full_name, prior_name, user_name, email_address, date_of_birth, age, gender, office_phone, secondry_office_phone, salutation, country_of_citizenship, is_faculty, is_graduate_student_staff, is_service_staff, is_support_staff, is_other_accademic_group, is_medical_staff, address_line_1, address_line_2, address_line_3, city, state, postal_code, country_code, fax_number, pager_number, mobile_phone_number, era_commons_user_name, kim_person_id, active_flag)';
  execute immediate the_sql; 
  
  the_sql := 'ALTER TABLE PERSON RENAME TO PERSON_EXT_T';
  execute immediate the_sql;
  
  the_sql := 'DROP VIEW OSP$Person';
  execute immediate the_sql;
  
  the_sql := 'DROP VIEW OSP$User';
  execute immediate the_sql;
END;
