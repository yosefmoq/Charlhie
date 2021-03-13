package com.yosefmoq.charlhie.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.yosefmoq.charlhie.DrawerActivity;
import com.yosefmoq.charlhie.R;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    ImageView ivMenu;
    TextView tvEmail,tvWebsite,tvPhone;
    ImageView ivInsta;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.homeViewModel = (HomeViewModel) new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView imageView = (ImageView) root.findViewById(R.id.ivMenu);
        tvEmail = root.findViewById(R.id.tvEmailAddress);
        tvWebsite = root.findViewById(R.id.tvWebsite);
        ivInsta   = root.findViewById(R.id.ivInsta);
        tvPhone   = root.findViewById(R.id.tvPhoneNumber);
        this.ivMenu = imageView;
        tvEmail.setOnClickListener(v -> {
            openEmailIntent();
        });
        tvPhone.setOnClickListener(v -> {
            openPhoneCallIntent();
        });
        tvWebsite.setOnClickListener(v -> {
            openWebAddress("https://www.charlhie.be");
        });
        ivInsta.setOnClickListener(v -> {
            openWebAddress("https://www.instagram.com/charlhie20");
        });
        imageView.setOnClickListener(view -> ((DrawerActivity) requireActivity()).drawer.openDrawer(GravityCompat.START));
        return root;
    }
    private void openEmailIntent() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"pvh@charlhie.be"});

        startActivity(Intent.createChooser(intent, "Email via..."));
    }

    private void openPhoneCallIntent() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String phone = "+32468128272";
        intent.setData(Uri.parse("tel:".concat(phone)));
        startActivity(intent);
    }
    private void openWebAddress(String s) {
        String url = s;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}
