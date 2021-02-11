package com.yosefmoq.charlhie.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.yosefmoq.charlhie.R;

public class SlideshowFragment extends Fragment {
    private SlideshowViewModel slideshowViewModel;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.slideshowViewModel = (SlideshowViewModel) new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = (TextView) root.findViewById(R.id.text_slideshow);
        this.slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            /* class com.yosefmoq.charlhie.ui.slideshow.SlideshowFragment.AnonymousClass1 */

            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
