package com.example.tictac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] buttons = new Button[3][3];

    private boolean player1turn = true;

    private int roundcountdown ;

    private int playerpoint1;
    private int playerpoint2;

    private TextView player1;
    private TextView player2;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = findViewById(R.id.text_view_1);
        player2 = findViewById(R.id.text_view_2);

        buttons[0][0] =findViewById(R.id.Button_00);
        buttons[0][1] =findViewById(R.id.Button_01);
        buttons[0][2] =findViewById(R.id.Button_02);
        buttons[1][0] =findViewById(R.id.Button_10);
        buttons[1][1] =findViewById(R.id.Button_11);
        buttons[1][2] =findViewById(R.id.Button_12);
        buttons[2][0] =findViewById(R.id.Button_20);
        buttons[2][1] =findViewById(R.id.Button_21);
        buttons[2][2] =findViewById(R.id.Button_22);

        for(int i = 0;i < 3; i++)
        {
            for(int j = 0;j < 3; j++)
            {
               // String buttonid = "Button_"+ i + j;
                //int resid = getResources().getIdentifier(buttonid,"id",getPackageName());//dynamically call
               // buttons[i][j] = findViewById(resid); //so that we don't have to initialize each button one by one
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonrest = findViewById(R.id.button_reset);
        buttonrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetgame();

            }
        });

    }

    @Override
    public void onClick(View v) {

        if(!((Button)v).getText().toString().equals(""))
        {
            return;
        }

        if(player1turn)
        {
            ((Button)v).setText("x");
        }else
        {
            ((Button)v).setText("o");
        }
        roundcountdown++;
        if(checkforwin())
        {
            if(player1turn)
            {
                player1wins();
            }else
            {
                player2wins();
            }
        }else if(roundcountdown == 9)
        {
            draw();
        }else
        {
            player1turn = !player1turn;
        }

    }
    private boolean checkforwin()
    {
        String[][] field = new String[3][3];

        for(int i= 0;i < 3;i++)
        {
            for(int j =0;j < 3;j++)
            {
                field[i][j]= buttons[i][j].getText().toString();
            }
        }
        //for row
        for(int i=0;i<3;i++)
        {
            if((field[i][0].equals(field[i][1]))
            && (field[i][0].equals(field[i][2]))
            &&(!(field[i][0].equals(""))))
            {
                return true;
            }
        }
        //for coloumn
        for(int i=0;i<3;i++)
        {
            if((field[0][i].equals(field[1][i]))
            && (field[0][i].equals(field[2][i]))
            &&(!(field[0][i]).equals("")))
            {
                return true;
            }
        }

        if((field[0][0].equals(field[1][1]))
                && (field[0][0].equals(field[2][2]))
                &&(!(field[0][0]).equals("")))
        {
            return true;
        }
        if((field[0][2].equals(field[1][1]))
                && (field[0][2].equals(field[2][0]))
                &&(!(field[0][2]).equals("")))
        {
            return true;
        }

        return false;



    }
    private void player1wins()
    {
        playerpoint1++;
        Toast.makeText(MainActivity.this,"Player1 wins!",Toast.LENGTH_SHORT).show();
        updatepointText();
        resetBoard();
    }
    private void player2wins()
    {
        playerpoint2++;
        Toast.makeText(MainActivity.this,"Player2 wins!",Toast.LENGTH_SHORT).show();
        updatepointText();
        resetBoard();
    }
    private void updatepointText()
    {
        player1.setText("Player 1 : "+playerpoint1);
        player2.setText("Player 2 : "+playerpoint2);

    }
    private void draw()
    {
        Toast.makeText(MainActivity.this,"Draw",Toast.LENGTH_SHORT).show();;
        resetBoard();
    }
    private void resetBoard()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                buttons[i][j].setText("");
            }
        }
        roundcountdown =0;
        player1turn =true;
    }
    private void resetgame()
    {
        playerpoint1=0;
        playerpoint2=0;
        updatepointText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundcountdown",roundcountdown);
        outState.putInt("playerpoint1",playerpoint1);
        outState.putInt("playerpoint2",playerpoint2);
        outState.putBoolean("player1turn",player1turn);


    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundcountdown = savedInstanceState.getInt("roundcountdown");
        playerpoint1 = savedInstanceState.getInt("playerpoint1");
        playerpoint2 = savedInstanceState.getInt("playerpoint2");
        player1turn = savedInstanceState.getBoolean("player1turn");
    }
}
