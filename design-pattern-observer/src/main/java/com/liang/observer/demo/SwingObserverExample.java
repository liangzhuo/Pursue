package com.liang.observer.demo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingObserverExample {
	JFrame jFrame;
	
	public static void main(String[] args) {
		SwingObserverExample example = new SwingObserverExample();
		example.go();
	}
	
	public void go(){
		jFrame = new JFrame();
		
		JButton button = new JButton("Should I do it?");
		button.addActionListener(new AngelListenser());
		button.addActionListener(new DevilListenser());
		
		jFrame.getContentPane().add(BorderLayout.CENTER, button);
	}

}

class AngelListenser implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Don't do it, you might regret it!");
	}
	
}

class DevilListenser implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Come on, do it!");
	}
	
}
