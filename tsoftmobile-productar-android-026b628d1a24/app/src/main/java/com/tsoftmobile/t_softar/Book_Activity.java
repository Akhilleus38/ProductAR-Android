package com.tsoftmobile.t_softar;

import android.app.AutomaticZenRule;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.SystemClock;
import android.view.View;

public class Book_Activity extends AppCompatActivity {


    Button gonder;
    private TextView tvtitle,tvdescription,tvcategory;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_);

        tvtitle = findViewById(R.id.txtitle);
        tvdescription = findViewById(R.id.txtDesc);
        tvcategory = findViewById(R.id.txtCat);
        img = findViewById(R.id.bookthumbnail);

        // Datayı alma;
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Ürün Başlığı");
        String Category = intent.getExtras().getString("Kategori");
        String Description = intent.getExtras().getString("Açıklama");
        int image =  intent.getExtras().getInt("Küçük Resim");

        // Değerleri ayarlama;
        tvtitle.setText(Title);
        tvcategory.setText(Category);
        tvdescription.setText(Description);
        img.setImageResource(image);


        gonder = findViewById(R.id.linkbutton);
        gonder.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                Intent ıntent = new Intent(getApplicationContext(),arscreen_activity.class);
                startActivity(ıntent);
            }
        });
    }
//    public void gonder(View view) {
//
//        Intent ıntent = new Intent(getApplicationContext(),arscreen_activity.class);
//        startActivity(ıntent);
//    }


    /**
     * Bir denetime hızlı bir şekilde 2 kez (veya daha fazla kez) çift tıklamanın, onClick'in 2 kez (veya daha fazla kez) tetiklenmesine neden olacağı sorunu çöz
     * 2 tıklama olayının zaman aralığını değerlendirerek filtreleyin
     *
     * Alt sınıflar tarafından uygulanır{@link #onSingleClick}Tıklama olayına yanıt verme
     */

    public abstract class OnSingleClickListener implements View.OnClickListener {
        /**
         * En kısa tıklama etkinliği zaman aralığı
         */
        private static final long MIN_CLICK_INTERVAL=600;
        /**
         * Son tıklama zamanı
         */
        private long mLastClickTime;

        /**
         * tıklama yanıtı işlevi
         * @param v Tıklanan görünüm.
         */
        public abstract void onSingleClick(View v);

        @Override
        public final void onClick(View v) {
            long currentClickTime=SystemClock.uptimeMillis();
            long elapsedTime=currentClickTime-mLastClickTime;
            //MLastClickTime'ın her zaman son tıklamanın zamanını kaydetmesini sağlamak için 2 veya 3 isabet olabilir.
            mLastClickTime=currentClickTime;

            if(elapsedTime<=MIN_CLICK_INTERVAL)
                return;

            onSingleClick(v);
        }

    }

}