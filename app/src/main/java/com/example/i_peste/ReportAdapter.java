//package com.example.i_peste;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.util.Base64;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {
//
//    private List<ReportItem> reportList;
//
//    public ReportAdapter(List<ReportItem> reportList) {
//        this.reportList = reportList;
//    }
//
//    @NonNull
//    @Override
//    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item, parent, false);
//        return new ReportViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
//        ReportItem report = reportList.get(position);
//        holder.bind(report);
//    }
//
//    @Override
//    public int getItemCount() {
//        return reportList.size();
//    }
//
//    public static class ReportViewHolder extends RecyclerView.ViewHolder {
//
//        private ImageView imageViewCapturedPhoto;
//        private TextView textViewClassificationResult;
//        private TextView textViewName;
//        private TextView textViewAge;
//        private TextView textViewAddress;
//        private TextView textViewContact;
//        private TextView textViewDate;
//
//        public ReportViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageViewCapturedPhoto = itemView.findViewById(R.id.imageViewCapturedPhotoReport);
//            textViewClassificationResult = itemView.findViewById(R.id.textViewClassificationResultReport);
//            textViewName = itemView.findViewById(R.id.textViewNameReport);
//            textViewAge = itemView.findViewById(R.id.textViewAgeReport);
//            textViewAddress = itemView.findViewById(R.id.textViewAddressReport);
//            textViewContact = itemView.findViewById(R.id.textViewContactReport);
//            textViewDate = itemView.findViewById(R.id.textViewDateReport);
//        }
//
//        public void bind(ReportItem report) {
//            // Bind data to views
//            textViewName.setText("Captured by: " + report.getName());
//            textViewAge.setText("Age: " + report.getAge());
//            textViewAddress.setText("Address: " + report.getAddress());
//            textViewContact.setText("Contact: " + report.getContact());
//            textViewDate.setText("Date: " + report.getDate());
//            textViewClassificationResult.setText("Classified as: " + report.getClassifiedResult());
//
//            // Load the image using Base64 decoding
//            try {
//                byte[] decodedBytes = Base64.decode(report.getCapturedPhotoBase64(), Base64.DEFAULT);
//                Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
//                imageViewCapturedPhoto.setImageBitmap(decodedBitmap);
//            } catch (Exception e) {
//                Log.e("ReportAdapter", "Error decoding Base64: " + e.getMessage());
//            }
//        }
//    }
//}