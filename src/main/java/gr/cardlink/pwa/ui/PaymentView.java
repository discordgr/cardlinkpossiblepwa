package gr.cardlink.pwa.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;

public class PaymentView {

    //Add components here in case we need to duplicate UI with other values.
    H3 header;
    ComboBox<String> currencies;
    NumberField installmentsNumberField;

    HorizontalLayout amountLayout;
    NumberField amountNumberField;

    HorizontalLayout tipLayout;
    Checkbox tipCheckBox;

    Checkbox smsCheckBox;
    HorizontalLayout smsLayout;

    Button checkOut;


    public PaymentView() {
        header = new H3("CardLink Possible");

        currencies = new ComboBox<>("Currency");
        setCurrencies();

        amountLayout = new HorizontalLayout();
        createAmountLayout();

        installmentsNumberField = new NumberField("Installments");
        setInstallmentsNumberField();

        tipLayout = new HorizontalLayout();
        tipCheckBox = new Checkbox("Include Tip?");
        createTipLayout();

        smsLayout = new HorizontalLayout();
        smsCheckBox = new Checkbox("Send Receipt to mobile phone?");
        createSMSLayout();

        checkOut = new Button("CheckOut");
        setCheckOut();
    }



    private void createAmountLayout() {
        Text totalAmount = new Text("€0,00");
        amountNumberField = new NumberField("Amount");
        amountNumberField.setId("Amount");
        amountNumberField.setValue(0.0);
        amountNumberField.addFocusListener(k -> {
            amountNumberField.clear();
        });

        amountLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        amountLayout.add(amountNumberField, totalAmount);

        amountNumberField.addValueChangeListener(k -> {
            if ( amountNumberField.getValue() != null ) {
                double amount = amountNumberField.getValue();
                totalAmount.setText("€" + amount);
            }
        });
    }

    public void createTipLayout() {
        Text tipAmount = new Text("€0,00");

        NumberField tipNumberField = new NumberField("Tip");
        tipNumberField.setId("Tip");
        tipNumberField.setValue(0.0);
        tipNumberField.addFocusListener(t -> {
            tipNumberField.clear();
        });

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

        tipLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        tipLayout.add(tipNumberField, tipAmount);
        tipLayout.setVisible(false);
    }

    private void createSMSLayout() {
        NumberField phoneNumberField = new NumberField("Mobile Phone Number");
        phoneNumberField.setId("phoneNumber");

        smsCheckBox.addValueChangeListener(s -> {
            if ( s.getValue() ){
                smsLayout.setVisible(true);
            }else{
                smsLayout.setVisible(false);
            }
        });

        smsLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        smsLayout.add(phoneNumberField);
        smsLayout.setVisible(false);
    }

    private void setCurrencies(){
        currencies.setId("CurrencyList");

        currencies.setItems("EUR");
        currencies.setItems("DOL");

        currencies.setValue("EUR");
    }

    public void setInstallmentsNumberField() {
        installmentsNumberField.setValue(0.0);
        installmentsNumberField.setId("Installments");
    }

    private void setCheckOut() {
        checkOut.addClickListener(b -> {
            if (smsCheckBox.isEnabled()) {
                UI.getCurrent().getPage().executeJs("sendSMS()");
            }
            UI.getCurrent().getPage().executeJs("goToMobileApp()");
        });
    }

    public H3 getHeader() {
        return header;
    }

    public ComboBox<String> getCurrencies() {
        return currencies;
    }

    public NumberField getInstallmentsNumberField() {
        return installmentsNumberField;
    }

    public HorizontalLayout getAmountLayout() {
        return amountLayout;
    }

    public HorizontalLayout getTipLayout() {
        return tipLayout;
    }

    public Checkbox getTipCheckBox() {
        return tipCheckBox;
    }

    public Checkbox getSmsCheckBox() {
        return smsCheckBox;
    }

    public Button getCheckOut() {
        return checkOut;
    }

    public HorizontalLayout getSmsLayout() {
        return smsLayout;
    }
}
