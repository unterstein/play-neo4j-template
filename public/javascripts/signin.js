$(function () {
  var loginUrl = $(":input[name='loginUrl']").val();
  var registerUrl = $(":input[name='registerUrl']").val();
  var url = $("#registerRow").data("url");
  if (loginUrl == url && registerUrl != url) {
    $(":input[name='loginUrl']").val(registerUrl);
  }
  if (registerUrl == url && loginUrl != url) {
    $(":input[name='registerUrl']").val(loginUrl);
  }
});