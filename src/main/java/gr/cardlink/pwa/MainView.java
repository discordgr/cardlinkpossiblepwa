package gr.cardlink.pwa;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
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
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
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
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired GreetService service) {

        H3 header = new H3("CardLink Possible");

        //Currency List
        ComboBox<String> currencies = new ComboBox<>("Currency");
        currencies.setId("CurrencyList");
        currencies.setItems("EUR");
        currencies.setValue("EUR");

        //Amount Layout
        HorizontalLayout amountLayout = new HorizontalLayout();

        Text totalAmount = new Text("€0,00");
        NumberField amountNumberField = new NumberField("Amount");
        amountNumberField.setId("Amount");

        amountLayout.setAlignItems(Alignment.BASELINE);
        amountLayout.add(amountNumberField, totalAmount);

        amountNumberField.addValueChangeListener(k -> {
            if ( amountNumberField.getValue() != null ) {
                double amount = amountNumberField.getValue();
                totalAmount.setText("€" + amount);
            }
        });

        //Installments
        NumberField installmentsNumberField = new NumberField("Installments");
        installmentsNumberField.setValue(0.0);
        installmentsNumberField.setId("Installments");

        //Tip Layout
        HorizontalLayout tipLayout = new HorizontalLayout();

        Checkbox tipCheckBox = new Checkbox("Include Tip?");

        Text tipAmount = new Text("€0,00");

        NumberField tipNumberField = new NumberField("Tip");
        tipNumberField.setId("Tip");

        tipCheckBox.addValueChangeListener(t -> {
            if ( t.getValue() ){
                tipLayout.setVisible(true);
            }else{
                tipLayout.setVisible(false);
            }
        });

        tipNumberField.addValueChangeListener(t -> {
            tipAmount.setText("€" + tipNumberField.getValue());
        });

        tipLayout.setAlignItems(Alignment.BASELINE);
        tipLayout.add(tipNumberField, tipAmount);
        tipLayout.setVisible(false);

        //Checkout Button
        Button checkOut = new Button("CheckOut");
        checkOut.addClickListener(b -> {
            UI.getCurrent().getPage().executeJs("goToMobileApp()");
        });

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button is more prominent look.
        checkOut.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        checkOut.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");

        add(header, currencies, amountLayout, installmentsNumberField, tipCheckBox, tipLayout ,checkOut);
    }

}
