package org.ihc.esa

import grails.test.mixin.web.GroovyPageUnitTestMixin

import org.apache.log4j.LogManager
import org.apache.log4j.SimpleLayout
import org.apache.log4j.WriterAppender
import org.apache.poi.ss.formula.functions.T

@grails.test.mixin.TestMixin(GroovyPageUnitTestMixin)
class LogTagLibSpecification extends spock.lang.Specification
{
	StringWriter receivedLogMessage = new StringWriter()
	
	def setup()
	{
		LogManager.getRootLogger().addAppender(new WriterAppender(new SimpleLayout(), receivedLogMessage))
	}
	
	void "test that logMsg does not return output"()
	{
		given:
		mockTagLib(LogTagLib)
		
		expect:
		assertOutputEquals('', '<g:logMsg level="error">Hello</g:logMsg>')
	}
	
	void "test info level log messages"()
	{
		given:
		mockTagLib(LogTagLib)
		
		and:
		applyTemplate('<g:logMsg level="info">Hello</g:logMsg>')
		
		expect:
		receivedLogMessage.toString().trim().equals('INFO - Hello')
	}
	
	void "test warn level log messages"()
	{
		given:
		mockTagLib(LogTagLib)
		
		and:
		applyTemplate('<g:logMsg level="warn">Hello</g:logMsg>')
		
		expect:
		receivedLogMessage.toString().trim().equals('WARN - Hello')
	}
	
	void "test error level log messages"()
	{
		given:
		mockTagLib(LogTagLib)
		
		and:
		applyTemplate('<g:logMsg level="error">Hello</g:logMsg>')
		
		expect:
		receivedLogMessage.toString().trim().equals('ERROR - Hello')
	}
	
	void "test debug level log messages"()
	{
		given:
		mockTagLib(LogTagLib)
		
		and:
		applyTemplate('<g:logMsg level="debug">Hello</g:logMsg>')
		
		expect:
		receivedLogMessage.toString().trim().equals('DEBUG - Hello')
	}
}
