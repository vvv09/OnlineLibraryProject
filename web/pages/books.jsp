<%@page import="com.valunskii.libraryproject.beans.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.valunskii.libraryproject.enums.SearchType" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- хедер и футер подключены в web.xml -->
<%@include file="../WEB-INF/jspf/left_menu.jspf"%>

<jsp:useBean id="bookList" class="com.valunskii.libraryproject.beans.BookList" scope="page"/>

<%@include file="../WEB-INF/jspf/letters.jspf"%>


<div class="book_list">


    <%--<h3>${param.name}</h3>--%>


    <%
        ArrayList<Book> list = null;

        if (request.getParameter("genre_id") != null) {
            long genreId = Long.valueOf(request.getParameter("genre_id"));
            list = bookList.getBooksByGenre(genreId);
        } else if (request.getParameter("letter") != null) {
            String letter = request.getParameter("letter");
            list = bookList.getBooksByLetter(letter);
        } else if (request.getParameter("search_string") != null) {
            String searchStr = request.getParameter("search_string");
            SearchType searchType = SearchType.TITLE;
            if (request.getParameter("search_option").equals("Автор")) {
                searchType = SearchType.AUTHOR;
            }
            if (searchStr != null && !searchStr.trim().equals("")) {
                list = bookList.getBooksBySearch(searchStr, searchType);
            }
        }
    %>

    <h5 style="text-align: left; margin-top: 20px;">Найдено книг: <%=list.size()%> </h5>
    <%
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

