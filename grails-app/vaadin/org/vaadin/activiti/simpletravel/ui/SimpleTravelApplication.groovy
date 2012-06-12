package org.vaadin.activiti.simpletravel.ui

import com.vaadin.Application
import com.vaadin.service.ApplicationContext.TransactionListener
import org.vaadin.activiti.simpletravel.identity.CurrentUserFactoryBean

public class SimpleTravelApplication extends Application implements TransactionListener {

    transient CurrentUserFactoryBean currentUserFactoryBean = getBean('currentUserFactoryBean')


    public void init() {
        setTheme("simpletravel")
        getContext().addTransactionListener(this)
        setMainWindow(new MainWindow())
    }

    public void close() {
        setUser(null)
        getContext().removeTransactionListener(this)
        ((MainWindow) getMainWindow()).destroy()
        super.close()
    }

    public void transactionStart(Application application, Object transactionData) {
        String username = (String) getUser()
        currentUserFactoryBean.setCurrentUsername(username)
    }

    public void transactionEnd(Application application, Object transactionData) {
        currentUserFactoryBean.setCurrentUsername(null)
    }
}
