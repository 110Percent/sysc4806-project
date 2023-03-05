// Create an array of 100 values
var myArray = [];
for (var i = 1; i <= 100; i++) {
    myArray.push("Value " + i);
}

for (var i = 0; i < 100; i++) {
    var li = document.createElement("li");
    li.appendChild(document.createTextNode(myArray[i]));
    document.getElementById("myList").appendChild(li);
}