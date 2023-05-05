package com.example.cloudapp.local;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseRepository {
    private DatabaseReference databaseReference;

    public FirebaseRepository() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");
    }

    public void saveUser(User user, final DataListener dataListener) {
        String userId = user.getId();
        databaseReference.child(userId).setValue(user)
                .addOnSuccessListener(aVoid -> {
                    // User data saved successfully
                    dataListener.onDataSaved();

                })
                .addOnFailureListener(e -> {
                    // Error saving user data
                    dataListener.onDataSaveFailure(e.getMessage());
                });
    }

    public void getUser(String userId, final DataListener dataListener) {
        DatabaseReference userReference = databaseReference.child(userId);
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User data found, retrieve data
                    User user = dataSnapshot.getValue(User.class);
                    dataListener.onDataLoaded(user);
                } else {
                    // User data does not exist
                    dataListener.onDataNotFound();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error retrieving user data
                dataListener.onDataLoadFailure(databaseError.getMessage());
            }
        });
    }
}
