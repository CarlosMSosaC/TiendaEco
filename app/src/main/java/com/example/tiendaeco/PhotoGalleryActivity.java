package com.example.tiendaeco;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tiendaeco.R;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class PhotoGalleryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView emptyView;
    private PhotoAdapter photoAdapter;
    private List<File> photosList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);

        recyclerView = findViewById(R.id.recyclerView);
        emptyView = findViewById(R.id.emptyView);

        setupRecyclerView();
        loadPhotos();
    }

    private void setupRecyclerView() {
        photoAdapter = new PhotoAdapter(photosList, new PhotoAdapter.OnPhotoClickListener() {
            @Override
            public void onPhotoClick(File photoFile) {
                openPhoto(photoFile);
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(photoAdapter);
    }

    private void loadPhotos() {
        photosList.clear();
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (storageDir != null && storageDir.exists()) {
            File[] photos = storageDir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    String name = file.getName().toLowerCase();
                    return file.isFile() &&
                            (name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png"));
                }
            });

            if (photos != null) {
                Arrays.sort(photos, new Comparator<File>() {
                    @Override
                    public int compare(File o1, File o2) {
                        return Long.compare(o2.lastModified(), o1.lastModified());
                    }
                });
                photosList.addAll(Arrays.asList(photos));
            }
        }

        if (photosList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            photoAdapter.notifyDataSetChanged();
        }
    }

    private void openPhoto(File photoFile) {
        Uri photoURI = FileProvider.getUriForFile(
                this,
                getPackageName() + ".fileprovider",
                photoFile
        );

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(photoURI, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPhotos();
    }
}

