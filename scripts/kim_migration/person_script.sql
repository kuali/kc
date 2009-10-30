--Jay Hulslander
--This script produces some data anamonlies and border cases to test data migration scripts
--Note, the "rollback;" at the bottom.

insert into kim_persons_t (id, username, password, obj_id, ver_nbr)
values( nvl((select max(id) from kim_persons_t), 1) + 1, 'kimtester1', 'fK69ATFsAydwQuteang+xMva+Tc=', 
  sys_guid(), 1);

insert into kim_persons_t (id, username, password, obj_id, ver_nbr)
values( (select max(id) from kim_persons_t) + 1, 'kimtester2', 'fK69ATFsAydwQuteang+xMva+Tc=', 
  sys_guid(), 1);  
  
insert into kim_persons_t (id, username, password, obj_id, ver_nbr)
values( (select max(id) from kim_persons_t) + 1, 'kimtester3', 'fK69ATFsAydwQuteang+xMva+Tc=', 
  sys_guid(), 1);
  
insert into kim_persons_t (id, username, password, obj_id, ver_nbr)
values( (select max(id) from kim_persons_t) + 1, 'kimtester4', 'fK69ATFsAydwQuteang+xMva+Tc=', 
  sys_guid(), 1);  
  
insert into kim_persons_t (id, username, password, obj_id, ver_nbr)
values( (select max(id) from kim_persons_t) + 1, 'kimtester5', 'fK69ATFsAydwQuteang+xMva+Tc=', 
  sys_guid(), 1);  
  
insert into kim_persons_t (id, username, password, obj_id, ver_nbr)
values( (select max(id) from kim_persons_t) + 1, 'kimtester6', 'fK69ATFsAydwQuteang+xMva+Tc=', 
  sys_guid(), 1);
  
insert into kim_persons_t (id, username, password, obj_id, ver_nbr)
values( (select max(id) from kim_persons_t) + 1, 'kimtester7', 'fK69ATFsAydwQuteang+xMva+Tc=', 
  sys_guid(), 1);
  
insert into kim_persons_t (id, username, password, obj_id, ver_nbr)
values( (select max(id) from kim_persons_t) + 1, 'kimtester8', 'fK69ATFsAydwQuteang+xMva+Tc=', 
  sys_guid(), 1);
  
insert into kim_persons_t (id, username, password, obj_id, ver_nbr)
values( (select max(id) from kim_persons_t) + 1, 'kimtester9', 'fK69ATFsAydwQuteang+xMva+Tc=', 
  sys_guid(), 1);
  
--select id from kim_persons_t where username = 'kimtester1';
--select id from kim_persons_t where username = 'kimtester2';
--select id from kim_persons_t where username = 'kimtester3';
--select id from kim_persons_t where username = 'kimtester4';
---select id from kim_persons_t where username = 'kimtester5';
--select id from kim_persons_t where username = 'kimtester6';
--select id from kim_persons_t where username = 'kimtester7';
--select id from kim_persons_t where username = 'kimtester8';
--select id from kim_persons_t where username = 'kimtester9';

insert into person (kim_person_id, active_flag, pager_number, mobile_phone_number, era_commons_user_name, ver_nbr, obj_id,
  person_id, ssn, last_name, first_name, middle_name, prior_name, user_name, email_address, date_of_birth,
  age_by_fiscal_year, gender, race, education_level, degree, major, is_handicapped, handicap_type, is_veteran,
  veteran_type, visa_code, visa_type, visa_renewal_date, has_visa, office_location, office_phone, secondry_office_location,
  secondry_office_phone, school, year_graduated, directory_department, salutation, country_of_citizenship, primary_title,
  directory_title, home_unit, is_faculty, is_graduate_student_staff, is_research_staff, is_service_staff, is_support_staff,
  is_other_accademic_group, is_medical_staff, vacation_accural, is_on_sabbatical, id_provided, id_verified, update_timestamp,
  update_user, address_line_1, address_line_2, address_line_3, city, county, state, postal_code, country_code, fax_number)
values((select id from kim_persons_t where username = 'kimtester1'), 'Y', 12345678901234567890, 12345678901234567890, 12345678901234567890, 
  12345678, ( select sys_guid() from dual),
  '123', 123456789, '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '1234567890', '123456789012345678901234567890123456789012345678901234567890', 
  to_date('19771231', 'YYYYMMDD'),
  123, '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', '12345678901', 
  '123456789012345678901234567890', 'Y', '123456789012345678901234567890', 'Y',
  '123456789012345678901234567890', '12345678901234567890', '123456789012345678901234567890', sysdate, 'Y', '123456789012345678901234567890', 
  '12345678901234567890', '123456789012345678901234567890',
  '12345678901234567890', '12345678901234567890123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890123456789012345678901',
  '12345678901234567890123456789012345678901234567890', '12345678', 'Y', 'Y', 'Y', 'Y', 'Y',
  'Y', 'Y', 'Y', 'Y', '123456789012345678901234567890', '123456789012345678901234567890', sysdate,
  '123456789012345678901234567890123456789012345678901234567890', '12345678901234567890123456789012345678901234567890123456789012345678901234567890', 
  '12345678901234567890123456789012345678901234567890123456789012345678901234567890', '12345678901234567890123456789012345678901234567890123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345', 
  '123', '12345678901234567890');
  
  
insert into person (kim_person_id, active_flag, pager_number, mobile_phone_number, era_commons_user_name, ver_nbr, obj_id,
  person_id, ssn, last_name, first_name, middle_name, prior_name, user_name, email_address, date_of_birth,
  age_by_fiscal_year, gender, race, education_level, degree, major, is_handicapped, handicap_type, is_veteran,
  veteran_type, visa_code, visa_type, visa_renewal_date, has_visa, office_location, office_phone, secondry_office_location,
  secondry_office_phone, school, year_graduated, directory_department, salutation, country_of_citizenship, primary_title,
  directory_title, home_unit, is_faculty, is_graduate_student_staff, is_research_staff, is_service_staff, is_support_staff,
  is_other_accademic_group, is_medical_staff, vacation_accural, is_on_sabbatical, id_provided, id_verified, update_timestamp,
  update_user, address_line_1, address_line_2, address_line_3, city, county, state, postal_code, country_code, fax_number)
values((select id from kim_persons_t where username = 'kimtester2'), 'x', 12345678901234567890, 12345678901234567890, 12345678901234567890, 
  12345678, ( select sys_guid() from dual),
  '456', 123456789, '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '1234567890', '123456789012345678901234567890123456789012345678901234567890', 
  to_date('19771231', 'YYYYMMDD'),
  123, '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', '12345678901', 
  '123456789012345678901234567890', 'Y', '123456789012345678901234567890', 'Y',
  '123456789012345678901234567890', '12345678901234567890', '123456789012345678901234567890', sysdate, 'Y', '123456789012345678901234567890', 
  '12345678901234567890', '123456789012345678901234567890',
  '12345678901234567890', '12345678901234567890123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890123456789012345678901',
  '12345678901234567890123456789012345678901234567890', '12345678', 'x', 'x', 'c', 'x', 'x',
  'x', 'x', 'x', 'x', '123456789012345678901234567890', '123456789012345678901234567890', sysdate,
  '123456789012345678901234567890123456789012345678901234567890', '12345678901234567890123456789012345678901234567890123456789012345678901234567890', 
  '12345678901234567890123456789012345678901234567890123456789012345678901234567890', '12345678901234567890123456789012345678901234567890123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345', 
  '123', '12345678901234567890');  
  
insert into person (kim_person_id, active_flag, pager_number, mobile_phone_number, era_commons_user_name, ver_nbr, obj_id,
  person_id, ssn, last_name, first_name, middle_name, prior_name, user_name, email_address, date_of_birth,
  age_by_fiscal_year, gender, race, education_level, degree, major, is_handicapped, handicap_type, is_veteran,
  veteran_type, visa_code, visa_type, visa_renewal_date, has_visa, office_location, office_phone, secondry_office_location,
  secondry_office_phone, school, year_graduated, directory_department, salutation, country_of_citizenship, primary_title,
  directory_title, home_unit, is_faculty, is_graduate_student_staff, is_research_staff, is_service_staff, is_support_staff,
  is_other_accademic_group, is_medical_staff, vacation_accural, is_on_sabbatical, id_provided, id_verified, update_timestamp,
  update_user, address_line_1, address_line_2, address_line_3, city, county, state, postal_code, country_code, fax_number)
values((select id from kim_persons_t where username = 'kimtester3'), 'Y', 12345678901234567890, 12345678901234567890, 12345678901234567890, 
  12345678, ( select sys_guid() from dual),
  '657', 123456789, '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '1234567890', '123456789012345678901234567890123456789012345678901234567890', 
  to_date('19771231', 'YYYYMMDD'),
  123, '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', '12345678901', 
  '123456789012345678901234567890', 'Y', '123456789012345678901234567890', 'Y',
  '123456789012345678901234567890', '12345678901234567890', '123456789012345678901234567890', sysdate, 'Y', '123456789012345678901234567890', 
  '12345678901234567890', '123456789012345678901234567890',
  '12345678901234567890', '12345678901234567890123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890123456789012345678901',
  '12345678901234567890123456789012345678901234567890', '12345678', 'Y', 'Y', 'Y', 'Y', 'Y',
  'Y', 'Y', 'Y', 'Y', '123456789012345678901234567890', '123456789012345678901234567890', sysdate,
  'jhulslander', '120 Maple Ave', 
  'null', null, 
  'Ithaca', 'Tompkins', 'NY', '13068', 
  'USA', '16072559900'); 
  
insert into person (kim_person_id, active_flag, pager_number, mobile_phone_number, era_commons_user_name, ver_nbr, obj_id,
  person_id, ssn, last_name, first_name, middle_name, prior_name, user_name, email_address, date_of_birth,
  age_by_fiscal_year, gender, race, education_level, degree, major, is_handicapped, handicap_type, is_veteran,
  veteran_type, visa_code, visa_type, visa_renewal_date, has_visa, office_location, office_phone, secondry_office_location,
  secondry_office_phone, school, year_graduated, directory_department, salutation, country_of_citizenship, primary_title,
  directory_title, home_unit, is_faculty, is_graduate_student_staff, is_research_staff, is_service_staff, is_support_staff,
  is_other_accademic_group, is_medical_staff, vacation_accural, is_on_sabbatical, id_provided, id_verified, update_timestamp,
  update_user, address_line_1, address_line_2, address_line_3, city, county, state, postal_code, country_code, fax_number)
values((select id from kim_persons_t where username = 'kimtester4'), 'Y', 12345678901234567890, 12345678901234567890, 12345678901234567890, 
  12345678, ( select sys_guid() from dual),
  'ujytjt', 123456789, '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '1234567890', '123456789012345678901234567890123456789012345678901234567890', 
  to_date('19771231', 'YYYYMMDD'),
  123, '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', '12345678901', 
  '123456789012345678901234567890', 'Y', '123456789012345678901234567890', 'Y',
  '123456789012345678901234567890', '12345678901234567890', '123456789012345678901234567890', sysdate, 'Y', '123456789012345678901234567890', 
  '12345678901234567890', '123456789012345678901234567890',
  '12345678901234567890', '12345678901234567890123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890123456789012345678901',
  '12345678901234567890123456789012345678901234567890', '12345678', 'Y', 'Y', 'Y', 'Y', 'Y',
  'Y', 'Y', 'Y', 'Y', '123456789012345678901234567890', '123456789012345678901234567890', sysdate,
  'jhulslander', null, 
  '120 Maple Ave', null, 
  'Ithaca', null, 'New York', '13068', 
  'USA', '16072559900');   
insert into person (kim_person_id, active_flag, pager_number, mobile_phone_number, era_commons_user_name, ver_nbr, obj_id,
  person_id, ssn, last_name, first_name, middle_name, prior_name, user_name, email_address, date_of_birth,
  age_by_fiscal_year, gender, race, education_level, degree, major, is_handicapped, handicap_type, is_veteran,
  veteran_type, visa_code, visa_type, visa_renewal_date, has_visa, office_location, office_phone, secondry_office_location,
  secondry_office_phone, school, year_graduated, directory_department, salutation, country_of_citizenship, primary_title,
  directory_title, home_unit, is_faculty, is_graduate_student_staff, is_research_staff, is_service_staff, is_support_staff,
  is_other_accademic_group, is_medical_staff, vacation_accural, is_on_sabbatical, id_provided, id_verified, update_timestamp,
  update_user, address_line_1, address_line_2, address_line_3, city, county, state, postal_code, country_code, fax_number)
values((select id from kim_persons_t where username = 'kimtester5'), 'Y', 12345678901234567890, 12345678901234567890, 12345678901234567890, 
  12345678, ( select sys_guid() from dual),
  '89789', 123456789, '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '1234567890', '123456789012345678901234567890123456789012345678901234567890', 
  to_date('19771231', 'YYYYMMDD'),
  123, '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', '12345678901', 
  '123456789012345678901234567890', 'Y', '123456789012345678901234567890', 'Y',
  '123456789012345678901234567890', '12345678901234567890', '123456789012345678901234567890', sysdate, 'Y', '123456789012345678901234567890', 
  '12345678901234567890', '123456789012345678901234567890',
  '12345678901234567890', '12345678901234567890123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890123456789012345678901',
  '12345678901234567890123456789012345678901234567890', '12345678', 'Y', 'Y', 'Y', 'Y', 'Y',
  'Y', 'Y', 'Y', 'Y', '123456789012345678901234567890', '123456789012345678901234567890', sysdate,
  'jhulslander', null, 
  '120 Maple Ave', null, 
  'Ithaca', null, 'New York', '13068', 
  'USA', '16072559900');

insert into person (kim_person_id, active_flag, pager_number, mobile_phone_number, era_commons_user_name, ver_nbr, obj_id,
  person_id, ssn, last_name, first_name, middle_name, prior_name, user_name, email_address, date_of_birth,
  age_by_fiscal_year, gender, race, education_level, degree, major, is_handicapped, handicap_type, is_veteran,
  veteran_type, visa_code, visa_type, visa_renewal_date, has_visa, office_location, office_phone, secondry_office_location,
  secondry_office_phone, school, year_graduated, directory_department, salutation, country_of_citizenship, primary_title,
  directory_title, home_unit, is_faculty, is_graduate_student_staff, is_research_staff, is_service_staff, is_support_staff,
  is_other_accademic_group, is_medical_staff, vacation_accural, is_on_sabbatical, id_provided, id_verified, update_timestamp,
  update_user, address_line_1, address_line_2, address_line_3, city, county, state, postal_code, country_code, fax_number)
values((select id from kim_persons_t where username = 'kimtester6'), 'Y', 12345678901234567890, 12345678901234567890, 12345678901234567890, 
  12345678, ( select sys_guid() from dual),
  'terter', 123456789, '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '1234567890', '123456789012345678901234567890123456789012345678901234567890', 
  to_date('19771231', 'YYYYMMDD'),
  123, '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', '12345678901', 
  '123456789012345678901234567890', 'Y', '123456789012345678901234567890', 'Y',
  '123456789012345678901234567890', '12345678901234567890', '123456789012345678901234567890', sysdate, 'Y', '123456789012345678901234567890', 
  '12345678901234567890', '123456789012345678901234567890',
  '12345678901234567890', '12345678901234567890123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890123456789012345678901',
  '12345678901234567890123456789012345678901234567890', '12345678', 'Y', 'Y', 'Y', 'Y', 'Y',
  'Y', 'Y', 'Y', 'Y', '123456789012345678901234567890', '123456789012345678901234567890', sysdate,
  'jhulslander', '120 Maple Ave', 
  'null', null, 
  'Ithaca', 'Tompkins', 'NY', '13068', 
  'USA', '16072559900');
  
insert into person (kim_person_id, active_flag, pager_number, mobile_phone_number, era_commons_user_name, ver_nbr, obj_id,
  person_id, ssn, last_name, first_name, middle_name, prior_name, user_name, email_address, date_of_birth,
  age_by_fiscal_year, gender, race, education_level, degree, major, is_handicapped, handicap_type, is_veteran,
  veteran_type, visa_code, visa_type, visa_renewal_date, has_visa, office_location, office_phone, secondry_office_location,
  secondry_office_phone, school, year_graduated, directory_department, salutation, country_of_citizenship, primary_title,
  directory_title, home_unit, is_faculty, is_graduate_student_staff, is_research_staff, is_service_staff, is_support_staff,
  is_other_accademic_group, is_medical_staff, vacation_accural, is_on_sabbatical, id_provided, id_verified, update_timestamp,
  update_user, address_line_1, address_line_2, address_line_3, city, county, state, postal_code, country_code, fax_number)
values((select id from kim_persons_t where username = 'kimtester7'), 'Y', 12345678901234567890, 12345678901234567890, 12345678901234567890, 
  12345678, ( select sys_guid() from dual),
  'rftgtrg', 123456789, '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '1234567890', '123456789012345678901234567890123456789012345678901234567890', 
  to_date('19771231', 'YYYYMMDD'),
  123, '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', '12345678901', 
  '123456789012345678901234567890', 'Y', '123456789012345678901234567890', 'Y',
  '123456789012345678901234567890', '12345678901234567890', '123456789012345678901234567890', sysdate, 'Y', '123456789012345678901234567890', 
  '12345678901234567890', '123456789012345678901234567890',
  '12345678901234567890', '12345678901234567890123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890123456789012345678901',
  '12345678901234567890123456789012345678901234567890', '12345678', 'Y', 'Y', 'Y', 'Y', 'Y',
  'Y', 'Y', 'Y', 'Y', '123456789012345678901234567890', '123456789012345678901234567890', sysdate,
  'jhulslander', null, 
  '120 Maple Ave', null, 
  'Ithaca', 'Tompkins', 'NY', '13068', 
  'USA', '16072559900');  

insert into person (kim_person_id, active_flag, pager_number, mobile_phone_number, era_commons_user_name, ver_nbr, obj_id,
  person_id, ssn, last_name, first_name, middle_name, prior_name, user_name, email_address, date_of_birth,
  age_by_fiscal_year, gender, race, education_level, degree, major, is_handicapped, handicap_type, is_veteran,
  veteran_type, visa_code, visa_type, visa_renewal_date, has_visa, office_location, office_phone, secondry_office_location,
  secondry_office_phone, school, year_graduated, directory_department, salutation, country_of_citizenship, primary_title,
  directory_title, home_unit, is_faculty, is_graduate_student_staff, is_research_staff, is_service_staff, is_support_staff,
  is_other_accademic_group, is_medical_staff, vacation_accural, is_on_sabbatical, id_provided, id_verified, update_timestamp,
  update_user, address_line_1, address_line_2, address_line_3, city, county, state, postal_code, country_code, fax_number)
values((select id from kim_persons_t where username = 'kimtester8'), 'Y', 16072553047, 16072806544, 'Jay', 
  12345678, ( select sys_guid() from dual),
  'kukykyuj', 123456789, '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '1234567890', '123456789012345678901234567890123456789012345678901234567890', 
  to_date('19771231', 'YYYYMMDD'),
  123, '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', '12345678901', 
  '123456789012345678901234567890', 'Y', '123456789012345678901234567890', 'Y',
  '123456789012345678901234567890', '12345678901234567890', '123456789012345678901234567890', sysdate, 'Y', '123456789012345678901234567890', 
  '12345678901234567890', '123456789012345678901234567890',
  '12345678901234567890', '12345678901234567890123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890', 
  '123456789012345678901234567890', '123456789012345678901234567890', '123456789012345678901234567890123456789012345678901',
  '12345678901234567890123456789012345678901234567890', '12345678', 'Y', 'Y', 'Y', 'Y', 'Y',
  'Y', 'Y', 'Y', 'Y', '123456789012345678901234567890', '123456789012345678901234567890', sysdate,
  'jhulslander', '120 Maple Ave', 
  null, null, 
  'Ithaca', 'Tompkins', 'NY', '13068', 
  'USA', '16072559900');
  
insert into person (kim_person_id, active_flag, pager_number, mobile_phone_number, era_commons_user_name, ver_nbr, obj_id,
  person_id, ssn, last_name, first_name, middle_name, prior_name, user_name, email_address, date_of_birth,
  age_by_fiscal_year, gender, race, education_level, degree, major, is_handicapped, handicap_type, is_veteran,
  veteran_type, visa_code, visa_type, visa_renewal_date, has_visa, office_location, office_phone, secondry_office_location,
  secondry_office_phone, school, year_graduated, directory_department, salutation, country_of_citizenship, primary_title,
  directory_title, home_unit, is_faculty, is_graduate_student_staff, is_research_staff, is_service_staff, is_support_staff,
  is_other_accademic_group, is_medical_staff, vacation_accural, is_on_sabbatical, id_provided, id_verified, update_timestamp,
  update_user, address_line_1, address_line_2, address_line_3, city, county, state, postal_code, country_code, fax_number)
values((select id from kim_persons_t where username = 'kimtester9'), 'Y', null, '16072806544', 'Jay', 1, ( select sys_guid() from dual),
  'dsfdsfs', '111223333', 'Hulslander', 'Jay', 'David', null, 'jhulslande', 'jdh34@cornell.edu', 
  to_date('19771231', 'YYYYMMDD'),
  32, 'M', 'C', 'Associates', 'AAS CIS', 'CIS', 'N', null, 'N',
  null, null, null, sysdate, 'N', 'Ithaca NY', '16072552047', null,
  null, 'TC3', '1998', 'CIT', 'Mr.', 'USA', 'Programmer Analyst Senior',
  'P/A Sr.', 'CIT', 'N', 'N', 'N', 'N', 'N',
  'N', 'N', 'Y', 'N', 'Y', 'Y', sysdate,
  'jhulslander', '120 Maple Ave', null, null, 'Ithaca', 'Tompkins', 'NY', '13068', 'USA', null);  
  
--rollback;  
