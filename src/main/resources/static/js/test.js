var pieCtx = document.getElementById('pieChart').getContext('2d');
var pieChart = new Chart(pieCtx, {
  type: 'pie',
  data: {
    labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
    datasets: [{
      label: 'Colors',
      data: [12, 19, 3, 5, 2, 3],
      backgroundColor: [
        'rgba(255, 99, 132, 0.8)',
        'rgba(54, 162, 235, 0.8)',
        'rgba(255, 206, 86, 0.8)',
        'rgba(75, 192, 192, 0.8)',
        'rgba(153, 102, 255, 0.8)',
        'rgba(255, 159, 64, 0.8)'
      ]
    }]
  }
});

var barCtx = document.getElementById('barChart').getContext('2d');
var barChart = new Chart(barCtx, {
  type: 'bar',
  data: {
    labels: ['January', 'February', 'March', 'April', 'May', 'June'],
    datasets: [{
      label: 'Randomly Generated Values',
      data: [12, 19, 3, 5, 2, 3],
      backgroundColor: 'rgba(54, 162, 235, 0.8)',
      borderColor: 'rgba(54, 162, 235, 1)',
      borderWidth: 1
    }]
  },
  options: {
    scales: {
      y: {
        beginAtZero: true
      }
    }
  }
});

var histCtx = document.getElementById('histogram').getContext('2d');
var histogram = new Chart(histCtx, {
  type: 'bar',
  data: {
    labels: ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', '40', '41', '42', '43', '44', '45', '46', '47', '48', '49', '50', '51', '52', '53', '54', '55', '56', '57', '58', '59', '60', '61', '62', '63', '64', '65', '66', '67', '68', '69', '70', '71', '72', '73', '74', '75', '76', '77', '78', '79', '80', '81', '82', '83', '84', '85', '86', '87', '88', '89', '90', '91', '92', '93', '94', '95', '96', '97', '98', '99', '100'],
    datasets: [{
      label: 'Randomly Generated Values',
      data: [35, 87, 12, 56, 70, 41, 98, 6, 66, 92, 44, 50, 28, 80, 57, 81, 39, 62, 53, 14, 59, 30, 7, 73, 15, 20, 79, 10, 69, 90, 77, 99, 2, 64, 61, 9, 23, 18, 48, 76, 22, 47, 83, 36, 84, 33, 49, 40, 86, 45, 74, 42, 68, 46, 71, 75, 97, 54, 31, 11, 96, 58, 38, 67, 25, 27, 65, 52, 95, 55, 24, 60, 8, 37, 16, 43, 3, 72, 89, 19, 34, 78, 85, 51, 1, 21, 91, 26, 5, 4, 88, 17, 32, 63, 94, 82, 13, 9, 12, 2],
      backgroundColor: 'rgba(255, 99, 132, 0.8)',
      borderColor: 'rgba(255, 99, 132, 1)',
      borderWidth: 1
    }]
  },
  options: {
    scales: {
      x: {
        grid: {
          display: false
        }
      },
      y: {
        beginAtZero: true
      }
    }
  }
});
