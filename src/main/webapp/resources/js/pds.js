$('#pdregbtn').on('click', function () {
    if ($('#title').val() == '') {
        alert('제목을 입력하세요!!');
        $('#title').focus();

    } else if ($('#userid').val() == '') {
        alert('작성자를 입력하세요!!');
        $('#userid').focus();

    } else if ($('#contents').val() == '') {
        alert('본문을 입력하세요!!');
        $('#contents').focus();

    } else {
        $('#pdsfrm').submit();
    }
}); // 새글등록

$('#newpds').on('click', function () {
    location.href = '/pds/write.do';
}); // 새글쓰기

$('#lstpds').on('click', function () {
    location.href = '/pds/list.do';
}); // 목록으로

$('#pdnobtn').on('click', function () {
    location.href = '/pds/list.do';
}); // 취소하기

$('#deltpds').on('click', function () {
    location.href =
        '/pds/delete.do?pno=${param.pno}';
}); // 삭제하기

$('#updpds').on('click', function () {
    location.href =
        '/pds/update.do?pno=${param.pno}';
}); // 수정하기