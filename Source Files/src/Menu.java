
import java.awt.Color;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Menu {
	private JFrame janela;
	
	private JLabel texto;
	private JLabel size;
	
	private JTextField field;
	
	private String recipeS;
	private float tamanho;
	
	private JButton ok;
	private JButton cancel;
	
	private JComboBox rec;
	private JComboBox recipes;
	
	private Object[] type = {"Mixing", "Baking", "Simmering", "Kneading", "Boiling", "Noodle Making", "Deep-frying", "Stir-frying"};
	
	private Object[] mixing = {"Tomato Basil Salad", "Lemonade"};
	private Object[] simmering = {"Steamed Potato"};
	private Object[] baking = {""};
	private Object[] kneading = {"Flour Dough", "Fry Batter"};
	private Object[] boiling = {"Basil Tea","Cheese Fondue", "Starch Syrup"};
	private Object[] noodle = {"Noodle"};
	private Object[] deep_frying = {"Glazed Sweet Potatoes"};
	private Object[] stir_frying = {""};
	
	private Insets margem = new Insets(0,0,0,0);
	
	private ImageIcon img_I = new ImageIcon(
			getClass().getClassLoader().getResource("cooking.png"));
	
	public void GetMenu() {
		texto = new JLabel();
		texto.setText("Choice the recipe");
		texto.setBounds(25, 5, 120, 15);
		texto.setFont(new Font("Arial", Font.BOLD, 13));
		
		
		rec = new JComboBox(type);
		rec.setBounds(10, 25, 150, 30);
		rec.setFont(new Font("Arial", Font.BOLD, 13));
		
		recipes = new JComboBox(mixing);
		recipes.setBounds(10, 65, 150, 30);
		recipes.setFont(new Font("Arial", Font.BOLD, 13));
		
		rec.addActionListener (new ActionListener () { //quando mudar o combobox
		    public void actionPerformed(ActionEvent e) {
		    	ChangeBox();
		    }
		});
		
		ok = new JButton("SELECT");
		ok.setBounds(10, 150, 70, 25);
		ok.setMargin(margem);
		ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ok.setFont(new Font("Arial", Font.BOLD, 13));
		ok.setBackground(Color.DARK_GRAY);
		ok.setForeground(Color.white);
		ok.setFocusable(false);
		ok.setEnabled(true);
		
		cancel = new JButton("CANCEL");
		cancel.setBounds(90, 150, 70, 25);
		cancel.setMargin(margem);
		cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cancel.setFont(new Font("Arial", Font.BOLD, 13));
		cancel.setBackground(Color.RED);
		cancel.setForeground(Color.white);
		cancel.setFocusable(false);
		cancel.setEnabled(true);
		
		size = new JLabel();
		size.setText("Size of bar(cm)");
		size.setBounds(38, 95, 120, 30);
		size.setFont(new Font("Arial", Font.BOLD, 13));
		
		field = new JTextField("6.7");
		field.setBounds(35, 120, 100, 20);
		field.setHorizontalAlignment(JTextField.CENTER);
		
		janela.getContentPane().add(texto);
		janela.getContentPane().add(rec);
		janela.getContentPane().add(recipes);
		janela.getContentPane().add(ok);
		janela.getContentPane().add(cancel);
		janela.getContentPane().add(field);
		janela.getContentPane().add(size);
		
		
		//a��es bot�es
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recipeS = (String)recipes.getSelectedItem();
				try {
					tamanho = Float.parseFloat(field.getText());
					Operations op = new Operations(recipeS, tamanho);
					
					//System.out.println(tamanho);
				}catch(NumberFormatException E) {
					JOptionPane.showMessageDialog(janela, "Invalid number format");
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janela.setVisible(false);
				System.exit(0);
			}
		});
	
	}
	
	
	public JFrame Criarjanela() {
		janela = new JFrame();
		//janela.setUndecorated(true);
		janela.setBounds(0, 0, 185, 220);
		janela.setLocationRelativeTo(null); //alinha a janela no centro da tela
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setIconImage(img_I.getImage()); //define o icone da aplica��o
//		janela.getContentPane().setBackground(Color.BLACK);
		janela.getContentPane().setLayout(null);
		
		return janela;
	}
	
	private void ChangeBox() { //mudar recipes
		recipes.removeAllItems();
		//{"Mixing", "Baking", "Simmering", "Kneading", "Boiling", "Noodle Making", "Deep-frying", "Stir-frying"};
		if(rec.getSelectedItem().toString().equals("Mixing")) {
			for(int i = 0; i < mixing.length; i++) {
				recipes.addItem(mixing[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Baking")) {
			for(int i = 0; i < baking.length; i++) {
				recipes.addItem(baking[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Simmering")) {
			for(int i = 0; i < simmering.length; i++) {
				recipes.addItem(simmering[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Kneading")) {
			for(int i = 0; i < kneading.length; i++) {
				recipes.addItem(kneading[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Boiling")) {
			for(int i = 0; i < boiling.length; i++) {
				recipes.addItem(boiling[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Noodle Making")) {
			for(int i = 0; i < noodle.length; i++) {
				recipes.addItem(noodle[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Deep-frying")) {
			for(int i = 0; i < deep_frying.length; i++) {
				recipes.addItem(deep_frying[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Stir-frying")) {
			for(int i = 0; i < stir_frying.length; i++) {
				recipes.addItem(stir_frying[i]);
			}
		}
		
	}
	
	public void check() {
		System.out.println("aqui estou eu");
	}
	
}

