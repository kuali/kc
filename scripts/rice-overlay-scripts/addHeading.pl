#!/usr/bin/perl -w
use strict;
use DBI;

my $dir = "bootstrap";
    opendir(DIR, "$dir") || die "cannot open dir:$dir\n";
    my @files = readdir(DIR);
    close DIR;

for(my $i = 0; $i < scalar(@files); $i++) {
    if ($files[$i] =~ /^[A-Za-z_]+/) {
	my @tableName = split(/\./, $files[$i]);
	my $tableName = $tableName[0];

	open(FILE, "$dir/$files[$i]") || die "Cannot open file: $files[$i]\n";
	my @data = <FILE>;
	close FILE;

	open(FILE, ">$dir/$files[$i]") || die "Cannot open file: $files[$i]\n";
	my $heading = &getHeader($files[$i]);
	print FILE "$heading\n";
	foreach my $data(@data) {
	    # a spurious \ gets added to the parameter, need to remove it or Oracle complains
	    if ($files[$i] =~ /KRCR_PARM_T/) {
		if ($data =~ /s2s\.polling\.scheduler\.enabled/) {
		    $data =~ s/job\\"/job/;
		}
	    }
	    ## mysql exports null as \N, change it back
	    $data =~ s/\\N/NULL/g;
	    print FILE $data;
	}
	close FILE;
    }
}

#### add staging data



sub getHeader {
    my ($tableName) = @_;
    my %header;
    open(OP, "headers") || die "cannot open file : headers\n";
    while(<OP>) {
	if($_ =~ /\w+/) {
	    chomp;
	    my @data = split(/\s*\-\s*/, $_);
	    $header{$data[0].".csv"} = $data[1]; 
	}
    }
    close OP;

    return $header{$tableName};
}
