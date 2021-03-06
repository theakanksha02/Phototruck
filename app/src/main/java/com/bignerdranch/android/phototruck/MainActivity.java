
package com.bignerdranch.android.phototruck;

import android.app.WallpaperManager;
import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import com.bignerdranch.android.phototruck.utility.ItemClickSupport;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private RecyclerView rvGrid,rvCategory;
    private RecyclerView.LayoutManager mLayoutManager;
    private MainAdapter mAdapter;
    private WallpaperAdapter adapter;
    TextView tvRandom ;
    ImageView img;





    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    ArrayList<String> mCategoryList;
    List<Integer> mCurrentWallpaperList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);





    Log.d("bla-blah", "this is just a log");
        img=(ImageView) findViewById(R.id.iv_wallpaper);




        rvGrid = (RecyclerView) findViewById(R.id.rv_grid);
        rvCategory = (RecyclerView) findViewById(R.id.rv_category);

        mCategoryList = new ArrayList<String>();
        mCategoryList.add("Nature");
        mCategoryList.add("Abstract");
        mCategoryList.add("Spiritual");
        mCategoryList.add("Cartoons");
        mCategoryList.add("Gadgets");






        // for category
        rvCategory.setAdapter(new MainAdapter(this, mCategoryList));
        rvCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();

                mCurrentWallpaperList = getWallpaperList(mCategoryList.get(position));
                // here i have change


                adapter = new WallpaperAdapter(mCurrentWallpaperList,MainActivity.this);
                mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

                rvGrid.setLayoutManager(mStaggeredGridLayoutManager);

                rvGrid.setAdapter(adapter);


                rvGrid.setHasFixedSize(false);
                SpacesItemDecoration decoration = new SpacesItemDecoration(8);
                rvGrid.addItemDecoration(decoration);




            }
        });


        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        ItemClickSupport.addTo(rvGrid).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        // here you handle what you want to do on the click of item.
                        // set the wallpaper here.

                        // here you get the wallpaper user have clicked from mCurrentWallpaperList
                        int imageResource = mCurrentWallpaperList.get(position);
                        setThisAsWallpaper(imageResource);

                    }
                }
        );


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id= item.getItemId();
        if(res_id==R.id.action_about)
        {
            FragmentManager fm = getSupportFragmentManager();
            MyDialogFragment dialogFragment = new MyDialogFragment ();
            dialogFragment.show(fm, "Sample Fragment");

        }


        return true;
    }



    void setThisAsWallpaper(int imageResource){
        WallpaperManager wallmgr= WallpaperManager.getInstance(getApplicationContext());
        try
        {
            wallmgr.setResource(imageResource);

            Snackbar bar =  Snackbar.make(findViewById(android.R.id.content), "Wallpaper Updated", Snackbar.LENGTH_LONG)
                    .setAction("DISMISS", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Handle user action
                        }
                    });
            bar.setActionTextColor(Color.RED);
            View snackbarView = bar.getView();
            snackbarView.setBackgroundColor(Color.DKGRAY);
            TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
                   bar.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * this method will retun wallpaper list based on
     * category selected.
     * */
    public List<Integer> getWallpaperList(String category){
        if (category.equalsIgnoreCase("nature")){

            final List<Integer> wallList = new ArrayList<>();
            wallList.add(R.drawable.wall1);
            wallList.add(R.drawable.wall2);
            wallList.add(R.drawable.wall3);
            wallList.add(R.drawable.wall4);
            wallList.add(R.drawable.wall5);
            wallList.add(R.drawable.wall6);
            wallList.add(R.drawable.wall7);
            wallList.add(R.drawable.wall8);
            wallList.add(R.drawable.wall9);
            wallList.add(R.drawable.wall10);
            wallList.add(R.drawable.wall55);
            wallList.add(R.drawable.wall56);
            wallList.add(R.drawable.wall57);
            wallList.add(R.drawable.wall58);

            return  wallList;
        } else if(category.equalsIgnoreCase("Abstract")){

            final List<Integer> wallList = new ArrayList<>();
            wallList.add(R.drawable.wall11);
            wallList.add(R.drawable.wall12);
            wallList.add(R.drawable.wall13);
            wallList.add(R.drawable.wall14);
            wallList.add(R.drawable.wall15);
            wallList.add(R.drawable.wall16);
            wallList.add(R.drawable.wall17);
            wallList.add(R.drawable.wall18);
            wallList.add(R.drawable.wall19);
            wallList.add(R.drawable.wall20);


            return wallList;
        }

        else if(category.equalsIgnoreCase("Spiritual")){

            final List<Integer> wallList = new ArrayList<>();
            wallList.add(R.drawable.wall21);
            wallList.add(R.drawable.wall22);
            wallList.add(R.drawable.wall23);
            wallList.add(R.drawable.wall24);
            wallList.add(R.drawable.wall25);
            wallList.add(R.drawable.wall26);
            wallList.add(R.drawable.wall27);
            wallList.add(R.drawable.wall28);
            wallList.add(R.drawable.wall29);
            wallList.add(R.drawable.wall30);


            return wallList;
        }


        else if(category.equalsIgnoreCase("Cartoons")){

            final List<Integer> wallList = new ArrayList<>();
            wallList.add(R.drawable.wall31);
            wallList.add(R.drawable.wall32);
            wallList.add(R.drawable.wall33);
            wallList.add(R.drawable.wall34);
            wallList.add(R.drawable.wall35);
            wallList.add(R.drawable.wall36);
            wallList.add(R.drawable.wall37);
            wallList.add(R.drawable.wall38);
            wallList.add(R.drawable.wall39);
            wallList.add(R.drawable.wall40);
            wallList.add(R.drawable.wall41);
            wallList.add(R.drawable.wall42);


            return wallList;
        }


        else if(category.equalsIgnoreCase("Gadgets")){

            final List<Integer> wallList = new ArrayList<>();
            wallList.add(R.drawable.wall43);
            wallList.add(R.drawable.walll44);
            wallList.add(R.drawable.wall45);
            wallList.add(R.drawable.wall46);
            wallList.add(R.drawable.wall47);
            wallList.add(R.drawable.wall48);
            wallList.add(R.drawable.wall49);
            wallList.add(R.drawable.wall50);
            wallList.add(R.drawable.wall51);
            wallList.add(R.drawable.wall52);



            return wallList;
        }
        else {

            return null;
        }
    }

}