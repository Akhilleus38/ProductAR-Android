package com.tsoftmobile.t_softar;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //    Button linkbutton;
    List<Book> IsBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        IsBook = new ArrayList<>();
        IsBook.add(new Book("Buzdolabı","Beyaz Eşya","Elektronik",R.drawable.refrigerator,"https://ar.tsoft.club/can/testing/refrigerator/refrigerator4.glb"));
        IsBook.add(new Book("Çamaşır Makinesi","Beyaz Eşya","Elektronik",R.drawable.washing,"https://ar.tsoft.club/can/testing/washing/washing4.glb"));
        IsBook.add(new Book("Bulaşık Makinesi","Beyaz Eşya","Elektronik",R.drawable.dishwasher,"https://ar.tsoft.club/can/testing/dishwasher/dishw5.glb"));
        IsBook.add(new Book("Fırın","Beyaz Eşya","Elektronik",R.drawable.bakery,"https://ar.tsoft.club/can/testing/oven/oven3.glb"));
        IsBook.add(new Book("Kaktüs","Ev Dekoru","Çiçek",R.drawable.kaktus,"https://ar.tsoft.club/can/testing/Cactus.glb"));
        IsBook.add(new Book("Kahve Makinesi","Beyaz Eşya","Elektronik",R.drawable.coffe,"https://ar.tsoft.club/can/testing/kahve.glb"));
        IsBook.add(new Book("Mixer","Beyaz Eşya","Elektronik",R.drawable.mixer,"https://ar.tsoft.club/can/testing/Mixer.glb"));
        IsBook.add(new Book("Tren","Ev Dekoru","Oyuncak",R.drawable.tren,"https://ar.tsoft.club/can/testing/ToyTrain.glb"));
        IsBook.add(new Book("Robot","Ev Dekoru","Oyuncak",R.drawable.robot,"https://ar.tsoft.club/can/testing/Robot.glb"));


//        //PATLIYOR
//        linkbutton = (Button)findViewById(R.id.linkbutton);
//
//        linkbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Uri linkimiz=Uri.parse("https://developers.google.com/ar"); //butona vermek istediğimiz linki buraya yazıyoruz.
//                Intent intentimiz =new Intent(Intent.ACTION_VIEW,linkimiz);
//                startActivity(intentimiz);
//            }
//        });

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,IsBook);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);
    }
}