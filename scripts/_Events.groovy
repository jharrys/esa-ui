eventCompileStart = { msg ->
	final String tagName = "release"
	File gitFile = new File("grails-app/views/_git.gsp")
	String headCommit = ("git rev-parse HEAD".execute().text)
	String cmd = "git describe --tags --match " + tagName + " HEAD"
	String headTag = (cmd.execute().text.trim())

	if (tagName.equals(headTag)) {
		println "found tag named " + tagName + " - writing commit " + headCommit + " to _git.gsp"
		gitFile.text = headCommit
	} else {
		gitFile.text = 'in development mode'
	}
}