package com.yosefmoq.charlhie.ui.payment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.lifecycle.ViewModelProviders;

import com.stripe.android.SourceCallback;
import com.stripe.android.Stripe;
import com.stripe.android.model.Source;
import com.stripe.android.model.SourceParams;
import com.yosefmoq.charlhie.Base.BaseActivity;
import com.yosefmoq.charlhie.CardNavigation;
import com.yosefmoq.charlhie.MainActivity;
import com.yosefmoq.charlhie.R;
import com.yosefmoq.charlhie.databinding.ActivityPaymentBinding;
import com.yosefmoq.charlhie.models.BanContactRequest;
import com.yosefmoq.charlhie.models.Category;
import com.yosefmoq.charlhie.models.RigesterRequest;
import com.yosefmoq.charlhie.repository.local.LocalSave;
import com.yosefmoq.charlhie.utils.BR;
import com.yosefmoq.charlhie.utils.EmailFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PaymentActivity extends BaseActivity<ActivityPaymentBinding, PaymentMV> implements CardNavigation {
    Dialog dialog;
    private AlertDialog finishAlertDialog;
    private final SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    ArrayList<Category> productResponses;
    RigesterRequest rigesterRequest;
    Stripe stripeObject;
    private Source stripeSource;

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public int getBindingVariable() {
        return BR._all;
    }

    @Override
    public void initItems() {
        this.rigesterRequest = LocalSave.getInstance(getApplicationContext()).getCurrentUser();
        this.productResponses = getViewModel().getProduct();
//        sendOrderEmail("yosefmoqq@gmail.com");
//        new SendOrderTask(this, getViewModel().myDatabase.getProducts(), "").execute(new String[0]);

        getViewModel().setNavigator(this);
    }

    @Override
    public void initClicks() {
        getViewModel().baseResponseMutableLiveData.observe(this,s -> {
            getViewModel().deleteCart();
            intent();
        });
        ((ActivityPaymentBinding) getViewDataBinding()).rlPayBancontact.setOnClickListener(new View.OnClickListener() {

            public final void onClick(View view) {
                createBancontactPayment();
            }
        });
        ((ActivityPaymentBinding) getViewDataBinding()).imvBackFromPayment.setOnClickListener(new View.OnClickListener() {

            public final void onClick(View view) {
                finish();

            }
        });
    }

    @Override
    public PaymentMV getViewModel() {
        return (PaymentMV) ViewModelProviders.of(this).get(PaymentMV.class);
    }

    private void createBancontactPayment() {
        findViewById(R.id.paymentProgress).setVisibility(View.VISIBLE);
        try {
            this.stripeObject = new Stripe(getApplicationContext(), Constant.STRIPE_PUBLIC_KEY);
            this.stripeObject.createSource(SourceParams.createBancontactParams(Math.round(getViewModel().totalAmountToPay()* 100.0d), "Charlhie", "my://charlhie/?status=1", "ORDER AT11"), new SourceCallback() {
                @Override
                public void onError(Exception error) {
                    findViewById(R.id.paymentProgress).setVisibility(View.GONE);
                    Log.e("NIKO", error.getLocalizedMessage());
                }

                @Override
                public void onSuccess(Source source) {
                    PaymentActivity.this.findViewById(R.id.paymentProgress).setVisibility(View.GONE);
                    PaymentActivity.this.stripeSource = source;
                    PaymentActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(source.getRedirect().getUrl())));
                }
            });
        } catch (Exception e) {
            findViewById(R.id.paymentProgress).setVisibility(View.GONE);
            e.printStackTrace();
            Log.v("ttt", "Exception bancontact" + e.getLocalizedMessage());
        }
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null && getWindow().getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void sendOrderEmail(String email) {
        String text = new EmailFormatter(this, getViewModel().totalAmountToPay(), this.rigesterRequest, this.productResponses, getViewModel().myDatabase.getAnnal()).formatText(false);
        sendEmailToServer(email, text);
        sendEmailToServer("kurtwarson@hotmail.com", text);
        sendEmailToServer("yosefmoqq@gmail.com",text);
    }

    private void sendEmailToServer(String email, String text) {
        getViewModel().sendEmail(email, text);
    }

    private void showFinalDialog(int title, int message) {
        if (this.finishAlertDialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(title);
            builder.setMessage(message).setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                /* class com.yosefmoq.charlhie.ui.payment.$$Lambda$PaymentActivity$wG8lW8pN6p9mzaATQzOmPfxDW34 */

                public final void onClick(DialogInterface dialogInterface, int i) {
                    getViewModel().deleteCart();
                    finish();
                }
            });
            AlertDialog create = builder.create();
            this.finishAlertDialog = create;
            create.show();
        }
        finish();
    }

    @Override
    public void success() {
        sendOrderEmail(this.rigesterRequest.getEmail());
//        new SendOrderTask(this, getViewModel().myDatabase.getProducts(), "").execute(new String[0]);
    }

    @Override
    public void error() {
        getViewModel().setErrorLoding(true, "try again later!");
    }

    @Override
    public void SuccessBan() {
        sendOrderEmail(this.rigesterRequest.getEmail());
//        new SendOrderTask(this, getViewModel().myDatabase.getProducts(), "").execute(new String[0]);

    }

    @Override
    public void ErrorBan(String msg) {
        showError(msg);
/*
        sendOrderEmail(this.rigesterRequest.getEmail());
        new SendOrderTask(this, getViewModel().myDatabase.getProducts(), "").execute(new String[0]);
*/
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getData() != null && intent.getData().getQuery() != null) {
            onAuthFinished();
        }
    }

    public void onAuthFinished() {
        Source source = this.stripeSource;
        if (source != null) {
            // com.stripe.android.net.PollingResponseHandler
            this.stripeObject.pollSource(source.getId(), this.stripeSource.getClientSecret(), Constant.STRIPE_PUBLIC_KEY, pollingResponse -> {
                stripeSource = pollingResponse.getSource();
                if (pollingResponse.isSuccess() && stripeSource.getStatus().equals(Source.CHARGEABLE)) {
                    Log.v("ttt", stripeSource.getId());
                    BanContactRequest banContactRequest = new BanContactRequest();
                    banContactRequest.setAmount((int) Math.round(getViewModel().totalAmountToPay()* 100.0d));
                    banContactRequest.setSource(pollingResponse.getSource().getId());
                    Log.v("ttt", banContactRequest.toString());
                    getViewModel().banRequest(banContactRequest);
                }
            }, 30000);
        }
    }




    public void intent() {
        Dialog dialog2 = this.dialog;
        if (dialog2 != null && dialog2.isShowing()) {
            this.dialog.dismiss();
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("done", "done");
        setResult(-1, intent);
        finish();
    }
}
