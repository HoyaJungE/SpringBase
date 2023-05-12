<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://tiles.apache.org/tags-tiles"%>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/uii.css'/>" />

<!-- jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/js/commonn.js'/>" charset="utf-8"></script>
</head>

<style>

a {
  text-decoration: none;
  color: #666;
  text-decoration:none
}

h1 {
    text-align: center;
    padding: 50px 0;
    font-weight: normal;
    font-size: 2em;
    letter-spacing: 10px;
}  
</style>
<body>
	<h2>메뉴상세</h2>
	<form id="frm" name="frm" enctype="multipart/form-data">
		<table class="board_view">
			<colgroup>
				 <col width="10%" />
		         <col width="*" />
		         <col width="20%" />
		         <col width="5%" />
		         <col width="20%" />
		         <col width="10%" />
		         <col width="10%" />
			</colgroup>
			<caption>메뉴상세</caption>
			<tbody>
				<tr>
					<th scope="row">메뉴아이디</th>
					<td>
						${map.menuId}
						<input type="hidden" id="menuId" name="menuId" value="${map.menuId}">
					</td>
				</tr>
				<tr>
					<th scope="row">메뉴명</th>
					<td colspan="3">
						<input type="text" id="menuName" name="menuName" value="${map.menuName}">
					</td>
				</tr>
				<tr>
					<th scope="row">작성자</th>
					<td>
						<input type="text" id="writerId" name="writerId" value="${map.writerId}">
					</td>
					<th scope="row">작성시간</th>
					<td>${map.insrtDt}</td>
				</tr>
				<tr>
					<th scope="row">메뉴순서</th>
					<td colspan="3">
						<input type="text" id="ordr" name="ordr" value="${map.ordr}">
					</td>
				</tr>
				<tr>
					<th scope="row">메뉴URL</th>
					<td colspan="3">
						<input type="text" id="menuUrl" name="menuUrl" value="${map.menuUrl}">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<br />

	<a href="#this" class="btn" id="list">목록으로</a>
	<a href="#this" class="btn" id="update">수정하기</a>

	<form id="commonForm" name="commonForm"></form>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#list").on("click", function(e) { //목록으로 버튼
				e.preventDefault();
				fn_openMenuList();
			});

			$("#update").on("click", function(e) { //수정하기 버튼
				e.preventDefault();
				fn_openMenuUpdate();
			});

		});

		function fn_openMenuList() {
			var comSubmit = new ComSubmit();
			comSubmit.setUrl("<c:url value='/menu/menuList.do' />");
			comSubmit.submit();
		}

		function fn_openMenuUpdate() {
			var comSubmit = new ComSubmit("frm");
			comSubmit.setUrl("<c:url value='/menu/menuUpdate.do' />");
			comSubmit.submit();
		}
	</script>
</body>
</html>