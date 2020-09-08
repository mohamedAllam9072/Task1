package com.example.task1.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.task1.R;
import com.example.task1.databinding.FragmentBlankBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BlankFragment extends Fragment {

    private FragmentBlankBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false);
        binding.buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetDialogTheme);

                View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet, container, false);
                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();
            }


        });
        return binding.getRoot();
    }
}