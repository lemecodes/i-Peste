package com.example.i_peste;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        // Get the captured photo and classified result from the intent
        byte[] byteArray = getIntent().getByteArrayExtra("capturedPhoto");
        String classifiedResult = getIntent().getStringExtra("classifiedResult");

        // Display the captured photo
        displayCapturedPhoto(byteArray);

        // Display the classification result
        displayClassificationResult(classifiedResult);

        // Display additional information for specific items
        displayAdditionalInformation(classifiedResult);

        Button btnDone = findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save data to ReportActivity
                saveDataToReport();

                // Navigate to HomePage
                Intent intent = new Intent(InformationActivity.this, HomePage.class);
                startActivity(intent);
                finish(); // Finish the InformationActivity
            }
        });
    }

    private void saveDataToReport() {
        SharedPreferences preferences = getSharedPreferences("ReportData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Get data from Intent
        byte[] byteArray = getIntent().getByteArrayExtra("capturedPhoto");
        String classifiedResult = getIntent().getStringExtra("classifiedResult");
        String additionalInformation = getAdditionalInformation(classifiedResult);

        // Save data to SharedPreferences
        editor.putString("name", preferences.getString("name", ""));
        editor.putString("address", preferences.getString("address", ""));
        editor.putString("age", preferences.getString("age", ""));
        editor.putString("contact", preferences.getString("contact", ""));
        editor.putString("capturedPhoto", Base64.encodeToString(byteArray, Base64.DEFAULT));
        editor.putString("classifiedResult", classifiedResult);
        editor.putString("additionalInformation", additionalInformation);

        editor.apply();
    }

    private String getAdditionalInformation(String result) {
        if ("Mole cricket".equals(result)) {
            return "Additional information for Mole cricket";
        } else if ("Leaf folder".equals(result)) {
            return "Additional information for Leaf folder";
        } else if ("Case worm".equals(result)) {
            return "Additional information for Case worm";
        } else if ("Yellow stem borer".equals(result)) {
            return "Additional information for Yellow stem borer";
        } else if ("Brown planthopper".equals(result)) {
            return "Additional information for Brown planthopper";
        } else if ("Zigzag leafhopper".equals(result)) {
            return "Additional information for Zigzag leafhopper";
        } else if ("Dragonfly".equals(result)) {
            return "Additional information for Dragonfly";
        } else if ("Orb spider".equals(result)) {
            return "Additional information for Orb spider";
        } else if ("Wolf spider".equals(result)) {
            return "Additional information for Wolf spider";
        } else if ("Ripple bug".equals(result)) {
            return "Additional information for Ripple bug";
        }

        // Default value if no match is found
        return "No additional information available";

    }




    private void displayCapturedPhoto(byte[] byteArray) {
        try {
            if (byteArray != null && byteArray.length > 0) {
                // Convert the byte array back to a Bitmap
                Bitmap capturedPhoto = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                // Find the ImageView in your layout and set the Bitmap
                ImageView imageView = findViewById(R.id.imageViewCapturedPhoto);
                imageView.setImageBitmap(capturedPhoto);

            } else {
                Log.e("InformationActivity", "Captured photo is null or empty");
            }
        } catch (Exception e) {
            Log.e("InformationActivity", "Error displaying captured photo: " + e.getMessage());
        }
    }

    private void displayClassificationResult(String result) {
        // Find the TextView in your layout and set the classification result
        TextView textViewResult = findViewById(R.id.textViewClassificationResult);
        textViewResult.setText("Name: " + result);
    }

    private void displayAdditionalInformation(String result) {
        // Find the TextView in your layout to display additional information
        TextView informationTextView = findViewById(R.id.informationTextView);

        // Check if the result is "Pen" and display pen-specific information
        if ("Mole cricket".equals(result)) {
            String moleCricket =
                    "Minor Pest\n" +
                    "Pilipino name: Susuhong\n" +
                            "Susceptible stages: Seedling to tillering stages\n" +
                            "Damage: Adults and nymphs feed on the roots of sown seends either in seedbed or of young seedlings, resulting in missing hills. Damage can be tolerated in older plants\n" +
                    "Life cycle: Depending on the field temperature, eggs can hatch in 15-40 days. Nymphs turn to adults in 3-4 months.";
            informationTextView.setText(moleCricket);
        }else if ("Leaf folder".equals(result)) {
            String leafFolder = "Minor Pest\n" +
                    "Pilipino name: Mambibilot, Maniniklop\n" +
                    "Susceptible stages: Seedling to flowering stages\n" +
                    "Damage: Its larva is destructive. Leaf folder caterpillars fold a rice leaf around themselves and attach the leaf margins together with silk strands. They feed inside the folded leaf creating longitudinal white and transparent streaks on the blade.\n" +
                    "Life cycle: Eggs hatch 4-7 days after being laid singly or in pairs on the young leaves. The larvae feed inside the folded leaves for 15-25 days before pupationg. Adults emerge in 6-8 days. Total life cycle takes 25-52 days.";
            informationTextView.setText(leafFolder);

        }else if ("Case worm".equals(result)) {
            String caseWorm = "Minor Pest\n" +
                    "Pilipino name: Uod na nasa supot\n" +
                    "Susceptible stages: Seedling to tillering\n" +
                    "Damage: Its larva is damaging. Caseworms cut off leaf tips to make leaf cases. At night, they feed on the lower side of leaves on the water surface, or submerged. A young crop can recover from defoliation but maturity may be delayed by 7-10 days.\n" +
                    "Life cycle: Eggs hatch into larvae in 2-6 days. Larvae are semi-aquatic and construct their cases. The adults emerge in 4-7 days from the pupa, and can live up to 3 weeks";
            informationTextView.setText(caseWorm);

        }else if ("Yellow stem borer".equals(result)) {
            String yellowstemBorer = "Major Pest\n" +
                    "Pilipino name: Dilaw na aksip, Dilaw na bagumbong\n" +
                    "Susceptible stages: All rice stages\n" +
                    "Damage: The larvae bore into and feed on the stems. In young plants, the central leaf whorl does not unfold, turns brownish, and dries up; lower leaves remain green and healthy. Such symptom is referred to as deadheart. In older plants, the panicles dry up with unfilled grains and turn white. Such symptom is called whitehead. Only one larva bores in a stem.\n" +
                    "Life cycle: Egg masses are laid in batches of 80-150, and are covered with the brown anal hairs of the female moth. An egg mass hatches simultaneously in 4-9 days. Larval period is 30-40 days. The adult moths emerge from the pupa within 7-11 days. The adult can survive 4-10 days without food.";
            informationTextView.setText(yellowstemBorer);

        }else if ("Brown planthopper".equals(result)) {
            String brownPlanthopper = "Major Pest\n" +
                    "Pilipino name: Kayumangging hanip, Kayumangging ngusong kabayo\n" +
                    "Susceptible stages: All crop growth stages\n" +
                    "Damage: Adults and nymphs suck the sap at the base of the tillers. Plants turn yellow and dry up rapidly. Heavy infestation creates brown patches of dried plants known as hopperburn. They also cause indirect damage by transmitting virus diseases: ragged stunt, grassy stunt, and wilted stunt. Excreted honeydew on infested plants may also become a medium for sooty mold fungus.\n" +
                    "Life cycle: Eggs are laid in batches inside the leaf sheaths and on the leaf midrib, and hatch in 4-8 days. Nymphs molt five times within 14-21 days before turning to adults.";
            informationTextView.setText(brownPlanthopper);

        }else if ("Zigzag leafhopper".equals(result)) {
            String zigzagLeafhopper = "Minor Pest\n" +
                    "Pilipino name: Guhitang ngusong kabayo\n" +
                    "Susceptible stages: Early growth stages\n" +
                    "Damage:  Adults and nymphs cause direct damage by removing sap from young leave. They also transmit orange leaf, tungro, and dwarf viruses.\n" +
                    "Life cycle: Eggs hatch into nymphs in 7-9 days. There are 5 instars lasting for 16 days, before they become adults. Adults live for 10-14 days.";
            informationTextView.setText(zigzagLeafhopper);

        }else if ("Dragonfly".equals(result)) {
            String dragonfly =
                    "Pilipino name: Tutubi\n" +
                    "Characteristics: Their wings are bigger than those of the damselfly, and can fly long distances. Nymphs lack fully developed wings, and are often attached to lower parts of the rice tillers.\n" +
                    "Food: They prey on adult nymphs of planthoppers, leafhoppers, and moths.";
            informationTextView.setText(dragonfly);

        }else if ("Orb spider".equals(result)) {
            String orbspider =
                    "Pilipino name: Gagambang pari; Gagambang gumagawa ng sapot na bilog\n" +
                    "Characteristics: Highly colorful spiders with curved legs and large and swollen abdomen. They hang head down from the center of the webs, and rely on their webs to catch their prey by making a strong, sticky, and highly specialized vertical orb web trap. This web structure makes it more efficient in sharing their preys.\n" +
                    "Food: They feed on planthoppers, leafhoppers, caseworms, whorl maggots, stem borer adults, large butterflies, and grasshoppers.";
            informationTextView.setText(orbspider);

        }else if ("Wolf spider".equals(result)) {
            String wolfspider =
                    "Pilipino name: Gagambang lobo\n" +
                    "Characteristics: They have an oval-shaped abdomen with long and tapered legs. The colors are usually dull with gray, brown, and black predominating. They do not build webs, instead they catch their prey directly.\n" +
                    "Food: The wolf spider can eat 7-45 hoppers per day. They are the major predators of planthoppers and leafhoppers. Spiderlings also attack planthopper and leafhopper nymphs. They are aggressive hunter that patrol plant and water surface for prey that include caseworms, leaffolders, whorl maggots, and newly hatched larvae and moths of stem borers.";
            informationTextView.setText(wolfspider);

        }else if ("Ripple bug".equals(result)) {
            String ripplebug =
                    "Pilipino name: N/A\n" +
                    "Characteristics: They are dark brown to black, broad-shouldered, and minute. They can be observed on the water surface. They also sting.\n" +
                    "Food: They kill planthopper and leafhopper nymphs that fall into the water. They also eat newly hatched larvae of stem borers. One ripple bug can prey on 4-7 hoppers per day.";
            informationTextView.setText(ripplebug);






        }
    }
}