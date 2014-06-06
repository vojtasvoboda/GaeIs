$(document).ready(function(){
  
    if ($('#form').length != 0) {
        $("#form").validate({
            invalidHandler: function(form, validator) {
                var errors = validator.numberOfInvalids();
                if (errors) {
                    var message = 'Vyplňte prosím povinné položky';
                    $("div.error span").html(message);
                    $("div.boxerror").show();
                } else {
                    $("div.boxerror").hide();
                }
            },
            // pravidla formularu
            rules: {
                // cena musi byt cislo
                price: {
                        required: true,
                        number: true
                }
            }
        })
    }
    // povesime datepicker na datumy
    $("#date").datepicker({ dateFormat: 'dd.mm.yy' });
});