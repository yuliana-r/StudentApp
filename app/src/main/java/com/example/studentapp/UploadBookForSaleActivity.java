package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UploadBookForSaleActivity extends AppCompatActivity {

    EditText titleBookUpload, authorBookUpload, isbnBookUpload, descBookUpload;
    Button uploadBook;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    ImageView bookUserImage;
    String url;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_book_for_sale);

        titleBookUpload = findViewById(R.id.uploadTitleEt);
        authorBookUpload = findViewById(R.id.uploadAuthorEt);
        isbnBookUpload = findViewById(R.id.uploadIsbnEt);
        descBookUpload = findViewById(R.id.uploadDescriptionEt);
        uploadBook = findViewById(R.id.uploadBookButton);
        bookUserImage = findViewById(R.id.uploadBookImage);


//        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("books_for_sale");
        final String bookId = databaseReference.push().getKey();
        storageReference = FirebaseStorage.getInstance().getReference("books");
//        storageReference = firebaseStorage.getReferenceFromUrl("gs://studentapp-c7f7d.appspot.com");

        bookUserImage.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(i, 100);
        });

        uploadBook.setOnClickListener(v -> {

            final StorageReference reference = storageReference.child(bookId+"."+getExtension(imageUri));

            reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            url = uri.toString();
                            BookForSale bookForSale = new BookForSale(bookId, titleBookUpload.getText().toString(), authorBookUpload.getText().toString(),
                                    isbnBookUpload.getText().toString(), descBookUpload.getText().toString(), url);
                            databaseReference.child(bookId).setValue(bookForSale);
                            Toast.makeText(UploadBookForSaleActivity.this, "Book for sale added successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(UploadBookForSaleActivity.this, LibraryActivity.class);
                            startActivity(i);



                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            reference.delete();
                        }
                    });
                }
            });
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data.getData() != null) {
            Picasso.get().load(data.getData()).fit().into(bookUserImage);
            imageUri = data.getData();
        }
    }


    private String getExtension(Uri _imageUri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(contentResolver.getType(_imageUri));
    }
}