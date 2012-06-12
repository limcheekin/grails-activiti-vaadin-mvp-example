grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
	// inherit Grails' default dependencies
	inherits("global") {
		// uncomment to disable ehcache
		// excludes 'ehcache'
	}
	log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
	repositories {
		grailsPlugins()
		grailsHome()
		grailsCentral()

		// uncomment the below to enable remote dependency resolution
		// from public Maven repositories
		//mavenLocal()
		//mavenCentral()
		mavenRepo "http://repo.grails.org/grails/plugins"
		mavenRepo "http://maven.vaadin.com/vaadin-addons"
		mavenRepo "http://anonsvn.icefaces.org/repo/maven2/releases/"
		mavenRepo "http://openccdb.org:8081/nexus/content/repositories/thirdparty"
		//mavenRepo "http://snapshots.repository.codehaus.org"
		//mavenRepo "http://repository.codehaus.org"
		//mavenRepo "http://download.java.net/maven/2/"
		//mavenRepo "http://repository.jboss.com/maven2/"
	}
	dependencies {
		// specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

		// runtime 'mysql:mysql-connector-java:5.1.13'
		compile 'org.vaadin.addons:stuff4vaadin:0.0.1'
		//compile 'org.vaadin.addons:mvp4vaadin:0.9.0'
		compile 'org.vaadin.addons:icepush:0.2.1'
		runtime 'org.icepush:icepush:2.0.2'
		compile 'org.vaadin:icepush-gwt:0.1.2'
		compile 'com.vaadin.addon:beanvalidation-addon:0.6.2'
		compile 'org.reflections:reflections:0.9.8'
		test 'org.easymock:easymock:3.0'
	}
}
