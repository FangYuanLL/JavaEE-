package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Books;
import beans.Page;
import dao.LibraryDAO;

/**
 * Servlet implementation class Administrator
 */
public class Administrator extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private  int page=1;
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LibraryDAO library=new LibraryDAO();
		Page newpage=new Page();
    	
    	if(request.getParameter("page")!=null)
    	{
    		page=Integer.parseInt(request.getParameter("page"));
    	}
    	else {
    		page=1;
    	}
    	newpage.setPageNow(page);//���õ�ǰҳ��
    	newpage.setTotalBooks(library.Count());//�����鼮����������
    	
    	int beginIndex=newpage.BeginIndex();//����ÿһ��ҳ���һ�����ݵļ�¼
    	int booksPerPage=newpage.getBooksPerPage();
    	int total=newpage.totalPages();
		List<Books> list = library.listAllOf(beginIndex, booksPerPage);
		
        //System.out.println(page+1);
        request.setAttribute("list", list);
        request.setAttribute("newpage", newpage);
        request.setAttribute("total", total);
		RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
    	
   }

