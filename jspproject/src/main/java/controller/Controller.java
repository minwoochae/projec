package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import DAO.MovieDAO;
import DTO.Movie;

@WebServlet("/")
@MultipartConfig(maxFileSize = 1024 * 1024 * 2, location = "c:/Temp/img")

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieDAO dao; // model
	private ServletContext ctx; // 페이지 이동, forward를 위해 사용한다.

	public Controller() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new MovieDAO();
		ctx = getServletContext(); // getServletContext: 웹 어플리케이션 자원 관리

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글 깨짐 방지
		String command = request.getServletPath(); // 경로를 가지고 온다.
		String site = null;

		System.out.println("command: " + command);
		// 1. 경로를 정해준다(라우팅)
		switch (command) {
		case "/list":
			site = getList(request);
			break;
		case "/view":
			site = getView(request);
			break;
		case "/write":
			site = "write.jsp";
			break; // 글쓰는 화면을 보여준다
		case "/insert":
			site = insertMovie(request);
			break;
		case "/edit":
			site = getViewForEdit(request);
			break;
		case "/update":
			site = updateMovie(request); break;
		case "/delete":
			site = deleteMovie(request); break;
		}
			
		

		if (site.startsWith("redirect:/")) { // redirect
			// redirect 경로만 잘라온다
			String rview = site.substring("redirect:/".length());
			System.out.println(rview);

			response.sendRedirect(rview); // 페이지 이동

		} else {
			ctx.getRequestDispatcher("/" + site).forward(request, response);
		}

	}

	public String getList(HttpServletRequest request) {
		ArrayList<Movie> list;

		try {
			list = dao.getList();

			request.setAttribute("movieList", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시판 목록 생성 과정에서 문제 발생");
			request.setAttribute("error", "게시판 목록이 정상적으로 처리되지 않았습니다");
		}

		return "index.jsp";
	}

	public String getView(HttpServletRequest request) {
		int movie_no = Integer.parseInt(request.getParameter("movie_no"));

		try {
			dao.updateViews(movie_no);
			Movie m = dao.getView(movie_no);
			request.setAttribute("movie", m);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "게시글을  정상적으로 가져오지 못했습니다");
		}
		return "view.jsp";
	}

	public String insertMovie(HttpServletRequest request) {
		Movie m = new Movie();

		try {
			BeanUtils.populate(m, request.getParameterMap());
			// 1. 이미지 파일 자체를 서버 컴퓨터에 저장
			Part part = request.getPart("file"); // 이미지파일 받기
			String fileName = getFilename(part); // 파일 이름구하기

			if (fileName != null && !fileName.isEmpty()) {
				part.write(fileName); // 파일을 컴퓨터에 저장한다.
			}
			// 2. 이미지 파일 이름에 "/img/결로를 붙여서 Movie 객체에 저장"
			m.setImg("/img/" + fileName);
			dao.insertMovie(m);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 등록 과정에서 문제 발생");
			
			try {
				//get방식으로 넘길때 한글 깨짐 방지
				String encodeName = URLEncoder.encode("게시물이 정상적으로 등록되지 않았습니다.", "UTF-8");
				return "redirect:/:list?error=" + encodeName;
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}

		return "redirect:/list";
	}

	public String getViewForEdit(HttpServletRequest request) {
		int movie_no = Integer.parseInt(request.getParameter("movie_no"));

		try {
			Movie m = dao.getViewForEdit(movie_no);
			request.setAttribute("movie", m);

		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "게시글을  정상적으로 가져오지 못했습니다");
		
		}

		return "edit.jsp";
	}
	
	private String updateMovie(HttpServletRequest request) {
		Movie m = new Movie();
		try {
			BeanUtils.populate(m, request.getParameterMap());
			
			Part part = request.getPart("file"); // 이미지파일 받기
			String fileName = getFilename(part); // 파일 이름구하기

			if (fileName != null && !fileName.isEmpty()) {
				part.write(fileName); // 파일을 컴퓨터에 저장한다.
			}
			m.setImg("/img/" + fileName);
			dao.updateMovie(m);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 작성과정에서 문제 발생");
			
			try {
				//get방식으로 넘길때 한글 깨짐 방지
				String encodeName = URLEncoder.encode("게시물이 정상적으로 등록되지 않았습니다.", "UTF-8");
				return "redirect:/view?movie_no=" + m.getMovie_no() + "&error=" + encodeName;
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		
		return "redirect:/view?movie_no=" + m.getMovie_no(); 
	}
	
	public String deleteMovie(HttpServletRequest request) {
		int movie_no = Integer.parseInt(request.getParameter("movie_no"));
		try {
			dao.deleteMovie(movie_no);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("게시글을 삭제되는 과정에서 문제 발생");
		
		try {
			//get방식으로 넘길때 한글 깨짐 방지
			String encodeName = URLEncoder.encode("게시물이 정상적으로 삭제되지 않았습니다.", "UTF-8");
			return "redirect:/:list?error=" + encodeName;
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}
		
		return "redirect:/list";
	}
	
	
	private String getFilename(Part part) {
		String fileName = null;
		String header = part.getHeader("content-disposition");
		System.out.println("header =>" + header);

		int start = header.indexOf("filename=");
		fileName = header.substring(start + 10, header.length() - 1);
		System.out.println("파일명 " + fileName);

		return fileName;
	}

}
