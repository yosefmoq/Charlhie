package com.yosefmoq.charlhie.ui.Auth;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.yosefmoq.charlhie.Base.BaseActivity;
import com.yosefmoq.charlhie.R;
import com.yosefmoq.charlhie.databinding.ActivityAuthBinding;
import com.yosefmoq.charlhie.listener.auth;
import com.yosefmoq.charlhie.models.RigesterRequest;
import com.yosefmoq.charlhie.ui.payment.PaymentActivity;

public class AuthActivity extends BaseActivity<ActivityAuthBinding, AuthMV> implements auth {
    @Override
    public int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    public int getBindingVariable() {
        return 1;
    }

    @Override
    public void initItems() {
        getViewModel().setNavigator(this);
        changeToSignIn();
    }

    @Override
    public void initClicks() {
      ;  ((ActivityAuthBinding) getViewDataBinding()).ivBack.setOnClickListener((View.OnClickListener) view -> finish());
    };

    @Override
    public AuthMV getViewModel() {
        return (AuthMV) ViewModelProviders.of(this).get(AuthMV.class);
    }

    public void changeToSignIn() {
        getSupportFragmentManager().beginTransaction().replace(R.id.cl, new SignInFragment()).commit();
        ((ActivityAuthBinding) getViewDataBinding()).tvToolbarText.setText("Login");
    }

    public void changeToSignUp() {
        getSupportFragmentManager().beginTransaction().replace(R.id.cl, new SignUpFragment()).commit();
        ((ActivityAuthBinding) getViewDataBinding()).tvToolbarText.setText("Registreer");
    }

    public void signIn(String name, String password) {
        getViewModel().login(name, password);
    }

    public void signUp(RigesterRequest rigesterRequest) {
        getViewModel().register(rigesterRequest);
    }

    @Override
    public void goToPayment() {
        startActivityForResult(new Intent(this, PaymentActivity.class), 102);
    }

    @Override
    public void goToSignIn() {
        changeToSignIn();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(-1, data);
        finish();
    }
}
