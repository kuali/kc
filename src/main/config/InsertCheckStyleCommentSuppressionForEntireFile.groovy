import groovy.xml.MarkupBuilder

/**
 * This script generates a checkstyle comment suppression
 * for all the java files in the input directory tree that
 * also exist in the output directory tree.  If you want this
 * script to update a entire directory tree just set the input
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
 * Note: this script assumes that all java files will contain a valid package statement
 * and it will be the first occurance of the word "package" in the file :-)
 * 
 * Written with Groovy 1.5
 */

println "started..." 

//platform specific constants
static final INPUT_PROJECT_SOURCE_ROOT = "C:\\java\\projects\\kc_project_r1_patch_br\\src"
static final OUTPUT_PROJECT_SOURCE_ROOT = "C:\\java\\projects\\kc_project\\src"

static final SEPARATOR_PATTERN = "\\\\"
static final NEW_LINE = "\n";

//other contants
static final JAVA_FILE_PATTERN = ~/.*\.java/
static final CHECKSTYLE_SUPPRESSION = NEW_LINE + "//CHECKSTYLE_OFF: ALL - Suppression filter auto-generated" + NEW_LINE

println "INPUT_PROJECT_SOURCE_ROOT: " + INPUT_PROJECT_SOURCE_ROOT
println "OUTPUT_PROJECT_SOURCE_ROOT: " + OUTPUT_PROJECT_SOURCE_ROOT
println "SEPARATOR_PATTERN: " + SEPARATOR_PATTERN     
println "CHECKSTYLE_SUPPRESSION: " + CHECKSTYLE_SUPPRESSION

/**
 * adds the suppression comment directly under the package statement of a java file.
 */
def addSuppressionToFile = { javaFile ->
	
    if (javaFile.exists()) {
		def newText = new StringBuilder(javaFile.text)
		//insert directly after the package statement
		newText.insert(newText.indexOf(";", newText.indexOf("package"))+ 1, CHECKSTYLE_SUPPRESSION)
		if (javaFile.delete()) {
			if(javaFile.createNewFile()) {
			    javaFile.text = newText.toString()
				println("writing output file: " + javaFile.canonicalPath)
			} else {
				err.println("unable to recreate file." + javaFile.canonicalPath)
			}
		} else {
			err.println("unable to delete file." + javaFile.canonicalPath)
		}
	} else {
		println("cannot find output file: " + javaFile.canonicalPath)
	}
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