window.goToMobileApp = function() {
    var currency = "EUR";
    var amount = document.getElementById("Amount").value;
    var installments = document.getElementById("Installments").value;
    var tip = document.getElementById("Tip").value;
    window.location.href = "cardlink-possible://sale?amount=" + amount + "&currency=" + currency + "&installments=" + installments + "&tip=" + tip;
};