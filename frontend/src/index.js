window.goToMobileApp = function() {
    var currency = "EUR";
    var amount = document.getElementById("Amount").value;
    var installments = document.getElementById("Installments").value;
    var tip = document.getElementById("Tip").value;
    window.location.href = "cardlink-possible://sale?amount=" + "€" + amount + "&currency=" + currency + "&installments=" + installments + "&tip=" + "€" + tip;
}

window.sendSMS = function(){
    var to = document.getElementById("phoneNumber").value;
    var text = "Amount: €" + document.getElementById("Amount").value + "%0A" +
                "Installments: " + document.getElementById("Installments").value + "%0A" +
                "Tip: €" + document.getElementById("Tip").value;
    var Http = new XMLHttpRequest();
    var url= "http://192.168.2.2:8090/api/v1/sms?to=" + to + "&text=" + text;
    Http.open("POST", url);
    Http.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    Http.send();


    Http.onreadystatechange = (e) => {
      console.log(Http.responseText)
    }

    //window.location.href = "
};