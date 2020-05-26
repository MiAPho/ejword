package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateDB {
	public static void main(String[] args) {
		try {
			FileInputStream fis=new FileInputStream("assets/ejdic-hand-utf8.txt");
			InputStreamReader isr=new InputStreamReader(fis,"utf-8");
			BufferedReader br=new BufferedReader(isr);
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/ejword?useUnicode=true&characterEncoding=utf8";
			String user = "root";
			String pass = "root";
			Connection db = DriverManager.getConnection(url, user, pass);
			db.setAutoCommit(false);
			PreparedStatement ps=db.prepareStatement("INSERT INTO words(title,body) VALUES(?,?)");
			String line;
			while((line=br.readLine())!=null) {
				String[] vals=line.split("\t");
				ps.setString(1, vals[0]);
				ps.setString(2, vals[1]);
				ps.executeUpdate();
			}
			db.commit();
			db.close();
			br.close();
			//時間がかかる処理なのでコンソールで終了を伝える。
			System.out.println("done!");
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
