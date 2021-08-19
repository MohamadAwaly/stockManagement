$(document).ready(function () {
    //Var add User
    var login = $('#login'),
        lastName = $('.lastNameClass'),
        firstName = $('.firstNameClass'),
        dayOfBirth = $('.dayOfBirthClass'),
        vat = $('.vatClass'),
        mail = $('.emailClass'),
        password = $('.passwordClass'),
        rpassword = $('.rpPasswordClass');

    //Minimum eight characters, at least one upper case English letter, one lower case English letter, one number and one special character
    var regPass = new RegExp("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");
    var regEmail = new RegExp(/[a-zA-Z0-9\.]{1,}[@][a-zA-Z0-9\.]{1,}[\.][a-zA-Z0-9\.]{1,}$/);
    var regVat = new RegExp("^(BE){0,1}[0]{0,1}[0-9]{9}$");

    //Form addUser & updateUser

    login.keyup(function () {
        var errorVal = $(this).val();
        var errorLogin = document.getElementById("errorLogin");
        var errorUserExist = document.getElementById("errorUserExist");
        if (errorVal.length < 4) {
            console.log("login");
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorLogin.hidden = false;
        } else {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorLogin.hidden = true;
        }
        //test ajax
        $.ajax({
            type: "POST",
            url: 'UserAjaxCheckUserExist',
            data: {
                login: login.val(),
            },
            // dataType : 'json',
        }).done(function (data) {
            $('#error').attr('value', data);
            if (data == "error") {
                login.removeClass("is-valid");
                login.addClass("is-invalid");
                $('#errorUserExist').html("l'utilisateur " + login.val() + " existe deja");
                errorUserExist.hidden = false;
            } else {
                login.removeClass("is-invalid");
                login.addClass("is-valid");
                errorUserExist.hidden = true;
            }
        });
    });
    lastName.keyup(function () {
        var errorValLastName = $(this).val();
        var errorlastName = document.getElementById("errorlastName");
        if (errorValLastName.length < 3) {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorlastName.hidden = false;
        } else {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorlastName.hidden = true;
        }
    })
    firstName.keyup(function () {
        var errorVal = $(this).val();
        var errorfirstName = document.getElementById("errorfirstName");
        if (errorVal.length < 3) {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorfirstName.hidden = false;
        } else {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorfirstName.hidden = true;
        }
    })
    dayOfBirth.keyup(function () {
        const months = [
            'January',
            'February',
            'March',
            'April',
            'May',
            'June',
            'July',
            'August',
            'September',
            'October',
            'November',
            'December'
        ]
        var errordate = document.getElementById("errodayOfBirth");
        var errordate17ans = document.getElementById("errodayOfBirth17ans");
        var today = new Date();
        var dateFormLenght = $('.dayOfBirthClass').val();
        var dateForm = new Date($('.dayOfBirthClass').val());
        if (dateFormLenght.length == 10) {
            if (dateForm.getUTCFullYear() > 1900) {
                var dateOfBirth = new Date(dateForm.getDate() + " " + months[dateForm.getMonth()] + " " + dateForm.getUTCFullYear());
                var resultDay = dateDiffInDays(dateOfBirth, today);
                var resultMons = Math.round(resultDay / 30);
                var resultYears = Math.round(resultMons / 12);
                if (resultYears > 17 && resultYears < 100) {
                    console.log("ok");
                    $(this).removeClass("is-invalid");
                    $(this).addClass("is-valid");
                    errordate.hidden = true;
                    errordate17ans.hidden = true;
                } else {
                    errordate17ans.hidden = false;
                }
            }
        } else {
            console.log("non ok");
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errordate.hidden = false;
        }

    })
    vat.keyup(function () {
        var vatVal = $(this).val();
        var errorTva = document.getElementById("errorTva");
        if (regVat.test(vatVal)) {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorTva.hidden = true;
        } else {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorTva.hidden = false;
        }
    })
    mail.keyup(function () {
        var email = $(this).val();
        var errorEmail = document.getElementById("errorEmail");
        console.log("mail");
        if (email.length > 0){
            if (regEmail.test(email)) {
                $(this).removeClass("is-invalid");
                $(this).addClass("is-valid");
                errorEmail.hidden = true;
            } else {
                $(this).removeClass("is-valid");
                $(this).addClass("is-invalid");
                errorEmail.hidden = false;
            }
        }
    })
    password.keyup(function () {
        var errorPass = document.getElementById("erroPassword");
        var errorRP = document.getElementById("errorRP");
        var pass = $(this).val();
        var rpass = $(this).val();
        if (regPass.test(pass)) {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorPass.hidden = true;
        } else {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorPass.hidden = false;
        }
    })
    rpassword.keyup(function () {
        var errorRpass = document.getElementById("errorRPassword");
        var rpass = $(this).val();
        var pass = password.val();
        if (rpass == pass) {
            console.log("les mot de passe correpond");
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorRpass.hidden = true;
        } else {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorRpass.hidden = false;
            console.log("les mot de passe ne correponde pas")
        }

    })


    /*Color lign on double click*/
    $('.usersList').dblclick(function () {
        console.log("test dbclick");
        $('.usersList').removeAttr('style');
        $(this).css('background', '#31B0D5');
        $('#selectedUserid').attr('value', $(this).find("td").eq(0).html());
        $('#selectedUserLogin').attr('value', $(this).find("td").eq(1).html());
    });
    //add the information in the field to add an address
    $('#idAddAdress').click(function () {
        $('#selectedUseridUpdate').attr('value', $('#iduserUpdate').val());
        $('#selectedUserLoginUpdate').attr('value', $('#loginUpdate').val());
    });
    //add id-adress in the field to update adress selected
    $('.adressList').dblclick(function () {
        $('#selected-IdAdress').attr('value', $(this).find("td").eq(0).html());
    });

});

/**
 * Return the difference between the two date in day
 * @param a Date of birth
 * @param b Today
 * @returns {number}
 */
function dateDiffInDays(a, b) {
    const _MS_PER_DAY = 1000 * 60 * 60 * 24;

    // Discard the time and time-zone information.
    const utc1 = Date.UTC(a.getFullYear(), a.getMonth(), a.getDate());
    const utc2 = Date.UTC(b.getFullYear(), b.getMonth(), b.getDate());

    return Math.floor((utc2 - utc1) / _MS_PER_DAY);
}

// function dateFormat (date){
//     // const utc = new Date(date.getDate() + " " + [date.getMonth()] + " " + date.getUTCFullYear());
//     // const utc = new Date(date.getUTCFullYear() + " " + [date.getMonth()] + " " + date.getDate());
//     return utc;
// }



