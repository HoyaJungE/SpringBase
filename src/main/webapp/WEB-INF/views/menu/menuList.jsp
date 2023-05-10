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
</head>

<body>
   <br />
   <div class="pagemid">
      <div class="wrapper3">
         <ul class="flex-menu">
            <li><a href="/base/faq/openFaqList.do">FAQ</a></li>
            <li><a href="/base/notice/openNoticeList.do">공지사항</a></li>
            <li><a href="/base/menu/openMenuList.do">QNA</a></li>
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
         <col width="20%" />
      </colgroup>
      <thead>
         <tr>
            <th scope="col">메뉴ID</th>
            <th scope="col">제목</th>
            <th scope="col">작성자 아이디</th>
            <th scope="col">순서</th>
            <th scope="col">작성일</th>
            <th scope="col">수정일</th>
         </tr>
      </thead>
      <tbody>

      </tbody>
   </table>

   <form id="commonForm" name="commonForm"></form>

	<script type="text/javascript">
      $(document).ready(function() {
         fn_selectMenuList(1);         
      });

      function fn_selectMenuList(pageNo) {
    	  var body = $("table>tbody");
    	  var str = "";
    	  $.ajax({
  			url : "<c:url value='/menu/selectMenuListData.do' />",    
  			type : "POST",   
  			data : this.param,
  			async : false, 
  			success : function(data, status) {
  				$.each(data.menuList, function(key, value){
  	               str += '<tr class="list' + value.MENU_ID + '">';
  	               str += '<td>';
  	               str += value.MENU_ID;
  	               str += '</td>';
  	               str += '<td>';
  	               str += value.MENU_NAME;
  	               str += '</td>';
  	               str += '<td>';
	               str += value.WRITER_ID;
	               str += '</td>';
	               str += '<td>';
  	               str += value.ORDR;
  	               str += '</td>';
	               str += '<td>';
  	               str += value.INSRT_DT;
  	               str += '</td>';
  	               str += '<td>';
	               str += value.UPDT_DT;
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