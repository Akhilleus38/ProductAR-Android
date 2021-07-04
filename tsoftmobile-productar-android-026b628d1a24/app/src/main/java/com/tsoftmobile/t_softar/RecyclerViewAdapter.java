package com.tsoftmobile.t_softar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Book> mData ;

    public RecyclerViewAdapter(Context mContext, List<Book> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardveiw_item_book,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder holder, final int position) {

        holder.tv_book_title.setText(mData.get(position).getTitle());

        holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view){

               Intent intent = new Intent(mContext, com.tsoftmobile.t_softar.Book_Activity.class);

               //verileri aktarmak(in book activity)
               intent.putExtra("Ürün Başlığı",mData.get(position).getTitle());
               intent.putExtra("Kategori",mData.get(position).getCategory());
               intent.putExtra("Açıklama",mData.get(position).getDescription());
               intent.putExtra("Küçük Resim",mData.get(position).getThumbnail());

               //aktiviteyi başlatır
               mContext.startActivity(intent);


//               if (holder.cardView.getVisibility() == View.GONE){
                   holder.cardView.setVisibility(View.VISIBLE);
                   com.tsoftmobile.t_softar.Common.selectedModel = mData.get(position);
//               } else {
//                   holder.cardView.setVisibility(View.GONE);
//                   Common.selectedModel =  null;
//               }

           }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_book_title;
        TextView tv_book_cat;
        ImageView img_book_thumbnail;
        CardView cardView;

        public MyViewHolder (View itemView) {

            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id);
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }


}
