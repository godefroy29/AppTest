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
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

public class ProjetAccueil extends AppCompatActivity {
    private int id_projet;
    private int id_identifiant;
    private Button btnAddColl;
    private Button btnAddTache;
    private Intent createTache;
    private TextView txtTitre;
    private TextView txtDesc;
    private TextView txtDateD;
    private TextView txtDateFP;
    private TextView txtBudgetD;
    private TextView txtBudgetFP;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet_accueil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Accueil du projet");
        id_projet = getIntent().getExtras().getInt("id_projet");
        id_identifiant = getIntent().getExtras().getInt("id_identifiant");
        createTache = new Intent(this, TacheCreate.class);
        createTache.putExtra("id_identifiant", id_identifiant);
        createTache.putExtra("id_projet", id_projet);
        btnAddColl = (Button) findViewById(R.id.btnAddCol);
        btnAddTache = (Button) findViewById(R.id.btnAddTache);
        txtTitre = (TextView) findViewById(R.id.txtTitre);
        txtDesc = (TextView) findViewById(R.id.txtDesc);
        txtDateD = (TextView) findViewById(R.id.txtDateD);
        txtDateFP = (TextView) findViewById(R.id.txtDateFP);
        txtBudgetD = (TextView) findViewById(R.id.txtBudgetD);
        txtBudgetFP = (TextView) findViewById(R.id.txtBudgetFP);
        btnAddColl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnAddTache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(createTache);
            }
        });
        ProjetAccueilDB projetAccueilDB = new ProjetAccueilDB(this, id_projet);
        progressDialog = ProgressDialog.show(ProjetAccueil.this, "Récupération des données ...", "Récupération du projet", true, false);
        projetAccueilDB.searchProjet();
    }

    public void populateProjet(ItemProjet myItem){
        if (myItem != null){
            txtTitre.setText(myItem.pro_titre);
            txtDesc.setText(myItem.pro_desc);
            txtBudgetD.setText(myItem.pro_budgetD + "");
            txtBudgetFP.setText(myItem.pro_budgetFP + "");
            txtDateD.setText(new SimpleDateFormat("dd/MM/yyyy").format(myItem.pro_dateD));
            txtDateFP.setText(new SimpleDateFormat("dd/MM/yyyy").format(myItem.pro_dateFP));
        }

        progressDialog.dismiss();
    }

}
