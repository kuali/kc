CREATE TABLE KC_COUNTRY_CODES_T
(
  ID NUMBER(8) NOT NULL,
  COUNTRY_NAME VARCHAR2(50) NOT NULL,
  TWO_CHAR_CODE VARCHAR2(2) NOT NULL,
  THREE_CHAR_CODE VARCHAR2(3) NOT NULL,
  OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL,
  VER_NBR VARCHAR2(8) NOT NULL, 
  CONSTRAINT KC_COUNTRY_CODES_T_PK PRIMARY KEY (ID)
  ENABLE
);

ALTER TABLE KC_COUNTRY_CODES_T
ADD CONSTRAINT KC_COUNTRY_CODES_T_UK1 UNIQUE (TWO_CHAR_CODE)
 ENABLE;

ALTER TABLE KC_COUNTRY_CODES_T
ADD CONSTRAINT KC_COUNTRY_CODES_T_UK2 UNIQUE (THREE_CHAR_CODE)
 ENABLE;
 
ALTER TABLE KC_COUNTRY_CODES_T 
ADD ("UPDATE_USER" VARCHAR2(60));

ALTER TABLE KC_COUNTRY_CODES_T 
ADD ("UPDATE_TIMESTAMP" DATE);
 
commit;

CREATE SEQUENCE KC_COUNTRY_CODES_SEQ INCREMENT BY 1 START WITH 1 MINVALUE 0;
commit;

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Afghanistan','AF','AFG', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Albania','AL','ALB', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Algeria','DZ','DZA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'American Samoa','AS','ASM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Andorra','AD','AND', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Angola','AO','AGO', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Anguilla','AI','AIA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Antarctica','AQ','ATA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Antigua and Barbuda','AG','ATG', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Argentina','AR','ARG', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Armenia','AM','ARM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Aruba','AW','ABW', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Australia','AU','AUS', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Austria','AT','AUT', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Azerbaijan','AZ','AZE', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Bahamas','BS','BHS', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Bahrain','BH','BHR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Bangladesh','BD','BGD', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Barbados','BB','BRB', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Belarus','BY','BLR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Belgium','BE','BEL', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Belize','BZ','BLZ', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Benin','BJ','BEN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Bermuda','BM','BMU', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Bhutan','BT','BTN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Bolivia','BO','BOL', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Bosnia and Herzegovina','BA','BIH', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Botswana','BW','BWA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Bouvet Island','BV','BVT', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Brazil','BR','BRA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'British Indian Ocean Territory','IO','IOT', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Brunei Darussalam','BN','BRN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Bulgaria','BG','BGR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Burkina Faso','BF','BFA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Burundi','BI','BDI', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Cambodia','KH','KHM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Cameroon','CM','CMR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Canada','CA','CAN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Cape Verde','CV','CPV', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Cayman Islands','KY','CYM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Central African Republic','CF','CAF', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Chad','TD','TCD', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Chile','CL','CHL', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'China','CN','CHN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Christmas Island','CX','CXR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Cocos (Keeling) Islands','CC','CCK', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Colombia','CO','COL', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Comoros','KM','COM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Congo','CG','COG', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Congo, The Democratic Republic Of The','CD','COD', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Cook Islands','CK','COK', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Costa Rica','CR','CRI', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Croatia','HR','HRV', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Cuba','CU','CUB', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Cyprus','CY','CYP', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Czech Republic','CZ','CZE', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Côte D''Ivoire','CI','CIV', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Denmark','DK','DNK', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Djibouti','DJ','DJI', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Dominica','DM','DMA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Dominican Republic','DO','DOM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Ecuador','EC','ECU', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Egypt','EG','EGY', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'El Salvador','SV','SLV', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Equatorial Guinea','GQ','GNQ', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Eritrea','ER','ERI', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Estonia','EE','EST', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Ethiopia','ET','ETH', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Falkland Islands (Malvinas)','FK','FLK', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Faroe Islands','FO','FRO', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Fiji','FJ','FJI', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Finland','FI','FIN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'France','FR','FRA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'French Guiana','GF','GUF', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'French Polynesia','PF','PYF', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'French Southern Territories','TF','ATF', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Gabon','GA','GAB', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Gambia','GM','GMB', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Georgia','GE','GEO', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Germany','DE','DEU', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Ghana','GH','GHA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Gibraltar','GI','GIB', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Greece','GR','GRC', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Greenland','GL','GRL', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Grenada','GD','GRD', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Guadeloupe','GP','GLP', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Guam','GU','GUM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Guatemala','GT','GTM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Guernsey','GG','GGY', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Guinea','GN','GIN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Guinea-Bissau','GW','GNB', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Guyana','GY','GUY', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Haiti','HT','HTI', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Heard and McDonald Islands','HM','HMD', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Holy See (Vatican City State)','VA','VAT', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Honduras','HN','HND', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Hong Kong','HK','HKG', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Hungary','HU','HUN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Iceland','IS','ISL', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'India','IN','IND', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Indonesia','ID','IDN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Iran, Islamic Republic Of','IR','IRN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Iraq','IQ','IRQ', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Ireland','IE','IRL', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Isle of Man','IM','IMN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Israel','IL','ISR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Italy','IT','ITA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Jamaica','JM','JAM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Japan','JP','JPN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Jersey','JE','JEY', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Jordan','JO','JOR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Kazakhstan','KZ','KAZ', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Kenya','KE','KEN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Kiribati','KI','KIR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Korea, Democratic People''s Republic Of','KP','PRK', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Korea, Republic of','KR','KOR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Kuwait','KW','KWT', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Kyrgyzstan','KG','KGZ', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Lao People''s Democratic Republic','LA','LAO', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Latvia','LV','LVA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Lebanon','LB','LBN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Lesotho','LS','LSO', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Liberia','LR','LBR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Libyan Arab Jamahiriya','LY','LBY', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Liechtenstein','LI','LIE', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Lithuania','LT','LTU', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Luxembourg','LU','LUX', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Macao','MO','MAC', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Macedonia, the Former Yugoslav Republic Of','MK','MKD', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Madagascar','MG','MDG', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Malawi','MW','MWI', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Malaysia','MY','MYS', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Maldives','MV','MDV', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Mali','ML','MLI', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Malta','MT','MLT', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Marshall Islands','MH','MHL', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Martinique','MQ','MTQ', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Mauritania','MR','MRT', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Mauritius','MU','MUS', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Mayotte','YT','MYT', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Mexico','MX','MEX', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Micronesia, Federated States Of','FM','FSM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Moldova, Republic of','MD','MDA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Monaco','MC','MCO', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Mongolia','MN','MNG', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Montenegro','ME','MNE', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Montserrat','MS','MSR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Morocco','MA','MAR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Mozambique','MZ','MOZ', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Myanmar','MM','MMR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Namibia','NA','NAM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Nauru','NR','NRU', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Nepal','NP','NPL', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Netherlands','NL','NLD', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Netherlands Antilles','AN','ANT', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'New Caledonia','NC','NCL', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'New Zealand','NZ','NZL', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Nicaragua','NI','NIC', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Niger','NE','NER', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Nigeria','NG','NGA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Niue','NU','NIU', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Norfolk Island','NF','NFK', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Northern Mariana Islands','MP','MNP', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Norway','NO','NOR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Oman','OM','OMN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Pakistan','PK','PAK', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Palau','PW','PLW', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Palestinian Territory, Occupied','PS','PSE', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Panama','PA','PAN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Papua New Guinea','PG','PNG', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Paraguay','PY','PRY', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Peru','PE','PER', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Philippines','PH','PHL', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Pitcairn','PN','PCN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Poland','PL','POL', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Portugal','PT','PRT', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Puerto Rico','PR','PRI', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Qatar','QA','QAT', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Romania','RO','ROU', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Russian Federation','RU','RUS', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Rwanda','RW','RWA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Réunion','RE','REU', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Saint Barthélemy','BL','BLM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Saint Helena','SH','SHN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Saint Kitts And Nevis','KN','KNA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Saint Lucia','LC','LCA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Saint Martin','MF','MAF', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Saint Pierre And Miquelon','PM','SPM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Saint Vincent And The Grenedines','VC','VCT', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Samoa','WS','WSM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'San Marino','SM','SMR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Sao Tome and Principe','ST','STP', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Saudi Arabia','SA','SAU', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Senegal','SN','SEN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Serbia','RS','SRB', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Seychelles','SC','SYC', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Sierra Leone','SL','SLE', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Singapore','SG','SGP', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Slovakia','SK','SVK', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Slovenia','SI','SVN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Solomon Islands','SB','SLB', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Somalia','SO','SOM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'South Africa','ZA','ZAF', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'South Georgia and the South Sandwich Islands','GS','SGS', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Spain','ES','ESP', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Sri Lanka','LK','LKA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Sudan','SD','SDN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Suriname','SR','SUR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Svalbard And Jan Mayen','SJ','SJM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Swaziland','SZ','SWZ', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Sweden','SE','SWE', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Switzerland','CH','CHE', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Syrian Arab Republic','SY','SYR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Taiwan, Province Of China','TW','TWN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Tajikistan','TJ','TJK', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Tanzania, United Republic of','TZ','TZA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Thailand','TH','THA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Timor-Leste','TL','TLS', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Togo','TG','TGO', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Tokelau','TK','TKL', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Tonga','TO','TON', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Trinidad and Tobago','TT','TTO', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Tunisia','TN','TUN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Turkey','TR','TUR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Turkmenistan','TM','TKM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Turks and Caicos Islands','TC','TCA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Tuvalu','TV','TUV', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Uganda','UG','UGA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Ukraine','UA','UKR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'United Arab Emirates','AE','ARE', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'United Kingdom','GB','GBR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'United States','US','USA', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'United States Minor Outlying Islands','UM','UMI', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Uruguay','UY','URY', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Uzbekistan','UZ','UZB', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Vanuatu','VU','VUT', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Venezuela, Bolivarian Republic of','VE','VEN', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Viet Nam','VN','VNM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Virgin Islands, British','VG','VGB', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Virgin Islands, U.S.','VI','VIR', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Wallis and Futuna','WF','WLF', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Western Sahara','EH','ESH', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Yemen','YE','YEM', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Zambia','ZM','ZMB', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Zimbabwe','ZW','ZWE', 'kradev', sysdate);

insert into kc_country_codes_t (id, obj_id, ver_nbr, country_name, two_char_code, three_char_code, update_user, update_timestamp)
values(kc_country_codes_seq.nextval , sys_guid(), 1, 'Åland Islands','AX','ALA', 'kradev', sysdate);

commit;