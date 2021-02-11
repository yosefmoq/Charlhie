package com.yosefmoq.charlhie.ui.Auth;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.yosefmoq.charlhie.Base.BaseFragment;
import com.yosefmoq.charlhie.R;
import com.yosefmoq.charlhie.databinding.FragmentSignUpBinding;
import com.yosefmoq.charlhie.models.RigesterRequest;
import com.yosefmoq.charlhie.utils.Utils;

public class SignUpFragment extends BaseFragment<FragmentSignUpBinding, AuthMV> {
    @Override
    public int getBindingVariable() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sign_up;
    }

    @Override
    public void initItems() {
    }

    @Override
    public void initListners() {
        ((FragmentSignUpBinding) getViewDataBinding()).btnRegisterUser.setOnClickListener((View.OnClickListener) view -> SignUpFragment.this.lambda$initListners$0$SignUpFragment(view));
    }

    public /* synthetic */ void lambda$initListners$0$SignUpFragment(View v) {
        String name = ((FragmentSignUpBinding) getViewDataBinding()).etRegisterEmail.getText().toString();
        String firstname = ((FragmentSignUpBinding) getViewDataBinding()).etFirstName.getText().toString();
        String lastname = ((FragmentSignUpBinding) getViewDataBinding()).etLastName.getText().toString();
        String postalcode = ((FragmentSignUpBinding) getViewDataBinding()).etPostCode.getText().toString();
        String box = ((FragmentSignUpBinding) getViewDataBinding()).etBox.getText().toString();
        String city = ((FragmentSignUpBinding) getViewDataBinding()).etCity.getText().toString();
        String password = ((FragmentSignUpBinding) getViewDataBinding()).etRegisterPassword.getText().toString();
        String country = ((FragmentSignUpBinding) getViewDataBinding()).etCountry.getText().toString();
        String number = ((FragmentSignUpBinding) getViewDataBinding()).etNumber.getText().toString();
        String streetName = ((FragmentSignUpBinding) getViewDataBinding()).etStreetName.getText().toString();
        String vat = ((FragmentSignUpBinding) getViewDataBinding()).etVat.getText().toString();
        String repeatPassword = ((FragmentSignUpBinding) getViewDataBinding()).etRepeatPassword.getText().toString();
        if (!Utils.isValidEmail(name)) {
            Toast.makeText(getContext(), "Geef geldig e-mail adres in", Toast.LENGTH_LONG).show();
        } else if (password.length() < 6) {
            Toast.makeText(getContext(), "Wachtwoord moet minstens 6 karakters bevatten", Toast.LENGTH_LONG).show();
        } else if (!password.equals(repeatPassword)) {
            Toast.makeText(getContext(), "Wachtwoorden zijn niet hetzelfde", Toast.LENGTH_LONG).show();
        } else if (firstname.length() == 0) {
            Toast.makeText(getContext(), "Voornaam is verplicht", Toast.LENGTH_LONG).show();
        } else if (lastname.length() == 0) {
            Toast.makeText(getContext(), " Achternaam is verplicht", Toast.LENGTH_LONG).show();
        } else if (streetName.isEmpty()) {
            Toast.makeText(getContext(), "Straat is verplicht", Toast.LENGTH_LONG).show();
        } else if (postalcode.isEmpty()) {
            Toast.makeText(getContext(), "Postcode is verplicht", Toast.LENGTH_LONG).show();
        } else if (city.isEmpty()) {
            Toast.makeText(getContext(), "Stad  is verplicht", Toast.LENGTH_LONG).show();
        } else if (((FragmentSignUpBinding) getViewDataBinding()).etVat.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(getContext(), "Telefoonnummer is verplicht", Toast.LENGTH_LONG).show();
        } else {
            RigesterRequest rigesterRequest = new RigesterRequest();
            rigesterRequest.setBoxnumber(box);
            rigesterRequest.setCity(city);
            rigesterRequest.setCountry(country);
            rigesterRequest.setEmail(name);
            rigesterRequest.setFirstname(firstname);
            rigesterRequest.setLastname(lastname);
            rigesterRequest.setHousenumber(number);
            rigesterRequest.setPassword(password);
            rigesterRequest.setPostcode(postalcode);
            rigesterRequest.setStreetname(streetName);
            rigesterRequest.setVat(vat);
            Log.v("ttt", rigesterRequest.toString());
            ((AuthActivity) getContext()).signUp(rigesterRequest);
        }
    }

    @Override
    public AuthMV getViewModel() {
        return (AuthMV) ViewModelProviders.of(this).get(AuthMV.class);
    }
}
