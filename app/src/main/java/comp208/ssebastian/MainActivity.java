package comp208.ssebastian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void onswitch(View view)
    {
        Intent intent = new Intent(this,MainActivity2.class);
//        intent.putExtra("data","this is some data");
        startActivity(intent);
    }
}