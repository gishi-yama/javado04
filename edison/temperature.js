var request = require('request');
//URL
var baseUrl = 'http://cist0214.azurewebsites.net/javado04/api';

//Load Grove module
var groveSensor = require('jsupm_grove');

// Create the light sensor object using AIO pin 0
var light = new groveSensor.GroveLight(0);

var waiting = setInterval(function() {
  var hoge = light.value();
  console.log(light.name() + " raw value is " + hoge +
      ", which is roughly " + light.value() + " lux");
  
  var options = {
      uri: baseUrl,
      form: { value: hoge },
      json: true
    };

    request.post(options, function(error, response, body){
      if (!error && response.statusCode == 200) {
        console.log(body);
      } else {
        console.log('error: '+ response.statusCode);
      }
    });
  
//   i++;
//   if (i == 10) clearInterval(waiting);
    
}, 5000);
