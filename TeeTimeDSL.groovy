package net.travisdazell.javaone.groovy.examples

import groovy.xml.MarkupBuilder

import java.text.SimpleDateFormat

class Golfer {
	String firstName
	String lastName
	Integer guests

	def methodMissing(String name, args) {
		String golferName = name.split()
		def i = name.indexOf(' ')

		this.metaClass.getMetaProperty("firstName").setProperty(this, name.substring(0, i))
		this.metaClass.getMetaProperty("lastName").setProperty(this, name.substring(i+1, name.length()))
		this.metaClass.getMetaProperty("guests").setProperty(this, args[0].toString().toInteger())
	}
}

class TeeTime {
	Date time
	Golfer golfer

	TeeTime at(time) {
		this.time = new SimpleDateFormat("d-MMM-yyyy hh:mm a").parse(time)

		this
	}

	TeeTime forGolfer(closure) {
		golfer = new Golfer()

		closure.delegate = golfer

		closure()
		
		this
	}

	def getBookTeeTime() {
		def writer = new StringWriter()
		def xml = new MarkupBuilder(writer)

		xml.teeTime() {
			time(time)
			golfer {
				name(golfer.firstName.replace('[', '').replace(']', '') + " " + golfer.lastName.replace('[', '').replace(']', ''))
				numberOfGuests(golfer.guests)
			}
		}

		println writer.toString()
	}
}

TeeTime newTeeTime() {
	new TeeTime()
}

Integer.metaClass.getGuests = { -> delegate }
