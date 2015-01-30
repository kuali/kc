--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

delete from ARG_VALUE_LOOKUP where ARGUMENT_NAME = 'GraduateLevelDegree';

delete from ARG_VALUE_LOOKUP where ARGUMENT_NAME = 'Kirschstein-NRSASupportLevel';

delete from ARG_VALUE_LOOKUP where ARGUMENT_NAME = 'Kirschstein-NRSAAwardTYPE';

delete from ARG_VALUE_LOOKUP where ARGUMENT_NAME = 'AcademicAppointmentPeriod';

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','3200 PHARMACOLOGY','PHARMACOLOGY','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','1100 BIOCHEMISTRY','BIOCHEMISTRY','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','1200 BIOENGINEERING','BIOENGINEERING','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','1400 BIOPHYSICS','BIOPHYSICS','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','1500 BIOTECHNOLOGY','BIOTECHNOLOGY','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','1600 CELL AND DEVELOPMENTAL BIOLOGY','CELL AND DEVELOPMENTAL BIOLOGY','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','1700 CHEMISTRY','CHEMISTRY','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','1900 ENVIRONMENTAL SCIENCES','ENVIRONMENTAL SCIENCES','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','2000 GENETICS','GENETICS','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','2200 IMMUNOLOGY','IMMUNOLOGY','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','2400 MICROBIOLOGY AND INFECTIOUS DISEASES','MICROBIOLOGY AND INFECTIOUS DISEASES','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','2600 MOLECULAR BIOLOGY','MOLECULAR BIOLOGY','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','2800 NEUROSCIENCE','NEUROSCIENCE','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','3100 NUTRITIONAL SCIENCES','NUTRITIONAL SCIENCES','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','3300 PHYSIOLOGY','PHYSIOLOGY','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','3500 PLANT BIOLOGY','PLANT BIOLOGY','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','3600 PSYCHOLOGY, NON-CLINICAL','PSYCHOLOGY, NON-CLINICAL','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','3900 PUBLIC HEALTH','PUBLIC HEALTH','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','4100 RADIATION, NON-CLINICAL','RADIATION, NON-CLINICAL','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','4200 SOCIAL SCIENCES','SOCIAL SCIENCES','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','4400 STATISTICS AND/OR RESEARCH METHODS AND/OR INFORMATICS','RESEARCH METHODS AND/OR INFORMATICS','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','4600 TRAUMA, NON CLINICAL','TRAUMA, NON-CLINICAL','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','6100 ALLIED HEALTH','ALLIED HEALTH','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','6400 CLINICAL DENTISTRY',' CLINICAL DENTISTRY','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','6500 MEDICAL DISCIPLINES','MEDICAL DISCIPLINES','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','7300 PEDIATRIC DISCIPLINES','PEDIATRIC DISCIPLINES','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','7500 NURSING','NURSING','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','7700 VETERINARY MEDICINE','VETERINARY MEDICINE','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','5000 OTHER, Predominantly Non-Clinical or Lab-Based Research Training','5000 OTHER, Predominantly Non-Clinical or Lab-Based Research Training','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','6400 DENTISTRY','6400 DENTISTRY','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','6500 CLINICAL DISCIPLINES','6500 CLINICAL DISCIPLINES','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-Broad','8000 OTHER, Predominantly Clinical Research Training','8000 OTHER, Predominantly Clinical Research Training','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1110 Biological Chemistry','1110 Biological Chemistry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1120 Bioenergetics','1120 Bioenergetics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1130 Enzymology','1130 Enzymology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1140 Metabolism','1140 Metabolism','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1210 Bioelectric/Biomagnetic','1210 Bioelectric/Biomagnetic','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1220 Biomaterials','1220 Biomaterials','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1230 Biomechanical Engineering','1230 Biomechanical Engineering','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1240 Imaging','1240 Imaging','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1250 Instrumentation and Devices','1250 Instrumentation and Devices','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1260 Mathematical Modeling','1260 Mathematical Modeling','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1270 Medical Implant Science','1270 Medical Implant Science','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1280 Nanotechnology','1280 Nanotechnology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1290 Rehabilitation Engineering','1290 Rehabilitation Engineering','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1310 Tissue Engineering','1310 Tissue Engineering','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1410 Kinetics','1410 Kinetics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1420 Spectroscopy','1420 Spectroscopy','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1430 Structural Biology','1430 Structural Biology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1440 Theoretical Biophysics','1440 Theoretical Biophysics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1510 Applied Molecular Biology','1510 Applied Molecular Biology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1520 Bioprocessing and Fermentation','1520 Bioprocessing and Fermentation','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1530 Metabolic Engineering','1530 Metabolic Engineering','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1610 Cell Biology','1610 Cell Biology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1620 Developmental Biology','1620 Developmental Biology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1710 Analytical Chemistry','1710 Analytical Chemistry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1720 Bioinorganic Chemistry','1720 Bioinorganic Chemistry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1730 Bioorganic Chemistry','1730 Bioorganic Chemistry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1740 Biophysical Chemistry','1740 Biophysical Chemistry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1750 Medicinal Chemistry','1750 Medicinal Chemistry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1760 Physical Chemistry','1760 Physical Chemistry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','1770 Synthetic Chemistry','1770 Synthetic Chemistry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2010 Behavioral Genetics','2010 Behavioral Genetics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2020 Developmental Genetics','2020 Developmental Genetics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2030 Genetic Epidemiology','2030 Genetic Epidemiology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2040 Genetics of Aging','2040 Genetics of Aging','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2050 Genomics','2050 Genomics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2060 Human Genetics','2060 Human Genetics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2070 Molecular Genetics','2070 Molecular Genetics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2080 Population Genetics','2080 Population Genetics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2210 Asthma and Allergic Mechanisms','2210 Asthma and Allergic Mechanisms','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2220 Autoimmunity','2220 Autoimmunity','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2230 Immunodeficiency','2230 Immunodeficiency','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2240 Immunogenetics','2240 Immunogenetics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2250 Immunopathology','2250 Immunopathology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2260 Immunoregulation','2260 Immunoregulation','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2270 Inflammation','2270 Inflammation','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2280 Structural Immunology','2280 Structural Immunology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2290 Transplantation Biology','2290 Transplantation Biology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2310 Vaccine Development','2310 Vaccine Development','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2410 Bacteriology','2410 Bacteriology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2420 Etiology','2420 Etiology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2430 HIV/AIDS','2430 HIV/AIDS','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2440 Mycology','2440 Mycology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2450 Parasitology','2450 Parasitology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2460 Pathogenesis of Infectious Diseases','2460 Pathogenesis of Infectious Diseases','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2470 Virology','2470 Virology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2810 Behavioral Neuroscience','2810 Behavioral Neuroscience','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2820 Cellular neuroscience','2820 Cellular neuroscience','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2830 Cognitive neuroscience','2830 Cognitive neuroscience','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2840 Communication Neuroscience','2840 Communication Neuroscience','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2850 Computational Neuroscience','2850 Computational Neuroscience','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2860 Developmental Neuroscience','2860 Developmental Neuroscience','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2870 Molecular Neuroscience','2870 Molecular Neuroscience','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2880 Neurochemistry','2880 Neurochemistry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2890 Neurodegeneration','2890 Neurodegeneration','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2910 Neuropharmacology','2910 Neuropharmacology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','2920 Systems/Integrative Neuroscience','2920 Systems/Integrative Neuroscience','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3210 Molecular Pharmacology','3210 Molecular Pharmacology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3220 Pharmacodynamics','3220 Pharmacodynamics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3230 Pharmacogenetics','3230 Pharmacogenetics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3240 Toxicology','3240 Toxicology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3310 Aging','3310 Aging','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3320 Anesthesiology (basic science)','3320 Anesthesiology (basic science)','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3330 Endocrinology (basic science)','3330 Endocrinology (basic science)','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3340 Exercise Physiology (basic science)','3340 Exercise Physiology (basic science)','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3350 Integrative Biology','3350 Integrative Biology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3360 Molecular Medicine','3360 Molecular Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3370 Physiological Optics','3370 Physiological Optics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3380 Reproductive Physiology','3380 Reproductive Physiology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3610 Behavioral Communication Sciences','3610 Behavioral Communication Sciences','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3620 Behavioral Medicine (non-clinical)','3620 Behavioral Medicine (non-clinical)','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3630 Cognitive Psychology','3630 Cognitive Psychology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3640 Developmental and Child Psychology','3640 Developmental and Child Psychology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3650 Experimental and General Psychology','3650 Experimental and General Psychology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3660 Mind-Body Studies','3660 Mind-Body Studies','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3680 Neuropsychology','3680 Neuropsychology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3690 Personality and Emotion','3690 Personality and Emotion','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3710 Physiological Psychology and Psychobiology','3710 Physiological Psychology and Psychobiology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3720 Psychology of Aging','3720 Psychology of Aging','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3730 Psychometrics','3730 Psychometrics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3740 Psychophysics','3740 Psychophysics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3750 Social Psychology','3750 Social Psychology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3910 Disease Prevention and Control','3910 Disease Prevention and Control','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3920 Epidemiology','3920 Epidemiology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3930 Health Economics','3930 Health Economics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3940 Health Education','3940 Health Education','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3950 Health Policy Research','3950 Health Policy Research','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3960 Health Services Research','3960 Health Services Research','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','3970 Occupational and Environmental Health','3970 Occupational and Environmental Health','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4110 Nuclear Chemistry','4110 Nuclear Chemistry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4120 Radiation Physics','4120 Radiation Physics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4130 Radiobiology','4130 Radiobiology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4210 Anthropology','4210 Anthropology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4220 Bioethics','4220 Bioethics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4230 Demography and Population Studies','4230 Demography and Population Studies','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4240 Economics','4240 Economics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4250 Education','4250 Education','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4260 Language and Linguistics','4260 Language and Linguistics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4270 Sociology','4270 Sociology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4410 Biostatistics and/or Biometry','4410 Biostatistics and/or Biometry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4420 Bioinformatics','4420 Bioinformatics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4430 Computational Science','4430 Computational Science','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4440 Information Science','4440 Information Science','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','4450 Clinical Trials Methodology','4450 Clinical Trials Methodology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6110 Audiology','6110 Audiology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6120 Community Psychology','6120 Community Psychology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6130 Exercise Physiology (clinical)','6130 Exercise Physiology (clinical)','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6140 Medical Genetics','6140 Medical Genetics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6150 Occupational Health','6150 Occupational Health','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6160 Palliative Care','6160 Palliative Care','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6170 Physical Therapy','6170 Physical Therapy','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6180 Pharmacy','6180 Pharmacy','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6190 Social Work','6190 Social Work','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6210 Speech-language Pathology','6210 Speech-language Pathology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6211 Rehabilitation','6211 Rehabilitation','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6510 Allergy','6510 Allergy','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6520 Anesthesiology','6520 Anesthesiology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6530 Behavioral Medicine (clinical)','6530 Behavioral Medicine (clinical)','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6540 Cardiovascular Diseases','6540 Cardiovascular Diseases','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6550 Clinical Laboratory Medicine','6550 Clinical Laboratory Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6560 Clinical Nutrition','6560 Clinical Nutrition','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6570 Clinical Pharmacology','6570 Clinical Pharmacology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6580 Complementary and Alternative Medicine','6580 Complementary and Alternative Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6590 Clinical Psychology','6590 Clinical Psychology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6610 Connective Tissue Diseases','6610 Connective Tissue Diseases','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6620 Dermatology','6620 Dermatology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6630 Diabetes','6630 Diabetes','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6640 Gastroenterology','6640 Gastroenterology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6650 Endocrinology','6650 Endocrinology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6660 Immunology','6660 Immunology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6670 Gene Therapy (clinical)','6670 Gene Therapy (clinical)','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6680 Geriatrics','6680 Geriatrics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6690 Hematology','6690 Hematology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6710 HIV/AIDS','6710 HIV/AIDS','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6820 Infectious Diseases','6820 Infectious Diseases','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6830 Liver Diseases','6830 Liver Diseases','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6840 Metabolic Diseases','6840 Metabolic Diseases','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6850 Nephrology','6850 Nephrology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6860 Neurology','6860 Neurology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6870 Ophthalmology','6870 Ophthalmology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6880 Nuclear Medicine','6880 Nuclear Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6890 OB-GYN','6890 OB-GYN','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6910 Oncology','6910 Oncology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6920 Orthopedics','6920 Orthopedics','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6930 Otorhinolarynology','6930 Otorhinolarynology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6940 Preventive Medicine','6940 Preventive Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6950 Radiation, Interventional','6950 Radiation, Interventional','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6960 Pulmonary Diseases','6960 Pulmonary Diseases','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6970 Radiology, Diagnostic','6970 Radiology, Diagnostic','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6980 Rehabilitation Medicine','6980 Rehabilitation Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','6990 Psychiatry','6990 Psychiatry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','7110 Surgery','7110 Surgery','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','7120 Trauma','7120 Trauma','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','7130 Urology','7130 Urology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','7310 Pediatric Endocrinology','7310 Pediatric Endocrinology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','7320 Pediatric Hematology','7320 Pediatric Hematology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','7330 Pediatric Oncology','7330 Pediatric Oncology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','7340 Pediatric, Prematurity and Newborn','7340 Pediatric, Prematurity and Newborn','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','DBOTH: Other Double Degree Program','DBOTH: Other Double Degree Program','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','DC  : Doctor of Chiropractic','DC  : Doctor of Chiropractic','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','DDOT: Other Doctor of Medical Dentistry','DDOT: Other Doctor of Medical Dentistry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','DDS : Doctor of Dental Surgery','DDS : Doctor of Dental Surgery','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','DMD : Doctor of Medical Dentistry','DMD : Doctor of Medical Dentistry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','DNSC: Doctor of Nursing Science','DNSC: Doctor of Nursing Science','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','DO  : Doctor of Osteopathy','DO  : Doctor of Osteopathy','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','DOTH: Other Doctorate','DOTH: Other Doctorate','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','DPH : Doctor of Public Health','DPH : Doctor of Public Health','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','DRPH: Doctor of Public Health','DRPH: Doctor of Public Health','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','DSC : Doctor of Science','DSC : Doctor of Science','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','DVM : Doctor of Veterinary Medicine','DVM : Doctor of Veterinary Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','EDD : Doctor of Education','EDD : Doctor of Education','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','JD  : Doctor of Juris Prudence','JD  : Doctor of Juris Prudence','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','MA  : Master of Arts','MA  : Master of Arts','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','MB  : Foreign - Bachelor of Medicine','MB  : Foreign - Bachelor of Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','MBA : Master of Business Administration','MBA : Master of Business Administration','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','MBBS: Foreign - Bachelor of Medicine and Surgery','MBBS: Foreign - Bachelor of Medicine and Surgery','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','MD  : Doctor of Medicine','MD  : Doctor of Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','MDOT: Other Doctor of Medicine','MDOT: Other Doctor of Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','MDPHD: MD/PhD Program','MDPHD: MD/PhD Program','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','MLS : Master of Library Science','MLS : Master of Library Science','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','MOTH: Other Masters Degree','MOTH: Other Masters Degree','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','MPA : Master of Public Administration','MPA : Master of Public Administration','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','MPH : Master of Public Health','MPH : Master of Public Health','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','MS  : Master of Science','MS  : Master of Science','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','MSN : Master of Science In Nursing','MSN : Master of Science In Nursing','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','OD  : Doctor of Optometry','OD  : Doctor of Optometry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','PHD : Doctor of Philosophy','PHD : Doctor of Philosophy','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','SCD : Doctor of Science','SCD : Doctor of Science','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','VDOT: Other Doctor of Veterinary Medicine','VDOT: Other Doctor of Veterinary Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree','VMD : Doctor of Veterinary Medicine','VMD : Doctor of Veterinary Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','DC: Doctor of Chiropractic','DC: Doctor of Chiropractic','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','DDOT: Other Doctor of Medical Dentistry','DDOT: Other Doctor of Medical Dentistry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','DDS: Doctor of Dental Surgery','DDS: Doctor of Dental Surgery','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','DMD: Doctor of Medical Dentistry','DMD: Doctor of Medical Dentistry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','DNSC: Doctor of Nursing Science','DNSC: Doctor of Nursing Science','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','DO: Doctor of Osteopathy','DO: Doctor of Osteopathy','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','DOTH: Other Doctorate','DOTH: Other Doctorate','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','DPH: Doctor of Public Health','DPH: Doctor of Public Health','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','DPM: Doctor of Podiatric Medicine','DPM: Doctor of Podiatric Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','DRPH: Doctor of Public Health','DRPH: Doctor of Public Health','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','DSC: Doctor of Science','DSC: Doctor of Science','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','DSW: Doctor of Social Work','DSW: Doctor of Social Work','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','DVM: Doctor of Veterinary Medicine','DVM: Doctor of Veterinary Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','EDD: Doctor of Education','EDD: Doctor of Education','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','EGND: Foreign Doctor Engineering','EGND: Foreign Doctor Engineering','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','JD: Doctor of Juris Prudence','JD: Doctor of Juris Prudence','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','MA: Master of Arts','MA: Master of Arts','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','MB: Foreign - Bachelor of Medicine','MB: Foreign - Bachelor of Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','MBA: Master of Business Administration','MBA: Master of Business Administration','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','MBBS: Foreign - Bachelor of Medicine and Surgery','MBBS: Foreign - Bachelor of Medicine and Surgery','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','MD: Doctor of Medicine','MD: Doctor of Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','MDOT: Other Doctor of Medicine','MDOT: Other Doctor of Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','MLS: Master of Library Science','MLS: Master of Library Science','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','MOTH: Other Masters Degree','MOTH: Other Masters Degree','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','MPA: Master of Public Administration','MPA: Master of Public Administration','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','MPH: Master of Public Health','MPH: Master of Public Health','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','MS: Master of Science','MS: Master of Science','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','MSN: Master of Science In Nursing','MSN: Master of Science In Nursing','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','ND: Doctor of Naturopathy','ND: Doctor of Naturopathy','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','OD: Doctor of Optometry','OD: Doctor of Optometry','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','OTH: Other','OTH: Other','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','PHD: Doctor of Philosophy','PHD: Doctor of Philosophy','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','PHMD: Doctor of Pharmacy','PHMD: Doctor of Pharmacy','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','PSYD: Doctor of Psychology','PSYD: Doctor of Psychology','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','SCD: Doctor of Science','SCD: Doctor of Science','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','VDOT: Other Doctor of Veterinary Medicine','VDOT: Other Doctor of Veterinary Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'GraduateLevelDegree1-2','VMD: Doctor of Veterinary Medicine','VMD: Doctor of Veterinary Medicine','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'Kirschstein-NRSASupportLevel','Predoctoral','PREDOCTORAL','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'Kirschstein-NRSASupportLevel','Postdoctoral','POSTDOCTORAL','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'Kirschstein-NRSAAwardTYPE','Individual','INDIVIDUAL','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'Kirschstein-NRSAAwardTYPE','Institutional','INSTITUTIONAL','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'AcademicAppointmentPeriod','6-month','6-Month','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'AcademicAppointmentPeriod','9-month','9-Month','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'AcademicAppointmentPeriod','10-month','10-Month','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'AcademicAppointmentPeriod','12-month','12-month','admin',now(),uuid());

set @max_id := (select max(ARG_VALUE_LOOKUP_ID) + 1 from ARG_VALUE_LOOKUP);
insert into ARG_VALUE_LOOKUP (ARG_VALUE_LOOKUP_ID,ARGUMENT_NAME,VALUE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID)
    values(@max_id,'FieldOfTraining-SubCategory','Sub Category not found','Sub Category not found','admin',now(),uuid());
    
commit;
