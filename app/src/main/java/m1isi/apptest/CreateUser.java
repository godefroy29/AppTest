package m1isi.apptest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateUser extends AppCompatActivity {
    ProgressDialog progressDialog;
    EditText editTextLogin;
    EditText editTextPassword;
    Button btnValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        editTextLogin = (EditText) findViewById(R.id.CreateUserLoginEditText);
        editTextPassword = (EditText) findViewById(R.id.CreateUserPasswordEditText);
        btnValidate = (Button) findViewById(R.id.CreateUserButtonValidate);

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = ProgressDialog.show(CreateUser.this, getResources().getString(R.string.general_connecting), getResources().getString(R.string.createUser_adding_user), false);
                CreateUserDB testId = new CreateUserDB(CreateUser.this);
                testId.testId(editTextLogin.getText().toString(), editTextPassword.getText().toString());
            }
        });
    }

    public void testIdError(){
        progressDialog.dismiss();

        Toast.makeText(CreateUser.this, R.string.general_connect_error, Toast.LENGTH_LONG).show();
    }

    public void testIdValide(boolean idValide){
        progressDialog.dismiss();
        if (idValide){
            Toast.makeText(CreateUser.this, R.string.createUser_user_added, Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(CreateUser.this, R.string.general_connect_not_found, Toast.LENGTH_LONG).show();
        }
    }

    public void idAlreadyExist(){
        progressDialog.dismiss();

        Toast.makeText(CreateUser.this, R.string.createUser_already_exist, Toast.LENGTH_LONG).show();
    }
}
