package com.yosefmoq.charlhie.utils;

import android.content.Context;
import com.yosefmoq.charlhie.models.Category;
import com.yosefmoq.charlhie.models.RigesterRequest;
import com.yosefmoq.charlhie.repository.local.MyDatabase;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class EmailFormatter {
    private Context context;
    private MyDatabase myDatabase;
    private double price;
    private ArrayList<Category> products;
    private int quantity;
    private RigesterRequest rigesterRequest;

    public EmailFormatter(Context context2, double price2, RigesterRequest rigesterRequest2, ArrayList<Category> products2, int quantity2) {
        this.price = price2;
        this.rigesterRequest = rigesterRequest2;
        this.products = products2;
        this.quantity = quantity2;
        this.context = context2;
        this.myDatabase = new MyDatabase(context2);
    }

    public String formatRigestration() {
        return (((((((("" + "Beste ") + getName()) + welcomeApp()) + "Met vriendelijke groeten,\n<br><br>") + "Charlie<br>") + address()) + "3770 Riemst<br>") + "Belgium<br>") + "Tel 003212454222<br>";
    }

    private String address() {
        return "proostyeah 20 2<br>3000 leuven<br>";
    }

    private String welcomeApp() {
        return "Bedankt voor uw registratie op de Charlie app. Laat het smaken!\n<br><br>";
    }

    private String getName() {
        return this.rigesterRequest.getFirstname() + "  " + this.rigesterRequest.getLastname() + "<br><br>";
    }

    public String formatText(boolean isReservation) {
        StringBuilder stringBuilder = new StringBuilder("Beste ");
        stringBuilder.append(this.rigesterRequest.getFirstname());
        stringBuilder.append(" ");
        stringBuilder.append(this.rigesterRequest.getLastname());
        stringBuilder.append(" <br><br>");
        if (isReservation) {
            stringBuilder.append("Bedankt voor je bes bij Charlie. Gelieve binnen de 7 dagen langs te komen in de zaak. ");
            stringBuilder.append(" <br><br>");
            stringBuilder.append("We hebben uw reservatie geregistreerd met volgende gegevens :");
            stringBuilder.append(" <br><br>");
        } else {
            stringBuilder.append("Bedankt voor uw order bij Charlhie.");
            stringBuilder.append(" <br><br>");
            stringBuilder.append("We hebben uw order geregistreerd met volgende gegevens :  ");
            stringBuilder.append(" <br><br>");
        }
        stringBuilder.append("Order overzicht: ");
        stringBuilder.append(" <br> ");
        stringBuilder.append(" <br> ");
        stringBuilder.append("totaal : " + this.myDatabase.getAnnal() + "<br><br>");
        stringBuilder.append(" <br> ");
        stringBuilder.append(" <br> ");
        stringBuilder.append(createProductsText());
        stringBuilder.append(" <br>");
        stringBuilder.append("Totaal bedrag: ");
        stringBuilder.append(Utils.displayDoubleValue(this.price) + " EUR");
        stringBuilder.append(" <br><br> ");
        stringBuilder.append(this.rigesterRequest.getStreetname() + " ");
        stringBuilder.append(this.rigesterRequest.getHousenumber() + " ");
        stringBuilder.append((this.rigesterRequest.getBox() == null || this.rigesterRequest.getBox().equalsIgnoreCase("")) ? "<br>" : this.rigesterRequest.getBox());
        stringBuilder.append(" ");
        stringBuilder.append(this.rigesterRequest.getPostcode() + " ");
        stringBuilder.append(this.rigesterRequest.getCity() + "<br>");
        stringBuilder.append(this.rigesterRequest.getVat() + "<br><br>");
        stringBuilder.append(this.address()+"<br><br>");
        stringBuilder.append(" <br> ");
        stringBuilder.append(" Charlhie");
        stringBuilder.append(" <br> ");
        stringBuilder.append(" Tel +32 486678082");
        stringBuilder.append(" <br> ");
        return stringBuilder.toString();
    }

    private String createProductsText() {
        String text = "";
        ArrayList<Category> arrayList = this.products;
        if (arrayList != null) {
            Iterator<Category> it = arrayList.iterator();
            while (it.hasNext()) {
                Category product = it.next();
                String text2 = ((text + "Categorie : " + product.getCategory() + "<br> Beschrijving : " + product.getDescription() + "<br> Prijs : " + new DecimalFormat("##.##").format(product.getNativePrice() * ((double) product.getQuantity())) + " EUR <br>") + "<br>") + "Aantal :  " + product.getQuantity();
                if (0 == this.products.size() - 1) {
                    text = text2 + "<br>";
                } else {
                    text = text2 + "<br><br><br>";
                }
            }
        }
        return text;
    }
}
