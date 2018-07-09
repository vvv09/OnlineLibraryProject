<%@page import="com.valunskii.libraryproject.beans.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- хедер и футер подключены в web.xml -->
<%@include file="../WEB-INF/jspf/left_menu.jspf"%>

<%request.setCharacterEncoding("UTF-8");

    long genreId = 0L;

    try{
        genreId = Long.valueOf(request.getParameter("genre_id"));
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<jsp:useBean id="bookList" class="com.valunskii.libraryproject.beans.BookList" scope="page"/>

<div class="book_list">
    <h3>${param.name}</h3>


    <%
        ArrayList<Book> list = bookList.getBooksByGenre(genreId);
        session.setAttribute("currentBookList", list);
        for (Book book : list) {

    %>

    <div class="book_info">
        <div class="book_title">
            <p> <%=book.getName()%></p>
        </div>
        <div class="book_image">
            <img src="<%=request.getContextPath()%>/ShowImage?index=<%=list.indexOf(book) %>" height="250" width="190" alt="Обложка"/>
        </div>
        <div class="book_details">
            <br><strong>ISBN:</strong> <%=book.getIsbn()%>
            <br><strong>Издательство:</strong> <%=book.getPublisher() %>

            <br><strong>Количество страниц:</strong> <%=book.getPageCount() %>
            <br><strong>Год издания:</strong> <%=book.getPublishYear() %>
            <br><strong>Автор:</strong> <%=book.getAuthor() %>
            <p style="margin:10px;"> <a href="#">Читать</a></p>
        </div>
    </div>


    <%}%>



</div>

