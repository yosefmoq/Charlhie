package com.yosefmoq.charlhie.ui.Auth;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.yosefmoq.charlhie.Base.BaseFragment;
import com.yosefmoq.charlhie.R;
import com.yosefmoq.charlhie.databinding.FragmentSignInBinding;
import com.yosefmoq.charlhie.utils.BR;
import com.yosefmoq.charlhie.utils.Utils;


public class SignInFragment extends BaseFragment<FragmentSignInBinding, AuthMV> {
    @Override
    public int getBindingVariable() {
        return BR._all;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sign_in;
    }

    @Override
    public void initItems() {
        ((FragmentSignInBinding) getViewDataBinding()).btnLogin.setOnClickListener(new View.OnClickListener() {
            /* class com.yosefmoq.charlhie.ui.Auth.SignInFragment.AnonymousClass1 */

            public void onClick(View v) {
                if (!Utils.isValidEmail(((FragmentSignInBinding) SignInFragment.this.getViewDataBinding()).etUsername.getText().toString())) {
                    Toast.makeText(SignInFragment.this.getContext(), "Geef geldig e-mail adres in", Toast.LENGTH_LONG).show();
                } else if (((FragmentSignInBinding) SignInFragment.this.getViewDataBinding()).etPassword.getText().toString().length() < 6) {
                    Toast.makeText(SignInFragment.this.getContext(), "Wachtwoord moet minstens 6 karakters bevatten", Toast.LENGTH_LONG).show();
                } else {
                    ((AuthActivity) SignInFragment.this.getContext()).signIn(((FragmentSignInBinding) SignInFragment.this.getViewDataBinding()).etUsername.getText().toString(), ((FragmentSignInBinding) SignInFragment.this.getViewDataBinding()).etPassword.getText().toString());
                }
            }
        });
        ((FragmentSignInBinding) getViewDataBinding()).tvRegister.setOnClickListener(new View.OnClickListener() {
            /* class com.yosefmoq.charlhie.ui.Auth.$$Lambda$SignInFragment$HtT95dIi1CwL_yCwMiZ8uyGUFXE */

            public final void onClick(View view) {
                SignInFragment.this.lambda$initItems$0$SignInFragment(view);
            }
        });
    }

    public /* synthetic */ void lambda$initItems$0$SignInFragment(View v) {
        ((AuthActivity) getContext()).changeToSignUp();
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public void initListners() {
    }

    @Override // com.yosefmoq.charlhie.Base.BaseFragment
    public AuthMV getViewModel() {
        return (AuthMV) ViewModelProviders.of(this).get(AuthMV.class);
    }
}
