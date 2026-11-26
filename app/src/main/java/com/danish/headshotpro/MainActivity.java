
package com.danish.headshotpro;

import android.app.Activity;
import android.os.Bundle;
import android.os.Build;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.*;
import android.view.View;

public class MainActivity extends Activity {

    private final int bg = Color.rgb(7, 7, 10);
    private final int panel = Color.rgb(23, 18, 22);
    private final int red = Color.rgb(255, 35, 55);
    private final int white = Color.WHITE;
    private final int muted = Color.rgb(190, 178, 184);

    private LinearLayout root, content;
    private TextView selectedValues;

    private String selectedDevice = "MID-RANGE ANDROID";

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        showHome();
    }

    private GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        d.setStroke(2, red);
        return d;
    }

    private TextView text(String s, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(size);
        t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setPadding(0, 5, 0, 5);
        return t;
    }

    private Button button(String label) {
        Button b = new Button(this);
        b.setText(label);
        b.setTextColor(Color.BLACK);
        b.setTextSize(13);
        b.setGravity(Gravity.CENTER);
        b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        b.setAllCaps(false);
        b.setPadding(8, 0, 8, 0);
        b.setMinHeight(52);
        b.setMinimumHeight(52);
        b.setBackground(shape(red, 18));
        return b;
    }

    private void addLogo() {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0, 2, 0, 8);

        TextView mark = text("D", 30, white, true);
        mark.setGravity(Gravity.CENTER);
        mark.setBackground(shape(red, 18));

        LinearLayout.LayoutParams mp =
            new LinearLayout.LayoutParams(58, 58);
        mp.setMargins(0, 0, 12, 0);
        row.addView(mark, mp);

        LinearLayout words = new LinearLayout(this);
        words.setOrientation(LinearLayout.VERTICAL);
        words.addView(text("DANISH", 21, red, true));
        words.addView(text("HEADSHOT PRO MAX", 13, white, true));
        words.addView(text("AIM SMART  •  PLAY FAIR", 10, muted, true));

        row.addView(words);
        root.addView(row);
    }

    private void base(String title) {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(bg);
        root.setPadding(18, 16, 18, 14);

        addLogo();

        TextView sub = text(title.toUpperCase(), 12, muted, true);
        sub.setPadding(2, 2, 0, 8);
        root.addView(sub);

        ScrollView scroll = new ScrollView(this);
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(0, 6, 0, 16);
        scroll.addView(content);

        root.addView(scroll,
            new LinearLayout.LayoutParams(-1, 0, 1));

        LinearLayout nav = new LinearLayout(this);
        nav.setGravity(Gravity.CENTER);
        nav.setPadding(0, 8, 0, 0);

        String[] names = {"HOME", "PANEL", "SENSI", "HUD"};

        for (String n : names) {
            Button b = button(n);
            b.setTextSize(10);

            LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(0, 46, 1);
            p.setMargins(2, 0, 2, 0);
            nav.addView(b, p);

            b.setOnClickListener(v -> {
                if (n.equals("HOME")) showHome();
                else if (n.equals("PANEL")) showPanel();
                else if (n.equals("SENSI")) showSensi();
                else showHud();
            });
        }

        root.addView(nav);
        setContentView(root);
    }

    private void card(String heading, String body) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(16, 13, 16, 13);
        box.setBackground(shape(panel, 18));

        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1, -2);
        p.setMargins(0, 8, 0, 8);

        content.addView(box, p);
        box.addView(text(heading, 17, white, true));
        box.addView(text(body, 14, muted, false));
    }

    private void panelButton(String name, Runnable action) {
        Button b = button(name);

        LinearLayout.LayoutParams p =
            new LinearLayout.LayoutParams(-1, 56);
        p.setMargins(0, 7, 0, 7);

        content.addView(b, p);
        b.setOnClickListener(v -> action.run());
    }

    private void showHome() {
        base("Your practice dashboard");

        TextView hero = text(
            "AIM SMART.\nPRACTICE DAILY.\nPLAY FAIR.",
            27, white, true
        );
        hero.setPadding(0, 22, 0, 12);
        content.addView(hero);

        card("WELCOME PLAYER",
            "Welcome to Danish Headshot Pro MAX.");

        card("YOUR DEVICE",
            Build.MANUFACTURER.toUpperCase() + " " +
            Build.MODEL + "\nAndroid " +
            Build.VERSION.RELEASE);

        panelButton("OPEN MAX PANEL", this::showPanel);
        panelButton("SENSITIVITY SETTINGS", this::showSensi);
        panelButton("MOVEMENT SETTINGS", this::showMovement);
        panelButton("HUD SETTINGS", this::showHud);

        card("FAIR PLAY NOTICE",
            "Manual gaming guides and calibration only. " +
            "This app does not control Free Fire MAX or modify game files.");
    }

    private void showPanel() {
        base("MAX Gaming Panel");

        TextView heading = text(
            "MAX GAMING PANEL", 22, red, true
        );
        heading.setGravity(Gravity.CENTER);
        heading.setPadding(0, 16, 0, 12);
        content.addView(heading);

        card("DANISH HEADSHOT PRO",
            "Your gaming settings and calibration center.");

        panelButton("AUTO HEAD AIM CALIBRATION",
            this::showAutoAim);

        panelButton("HEADSHOT GUIDE",
            this::showHeadshotGuide);

        panelButton("SENSITIVITY SETTINGS",
            this::showSensi);

        panelButton("MOVEMENT CONTROL",
            this::showMovement);

        panelButton("HUD SETTINGS",
            this::showHud);

        panelButton("SOFT WRAP",
            this::showSoftWrap);
    }

    private void showAutoAim() {
        base("Auto Head Aim Calibration");

        TextView heading = text(
            "AIM CALIBRATION", 24, red, true
        );
        heading.setGravity(Gravity.CENTER);
        heading.setPadding(0, 16, 0, 12);
        content.addView(heading);

        card("MANUAL AIM ASSIST",
            "Set up your sensitivity, drag control and HUD " +
            "to make your own aiming more comfortable.");

        panelButton("CHOOSE DEVICE",
            this::showSensi);

        panelButton("MOVEMENT SETTINGS",
            this::showMovement);

        panelButton("CHOOSE FINGER HUD",
            this::showHud);

        card("FIRE + DRAG",
            "Keep your finger on the fire button and practice " +
            "a small controlled upward drag when aiming at an opponent.");

        card("IMPORTANT",
            "This is a manual calibration feature. It does not " +
            "automatically identify opponents or move the game's aim.");
    }

    private void showHeadshotGuide() {
        base("Headshot Guide");

        card("HEADSHOT PRACTICE",
            "Improve your timing, aim placement and drag control.");

        card("HOW TO PRACTICE",
            "1. Open the game's Training Ground.\n" +
            "2. Aim comfortably at the opponent.\n" +
            "3. Press fire and practice a small upward drag.\n" +
            "4. Adjust sensitivity gradually.");

        card("HEADSHOT TIP",
            "Avoid large sudden drags. Find a comfortable " +
            "movement and repeat it consistently.");
    }

    private void showSensi() {
        base("Sensitivity Guide");

        card("CHOOSE DEVICE CLASS",
            "Select your device type to see suggested starting values.");

        selectedValues = text(
            "Selected: " + selectedDevice,
            16, white, true
        );
        selectedValues.setBackground(shape(panel, 16));
        selectedValues.setPadding(14, 14, 14, 14);
        content.addView(selectedValues);

        String[] devices = {
            "ENTRY-LEVEL ANDROID",
            "MID-RANGE ANDROID",
            "HIGH-REFRESH ANDROID",
            "VIVO V60e / SIMILAR"
        };

        String[] vals = {
            "General: 85-95\nRed Dot: 80-90\n2x: 70-80\n4x: 60-70\nSniper: 40-55\nFree Look: 65-80",
            "General: 90-100\nRed Dot: 85-95\n2x: 75-85\n4x: 65-75\nSniper: 45-60\nFree Look: 70-85",
            "General: 95-100\nRed Dot: 90-100\n2x: 80-90\n4x: 70-80\nSniper: 50-65\nFree Look: 75-90",
            "General: 92-100\nRed Dot: 88-96\n2x: 78-88\n4x: 68-78\nSniper: 48-62\nFree Look: 72-88"
        };

        for (int i = 0; i < devices.length; i++) {
            final int index = i;

            panelButton(devices[i], () -> {
                selectedDevice = devices[index];
                selectedValues.setText(
                    selectedDevice + "\n\n" +
                    vals[index] +
                    "\n\nApply manually and test."
                );
            });
        }

        card("ANDROID BRANDS",
            "Vivo • Samsung • Xiaomi • Redmi • POCO\n" +
            "OPPO • Realme • OnePlus • Motorola\n" +
            "Google Pixel • Nothing • Infinix • Tecno");

        card("IMPORTANT",
            "These are starting suggestions, not guaranteed headshots. " +
            "Fine-tune them in the game.");
    }

    private void showMovement() {
        base("Movement Settings");

        card("MOVEMENT CONTROL",
            "Practice smooth movement while keeping your aim steady.");

        card("LEFT THUMB",
            "Use the movement joystick. Avoid unnecessary sharp turns.");

        card("JUMP + CROUCH",
            "Keep jump and crouch buttons within comfortable reach.");

        card("DRAG CONTROL",
            "Practice short controlled upward drags while firing. " +
            "Avoid moving your aim too far.");

        card("MOVEMENT PRACTICE",
            "Move sideways, stop briefly, aim and fire. " +
            "Repeat slowly until your movement feels comfortable.");
    }

    private void showHud() {
        base("HUD Layouts");

        card("CHOOSE YOUR FINGER STYLE",
            "Use the layout that feels most comfortable.");

        card("2 FINGER HUD",
            "Left thumb: Movement\n" +
            "Right thumb: Aim, fire, jump and crouch.");

        card("3 FINGER HUD",
            "Left thumb: Movement\n" +
            "Left index: Fire\n" +
            "Right thumb: Aim, jump and crouch.");

        card("4 FINGER HUD",
            "Left thumb: Movement\n" +
            "Left index: Fire\n" +
            "Right index: Scope\n" +
            "Right thumb: Aim and movement actions.");

        card("HUD TIP",
            "Keep fire and scope buttons easy to reach. " +
            "Adjust their positions in the game's HUD editor.");
    }

    private void showSoftWrap() {
        base("Soft Wrap");

        card("SOFT WRAP",
            "Text automatically wraps to fit your phone screen.");

        TextView sample = text(
            "DANISH HEADSHOT PRO MAX\n" +
            "AIM SMART. PRACTICE DAILY.\n" +
            "HEADSHOT TRAINING",
            17, white, true
        );

        sample.setBackground(shape(panel, 18));
        sample.setPadding(16, 18, 16, 18);
        content.addView(sample);

        card("NOTE",
            "This is a display feature. It does not change game controls.");
    }
}
