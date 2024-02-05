package com.renanduarte.grudgelangui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.renanduarte.grudgelang.*;
import com.renanduarte.grudgelang.Compiler;

/* Componentes Swing*/
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.GroupLayout;

public class Widget extends JFrame {
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final String TITLE = "IDE GrudgeLang";
	
	// Componentes
	private JPanel pnlIDE;
	private JTextArea taEditor;
	private JTextArea taOutput;
	private JScrollPane scTaEditor;
	private JScrollPane scTaOutput;
	private JButton btnCompilar;
	private JButton btnCompilarBF;
	private JLabel lblEditor;
	private JLabel lblOutput;
	private GroupLayout layout;
	
	
	public Widget() {
		super(TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		/* Componentes da janela */
		pnlIDE = new JPanel();
		layout = new GroupLayout(pnlIDE);
		pnlIDE.setLayout(layout);
		add(pnlIDE);
		
		lblEditor = new JLabel("Editor");
		lblOutput = new JLabel("Saída");
		taEditor = new JTextArea();
		taOutput = new JTextArea();
		taOutput.setEditable(false);
		scTaEditor = new JScrollPane(taEditor);
		scTaEditor.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scTaOutput = new JScrollPane(taOutput);
		scTaOutput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		taEditor.scrollRectToVisible(getBounds());
		
		btnCompilar = new JButton("Compilar");
		btnCompilar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				compilarAction(e);
			}
			
		});
		btnCompilarBF = new JButton("Compilar Brainf**k");
		btnCompilarBF.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				compilarBFAction(e);
			}
			
		});
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addComponent(lblEditor)
					.addComponent(scTaEditor)
					.addComponent(btnCompilar)
					)
				.addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(lblOutput)
						.addComponent(scTaOutput)
						.addComponent(btnCompilarBF)
					)
				);
		layout.setVerticalGroup(
			layout.createSequentialGroup()
			.addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(lblEditor)
					.addComponent(lblOutput)
				)
			.addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(scTaEditor)
					.addComponent(scTaOutput)
				)
			.addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(btnCompilar)
					.addComponent(btnCompilarBF)
				)
			);
		
		/* Aloca janela */
		pack();
		setVisible(true);
	}
	
	private void compilarAction(ActionEvent e) {
		taOutput.setText("Compilando...\n");
		IDECompiler c = new IDECompiler();
		String code = taEditor.getText().replaceAll("(\\r|\\n)", "");;
		try {
			c.setOutput(taOutput);
			c.compile(code);
			taOutput.setText(taOutput.getText() + "\nCompilado com sucesso!!");
		} catch (CompilerException e1) {
			// TODO Auto-generated catch block
			taOutput.setText(taOutput.getText() + "\nErro: "+e1.getMessage());
			e1.printStackTrace();
		}
	}
	
	private void compilarBFAction(ActionEvent e) {
		taOutput.setText("Compilando...\n");
		IDECompiler c = new IDECompiler();
		String code = taEditor.getText().replaceAll("(\\r|\\n)", "");
		try {
			c.setOutput(taOutput);
			String codeBF = c.convertFromBFCodeToGDG(code);
			c.compile(codeBF);
			taOutput.setText(taOutput.getText() + "\nCompilado com sucesso!!");
		} catch (CompilerException e1) {
			// TODO Auto-generated catch block
			taOutput.setText(taOutput.getText() + "\nErro: "+e1.getMessage());
			e1.printStackTrace();
		}
	}
}
