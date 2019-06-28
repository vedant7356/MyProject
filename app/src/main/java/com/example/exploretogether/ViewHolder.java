package com.example.exploretogether;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {


    View mview;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mview=itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mclickListner.onItemClick(view,getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mclickListner.onItemlongClick(view,getAdapterPosition());
                return false;
            }
        });
    }

    public void setDetails(Context ctx,String title,String desc,String loc,String date,String time, String image){
        TextView Title=mview.findViewById(R.id.list_title);
        TextView Desc=mview.findViewById(R.id.tvmdesc);
        TextView Loc=mview.findViewById(R.id.tvmloc);
        TextView Date=mview.findViewById(R.id.tvmdate);
        TextView Time=mview.findViewById(R.id.tvmtime);
        ImageView Image=mview.findViewById(R.id.ivimgvw);

        Title.setText(title);
        Desc.setText(desc);
        Loc.setText(loc);
        Date.setText(date);
        Time.setText(time);
      Picasso.get().load(image).into(Image);
    }

    private ViewHolder.ClickListner mclickListner;
    public interface ClickListner{
        void onItemClick(View view,int position);
        void onItemlongClick(View view,int position);
    }
   public void setOnClickListner(ViewHolder.ClickListner clickListner){
        mclickListner=clickListner;
   }
}
