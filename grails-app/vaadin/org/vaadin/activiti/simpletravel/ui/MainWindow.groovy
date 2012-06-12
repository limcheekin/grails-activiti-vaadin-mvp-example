package org.vaadin.activiti.simpletravel.ui

import com.github.peholmst.mvp4vaadin.ViewEvent
import com.github.peholmst.mvp4vaadin.ViewListener
import com.vaadin.ui.Window
import org.vaadin.activiti.simpletravel.ui.dashboard.DashboardView
import org.vaadin.activiti.simpletravel.ui.dashboard.components.DashboardViewComponent
import org.vaadin.activiti.simpletravel.ui.dashboard.UserLoggedOutEvent
import org.vaadin.activiti.simpletravel.ui.login.LoginView
import org.vaadin.activiti.simpletravel.ui.login.components.LoginViewComponent
import org.vaadin.activiti.simpletravel.ui.login.UserLoggedInEvent

class MainWindow extends Window implements ViewListener {

    private LoginView loginView
    private DashboardView dashboardView

    MainWindow() {
        super("Activiti and Vaadin - A Match made in heaven")
        showLoginView()
    }

    void handleViewEvent(ViewEvent event) {
        if (event instanceof UserLoggedInEvent) {
            UserLoggedInEvent userLoggedInEvent = (UserLoggedInEvent) event
            loginUser(userLoggedInEvent.getUsername())
        } else if (event instanceof UserLoggedOutEvent) {
            logoutUser()
        }
    }

    void destroy() {
        disposeLoginView()
        disposeDashboardView()
    }

    private void loginUser(String username) {
        getApplication().setUser(username)
        showDashboardView()
        disposeLoginView()
    }

    private void logoutUser() {
        getApplication().close()
    }

    private void showLoginView() {
        loginView = new LoginViewComponent()
        loginView.addListener((ViewListener)this)
        println "showLoginView: setContent()"
        setContent((LoginViewComponent) loginView)
    }

    private void disposeLoginView() {
        if (loginView != null) {
            loginView.removeListener((ViewListener)this)
            loginView = null
        }
    }

    private void showDashboardView() {
        dashboardView = new DashboardViewComponent()
        dashboardView.addListener((ViewListener)this)
        setContent((DashboardViewComponent) dashboardView)
        dashboardView.startProcessEnginePolling()
    }

    private void disposeDashboardView() {
        if (dashboardView != null) {
            dashboardView.removeListener((ViewListener)this)
            dashboardView.stopProcessEnginePolling()
            dashboardView = null
        }
    }
}
