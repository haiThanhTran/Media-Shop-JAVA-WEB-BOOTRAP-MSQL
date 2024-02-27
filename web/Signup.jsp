<%-- 
    Document   : Login.jsp
    Created on : Feb 26, 2024, 8:28:54 AM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Untitled</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <style>
            /* CSS cho nội dung cần ẩn đi */
            .hidden-content {
                animation: hideContent 1s forwards;
                font-size: 1.5rem;
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

            .text-danger{
                font-size: 0.8rem;
            }
            
            p{
                font-size: 0.8rem;
                color: #6f7a85;
            }
        </style>
    </head>

    <body>

        <div class="spinner"></div>
        <div class="register-photo hidden-content">
            <div class="form-container">
                <div class="image-holder">

                </div>
                <form method="post" action="signup">
                    <h2 class="text-center"><strong>Create</strong> an account.</h2>
                    <p class="text-danger">${errM}</p>
                    <div class="form-group"><input class="form-control" type="acount" name="user" placeholder="Acount"></div>
                    <p>Username must be at least 3 characters long.</p>
                    <div class="form-group"><input class="form-control" type="password" name="pass" placeholder="Password"></div>
                    <p>At least one uppercase letter,number,at least 3 characters.</p>
                    <div class="form-group"><input class="form-control" type="password" name="confirmPass" placeholder="Password (repeat)"></div>
                    <div class="form-group">
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox" name="agree" value="yes" >I agree to the license terms.
                            </label>
                        </div>
                    </div>
                    <div class="form-group"><button class="btn btn-primary btn-block" type="submit" >Sign Up</button></div>
                    <a href="Login1.jsp" class="already" >You already have an account? Login here.</a>
                </form>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
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
