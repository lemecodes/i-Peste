//package com.example.i_peste;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class ReportActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private List<ReportItem> reportList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_report);
//
//        recyclerView = findViewById(R.id.recyclerViewReports);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        // Initialize your reportList (retrieve this from SharedPreferences)
//        reportList = initializeReportList();
//
//        // Set up the RecyclerView adapter
//        ReportAdapter adapter = new ReportAdapter(reportList);
//        recyclerView.setAdapter(adapter);
//    }
//
//    // Retrieve user information from SharedPreferences and initialize reportList
//    private List<ReportItem> initializeReportList() {
//        List<ReportItem> reports = new ArrayList<>();
//
//        // Retrieve data from SharedPreferences
//        SharedPreferences reportDataPreferences = getSharedPreferences("ReportData", MODE_PRIVATE);
//        String capturedPhotoBase64 = reportDataPreferences.getString("capturedPhoto", "");
//        String classifiedResult = reportDataPreferences.getString("classifiedResult", "");
//        String additionalInformation = reportDataPreferences.getString("additionalInformation", "");
//
//        // Replace with your actual user information retrieval logic
//        SharedPreferences userDataPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
//        String name = userDataPreferences.getString("name", "");
//        String address = userDataPreferences.getString("address", "");
//        String age = userDataPreferences.getString("age", "");
//        String contact = userDataPreferences.getString("contact", "");
//        String date = userDataPreferences.getString("date", "");
//
//        // Add the retrieved data to the list
//        reports.add(new ReportItem(capturedPhotoBase64, classifiedResult, additionalInformation, name, address, age, contact, date));
//
//        return reports;
//    }
//}