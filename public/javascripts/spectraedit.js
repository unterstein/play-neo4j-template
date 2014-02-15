$(function() {
  $(".datetimepicker").each(function() {
    $(this).datetimepicker({
      pickSeconds: false,
      defaultDate: new Date(),
      format: $(this).find(":input").data("format")
    });
  });
});