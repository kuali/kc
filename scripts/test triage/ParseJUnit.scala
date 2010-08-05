/** parses a Junit results xml file.  Creates two files: one containing all the Test classes with failures & one with passes.*/
  object ParseJUnit {
    /** pass in the name of the xml file.  The xml file must be in the same directory as this script. */
	def main(args: Array[String]) {
      require(args.length == 1 && args(0) != null && args(0).trim() != "")
	  
	  import scala.xml._;
	  val xml = XML.loadFile(args(0))
	  
	  import scala.collection.mutable.HashSet
	  val total = HashSet[String]()
	  val errors = HashSet[String]() 
	  
	  for (testsuite <- (xml \\ "testsuite")) {
		for (testcase <- (testsuite \\ "testcase")) {
		  val className = (testcase \ "@classname").text
		  total += className
		  if ((testcase \\ "failure") != Nil || (testcase \\ "error") != Nil) {
			errors += className
		  }
		}
	  }

	  writeFileAndClose(errors.reduceLeft(_ + ".class,\n" + _) + ".class\n", "failures.txt")
	  writeFileAndClose(total.filter(!errors.contains(_)).reduceLeft(_ + ".class,\n" + _) + ".class\n", "passes.txt")
    }
	
	/** writes the passed in string to a file with the passed in name.  If the file will not be appended to. */
	private def writeFileAndClose(toFile: String, fileName: String) {
	  import java.io.FileWriter
	  val writer = new FileWriter(fileName, false)
	  try {
		writer.write(toFile)
	  } finally {
		writer.flush()
		writer.close()	  
	  }
	}
}