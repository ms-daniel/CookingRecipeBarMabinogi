
import java.awt.Color;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Menu {
	private BufferedReader br = null;
	private String[] dados;
	private Operations op = new Operations(); //cria instancia da classe que ira trabalhar com a barra em popup
	
	private JFrame janela;

	private Object[] resolution = {"1920x1080", "1280x720"};
	
	private String recipeS;
	private JComboBox recipes;
	private JComboBox rec;
	
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
		
		JLabel texto = new JLabel();
		texto.setText("Choice the recipe");
		texto.setBounds(25, 5, 120, 15);
		texto.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel version = new JLabel();
		version.setText("v0.3.1");
		version.setBounds(70, 135, 90, 15);
		version.setFont(new Font("Arial", 0, 10));
		
		
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
		
		JButton ok = new JButton("SELECT");
		ok.setBounds(10, 150, 70, 25);
		ok.setMargin(margem);
		ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ok.setFont(new Font("Arial", Font.BOLD, 13));
		ok.setBackground(Color.DARK_GRAY);
		ok.setForeground(Color.white);
		ok.setFocusable(false);
		ok.setEnabled(true);
		
		JButton cancel = new JButton("CANCEL");
		cancel.setBounds(90, 150, 70, 25);
		cancel.setMargin(margem);
		cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cancel.setFont(new Font("Arial", Font.BOLD, 13));
		cancel.setBackground(Color.RED);
		cancel.setForeground(Color.white);
		cancel.setFocusable(false);
		cancel.setEnabled(true);
		
		JLabel Lresolution = new JLabel();
		Lresolution.setText("Screen Game Resolution");
		Lresolution.setBounds(15, 90, 200, 30);
		Lresolution.setFont(new Font("Arial", Font.BOLD, 12));
		
		JComboBox ComboResolution = new JComboBox(resolution);
		ComboResolution.setBounds(10, 115, 150, 20);
		ComboResolution.setFont(new Font("Arial", Font.BOLD, 12));
		
		getResolution(ComboResolution); //verificar se há resolução salva anteriormente
		
		
		janela.getContentPane().add(texto);
		janela.getContentPane().add(rec);
		janela.getContentPane().add(recipes);
		janela.getContentPane().add(ok);
		janela.getContentPane().add(cancel);
		janela.getContentPane().add(ComboResolution);
		janela.getContentPane().add(Lresolution);
		janela.getContentPane().add(version);
		
		//aciona uma acao quando troca o item do combobox de resolução de tela
		ComboResolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					setResolution((String)ComboResolution.getSelectedItem());
			}
		});
		
		
		//quando pressiona o botao de select
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//atribui a String(nome da receita selecionada no combobox) do combobox
				recipeS = (String)recipes.getSelectedItem();
				
				try {
					//chama a criação da barra com os parametros nome da receita, resolução usada na receita e as coordenadas x e y da barra
					// que estão no arquivo configs 
					op.runBar(recipeS, (String)ComboResolution.getSelectedItem(), dados);

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
		janela.setBounds(0, 0, 185, 220);
		janela.setLocationRelativeTo(null); //alinha a janela no centro da tela
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setIconImage(img_I.getImage()); //define o icone
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
	
	//abre o arquivo para ler a resolução anterior
	private void getResolution(JComboBox resol) {
		//criação do buffer para abrir arquivo txt contendo informações
		try {
			String linha;
			
			//abre arquivo
	        br = new BufferedReader(new FileReader("data/configs.txt"));
	        
	        
	        linha = br.readLine();
	        if(linha!=null) {
	        	this.dados = linha.split(",");
	        	
	        	if(!resol.getItemAt(0).equals(dados[0])) {
		        	resol.setSelectedIndex(1);
	        	}
	        }else {
	        	this.dados = new String(" , , ").split(",");
	        }


	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch(ArrayIndexOutOfBoundsException e){
	    	e.printStackTrace();
	    }finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	//metodo usado para mudar a resolução no arquivo
	private void setResolution(String resol) {
		dados[0] = new String(resol);
		
		String save = dados[0] + "," + dados[1] + "," + dados[2];
	
			
		
		File arq = new File("data", "configs.txt");
		
		//caso o arquivo não exista ele irá criar
		if(!arq.exists()) {
			try {
				arq.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			//criamos um arquivo "escrevivel"
			FileWriter esc_arq = new FileWriter(arq, false);
			
			//usamos a classe printwirter para poder escrever nele
			PrintWriter escrever = new PrintWriter(esc_arq);
			
			//escrevemos
			escrever.println(save);
			escrever.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
}

