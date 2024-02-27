<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="entity.Product"%>
<link href="css/style.css" rel="stylesheet" ></link>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            /* CSS cho nội dung cần ẩn đi */
            .hidden-content {
                animation: hideContent 1s forwards;
                
            }

            @keyframes hideContent {
                from {
                    opacity: 0;
                }
                to {
                    opacity: 1;
                }
            }

            /* CSS để tạo hiệu ứng xoay xoay */
            @keyframes spin {
                0% {
                    transform: rotate(0deg);
                }
                100% {
                    transform: rotate(360deg);
                }
            }

            .spinner {
                border: 8px solid #f3f3f3; /* Màu nền của spinner */
                border-top: 8px solid #3498db; /* Màu của vòng trên cùng */
                border-radius: 50%;
                width: 60px;
                height: 60px;
                animation: spin 2s linear infinite; /* Áp dụng animation spin */
                position: fixed;
                top: 50%;
                left: 50%;
                margin-top: -60px; /* Để canh giữa theo chiều dọc */
                margin-left: -60px; /* Để canh giữa theo chiều ngang */
                z-index: 9999; /* Đảm bảo spinner ở trên cùng */
            }

            /* Ẩn spinner sau khi tải xong trang */
            body.loaded .spinner {
                display: none;
            }
        </style>
    </head>
    <body>
        <div class="spinner"></div>

        <div class="hidden-content">
            <jsp:include page="Menu.jsp"></jsp:include>
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
                                    <li class="breadcrumb-item"><a href="home">Category</a></li>
                                    
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
                <div class="container ">
                    <div class="row">
                    <jsp:include page="Left.jsp"></jsp:include>
                        <div class="col-sm-9">
                            <div class="row">
                            <%
                                ArrayList<Product> list = (ArrayList<Product>)request.getAttribute("listP");
                                if (list == null || list.isEmpty()) {
                            %>
                            <p class="card-title show_txt">Không có sản phẩm!</p>
                            <%
                                } else {
                                    for (Product s : list) {
                            %>
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <img class="card-img-top" src="<%=s.getImage()%>" alt="Card image cap">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?pid=<%=s.getId()%>" title="View Product"><%=s.getName()%></a></h4>
                                        <p class="card-text show_txt"><%=s.getTitle()%></p>
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-danger btn-block"><%=s.getPrice()%></p>
                                            </div>
                                            <div class="col">
                                                <a href="#" class="btn btn-success btn-block">Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% 
                                }
                            } 
                            %>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="Footer.jsp"></jsp:include>

        </div>

    </body>
     <script>
        // Khi trang được tải xong, gán class "loaded" vào body để ẩn spinner
        window.addEventListener('load', function () {
            setTimeout(function () {
                document.body.classList.add('loaded');
                document.querySelector('.hidden-content').classList.add('hidden');
            }, 1000); // Trì hoãn ẩn đi spinner và hiển thị nội dung trong 1000 miligiây (1 giây)
        });
    </script>
</html>
