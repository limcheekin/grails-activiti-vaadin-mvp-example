package org.vaadin.activiti.simpletravel.ui.login

import com.github.peholmst.mvp4vaadin.Presenter
import org.activiti.engine.IdentityService
import org.vaadin.activiti.simpletravel.identity.CurrentUserFactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Configurable

@Configurable
class LoginPresenter extends Presenter<LoginView> {
    @Autowired
    transient IdentityService identityService
    
    @Autowired
    transient CurrentUserFactoryBean currentUserFactoryBean    
    
    public void init() {
		    println "LoginPresenter.init()"
	    }
    
    public void attemptLogin(String username, String password) {
        if (identityService.checkPassword(username, password)) {
            currentUserFactoryBean.setCurrentUsername(username)
            fireViewEvent(new UserLoggedInEvent(getView(), username))
        } else {
            getView().clearForm()
            getView().showLoginFailed()
        }
    }
    
}
