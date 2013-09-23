package net.travisdazell.javaone.groovy.examples;

import static org.junit.Assert.*
import groovy.xml.MarkupBuilder

import org.junit.Test

class TeeTimeTester {

	@Test
	public void test() {

		def dslDefinition = new File('C:\\Users\\Travis_Dazell\\workspace\\Golf Scheduler with a Groovy DSL\\src\\net\\travisdazell\\javaone\\groovy\\examples\\TeeTimeDSL.groovy').text

		def dsl = new File('C:\\Users\\Travis_Dazell\\workspace\\Golf Scheduler with a Groovy DSL\\test\\net\\travisdazell\\javaone\\groovy\\examples\\NewTeeTime.groovy').text

		def script = """
			${dslDefinition}
			${dsl}
		"""

		new GroovyShell().evaluate(script)
	}
}
