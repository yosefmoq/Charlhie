package com.yosefmoq.charlhie.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.yosefmoq.charlhie.DrawerActivity;
import com.yosefmoq.charlhie.R;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    ImageView ivMenu;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.homeViewModel = (HomeViewModel) new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView imageView = (ImageView) root.findViewById(R.id.ivMenu);
        this.ivMenu = imageView;
        imageView.setOnClickListener(view -> ((DrawerActivity) requireActivity()).drawer.openDrawer(GravityCompat.START));
        return root;
    }

}
