set define off
--Important:

--This script will do the following:
  --1) Drop all constraints on tables which the constraint is on the PERSON table
  --2) Read all Person data, do data transformation, and create the appropriate new KIM entity records
  --3) Create a new KIM principal entry for each new KIM entity if a old KIM Person entry exists
  --4) change all PERSON_ID columns to in KC tables to point to the new KIM entity id.

-- *This script does not yet migrate data that is not supported by new KIM (entexted entity data)
--    b/c it is not understood where this data will go.

--This script assumes that the KRIM tables are clean otherwise constraint
--violations may occur.

--Any errors detected during data transformation will generate a message within the dbms.

--Enjoy :-)

DECLARE
  CURSOR cur IS SELECT * FROM Person FOR UPDATE;
  
  CURSOR consOnPerson IS select child_table.table_name as table_name, child_constraint.constraint_name as constraint_name
  from user_tables      parent_table,
        user_constraints parent_constraint,
        user_constraints child_constraint,
        user_tables      child_table
  where parent_table.table_name = parent_constraint.table_name
    and parent_constraint.constraint_type IN( 'P', 'U' )
    and child_constraint.r_constraint_name = parent_constraint.constraint_name
    and child_constraint.constraint_type   = 'R'
    and child_table.table_name = child_constraint.table_name
    and child_table.table_name != parent_table.table_name
    and parent_table.table_name = 'PERSON';
  
  the_sql varchar2(9999);
  newEntityId varchar2(40);
  --this data is needed in multiple places...defining at the top for consistent use
  entityType CONSTANT varchar2(40) := 'PERSON';
  
  newEntityNamePfrdId varchar2(40);
  newEntityNamePriorId varchar2(40);
  
  newEntityPhoneFaxId varchar2(40);
  newEntityPhonePagerId varchar2(40);
  newEntityPhoneMobileId varchar2(40);
  newEntityPhoneOfficeId varchar2(40);
  newEntityPhoneSecOfficeId varchar2(40);
  
  newEntityEmailWorkId varchar2(40);
  
  genderConversion varchar2(1) NOT NULL := ' ';
  
  newEntityAfltnFacultyId varchar2(40);
  newEntityAfltnGradStudentId varchar2(40);
  newEntityAfltnResearchId varchar2(40);
  newEntityAfltnServiceId varchar2(40);
  newEntityAfltnSupportId varchar2(40);
  newEntityAfltnAcademicId varchar2(40);
  newEntityAfltnMedicalId varchar2(40);
  
  countryConversion varchar2(2);
  stateConversion varchar2(2);
  
  newEntityCitizenshipId varchar(40);
  
  newEntityAddressId varchar(40);
  
  newEntityExtSsnId varchar(40);
  newEntityExtEraCommonsId varchar(40);
  
  newPrincipalId varchar2(40);
  
  newEthnicEntityId varchar2(40);
  
  kimUserName varchar2(500);
  kimPassword varchar2(500);
  
  addressLine1 varchar2(50);
  addressLine2 varchar2(50);
  addressLine3 varchar2(50);
  
BEGIN
  --Drop Constraints on PERSON table
  FOR rec IN consOnPerson
  LOOP
    the_sql := ' ALTER TABLE ' || rec.table_name || ' DROP CONSTRAINT ' || rec.constraint_name;
    execute immediate the_sql;
  END LOOP;
  
  --Change PERSON_ID field to be a varchar(40) to match the principal id field
  the_sql := 'ALTER TABLE AWARD_PERSONS MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE AWARD_UNIT_CONTACTS MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE BUDGET_PERSONNEL_DETAILS MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
    
  the_sql := 'ALTER TABLE BUDGET_PERSONS MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE BUDGET_PER_DET_RATE_AND_BASE MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE COMM_MEMBERSHIPS MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE COMM_SCHEDULE_ATTENDANCE MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE EPS_PROP_PERSON MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE EPS_PROP_PERSON_BIO MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE KRA_USER MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE EPS_PROP_USER_ROLES MODIFY (USER_ID varchar(40))';
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE PERSON MODIFY (PERSON_ID varchar(100))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE PERSON_DEGREE MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE PROPOSAL_PER_CREDIT_SPLIT MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE PROPOSAL_PERSONS MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE PERSON_TRAINING MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE PROPOSAL_UNITS MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE PROPOSAL_UNIT_CREDIT_SPLIT MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE PROP_ROLE_TEMPLATE MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE PROTOCOL_PERSONS MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE PROTOCOL_REVIEWERS MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE PROTOCOL_UNITS MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
    
  the_sql := 'ALTER TABLE UNIT_ACL MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
    
  the_sql := 'ALTER TABLE UNIT_ADMINISTRATOR MODIFY (PERSON_ID varchar(40))'; 
  execute immediate the_sql;
  
  the_sql := 'ALTER TABLE NARRATIVE_USER_RIGHTS MODIFY (USER_ID varchar(40))'; 
  execute immediate the_sql;
  
  FOR rec IN cur
  LOOP
  
    --Get new entity id
    the_sql := 'SELECT KRIM_ENTITY_ID_S.nextval FROM dual';
    execute immediate the_sql INTO newEntityId;
  
    --KRIM_ENTITY_T
    the_sql := 'INSERT INTO KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
    VALUES (:1, :2, :3, :4, :5)';
    execute immediate the_sql USING newEntityId, SYS_GUID(), 1, rec.active_flag, SYSDATE;
    
    --KRIM_ENTITY_ENT_TYP_T (PERSON Type)
    the_sql := 'INSERT INTO KRIM_ENTITY_ENT_TYP_T (ENT_TYP_CD, ENTITY_ID, ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR)
    VALUES (:1, :2, :3, :4, :5, :6)';
    execute immediate the_sql USING entityType, newEntityId, rec.active_flag, SYSDATE, SYS_GUID(), 1;

    IF (rec.FIRST_NAME IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.FIRST_NAME))) > 0)
     OR (rec.MIDDLE_NAME IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.MIDDLE_NAME))) > 0)
     OR (rec.LAST_NAME IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.LAST_NAME))) > 0)
     OR (rec.SALUTATION IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.SALUTATION))) > 0) THEN
      --Get new entity name id (PRFR Type)
      the_sql := 'SELECT KRIM_ENTITY_NM_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityNamePfrdId;

      IF (LENGTH(rec.SALUTATION) > 20) THEN
          dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with salutation of ' || rec.SALUTATION || ' is too long and must be substringed to ' || SUBSTR(COALESCE(rec.SALUTATION,''), 1, 20) || '.');
      END IF; 

      --KRIM_ENTITY_NM_T (PRFR Type)
      the_sql := 'insert into krim_entity_nm_t (entity_nm_id, obj_id, ver_nbr, entity_id, nm_typ_cd, first_nm, middle_nm, last_nm, suffix_nm, title_nm, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12, :13)';
      execute immediate the_sql USING newEntityNamePfrdId, SYS_GUID(), 1, newEntityId, 'PRFR', rec.FIRST_NAME, rec.MIDDLE_NAME, rec.LAST_NAME, '', SUBSTR(COALESCE(rec.SALUTATION,''), 1, 20), 'Y', rec.active_flag, SYSDATE;
    END IF;

    IF (rec.PRIOR_NAME IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.PRIOR_NAME))) > 0) THEN
      --Get new entity name id (PRIOR Type)
      the_sql := 'SELECT KRIM_ENTITY_NM_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityNamePriorId;

      IF (LENGTH(rec.SALUTATION) > 20) THEN
          dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with salutation of ' || rec.SALUTATION || ' is too long and must be substringed to ' || SUBSTR(COALESCE(rec.SALUTATION,''), 1, 20) || '.');
      END IF; 

      --KRIM_ENTITY_NM_T (PRIOR Type)
      the_sql := 'insert into krim_entity_nm_t (entity_nm_id, obj_id, ver_nbr, entity_id, nm_typ_cd, first_nm, middle_nm, last_nm, suffix_nm, title_nm, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12, :13)';
      execute immediate the_sql USING newEntityNamePriorId, SYS_GUID(), 1, newEntityId, 'PRIOR', rec.FIRST_NAME, rec.MIDDLE_NAME, rec.PRIOR_NAME, '', SUBSTR(COALESCE(rec.SALUTATION,''), 1, 20), 'N', rec.active_flag, SYSDATE;
    END IF;
    
    IF (rec.FAX_NUMBER IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.FAX_NUMBER))) > 0) THEN
      --Get new entity phone id (FAX Type)
      the_sql := 'SELECT KRIM_ENTITY_PHONE_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityPhoneFaxId;
      
      --KRIM_ENTITY_PHONE_T (FAX Type)
      the_sql := 'insert into krim_entity_phone_t (entity_phone_id, obj_id, ver_nbr, entity_id, ent_typ_cd, phone_typ_cd, phone_nbr, phone_extn_nbr, postal_cntry_cd, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12)';
      execute immediate the_sql USING newEntityPhoneFaxId, SYS_GUID(), 1, newEntityId, entityType, 'FAX', rec.FAX_NUMBER, '', '', 'N', rec.active_flag, SYSDATE;
    END IF;
    
    IF (rec.PAGER_NUMBER IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.PAGER_NUMBER))) > 0) THEN
      --Get new entity phone id (PAGER Type)
      the_sql := 'SELECT KRIM_ENTITY_PHONE_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityPhonePagerId;
      
      --KRIM_ENTITY_PHONE_T (PAGER Type)
      the_sql := 'insert into krim_entity_phone_t (entity_phone_id, obj_id, ver_nbr, entity_id, ent_typ_cd, phone_typ_cd, phone_nbr, phone_extn_nbr, postal_cntry_cd, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12)';
      execute immediate the_sql USING newEntityPhonePagerId, SYS_GUID(), 1, newEntityId, entityType, 'PGR', rec.PAGER_NUMBER, '', '', 'N', rec.active_flag, SYSDATE;
    END IF;
    
    IF (rec.MOBILE_PHONE_NUMBER IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.MOBILE_PHONE_NUMBER))) > 0) THEN
      --Get new entity phone id (MOBILE Type)
      the_sql := 'SELECT KRIM_ENTITY_PHONE_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityPhoneMobileId;
      
      --KRIM_ENTITY_PHONE_T (MOBILE Type)
      the_sql := 'insert into krim_entity_phone_t (entity_phone_id, obj_id, ver_nbr, entity_id, ent_typ_cd, phone_typ_cd, phone_nbr, phone_extn_nbr, postal_cntry_cd, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12)';
      execute immediate the_sql USING newEntityPhoneMobileId, SYS_GUID(), 1, newEntityId, entityType, 'MBL', rec.MOBILE_PHONE_NUMBER, '', '', 'N', rec.active_flag, SYSDATE;
    END IF;
    
    IF (rec.OFFICE_PHONE IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.OFFICE_PHONE))) > 0) THEN
      --Get new entity phone id (Work Type & default)
      the_sql := 'SELECT KRIM_ENTITY_PHONE_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityPhoneOfficeId;
      
      --KRIM_ENTITY_PHONE_T (Work Type & default)
      the_sql := 'insert into krim_entity_phone_t (entity_phone_id, obj_id, ver_nbr, entity_id, ent_typ_cd, phone_typ_cd, phone_nbr, phone_extn_nbr, postal_cntry_cd, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12)';
      execute immediate the_sql USING newEntityPhoneOfficeId, SYS_GUID(), 1, newEntityId, entityType, 'WRK', rec.OFFICE_PHONE, '', '', 'Y', rec.active_flag, SYSDATE;
    END IF;
    
    IF (rec.SECONDRY_OFFICE_PHONE IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.SECONDRY_OFFICE_PHONE))) > 0) THEN
      --Get new entity phone id (2nd Work Type)
      the_sql := 'SELECT KRIM_ENTITY_PHONE_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityPhoneSecOfficeId;
      
      --KRIM_ENTITY_PHONE_T (2nd Work Type)
      the_sql := 'insert into krim_entity_phone_t (entity_phone_id, obj_id, ver_nbr, entity_id, ent_typ_cd, phone_typ_cd, phone_nbr, phone_extn_nbr, postal_cntry_cd, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12)';
      execute immediate the_sql USING newEntityPhoneSecOfficeId, SYS_GUID(), 1, newEntityId, entityType, 'WRK', rec.SECONDRY_OFFICE_PHONE, '', '', 'N', rec.active_flag, SYSDATE;
    END IF;
    
    IF (rec.EMAIL_ADDRESS IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.EMAIL_ADDRESS))) > 0) THEN
      --Get new entity email id (Work Type & default)
      the_sql := 'SELECT KRIM_ENTITY_EMAIL_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityEmailWorkId;
      
      --KRIM_ENTITY_EMAIL_T (Work Type & default)
      the_sql := 'insert into krim_entity_email_t (entity_email_id, obj_id, ver_nbr, entity_id, ent_typ_cd, email_typ_cd, email_addr, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10)';
      execute immediate the_sql USING newEntityEmailWorkId, SYS_GUID(), 1, newEntityId, entityType, 'WRK', rec.EMAIL_ADDRESS, 'Y', rec.active_flag, SYSDATE;
    END IF;
    
    IF (rec.GENDER IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.GENDER))) > 0)
     OR (rec.DATE_OF_BIRTH IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.DATE_OF_BIRTH))) > 0) THEN
      --Convert gender code
      IF UPPER(rec.GENDER) = 'MALE' OR UPPER(rec.GENDER) = 'M' THEN
         genderConversion := 'M';
      ELSIF UPPER(rec.GENDER) = 'FEMALE' OR UPPER(rec.GENDER) = 'F' THEN
         genderConversion := 'F';
      ELSE
         dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with gender of ' || rec.GENDER || ' could not be converted to a M or F code value.');
      END IF;
      --KRIM_ENTITY_BIO_T
      the_sql := 'insert into krim_entity_bio_t (entity_id, obj_id, ver_nbr, birth_dt, gndr_cd, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6)';
      execute immediate the_sql USING newEntityId, SYS_GUID(), 1, rec.DATE_OF_BIRTH, genderConversion, SYSDATE;
    END IF;

    IF (rec.RACE IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.RACE))) > 0) THEN
      --Get new entity ethnic id
      the_sql := 'SELECT KRIM_ENTITY_ETHNIC_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEthnicEntityId;
      
      --KRIM_ENTITY_ETHNIC_T
      the_sql := 'insert into krim_entity_ethnic_t (id, entity_id, ethncty_cd, ver_nbr, obj_id)
      values (:1, :2, :3, :4, :5)';
      execute immediate the_sql USING newEthnicEntityId, newEntityId, rec.RACE, 1, SYS_GUID();
    END IF;

    --Affiliation record START
    
    --for faculty type.
    IF UPPER(rec.IS_FACULTY) = 'Y' THEN
      --Get new affiliation id
      the_sql := 'SELECT KRIM_ENTITY_AFLTN_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityAfltnFacultyId;
      
      --KRIM_ENTITY_AFLTN_T
      the_sql := 'insert into krim_entity_afltn_t (entity_afltn_id, obj_id, ver_nbr, entity_id, afltn_typ_cd, campus_cd, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9)';
      execute immediate the_sql USING newEntityAfltnFacultyId, SYS_GUID(), 1, newEntityId, 'FCLTY', '', 'Y', 'Y', SYSDATE;
    ELSIF UPPER(rec.IS_FACULTY) <> 'N' AND UPPER(rec.IS_FACULTY) <> '' AND UPPER(rec.IS_FACULTY) IS NOT NULL THEN
          dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with IS_FACULTY of ' || rec.IS_FACULTY || ' could not be converted to a new affiliation record.');
    END IF;
    
    --for graduate student type.
    IF UPPER(rec.IS_GRADUATE_STUDENT_STAFF) = 'Y' THEN
      
      --Get new affiliation id
      the_sql := 'SELECT KRIM_ENTITY_AFLTN_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityAfltnGradStudentId;
      
      --KRIM_ENTITY_AFLTN_T
      the_sql := 'insert into krim_entity_afltn_t (entity_afltn_id, obj_id, ver_nbr, entity_id, afltn_typ_cd, campus_cd, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9)';
      execute immediate the_sql USING newEntityAfltnGradStudentId, SYS_GUID(), 1, newEntityId, 'GRD_STDNT_STAFF', '', 'N', 'Y', SYSDATE;
    ELSIF UPPER(rec.IS_GRADUATE_STUDENT_STAFF) <> 'N' AND UPPER(rec.IS_GRADUATE_STUDENT_STAFF) <> '' AND UPPER(rec.IS_GRADUATE_STUDENT_STAFF) IS NOT NULL THEN
          dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with IS_GRADUATE_STUDENT_STAFF of ' || rec.IS_GRADUATE_STUDENT_STAFF || ' could not be converted to a new affiliation record.');
    END IF;
    
    --for research type.
    IF UPPER(rec.IS_RESEARCH_STAFF) = 'Y' THEN
      
      --Get new affiliation id
      the_sql := 'SELECT KRIM_ENTITY_AFLTN_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityAfltnResearchId;
      
      --KRIM_ENTITY_AFLTN_T
      the_sql := 'insert into krim_entity_afltn_t (entity_afltn_id, obj_id, ver_nbr, entity_id, afltn_typ_cd, campus_cd, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9)';
      execute immediate the_sql USING newEntityAfltnResearchId, SYS_GUID(), 1, newEntityId, 'RSRCH_STAFF', '', 'N', 'Y', SYSDATE;
    ELSIF UPPER(rec.IS_RESEARCH_STAFF) <> 'N' AND UPPER(rec.IS_RESEARCH_STAFF) <> '' AND UPPER(rec.IS_RESEARCH_STAFF) IS NOT NULL THEN
          dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with IS_RESEARCH_STAFF of ' || rec.IS_RESEARCH_STAFF || ' could not be converted to a new affiliation record.');
    END IF;
    
    --for service type.
    IF UPPER(rec.IS_SERVICE_STAFF) = 'Y' THEN
      
      --Get new affiliation id
      the_sql := 'SELECT KRIM_ENTITY_AFLTN_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityAfltnServiceId;
      
      --KRIM_ENTITY_AFLTN_T
      the_sql := 'insert into krim_entity_afltn_t (entity_afltn_id, obj_id, ver_nbr, entity_id, afltn_typ_cd, campus_cd, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9)';
      execute immediate the_sql USING newEntityAfltnServiceId, SYS_GUID(), 1, newEntityId, 'SRVC_STAFF', '', 'N', 'Y', SYSDATE;
    ELSIF UPPER(rec.IS_SERVICE_STAFF) <> 'N' AND UPPER(rec.IS_SERVICE_STAFF) <> '' AND UPPER(rec.IS_SERVICE_STAFF) IS NOT NULL THEN
          dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with IS_SERVICE_STAFF of ' || rec.IS_SERVICE_STAFF || ' could not be converted to a new affiliation record.');
    END IF;
    
    --for support type.
    IF UPPER(rec.IS_SUPPORT_STAFF) = 'Y' THEN
      
      --Get new affiliation id
      the_sql := 'SELECT KRIM_ENTITY_AFLTN_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityAfltnSupportId;
      
      --KRIM_ENTITY_AFLTN_T
      the_sql := 'insert into krim_entity_afltn_t (entity_afltn_id, obj_id, ver_nbr, entity_id, afltn_typ_cd, campus_cd, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9)';
      execute immediate the_sql USING newEntityAfltnSupportId, SYS_GUID(), 1, newEntityId, 'SUPPRT_STAFF', '', 'N', 'Y', SYSDATE;
    ELSIF UPPER(rec.IS_SUPPORT_STAFF) <> 'N' AND UPPER(rec.IS_SUPPORT_STAFF) <> '' AND UPPER(rec.IS_SUPPORT_STAFF) IS NOT NULL THEN
          dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with IS_SUPPORT_STAFF of ' || rec.IS_SUPPORT_STAFF || ' could not be converted to a new affiliation record.');
    END IF;
    
    --for other academic type.
    IF UPPER(rec.IS_OTHER_ACCADEMIC_GROUP) = 'Y' THEN
      
      --Get new affiliation id
      the_sql := 'SELECT KRIM_ENTITY_AFLTN_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityAfltnAcademicId;
      
      --KRIM_ENTITY_AFLTN_T
      the_sql := 'insert into krim_entity_afltn_t (entity_afltn_id, obj_id, ver_nbr, entity_id, afltn_typ_cd, campus_cd, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9)';
      execute immediate the_sql USING newEntityAfltnAcademicId, SYS_GUID(), 1, newEntityId, 'OTH_ACADMC_GRP', '', 'N', 'Y', SYSDATE;
    ELSIF UPPER(rec.IS_OTHER_ACCADEMIC_GROUP) <> 'N' AND UPPER(rec.IS_OTHER_ACCADEMIC_GROUP) <> '' AND UPPER(rec.IS_OTHER_ACCADEMIC_GROUP) IS NOT NULL THEN
          dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with IS_OTHER_ACCADEMIC_GROUP of ' || rec.IS_OTHER_ACCADEMIC_GROUP || ' could not be converted to a new affiliation record.');
    END IF;
    
    --for medical type.
    IF UPPER(rec.IS_MEDICAL_STAFF) = 'Y' THEN
      
      --Get new affiliation id
      the_sql := 'SELECT KRIM_ENTITY_AFLTN_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityAfltnMedicalId;
      
      --KRIM_ENTITY_AFLTN_T
      the_sql := 'insert into krim_entity_afltn_t (entity_afltn_id, obj_id, ver_nbr, entity_id, afltn_typ_cd, campus_cd, dflt_ind, actv_ind, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9)';
      execute immediate the_sql USING newEntityAfltnMedicalId, SYS_GUID(), 1, newEntityId, 'MED_STAFF', '', 'N', 'Y', SYSDATE;
    ELSIF UPPER(rec.IS_MEDICAL_STAFF) <> 'N' AND UPPER(rec.IS_MEDICAL_STAFF) <> '' AND UPPER(rec.IS_MEDICAL_STAFF) IS NOT NULL THEN
          dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with IS_MEDICAL_STAFF of ' || rec.IS_MEDICAL_STAFF || ' could not be converted to a new affiliation record.');
    END IF;
    
    --Affiliation record END
    
    IF (UPPER(rec.COUNTRY_OF_CITIZENSHIP) IS NOT NULL OR LENGTH(RTRIM(LTRIM(rec.COUNTRY_OF_CITIZENSHIP))) > 0) THEN                         

       BEGIN
          --the country of citizenship column length suggests that it holds the description but the Person maintenance does not
          --the_sql := 'select t.two_char_code from kc_country_codes_t t where country_name = :1';
          --execute immediate the_sql INTO countryConversion USING rec.COUNTRY_OF_CITIZENSHIP;
          the_sql := 'select t.two_char_code from kc_country_codes_t t where t.three_char_code = :1';
          execute immediate the_sql INTO countryConversion USING rec.COUNTRY_OF_CITIZENSHIP;
       
          --Get new Entity Citizenship id
          the_sql := 'SELECT KRIM_ENTITY_CTZNSHP_ID_S.nextval FROM dual';
          execute immediate the_sql INTO newEntityCitizenshipId;
              
          --KRIM_ENTITY_CTZNSHP_T
          the_sql := 'insert into krim_entity_ctznshp_t (entity_ctznshp_id, obj_id, ver_nbr, entity_id, postal_cntry_cd, ctznshp_stat_cd, strt_dt, end_dt, actv_ind, last_updt_dt)
          values (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10)';
          execute immediate the_sql USING newEntityCitizenshipId, SYS_GUID(), 1, newEntityId, countryConversion, '', '', '', rec.active_flag, SYSDATE;
       EXCEPTION
         WHEN NO_DATA_FOUND THEN 
              dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with country_of_citizenship of ' || rec.COUNTRY_OF_CITIZENSHIP || ' could not be converted to a code value.');
       END;
    END IF; 
    
    IF (rec.COUNTRY_CODE IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.COUNTRY_CODE))) > 0)
     OR (rec.STATE IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.STATE))) > 0)
     OR (rec.ADDRESS_LINE_1 IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.ADDRESS_LINE_1))) > 0)
     OR (rec.ADDRESS_LINE_2 IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.ADDRESS_LINE_2))) > 0)
     OR (rec.ADDRESS_LINE_3 IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.ADDRESS_LINE_3))) > 0)
     OR (rec.CITY IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.CITY))) > 0)
     OR (rec.POSTAL_CODE IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.POSTAL_CODE))) > 0) THEN
      --Get new Entity Address id
      the_sql := 'SELECT KRIM_ENTITY_ADDR_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityAddressId;
      
      BEGIN
          --convert the country code
          IF rec.COUNTRY_CODE IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.COUNTRY_CODE))) > 0 THEN
             the_sql := 'select t.two_char_code from kc_country_codes_t t where t.three_char_code = :1';
          	 execute immediate the_sql INTO countryConversion USING rec.COUNTRY_CODE;
          ELSE 
             countryConversion := null;
          END IF;
       EXCEPTION
          WHEN NO_DATA_FOUND THEN 
            dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with country_code of ' || rec.COUNTRY_CODE || ' could not be converted to a code value.');
            countryConversion := null;
      END;
      
      BEGIN
          --convert the state code
          IF rec.STATE IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.STATE))) > 0 THEN
             --don't do conversion b/c the Person maintenance stores states
             --as codes even though the column length suggests descriptions 
             --the_sql := 'select state_code from state_code where description = :1';
             --execute immediate the_sql INTO stateConversion USING rec.STATE;
             the_sql := 'select state_code from state_code where state_code = :1';
             --execute immediate the_sql INTO stateConversion USING rec.STATE;
          ELSE
             stateConversion := null;
          END IF;
      EXCEPTION
          WHEN NO_DATA_FOUND THEN 
            dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with state of ' || rec.STATE || ' could not be converted to a code value.');
            countryConversion := null;
      END;
      
      IF (LENGTH(rec.ADDRESS_LINE_1) > 50) THEN
          dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with address line 1 of ' || rec.ADDRESS_LINE_1 || ' is too long and must be substringed to ' || SUBSTR(COALESCE(rec.ADDRESS_LINE_1,''), 1, 50) || '.');
      END IF;
      
      IF (LENGTH(rec.ADDRESS_LINE_2) > 50) THEN
          dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with address line 2 of ' || rec.ADDRESS_LINE_2 || ' is too long and must be substringed to ' || SUBSTR(COALESCE(rec.ADDRESS_LINE_2,''), 1, 50) || '.');
      END IF; 
      
      IF (LENGTH(rec.ADDRESS_LINE_3) > 50) THEN
          dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with address line 3 of ' || rec.ADDRESS_LINE_3 || ' is too long and must be substringed to ' || SUBSTR(COALESCE(rec.ADDRESS_LINE_3,''), 1, 50) || '.');
      END IF; 
      
      --KRIM_ENTITY_ADDRESS_T
      the_sql := 'insert into krim_entity_addr_t (postal_cntry_cd, dflt_ind, actv_ind, last_updt_dt, entity_addr_id, obj_id, ver_nbr, entity_id, ent_typ_cd, addr_typ_cd, addr_line_1, addr_line_2, addr_line_3, city_nm, postal_state_cd, postal_cd)
      values (:1, :2, :3, :4, :5, :6, :7, :8, :9, :10, :11, :12, :13, :14, :15, :16)';
      execute immediate the_sql USING countryConversion, 'Y', rec.active_flag, SYSDATE, newEntityAddressId, SYS_GUID(), 1, newEntityId, entityType, 'WRK', SUBSTR(COALESCE(rec.ADDRESS_LINE_1,''), 1, 50), SUBSTR(COALESCE(rec.ADDRESS_LINE_2,''), 1, 50), SUBSTR(COALESCE(rec.ADDRESS_LINE_3,''), 1, 50), rec.CITY, stateConversion, rec.POSTAL_CODE;
    END IF;
    
    IF (rec.SSN IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.SSN))) > 0) THEN
      --Get new ext id
      the_sql := 'SELECT KRIM_ENTITY_EXT_ID_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityExtSsnId;
    
      --KRIM_ENTITY_EXT_ID_T (SSN)
      the_sql := 'insert into krim_entity_ext_id_t (entity_ext_id_id, obj_id, ver_nbr, entity_id, ext_id_typ_cd, ext_id, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7)';
      execute immediate the_sql USING newEntityExtSsnId, SYS_GUID(), 1, newEntityId, 'SSN', rec.SSN, SYSDATE;
    END IF;
    
    IF (rec.ERA_COMMONS_USER_NAME IS NOT NULL AND LENGTH(RTRIM(LTRIM(rec.ERA_COMMONS_USER_NAME))) > 0) THEN
      --Get new ext id
      the_sql := 'SELECT KRIM_ENTITY_EXT_ID_ID_S.nextval FROM dual';
      execute immediate the_sql INTO newEntityExtEraCommonsId;
      
      --KRIM_ENTITY_EXT_ID_T (ERA COMMONS)
      the_sql := 'insert into krim_entity_ext_id_t (entity_ext_id_id, obj_id, ver_nbr, entity_id, ext_id_typ_cd, ext_id, last_updt_dt)
      values (:1, :2, :3, :4, :5, :6, :7)';
      execute immediate the_sql USING newEntityExtEraCommonsId, SYS_GUID(), 1, newEntityId, 'ERAC', rec.ERA_COMMONS_USER_NAME, SYSDATE;      
    END IF;
    
    --Get new principal id
    the_sql := 'SELECT KRIM_PRNCPL_ID_S.nextval FROM dual';
    execute immediate the_sql INTO newPrincipalId;

    IF rec.KIM_PERSON_ID IS NOT NULL THEN
      BEGIN
        --get the username & password out of KIM
        the_sql := 'select username, password from kim_persons_t where id = :1';
        execute immediate the_sql INTO kimUserName, kimPassword USING rec.KIM_PERSON_ID;        
      EXCEPTION
        WHEN NO_DATA_FOUND THEN 
          dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' with kim person id of ' || rec.KIM_PERSON_ID || ' could not be found.');
      END;
    ELSE
      dbms_output.put_line ('The person id ' || rec.PERSON_ID || ' with new entity id of ' || newEntityId || ' has a null kim person id.');
      kimUserName := rec.USER_NAME;
      kimPassword := '';
    END IF;

    --KRIM_PRNCPL_T
    the_sql := 'insert into krim_prncpl_t (obj_id, ver_nbr, prncpl_nm, entity_id, prncpl_pswd, actv_ind, last_updt_dt, prncpl_id)
    values (:1, :2, :3, :4, :5, :6, :7, :8)';
    execute immediate the_sql USING SYS_GUID(), 1, kimUserName, newEntityId, kimPassword, rec.active_flag, SYSDATE, newPrincipalId;
     
    --Switch references to new entity id
    --not using dynamic sql to figure out what tables refer to PERSON_ID for 2 reasons
    --  1) checking tables for column name PERSON_ID will not work b/c some tables w/ column PERSON_ID do not refer to the PERSON table.
    --  2) checking tables for a fk constraint to the PERSON table will not work b/c most of these constraints are missing.   
    the_sql := 'UPDATE AWARD_PERSONS SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;
    
    the_sql := 'UPDATE AWARD_UNIT_CONTACTS SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE BUDGET_PERSONNEL_DETAILS SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE BUDGET_PERSONS SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE BUDGET_PER_DET_RATE_AND_BASE SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE COMM_MEMBERSHIPS SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE COMM_SCHEDULE_ATTENDANCE SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE EPS_PROP_PERSON SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE EPS_PROP_PERSON_BIO SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;
    
    the_sql := 'UPDATE EPS_PROP_USER_ROLES SET USER_ID=:1 WHERE USER_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE KRA_USER SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE PERSON_DEGREE SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE PERSON_TRAINING SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE PROPOSAL_PERSONS SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE PROPOSAL_PER_CREDIT_SPLIT SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE PROPOSAL_UNITS SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE PROPOSAL_UNIT_CREDIT_SPLIT SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE PROP_ROLE_TEMPLATE SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE PROTOCOL_PERSONS SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE PROTOCOL_REVIEWERS SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE PROTOCOL_UNITS SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE UNIT_ACL SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE UNIT_ADMINISTRATOR SET PERSON_ID=:1 WHERE PERSON_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;

    the_sql := 'UPDATE NARRATIVE_USER_RIGHTS SET USER_ID=:1 WHERE USER_ID=:2';
    execute immediate the_sql USING newPrincipalId, rec.PERSON_ID;
    
    --Change the primary key to the principal id
    rec.PERSON_ID := newPrincipalId;

  END LOOP;
  
END;
