package com.tsoftmobile.t_softar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;

public class arscreen_activity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ArFragment arFragment;
    private static final String TAG = arscreen_activity.class.getSimpleName();
    Button help,btnAccept,closealert,nextalert,nextalert2;
    Dialog epicDialog,alert;
    ImageButton geri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arscreen_activity);

        Snackbar snackbarY = Snackbar.make(findViewById(android.R.id.content),
                "Etrafı Tarayın.", BaseTransientBottomBar.Duration.class.getModifiers());
        snackbarY.show();

        /** GERİ TUŞU **/
        geri = findViewById(R.id.geri);
        geri.setOnClickListener(v -> finish());
        geri.setEnabled(true);

        /** YARDIM BUTONU **/
        epicDialog = new Dialog(this);
        help = findViewById(R.id.helpButton);
        help.setOnClickListener(v -> ShowPositivePopup());
        help.setEnabled(true);

        /** KULLANIM TALİMATLARI**/

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart",true);

        alert = new Dialog(this);
        alert.setContentView(R.layout.alertpage);
        alert.getWindow().setBackgroundDrawable((new ColorDrawable(Color.TRANSPARENT)));

        if (firstStart) {
            alert.show();
        }

        nextalert = alert.findViewById(R.id.alertbtn);
        nextalert.setOnClickListener(v10 -> {
            alert.setContentView(R.layout.alertpage2);
            nextalert2 = alert.findViewById(R.id.nextalert);
            nextalert2.setOnClickListener(v -> {
                alert.setContentView(R.layout.alertpage3);
                closealert = alert.findViewById(R.id.closealert);
                closealert.setOnClickListener(v1 -> alert.dismiss());
            });
        });

//        /** EKRAN GÖRÜNTÜSÜ ALMA (SS) **/
//
//        Button btn = findViewById(R.id.screenshotButton);
//        btn.setOnClickListener(v -> takePhoto());

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        progressBar = findViewById(R.id.progressBar);
        arFragment.setOnTapArPlaneListener((HitResult hitResult, Plane plane, MotionEvent motionEvent)  -> {
            @SuppressLint("WrongConstant") Snackbar snackbarX = Snackbar.make(findViewById(android.R.id.content),
                    "Görsel İndiriliyor.", Snackbar.ANIMATION_MODE_SLIDE);
            snackbarX.show();
            placeModel(hitResult.createAnchor());
            progressBar.setVisibility(View.VISIBLE);
        });
        //        help.performClick();


        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();
    }

//    private String generateFilename() {
//
//        //현재시간을 기준으로 파일 이름 생성
//        String date =
//                new SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.getDefault()).format(new Date());
//        return Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES) + File.separator + "IM/" + date + "_screenshot.jpg";
//    }
//
//    private void saveBitmapToDisk(Bitmap bitmap, String filename) throws IOException {
//
//        //사용자의 갤러리에 IM 디렉토리 생성 및 Bitmap 을 JPEG 형식으로 갤러리에 저장
//        File out = new File(filename);
//        if (!out.getParentFile().exists()) {
//            out.getParentFile().mkdirs();
//        }
//        try (FileOutputStream outputStream = new FileOutputStream(filename);
//             ByteArrayOutputStream outputData = new ByteArrayOutputStream()) {
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputData);
//            outputData.writeTo(outputStream);
//            outputStream.flush();
//            outputStream.close();
//        } catch (IOException ex) {
//            throw new IOException("Failed to save bitmap to disk", ex);
//        }
//    }
//
//    private void takePhoto(){
//        //PixelCopy 를 사용하여 카메라 화면과 object 를 bitmap 으로 생성
//        final String filename = generateFilename();
//        ArSceneView view = arFragment.getArSceneView();
//
//        final Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(),
//                Bitmap.Config.ARGB_8888);
//
//        final HandlerThread handlerThread = new HandlerThread("PixelCopier");
//        handlerThread.start();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            PixelCopy.request(view, bitmap, (copyResult) -> {
//                if (copyResult == PixelCopy.SUCCESS) {
//                    try {
//                        saveBitmapToDisk(bitmap, filename);
//
//                        //Media Scanning 실시
//                        Uri uri = Uri.parse("file://" + filename);
//                        Intent i = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//                        i.setData(uri);
//                        sendBroadcast(i);
//
//                    } catch (IOException e) {
//                        Toast toast = Toast.makeText(arscreen_activity.this, e.toString(),
//                                Toast.LENGTH_LONG);
//                        toast.show();
//                        return;
//                    }
//                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
//                            "Görüntü Kaydedilsin mi?.", Snackbar.LENGTH_LONG);
//                    snackbar.setAction("Evet", v -> {
//                        //어플 내에서 저장한 스크린샷을 확인 가능
//                        File photoFile = new File(filename);
//
//                        Uri photoURI = FileProvider.getUriForFile(arscreen_activity.this,
//                                arscreen_activity.this.getPackageName() + ".ar.codelab.name.provider",
//                                photoFile);
//                        Intent intent = new Intent(Intent.ACTION_VIEW, photoURI);
//                        intent.setDataAndType(photoURI, "image/*");
//                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                        startActivity(intent);
//                    });
//                    snackbar.show();
//                } else {
//                    Toast toast = Toast.makeText(arscreen_activity.this,
//                            "Ekran görüntüsü kaydedilemedi!" + copyResult, Toast.LENGTH_LONG);
//                    toast.show();
//                }
//                handlerThread.quitSafely();
//            }, new Handler(handlerThread.getLooper()));
//        }
//    }

    public  void ShowPositivePopup(){
        epicDialog.setContentView(R.layout.tutorial_screen_01);
        btnAccept = epicDialog.findViewById(R.id.btnAccept);
        progressBar.setVisibility(View.GONE);
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
        btnAccept.setOnClickListener(v -> {
                    epicDialog.setContentView(R.layout.tutorial_screen_02);
                    Button btnAccept2 = epicDialog.findViewById(R.id.btnAccept2);
                    btnAccept2.setOnClickListener(view-> {
                                epicDialog.setContentView(R.layout.tutorial_screen_03);
                                Button btnAccept3 = epicDialog.findViewById(R.id.btnAccept3);
                                btnAccept3.setOnClickListener(v1 -> {
                                            epicDialog.setContentView(R.layout.tutorial_screen_04);
                                            Button btnAccept4 = epicDialog.findViewById(R.id.btnAccept4);
                                            btnAccept4.setOnClickListener(v2 ->
                                                    epicDialog.dismiss()
                                            );
                                        }
                                );
                            }
                    );
                }
        );
    }
    private void placeModel (Anchor anchor){

        ModelRenderable.builder()
                .setSource(this, RenderableSource
                        .builder()
                        .setSource(this, Uri.parse(Common.selectedModel.host_url),RenderableSource.SourceType.GLB)
                        //.setScale(0.75f)
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build()
                )
                .setRegistryId(Common.selectedModel.host_url)
                .build()
                .thenAccept(modelRenderable ->
                        addNodeToScene(modelRenderable, anchor)
                )
                .exceptionally(throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(throwable.getMessage()).show();
                    hideLoading();
                    return null;
                });
    }
    private void addNodeToScene(ModelRenderable modelRenderable, Anchor anchor) {

        AnchorNode anchorNode  = new AnchorNode(anchor);
        hideLoading();
        ux.TransformableNode2 node = new ux.TransformableNode2(arFragment.getTransformationSystem());
        hideLoading();
        node.setParent(anchorNode);
        hideLoading();
        node.setRenderable(modelRenderable);
        hideLoading();
        progressBar.setVisibility(View.GONE);
        hideLoading();
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        progressBar.setVisibility(View.GONE);
        node.select();
        hideLoading();
    }
    void hideLoading() {
        runOnUiThread(() -> progressBar.setVisibility(View.GONE));
    }
}