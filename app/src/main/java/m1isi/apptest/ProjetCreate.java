package m1isi.apptest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class ProjetCreate extends AppCompatActivity {
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet_create);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = ProgressDialog.show(ProjetCreate.this, "Connecting", "Trying to verify your informations", true, false);
                ProjetCreateDB projetCreate = new ProjetCreateDB(ProjetCreate.this);
                projetCreate.eAddProjet(fillProjectItem());
            }
        });
    }

    private ItemProjet fillProjectItem() {
        progressDialog = ProgressDialog.show(ProjetCreate.this, "Création du projet ...", "", true, false);
        ItemProjet myItem = new ItemProjet();
        myItem.pro_titre = ((TextView) findViewById(R.id.eTxtTitre)).getText().toString();
        myItem.pro_desc = ((TextView) findViewById(R.id.eTxtTitre)).getText().toString();

        return myItem;//TODO : fill with champs from activity
    }


    public void eAddProjetDone(){
        progressDialog.dismiss();
    }

}
