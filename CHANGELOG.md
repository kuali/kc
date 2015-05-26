

##CURRENT
* The reject button for the Award Document is missing when the document is on the initial node.
  * Travis Schneberger on Fri, 22 May 2015 08:37:33 -0400 [View Commit](../../commit/4561b722f639bedd5c7ce2c4c0087cdcd408bb7d)
* code cleanup in preparation for bugfix
  * Travis Schneberger on Fri, 22 May 2015 09:12:00 -0400 [View Commit](../../commit/19e51dda6dc904ba00be227649f1e58f55d56fc5)
* Award Budget causes an exception when adding personnel details.
  * Travis Schneberger on Fri, 22 May 2015 09:12:41 -0400 [View Commit](../../commit/4ecc447a74cdc9de91464d66e625402b6dda7a54)
* getting budget before reloading, fixing reload error.
  * Travis Schneberger on Fri, 22 May 2015 09:18:48 -0400 [View Commit](../../commit/c1c476618b1bdc3b6e3934a3ca341325808dbfc9)
* Institutional Proposal's award type not saving for an anticipated award type.
  * Travis Schneberger on Fri, 22 May 2015 11:18:59 -0400 [View Commit](../../commit/f4b4b177b8ea1ddc2267358e12e27252f3d809cb)
* display disaproved submitted protocols in meeting

  * Amendment or Renewal with or without Amendment docs that are assigned to a Full Committee Meeting Agenda appear in the Protocol Submitted panel of the meeting document but disappear from that list after the recording of Disapprove decision on the amendment doc. This is the opposite of what is required.

  * On further searching, it appears that the after taking the Disapprove action on an amendment or renewal, the entire amendment/renewal record disappears. If attached steps are followed for a renewal w/amendment, the record disappears after Disapprove. If a new renewal is created, KC will maintain sequence structure (so R002 will appear, if R001 was preceding), but R001 will not return from search results.

  * Steps:

    Login as quickstart
    On Central Admin tab click on Lookup next to Human Participants
    Select Protocol Status – Active – Open to Enrollment
    Click search
    Click edit in Actions column on result list and Protocol displays
    Click on Protocol Actions tab
    Click show on Request an Action panel
    Click show on Create Renewal with Amendment
    Enter something in Summary box and check all the Amend boxes
    Click create button
    Record the Protocol # for this amendment (1502000021R002)
    Click cancel for Notification
    Click on Protocol Actions tab
    Click show on Data Validation panel
    Click turn on validation – fix any errors and save
    Click show on Request an Action panel
    Click show on Submit for Review panel
    Submission Type Continuing Review/Continuation with Amendment, Submission Review Type Full
    Select Committee and Schedule Date (next available)
    Select one primary reviewer
    Record Schedule date
    Click submit button
    Click show on Request an Action
    Click show on Assign to Agenda
    Click submit
    Click close button for protocol
    On Central Admin tab click lookup on IRB Schedules
    Enter the Schedule Date From and Schedule Date To that matches the Full Committee Schedule Date from above.
    Committee Id – select from dropdown menu
    Click search
    Click edit on result list item
    Click show on Protocol Submitted
    Verify that the protocol Amendment for this scenario is listed as In Agenda
    Click show on Attendance
    Add 3 Voting Members as Present Voting (only if there are not at least 3 Voting Members Present)
    Click close for the Meeting document – yes for “Would you like to save meeting data before close it?”
    Lookup for Human Participants on Central Admin tab
    Enter the Renewal with Amendment doc nbr in Protocol # field and search
    Click edit on the result list item
    Click on Protocol Actions tab
    Click show on Request an Action
    Click show on Record Committee Decision
    For Motion Type select “Disapprove”
    Enter No = 0 and Yes = 3
    Show on Review Comments – type in a comment
    Click final check box for the Comment
    Click add
    Click submit
    On Disapprove panel click submit
    Verify that Submission Status and Status are Disapproved
    Click close
    Click on Central Admin tab
    Click on IRB Schedules on Central Admin tab
    Enter the Schedule Date From and Schedule Date for this amendment
    Click edit on result list item
    Click show on Protocol Submitted
    The Renewal with Amendment Protocol number for this scenario is no longer listed. Expectation is that it will still be listed as a Protocol Submitted.
  * Joe Williams on Fri, 22 May 2015 10:59:53 -0500 [View Commit](../../commit/a15a9120600dc6bd1d6300ecf97c2ecc2d56ea30)
* fix to display TBN table on add budget personnel

  * Tried to add a TBA person to a budget; but the list does not appear and the modal window does not fully generate.
  * I verified that there are 7 defined TBA persons in the maintenance document.

  * Steps to duplicate:
  * Locate an in-progress proposal or create a new one.
  * Open or create a budget version
  * On the Project Personnel screen, use the +Add Personnel button to open the modal
  * Select TBA from the 'search for' list option

  * Expected result: the list of maintained To Be Named persons should present with the ability to select how many to apply.

  * Current result: the field updates to To Be Named, but the rest of the modal window does not generate. There is only a letter-box window containing this type field. User cannot add a TBA person to the budget.
  * Joe Williams on Tue, 26 May 2015 09:50:22 -0500 [View Commit](../../commit/0d6467ccecdd72c6209a1ed2a4ce03910044d7aa)
*  Do not show cancelled award docs in T&M history
  * As an award administrator, I may need to cancel an award edit transaction instead of saving or submitting. The information may be incomplete, incorrect, or I may have selected the wrong award/node by mistake. I should be able to use the cancel button on the Actions panel to completely delete any updates or changes.

  * Steps:

  * Open existing award
  * Go to time and money and check history, note current version number and update timestamp/user
  * Return to award and click edit
  * Go to award actions and click cancel
  * Reopen award
  * Go to time and money and check history: cancelled award version will now be listed

  * Expected Result: all evidence of the cancelled document are removed from the Award, T&M, or Award Budget documents.
  * Currently, any cancelled documents are appearing as a Version in the T&M History panel.
  * Gayathri on Tue, 26 May 2015 10:41:02 -0700 [View Commit](../../commit/f84addc63df91c0afcbb4bcb4464345f469b9bb3)
* Fixes Issues With Sorting of Budget Persons
  * vineeth on Thu, 15 Jan 2015 16:49:20 -0500 [View Commit](../../commit/51fb5dc493e10c206496f3310234ac5eaae56058)
* fix compiler error related to generics, fix compiler warnings
  * Travis Schneberger on Tue, 26 May 2015 15:58:53 -0400 [View Commit](../../commit/ca0c90838bd55f4b683b9e17d6e1c7550b29e632)

##coeus-1505.61
* No Changes


##coeus-1505.60
* No Changes


##coeus-1505.59
* No Changes


##coeus-1505.58
* No Changes


##coeus-1505.57
* No Changes


##coeus-1505.56
* Clarifying documentation, fixing typos, changing recommended git version
  * Travis Schneberger on Fri, 22 May 2015 18:35:52 -0400 [View Commit](../../commit/0d9d29050adddddb3c1eeed9d08ab6c278e08eb4)

##coeus-1505.55
* Fix Java 7 compilation, code cleanup
  * Travis Schneberger on Fri, 22 May 2015 16:55:49 -0400 [View Commit](../../commit/c566ab7abe65b86773ac7725a17bf38c24b675ca)
* Fix award budget versioning issues

  * When versioning awards the budgets need to stay with the original award, but new awards need to display all budgets. Additionally the budget creation process wasn't looking at the correct list of budgets and only the current awards budgets which meant it could easily ignore previously posted budgets.
  * blackcathacker on Fri, 22 May 2015 14:36:31 -0700 [View Commit](../../commit/b6734a4345c6c7e08675860d3995a308c67bc54e)

##coeus-1505.54
* No Changes


##coeus-1505.53
* Removing validation constraints from award comments
  * Travis Schneberger on Fri, 22 May 2015 11:35:42 -0400 [View Commit](../../commit/199f9746681b9825ce1bf816d2aed7b2ef924a62)
*  fix award budget issues
  * Award budget documents seem to be attached to a particular sequence of the award and when new award sequences are created you can no longer view the budget created on an earlier sequence. Such as today account 2388937. When this happens users are also able to create new budget versions in the new award sequence regardless of the status of the earlier budget version. So now account 2388937 has two budget documents 1440820 and 1439251. Which again, shouldn't affect our work or SAP but I don't think this is how we want this to work.
  * Award budget totals when editing / versioning an award are not right either.
  * Gayathri on Fri, 22 May 2015 09:29:50 -0700 [View Commit](../../commit/0a7c381178303321d9753e3c9436ea94ae5b81c7)

##coeus-1505.52
* adds lookup fields related to s2s
  * Travis Schneberger on Thu, 21 May 2015 10:54:19 -0400 [View Commit](../../commit/6fffc2914affe29761f7298ed1d3385690dcc6a7)
* populate the anticipated award type description
  * Travis Schneberger on Thu, 21 May 2015 11:13:19 -0400 [View Commit](../../commit/aaca8247cfb21b0d965702150f74bcc7de64e504)
* filter subaward funding source with query criteria
  * Travis Schneberger on Thu, 21 May 2015 11:28:48 -0400 [View Commit](../../commit/49294778766e32bbd5bc541d654529bbd6cdf7df)
* Fixed F and A cost resetting when approve Award Budget
  * Travis Schneberger on Fri, 22 May 2015 08:30:43 -0400 [View Commit](../../commit/a2606ad24055755b7023b1e4bbfbc2265fbae335)
* Validation for eRA Commons User Name is based on sponsor group NIH

  * Currently any proposal that has a sponsor under the 'NIH Multiple PI' Sponsor Hierarchy will require the eRA Commons username for PD/PIs, and if missing it will throw the following validation: eRA Commons User Name is missing for <insert PI name>.

  * Originally we tied this validation to the 'NIH Multiple PI' sponsor hierarchy, but since schools may be using this hierarchy for all sponsors (not just NIH) we need to tie this validation to another hierarchy.
  * Joe Williams on Fri, 22 May 2015 09:02:53 -0500 [View Commit](../../commit/5fca18771194606d0554d80c096d7ba883d59ee2)
* code cleanup, minor schemaspy fixes.
  * Travis Schneberger on Fri, 22 May 2015 10:07:13 -0400 [View Commit](../../commit/e72cb2dedf7396823efc6e83f3030c1e11c930ed)

##coeus-1505.51
*  STE while printing budget summary with TBS budget person

  * While priting budget summary with a TBS person in the budget, the following STE was received
  * ERROR org.kuali.rice.krad.web.controller.UifHandlerExceptionResolver - For input string: "TBA4650"
  * java.lang.NumberFormatException: For input string: "TBA4650"
  * at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
  * at java.lang.Integer.parseInt(Integer.java:492)
  * at java.lang.Integer.valueOf(Integer.java:582)
  * at org.kuali.coeus.propdev.impl.core.DevelopmentProposal.getParentInvestigatorFlag(DevelopmentProposal.java:2131)
  * at org.kuali.coeus.common.budget.impl.print.BudgetBaseStream.getInvestigatorFlag(BudgetBaseStream.java:1928)
  * at org.kuali.coeus.common.budget.impl.print.BudgetBaseStream.getReportTypeVO(BudgetBaseStream.java:2296)
  * at org.kuali.coeus.common.budget.impl.print.BudgetBaseStream.addReportTypeVO(BudgetBaseStream.java:2244)
  * at org.kuali.coeus.common.budget.impl.print.BudgetBaseStream.getReportTypeVOList(BudgetBaseStream.java:2227)
  * at org.kuali.coeus.common.budget.impl.print.BudgetSummaryXmlStream.setReportTypeForBudgetSalarySummary(BudgetSummaryXmlStream.java:199)
  * at org.kuali.coeus.common.budget.impl.print.BudgetSummaryXmlStream.getSalarySummary(BudgetSummaryXmlStream.java:185)
  * at org.kuali.coeus.common.budget.impl.print.BudgetSummaryXmlStream.getBudgetSummary(BudgetSummaryXmlStream.java:156)
  * at org.kuali.coeus.common.budget.impl.print.BudgetSummaryXmlStream.getBudgetSummaryReportPageType(BudgetSummaryXmlStream.java:138)
  * at org.kuali.coeus.common.budget.impl.print.BudgetSummaryXmlStream.getBudgetSummaryReport(BudgetSummaryXmlStream.java:112)
  * at org.kuali.coeus.common.budget.impl.print.BudgetSummaryXmlStream.generateXmlStream(BudgetSummaryXmlStream.java:86)
  * at org.kuali.coeus.common.framework.print.AbstractPrint.renderXML(AbstractPrint.java:120)
  * at org.kuali.c
  * Gayathri on Thu, 21 May 2015 11:28:00 -0700 [View Commit](../../commit/9eebe1e17a689dd496ae1f3d17834ada7db1b2ef)
* Add Numeric Validation to BudgetLineItem Quantity
  * Joe Williams on Thu, 21 May 2015 15:56:35 -0500 [View Commit](../../commit/02c38d3ff0ce5d27c625588d8b496434c308a79b)
* Rice upgrade for security fix KULRICE-14248 and jdk8 bug RESKC-452
  * bsmith83 on Thu, 21 May 2015 16:16:45 -0700 [View Commit](../../commit/49277aa09477fec4d73531142a689b0a70f8146f)

##coeus-1505.50
* Fix to AwardComment to display correct update timestamp string
  * Joe Williams on Wed, 20 May 2015 14:53:48 -0500 [View Commit](../../commit/203598d178e7b244f4bdab368b9c7c3eaefd2746)
* fix where the negotiation counter is off by one day
  * Travis Schneberger on Thu, 21 May 2015 11:01:11 -0400 [View Commit](../../commit/569c9c8c370ca6411e56db1fac856e4e37301b3c)
* excluding amount from ip rule
  * Travis Schneberger on Thu, 21 May 2015 11:49:06 -0400 [View Commit](../../commit/980462adb249dde972e0cd002d482f276862c863)

##coeus-1505.49
*  Fixing occasional STE in award
  * This error cropts up occassionally when you try to save an award.

  * SEVERE: Servlet.service() for servlet [action] in context with path [/kc-qa-wkly] threw exception
  * java.lang.RuntimeException
	at org.kuali.rice.kns.web.struts.form.pojo.PojoFormBase.populateForProperty(PojoFormBase.java:233)
  * Caused by: java.lang.NullPointerException
	at org.kuali.kra.award.home.Award.getAwardCgb(Award.java:3548)
	... 76 more
  * Gayathri on Wed, 20 May 2015 15:19:10 -0700 [View Commit](../../commit/75294447d9bfa8db54422d8b87682832f71ab466)
*  support the oracle thin driver in schemaspy
  * Travis Schneberger on Thu, 21 May 2015 09:39:15 -0400 [View Commit](../../commit/e458426eafb623f3d04e349bfc6bf2772bb8d353)

##coeus-1505.48
* Rice upgrade for RESKC-432
  * bsmith83 on Wed, 20 May 2015 15:25:33 -0700 [View Commit](../../commit/b9e7b201a08ef08e0af544563f5cc6279a5e33e8)

##coeus-1505.47
* UnitAgenda does not apply to subunits.

  * To reproduce: Create a UnitAgenda with a unit number of some parent unit (BL-BL in demo data).  Add a proposition to call a function such as All proposals when 'true'.  Add an action such as a KC Validation Action where it displays a warning such as (Area Name: Proposal Details, Page Id: PropDev-DetailsPage, Section Id: PropDev-DetailsPage-Section).  Create a proposal with a lead unit that is above the unit in the agenda (000001 in demo data).  Add a key person that belongs to a unit in the agenda or a subunit (BL-RUGS or BL-BL in demo data).  Turn validation on.  A warning from KRMS should appear.  It does not.
  * Travis Schneberger on Tue, 19 May 2015 14:36:58 -0400 [View Commit](../../commit/1713c9255a506294259b2384c68695491dca83cf)
*  budget personnel STE
  * Steps to reproduce
  * Create a new proposal
  * Add several persons to the Key Personnel screen for budget use
  * Create a new detailed budget version
  * Define the budget persons with appointment types & salaries
  * On Assign Personnel to Period> select a person, maintain the required fields, click Save.
  * STE appears.
  * java.util.ConcurrentModificationException at java.util.Vector$Itr.checkForComodification(Vector.java:1184) at java.util.Vector$Itr.next(Vector.java:1137) at org.eclipse.persistence.indirection.IndirectList$1.next(IndirectList.java:618) at org.kuali.coeus.common.budget.impl.calculator.SalaryCalculator.filterBudgetPersons(SalaryCalculator.java:159) at
  * Gayathri on Tue, 19 May 2015 11:50:53 -0700 [View Commit](../../commit/549c441d856b0bedc5e5d63de4b17b9394fa9d42)
*  making award title required in the data dictionary to avoid a constraint violation when award title is not entered.
  * Travis Schneberger on Tue, 19 May 2015 16:13:03 -0400 [View Commit](../../commit/2851c3ff2f392e08c626d024c41e2db801cd23c9)
* Rice update for KULRICE-12991
  * bsmith83 on Tue, 19 May 2015 13:42:57 -0700 [View Commit](../../commit/0646694b70c3c8de7df65ee3e23287498a07303c)
*  Fixing printed dates and amounts
  * 1. Create a new Award, completing all fields required to save and submit the document and to create a Time & Money document, but do not submit the Award document
  * 2. Click Time & Money
  * 3. On the Transactions panel, add a transaction, such as the following:
   Source Award: External
  Destination Award: 000021-00001
  Obligated Change: 100.00
  Anticipated Change: 100.00
  * 4. Submit the Time & Money document
  * 5. Return to the Award and Submit the Award document
  * 6. Once the Award is submitted, observe the Time & Money panel on the Award tab and verify the accurate Anticipated and Obligated dates and values are displayed
  * 7. Click the Award Actions tab
  * 8. Click Show on the Print panel
  * 9. On the Award Notice row, click the Select All action button
  * 10. Click Print on the Award Notice row
  * 11. Observe the Anticipated and Obligated values and Obligated Start and End Dates on the printed Award Notice
  * +Actual Behavior+: The system does not display the Obligated Start and End Dates on the printed Award Notice for Version #1. The system displays $0.00 for all Anticipated and Obligated amounts.
  * +Expected Behavior+: The system should display the Obligated Start and End Dates on the printed Award Notice. The system should display accurate values for all Anticipated and Obligated amounts. See attached screen images.

  * 12. On the Award, click Edit
  * 13. Without making any changes, other than selecting a Transaction Type, Submit the Award
  * 14. Print and observe the Award Notice for Version 2 of the Award
  * +Actual Behavior+: The system displays the correct Obligation Start and End Dates. The system displays the correct Anticipated and Obligated amounts.

  * +Expected Behavior+: The system should display the correct dates and amounts on the Award Notice that reflect the current version of the Award.

  * 15. Edit the Time & Money document
  * 16. On the Transactions panel, add a transaction, such as the following:
  Source Award: External
  Destination Award: 000021-00001
  Obligated Change: 250.00
  Anticipated Change: 250.00
  * 17. Submit the Time & Money document
  * 18. Return to the Award
  * 19. Once the Award is submitted, observe the Time & Money panel on the Award tab and verify the accurate Anticipated and Obligated dates and values are displayed
  * 20. Click the Award Actions tab
  * 21. Click Show on the Print panel
  * 22. Click Print on the Award Notice row
  * 23. Observe the Anticipated and Obligated values and Obligated Start and End Dates on the printed Award Notice
  * +Actual Behavior+: Again, the system does not display the current Obligated Start and End Dates on the printed Award Notice. The system displays $100.00 for all Anticipated and Obligated amounts, the amounts associated with Version 1 of the Award.
  * +Expected Behavior+: The system should display the accurate Obligated Start and End Dates on the printed Award Notice for the current version of the Award, even if a new Time & Money document has been edited and submitted for the existing Award version. The system should display accurate values for all Anticipated and Obligated amounts reflecting the current version of the Award, even if a new Time & Money document has been edited and submitted for the existing Award version.
  * Gayathri on Tue, 19 May 2015 15:20:02 -0700 [View Commit](../../commit/b71459b454b890485c5a72e238ef9035bf26afe4)
* fix compilation issue on java 7 related to generics
  * Travis Schneberger on Wed, 20 May 2015 11:56:16 -0400 [View Commit](../../commit/53047746238ee7844f65271b4134fee78a00687e)

##coeus-1505.46
*  Change column length of indicator fields in IP
  * 1. Create and submit/blanket approve a Proposal Log
  * 2. Create a new Institutional Proposal, using the just-created Proposal Log
  * 3. Complete all required fields to save and submit the document
  * 4. Submit the document so that the Status = FINAL
  * 5. On the Institutional Proposal tab, click Edit
  * 6. Click the Institutional Proposal Actions tab
  * 7. Click Submit (or Blanket Approve)
  * Actual Behavior: The system does not allow the user to submit the document. The system indicates that three error messages were found on the page. The system displays three instances of the following error message below all panels: Must be at most 1 characters
  * Expected Behavior: The system should allow the user to submit the Institutional Proposal document. The system should not display any error messages. If the system displays any valid error messages, the system should indicate to the user to how fix them.
  * Gayathri on Tue, 19 May 2015 09:47:04 -0700 [View Commit](../../commit/045bc87f662b10841c8339d5b98b098a01cf9c34)
*  Fix IP creation from proposal log
  * Create a proposal log and blanket approve.
  * Click on Create IP link, search for proposal log.
  * Results display with edit, copy buttons instead of select.
  * There's no way to select a proposal log.
  * Gayathri on Mon, 18 May 2015 20:41:50 -0700 [View Commit](../../commit/0a66d55d943809e4dd6df7615f1a4a28b8754649)

##coeus-1505.45
*  fix schemaspy authz when a user does not have a session established.
  * Travis Schneberger on Tue, 19 May 2015 09:57:40 -0400 [View Commit](../../commit/e17a1c3923aecf2f43ac6d8ac43b23f9878ef943)

##coeus-1505.44
* Multiple Choice enhancement for contribution.

  * https://github.com/kuali/kc/pull/1582

  * KRAFDBCK-12521

* fix ability to submit document with multi choice
* fix ability to delete multi choice when form first opens
* now allows whitespace in prompt and description
  * Travis Schneberger on Tue, 19 May 2015 08:55:16 -0400 [View Commit](../../commit/7d197581c5c77714aa37311f0a58085b73d81c01)
* updating documentation for changes in javadoc tool and for the move to Java 8
  * Travis Schneberger on Mon, 18 May 2015 10:28:59 -0400 [View Commit](../../commit/168bb268de43b7362d6fd9df11ae0d7a13bc6b09)

##coeus-1505.43
* No Changes


##coeus-1505.42
* release
  * Travis Schneberger on Mon, 16 Mar 2015 12:51:00 -0400 [View Commit](../../commit/bb72f3def08e57a5f0b5cb18f8b6dcb4950849ed)
* releasing
  * Travis Schneberger on Mon, 16 Mar 2015 14:13:11 -0400 [View Commit](../../commit/7f64da820e133d1d0fbd3d5bc216145d58d0dc44)
* kc-sql version update
  * blackcathacker on Fri, 17 Apr 2015 21:48:12 -0700 [View Commit](../../commit/000b000c1bcb3c8ae91236e60f120b1e7863a1c4)
* added update user to data over history
  * Joe Williams on Fri, 17 Apr 2015 13:58:48 -0500 [View Commit](../../commit/2f75317d94266a9cb1054511dec9cd090a2fb36f)
* added rolodex and ynq inquiry links to organization inquiry view
  * Joe Williams on Fri, 17 Apr 2015 14:59:43 -0500 [View Commit](../../commit/a1c752970037090bb002df5fb75632b13722a169)
* Oracle scripts

  * Bring full oracle scripts renamed and numbered back into the project
  * blackcathacker on Tue, 21 Apr 2015 09:15:55 -0400 [View Commit](../../commit/14507ad004e32834d9cc4767d4186a1fceda7dc4)
* Allow sponsor code to be smaller than 6 characters

  * A customer ended up with sponsor codes shorter than 6 characters and since it was allowed by the database and a minimal functional change the sponsor code can now be any alphanumeric up to 6 characters
  * blackcathacker on Tue, 21 Apr 2015 16:02:18 -0400 [View Commit](../../commit/ed1500ec3dd9bfa11425fcbf3519f7574ed64bea)
* fix rolodex persistence through sponsor maintenance document

  * Scenario:
  * 1 - Create a new Sponsor
  * 2 - Add Sponsor and Address details
  * 3 - Submit new Sponsor
  * 4 - Search for new Sponsor and open inquiry.

  * Result:
  * When the inquiry is opened you can see that a new rolodex record has been created but there is no address book information. If you try searching for the address book record, you can only copy the record, not edit it.

  * Expected Behavior:
  * 1 - The address book entry details should be saved when the sponsor record saves.
  * 2 - The address book record should be editable from the address book lookup.
  * Joe Williams on Tue, 21 Apr 2015 18:53:04 -0400 [View Commit](../../commit/6accf7789f227682d599c533e7d3bc6a3d4d2aaf)
*  Cleaning up commit. Since we are overriding refreshNonUpdateableReferences anyway, we do not need to override method in rule base. Serialization changes also not needed it appears.
  * Gayathri on Wed, 22 Apr 2015 05:59:13 -0700 [View Commit](../../commit/abbd850c9110b1bffe6e2c11166d7f5bd596e436)
* Award Placeholder Document fixes

  * When the placeholder doc was created by creating an award and not copying a hierarchy the resulting document doesn't have a valid award. Specifically no sponsor code which was causing an exception. This checks for the placeholder doc before checking for the sponsor groups.
  * blackcathacker on Wed, 22 Apr 2015 11:33:56 -0400 [View Commit](../../commit/29773eb718669a185ef526f2919b9358ea39d16d)
* Fix update user and date on attachments in PropDev
  * blackcathacker on Thu, 16 Apr 2015 22:28:17 -0700 [View Commit](../../commit/21dbe7a52a4ec67e9db54db8b74e9bf279793d7b)
* Code review comments
  * blackcathacker on Fri, 17 Apr 2015 09:25:18 -0700 [View Commit](../../commit/49aab06b0024335363541359a2150be529de8095)
* add warning message to autocalculate periods
  * Joe Williams on Fri, 17 Apr 2015 15:32:59 -0500 [View Commit](../../commit/0e0a7e0cde1e9cbcddfbd787a02d6fc570c959c4)
* Remove duplicately versioned sql files
  * blackcathacker on Wed, 22 Apr 2015 16:43:11 -0400 [View Commit](../../commit/6c90e27d2050456d8a7a88dc59c4fc084b1e866b)
* Fix failing integration tests

  * Integration tests are failing as the controllers were the only things setting the upload information in the attachment. By setting the upload info during pre-persist we make sure the info is never null in integration tests or in the case of additional code paths
  * blackcathacker on Thu, 23 Apr 2015 09:30:24 -0400 [View Commit](../../commit/57f3a7f16a133ce76a23dd738db592748771da9e)
* HealthCheck now reports configured version and specific database status
  * blackcathacker on Thu, 16 Apr 2015 17:52:24 -0700 [View Commit](../../commit/deee751524ba0e8dcad9a8b0a61b585458826f44)
*  added correct s2s error message and fix it link for congressional district errors

  * The Fix button for Validation error on Congressional District in the Grants.gov Errors section takes you to S2S window; it should take you to Organization Tab where error can be fixed.
  * Joe Williams on Fri, 24 Apr 2015 09:40:05 -0400 [View Commit](../../commit/b3428ae736ac22b7d2d826b6de8792f5e9950ec8)
* Make all xmlstream beans prototype scope

  * XMLStream beans contain state which means they all need to be prototype scope to avoid potential clashes when server is under load
  * blackcathacker on Fri, 24 Apr 2015 10:03:02 -0400 [View Commit](../../commit/9d03addaef34a426aaa43286af951c931d61028b)
*  cleanup
  * Travis Schneberger on Wed, 15 Apr 2015 09:02:54 -0400 [View Commit](../../commit/6a44b3b4a0e1c08183ca4895dae7217c5bab704f)
*  make award date change transactions show up with a transaction id in the various history views and transaction print dropdown menus
  * Travis Schneberger on Wed, 15 Apr 2015 15:42:18 -0400 [View Commit](../../commit/3832977239e89c6d877c0e68219edf1cf77abc6e)
*  code review comments
  * Travis Schneberger on Fri, 24 Apr 2015 10:18:51 -0400 [View Commit](../../commit/de832842dafaaeacbee2d0a34c3206f2a711e401)
*  resequencing sql scripts, adding oracle script
  * Travis Schneberger on Fri, 24 Apr 2015 10:57:40 -0400 [View Commit](../../commit/3be9e0c54554a8d11f7f9ce74eb3a0daeeded7ae)
*  depending on new kualico build of jasper token auth jar
  * Travis Schneberger on Mon, 27 Apr 2015 17:26:07 -0400 [View Commit](../../commit/7d09e80ead59320026a5f93474336f94ea695953)
*  added institutional proposal rest service
  * Joe Williams on Thu, 23 Apr 2015 12:30:06 -0400 [View Commit](../../commit/a77012e0380744c91b4b96dc3ae7ad251ebccba1)
* RESKC_352:clean up proposalHierarchyServiceImpl
  * Joe Williams on Mon, 27 Apr 2015 08:18:52 -0500 [View Commit](../../commit/530d493dd6db7a5eb6a6e4304e80c941a7ab0e30)
* Fix Wrong Proposal Status after Proposal Approved at Final Stop and Submitted to Sponsor

  * Create Proposal
  * Submit for Review
  * Do approvals
  * At final stop, approve and then automatic submission to sponsor
  * Actual Result: Status is set to 'Approved Post-Submission'
  * Expected Result: Status is set to 'Approved and Submitted'
  * Joe Williams on Mon, 27 Apr 2015 15:35:26 -0500 [View Commit](../../commit/47212200a0a570fdb927c032a01a0be28ebf9c42)
* release workflow pessimistic locks after approval

  * Approvers receive the following error message after approving proposal development document:

  * This document currently has a 160-PROPOSAL DEVELOPMENT lock owned by KR
  * Joe Williams on Tue, 28 Apr 2015 08:39:16 -0500 [View Commit](../../commit/26b45e35514791bfbf5d4ea361946066b56dd864)
* set correct update user after editing an institutional proposal

  * Edit an IP
  * The Last Update field appropriately indicates the user that is updating the record while it is open.
  * Approve
  * Close
  * Reopen the IP

  * Actual result: Last update shows as performed by "kr"
  * Expected result: Last update should display user not "kr".
  * Joe Williams on Tue, 28 Apr 2015 09:01:05 -0500 [View Commit](../../commit/a533f70ea2acf7963a8a1532b2fa1bcb7819dd2b)
*  IP versioning should not copy award funding proposals over.
  * When a version of IP is linked to a version of the award, only that version needs to be linked instead of all edits of ths IP beling linked to the award. While displaying the awards linked in IP, display all the IP versions linked to awards.
  * Gayathri on Mon, 27 Apr 2015 11:28:20 -0700 [View Commit](../../commit/c96268ccb1fdc8e62bf13e0014cd8e971021a89c)
*  Moving schemaspy feature to public release.

* Moving schemaspy initialization logic and dependency info out of the grm profile
* Making schemaspy a compile-time dependency, with current version
* Adding database script with conditional insert for schemaspy auth
* Adding schemaspy instructions to readme.md
* supporting oracle in schemaspy filter and cleanup
* cleanup of poms
* fix incorrect instructions for instrumentation in readme.md
  * Travis Schneberger on Tue, 28 Apr 2015 10:57:12 -0400 [View Commit](../../commit/090381e3152e2f78597122ff0bf1d8ef16c38310)
* RESKC-361 avoiding a NullPointerException on proposal copy.

  * When an attachment exists such as a narrative or biography but the attachment does not have attachment data and the proposal attachment is copied with the copy attachments option then a NullPointerException occurs.  This scenario should not happen under normal circumstances and may indicate bad data in the system.
  * Travis Schneberger on Wed, 29 Apr 2015 15:18:45 -0400 [View Commit](../../commit/81e13a9a03d04e0e7aa131551524721ac16d239b)
* RESKC-361 avoiding a resource leak that was indicated by a OJB warning message.
  * Travis Schneberger on Wed, 29 Apr 2015 15:19:27 -0400 [View Commit](../../commit/8e50c6bbd1a73be876acadf5d8f17b0d5463495c)
* Omit questionnaires when related forms are not marked as included
  * Joe Williams on Wed, 29 Apr 2015 15:54:10 -0500 [View Commit](../../commit/d4ddf1ced13386c23d87a53b8b9c348d616c8d6c)
* Set bounds on DevelopmentProposal lookups

  * When tested originally we were able to repo a outofmemory error and the search taking a LONG time. By setting bounds on the search always the search returns rather quickly. Further work needs to be done to resolve problems when a user has limited access to proposals as none may returned as the first 20 returned don't include any they have permission on.
  * blackcathacker on Wed, 29 Apr 2015 14:11:21 -0700 [View Commit](../../commit/479f8b3c87119a205dac441f16ba6dcaa8768215)
* Fix unit test related to: Omit questionnaires when related forms are not marked as included
  * Travis Schneberger on Wed, 29 Apr 2015 19:38:44 -0400 [View Commit](../../commit/32b9392ebb5052ab2c9a79ce76dbfc2c513f5f54)
* Award: Increase system performance on structure with many child accounts
  * Travis Schneberger on Thu, 30 Apr 2015 09:16:14 -0400 [View Commit](../../commit/42e27297087a985e47107aecc2729a60fdb8420e)
* Use awardDocument updated info instead of award

  * Award Time and Money - T&M updates should not alter Award Version update timestamp and user
  * The History view should not reflect the update of the T&M change, but maintain when the award was last updated.
  * Using the awardDocument update timestamp accomplishes this.
  * blackcathacker on Wed, 29 Apr 2015 17:58:30 -0700 [View Commit](../../commit/776c97730929f9f9085e2efc9a3b5f537954dd7a)
*  Refactoring and small unit test to exercise code
  * blackcathacker on Wed, 29 Apr 2015 20:11:52 -0700 [View Commit](../../commit/8bfb47b2ba80b14cbc294503e24062e17a0fe9cc)
* Add contributing file for public contributions
  * blackcathacker on Wed, 29 Apr 2015 10:19:55 -0700 [View Commit](../../commit/dabcec5c3c1d54eec3f3852f5e4f9db92364ebe9)
* Review comment updates for CONTRIB file
  * blackcathacker on Thu, 30 Apr 2015 09:13:52 -0700 [View Commit](../../commit/f20c423660567290a9a07223675d83d31fba7419)
* Editing budget category generates STE

  * User Story: As a system administrator, I want to periodically edit budget categories, so our budget calculations are accurate

  * Steps to Reproduce:

  * 1) System Admin Portal > Maintenance > Budget Category
  * 2) Click the 'search'
  * 3) Click 'edit' next to any result
  * 4) Enter a description on the Document Overview tab and then edit the Description in the Edit Budget Category tab
  * 5) Click either 'submit' or ' blanket approve'

  * Actual results:

  * User is redirected to an Incident Report screen with the following error: Error Details: OJB operation; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'budgetCategoryTypeCode' in 'where clause'

  * Expected results:

  * Document should go directly to final and record should be updated with the users changes.

  * User acceptance criteria:

  * Given a system administrator has to edit a budget category, when the appropriate edits are made and submitted, then the budget category is updated with my changes.
  * Joe Williams on Thu, 30 Apr 2015 14:20:24 -0500 [View Commit](../../commit/e95f90171f079a8add01d288fc427a37711b5237)
* Fixed issue of showing latest version of award instead of Active in Awrd
  * heirarchy view.
  * vineeth on Thu, 2 Oct 2014 10:51:48 -0400 [View Commit](../../commit/e30394d9e7f2e5a190b59f2ed88ff8eaf9d26007)
* Negotiation Unassociated Detail Inquiry Title Change fix
  * Joe Williams on Thu, 30 Apr 2015 16:32:05 -0500 [View Commit](../../commit/dc022f5e81122533bae7b702e0fd9164e4876f21)
* https://github.com/kuali/kc/pull/1584
  * KRAFDBCK-12440

  * This enhancement adds a new parameter "IP_INTELLECTUAL_PROPERTY_REVIEW_TAB_ENABLED" to determine whether or not the Intellectual Property Review tab should be shown in IP.
  * Travis Schneberger on Thu, 30 Apr 2015 15:46:24 -0400 [View Commit](../../commit/eb2d937679106ab4bb292c825eccf4d93bf1069b)
* SubAward Status is incorporated instead of Award Status in Subaward tab
  * in Award Document.
  * anumole on Mon, 27 Oct 2014 17:33:28 +0530 [View Commit](../../commit/86a8e980638252e10984a776f98a3d1682d9dda5)
*  Do not regenerate periods if periods already exist!
  * As an award budget user, when I create a rebudget (new version of posted budget with no change to authorized amount), I expect to have all the budget details from the last posted version appear in the rebudget. I should be reducing funds from the existing line items and redistributing that amount to current or new line items, confined to matching the preexisting authorized total.
  * Current (4/29/15) release is not copying the posted budget details to the rebudget; the rebudget is blank, only letting me search for an IP to link details or manually reenter all items. This is not the intended functionality, and not consistent with prior releases.
  * Steps to recreate:
  * Kuali.com demo1 site on 4/29/2015. Award *73-00001, acct id 2533529
  * As Quickstart:
  * Locate an existing award or create a new award with T&M values maintained.
  * Create an initial Award Budget, match to auth total: Actions> submit;
  * As JTester:
  * Action list: Award budget link:Actions> approve the award budget.
  * As Quickstart:
  * Open Award Budget: Actions > post.
  * Open Award > Open Budget Versions
  * To create the rebudget (new AB without change to T&M).
  * -click 'new' to create the rebudget documument
  * result:
  * The new 'rebudget' is not populated with the details entered in the prior approved/posted version.
  * Thus, all the manually entered the details (or modified pulled in PD budget details) are lost, requiring the AB admin to do all the work over again from scratch.
  * The system should be copying the budget line item details forward to the rebudget; users should only be required to shift funds (reduce some line items to rebudget to another) to meet the authorized amount.
  * Rebudget had been working in previous tests: I have done a rebudget where I put in negative value in an existing detail line item to the add that value to another or new line item.
  * Gayathri on Thu, 30 Apr 2015 17:32:16 -0700 [View Commit](../../commit/7c23cc83246c24d0b6f59c6ed96fdfaf4e3cc9d1)
* KRMS - Rule Function 'Incomplete Narrative Rule' has logic reversed

  * The KC KRMS Function "Incomplete Narrative Rule" has logic reversed and is actually checking that narratives are complete.
  * Analogous Coeus function is "Complete Proposal Narratives.
  * Rather than reprogramming the logic of the KC function, the name should be changed to represent what the function actually does.

  * Change name of function to "Complete Narrative Rule"
  * Joe Williams on Thu, 30 Apr 2015 15:43:40 -0500 [View Commit](../../commit/3ebea27d494d8f720ce82cb1e7d8044d67dd089e)
* format currency values with commas

  * Throughout the PD Budget, money values are formatted without any commas (or dollar signs). When populated, money value fields should be formatted with commas and decimal as for currency. And especially when you get into the millions not having the commas becomes a problem.
  * Joe Williams on Thu, 30 Apr 2015 11:24:24 -0500 [View Commit](../../commit/2b6865ce25af2cfc068be4d219f34fda113b3c08)
* Sponsor Award ID label.

  * https://github.com/kuali/kc/pull/1578
  * KRAFDBCK-9468

  * Changing "Sponsor Award" label to "Sponsor Award ID" in Negotiation.
  * Travis Schneberger on Fri, 1 May 2015 14:48:22 -0400 [View Commit](../../commit/9e8c0d664922d138e3a91b41791a86e10325710b)
* Custom data escape.

  * https://github.com/kuali/kc/pull/1579

  * [KRAFDBCK-10749] Escaping custom data values so single quotes don't terminate them early.

  * This fixes being able to break out of custom data values with single quotes in the KNS custom data tag (KRAD does this already).
  * Travis Schneberger on Fri, 1 May 2015 14:54:18 -0400 [View Commit](../../commit/5fa3491f23e46113ccc743465c6c8cd152b6e080)
* Copying award results in read-only award and broken hierarchies

  * This is due to the authorization fields no being populated and only populated during docHandler. Removing this check added fixes this.
  * blackcathacker on Fri, 1 May 2015 15:28:27 -0700 [View Commit](../../commit/a57e6d07e05c851b5dd8ca94a9732c85b504a3c5)
* return user to the subaward document from subaward invoice if subaward invoice was opened from the subaward document

  * Create/open subaward document.
  * Use Add Invoice Button on the Financial tab to open an invoice document.
  * At close, submit, or blanket approve, the user is taken back to the KNS maintenance screen instead of the KRAD portal
  * Joe Williams on Mon, 4 May 2015 11:03:48 -0500 [View Commit](../../commit/7812a240f2dc29d06459dcea768f22822b7bbb21)
* fixes issues with printing s2s forms after proposal has been submitted to s2s

  * As a user, I need to be able to open a previously submitted PD and generate the PDF form files. I use this tool to verify the attachments I uploaded are the same as those in the sponsor's grant image, and to reference when I am preparing a resubmitted version of this research project. In the current release, I cannot generate the PDF forms of a submitted proposal. I get an STE.

  * Steps to reproduce:
  * Locate a submitted proposal (example res-demo1: Proposal #65, doc 5146)
  * Navigate to Basics > S2S Opportunity Search > Forms
  * OR Toolbar > Print > Grants.gov forms
  * Select one or all of the available s2s forms
  * use the Generate the PDF file tool

  * Expected result: the PDF file is generated and can be opened, viewed, saved, etc.
  * Actual result: STE appears onscreen.

  * java.lang.NullPointerException at org.kuali.coeus.propdev.impl.s2s.ProposalDevelopmentS2SController.printForms(ProposalDevelopmentS2SController.java:196) at org.kuali.coeus.propdev.impl.s2s.ProposalDevelopmentS2SController$$FastClassBySpringCGLIB$$5f5dd18.invoke(<generated>) at
  * Joe Williams on Mon, 4 May 2015 16:26:12 -0500 [View Commit](../../commit/328288555a35833ae16ac9cecbd25e8774b25bab)
* Oracle fixes
  * Our oracle installation scripts had a few issues.
  * 1. The anonymous block was assuming there was only one record in the subawards table while there could be more than one.
  * 2. Constraint was being applied in the wrong order.
  * The errors were
  * ADD CONSTRAINT FK3_BUDGET_SUB_AWARDS
                   *
  * ERROR at line 2:
  * ORA-02298: cannot validate (MG1504.FK3_BUDGET_SUB_AWARDS) - parent keys not
  * found

  * DECLARE
*
  * ERROR at line 1:
  * ORA-01422: exact fetch returns more than requested number of rows
  * ORA-06512: at line 7
  * Gayathri on Mon, 4 May 2015 13:59:37 -0700 [View Commit](../../commit/ced5293396f1b9f30c9de75d5cd212f0448782de)
* fixes STE when editing or adding sponsor templates

  * Maintenance > Awards > Sponsor Template > Create New.
  * Selected and added all required fields in each section:
  * -edit sponsor template - filled in all

    edit sponsor contacts - added a contact
    edit sponsor comments - added a comment
    -edit sponsor template reports - added a report type
    added a report recipient
    Tried to save; got STE

  * java.lang.RuntimeException: Unable to resolve collection property: class org.kuali.kra.award.home.AwardTemplate:templateContacts
  * org.kuali.rice.kns.service.impl.BusinessObjectAuthorizationServiceImpl.addMaintainableItemRestrictions(BusinessObjectAuthorizationServiceImpl.java:519)
  * Joe Williams on Tue, 5 May 2015 09:53:12 -0500 [View Commit](../../commit/1d0c82975d545371f0d51acd847a39f1148e0160)
* QuestionResolver contribution

  * https://github.com/kuali/kc/pull/1577
  * KRACOEUS-7230

  * This re-implements some changes to the QuestionResolver code that we contributed before, and which made it into KC 5.2.1 but seem to have been left out of KC 6.0. It also includes a couple of our additional improvements/fixes to the QuestionResolver functionality, such as using the module sub item key when retrieving Answer Headers, filtering retrieved Answer Headers to only include those for the latest versions of Questionnaires, and making sure the Question Seq Id comparison is properly comparing String values.
  * Travis Schneberger on Thu, 30 Apr 2015 12:08:09 -0400 [View Commit](../../commit/04e5d1096c40f9392da2b372b44fb7448ce0e776)
* Avoid NullPointerException in KRMS when a narrative does not have a narrative status.
  * Travis Schneberger on Tue, 5 May 2015 14:10:01 -0400 [View Commit](../../commit/32dd6eeb4478e1fafdf1cdabd6022d491cc1257d)
* RESKC-291 - Budget summary table ui fix for fa columns
  * bsmith83 on Tue, 5 May 2015 10:10:28 -0700 [View Commit](../../commit/dba0ab3ae66db0a31df76a8ee933668bda14ba05)
*  Accept lower case characters
  * In the S2S opportunity lookup, if user enters any lower case letters as part of the opportunity id, the system returns an error message.
  * The search field should not be case sensitive.
  * Search field is not case sensitive in 5.2.x.
  * Gayathri on Tue, 5 May 2015 10:51:09 -0700 [View Commit](../../commit/26fc0a13fc394ca5a9344d71582d9c505186521d)
* Create and route award placeholder as admin

  * This will avoid the placeholder ever being created as the user who is only trying to create a new award and avoid creating an editable Placeholder as well. This prevents the situation of placeholder award showing up in the users action list as an item to complete. This change also depends on the 'admin' user still existing and still having superuser privileges.
  * blackcathacker on Thu, 30 Apr 2015 15:09:07 -0700 [View Commit](../../commit/992884c355e41320e9b76910cb1561c7accb9b0b)
* Fix character encoding in the app.
  * Create or edit award
  * In a Word document, type in: 'single quote' "double quote" long—dash
  * Cut that from Word and past in Award Title
  * Try to save it.
  * Result:
  * "Errors found in this Section: The Award Title (Title) may only consist of visible characters, spaces, or tabs."
  * And Award title is changed to: â€˜singleâ€™ â€œdoubleâ€ longâ€”dash
  * And if the user keeps saving, the system keeps transforming the characters until the maximum allowed characters are reached and an STE occurs: org.springframework.jdbc.UncategorizedSQLException: OJB operation; uncategorized SQLException for SQL []; SQL state [72000]; error code [12899]; ORA-12899: value too large for column "KCSO"."AWARD"."TITLE" (actual: 250, maximum: 200)
  * Expected Result is that KC accepts 'single quote' "double quote" long—dash.
  * Unless there is some technical reason that KC cannot accept (or transform and accept) these characters, based on how we are handling unicode.
  * Gayathri on Tue, 5 May 2015 09:53:42 -0700 [View Commit](../../commit/dc7f4acb037a248626beaaff8aefcefb8495c1fe)
*  Allow other roles to be assigned in addition to aggregator document level role
  * There should not be a constraint of adding of other roles when aggregator is selected. These roles may be modified by implementing school so it may be necessary for Aggregator to also have any other role, ex: delete proposal.
  * Gayathri on Tue, 5 May 2015 13:01:28 -0700 [View Commit](../../commit/64fb806bb2641bc0a2b06058187671f8e28a496b)
*  Budget null pointer
  * In trying to figure out RESOPS-114, I could not open budget because of a null pointer so I added a null check in the place where I think this is coming from. Not sure if this will fix it but attempting.
  * java.lang.RuntimeException: Exception evaluating expression: #ViewHelper.getDateFromTimeStamp(budget.createTimestamp) at org.kuali.rice.krad.uif.view.DefaultExpressionEvaluator.evaluateExpression(DefaultExpressionEvaluator.java:448) at org.kuali.rice.krad.uif.view.DefaultExpressionEvaluator.evaluatePropertyExpression(DefaultExpressionEvaluator.java:514) at org.kuali.rice.krad.uif.view.DefaultExpressionEvaluator.evaluatePropertyExpressions(DefaultExpressionEvaluator.java:735) at org.kuali.rice.krad.uif.view.DefaultExpressionEvaluator.evaluateExpressionsOnConfigurable(DefaultExpressionEvaluator.java:421)
  * Caused by: java.lang.NullPointerException at org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetViewHelperServiceImpl.getDateFromTimeStamp(ProposalBudgetViewHelperServiceImpl.java:209) at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57) at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.lang.reflect.Method.invoke(Method.java:606) at org.springframework.expression.spel.support.ReflectiveMethodExecutor.execute(ReflectiveMethodExecutor.java:69) at org.springframework.expression.spel.ast.MethodReference.getValueInternal(MethodReference.java:122) at org.springframework.expression.spel.ast.MethodReference.access$000(MethodReference.java:44) at org.springframework.expression.spel.ast.MethodReference$MethodValueRef.getValue(MethodReference.java:258) at org.springframework.expression.spel.ast.CompoundExpression.getValueInternal(CompoundExpression.java:82) at org.springframework.expression.spel.ast.SpelNodeImpl.getValue(SpelNodeImpl.java:93) at org.springframework.expression.spel.standard.SpelExpression.getValue(SpelExpression.java:89) at org.kuali.rice.krad.uif.view.DefaultExpressionEvaluator.evaluateExpression(DefaultExpressionEvaluator.java:445) ... 81 more
  * Gayathri on Tue, 5 May 2015 16:39:57 -0700 [View Commit](../../commit/a020e99dd03db54a2225fe4fd1278d94718043c1)
* Sort Special Review Approval Status Alphabetically
  * Joe Williams on Tue, 5 May 2015 16:41:02 -0500 [View Commit](../../commit/e4d1de92e60910d6df9672992eb93843509d2847)
* Added View Institutionally Maintained Salaries Document Level Role
  * Joe Williams on Thu, 30 Apr 2015 15:27:12 -0500 [View Commit](../../commit/b3e54a17d2bffb04c83cad0bc8f1b5cc15ba89ea)
* validate attachment status when submitting to sponsor

  * There is a parameter for Proposal Development named AUDIT_INCOMPLETE_PROPOSAL_ATTACHMENTS that has the following description:
  * "When set to Y, proposals will be audited for incomplete attachments up to and including sponsor submission. When set to N, incomplete attachments are valid on a proposal up to but excluding sponsor submission."
  * When this flag is set to N, a proposal can be successfully routed with an incomplete attachment, but there is no validation upon submission to sponsor that the status has been set to complete. On the KualiCo QA instance running build 1505.4, I was able to submit a proposal with an incomplete attachment without any warnings or errors; see attached screenshot.
  * In the KC 1504.3 release, there appeared to be some validation logic that was intended to prevent submission with incomplete attachments (in the class org.kuali.coeus.propdev.impl.core.SubmitToSponsorRule) but it was buggy; upon submitting a proposal with an incomplete attachment, the data validation window would pop up but would contain no errors, and the proposal would go into "approved" state but no IP would be generated. So it seems that the logic in SubmitToSponsorRule was partially working, but no indication of this error was given to the user and the proposal was not prevented from entering the "approved" state.
  * In 1505.4 it seems as though this partial validation has been removed.
  * Joe Williams on Tue, 5 May 2015 15:06:35 -0500 [View Commit](../../commit/014644597889f46b2cdbdcca010dfdb53d58dc63)
* QuestionResolver contribution

  * https://github.com/kuali/kc/pull/1577
  * KRACOEUS-7230

  * Fixing conversion program
  * Travis Schneberger on Wed, 6 May 2015 14:58:59 -0400 [View Commit](../../commit/6d099d70e752bbc2e43ab3b1526ddf571a734992)
*  Fix term spec to call the right method.
  * If you build an agenda with the costShareAmount term and create a PD with budget, you cannot navigate to the submit or questionnaire pages. Following STE
  * org.kuali.rice.krms.api.engine.TermResolutionException: Unable to plan the resolution of Term([costShareAmount]) [TermResolver=null, parameters={}] at org.kuali.rice.krms.framework.engine.TermResolutionEngineImpl.resolveTerm(TermResolutionEngineImpl.java:121) at org.kuali.rice.krms.framework.engine.BasicExecutionEnvironment.resolveTerm(BasicExecutionEnvironment.java:100) at org.kuali.rice.krms.framework.engine.expression.TermExpression.invoke(TermExpression.java:46) at  org.kuali.coeus.common.impl.krms.KrmsRulesExecutionServiceImpl.runApplicableRules(KrmsRulesExecutionServiceImpl.java:134) at org.kuali.coeus.common.questionnaire.impl.answer.QuestionnaireAnswerServiceImpl.runApplicableRules(QuestionnaireAnswerServiceImpl.java:799) at org.kuali.coeus.common.questionnaire.impl.answer.QuestionnaireAnswerServiceImpl.setupChildAnswerIndicator(QuestionnaireAnswerServiceImpl.java:549) at org.kuali.coeus.common.questionnaire.impl.answer.QuestionnaireAnswerServiceImpl.getQuestionnaireAnswer(QuestionnaireAnswerServiceImpl.java:258) at org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase.populateAnswers(QuestionnaireHelperBase.java:163) at org.kuali.coeus.propdev.impl.core.ProposalDevelopmentViewHelperServiceImpl.populateQuestionnaires(ProposalDevelopmentViewHelperServiceImpl.java:620) at org.kuali.coeus.propdev.impl.core.ProposalDevelopmentViewHelperServiceImpl.prepareSummaryPage(ProposalDevelopmentViewHelperServiceImpl.java:772) at org.kuali.coeus.propdev.impl.core.ProposalDevelopmentSubmitController.navigateToSubmit(ProposalDevelopmentSubmitController.java:244)
  * Gayathri on Wed, 6 May 2015 14:37:13 -0700 [View Commit](../../commit/9e1838e75ed3a9b250c778111506e5d0e173b124)
* When opening subaward for award use edit mode

  * Link to Subaward in the Award funding should open with edit button if user has appropriate role/permissions
  * Award modifiers should be able to edit an existing subaward via the awards module by selecting subawards -> Open subaward and then an edit button. The edit button is currently missing from this page.
  * Steps to Reproduce
  * Open an Award Document that has subaward funding (ex 6703 in https://res-demo1.kuali.co)
  * go to the Subawards panel on Award Tab
  * Click on the Open Subaward link in the Subawards where this awared is a Funding source subpanel.
  * Scroll to the bottom of the subaward document and look for the edit button.
  * RESULT: Only close button appears. Even though user has role/permission to create & edit subawards, the edit button is missing.
  * EXPECTED RESULT: User with appropriate role/permission should see an edit button in the subaward document when opening it via the link from awards. This is similar to how the link from subaward to award works when it opens the award and displays the edit button on the award document.
  * blackcathacker on Wed, 6 May 2015 17:58:23 -0700 [View Commit](../../commit/3de848fa6f829f2f09373bbe57467d7ee130b349)
*  Cleaning up all the other terms
  * While working on this jira, I noticed that the method mapped for the term was wrong. While checking other terms, I found that other terms were mapped wrong as well. So fixing those.
  * Gayathri on Wed, 6 May 2015 15:25:02 -0700 [View Commit](../../commit/06439fc9933399b11c2c33756027c555080a94b2)
* Institutional Proposal with inactive sponsor or prime sponsor.

  * https://github.com/kuali/kc/pull/1581

  * [KRAFDBCK-12386] Disabled hard error on save for inactive Sponsors in IP and replaced with an audit warning.

  * This code change provides a solution to the problem of trying to edit IPs with Sponsors that have been inactivated since the IP was first approved, by replacing the hard save error with an audit warning instead.
  * Travis Schneberger on Mon, 4 May 2015 16:14:41 -0400 [View Commit](../../commit/674046f87b5f66f8b4856a519499787536947ae9)
* When and invalid prime sponsor is entered on an Institutional Proposal Document the Prime Sponsor ID field is not highlighted with an error indicator.
  * Travis Schneberger on Thu, 7 May 2015 09:30:00 -0400 [View Commit](../../commit/4f551c99fbea31a6412efca5f25b455c6f7bae2c)
* Ensure GlobalVariables is updated in IncidentReport

  * The incident report in some cases(clicking cancel button for instance) doesn't update the GlobalVariables user session. This checks for and logs incidents of this nature but also overwrites the GlobalVariables with the request user session to ensure it is up to date.
  * blackcathacker on Thu, 7 May 2015 14:35:41 -0700 [View Commit](../../commit/5cac86e2863da970a4a1db64bce4d1be6ad10c3c)
* Upgrading libraries in preparation for the move to Java 8
  * Travis Schneberger on Fri, 8 May 2015 10:33:46 -0400 [View Commit](../../commit/77db2617239fe2b95f7d24561e6050d9b67d5f10)
* Fixes autocalculate dates error with dates not exactly the same as the period

  * If user adds a budget expense for tba personnel and the dates are not the same as the budget period start and end dates, then the autocalculate does not get dates for any budget items in additional years correct.

steps to reproduce

    create a proposal with basic requirements to start make sure period is for at least two years
    add a budget
    add personnel: one tba data entry assistant and one tba programmer
    give personnel salary
    navigate to assign personnel
    add programmer object code as post-doc
    effort and charge is 100
    period type is calendar
    add the tba data entry personnel
    add data entry personnel object code as post-doc
    change dates to be three months summer (6/1-8/31 of that year)
    effort and charge is 100
    period type is summer
    save and autocalculate periods
    compare period 1 to period 2 and you will see that dates are not for the same months in each of the years.

  * Current Result - Period 2 shows the data entry person and the programmer as starting and ending on the same day when period 1 was set up to show persons starting and ending on different days.

  * Expected result - whatever information (other than amount) entered in period 1 will be the same in out years when user autocalculates, with only the year applicable changing
  * Joe Williams on Thu, 7 May 2015 09:01:38 -0500 [View Commit](../../commit/42654d78a834682b77f2042faa40154d2c40de2b)
* Upgrading libraries in preparation for the move to Java 8: Spring upgrade
  * Travis Schneberger on Mon, 11 May 2015 10:08:06 -0400 [View Commit](../../commit/2be0fe4903074768439f75c43d91ddf95923b5b3)
* Unanswered question audit errors.

  * https://github.com/kuali/kc/pull/1583
  * [KRAFDBCK-12535] Adding error messages to individual incomplete Questionnaire questions when audit mode is active and the questionnaire is mandatory.
  * Travis Schneberger on Wed, 6 May 2015 13:13:57 -0400 [View Commit](../../commit/f12527fbe308d39afd3507fc1dd39e3352c42003)
* Added the ability to view budget summary and print budget forms when proposal is view only

  * As an Approver and an Aggregator, I need to have access to the Budget Printed Reports for all status of proposals.
  * Issue: in the 1504 kuali co release; the Budget > Actions menu (where the Print menu for budget reports is located) does not appear to the user when the proposal is submitted to routing, and stays hidden when the proposal is submitted.
  * For non-s2s proposals, this leaves no method to access budget reports.

  * Scenario:
  * Create a proposal with a detailed budget that is going to an industrial or foundation sponsor.
  * Complete the required information to route the proposal.
  * Submit the proposal to routing.
  * As an APPROVER: attempt to print the Budget Summary Report from the Budget > Actions menu. There is no Action menu.
  * Joe Williams on Mon, 11 May 2015 13:57:44 -0500 [View Commit](../../commit/dcc16aba588d3843832fc0730c6bd545ec05ba19)
* added warning message when abstract detail character limit has been reached

  * In a basic proposal, add an Abstract (Layman Abstract type used for test). Use text generator to get a 50,000-character (with spaces) block of text. Copy/paste into abstract details modal (actually called Add Line). Text is truncated at 49,000 characters (with spaces) with no warning message that not all of the text has been pasted.
  * User Story:
  * As a user, I would like to have a warning message that I have reached a system limit for entering text, especially so that I do not assume my entire selection has been pasted into the limited space.
  * Something like << Data entry for this field is limited to 49,000 characters (with spaces). >> would cover both the situation of a person typing directly into the field and hitting the limit, and a user pasting a block of text from another source.
  * Joe Williams on Mon, 11 May 2015 08:25:23 -0500 [View Commit](../../commit/7a9b5711af404d8c11a9bbbb46246a5c51d263c4)
*  Creating proposal from s2s opportunity
  * Module: Proposal Development/Researcher Portal link
  * Issue: STE when clicking on Create Proposal for S2S Opportunity link
  * User Story: As a proposal creator, I want to click on the link in the portal to Create a Proposal for S2S opportunity so that I can submit via to Grants.gov
  * Given a proposal creator want to create a Proposal for S2S, when I click on the Create Proposal for S2S Opportunity link in the portal, then I open a proposal development document and begin creating proposal.
  * Expected Result: Click on link and create a proposal
  * Actual result: click on link and get error:
  * org.springframework.web.bind.UnsatisfiedServletRequestParameterException: Parameter conditions "methodToCall=preparePrintDialog" not met for actual request parameters: document.developmentProposal.proposalTypeCode={}, document.developmentProposal.ownedByUnitNumber={}, document.developmentProposal.activityTypeCode={}, document.developmentProposal.requestedStartDateInitial={}, document.developmentProposal.requestedEndDateInitial={}, document.developmentProposal.title={}, document.developmentProposal.sponsorCode={}
  * Gayathri on Mon, 11 May 2015 21:04:14 -0700 [View Commit](../../commit/be4d40f7e4eb9bedaa9d74e4679895f4bc66a83e)
* Fixed proposal copy action to keep original performance sites and other organizations

  * Copied Proposals replace the user added organization record with the Applicant Organization data.
  * Steps to Duplicate:
  * 1. Locate a proposal with an added Organization in the Basics > Organizations & Locations > Other Organizations (Example #276 in the res-i1ap.kuali.co) I added University of MD.
  * 2. Copy the Proposal (Example #351)
  * 3. Navigate to Basics > Org &Loc > Other Organizations: the org record matches the Applicant - NOT the U. MD record added by the user in the source proposal.

  * Occurs for Performance Sites as well Other Organizations. To reproduce use steps above for Performance Site Locations instead of Other Organizations
  * Joe Williams on Wed, 13 May 2015 08:57:42 -0500 [View Commit](../../commit/cb61949d861d36007865c2607b96da04dff61ddf)
* Fixes inablility to open committee
  * sanju.b on Mon, 10 Nov 2014 21:32:40 +0530 [View Commit](../../commit/2cbe4e0b22efedd2e1750cc9a162034d70bd4865)
* add close button to IACUC protocols after Submit action

  * Steps:

    Complete an IACUC protocol
    Submit for Review
    Post-processing page appears
    Do not select option to return to portal on Post-processing page
    After processing, Protocol is displayed and message appears ‘Document was successfully submitted."

  * Actual Result:
  * There is then no way to close the protocol.

  * Expected Result:
  * There should be a Close button like there is in IRB protocols. Clicking the Close button should remove any locks on the protocol and return the user to the KRAD Portal.
  * Joe Williams on Tue, 12 May 2015 12:39:57 -0500 [View Commit](../../commit/a560732a9ca958365fc7a0d14f52ad371ace275a)
* Fix modular idc currency validation to allow commas

  * Attempting to verify an RESSUP case, I created a new budget in existing proposal 332, added details, and then synced to the modular budget screen.
  * At Save, I got Errors on all 5 budget periods.
  * The F&A Base field in outlined in Red.
  * The value displayed conforms to the format. Example P1 = 192,496.73

  * With this error, I cannot save, cannot navigate of the screen, cannot return to proposal.
  * The only way I have found to save and safely exit is to delete the F&A Base value entirely for each period.

  * This is the first time I've synced a modular budget since the addition of commas to the numbers.
  * Joe Williams on Tue, 12 May 2015 14:02:46 -0500 [View Commit](../../commit/cbdcfd024fba4f7d2bc1a37aecc458339e4ca354)
* fix to display sponsor name when proposal is view only

  * The proposal opens on the Basics screen where Sponsor field displays the sponsor code but not the sponsor name (see attached screen shot).

  * Expected Result: The sponsor name needs to appear along with the sponsor code.
  * Joe Williams on Wed, 13 May 2015 09:39:54 -0500 [View Commit](../../commit/0a4242d8d3e6a0164e38236cd9a6659b0ee11417)
* scale attachment collections better with page resize

  * The new kualico ui, many of the screens redraw when in edit/add mode, putting several columns/buttons out of the window view, requiring scrolling.

  * When I add attachments, I must now use the scroll bar to find/have access to the Details/Edit/Delete options, as well as other columns (userid/timestamp).
  * If I compress the browser window, there is no compacting or wrapping of any column to aid in using a smaller screen.
  * I have to have the browser window open to at least 14.5 inches to see all the columns. If I compact the left navigation bar to icon mode, I still have to have the window at 13 inches to see all columns. (Chrome on a PC)
  * IE, I was able to see all in a 10.5 inch screen if I used icons for left nav bar.

  * Once a user gets the 10-20 narratives uploaded for an NIH submission, they will need to scroll down to get to the horizontal scroll bar, and then scroll back up to get to the Actions column to edit the attachments.
  * The white space is nice, but the row number column could be smaller, as can the Description column. Any ability to wrap text would also help if a user wanted to/had to work in a smaller window.

  * Can something be done to address the expanding window content? Like allowing these columns to text-wrap? In Add Attachments - reducing the column width of the Description field?
  * Joe Williams on Wed, 13 May 2015 13:42:45 -0500 [View Commit](../../commit/e4c0c7b45f8b50236a02e5d7d62e9e45d7f4ec92)
* Sort Special Review Type Alphabetically
  * Joe Williams on Wed, 13 May 2015 16:11:13 -0500 [View Commit](../../commit/61b40cc2d03e36f9e5ea7cbe6ba57da375908e6c)
* Fixed double headers for cache admin and proposal development documents

  * 1) login as quickstart
  * 2) switch to System Admin Tab (or maintenance tab)
  * 3) Select Action list
  * 4) Open document
  * Result: Double Headers appear
  * Joe Williams on Thu, 14 May 2015 09:55:38 -0500 [View Commit](../../commit/2fe83a31e4911a57708e86cfb1f771f75deeabd6)
* Allow modification of S2S Opportunity anytime before submit

  * Revise selections in S2S Opportunity screen pre- and post-submission
  * As a Superuser or Proposal Approver and Submitter of S2S proposals to Grants.gov, I need to be able revise selections on the S2S Opportunity screen > Opportunity, Forms, and User Attached Forms tabs in proposals that are enroute/under review as well as Approved status, because the Submit to S2S step normally takes place after approval and submit to sponsor.
  * A new permission called 'Modify Proposal Development S2s While Enroute' has been added that controls the ability for a user to make modifications after the proposal is enroute.
  * blackcathacker on Tue, 12 May 2015 20:10:52 -0700 [View Commit](../../commit/5b4625f60f7b28a9f719bb6a47951b2354e83bd0)
* when proposal is view only don't prompt user to save on close

  * User is confused if they enter a proposal in view mode and then select close that the modal asks if they want to save.

  * Steps to Reproduce
  * 1. Login as quickstart. Create proposal.
  * 2. Add user psmock as Viewer Document level.
  * 3. Note proposal number and doc number. Close. Save.
  * 4. Open incognito or private browsing window.
  * 5. Login as psmock.
  * 6. Use Search Proposal link (Researcher menu) to find proposal number
  * 7. Click ‘view’ link.
  * 8. Navigate to one of the other sections.
  * 9. Click close on that screen. Should not get the ‘do you want to save message’.

  * Results: The modal asking the user if they would like to save document displays.
  * Expected Results: Whenever proposal is in view mode It should not generate the modal asking to save when when closing.
  * Joe Williams on Thu, 14 May 2015 11:21:33 -0500 [View Commit](../../commit/c2a77a56286bf1e1b3ee79196fddf4bba8ff6a38)
* fixes STE when cancelling from add instituional proposal lookup

  * 1. Central Admin > Pre-Award > Institutional Proposal
  * 2. Click on plus sign for creating an institutional proposal
  * 3. On the Proposal Log Lookup screen click on the cancel button
  * RESULT: Stack trace error (see below)
  * EXPECTED RESULT: User should be returned to the KRAD window.

  * org.kuali.rice.krad.datadictionary.DataDictionaryException: Unable to find View with id: Kc-LandingPage-RedirectView?forInstitutionalProposal
  * at org.kuali.rice.krad.datadictionary.uif.UifDictionaryIndex.getImmutableViewById(UifDictionaryIndex.java:142)
  * at org.kuali.rice.krad.datadictionary.uif.UifDictionaryIndex.getViewById(UifDictionaryIndex.java:128)
  * Joe Williams on Thu, 14 May 2015 12:38:14 -0500 [View Commit](../../commit/9c26bbd42ab635382b1f0eacb9aa70f6755b14f9)
*  Making field readOnly so the values do not get updated while enroute
  * As a viewer of routing proposals, I navigate to review the included S2S Opportunity Forms. Upon opening this screen, all selected forms to include display as unchecked. Confirmed that the items were checked prior to routing; and remain checked if no one views them. But if the screen is opened prior to submitting to s2s, none of these optional forms & their attachments are being submitted.
  * Tried an alternate method to preview S2S forms via the Toolbar menu. At first opening, the checks were there. But when I reopened the toolbar print option in the same session - the checkmarks were gone.
  * This is a serious problem.
  * OSP Admins always verify that the users have checked the correct s2s forms to include, and print/preview some attachments as part of their review from this screen.

  * Created and submitted by user rhanlon
  * PA-C-R01 with Detailed Budget and Subaward Budget selected as optional forms.
  * Confirmed checks prior to submitting.
  * As approver RRabbit - logged in, Action List> opened proposal
  * Navigated to S2S Opportunity screen > Forms - all optional forms unchecked.
  * Gayathri on Thu, 14 May 2015 14:46:01 -0700 [View Commit](../../commit/0ea3178b4ee77c0cd5074cbc3c78b1ff90404762)
* Upgrading to Java 8: updating libraries, documentation, fixing test.
  * Travis Schneberger on Thu, 14 May 2015 21:18:16 -0400 [View Commit](../../commit/96c7e65fb0f166ead4e30826416721560f3a9854)
* Upgrading to Java 8: fixing javadoc errors from Java 8's strict javadoc tool
  * Travis Schneberger on Fri, 15 May 2015 09:27:28 -0400 [View Commit](../../commit/d681620f0f5808b553395d8b62f29af8958faa22)
* Upgrading to Java 8: upgrading jmock
  * Travis Schneberger on Fri, 15 May 2015 11:20:12 -0400 [View Commit](../../commit/280ee3568f8742ad1befe9849da8cff6fb5b2e71)
* Multiple Choice enhancement for contribution.

  * https://github.com/kuali/kc/pull/1582

  * KRAFDBCK-12521
  * Adds a Multiple Choice question type to the Questionnaire framework. Utilizes a new table to store the multiple choice prompts and descriptions, respectively. Uses the MAX_ANSWERS fields to determine whether the question renders as a set of checkboxes or radio buttons.

  * Additional bugfixes:
* Fixed question deletion causing a constraint violation where a question has an explanation.
* Fixed proper form initialization based on the Response type where the first time a question page was loaded the page would display incorrectly until the response type was changed.
* Fixed the read only display not showing the correct dynamic information based on response type.  This was common when viewing after routing.

  * Other:
  * Cleaned up questionnaire maintenance code that is no longer used as of KC 6.0
  * Added Missing license headers
  * Travis Schneberger on Wed, 13 May 2015 14:54:54 -0400 [View Commit](../../commit/9422724a92bda8496e787abc4ca93e3b95e7eb91)
* Multiple Choice enhancement for contribution.

  * https://github.com/kuali/kc/pull/1582

  * KRAFDBCK-12521

  * code review comments
  * Travis Schneberger on Fri, 15 May 2015 10:43:38 -0400 [View Commit](../../commit/8ecc43ab6555c69c0e48d785b620ba1b48ed7ee4)
* Upgrading to Java 8: switching version of cglib to use an updated/non-repackaged version of asm
  * Travis Schneberger on Fri, 15 May 2015 12:32:24 -0400 [View Commit](../../commit/b8e88dfdd7cab8052fefe36223f54f71a1cf4449)
* Upgrading to Java 8: removing MaxPerm setting since java 8 does not have perm gen space
  * Travis Schneberger on Fri, 15 May 2015 12:46:55 -0400 [View Commit](../../commit/c747ca00a8e0dd01043bc42a9b50b3d5eb3cd84c)
* return user to krad portal when closing a transactional document
  * Joe Williams on Fri, 15 May 2015 10:37:34 -0500 [View Commit](../../commit/3fb76b8f8c1a505707271e2765ee0aa0480af2ff)
* Fix to display correct linked subawards when award is versioned

  * 1. Create and submit a new Award (just complete the required fields to save and submit, no need to do a T&M document)
  * 2. Create a new Subaward, adding the just-created Award as the Funding Source, and then submit the Subaward
  * 3. Observe Medusa from both the Award and Subaward view
  * Actual Behavior: The system displays the linkage between the Award and Subaward from both Medusa views.
  * 4. Create and submit a new Proposal Log document
  * 5. Create and submit a new Institutional Proposal document, using the just-created Proposal Log
  * 6. Search for the just-submitted Award document and click the Edit action button
  * 7. Add the just-created Institutional Proposal as a Funding Proposal
  * 8. Select a Transaction Type
  * 9. Submit the edited Award
  * 10. Observe Medusa from the Award, Institutional Proposal, and Subaward views
  * Actual Behavior: The system does not display the Subaward linkage from either the Award and Institutional Proposal Medusa views. The system does display all linkages from the Subaward Medusa view (see attached screen images).
  * Expected Behavior: The system should display the linkage to the Subaward from both the Award and Institutional Proposal Medusa views.
  * 11. Create a Negotiation, selecting Award, Institutional Proposal, or Subaward for Negotiation Association Type and selecting one of the just-created documents
  * 12. Click Save to finalize the Negotiation
  * 13. Observe Medusa from the Award, Institutional Proposal, Negotiation and Subaward views
  * Actual Behavior: The system does not display the Subaward linkage from the Award, Institutional Proposal, and Negotiation Medusa views. The system does display all linkages from the Subaward Medusa view (see attached screen images).
  * Expected Behavior: The system should display the linkage to the Subaward from the Award, Institutional Proposal, and Negotiation Medusa views.
  * Joe Williams on Fri, 15 May 2015 09:39:13 -0500 [View Commit](../../commit/53db7d44ee996b0ac3408b22bceab708c77babec)
* refactoring and added tests
  * Joe Williams on Fri, 15 May 2015 09:42:08 -0500 [View Commit](../../commit/695a397d36d1a398fc1fce040b5e3aa9041b0931)
* Java 8: Upgrading Drools and MVEL to support Java 8, removing incorrect references to drools string utils
  * Travis Schneberger on Fri, 15 May 2015 15:14:13 -0400 [View Commit](../../commit/f144fab784cfbe0d3998437e52406151b53b267e)
* Java 8: Fixing tests, changing StringUtils api usage to isBlank which is closer to drool's StringUtils isEmpty method.
  * Travis Schneberger on Fri, 15 May 2015 17:02:22 -0400 [View Commit](../../commit/dc938b3641a91ee4b488fe4b7c71fd5db7e3664e)
* Java 8: Fixing drools so that rules files can be loaded from within jars.
  * Travis Schneberger on Fri, 15 May 2015 17:49:07 -0400 [View Commit](../../commit/54be5e8613aa5842803024bc326054a19b51570b)
* Java 8: making is possible to turn off java 8 specific javadoc parm
  * Travis Schneberger on Sun, 17 May 2015 21:20:56 -0400 [View Commit](../../commit/7305c54e5731b274ccf72899117af2edeaeefe36)
* Fix for award error when saving on Payment, Reports & Terms

  * -Create new award
  * -Enter required data on Award tab, save
  * -Go to Payment, Reports & Terms tab
  * -Click save, error occurs

  * This is only happening on new awards.
  * HTTP Status 500 -

  * type Exception report

  * message

  * description The server encountered an internal error that prevented it from fulfilling this request.

  * org.springframework.dao.DataIntegrityViolationException: OJB operation; SQL []; ORA-01400: cannot insert NULL into ("KCSO"."AWARD_REPORT_TRACKING"."LAST_UPDATE_USER")
  * ; nested exception is java.sql.SQLIntegrityConstraintViolationException: ORA-01400: cannot insert NULL into ("KCSO"."AWARD_REPORT_TRACKING"."LAST_UPDATE_USER")
  * Joe Williams on Mon, 18 May 2015 08:49:18 -0500 [View Commit](../../commit/fc05d34448ed466425bc5e731c57673fa6919f2c)
* return to award from award budget if budget was opened from award

  * Open an Award
  * Go to Budget Versions
  * Create or open an Award Budget
  * Perform a budget action such as approve, reject, or even close

  * Actual Results:
  * -User is taken to the Sys Admin portal.

  * Expected Results:
  * -User remains in the Award.
  * Joe Williams on Fri, 15 May 2015 14:59:20 -0500 [View Commit](../../commit/3248d256434f9643a6de6dc94b9134cd27360818)

##coeus-1505.41
* return to award from award budget if budget was opened from award

  * Open an Award
  * Go to Budget Versions
  * Create or open an Award Budget
  * Perform a budget action such as approve, reject, or even close

  * Actual Results:
  * -User is taken to the Sys Admin portal.

  * Expected Results:
  * -User remains in the Award.
  * Joe Williams on Fri, 15 May 2015 14:59:20 -0500 [View Commit](../../commit/5fe248a09e4cc55bceeb24fd7845f951d79f1e0e)

##coeus-1505.40
* No Changes


##coeus-1505.39
* Fix for award error when saving on Payment, Reports & Terms

  * -Create new award
  * -Enter required data on Award tab, save
  * -Go to Payment, Reports & Terms tab
  * -Click save, error occurs

  * This is only happening on new awards.
  * HTTP Status 500 -

  * type Exception report

  * message

  * description The server encountered an internal error that prevented it from fulfilling this request.

  * org.springframework.dao.DataIntegrityViolationException: OJB operation; SQL []; ORA-01400: cannot insert NULL into ("KCSO"."AWARD_REPORT_TRACKING"."LAST_UPDATE_USER")
  * ; nested exception is java.sql.SQLIntegrityConstraintViolationException: ORA-01400: cannot insert NULL into ("KCSO"."AWARD_REPORT_TRACKING"."LAST_UPDATE_USER")
  * Joe Williams on Mon, 18 May 2015 08:49:18 -0500 [View Commit](../../commit/aca0ce6122a5e03405598b0a13d7640e7db17046)

##coeus-1505.38
* Java 8: making is possible to turn off java 8 specific javadoc parm
  * Travis Schneberger on Sun, 17 May 2015 21:20:56 -0400 [View Commit](../../commit/9fabcc7995c78c1f995747dccdfa7950fdc2cb95)

##coeus-1505.37
* No Changes


##coeus-1505.36
* Upgrading to Java 8: updating libraries, documentation, fixing test.
  * Travis Schneberger on Thu, 14 May 2015 21:18:16 -0400 [View Commit](../../commit/b7f1a198b77ea4134c7beedbf898f92ec6378101)
* Upgrading to Java 8: fixing javadoc errors from Java 8's strict javadoc tool
  * Travis Schneberger on Fri, 15 May 2015 09:27:28 -0400 [View Commit](../../commit/09301ace83c6fe888f32ec5f90231e00c5c8d09e)
* Multiple Choice enhancement for contribution.

  * https://github.com/kuali/kc/pull/1582

  * KRAFDBCK-12521
  * Adds a Multiple Choice question type to the Questionnaire framework. Utilizes a new table to store the multiple choice prompts and descriptions, respectively. Uses the MAX_ANSWERS fields to determine whether the question renders as a set of checkboxes or radio buttons.

  * Additional bugfixes:
* Fixed question deletion causing a constraint violation where a question has an explanation.
* Fixed proper form initialization based on the Response type where the first time a question page was loaded the page would display incorrectly until the response type was changed.
* Fixed the read only display not showing the correct dynamic information based on response type.  This was common when viewing after routing.

  * Other:
  * Cleaned up questionnaire maintenance code that is no longer used as of KC 6.0
  * Added Missing license headers
  * Travis Schneberger on Wed, 13 May 2015 14:54:54 -0400 [View Commit](../../commit/39264466a27f087779f1d76b506c89c2bc3f6059)
* Fix to display correct linked subawards when award is versioned

  * 1. Create and submit a new Award (just complete the required fields to save and submit, no need to do a T&M document)
  * 2. Create a new Subaward, adding the just-created Award as the Funding Source, and then submit the Subaward
  * 3. Observe Medusa from both the Award and Subaward view
  * Actual Behavior: The system displays the linkage between the Award and Subaward from both Medusa views.
  * 4. Create and submit a new Proposal Log document
  * 5. Create and submit a new Institutional Proposal document, using the just-created Proposal Log
  * 6. Search for the just-submitted Award document and click the Edit action button
  * 7. Add the just-created Institutional Proposal as a Funding Proposal
  * 8. Select a Transaction Type
  * 9. Submit the edited Award
  * 10. Observe Medusa from the Award, Institutional Proposal, and Subaward views
  * Actual Behavior: The system does not display the Subaward linkage from either the Award and Institutional Proposal Medusa views. The system does display all linkages from the Subaward Medusa view (see attached screen images).
  * Expected Behavior: The system should display the linkage to the Subaward from both the Award and Institutional Proposal Medusa views.
  * 11. Create a Negotiation, selecting Award, Institutional Proposal, or Subaward for Negotiation Association Type and selecting one of the just-created documents
  * 12. Click Save to finalize the Negotiation
  * 13. Observe Medusa from the Award, Institutional Proposal, Negotiation and Subaward views
  * Actual Behavior: The system does not display the Subaward linkage from the Award, Institutional Proposal, and Negotiation Medusa views. The system does display all linkages from the Subaward Medusa view (see attached screen images).
  * Expected Behavior: The system should display the linkage to the Subaward from the Award, Institutional Proposal, and Negotiation Medusa views.
  * Joe Williams on Fri, 15 May 2015 09:39:13 -0500 [View Commit](../../commit/2a483666703e61eb93a8804faeb70d09499497e8)
* Multiple Choice enhancement for contribution.

  * https://github.com/kuali/kc/pull/1582

  * KRAFDBCK-12521

  * code review comments
  * Travis Schneberger on Fri, 15 May 2015 10:43:38 -0400 [View Commit](../../commit/d9690e74dfec73c6431f932aca5d9525a13820ec)
* refactoring and added tests
  * Joe Williams on Fri, 15 May 2015 09:42:08 -0500 [View Commit](../../commit/d489080c07844d78585d69e085c3b5fefe7c93cd)
* Upgrading to Java 8: upgrading jmock
  * Travis Schneberger on Fri, 15 May 2015 11:20:12 -0400 [View Commit](../../commit/2cd5e268c5ab56658c411f870d515ce7e7542925)
* Upgrading to Java 8: switching version of cglib to use an updated/non-repackaged version of asm
  * Travis Schneberger on Fri, 15 May 2015 12:32:24 -0400 [View Commit](../../commit/cb8adf607d85b67a7d71e5defd1b00e0379bc8ac)
* return user to krad portal when closing a transactional document
  * Joe Williams on Fri, 15 May 2015 10:37:34 -0500 [View Commit](../../commit/3e3c3d50c6df0e90ffb3942d3311cb3c0ec245d3)
* Upgrading to Java 8: removing MaxPerm setting since java 8 does not have perm gen space
  * Travis Schneberger on Fri, 15 May 2015 12:46:55 -0400 [View Commit](../../commit/7217b0a4096a69b468ccf40fa2597cc03add0604)
* Java 8: Upgrading Drools and MVEL to support Java 8, removing incorrect references to drools string utils
  * Travis Schneberger on Fri, 15 May 2015 15:14:13 -0400 [View Commit](../../commit/edc26dfe1b6c82769d11c051bae13c155a0dc5f7)
* Java 8: Fixing tests, changing StringUtils api usage to isBlank which is closer to drool's StringUtils isEmpty method.
  * Travis Schneberger on Fri, 15 May 2015 17:02:22 -0400 [View Commit](../../commit/f1645535c21b60a2ca05e6e65e76f050c3275c23)
* Java 8: Fixing drools so that rules files can be loaded from within jars.
  * Travis Schneberger on Fri, 15 May 2015 17:49:07 -0400 [View Commit](../../commit/82f1d2bcc43e389b31b6cab81263bb500dafdea4)

##coeus-1505.35
* No Changes


##coeus-1505.34
* Sort Special Review Type Alphabetically
  * Joe Williams on Wed, 13 May 2015 16:11:13 -0500 [View Commit](../../commit/28424fd6ca9a57554ed4cb0e90b675083e79b78c)
* Allow modification of S2S Opportunity anytime before submit

  * Revise selections in S2S Opportunity screen pre- and post-submission
  * As a Superuser or Proposal Approver and Submitter of S2S proposals to Grants.gov, I need to be able revise selections on the S2S Opportunity screen > Opportunity, Forms, and User Attached Forms tabs in proposals that are enroute/under review as well as Approved status, because the Submit to S2S step normally takes place after approval and submit to sponsor.
  * A new permission called 'Modify Proposal Development S2s While Enroute' has been added that controls the ability for a user to make modifications after the proposal is enroute.
  * blackcathacker on Tue, 12 May 2015 20:10:52 -0700 [View Commit](../../commit/b4e28cabef310cab212cc24275ba42c3da6b7e8d)
* Fixed double headers for cache admin and proposal development documents

  * 1) login as quickstart
  * 2) switch to System Admin Tab (or maintenance tab)
  * 3) Select Action list
  * 4) Open document
  * Result: Double Headers appear
  * Joe Williams on Thu, 14 May 2015 09:55:38 -0500 [View Commit](../../commit/5cfb32530579f088458d6b8bcc342aa69de4688b)
* when proposal is view only don't prompt user to save on close

  * User is confused if they enter a proposal in view mode and then select close that the modal asks if they want to save.

  * Steps to Reproduce
  * 1. Login as quickstart. Create proposal.
  * 2. Add user psmock as Viewer Document level.
  * 3. Note proposal number and doc number. Close. Save.
  * 4. Open incognito or private browsing window.
  * 5. Login as psmock.
  * 6. Use Search Proposal link (Researcher menu) to find proposal number
  * 7. Click ‘view’ link.
  * 8. Navigate to one of the other sections.
  * 9. Click close on that screen. Should not get the ‘do you want to save message’.

  * Results: The modal asking the user if they would like to save document displays.
  * Expected Results: Whenever proposal is in view mode It should not generate the modal asking to save when when closing.
  * Joe Williams on Thu, 14 May 2015 11:21:33 -0500 [View Commit](../../commit/cea4ee4ed5816eff95400775ccc41448f64330d8)
* fixes STE when cancelling from add instituional proposal lookup

  * 1. Central Admin > Pre-Award > Institutional Proposal
  * 2. Click on plus sign for creating an institutional proposal
  * 3. On the Proposal Log Lookup screen click on the cancel button
  * RESULT: Stack trace error (see below)
  * EXPECTED RESULT: User should be returned to the KRAD window.

  * org.kuali.rice.krad.datadictionary.DataDictionaryException: Unable to find View with id: Kc-LandingPage-RedirectView?forInstitutionalProposal
  * at org.kuali.rice.krad.datadictionary.uif.UifDictionaryIndex.getImmutableViewById(UifDictionaryIndex.java:142)
  * at org.kuali.rice.krad.datadictionary.uif.UifDictionaryIndex.getViewById(UifDictionaryIndex.java:128)
  * Joe Williams on Thu, 14 May 2015 12:38:14 -0500 [View Commit](../../commit/f53d8d72fe0eed583664c258863efe2cde34872b)
*  Making field readOnly so the values do not get updated while enroute
  * As a viewer of routing proposals, I navigate to review the included S2S Opportunity Forms. Upon opening this screen, all selected forms to include display as unchecked. Confirmed that the items were checked prior to routing; and remain checked if no one views them. But if the screen is opened prior to submitting to s2s, none of these optional forms & their attachments are being submitted.
  * Tried an alternate method to preview S2S forms via the Toolbar menu. At first opening, the checks were there. But when I reopened the toolbar print option in the same session - the checkmarks were gone.
  * This is a serious problem.
  * OSP Admins always verify that the users have checked the correct s2s forms to include, and print/preview some attachments as part of their review from this screen.

  * Created and submitted by user rhanlon
  * PA-C-R01 with Detailed Budget and Subaward Budget selected as optional forms.
  * Confirmed checks prior to submitting.
  * As approver RRabbit - logged in, Action List> opened proposal
  * Navigated to S2S Opportunity screen > Forms - all optional forms unchecked.
  * Gayathri on Thu, 14 May 2015 14:46:01 -0700 [View Commit](../../commit/2fb0b7106cc3a7670d181a0f8cd34469f53a721b)

##coeus-1505.33
* scale attachment collections better with page resize

  * The new kualico ui, many of the screens redraw when in edit/add mode, putting several columns/buttons out of the window view, requiring scrolling.

  * When I add attachments, I must now use the scroll bar to find/have access to the Details/Edit/Delete options, as well as other columns (userid/timestamp).
  * If I compress the browser window, there is no compacting or wrapping of any column to aid in using a smaller screen.
  * I have to have the browser window open to at least 14.5 inches to see all the columns. If I compact the left navigation bar to icon mode, I still have to have the window at 13 inches to see all columns. (Chrome on a PC)
  * IE, I was able to see all in a 10.5 inch screen if I used icons for left nav bar.

  * Once a user gets the 10-20 narratives uploaded for an NIH submission, they will need to scroll down to get to the horizontal scroll bar, and then scroll back up to get to the Actions column to edit the attachments.
  * The white space is nice, but the row number column could be smaller, as can the Description column. Any ability to wrap text would also help if a user wanted to/had to work in a smaller window.

  * Can something be done to address the expanding window content? Like allowing these columns to text-wrap? In Add Attachments - reducing the column width of the Description field?
  * Joe Williams on Wed, 13 May 2015 13:42:45 -0500 [View Commit](../../commit/4c2a6a84f58b000a37895bf9d5cf1982d09566a4)

##coeus-1505.32
* No Changes


##coeus-1505.31
* No Changes


##coeus-1505.30
* add close button to IACUC protocols after Submit action

  * Steps:

    Complete an IACUC protocol
    Submit for Review
    Post-processing page appears
    Do not select option to return to portal on Post-processing page
    After processing, Protocol is displayed and message appears ‘Document was successfully submitted."

  * Actual Result:
  * There is then no way to close the protocol.

  * Expected Result:
  * There should be a Close button like there is in IRB protocols. Clicking the Close button should remove any locks on the protocol and return the user to the KRAD Portal.
  * Joe Williams on Tue, 12 May 2015 12:39:57 -0500 [View Commit](../../commit/46d635687f3609d414fed881453d6afe30a0c2c3)
* Fix modular idc currency validation to allow commas

  * Attempting to verify an RESSUP case, I created a new budget in existing proposal 332, added details, and then synced to the modular budget screen.
  * At Save, I got Errors on all 5 budget periods.
  * The F&A Base field in outlined in Red.
  * The value displayed conforms to the format. Example P1 = 192,496.73

  * With this error, I cannot save, cannot navigate of the screen, cannot return to proposal.
  * The only way I have found to save and safely exit is to delete the F&A Base value entirely for each period.

  * This is the first time I've synced a modular budget since the addition of commas to the numbers.
  * Joe Williams on Tue, 12 May 2015 14:02:46 -0500 [View Commit](../../commit/42e085fefb89598f7fadf0ccef0adab133eb8445)
* Fixed proposal copy action to keep original performance sites and other organizations

  * Copied Proposals replace the user added organization record with the Applicant Organization data.
  * Steps to Duplicate:
  * 1. Locate a proposal with an added Organization in the Basics > Organizations & Locations > Other Organizations (Example #276 in the res-i1ap.kuali.co) I added University of MD.
  * 2. Copy the Proposal (Example #351)
  * 3. Navigate to Basics > Org &Loc > Other Organizations: the org record matches the Applicant - NOT the U. MD record added by the user in the source proposal.

  * Occurs for Performance Sites as well Other Organizations. To reproduce use steps above for Performance Site Locations instead of Other Organizations
  * Joe Williams on Wed, 13 May 2015 08:57:42 -0500 [View Commit](../../commit/3eb72b26048625fac9a3eb97f17975b16317792a)
* Fixes inablility to open committee
  * sanju.b on Mon, 10 Nov 2014 21:32:40 +0530 [View Commit](../../commit/2940fb7ce4a57e93426b4ebb1151b20f580246c3)
* fix to display sponsor name when proposal is view only

  * The proposal opens on the Basics screen where Sponsor field displays the sponsor code but not the sponsor name (see attached screen shot).

  * Expected Result: The sponsor name needs to appear along with the sponsor code.
  * Joe Williams on Wed, 13 May 2015 09:39:54 -0500 [View Commit](../../commit/c4c21ada04c0b92cc1d48bb309fc4f437bcc5669)

##coeus-1505.29
*  Creating proposal from s2s opportunity
  * Module: Proposal Development/Researcher Portal link
  * Issue: STE when clicking on Create Proposal for S2S Opportunity link
  * User Story: As a proposal creator, I want to click on the link in the portal to Create a Proposal for S2S opportunity so that I can submit via to Grants.gov
  * Given a proposal creator want to create a Proposal for S2S, when I click on the Create Proposal for S2S Opportunity link in the portal, then I open a proposal development document and begin creating proposal.
  * Expected Result: Click on link and create a proposal
  * Actual result: click on link and get error:
  * org.springframework.web.bind.UnsatisfiedServletRequestParameterException: Parameter conditions "methodToCall=preparePrintDialog" not met for actual request parameters: document.developmentProposal.proposalTypeCode={}, document.developmentProposal.ownedByUnitNumber={}, document.developmentProposal.activityTypeCode={}, document.developmentProposal.requestedStartDateInitial={}, document.developmentProposal.requestedEndDateInitial={}, document.developmentProposal.title={}, document.developmentProposal.sponsorCode={}
  * Gayathri on Mon, 11 May 2015 21:04:14 -0700 [View Commit](../../commit/9a9266531eac4e8ccdd16cff9250cc7a2d6326df)

##coeus-1505.28
* added warning message when abstract detail character limit has been reached

  * In a basic proposal, add an Abstract (Layman Abstract type used for test). Use text generator to get a 50,000-character (with spaces) block of text. Copy/paste into abstract details modal (actually called Add Line). Text is truncated at 49,000 characters (with spaces) with no warning message that not all of the text has been pasted.
  * User Story:
  * As a user, I would like to have a warning message that I have reached a system limit for entering text, especially so that I do not assume my entire selection has been pasted into the limited space.
  * Something like << Data entry for this field is limited to 49,000 characters (with spaces). >> would cover both the situation of a person typing directly into the field and hitting the limit, and a user pasting a block of text from another source.
  * Joe Williams on Mon, 11 May 2015 08:25:23 -0500 [View Commit](../../commit/1e81f10f668f432f5875d10ca6751b93360f2f49)

##coeus-1505.27
* No Changes


##coeus-1505.26
* Added the ability to view budget summary and print budget forms when proposal is view only

  * As an Approver and an Aggregator, I need to have access to the Budget Printed Reports for all status of proposals.
  * Issue: in the 1504 kuali co release; the Budget > Actions menu (where the Print menu for budget reports is located) does not appear to the user when the proposal is submitted to routing, and stays hidden when the proposal is submitted.
  * For non-s2s proposals, this leaves no method to access budget reports.

  * Scenario:
  * Create a proposal with a detailed budget that is going to an industrial or foundation sponsor.
  * Complete the required information to route the proposal.
  * Submit the proposal to routing.
  * As an APPROVER: attempt to print the Budget Summary Report from the Budget > Actions menu. There is no Action menu.
  * Joe Williams on Mon, 11 May 2015 13:57:44 -0500 [View Commit](../../commit/f0ee8d85e649106fdcc285b117b096e07d11fc00)

##coeus-1505.25
* Unanswered question audit errors.

  * https://github.com/kuali/kc/pull/1583
  * [KRAFDBCK-12535] Adding error messages to individual incomplete Questionnaire questions when audit mode is active and the questionnaire is mandatory.
  * Travis Schneberger on Wed, 6 May 2015 13:13:57 -0400 [View Commit](../../commit/fb144a4af7ebcbf8d83964ec3b345cb5d1776e07)

##coeus-1505.24
* Upgrading libraries in preparation for the move to Java 8: Spring upgrade
  * Travis Schneberger on Mon, 11 May 2015 10:08:06 -0400 [View Commit](../../commit/b98b9183f9bd0c5f4a754878bc2845b5acc41131)

##coeus-1505.23
* No Changes


##coeus-1505.22
* Fixes autocalculate dates error with dates not exactly the same as the period

  * If user adds a budget expense for tba personnel and the dates are not the same as the budget period start and end dates, then the autocalculate does not get dates for any budget items in additional years correct.

steps to reproduce

    create a proposal with basic requirements to start make sure period is for at least two years
    add a budget
    add personnel: one tba data entry assistant and one tba programmer
    give personnel salary
    navigate to assign personnel
    add programmer object code as post-doc
    effort and charge is 100
    period type is calendar
    add the tba data entry personnel
    add data entry personnel object code as post-doc
    change dates to be three months summer (6/1-8/31 of that year)
    effort and charge is 100
    period type is summer
    save and autocalculate periods
    compare period 1 to period 2 and you will see that dates are not for the same months in each of the years.

  * Current Result - Period 2 shows the data entry person and the programmer as starting and ending on the same day when period 1 was set up to show persons starting and ending on different days.

  * Expected result - whatever information (other than amount) entered in period 1 will be the same in out years when user autocalculates, with only the year applicable changing
  * Joe Williams on Thu, 7 May 2015 09:01:38 -0500 [View Commit](../../commit/f233b94bfc5a0515a308bc90ef0c17ed131938de)
* Upgrading libraries in preparation for the move to Java 8
  * Travis Schneberger on Fri, 8 May 2015 10:33:46 -0400 [View Commit](../../commit/e077f0d99b1538aa7f56cd2f26253a588c87183d)

##coeus-1505.21
* No Changes


##coeus-1505.20
* Ensure GlobalVariables is updated in IncidentReport

  * The incident report in some cases(clicking cancel button for instance) doesn't update the GlobalVariables user session. This checks for and logs incidents of this nature but also overwrites the GlobalVariables with the request user session to ensure it is up to date.
  * blackcathacker on Thu, 7 May 2015 14:35:41 -0700 [View Commit](../../commit/c07238ae057f362a1ee301db6abb0b03fe822586)

##coeus-1505.19
* Institutional Proposal with inactive sponsor or prime sponsor.

  * https://github.com/kuali/kc/pull/1581

  * [KRAFDBCK-12386] Disabled hard error on save for inactive Sponsors in IP and replaced with an audit warning.

  * This code change provides a solution to the problem of trying to edit IPs with Sponsors that have been inactivated since the IP was first approved, by replacing the hard save error with an audit warning instead.
  * Travis Schneberger on Mon, 4 May 2015 16:14:41 -0400 [View Commit](../../commit/f9204cfdc0c565c21f85ade541870f90873269c0)
* When and invalid prime sponsor is entered on an Institutional Proposal Document the Prime Sponsor ID field is not highlighted with an error indicator.
  * Travis Schneberger on Thu, 7 May 2015 09:30:00 -0400 [View Commit](../../commit/f048b27c305a1164ac751e5e308e3793b50bfb70)

##coeus-1505.18
*  Cleaning up all the other terms
  * While working on this jira, I noticed that the method mapped for the term was wrong. While checking other terms, I found that other terms were mapped wrong as well. So fixing those.
  * Gayathri on Wed, 6 May 2015 15:25:02 -0700 [View Commit](../../commit/a8fd87a4556c0b3d44399effcc160b7cf8811a00)

##coeus-1505.17
* When opening subaward for award use edit mode

  * Link to Subaward in the Award funding should open with edit button if user has appropriate role/permissions
  * Award modifiers should be able to edit an existing subaward via the awards module by selecting subawards -> Open subaward and then an edit button. The edit button is currently missing from this page.
  * Steps to Reproduce
  * Open an Award Document that has subaward funding (ex 6703 in https://res-demo1.kuali.co)
  * go to the Subawards panel on Award Tab
  * Click on the Open Subaward link in the Subawards where this awared is a Funding source subpanel.
  * Scroll to the bottom of the subaward document and look for the edit button.
  * RESULT: Only close button appears. Even though user has role/permission to create & edit subawards, the edit button is missing.
  * EXPECTED RESULT: User with appropriate role/permission should see an edit button in the subaward document when opening it via the link from awards. This is similar to how the link from subaward to award works when it opens the award and displays the edit button on the award document.
  * blackcathacker on Wed, 6 May 2015 17:58:23 -0700 [View Commit](../../commit/310ee38bc3b3b082e18658eb41553bfef8b49cba)

##coeus-1505.16
*  Fix term spec to call the right method.
  * If you build an agenda with the costShareAmount term and create a PD with budget, you cannot navigate to the submit or questionnaire pages. Following STE
  * org.kuali.rice.krms.api.engine.TermResolutionException: Unable to plan the resolution of Term([costShareAmount]) [TermResolver=null, parameters={}] at org.kuali.rice.krms.framework.engine.TermResolutionEngineImpl.resolveTerm(TermResolutionEngineImpl.java:121) at org.kuali.rice.krms.framework.engine.BasicExecutionEnvironment.resolveTerm(BasicExecutionEnvironment.java:100) at org.kuali.rice.krms.framework.engine.expression.TermExpression.invoke(TermExpression.java:46) at  org.kuali.coeus.common.impl.krms.KrmsRulesExecutionServiceImpl.runApplicableRules(KrmsRulesExecutionServiceImpl.java:134) at org.kuali.coeus.common.questionnaire.impl.answer.QuestionnaireAnswerServiceImpl.runApplicableRules(QuestionnaireAnswerServiceImpl.java:799) at org.kuali.coeus.common.questionnaire.impl.answer.QuestionnaireAnswerServiceImpl.setupChildAnswerIndicator(QuestionnaireAnswerServiceImpl.java:549) at org.kuali.coeus.common.questionnaire.impl.answer.QuestionnaireAnswerServiceImpl.getQuestionnaireAnswer(QuestionnaireAnswerServiceImpl.java:258) at org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase.populateAnswers(QuestionnaireHelperBase.java:163) at org.kuali.coeus.propdev.impl.core.ProposalDevelopmentViewHelperServiceImpl.populateQuestionnaires(ProposalDevelopmentViewHelperServiceImpl.java:620) at org.kuali.coeus.propdev.impl.core.ProposalDevelopmentViewHelperServiceImpl.prepareSummaryPage(ProposalDevelopmentViewHelperServiceImpl.java:772) at org.kuali.coeus.propdev.impl.core.ProposalDevelopmentSubmitController.navigateToSubmit(ProposalDevelopmentSubmitController.java:244)
  * Gayathri on Wed, 6 May 2015 14:37:13 -0700 [View Commit](../../commit/4990388e6b19fecbd00199b3fc777ff8a398b405)

##coeus-1505.15
* QuestionResolver contribution

  * https://github.com/kuali/kc/pull/1577
  * KRACOEUS-7230

  * Fixing conversion program
  * Travis Schneberger on Wed, 6 May 2015 14:58:59 -0400 [View Commit](../../commit/f84886cc19d3068f3903be077ab96847ef2ac1d5)

##coeus-1505.14
* Sort Special Review Approval Status Alphabetically
  * Joe Williams on Tue, 5 May 2015 16:41:02 -0500 [View Commit](../../commit/d514c3fa7b68e6b029917f521b2d512f169663b0)
* Added View Institutionally Maintained Salaries Document Level Role
  * Joe Williams on Thu, 30 Apr 2015 15:27:12 -0500 [View Commit](../../commit/70429e355b58b94d74a2fae52eb172df1560ba2f)
* validate attachment status when submitting to sponsor

  * There is a parameter for Proposal Development named AUDIT_INCOMPLETE_PROPOSAL_ATTACHMENTS that has the following description:
  * "When set to Y, proposals will be audited for incomplete attachments up to and including sponsor submission. When set to N, incomplete attachments are valid on a proposal up to but excluding sponsor submission."
  * When this flag is set to N, a proposal can be successfully routed with an incomplete attachment, but there is no validation upon submission to sponsor that the status has been set to complete. On the KualiCo QA instance running build 1505.4, I was able to submit a proposal with an incomplete attachment without any warnings or errors; see attached screenshot.
  * In the KC 1504.3 release, there appeared to be some validation logic that was intended to prevent submission with incomplete attachments (in the class org.kuali.coeus.propdev.impl.core.SubmitToSponsorRule) but it was buggy; upon submitting a proposal with an incomplete attachment, the data validation window would pop up but would contain no errors, and the proposal would go into "approved" state but no IP would be generated. So it seems that the logic in SubmitToSponsorRule was partially working, but no indication of this error was given to the user and the proposal was not prevented from entering the "approved" state.
  * In 1505.4 it seems as though this partial validation has been removed.
  * Joe Williams on Tue, 5 May 2015 15:06:35 -0500 [View Commit](../../commit/9a968d1c2ffad96304f50d3b4fa4d71ff7efb705)

##coeus-1505.13
*  Budget null pointer
  * In trying to figure out RESOPS-114, I could not open budget because of a null pointer so I added a null check in the place where I think this is coming from. Not sure if this will fix it but attempting.
  * java.lang.RuntimeException: Exception evaluating expression: #ViewHelper.getDateFromTimeStamp(budget.createTimestamp) at org.kuali.rice.krad.uif.view.DefaultExpressionEvaluator.evaluateExpression(DefaultExpressionEvaluator.java:448) at org.kuali.rice.krad.uif.view.DefaultExpressionEvaluator.evaluatePropertyExpression(DefaultExpressionEvaluator.java:514) at org.kuali.rice.krad.uif.view.DefaultExpressionEvaluator.evaluatePropertyExpressions(DefaultExpressionEvaluator.java:735) at org.kuali.rice.krad.uif.view.DefaultExpressionEvaluator.evaluateExpressionsOnConfigurable(DefaultExpressionEvaluator.java:421)
  * Caused by: java.lang.NullPointerException at org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetViewHelperServiceImpl.getDateFromTimeStamp(ProposalBudgetViewHelperServiceImpl.java:209) at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57) at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.lang.reflect.Method.invoke(Method.java:606) at org.springframework.expression.spel.support.ReflectiveMethodExecutor.execute(ReflectiveMethodExecutor.java:69) at org.springframework.expression.spel.ast.MethodReference.getValueInternal(MethodReference.java:122) at org.springframework.expression.spel.ast.MethodReference.access$000(MethodReference.java:44) at org.springframework.expression.spel.ast.MethodReference$MethodValueRef.getValue(MethodReference.java:258) at org.springframework.expression.spel.ast.CompoundExpression.getValueInternal(CompoundExpression.java:82) at org.springframework.expression.spel.ast.SpelNodeImpl.getValue(SpelNodeImpl.java:93) at org.springframework.expression.spel.standard.SpelExpression.getValue(SpelExpression.java:89) at org.kuali.rice.krad.uif.view.DefaultExpressionEvaluator.evaluateExpression(DefaultExpressionEvaluator.java:445) ... 81 more
  * Gayathri on Tue, 5 May 2015 16:39:57 -0700 [View Commit](../../commit/c5e10d16d8f151b88c137a77fb54beff47fe6777)

##coeus-1505.12
* No Changes


##coeus-1505.11
*  Allow other roles to be assigned in addition to aggregator document level role
  * There should not be a constraint of adding of other roles when aggregator is selected. These roles may be modified by implementing school so it may be necessary for Aggregator to also have any other role, ex: delete proposal.
  * Gayathri on Tue, 5 May 2015 13:01:28 -0700 [View Commit](../../commit/fd979aa5554c8b42f2fde9412258c2217eb65ca6)

##coeus-1505.10
* Create and route award placeholder as admin

  * This will avoid the placeholder ever being created as the user who is only trying to create a new award and avoid creating an editable Placeholder as well. This prevents the situation of placeholder award showing up in the users action list as an item to complete. This change also depends on the 'admin' user still existing and still having superuser privileges.
  * blackcathacker on Thu, 30 Apr 2015 15:09:07 -0700 [View Commit](../../commit/71b4839cad80c6af75da7987ee5b809b1e474c67)
* RESKC-291 - Budget summary table ui fix for fa columns
  * bsmith83 on Tue, 5 May 2015 10:10:28 -0700 [View Commit](../../commit/16be4d6e78b2d02ba4225f913cf1e925420eb7c4)
*  Accept lower case characters
  * In the S2S opportunity lookup, if user enters any lower case letters as part of the opportunity id, the system returns an error message.
  * The search field should not be case sensitive.
  * Search field is not case sensitive in 5.2.x.
  * Gayathri on Tue, 5 May 2015 10:51:09 -0700 [View Commit](../../commit/23d7d0490af7dddb180a1dc18e9e70cf5595490a)
* Avoid NullPointerException in KRMS when a narrative does not have a narrative status.
  * Travis Schneberger on Tue, 5 May 2015 14:10:01 -0400 [View Commit](../../commit/85e1c3fb8d3d566c0f1ba92759cb9d5a17966572)
* Fix character encoding in the app.
  * Create or edit award
  * In a Word document, type in: 'single quote' "double quote" long—dash
  * Cut that from Word and past in Award Title
  * Try to save it.
  * Result:
  * "Errors found in this Section: The Award Title (Title) may only consist of visible characters, spaces, or tabs."
  * And Award title is changed to: â€˜singleâ€™ â€œdoubleâ€ longâ€”dash
  * And if the user keeps saving, the system keeps transforming the characters until the maximum allowed characters are reached and an STE occurs: org.springframework.jdbc.UncategorizedSQLException: OJB operation; uncategorized SQLException for SQL []; SQL state [72000]; error code [12899]; ORA-12899: value too large for column "KCSO"."AWARD"."TITLE" (actual: 250, maximum: 200)
  * Expected Result is that KC accepts 'single quote' "double quote" long—dash.
  * Unless there is some technical reason that KC cannot accept (or transform and accept) these characters, based on how we are handling unicode.
  * Gayathri on Tue, 5 May 2015 09:53:42 -0700 [View Commit](../../commit/feb6e0869f5dd368b6f2067b53f7e355757c47a4)

##coeus-1505.9
* fixes STE when editing or adding sponsor templates

  * Maintenance > Awards > Sponsor Template > Create New.
  * Selected and added all required fields in each section:
  * -edit sponsor template - filled in all

    edit sponsor contacts - added a contact
    edit sponsor comments - added a comment
    -edit sponsor template reports - added a report type
    added a report recipient
    Tried to save; got STE

  * java.lang.RuntimeException: Unable to resolve collection property: class org.kuali.kra.award.home.AwardTemplate:templateContacts
  * org.kuali.rice.kns.service.impl.BusinessObjectAuthorizationServiceImpl.addMaintainableItemRestrictions(BusinessObjectAuthorizationServiceImpl.java:519)
  * Joe Williams on Tue, 5 May 2015 09:53:12 -0500 [View Commit](../../commit/80823d2cd8b55530839cc34c2aad91b74c3677bd)
* QuestionResolver contribution

  * https://github.com/kuali/kc/pull/1577
  * KRACOEUS-7230

  * This re-implements some changes to the QuestionResolver code that we contributed before, and which made it into KC 5.2.1 but seem to have been left out of KC 6.0. It also includes a couple of our additional improvements/fixes to the QuestionResolver functionality, such as using the module sub item key when retrieving Answer Headers, filtering retrieved Answer Headers to only include those for the latest versions of Questionnaires, and making sure the Question Seq Id comparison is properly comparing String values.
  * Travis Schneberger on Thu, 30 Apr 2015 12:08:09 -0400 [View Commit](../../commit/e8a5e39b895bfb7a425d7f4a1c1597ca8301aa57)

##coeus-1505.8
* Oracle fixes
  * Our oracle installation scripts had a few issues.
  * 1. The anonymous block was assuming there was only one record in the subawards table while there could be more than one.
  * 2. Constraint was being applied in the wrong order.
  * The errors were
  * ADD CONSTRAINT FK3_BUDGET_SUB_AWARDS
                   *
  * ERROR at line 2:
  * ORA-02298: cannot validate (MG1504.FK3_BUDGET_SUB_AWARDS) - parent keys not
  * found

  * DECLARE
*
  * ERROR at line 1:
  * ORA-01422: exact fetch returns more than requested number of rows
  * ORA-06512: at line 7
  * Gayathri on Mon, 4 May 2015 13:59:37 -0700 [View Commit](../../commit/20babdc1852e11501469e4022a466eca3d8d3759)

##coeus-1505.7
* No Changes


##coeus-1505.6
* fixes issues with printing s2s forms after proposal has been submitted to s2s

  * As a user, I need to be able to open a previously submitted PD and generate the PDF form files. I use this tool to verify the attachments I uploaded are the same as those in the sponsor's grant image, and to reference when I am preparing a resubmitted version of this research project. In the current release, I cannot generate the PDF forms of a submitted proposal. I get an STE.

  * Steps to reproduce:
  * Locate a submitted proposal (example res-demo1: Proposal #65, doc 5146)
  * Navigate to Basics > S2S Opportunity Search > Forms
  * OR Toolbar > Print > Grants.gov forms
  * Select one or all of the available s2s forms
  * use the Generate the PDF file tool

  * Expected result: the PDF file is generated and can be opened, viewed, saved, etc.
  * Actual result: STE appears onscreen.

  * java.lang.NullPointerException at org.kuali.coeus.propdev.impl.s2s.ProposalDevelopmentS2SController.printForms(ProposalDevelopmentS2SController.java:196) at org.kuali.coeus.propdev.impl.s2s.ProposalDevelopmentS2SController$$FastClassBySpringCGLIB$$5f5dd18.invoke(<generated>) at
  * Joe Williams on Mon, 4 May 2015 16:26:12 -0500 [View Commit](../../commit/0b9d8a26de48a35bd0d6ac15975f6c019df988bc)

##coeus-1505.5
* return user to the subaward document from subaward invoice if subaward invoice was opened from the subaward document

  * Create/open subaward document.
  * Use Add Invoice Button on the Financial tab to open an invoice document.
  * At close, submit, or blanket approve, the user is taken back to the KNS maintenance screen instead of the KRAD portal
  * Joe Williams on Mon, 4 May 2015 11:03:48 -0500 [View Commit](../../commit/a4d4f6cca622470bc84d6fc5e1cf955bce375cab)

##coeus-1505.4
* No Changes


##coeus-1505.3
* Custom data escape.

  * https://github.com/kuali/kc/pull/1579

  * [KRAFDBCK-10749] Escaping custom data values so single quotes don't terminate them early.

  * This fixes being able to break out of custom data values with single quotes in the KNS custom data tag (KRAD does this already).
  * Travis Schneberger on Fri, 1 May 2015 14:54:18 -0400 [View Commit](../../commit/5a966812b677c0591014020e365e15f1aa532ba4)
* Sponsor Award ID label.

  * https://github.com/kuali/kc/pull/1578
  * KRAFDBCK-9468

  * Changing "Sponsor Award" label to "Sponsor Award ID" in Negotiation.
  * Travis Schneberger on Fri, 1 May 2015 14:48:22 -0400 [View Commit](../../commit/35bfa7ebd1d70a91bc7932ab5ed5937ceb42ca85)
* Copying award results in read-only award and broken hierarchies

  * This is due to the authorization fields no being populated and only populated during docHandler. Removing this check added fixes this.
  * blackcathacker on Fri, 1 May 2015 15:28:27 -0700 [View Commit](../../commit/a250dbabb0184ea44f5a324e02e641d16278813e)

##coeus-1505.2
* No Changes


##coeus-1505.1
* format currency values with commas

  * Throughout the PD Budget, money values are formatted without any commas (or dollar signs). When populated, money value fields should be formatted with commas and decimal as for currency. And especially when you get into the millions not having the commas becomes a problem.
  * Joe Williams on Thu, 30 Apr 2015 11:24:24 -0500 [View Commit](../../commit/c5d86877ac264163546d13cb259231f96de22d37)
* KRMS - Rule Function 'Incomplete Narrative Rule' has logic reversed

  * The KC KRMS Function "Incomplete Narrative Rule" has logic reversed and is actually checking that narratives are complete.
  * Analogous Coeus function is "Complete Proposal Narratives.
  * Rather than reprogramming the logic of the KC function, the name should be changed to represent what the function actually does.

  * Change name of function to "Complete Narrative Rule"
  * Joe Williams on Thu, 30 Apr 2015 15:43:40 -0500 [View Commit](../../commit/e7cb52c9b5bd23ebf1b714c7eb4394a9033ac402)

##coeus-1504.17
* No Changes


##coeus-1504.16
* RESKC-361 avoiding a NullPointerException on proposal copy.

  * When an attachment exists such as a narrative or biography but the attachment does not have attachment data and the proposal attachment is copied with the copy attachments option then a NullPointerException occurs.  This scenario should not happen under normal circumstances and may indicate bad data in the system.
  * Travis Schneberger on Wed, 29 Apr 2015 15:18:45 -0400 [View Commit](../../commit/cf89f15c4f0ef48d29809933eb6d568a1985c462)
* RESKC-361 avoiding a resource leak that was indicated by a OJB warning message.
  * Travis Schneberger on Wed, 29 Apr 2015 15:19:27 -0400 [View Commit](../../commit/3214d06c87af20c54ff046821a1df6e3942db543)
* Omit questionnaires when related forms are not marked as included
  * Joe Williams on Wed, 29 Apr 2015 15:54:10 -0500 [View Commit](../../commit/040f6e167a863d1d8aa30e567f89d97024b69840)
* Set bounds on DevelopmentProposal lookups

  * When tested originally we were able to repo a outofmemory error and the search taking a LONG time. By setting bounds on the search always the search returns rather quickly. Further work needs to be done to resolve problems when a user has limited access to proposals as none may returned as the first 20 returned don't include any they have permission on.
  * blackcathacker on Wed, 29 Apr 2015 14:11:21 -0700 [View Commit](../../commit/5fa5fff9f459bff9db49cdc183685bef9fdca608)
* Fix unit test related to: Omit questionnaires when related forms are not marked as included
  * Travis Schneberger on Wed, 29 Apr 2015 19:38:44 -0400 [View Commit](../../commit/abac58680d59d3ca592e1740159e9b50ed651785)
* Use awardDocument updated info instead of award

  * Award Time and Money - T&M updates should not alter Award Version update timestamp and user
  * The History view should not reflect the update of the T&M change, but maintain when the award was last updated.
  * Using the awardDocument update timestamp accomplishes this.
  * blackcathacker on Wed, 29 Apr 2015 17:58:30 -0700 [View Commit](../../commit/f03dc89bc3732fbfc12ac87ead09987350ff8b84)
*  Refactoring and small unit test to exercise code
  * blackcathacker on Wed, 29 Apr 2015 20:11:52 -0700 [View Commit](../../commit/31c692bece2a7316e85c62af70b319d7f9d5ef3a)
* Award: Increase system performance on structure with many child accounts
  * Travis Schneberger on Thu, 30 Apr 2015 09:16:14 -0400 [View Commit](../../commit/790971bf9c1e21abbfb82d04b78132aafa35a9db)
* Add contributing file for public contributions
  * blackcathacker on Wed, 29 Apr 2015 10:19:55 -0700 [View Commit](../../commit/5a54613840fd271df6650d4ec9ff54180333f254)
* Review comment updates for CONTRIB file
  * blackcathacker on Thu, 30 Apr 2015 09:13:52 -0700 [View Commit](../../commit/b7605bb14d3a16800523ad86ad7da20a7f7939b9)
* Editing budget category generates STE

  * User Story: As a system administrator, I want to periodically edit budget categories, so our budget calculations are accurate

  * Steps to Reproduce:

  * 1) System Admin Portal > Maintenance > Budget Category
  * 2) Click the 'search'
  * 3) Click 'edit' next to any result
  * 4) Enter a description on the Document Overview tab and then edit the Description in the Edit Budget Category tab
  * 5) Click either 'submit' or ' blanket approve'

  * Actual results:

  * User is redirected to an Incident Report screen with the following error: Error Details: OJB operation; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'budgetCategoryTypeCode' in 'where clause'

  * Expected results:

  * Document should go directly to final and record should be updated with the users changes.

  * User acceptance criteria:

  * Given a system administrator has to edit a budget category, when the appropriate edits are made and submitted, then the budget category is updated with my changes.
  * Joe Williams on Thu, 30 Apr 2015 14:20:24 -0500 [View Commit](../../commit/261e859ae06470ad7a7d44c7890d80dabf98f23a)
* https://github.com/kuali/kc/pull/1584
  * KRAFDBCK-12440

  * This enhancement adds a new parameter "IP_INTELLECTUAL_PROPERTY_REVIEW_TAB_ENABLED" to determine whether or not the Intellectual Property Review tab should be shown in IP.
  * Travis Schneberger on Thu, 30 Apr 2015 15:46:24 -0400 [View Commit](../../commit/ebc0f36c251b60ca7465d727787126f601c20491)
* Fixed issue of showing latest version of award instead of Active in Awrd
  * heirarchy view.
  * vineeth on Thu, 2 Oct 2014 10:51:48 -0400 [View Commit](../../commit/4fbb3c6a02ac56af40f52424b5e1e56278943a76)
* Negotiation Unassociated Detail Inquiry Title Change fix
  * Joe Williams on Thu, 30 Apr 2015 16:32:05 -0500 [View Commit](../../commit/f42ff59a3949e71d4baf2e46105a574f47d0ff97)
* SubAward Status is incorporated instead of Award Status in Subaward tab
  * in Award Document.
  * anumole on Mon, 27 Oct 2014 17:33:28 +0530 [View Commit](../../commit/f797df76df53b03bef8fc3fd086f4792c7de8367)
*  Do not regenerate periods if periods already exist!
  * As an award budget user, when I create a rebudget (new version of posted budget with no change to authorized amount), I expect to have all the budget details from the last posted version appear in the rebudget. I should be reducing funds from the existing line items and redistributing that amount to current or new line items, confined to matching the preexisting authorized total.
  * Current (4/29/15) release is not copying the posted budget details to the rebudget; the rebudget is blank, only letting me search for an IP to link details or manually reenter all items. This is not the intended functionality, and not consistent with prior releases.
  * Steps to recreate:
  * Kuali.com demo1 site on 4/29/2015. Award *73-00001, acct id 2533529
  * As Quickstart:
  * Locate an existing award or create a new award with T&M values maintained.
  * Create an initial Award Budget, match to auth total: Actions> submit;
  * As JTester:
  * Action list: Award budget link:Actions> approve the award budget.
  * As Quickstart:
  * Open Award Budget: Actions > post.
  * Open Award > Open Budget Versions
  * To create the rebudget (new AB without change to T&M).
  * -click 'new' to create the rebudget documument
  * result:
  * The new 'rebudget' is not populated with the details entered in the prior approved/posted version.
  * Thus, all the manually entered the details (or modified pulled in PD budget details) are lost, requiring the AB admin to do all the work over again from scratch.
  * The system should be copying the budget line item details forward to the rebudget; users should only be required to shift funds (reduce some line items to rebudget to another) to meet the authorized amount.
  * Rebudget had been working in previous tests: I have done a rebudget where I put in negative value in an existing detail line item to the add that value to another or new line item.
  * Gayathri on Thu, 30 Apr 2015 17:32:16 -0700 [View Commit](../../commit/b4489c9e8c93664f560489a4c41e0df1be229683)

##coeus-1504.15
* No Changes


##coeus-1504.14
* release workflow pessimistic locks after approval

  * Approvers receive the following error message after approving proposal development document:

  * This document currently has a 160-PROPOSAL DEVELOPMENT lock owned by KR
  * Joe Williams on Tue, 28 Apr 2015 08:39:16 -0500 [View Commit](../../commit/c85a0f15a28626e69ec1bf61468d2d751afa2fb1)
* set correct update user after editing an institutional proposal

  * Edit an IP
  * The Last Update field appropriately indicates the user that is updating the record while it is open.
  * Approve
  * Close
  * Reopen the IP

  * Actual result: Last update shows as performed by "kr"
  * Expected result: Last update should display user not "kr".
  * Joe Williams on Tue, 28 Apr 2015 09:01:05 -0500 [View Commit](../../commit/05d252067cca63f76fa850383904024a17cb476c)
*  IP versioning should not copy award funding proposals over.
  * When a version of IP is linked to a version of the award, only that version needs to be linked instead of all edits of ths IP beling linked to the award. While displaying the awards linked in IP, display all the IP versions linked to awards.
  * Gayathri on Mon, 27 Apr 2015 11:28:20 -0700 [View Commit](../../commit/d7220558d85e3cdd9889898462c87baedcd797a7)
*  Moving schemaspy feature to public release.

* Moving schemaspy initialization logic and dependency info out of the grm profile
* Making schemaspy a compile-time dependency, with current version
* Adding database script with conditional insert for schemaspy auth
* Adding schemaspy instructions to readme.md
* supporting oracle in schemaspy filter and cleanup
* cleanup of poms
* fix incorrect instructions for instrumentation in readme.md
  * Travis Schneberger on Tue, 28 Apr 2015 10:57:12 -0400 [View Commit](../../commit/12995f562722e950f551547999a069b875014137)

##coeus-1504.13
*  added institutional proposal rest service
  * Joe Williams on Thu, 23 Apr 2015 12:30:06 -0400 [View Commit](../../commit/15f392cb5600d3c13017bc2840d0930f7535e6b0)
*  cleanup
  * Travis Schneberger on Wed, 15 Apr 2015 09:02:54 -0400 [View Commit](../../commit/90f296b210c720b318d9eea90379c57676f3f431)
*  make award date change transactions show up with a transaction id in the various history views and transaction print dropdown menus
  * Travis Schneberger on Wed, 15 Apr 2015 15:42:18 -0400 [View Commit](../../commit/89271da4bc2bdc7ced2643f05b5d46200dbebc66)
*  code review comments
  * Travis Schneberger on Fri, 24 Apr 2015 10:18:51 -0400 [View Commit](../../commit/090ce7405cf9ce13a1efe4704b84b456e0d88f23)
*  resequencing sql scripts, adding oracle script
  * Travis Schneberger on Fri, 24 Apr 2015 10:57:40 -0400 [View Commit](../../commit/497023f9082dd4fc2a659d04c17674cbe267e1a2)
* RESKC_352:clean up proposalHierarchyServiceImpl
  * Joe Williams on Mon, 27 Apr 2015 08:18:52 -0500 [View Commit](../../commit/18e3411729d385aaf45ef68227ee2f02c2fff205)
* Fix Wrong Proposal Status after Proposal Approved at Final Stop and Submitted to Sponsor

  * Create Proposal
  * Submit for Review
  * Do approvals
  * At final stop, approve and then automatic submission to sponsor
  * Actual Result: Status is set to 'Approved Post-Submission'
  * Expected Result: Status is set to 'Approved and Submitted'
  * Joe Williams on Mon, 27 Apr 2015 15:35:26 -0500 [View Commit](../../commit/e886230a92b6eff85f03c7c6281038af0d539cca)
*  depending on new kualico build of jasper token auth jar
  * Travis Schneberger on Mon, 27 Apr 2015 17:26:07 -0400 [View Commit](../../commit/c690912d6b92bd4e13dcfa48e2f9977a980cee9b)

##coeus-1504.12
*  added correct s2s error message and fix it link for congressional district errors

  * The Fix button for Validation error on Congressional District in the Grants.gov Errors section takes you to S2S window; it should take you to Organization Tab where error can be fixed.
  * Joe Williams on Fri, 24 Apr 2015 09:40:05 -0400 [View Commit](../../commit/e43cc0dfc627da384a9d38f44400cce0de94a106)
* Make all xmlstream beans prototype scope

  * XMLStream beans contain state which means they all need to be prototype scope to avoid potential clashes when server is under load
  * blackcathacker on Fri, 24 Apr 2015 10:03:02 -0400 [View Commit](../../commit/99f10d6cdf0244dd1d5fa52e80c334f1aea21cf7)

##coeus-1504.11
* HealthCheck now reports configured version and specific database status
  * blackcathacker on Thu, 16 Apr 2015 17:52:24 -0700 [View Commit](../../commit/0f5d4f83b24c46abeaed8f4ebcb755d47bac10a4)

##coeus-1504.10
* Fix update user and date on attachments in PropDev
  * blackcathacker on Thu, 16 Apr 2015 22:28:17 -0700 [View Commit](../../commit/4d820fb8ab3f8ec8a4b879922b8e77aaa9070ccd)
* Code review comments
  * blackcathacker on Fri, 17 Apr 2015 09:25:18 -0700 [View Commit](../../commit/998b02c569c33888517c6b390099c608af2a4a1f)
* add warning message to autocalculate periods
  * Joe Williams on Fri, 17 Apr 2015 15:32:59 -0500 [View Commit](../../commit/ba6d6074d5f63f4a3e54b722726a8f0e1cb12494)
* Remove duplicately versioned sql files
  * blackcathacker on Wed, 22 Apr 2015 16:43:11 -0400 [View Commit](../../commit/79a52c523bd8a4a0d3a0e420ead15ddc848c5313)
* Fix failing integration tests

  * Integration tests are failing as the controllers were the only things setting the upload information in the attachment. By setting the upload info during pre-persist we make sure the info is never null in integration tests or in the case of additional code paths
  * blackcathacker on Thu, 23 Apr 2015 09:30:24 -0400 [View Commit](../../commit/de24948ab4c31f1f667ec43b6c0c63fe9e23764e)

##coeus-1504.9
* Award Placeholder Document fixes

  * When the placeholder doc was created by creating an award and not copying a hierarchy the resulting document doesn't have a valid award. Specifically no sponsor code which was causing an exception. This checks for the placeholder doc before checking for the sponsor groups.
  * blackcathacker on Wed, 22 Apr 2015 11:33:56 -0400 [View Commit](../../commit/bc28f8e7a6016e15b6b550f1c9c906f1100c8422)

##coeus-1504.8
*  Cleaning up commit. Since we are overriding refreshNonUpdateableReferences anyway, we do not need to override method in rule base. Serialization changes also not needed it appears.
  * Gayathri on Wed, 22 Apr 2015 05:59:13 -0700 [View Commit](../../commit/f888b9beac9a478fa7740bb0af028fcbefa37cbd)

##coeus-1504.7
* fix rolodex persistence through sponsor maintenance document

  * Scenario:
  * 1 - Create a new Sponsor
  * 2 - Add Sponsor and Address details
  * 3 - Submit new Sponsor
  * 4 - Search for new Sponsor and open inquiry.

  * Result:
  * When the inquiry is opened you can see that a new rolodex record has been created but there is no address book information. If you try searching for the address book record, you can only copy the record, not edit it.

  * Expected Behavior:
  * 1 - The address book entry details should be saved when the sponsor record saves.
  * 2 - The address book record should be editable from the address book lookup.
  * Joe Williams on Tue, 21 Apr 2015 18:53:04 -0400 [View Commit](../../commit/03d6db21c678c2728cf9d5ca447cd648b735b891)

##coeus-1504.6
* No Changes


##coeus-1504.5
* added update user to data over history
  * Joe Williams on Fri, 17 Apr 2015 13:58:48 -0500 [View Commit](../../commit/c07944d2ec00d457defeb1ce99ccecec973c7e4a)
* added rolodex and ynq inquiry links to organization inquiry view
  * Joe Williams on Fri, 17 Apr 2015 14:59:43 -0500 [View Commit](../../commit/c7cf6e478b5d2a48414ed0ef7c91c94b3495317e)
* Oracle scripts

  * Bring full oracle scripts renamed and numbered back into the project
  * blackcathacker on Tue, 21 Apr 2015 09:15:55 -0400 [View Commit](../../commit/4df62ebd31a478844448a5e7d0a250953058cfc4)
* Allow sponsor code to be smaller than 6 characters

  * A customer ended up with sponsor codes shorter than 6 characters and since it was allowed by the database and a minimal functional change the sponsor code can now be any alphanumeric up to 6 characters
  * blackcathacker on Tue, 21 Apr 2015 16:02:18 -0400 [View Commit](../../commit/0cc7dfba835c8343524a797e540d6d65871ee780)

##coeus-1504.4
* kc-sql version update
  * blackcathacker on Fri, 17 Apr 2015 21:48:12 -0700 [View Commit](../../commit/e0669b0a138ac093a19a33dde380a4f534762e3c)

##coeus-1504.3
* KRAFDBCK-10988: Adding totals to Negotiation Activity sub-tabs
  * rojlarge on Tue, 7 Apr 2015 18:39:30 -0400 [View Commit](../../commit/c0c22a523dc0f8e0c7361343eb0e03af5a5d4b75)

##coeus-1504.2
*  Adding 1504 mysql and oracle release scripts
  * Gayathri on Fri, 17 Apr 2015 15:23:03 -0700 [View Commit](../../commit/cc29093c62e019bb200c83a02d8088137a407be5)
* renaming files and removing temp files
  * Gayathri on Fri, 17 Apr 2015 15:45:36 -0700 [View Commit](../../commit/bcb851a18f8bbde30d3df87fa1bc4e083debd1c3)
* Documentation for public release and build
  * blackcathacker on Fri, 17 Apr 2015 16:04:52 -0700 [View Commit](../../commit/80748ea9cb76f523f7d50f4a6b975aec7c96263f)
*  Renaming log files to right version
  * Gayathri on Fri, 17 Apr 2015 16:19:58 -0700 [View Commit](../../commit/aaa91348c850f74e22f028619b714954ccb15c6b)

##coeus-1504.1
* moving away from sem ver
  * Travis Schneberger on Fri, 17 Apr 2015 18:30:33 -0400 [View Commit](../../commit/d0596b11edec6e28577a48b08f26ad065304191f)

##coeus-6.0.2.27
* updating dependencies and plugins in pom file
  * Travis Schneberger on Thu, 16 Apr 2015 19:48:55 -0400 [View Commit](../../commit/56974b1f0ed5bd1440569d2ad0b6fbb307e0557b)

##coeus-6.0.2.26
* populated budget formulated costs from unit formulated cost tables
  * Joe Williams on Wed, 15 Apr 2015 09:46:54 -0500 [View Commit](../../commit/47bdb61e409299b37e172b931d8970b6846c435b)
* allow printing of sponsor form when view only
  * Joe Williams on Wed, 15 Apr 2015 13:03:16 -0500 [View Commit](../../commit/d4321526420059f0232fb283391ebb2461d34c03)
* fix report tracking to hide update user and timestamp when date entry hasn't been entered
  * Joe Williams on Thu, 16 Apr 2015 12:20:32 -0500 [View Commit](../../commit/fd64b8d58279084f53791420b1746ae10b73b205)

##coeus-6.0.2.25
* No Changes


##coeus-6.0.2.24
*  upgrade rice
  * Joe Williams on Thu, 16 Apr 2015 08:22:34 -0500 [View Commit](../../commit/8bae0b0bdc31c5833a25aa5f7f42a92c44e8a569)

##coeus-6.0.2.23
* Move rest service dependency out of grm profile

  * The moo and mvel dependencies are always required so move them into the main dependency section
  * blackcathacker on Wed, 15 Apr 2015 17:59:49 -0700 [View Commit](../../commit/985d7f88218dd871624148d4faa2ec84ca152d55)

##coeus-6.0.2.22
* Update rice version
  * Joe Williams on Wed, 15 Apr 2015 17:03:38 -0500 [View Commit](../../commit/bf02c41a3b57ecc550aaf04ee416e959c5debf00)

##coeus-6.0.2.21
*  rice upgrade
  * Gayathri on Wed, 15 Apr 2015 08:37:43 -0700 [View Commit](../../commit/95dc07436f1afb7fcd8d5414ca83801fd943e591)

##coeus-6.0.2.20
*  fixing date calculation
  * Travis Schneberger on Tue, 14 Apr 2015 09:13:19 -0400 [View Commit](../../commit/8d546ff3ffc857bc2507d8f6fe0390a4a26905ff)

##coeus-6.0.2.19
*  moving rejected docs to saved state
  * Gayathri on Mon, 13 Apr 2015 10:34:44 -0700 [View Commit](../../commit/8e741a6bcc9030cd8f0883138b01026eb181f91c)
* review comments
  * Gayathri on Mon, 13 Apr 2015 16:55:43 -0700 [View Commit](../../commit/a1a7ed197223235358f56d9aadbbd8947a77c04f)

##coeus-6.0.2.18
* Update Rice Version
  * blackcathacker on Mon, 13 Apr 2015 14:07:58 -0700 [View Commit](../../commit/aa01f02f4b30a4cef77f9bfe333829eabb27275c)

##coeus-6.0.2.17
*  PSU Award Summary Service
  * blackcathacker on Wed, 18 Feb 2015 15:40:02 -0800 [View Commit](../../commit/a35bf7b3a4d80385e0ccbcdc2cd712bfe35ad371)
* Code review comments and copyright notices
  * blackcathacker on Mon, 13 Apr 2015 13:04:01 -0700 [View Commit](../../commit/c829857a50660bd18ad5a996665f937fdd02bd13)

##coeus-6.0.2.16
* fix people flow member require validation on add line on submit
  * Joe Williams on Mon, 13 Apr 2015 11:11:00 -0500 [View Commit](../../commit/733b6f6a9cd23d536427815261db896f9b2756f5)

##coeus-6.0.2.15
*  fixing key person unit validation
  * Travis Schneberger on Mon, 13 Apr 2015 11:07:51 -0400 [View Commit](../../commit/74065cd3dc7c96c0216ec2235fd8ae2e72d3575a)

##coeus-6.0.2.14
* make personnel details viewable in view mode
  * Joe Williams on Fri, 10 Apr 2015 13:33:55 -0500 [View Commit](../../commit/4f6ab6dd0ef5b843815a615bd91573d9a834c62a)
* make non personnel budget line items detail viewable when budget is readOnly
  * Joe Williams on Fri, 10 Apr 2015 10:44:30 -0500 [View Commit](../../commit/858e55f9b573132f6673f5d4d4c7ddb5f7d32f6e)

##coeus-6.0.2.13
*  upgrading rice
  * Gayathri on Fri, 10 Apr 2015 09:14:14 -0700 [View Commit](../../commit/814d625c456c7c9c12a1dc8d29d0103120264169)
* remove narrative locking region
  * Joe Williams on Fri, 10 Apr 2015 12:38:06 -0500 [View Commit](../../commit/a15fc1881b9d22be5da065cd6e1bf430ab9c4179)
* Jasper Report Links
  * blackcathacker on Fri, 10 Apr 2015 12:41:03 -0700 [View Commit](../../commit/dec341bf9aed3ce0c2f175760636c453e1d0da46)

##coeus-6.0.2.12
* No Changes


##coeus-6.0.2.11
* add warning message when adding a narrative type to the child that is already on the parent
  * Joe Williams on Thu, 9 Apr 2015 09:19:57 -0500 [View Commit](../../commit/f7ddacadd29371782ffa032c7d992cb3edb1464a)
* removed duplicate headers on krms and people flow documents
  * Joe Williams on Fri, 10 Apr 2015 10:08:02 -0500 [View Commit](../../commit/926204a501f784b198b07b46fe70fa887bcef87d)

##coeus-6.0.2.10
* Make PD unit number search field uniform and not restricted
  * blackcathacker on Thu, 9 Apr 2015 22:06:18 -0700 [View Commit](../../commit/f9021255f95f95f159989f5a831eb6209f5c3ab4)

##coeus-6.0.2.9
*  Award authorization issue
  * Gayathri on Thu, 9 Apr 2015 12:11:36 -0700 [View Commit](../../commit/0df17791f67164c47d6b08e4130f8ec49b973c1f)

##coeus-6.0.2.8
* fixed lookups on people flow members section
  * Joe Williams on Thu, 9 Apr 2015 13:42:24 -0500 [View Commit](../../commit/d42d12a23f0eb631ca324174af5702bd26ac670d)

##coeus-6.0.2.7
* make personnel budget line items details viewable when budget is readOnly
  * Joe Williams on Thu, 9 Apr 2015 12:52:40 -0500 [View Commit](../../commit/436b252792a8d6bed66678b6c7b3a95dcabb5b0c)

##coeus-6.0.2.6
*  Budget STE fix
  * Gayathri on Wed, 8 Apr 2015 10:55:26 -0700 [View Commit](../../commit/ba7600fabcee66f9673d8c09fcbddbbc40a65b40)

##coeus-6.0.2.5
*  Add fyi and acknowledge to action list.
  * Gayathri on Wed, 8 Apr 2015 09:21:18 -0700 [View Commit](../../commit/3f8bfb1d44a959edd717a52544ddac1ac492f104)

##coeus-6.0.2.4
* Fix sql to avoid primary key conflicts
  * blackcathacker on Tue, 7 Apr 2015 20:33:23 -0700 [View Commit](../../commit/22a4b96beaed5dc64aba04825f751192094f0e56)

##coeus-6.0.2.3
* Fix attachment problem by never doing orphanRemoval
  * blackcathacker on Tue, 7 Apr 2015 16:50:30 -0700 [View Commit](../../commit/ea2f774303c1b476579ebf90155be07e334a4108)
* Fix integration test
  * blackcathacker on Tue, 7 Apr 2015 18:02:21 -0700 [View Commit](../../commit/d056987b4666d51b996e984854df5904e6381370)

##coeus-6.0.2.2
* rename asdf custrom attribute group name to additional data
  * Joe Williams on Mon, 6 Apr 2015 16:07:16 -0500 [View Commit](../../commit/0c6c27bb02bb3c7543f7338be56f653769fe6665)
* apply unit agenda to all units in hierarchy
  * Joe Williams on Mon, 6 Apr 2015 15:06:51 -0500 [View Commit](../../commit/06939b293f62441f7acf74f549439ec4be32d81b)
*  making PeopleFlow lookup not open in an iframe
  * Travis Schneberger on Tue, 7 Apr 2015 10:47:00 -0400 [View Commit](../../commit/883318884f03f90eab034b83b01f53967b9c2715)
* fix broken test
  * Joe Williams on Tue, 7 Apr 2015 09:57:15 -0500 [View Commit](../../commit/1f8f4ffe0e61702bc04d00f1e41741cb2fa7d259)
*  displaying sponsor award id not sponsor code
  * Travis Schneberger on Tue, 7 Apr 2015 16:23:56 -0400 [View Commit](../../commit/3a5288b201bc8f7540f332c5e6215e3b19a95fe2)
*  nsf cover page 1-6
  * Gayathri on Wed, 25 Mar 2015 12:22:35 -0700 [View Commit](../../commit/b00fdc8f2e00e08da81104485e8502f53b299f4e)
*  updating pom, fixing db scripts that were changed as a part of a code review
  * Travis Schneberger on Tue, 7 Apr 2015 16:58:28 -0400 [View Commit](../../commit/d468f72bc90cdc47f8266e19b7c355fbef075d5b)

##coeus-6.0.2.1
* release process
  * Travis Schneberger on Mon, 6 Apr 2015 21:36:12 -0400 [View Commit](../../commit/7313e436dac0e1dec0d339a982bd13678937e935)

##coeus-6.0.2.0
* KRACOUES-8764:fix additional equipment system generated attachments
  * Joe Williams on Mon, 2 Feb 2015 10:21:53 -0600 [View Commit](../../commit/36480a289ba551da181442a5dc052c9fecb809dd)
* KRACOEUS-8775: next iteration
  * Travis Schneberger on Mon, 2 Feb 2015 17:41:56 -0500 [View Commit](../../commit/154a686f9289c09ae865376763d561c11e068038)
* KRACOEUS-8775: setting the s2sgen lib to next iteration
  * Travis Schneberger on Mon, 2 Feb 2015 17:44:29 -0500 [View Commit](../../commit/defd2f9b95e391079eb3b1f681594d2aeffd0e3d)
* KRACOEUS-8784:make sub award line items non editable from periods page
  * Joe Williams on Tue, 3 Feb 2015 14:23:22 -0600 [View Commit](../../commit/7ea052db65075ce4d3958d30df2fa1005e53a7f4)
* KRACOEUS-8760:moved version and sql info to footer
  * Joe Williams on Fri, 30 Jan 2015 16:30:35 -0600 [View Commit](../../commit/d9b846d119222ba6ead2697277fd63d815b62a5e)
* KRACOEUS-8776: lic header issue
  * Travis Schneberger on Tue, 3 Feb 2015 17:01:14 -0500 [View Commit](../../commit/b29c8f46fd05b1a958ba23b4dbac3febf1025771)
* KRACOEUS-8786:fixed subaward close out date constraint issues
  * Joe Williams on Wed, 4 Feb 2015 08:31:23 -0600 [View Commit](../../commit/b0e5a9abcf8053371659df415c8082422671b645)
* KRACOEUS-8783:allow routing actions for proposals accessed through proposal search
  * Joe Williams on Wed, 4 Feb 2015 09:43:34 -0600 [View Commit](../../commit/bd780608c82c5dcda20fdc637e4cd9dc239c8cfb)
* KRACOEUS-7863
  * Implement Budget Non-Personnel Costs - Formulated Costs
  * rmancher on Wed, 4 Feb 2015 15:54:32 -0500 [View Commit](../../commit/ef3d3ba03a72a3b0596d4dbce870a41a9fa7ee45)
* KRACOEUS-8773: fixing a NPE
  * Travis Schneberger on Wed, 4 Feb 2015 16:07:40 -0500 [View Commit](../../commit/0f6e2b771e5e2d9bfb95401cbb60e75a64d7ccfc)
* KRACOEUS-8789:subaward sync from pdf refreshes details section
  * Joe Williams on Wed, 4 Feb 2015 16:39:15 -0600 [View Commit](../../commit/b20d724872eda17cc19650ce53ee3f5ceb74ecd4)
* CX Version Changes -- Primarily Bug Fixes

  * g diff -w kc-release-5_2_1-tag..rsmart-5.2.1 --stat=999 >! git_diff_kc-release-5_2_1-tag..rsmart-5.2.1_stat.txt

  * Removed all files we do not want to consider for merge

  * normalize changed files we want to merge

  * Create patches for kc-release-5_2_1-tag..rsmart-5.2.1 diffs

  * map relocated path for files that could not be automatically matched

  * Added missing patches from: Create patches for kc-release-5_2_1-tag..rsmart-5.2.1 diffs

  * removed trailing space from patch file

  * basic patch rewriting script ready

squashed some path rewriting logic bugs; smoke testing LGTM

  * ./rewrite_patches.rb

  * add CX_PORTING.md

  * Manually merged `cb38ce9` KualiCo pom.xml changes.

  * Conflicts:
	pom.xml

  * updated CX_PORTING for cx_patches/pom.xml.patch

  * applied cx_patches/src/main/config/kew/xml/2/AwardDocument.xml.patch

  * cx_patches/src/main/config/sql/current/5.2.1/dml/KR_DML_01_KRACOEUS-6640_B000.sql.patch

  * cx_patches/src/main/config/sql/current/5.2.1/sequences/KC_SEQ_S2S_USER_ATTACHED_FORM_ID.sql.patch

  * cx_patches/src/main/config/sql/current/5.2.1/sequences/KC_SEQ_S2S_USER_ATTD_FORM_ATT_ID.sql.patch

  * cx_patches/src/main/config/sql/current_mysql/0.0.0/dml/KR_DML_01_KCINFR-1018_B000.sql.patch

  * cx_patches/src/main/config/sql/current_mysql/5.2.1/dml/KR_DML_01_KRACOEUS-6640_B000.sql.patch

  * cx_patches/src/main/config/sql/current_mysql/5.2.1/sequences/KC_SEQ_S2S_USER_ATTACHED_FORM_ATT_ID.sql.patch

  * cx_patches/src/main/config/sql/current_mysql/5.2.1/sequences/KC_SEQ_S2S_USER_ATTACHED_FORM_ID.sql.patch

  * cx_patches/src/main/config/sql/current_mysql/5.2.1/tables/KC_TBL_CONTACT_USAGE.sql.patch
  * Fixes: Caused by: java.sql.SQLException: Can't create table `coeus`.`#sql-c8e0_78` (errno: 150 "Foreign key constraint is incorrectly formed")
  * Caused by: com.googlecode.flyway.core.command.FlywaySqlScriptException: Error executing statement at line 3: ALTER TABLE CONTACT_USAGE

  * cx_patches/src/main/config/sql/RELEASE-SCRIPTS/KC_Install.sh.patch

  * cx_patches/src/main/java/com/rsmart/kuali/kra/workflow/UnitAdministratorRoleAttribute.java.patch

  * cx_patches/src/main/java/org/kuali/kra/award/contacts/AwardSponsorContactAuditRule.java.patch
  * Adding rule to check for Country Code. If a country code is not found, an error is reported and the user is taken to the Award Contacts tab to handle it.
  * fixes rSmart/issues#448

  * cx_patches/src/main/java/org/kuali/kra/award/document/authorization/AwardDocumentAuthorizer.java.patch

  * cx_patches/src/main/java/org/kuali/kra/award/home/Award.java.patch

  * cx_patches/src/main/java/org/kuali/kra/award/home/AwardAmountInfo.java.patch
  * Default all award amount info amounts to zero when null.
  * Fixes rSmart/issues#503

  * cx_patches/src/main/java/org/kuali/kra/award/lookup/AwardLookupableHelperServiceImpl.java.patch
  * Ignore missing person/entity for https://github.com/rSmart/issues/issues/179

  * cx_patches/src/main/java/org/kuali/kra/award/printing/xmlstream/AwardBaseStream.java.patch
  * Make rolodex country code optional for Award printing.
  * fixes rSmart/issues#448

  * cx_patches/src/main/java/org/kuali/kra/award/web/struts/action/AwardActionsAction.java.patch
  * Adding rule to check for Country Code. If a country code is not found, an error is reported and the user is taken to the Award Contacts tab to handle it.

  * fixes rSmart/issues#448

  * cx_patches/src/main/java/org/kuali/kra/award/web/struts/action/AwardHomeAction.java.patch
  * CCU STE on Document #9532
  * java.lang.NullPointerException
        at org.kuali.kra.award.web.struts.action.AwardHomeAction.save(AwardHomeAction.java:240)
  * https://github.com/rSmart/issues/issues/468

  * Adding null checks to fix NPE.

  * fixes rSmart/issues#270

  * cx_patches/src/main/java/org/kuali/kra/bo/KcPerson.java.patch
  * Return a new empty KcPerson from fromEntityAndPersonId when entity is null.

  * cx_patches/src/main/java/org/kuali/kra/bo/Rolodex.java.patch

  * Commit: edf06332fe7d3cb3940e9353434523fab5d25033
  * Date:   2014-06-30 11:43:17 -0700 (5 months ago)

  * Patching fix from changeset 19864. This patch adds back the isSponsorAddress property to Rolodex. When isSponsorAddress is
  * "N", the behavior changes so the Rolodex reflects the Organization address. For this, organizations had to be added to
  * Rolodex. The maintainable also needed to be modified to recognize isSponsorAddress functionality.

  * fixes rSmart/issues#201

  * cx_patches/src/main/java/org/kuali/kra/bo/RolodexMaintainableImpl.java.patch

  * cx_patches/src/main/java/org/kuali/kra/bo/UnitAdministrator.java.patch

  * cx_patches/src/main/java/org/kuali/kra/budget/printing/xmlstream/BudgetBaseStream.java.patch

  * cx_patches/src/main/java/org/kuali/kra/budget/printing/xmlstream/BudgetCumilativeXmlStream.java.patch

  * cx_patches/src/main/java/org/kuali/kra/budget/printing/xmlstream/BudgetSummaryXmlStream.java.patch

  * cx_patches/src/main/java/org/kuali/kra/budget/printing/xmlstream/IndustrialBudgetXmlStream.java.patch

  * cx_patches/src/main/java/org/kuali/kra/budget/web/struts/action/BudgetAction.java.patch
  * java.lang.NullPointerException
        at org.kuali.kra.budget.web.struts.action.BudgetAction.execute(BudgetAction.java:189)
  * https://github.com/rSmart/issues/issues/417

  * cx_patches/src/main/java/org/kuali/kra/coi/disclosure/CoiDisclosureServiceImpl.java.patch
  * Ignore missing person/entity from org.kuali.kra.protocol.ProtocolActionBase.permissions(ProtocolActionBase.java:123)

  * cx_patches/src/main/java/org/kuali/kra/coi/notification/CoiNotificationRoleQualifierServiceImpl.java.patch
  * java.lang.NullPointerException
    at org.kuali.kra.coi.notification.CoiNotificationRoleQualifierServiceImpl.getRoleQualifierValue(CoiNotificationRoleQualifierServiceImpl.java:47)
    at org.kuali.kra.common.notification.NotificationContextBase.populateRoleQualifiers(NotificationContextBase.java:85)
    at org.kuali.kra.common.notification.service.impl.KcNotificationServiceImpl.getRoleRecipients(KcNotificationServiceImpl.java:434)
    at org.kuali.kra.common.notification.service.impl.KcNotificationServiceImpl.getNotificationRecipients(KcNotificationServiceImpl.java:352)
    at org.kuali.kra.common.notification.service.impl.KcNotificationServiceImpl.fillinNotificationObject(KcNotificationServiceImpl.java:623)
    at org.kuali.kra.common.notification.service.impl.KcNotificationServiceImpl.sendNotificationAndPersist(KcNotificationServiceImpl.java:638)
    at org.kuali.kra.coi.personfinancialentity.FinancialEntityAction.sendNotificationAndPersist(FinancialEntityAction.java:444)
    at org.kuali.kra.coi.personfinancialentity.FinancialEntityEditNewAction.submit(FinancialEntityEditNewAction.java:60)

  * Fixes https://github.com/rSmart/issues/issues/377

  * cx_patches/src/main/java/org/kuali/kra/coi/notification/FinancialEntityNotificationRenderer.java.patch
  * java.lang.NullPointerException
    at org.kuali.kra.coi.notification.FinancialEntityNotificationRenderer.getDefaultReplacementParameters(FinancialEntityNotificationRenderer.java:56)
  * Fixes https://github.com/rSmart/issues/issues/377

  * cx_patches/src/main/java/org/kuali/kra/coi/service/impl/CoiMessagesServiceImpl.java.patch
  * When setting ANNUAL_DISCLOSURE_RENEWAL_DATE system parameter, the year is not parsed correctly.
  * https://github.com/rSmart/issues/issues/470

  * Fixes java.lang.NullPointerException in CoiMessagesServiceImpl

  * java.sql.Timestamp.compareTo(Timestamp.java:477)
  * java.sql.Timestamp.before(Timestamp.java:447)
  * org.kuali.kra.coi.service.impl.CoiMessagesServiceImpl.getMessages(CoiMessagesServiceImpl.java:75)

  * Fixes https://github.com/rSmart/issues/issues/240

  * cx_patches/src/main/java/org/kuali/kra/committee/lookup/CommitteeLookupableHelperServiceImpl.java.patch
  * Adding fix so that iacuc committees now use proper url for resume edit

  * fixes rSmart/issues#201

  * cx_patches/src/main/java/org/kuali/kra/common/committee/bo/CommitteeBase.java.patch
  * java.lang.NullPointerException
  * at org.kuali.kra.common.committee.bo.CommitteeBase.compareTo(CommitteeBase.java:356)
  * at org.kuali.kra.common.committee.bo.CommitteeBase.compareTo(CommitteeBase.java:38)
  * Fixes https://github.com/rSmart/issues/issues/335

  * cx_patches/src/main/java/org/kuali/kra/common/committee/lookup/CommitteeLookupableHelperServiceImplBase.java.patch
  * Adding fix so that iacuc committees now use proper url for resume edit

  * fixes rSmart/issues#201

  * cx_patches/src/main/java/org/kuali/kra/common/committee/meeting/CommitteeScheduleAttachmentsBase.java.patch

  * cx_patches/src/main/java/org/kuali/kra/common/committee/meeting/MeetingActionBase.java.patch
  * NPE When Accessing Meeting Actions Tab on IRB Schedule
  * https://github.com/rSmart/issues/issues/449

  * cx_patches/src/main/java/org/kuali/kra/common/committee/meeting/MeetingActionsActionBase.java.patch
  * java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 0 at java.util.Vector.get(Vector.java:744)
  * at org.kuali.kra.common.committee.meeting.MeetingActionsActionBase.viewAgenda(MeetingActionsActionBase.java:205)
  * Fixes https://github.com/rSmart/issues/issues/307

  * cx_patches/src/main/java/org/kuali/kra/common/committee/notification/AgendaCreatedNotificationRenderer.java.patch

  * cx_patches/src/main/java/org/kuali/kra/common/notification/service/impl/KcNotificationServiceImpl.java.patch
  * Fixes https://github.com/rSmart/issues/issues/377

  * org.kuali.rice.core.api.exception.RiceIllegalArgumentException: principalName was blank
        at org.kuali.rice.kim.impl.identity.IdentityServiceImpl.incomingParamCheck(IdentityServiceImpl.java:1566)
        at org.kuali.rice.kim.impl.identity.IdentityServiceImpl.getEntityByPrincipalName(IdentityServiceImpl.java:125)
  * ...
        at org.kuali.kra.common.notification.service.impl.KcNotificationServiceImpl.getRecipientEmailAddresses(KcNotificationServiceImpl.java:542)
        at org.kuali.kra.common.notification.service.impl.KcNotificationServiceImpl.sendEmailNotification(KcNotificationServiceImpl.java:524)
        at org.kuali.kra.common.notification.service.impl.KcNotificationServiceImpl.sendNotification(KcNotificationServiceImpl.java:184)
        at org.kuali.kra.common.notification.service.impl.KcNotificationServiceImpl.sendNotificationAndPersist(KcNotificationServiceImpl.java:630)
        at org.kuali.kra.coi.personfinancialentity.FinancialEntityAction.sendNotificationAndPersist(FinancialEntityAction.java:444)
        at org.kuali.kra.coi.personfinancialentity.FinancialEntityEditNewAction.submit(FinancialEntityEditNewAction.java:60)

  * Workaround https://github.com/rSmart/issues/issues/158 ; added try/catch to be more forgiving.

  * cx_patches/src/main/java/org/kuali/kra/iacuc/actions/IacucProtocolActionsAction.java.patch

  * cx_patches/src/main/java/org/kuali/kra/iacuc/committee/lookup/IacucCommitteeLookupableHelperServiceImpl.java.patch
  * Adding fix so that iacuc committees now use proper url for resume edit

  * fixes rSmart/issues#201

  * cx_patches/src/main/java/org/kuali/kra/iacuc/protocol/IacucProtocolProtocolAction.java.patch
  * Adding fixes specific to IACUC

  * fixes rSmart/issues#299

  * cx_patches/src/main/java/org/kuali/kra/institutionalproposal/contacts/InstitutionalProposalPerson.java.patch

  * cx_patches/src/main/java/org/kuali/kra/institutionalproposal/contacts/InstitutionalProposalProjectPersonnelBean.java.patch
  * UMC - NPE when editing a new Institutional Proposal.
  * https://github.com/rSmart/issues/issues/492

  * cx_patches/src/main/java/org/kuali/kra/institutionalproposal/proposallog/ProposalLogMergeAction.java.patch
  * [SO-27] The session is clearing out the value of the proposal log number before the action can handle it. I switched to by more synchronous
  * using request parameters instead of session data. This is more intuitive to how lookups work anyway. I also added a null check just in case.

  * cx_patches/src/main/java/org/kuali/kra/institutionalproposal/proposallog/service/impl/ProposalLogServiceImpl.java.patch

  * cx_patches/src/main/java/org/kuali/kra/institutionalproposal/service/impl/InstitutionalProposalLookupableHelperServiceImpl.java.patch
  * [SO-27] The session is clearing out the value of the proposal log number before the action can handle it. I switched to by more synchronous
  * using request parameters instead of session data. This is more intuitive to how lookups work anyway. I also added a null check just in case.

  * STE when PD "Submit to Sponsor"
  * java.lang.NullPointerException
  * at org.kuali.kra.institutionalproposal.service.impl.InstitutionalProposalServiceImpl.doBudgetDataFeed(InstitutionalProposalServiceImpl.java:539)
  * Prop Dev submitted. (PD 104)
  * approvals bypassed.
  * try to submit to sponsor as admin.
  * get the following STE.
  * this has happened more than once, but not every time.
  * http://jira.s2.rsmart.com:8080/browse/SO-32

  * cx_patches/src/main/java/org/kuali/kra/institutionalproposal/service/impl/InstitutionalProposalServiceImpl.java.patch

  * cx_patches/src/main/java/org/kuali/kra/irb/actions/IrbProtocolActionRequestServiceImpl.java.patch

  * cx_patches/src/main/java/org/kuali/kra/irb/actions/notification/ProtocolWithdrawnNotificationRenderer.java.patch
  * Adding new notification renderer to handle reason and action date types. I tried adding unit tests for this, but they're not working. I'll have to commit those at a later date once I get them to work.

  * fixes rsmart/issues#414

  * cx_patches/src/main/java/org/kuali/kra/irb/actions/ProtocolProtocolActionsAction.java.patch

  * cx_patches/src/main/java/org/kuali/kra/irb/protocol/ProtocolProtocolAction.java.patch

  * cx_patches/src/main/java/org/kuali/kra/negotiations/notifications/NegotiationNotificationRenderer.java.patch

  * cx_patches/src/main/java/org/kuali/kra/proposaldevelopment/document/authorizer/NarrativeReplaceAuthorizer.java.patch

  * cx_patches/src/main/java/org/kuali/kra/proposaldevelopment/document/authorizer/PersonnelAttachmentReplaceAuthorizer.java.patch

  * cx_patches/src/main/java/org/kuali/kra/proposaldevelopment/notification/ProposalDevelopmentNotificationRenderer.java.patch

  * cx_patches/src/main/java/org/kuali/kra/proposaldevelopment/rules/ProposalDevelopmentDocumentRule.java.patch

  * cx_patches/src/main/java/org/kuali/kra/proposaldevelopment/service/impl/ProposalCopyServiceImpl.java.patch

  * cx_patches/src/main/java/org/kuali/kra/proposaldevelopment/web/struts/form/ProposalDevelopmentForm.java.patch

  * cx_patches/src/main/java/org/kuali/kra/protocol/actions/ActionHelperBase.java.patch

  * cx_patches/src/main/java/org/kuali/kra/protocol/notification/ProtocolReplacementParameters.java.patch

  * cx_patches/src/main/java/org/kuali/kra/questionnaire/answer/QuestionnaireAnswerServiceImpl.java.patch

  * cx_patches/src/main/java/org/kuali/kra/s2s/service/impl/S2SUtilServiceImpl.java.patch

  * cx_patches/src/main/java/org/kuali/kra/service/impl/KraAuthorizationServiceImpl.java.patch

  * cx_patches/src/main/java/org/kuali/kra/subaward/web/struts/action/SubAwardAction.java.patch

  * cx_patches/src/main/java/org/kuali/kra/subaward/web/struts/action/SubAwardActionsAction.java.patch

  * cx_patches/src/main/java/org/kuali/kra/subawardReporting/printing/service/impl/SubAwardPrintingServiceImpl.java.patch

  * cx_patches/src/main/java/org/kuali/kra/subawardReporting/printing/xmlstream/SubAwardFDPPrintXmlStream.java.patch

  * cx_patches/src/main/java/org/kuali/kra/timeandmoney/transactions/TransactionRuleImpl.java.patch

  * cx_patches/src/main/java/org/kuali/kra/web/filter/PerformanceLoggingFilter.java.patch

  * cx_patches/src/main/java/org/kuali/kra/web/struts/action/KraTransactionalDocumentActionBase.java.patch

  * cx_patches/src/main/resources/ApplicationResources.properties.patch

  * cx_patches/src/main/resources/org/kuali/kra/CoreSpringBeans.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/Award.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/AwardPerson.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/Budget.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/CustReportDetails.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/CustReportType.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/CustRptDefaultParms.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/CustRptTypeDocument.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/DevelopmentProposal.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/docs/RolodexMaintenanceDocument.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/docs/SponsorMaintenanceDocument.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/IacucProtocol.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/InstitutionalProposal.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/InstitutionalProposalPerson.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/LookupableDevelopmentProposal.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/Negotiation.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/NegotiationActivity.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/NegotiationUnassociatedDetail.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/ProposalDevelopmentDocument.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/Protocol.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/Rolodex.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/Sponsor.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/SponsorSpecial.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/SubAward.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/SubAwardCopyRightsType.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/SubAwardCostType.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/datadictionary/SubAwardFundingSource.xml.patch

  * cx_patches/src/main/resources/org/kuali/kra/printing/stylesheet/AwardNotice.xsl.patch

  * cx_patches/src/main/resources/org/kuali/kra/printing/stylesheet/BudgetSummaryTotalPage.xsl.patch

  * cx_patches/src/main/resources/org/kuali/kra/printing/stylesheet/FDP_Modification_Template.xsl.patch

  * cx_patches/src/main/resources/org/kuali/kra/printing/stylesheet/FDP_Template_Agreement.xsl.patch

  * cx_patches/src/main/resources/org/kuali/kra/RiceOverridesSpringBeans.xml.patch

  * cx_patches/src/main/resources/repository.xml.patch

  * begin phase 2 of CX merges

  * cx_patches/src/main/config/sql/RELEASE-SCRIPTS/KC_Install.sh.patch pass 2

  * cx_patches/src/main/java/com/rsmart/kuali/kra/workflow/UnitAdministratorRoleAttribute.java.patch

  * updated CX_PORTING for files that cannot be found in pass 2

  * updated CX_PORTING after pass 2 TODO inspection

  * [ERROR] /Develop/Kuali/svn_kuali_org_kc/coeus-impl/src/main/java/org/kuali/coeus/common/notification/impl/service/impl/KcNotificationServiceImpl.java:[451,16] error: 'catch' without 'try'

  * [ERROR] /Develop/Kuali/svn_kuali_org_kc/coeus-impl/src/main/java/org/kuali/coeus/common/framework/person/KcPerson.java:[75,29] error: variable LOG is already defined in class KcPerson

  * Revert "cx_patches/src/main/java/org/kuali/kra/award/web/struts/action/AwardActionsAction.java.patch"

  * This reverts commit 6602461f15248817d029d055c62b9d694677941d.

  * Conflicts:
	CX_PORTING.md

  * revert changes to cx_patches/src/main/java/org/kuali/kra/award/web/struts/action/AwardActionsAction.java.patch and update CX_PORTING

  * Fixed: AuthTokenGenerator cannot be resolved to a type

  * Fixed: Duplicate field CoiMessagesServiceImpl.LOG

  * Fixed: ProposalPerson cannot be resolved to a type

  * Updated object references for com/rsmart/kuali/kra/workflow/UnitAdministratorRoleAttribute.java. A couple of errors remain that need attention

  * Fixed: The import org.kuali.kra.bo.Unit cannot be resolved

  * Fixed: Unit cannot be resolved to a type

  * Revert "cx_patches/src/main/java/org/kuali/kra/bo/RolodexMaintainableImpl.java.patch"

  * This reverts commit 3aa4c838aae72ce5f0173761a980fa146db83967.

  * Conflicts:
	CX_PORTING.md

  * [ERROR] /Develop/Kuali/svn_kuali_org_kc/coeus-impl/src/main/java/com/rsmart/kuali/kra/workflow/UnitAdministratorRoleAttribute.java:[111,130] error: cannot find symbol
  * [ERROR] symbol:   method getUnitAdministratorTypeCode()
  * [ERROR] location: class UnitAdministratorType
  * [ERROR] /Develop/Kuali/svn_kuali_org_kc/coeus-impl/src/main/java/com/rsmart/kuali/kra/workflow/UnitAdministratorRoleAttribute.java:[128,135] error: cannot find symbol

  * CX 6 now compiles! w00t! Next bind to our fork of rice

  * bind to CX rice version 2.5.2.0-kualico-SNAPSHOT

  * Fixes: Caused by: java.lang.ClassNotFoundException: com.rsmart.kuali.coeus.data.migration.FlywayMigrator

  * very close to getting CX6 to start

  * Disable HR REST API until we can resolve: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'hrImportService' defined in class path resource [com/rsmart/kuali/coeus/hr/JerseyBeans.xml]: Instantiation of bean failed; nested exception is org.springframework.beans.BeanInstantiationException: Could not instantiate bean class [com.rsmart.kuali.coeus.hr.service.impl.HRImportServiceImpl]: Constructor threw exception; nested exception is java.lang.NoClassDefFoundError: org/kuali/kra/bo/PersonAppointment

  * Fixes ERROR 1064 (42000) at line 17: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near '--creates additional attribute for krms data validation rules

  * Added a quick hack to apply KC6 upgrade SQL/DML

  * updated CX_PORTING with new SQL error info

  * Fixes dml/KC_DML_02_KCINFR-979_B000.sql error

  * Erronous duplicate entry in mysql file

  * updated CX_PORTING.md for REST API progress

  * added todo for REST API spring context errors

  * Manually merged PD authorizer changes into PDDocAuthorizer

  * Merged in changes to KcAuthorizationBase and KcTransDocActionBase

  * resolved REST API startup issue

  * AwardActionsAction merged and other files confirmed

  * Relevant changes from patch applied to Rolodex.java

  * S2SUtil patch changes being applied upstream in KC
  * Lance Speelmon on Tue, 25 Nov 2014 10:53:18 -0700 [View Commit](../../commit/1ae2630486ac0ef911c1ffa8127b9e15ae60d1ce)
* CX Reporting Framework

  * cx_patches/src/main/webapp/WEB-INF/struts-config.xml.patch

  * cx_patches/src/main/webapp/WEB-INF/web.xml.patch

  * cx_patches/src/main/java/com/rsmart/kuali/kra/reporting/web/struts/action/ReportForwardAction.java.patch

  * cx_patches/src/main/java/com/rsmart/kuali/kra/reporting/web/struts/form/ReportFormwardForm.java.patch
  * Lance Speelmon on Mon, 8 Dec 2014 16:46:02 -0700 [View Commit](../../commit/af713a3c60c7b2ab3c738854ddcd08d1ad16f000)
* CX Skinning Changes from 5.2.1

  * cx_patches/src/main/java/com/rsmart/kuali/kra/web/jstl/PortalFunctions.java.patch

  * cx_patches/src/main/resources/META-INF/kc-config-defaults.xml.patch

  * cx_patches/src/main/webapp/css/bootstrap/css/bootstrap-skinned.css.patch

  * cx_patches/src/main/webapp/css/bootstrap/css/bootstrap.css.patch

  * cx_patches/src/main/webapp/css/bootstrap/*

  * cx_patches/src/main/webapp/css/images/bl-bevel-focus.gif.patch

  * cx_patches/src/main/webapp/css/rsmart.css.patch

  * cx_patches/src/main/webapp/rice-portal/scripts/easyXDM/resize_intermediate.html.patch

  * cx_patches/src/main/webapp/scripts/bootstrap/bootstrap.js.patch

  * cx_patches/src/main/webapp/sponsorHierarchyMaint.jsp.patch

  * cx_patches/src/main/webapp/static/images/checkbox.gif.patch

  * cx_patches/src/main/webapp/static/images/checked.gif.patch

  * cx_patches/src/main/webapp/unitHierarchy.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/award/awardHierarchyAwardActionsAjax.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/coi/FinancialEntityEditList.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/coi/FinancialEntityEditNew.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/coi/FinancialEntityManagement.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/coi/FinancialEntityView.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/coi/ViewNotification.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/iacuc/IacucResearchAreaAjax.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/irb/researchAreaAjax.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/meeting/IacucMeetingActions.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/meeting/IacucMeetingCorrespondenceAction.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/meeting/IacucMeetingManagement.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/meeting/meetingActions.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/meeting/meetingCorrespondenceAction.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/meeting/meetingManagement.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/multicampus_dummy_login.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/proposaldevelopment/ProposalDevelopmentKeyPersonnel.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/proposaldevelopment/ProposalDevelopmentProposal.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/jsp/timeandmoney/awardHierarchyTimeAndMoneyAjax.jsp.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/budget/budgetVersions.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/coi/approveAction.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/iacuc/onlinereview/onlineReview.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/infopage.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/irb/onlinereview/onlineReview.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/medusa/medusaNegotiationSummary.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/questionnaire/qnInfopage.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/channel/researcherAwards.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/channel/researcherCompliance.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/channel/researcherPersonnel.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/channel/researcherQuicklinks.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/channel/researcherReportLinks.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/channel/unitQuicklinks.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/portalBody.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/portalResearcherBody.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/portalTabs.tag.patch

  * cx_patches/src/main/webapp/WEB-INF/tlds/portal-func.tld.patch

switch back to more genric version of CX skin

  * replace rsmart logos with kualico
  * Lance Speelmon on Mon, 1 Dec 2014 14:03:22 -0700 [View Commit](../../commit/2cb2af80492a7d8488be14f8bcc05b80e9fd73d8)
* Standardizing version
  * blackcathacker on Tue, 13 Jan 2015 19:57:12 -0800 [View Commit](../../commit/6105b56370890029df004a4ac62aa114f1322d19)
* Updates to sites styles to match cx-qa
  * Brandon Nicholls on Wed, 21 Jan 2015 11:50:32 -0700 [View Commit](../../commit/70c9a2749eed3c541f5659b271b6ce01bf5d3e6a)
* Updates to flyway db conversion
  * blackcathacker on Tue, 3 Feb 2015 12:41:43 -0800 [View Commit](../../commit/03b556e3509b8c5a2c906410fcc17e2492ea0bfd)
* Rewrite login page to assist styling
  * blackcathacker on Thu, 22 Jan 2015 17:18:05 -0800 [View Commit](../../commit/db15cc386c8fdf6fea3b6301357be22d4b5398fd)
* Fixes several styling issues:
* Logo is now the KualiCo logo
* Login page not table based anymore, styled correctly (although still hacked)
* Dropdown menus now look correct on large screens as well as small
* Colors on the proposal screens are now longer red.
  * Brandon Nicholls on Tue, 27 Jan 2015 21:40:08 -0700 [View Commit](../../commit/6f5d666b4dc4a472d17c2ad73754ab2138fc9e6a)
* Repackage com.rsmart
  * blackcathacker on Tue, 3 Feb 2015 17:54:57 -0800 [View Commit](../../commit/6500799ded24611653a7f43ca29d99af41b2fa38)
* CX Merge Code Review Comment Changes
  * blackcathacker on Wed, 4 Feb 2015 11:41:45 -0800 [View Commit](../../commit/0024f17123dd4eb3b7f98e79e20dd63adcfb3f15)
* KRACOEUS-8791: Adding copy info to header
  * Gayathri on Thu, 5 Feb 2015 12:12:39 -0700 [View Commit](../../commit/681017e8fd4adf29bef691f17c85b2d5f36a6321)
* KRACOEUS-7992: adding copy message
  * Gayathri on Thu, 5 Feb 2015 15:01:35 -0700 [View Commit](../../commit/866c1b1e16b1e7d79cbdc3c3ca7109b56e58081f)
* KRACOUES-8792:fixed first period budget copy
  * Joe Williams on Thu, 5 Feb 2015 16:51:34 -0600 [View Commit](../../commit/c84f24c626a0f3bcffddfcdc875a246b03d352eb)
* KRACOEUS-8777:added hierarchy summary to budget module
  * Joe Williams on Thu, 5 Feb 2015 16:59:13 -0600 [View Commit](../../commit/6a7c972a649924e6f2fe54ef094e20b6e890294d)
* KRACOEUS-8799 : Style footer for KualiCo
  * blackcathacker on Thu, 5 Feb 2015 17:34:49 -0800 [View Commit](../../commit/3e137f8236e46611612fda0465f6d9cdb01e9e46)
* KRACOEUS-8782:updated access collection to add with dialog, fixed check that roles have changed
  * Joe Williams on Fri, 6 Feb 2015 08:57:47 -0600 [View Commit](../../commit/77b7a237415ac133a3f3c978ea9ec4dabb173de2)
* KRACOEUS-8777:add missing hierarchybudgetsummary xml
  * Joe Williams on Fri, 6 Feb 2015 09:38:30 -0600 [View Commit](../../commit/ddeef4767f90d78823d609060d60cf0a702ec028)
* KRACOEUS-8796:fix attachment errors preventing canEdit from being evaluated
  * Joe Williams on Fri, 6 Feb 2015 10:12:40 -0600 [View Commit](../../commit/9a2919bd1ad560f40101b3fcb39e0e6fb53df466)
* KRACOEUS-8813: fixing wilcard search
  * Travis Schneberger on Fri, 6 Feb 2015 11:40:54 -0500 [View Commit](../../commit/b3079ac2b27ef71777bfdaf8acb217a7d594393b)
* KRACOEUS-8812:fixed sub award validation error
  * Joe Williams on Fri, 6 Feb 2015 11:17:58 -0600 [View Commit](../../commit/ab0d4124e54a7a3bbdbf177a00074f9ef0b0cc34)
* KRACOEUS-8176
  * Save new line items - budget personnel and non-personnel costs
  * rmancher on Fri, 6 Feb 2015 12:51:31 -0500 [View Commit](../../commit/365819f4f7436bf011a65499d28d4cd192e08275)
* KRACOEUS-8661-fixing personnel sync
  * Gayathri on Fri, 6 Feb 2015 11:45:28 -0700 [View Commit](../../commit/11edfe1104b5ae21b5fe981a6320b08cf61e16bd)
* KRACOEUS-8811: Copy parent changes
  * Gayathri on Fri, 6 Feb 2015 12:27:29 -0700 [View Commit](../../commit/0300c0dda1b7f3cc50c9df39026faaae46f3a96c)
* KRACOEUS-8810:fixed error with non compliance entries not having special review numbers
  * Joe Williams on Fri, 6 Feb 2015 14:38:55 -0600 [View Commit](../../commit/a8a949f31b7212ded0c7d33f1853c0a67305b77b)
* KRACOEUS-8730: Fix copy in view mode
  * Gayathri on Fri, 6 Feb 2015 13:42:07 -0700 [View Commit](../../commit/d49ea5bb0f1d2181dbf6519072ded55bf8b91422)
* KRACOEUS-8820 : Make flyway process configurable
  * blackcathacker on Sat, 7 Feb 2015 23:28:38 -0800 [View Commit](../../commit/b5b6ff7812634ea4d0b94db36c8702ab21c48489)
* KRACOEUS-8817:added create xml to s2s forms page
  * Joe Williams on Mon, 9 Feb 2015 10:41:51 -0600 [View Commit](../../commit/3f10a750c67b1c5f29e9169278db559a8556963f)
* KRACOEUS-8801:add remove from submission to deselect final budget
  * Joe Williams on Mon, 9 Feb 2015 11:36:16 -0600 [View Commit](../../commit/286f70da31a3598cebb4eb11274ad3ed5abe9daf)
* KRACOEUS-8814: adding billing frequency, fixing dd config
  * Travis Schneberger on Mon, 9 Feb 2015 10:32:20 -0500 [View Commit](../../commit/d350ec53a6d05d3474e74d7685beaeac65927003)
* KRACOEUS-8814: deleting sql scripts
  * Travis Schneberger on Mon, 9 Feb 2015 18:30:42 -0500 [View Commit](../../commit/7c762e65956086c235b1c73c7ef8a5146f1fcc2f)
* Revert "KRACOEUS-8814: adding billing frequency, fixing dd config"
  * Travis Schneeberger on Mon, 9 Feb 2015 18:32:53 -0500 [View Commit](../../commit/4093692a6ac9a92993d0c4a30501a15a813ad05d)
* KRACOEUS-8827: returning title
  * Travis Schneberger on Tue, 10 Feb 2015 12:56:00 -0500 [View Commit](../../commit/bb5277eb281bedefeef9c4905a7c6058863036ad)
* KRACOEUS-8829: making krad award lookup use custom lookupable
  * Travis Schneberger on Tue, 10 Feb 2015 18:55:39 -0500 [View Commit](../../commit/6e0b0a1888b1f3914a366d6a625ab5090ac4f716)
* KRACOEUS-6639:code changes for proposal person effort
  * Joe Williams on Thu, 5 Feb 2015 16:48:56 -0600 [View Commit](../../commit/6358143733c126062353a6a5264d27d110878a66)
* KRACOEUS-7942
  * Reset compliance modal.
  * rmancher on Tue, 10 Feb 2015 14:34:37 -0500 [View Commit](../../commit/d4b76c1d8d1998020e9f2e4d039bfbaff6c2576e)
* KRACOEUS-8286
  * Fix to add new period to person salary details when dates change in proposal
  * and default periods are triggered.
  * rmancher on Thu, 5 Feb 2015 11:44:27 -0500 [View Commit](../../commit/9ca70b8dde6e804372c3c4c022a3829ba35414c0)
* KRACOEUS-8833: keyword display fix
  * Gayathri on Wed, 11 Feb 2015 12:43:07 -0700 [View Commit](../../commit/aa6ece39878187077e5edbe5297e174e1796e2cb)
* KRACOEUS-8782:changed access screen add button dynamic to be similiar to key personnel
  * Joe Williams on Wed, 11 Feb 2015 15:21:38 -0600 [View Commit](../../commit/40de74403b250cfeea3961a3200ea63b4cafc17c)
* KRACOEUS-8798: Recall fix
  * Gayathri on Wed, 11 Feb 2015 15:19:56 -0700 [View Commit](../../commit/2bad6c9d84fd9ac02f5b5a6eabff5ab0532319df)
* KRACOEUS-8835: handling delete line from the wizard
  * Travis Schneberger on Wed, 11 Feb 2015 17:35:06 -0500 [View Commit](../../commit/74ec4ecf082dc5e5637b70380431765c2f3608e2)
* KRACOEUS-8832: Linking back
  * Gayathri on Wed, 11 Feb 2015 15:47:33 -0700 [View Commit](../../commit/4145aea9cf3d52b03e0955d40a3085a44a205dd3)
* KRACOEUS-8835: fix cancel button
  * Travis Schneberger on Thu, 12 Feb 2015 07:46:31 -0500 [View Commit](../../commit/7702d508133ab412950a13e6414d8be4edc558bb)
* KRACOEUS-8838: adding missing dd file
  * Travis Schneberger on Thu, 12 Feb 2015 08:54:53 -0500 [View Commit](../../commit/f689f4758b3c211c327a43482e2f123a08cc1ce0)
* KRACOEUS-8761
  * Custom data fields erased when editing Institutional Proposals or Awards
  * rmancher on Thu, 12 Feb 2015 09:48:29 -0500 [View Commit](../../commit/ae7a2246eced3a00815f2e38154ce1cd148e1547)
* KRACOEUS-8826: fixing DD file descriptions
  * Travis Schneberger on Thu, 12 Feb 2015 10:14:18 -0500 [View Commit](../../commit/c6a1c21b8183d08d5d8d3d12d9ddfafc26de1639)
* KRACOEUS-8819: Fixing STE
  * Gayathri on Tue, 10 Feb 2015 13:20:06 -0700 [View Commit](../../commit/fdadea0ffd7af550bbfd1221b6f5055d2d6ed204)
* KRACOEUS-8841: fixing jobCode force uppercase
  * Travis Schneberger on Thu, 12 Feb 2015 11:49:04 -0500 [View Commit](../../commit/7e9fa3528b1261756fa3a8c5bb48aa236642214b)
* KRACOEUS-8301
  * Required fields in custom data is preventing from navigating away from
supplemental information page.
  * rmancher on Thu, 5 Feb 2015 16:24:05 -0500 [View Commit](../../commit/aa036ad77118a40b1877a3447f2b885e1a55acd3)
* KRACOEUS-8842
  * Fix issue - Missing parts of screen on report COI in New projects to disclose
  * rmancher on Thu, 12 Feb 2015 14:47:30 -0500 [View Commit](../../commit/aa9ac53acc3a7d66d24ced90b729c8972b640251)
* KRACOEUS-8815: Fix authorizer
  * Gayathri on Thu, 12 Feb 2015 17:47:41 -0700 [View Commit](../../commit/2639d2171382f75d2060053d394fff39288377c4)
* KRACOEUS-8824 : Auto ingestion changes
  * blackcathacker on Thu, 12 Feb 2015 16:26:04 -0700 [View Commit](../../commit/9b8536d3c2e02b2880b3a0bc8224e98b833bf2b3)
* KRACOEUS-8844: Fixing reject action
  * Gayathri on Thu, 12 Feb 2015 17:53:33 -0700 [View Commit](../../commit/6a6c89efcdb38b7e57c518827e73a7d1657dacf4)
* KRACOEUS-8828:hide s2s opportunity if non-federal sponsor is selected
  * Joe Williams on Tue, 10 Feb 2015 16:15:19 -0600 [View Commit](../../commit/7e3ed1bb2c684f6b126715fa211ed0d828fdd865)
* KRACOEUS-8841:update criteria input field to retreive force upper case from dd attribute defintion
  * Joe Williams on Fri, 13 Feb 2015 11:55:17 -0600 [View Commit](../../commit/8522588893434bce41bf552480a072e7e93e05c0)
* KRACOEUS-8847:add validation to check for multiple units on the same key person
  * Joe Williams on Fri, 13 Feb 2015 15:00:14 -0600 [View Commit](../../commit/d89cdfe65f8f701351ce1c51c2fd4f9fe30fd90e)
* KRACOEUS-8849:perform client side validation on budget navigation
  * Joe Williams on Fri, 13 Feb 2015 15:24:18 -0600 [View Commit](../../commit/429cb74decfcc83a22df67fee3bec93d76990a1a)
* KRACOEUS-8830: removing old files
  * Travis Schneberger on Wed, 11 Feb 2015 10:07:10 -0500 [View Commit](../../commit/1f798e481a3f74e753cf83205617072d5d8308bc)
* KRACOEUS-8830: cleanup, move out grm specific files, setting migration path
  * Travis Schneberger on Fri, 13 Feb 2015 10:43:38 -0500 [View Commit](../../commit/2676d331048081acb13d25fcc6a6cc71a88b2ff1)
* KRACOEUS-8830: moving sql scripts
  * Travis Schneberger on Fri, 13 Feb 2015 10:48:50 -0500 [View Commit](../../commit/e98c9278716bbd83409af7be09486f27180f52f5)
* KRACOEUS-8830: code review comments
  * Travis Schneberger on Fri, 13 Feb 2015 17:16:23 -0500 [View Commit](../../commit/8826a37317b722de934484bef01b01d629447493)
* KRACOEUS-8850: Fix cong districts while copying
  * Gayathri on Fri, 13 Feb 2015 15:25:08 -0700 [View Commit](../../commit/f4998a888d44264cbc84f40d9fbe3e6df3a86b50)
* KRACOEUS-8853: moving data conversion program into kc as a submodule
  * Travis Schneberger on Sat, 14 Feb 2015 11:57:29 -0500 [View Commit](../../commit/3fa7244db48400216cd97424d0c22f55b93b854c)
* KRACOEUS-8837: fixing parameter text
  * Travis Schneberger on Mon, 16 Feb 2015 09:43:43 -0500 [View Commit](../../commit/73abe72776a9a8645c7c33098512d58eb2a44be9)
* KRACOEUS-8454: fix notifications
  * Gayathri on Mon, 16 Feb 2015 08:07:34 -0700 [View Commit](../../commit/614a66ac13d2ec6044bbd021c0f7c6090e7f735a)
* KRACOEUS-8454: Adding SQL
  * Gayathri on Mon, 16 Feb 2015 09:37:08 -0700 [View Commit](../../commit/219679a04a8c9737829af689b6fe8171cf5daae5)
* KRACOEUS-8824 : Fix duplicate current KualiDocument
  * blackcathacker on Mon, 16 Feb 2015 11:05:42 -0800 [View Commit](../../commit/89ab12b325629b7addfd253ca0a942468abdddea)
* KRACOEUS-8824 : Fix duplicate version
  * blackcathacker on Mon, 16 Feb 2015 11:27:01 -0800 [View Commit](../../commit/38f1bf82c7e17a2936d2ca48ea8130c99e9329c1)
* KRACOEUS-8855 : Use more realistic workflow for PD
  * blackcathacker on Mon, 16 Feb 2015 11:54:22 -0800 [View Commit](../../commit/e55925955e02a902144bf9df928035580566c092)
* KRACOEUS-8851:run budget save event when save method is called, fixed issue with non-employee code not being set on new budget person
  * Joe Williams on Mon, 16 Feb 2015 08:33:09 -0600 [View Commit](../../commit/25e170c6cbe957acbaf0dd7f52977c5793b88221)
* KRACOEUS-8840: convert budget periods page to inline editing
  * Joe Williams on Thu, 12 Feb 2015 12:24:35 -0600 [View Commit](../../commit/b84a140d68912968e662843003756473bc019155)
* KRACOEUS-8856: changing message
  * Travis Schneberger on Mon, 16 Feb 2015 16:15:18 -0500 [View Commit](../../commit/50c26e5a2ab6cc530ca6e579ab0bea6fdffdc1d2)
* KRACOEUS-8857:fix styling of add button on subaward page to not display in header of the table
  * Joe Williams on Mon, 16 Feb 2015 15:36:01 -0600 [View Commit](../../commit/4e3ef2d9fa4861773a7242fda85cdb2b879da73a)
* KRACOEUS-8806:disabled subaward view xml if attached file does not contain xml data
  * Joe Williams on Mon, 16 Feb 2015 16:03:35 -0600 [View Commit](../../commit/09e32237e30db33f08c3bd508ad62b5a6212dec5)
* KRACOEUS-8745:added client side file size validation
  * Joe Williams on Mon, 16 Feb 2015 09:26:01 -0600 [View Commit](../../commit/5c22bfd5faa016d1ea0483d0372726f47b71b1a9)
* KRACOEUS-8855 : Code review changes
  * blackcathacker on Mon, 16 Feb 2015 14:38:04 -0800 [View Commit](../../commit/123feac228fe2d12a2935c364f68d79cb150940a)
* KRACOEUS-8860: changing help link
  * Travis Schneberger on Tue, 17 Feb 2015 08:55:19 -0500 [View Commit](../../commit/b640c0324f4985d878baa13126380bb87662289b)
* KRACOEUS-8861:fix progressive navigation to supplement info
  * Joe Williams on Tue, 17 Feb 2015 09:14:48 -0600 [View Commit](../../commit/74caa9abc9edee5e8ab403ca8399cd1d43d79b48)
* KRACOEUS-8862:fix send personnel cert notification buttons to always display, and save personnel when they are sent
  * Joe Williams on Tue, 17 Feb 2015 09:45:48 -0600 [View Commit](../../commit/25f8a157f93808346418bddb07737e2dfc485f32)
* KRACOEUS-8863: changing button text
  * Travis Schneberger on Tue, 17 Feb 2015 10:58:06 -0500 [View Commit](../../commit/936a6420f2d83d23776453c94c8168dea91111f9)
* KRACOEUS-8865: adding budget version
  * Travis Schneberger on Tue, 17 Feb 2015 11:23:28 -0500 [View Commit](../../commit/6b438adb9fbb047f56d9cee26fd8f3e744563c5f)
* ISO-135
  * Gayathri on Tue, 17 Feb 2015 10:28:50 -0700 [View Commit](../../commit/ab01366fed35124d11f2855281cb4163c7abfe0f)
* KRACOEUS-8845: Adding ScaleTwo and ScaleThree decimal support.
  * Gayathri on Mon, 16 Feb 2015 16:32:28 -0700 [View Commit](../../commit/6b6a0ca6a1c5f3b3257c343b98d05b3aac4ac132)
* KRACOEUS-8860: making old portal and new portal use the same help link properties
  * Travis Schneberger on Tue, 17 Feb 2015 12:55:35 -0500 [View Commit](../../commit/68b57ec73e2bc0cdc0bda6d6bc2cbc4d28bd7c45)
* KRACOEUS-8805
  * Budget Settings labels disappear when marked complete
  * rmancher on Tue, 17 Feb 2015 13:43:58 -0500 [View Commit](../../commit/663e502ce022593513764921673b4e9b70c3b6f1)
* SO-138 : Make questionnaire certifcation generic
  * blackcathacker on Tue, 17 Feb 2015 10:46:55 -0800 [View Commit](../../commit/6fcfdb0c9c3e8cbda759d8df9c9f96f59a1fb240)
* KRACOEUS-8846: Question edit fix
  * Gayathri on Tue, 17 Feb 2015 12:58:00 -0700 [View Commit](../../commit/bba344a7045ca0a06422db7c0ec42aff0bf728f6)
* KRACOEUS-8866: increasing column length
  * Travis Schneberger on Tue, 17 Feb 2015 15:14:17 -0500 [View Commit](../../commit/79f2da88961cf53f8ca2bc752fd13268109471df)
* KRACOEUS-8869: prevent PD from opening in iframe
  * Travis Schneberger on Tue, 17 Feb 2015 15:39:37 -0500 [View Commit](../../commit/15c7cd7f1726c4437aa75995901341dd0b9df4aa)
* Set default.htm as an index file so help works when null
  * Even when the help url is null, in the case of a missing help parameter
  * the help index will still show up. Partial fix for rSmart/issues#512
  * blackcathacker on Tue, 17 Feb 2015 13:42:49 -0800 [View Commit](../../commit/132c215f0efdd2067276490813dfb253717e26b6)
* Notification renderer unused
  * Related to investigating rsmart/issues#307 it was noticed this renderer is never referenced or used
  * blackcathacker on Tue, 17 Feb 2015 13:48:24 -0800 [View Commit](../../commit/fcdc0adeae27563e075fdf55fdd78e2ac951959b)
* Workaround OJB bug that causes wrong class to be cached
  * Whenever the Budget.class is cached instead of AwardBudgetExt
  * OJB will only return Budget.class for that ID.

  * Fixes rSmart/issues#510
  * blackcathacker on Tue, 17 Feb 2015 13:54:03 -0800 [View Commit](../../commit/c31c842be236e5dbed45dacc6c5408a1e050b677)
* KRACOEUS-7443: Fix notification email address
  * Gayathri on Tue, 17 Feb 2015 15:58:48 -0700 [View Commit](../../commit/63cebfa4ef6ca0a67405ed3c18b6b2cb4fee12dd)
* KRACOEUS-8870: Set demo cost elements to be active
  * At some point during the 4.0 release cost elements where changed from using 'N' to indicate active to 'Y' being active. Due to the oddities with the old sql scripts the bootstrap script that did this now runs before the demo data that inserts these records. This enables all the demo cost elements.
  * blackcathacker on Tue, 17 Feb 2015 15:25:31 -0800 [View Commit](../../commit/11d1407549ed2fd3e7735abf79e2b5b388214e3d)
* KRACOEUS-8872: Adding warning while deleting attachments.
  * Gayathri on Tue, 17 Feb 2015 16:53:32 -0700 [View Commit](../../commit/a7f4624e65a97dd5b49d0a7a119a48b8eee02271)
* KRACOEUS-8855:Make PD KEW doc standalone and repackage KEW related class
  * The PD KEW File that was being used by CX was apparently dependent on the previous foundation KEW file being ingested. I have copied relevant elements from it such that this file can be ingested into an empty database.
  * blackcathacker on Tue, 17 Feb 2015 16:09:27 -0800 [View Commit](../../commit/a9aa1c154a1c26ddefd29c47a5656dde06922025)
* KRACOEUS-8854 : Fix issue with bad data so script can apply
  * blackcathacker on Sun, 15 Feb 2015 14:15:05 -0800 [View Commit](../../commit/0a1da018c96c302f26a5482783bef36c7582953c)
* KRACOEUS-8871:implement budget progressive navigation
  * Joe Williams on Tue, 17 Feb 2015 16:53:05 -0600 [View Commit](../../commit/6c16496c904410f221b4ea171df6e770ad99feb4)
* KRACOEUS-8875: resequence sql file
  * Travis Schneberger on Wed, 18 Feb 2015 09:05:12 -0500 [View Commit](../../commit/96d2509bd4b56038cd2f5a96564564aa6af91eb4)
* KRACOEUS-8856: fix grammar
  * Travis Schneberger on Wed, 18 Feb 2015 11:26:23 -0500 [View Commit](../../commit/e20cc1e98f1cb6c2b8fb96e43969b4d073fd28d3)
* KRACOEUS-8873: Delivery info changes
  * Gayathri on Wed, 18 Feb 2015 08:46:30 -0700 [View Commit](../../commit/10d0b073f7cc16f70861064ae77df9c3188a2908)
* KRACOEUS-8879: adding header text
  * Travis Schneberger on Wed, 18 Feb 2015 13:38:52 -0500 [View Commit](../../commit/f444865410b1b61e2b9d18f4eb85a2e9e2f2f11e)
* KRACOEUS-8802 Subaward refresh on delete and replace
  * Fix to refresh the modal and clear current period details.

  * update review comments
  * rmancher on Tue, 17 Feb 2015 11:25:39 -0500 [View Commit](../../commit/ac92585b9089a377eb91126d843122b629065e27)
* KRACOEUS-8873: Removing validation while deleting.
  * Gayathri on Wed, 18 Feb 2015 15:49:58 -0700 [View Commit](../../commit/81dd6283ec6ca5918425ad18b575c194f70be92d)
* KRACOEUS-8881:Support for MySQL5.6/MariaDB10
  * This primarily involved making sure to drop the foreign key before modifying a referenced column and limited indexes to 767 bytes.
  * In order to still support Mysql5.5 you also have to make sure that when dropping the foreign keys the names of the contrainsts match exactly.
  * blackcathacker on Wed, 18 Feb 2015 16:21:42 -0800 [View Commit](../../commit/7b9c9681eb223dd81c5697e614c8f6b3880f1ed9)
* KRACOEUS-8880: adding validation to s2s opportunity lookup fields
  * Travis Schneberger on Thu, 19 Feb 2015 09:18:44 -0500 [View Commit](../../commit/149877bcab443afce145477c8a223916e14b89a3)
* KRACOEUS-8809 Refresh budget summary during sync
  * Budget summary is not updated while performing sync operation in parent
  * proposal budget
  * rmancher on Wed, 18 Feb 2015 14:44:38 -0500 [View Commit](../../commit/d3d7d3008b26b67f992ccb5e1269387be80a99e1)
* KRACOEUS-8864:added special review validation
  * Joe Williams on Thu, 19 Feb 2015 10:07:37 -0600 [View Commit](../../commit/ea8a24d2591296408d1d9d5050ca4d649b8e5091)
* KRACOEUS-8831:moved div and program code validation to audit errors
  * Joe Williams on Thu, 19 Feb 2015 12:54:42 -0600 [View Commit](../../commit/6a468602f6ee4f8157edcbce35d6312fdcfa0092)
* KRACOEUS-8876:fix text area inline edit fieldwidth
  * Joe Williams on Thu, 19 Feb 2015 14:22:20 -0600 [View Commit](../../commit/63c925b8fa4657574ba457212bc164559b02a6f0)
* KRACOEUS-8883:implement widget input only for questions with lookups
  * Joe Williams on Thu, 19 Feb 2015 15:50:04 -0600 [View Commit](../../commit/79eeb151b269c8a4cb876ccc5135cd95677bd8e0)
* KRACOEUS-8884:fixed non personnel budget rates
  * Joe Williams on Thu, 19 Feb 2015 16:45:09 -0600 [View Commit](../../commit/fbf28001e2295e48c087c0511d8475def3dd41ab)
* KRACOEUS-8770: KNS, Proposal and Budget Online help
  * Gayathri on Thu, 19 Feb 2015 17:53:00 -0700 [View Commit](../../commit/60f52092152454f2158ba1055bc9662c5d6a33c1)
* KRACOEUS-8885: Removing notify from child proposals
  * Gayathri on Thu, 19 Feb 2015 18:38:57 -0700 [View Commit](../../commit/660cc615e0a7bf2f0baf41f4d15be0748467342c)
* KRACOEUS-7453 Enable unit inquiry
  * Display unit and administrator details
  * rmancher on Fri, 20 Feb 2015 10:36:00 -0500 [View Commit](../../commit/53bd1e178c81b53d714aaa6d7b4b66e1273dfbd8)
* KRACOEUS-8886:perform client side validation on navigation back to proposal
  * Joe Williams on Fri, 20 Feb 2015 09:49:55 -0600 [View Commit](../../commit/0d24748200d4bb6934d2f114f51636761724bff8)
* KRACOEUS-8889: fixing PD routing
  * Gayathri on Fri, 20 Feb 2015 11:07:45 -0700 [View Commit](../../commit/aef03439164dcfd8f23cf32b14cfae76fc8c900f)
* KRACOEUS-8888:create unique id and person name for tbn persons
  * Joe Williams on Fri, 20 Feb 2015 14:35:03 -0600 [View Commit](../../commit/f4be0deee195052ccf31b6e34756450d52cfd32a)
* KRACOEUS-8891:Health Check Page
  * Simple health check page that return 200 on success and 417 on failure.
  * Does simple query against the KC and Rice database to confirm they are accessible
  * blackcathacker on Sun, 22 Feb 2015 15:34:14 -0800 [View Commit](../../commit/cf65617ece8b605feba4a9fe92e7bf31d7ef3728)
* KRACOUES-8890:set budget creator role to only edit budgets
  * Joe Williams on Mon, 23 Feb 2015 09:16:46 -0600 [View Commit](../../commit/cb97f0619570b40f427a42960ce1b3bd1794f916)
* KRACOEUS-7451 Add status option for internal attachments
  * Include status option for add attachments and in the list.
  * Also add mark all status option.
  * rmancher on Mon, 23 Feb 2015 10:59:58 -0500 [View Commit](../../commit/7a5fd354b2fc149ea5d82c9eb769729f1f5ee5cf)
* KRACOEUS-8739: Fixing lookup sort
  * Gayathri on Mon, 23 Feb 2015 11:26:37 -0700 [View Commit](../../commit/54342126657e501a0d40074b068c510e5192616c)
* KRACOEUS-8897: removing references to old commons-lang
  * Travis Schneberger on Mon, 23 Feb 2015 13:26:16 -0500 [View Commit](../../commit/b77d55410c839d411ce40541a8bbd5b1b25e70e7)
* KRACOEUS-8894:allow add viewer permission when document is enroute
  * Joe Williams on Mon, 23 Feb 2015 13:04:28 -0600 [View Commit](../../commit/2df23475e2b3871adb8f16d9cca5f7d488d0845d)
* KRACOEUS-8895:Add CAS as dependency
  * blackcathacker on Mon, 23 Feb 2015 10:49:49 -0800 [View Commit](../../commit/384a628f51858d9b508cda8078da05483f6c4f47)
* KRACOEUS-8682: Hiding create proposal button
  * Gayathri on Mon, 23 Feb 2015 16:27:06 -0700 [View Commit](../../commit/6e3a6ae210449cc6b5d796efbb2d079578063a38)
* KRACOEUS-8892:Various db issues
  * V600_067__KC_DML_01_KRACOEUS-7014_B000.sql and others failed due to the coeus_module description being modified so the module_code as harded as was done during insert and in other locations in code, additionally they modified the special review type 'Animal Subjects' to 'Live Vertebrate Studies' and again this is a code hard-code in sql and in code so no reason to query for it.
  * V601_017__KRACOEUS-8864.sql failed because of conflicting primary keys so using new procedure to find first unused id
  * V600_046__KC_TBL_AWARD_BUDGET_EXT.sql needed to orphan the records in award_budget_limit as well
  * V600_063__KC_DML_01_KFSMI-11381_B000.sql failed because customer had inserted extra record at 999 which meant the next value was 1000. Instead using new procedure to find first available id
  * V300_002__schema.sql and V300_107__schema.sql were modified because of previously unseen errors with mysql 5.6
  * V600_054__KC_TBL_EPS_PROPOSAL_BUDGET_EXT.sql is another example of orphaned records where the proposal was deleted but the budgets weren't
  * blackcathacker on Mon, 23 Feb 2015 18:00:05 -0800 [View Commit](../../commit/2b15e79bb9bd3fce04684bfc914e4e4c05dfd2a8)
* KRACOEUS-8896:fix research strategy s2s error
  * Joe Williams on Tue, 24 Feb 2015 08:32:58 -0600 [View Commit](../../commit/a121f6d39f35b131d789ee8a2434aa5f7ecae777)
* KRACOEUS-8887:add confirmation dialog to complete budget
  * Joe Williams on Fri, 20 Feb 2015 14:17:53 -0600 [View Commit](../../commit/ce21ff2c850c80d7fe9ea33e66bad6225d0b1a3a)
* KRACOEUS-7438: Fixing PI certification while routing
  * Gayathri on Mon, 23 Feb 2015 14:02:03 -0700 [View Commit](../../commit/686f68da9f24934237b5ec8fa5fb800152aa9bc3)
* KRACOEUS-8899:fixed client side date validation
  * Joe Williams on Tue, 24 Feb 2015 11:48:22 -0600 [View Commit](../../commit/29e973127d24d62320b428e32e9f476945f7a401)
* KRACOEUS-8874 Incorrect distributable amount when editing amount in award
  * rmancher on Tue, 24 Feb 2015 09:10:27 -0500 [View Commit](../../commit/61a0bb8e31e55885c90265a1ae253e9ad662915b)
* KRACOEUS-8898:Update doc typ to use new PD URL
  * blackcathacker on Tue, 24 Feb 2015 14:27:33 -0800 [View Commit](../../commit/f036776180f5395a6fdfa63b16f1ead007bd41a2)
* KRACOEUS-8904: adding missing closing tag
  * Travis Schneberger on Wed, 25 Feb 2015 08:40:34 -0500 [View Commit](../../commit/2982988cd70a11dc486396ec50b6c8b0f7bbef28)
* KRACOEUS-8903:complete budget from budget versions page
  * Joe Williams on Wed, 25 Feb 2015 09:22:34 -0600 [View Commit](../../commit/2e7a378487226ad17b067829f68116c0426722e2)
* KRACOEUS-8878: moving external stuff back to the old package
  * Travis Schneberger on Fri, 20 Feb 2015 15:26:35 -0500 [View Commit](../../commit/9b74b87dd1511e6101e11a0e088629809ceebf02)
* KRACOEUS-8878: moving external stuff back to the old package
  * Travis Schneberger on Fri, 20 Feb 2015 16:13:46 -0500 [View Commit](../../commit/53147ca389922676a65bd5982b37f6add5c57de6)
* KRACOEUS-8878: rolling back cgb api changes, fixing external DD files
  * Travis Schneberger on Mon, 23 Feb 2015 13:21:19 -0500 [View Commit](../../commit/61197fd08ace4b6de7dfdffdfc724f6acda648b5)
* KRACOEUS-8878: resequencing sql file
  * Travis Schneberger on Tue, 24 Feb 2015 09:47:46 -0500 [View Commit](../../commit/3c844db62debb972ea9cd32b1aa944cfc81712d7)
* KRACOEUS-8878: fixing package
  * Travis Schneberger on Wed, 25 Feb 2015 11:13:03 -0500 [View Commit](../../commit/f0f7870f82ebb0359611af92533043663a38564f)
* KRACOEUS-8878: code review comments
  * Travis Schneberger on Wed, 25 Feb 2015 14:27:07 -0500 [View Commit](../../commit/17ea473279f11bc95fbd1dc285b4616b8f92c131)
* KRACOEUS-8878: resequencing sql file
  * Travis Schneberger on Wed, 25 Feb 2015 15:35:25 -0500 [View Commit](../../commit/72b396be6de6fb7ef9c881a31f554694d22f7fdb)
* KRACOEUS-8902: Add notification when changing narratives
  * Gayathri on Wed, 25 Feb 2015 13:56:14 -0700 [View Commit](../../commit/7baf3d22ae28b92e6ea61126944c9dae314616c6)
* KRACOEUS-8739: Adding missed change
  * Gayathri on Wed, 25 Feb 2015 14:51:58 -0700 [View Commit](../../commit/706f2b6579f3e94b68bf7cc70452833204bf5a6b)
* KRACOEUS-8909: make sponsor maintenance work without KFS integration
  * Travis Schneberger on Thu, 26 Feb 2015 08:26:31 -0500 [View Commit](../../commit/aa7e7969c3b76b78a0c1f91e18139028512b0320)
* KRACOEUS-8909: fixing role qualifiers for sponsor for quickstart
  * Travis Schneberger on Thu, 26 Feb 2015 09:54:07 -0500 [View Commit](../../commit/0da1feff9db96c9e9e2cccfafb8ca47c242f8a5b)
* KRACOEUS-8909: fixing constraint violation on save
  * Travis Schneberger on Thu, 26 Feb 2015 10:12:46 -0500 [View Commit](../../commit/1a0386df6b515ad6964a609e5fa58a2746c3d589)
* KRACOEUS-8913: swapping field locations
  * Travis Schneberger on Thu, 26 Feb 2015 11:01:29 -0500 [View Commit](../../commit/bee646b0273fa9d4305a8779c45bce1c2929104e)
* KRACOEUS-8914: changing fixed point number message
  * Travis Schneberger on Thu, 26 Feb 2015 11:14:27 -0500 [View Commit](../../commit/1556750bcc25b76d2921da9ab74b781e32fe4d9c)
* KRACOEUS-8390 Add close button to superuser action page
  * rmancher on Thu, 26 Feb 2015 11:28:51 -0500 [View Commit](../../commit/5d072dbc7b87fd9960f67c5c7c51e6c2a0eca394)
* KRACOEUS-8803 Fix unauthorized lead unit options
  * Currently KIM is not checking whether user is associated with the
  * new group. We need a rice fix.
  * This is an interim solution.
  * rmancher on Wed, 25 Feb 2015 14:51:18 -0500 [View Commit](../../commit/dc158b3403c8ccd6e8d366ca9ca4a320f8914b9c)
* KRACOEUS-8905:display s2s errors when print invalid xml
  * Joe Williams on Fri, 27 Feb 2015 08:08:31 -0600 [View Commit](../../commit/638e3c932d570f06c7b0dd3ad34b41520af829a2)
* KRACOEUS-8918: adding not found message
  * Travis Schneberger on Fri, 27 Feb 2015 10:18:19 -0500 [View Commit](../../commit/fcc1b86707b791c3ece17c13411b68dc4c89aa3b)
* KRACOEUS-8919: changing field length
  * Travis Schneberger on Fri, 27 Feb 2015 10:25:46 -0500 [View Commit](../../commit/a9abe0e82e3b014d7c93200a905d8039f1081c8d)
* KRACOEUS-8920:added inquiry link to arg value custom data
  * Joe Williams on Fri, 27 Feb 2015 12:15:03 -0600 [View Commit](../../commit/55f79fe4c5ba3b6addabca216d5117cd781b4e8d)
* KRACOEUS-8176 Save budget to reflect totals in periods and budget
  * Adding a line item and closing window should recalcuate budget and
  * persist line item. We need to persist budget in this scenario since
  * we need to update periods and totals and budget totals.
  * rmancher on Fri, 27 Feb 2015 16:23:15 -0500 [View Commit](../../commit/c5daab6f9d347b1a7da0371e4726794eca2de41b)
* KRACOEUS-8767: Fixing help links
  * Gayathri on Fri, 27 Feb 2015 15:51:20 -0700 [View Commit](../../commit/8ec7e5887978dbcfacf80d85e6d134283364f357)
* KRACOEUS-8868: increasing reqID length
  * Gayathri on Fri, 27 Feb 2015 16:38:09 -0700 [View Commit](../../commit/b67b44adbef02c03923ecf14168095b9f2a56f1e)
* Revert "KRACOEUS-8803 Fix unauthorized lead unit options"
  * Douglas Pace on Fri, 27 Feb 2015 18:00:16 -0800 [View Commit](../../commit/87d09dd52eccab5e742b742d2b4017b5c541b547)
* KRACOEUS-8905:generate module periods when chnage budget settings
  * Joe Williams on Mon, 2 Mar 2015 09:56:03 -0600 [View Commit](../../commit/5faa4fda4c723dae30f5614006bb709113e21bb7)
* KRACOEUS-8903:mark budget incomplete from budget versions
  * Joe Williams on Mon, 2 Mar 2015 11:35:11 -0600 [View Commit](../../commit/9cb329e277eed5700e37736df73f66ab3ceed3d8)
* KRACOEUS-8839:fix attachment data
  * Joe Williams on Mon, 2 Mar 2015 13:21:19 -0600 [View Commit](../../commit/617054110223f667970ccf22e90a5186b23127ed)
* KRACOEUS-8920:added arg value inquiries to questionnaire
  * Joe Williams on Mon, 2 Mar 2015 14:20:54 -0600 [View Commit](../../commit/94307eb585e2032318147bfbed3413437d38c11a)
* KRACOEUS-8839:Fix up commit related to attachments
  * Rename base class to match up with attachment dao given other different usages for AttachmentDataSource.java
  * Add javadoc and other clean up.
  * blackcathacker on Mon, 2 Mar 2015 18:04:13 -0700 [View Commit](../../commit/942939dcd5c4d986603923b41a09184a572339e6)
* KRACOEUS-8839:Code review changes
  * blackcathacker on Mon, 2 Mar 2015 21:10:30 -0700 [View Commit](../../commit/48644b1636a1f90e03a96ab708a49a164615497e)
* KRACOEUS-8891:Reduce logging on health checks
  * We are using the health check to make sure elb knows the server is up, but the performance logging log messages showing up every couple of seconds is annoying and expensive.
  * blackcathacker on Mon, 2 Mar 2015 22:39:43 -0700 [View Commit](../../commit/3241887a1167910856445eb7c8497ed253f1f2c9)
* KRACOEUS-8925:save budget on modular sync
  * Joe Williams on Tue, 3 Mar 2015 08:29:45 -0600 [View Commit](../../commit/6647d3a68e113c7c721d494697a8007104480843)
* KRACOEUS-8740:wrap long medusa text fields
  * Joe Williams on Tue, 3 Mar 2015 12:58:04 -0600 [View Commit](../../commit/2591263e27575bcf0eed36df2a242e0c550fcff4)
* KRACOEUS-8927:removed broken budget rule
  * Joe Williams on Tue, 3 Mar 2015 13:30:45 -0600 [View Commit](../../commit/f27cd8389153dc3ea47c07d8ba6446d5853887e6)
* KRACOEUS-8912: Add ability to certify enroute proposal
  * Gayathri on Tue, 3 Mar 2015 12:35:29 -0700 [View Commit](../../commit/ba4503daf9afdcc963dc0b414640135a109647b2)
* KRACOEUS-8924: hierarchy sync trace fix
  * Gayathri on Wed, 4 Mar 2015 09:23:35 -0700 [View Commit](../../commit/efddcd6130634812811f75975136ad74cad95125)
* KRACOEUS-8929:resize navigation menu drop downs when window size changes
  * Joe Williams on Wed, 4 Mar 2015 10:23:57 -0600 [View Commit](../../commit/7a4d6bac1635c7d4c009358a7bfee01c781b3414)
* KRACOEUS-8748, KRACOEUS-8930:
* Adding rule to check if a record can be deleted before submit (supports OJB, JPA, and DD relationships)
* Making all KC maintenance docs inherit from KcMaintenanceDocumentEntry (ensures that all maintenance docs use KcMaintenanceDocumentRuleBase if not specified
* Making all Kc Rules extend from KcMaintenanceDocumentRuleBase
* Fixing bootstrap skinning so that GLOBAL_ERRORS are visible
  * Travis Schneberger on Wed, 4 Mar 2015 14:03:04 -0500 [View Commit](../../commit/45168d24cc4a151c04e2e84c44918d48e815c7a8)
* KRACOEUS-8928: upgrading wss4j
  * Travis Schneberger on Wed, 4 Mar 2015 16:05:26 -0500 [View Commit](../../commit/f4ca95b06fa49cb90fd0ce9dabaeec6fa41309a2)
* KRACOEUS-8933: data cleanup, add constraints
  * Travis Schneberger on Thu, 5 Mar 2015 09:59:08 -0500 [View Commit](../../commit/3962401b85a683270189c2c784349ede962289cb)
* KRACOEUS-8748: code review comments
  * Travis Schneberger on Thu, 5 Mar 2015 10:14:50 -0500 [View Commit](../../commit/12fd321b813b96f3d59a29789c4fed23600e9841)
* KRACOEUS-8935: adding logging
  * Travis Schneberger on Thu, 5 Mar 2015 11:45:08 -0500 [View Commit](../../commit/cec39f1e9a42f6efe3947994b83a9529a58cddd0)
* KRACOEUS-8839:Ensure validations don't grab attachments
  * blackcathacker on Thu, 5 Mar 2015 12:10:37 -0700 [View Commit](../../commit/fb4e7689345b38158183c91ac5e6fc8927e1bba1)
* KRACOEUS_8938:fix some integration tests
  * Joe Williams on Fri, 6 Mar 2015 10:34:09 -0600 [View Commit](../../commit/2ba3289808dafaad309644ab7964e3fd1b9252d9)
* KRACOEUS-8940:fix reject action
  * Joe Williams on Fri, 6 Mar 2015 16:11:20 -0600 [View Commit](../../commit/9716421d803246f6a9ae4cbbadec758d604cae91)
* KRACOEUS-8937: avoiding an NPE on orphaned records
  * Travis Schneberger on Fri, 6 Mar 2015 20:20:25 -0500 [View Commit](../../commit/60e96b1d29bb46cabaeb9520c3aae72fe8aea0e4)
* KRACOEUS-8934: grm profiles
* moved grm specific items to a profile (maven and spring)
* fixed several pom issue from code merge
* fixed the ReportAction that is clearly broken
* made all grm internal dependencies runtime scope so we do not have compile time dependencies
* confirmed app builds and starts with the grm profile enabled and disabled with the correct functionality
  * Travis Schneberger on Fri, 6 Mar 2015 13:01:36 -0500 [View Commit](../../commit/af7ae9c108a974a814c1bc5a2741f40255984be9)
* KRACOEUS-8786:fix subaward validation
  * Joe Williams on Mon, 9 Mar 2015 09:44:03 -0500 [View Commit](../../commit/597228500c5053ea41146fb9c25c352669e421ea)
* KRACOEUS-8936:fix unit hierarchy view
  * Joe Williams on Mon, 9 Mar 2015 11:35:15 -0500 [View Commit](../../commit/5763260fdcbdca17f1b3c97dccfea92dafe7ea39)
*  fixing hr import
  * Travis Schneberger on Mon, 9 Mar 2015 15:33:38 -0400 [View Commit](../../commit/d11559e28810e38dcc86878e07311f74dda7b1a9)
* KRACOEUS-8931: fixing mysql file placement
  * Gayathri on Mon, 9 Mar 2015 15:15:44 -0700 [View Commit](../../commit/67d49af07d9490a5b477abf71732d666d983b51f)
*  supporting grm sql files
  * Travis Schneberger on Mon, 9 Mar 2015 23:41:30 -0400 [View Commit](../../commit/c15970262dc57fc22f473437ec2b1992ebf5e12f)
* KRACOEUS-8931: oracle scripts for release 6.0.1
  * Gayathri on Mon, 9 Mar 2015 14:40:01 -0700 [View Commit](../../commit/deb2667c659616e8e11e7481791a287e9f7e36b1)
*  Award locks fix
  * Gayathri on Tue, 10 Mar 2015 15:04:11 -0700 [View Commit](../../commit/42ef8b17a34420e6bff58d6d1c69f4a3822ffcd3)
* fix attachment errors when linking to child from parent
  * Joe Williams on Wed, 11 Mar 2015 10:33:15 -0500 [View Commit](../../commit/5beb381422c896a3623eaa0908d536e9e22731eb)
*  using a set to remove duplicate unit qualifications
  * Travis Schneberger on Wed, 11 Mar 2015 13:58:12 -0400 [View Commit](../../commit/f55da36e837ffdcc39b89760753940b5d8ca9603)
*  fixing int tests for the grm profile
  * Travis Schneberger on Wed, 11 Mar 2015 21:23:10 -0400 [View Commit](../../commit/db2eea4081080f78157a08669d4a1cccd3224e93)
* KRACOEUS-8902: fix notification text
  * Gayathri on Thu, 12 Mar 2015 10:54:23 -0700 [View Commit](../../commit/a4484743ea134628ed757d6fc1c64bdddd0fabba)
* KRACOEUS-8947: fixing data length problems, a serialization issue after submit, no need for oracle script change
  * Travis Schneberger on Thu, 12 Mar 2015 17:34:41 -0400 [View Commit](../../commit/8d4191bf20a98bbac28fb060c3b4903d0831b14d)
* Fix license in files
  * blackcathacker on Thu, 12 Mar 2015 11:24:03 -0700 [View Commit](../../commit/237357f04bff1866ab6a099c184f19786924a7cd)
* fix enroute attachment saving
  * Joe Williams on Fri, 13 Mar 2015 09:16:56 -0500 [View Commit](../../commit/d779cdb339a5adb7365e6a1a938d666c39a4e41c)
* return authorization exception messaeg instead of STE
  * Joe Williams on Fri, 13 Mar 2015 15:22:01 -0500 [View Commit](../../commit/bc1a27dc1dbeec2eb35aabc71946f4746698ed19)
* KCSUPPORT-661: adding documentation, fixing bug related to http://bugs.java.com/view_bug.do?bug_id=6476706
  * Travis Schneberger on Fri, 13 Mar 2015 21:09:05 -0400 [View Commit](../../commit/662d50db4abbe64022a44a7ffae43ff6f24fbf6c)
*  adding documentation
  * Travis Schneberger on Fri, 13 Mar 2015 21:48:55 -0400 [View Commit](../../commit/e63af1d39cecdfbc28e064d7ea6039efcdfcb59c)
* fix STE on unlink hierarchy
  * Joe Williams on Mon, 16 Mar 2015 09:06:49 -0500 [View Commit](../../commit/133f49a1f5053263661403c2b33ff8f8f2f930f1)
* KRACOEUS-8949: releasing s2s
  * Travis Schneberger on Mon, 16 Mar 2015 10:18:12 -0400 [View Commit](../../commit/4bf6fc11c1d7e71abb46a4260e01b00781dea35d)
* Make debug logging debug
  * blackcathacker on Mon, 16 Mar 2015 09:56:34 -0700 [View Commit](../../commit/dc19e393ad5def2beeb2ffb12240865082d238ab)
* Update pom.xml  * Travis Schneeberger on Mon, 16 Mar 2015 14:24:25 -0400 [View Commit](../../commit/4580ff0b6e7541519476735526612290a0fb3dbb)
* next iteration
  * Travis Schneberger on Mon, 16 Mar 2015 15:23:47 -0400 [View Commit](../../commit/0bf3f2b0b75a36de50a19ac259c5a537f8cfad0a)
* Support supplying test config via -Dvar=value
  * In order to support more easily configuring the CI server we want to move to -Dvar=value configuration for integration tests. By making these params not override previous values the -Dvar values are automatically pulled in and used.
  * blackcathacker on Mon, 16 Mar 2015 13:43:03 -0700 [View Commit](../../commit/bd7b6d440875a4bf445347aa6cdcda3ca702aa99)
* fix subaward attachment data
  * Joe Williams on Tue, 10 Mar 2015 16:55:33 -0500 [View Commit](../../commit/3b8220e13d57a0c148d55eecb1db52620e39d378)
* fix attachment deletion on copy and hierarchy
  * Joe Williams on Wed, 11 Mar 2015 13:24:19 -0500 [View Commit](../../commit/b3488496f6b224525b13f433eb95cdaae2a865b0)
* added budget category to non-budget add and edit modals
  * Joe Williams on Thu, 12 Mar 2015 14:34:01 -0500 [View Commit](../../commit/9b978e22af59cfa60c3b4b720495eaf70d7203cd)
* fixed extended attribute biosketch validation
  * Joe Williams on Tue, 17 Mar 2015 10:11:25 -0500 [View Commit](../../commit/6a4a190622ad563ad15fdc94d246acb5226038cc)
*  fixing budget rules
  * Gayathri on Mon, 16 Mar 2015 15:19:26 -0700 [View Commit](../../commit/f1be1a318f120bf0f3b9b9e62ed9b6688a2ae40b)
*  info text
  * Gayathri on Tue, 17 Mar 2015 12:28:12 -0700 [View Commit](../../commit/1dfae9b559380ca2c39e22b286c7d684b7cd317c)
*  IACUC and IRB help fixes
  * Gayathri on Wed, 11 Mar 2015 17:10:28 -0700 [View Commit](../../commit/f7e55fa9231bfd5d074ac54d8140489ad4b36113)
* fix sql scripts
  * Joe Williams on Wed, 18 Mar 2015 08:33:28 -0500 [View Commit](../../commit/98f7c4632c10426a1b688eb9d8613f442c001bb7)
* dismiss add person modal on add person request
  * Joe Williams on Wed, 18 Mar 2015 12:39:36 -0500 [View Commit](../../commit/47bc4c819ca6f8d87854d6a1a61760ee20ed9595)
*  approver cannot view budget
  * Gayathri on Wed, 18 Mar 2015 15:28:39 -0700 [View Commit](../../commit/ab3135b5d0e8e0e3d451bc860c071c4f640b03ed)
*  more help fixes
  * Gayathri on Thu, 12 Mar 2015 15:38:39 -0700 [View Commit](../../commit/e4be54df3781059509869faf9ce205be8388ec3b)
*  adding schemaspy to our project
  * Travis Schneberger on Wed, 18 Mar 2015 17:36:02 -0400 [View Commit](../../commit/f453ad29308a3220c8b5affe202f7f058fe62bd7)
* KCSUPPORT-661: properly reading logging files from the classpath
  * Travis Schneberger on Wed, 18 Mar 2015 20:18:57 -0400 [View Commit](../../commit/d9c57d4c0e914e71839cdd6e29ceebffc58edfd2)
*  turning on flyway grm by default
  * Travis Schneberger on Thu, 19 Mar 2015 08:08:02 -0400 [View Commit](../../commit/dba00aac7ff22230ddda6560ab5983056502e95a)
*  fixing coeus-it dependency and build order
  * Travis Schneberger on Thu, 19 Mar 2015 10:00:35 -0400 [View Commit](../../commit/e4cdd867c4ef8de28e23205626097cb5e26d62a3)
*  fixing staging data
  * Travis Schneberger on Thu, 19 Mar 2015 10:41:56 -0400 [View Commit](../../commit/4338fdb21b561862ba26e8ede085c28102aa91d6)
*  resequencing db script
  * Travis Schneberger on Thu, 19 Mar 2015 10:44:03 -0400 [View Commit](../../commit/8e5c516212ec97cb1250c717efa9eedb550cc48c)
* Disable OJB mappings for subaward attachments
  * Subawards are only used by prop dev and therefore only relevent under JPA
  * blackcathacker on Thu, 19 Mar 2015 11:01:33 -0700 [View Commit](../../commit/6ad78c73a8bc378d3bd1011014478ac582c6c330)
* OJB mapping test configurable by system prop
  * In order to support configuring Jenkins CI test runs through Jenkins itself we need to be able to configure the OJB mapping test through system props
  * blackcathacker on Thu, 19 Mar 2015 11:04:51 -0700 [View Commit](../../commit/4edf73fa983f9bed8da46e8f0de29a9298dedf3f)
* Fix remaining integration tests
  * This also makes it so system properties always override configuration params
  * blackcathacker on Thu, 19 Mar 2015 20:02:06 -0700 [View Commit](../../commit/97d189cb3f616109c3f363bf62ec9467b06f9966)
* get xml file from subaward
  * Joe Williams on Fri, 20 Mar 2015 08:34:58 -0500 [View Commit](../../commit/13c43dc9386e327e7eb1929d57af27554c631493)
*  updating error prone compiler, fixing several bugs found by compiler
  * Travis Schneberger on Fri, 20 Mar 2015 09:41:19 -0400 [View Commit](../../commit/1ef2966853ddc1328f74fc02ada486a926340dee)
* allow edit/copy of person training
  * Joe Williams on Fri, 20 Mar 2015 11:06:09 -0500 [View Commit](../../commit/9e29536a64c4d0cffe4eaf25cc6d456b359575ac)
* fix STE on award notice print
  * Joe Williams on Fri, 20 Mar 2015 15:40:06 -0500 [View Commit](../../commit/adc64c4239a0b93f2f4a9df97a944f4062c5bed0)
* set default period to calendar
  * Joe Williams on Fri, 20 Mar 2015 17:01:31 -0500 [View Commit](../../commit/5b4be2c7bf7c82dc432f76182d9d8bda7ce1f8cc)
*  Verify that the Instrumentation Configuration is correct
  * Travis Schneberger on Sat, 21 Mar 2015 21:21:40 -0400 [View Commit](../../commit/e208c6be03ab8e444a5254083b3e98714bd2890e)
* add inflation rate info to add budget line item modals
  * Joe Williams on Thu, 19 Mar 2015 16:09:11 -0500 [View Commit](../../commit/40561a4ba47d0cf06a7b9e53ceeb9643a6c58bec)
* create s2s error for missing citizenshiptype instead of throwing exception
  * Joe Williams on Mon, 23 Mar 2015 10:16:26 -0500 [View Commit](../../commit/80c865922b44fb588e89a14d69bf4665248ca649)
* fix award labels on medusa view
  * Joe Williams on Mon, 23 Mar 2015 14:22:30 -0500 [View Commit](../../commit/32163e1b7400461130fde7d17293242cb9f1a799)
* fix STE on navigation to award budget summary
  * Joe Williams on Tue, 24 Mar 2015 09:33:42 -0500 [View Commit](../../commit/7dded8ecc47fcb1575a887b4d6e8150fb2a0049d)
* added campus code and unit quickfinders to add personnel wizard
  * Joe Williams on Tue, 24 Mar 2015 10:21:29 -0500 [View Commit](../../commit/27e5fec98788f52c467d05666babee428da27e39)
* filter on inflation rateClassType and add ID to table
  * Joe Williams on Tue, 24 Mar 2015 11:43:16 -0500 [View Commit](../../commit/b5be2afb71ccbe6cc22dd148535812ded8e0aeec)
* fix STE on award budget navigation
  * Joe Williams on Tue, 24 Mar 2015 16:41:34 -0500 [View Commit](../../commit/4c1371b4689abd9ea38d842d37adbe0917e1c739)
* fix print s2s xml button
  * Joe Williams on Wed, 25 Mar 2015 13:15:33 -0500 [View Commit](../../commit/572e2d6cf531cba3c986cb7a86a81c38d95a09ca)
* RESKC_239:update attachment handling to be compatible with oracle and mysql
  * Joe Williams on Wed, 25 Mar 2015 12:34:23 -0500 [View Commit](../../commit/40bf50c3ab7ed4ee560a20ef6a908f43f6a89008)
* fix issue with escalating award budget total costs
  * Joe Williams on Wed, 25 Mar 2015 14:34:40 -0500 [View Commit](../../commit/f07ed082e40e3fecce2923c64f07a02fbf85c942)
* save budget when applying rates to later periods
  * Joe Williams on Wed, 25 Mar 2015 16:23:20 -0500 [View Commit](../../commit/2e7aa920e36fe2ffb550b73e1fc797a679e8117a)
* once a budget line item is added summary rows are no longer editable
  * Joe Williams on Wed, 25 Mar 2015 16:33:47 -0500 [View Commit](../../commit/e3ba5d7beb67c6f5600561ad2095d590296b3a5b)
* Fix GRM SQL Error
  * blackcathacker on Thu, 26 Mar 2015 16:44:04 -0700 [View Commit](../../commit/c7113ca039623421b63f44ac81f51e1ea0706ef9)
* View questionnaire using table instead of doc
  * This is to avoid serialization issues that occur due to questionnaire class repackaging
  * blackcathacker on Thu, 26 Mar 2015 17:52:57 -0700 [View Commit](../../commit/f0a41a0001fa963fe965483c29b3686aec0d92bb)
* Add citizenship type to clear error
  * blackcathacker on Thu, 26 Mar 2015 19:51:35 -0700 [View Commit](../../commit/a73050fea047564acbeefa8569ffa86990b3c2ce)
* Modify and refactor unit and jetty memory arguments
  * blackcathacker on Fri, 27 Mar 2015 15:36:55 -0700 [View Commit](../../commit/4e7152bf30f5a6f8a8863bba3b10cb993d366396)
* Refactoring UnitServiceImpl
  * Travis Schneberger on Tue, 31 Mar 2015 11:43:05 -0400 [View Commit](../../commit/f4b6e5137e7c601695ce9d58417484cb16af9721)
* Refactor/test time and money trans history
  * blackcathacker on Tue, 31 Mar 2015 16:52:08 -0600 [View Commit](../../commit/1f39073e2a318206b794ea2fe3eb737addeb5055)
* Fix up abstractBudgetService
  * Joe Williams on Thu, 2 Apr 2015 13:01:34 -0600 [View Commit](../../commit/3a9dc171391f24bbfb27a2b46fb998672504a7d6)
* Cleanup dead code in TimeAndMoneyHistoryServiceImpl
  * blackcathacker on Wed, 1 Apr 2015 15:59:19 -0600 [View Commit](../../commit/5a6f2e5102bd766a450cf6a3ec35723fc5a8d69e)
* cleanup dead pd code, fixing precompilation step, fixing jsp compilation failures
  * Travis Schneberger on Fri, 3 Apr 2015 10:39:19 -0400 [View Commit](../../commit/b8280d98a718769e4be368fa4580998ba6273e94)
* dead js cleanup
  * Travis Schneberger on Fri, 3 Apr 2015 11:40:05 -0400 [View Commit](../../commit/59baafe9d00379094d7d7073e974ed291bba7e23)
* attempting to fix precompile
  * Travis Schneberger on Fri, 3 Apr 2015 14:10:28 -0400 [View Commit](../../commit/88de6f425229e94204c491ec313fb6f5627e5839)
* Revert "attempting to fix precompile"
  * Travis Schneeberger on Fri, 3 Apr 2015 14:19:23 -0400 [View Commit](../../commit/0bb8dd92b3f020ed3bf5b558f13d0fde5a1dc97f)
* attempting to fix precompile
  * Travis Schneberger on Fri, 3 Apr 2015 16:24:44 -0400 [View Commit](../../commit/c121570946cd8c1a4672ba3ade7ec2630ec8b86d)
* attempting to fix precompile
  * Travis Schneberger on Fri, 3 Apr 2015 17:17:51 -0400 [View Commit](../../commit/d18ad43b5ef50726b0e463c9f695a47f153fb4a1)
* attempting to fix precompile
  * Travis Schneberger on Fri, 3 Apr 2015 18:09:57 -0400 [View Commit](../../commit/778e3d1004c1786f7ae65da72a40d031af8ad4fb)
* Revert "attempting to fix precompile"
  * Travis Schneeberger on Fri, 3 Apr 2015 18:15:28 -0400 [View Commit](../../commit/aee775ffe111bdcbb7e417ae4a7750300e688042)
* Update pom.xml  * Travis Schneeberger on Fri, 3 Apr 2015 20:30:22 -0400 [View Commit](../../commit/8e0e708802ae702e1f64ea6e6cf4bcf4806d3a1f)
* Update pom.xml  * Travis Schneeberger on Sat, 4 Apr 2015 00:50:06 -0400 [View Commit](../../commit/35e0dd33e841e1c606fad02734d802332e9773ab)
* Update pom.xml  * Travis Schneeberger on Sat, 4 Apr 2015 09:28:24 -0400 [View Commit](../../commit/768e003ff526fc0a826f7d5916491db80ccf46ba)
* release process
  * Travis Schneberger on Mon, 6 Apr 2015 10:16:45 -0400 [View Commit](../../commit/69b167e22afd71c8ea6dbbab40fd38ec777e635b)
* fix issues with person biosketch on prop dev
  * Joe Williams on Mon, 6 Apr 2015 08:28:26 -0500 [View Commit](../../commit/4a9d5ee0c8fb954e22a6d17f63619ab1e22582b3)
* Fix questionnaire test
  * Test was broken when switching viewing questionnaires from using the last document verison to using the database version always
  * blackcathacker on Mon, 6 Apr 2015 10:15:07 -0700 [View Commit](../../commit/8529b0d786b69d04ed2a98bced752aac7f7360e3)
* Fix questionnaire test -- apparently didn't stage changes
  * blackcathacker on Mon, 6 Apr 2015 16:09:09 -0700 [View Commit](../../commit/7ebc716812c82ca06b83d46783beb77d26326b4a)