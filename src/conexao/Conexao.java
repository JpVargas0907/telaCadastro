package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	public static Connection faz_conexao() throws SQLException{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/db_cadastro","root","jpv@rg@s0907");
			
			
		}catch(ClassNotFoundException e) {
			
			throw new SQLException(e.getException());
		}
	}
}
