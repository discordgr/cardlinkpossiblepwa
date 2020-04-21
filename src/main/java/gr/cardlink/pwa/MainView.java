package gr.cardlink.pwa;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import gr.cardlink.pwa.ui.PaymentView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "CardLink Payment Application",
        shortName = "CardLink Payment App",
        description = "This is a Payment application.",
        enableInstallPrompt = true)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@JavaScript("src/index.js")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * param service The message service. Automatically injected Spring managed bean.
     */
    public MainView() {

        PaymentView paymentView = new PaymentView();

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button is more prominent look.
        paymentView.getCheckOut().addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        paymentView.getCheckOut().addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");

        add(paymentView.getHeader(), paymentView.getCurrencies(), paymentView.getAmountLayout(), paymentView.getInstallmentsNumberField(),
                paymentView.getTipCheckBox(), paymentView.getTipLayout() ,paymentView.getSmsCheckBox(), paymentView.getSmsLayout(), paymentView.getCheckOut());
    }
}