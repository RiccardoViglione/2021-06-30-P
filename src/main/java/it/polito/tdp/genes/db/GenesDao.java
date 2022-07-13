package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenti;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interactions;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
		}
		
		public List<String> getVertici(){
			String sql = "select  distinct c.`Localization` as l "
					+ "from classification c "
					+ "order by c.`Localization` ";
			List<String> result = new ArrayList<String>();
			Connection conn = DBConnect.getConnection();

			try {
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet res = st.executeQuery();
				while (res.next()) {

					result.add(res.getString("l"));
				}
				res.close();
				st.close();
				conn.close();
				return result;
				
			} catch (SQLException e) {
				throw new RuntimeException("Database error", e) ;
			}
	}
	

		public List<Adiacenti> getArchi(){
			String sql = "select  distinct c.`Localization`as l1 ,c2.`Localization` as l2 ,COUNT(distinct i.`Type` "
					+ ") as peso "
					+ "from classification c,classification c2,interactions i "
					+ "where c.`Localization`>c2.`Localization`and c.`GeneID`!=c2.`GeneID`and ((i.`GeneID1`=c.`GeneID`and i.`GeneID2`=c2.`GeneID`)||(i.`GeneID1`=c2.`GeneID`and i.`GeneID2`=c.`GeneID`)) "
					+ "group by c.`Localization`,c2.`Localization` " ;
			List<Adiacenti> result = new ArrayList<Adiacenti>();
			Connection conn = DBConnect.getConnection();

			try {
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet res = st.executeQuery();
				while (res.next()) {

					result.add(new Adiacenti(res.getString("l1"),res.getString("l2"),res.getInt("peso")));
				}
				res.close();
				st.close();
				conn.close();
				return result;
				
			} catch (SQLException e) {
				throw new RuntimeException("Database error", e) ;
			}
	}
	
}
