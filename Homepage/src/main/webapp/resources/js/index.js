$(document).ready(function () {
    $("#indexCarousel").carousel({
        interval: 2000
    });
});

function goTop() {
    document.documentElement.scrollTop = 0;
}