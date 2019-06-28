package com.example.exploretogether;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;


public class EventsActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    LinearLayoutManager linearLayoutManager;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Events");
sharedPreferences=getSharedPreferences("SortSettings",MODE_PRIVATE);
String msort=sharedPreferences.getString("Sort","newest");
if(msort.equals("newest")){
    linearLayoutManager=new LinearLayoutManager(this);
    linearLayoutManager.setReverseLayout(true);
    linearLayoutManager.setStackFromEnd(true);
}
else if (msort.equals("oldest")){

        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
}
        recyclerView=findViewById(R.id.recyclerrv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Events");
    }
private void FireBaseSearch(String searchText){
    Query firebasesearchquery=databaseReference.orderByChild("Title").startAt(searchText).endAt(searchText+"\uf8ff");
    FirebaseRecyclerAdapter<NewEventsModel,ViewHolder>firebaseRecyclerAdapter=
            new FirebaseRecyclerAdapter<NewEventsModel, ViewHolder>(
                    NewEventsModel.class,
                    R.layout.list,
                    ViewHolder.class,
                    firebasesearchquery

            ) {
                @Override
                protected void populateViewHolder(ViewHolder viewHolder, NewEventsModel model, int position) {
                    viewHolder.setDetails(getApplicationContext(),model.getTitle(),model.getDescription(),model.getLocation(),model.getDate(),model.getTime(),model.getImage());
                }

                @Override
                public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    ViewHolder viewHolder=super.onCreateViewHolder(parent, viewType);
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

                            Intent intent=new Intent(view.getContext(),EventDetails.class);
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
    recyclerView.setAdapter(firebaseRecyclerAdapter);
}
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<NewEventsModel,ViewHolder>firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<NewEventsModel, ViewHolder>(
                        NewEventsModel.class,
                        R.layout.list,
                        ViewHolder.class,
        databaseReference

                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, NewEventsModel model, int position) {
viewHolder.setDetails(getApplicationContext(),model.getTitle(),model.getDescription(),model.getLocation(),model.getDate(),model.getTime(),model.getImage());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder=super.onCreateViewHolder(parent, viewType);
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

                                Intent intent=new Intent(view.getContext(),EventDetails.class);
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
        recyclerView.setAdapter(firebaseRecyclerAdapter);
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
            showSortDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSortDialog() {
        String[] so={"Newest", "Oldest"};
        AlertDialog .Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Sort By")
                .setIcon(R.drawable.ic_sort_black_24dp)
                .setItems(so, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i==0){
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("Sort","newest");
                            editor.apply();
                            recreate();
                        }
                        else if(i==1){
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("Sort","oldest");
                            editor.apply();
                            recreate();
                        }
                    }
                });
builder.show();
    }
}
