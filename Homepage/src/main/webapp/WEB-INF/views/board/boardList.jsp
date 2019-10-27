<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <link href="/resources/css/boardList.css" rel="stylesheet">
    <title>명지대학교 융합소프트웨어학부 : 게시글 목록</title>

    <script>
        var path = "/board/boardLists?pageNum=";
        var path2 = "&cycle=";
        var first = 1;
        var second = 2;
        var third = 3;
        var fourth = 4;
        var fifth = 5;

        var cycle = 0;

        var add = 5;
        var sub = -5;

        $(document).ready(function () {

            cycle = "${cycle}";

            first = first + add * cycle;
            second = second + add * cycle;
            third = third + add * cycle;
            fourth = fourth + add * cycle;
            fifth = fifth + add * cycle;

            // #first 의 값을 페이지 번호로 변경, URL 링크 매핑
            document.getElementById('first').innerHTML = first;
            document.getElementById('second').innerHTML = second;
            document.getElementById('third').innerHTML = third;
            document.getElementById('fourth').innerHTML = fourth;
            document.getElementById('fifth').innerHTML = fifth;
            document.getElementById('first').setAttribute('href', path + first + path2 + cycle);
            document.getElementById('second').setAttribute('href', path + second + path2 + cycle);
            document.getElementById('third').setAttribute('href', path + third + path2 + cycle);
            document.getElementById('fourth').setAttribute('href', path + fourth + path2 + cycle);
            document.getElementById('fifth').setAttribute('href', path + fifth + path2 + cycle);
        });


        function backward() {
            if (cycle != 0) {
                cycle = cycle - 1;
                first = first + sub;
                second = second + sub;
                third = third + sub;
                fourth = fourth + sub;
                fifth = fifth + sub;

                document.getElementById('first').innerHTML = first;
                document.getElementById('second').innerHTML = second;
                document.getElementById('third').innerHTML = third;
                document.getElementById('fourth').innerHTML = fourth;
                document.getElementById('fifth').innerHTML = fifth;
                document.getElementById('first').setAttribute('href', path + first + path2 + cycle);
                document.getElementById('second').setAttribute('href', path + second + path2 + cycle);
                document.getElementById('third').setAttribute('href', path + third + path2 + cycle);
                document.getElementById('fourth').setAttribute('href', path + fourth + path2 + cycle);
                document.getElementById('fifth').setAttribute('href', path + fifth + path2 + cycle);
            } else {
                alert("더이상 뒤로 갈 수 없습니다.")
            }
        }

        function forward() {
            cycle = cycle + 1;

            first = first + add;
            second = second + add;
            third = third + add;
            fourth = fourth + add;
            fifth = fifth + add;

            document.getElementById('first').innerHTML = first;
            document.getElementById('second').innerHTML = second;
            document.getElementById('third').innerHTML = third;
            document.getElementById('fourth').innerHTML = fourth;
            document.getElementById('fifth').innerHTML = fifth;
            document.getElementById('first').setAttribute('href', path + first + path2 + cycle);
            document.getElementById('second').setAttribute('href', path + second + path2 + cycle);
            document.getElementById('third').setAttribute('href', path + third + path2 + cycle);
            document.getElementById('fourth').setAttribute('href', path + fourth + path2 + cycle);
            document.getElementById('fifth').setAttribute('href', path + fifth + path2 + cycle);
        }

        $(function () {
            var msg = "<c:out value="${msg}" />";
            if (msg != "") {
                alert(msg)
            }
        });
    </script>
</head>

<body>
<div id="mainWrapper">
    <ul>
        <!-- 게시판 제목 -->
        <li><h3>자유 게시판</h3></li>

        <!-- 게시글 목록 -->
        <li>
            <ul id="ulTable">
                <li>
                    <ul>
                        <li>번호</li>
                        <li>제목</li>
                        <li>작성자</li>
                        <li>작성일</li>
                        <li>조회수</li>
                        <li>좋아요</li>
                    </ul>
                </li>

                <!-- 게시글이 출력될 영역 -->
                <c:forEach items="${boardList}" var="boards">
                    <li>
                        <ul>
                            <li>${boards.boardIdx}</li>
                            <li><a href="${path}/board/boardDetail?boardIdx=${boards.boardIdx}">${boards.boardTitle}</a></li>
                            <li>${boards.boardAuthor}</li>
                            <li><fmt:formatDate value="${boards.boardCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
                            <li>${boards.boardHit}</li>
                            <li>${boards.boardLike}</li>
                        </ul>
                    </li>
                </c:forEach>
            </ul>
        </li>

        <!-- 게시글 페이징 영역 -->
        <li>
            <div id="divPaging">
                <div><a href="#" onclick="backward();">◀</a></div>
                <div><a class="pageBtn" id="first" name="first" href="" value=""></a></div>
                <div><a class="pageBtn" id="second" name="second" href="" value=""></a></div>
                <div><a class="pageBtn" id="third" name="third" href="" value=""></a></div>
                <div><a class="pageBtn" id="fourth" name="fourth" href="" value=""></a></div>
                <div><a class="pageBtn" id="fifth" name="fifth" href="" value=""></a></div>
                <div><a href="#" onclick="forward();">▶</a></div>
            </div>
        </li>

        <!-- 버튼 그룹 -->
        <li>
            <p id="buttonGroup">
                <c:if test="${loggedUser != null}">
                    <input type=button value="글쓰기" onclick="window.location ='/board/createBoard'">
                </c:if>
                <input type=button value="돌아가기" onclick="window.location ='/'">
            </p>
        </li>
    </ul>

    <!-- 검색 폼 영역 -->
    <form name="searchOption" method="get" action="/board/boardSearchedList">
        <li id="liSearchOption">
            <div>
                <table id="layoutTable">
                    <tr>
                        <td>
                            <select name="searchOption">
                                <option value="title">제목</option>
                                <option value="content">내용</option>
                                <option value="idx">번호</option>
                            </select>
                        </td>
                        <td>&nbsp&nbsp&nbsp&nbsp</td>
                        <td>
                            <input id="searchField" name="searchKeyword" value="${searchKeyword}">
                        </td>
                        <td>
                            <input id="submitBtn" type="submit" class="btn btn-dark" value="검색">
                        </td>
                    </tr>
                </table>
            </div>
        </li>
    </form>

</div>
</body>