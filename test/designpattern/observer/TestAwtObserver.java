package test.designpattern.observer;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestAwtObserver extends Frame {

	public void launch() {
		Button b = new Button();
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("button clicked");
			}
		});
		add(b);
	}

	public static void main(String[] args) {
		TestAwtObserver t = new TestAwtObserver();
		t.setBounds(100, 100, 100, 50);
		t.launch();
		t.setVisible(true);
	}

}
