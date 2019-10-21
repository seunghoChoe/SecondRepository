<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
    <link href="/resources/css/board/postList.css" rel="stylesheet">
    <title>명지대학교 융합소프트웨어학부 : 게시글 목록</title>
</head>

<div id="postList" class="container">
    <div id="postListHeader" class="container">
        <h4>자유 게시판</h4>
        <p>자유 게시판</p>
    </div>

    <br/>

    <div id="postListBody" class="container">
        <table class="table table-hover">
            <thead>
            <tr id="postListItemHeader">
                <th id="postId" style="width: 10%"><strong>번호</strong></th>
                <th id="postTitle" style="width: 46%"><strong>제목</strong></th>
                <th id="postUserId" style="width: 15%"><strong>작성자</strong></th>
                <th id="postCreatedAt" style="width: 15%"><strong>작성일</strong></th>
                <th id="postLike" style="width: 7%"><strong>좋아요</strong></th>
                <th id="postView" style="width: 7%"><strong>조회수</strong></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="post" items="${postList}">
                <tr class="postListItem" onclick="location.href='/board/post?postId=${post.postId}'">
                    <td>${post.postId}</td>
                    <c:if test="${post.postComment ne 0}">
                        <td style="text-align: left">${post.postTitle} [${post.postComment}]</td>
                    </c:if>
                    <c:if test="${post.postComment eq 0}">
                        <td style="text-align: left">${post.postTitle}</td>
                    </c:if>
                    <td>${post.postUserId}</td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${post.postCreatedAt}"/></td>
                    <td>${post.postLike}</td>
                    <td>${post.postView}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%-- 페이징 --%>
<div id="postListPageNation" class="container">
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">

            <c:if test="${searchOption eq null && searchKeyword eq null}">
                <spring:url var="previousPageUrl" value="${baseUrl}?page=${pageMaker.startPage - 1}"/>
            </c:if>
            <c:if test="${searchOption ne null && searchKeyword ne null}">
                <spring:url var="previousPageUrl" value="${baseUrl}?page=${pageMaker.startPage - 1}&searchOption=${searchOption}&searchKeyword=${searchKeyword}"/>
            </c:if>
            <c:if test="${pageMaker.previous}">
                <li class="page-item">
                    <a class="page-link" aria-label="Previous" href="${previousPageUrl}">
                        <span aria-hidden="true"><i class="fa fa-angle-double-left" aria-hidden="true"></i></span>
                    </a>
                </li>
            </c:if>

            <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="pageNumber">
                <c:if test="${searchOption eq null && searchKeyword eq null}">
                    <spring:url var="pageUrl" value="${baseUrl}?page=${pageNumber}"/>
                </c:if>
                <c:if test="${searchOption ne null && searchKeyword ne null}">
                    <spring:url var="pageUrl" value="${baseUrl}?page=${pageNumber}&searchOption=${searchOption}&searchKeyword=${searchKeyword}"/>
                </c:if>
                <c:choose>
                    <c:when test="${currentPage eq pageNumber}">
                        <li class="page-item active">
                            <a class="page-link" href="${pageUrl}">${pageNumber}</a>
                        </li>
                    </c:when>
                    <c:when test="${currentPage ne pageNumber}">
                        <li class="page-item">
                            <a class="page-link" href="${pageUrl}">${pageNumber}</a>
                        </li>
                    </c:when>
                </c:choose>
            </c:forEach>

            <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                <li class="page-item">
                    <c:if test="${searchOption eq null && searchKeyword eq null}">
                        <spring:url var="nextPageUrl" value="${baseUrl}?page=${pageMaker.endPage + 1}"/>
                    </c:if>
                    <c:if test="${searchOption ne null && searchKeyword ne null}">
                        <spring:url var="nextPageUrl" value="${baseUrl}?page=${pageMaker.endPage + 1}&searchOption=${searchOption}&searchKeyword=${searchKeyword}"/>
                    </c:if>
                    <a class="page-link" aria-label="Next" href=${nextPageUrl}>
                        <span aria-hidden="true"><i class="fa fa-angle-double-right" aria-hidden="true"></i></span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

<div id="postListFooter" class="container">
    <div id="postListFooterSearch" class="container">
        <form name="searchOption" method="get" action="/board/postList">
            <li id="liSearchOption">
                <div>
                    <table id="layoutTable">
                        <tr>
                            <td>
                                <select class="form-control"  name="searchOption">
                                    <option value="post_title">제목</option>
                                    <option value="post_content">내용</option>
                                    <option value="post_user_id">작성자</option>
                                </select>
                            </td>
                            <td>&nbsp&nbsp&nbsp&nbsp</td>
                            <td>
                                <div class="form-group">
                                    <input class="form-control" name="searchKeyword" id="searchInputField" type="text" value="${searchKeyword}">
                                </div>
                            </td>
                            <td>
                                <input id="searchButton" type="submit" class="btn btn-warning" value="검색">
                            </td>
                        </tr>
                    </table>
                </div>
            </li>
        </form>
    </div>

    <div id="postListFooterButton" class="d-flex bd-highlight mb-3">
        <button id="postListPostButton" type="button" class="btn btn-primary" onclick="location.href='/board/newPost'"><strong><i class="fas fa-edit"></i> 글쓰기</strong></button>
    </div>
</div>

<c:if test="${serverMessage != null}">
    <script>
        var message = "${serverMessage}";
        alert(message)
    </script>
</c:if>