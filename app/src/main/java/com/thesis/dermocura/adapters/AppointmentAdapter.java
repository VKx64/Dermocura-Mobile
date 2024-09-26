package com.thesis.dermocura.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thesis.dermocura.R;
import com.thesis.dermocura.models.Appointment;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
    private List<Appointment> appointmentList;
    private Context context;

    public AppointmentAdapter(Context context, List<Appointment> appointmentList) {
        this.context = context;
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_appointment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);

        // Set doctor name, clinic name, and appointment status
        holder.tvDoctorName.setText(appointment.getDoctorName());
        holder.tvClinicName.setText(appointment.getClinicName());

        // Format date and time
        holder.tvAppointmentDate.setText(appointment.getAvailDate());
        holder.tvAppointmentTime.setText(appointment.getStartTime() + " - " + appointment.getEndTime());

        // Set status
        holder.tvStatus.setText(appointment.getStatus());

        // Load the clinic logo into the ImageView (you can use Glide or Picasso)
        if (appointment.getClinicLogo() != null) {
            Glide.with(context)
                    .load(appointment.getClinicLogo())  // Adjust this to your base URL + image path
                    .into(holder.ivClinicLogo);
        } else {
            // Set a placeholder if no image is available
            holder.ivClinicLogo.setImageResource(R.drawable.default_placeholder);
        }
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDoctorName, tvClinicName, tvAppointmentDate, tvAppointmentTime, tvStatus;
        ImageView ivClinicLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDoctorName = itemView.findViewById(R.id.tvDoctorName);
            tvClinicName = itemView.findViewById(R.id.tvClinicName);
            tvAppointmentDate = itemView.findViewById(R.id.tvAppointmentDate);
            tvAppointmentTime = itemView.findViewById(R.id.tvAppointmentTime);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            ivClinicLogo = itemView.findViewById(R.id.ivClinicLogo);
        }
    }
}
