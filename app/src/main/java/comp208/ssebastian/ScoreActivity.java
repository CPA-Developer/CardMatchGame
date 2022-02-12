package comp208.ssebastian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        TextView tv = findViewById(R.id.guessMade);
        Bundle extras = getIntent().getExtras();
       tv.setText(extras.getString("guess"));
    }

    public  void switchToMainActivity(View view)
    {
        Intent intent = new Intent(this,MainActivity2.class);
//        intent.putExtra("data","this is some data");
        startActivity(intent);
    }

    public void onClickExit(View view)
    {

        finish();
        System.exit(0);
    }
}