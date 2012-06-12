package org.vaadin.activiti.simpletravel.process.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import org.springframework.beans.factory.annotation.Configurable;
import org.vaadin.activiti.simpletravel.domain.TravelRequest;
import org.vaadin.activiti.simpletravel.ui.forms.TaskForm;
import org.vaadin.activiti.simpletravel.ui.forms.TaskFormViewComponent;

@TaskForm(formKey = "bookTickets")
@Configurable
public class BookTicketsFormViewComponent extends TaskFormViewComponent<BookTicketsFormView, BookTicketsFormPresenter> implements BookTicketsFormView {

    private VerticalLayout layout;
    private TravelRequestViewerComponent requestViewer;
    private Button ticketsBooked;
    private Button cancel;

    @Override
    protected Component createCompositionRoot() {
        layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        final Label header = new Label("Book Tickets");
        header.addStyleName(Reindeer.LABEL_H1);
        layout.addComponent(header);

        requestViewer = new TravelRequestViewerComponent();
        layout.addComponent(requestViewer);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);

        ticketsBooked = new Button("Tickets have been booked", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                ticketsBookedClick();
            }
        });
        ticketsBooked.setDisableOnClick(true);
        buttons.addComponent(ticketsBooked);

        cancel = new Button("Cancel", new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                cancelClick();
            }
        });
        cancel.addStyleName(Reindeer.BUTTON_LINK);
        cancel.setDisableOnClick(true);
        buttons.addComponent(cancel);

        layout.addComponent(buttons);

        return layout;
    }

    @Override
    public void setRequest(TravelRequest request) {
        requestViewer.setRequest(request);
    }

    private void cancelClick() {
        getPresenter().cancel();
    }

    private void ticketsBookedClick() {
        getPresenter().bookTickets();
    }
}
