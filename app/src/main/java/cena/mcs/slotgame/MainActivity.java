package cena.mcs.slotgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Thread t;
    Handler h;
    int id = 1;
    private ImageView imgSlot;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.imgSlot = this.findViewById(R.id.imgSlot);
        this.btnStart = this.findViewById(R.id.btnStart);
        this.btnStart.setOnClickListener(this);

        this.h = new Handler(Looper.getMainLooper());

    }

    @Override
    public void onClick(View v) {
        if (this.t != null && this.t.isAlive()) {
            this.t.interrupt();
            return;
        }

        this.t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (id == 9) id = 1;
                    else id++;
                    try {
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                imgSlot.setImageResource(Helper.getIcon(id));
                            }
                        });
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });
        this.t.start();
    }
}