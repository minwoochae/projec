package DAO;

import java.sql.*;
import java.util.ArrayList;

import DTO.Movie;

public class MovieDAO {
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	//db 연결 메소드
	public Connection open() {
			Connection conn = null;
		try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(JDBC_URL, "test", "test1234"); //db 연결
		} catch (Exception e) {
				e.printStackTrace();
			}
			
		return conn; //데이터 베이스의 연결 객체를 리턴
	}
		
	//게시판 리스트 가져오기
	public ArrayList<Movie> getList() throws Exception{
		Connection conn = open();
		ArrayList<Movie> MovieList = new ArrayList<>();
		
		
		
		String sql = "select * from Movie";
		PreparedStatement pstmt = conn.prepareStatement(sql); //쿼리문 등록
		ResultSet rs = pstmt.executeQuery(); //쿼리문으로 실행
		
		try (conn; pstmt; rs){
			while(rs.next()){
				Movie m = new Movie();
				
				m.setMovie_no(rs.getInt("movie_no"));
				m.setTitle(rs.getString("title"));
				m.setUser_id(rs.getString("user_id"));
				m.setReg_date(rs.getString("reg_date"));
				m.setViews(rs.getInt("views"));
				m.setImg(rs.getString("img"));
				m.setContent(rs.getString("content"));
				MovieList.add(m);
				
				}
			}
		
		return MovieList;
}	
		
	//게시판 내용 가져오기
	public Movie getView(int movie_no) throws Exception {
			Connection conn = open();
			Movie m = new Movie();
			
			String sql = "select * from Movie where movie_no = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movie_no);
			ResultSet rs = pstmt.executeQuery();
			
			try(conn; pstmt; rs) {
				while(rs.next()) {
					m.setMovie_no(rs.getInt("movie_no"));
					m.setTitle(rs.getString("title"));
					m.setUser_id(rs.getString("user_id"));
					m.setReg_date(rs.getString("reg_date"));
					m.setViews(rs.getInt("views"));
					m.setContent(rs.getString("content"));
					m.setImg(rs.getString("img"));
					
					
					
				}
				return m;
			}
	}
	
	//조회수 증가
	public  void updateViews(int movie_no) throws Exception {
		Connection conn = open();
		String sql = "update Movie set views = (views + 1 ) where movie_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setInt(1, movie_no);
			pstmt.executeUpdate();
			
			
		}
	}
	
	
	//게시글 등록
	public void insertMovie(Movie m) throws Exception {
		Connection conn = open();
		String sql = "insert into Movie(movie_no, user_id, title, content, reg_date, views, img) "
				+ " values(Movie_seq.nextval, ? , ?, ? , sysdate, 0 , ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try (conn; pstmt) {
			pstmt.setString(1, m.getUser_id());
			pstmt.setString(2, m.getTitle());
			pstmt.setString(3, m.getContent());
			pstmt.setString(4, m.getImg());
			
			pstmt.executeUpdate();
		}
	}
	
	public Movie  getViewForEdit(int movie_no) throws Exception {
		Connection conn = open();
		Movie m = new Movie();
		
		String sql= "select movie_no, title, user_id, to_char(reg_date, 'yyyy.mm.dd') reg_date , views, content, img from Movie where movie_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, movie_no);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt){		
				while(rs.next()) {
					m.setMovie_no(rs.getInt("movie_no"));
					m.setTitle(rs.getString("title"));
					m.setUser_id(rs.getString("user_id"));
					m.setReg_date(rs.getString("reg_date"));
					m.setViews(rs.getInt("views"));
					m.setContent(rs.getString("content"));
					m.setImg(rs.getString("img"));
					
				}
			}
			return m;
			
		}
	
	
	//게시글 수정화면 보여주기
	public void updateMovie(Movie m) throws Exception {
		Connection conn = open();
		String sql = "update Movie set title = ?, user_id=?, content = ?, img= ? "
				+ " where movie_no =?" ;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setString(1, m.getTitle() );
			pstmt.setString(2, m.getUser_id());
			pstmt.setString(3, m.getContent());
			pstmt.setString(4, m.getImg());
			pstmt.setInt(5, m.getMovie_no());
			
			//수정된 글이 없을 경우
			if(pstmt.executeUpdate() != 1) {
				throw new Exception("수정된 글이 없습니다.");
			}
			}
		}
	
	//게시글 삭제하게
	public void deleteMovie(int movie_no) throws Exception {
		Connection conn = open();
		String sql = "delete from movie where movie_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setInt(1, movie_no);
			if(pstmt.executeUpdate() != 1) {
				throw new Exception("삭제된 글이 없습니다.");
			}
		
		}
	}
		
		
		
}
