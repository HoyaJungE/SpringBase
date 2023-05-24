<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/template/header.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/ui.css'/>" />
<!-- jQuery -->
<script src="<c:url value='/js/jquery-3.7.0.min.js'/>" charset="utf-8"></script>
</head>
<body>

<div class="header">
<div class="pagetop">

<div align="right" style="margin-right:300px">
<form name="frm">
		<tr>
			<c:set var="MEMBER_NO" value="${SESSION_NO }" />
			<c:choose>
				<c:when test="${MEMBER_NO eq null}">
					<td><a href="/base/loginForm.do">로그인</a></td> <td> | </td>
					<td><a href="/base/joinForm.do">회원가입</a></td> <td> | </td>
					<td><a href="/base/loginForm.do">마이페이지</a></td> <td> | </td>
					<td><a href="/base/event/list.do">이벤트</a></td> <td> | </td>
				</c:when>
				<c:otherwise>
					<td>Hi, ${SESSION_NAME }님!</td> <td> | </td>
					<td><a href="#" onClick="signOut();">로그아웃</a></td> <td> | </td>
					<td><a href="/base/myOrderList.do">마이페이지</a></td> <td> | </td>
					<td><a href="/base/basket/basketList.do">장바구니</a></td> <td> | </td>
					<td><a href="/base/event/list.do">이벤트</a></td> <td> | </td>
					<td><a href="/base/menu/menuList.do">메뉴관리</a></td> <td> | </td>
				</c:otherwise>
			</c:choose>
			
			
			<td><a href="/base/faq/openFaqList.do">고객센터</a></td> <td> | </td>
		</tr>
</form>
</div>
</div>


<!-- 검색 -->
<div style="margin-left:23%;">
<form method="post" action="/base/shop/openMainSearch.do" id="searchbox5">
	<input id="search51" name="keyword" type="text" size="40" placeholder="#원피스 #가디건 #데일리" value="${keyword1}"/>
</form>
</div>

<!-- 메뉴 -->
<div class="pagemid">
  <div class="wrapper2">
    <I><h1><a href="/base/main.do">base</a></h1></I>
    <ul class="flex-menu">
     <!--  <li><a href="#">서비스소개</a></li>
      <li><a href="/base/shop/bestGoodsList.do">BEST</a></li>
      <li><a href="/base/shop/newGoodsList.do">NEW</a></li>
      <li><a href="/base/shop/goodsList/outer/NewItem.do">OUTER</a></li>
      <li><a href="/base/shop/goodsList/top/NewItem.do">TOP</a></li>
      <li><a href="/base/shop/goodsList/one-piece/NewItem.do">ONE-PIECE</a></li>
      <li><a href="/base/shop/goodsList/bottom/NewItem.do">BOTTOM</a></li>
      <li><a href="/base/shop/goodsList/acc/NewItem.do">ACC</a></li> -->
    </ul>
  </div>
</div>

</div>

<script>
	function signOut() {
		var auth2 = gapi.auth2.getAuthInstance();
		var data = {};
		$.ajax({
			type : "POST",
			url : "base/logout.do",
			data : JSON.stringify(data), 
			dataType : "json",   
			contentType:"application/json;charset=UTF-8",
		    async: false,
			success : function(data, status, xhr) {
				console.log(data);
				if (auth2 != null && auth2 != "undefined") {
					auth2.signOut().then(function() {
						console.log('User signed out.');
						if (!Kakao.Auth.getAccessToken()) {
							  console.log('Not logged in.');
							  return;
						}
						Kakao.Auth.logout(function() {
						  alert(Kakao.Auth.getAccessToken());
						}); 
					});
				}
		 		
					location.href=data.URL;
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("에러가 발생했습니다.");
			}
		});

	}
	function onLoad() {
		gapi.load('auth2', function() {
			gapi.auth2.init();
		});
	}
	
	$(document).ready(function() {
        fn_selectHeaderMenuList();         
    });

    function fn_selectHeaderMenuList() {
   		var body = $(".flex-menu");
   		$.ajax({
 			url : "<c:url value='/menu/selectMenuListData.do' />",    
 			type : "POST",   
 			data : this.param,
 			async : false, 
 			success : function(data, status) {
 				var menuStr = "";
 				$.each(data.menuList, function(key, value){
 					menuStr += '<li>';
 					menuStr += '<a href="/base' + value.menuUrl + '">' + value.menuName + '</a>';
 					menuStr += '</li>';
 	            });
 				
 				body.append(menuStr);
 			}
    	});
    }
</script>
<script src = "//developers.kakao.com/sdk/js/kakao.min.js"></script>
</body>
</html>