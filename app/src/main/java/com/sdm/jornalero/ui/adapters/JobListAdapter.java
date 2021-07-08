package com.sdm.jornalero.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.sdm.jornalero.R;
import com.sdm.jornalero.model.Job;
import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.ViewHolder> {

    public List<Job> jobs;
    private View.OnClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView jobName, jobDescription, fechaEstado, descCorta, ruta, ubicacion;
        //ImageView imagenErrorEnvio;

        public ViewHolder(View view) {
            super(view);
            jobName = view.findViewById(R.id.lbl_job_name);
            jobDescription = view.findViewById(R.id.lbl_job_description);
            /*
            estado = view.findViewById(R.id.txtStatus);
            fechaEstado = view.findViewById(R.id.txtStatusDate);
            descCorta = view.findViewById(R.id.txtDescriptionOT);
            //imagenErrorEnvio = view.findViewById(R.id.imagenErrorEnvio);
            ruta = view.findViewById(R.id.txtPathLocationUTD);
            ubicacion = view.findViewById(R.id.txtLocationDescription);
            */
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter_jobs,parent, false);
        layout.setOnClickListener(listener);
        return new ViewHolder(layout);
    }

    public JobListAdapter(List<Job> ordenesTrabajo, View.OnClickListener listener) {
        this.jobs = ordenesTrabajo; this.listener=listener;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try{
            final Job job = jobs.get(position);
            holder.jobName.setText(job.name);
            holder.jobDescription.setText(job.description);
           /*
            holder.estado.setText(orden.getEstado());
            holder.fechaEstado.setText(DateUtil.formatDate(orden.getFechaEstado(),DateUtil.TIMEZONE,DateUtil.SLASH_DD_MM_AA_HH_MM));
            holder.ruta.setText(orden.getRutaUbicacion());
            holder.descCorta.setText(orden.getDescBreve());
            */
        }catch (Exception e){ Log.getStackTraceString(e); }
    }

    @Override
    public int getItemCount() { return jobs ==null? 0 : jobs.size(); }
}
