package com.sdm.jornalero.ui.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.sdm.jornalero.R;
import com.sdm.jornalero.database.JornalerosDatabase;
import com.sdm.jornalero.database.dao.JobDao;
import com.sdm.jornalero.model.Job;

public class EditJob extends AppCompatActivity {

    private static final int OK=0, CATEGORY_UNSELECTED=1, ERROR=2;

    private TextView jobName, jobDescription, hours, salary;
    private RelativeLayout fullTimeData;
    private Spinner jobType;
    private Button btnSave;
    private JobDao jobDao;
    private Long jobId;
    private boolean editing;
    private Job job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.jobName = this.findViewById(R.id.txt_job_name);
        this.jobDescription = this.findViewById(R.id.txt_job_description);
        this.jobType = this.findViewById(R.id.sp_job_type);
        this.fullTimeData = findViewById(R.id.rl_full_part);
        this.fullTimeData.setVisibility(View.GONE);
        this.hours = findViewById(R.id.txt_job_hours);
        this.salary = findViewById(R.id.txt_job_salary);
        this.jobType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==1 || position==2){ fullTimeData.setVisibility(View.VISIBLE); }
                else{ fullTimeData.setVisibility(View.GONE); }
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
        this.btnSave = findViewById(R.id.btn_save_job);
        this.btnSave.setOnClickListener(view -> { this.save(); });
        this.jobDao = JornalerosDatabase.getDatabase(this).jobsDao();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    Intent parameters = getIntent();
                    jobId = parameters.getLongExtra(Job.JOB_ID,-1L);
                    editing = jobId!=-1L;
                    job = jobDao.find(jobId);
                    return true;
                }catch(Exception e){ return false; }
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditJob.this,
                        R.array.jobTypes, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                jobType.setAdapter(adapter);
                Intent parameters = getIntent();
                jobId = parameters.getLongExtra(Job.JOB_ID,-1L);
                editing = jobId!=-1L;
                if(editing){
                    jobName.setText(job.name);
                    jobDescription.setText(job.description);
                    jobType.setSelection(job.jobType);
                }else{ job = new Job(); }
            }
        }.execute();
    }


    private void save() {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                try {
                    if(jobType.getSelectedItemPosition()==0) { return CATEGORY_UNSELECTED; }
                    job.name = jobName.getText().toString();
                    job.description = jobDescription.getText().toString();
                    if(editing){ jobDao.update(job); }
                    else{ jobDao.save(job); }
                    return OK;
                }catch(Exception e){ Log.getStackTraceString(e); return ERROR; }
            }

            @Override
            protected void onPostExecute(Integer result) {
                switch (result){
                    case OK: Toast.makeText(EditJob.this,R.string.job_saved,Toast.LENGTH_SHORT).show(); return;
                    case CATEGORY_UNSELECTED: Toast.makeText(EditJob.this,R.string.category_unselected,Toast.LENGTH_SHORT).show(); return;
                    case ERROR: Toast.makeText(EditJob.this,R.string.unexpected_error,Toast.LENGTH_SHORT).show(); return;
                }
            }
        }.execute();
    }
}
