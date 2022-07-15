package hmz.question.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import hmz.question.quiz.Database.Class_Dao.User_Dao;
import hmz.question.quiz.Database.Class_Data.User;

public class MainActivity extends AppCompatActivity {

    private Button btn_log_in,btn_exit;
    private EditText txt_email,txt_password;
    private CheckBox check_show_password;
    private AlertDialog.Builder builder;
    private User_Dao user_dao;
    private User user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inflate event
        btn_exit = (Button) findViewById(R.id.btn_Exit_app);
        btn_log_in = (Button) findViewById(R.id.btn_log_in);
        txt_email = (EditText) findViewById(R.id.edit_text_email);
        txt_password = (EditText) findViewById(R.id.edit_text_password);
        check_show_password = (CheckBox) findViewById(R.id.check_show_password);

        // Evente de button
        txt_email.requestFocus();
        check_show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (check_show_password.isChecked())
                {
                    txt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    txt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        //log in
        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user_dao = new User_Dao(MainActivity.this);
                //user = new User("Hamza Hajjaji","ADM","sys","sys");
                //user_dao.add_User(user);
                //user = null;
                if (!txt_email.getText().toString().equals("") && !txt_password.getText().toString().equals(""))
                {
                    user = user_dao.login_User(txt_email.getText().toString(),txt_password.getText().toString());
                    if (user != null)
                    {
                        String tp = user.getType();
                        if (user.getType().equals("ADM"))
                        {

                            Intent intent = new Intent(getApplicationContext(),Activity_Admin.class);
                            intent.putExtra(User_Dao.Column_Username,user.getUsername());
                            startActivity(intent);

                            txt_email.setText("");
                            txt_password.setText("");
                        }
                        else
                        {
                            Intent intent = new Intent(getApplicationContext(),Activity_User.class);
                            intent.putExtra(User_Dao.Column_Username,user.getUsername());
                            intent.putExtra(User_Dao.Column_Id,user.getId());
                            startActivity(intent);
                            txt_email.setText("");
                            txt_password.setText("");
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Password or Email incorecte", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please Enter all information.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Exit Application").setMessage("Are you sur to Exit Application")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
            }
        });

    }//fin on create

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(),Activity_Sign_up.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}