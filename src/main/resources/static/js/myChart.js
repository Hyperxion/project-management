var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);

var arrayLenght = chartJsonArray.length;
var numericData = [];
var labelData = [];

for (var i=0; i < arrayLenght; i++) {
    numericData[i] = chartJsonArray[i].value;
    labelData[i] = chartJsonArray[i].label;
}

// For a pie chart
new Chart(document.getElementById("myPieChart"), {
    type: 'pie',
    // The data for our dataset
    data: {
        labels: labelData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ["#3e95cd", "8e5ea2", "#3cba9f"],
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
        title: {
            display: true,
            text: "Project Statuses"
        }
    }
});

/*
This is how chartData look like passed from controller to Thymeleaf HTML

[{&quot;value&quot;:1,&quot;label&quot;:&quot;COMPLETED&quot;},{&quot;value&quot;:2,&quot;label&quot;:&quot;INPROGRESS&quot;},{&quot;value&quot;:1,&quot;label&quot;:&quot;NOTSTARTED&quot;}]

This is not pure JSON yet, because we want quites ("") instead of &quot. This function will take care of it
 */
function decodeHtml(html) {
    var txt = document.createElement("textarea");
    txt.innerHTML = html;

    return txt.value;
}