import groovy.xml.MarkupBuilder

/**
 * This script will add CheckStyle suppression comments around various methods in a Java file.
 * The java files affected are file in the input directory tree that also exist in the output
 * directory tree.  If you want this script to update a entire directory tree just set the input
 * and output directory to the same path. 
 * 
 * This assumes that a file has not been moved to a different directory.
 * -----------------------------------------------------------------
 * For example :
 * 
 * input:  c:\\proj_branch\\src\\foo\\Foo.java
 * output: c:\\proj_trunkk\\src\\foo\\more\Foo.java
 * 
 * this will NOT be a match therefore a suppression will not be added
 * ------------------------------------------------------------------
 * 
 * This script is not platform independant.
 * 
 * A few constants must be changed to run this in a non-windows environment
 * 
 * WARNING:  This script is not all that robust in regards to Java syntax.  To do this correctly
 * the Java source files should have been lexed using a formal grammer.  Instead this script is using
 * simple regex and so strange Java formatting may break this program. Furthermore,
 * this script does not take into account method overloading. Use at your own risk!
 * 
 * Also, this isn't exactly the worlds greatest Groovy code...my apologies
 * 
 * Written with Groovy 1.5
 */
println "started..." 

//platform specific constants
static final INPUT_PROJECT_SOURCE_ROOT = "C:\\java\\projects\\Sandbox\\src"
static final OUTPUT_PROJECT_SOURCE_ROOT = "C:\\java\\projects\\Sandbox\\src"

static final SEPARATOR_PATTERN = "\\\\"
static final NEW_LINE = "\n";

//other contants
static final JAVA_FILE_PATTERN = ~/.*\.java/
static final CHECKSTYLE_SUPPRESSION_OFF = NEW_LINE + "//CHECKSTYLE_OFF: ALL - Suppression filter auto-generated" + NEW_LINE
static final CHECKSTYLE_SUPPRESSION_ON = NEW_LINE + "//CHECKSTYLE_ON: ALL - Suppression filter auto-generated" + NEW_LINE
static final METHOD_PATTERN = ~/.*public\s+boolean\s+equals\s*\(.*|.*public\s+int\s+hashCode\s*\(.*|.*protected\s+LinkedHashMap(<.*>)?\s+toStringMapper\s*\(.*|.*public\s+LinkedHashMap(<.*>)?\s+toStringMapper\s*\(.*/

println "INPUT_PROJECT_SOURCE_ROOT: " + INPUT_PROJECT_SOURCE_ROOT
println "OUTPUT_PROJECT_SOURCE_ROOT: " + OUTPUT_PROJECT_SOURCE_ROOT
println "SEPARATOR_PATTERN: " + SEPARATOR_PATTERN     
println "CHECKSTYLE_SUPPRESSION_OFF: " + CHECKSTYLE_SUPPRESSION_OFF
println "CHECKSTYLE_SUPPRESSION_ON: " + CHECKSTYLE_SUPPRESSION_ON
println "METHOD_PATTERN: " + METHOD_PATTERN

/**
 * adds the suppression comments around various methods within a java file.
 */
def addSuppressionToFile = { javaFile ->
	
    if (javaFile.exists()) {
		def lines = javaFile.readLines()
		
		def openCurCount = 0;
		def closeCurCount = 0;
		
		def insertOff = false;
		def modified = false;
		for (i in 0..lines.size - 1) {
	        java.util.regex.Matcher m = METHOD_PATTERN.matcher(lines[i])
	        
	        if (m.matches()) {
	            
	            /** inserts the suppression filter off. */
	            insertSuppressionOff = {
    	            for (j in i..0) {
    	                //if it cannot find a blank line
    	                //to place the suppression off then it will not get inserted :-)
    	                if(lines[j].trim() == "") {
    	                    lines[j] = NEW_LINE + CHECKSTYLE_SUPPRESSION_OFF
    	                    insertOff = true;
    	                    modified = true;
    	                    break;
    	                }
    	            }
	            }
	            
	            insertSuppressionOff();
	        }
	        
	        if (!insertOff) {
	            continue;
	        }
	        
	        /** increments the curly counts. */
	        incrementCurlyCounts = {
    	        //tried to use regex to get count - Groovy kept crashing :-(
    	        for (ch in lines[i]) {
    	            if (ch == '{') {
    	                openCurCount++
    	            }
    	            
    	            if (ch == '}') {
    	                closeCurCount++
    	            }
    	        }  
	        }
	        
	        incrementCurlyCounts();
	        
	        /** inserts the suppresion filter on.*/
	        insertSuppressionOn = {
    	        if (openCurCount > 0 && closeCurCount > 0 && (openCurCount - closeCurCount) == 0) {
    	            lines[i] = lines[i] + NEW_LINE + CHECKSTYLE_SUPPRESSION_ON
    	            insertOff = false;
    	        }
	        }
	        
	        insertSuppressionOn();
		}

		if (modified) {
			javaFile.text = collectionToString(lines);
		}
		println("writing output file: " + javaFile.canonicalPath)
	} else {
		println("cannot find output file: " + javaFile.canonicalPath)
	}
}

/** takes a collection of string and create single string w/ newline. */
collectionToString = { lines ->
    def newText = ""
		for (line in lines) {
		    newText += line
		    newText += NEW_LINE
		}
    return newText;
}

/**
 * processes each java file in the input source root.
 * Then processes the same file in the output source root.  
 */
def processFiles 
processFiles = {
	
	it.eachDir(processFiles)
	
	it.eachFileMatch(JAVA_FILE_PATTERN) {
		println("reading input file: " + it.canonicalPath)
		addSuppressionToFile(new File(it.canonicalPath.replace(INPUT_PROJECT_SOURCE_ROOT, OUTPUT_PROJECT_SOURCE_ROOT)));
	}
}
processFiles(new File(INPUT_PROJECT_SOURCE_ROOT))
println "finished..."