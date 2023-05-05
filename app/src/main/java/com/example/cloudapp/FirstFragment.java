package com.example.cloudapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.cloudapp.databinding.FragmentFirstBinding;
import com.example.cloudapp.local.ConsoleDataListener;
import com.example.cloudapp.local.FirebaseRepository;
import com.example.cloudapp.local.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirstFragment extends Fragment {

    private DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
    private FragmentFirstBinding binding;
    private FirebaseRepository FBRepo = new FirebaseRepository();
    private ConsoleDataListener listener = new ConsoleDataListener();



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentFirstBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseRef.child("users").child("maria").setValue(5);
                User u1 = new User("Name", "email", 19);
                FBRepo.saveUser(u1, listener);
                FBRepo.getUser("haha19", listener);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}