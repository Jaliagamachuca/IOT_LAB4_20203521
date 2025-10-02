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

import com.example.l3_julioaliaga.databinding.FragmentPronosticoBinding;

import java.util.ArrayList;

public class PronosticoFragment extends Fragment {

    private FragmentPronosticoBinding binding;
    private MiAdapter adapter;
    private ArrayList<String> fullList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPronosticoBinding.inflate(inflater, container, false);

        for (int i = 0; i < 10; i++) fullList.add("Pronóstico Día " + i);

        adapter = new MiAdapter(new ArrayList<>(fullList));
        binding.recyclerPronostico.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerPronostico.setAdapter(adapter);

        binding.btnSearchPronostico.setOnClickListener(v -> {
            String idLoc = binding.etIdLocation.getText().toString().trim();
            String days = binding.etDays.getText().toString().trim();

            if (idLoc.isEmpty() && days.isEmpty()) {
                adapter.updateData(new ArrayList<>(fullList));
                Toast.makeText(getContext(), "Mostrando todos", Toast.LENGTH_SHORT).show();
                return;
            }

            ArrayList<String> filtered = new ArrayList<>();
            for (String s : fullList) {
                if (s.contains(idLoc) || s.contains(days)) {
                    filtered.add(s);
                }
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
