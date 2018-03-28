
package service;
import java.util.ArrayList;

import dao.PaisDao;
import model.Pais;
public class PaisService {
	
		PaisDao dao = new PaisDao();
		
		public int criar(Pais pais) {
			return dao.criar(pais);
		}
		
		public void atualizar(Pais pais){
			dao.atualizar(pais);
		}
		
		public void excluir(int id){
			dao.excluir(id);
		}
		
		public Pais carregar(int id){
			return dao.carregar(id);
		}
		public Pais menorArea() {
			return dao.menorArea();
		}
		
		public Pais maiorPopulacao() {
			return dao.maiorPopulacao();
		}
		
		public ArrayList<Pais> vetorTresPaises() {
			return dao.vetorTresPaises();
		}
	}


