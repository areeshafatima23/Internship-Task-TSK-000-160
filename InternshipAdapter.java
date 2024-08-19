package com.example.interneeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class InternshipAdapter extends RecyclerView.Adapter<InternshipAdapter.InternshipViewHolder> {

    private List<Internship> internships;

    public InternshipAdapter(List<Internship> internships) {
        this.internships = internships;
    }

    @NonNull
    @Override
    public InternshipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.internship_item, parent, false);
        return new InternshipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InternshipViewHolder holder, int position) {
        Internship internship = internships.get(position);
        holder.titleTextView.setText(internship.getTitle());
        holder.descriptionTextView.setText(internship.getDescription());

        Glide.with(holder.itemView.getContext())
                .load(internship.getImageResourceId())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return internships.size();
    }

    public void updateData(List<Internship> newInternships) {
        internships = newInternships;
        notifyDataSetChanged();
    }

    static class InternshipViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        public InternshipViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.internshipImageView);
            titleTextView = itemView.findViewById(R.id.internshipTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.internshipDescriptionTextView);
        }
    }
}
