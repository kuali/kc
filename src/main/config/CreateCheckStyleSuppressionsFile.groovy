import groovy.xml.MarkupBuilder

/**
 * This script generates a checkstyle suppressions file (that suppresses all checks)
 * for all the java files in a directory tree. 
 * 
 * Note: this script is not platform independant.
 * 
 * A few constants must be changed to run this in a non-windows environment
 * 
 * Written with Groovy 1.5
 * 
 * WARNING: this script may generate a file that is too large for CheckStyle to process quickly.
 */

println "starting..." 
 
//platform specific constants
static final PROJECT_SOURCE_ROOT = "C:\\java\\projects\\kc_project_r1_patch_br\\src"
static final SOURCE_FOLDER = PROJECT_SOURCE_ROOT.substring(PROJECT_SOURCE_ROOT.lastIndexOf("\\"))
static final OUTPUT_FILE = "C:\\test\\suppressions.xml"
static final SEPARATOR_PATTERN = "\\\\"

//other contants
static final JAVA_FILE_PATTERN = ~/.*\.java/
static final MULTI_PLATFORM_SEPARATOR_PATTERN = "[\\\\\\\\/]"
static final ALL_CHECKS_PATTERN = "."
static final ANY_CHAR_PATTERN = ".*"

println "PROJECT_SOURCE_ROOT: " + PROJECT_SOURCE_ROOT
println "SOURCE_FOLDER: " + SOURCE_FOLDER
println "OUTPUT_FILE: " + OUTPUT_FILE
println "SEPARATOR_PATTERN: " + SEPARATOR_PATTERN

def writer = new StringWriter()

//write directly to writer because MarkupBuilder does support these types of xml artifacts well
writer.println('<?xml version="1.0"?>')
writer.println('<!DOCTYPE suppressions PUBLIC "-//Puppy Crawl//DTD Suppressions 1.3//EN" "http://www.puppycrawl.com/dtds/suppressions_1_3.dtd">')

def xml = new MarkupBuilder(writer)
xml.setDoubleQuotes(true)

xml.suppressions() {     
	
	def processFiles 
	processFiles = {
		
		it.eachDir(processFiles)
		
		it.eachFileMatch(JAVA_FILE_PATTERN) {
		    println("processing file: " + it.canonicalPath);
		    def startDirIndex = it.canonicalPath.indexOf(SOURCE_FOLDER)
		    def partialDir = it.canonicalPath.substring(startDirIndex)
		    suppress(checks:ALL_CHECKS_PATTERN, files:ANY_CHAR_PATTERN + partialDir.replaceAll(SEPARATOR_PATTERN, MULTI_PLATFORM_SEPARATOR_PATTERN))
		}
	}
	processFiles(new File(PROJECT_SOURCE_ROOT))
	
}

//this seems like a strange way to do this...should be able to send the MarkupBuilder
//a FileWriter but the writer is not getting flushed
new File(OUTPUT_FILE).write(writer.toString())
println "finished..."