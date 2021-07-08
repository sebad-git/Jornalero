package com.sdm.jornalero.ui.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sdm.jornalero.R;
import com.sdm.jornalero.database.JornalerosDatabase;
import com.sdm.jornalero.database.dao.JobDao;
import com.sdm.jornalero.model.Job;
import com.sdm.jornalero.ui.activities.EditJob;
import com.sdm.jornalero.ui.adapters.JobListAdapter;

import java.util.ArrayList;
import java.util.List;

public class JobsFragment extends Fragment {

    private RecyclerView rvJobList;
    private SwipeRefreshLayout srJobUpdate;
    private JobDao jobsDao;
    private List<Job> jobs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_jobs, container, false);
        this.rvJobList = root.findViewById(R.id.lst_jobs);
        this.srJobUpdate = root.findViewById(R.id.refresh_jb);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        this.rvJobList.setLayoutManager(layoutManager);
        this.rvJobList.addItemDecoration(new DividerItemDecoration(this.rvJobList.getContext(), DividerItemDecoration.VERTICAL));
        this.jobsDao = JornalerosDatabase.getDatabase(getActivity()).jobsDao();
        new LoadJobsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        this.srJobUpdate.setOnRefreshListener( () -> new LoadJobsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR));

        FloatingActionButton fab = root.findViewById(R.id.fb_add_job);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EditJob.class);
            getActivity().startActivity(intent);
        });
        return root;
    }

    private class LoadJobsTask extends AsyncTask<Void, Boolean, Boolean> {
        @Override
        protected void onPreExecute() { srJobUpdate.setRefreshing(true); }

        @Override
        protected Boolean doInBackground(Void... voids) {
            //try { jobs = jobsDao.list(); return true; }
            try {
                jobs = new ArrayList<>();
                Job job1 = new Job();
                job1.name="Developer";
                job1.description="Android developer";
                jobs.add(job1);
                Job job2 = new Job();
                job2.name="Carpinter";
                job2.description="Full time carpenter";
                jobs.add(job2);
                return true;
            }
            catch (Exception e){ Log.getStackTraceString(e); return false; }
        }

        @Override
        protected void onPostExecute(Boolean executedOK) {
            srJobUpdate.setRefreshing(false);
            if(!executedOK){ Toast.makeText(getActivity(), R.string.load_jobs_error,Toast.LENGTH_SHORT).show(); return; }
            updateView();
        }
    }

    private void updateView(){
        try {
            JobListAdapter adapter = new JobListAdapter(jobs, view -> {
                try {
                    int itemPosition = rvJobList.getChildLayoutPosition(view);
                    JobListAdapter adapter1 = (JobListAdapter)rvJobList.getAdapter();
                    Job job = adapter1.jobs.get(itemPosition);
                    //  Intent intent = new Intent(getActivity(), IncidenciaRadioActivity.class);
                    //  intent.putExtra(Incidencia.INCIDENCIA_ID,asignada.Id);
                    //  getActivity().startActivity(intent);
                }catch (Exception e){ Log.getStackTraceString(e); }
            });
            rvJobList.setAdapter(adapter);
        }catch (Exception e){ Log.getStackTraceString(e); }
    }
}
