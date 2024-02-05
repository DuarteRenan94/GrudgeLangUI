package com.renanduarte.grudgelangui;

import com.renanduarte.grudgelang.Compiler;

import javax.swing.JTextArea;

public class IDECompiler extends Compiler {

	private JTextArea console;
	private static final String DELIMITER = "$ ";
	
	public IDECompiler() {
		
	}
	
	public void setOutput(JTextArea out) {
		console = out;
	}
	
	public JTextArea getOutput() {
		return this.console;
	}
	
	@Override
	protected void input() {
		String log = console.getText();
		console.setText(log + DELIMITER);
		console.setEditable(true);
		String[] tokens = log.split(DELIMITER);
		int ultimo = tokens.length - 1;
		memory[p] = Integer.parseInt(tokens[ultimo]);
		console.setEditable(false);
		
	}
	
	@Override
	protected void output() {
		console.setText(console.getText() + (char) memory[p] + "\n");
	}
}
