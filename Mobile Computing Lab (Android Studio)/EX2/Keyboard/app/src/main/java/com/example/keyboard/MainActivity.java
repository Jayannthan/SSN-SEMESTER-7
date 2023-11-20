package com.example.keyboard;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
	Button a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;
	Button caps, backspace, space, enter, change;
	Button submit_btn, clear_btn;
	EditText editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		caps = findViewById(R.id.caps);
		change = findViewById(R.id.change);
		a = findViewById(R.id.a);
		b = findViewById(R.id.b);
		c = findViewById(R.id.c);
		d = findViewById(R.id.d);
		e = findViewById(R.id.e);
		f = findViewById(R.id.f);
		g = findViewById(R.id.g);
		h = findViewById(R.id.h);
		i = findViewById(R.id.i);
		j = findViewById(R.id.j);
		k = findViewById(R.id.k);
		l = findViewById(R.id.l);
		m = findViewById(R.id.m);
		n = findViewById(R.id.n);
		o = findViewById(R.id.o);
		p = findViewById(R.id.p);
		q = findViewById(R.id.q);
		r = findViewById(R.id.r);
		s = findViewById(R.id.s);
		t = findViewById(R.id.t);
		u = findViewById(R.id.u);
		v = findViewById(R.id.v);
		w = findViewById(R.id.w);
		x = findViewById(R.id.x);
		y = findViewById(R.id.y);
		z = findViewById(R.id.z);
		final boolean[] isUpperCase = { false };
		final boolean[] isSymbolMode = { false };
		caps.setOnClickListener(view -> {
			if (isSymbolMode[0]) {
				a.setText(isUpperCase[0] ? "A" : "a");
				b.setText(isUpperCase[0] ? "B" : "b");
				c.setText(isUpperCase[0] ? "C" : "c");
				d.setText(isUpperCase[0] ? "D" : "d");
				e.setText(isUpperCase[0] ? "E" : "e");
				f.setText(isUpperCase[0] ? "F" : "f");
				g.setText(isUpperCase[0] ? "G" : "g");
				h.setText(isUpperCase[0] ? "H" : "h");
				i.setText(isUpperCase[0] ? "I" : "i");
				j.setText(isUpperCase[0] ? "J" : "j");
				k.setText(isUpperCase[0] ? "K" : "k");
				l.setText(isUpperCase[0] ? "L" : "l");
				m.setText(isUpperCase[0] ? "M" : "m");
				n.setText(isUpperCase[0] ? "N" : "n");
				o.setText(isUpperCase[0] ? "O" : "o");
				p.setText(isUpperCase[0] ? "P" : "p");
				q.setText(isUpperCase[0] ? "Q" : "q");
				r.setText(isUpperCase[0] ? "R" : "r");
				s.setText(isUpperCase[0] ? "S" : "s");
				t.setText(isUpperCase[0] ? "T" : "t");
				u.setText(isUpperCase[0] ? "U" : "u");
				v.setText(isUpperCase[0] ? "V" : "v");
				w.setText(isUpperCase[0] ? "W" : "w");
				x.setText(isUpperCase[0] ? "X" : "x");
				y.setText(isUpperCase[0] ? "Y" : "y");
				z.setText(isUpperCase[0] ? "Z" : "z");
			} else {

				a.setText(isUpperCase[0] ? "a" : "A");
				b.setText(isUpperCase[0] ? "b" : "B");
				c.setText(isUpperCase[0] ? "c" : "C");
				d.setText(isUpperCase[0] ? "d" : "D");
				e.setText(isUpperCase[0] ? "e" : "E");
				f.setText(isUpperCase[0] ? "f" : "F");
				g.setText(isUpperCase[0] ? "g" : "G");
				h.setText(isUpperCase[0] ? "h" : "H");
				i.setText(isUpperCase[0] ? "i" : "I");
				j.setText(isUpperCase[0] ? "j" : "J");
				k.setText(isUpperCase[0] ? "k" : "K");
				l.setText(isUpperCase[0] ? "l" : "L");
				m.setText(isUpperCase[0] ? "m" : "M");
				n.setText(isUpperCase[0] ? "n" : "N");
				o.setText(isUpperCase[0] ? "o" : "O");
				p.setText(isUpperCase[0] ? "p" : "P");
				q.setText(isUpperCase[0] ? "q" : "Q");
				r.setText(isUpperCase[0] ? "r" : "R");
				s.setText(isUpperCase[0] ? "s" : "S");
				t.setText(isUpperCase[0] ? "t" : "T");
				u.setText(isUpperCase[0] ? "u" : "U");
				v.setText(isUpperCase[0] ? "v" : "V");
				w.setText(isUpperCase[0] ? "w" : "W");
				x.setText(isUpperCase[0] ? "x" : "X");
				y.setText(isUpperCase[0] ? "y" : "Y");
				z.setText(isUpperCase[0] ? "z" : "Z");
			}
			isUpperCase[0] = !isUpperCase[0];
		});
		change.setOnClickListener(view -> {
			if (isSymbolMode[0]) {
				// Switching from symbol mode to letter mode
				a.setText(isUpperCase[0] ? "A" : "a");
				b.setText(isUpperCase[0] ? "B" : "b");
				c.setText(isUpperCase[0] ? "C" : "c");
				d.setText(isUpperCase[0] ? "D" : "d");
				e.setText(isUpperCase[0] ? "E" : "e");
				f.setText(isUpperCase[0] ? "F" : "f");
				g.setText(isUpperCase[0] ? "G" : "g");
				h.setText(isUpperCase[0] ? "H" : "h");
				i.setText(isUpperCase[0] ? "I" : "i");
				j.setText(isUpperCase[0] ? "J" : "j");
				k.setText(isUpperCase[0] ? "K" : "k");
				l.setText(isUpperCase[0] ? "L" : "l");
				m.setText(isUpperCase[0] ? "M" : "m");
				n.setText(isUpperCase[0] ? "N" : "n");
				o.setText(isUpperCase[0] ? "O" : "o");
				p.setText(isUpperCase[0] ? "P" : "p");
				q.setText(isUpperCase[0] ? "Q" : "q");
				r.setText(isUpperCase[0] ? "R" : "r");
				s.setText(isUpperCase[0] ? "S" : "s");
				t.setText(isUpperCase[0] ? "T" : "t");
				u.setText(isUpperCase[0] ? "U" : "u");
				v.setText(isUpperCase[0] ? "V" : "v");
				w.setText(isUpperCase[0] ? "W" : "w");
				x.setText(isUpperCase[0] ? "X" : "x");
				y.setText(isUpperCase[0] ? "Y" : "y");
				z.setText(isUpperCase[0] ? "Z" : "z");
				// Change the text of the special button
				change.setText("?123");
			} else {
				// Switching to symbol mode
				a.setText(")");
				b.setText("9");
				c.setText("7");
				d.setText("4");
				e.setText("1");
				f.setText("5");
				g.setText("6");
				h.setText("+");
				i.setText("*");
				j.setText("%");
				k.setText("!");
				l.setText("@");
				m.setText("#");
				n.setText("$");
				o.setText("0");
				p.setText("^");
				q.setText("&");
				r.setText("2");
				s.setText("(");
				t.setText("3");
				u.setText("-");
				v.setText("8");
				w.setText("=");
				x.setText("/");
				y.setText("?");
				z.setText("'");
				// Change the text of the special button
				change.setText("ABC");
			}
			isSymbolMode[0] = !isSymbolMode[0];
		});
		editor = findViewById(R.id.editor);
		View.OnClickListener alphabetClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Button clickedButton = (Button) v;
				String buttonText = clickedButton.getText().toString();
				String currentText = editor.getText().toString();
				editor.setText(currentText + buttonText);
			}
		};
		int[] alphabetButtonIds = {
				R.id.a, R.id.b, R.id.c, R.id.d, R.id.e, R.id.f, R.id.g, R.id.h,
				R.id.i, R.id.j, R.id.k, R.id.l, R.id.m, R.id.n, R.id.o, R.id.p,
				R.id.q, R.id.r, R.id.s, R.id.t, R.id.u, R.id.v, R.id.w, R.id.x,
				R.id.y, R.id.z
		};
		for (int buttonId : alphabetButtonIds) {
			Button alphabetButton = findViewById(buttonId);
			alphabetButton.setOnClickListener(alphabetClickListener);
		}
		space = findViewById(R.id.space);
		space.setOnClickListener(view -> {
			String currentText = editor.getText().toString();
			editor.setText(currentText + " ");
		});
		enter = findViewById(R.id.enter);
		enter.setOnClickListener(view -> {
			String currentText = editor.getText().toString();
			editor.setText(currentText + "\n");
		});
		backspace = findViewById(R.id.backspace);
		backspace.setOnClickListener(view -> {
			String currentText = editor.getText().toString();
			if (!currentText.isEmpty()) {
				String newText = currentText.substring(0, currentText.length() - 1);
				editor.setText(newText);
			}
		});
		submit_btn = findViewById(R.id.submit_btn);
		submit_btn.setOnClickListener(view -> {
			Intent navigate = new Intent(MainActivity.this, DisplayActivity.class);
			navigate.putExtra("input_value", editor.getText().toString());
			startActivity(navigate);
		});
		clear_btn = findViewById(R.id.clear_btn);
		clear_btn.setOnClickListener(view -> {
			String currentText = editor.getText().toString();
			if (!currentText.isEmpty()) {
				editor.setText("");
			}
		});
	}
}