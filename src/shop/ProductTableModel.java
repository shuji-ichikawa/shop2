package shop;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ProductTableModel implements Serializable {

	private String Login_id;
	private int shohin_id;
	private String shohin_mei;
	private int tanka;
	private int zaiko;
	private int kosu;
	private String shashin;
	private String setsumei;
	private int chumon_id;
	private int pagecount;
	Timestamp chumon_bi;

	public String getLogin_id() {
		return Login_id;
	}
	public void setLogin_id(String login_id) {
		Login_id = login_id;
	}
	public int getShohin_id() {
		return shohin_id;
	}
	public void setShohin_id(int shohin_id) {
		this.shohin_id = shohin_id;
	}
	public String getShohin_mei() {
		return shohin_mei;
	}
	public void setShohin_mei(String shohin_mei) {
		this.shohin_mei = shohin_mei;
	}
	public int getTanka() {
		return tanka;
	}
	public void setTanka(int tanka) {
		this.tanka = tanka;
	}
	public int getZaiko() {
		return zaiko;
	}
	public void setZaiko(int zaiko) {
		this.zaiko = zaiko;
	}
	public int getKosu() {
		return kosu;
	}
	public void setKosu(int kosu) {
		this.kosu = kosu;
	}
	public String getShashin() {
		return shashin;
	}
	public void setShashin(String shashin) {
		this.shashin = shashin;
	}
	public int getChumon_id() {
		return chumon_id;
	}
	public void setChumon_id(int chumon_id) {
		this.chumon_id = chumon_id;
	}
	public Timestamp getChumon_bi() {
		return chumon_bi;
	}
	public void setChumon_bi(Timestamp chumon_bi) {
		this.chumon_bi = chumon_bi;
	}
	public String getSetsumei() {
		return setsumei;
	}
	public void setSetsumei(String setsumei) {
		this.setsumei = setsumei;
	}
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public final static int INVENTORY_SHORTAGE = -1;	// 在庫不足
	public final static int SHOUHIN_MEI_NOT_FOUND = -1; // 商品が存在しない

	//★
	// 商品IDの存在チェック処理
	public static Boolean ShohinIDExist(int shohin_id){

		System.out.println("ProductTableModel");

		ArrayList<ProductTableModel> rlist = selectAllList();
		ProductTableModel rbean = null;
		for(int i = 0; i< rlist.size() ; i++) {
			rbean = (ProductTableModel)rlist.get(i);
			if ( shohin_id == rbean.getShohin_id() ) {
				return true;
			}
		}
		return false;
	}

//	// 商品在庫チェック処理
//	public static int ShohinZaikoCheck(String shohin_mei, int kosu){
//		ArrayList<ProductTableModel> rlist = selectAllList();
//		ProductTableModel rbean = null;
//		for(int i = 0; i< rlist.size() ; i++) {
//			rbean = (ProductTableModel)rlist.get(i);
//			if ( shohin_mei.equals(rbean.getShohin_mei()) ) {
//				if ( kosu <= rbean.getZaiko() ) {
//					return rbean.getZaiko();
//				}
//			}
//		}
//		return INVENTORY_SHORTAGE;
//	}
//
//	// ID取得処理(productテーブル)
//	public static int ShohinIDCheck(String shohin_mei){
//		ArrayList<ProductTableModel> rlist = selectAllList();//データ取得
//		ProductTableModel rbean = null;
//		for(int i = 0; i< rlist.size() ; i++) {
//			rbean = (ProductTableModel)rlist.get(i);
//			if ( shohin_mei.equals(rbean.getShohin_mei()) ) {
//				return rbean.getShohin_id();
//			}
//		}
//		return SHOUHIN_MEI_NOT_FOUND;
//	}

	//★
	public static ArrayList<ProductTableModel> selectAllList(){

		System.out.println("ProductTableModel");

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ProductTableModel> rlist = new ArrayList<ProductTableModel>();
		try {
			conn = new ResourceFinder().getConnectionpuser();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM product");
			while (rs.next()){
				ProductTableModel rs_model = new  ProductTableModel();
				rs_model.setShohin_id(rs.getInt("shohin_id"));
				rs_model.setShohin_mei(rs.getString("shohin_mei"));
				rs_model.setTanka(rs.getInt("tanka"));
				rs_model.setZaiko(rs.getInt("zaiko"));
				rs_model.setShashin(rs.getString("shashin"));
				rs_model.setSetsumei(rs.getString("setsumei"));
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

	public static ArrayList<ProductTableModel> selectSUBList(int pagecount, int sort_number){

		System.out.println("ProductTableModel");

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<ProductTableModel> rlist = new ArrayList<ProductTableModel>();
		int pagecount1 = pagecount * 3 - 3;
//		int pagecount2 = pagecount * 3;
		try {

			if(sort_number == 1) {
				conn = new ResourceFinder().getConnectionpuser();
				st = conn.prepareStatement("SELECT * FROM product order by shohin_id LIMIT 3 OFFSET ?");
				st.setInt(1, pagecount1);
				rs = st.executeQuery();
				while (rs.next()){
					ProductTableModel rs_model = new  ProductTableModel();
					rs_model.setShohin_id(rs.getInt("shohin_id"));
					rs_model.setShohin_mei(rs.getString("shohin_mei"));
					rs_model.setTanka(rs.getInt("tanka"));
					rs_model.setZaiko(rs.getInt("zaiko"));
					rs_model.setShashin(rs.getString("shashin"));
					rs_model.setSetsumei(rs.getString("setsumei"));
					rlist.add(rs_model);
				}
				rs.close();
				st.close();
			} else if(sort_number == 2) {
				conn = new ResourceFinder().getConnectionpuser();
				st = conn.prepareStatement("SELECT * FROM product order by shohin_id desc LIMIT 3 OFFSET ?");
				st.setInt(1, pagecount1);
				rs = st.executeQuery();
				while (rs.next()){
					ProductTableModel rs_model = new  ProductTableModel();
					rs_model.setShohin_id(rs.getInt("shohin_id"));
					rs_model.setShohin_mei(rs.getString("shohin_mei"));
					rs_model.setTanka(rs.getInt("tanka"));
					rs_model.setZaiko(rs.getInt("zaiko"));
					rs_model.setShashin(rs.getString("shashin"));
					rs_model.setSetsumei(rs.getString("setsumei"));
					rlist.add(rs_model);
				}
				rs.close();
				st.close();
			} else if(sort_number == 3) {
				conn = new ResourceFinder().getConnectionpuser();
				st = conn.prepareStatement("SELECT * FROM product order by tanka LIMIT 3 OFFSET ?");
				st.setInt(1, pagecount1);
				rs = st.executeQuery();
				while (rs.next()){
					ProductTableModel rs_model = new  ProductTableModel();
					rs_model.setShohin_id(rs.getInt("shohin_id"));
					rs_model.setShohin_mei(rs.getString("shohin_mei"));
					rs_model.setTanka(rs.getInt("tanka"));
					rs_model.setZaiko(rs.getInt("zaiko"));
					rs_model.setShashin(rs.getString("shashin"));
					rs_model.setSetsumei(rs.getString("setsumei"));
					rlist.add(rs_model);
				}
				rs.close();
				st.close();
			} else if(sort_number == 4) {
				conn = new ResourceFinder().getConnectionpuser();
				st = conn.prepareStatement("SELECT * FROM product order by tanka desc LIMIT 3 OFFSET ?");
				st.setInt(1, pagecount1);
				rs = st.executeQuery();
				while (rs.next()){
					ProductTableModel rs_model = new  ProductTableModel();
					rs_model.setShohin_id(rs.getInt("shohin_id"));
					rs_model.setShohin_mei(rs.getString("shohin_mei"));
					rs_model.setTanka(rs.getInt("tanka"));
					rs_model.setZaiko(rs.getInt("zaiko"));
					rs_model.setShashin(rs.getString("shashin"));
					rs_model.setSetsumei(rs.getString("setsumei"));
					rlist.add(rs_model);
				}
				rs.close();
				st.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rlist;
	}

	//★
	public static ProductTableModel SelectDetails(int shohin_id) {

		System.out.println("ProductTableModel");

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ProductTableModel prbean = new ProductTableModel();
		try {
			conn = new ResourceFinder().getConnectionpuser();
			st = conn.prepareStatement("SELECT * FROM product WHERE shohin_id=?");
			st.setInt(1, shohin_id);
			rs = st.executeQuery();
			rs.next();
			prbean.setShohin_id(rs.getInt("shohin_id"));
			prbean.setShohin_mei(rs.getString("shohin_mei"));
			prbean.setTanka(rs.getInt("tanka"));
			prbean.setZaiko(rs.getInt("zaiko"));
			prbean.setShashin(rs.getString("shashin"));
			prbean.setSetsumei(rs.getString("setsumei"));
			rs.close();
			st.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return prbean;
	}

	//★
	public static int SelectStock(int shohin_id) {

		System.out.println("ProductTableModel");

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int zaiko = 0;
		try {
			conn = new ResourceFinder().getConnectionpuser();
			st = conn.prepareStatement("SELECT zaiko FROM product WHERE shohin_id=?");
			st.setInt(1, shohin_id);
			rs = st.executeQuery();
			rs.next();
			zaiko = rs.getInt("zaiko");
			rs.close();
			st.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return zaiko;
	}

	//★
	public static void InsertData(ArrayList<ProductTableModel> listA, String Login){

		System.out.println("ProductTableModel");

		Connection conn = null;
		ProductTableModel prbean = null;
		PreparedStatement st = null;
		try {
			conn = new ResourceFinder().getConnectionpuser();
			for(int i = 0; i < listA.size(); i++) {
				prbean = (ProductTableModel)listA.get(i);
				st = conn.prepareStatement("INSERT INTO chumon(shohin_id, kokyaku_id, suryo, chumon_bi) VALUES(?,?,?,now())");
				st.setInt(1, prbean.getShohin_id());
				st.setString(2, Login);
				st.setInt(3, prbean.getKosu());
				st.executeUpdate();
				st.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//★
	public static int Sum(ArrayList<ProductTableModel> list) {

		System.out.println("ProductTableModel");

		int s = 0;
		if(list!=null) {
			for(ProductTableModel prbean : list) {
				s += prbean.tanka*prbean.kosu;
			}
		}
		return s;
	}

	//★
	public static ArrayList<ProductTableModel> selectrireki(String Login){

		System.out.println("ProductTableModel");

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<ProductTableModel> list = new ArrayList<ProductTableModel>();
		try {
			conn = new ResourceFinder().getConnectionpuser();
			st = conn.prepareStatement("select CH.chumon_id, P.shashin, P.shohin_mei, P.tanka, CH.suryo, C.kokyaku_id, CH.chumon_bi from chumon as CH inner join product as P on CH.shohin_id = P.shohin_id inner join customer as C on CH.kokyaku_id = C.kokyaku_id where CH.kokyaku_id = ?");
			st.setString(1, Login);
			rs = st.executeQuery();
			while(rs.next()) {
				ProductTableModel chumonmodel = new ProductTableModel();
				chumonmodel.setChumon_id(rs.getInt("chumon_id"));
				chumonmodel.setShashin(rs.getString("shashin"));
				chumonmodel.setShohin_mei(rs.getString("shohin_mei"));
				chumonmodel.setTanka(rs.getInt("tanka"));
				chumonmodel.setZaiko(rs.getInt("suryo"));
				chumonmodel.setChumon_bi(rs.getTimestamp("chumon_bi"));
				list.add(chumonmodel);
			}
			st.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	//★
	// 管理者用データベース挿入処理(productテーブル)
	public static void InsertProductData(ArrayList<ProductTableModel> list){

		System.out.println("ProductTableModel");

		Connection conn = null;
		ProductTableModel ptmodel = null;
		PreparedStatement st = null;
		try {
			conn = new ResourceFinder().getConnectionpuser();
			for(int i = 0; i < list.size(); i++) {
				ptmodel = (ProductTableModel)list.get(i);
				st = conn.prepareStatement("INSERT INTO product(shohin_id,shohin_mei,tanka,zaiko,shashin,setsumei) VALUES(?,?,?,?,?,?)");
				st.setInt(1, ptmodel.getShohin_id());
				st.setString(2, ptmodel.getShohin_mei());
				st.setInt(3, ptmodel.getTanka());
				st.setInt(4, ptmodel.getZaiko());
				st.setString(5, ptmodel.getShashin());
				st.setString(6, ptmodel.getSetsumei());
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

	//★
	public static void UpdateProductData(ArrayList<ProductTableModel> zaiko_list) {

		System.out.println("ProductTableModel");

		Connection conn = null;
		ProductTableModel ptmodel = null;
		PreparedStatement st = null;
		try {
			conn = new ResourceFinder().getConnectionpuser();
			for(int i = 0; i < zaiko_list.size(); i++) {
				ptmodel = (ProductTableModel)zaiko_list.get(i);
				st = conn.prepareStatement("UPDATE product SET zaiko = ? WHERE shohin_id = ?");
				st.setInt(1, ptmodel.getZaiko());
				st.setInt(2, ptmodel.getShohin_id());
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

//	// 管理者用データベース更新処理(productテーブル)
//	public static void UpdateProductData2(ArrayList<ProductTableModel> List){
//		Connection conn = null;
//		ProductTableModel ptmodel = null;
//		PreparedStatement st = null;
//		try {
//			conn = new ResourceFinder().getConnectionpuser();
//			for(int i = 0; i < List.size(); i++) {
//				ptmodel = (ProductTableModel)List.get(i);
//				st = conn.prepareStatement("update product set shohin_mei=?, tanka=?, zaiko=?, shashin=? where shohin_id=?");
//				st.setString(1, ptmodel.getShohin_mei());
//				st.setInt(2, ptmodel.getTanka());
//				st.setInt(3, ptmodel.getZaiko());
//				st.setString(4, ptmodel.getShashin());
//				st.setInt(5, ptmodel.getShohin_id());
//				st.executeUpdate();
//				st.close();
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				conn.close();
//			}catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}
