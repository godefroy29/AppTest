package m1isi.apptest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class BestSplashScreen extends Activity {

    Intent accueil;

    Button btnAdmin;
    Button btnConnexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_best_splash_screen);

        accueil = new Intent(this, Accueil.class);
        btnAdmin = (Button) findViewById(R.id.btnAdmin);
        btnConnexion = (Button) findViewById(R.id.btnConnexion);

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(accueil);
            }
        });
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (testConnexion()) {
                    if (testIdentifiants()){
                        startActivity(accueil);
                    }else{
                        Toast.makeText(BestSplashScreen.this, "Vérifiez vos identifiants.", Toast.LENGTH_SHORT);
                    }
                }else{
                    Toast.makeText(BestSplashScreen.this, "Pas de connection à la base de données.", Toast.LENGTH_SHORT);
                }

            }
        });
    }

    //TODO: verifier id
    private boolean testIdentifiants() {
        return true;
    }

    //TODO: verifier connexion
    private boolean testConnexion() {
        return true;
    }

}
