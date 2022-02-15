package comp208.ssebastian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    final int ROWS = 4;
    final int COLS = 3;
    TableLayout matchBoard;
    int gameOver = 0;
    int cardClick =0;
    Game card = new Game() ;
    int firstId ;
    int indexOfCard =0;
    boolean turnOver = false;
    ImageView currentImage ;



    String[] imageValues = new String[]{String.valueOf(R.drawable.frontface1),
            String.valueOf(R.drawable.frontface1),String.valueOf(R.drawable.frontface2),
            String.valueOf(R.drawable.frontface2),String.valueOf(R.drawable.frontface3),
            String.valueOf(R.drawable.frontface3),String.valueOf(R.drawable.frontface4),
            String.valueOf(R.drawable.frontface4),String.valueOf(R.drawable.frontface5),
            String.valueOf(R.drawable.frontface5),String.valueOf(R.drawable.frontface6),
            String.valueOf(R.drawable.frontface6)};

    List<String> imageList = new ArrayList<>(Arrays.asList(imageValues));

    Card[][] matchGrid = new Card[ROWS][COLS];

    String idName;
    String firstCardId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        matchBoard = findViewById(R.id.matchBoard);
        Collections.shuffle(imageList);




        for (int row=0; row<ROWS; row++) {
            TableRow tableRow = (TableRow) matchBoard.getChildAt(row);
            for (int col = 0; col < COLS; col++) {
                ImageView iv = (ImageView) tableRow.getChildAt(col);
                iv.setOnClickListener(ivListerner);
                //           iv.setImageResource(R.drawable.blank);

                Card square = new Card();

                square.imageId = R.drawable.backface;
                square.firstId = R.drawable.backface;
                square.row=row;
                square.col=col;
                matchGrid[row][col]=square;
                iv.setTag(square);


            }

        }

    }

    final View.OnClickListener ivListerner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            ImageView iv = (ImageView) view;
            Card square = (Card) iv.getTag();

            idName = getResources().getResourceEntryName(iv.getId());
            //      Toast.makeText(MainActivity.this, "Id: "+idName, Toast.LENGTH_LONG).show();


            switch (getResources().getResourceEntryName(iv.getId())) {
                case "one":
//                    Toast.makeText(MainActivity.this, "1", Toast.LENGTH_LONG).show();
                    indexOfCard = 1;
                    break;
                case "two":
                    indexOfCard = 2;
                    break;
                case "three":
                    indexOfCard = 3;
                    break;
                case "four":
                    indexOfCard = 4;
                    break;
                case "five":
                    indexOfCard = 5;
                    break;
                case "six":
                    indexOfCard = 6;
                    break;
                case "seven":
                    indexOfCard = 7;
                    break;
                case "eight":
                    indexOfCard = 8;
                    break;
                case "nine":
                    indexOfCard = 9;
                    break;
                case "ten":
                    indexOfCard = 10;
                    break;
                case "eleven":
                    indexOfCard = 11;
                    break;
                case "twelve":
                    indexOfCard = 0;
                    break;

            }


//            Toast.makeText(MainActivity.this, "Id: "+getResources().getResourceEntryName(iv.getId()), Toast.LENGTH_LONG).show();

            //square.firstId = R.drawable.backface;


            if (square.imageId == R.drawable.backface && cardClick<2) {

                iv.setImageResource(Integer.parseInt(imageList.get(indexOfCard)));
                iv.setContentDescription(imageList.get(indexOfCard));
                square.imageId = Integer.parseInt(imageList.get(indexOfCard));

                if (cardClick == 0) {
                    card.firstCardContent =  iv.getContentDescription();
                    firstId = iv.getId();
                    firstCardId = imageList.get(indexOfCard);
                    iv.setEnabled(true);

                }

                cardClick++;


            } else if (square.imageId != R.drawable.backface) {
                iv.setImageResource(R.drawable.backface);
                iv.setContentDescription("backFace");
                square.imageId = R.drawable.backface;
                turnOver = false;

                cardClick--;

            }
              //  Toast.makeText(MainActivity2.this, "Click "+cardClick , Toast.LENGTH_LONG).show();
            if (cardClick == 2) {
                turnOver = true;
                card.guess++;
                setClickable(iv,firstId);
                cardClick=0;


            }
        }
    };

    public void setClickable(View view, int firstId){

        ImageView iv = (ImageView) view;
        currentImage = iv;
        if (iv.getContentDescription().equals(card.firstCardContent)) {
           // Toast.makeText(MainActivity2.this, "entered this loop "+getString(firstId), Toast.LENGTH_SHORT).show();
            iv.setEnabled(false);
            ImageView firstCardImage = findViewById(firstId);
            firstCardImage.setEnabled(false);
            turnOver = false;
            cardClick = 0;
            gameOver++;
        }else if(iv.getContentDescription()!=(card.firstCardContent)){

            turnCardsDown();
        }

        if(gameOver ==6){
            switchToScore();
        }

    }

    Handler flipCardHandler = new Handler();

    public  void switchToScore()
    {
        Intent intent = new Intent(this,ScoreActivity.class);
        String guessMade = "guess made: "+ card.guess;
      intent.putExtra("guess",guessMade);
        startActivityForResult(intent,1);
    }

    Runnable flip = () ->{
        currentImage.setImageResource(R.drawable.backface);
        Card square = (Card) currentImage.getTag();

        currentImage.setContentDescription("backFace");
        square.imageId = R.drawable.backface;

        ImageView firstCardImage = findViewById(firstId);
        firstCardImage.setImageResource(R.drawable.backface);
        firstCardImage.setContentDescription("backFace");
        card.firstCardContent=currentImage.getContentDescription();
        Card firstCardTag = (Card) firstCardImage.getTag();

        firstCardTag.imageId = R.drawable.backface;



    };

    public void turnCardsDown(){

        flipCardHandler.postDelayed(flip,500);


    }
}