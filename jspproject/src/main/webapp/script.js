function chkForm(){
	var f = document.frm; //form 태그
	
	if(frm.title.value ==''){ //name이 title인 input 태그의 입력된 값을 가져온다.
		alert("제목을 입력해주세요.");
		return false; //return문을 만나면 함수가 끝난다.
	}
	
	if(frm.id.value ==''){ //name이 title인 input 태그의 입력된 값을 가져온다.
		alert("아이디을 입력해주세요.");
		return false;
	}
	if(frm.content.value ==''){ //name이 title인 input 태그의 입력된 값을 가져온다.
		alert("글을 입력해주세요.");
		return false;
	}
	
	f.submit(); //폼태그 전송
}

function chkDelete(movie_no){
	const result = confirm("삭제하시겠습니까");
	
	if(result) {
		const url = location.origin;
		location.href = url + "/jspproject/delete?movie_no=" + movie_no;
		"/delete";
		
	}  else{
		false;
	}
	
	
}

