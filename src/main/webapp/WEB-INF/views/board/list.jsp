<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 10. 11.
  Time: 오후 3:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../includes/header.jsp"%>

<div class="row-content">
  <div class="card">
    <div class="card-body">
      <h5 class="card-title">검색</h5>

      <form action="/board/list" method="get">
        <input type="hidden" name="size" value="${pageRequestDTO.size}">
        <input type="hidden" name>

        <div class="mb-3">
          <input type="text"  name="keyword" class="form-control" value="${pageRequestDTO.keyword}">
        </div>
        <div class="input-group mb-3">
          <div class="float-end">
            <button class="btn btn-primary" type="submit">Search</button>
            <button class="btn btn-info clearBtn" type="reset">Clear</button>
          </div>
        </div>
      </form>

    </div>
  </div>
</div>
<div class="row-content">
  <div class="card">
    <div class="card-header">
      Featured
    </div>
    <div class="card-body">
      <h5 class="card-title">Special title treatment</h5>
      <table class="table">
        <thead>
        <tr>
          <th scope="col">Bno</th>
          <th scope="col">Title</th>
          <th scope="col">Content</th>
          <th scope="col">Writer</th>
          <th scope="col">postdate</th>
          <th scope="col">visitCount</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${responseDTO.dtoList}" var="dto">
          <tr>
            <th scope="row">${dto.bno}</th>
            <td><a href="/board/read?bno=${dto.bno}&${pageRequestDTO.link}">${dto.title}</a></td>
            <td>${dto.content}</td>
            <td>${dto.writer}</td>
            <td>${dto.postdate}</td>
            <td>${dto.visitCount}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>

      <nav aria-label="...">
        <ul class="pagination">
          <c:if test="${responseDTO.prev}">
            <li class="page-item">
              <a class="page-link" data-num="${responseDTO.start-1}">Previous</a>
            </li>
          </c:if>
          <c:forEach begin="${responseDTO.start}" end="${responseDTO.end}" var="num">
            <li class="page-item ${responseDTO.page==num? "active":""}">
              <a class="page-link" data-num="${num}">${num}</a>
            </li>
          </c:forEach>
          <c:if test="${responseDTO.next}">
            <li class="page-item">
              <a class="page-link" data-num="${responseDTO.end+1}">Next</a>
            </li>
          </c:if>
        </ul>
      </nav>
      <script>

        document.querySelector(".pagination").addEventListener("click", function (e){
          e.preventDefault()
          e.stopPropagation()
          const target=e.target
          if(target.tagName !== 'A'){
            return
          }
          const num=target.getAttribute("data-num")
          const formObj=document.querySelector("form");
          formObj.innerHTML+=`<input type='hidden' name='page' value='\${num}'>`
          formObj.submit()
          //    self.location=`/board/list?page=\${num}&\${pageRequestDTO.link}`
        },false)
        document.querySelector(".clearBtn").addEventListener("click", function (e){
          e.preventDefault()
          e.stopPropagation()

          self.location ='/board/list'

        },false)
      </script>
    </div>
  </div>
</div>
<%@include file="../includes/footer.jsp"%>
