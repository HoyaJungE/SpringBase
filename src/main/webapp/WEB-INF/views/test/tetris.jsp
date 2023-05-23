<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://tiles.apache.org/tags-tiles"%>
<link rel="stylesheet" type="text/css"href="<c:url value='/css/uii.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/menu/menu.css'/>" />

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>테트리스</title>
    <style>
    * {margin: 0; padding: 0;}

	li {list-style: none;}
	
	body {height:100%; overflow: hidden;}
	
	.game-text {
	    /* display: flex; */
	    display: none;
	    justify-content: center;
	    align-items: center;
	    flex-direction: column; 
	    position: fixed;
	    width: 100%;
	    height: 100%;
	    background: rgba(0,0,0,0.7);
	    left: 0;
	    top: 0;
	    color: #fff;
	    font-size: 50px;
	}
	
	.game-text > button {
	    margin-top: 0.5rem;
	    padding: 0.5rem 1rem;
	    color: #fff;
	    background: salmon;
	    border: none;
	    cursor: pointer;
	}
	
	.score {text-align: center; font-size: 36px;}
	
	.playground > ul {border: 1px solid #333; width: 250px; margin-bottom: 2rem; margin: 0 auto;}
	.playground > ul >li {width: 100%; height: 25px;}
	.playground > ul >li > ul {display: flex;}
	.playground > ul >li > ul > li {width: 25px; height: 25px; outline: 1px solid #ccc;}
	.tree {background:#67c23a;}
	.bar {background:salmon;}
	.zee {background:#e6a23c;}
	.elLeft{background: #8e44ad;}
	.elRight{background:#16a085;}
	.square{background:deepskyblue;}
    </style>
</head>
<body>
    <div class="wapper">
        <div class="game-text">
            <span>게임 종료</span>
            <button>다시 시작</button>
        </div>
        <div class="score">0</div>
        <div class="playground">
            <ul></ul>
        </div>
    </div>
    <script src="<c:url value='/js/test/tetris.js'/>" type="module" charset="utf-8"></script> 
    <!-- 스크립트 불러올 때 임포트문 쓸려면  웹팩같은 번들러 라이브러리 쓸 수 있다-->
</body>
</html>