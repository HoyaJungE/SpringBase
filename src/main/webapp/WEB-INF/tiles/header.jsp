<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id" content="840345488051-t7d9q5tg8he8kt3om4dmlovpjom64m3q.apps.googleusercontent.com">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/template/header.css'/>" />
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
   <I><h1><a href="/base/main.do">J STYLE</a></h1></I>
    <ul class="flex-menu">
      <li>&nbsp;</li>
      <li><a href="#">서비스소개</a></li>
      <li><a href="/base/shop/bestGoodsList.do">BEST</a></li>
      <li><a href="/base/shop/newGoodsList.do">NEW</a></li>
      <li><a href="/base/shop/goodsList/outer/NewItem.do">OUTER</a></li>
      <li><a href="/base/shop/goodsList/top/NewItem.do">TOP</a></li>
      <li><a href="/base/shop/goodsList/one-piece/NewItem.do">ONE-PIECE</a></li>
      <li><a href="/base/shop/goodsList/bottom/NewItem.do">BOTTOM</a></li>
      <li><a href="/base/shop/goodsList/acc/NewItem.do">ACC</a></li>
    </ul>
    <br>
    <div class="bar">
    </div>
  </div>
</div>

</div>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>

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
        fn_selectMenuList();         
     });

     function fn_selectMenuList() {
   	  var body = $(".flex-menu");
   	  var str = "";
   	  $.ajax({
 			url : "<c:url value='/menu/selectMenuListData.do' />",    
 			type : "POST",   
 			data : this.param,
 			async : false, 
 			success : function(data, status) {
 				$.each(data.menuList, function(key, value){
 	               str += '<li>';
 	               str += '<a href="' + value.MENU_URL + '>' + value.MENU_ID;
 	               str += '</li>';
 	               str += '<li>';
 	               str += value.MENU_NAME;
 	               str += '</li>';
 	               str += '<li>';
	               str += value.WRITER_ID;
	               str += '</li>';
	               str += '<li>';
 	               str += value.ORDR;
 	               str += '</li>';
	               str += '<li>';
 	               str += value.INSRT_DT;
 	               str += '</li>';
 	               str += '<li>';
	               str += value.UPDT_DT;
	               str += '</li>';
 	            });
 				
 				body.append(str);
 			}
   	  });
     }
</script>
<script src = "//developers.kakao.com/sdk/js/kakao.min.js"></script>
</body>
</html>