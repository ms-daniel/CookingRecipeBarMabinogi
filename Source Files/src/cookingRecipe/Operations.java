package cookingRecipe;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;

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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Operations {
	private JFrame jan;
	
	private File dir;
	private File img_dir;
	
	private String user_dir = System.getProperty("user.dir");
	
	private loadRecipesInfo search = new loadRecipesInfo();
	private createImage createI = new createImage();
	
	private String[] dados;
	private int cord_x, cord_y;
	
	//nome da receita
	private String recipe;
	private String method;
	private String[] recipe_data;
	
	//nome da pasta onde esta a receita com a resolucao escolhida 
	private String resolution;
	
	//classe usada para mover a janela com o mouse e ela ir se auto ajustando
	private Point point = new Point();
	
	private JButton close;
	
	private ImageIcon img;
	
	private JLabel bar_img;
	
	private Insets margem = new Insets(0,0,0,0);
	
	public Operations(String[] dados) {
		this.dados = dados;
		this.cord_x = Integer.parseInt(dados[1]);
		this.cord_y = Integer.parseInt(dados[2]);
		
		jan = new JFrame();
		jan.setUndecorated(true);
		jan.setBounds(cord_x, cord_y, 330, 27);
		jan.setBackground(new Color(1.0f,1.0f,1.0f,0.05f));
		jan.setLayout(null);
		jan.setAlwaysOnTop(true);
		
		close = new JButton("X");
		close.setBounds(310, 4, 15, 15);
		close.setMargin(margem);
		close.setCursor(new Cursor(Cursor.HAND_CURSOR));
		close.setFont(new Font("Arial", Font.BOLD, 13));
		close.setBackground(Color.red);
		close.setForeground(Color.white);
		close.setFocusable(false);
		close.setEnabled(true);
		
		bar_img = new JLabel();
		bar_img.setBounds(4, -9, 300, 40);
		
		//JLabel para definir bordas no frame
		JLabel border = new JLabel();
		border.setBounds(0, 0, 330, 27);
		border.setBorder(BorderFactory.createLineBorder(Color.black));
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jan.setVisible(false);
			}
		});
		
		//esses eventos sao de quando o usuario puxa a janela pop-up.
		//assim que esse evento acontece o programa vai capturando a posicao do mouse
		//para mover a janela junto
		jan.addMouseListener(new MouseAdapter() {
		      public void mousePressed(MouseEvent e) {
		        point.x = e.getX();
		        point.y = e.getY();
		      }
			public void mouseReleased(MouseEvent e) {
		    	  jan.setCursor(Cursor.DEFAULT_CURSOR);
			  }
		    });
		 jan.addMouseMotionListener(new MouseMotionAdapter() {
		      public void mouseDragged(MouseEvent e) {
		    	jan.setCursor(Cursor.MOVE_CURSOR);
		        Point p = jan.getLocation();
		        
		        cord_x = p.x + e.getX() - point.x;
		        cord_y = p.y + e.getY() - point.y;
		        
		        //salva coordenadas nos vetores para poder salvar a configuraÃ§Ã£o para uso posterior
		        dados[1] = String.valueOf(cord_x);
		        dados[2] = String.valueOf(cord_y);
		        
		        jan.setLocation(cord_x, cord_y);
		        setXandY();
		      }
		      
		    });
		
		jan.getContentPane().add(bar_img);
		jan.getContentPane().add(close);
		jan.getContentPane().add(border);
	}
	
	public void runBar(String recipe, String resolution, String method) {
		this.recipe = recipe;
		this.method = method;
		this.resolution = resolution;

		dir = new File("resources/" + resolution);
		
		Bar();
		
	}
	
	private void Bar() {
		recipe_data = null;

		//verifica se diretorio não existe e se não existir ele cria
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		//aqui ele irá carregar a imagem
		//try {
			img_dir = new File(user_dir + "\\resources\\" + resolution + "\\" + recipe + ".png");
			
			if(!img_dir.exists()){
				if(resolution.equals("1920x1080"))
					recipe_data = search.getRecipeInfo(recipe, method);
				
				//criara a imagem a partir das informacoes contidas no arquivo(se houver)
				if(recipe_data != null) {
					//criar imagem
					
					int v1, v2, v3;
					v1 = (int) (244 * (Float.parseFloat(recipe_data[2])/100));
					v2 = (int) (244 * (Float.parseFloat(recipe_data[4])/100)) + v1;
					v3 = (int) (244 * (Float.parseFloat(recipe_data[6])/100)) + v2;
					
					createI.doLines(v1, v2, v3 );
					createI.doFont(recipe_data[1], recipe_data[3], recipe_data[5]);
					
					//tem um try/cach la dentro não tratado, se futuramente houver problema dar uma olhada nele
					createI.saveImage(recipe_data[0], this.resolution);
				}
			}
			
			//if(img_dir.exists()) {
				img = new ImageIcon(img_dir.getAbsolutePath());
				
				//atribui a imagem a label
				bar_img.setIcon(img);
				
				jan.setVisible(true);
			//}
	}
	
	//usado para mudar o X e Y dentro do arquivo configs (segundo e terceiro elementos)
	private void setXandY() {
		String save = dados[0] + "," + dados[1] + "," + dados[2];

		File arq = new File("data", "configs.txt");
		
		//caso o arquivo nï¿½o exista ele irï¿½ criar
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
