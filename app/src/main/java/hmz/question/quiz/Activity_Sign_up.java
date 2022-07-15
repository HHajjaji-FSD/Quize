package hmz.question.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import hmz.question.quiz.Database.Class_Dao.User_Dao;
import hmz.question.quiz.Database.Class_Data.User;

public class Activity_Sign_up extends AppCompatActivity {

    private EditText txt_first_name,txt_last_name,txt_email,txt_password;
    private CheckBox check_show_password;
    private Button btn_sing_up,btn_cansel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // inflat widget
        txt_first_name = (EditText) findViewById(R.id.edit_text_first_name);
        txt_last_name = (EditText) findViewById(R.id.edit_text_last_name);
        txt_email = (EditText) findViewById(R.id.edit_text_email_user);
        txt_password = (EditText) findViewById(R.id.edit_text_password_user);
        check_show_password = (CheckBox) findViewById(R.id.check_show_password_user);
        btn_cansel = (Button) findViewById(R.id.btn_cancel_user);
        btn_sing_up = (Button) findViewById(R.id.btn_sing_up_user);

        //event
        txt_first_name.requestFocus();
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
        btn_cansel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //sing up
        btn_sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User_Dao user_dao = new User_Dao(Activity_Sign_up.this);
                String first = txt_first_name.getText().toString();
                String last = txt_last_name.getText().toString();
                User user = new User(first+" "+last,"USR",txt_email.getText().toString(),txt_password.getText().toString());
                user_dao.add_User(user);
                Toast.makeText(Activity_Sign_up.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }//fin oncreat
}