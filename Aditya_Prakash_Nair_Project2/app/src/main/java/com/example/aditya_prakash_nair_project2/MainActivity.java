package com.example.aditya_prakash_nair_project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    protected static final String IMAGE_ID = "IMG_ID";
    protected static final String POS = "POS";
    protected static final String LANDSCAPE_ID = "LANDSCAPE_ID";

    private ArrayList<Uri> wikiList = new ArrayList<Uri>();
    private ArrayList<Integer> imageList = new ArrayList<Integer>(
            Arrays.asList(R.drawable.cow, R.drawable.bear, R.drawable.fox,
                          R.drawable.hyena, R.drawable.panther, R.drawable.rhino,
                          R.drawable.tiger, R.drawable.wolf, R.drawable.deer,
                          R.drawable.kangaroo, R.drawable.bison, R.drawable.croc));
    private HashMap<Integer, String> map = new HashMap<>();
    private ArrayList<Integer> landScapeList = new ArrayList<>(
            Arrays.asList(R.drawable.cow_land, R.drawable.bear_land, R.drawable.fox_land,
                          R.drawable.hyena_land, R.drawable.panther_land, R.drawable.rhino_land,
                           R.drawable.tiger_land, R.drawable.wolf_land, R.drawable.deer_land,
                          R.drawable.kangaroo_land, R.drawable.bison_land, R.drawable.croc_land)
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeWikiLinks();
        fillMap();
        GridView imageGrid = (GridView) findViewById(R.id.imageGrid);
        registerForContextMenu(imageGrid);
//        Log.i("Main Activity", "First Nmae = " + map.get(imageList.get(0)));
        imageGrid.setAdapter(new ImageAdapter(this, imageList, this.map));

        imageGrid.setOnItemClickListener((parent, v, position, id) -> {

            Intent intent = new Intent(MainActivity.this, EnlargeImageActivity.class);
//            Log.i("MainActivity", "Image id = " + id);

            intent.putExtra(IMAGE_ID,(int) id);
            intent.putExtra(POS,(int) position);
            intent.putExtra(LANDSCAPE_ID, landScapeList.get((int) position));

            startActivity(intent);

        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.wiki:
                Uri wikiLink = wikiList.get(info.position) ;
                Intent wikiIntent = new Intent(Intent.ACTION_VIEW);
                wikiIntent.setData(wikiLink) ;
                wikiIntent.addCategory(Intent.CATEGORY_BROWSABLE) ;
                startActivity(wikiIntent) ;
                return true;
            case R.id.facts:
                Intent imageClickIntent = new Intent(MainActivity.this, Facts.class);
                imageClickIntent.putExtra(MainActivity.POS, info.position);
                startActivity(imageClickIntent);
                return true;
            case R.id.enlarge:
                Intent enlargeIntent = new Intent(MainActivity.this, EnlargeImageActivity.class);
                enlargeIntent.putExtra(IMAGE_ID,(int) info.id);
                enlargeIntent.putExtra(MainActivity.POS, info.position);
                enlargeIntent.putExtra(LANDSCAPE_ID, landScapeList.get(info.position));
                startActivity(enlargeIntent);
                return true;
            default:
            return super.onContextItemSelected(item);
        }
    }

    private void initializeWikiLinks(){
        wikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Cattle"));
        wikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Himalayan_black_bear"));
        wikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Fox"));
        wikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Spotted_hyena"));
        wikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Black_panther"));
        wikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Rhinoceros"));
        wikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Bengal_tiger"));
        wikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Wolf"));
        wikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Red_deer"));
        wikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Kangaroo"));
        wikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Bison"));
        wikiList.add(Uri.parse("https://en.wikipedia.org/wiki/Crocodile"));
    }

    private void fillMap(){
        String[] name = new String[]{"Cattle", "Himalayan Black Bear", "Fox", "Spotted Hyena","Black Panther",
                                      "Rhinoceros", "Bengal Tiger", "Wolf", "Red Deer", "Kangaroo", "Bison", "Crocodile"};
        int i = 0;
        for(int index : imageList){
            this.map.put(index, name[i++]);
        }
    }


}