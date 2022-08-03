package cookingRecipe;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class loadRecipesInfo {
	private BufferedReader file;
	private String recipe_name;
	private String recipe_method;
	private String[] dados;
	
	//constructor ira receber nome da receita e metodo para poder buscar 
	/*public loadRecipesInfo(String name, String method) {
		this.recipe_name = name;
		this.recipe_method = method;
	}*/
	//metodo para obter informações da receita desejada
	public String[] getRecipeInfo(String name, String method) {
		this.recipe_name = name;
		this.recipe_method = method.toLowerCase();
		this.recipe_method = this.recipe_method.replaceAll(" ",	"_");//troca espacos por underline
		this.recipe_method = this.recipe_method.replaceAll("-",	"_");//troca o tracinho por underline
		
		searchRecipe();
		
		return dados;
	}
	private void searchRecipe() {
		String linha;
		
		try {
			file = new BufferedReader(new FileReader("data\\recipes\\" + recipe_method + ".csv"));
			
			//lê linha a linha do arquivo
			while((linha = file.readLine()) != null) {
				if(linha != null) {
					dados = linha.split(",");
					if(dados[0].equals(recipe_name)) 
						break;
				}
			}
			
			if(!dados[0].equals(recipe_name)) {
				JOptionPane.showMessageDialog(null, "Receita não encontrada!");
				dados = null;
			}
			 
		}catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Arquivo não encontrado no diretorio!");
			
		}catch (HeadlessException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
