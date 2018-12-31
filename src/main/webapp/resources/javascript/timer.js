<script>

function startTimer(duration, display) {
	var timer = duration, minutes, seconds;
	var clearint = setInterval(function () {
	    minutes = parseInt(timer / 60, 10);
	    seconds = parseInt(timer % 60, 10);
	
	    minutes = minutes < 10 ? "0" + minutes : minutes;
	    seconds = seconds < 10 ? "0" + seconds : seconds;
	
	    display.textContent = minutes + " " + " " + seconds;
	
	    if (--timer < 0) {
	      timer = -1;
	      display.textContent = "Expired";
	      localStorage.clear();
	      clearInterval(clearint);
	    }
	    console.log(parseInt(seconds));
	  
	    window.localStorage.setItem("seconds",seconds);
	    window.localStorage.setItem("minutes",minutes);
	    window.localStorage.setItem("timer",timer);
	}, 1000);
}


function disableF5(e) { 
	if ((e.which || e.keyCode) == 116) { 
		e.preventDefault(); 
	};
}

window.onload = function () {
	  sec = parseInt(window.localStorage.getItem("seconds"));
	  min = parseInt(window.localStorage.getItem("minutes"));
	  timer = parseInt(window.localStorage.getItem("timer"));
	
	  if (timer>0) {
	    var fiveMinutes = (parseInt(min*60)+sec);
	  } else {
	    var fiveMinutes = 30;
	  }
	    // var fiveMinutes = 60 * 5;
	  display = document.querySelector('#demo');
	  startTimer(fiveMinutes, display);
}

</script>  
