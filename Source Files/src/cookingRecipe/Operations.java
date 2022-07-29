package cookingRecipe;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Operations {
	private String[] dados;
	private int cord_x, cord_y;
	
	//nome da receita
	private String recipe;
	
	//nome da pasta onde esta a receita com a resolucao escolhida 
	private String resolution;
	
	//classe usada para mover a janela com o mouse e ela ir se auto ajustando
	private Point point = new Point();
	
	private boolean mouseEstado = false;
	
	private JButton close;
	
	private int n1, n2, n3;
	private int qt;
	
	private ImageIcon img;
	
	private JLabel l1, l2, l3, bar_img;
	
	private Insets margem = new Insets(0,0,0,0);
	
	public void runBar(String recipe, String resolution, String[] dados) {
		this.recipe = recipe;
		this.resolution = resolution;
		this.dados = dados;
		this.cord_x = Integer.parseInt(dados[1]);
		this.cord_y = Integer.parseInt(dados[2]);
		
		CountQt();
		Bar();
	}
	
	private void Bar() {
		JFrame jan = new JFrame();
		jan.setUndecorated(true);
		jan.setBounds(cord_x, cord_y, 360, 27);
		jan.setBackground(new Color(1.0f,1.0f,1.0f,0.1f));
		jan.setLayout(null);
		jan.setAlwaysOnTop(true);
		
		try {
			img = new ImageIcon(getClass().getClassLoader().getResource(resolution + "/" + recipe + ".png"));
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(jan, "This recipe doesn't exist yet.");
			jan.setVisible(false);
		}
		
		close = new JButton("X");
		close.setBounds(300, 0, 15, 15);
		close.setMargin(margem);
		close.setCursor(new Cursor(Cursor.HAND_CURSOR));
		close.setFont(new Font("Arial", Font.BOLD, 13));
		close.setBackground(Color.red);
		close.setForeground(Color.white);
		close.setFocusable(false);
		close.setEnabled(true);
		
		bar_img = new JLabel();
		bar_img.setIcon(img);
		bar_img.setBounds(0, -9, 300, 40);
		
		jan.getContentPane().add(bar_img);
		jan.getContentPane().add(close);
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jan.setVisible(false);
			}
		});
		
		//esses eventos são de quando o usuario puxa a janela pop-up.
		//assim que esse evento acontece o programa vai capturando a posição do mouse
		//para mover a janela junto
		jan.addMouseListener(new MouseAdapter() {
		      public void mousePressed(MouseEvent e) {
		        point.x = e.getX();
		        point.y = e.getY();
		      }
		    });
		 jan.addMouseMotionListener(new MouseMotionAdapter() {
		      public void mouseDragged(MouseEvent e) {
		        Point p = jan.getLocation();
		        
		        cord_x = p.x + e.getX() - point.x;
		        cord_y = p.y + e.getY() - point.y;
		        
		        dados[1] = String.valueOf(cord_x);
		        dados[2] = String.valueOf(cord_y);
		        
		        jan.setLocation(cord_x, cord_y);
		        setXandY();
		      }
		    });
		 
		 jan.setVisible(true);
		
	}
	
	private void CountQt() {
		if(recipe.equals("Steamed Potato")) {
			qt = 3;
		}
		else
			qt = 3;
	}
	
	private void Change() {
		JFrame jan = new JFrame();
		if(!mouseEstado)
			jan.setUndecorated(false);
		else
			jan.setUndecorated(true);
		
		jan.setBounds(50, 900, 340, 50);
		//jan.setBackground(new Color(1.0f,1.0f,1.0f,0.1f));
		jan.setVisible(true);
		jan.setLayout(null);
	}
	
	//usado para mudar o X e Y dentro do arquivo configs (segundo e terceiro elementos)
	private void setXandY() {
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
