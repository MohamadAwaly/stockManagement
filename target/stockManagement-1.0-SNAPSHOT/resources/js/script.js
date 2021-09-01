/**
 *  PART USER
 */
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

    // Error add user
    var errorLoginValide = "true",
        errorLastNameValide = "true",
        errorFirstNameValide = "true",
        errorDayOfBirthValide = "true",
        errorVatValide = "true",
        errorMailValide = "true",
        errorPasswordValide = "true",
        errorRpPAsswordValide = "true";

    //Minimum eight characters, at least one upper case English letter, one lower case English letter, one number and one special character
    var regPass = new RegExp("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");
    var regEmail = new RegExp(/[a-zA-Z0-9\.]{1,}[@][a-zA-Z0-9\.]{1,}[\.][a-zA-Z0-9\.]{1,}$/);
    var regVat = new RegExp("^(BE){0,1}[0]{0,1}[0-9]{9}$");
    var noNumber = new RegExp("^[a-zA-Z]+$", "i");

    //Form addUser & updateUser
    login.keyup(function () {
        var errorVal = $(this).val();
        var errorLogin = document.getElementById("errorLogin");
        var errorUserExist = document.getElementById("errorUserExist");
        if (errorVal.length < 4) {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorLogin.hidden = false;
            errorLoginValide = "true";
            $('#btn-addUser').prop('disabled', true);
        } else {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorLogin.hidden = true;
            errorLoginValide = "false";
            $('#btn-addUser').prop('disabled', false);
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
                errorLoginValide = "true";
                $('#btn-addUser').prop('disabled', true);
                errorUserExist.hidden = false;
            } else {
                login.removeClass("is-invalid");
                login.addClass("is-valid");
                errorUserExist.hidden = true;
                errorLoginValide = "false";
                $('#btn-addUser').prop('disabled', false);
            }
        });
    });
    lastName.keyup(function () {
        var errorValLastName = $(this).val();
        var errorlastName = document.getElementById("errorlastName");
        // errorValLastName.length < 3
        if (noNumber.test(errorValLastName) === false || errorValLastName.length < 3) {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorlastName.hidden = false;
            errorLastNameValide = "true";
            $('#btn-addUser').prop('disabled', true);

        } else {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorlastName.hidden = true;
            errorLastNameValide = "false"
            $('#btn-addUser').prop('disabled', false);
        }
    })
    firstName.keyup(function () {
        var errorVal = $(this).val();
        var errorfirstName = document.getElementById("errorfirstName");
        if (noNumber.test(errorVal) === false || errorVal.length < 3) {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorfirstName.hidden = false;
            errorFirstNameValide = "true";
            $('#btn-addUser').prop('disabled', true);
        } else {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorfirstName.hidden = true;
            errorFirstNameValide = "false"
            $('#btn-addUser').prop('disabled', false);
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
        console.log("dateform: " + dateFormLenght.length);
        console.log("dateform: " + dateFormLenght);

        if (dateFormLenght.length == 10 ) {
            if (dateForm.getUTCFullYear() > 1900) {
                var dateOfBirth = new Date(dateForm.getDate() + " " + months[dateForm.getMonth()] + " " + dateForm.getUTCFullYear());
                var resultDay = dateDiffInDays(dateOfBirth, today);
                var resultMons = Math.round(resultDay / 30);
                var resultYears = Math.round(resultMons / 12);
                if (resultYears > 17 && resultYears < 100) {
                    $(this).removeClass("is-invalid");
                    $(this).addClass("is-valid");
                    errordate.hidden = true;
                    errordate17ans.hidden = true;
                    errorDayOfBirthValide = "false";
                    $('#btn-addUser').prop('disabled', false);
                } else {
                    errordate17ans.hidden = false;
                    errorDayOfBirthValide = "true";
                    $('#btn-addUser').prop('disabled', true);
                }
            }
        } else {
            $(this).removeClass("is-valid");
            $(this).removeClass("is-invalid");
            errordate.hidden = true;
            errorDayOfBirthValide = "false";
            $('#btn-addUser').prop('disabled', false);
        }

    })
    vat.keyup(function () {
        var vatVal = $(this).val();
        var errorTva = document.getElementById("errorTva");
        if (regVat.test(vatVal)) {
            $(this).removeClass("is-invalid");
            $(this).addClass("is-valid");
            errorTva.hidden = true;
            errorVatValide = "false";
            $('#btn-addUser').prop('disabled', false);
        } else {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorTva.hidden = false;
            errorVatValide = "true";
            $('#btn-addUser').prop('disabled', true);
        }
    })
    mail.keyup(function () {
        var email = $(this).val();
        var errorEmail = document.getElementById("errorEmail");
        console.log("mail");
        if (email.length > 0) {
            if (regEmail.test(email)) {
                $(this).removeClass("is-invalid");
                $(this).addClass("is-valid");
                errorEmail.hidden = true;
                errorMailValide = "false";
                $('#btn-addUser').prop('disabled', false);
            } else {
                $(this).removeClass("is-valid");
                $(this).addClass("is-invalid");
                errorEmail.hidden = false;
                errorMailValide = "true";
                $('#btn-addUser').prop('disabled', true);
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
            errorPasswordValide = "false";
            $('#btn-addUser').prop('disabled', false);
        } else {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorPass.hidden = false;
            errorPasswordValide = "true";
            $('#btn-addUser').prop('disabled', true);
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
            errorRpPAsswordValide = "false";
            $('#btn-addUser').prop('disabled', false);
        } else {
            $(this).removeClass("is-valid");
            $(this).addClass("is-invalid");
            errorRpass.hidden = false;
            console.log("Password does not match")
            errorRpPAsswordValide = "true";
            $('#btn-addUser').prop('disabled', true);
        }

    })
    //|| onlyNumber.test(errorValLastName)

    /**
     * disabled btn add new user if there is any error
     */
    $('#btn-addUser').click( function () {
        if (errorLoginValide == "true" && errorLastNameValide == "true" && errorFirstNameValide == "true" && errorDayOfBirthValide == "true" && errorVatValide == "true" && errorMailValide == "true" && errorPasswordValide == "true" && errorRpPAsswordValide == "true") {
            $(this).prop('disabled', true);
        } else {
            $(this).prop('disabled', false);
        }
    });

    /*Color lign on double click*/
    $(document).on('dblclick','.usersList',function (){
        console.log("userList . dblclick");
        $('#updateUserbtn').removeAttr('disabled');
        $('.usersList').removeAttr('style');
        $(this).css('background', '#31B0D5');
        $('#selectedUserid').attr('value', $(this).find("td").eq(0).html());
        $('#selectedUserLogin').attr('value', $(this).find("td").eq(1).html());
    })
    // $('.usersList').dblclick(function () {
    //     console.log("userList . dblclick");
    //     $('#updateUserbtn').removeAttr('disabled');
    //     $('.usersList').removeAttr('style');
    //     $(this).css('background', '#31B0D5');
    //     $('#selectedUserid').attr('value', $(this).find("td").eq(0).html());
    //     $('#selectedUserLogin').attr('value', $(this).find("td").eq(1).html());
    // });
    //add the information in the field to add an address
    $('#idAddAdress').click(function () {
        $('#selectedUseridUpdate').attr('value', $('#iduserUpdate').val());
        $('#selectedUserLoginUpdate').attr('value', $('#loginUpdate').val());
    });
    //add id-adress in the field to update adress selected
    $('.adressList').dblclick(function () {
        $('#selected-IdAdress').attr('value', $(this).find("td").eq(0).html());
        $('#id-UpdateAdress').prop('disabled', false);
        $('#user-id').attr('value', $('#iduserUpdate').val());
    });
    // retrieve id and login user to update from session
    $('#id-btn-profile').on('click', function () {
        var login = $('#logUserProfile').text();
        var id = $('#idUserprofile').text();

        console.log("user: " + login);
        console.log("id: " + id);
    });

    /**
     * Users search bar
     *by login, lastName, firstName, mail, roles")
     */
    $('#usersSearchBar').keyup(function (){
        $.ajax({
            url: "UsersSearchAjax",
            method: "POST",
            data: {
                searchUserBar: $('#usersSearchBar').val()
            }
        }).done(function (data) {
            $('.usersList').remove();
            data.forEach(res => {
                let tva = res[7] == null ? "": res[7];
                let mail = res[8] == null ? "": res[8];
                let dayofbirth = res[5] == null ? "": res[5];
                $('#tableListUser').append(
                    "<tr class='usersList' id='" + res[0] + "'>" +
                        "<td hidden>" + res[0] + "</td>" +
                        "<td>" + res[1] + "</td>" +
                        "<td>" + res[2] + "</td>" +
                        "<td>" + res[3] + "</td>" +
                        "<td>" + res[4] + "</td>" +
                        "<td>" + dayofbirth + "</td>" +
                        "<td>" + res[6] + "</td>" +
                        "<td>" + tva + "</td>" +
                        "<td>" + mail+ "</td>" +
                        "<td>" + res[9] + "</td>" +
                    "</tr>");
            })
        });
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

/**
 *  PARTIE FOURNISSEUR
 */

/**
 * Page : supplierCreate
 * Contrôle fournisseur si Existe
 * Retourne 1 = existe ou 0 = n'existe pas
 * @author Jiwaii
 */
$(document).on('keyup', '#formNewSupplierName', function () {
    let inputNewSupplierName = $(this).val().toString().trim();
    $.ajax({
        url: "SupplierExist",
        method: "POST",
        data: {
            name: inputNewSupplierName
        }
    }).done(function (data) {
            // console.log('Response Data : '+data);
            let supplierExist = parseInt(data.toString().trim());
            let inputName = $('#formNewSupplierName').val().trim();
            if (supplierExist == 1 || inputName == '') {
                $('#formNewSupplierName').attr('class', 'form-control is-invalid');
                $('#submitNewSupplier').attr('disabled', 'true');
            } else {
                $('#formNewSupplierName').attr('class', 'form-control is-valid');
                $('#submitNewSupplier').removeAttr('disabled');
            }
        }
    )
});
/**
 * Page : CommandSupplierCreate.jsp
 * Quantity control
 */
$(document).on('keyup', '.inputQuantity', function () {
    let qte = $(this).val();
    console.log('is a number : ' + !isNaN(qte));
    if (isNaN(qte)) {
        $(this).val('');//$('.inputQuantity').val('');
    }
})
/**
 * Page : CommandSupplierCreate.jsp
 * Btn Add new row product to the command
 */
$(document).on('click', '#CmdSuppAddProductToCmd', function () {
    let nbProduct = $('#tableProducts tr').length;
    $('#product1').clone().appendTo('#tableProducts');
    $('#tableProducts tr:last').attr('id', 'product' + nbProduct);
    $('#tableProducts tr:last td:first select').attr('name', 'Product' + nbProduct);
    $('#tableProducts tr:last td:last input').attr('name', 'Quantity' + nbProduct);
    $('#nbRowProduct').attr('value', nbProduct);
    console.log('bouton ajouter produit , nb produits : ' + nbProduct);

})


/**
 * Page : CommandSupplierShowAll.jsp
 * search Bar and AJAX
 */
$(document).on('keyup', '#CmdSupBchSearchBar', function () {
    $.ajax({
        url: "CommandSuppliersSearch",
        method: "POST",
        data: {
            searchBar: $('#CmdSupBchSearchBar').val()
        }
    }).done(function (data) {
        let json = data; // pas de parse Json, déjà fait dans la servlet
        console.log(json);
        $('#CmdSupListContent').empty();
        json.forEach(obj => {
            //cs.idCommandSuppliers ,s.name, cs.orderDate, csb.lotQuantity, b.idBatch, p.designation,cs.users.lastName
            $('#CmdSupListContent').append("" +
                "<tr id='" + obj[0] + "'>" +
                "<td>" + obj[0] + "</td>" +
                "<td>" + obj[1] + "</td>" +
                "<td>" + obj[2] + "</td>" +
                "<td>" + obj[3] + "</td>" +
                "</tr>");
        })
    });
});
/**
 * Page : CommandSupplierShowAll.jsp
 * Get PDF Button
 */
$(document).on('click', '#GetCmdSuppPdf', function () {
    $.ajax({
        url: "GetPdfCmdSupp",
        method: "POST",
        data: {
            searchBar: $('#CmdSupBchSearchBar').val()
        }
    }).done(function (data) {
        alert('Enregistré dans C:\\StockManagement\\ ');
    })
});

$(document).on('dblclick','.rowCommand', function (){
    let idCmdSupp = (this).getAttribute('id');
    console.log(idCmdSupp);
    window.location.href = 'CommandSupplierBatchShowAll?idCmdSupp='+idCmdSupp;
})




