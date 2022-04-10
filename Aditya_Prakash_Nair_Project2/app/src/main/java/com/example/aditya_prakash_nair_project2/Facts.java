package com.example.aditya_prakash_nair_project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Facts extends AppCompatActivity {

    List<List<String>> factsList = new ArrayList<>();
    private final String[] facts = new String[]{"Details : ", "Weight : (Pounds)", "Diet : ", "Lifespan : (Years)", "Habitat :"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        fillList();

        Intent intent = getIntent();
        int pos = intent.getIntExtra(MainActivity.POS, 0);
        Log.i("Facts Activity","Postiton = " + pos);
        List<String> selectedAnimalFacts = factsList.get(pos);

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(selectedAnimalFacts.get(0));
        name.setTypeface(null, Typeface.BOLD);

//
//        TextView charecteristics = (TextView) findViewById(R.id.characteristic);
//        charecteristics.setText(fact.get(1));
//
//        TextView weight = (TextView) findViewById(R.id.weight);
//        weight.setText(fact.get(2));
//
//        TextView diet = (TextView) findViewById(R.id.diet);
//        diet.setText(fact.get(3));

        TableLayout table = (TableLayout) findViewById(R.id.factsTable);

        for(int i = 0; i < facts.length; i++){
            TableRow row = new TableRow(this);
            TextView factName = new TextView(this);
            TextView factDesc = new TextView(this);

            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
//            row.setBackgroundResource(R.drawable.border);
//            row.setWeightSum(2);
//            row.setBackgroundResource(R.drawable.top_bottom_border);

//            factName.setText(facts[i]);
//            factName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
//            factName.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.8f));
////            factName.setPadding(3,3,3,3);
//            factName.setTypeface(null, Typeface.BOLD);

//            factName.setBackgroundResource(R.drawable.border);

            factDesc.setText(Html.fromHtml(selectedAnimalFacts.get(i+1)));
            factDesc.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            factDesc.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 0.2f));
            factDesc.setPadding(3,3,3,3);
//            factDesc.setBackgroundResource(R.drawable.border);


//            row.addView(factName);
            row.addView(factDesc);
            table.addView(row);
        }


    }

    private void fillList(){
        factsList.add(new ArrayList<>(Arrays.asList("Cattle", "<b>Details :</b> Cattle (Bos taurus) are large, domesticated, cloven-hooved, herbivores.\n Adult females are referred to as cows and adult males are referred to as bulls.", "<b>Weight :</b> 1600 lbs","<b>Diet :</b> Herbivores","<b> Lifespan :</b> 15-20 years","<b>Habitat :</b> Pastures, savannas, scrub forests, and even desert edges")));
        factsList.add(new ArrayList<>(Arrays.asList("Himalayan Black Bear", "<b>Details :</b> Himalayan black bears have a black coat with a light brown muzzle and a pale yellow crescent on their chest.They are diurnal by nature, though the majority of them have become nocturnal in order to avoid humans. They often spend the day in caves or hollowed out trees.", "<b>Weight :</b> 265 lbs","<b>Diet :</b> Omnivorous", "<b> Lifespan :</b> 25 years", "<b>Habitat :</b> Himalayan black bears are scattered across the Himalayas from Bhutan to Pakistan. They are most populous in mountainous areas and jungles.")));
        factsList.add(new ArrayList<>(Arrays.asList("Fox", "<b>Details :</b> Foxes are small to medium-sized, omnivorous mammals belonging to several genera of the family Canidae..Widely held as a symbol of animal cunning, the red fox is the subject of considerable folklore. The red fox has the largest natural distribution of any land mammal except human beings.", "<b>Weight :</b> 31 lbs","<b>Diet :</b> Omnivorous", "<b> Lifespan :</b> 3-4 years","<b>Habitat :</b> Forested areas, though they are also found in mountains, grasslands and deserts.")));
        factsList.add(new ArrayList<>(Arrays.asList("Spotted Hyena", "<b>Details :</b> The spotted hyena may look unusual, at first sight. It has a large head with a long, thick, muscular neck and powerful jaws that give the hyena the strongest bite of any mammal. Its front legs are longer than its back legs, giving the hyena a profile somewhat like that of a wildebeest or bison.", "<b>Weight :</b> 140 lbs","<b>Diet :</b> Carnivorous","<b> Lifespan :</b> 12 years","<b>Habitat :</b> Savannas, grasslands, woodlands, forest edges, subdeserts, and even mountains up to 4,000 meters.")));
        factsList.add(new ArrayList<>(Arrays.asList("Black Panther", "<b>Details :</b> Black Panther characterized by a coat of black fur or large concentrations of black spots set against a dark background. Black coat coloration is attributed to the expression of recessive alleles in leopards and dominant alleles in jaguars.", "<b>Weight :</b> 165 lbs","<b>Diet :</b> Carnivorous", "<b> Lifespan :</b> 10-12 years", "<b>Habitat :</b> Hot, dense tropical rainforests of South and Southeast Asia.")));
        factsList.add(new ArrayList<>(Arrays.asList("Rhinoceros", "<b>Details :</b> Rhinos are thought to be the second largest land animal, with the elephant being the largest. They have a robust, cylindrical body with a large head, relatively short legs, and short tail. The characteristic feature of these animals is a large horn in the middle of their faces; some species have a second, smaller horn.", "<b>Weight :</b> 3080 lbs","<b>Diet :</b> Herbivores", "<b> Lifespan :</b> 35-50 years", "<b>Habitat :</b> Tropical and subtropical grasslands, savannas and shrublands, tropical moist forests, deserts and shrublands")));
        factsList.add(new ArrayList<>(Arrays.asList("Bengal Tiger", "<b>Details :</b> The Bengal tiger is one of six living subspecies of tigers. This subspecies lives in India, Bangladesh, Nepal, and Bhutan. Like the tiger species as a whole, the IUCN lists the Bengal tiger subspecies as Endangered.", "<b>Weight :</b> 570 lbs","<b>Diet :</b> Carnivores", "<b> Lifespan :</b> 8-15 years","<b>Habitat :</b> Tropical forests of the Indian sub-continent")));
        factsList.add(new ArrayList<>(Arrays.asList("Wolf", "<b>Details :</b> Gray wolves, or timber wolves, are canines with long bushy tails that are often black-tipped. Their coat color is typically a mix of gray and brown with buffy facial markings and undersides, but the color can vary from solid white to brown or black.", "<b>Weight :</b> 145 lbs","<b>Diet :</b> Carnivores", "<b> Lifespan :</b> 14-16 years", "<b>Habitat :</b> Tundra to woodlands, forests, grasslands and deserts")));
        factsList.add(new ArrayList<>(Arrays.asList("Red Deer", "<b>Details :</b> Red deer are ruminants, characterized by a four-chambered stomach. The red deer (Cervus elaphus) is one of the largest deer species. A male red deer is called a stag or hart, and a female is called a hind. ", "<b>Weight :</b> 440 lbs","<b>Diet :</b> Herbivores","<b> Lifespan :</b> 10-20 years","<b>Habitat :</b> Open Woodlands")));
        factsList.add(new ArrayList<>(Arrays.asList("Kangaroo", "<b>Details :</b> All kangaroos have short hair, powerful hind legs, small forelimbs, big feet and a long tail. They have excellent hearing and keen eyesight. Depending on the species, their fur coat can be red, grey or light to dark brown.\n" +
                "\n" +
                "Kangaroos are famous for their means of locomotion: hopping!", "<b>Weight :</b> 150 lbs","<b>Diet :</b> Herbivores", "<b> Lifespan :</b> 8-25 years", "<b>Habitat :</b> Forests, woodlands, plains, and savannas")));
        factsList.add(new ArrayList<>(Arrays.asList("Bison", "<b>Details :</b> The bison has long shaggy brown fur, a mane and beard under its chin and a long tail with a tuft of hair at the end. It has a big head with short black horns and a hump on its shoulders.", "<b>Weight :</b> 2200 lbs","<b>Diet :</b> Herbivores","<b> Lifespan :</b> 10-20 years", "<b>Habitat :</b> Plains, Prairies, River Valley")));
        factsList.add(new ArrayList<>(Arrays.asList("Crocodile", "<b>Details :</b> Crocodiles have powerful jaws with many conical teeth and short legs with clawed webbed toes. They share a unique body form that allows the eyes, ears, and nostrils to be above the water surface while most of the animal is hidden below. The tail is long and massive, and the skin is thick and plated.", "<b>Weight :</b> 1200 lbs","<b>Diet :</b> Carnivores", "<b> Lifespan :</b> 30-40 years", "<b>Habitat :</b> Saltwater")));
    }
}