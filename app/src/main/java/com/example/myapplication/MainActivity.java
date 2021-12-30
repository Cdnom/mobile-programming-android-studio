package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static final int TAKE_BIG_PHOTO = 1;
    private static final int TAKE_SMALL_PHOTO = 2;
    private static final int TAKE_VIDEO = 3;
    private static final int SHOW_GALLERY = 4;
    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";
    private ImageView imageView;
    private Bitmap bitmap;
    private VideoView videoView;
    private Uri videoUri;
    private String mCurrentPhotoPath;
    private Intent takePictureIntent;
    private Intent takeVideoIntent;
    private Intent showGalleryIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        videoView = (VideoView) findViewById(R.id.videoView);

        findViewById(R.id.cameraBButton).setOnClickListener(this);
        findViewById(R.id.cameraSButton).setOnClickListener(this);
        findViewById(R.id.videoButton).setOnClickListener(this);
        findViewById(R.id.galleryButton).setOnClickListener(this);
        findViewById(R.id.closeButton).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private File getAlbumDir() {
        File storageDir = null;
        final String namaDirektori = "/DCIM/CameraSample";
        if
        (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            storageDir = new File(Environment.getExternalStorageDirectory() + namaDirektori);
            if (storageDir != null) {
                if (!storageDir.mkdirs()) {
                    if
                    (!storageDir.exists()) {
                        Log.d("CameraSample", "Gagal membuat direktori " + storageDir);
                        return null;
                    }
                }
            }
        } else {
            Log.v(getString(R.string.app_name), "Eksternal penyimpanan tidak diset READ/WRITE");
        }
        return storageDir;
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp;

        File albumF = getAlbumDir();
        File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);

        return imageF;
    }

    private File setUpPhotoFile() throws IOException {
        File f = createImageFile();

        mCurrentPhotoPath = f.getAbsolutePath();
        return f;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cameraBButton:
                takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                File f = null;

                try {
                    f = setUpPhotoFile();
                    mCurrentPhotoPath = f.getAbsolutePath();
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile( MainActivity.this, BuildConfig.APPLICATION_ID +".provider", f));
                } catch (IOException e) {
                    e.printStackTrace();
                    f = null;
                    mCurrentPhotoPath = null;
                }

                startActivityForResult(takePictureIntent, TAKE_BIG_PHOTO);
                break;
            case R.id.cameraSButton:
                takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, TAKE_SMALL_PHOTO);
                break;
            case R.id.videoButton:
                takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(takeVideoIntent, TAKE_VIDEO);
                break;
            case R.id.galleryButton:
                showGalleryIntent = new Intent(Intent.ACTION_PICK);
                showGalleryIntent.setType("image/*");
                startActivityForResult(showGalleryIntent, SHOW_GALLERY);
                break;
            case R.id.closeButton:
                System.exit(0);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_BIG_PHOTO:
                if (resultCode == RESULT_OK) {
                    handleBigCameraPhoto();
                }
                break;
            case TAKE_SMALL_PHOTO:
                if (resultCode == RESULT_OK) {
                    handleSmallCameraPhoto(data);
                }

                break;
            case TAKE_VIDEO:
                if (resultCode == RESULT_OK) {
                    handleCameraVideo(data);
                }
                break;
            case SHOW_GALLERY:
                if (resultCode == RESULT_OK) {
                    Uri photoUri =
                            data.getData();
                    imageView.setImageURI(photoUri);

                    videoView.setVisibility(View.INVISIBLE);
                    imageView.setVisibility(View.VISIBLE);
                }

                break;
        }
    }

    private void handleBigCameraPhoto() {
        if (mCurrentPhotoPath != null) {
            setPic();
            galleryAddPic();
            mCurrentPhotoPath = null;
        }
    }

    private void setPic() {
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = 1;
        if ((targetW > 0) || (targetH > 0)) {
            scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        }
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

        imageView.setImageBitmap(bitmap);
        videoUri = null;
        imageView.setVisibility(View.VISIBLE);
        videoView.setVisibility(View.INVISIBLE);
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }


    private void handleSmallCameraPhoto(Intent intent) {
        Bundle extras = intent.getExtras();
        bitmap = (Bitmap) extras.get("data");
        imageView.setImageBitmap(bitmap);

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp;
        if (MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, imageFileName, null) != null) {
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
        }

        videoUri = null;
        videoView.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.VISIBLE);
    }

    private void handleCameraVideo(Intent intent) {
        videoUri = intent.getData();
        videoView.setVideoURI(videoUri);

        bitmap = null;
        imageView.setVisibility(View.INVISIBLE);
        videoView.setVisibility(View.VISIBLE);
        videoView.start();
    }
}
