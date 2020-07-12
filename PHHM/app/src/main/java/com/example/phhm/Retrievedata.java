package com.example.phhm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Retrievedata extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "a";
    private Button chooseButton, saveButton, displayButton;
    private ImageView imageView;
    private EditText imageNameEditText;
    private Uri imageUri;
    private ProgressBar progressBar;
    private static final int IMAGE_REQUEST = 1;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    StorageTask uploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrievedata);
        databaseReference= FirebaseDatabase.getInstance().getReference("Upload");
        storageReference= FirebaseStorage.getInstance().getReference("Upload");
        //Toast.makeText(MainActivity.this,"FIREBASE",Toast.LENGTH_LONG).show();
        chooseButton = findViewById(R.id.chooseImageButton);
        saveButton = findViewById(R.id.saveImageButton);
        displayButton = findViewById(R.id.displayImageButton);
        progressBar = findViewById(R.id.progressbarId);
        imageView = findViewById(R.id.imageViewId);
        imageNameEditText = findViewById(R.id.imageNameEditTextId);
        saveButton.setOnClickListener(this);
        chooseButton.setOnClickListener(this);
        displayButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chooseImageButton:
                openFileChooser();
                break;
            case R.id.saveImageButton:
                if(uploadTask!=null&&uploadTask.isInProgress())
                {
                    Toast.makeText(getApplicationContext(),"Uploading in progress",Toast.LENGTH_LONG).show();;
                }
                else{
                    saveData();
                }
                break;
            case R.id.displayImageButton:
                Intent intent=new Intent(Retrievedata.this,ImageActivity.class);
//                intent.putExtra("imageUri", imageUri.toString());
                startActivity(intent);
                break;

        }
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null ) {

            imageUri = data.getData();
            Picasso.get().load(imageUri).into(imageView);
        }

    }
    public String getFileExtension(Uri imageUri)
    {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }
    private void saveData()
    {
        final String imageName= imageNameEditText.getText().toString().trim();
        if(imageName.isEmpty())
        {
            imageNameEditText.setError("Enter the image name");
            imageNameEditText.requestFocus();
            return;
        }
        StorageReference ref=storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));
        ref.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Toast.makeText(getApplicationContext(),"Image is stored successfully",Toast.LENGTH_LONG).show();
                        Task<Uri> urlTask=taskSnapshot.getStorage().getDownloadUrl();
                        while (!urlTask.isSuccessful());
                        Uri downloadUrl=urlTask.getResult();
                        Upload upload=new Upload(imageName, downloadUrl.toString());
                        //Upload upload=new Upload(imageName, taskSnapshot.getStorage().getDownloadUrl().toString());
                        /*if (imageUri != null)
                        {
                            storageReference.putFile(imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
                            {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                                {
                                    if (!task.isSuccessful())
                                    {
                                        throw task.getException();
                                    }
                                    return storageReference.getDownloadUrl();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Uri downloadUri = task.getResult();
                                        Log.e(TAG, "then: " + downloadUri.toString());


                                        Upload upload = new Upload(imageNameEditText.getText().toString().trim(),
                                                downloadUri.toString());

                                        databaseReference.push().setValue(upload);
                                    } else
                                    {
                                        Toast.makeText(Retrievedata.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }*/

                        String uploadId=databaseReference.push().getKey();
                        databaseReference.child(uploadId).setValue(upload);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                        Toast.makeText(getApplicationContext(),"Image is not stored successfully"+exception.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
    }
}

