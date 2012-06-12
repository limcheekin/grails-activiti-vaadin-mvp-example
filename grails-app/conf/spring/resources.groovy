// Place your Spring DSL code here
beans = {
	currentUserFactoryBean(org.vaadin.activiti.simpletravel.identity.CurrentUserFactoryBean)
	
	/* loginPresenter(org.vaadin.activiti.simpletravel.ui.login.LoginPresenter) { bean ->
        bean.singleton = false
        identityService = ref("identityService")
        currentUserFactoryBean = ref("currentUserFactoryBean")
    } */
  formViewService(org.vaadin.activiti.simpletravel.ui.forms.impl.FormViewServiceImpl) {
        packagesToScan = "org.vaadin.activiti.simpletravel.process.ui"
     }    
     
  scheduledThreadPoolExecutor(java.util.concurrent.ScheduledThreadPoolExecutor, 10) { bean ->
      bean.destroyMethod = "shutdown"
    }
    
  //loginViewComponent(org.vaadin.activiti.simpletravel.ui.login.components.LoginViewComponent) { bean ->
  //      bean.singleton = false
   // }
}     
