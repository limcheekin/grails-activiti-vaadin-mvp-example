
vaadin {

    // Your Vaadin application class that extends com.vaadin.Application:
    applicationClass = "org.vaadin.activiti.simpletravel.ui.SimpleTravelApplication"

	// This is optional, GrailsAwareApplicationServlet is provided by default. Use this if you need to add or change application servlet. 
	// You should extend GrailsAwareApplicationServlet or GrailsAwareGAEApplicationServlet (from com.vaadin.grails.terminal.gwt.server package).
	  servletClass = "org.vaadin.artur.icepush.GrailsIcePushServlet"
	
    autowire = "byName" //how should dependencies be injected? other option is 'byType'

    // The context relative path where you want to access your Vaadin UI. Default is the context root.
    contextRelativePath = "/"
              
    productionMode = false

    googleAppEngineMode = false
}

environments {
    production {
        vaadin {
            productionMode = true
        }
    }
}
