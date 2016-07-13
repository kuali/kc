/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.dc;

public class CliOptions {

    private String[] args;

    public CliOptions(String[] args) {
        this.args = args;
    }

    public boolean isValid() {
        boolean valid = args != null && args.length > 0;
        if (valid && (containsHelp() || containsVersion())) {
            return true;
        }

        if (valid && (containsValidate() && !"".equals(getCoeusConnectionString()) && !"".equals(getRiceConnectionString()))) {
            return true;
        }

        if (valid && (containsProposalPersonRole() && !"".equals(getCoeusConnectionString()) && !"".equals(getRiceConnectionString()))) {
            return true;
        }

        if (valid && (containsProposal() && !"".equals(getCoeusConnectionString()) && !"".equals(getRiceConnectionString()))) {
            return true;
        }

        if (valid && (containsIrb() && !"".equals(getCoeusConnectionString()) && !"".equals(getRiceConnectionString()))) {
            return true;
        }

        if (valid && (containsIacuc() && !"".equals(getCoeusConnectionString()) && !"".equals(getRiceConnectionString()))) {
            return true;
        }

        if (valid && (containsQuestSeq() && !"".equals(getCoeusConnectionString()) && !"".equals(getRiceConnectionString()))) {
            return true;
        }

        if (valid && (inactivateCleanupPolicy() || deleteCleanupPolicy())) {
            return true;
        }

        return false;
    }

    public boolean containsHelp() {
        return contains("-help");
    }

    public boolean containsVersion() {
        return contains("-version");
    }

    public boolean containsDebug() {
        return contains("-debug");
    }

    public boolean containsDryRun() {
        return contains("-dryrun");
    }

    public boolean containsValidate() {
        return contains("-validate");
    }

    public boolean containsProposalPersonRole() {
        return contains("pprole");
    }

    public boolean containsProposal() {
        return contains("proposal");
    }

    public boolean containsIrb() {
        return contains("irb");
    }

    public boolean containsIacuc() {
        return contains("iacuc");
    }

    public boolean containsQuestSeq() {
        return contains("questseq");
    }
    
    public boolean containsTimeAndMoneyDocStatus() {
    	return contains("tmdocstatus");
    }
    
    public boolean containsSubawardAmountInfo() {
    	return contains("subaward-amountinfo");
    }
    
    public boolean containsTimeAndMoneyDups() {
    	return contains("tm-dups");
    }
    
    public boolean containsProposalYnq() {
    	return contains("proposal-ynq");
    }

    public boolean containsProposalPersonNames() {
        return contains("proposal-person-names");
    }

    private boolean contains(String name) {
        for (String arg : args) {
            if (name.equals(arg)) {
                return true;
            }
        }
        return false;
    }

    private String nextArg(String name) {
        boolean returnNext = false;
        for (String arg : args) {
            if (returnNext) {
                return arg;
            }

            if (name.equals(arg)) {
                returnNext = true;
            }
        }
        return "";
    }

    public String getCoeusConnectionString() {
        if (contains("-dbcoeuscon")) {
            return nextArg("-dbcoeuscon");
        }
        return "";
    }

    public String getRiceConnectionString() {
        if (contains("-dbricecon")) {
            return nextArg("-dbricecon");
        }
        return "";
    }

    public String getCoeusUser() {
        if (contains("-dbcoeususer")) {
            return nextArg("-dbcoeususer");
        }
        return "";
    }
    public String getCoeusPassword() {
        if (contains("-dbcoeuspwd")) {
            return nextArg("-dbcoeuspwd");
        }
        return "";
    }
    public String getRiceUser() {
        if (contains("-dbriceuser")) {
            return nextArg("-dbriceuser");
        }
        return "";
    }
    public String getRicePassword() {
        if (contains("-dbricepwd")) {
            return nextArg("-dbricepwd");
        }
        return "";
    }
    public String getCleanupPolicy() {
        if (contains("-cleanup")) {
            return nextArg("-cleanup");
        }
        return "inactivate";
    }

    public boolean deleteCleanupPolicy() {
        return getCleanupPolicy().equals("delete");
    }

    public boolean inactivateCleanupPolicy() {
        return getCleanupPolicy().equals("inactivate");
    }

    public boolean isMySql() {
        if (contains("-platform") && "MySql".equalsIgnoreCase(nextArg("-platform"))) {
            return true;
        } else if (contains("-dbricecon") && nextArg("-dbricecon").startsWith("jdbc:mysql")) {
            return true;
        } else if (contains("-dbcoeuscon") && nextArg("-dbcoeuscon").startsWith("jdbc:mysql")) {
            return true;
        }

        return false;
    }

    public boolean isOracle() {
        if (contains("-platform") && "Oracle".equalsIgnoreCase(nextArg("-platform"))) {
            return true;
        } else if (contains("-dbricecon") && nextArg("-dbricecon").startsWith("jdbc:oracle")) {
            return true;
        } else if (contains("-dbcoeuscon") && nextArg("-dbcoeuscon").startsWith("jdbc:oracle")) {
            return true;
        }

        return false;
    }

    public String getCliHelpString() {
        return "coeus-data-conv [options] [conv_target [conv_target2 [conv_target3] ...]]\n"
                + "  Options:\n"
                + "  -help                    print this message\n"
                + "  -version                 print the version information and exit\n"
                + "  -dryrun                  executes conversion without writing out to the database\n"
                + "  -validate                validates database connections only\n"
                + "  -debug                   print debugging information\n"
                + "  -cleanup <policy>        the policy used for data cleanup (delete|inactivate)\n"
                + "  -dbplatform <platform>   the database platform (MySql|Oracle)\n"
                + "  -dbricecon <connection>  the kuali rice jdbc database connection string (jdbc:mysql://localhost/rice?user=usr&password=pwd)\n"
                + "  -dbcoeuscon <connection> the kuali coeus jdbc database connection string (jdbc:mysql://localhost/coeus?user=usr&password=pwd)\n"
                + "  -dbriceuser <ricedbuser>  the kuali rice database user\n"
                + "  -dbricepwd <ricerbpassword> the kuali rice database password\n"
                + "  -dbcoeususer <coeususer>  the kuali coeus database user\n"
                + "  -dbcoeuspwd <coeuspassword> the kuali coeus database password\n"
                + "\n"
                + "If platform is not specified then the platform will be autodetected from the connection strings.\n"
                + "\n"
                + "The valid conversion targets are (proposal|irb|iacuc|pprole|questseq|tmdocstatus|subaward-amountinfo|tm-dups).\n"
                + "\n"
                + "The dryrun flag may still cause database sequences to increment.\n"
                + "\n"
                + "The cleanup flag when choosing inactivate will attempt to set the active flag to false when possible.  This is the default. ";
    }

}
