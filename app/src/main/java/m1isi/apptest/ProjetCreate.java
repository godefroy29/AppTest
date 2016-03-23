package m1isi.apptest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProjetCreate extends AppCompatActivity {

     //TODO : finish l'activité précédente
     //TODO : lancer la meme activité de nouveau quand on press back
    ProgressDialog progressDialog;
    Button btnValider;
    DatePicker dtpD;
    DatePicker dtpFP;

    @Override
    public void onBackPressed() {
       startActivity(new Intent(new Intent(this, Accueil.class)));
       finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet_create);

        dtpD = (DatePicker) findViewById(R.id.dtpD);
        dtpFP = (DatePicker) findViewById(R.id.dtpFP);
        btnValider = (Button) findViewById(R.id.btnValider);
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = ProgressDialog.show(ProjetCreate.this, getString(R.string.general_processing), getString(R.string.projectCreate_creating_project), true, false);
                ProjetCreateDB projetCreate = new ProjetCreateDB(ProjetCreate.this);
                projetCreate.eAddProjet(fillProjectItem());
            }
        });
    }

    private ItemProjet fillProjectItem() {
        ItemProjet myItem = new ItemProjet();
        myItem.pro_titre = ((TextView) findViewById(R.id.eTxtTitre)).getText().toString();
        myItem.pro_desc = ((TextView) findViewById(R.id.eTxtDesc)).getText().toString();
        myItem.pro_budgetD = Float.valueOf(((TextView) findViewById(R.id.eTxtBudgetD)).getText().toString());
        myItem.pro_budgetFP = Float.valueOf(((TextView) findViewById(R.id.eTxtBudgetD)).getText().toString());
        myItem.pro_dateD = new java.sql.Date(new Date(dtpD.getYear(), dtpD.getMonth(), dtpD.getDayOfMonth()).getTime());
        myItem.pro_dateFP = new java.sql.Date(new Date(dtpFP.getYear(), dtpFP.getMonth(), dtpFP.getDayOfMonth()).getTime());
        return myItem;//TODO : fill with champs from activity
    }


    public void eAddProjetDone(){
        progressDialog.dismiss();
    }

}
