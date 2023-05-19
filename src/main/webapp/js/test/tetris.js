const BLOCKS = {
    tree: [
        [[1,0],[0,1],[1,1],[2,1]],
        [[1,0],[0,1],[1,1],[1,2]],
        [[2,1],[0,1],[1,1],[1,2]],
        [[2,1],[1,2],[1,1],[1,0]],
    ],
    square: [
        [[0,0],[0,1],[1,0],[1,1]],
        [[0,0],[0,1],[1,0],[1,1]],
        [[0,0],[0,1],[1,0],[1,1]],
        [[0,0],[0,1],[1,0],[1,1]],
    ],
    bar: [
        [[1,0],[2,0],[3,0],[4,0]],
        [[2,-1],[2,0],[2,1],[2,2]],
        [[1,0],[2,0],[3,0],[4,0]],
        [[2,-1],[2,0],[2,1],[2,2]],
    ],
    zee: [
        [[0,0],[1,0],[1,1],[2,1]],
        [[0,1],[1,0],[1,1],[0,2]],
        [[0,1],[1,1],[1,2],[2,2]],
        [[2,0],[2,1],[1,1],[1,2]],
    ],
    elLeft: [
        [[0,0],[0,1],[1,1],[2,1]],
        [[1,0],[1,1],[1,2],[0,2]],
        [[0,1],[1,1],[2,1],[2,2]],
        [[1,0],[2,0],[1,1],[1,2]],
    ],
    elRight: [
        [[1,0],[2,0],[1,1],[1,2]],
        [[0,0],[0,1],[1,1],[2,1]],
        [[0,2],[1,0],[1,1],[1,2]],
        [[0,1],[1,1],[2,1],[2,2]],
    ]
}
// DOM
const playground = document.querySelector(".playground > ul");
const gameText = document.querySelector(".game-text");
const scoreDisplay = document.querySelector(".score");
const restartButton = document.querySelector(".game-text > button");
// console.log(playground);

// setting
const GAME_ROWS = 20;
const GAME_COLS = 10;

// variables
let score = 0; // 점수를 보여줌 => score 오타가 있었다. 오타 주의
let duration = 500; // 블럭이 떨어지는 시간
let downInterval; // 초기 null 값
let tempMovingItem; // 무빙을 실질적으로 실행하기전 전 담아두는 변수
let isFalling = false;

// const BLOCKS = {
//     tree: [
//         [[2,1],[0,1],[1,0],[1,1]],
//         [[1,2],[0,1],[1,0],[1,1]],
//         [[1,2],[0,1],[2,1],[1,1]],
//         [[2,1],[1,2],[1,0],[1,1]],
//         // 한 번더 돌리면 4개의 모양이 움직임
//     ],
//     square: [
//         [[2,1],[0,1],[1,0],[1,1]],
//         [[1,2],[0,1],[1,0],[1,1]],
//         [[1,2],[0,1],[2,1],[1,1]],
//         [[2,1],[1,2],[1,0],[1,1]],
//     ]
// }
// 적다 보면 코드 양이 많아지기 때문에 다른 파일로 저장한다

const movingItem = {
    type: "",
    direction: 3, // 방향키 역할
    top: 0, // 좌표 기준
    left: 0, // 좌우값
};
// 실질적으로 다음 블럭의 타입과 좌표와 정보를 가지고 있는 변수
// 주기적으로 바꾼 값을 원복 시키기 위해 템플무빙아이템을 바꾼 후에 맞는지 아닌 지 본 후에 바꾼다

init()

// functions
// 처음 시작이 되는 함수 이름 init
function init() {
    // blockArray.forEach(block => {
    //     console.log(block[0])
    // })
    tempMovingItem = {...movingItem}; 
    // 스프레드 오퍼레이터 이 안의 값만 가져와서 넣는 것, 껍데기 안 내용만 바뀜

    for (let i = 0; i < GAME_ROWS; i++) {
        prependNewLine()
    }
    generateNewBlock()
}


function prependNewLine() {
    const li = document.createElement("li");
    const ul = document.createElement("ul");
    // ul과 li 요소 자바스크립트의 api로 만들 수 있다
    for (let j = 0; j < GAME_COLS; j++) {
        const matrix = document.createElement("li");
        ul.prepend(matrix);
    }
    li.prepend(ul);
    playground.prepend(li);
    // Element.prepend() 메서드는 Node 개체 또는 문자열 개체 집합을 Element의 첫 번째 자식 앞에 삽입합니다. 문자열 개체는 동등한 텍스트 노드로 삽입됩니다.
}

function renderBlocks(moveType = "") {
    const{type, direction, top, left} = tempMovingItem; // 디스트럭셔닝
    const movingBlocks = document.querySelectorAll(".moving");
    movingBlocks.forEach(moving => {
        moving.classList.remove(type, "moving");
        // console.log(moving)
    })
    // BLOCKS[type][direction].forEach(block => {
    BLOCKS[type][direction].some(block => {
        const x = block[0] + left;
        const y = block[1] + top;
        // console.log({playground});
        // const target = playground.childNodes[y].childNodes[0].childNodes[x];
         //  삼합 연산자 const 변수 = 조건 ? 참일경우 : 거짓일 경우
        const target = playground.childNodes[y] ? playground.childNodes[y].childNodes[0].childNodes[x] : null;
        const isAvailable = checkEmpty(target);
        // console.log(target);
        if(isAvailable) {
            target.classList.add(type, "moving")
        } else {
            tempMovingItem = {...movingItem}
            if(moveType === 'retry') {
                clearInterval(downInterval)
                showGameoverText()
            }
            setTimeout(() => {
                    renderBlocks('retry');
                    if(moveType === "top") {
                        seizeBlock();
                }
                // renderBlocks();
                // 이벤트 스텍이 넘쳐버리는 것을 방지
            }, 0)
            // renderBlocks();
            return true; 
            // 빈 값 있을 때 리턴 트루 쓴다
            // 중간에 멈추고 싶을 때 forEach함수 보다는 some 사용히면 반복문 중지 됨
        }
    })
    movingItem.left = left;
    movingItem.top = top;
    movingItem.direction = direction;
    // 바꾸기 전에 {...movingItem} 으로 다시 집어넣고, 다시 한번 더 renderBlocks(); 호출 => 멕시멈 콜 스택 사이즈 엑시디드 => 이벤트를 계속해서 호출하고 있는 상황이 됨
}
function seizeBlock() {
    // console.log("seize block")
    const movingBlocks = document.querySelectorAll(".moving");
    movingBlocks.forEach(moving => {
        moving.classList.remove("moving");
        moving.classList.add("seized");
        // console.log(moving)
    })
    checkMatch()
    // generateNewBlock()
}

function checkMatch() {
    
    const childNodes = playground.childNodes;
    childNodes.forEach(child => {
        let matched = true;
        child.children[0].childNodes.forEach(li => {
            if (!li.classList.contains("seized")) {
                matched = false;
            }
        })
        if (matched) {
            child.remove();
            prependNewLine();
            score++;
            scoreDisplay.innerText = score;
        }
    })

    generateNewBlock()
}
function generateNewBlock() { 
        // console.log(BLOCKS.length) // BLOCKS는 오브젝트이기 떄문에 콘솔창에 undefined 가 뜬다
    // console.log(Object.entries(BLOCKS).length)
    // const randomIndex = Math.floor(Math.random() * 6)
    clearInterval(downInterval);
    downInterval = setInterval(()=>{
        moveBlock('top',1)
    },duration)
    const blockArray = Object.entries(BLOCKS);
    const randomIndex = Math.floor(Math.random() * blockArray.length)
    // movingItem.type = ""
    movingItem.type = blockArray[randomIndex][0]
    movingItem.top = 0;
    movingItem.left = 3;
    movingItem.direction = 0;
    tempMovingItem = {...movingItem};
    renderBlocks()
}

function checkEmpty(target) {
    if (!target || target.classList.contains("seized")) {
        return false;
    }
    return true;
}
function moveBlock(moveType, amount) {
    tempMovingItem[moveType] += amount;
    renderBlocks(moveType);
}
function changeDirection() {
    const direction = tempMovingItem.direction;
    direction === 3 ? tempMovingItem.direction = 0 : tempMovingItem.direction += 1;
    // tempMovingItem.direction += 1;
    // if(tempMovingItem.direction === 4) {
    //     tempMovingItem.direction === 0;
    // }
    renderBlocks();
}

function dropBlock() {
    clearInterval(downInterval);
    downInterval = setInterval(()=> {
        moveBlock('top', 1)
    }, 10)
}
function showGameoverText () {
    gameText.style.display = "flex "
}

// event handling
document.addEventListener("keydown", e => {
    switch(e.keyCode) {
        case 39:
             moveBlock("left",+1);
            break;
        case 37:
            moveBlock("left",-1);
            break;
        case 40:
            moveBlock("top", 1);
            break;
        case 38:
            changeDirection();
            break;
        case 32:
            dropBlock();
            break;
        default:
            break;
    }
    // console.log(e)
})

restartButton.addEventListener("click", () => {
    playground.innerHTML = "";
    scoreDisplay.innerText = "0"; // 다시 시작 버튼 누르면 점수값도 초기화 한다
    gameText.style.display="none";
    init();
})