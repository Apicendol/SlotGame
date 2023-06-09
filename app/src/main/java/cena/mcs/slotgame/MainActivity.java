package cena.mcs.slotgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Thread t1, t2, t3;
    Handler h1, h2, h3;
    int id = 1;
//    int id;
    private ImageView imgSlot1;
    private ImageView imgSlot2;
    private ImageView imgSlot3;
    private Button btnStart;

//    private int[] images = new int[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.imgSlot1 = this.findViewById(R.id.imgSlot1);
        this.imgSlot2 = this.findViewById(R.id.imgSlot2);
        this.imgSlot3 = this.findViewById(R.id.imgSlot3);
        this.btnStart = this.findViewById(R.id.btnStart);
        this.btnStart.setOnClickListener(this);

        this.h1 = new Handler(Looper.getMainLooper());
        this.h2 = new Handler(Looper.getMainLooper());
        this.h3 = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onClick(View v) {
//        Random random = new Random();
        btnStart.setText("Stop");
        if (this.t1 != null && this.t1.isAlive()) {
            this.t1.interrupt();
            return;
        }
        if (this.t2 != null && this.t2.isAlive()) {
            this.t2.interrupt();
            return;
        }
        if (this.t3 != null && this.t3.isAlive()) {
            this.t3.interrupt();
            this.checkWinningCombination();
            btnStart.setText("Start Again");
            return;
        }

        this.t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (id == 9) id = 1;
                    else id++;
                    try {
                        h1.post(new Runnable() {
                            @Override
                            public void run() {
                                imgSlot1.setImageResource(Helper.getIcon(id));
                            }
                        });
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });

        this.t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (id == 9) id = 1;
                    else id++;
                    try {
                        h2.post(new Runnable() {
                            @Override
                            public void run() {
                                imgSlot2.setImageResource(Helper.getIcon(id));
                            }
                        });
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });

        this.t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (id == 9) id = 1;
                    else id++;
                    try {
                        h3.post(new Runnable() {
                            @Override
                            public void run() {
                                imgSlot3.setImageResource(Helper.getIcon(id));
                            }
                        });
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });

        this.t1.start();
        this.t2.start();
        this.t3.start();
    }

    private void checkWinningCombination() {
        if (imgSlot1.getDrawable().getConstantState().equals(imgSlot2.getDrawable().getConstantState())
        && imgSlot1.getDrawable().getConstantState().equals(imgSlot3.getDrawable().getConstantState())) {
            Toast.makeText(this, "Congratulations! You Win!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT).show();
        }
    }
}