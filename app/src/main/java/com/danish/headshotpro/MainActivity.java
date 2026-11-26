
package com.danish.headshotpro;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import java.util.Random;

public class MainActivity extends Activity {

    
private final int bg = Color.rgb(7,7,10);
private final int panel = Color.rgb(23,18,22);
private final int green = Color.rgb(255,35,55);
private final int blue = Color.rgb(255,35,55);
private final int white = Color.WHITE;
private final int muted = Color.rgb(190,178,184);

    private LinearLayout root, content;
    private int score = 0;
    private TextView scoreView;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        showHome();
    }

    private GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        d.setStroke(2, blue);
        return d;
    }

    private TextView text(String s, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(size);
        t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setPadding(0,5,0,5);
        return t;
    }

    
private Button button(String label) {
    Button b = new Button(this);
    b.setText(label);
    b.setTextColor(Color.BLACK);
    b.setTextSize(14);
    b.setGravity(Gravity.CENTER);
    b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
    b.setAllCaps(false);
    b.setIncludeFontPadding(false);
    b.setPadding(8, 0, 8, 0);
    b.setMinHeight(52);
    b.setMinimumHeight(52);
    b.setBackground(shape(green, 18));
    return b;
}
    

    private void base(String title) {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(bg);
        root.setPadding(20,18,20,18);

        ScrollView scroll = new ScrollView(this);
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(0,8,0,18);
        scroll.addView(content);

        TextView brand = text("DANISH / HEADSHOT PRO MAX",19,green,true);
        root.addView(brand);

        TextView sub = text(title.toUpperCase(),12,muted,true);
        root.addView(sub);

        root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));

        LinearLayout nav = new LinearLayout(this);
        nav.setGravity(Gravity.CENTER);
        nav.setPadding(0,10,0,0);

        String[] names = {"HOME","PANEL","SENSI","HUD","TRAIN"};

        for (String n : names) {
            Button b = button(n);
            b.setTextSize(10);

            LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(0,48,1);
            p.setMargins(2,0,2,0);

            nav.addView(b,p);

            b.setOnClickListener(v -> {
                if(n.equals("HOME")) showHome();
                else if(n.equals("PANEL")) showPanel();
                else if(n.equals("SENSI")) showSensi();
                else if(n.equals("HUD")) showHud();
                else showTrain();
            });
        }

        root.addView(nav);
        setContentView(root);
    }

    private void card(String heading, String body) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(16,13,16,13);
        box.setBackground(shape(panel,18));

        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1,-2);
        p.setMargins(0,8,0,8);

        content.addView(box,p);
        box.addView(text(heading,17,white,true));
        box.addView(text(body,14,muted,false));
    }

    private void panelButton(String name, Runnable action) {
        Button b = button(name);

        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1,58);
        p.setMargins(0,7,0,7);

        content.addView(b,p);
        b.setOnClickListener(v -> action.run());
    }

    // HOME
    private void showHome() {
        base("Your practice dashboard");

        TextView hero = text(
            "AIM SMART.\nPRACTICE DAILY.\nPLAY FAIR.",
            28,white,true
        );

        hero.setPadding(0,24,0,12);
        content.addView(hero);

        card("WELCOME PLAYER",
            "Welcome to Danish Headshot Pro MAX.");

        card("QUICK START",
            "Open Panel, choose your practice option and improve your skills.");

        card("FAIR PLAY NOTICE",
            "This app does not control Free Fire MAX or provide an aimbot.");
    }

    // MAX PANEL
    private void showPanel() {
        base("MAX Gaming Panel");

        TextView heading = text(
            "🔥 MAX GAMING PANEL",
            23,green,true
        );
        heading.setGravity(Gravity.CENTER);
        heading.setPadding(0,20,0,15);
        content.addView(heading);

        card("DANISH HEADSHOT PRO",
            "Your gaming practice control center.");

        panelButton("🎯 HEADSHOT PRACTICE", () -> showTrain());

        panelButton("⚡ AUTO HEAD SHORT",
            () -> showPracticeInfo());

        panelButton("🎮 AIM PRACTICE",
            () -> showTrain());

        panelButton("🎚 SENSITIVITY SETTINGS",
            () -> showSensi());

        panelButton("📱 HUD SETTINGS",
            () -> showHud());

        panelButton("↔ SOFT WRAP",
            () -> showSoftWrap());

        card("STATUS",
            "Practice panel ready.\nAll settings are manual.");
    }

    private void showPracticeInfo() {
        base("Auto Head Short");

        card("AUTO HEAD SHORT",
            "Practice guide for improving your headshot timing.");

        card("HOW TO PRACTICE",
            "1. Open Training Ground.\n" +
            "2. Keep your aim comfortable.\n" +
            "3. Practice controlled upward drag.\n" +
            "4. Repeat and improve your accuracy.");

        card("FAIR PLAY",
            "This is a manual practice guide. It does not automatically aim or control the game.");
    }

    private void showSoftWrap() {
        base("Soft Wrap");

        card("SOFT WRAP",
            "Soft Wrap is a text display option in this app.");

        TextView sample = text(
            "DANISH HEADSHOT PRO MAX\n" +
            "AIM SMART. PRACTICE DAILY.\n" +
            "HEADSHOT TRAINING",
            17,white,true
        );

        sample.setBackground(shape(panel,18));
        sample.setPadding(16,18,16,18);
        content.addView(sample);

        card("NOTE",
            "Text wrapping is automatic according to screen width.");
    }

    // SENSITIVITY
    private void showSensi() {
        base("Sensitivity guide");

        card("CHOOSE DEVICE CLASS",
            "Choose your device to see starting values.");

        String[] devices = {
            "Entry-level Android",
            "Mid-range Android",
            "High-refresh Android",
            "Vivo V60e / similar"
        };

        Spinner sp = new Spinner(this);
        sp.setAdapter(new ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            devices
        ));

        content.addView(sp);

        TextView values = text(
            "General: 90–100\nRed Dot: 85–95\n" +
            "2x Scope: 75–85\n4x Scope: 65–75\n" +
            "Sniper Scope: 45–60\nFree Look: 70–85",
            16,white,false
        );

        content.addView(values);

        sp.setOnItemSelectedListener(
            new android.widget.AdapterView.OnItemSelectedListener() {

                public void onNothingSelected(
                    android.widget.AdapterView<?> a) {}

                public void onItemSelected(
                    android.widget.AdapterView<?> a,
                    View v,int pos,long id) {

                    String[] vals = {
                        "General 85–95\nRed Dot 80–90\n2x 70–80\n4x 60–70\nSniper 40–55\nFree Look 65–80",
                        "General 90–100\nRed Dot 85–95\n2x 75–85\n4x 65–75\nSniper 45–60\nFree Look 70–85",
                        "General 95–100\nRed Dot 90–100\n2x 80–90\n4x 70–80\nSniper 50–65\nFree Look 75–90",
                        "General 92–100\nRed Dot 88–96\n2x 78–88\n4x 68–78\nSniper 48–62\nFree Look 72–88"
                    };

                    values.setText(vals[pos] +
                        "\n\nApply manually and test.");
                }
            }
        );

        card("IMPORTANT",
            "These are starting suggestions, not guaranteed headshots.");
    }

    // HUD
    private void showHud() {
        base("HUD layouts");

        card("2-FINGER LAYOUT",
            "Left thumb: Movement.\nRight thumb: Aim, fire, jump and crouch.");

        card("3-FINGER LAYOUT",
            "Left thumb: Movement.\nLeft index: Fire.\nRight thumb: Aim.");

        card("4-FINGER LAYOUT",
            "Left thumb: Movement.\nLeft index: Fire.\nRight index: Scope.\nRight thumb: Aim.");

        card("HUD TIP",
            "Choose comfortable button positions and test in Training Ground.");
    }

    // TRAINING GAME
    private void showTrain() {
        base("Tap reaction training");

        card("AIM TRAINER",
            "Tap the green target as it appears.");

        scoreView = text("SCORE: " + score,22,green,true);
        content.addView(scoreView);

        FrameLayout arena = new FrameLayout(this);
        arena.setBackground(shape(
            Color.rgb(27,39,57),22
        ));

        LinearLayout.LayoutParams ap =
            new LinearLayout.LayoutParams(-1,330);
        ap.setMargins(0,12,0,12);

        content.addView(arena,ap);

        Button target = button("TAP!");

        FrameLayout.LayoutParams tp =
            new FrameLayout.LayoutParams(100,64);

        tp.leftMargin = 80;
        tp.topMargin = 110;

        arena.addView(target,tp);

        Random r = new Random();

        target.setOnClickListener(v -> {
            score++;
            scoreView.setText("SCORE: " + score);

            FrameLayout.LayoutParams q =
                (FrameLayout.LayoutParams)target.getLayoutParams();

            int maxX = Math.max(0,
                arena.getWidth()-target.getWidth());

            int maxY = Math.max(0,
                arena.getHeight()-target.getHeight());

            q.leftMargin = r.nextInt(Math.max(1,maxX+1));
            q.topMargin = r.nextInt(Math.max(1,maxY+1));

            target.setLayoutParams(q);
        });

        Button reset = button("RESET SCORE");
        content.addView(reset);

        reset.setOnClickListener(v -> {
            score = 0;
            scoreView.setText("SCORE: 0");
        });
    }
}
