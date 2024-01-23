package com.example.i_peste;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class InformationActivity extends AppCompatActivity {

    // Define a constant for the edit request code
    private static final int EDIT_REQUEST_CODE = 1;

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

        // Display the edited information
        displayEditedInformation(classifiedResult);

        // Get the YouTube link from your intent or wherever you have stored it
        String youtubeLink = getYouTubeLink(classifiedResult);

        // Set the YouTube link as clickable text using Linkify
        TextView youtubeLinkTextView = findViewById(R.id.textViewYoutubeLink);
        youtubeLinkTextView.setText(youtubeLink);
        Linkify.addLinks(youtubeLinkTextView, Linkify.WEB_URLS);

        // Set up the Done button click listener
        Button btnDone = findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save data to ReportActivity
                saveDataToReport();

                // Navigate to HomePage
                Intent intent = new Intent(InformationActivity.this, HomePage.class);
                intent.putExtra("insectName", classifiedResult);
                intent.putExtra("YoutubeLink", youtubeLink);
                startActivity(intent);

                // Finish the InformationActivity
                finish();
            }
        });
    }

    private String getYouTubeLink(String classifiedResult) {
        // Retrieve YouTube link from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("InsectInfo", MODE_PRIVATE);
        return preferences.getString(classifiedResult + "_YoutubeLink", "https://www.youtube.com/watch?v=GtL2bkIqjso");
    }

    private void displayEditedInformation(String classifiedResult) {
        // Retrieve edited information from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("InsectInfo", MODE_PRIVATE);
        String pilipinoName = preferences.getString(classifiedResult + "_PilipinoName", "N/A");
        String disease = preferences.getString(classifiedResult + "_Disease", "N/A");
        String whatItDoes = preferences.getString(classifiedResult + "_WhatItDoes", "N/A");
        String howToManage = preferences.getString(classifiedResult + "_HowToManage", "N/A");

        // Find the TextViews in your layout to display additional information
        TextView pilipinoNameTextView = findViewById(R.id.pilipinoNameTextView);
        TextView diseaseTextView = findViewById(R.id.diseaseTextView);
        TextView whatItDoesTextView = findViewById(R.id.whatItDoesTextView);
        TextView howToManageTextView = findViewById(R.id.howToManageTextView);

        // Display information
        pilipinoNameTextView.setText("Pilipino name: " + pilipinoName);
        diseaseTextView.setText("Disease: " + disease);
        whatItDoesTextView.setText("What it does: " + whatItDoes);
        howToManageTextView.setText("How to manage: " + howToManage);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Retrieve the edited insectName from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("InsectInfo", MODE_PRIVATE);
        String editedInsectName = preferences.getString("editedInsectName", "");

        // Display the updated information based on the edited insectName
        if (!editedInsectName.isEmpty()) {
            // Update the classification result to the edited insectName
            displayClassificationResult(editedInsectName);

            // Display the edited additional information
            /*displayAdditionalInformation(editedInsectName);*/

            // Clear the editedInsectName to avoid displaying the same edited information repeatedly
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("editedInsectName");
            editor.apply();
        }
    }
    private void saveDataToReport() {

        SharedPreferences preferences = getSharedPreferences("ReportData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        // Get data from Intent
        byte[] byteArray = getIntent().getByteArrayExtra("capturedPhoto");
        String classifiedResult = getIntent().getStringExtra("classifiedResult");
        String additionalInformation = getAdditionalInformation(classifiedResult);

        // Save data to SharedPreferences
        editor.putString("capturedPhoto", Base64.encodeToString(byteArray, Base64.DEFAULT));
        editor.putString("classifiedResult", classifiedResult);
        editor.putString("additionalInformation", additionalInformation);

        // Retrieve user information from UserData SharedPreferences
        SharedPreferences userDataPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        editor.putString("name", userDataPreferences.getString("name", ""));
        editor.putString("address", userDataPreferences.getString("address", ""));
        editor.putString("age", userDataPreferences.getString("age", ""));
        editor.putString("contact", userDataPreferences.getString("contact", ""));
        editor.putString("date", userDataPreferences.getString("date", ""));

        editor.apply();
    }

    private String getAdditionalInformation(String result) {
        if ("Mole cricket".equals(result)) {
            return "Minor Pest\n" +
                    "Pilipino name: Susuhong\n" +
                    "Susceptible stages: Seedling to tillering stages\n" +
                    "Damage: Adults and nymphs feed on the roots of sown seeds either in the seedbed or of young seedlings, resulting in missing hills. Damage can be tolerated in older plants\n" +
                    "Life cycle: Depending on the field temperature, eggs can hatch in 15-40 days. Nymphs turn to adults in 3-4 months.";
        } else if ("Leaf folder".equals(result)) {
            return "Minor Pest\n" +
                    "Pilipino name: Mambibilot, Maniniklop\n" +
                    "Susceptible stages: Seedling to flowering stages\n" +
                    "Damage: Its larva is destructive. Leaf folder caterpillars fold a rice leaf around themselves and attach the leaf margins together with silk strands. They feed inside the folded leaf creating longitudinal white and transparent streaks on the blade.\n" +
                    "Life cycle: Eggs hatch 4-7 days after being laid singly or in pairs on the young leaves. The larvae feed inside the folded leaves for 15-25 days before pupating. Adults emerge in 6-8 days. Total life cycle takes 25-52 days.";
        }
        // Add more conditions for other results if needed
        else {
            // Default value if no match is found
            return "No additional information available";
        }
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

/*
    private void displayAdditionalInformation(String result) {
        // Find the TextViews in your layout to display additional information
        TextView pilipinoNameTextView = findViewById(R.id.pilipinoNameTextView);
        TextView diseaseTextView = findViewById(R.id.diseaseTextView);
        TextView whatItDoesTextView = findViewById(R.id.whatItDoesTextView);
        TextView howToManageTextView = findViewById(R.id.howToManageTextView);

        // Display information based on the result
        if ("Mole Cricket".equals(result)) {
            // Mole Cricket information
            pilipinoNameTextView.setText("Pilipino name: Susuhong, Kuliglig");
            diseaseTextView.setText("Disease: Minor Pest");
            whatItDoesTextView.setText("What it does: Mole crickets feed on seeds, tillers in mature plants, and roots. They can cut plants at the base resulting to loss of plant stand.");
            howToManageTextView.setText("To prevent mole cricket infestation:\n "+
                    "-Use resistant varieties (modern varieties with long and dense fibrous can tolerate damage better). Contact your local agriculture office for an up-to-date list of available varieties.\n" +
                    "-Flood rice fields for  for 3-4 days, level the field for better water control\n" +
                    "-Avoid construction of a raised nursery to reduce feeding damage on seedlings\n" +
                    "-During land preparation, collect the nymphs and adults");
        } else if ("Leaffolder".equals(result)) {
            // Leaf Folder information
            pilipinoNameTextView.setText("Pilipino name: Mambibilot, Maniniklop");
            diseaseTextView.setText("Disease: Minor Pest");
            whatItDoesTextView.setText("What it does: Leaffolder caterpillars fold a rice leaf around themselves and attach the leaf margins together with silk strands. They feed inside the folded leaf creating longitudinal white and transparent streaks on the blade.");
            howToManageTextView.setText("To prevent leaffolder outbreaks:\n "+
                    "-Use resistant varieties.\n" +
                    "Contact your local agriculture office for an up-to-date list of available varieties.\n" +
                    "-Follow rice with a different crop, or fallow period.\n" +
                    "-Avoid ratooning.\n" +
                    "-Flood and plow field after harvesting if possible.\n" +
                    "-Remove grassy weeds from fields and borders.\n" +
                    "-Reduce density of planting.\n" +
                    "-Use balanced fertilizer rates.");
        } else if ("Stem Borer".equals(result)) {
            // Leaf Folder information
            pilipinoNameTextView.setText("Pilipino name: Aksip, Bagumbong");
            diseaseTextView.setText("Disease: Major Pest");
            whatItDoesTextView.setText("What it does: Stem borers can destroy rice at any stage of the plant from seedling to maturity. They feed upon tillers and causes deadhearts or drying of the central tiller, during vegetative stage; and causes whiteheads at reproductive stage.");
            howToManageTextView.setText("To prevent stem borer infestation:\n "+
                    "-Use resistant varieties.\n" +
                    "-At seedbed and transplanting, handpick and destroy egg masses\n" +
                    "-Raise level of irrigation water periodically to submerge the eggs deposited on the lower parts of the plant\n" +
                    "-Before transplanting, cut the leaf-top to reduce carry-over of eggs from the seedbed to the field\n" +
                    "-Ensure proper timing of planting and synchronous planting, harvest crops at ground level to remove the larvae in stubble, remove stubble and volunteer rice, plow and flood the field");
        } else if ("Rice Whorl Maggot".equals(result)) {
            // Leaf Folder information
            pilipinoNameTextView.setText("Pilipino name: Langaw-palay, Langaw-bukid");
            diseaseTextView.setText("Disease: Minor Pest");
            whatItDoesTextView.setText("What it does: The feeding damage of whorl maggots causes yellow spots, white or transparent patches, and pinholes.");
            howToManageTextView.setText("To prevent rice whorl maggot outbreaks:\n "+
                    "-There is no cultural control for rice whorl maggot.\n" +
                    "-Small wasps parasitized the eggs and the maggots. Dolicopodid flies prey on the eggs and ephydrid flies and spiders feed on the adults.\n" +
                    "-The rice plant can compensate for the damage caused by the rice whorl maggot. Usually, the symptoms disappear during the maximum tillering stage of the crop.");
        } else if ("Planthopper".equals(result)) {
            // Leaf Folder information
            pilipinoNameTextView.setText("Pilipino name: Hanip, Ngusong Kabayo");
            diseaseTextView.setText("Disease: Major Pest");
            whatItDoesTextView.setText("What it does: High population of planthoppers cause leaves to initially turn orange-yellow before becoming brown and dry and this is a condition called hopperburn that kills the plant. ");
            howToManageTextView.setText("To prevent planthopper outbreaks:\n "+
                    "-Remove weeds from the field and surrounding areas.\n" +
                    "-Avoid indiscriminate use of insecticide, which destroys natural enemies.\n" +
                    "-Use light traps (e.g., an electric bulb or kerosene lamp near a light colored wall or over a pan of water) at night when rice is prone to planthopper attack. Do not place lights near seedbeds or fields. If the light trap is inundated with hundreds of BPH, it's a signal to check your seedbed or field immediately; then scout every day for the next few weeks. If farmers monitor on a daily basis anyway, then a light trap is unnecessary.");
        } else if ("Black bug".equals(result)) {
            // Leaf Folder information
            pilipinoNameTextView.setText("Pilipino name: Itim na Atangya");
            diseaseTextView.setText("Disease: Major Pest");
            whatItDoesTextView.setText("What it does: Black bugs remove the sap of the plant. They can cause browning of leaves, deadheart, and bugburn. Their damage also causes stunting in plants, reduced tiller number, and formation of whiteheads.On severe cases, black bugs weaken the plant preventing them from producing seeds.");
            howToManageTextView.setText("To prevent black bug infestation:\n "+
                    "-Use resistant varieties.\n" +
                    "Contact your local agriculture office for an up-to-date list of available varieties.\n" +
                    "-Maintain a clean field by removing the weeds and drying the rice field after plowing.\n" +
                    "-Plant rice varieties of the same maturity date to break the insect’s cycle.\n" +
                    "-Use of mercury bulbs as light traps for egg-laying adults, light trapping of insects should start 5 days before and after the full moon.");
        } else if ("Zigzag leafhopper".equals(result)) {
            // Leaf Folder information
            pilipinoNameTextView.setText("Pilipino name: Guhitang Ngusong Kabayo");
            diseaseTextView.setText("Disease: Minor Pest");
            whatItDoesTextView.setText("What it does: Feeding damage of zigzag leaf hopper causes the leaf tips to dry up, and whole leaves to become orange and curled.");
            howToManageTextView.setText("To prevent zigzag leafhopper outbreaks:\n "+
                    "-Use resistant varieties.\n" +
                    "Contact your local agriculture office for an up-to-date list of available varieties.\n" +
                    "-Follow rice with a different crop, or fallow period.\n" +
                    "-Avoid ratooning.\n" +
                    "-Flood and plow field after harvesting if possible.\n" +
                    "-Remove grassy weeds from fields and borders.");
        } else if ("Rice bug".equals(result)) {
            // Leaf Folder information
            pilipinoNameTextView.setText("Pilipino name: Atangya");
            diseaseTextView.setText("Disease: Major Pest");
            whatItDoesTextView.setText("What it does: Rice bugs damage rice by sucking out the contents of developing grains from pre-flowering spikelets to soft dough stage, therefore causing unfilled or empty grains and discoloration. Immature and adult rice bugs both feed on rice grains.");
            howToManageTextView.setText("To prevent rice bug outbreaks:\n "+
                    "-Remove weeds from fields and surrounding areas to prevent the multiplication of rice bugs during fallow periods.\n" +
                    "-Level fields with even applications of fertilizer and water encourage rice to grow and develop is at the same rate. Planting fields, within a village, at the same time (synchronous planting) also helps reduce rice bug problems.\n" +
                    "-Capturing rice bugs, in the early morning or late afternoon, by net can be effective at low rice bug densities, though labor intensive.\n" +
                    "-Encourage biological control agents: Some wasps, grasshoppers and spiders attack rice bugs or rice bug eggs. Indiscriminate insecticide use disrupts biological control, resulting in pest resurgence.");
        } else if ("Rice Caseworm".equals(result)) {
            // Leaf Folder information
            pilipinoNameTextView.setText("Pilipino name: Uod na nasa Supot");
            diseaseTextView.setText("Disease: Minor Pest");
            whatItDoesTextView.setText("What it does: Rice caseworms or case bearers cut off leaf tips to make leaf cases. Their feeding damage can cut leaves at right angles as with a pair of scissors.");
            howToManageTextView.setText("To prevent rice caseworm outbreaks:\n "+
                    "-Use of correct fertilizer application.\n" +
                    "-Plant early and use wider spacing (30 × 20 cm).\n" +
                    "-Drain the field.\n" +
                    "-Transplant older seedlings. Sparse planting can also reduce the damage.\n" +
                    "-Grow a ratoon.\n" +
                    "-Use foliar treatments of carbamate insecticides; avoid pyrethroids.");
        } else if ("Short-horned grasshopper".equals(result)) {
            // Leaf Folder information
            pilipinoNameTextView.setText("Pilipino name: Balang, Lukton");
            diseaseTextView.setText("Disease: Major Pest");
            whatItDoesTextView.setText("What it does: Feeding damage caused by short-horned grasshoppers and oriental migratory locusts result to cut out areas on leaves and cut-off panicles. They both feed on leaf margins.");
            howToManageTextView.setText("To prevent short-horned grasshopper outbreaks:\n "+
                    "-Flood the stubbles, shave bunds, sweep along the bunds, and pick adults directly from the foliage at night when they are sluggish.\n" +
                    "-Encourage biological control agents: scelionid wasps, parasitic flies, nematodes, and fungal pathogens, birds, frogs, and web-spinning spiders, and a certain species of an entomophthoralean fungus; and platystomatid fly and mite (eggs of oriental migratory locust), ants, birds, bats, field rats, mice, wild pigs, dogs, millipedes, fish, amphibia, reptiles, and monkeys.\n" +
                    "-Use poison baits from salt water and rice bran.\n" +
                    "-Use foliar sprays to control grasshoppers in rice fields. Granules are not effective.");
        } else if ("Wolf spider".equals(result)) {
            // Leaf Folder information
            pilipinoNameTextView.setText("Pilipino name: Gagambang Lobo");
            diseaseTextView.setText("Beneficial");
            whatItDoesTextView.setText("What it does: The wolf spider can eat 7-45 hoppers per day. They are the major predators of planthoppers and leafhoppers. Spiderlings also attack planthopper and leafhopper nymphs. They are aggressive hunter that patrol plant and water surface for prey that include caserowms, leaffolder, whorld maggots, and newly hatched larvae and moths of stem borers.");
*/
/*            howToManageTextView.setText("To prevent leaffolder outbreaks:\n "+
                    "-Use resistant varieties.\n" +
                    "Contact your local agriculture office for an up-to-date list of available varieties.\n" +
                    "-Follow rice with a different crop, or fallow period.\n" +
                    "-Avoid ratooning.\n" +
                    "-Flood and plow field after harvesting if possible.\n" +
                    "-Remove grassy weeds from fields and borders.\n" +
                    "-Reduce density of planting.\n" +
                    "-Use balanced fertilizer rates.");*//*

        } else if ("Orb spider".equals(result)) {
            // Leaf Folder information
            pilipinoNameTextView.setText("Pilipino name: Gagambang Pari");
            diseaseTextView.setText("Beneficial");
            whatItDoesTextView.setText("What it does: They feed on planthoppers, leafhoppers, caseworms, whorl maggots, stem borer adults, and grasshoppers");
*/
/*            howToManageTextView.setText("To prevent leaffolder outbreaks:\n "+
                    "-Use resistant varieties.\n" +
                    "Contact your local agriculture office for an up-to-date list of available varieties.\n" +
                    "-Follow rice with a different crop, or fallow period.\n" +
                    "-Avoid ratooning.\n" +
                    "-Flood and plow field after harvesting if possible.\n" +
                    "-Remove grassy weeds from fields and borders.\n" +
                    "-Reduce density of planting.\n" +
                    "-Use balanced fertilizer rates.");*//*

        } else if ("Dragonfly".equals(result)) {
            // Leaf Folder information
            pilipinoNameTextView.setText("Pilipino name: Tutubi");
            diseaseTextView.setText("Beneficial");
            whatItDoesTextView.setText("What it does: They feed on adult nymphs of planthoppers, leafhoppers and moths.");
*/
/*            howToManageTextView.setText("To prevent leaffolder outbreaks:\n "+
                    "-Use resistant varieties.\n" +
                    "Contact your local agriculture office for an up-to-date list of available varieties.\n" +
                    "-Follow rice with a different crop, or fallow period.\n" +
                    "-Avoid ratooning.\n" +
                    "-Flood and plow field after harvesting if possible.\n" +
                    "-Remove grassy weeds from fields and borders.\n" +
                    "-Reduce density of planting.\n" +
                    "-Use balanced fertilizer rates.");*//*

        } else if ("Ground beetle".equals(result)) {
            // Leaf Folder information
            pilipinoNameTextView.setText("Pilipino name: Tigreng Salagubang");
            diseaseTextView.setText("Beneficial");
            whatItDoesTextView.setText("What it does: They eat planthoppers and leafhoppers. Others prey on hairy catterpillars, semiloopers, and newly hatched larvae of stem borers. They feed inside the folded leaves made by leaffolder larvae. They can eat 3-5 larvae per day.");
*/
/*            howToManageTextView.setText("To prevent leaffolder outbreaks:\n "+
                    "-Use resistant varieties.\n" +
                    "Contact your local agriculture office for an up-to-date list of available varieties.\n" +
                    "-Follow rice with a different crop, or fallow period.\n" +
                    "-Avoid ratooning.\n" +
                    "-Flood and plow field after harvesting if possible.\n" +
                    "-Remove grassy weeds from fields and borders.\n" +
                    "-Reduce density of planting.\n" +
                    "-Use balanced fertilizer rates.");*//*

        }
        // Add more conditions for other results if needed
        else {
            // Default information
            pilipinoNameTextView.setText("Pilipino name: N/A");
            diseaseTextView.setText("Disease: N/A");
            whatItDoesTextView.setText("What it does: N/A");
            howToManageTextView.setText("How to manage: N/A");
        }
    }
*/
}