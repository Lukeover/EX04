package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Pais;

public class PaisDao {
	public int criar(Pais pais) {
		String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES (?, ?, ?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.execute();
			String sqlQuery  = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					pais.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pais.getId();
	}

	
	public void atualizar(Pais pais) {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.setInt(4, pais.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void excluir(int id) {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Pais carregar(int id) {
		Pais pais = new Pais();
		pais.setId(id);
		String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE pais.id = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, pais.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getLong("Area"));
					
				} else {
					pais.setId(-1);
					pais.setNome(null);
					pais.setPopulacao(-1);
					pais.setArea(-1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}
	public Pais maiorPopulacao() {
		Pais populacao = new Pais();
		String sqlSelect = "SELECT MAX(populacao) FROM Pais";
		try(Connection conn = ConnectionFactory.obtemConexao();
						PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try(ResultSet rs = stm.executeQuery()) {
				if(rs.next()) {
					populacao.setPopulacao(rs.getLong("populacao"));
				}
				else {
					populacao.setPopulacao(-1);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch(SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return populacao;
	}
	public Pais menorArea() {
		Pais area = new Pais();
		String sqlSelect = "SELECT MIN(area) FROM Pais";
		try(Connection conn = ConnectionFactory.obtemConexao();
						PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try(ResultSet rs = stm.executeQuery()) {
				if(rs.next()) {
					area.setArea(rs.getDouble("area"));
				}
				else {
					area.setArea(-1);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch(SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return area;
	}
	public ArrayList<Pais> vetorTresPaises() {
		Connection conn = null;
		try {
			conn = ConnectionFactory.obtemConexao();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 String sqlSelect = "SELECT * FROM Pais";
		PreparedStatement prepare = null;
		ResultSet result = null;
		ArrayList<Pais> lista = new ArrayList<Pais>();
		try {
			prepare = conn.prepareStatement(sqlSelect);
			result = prepare.executeQuery();
			if(result.next()) {
				Pais pais = new Pais();
				pais.setNome(result.getString("África do Sul"));
				pais.setNome(result.getString("Brasil"));
				pais.setNome(result.getString("Canáda"));
				lista.add(pais);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
}
