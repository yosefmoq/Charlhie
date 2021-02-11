package com.yosefmoq.charlhie;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import com.yosefmoq.charlhie.models.Category;
import com.yosefmoq.charlhie.models.RigesterRequest;
import com.yosefmoq.charlhie.repository.local.LocalSave;
import com.yosefmoq.charlhie.ui.payment.PaymentActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SendOrderTask extends AsyncTask<String, Void, String> {
    private Context context;
    private final SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    private ArrayList<Category> itemsToSend;
    private String tableNumber;

    public SendOrderTask(Context context2, ArrayList<Category> itemsToSend2, String tableNumber2) {
        this.itemsToSend = itemsToSend2;
        this.tableNumber = tableNumber2;
        this.context = context2;
    }

    /* access modifiers changed from: protected */
    public String doInBackground(String... params) {
        StringBuilder result = new StringBuilder();
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL("https://www.cocoonplace.com/DebiehalFiles/userOrderDebiehal.php").openConnection();
            urlConnection.setReadTimeout(20000);
            urlConnection.setConnectTimeout(20000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            writer.write(formatArrayToString(this.itemsToSend/*, this.tableNumber*/));
            writer.flush();
            writer.close();
            out.close();
            urlConnection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(urlConnection.getInputStream())));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ((PaymentActivity) this.context).getViewModel().deleteCart();
        ((PaymentActivity) this.context).intent();
    }


    private String formatArrayToString(ArrayList<Category> itemsToSend2/*, String tableNumber2*/) {
        String date = this.fmt.format(new Date());
        RigesterRequest rigesterRequest = LocalSave.getInstance(this.context).getCurrentUser();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < itemsToSend2.size(); i++) {
            Category countableItemENtity = itemsToSend2.get(i);
            if (i > 0) {
                stringBuilder.append("&");
            }
            String version = "0";
            try {
                version = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            stringBuilder.append(String.format(this.context.getResources().getConfiguration().locale, "c[%d]=%s&b[%d]=%s&a[%d]=%d&d[%d]=%s&e[%d]=%s&f[%d]=%s&g[%d]=%f&k[%d]=%d&h[%d]=%s&m[%d]=%s&n[%d]=%s&o[%d]=%s&z[%d]=%s&x[%d]=%s", Integer.valueOf(i), countableItemENtity.getSubCategory(), Integer.valueOf(i), countableItemENtity.getCategory(), Integer.valueOf(i), 0, Integer.valueOf(i), Integer.valueOf(rigesterRequest.getId()), Integer.valueOf(i), date, Integer.valueOf(i), rigesterRequest.getEmail(), Integer.valueOf(i), Double.valueOf(countableItemENtity.getPrice()), Integer.valueOf(i), Integer.valueOf(countableItemENtity.getQuantity()), Integer.valueOf(i), version, Integer.valueOf(i), rigesterRequest.getFirstname(), Integer.valueOf(i), "", Integer.valueOf(i), "", Integer.valueOf(i), "", Integer.valueOf(i), ""));
        }
        return stringBuilder.toString();
    }
}
