package com.example.i_peste;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    private EditText pilipinoNameEditText, diseaseEditText, whatItDoesEditText, howToManageEditText, youtubeLinkEditText;
    private String insectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        pilipinoNameEditText = findViewById(R.id.editTextPilipinoName);
        diseaseEditText = findViewById(R.id.editTextDisease);
        whatItDoesEditText = findViewById(R.id.editTextWhatItDoes);
        howToManageEditText = findViewById(R.id.editTextHowToManage);
        youtubeLinkEditText = findViewById(R.id.editTextYoutubeLink);

        Intent intent = getIntent();
        insectName = intent.getStringExtra("insectName");

        // Load default information into EditText fields
        loadDefaultInformation(insectName);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view -> saveChanges());

        youtubeLinkEditText.setOnClickListener(v -> openYouTubeLink());
    }

    private void loadDefaultInformation(String insectName) {
        // Retrieve default information from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("InsectInfo", MODE_PRIVATE);

        // Initialize default information for each insect type
        String defaultPilipinoName = "";
        String defaultDisease = "";
        String defaultWhatItDoes = "";
        String defaultHowToManage = "";
        String defaultYoutubeLink = "";

        // Set default values based on insect type
        if ("Black bug".equals(insectName)) {
            defaultPilipinoName = "Itim na Atangya";
            defaultDisease = "The black bug is a significant pest that can cause substantial damage to rice crops, particularly in dry season rice crops and densely planted fields.";
            defaultWhatItDoes = "Black bugs remove the sap of the plant. They can cause browning of leaves, deadheart, and bugburn. Their damage also causes stunting in plants, reduced tiller number, and formation of whiteheads.";
            defaultHowToManage = "To prevent black bug in rice crops, follow these steps:\n" +
                    "1. Use resistant varieties: Plant rice varieties that are resistant to black bug infestation.\n" +
                    "- Contact your local agriculture office for up-to-date lists of varieties available.\n" +
                    "- Choose rice varieties that are resistant to black bug infestation.\n" +
                    "- Plant the resistant varieties in your rice fields.\n" +
                    "2. Maintain a clean field: Remove weeds and dry the rice field after plowing to prevent black bugs from thriving\n" +
                    "3. Synchronize planting: Plant rice varieties of the same maturity date and synchronize planting with neighboring farms to break the insect's cycle\n" +
                    "4. Use biological control agents: Introduce natural enemies of black bugs, such as wasps, grasshoppers, and spiders, to help control the population\n" +
                    "5. Use Metarhizium: Apply the biocontrol agent Metarhizium anisopilae, which is effective, cheap, and environmentally friendly, to control rice black bugs\n" +
                    "6. Other methods: Use traps, plant flowers, and keep the field weed-free to further control black bug infestation";
            defaultYoutubeLink = "click the link below for more information\n" +
                    "https://www.youtube.com/watch?v=NRT1K5ipLzs&t=15s";
        } else if ("Zigzag leafhopper".equals(insectName)) {
            defaultPilipinoName = "Guhitang Ngusong Kabayo";
            defaultDisease = "The zigzag leafhopper is an important vector of viral diseases, including tungro, rice dwarf, and rice orange-leaf viruses, which can cause significant damage to rice crops.";
            defaultWhatItDoes = "What it does: Feeding damage of zigzag leaf hopper causes the leaf tips to dry up, and whole leaves to become orange and curled.";
            defaultHowToManage = "To prevent zigzag leafhopper in rice crops, follow these steps:\n" +
                    "1. Practice crop rotation: Rotate rice crops with other crops to prevent nutrient depletion and reduce the risk of infestation.\n" +
                    "2. Use light traps: Use light traps, such as sucking machines, to trap zigzag leafhoppers in seedling and pre-harvesting stages.\n" +
                    "3. Use neem extract: Apply neem extract, a botanical control agent, to control zigzag leafhoppers.\n" +
                    "- Obtain neem extract: Purchase neem extract from a reliable source, such as a certified supplier or agricultural research center.\n" +
                    "- Prepare the neem extract: Soak the pounded neem kernel powder in water overnight. Squeeze the muslin pouch containing the powder and filter the extract. Add an emulsifier like tween 80, sandovit, soap oil, nirma, or soap cake powder to the filtrate. One ml of emulsifier is added to one liter of water. The emulsifier helps the extract to stick well to the leaf surface.\n" +
                    "- Apply the neem extract: Spray the neem extract on the rice crops, focusing on the nymph stage of the zigzag leafhopper, as this is when neem extract is most effective.\n" +
                    "4. Maintain a clean field: Remove weeds and grassy plants in fallow fields to prevent the zigzag leafhopper and the viruses it transmits from thriving.\n" +
                    "5. Use resistant varieties: Plant rice varieties that are resistant to zigzag leafhopper infestation.\n" +
                    "- Identify resistant varieties: Consult with local agricultural authorities or research institutions to identify rice varieties that are resistant to zigzag leafhopper infestation.\n" +
                    "- Obtain the resistant seeds: Acquire the seeds of the identified resistant rice varieties from a reliable source, such as certified seed suppliers or agricultural research centers.\n" +
                    "- Plant the resistant varieties: Sow the seeds of the resistant rice varieties in your rice fields according to recommended planting practices, such as seed rate and planting depth.\n" +
                    "6. Use biological control agents: Introduce natural enemies of zigzag leafhoppers, such as mymarid wasps and mirid bugs, to help regulate the population.\n" +
                    "7. Monitor and adjust: Regularly inspect your rice crops for signs of infestation and adjust your management practices as needed.";
            defaultYoutubeLink = "click the link below for more information\n" +
                    "https://www.youtube.com/watch?v=n0w7dBwO4Ao";
        } else if ("Rice Whorl Maggot".equals(insectName)) {
            defaultPilipinoName = "Langaw-palay, Langaw-bukid";
            defaultDisease = "";
            defaultWhatItDoes  = "The feeding damage of whorl maggots causes yellow spots, white or transparent patches, and pinholes.";
            defaultHowToManage = "To prevent rice whorl maggot in rice crops, follow these steps:\n" +
                    "1. Practice crop rotation: Rotate rice crops with other crops to prevent nutrient depletion and reduce the risk of infestation.\n" +
                    "2. Use resistant varieties: Plant rice varieties that are resistant to pests and diseases, including rice whorl maggot.\n" +
                    "- Identify resistant varieties: Consult with local agricultural authorities or research institutions to identify rice varieties that are resistant to rice whorl maggot infestation.\n" +
                    "- Obtain the resistant seeds: Acquire the seeds of the identified resistant rice varieties from a reliable source, such as certified seed suppliers or agricultural research centers.\n" +
                    "- Plant the resistant varieties: Sow the seeds of the resistant rice varieties in your rice fields according to recommended planting practices, such as seed rate and planting depth.\n" +
                    "3. Maintain a clean field: Remove weeds and dry the rice field after plowing to prevent rice whorl maggot from thriving.\n" +
                    "4. Weed your fields regularly: Keep the fields weed-free to deter pests and diseases, as weeds can provide a habitat for them.\n" +
                    "5. Synchronize planting: Synchronize planting with neighboring farms to reduce pests, as crop plants at the same developmental stage can be more effectively managed.\n" +
                    "6. Use biological control agents: Introduce natural enemies of rice whorl maggot, such as wasps, grasshoppers, and spiders, to help control the population.\n" +
                    "7. Monitor and adjust: Regularly inspect your rice crops for signs of infestation and adjust your management practices as needed.";
            defaultYoutubeLink = "click the link below for more information\n" +
                    "https://www.youtube.com/watch?v=8tsB-s9l2hA";
        } else if ("Mole Cricket".equals(insectName)) {
            defaultPilipinoName = "Susuhong, Kuliglig";
            defaultDisease = "Mole crickets do not directly cause diseases, their feeding damage can make crops more susceptible to other pests and diseases, leading to further damage and potential crop loss.";
            defaultWhatItDoes  = "Mole crickets feed on seeds, tillers in mature plants, and roots. They can cut plants at the base resulting to loss of plant stand.";
            defaultHowToManage = "To prevent mole cricket in rice crops, follow these steps:\n" +
                    "1. Use resistant varieties (modern varieties with long and dense fibrous can tolerate damage better). Contact your local agriculture office for an up-to-date list of available varieties.\n" +
                    "- Identify resistant varieties: Consult with local agricultural authorities or research institutions to identify rice varieties that are resistant to pests and diseases, including mole crickets.\n" +
                    "- Obtain the resistant seeds: Acquire the seeds of the identified resistant rice varieties from a reliable source, such as certified seed suppliers or agricultural research centers.\n" +
                    "- Plant the resistant varieties: Sow the seeds of the resistant rice varieties in your rice fields according to recommended planting practices, such as seed rate and planting depth.\n" +
                    "2. Flood rice fields for  for 3-4 days, level the field for better water control\n" +
                    "3. Avoid construction of a raised nursery to reduce feeding damage on seedlings\n" +
                    "4. During land preparation, collect the nymphs and adults";
            defaultYoutubeLink = "click the link below for more information\n" +
                    "https://www.youtube.com/watch?v=CwSvGyQ9zzY&t=204s";
        } else if ("Rice Bug".equals(insectName)) {
            defaultPilipinoName = "Atangya";
            defaultDisease = "The rice bug is known to transmit Sarocladium oryzae and Sarocladium attenuatum fungi, which can cause sheath rot disease in rice crops.";
            defaultWhatItDoes  = "Rice bugs damage rice by sucking out the contents of developing grains from pre-flowering spikelets to soft dough stage, therefore causing unfilled or empty grains and discoloration. Immature and adult rice bugs both feed on rice grains.";
            defaultHowToManage = "To prevent rice bug in rice crops, follow these steps:\n" +
                    "1. Remove weeds from fields and surrounding areas to prevent the multiplication of rice bugs during fallow periods.\n" +
                    "2. Level fields with even applications of fertilizer and water encourage rice to grow and develop is at the same rate. Planting fields, within a village, at the same time (synchronous planting) also helps reduce rice bug problems.\n" +
                    "3. Capturing rice bugs, in the early morning or late afternoon, by net can be effective at low rice bug densities, though labor intensive.\n" +
                    "4. Encourage biological control agents: Some wasps, grasshoppers and spiders attack rice bugs or rice bug eggs. Indiscriminate insecticide use disrupts biological control, resulting in pest resurgence.";
            defaultYoutubeLink = "click the link below for more information\n" +
                    "https://www.youtube.com/watch?v=VWRBQDN8JVk";
        } else if ("Planthopper".equals(insectName)) {
            defaultPilipinoName = "Hanip, Ngusong Kabayo";
            defaultDisease = " Some of the diseases caused by planthoppers include Rice dwarf virus (RDV), Rice orange-leaf virus (ROLV), and Rice tungro virus (RTV). While planthoppers transmit these diseases, they do not directly cause the diseases themselves. ";
            defaultWhatItDoes  = "High population of planthoppers cause leaves to initially turn orange-yellow before becoming brown and dry and this is a condition called hopperburn that kills the plant. ";
            defaultHowToManage = "To prevent plant hopper in rice crops, follow these steps:\n" +
                    "1. Remove weeds from the field and surrounding areas.\n" +
                    "2. Promote healthy rice landscapes: Plant different types of flowering crops alongside rice to encourage natural predators of planthoppers.\n" +
                    "3. Avoid indiscriminate use of insecticide, which destroys natural enemies.\n" +
                    "4. Use light traps (e.g., an electric bulb or kerosene lamp near a light colored wall or over a pan of water) at night when rice is prone to planthopper attack. Do not place lights near seedbeds or fields. If the light trap is inundated with hundreds of BPH, it's a signal to check your seedbed or field immediately; then scout every day for the next few weeks. If farmers monitor on a daily basis anyway, then a light trap is unnecessary.";
            defaultYoutubeLink = "click the link below for more information\n" +
                    "https://www.youtube.com/watch?v=tAcIwYqAft4";
        } else if ("Rice Caseworm".equals(insectName)) {
            defaultPilipinoName = "Uod na nasa Supot";
            defaultDisease = "The rice caseworm, also known as Parapoynx stagnalis, is an insect that causes damage to rice crops. The symptoms of infestation include the cutting of rice leaves at right angles to make leaf cases, resulting in linear grazing of leaves and ladder-like structures of hard fibers on the affected leaves. The damage caused by the rice caseworm can be severe, leading to stunted growth and death of rice plants. ";
            defaultWhatItDoes  = "Rice caseworms or case bearers cut off leaf tips to make leaf cases. Their feeding damage can cut leaves at right angles as with a pair of scissors.";
            defaultHowToManage = "To prevent rice caseworm in rice crops, follow these steps:\n" +
                    "1. Plant early: Early planting can help rice crops avoid the peak activity period of caseworm moths.\n" +
                    "2. Drain the field: Draining rice fields for 5-7 days can effectively kill caseworm larvae.\n" +
                    "3. Provide wider hill spacing: Provide rice fields with wider hill spacing, typically 30 x 20 cm, to experience less damage from caseworm.\n" +
                    "4. Apply nitrogen fertilizer: Applying nitrogen fertilizer at optimal dosages and using split applications can reduce the incidence of caseworm.\n" +
                    "5. Use a rope to dislodge the caseworm: Use a rope to dislodge the caseworm, causing them to fall on the ground and subsequently drain the water, gather the cases, and destroy them.\n" +
                    "6. Plant older seedlings: Transplant older seedlings to reduce the risk of caseworm infestation.";
            defaultYoutubeLink = "click the link below for more information\n" +
                    "https://www.youtube.com/watch?v=bUzHyTfRVB8";
        } else if ("Stem Borer".equals(insectName)) {
            defaultPilipinoName = "Aksip, Bagumbong";
            defaultDisease = "Stem borer is a stem boring insect which is a serious pest of rice. It also infests maize, sugarcane and wheat. The stem borer caterpillars damage these crops by boring or tunnelling inside their plant stems. ";
            defaultWhatItDoes = "Stem borers can destroy rice at any stage of the plant from seedling to maturity. They feed upon tillers and causes deadhearts or drying of the central tiller, during vegetative stage; and causes whiteheads at reproductive stage.";
            defaultHowToManage = "To prevent stem borer infestation in rice crops, follow these steps:\n" +
                    "1. Plant resistant varieties: Select and plant rice varieties that are resistant to stem borers to reduce the risk of infestation.\n" +
                    "2. Practice crop rotation: Rotate rice crops with non-rice crops during the dry season and avoid growing more than two crops of rice per year.\n" +
                    "3. Maintain a clean field: Remove weeds and dry the rice field after plowing to prevent stem borers from thriving.\n" +
                    "4. Weed your fields regularly: Keep the fields weed-free to deter pests and diseases, as weeds can provide a habitat for stem borers.\n" +
                    "5. Synchronize planting: Synchronize planting with neighboring farms to reduce pests, as crop plants at the same developmental stage can be more effectively managed.\n" +
                    "6. Use biological control agents: Introduce natural enemies of stem borers, such as wasps, grasshoppers, and spiders, to help control the population.\n" +
                    "7. Monitor and adjust: Regularly inspect your rice crops for signs of infestation and adjust your management practices as needed.";
            defaultYoutubeLink = "click the link below for more information\n" +
                    "https://www.youtube.com/watch?v=wpFQcWOuLsM";
        } else if ("Leaffolder".equals(insectName)) {
            defaultPilipinoName = "Mambibilot, Maniniklop";
            defaultDisease = "The damage may be more important when it occurs during the reproductive phase, as high feeding damage on the flag leaves can cause yield loss.";
            defaultWhatItDoes  = "Leaffolder caterpillars fold a rice leaf around themselves and attach the leaf margins together with silk strands. They feed inside the folded leaf creating longitudinal white and transparent streaks on the blade.";
            defaultHowToManage = "To prevent leaf folder infestation in rice crops, follow these steps:\n" +
                    "1. Plant resistant varieties: Select and plant rice varieties that are resistant to leaf folders to reduce the risk of infestation.\n" +
                    "2. Practice crop rotation: Rotate rice crops with non-rice crops during the dry season and avoid growing more than two crops of rice per year.\n" +
                    "3. Maintain a clean field: Remove weeds and dry the rice field after plowing to prevent leaf folders from thriving.\n" +
                    "4. Weed your fields regularly: Keep the fields weed-free to deter pests and diseases, as weeds can provide a habitat for leaf folders.\n" +
                    "5. Synchronize planting: Synchronize planting with neighboring farms to reduce pests, as crop plants at the same developmental stage can be more effectively managed.\n" +
                    "6. Use biological control agents: Introduce natural enemies of leaf folders, such as wasps, grasshoppers, and spiders, to help control the population.\n" +
                    "7. Monitor and adjust: Regularly inspect your rice crops for signs of infestation and adjust your management practices as needed.";
            defaultYoutubeLink = "click the link below for more information\n" +
                    "https://www.youtube.com/watch?v=J6eVuPcLNGU";
        } else if ("Short-horned grasshopper".equals(insectName)) {
            defaultPilipinoName = "Balang, Lukton";
            defaultDisease = "Short-horned grasshoppers can cause damage to rice crops by feeding on the leaf margins, cutting out areas on leaves, and cutting off panicles. The damage caused by short-horned grasshoppers can lead to yield loss and reduced fruit set. ";
            defaultWhatItDoes  = "Feeding damage caused by short-horned grasshoppers and oriental migratory locusts result to cut out areas on leaves and cut-off panicles. They both feed on leaf margins.";
            defaultHowToManage = "To prevent short-horned grasshopper infestation in rice crops, follow these steps: \n" +
                    "1. Flood the stubbles, shave bunds, sweep along the bunds, and pick adults directly from the foliage at night when they are sluggish.\n" +
                    "2. Encourage biological control agents: scelionid wasps, parasitic flies, nematodes, and fungal pathogens, birds, frogs, and web-spinning spiders, and a certain species of an entomophthoralean fungus; and platystomatid fly and mite (eggs of oriental migratory locust), ants, birds, bats, field rats, mice, wild pigs, dogs, millipedes, fish, amphibia, reptiles, and monkeys.\n" +
                    "3. Use poison baits from salt water and rice bran.\n" +
                    "4. Use foliar sprays to control grasshoppers in rice fields. Granules are not effective.\n" +
                    "5. Monitor and adjust regularly inspect your rice crops for signs of infestation and adjust your management practices as needed.";
            defaultYoutubeLink = "click the link below for more information\n" +
                    "https://www.youtube.com/watch?v=KixbQj66U3Y";
        } else if ("Wolf spider".equals(insectName)) {
            defaultPilipinoName = "Gagambang lobo";
            defaultDisease = "N/A";
            defaultWhatItDoes = "The wolf spider can eat 7-45 hoppers per day. They are the major predators of planthoppers and leafhoppers. Spiderlings also attack planthopper and leafhopper nymphs. They are aggressive hunter that patrol plant and water surface for prey that include caserowms, leaffolder, whorld maggots, and newly hatched larvae and moths of stem borers.";
            defaultHowToManage = "N/A";

        } else if ("Orb spider".equals(insectName)) {
            defaultPilipinoName = "Gagambang pari";
            defaultDisease = "N/A";
            defaultWhatItDoes = "They feed on planthoppers, leafhoppers, caseworms, whorl maggots, stem borer adults, and grasshoppers";
            defaultHowToManage = "N/A";

        } else if ("Dragonfly".equals(insectName)) {
            defaultPilipinoName = "Tutubi";
            defaultDisease = "N/A";
            defaultWhatItDoes = "They feed on adult nymphs of planthoppers, leafhoppers and moths.";
            defaultHowToManage = "N/A";

        } else if ("Ground beetle".equals(insectName)) {
            defaultPilipinoName = "Tigreng Salagubang";
            defaultDisease = "N/A";
            defaultWhatItDoes = "They eat planthoppers and leafhoppers. Others prey on hairy catterpillars, semiloopers, and newly hatched larvae of stem borers. They feed inside the folded leaves made by leaffolder larvae. They can eat 3-5 larvae per day.";
            defaultHowToManage = "N/A";

        }


        // Add more cases for other insect types as needed

        // Set default values in EditText views
        pilipinoNameEditText.setText(preferences.getString(insectName + "_PilipinoName", defaultPilipinoName));
        diseaseEditText.setText(preferences.getString(insectName + "_Disease", defaultDisease));
        whatItDoesEditText.setText(preferences.getString(insectName + "_WhatItDoes", defaultWhatItDoes));
        howToManageEditText.setText(preferences.getString(insectName + "_HowToManage", defaultHowToManage));
        youtubeLinkEditText.setText(preferences.getString(insectName + "_YoutubeLink", defaultYoutubeLink));
    }

    private void saveChanges() {
        // Save changes to SharedPreferences
        SharedPreferences preferences = getSharedPreferences("InsectInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(insectName + "_PilipinoName", pilipinoNameEditText.getText().toString());
        editor.putString(insectName + "_Disease", diseaseEditText.getText().toString());
        editor.putString(insectName + "_WhatItDoes", whatItDoesEditText.getText().toString());
        editor.putString(insectName + "_HowToManage", howToManageEditText.getText().toString());
        editor.putString(insectName + "_YoutubeLink", youtubeLinkEditText.getText().toString());

        editor.apply();

        // Notify the user that changes have been saved if needed

        // Set the result and finish the activity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("insectName", insectName);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    private void openYouTubeLink() {
        String youtubeLink = youtubeLinkEditText.getText().toString().trim();

        // Check if the link is a valid YouTube link
        if (isValidYouTubeLink(youtubeLink)) {
            // Open the YouTube link
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
            startActivity(intent);
        } else {
            // Display a Toast indicating an invalid YouTube link
            Toast.makeText(this, "Invalid YouTube Link", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidYouTubeLink(String link) {
        // Add your validation logic for a YouTube link
        // For simplicity, you can check if the link contains "youtube.com" or "youtu.be"
        return link.contains("youtube.com") || link.contains("youtu.be");
    }
}