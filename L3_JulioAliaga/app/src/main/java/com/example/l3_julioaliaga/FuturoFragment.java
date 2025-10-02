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

import com.example.l3_julioaliaga.databinding.FragmentFuturoBinding;

import java.util.ArrayList;

public class FuturoFragment extends Fragment {

    private FragmentFuturoBinding binding;
    private MiAdapter adapter;
    private ArrayList<String> fullList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFuturoBinding.inflate(inflater, container, false);

        // Datos de ejemplo (mejor si usas idLocation - día en formato "id: X - Día: Y")
        for (int i = 1; i <= 10; i++) {
            fullList.add("Football - Deporte " + i);
        }

        adapter = new MiAdapter(new ArrayList<>(fullList));
        binding.recyclerFuturo.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerFuturo.setAdapter(adapter);

        binding.btnSearchFuturo.setOnClickListener(v -> {
            String idLoc = binding.etIdLocationFuturo.getText().toString().trim().toLowerCase();
            String dia = binding.etDiaInteres.getText().toString().trim().toLowerCase();

            if (idLoc.isEmpty() && dia.isEmpty()) {
                adapter.updateData(new ArrayList<>(fullList));
                Toast.makeText(getContext(), "Mostrando todos", Toast.LENGTH_SHORT).show();
                return;
            }

            ArrayList<String> filtered = new ArrayList<>();
            for (String s : fullList) {
                String sLow = s.toLowerCase();
                boolean matchId = !idLoc.isEmpty() && sLow.contains(idLoc);
                boolean matchDia = !dia.isEmpty() && sLow.contains(dia);
                if (matchId || matchDia) filtered.add(s);
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
