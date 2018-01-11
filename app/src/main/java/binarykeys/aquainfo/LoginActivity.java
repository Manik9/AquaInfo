package binarykeys.aquainfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextClock;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by ACER on 01-Jan-18.
 */

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputEditText email;
    private TextInputEditText pass;
    private FloatingActionButton loginButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        email=findViewById(R.id.email_id);
        pass=findViewById(R.id.pass);
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(LoginActivity.this,Activity2.class));
            finish();
        }

        loginButton=findViewById(R.id.floatingActionButtonEmailScreen);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login(email.getText().toString(),pass.getText().toString());
            }
        });
    }


    private void SignInWithEmailAndPassword(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {




                            startActivity(new Intent(LoginActivity.this,Activity2.class));
                            finish();
                        }else {
                            // If sign in fails, display a message to the user.
                            Log.w("LoginActivity", "signInWithEmail:failure", task.getException());
                            Toast t = Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT);
                            t.setGravity(Gravity.CENTER, 0, 0);
                            t.show();

                        }

                    }
                });
    }


    private void login(String temail,String tpass){
        if(TextUtils.isEmpty(temail))
            email.setError("Ooops!");
        else if(TextUtils.isEmpty(tpass))
            pass.setError("Oh No!");
        else{
            loginButton.animate().translationX(300).setDuration(500).start();
            SignInWithEmailAndPassword(temail,tpass);
        }
    }
}
