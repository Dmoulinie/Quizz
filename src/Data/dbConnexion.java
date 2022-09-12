package Data;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbConnexion {
	static InputStream inputStream;
	private static String login;
	private static String password;
	private static String host;
	private static String dbName;
	private static String url;
	private static String port;
	
	/*
	 * public dbConnexion(String login, String password, String host) {
	 * 
	 * this.login = login; this.password = password; this.host = host; }
	 */
	public dbConnexion() throws FileNotFoundException, IOException {
		BDDLecture BDDLecture = new BDDLecture();
		BDDLecture.getAppConfig();
		host = BDDLecture.getHost();
		port = BDDLecture.getPort();
		dbName = BDDLecture.getDbName();
		login = BDDLecture.getLogin();
		password = BDDLecture.getPassword();
		url = "jdbc:mysql://" + host + ":" + port + "/" + dbName
					+ "?enabledTLSProtocols=TLSv1.2";
	}
	

	
	/**
	 * Fichier properties pour choix graphique
	 * Todo architecture 3tier  base de donnée  / vue client  / entre deux
	 * mutualisation a revoir
	 * classe affichage
	 * @return
	 */

	public Connection dbConnect() {
		try{  		
			Class.forName("com.mysql.cj.jdbc.Driver");	
			Connection con = DriverManager.getConnection(url, login, password);
			return con;
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}		
	}
	
	public String[][] getData(Connection con) throws SQLException {
		PreparedStatement pstmt = con.prepareStatement("SELECT pays,ville FROM quesreponses",
				ResultSet.TYPE_SCROLL_INSENSITIVE,
			    ResultSet.CONCUR_READ_ONLY);
		ResultSet result = pstmt.executeQuery();
		result.last();
		
		String[][] data = new String[result.getRow()][2];
		result.beforeFirst();
		int index = 0;
		while(result.next()) {
			data[index][0] = result.getString(1);
			data[index][1] = result.getString(2);
			index++;
		}	
		return data;
	}
	
	/**
	 * Ferme la connection a la base de donnée
	 *  
	 * @param con
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws SQLException
	 */
	
	
	public static String[][] getAllData() throws FileNotFoundException, IOException, SQLException {
		dbConnexion dbConnexion = new dbConnexion();
		Connection con = dbConnexion.dbConnect();
		return dbConnexion.getData(con);
	}
	
	public static void dbClose(Connection con) throws SQLException {
		con.close();
	}
	
}
