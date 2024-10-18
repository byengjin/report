<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../includes/header.jsp"%>
<div class="row-content">
  <div class="card">
    <div class="card-header">
      Featured
    </div>
    <div class="card-body">
      <h5 class="card-title">게시판 수정</h5>
      <form method="post" action="/board/modify">
        <input type="hidden" name="bno" value="${dto.bno}">
        <div class="mb-3">
          <label class="form-label">Title</label>
          <input type="text" class="form-control" name="title" value="${dto.title}">
        </div>
        <div class="mb-3">
          <label class="form-label">Example textarea</label>
          <textarea class="form-control" name="content" rows="3">${dto.content}</textarea>
        </div>
        <div class="mb-3">
          <label class="form-label">Writer</label>
          <input type="text" class="form-control" name="writer" value="${dto.writer}">
        </div>
        <div>
          <button type="submit" class="btn btn-primary">Modify</button>
          <button type="button" class="btn btn-secondary">Board List</button>
        </div>
      </form>
      <script>
        document.querySelector(".btn-secondary").addEventListener("click", function (e){
          self.location="/board/list"
        })
      </script>

    </div>
  </div>
</div>

<%@include file="../includes/footer.jsp"%>