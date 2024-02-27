<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList" %>
<%@page import="entity.Category"%>
<%@page import="entity.Product"%>
<div class="col-sm-3">
    <div class="card bg-light mb-3">
        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
        <ul class="list-group category_block">
            <c:forEach items="${listC}" var="c">
                <li class="list-group-item text-white ${(flag != null && flag == c.getCid()) ? "active":""}">
                    <a href="category?cid=${c.getCid()}">${c.getCname()}</a>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="card bg-light mb-3">
        <div class="card-header bg-success text-white text-uppercase">Last product</div>
        <div class="card-body">
            <img class="img-fluid" src="${listNP.image}" />
            <h5 class="card-title">${listNP.name}</h5>
            <p class="card-text">${listNP.description}</p>
            <p class="bloc_left_price">${listNP.price}vnđ</p>
        </div>
    </div>
</div>
