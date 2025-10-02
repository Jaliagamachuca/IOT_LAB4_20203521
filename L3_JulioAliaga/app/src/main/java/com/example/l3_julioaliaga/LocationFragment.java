package com.example.l3_julioaliaga;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.l3_julioaliaga.databinding.FragmentLocationBinding;

import java.util.ArrayList;

public class LocationFragment extends Fragment {

    private FragmentLocationBinding binding;
    private MiAdapter adapter;
    private ArrayList<String> fullList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);

        // datos de ejemplo
        for (int i = 0; i < 10; i++) fullList.add("Ciudad " + i);

        adapter = new MiAdapter(new ArrayList<>(fullList));
        binding.recyclerLocation.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerLocation.setAdapter(adapter);

        binding.btnSearchLocation.setOnClickListener(v -> {
            String q = binding.etSearchLocation.getText().toString().trim().toLowerCase();
            if (q.isEmpty()) {
                adapter.updateData(new ArrayList<>(fullList));
                Toast.makeText(getContext(), "Mostrando todos", Toast.LENGTH_SHORT).show();
                return;
            }
            ArrayList<String> filtered = new ArrayList<>();
            for (String s : fullList) {
                if (s.toLowerCase().contains(q)) filtered.add(s);
            }
            adapter.updateData(filtered);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

