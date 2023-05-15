<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://tiles.apache.org/tags-tiles"%>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/uii.css'/>" />

<!-- jQuery -->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/js/commonn.js'/>" charset="utf-8"></script>

<style>
.menulist{
	text-align: center;
}

</style>
</head>

<body>
   <br />
   <div class="pagemid">
      <div class="wrapper3">
         <ul class="flex-menu">
            <li><a href="/base/board/faq/openFaqList.do">FAQ</a></li>
            <li><a href="/base/board/notice/openNoticeList.do">공지사항</a></li>
            <li><a href="/base/board/menu/openMenuList.do">QNA</a></li>
         </ul>
         <br>
         <div class="bar"></div>
      </div>
   </div>

   <table class="menulist">
      <colgroup>
         <col width="10%" />
         <col width="*" />
         <col width="20%" />
         <col width="5%" />
         <col width="20%" />
         <col width="10%" />
         <col width="10%" />
      </colgroup>
      <thead>
         <tr>
            <th scope="col">메뉴ID</th>
            <th scope="col">제목</th>
            <th scope="col">작성자 아이디</th>
            <th scope="col">순서</th>
            <th scope="col">메뉴URL</th>
            <th scope="col">작성일</th>
            <th scope="col">수정일</th>
         </tr>
      </thead>
      <tbody>

      </tbody>
   </table>

   <form id="commonForm" name="commonForm"></form>
   
   <a href="/base/menu/menuInsertPage.do">메뉴추가</a>

	<script type="text/javascript">
      $(document).ready(function() {
         fn_selectMenuList();         
      });

      function fn_selectMenuList() {
    	  var body = $("table>tbody");
    	  var str = "";
    	  $.ajax({
  			url : "<c:url value='/menu/selectMenuListData.do' />",    
  			type : "POST",   
  			data : this.param,
  			async : false, 
  			success : function(data, status) {
  				$.each(data.menuList, function(key, value){
  	               str += '<tr class="list' + value.menuId + '">';
  	               str += '<td>';
  	               str += value.menuId;
  	               str += '</td>';
  	               str += '<td>';
  	               str += '<a href="<c:url value="/menu/menuDetail.do"/>?menuId='+ value.menuId +'">' + value.menuName + '<a/>';
  	               str += '</td>';
  	               str += '<td>';
	               str += value.writerId;
	               str += '</td>';
	               str += '<td>';
  	               str += value.ordr;
  	               str += '</td>';
  	               str += '<td>';
	               str += value.menuUrl;
	               str += '</td>';
	               str += '<td>';
  	               str += value.insrtDt;
  	               str += '</td>';
  	               str += '<td>';
	               str += value.updtDt;
	               str += '</td>';
  	               str += '</tr>';
  	            });
  				
  				body.append(str);
  			}
    	  });
      }
   </script>
</body>
</html>