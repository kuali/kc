# Kuali Coeus Data Conversion

This module handles complex data migrations.  These migrations may span multiple schemas or contain complex business logic.
The migrations represent a modification to KC data that is required at a certain point in time in the KC project.  Just
like normal SQL migration scripts, these migration programs only need to be executed once and must be execute at the
correct time.  These migrations can be run in dry run mode and when dry run is turned off will never commit when
encountering an error.

This program is designed to run as a standalone java application or to be integrated into a data loading process.  The
easiest way to figure how to execute this program is to run it without any arguments.  In this case, it will print out
the various options and targets.  The general idea behind this program is to do the following:

1. Place the database driver on the classpath.
2. Setup the commandline arguments to point to the kc and rice schemas.  In bundled mode this will be the same.
3. Pick any options desired such as logging level or dry run
4. Pick one of more targets
5. Execute

Note that all of the steps other than the first step happen at once on the command line.

## Supported Targets

Proposal (proposal)

This migration reads KIM information related to proposal role membership at the document-level.  These document-level
memberships are managed from the document access panel through the user interface.  The document-level membership
is removed from our existing unit based roles and moved to newly created document-level derived roles.  The membership
data will eventually be placed into the document_access table.

To look at the placement of this migration within a migration sequence see V600_085__ProposalRoleConversion in the
coeus-db-sql module.


Proposal Person Role (pprole)

This migration reads rice database parameter information related to the configuration of Proposal Person role
data.  This parameter information is eventually moved to a proposal person role table.  The parameters are then deleted
from the system as they are no longer used.  The target table for this migration is eps_prop_person_role.

To look at the placement of this migration within a migration sequence see V600_084__PropAwardPersonRoleConversion in the
coeus-db-sql module.

KRMS Question & Questionnaire Sequence (questseq)

This migration does the following:

switches the name in the krms_term_rslvr_parm_spec_t to be Question Seq ID when the name is Question ID or Question Ref ID
switches the name in the krms_term_rslvr_parm_spec_t to be Questionnaire Seq ID when the name is Questionnaire ID or Questionnaire Ref ID
switches the name in the krms_term_parm_t to be Question Seq ID when the name is Question ID or Question Ref ID and populates the val to be the proper value from the question table.
switches the name in the krms_term_parm_t to be Questionnaire Seq ID when the name is Questionnaire ID or Questionnaire Ref ID and populates the val to be the proper value from the questionnaire table.

Time & Money Document Status (tmdocstatus)

This migration supports adding a new column to the TIME_AND_MONEY_DOCUMENT table. The column should already be added via sql script, but this conversion updates that column based on the Rice KEW document header status. This ensures that the new document status column correctly reflects the status of the document for efficient queries.

Subaward Amount Info De-Duplication (subaward-amountinfo)

This migration uses some complex logic to remove duplications from the subaward amount info table. This conversion was complex enough that
it was done using Java and JDBC based conversion to avoid needing to write and test complex procedures and pl/sql. Previous to this change 
subaward copied amount infos forward to each successive subaward. This removes those items that were copied forward, leaving only the original in place. The application now reads all previous subaward amount infos for viewing and disables editing of amount infos already routed through the subaward.

This conversion, while doing the best it can, isn't 100% due to the fact that amount infos could be edited after having been previously routed. If changes have occurred to amount infos and the conversion is therefore unable to remove an expected duplicate, log messages will be printed similar to the following

```
Nov 23, 2015 1:18:03 PM org.kuali.coeus.dc.subaward.SubAwardAmountInfoDaoImpl fixSubAwardAmountInfoHistory
SEVERE: SUBAWARD-AMOUNTINFO:Cannot determine matching subaward amount info to delete. Found 0 potential matches in subaward_code(3370) subaward_id(30519) for previous subaward_amount_info_id(47970)
Nov 23, 2015 1:18:13 PM org.kuali.coeus.dc.subaward.SubAwardAmountInfoDaoImpl fixSubAwardAmountInfoHistory
SEVERE: SUBAWARD-AMOUNTINFO:Cannot determine matching subaward amount info to delete. Found 0 potential matches in subaward_code(3437) subaward_id(29785) for previous subaward_amount_info_id(49023)
Nov 23, 2015 1:18:30 PM org.kuali.coeus.dc.subaward.SubAwardAmountInfoDaoImpl fixSubAwardAmountInfoHistory
SEVERE: SUBAWARD-AMOUNTINFO:Cannot determine matching subaward amount info to delete. Found 0 potential matches in subaward_code(3617) subaward_id(29604) for previous subaward_amount_info_id(51997)
Nov 23, 2015 1:18:30 PM org.kuali.coeus.dc.subaward.SubAwardAmountInfoDaoImpl fixSubAwardAmountInfoHistory
SEVERE: SUBAWARD-AMOUNTINFO:Number of amount infos not deleted due to differences = 19
Nov 23, 2015 1:18:30 PM org.kuali.coeus.dc.subaward.SubAwardAmountInfoDaoImpl fixSubAwardAmountInfoHistory
SEVERE: SUBAWARD-AMOUNTINFO:Number of subawards with errors = 11
Nov 23, 2015 1:18:30 PM org.kuali.coeus.dc.subaward.SubAwardAmountInfoDaoImpl fixSubAwardAmountInfoHistory
SEVERE: SUBAWARD-AMOUNTINFO:Subawards in error = 3689, 3702, 3765, 3092, 3985, 2739, 3250, 3286, 3370, 3437, 3617
```

You should review these messages and cleanup duplicates within the subawards mentioned as having errors. You can also review these subawards within the app to see the potentially incorrect amounts. All records removed from the subaward_amount_info table are inserted into a subaward_amount_info_dups table. If a problem with this conversion does occur and the information needs to be restored this can be simply by executing

```
insert into subaward_amount_info select * from subaward_amount_info_dups;
```

Time And Money Award Amount Info Duplicate Removal (tm-dups)

Since August 2015 in an attempt to resolve issues related to Time And Money when pending awards exist Research was duplicating `award_amount_info` records to both the active and pending award. This has caused other issues and we have changed this behavior and have a parameter to disallow Time & Money documents when pending awards exist. This conversion uses complex logic to determine which duplicates exist and which version of the duplicates to remove. It attempts to also resolve date changes which were applied differently and therefore we copy the dates forward.

This script again does the best it can to mitigate any loss of data, but there are edge cases that might not be handled perfectly(modifying dates in the pending award separate from Time & Money transaction to be specific), and in those cases we print out log messages for what awards should specifically be reviewed for correctness after running this script. The log message should look similar too the following, providing information on what transaction_ids and award numbers should be reviewed after the running of this script.

```
Feb 09, 2016 10:24:57 AM org.kuali.coeus.dc.award.amntinfo.AwardAmountInfoDuplicatesDaoImpl fixAwardAmountInfoDuplicates
WARNING: Deleting duplicate award_amount_infos with transaction id = 60492 but it is not equal to later amount info. award_amount_info_ids(800925, 800922). 
Feb 09, 2016 10:24:57 AM org.kuali.coeus.dc.award.amntinfo.AwardAmountInfoDuplicatesDaoImpl fixAwardAmountInfoDuplicates
FINE: Deleting duplicate award_amount_info with transaction id = 60491 and award_amount_info_id = 800924
Feb 09, 2016 10:24:59 AM org.kuali.coeus.dc.award.amntinfo.AwardAmountInfoDuplicatesDaoImpl fixAwardAmountInfoDuplicates
WARNING: Deleting duplicate award_amount_info for PENDING award with transaction id = 60853 and award_amount_info_id = 808425
Feb 09, 2016 10:25:03 AM org.kuali.coeus.dc.award.amntinfo.AwardAmountInfoDuplicatesDaoImpl fixAwardAmountInfoDuplicates
INFO: Removed 238 duplicate award_amount_info transactions removed from previous award versions.
Feb 09, 2016 10:25:03 AM org.kuali.coeus.dc.award.amntinfo.AwardAmountInfoDuplicatesDaoImpl fixAwardAmountInfoDuplicates
WARNING: The following 119 transaction ids had duplicates that were not equal. These have still been removed as they were likely modified in the award after the T&M doc finalization, but confirm that these records still reflect correct amounts. 59141, 59138, 60560, 60559, 59197, 59295, 59294, 59025, 59023, 59222, 59221, 59220, 59331, 59326, 59286, 60307, 60305, 60381, 60532, 60531, 60530, 60529, 59147, 59145, 60955, 60954, 60953, 60952, 60352, 60351, 61104, 61100, 61099, 61098, 60704, 60660, 60378, 59039, 59038, 59477, 59169, 60277, 60276, 60275, 60781, 59026, 59173, 60832, 60828, 60827, 60359, 59159, 60483, 60482, 59182, 60510, 59482, 59174, 59170, 58997, 58989, 58988, 59368, 59367, 59366, 60838, 60837, 58992, 58991, 58990, 59215, 59212, 60479, 60567, 58996, 59433, 59432, 59431, 60603, 60428, 60427, 60636, 60635, 60634, 60907, 60640, 59351, 60518, 60517, 60516, 60709, 59392, 59277, 59276, 59454, 59442, 59441, 60471, 60468, 60467, 59092, 59048, 60637, 59237, 59150, 59368, 59186, 59278, 59271, 59269, 60451, 59388, 60850, 60849, 60848, 60478, 60477, 60474, 60492
Feb 09, 2016 10:25:03 AM org.kuali.coeus.dc.award.amntinfo.AwardAmountInfoDuplicatesDaoImpl fixAwardAmountInfoDuplicates
WARNING: The following 12 awards had transactions removed that were different. Verify they are still correct. (151153-00001, 160003-00001, 160018-00001, 160082-00001, 160097-00001, 160101-00001, 160260-00001, 200056-00001, 200068-00001, 200078-00001, 200115-00001, 943129-00001)
Feb 09, 2016 10:25:03 AM org.kuali.coeus.dc.award.amntinfo.AwardAmountInfoDuplicatesDaoImpl fixAwardAmountInfoDuplicates
WARNING: The following awards were affected by duplicate transactions. (200086-00002, 160101-00002, 160101-00002, 090868-00001, 090868-00001, 090868-00001, 130217-00002, 130621-00002, 130641-00002, 140480-00002, 140480-00002, 140480-00002, 200115-00001, 200115-00001, 200115-00001, 120992-00002, 120992-00002, 120992-00002, 120992-00002, 130833-00002, 200138-00001, 200138-00001, 200166-00002)
```

All `award_amount_info` removed during this process are copied into a table named `award_amount_info_dups` if data from this process needs to be reviewed or restored based on review.

Proposal YNQ -> Questionnaire Conversion (proposal-ynq)

During the Kuali Coeus 6.0 release, Proposal Development YNQs were deprecated. This conversion will take any previously answered YNQs and turn 
them into deprecated questionnaires allowing the viewing of Proposal and Personnel certification questions from these YNQs. Proposal YNQs are 
converted into a questionnaire named 'Proposal Converted YNQs' and personnel certification questionnaire is named 'Certification Converted 
YNQs'. A new version of these questionnaires are added based on the questions available and answered for each proposal so many versions of each 
questionnaire can be created. Finally a questionnaire version which disables the usage of the questionnaire ensuring that these deprecated 
questionnaires do no show up in new proposals. In the database these YNQ are stored in a copy of the original tables at 'eps_prop_ynq_bak' and 
'eps_prop_pers_ynq_bak'. 

Award Update User (award-updateuser)

In some cases, Award tables have an incorrect update user.  The incorrect user is the System User that has a username of 'kr'.  This target figures out the username of the last user to execute a workflow
action on an award document and uses that as the update user.

Time And Money Update User (tm-updateuser)

In some cases, Time and Money tables have an incorrect update user.  The incorrect user is the System User that has a username of 'kr'.  This target figures out the username of the last user to execute a workflow
action on a time and money document and uses that as the update user.

Institutional Proposal Update User (ip-updateuser)

In some cases, Institutional Proposal tables have an incorrect update user.  The incorrect user is the System User that has a username of 'kr'.  This target figures out the username of the last user to execute a workflow
action on an institutional proposal document and uses that as the update user.

Subaward Update User (subaward-updateuser)

In some cases, Subaward tables have an incorrect update user.  The incorrect user is the System User that has a username of 'kr'.  This target figures out the username of the last user to execute a workflow
action on an subaward document and uses that as the update user.


IRB (irb)

For future use

IACUC (iacuc)

For future use


