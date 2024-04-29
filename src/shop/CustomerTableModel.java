package shop;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//★login処理を削除
public class CustomerTableModel implements Serializable {
	private String kokyaku_id;
	private String kokyaku_mei;
	private String jyusho;
	private String denwa;
	private String password;
	private int yubin_bango;

	public String getKokyaku_id() {
		return kokyaku_id;
	}
	public void setKokyaku_id(String kokyaku_id) {
		this.kokyaku_id = kokyaku_id;
	}
	public String getKokyaku_mei() {
		return kokyaku_mei;
	}
	public void setKokyaku_mei(String kokyaku_mei) {
		this.kokyaku_mei = kokyaku_mei;
	}
	public String getJyusho() {
		return jyusho;
	}
	public void setJyusho(String jyusho) {
		this.jyusho = jyusho;
	}
	public String getDenwa() {
		return denwa;
	}
	public void setDenwa(String denwa) {
		this.denwa = denwa;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getYubin_bango() {
		return yubin_bango;
	}
	public void setYubin_bango(int yubin_bango) {
		this.yubin_bango = yubin_bango;
	}

	// ------------------------ ログインチェック機構 ★新規追加
	public final static int match = 1;
	public final static int difference = 0;

//★
	public static int LoginCheck(String kokyaku_id, String password){

		System.out.println("CustomerTableModel");

		ArrayList<CustomerTableModel> rlist = selectAllList();//データ取得
		CustomerTableModel rbean = null;

		for(int i = 0; i< rlist.size() ; i++) {
			rbean = (CustomerTableModel)rlist.get(i);
			if ( kokyaku_id.equals(rbean.getKokyaku_id()) ) {
				if (password.equals(rbean.getPassword())) {
					return match;
				}
				return difference;
			}
		}
		return difference;
	}

	//★
	public static Boolean LoginCheck(String kokyaku_id){

		System.out.println("CustomerTableModel");

		ArrayList<CustomerTableModel> rlist = selectAllList();
		CustomerTableModel rbean = null;

		for(int i = 0; i< rlist.size() ; i++) {
			rbean = (CustomerTableModel)rlist.get(i);
			if ( kokyaku_id.equals(rbean.getKokyaku_id()) ) {
				return true;
			}
		}
		return false;
	}

	//★
	public static ArrayList<CustomerTableModel> selectAllList(){

		System.out.println("CustomerTableModel");

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<CustomerTableModel> rlist = new ArrayList<CustomerTableModel>();
		try {
			conn = new ResourceFinder().getConnectionpuser();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM customer");
			while (rs.next()){
				CustomerTableModel rs_model = new CustomerTableModel();
				rs_model.setKokyaku_id(rs.getString("kokyaku_id"));
				rs_model.setKokyaku_mei(rs.getString("kokyaku_mei"));
				rs_model.setJyusho(rs.getString("jyusho"));
				rs_model.setDenwa(rs.getString("denwa"));
				rs_model.setPassword(rs.getString("password"));
				rs_model.setYubin_bango(rs.getInt("yubin_bango"));
				rlist.add(rs_model);
			}
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return rlist;
	}

	//★
	public static void InsertCustomerData(ArrayList<CustomerTableModel> rlist){

		System.out.println("CustomerTableModel");

		Connection conn = null;
		CustomerTableModel rbean = null;
		PreparedStatement st = null;
		try {
			conn = new ResourceFinder().getConnectionpuser();
			for(int i = 0; i< rlist.size() ; i++) {
				rbean = (CustomerTableModel)rlist.get(i);
				st = conn.prepareStatement("INSERT INTO Customer(kokyaku_id, kokyaku_mei, jyusho, denwa, password, yubin_bango) VALUES(?,?,?,?,?,?)");
				st.setString(1, rbean.getKokyaku_id());
				st.setString(2, rbean.getKokyaku_mei());
				st.setString(3, rbean.getJyusho());
				st.setString(4, rbean.getDenwa());
				st.setString(5, rbean.getPassword());
				st.setInt(6, rbean.getYubin_bango());
				st.executeUpdate();
				st.close();
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
