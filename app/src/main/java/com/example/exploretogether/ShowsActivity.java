package com.example.exploretogether;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ShowsActivity extends AppCompatActivity {
    RecyclerView recyclerViewshw;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shows);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Events");

        recyclerViewshw=(RecyclerView) findViewById(R.id.listttt);
        recyclerViewshw.setHasFixedSize(true);
        recyclerViewshw.setLayoutManager(new LinearLayoutManager(this));
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Events");
    }
    private void FireBaseSearch(String searchText){
        Query firebasesearchquery=databaseReference.orderByChild("Title").startAt(searchText).endAt(searchText+"\uf8ff");
        FirebaseRecyclerAdapter<NewShowsModel,HolderShows> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<NewShowsModel, HolderShows>(
                        NewShowsModel.class,
                        R.layout.list,
                        HolderShows.class,
                        firebasesearchquery

                ) {
                    @Override
                    protected void populateViewHolder(HolderShows viewHolder, NewShowsModel model, int position) {
                        viewHolder.setDetails(getApplicationContext(),model.getTitle(),model.getDescription(),model.getLocation(),model.getDate(),model.getTime(),model.getImage());
                    }

                    @Override
                    public HolderShows onCreateViewHolder(ViewGroup parent, int viewType) {
                        HolderShows viewHolder=super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListner(new ViewHolder.ClickListner() {
                            @Override
                            public void onItemClick(View view, int position) {
                                TextView title=view.findViewById(R.id.list_title);
                                TextView desc=view.findViewById(R.id.tvmdesc);
                                TextView loc=view.findViewById(R.id.tvmloc);
                                TextView date=view.findViewById(R.id.tvmdate);
                                TextView time=view.findViewById(R.id.tvmtime);
                                ImageView image=view.findViewById(R.id.ivimgvw);

                                String mtitle=title.getText().toString();
                                String mdesc=desc.getText().toString();
                                String mloc=loc.getText().toString();
                                String mdate=date.getText().toString();
                                String mtime=time.getText().toString();
                                Drawable drawable=image.getDrawable();
                                Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();

                                Intent intent=new Intent(view.getContext(),ShowDetails.class);
                                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                                byte[]bytes=stream.toByteArray();
                                intent.putExtra("image",bytes);
                                intent.putExtra("title",mtitle);
                                intent.putExtra("desc",mdesc);
                                intent.putExtra("loc",mloc);
                                intent.putExtra("date",mdate);
                                intent.putExtra("time",mtime);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemlongClick(View view, int position) {

                            }
                        });
                        return viewHolder;
                    }
                };
        recyclerViewshw.setAdapter(firebaseRecyclerAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<NewShowsModel,HolderShows>firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<NewShowsModel, HolderShows>(
                        NewShowsModel.class,
                        R.layout.list,
                        HolderShows.class,
                        databaseReference

                ) {
                    @Override
                    protected void populateViewHolder(HolderShows viewHolder, NewShowsModel model, int position) {
                        viewHolder.setDetails(getApplicationContext(),model.getTitle(),model.getDescription(),model.getLocation(),model.getDate(),model.getTime(),model.getImage());
                    }

                    @Override
                    public HolderShows onCreateViewHolder(ViewGroup parent, int viewType) {
                        HolderShows viewHolder=super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListner(new ViewHolder.ClickListner() {
                            @Override
                            public void onItemClick(View view, int position) {
                                TextView title=view.findViewById(R.id.list_title);
                                TextView desc=view.findViewById(R.id.tvmdesc);
                                TextView loc=view.findViewById(R.id.tvmloc);
                                TextView date=view.findViewById(R.id.tvmdate);
                                TextView time=view.findViewById(R.id.tvmtime);
                                ImageView image=view.findViewById(R.id.ivimgvw);

                                String mtitle=title.getText().toString();
                                String mdesc=desc.getText().toString();
                                String mloc=loc.getText().toString();
                                String mdate=date.getText().toString();
                                String mtime=time.getText().toString();
                                Drawable drawable=image.getDrawable();
                                Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();

                                Intent intent=new Intent(view.getContext(),ShowDetails.class);
                                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                                byte[]bytes=stream.toByteArray();
                                intent.putExtra("image",bytes);
                                intent.putExtra("title",mtitle);
                                intent.putExtra("desc",mdesc);
                                intent.putExtra("loc",mloc);
                                intent.putExtra("date",mdate);
                                intent.putExtra("time",mtime);
                                startActivity(intent);
                            }

                            @Override
                            public void onItemlongClick(View view, int position) {

                            }
                        });
                        return viewHolder;
                    }
                };
        recyclerViewshw.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        SearchView searchViewfrfr=(SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_searchhh));
        searchViewfrfr.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String q) {
                FireBaseSearch(q);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                FireBaseSearch(s);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_sort){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}