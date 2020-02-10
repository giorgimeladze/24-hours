package com.example.a24hours.Model;

import android.graphics.Typeface;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class LocalRepository {
    private static DatabaseReference myDatabaseRef;
    private static String institutionName;
    private static String pathName;
    private static String keysPathName;
    private static String keysDistrictsPathName;
    private static Institution institutionObject;
    private static ArrayList<Institution> favorites;

    public static Institution getInstitutionObject() {
        return institutionObject;
    }

    public static void setInstitutionObject(Institution institutionObject) {
        LocalRepository.institutionObject = institutionObject;
    }

    public static String getKeysPathName() {
        return keysPathName;
    }

    public static void setKeysPathName(String keysPathName) {
        LocalRepository.keysPathName = keysPathName;
    }

    public static String getChosenDistrictName() {
        return keysDistrictsPathName;
    }

    public static void setChosenDistrictName(String keysDistrictsPathName) {
        LocalRepository.keysDistrictsPathName = keysDistrictsPathName;
    }

    public static DatabaseReference getDatabaseReference(String path) {
        FirebaseDatabase fd = FirebaseDatabase.getInstance();

        if (path == null || path.equals("") || path.length() == 0 ) {
            myDatabaseRef = fd.getReference();
            return myDatabaseRef;
        } else {
            myDatabaseRef = fd.getReference(path);
            return myDatabaseRef;
        }
    }

    public static DatabaseReference getMyDatabaseRef() {
        return myDatabaseRef;
    }

    public static void setMyDatabaseRef(DatabaseReference myRef) { myDatabaseRef = myRef; }

    public static void setInstitutionName(String name) {
        institutionName = name;
    }

    public static String getInstitutionName() {
        return institutionName;
    }

    public static String getPathName() {
        return pathName;
    }

    public static void setPathName(String pathName) {
        LocalRepository.pathName = pathName;
    }

    public static ArrayList<Institution> getFavorites() {
        if (favorites == null) favorites = new ArrayList<>();
        return favorites;
    }
}
