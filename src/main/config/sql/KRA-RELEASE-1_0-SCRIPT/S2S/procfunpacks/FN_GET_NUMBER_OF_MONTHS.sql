create or replace FUNCTION          "FN_GET_NUMBER_OF_MONTHS" (
  adt_start_date  		   	  in date,
  adt_end_date				  in date)
  return number is

  li_NumberOfMonths 		  number;
  li_StartMonth				  number;
  li_StartDay				  number;
  li_EndMonth				  number;
  li_EndDay					  number;
  li_WholeMonths			  number;
  li_NumberOfDays			  number;
  li_DaysInMonth			  number;

  li_FractionMonthStart		  number;
  li_FractionMonthEnd		  number;

  ldt_WholeMonthStartDate	  date;
  ldt_WholeMonthEndDate	  date;

begin

	 li_NumberOfMonths := 0;

	 if adt_end_date <= adt_start_date then
	   -- IF end date <= Start date return 0
	 	li_NumberOfMonths := 0;
     else

		 li_StartDay := to_number(extract(day from adt_start_date));
		 if li_StartDay = 1 then
		 -- Start date is on 1st of the month
		 	ldt_WholeMonthStartDate := adt_start_date;
		 else
		 -- Set ldt_WholeMonthStartDate to first day of next month
		 	 ldt_WholeMonthStartDate := last_day(adt_start_date) + 1;
		 end if;

		 if last_day(adt_end_date) = adt_end_date then
		 -- End date is end of the month
		 	ldt_WholeMonthEndDate := adt_end_date;
		 else
		  -- Last date of Previous month
		   ldt_WholeMonthEndDate := trunc(adt_end_date, 'MONTH') - 1;
		 end if;

		 if ldt_WholeMonthEndDate > ldt_WholeMonthStartDate then
		 	li_WholeMonths := months_between(ldt_WholeMonthEndDate + 1, ldt_WholeMonthStartDate);
		 else
		    li_WholeMonths := 0;
		 end if;

		 if adt_start_date <> ldt_WholeMonthStartDate then
		 	li_NumberOfDays := ldt_WholeMonthStartDate - adt_start_date;
			li_DaysInMonth := (last_day(adt_start_date) + 1) - trunc(adt_start_date, 'MONTH');
			li_FractionMonthStart := round((li_NumberOfDays/li_DaysInMonth), 2);
		 else
		 	 li_FractionMonthStart := 0;
		 end if;

		 if adt_end_date <> ldt_WholeMonthEndDate then
		 	li_NumberOfDays := adt_end_date - ldt_WholeMonthEndDate;
			li_DaysInMonth := (last_day(adt_end_date) + 1) - trunc(adt_end_date, 'MONTH');
			li_FractionMonthEnd := round((li_NumberOfDays/li_DaysInMonth), 2);
		 else
		 	 li_FractionMonthEnd := 0;
		 end if;


		 li_NumberOfMonths := li_WholeMonths + li_FractionMonthStart + li_FractionMonthEnd;
	 end if;

return li_NumberOfMonths ;
end ;
/