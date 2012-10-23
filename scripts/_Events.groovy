eventCompileStart = { msg ->
	final String tagName = "release"
	String headCommit = ("git rev-parse HEAD".execute().text)
	String cmd = "git describe --tags --match " + tagName + " HEAD"
	String headTag = (cmd.execute().text.trim())
	
	if (tagName.equals(headTag)) {
		println "found tag named " + tagName + " - writing commit " + headCommit + " to _git.gsp"
		new File("grails-app/views/_git.gsp").text = headCommit
	} 
}