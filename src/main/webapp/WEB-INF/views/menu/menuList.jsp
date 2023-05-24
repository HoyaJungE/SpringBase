<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://tiles.apache.org/tags-tiles"%>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/uii.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/menu/menu.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery-ui/jquery-ui.min.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jqgrid/ui.jqgrid.css'/>" />

<script src="<c:url value='/js/commonn.js'/>" charset="utf-8"></script>
<script src="<c:url value='/js/jqgrid/i18n/grid.locale-en.js'/>" charset="utf-8"></script>
<script src="<c:url value='/js/jqgrid/jquery.jqGrid.js'/>" charset="utf-8"></script>
<script src="<c:url value='/js/jquery-ui/jquery-ui.min.js'/>" charset="utf-8"></script>


<script type="text/javascript">
   $(document).ready(function() {
		fn_selectMenuList();
	});

   function setLink(cellValue, options, rowData){
	   return "<a href='/base/menu/menuDetail.do?menuId=" + rowData.menuId + "'>" + cellValue + "<a/>";
   }
   
   function fn_selectMenuList() {
 	  var body = $("table>tbody");
 	  var str = "";
 	  $.ajax({
		url : "<c:url value='/menu/selectMenuListData.do' />",    
		type : "POST",   
		data : this.param,
		async : false, 
		success : function(data, status) {
			$("#list").jqGrid({
				datatype: "local",
				data: data.menuList,
				loadtext : 'loading...',
				colNames:['메뉴ID', '제목', '작성자아이디','순서','URL','작성일', '수정일'],
				colModel:[
					{name:'menuId', index:'menuId', width:90, align: "center"},
					{name:'menuName', index:'menuName', width:100 , align: "center", formatter:setLink },
					{name:'writerId', index:'writerId', width:150, align: "center" },
					{name:'ordr', index:'ordr', width:80, align: "center"},
					{name:'menuUrl', index:'menuUrl', width:80, align: "center"},
					{name:'insrtDt', index:'insrtDt', width:80, align: "center"},
					{name:'updtDt', index:'updtDt', width:80, align: "center"},
					// name : 화면에 보여줄 data key, index : data 정렬시 사용할 key
					// width : cell 넓이, aligh : 정렬 ( left, right, center )
					// sortable : cell 정렬 옵션 사용여부 (boolean), sorttype: datatype 이 local 일때 데이터 정렬 타입 정의
					// key : key cell 로 사용 여부(boolean), 미지정 시 grid에서 제공되는 random id 사용
					// title : cell title 옵션 (default = true), hidden : cell 표시 여부 (boolean)
					// cellattr : cell attr 추가 시 사용될 function 정의
					// formatter : cell에 표시할 데이터의 형식으로 데이터 타입 또는 function 정의
					// resizable :  컬럼 사이즈를 조절 여부 (boolean)
				],
				autowidth:true,				// 넓이 조절 여부, window width 로 조절, cell 넓이도 자동 조절(width 옵션과 동시 사용 X) 
				multiselect: true,					// row checkbox 추가 옵션
				rownumbers: true,                   // row의 줄번호를 표시
				rownumWidth:40,						// 줄번호의 width
				gridview :true,
		        viewrecords: true,					// 그리드가 보여줄 총 페이지 현재 페이지등의 정보를 노출
				sortable: true,						// colmodel 에서 sortable 기능 사용 여부
				sortorder:"desc",					// 첫 로딩시 정렬 기준 (asc, desc)
				sortname:'name',					// 첫 로딩시 정렬 기준 컬럼
				shrinkToFit:true,					// 스크롤바 영역을 없애고 full로 화면 채움(default:true)
				scroll : false,						// (default:false)
				rowNum:10,							// 화면에 보여줄 row의 수, -1의 경우 unlimit 
													// 단, 데이터가 많을 경우 화면을 load 하는데 시간 오래 걸림
				rowList:[5,10,15],                  // 화면에서 볼 수 있는 row의 수를 조절 가능
				pager:'#jqGridPaging',              // 페이징관련 데이터를 보여줄 grid id (<div>)
				caption: "메뉴목록"	// grid 상단 캡션
			});
		}
 	  });
   }
</script>
</head>

<body>
   <table id="list"></table>
   <div id="jqGridPaging"></div>
   <form id="commonForm" name="commonForm"></form>
   <a href="/base/menu/menuInsertPage.do">메뉴추가</a>
</body>
</html>