package cookingRecipe;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


@SuppressWarnings({"rawtypes" , "unchecked"})
public class Menu {
	
	//instancia para recebr as Strings com os nomes das receitas
	private recipeList RL = new recipeList();
	
	private BufferedReader br = null;
	private String[] dados;
	
	private Operations op; //cria instancia da classe que ira trabalhar com a barra em popup
	
	private JFrame janela;

	private Object[] resolution = {"1920x1080", "1280x720"};
	
	private String recipeS;
	
	private JComboBox recipes;
	private JComboBox rec;
	
	private Object[] type = {
			"Baking",
			"Boiling",
			"Deep-frying",
			"Fermenting",
			"Jam Making",
			"Julienning",
			"Kneading",
			"Mixing",
			"Noodle Making",
			"Pasta Making",
			"Pie Making",
			"Pizza Making",
			"Simmering",
			"Sous Vide",
			"Steaming",
			"Stir-frying"
	};

	
	//============================================================
	
	//============================================================
	private Insets margem = new Insets(0,0,0,0);
	
	private ImageIcon img_I = new ImageIcon(
			getClass().getClassLoader().getResource("cooking.png"));
	
	private ImageIcon git  = new ImageIcon(getClass().getClassLoader().getResource("git-icon.gif"));
	

	public void GetMenu() {
		
		
		JLabel texto = new JLabel();
		texto.setText("Choice the recipe");
		texto.setBounds(45, 5, 120, 15);
		texto.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel version = new JLabel();
		version.setText("v1.0.0");
		version.setBounds(116, 170, 31, 15);
		version.setFont(new Font("Arial", 0, 10));
		version.setForeground(Color.BLUE);
		
		//caixa de selecao para os tipos de receita
		rec = new JComboBox(type);
		rec.setBounds(10, 25, 185, 30);
		rec.setFont(new Font("Arial", Font.BOLD, 13));
		
		//caixa de selecao para as receitas
		recipes = new JComboBox(RL.baking);
		recipes.setBounds(10, 65, 185, 30);
		recipes.setFont(new Font("Arial", Font.BOLD, 13));
		
		//acao quando muda o valor do tipo de receita
		rec.addActionListener (new ActionListener () { //quando mudar o combobox
		    public void actionPerformed(ActionEvent e) {
		    	ChangeBox();
		    }
		});
		
		//botao de select
		JButton ok = new JButton("SELECT");
		ok.setBounds(10, 190, 90, 25);
		ok.setMargin(margem);
		ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ok.setFont(new Font("Arial", Font.BOLD, 13));
		ok.setBackground(new Color(0, 128, 0));
		ok.setForeground(Color.white);
		ok.setFocusable(false);
		ok.setEnabled(true);
		
		//botao cancelar
		JButton cancel = new JButton("CANCEL");
		cancel.setBounds(105, 190, 90, 25);
		cancel.setMargin(margem);
		cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cancel.setFont(new Font("Arial", Font.BOLD, 13));
		cancel.setBackground(Color.RED);
		cancel.setForeground(Color.white);
		cancel.setFocusable(false);
		cancel.setEnabled(true);
		
		
		JLabel Lresolution = new JLabel();
		Lresolution.setText("Screen Game Resolution");
		Lresolution.setBounds(35, 90, 200, 30);
		Lresolution.setFont(new Font("Arial", Font.BOLD, 12));
		
		//caixa de selecao de resolucao
		JComboBox ComboResolution = new JComboBox(resolution);
		ComboResolution.setBounds(10, 115, 185, 20);
		ComboResolution.setFont(new Font("Arial", Font.BOLD, 12));
		
		//label para botao github
		JLabel gitC = new JLabel();
		gitC.setText("<html><center>Github<br>Creator</center></html>");
		gitC.setBounds(108, 132, 50, 50);
		gitC.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		//botao github
		JButton gitB = new JButton();
		gitB.setIcon(git);
		gitB.setContentAreaFilled(false);
		gitB.setBorderPainted(false);
		gitB.setCursor(new Cursor(Cursor.HAND_CURSOR));
		gitB.setFocusable(false);
		gitB.setToolTipText("Github creator");
		gitB.setBounds(50, 136, 50, 50);
		
		getResolution(ComboResolution); //verificar se ha resolucao salva anteriormente
		op = new Operations(dados); //cria a instancia da barra apos receber dados da resolucao e posicao
		
		janela.getContentPane().add(texto);
		janela.getContentPane().add(rec);
		janela.getContentPane().add(recipes);
		janela.getContentPane().add(ok);
		janela.getContentPane().add(cancel);
		janela.getContentPane().add(ComboResolution);
		janela.getContentPane().add(Lresolution);
		janela.getContentPane().add(version);
		janela.getContentPane().add(gitB);
		janela.getContentPane().add(gitC);
		
		//aciona uma acao quando troca o item do combobox de resolu��o de tela
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
					//chama a cria��o da barra com os parametros nome da receita, resolu��o usada na receita e as coordenadas x e y da barra
					// que est�o no arquivo configs 
					op.runBar(recipeS, (String)ComboResolution.getSelectedItem(), (String)rec.getSelectedItem());

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
		
		gitB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/ms-daniel"));
				} catch (IOException | URISyntaxException e1) {
			            e1.printStackTrace();
			    }
			}
		});
		
		gitC.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	try {
					Desktop.getDesktop().browse(new URI("https://github.com/ms-daniel"));
				} catch (IOException | URISyntaxException e1) {
			            e1.printStackTrace();
			    }
		    }

		});
	
	}
	
	
	public JFrame Criarjanela() {
		janela = new JFrame();
		janela.setTitle("Cooking Bar");
		janela.setBounds(0, 0, 220, 260);
		janela.setLocationRelativeTo(null); //alinha a janela no centro da tela
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setIconImage(img_I.getImage()); //define o icone
		janela.getContentPane().setLayout(null);
		janela.setResizable(false);
		
		return janela;
	}
	
	private void ChangeBox() { //mudar recipes
		recipes.removeAllItems();
		//{"Mixing", "Baking", "Simmering", "Kneading", "Boiling", "Noodle Making", "Deep-frying", "Stir-frying"};
		if(rec.getSelectedItem().toString().equals("Mixing")) {
			for(int i = 0; i < RL.mixing.length; i++) {
				recipes.addItem(RL.mixing[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Baking")) {
			for(int i = 0; i < RL.baking.length; i++) {
				recipes.addItem(RL.baking[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Simmering")) {
			for(int i = 0; i < RL.simmering.length; i++) {
				recipes.addItem(RL.simmering[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Kneading")) {
			for(int i = 0; i < RL.kneading.length; i++) {
				recipes.addItem(RL.kneading[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Boiling")) {
			for(int i = 0; i < RL.boiling.length; i++) {
				recipes.addItem(RL.boiling[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Noodle Making")) {
			for(int i = 0; i < RL.noodle.length; i++) {
				recipes.addItem(RL.noodle[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Deep-frying")) {
			for(int i = 0; i < RL.deep_frying.length; i++) {
				recipes.addItem(RL.deep_frying[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Stir-frying")) {
			for(int i = 0; i < RL.stir_frying.length; i++) {
				recipes.addItem(RL.stir_frying[i]);
			}
		}
		else if(rec.getSelectedItem().toString().equals("Pie Making")) {
			for(int i = 0; i < RL.pie_making.length; i++) {
				recipes.addItem(RL.pie_making[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Pizza Making")) {
			for(int i = 0; i < RL.pizza_making.length; i++) {
				recipes.addItem(RL.pizza_making[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Sous Vide")) {
			for(int i = 0; i < RL.sous_vide.length; i++) {
				recipes.addItem(RL.sous_vide[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Fermenting")) {
			for(int i = 0; i < RL.fermenting.length; i++) {
				recipes.addItem(RL.fermenting[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Jam Making")) {
			for(int i = 0; i < RL.jam_making.length; i++) {
				recipes.addItem(RL.jam_making[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Julienning")) {
			for(int i = 0; i < RL.julienning.length; i++) {
				recipes.addItem(RL.julienning[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Pasta Making")) {
			for(int i = 0; i < RL.pasta_making.length; i++) {
				recipes.addItem(RL.pasta_making[i]);
			}
		}
		
		else if(rec.getSelectedItem().toString().equals("Steaming")) {
			for(int i = 0; i < RL.steaming.length; i++) {
				recipes.addItem(RL.steaming[i]);
			}
		}
		
	}
	
	//abre o arquivo para ler a resolu��o anterior
	private void getResolution(JComboBox resol) {
		//cria��o do buffer para abrir arquivo txt contendo informa��es
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
	
	//metodo usado para mudar a resolu��o no arquivo
	private void setResolution(String resol) {
		dados[0] = new String(resol);
		
		String save = dados[0] + "," + dados[1] + "," + dados[2];
	
			
		
		File arq = new File("data", "configs.txt");
		
		//caso o arquivo n�o exista ele ir� criar
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

