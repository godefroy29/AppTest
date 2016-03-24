package m1isi.apptest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class TacheCreate extends AppCompatActivity {
    ProgressDialog progressDialog;

    Button btnValidate;
    EditText editTextTitle;
    EditText editTextDescription;
    EditText editTextTime;
    DatePicker datePickerStart;
    DatePicker datePickerEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tache_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.projectCreate_create_of_ATask);

        btnValidate = (Button) findViewById(R.id.TaskCreateValidateButton);
        editTextTitle = (EditText) findViewById(R.id.TaskCreateTitleEditText);
        editTextDescription = (EditText) findViewById(R.id.TaskCreateDescriptionEditText);
        editTextTime = (EditText) findViewById(R.id.TaskCreateTimeEditText);
        datePickerStart = (DatePicker)findViewById(R.id.TaskCreateDateBeginDatePicker);
        datePickerEnd = (DatePicker) findViewById(R.id.TaskCreateDateEndDatePicker);

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = ProgressDialog.show(TacheCreate.this, getResources().getString(R.string.general_connecting), getResources().getString(R.string.TaskCreate_adding_task), false);
                TacheCreateDB addTask = new TacheCreateDB(TacheCreate.this);
                int id_identifiant = getIntent().getExtras().getInt("id_identifiant");
                int id_projet = getIntent().getExtras().getInt("id_projet");
                addTask.eAddTache(id_identifiant, id_projet, editTextTitle.getText().toString(), editTextDescription.getText().toString(),
                        editTextTime.getText().toString(), new java.sql.Date(0), new java.sql.Date(0), 0);
            }
        });
    }

    public void eAddTacheDone(){
        progressDialog.dismiss();
        Toast.makeText(TacheCreate.this, R.string.TaskCreate_task_added, Toast.LENGTH_LONG).show();
        finish();
    }

}
