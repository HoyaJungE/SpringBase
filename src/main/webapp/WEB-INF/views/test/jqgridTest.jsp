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

<script src="<c:url value='/js/jquery-3.7.0.min.js'/>" charset="utf-8"></script>
<script src="<c:url value='/js/commonn.js'/>" charset="utf-8"></script>
<script src="<c:url value='/js/jqgrid/i18n/grid.locale-en.js'/>" charset="utf-8"></script>
<script src="<c:url value='/js/jqgrid/jquery.jqGrid.js'/>" charset="utf-8"></script>
<script src="<c:url value='/js/jquery-ui/jquery-ui.min.js'/>" charset="utf-8"></script>



<script type="text/javascript">
	//﻿샘플 데이터
	var dataArray = [
		{id:"1",invdate:"2007-10-01",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"2",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"3",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
		{id:"4",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"5",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"6",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
		{id:"7",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"8",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"9",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
		{id:"10",invdate:"2007-10-01",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"11",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"12",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
		{id:"13",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"14",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"15",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
		{id:"16",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		{id:"17",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		{id:"18",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}
	];
	
$(document).ready(function(){

	$("#jqGridTable").jqGrid({			// grid id (<table>)
		datatype: 'local',				// 데이터 전송방식 (xml, json, local)
		data: dataArray,				// datatype이 local일 경우만
		// url:		// datatype이 xml, json 경우만, 서버에서 데이터를 가져오는 경우
		// mtype: 'POST',				// datatype이 xml, json 경우만, 서버에서 데이터 요청 타입 POST, GET 가능
		// loadonce: true,				// datatype이 xml, json 경우만, 서버에서 데이터를 가져오는 경우
										// sort 옵션 또는 search 사용 시 loadonce가 true 인 경우 서버 호출 X
										// 서버에서 데이터를 재로드해야 하는 경우 loadonce false
		//postData:{name:'temp'},	// datatype이 xml, json 경우, 요청 URL Params 정의
		height: 250,					
		//width:'auto',					// 그리드 전체 넓이를 cel width로 조정
		autowidth:true,				// 넓이 조절 여부, window width 로 조절, cell 넓이도 자동 조절(width 옵션과 동시 사용 X) 
		loadtext : '로딩중...',
        colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'],	
		// th에 들어갈 컬럼명, colModel 에서 label 속성으로 대체 가능
		colModel:[
			{name:'id',index:'id', width:60, sorttype:"int", key : true},				
			{name:'invdate',index:'invdate', width:90, sorttype:"date"},
			{name:'name',index:'name', width:100},
			{name:'amount',index:'amount', width:80, align:"right",sorttype:"float"},
			{name:'tax',index:'tax', width:80, align:"right",sorttype:"float"},		
			{name:'total',index:'total', width:80,align:"right",sorttype:"float"},		
			{name:'note',index:'note', width:150, sortable:false}
			// name : 화면에 보여줄 data key, index : data 정렬시 사용할 key
			// width : cell 넓이, aligh : 정렬 ( left, right, center )
			// sortable : cell 정렬 옵션 사용여부 (boolean), sorttype: datatype 이 local 일때 데이터 정렬 타입 정의
			// key : key cell 로 사용 여부(boolean), 미지정 시 grid에서 제공되는 random id 사용
			// title : cell title 옵션 (default = true), hidden : cell 표시 여부 (boolean)
			// cellattr : cell attr 추가 시 사용될 function 정의
			// formatter : cell에 표시할 데이터의 형식으로 데이터 타입 또는 function 정의
			// resizable :  컬럼 사이즈를 조절 여부 (boolean)
		],
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
		rowNum:5,							// 화면에 보여줄 row의 수, -1의 경우 unlimit 
											// 단, 데이터가 많을 경우 화면을 load 하는데 시간 오래 걸림
		rowList:[5,10,15],                  // 화면에서 볼 수 있는 row의 수를 조절 가능
		pager:'#jqGridPaging',              // 페이징관련 데이터를 보여줄 grid id (<div>)
		caption: "Manipulating Array Data"	// grid 상단 캡션
	});

});
</script>
</head>

<body>
	<h3>jqGrid 테스트</h3>
	<div>
		<table id="jqGridTable"></table>
	</div>
	<div id ="jqGridPaging">
	</div>
</body>
</html>