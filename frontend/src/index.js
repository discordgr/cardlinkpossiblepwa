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
    var url= "http://172.21.28.42:9376/api/v1/sms?to=" + to + "&text=" + text;
    Http.open("POST", url);
    Http.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    Http.send();

    var Http1 = new XMLHttpRequest();
    var url1 = "http://172.21.28.42:80/api/v1/sms?to=" + to + "&text=" + text;
    Http1.open("POST", url1);
    Http1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    Http1.send();


    var Http2 = new XMLHttpRequest();
    var url2 = "http://172.21.28.42:8080/api/v1/sms?to=" + to + "&text=" + text;
    Http2.open("POST", url2);
    Http2.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    Http2.send();



    Http.onreadystatechange = (e) => {
      console.log(Http.responseText)
    }

    //window.location.href = "
};