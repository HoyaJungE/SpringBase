<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">

<title>메인화면</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/js/common1.js'/>" charset="utf-8"></script>

<link href="<c:url value="/css/board.css"/>" rel="stylesheet">
<link href="<c:url value="/css/goods.css"/>" rel="stylesheet">
<link href="<c:url value="/css/btn.css"/>" rel="stylesheet"> 

<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<head>

<style>


</style>

</head>
<body>

<br>
<div align="center">
<ul class="bxslider">
      <li><a href="#"><img src="../base/img/제이스타일5.png" alt="" ></a></li>
      <li><a href="#"><img src="../base/img/제이스타일1.png" ></a></li>
      <li><a href="#"><img src="../base/img/제이스타일2.png" alt="" ></a></li>
      <li><a href="#"><img src="../base/img/제이스타일3.png" alt="" ></a></li>
      <li><a href="#"><img src="../base/img/제이스타일4.png" alt="" ></a></li>
</ul>
</div>

<div align="center">
	<h1 class="mTitle">NEW</h1>
</div>

<div id="main-container">
<table class="New_list" style="width:100%">
		<colgroup>
			<col width="100%" />
		</colgroup>
		<thead>
			<tr>
			</tr>
		</thead>
		<tbody>
		</tbody>
		</table>



</div>


<div align="center">
	<h1 class="mTitle">BEST</h1>
</div>

<div id="main-container">
<table class="Best_list" style="width:100%">
		<colgroup>
			<col width="100%" />
		</colgroup>
		<thead>
			<tr>
			</tr>
		</thead>
		<tbody>
		</tbody>
		</table>



</div>



<form id="commonForm" name="commonForm"></form>

</body>
</html>

<script type="text/javascript">

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

fn_selectNewItemList(1);
fn_selectBestItemList(1);


$(document).ready(function () {
    $('.bxslider').bxSlider({
        auto: true, // 자동으로 애니메이션 시작
        speed: 500,  // 애니메이션 속도
        pause: 3000,  // 애니메이션 유지 시간 (1000은 1초)
        mode: 'horizontal', // 슬라이드 모드 ('fade', 'horizontal', 'vertical' 이 있음)
        autoControls: false, // 시작 및 중지버튼 보여짐
        pager: true, // 페이지 표시 보여짐
        captions: true, // 이미지 위에 텍스트를 넣을 수 있음
        slideWidth: 1260, // 크기
        slideMargin: 0,
        autoDelay: 0,
        responsive: true,
        controls : true,      
    });

    $("a[name='title']").on("click", function(e){ //제목 //name 이 title인거
    	console.log("잘들어옴");
    	e.preventDefault();
    	fn_openBoardDetail($(this));
    });
    
});

function fn_openBoardDetail(obj) {
	console.log("잘들어옴22");
	var comSubmit = new ComSubmit(); // 객체생성
	comSubmit.setUrl("<c:url value='/shop/goodsDetail.do' />"); // url설정
	comSubmit.addParam("IDX", obj.parent().find("#IDX").val()); // IDX; id로 값을넘김
	comSubmit.submit();
	
}


		/* 최신상품 아이템 */
function fn_selectNewItemList(pageNo) {
	var comAjax = new ComAjax();
	comAjax.setUrl("<c:url value='/mainList.do' />");
	comAjax.setCallback("fn_selectNewItemListCallback");
	comAjax.addParam("PAGE_INDEX", pageNo);
	comAjax.addParam("PAGE_ROW", 16);
	comAjax.ajax();
}

function fn_selectNewItemListCallback(data) {
	var total = data.TOTAL;
	var body = $(".New_list");
	body.empty();
	
	if (total == 0) {
		var str = "<tr>" + "<td colspan='4'>조회된 결과가 없습니다.</td>"
				+ "</tr>";
		body.append(str);
	} else {
		var params = {
			divId : "PAGE_NAVI",
			pageIndex : "PAGE_INDEX",
			totalCount : total,
			recordCount : 16,
			eventName : "fn_selectNewItemList"
		};
		gfn_renderPaging(params);

		var str = "";
		$.each(data.NewList, function(key, value) {
							var imgpath = "<img src='/base/file/"+value.GOODS_THUMBNAIL+"' width='400' height='400'>"

							var imgpath1 = value.GOODS_IMAGE_STD.split(',');
							var img0 = imgpath1[0];
							var img1 = imgpath1[1];
							
							var Pick = value.GOODS_PICK.split(',');

							var pick1 = "";
							var pick2 = "";
							var pick3 = "";
							var pick4 = "";
							var num = "";
						    for (var i=0; i<Pick.length; i++) {
									Pick[i];
									if(Pick[0] == null){
										pick1 = "";
									}else{
										pick1 = Pick[0];
									}
									if(Pick[1] == null){
										pick2 = "";
									}else {
										pick2 = Pick[1];
									}
									if(Pick[2] == null){
										pick3 = "";
									}else {
										pick3 = Pick[2];
									}
									if(Pick[3] == null){
										pick4 = "";
									}else{
										pick4 = Pick[3];
									}
								} 
							
							
							str +=    "<div class='card'>"
								+	  "<a href='#this' name='title'>"
								+	  "<div class='imgswap'>"
								+ 	  "<img src='/base/file/"+img0+"' width='400' height='400'>" 
								+ 	  "<img src='/base/file/"+img1+"' width='400' height='400'>"
								+     " </div> "
								+     " <c:if test='${"+num+" ne "+pick1+"}'> "
								+ 	  " <span style='background-color:#ff80bf; line-height: 27px; border-radius: 10px;'><font color='#ffffff' size='2'> "
								+		pick1 +"</font></span>"
								+	  " </c:if>"
								+     " <c:if test='${"+num+" ne "+pick2+"}'> "
								+     " <span style='background-color:#d456dc; line-height: 27px; border-radius: 10px;'><font color='#ffffff' size='2'> "
								+	  	pick2 + "</font></span>"
								+	  " </c:if>"
								+     " <c:if test='${"+num+" ne "+pick3+" }'> "
								+     " <span style='background-color:#33b7ff; line-height: 27px; border-radius: 10px;'><font color='#ffffff' size='2'> "  
								+       pick3 + "</font></span>"
								+	  " </c:if>"
								+     " <c:if test='${"+num+" ne "+pick4+" }'> "
								+     " <span style='background-color:#33b7ff; line-height: 27px; border-radius: 10px;'><font color='#ffffff' size='2' "  
								+       pick4 + "</font></span>"
								+	  " </c:if> <br>"
								+	  " <font class='font1'>"+value.GOODS_NAME+"</font><br>"
								+     " <font class='font2'>"+numberWithCommas(value.GOODS_SELL_PRICE)+"원</font> "   
								+  	  " <input type='hidden' id='IDX' name='IDX' value=" + value.GOODS_NO + ">"
								+	  " </a>"
								+	  " </div>";

						});
		body.append(str);
		$("a[name='title']").on("click", function(e){ //제목 
			e.preventDefault();
			fn_openBoardDetail($(this));
		});

		
	}
}


		/* 베스트 아이템 */

function fn_selectBestItemList(pageNo) {
	var comAjax = new ComAjax();
	comAjax.setUrl("<c:url value='/mainList.do' />");
	comAjax.setCallback("fn_selectBestItemListCallback");
	comAjax.addParam("PAGE_INDEX", pageNo);
	comAjax.addParam("PAGE_ROW", 28);
	comAjax.ajax();
}

function fn_selectBestItemListCallback(data) {
	var total = data.TOTAL1;
	var body = $(".Best_list");
	body.empty();
	
	if (total == 0) {
		var str = "<tr>" + "<td colspan='4'>조회된 결과가 없습니다.</td>"
				+ "</tr>";
		body.append(str);
	} else {
		var params = {
			divId : "PAGE_NAVI",
			pageIndex : "PAGE_INDEX",
			totalCount : total,
			recordCount : 28,
			eventName : "fn_selectBestItemList"
		};
		gfn_renderPaging(params);

		var str = "";
		$.each(data.BestList, function(key, value) {
							var imgpath = "<img src='/base/file/"+value.GOODS_THUMBNAIL+"' width='400' height='400'>";

							var imgpath1 = value.GOODS_IMAGE_STD.split(',');
							var img0 = imgpath1[0];
							var img1 = imgpath1[1];
							
							var Pick = value.GOODS_PICK.split(',');
							var pick1 = "";
							var pick2 = "";
							var pick3 = "";
							var pick4 = "";
							var num = "";
							for (var i=0; i<Pick.length; i++) {
									Pick[i];
									if(Pick[0] == null){
										pick1 = "";
									}else{
										pick1 = Pick[0];
									}
									if(Pick[1] == null){
										pick2 = "";
									}else {
										pick2 = Pick[1];
									}
									if(Pick[2] == null){
										pick3 = "";
									}else {
										pick3 = Pick[2];
									}
									if(Pick[3] == null){
										pick4 = "";
									}else{
										pick4 = Pick[3];
									}
								}
							
							
									
							
							str += "<div class='card'>"
								+		"<a href='#this' name='title'>"
								+		"<div class='imgswap'>"
								+ 		"<img src='/base/file/"+img0+"' width='400' height='400'>" 
								+ 		"<img src='/base/file/"+img1+"' width='400' height='400'>"
								+     " </div> "
								+     " <c:if test='${"+num+" ne "+pick1+"}'> "
								+ 	  " <span style='background-color:#ff80bf; line-height: 27px; border-radius: 10px;'><font color='#ffffff' size='2'> "
								+		pick1 +"</font></span>"	
								+	  " </c:if>"
								+     " <c:if test='${"+num+" ne "+pick2+"}'> "
								+     " <span style='background-color:#d456dc; line-height: 27px; border-radius: 10px;'><font color='#ffffff' size='2'> "
								+		pick2 + "</font></span>"
								+	  " </c:if>"
								+     " <c:if test='${"+num+" ne "+pick3+" }'> "
								+     " <span style='background-color:#33b7ff; line-height: 27px; border-radius: 10px;'><font color='#ffffff' size='2'> "  
								+       pick3 + "</font></span>"
								+	  " </c:if>"
								+     " <c:if test='${"+num+" ne "+pick4+" }'> "
								+     " <span style='background-color:#33b7ff; line-height: 27px; border-radius: 10px;'><font color='#ffffff' size='2'> "  
								+       pick4 + "</font></span>"
								+	  " </c:if> <br>"
								+	  " <font class='font1'>"+value.GOODS_NAME+"</font><br>"
								+     " <font class='font2'>"+numberWithCommas(value.GOODS_SELL_PRICE)+"원</font> "   
								+  	  " <input type='hidden' id='IDX' name='IDX' value=" + value.GOODS_NO + ">"
								+	  " </a>"
								+	  " </div>";

						});
		body.append(str);
		$("a[name='title']").on("click", function(e){ //제목 
			e.preventDefault();
			fn_openBoardDetail($(this));
		});
	}
}
</script>